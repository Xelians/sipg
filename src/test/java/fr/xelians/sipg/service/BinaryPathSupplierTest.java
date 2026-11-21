/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package fr.xelians.sipg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.xelians.sipg.TestInit;
import fr.xelians.sipg.model.ArchiveTransfer;
import fr.xelians.sipg.model.ArchiveUnit;
import fr.xelians.sipg.service.sedajson.SedaJsonConfig;
import fr.xelians.sipg.service.sedajson.SedaJsonConfigBuilder;
import fr.xelians.sipg.service.sedajson.SedaJsonService;
import fr.xelians.sipg.service.sedav2.SedaConfig;
import fr.xelians.sipg.service.sedav2.SedaConfigBuilder;
import fr.xelians.sipg.service.sedav2.Sedav2Service;
import fr.xelians.sipg.utils.SipException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.zip.ZipFile;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;

/**
 * Les fournisseurs de path sont appelés par les tâches de zip : un fournisseur qui crée le fichier
 * qu'il retourne n'en laisse exister qu'un à la fois par thread de conversion.
 */
@ExtendWith(TestInit.class)
class BinaryPathSupplierTest {

  private static final Path BINARY = Paths.get(TestInit.TEST_RESOURCES + "dummy.pdf");
  private static final int UNITS = 3;

  // Un seul thread de conversion : les fichiers fournis se succèdent sans jamais coexister
  private final SedaConfig sedaConfig =
      SedaConfigBuilder.builder().validate(true).strict(false).thread(1).build();
  private final SedaJsonConfig jsonConfig =
      SedaJsonConfigBuilder.builder().validate(true).strict(false).thread(1).build();

  // Le répertoire où les fournisseurs créent leurs fichiers
  @TempDir Path supplyDir;
  @TempDir Path outputDir;

  // Le nombre de fichiers présents dans le répertoire à chaque appel d'un fournisseur
  private final List<Long> filesAtCall = Collections.synchronizedList(new ArrayList<>());

  @Test
  void testSedav21SuppliesEachBinaryWhenZipped() throws IOException {
    assertSuppliesEachBinaryWhenZipped(Sedav2Service.getInstance());
  }

  @Test
  void testSedav22SuppliesEachBinaryWhenZipped() throws IOException {
    assertSuppliesEachBinaryWhenZipped(Sedav2Service.getV22Instance());
  }

  @Test
  void testSedav23SuppliesEachBinaryWhenZipped() throws IOException {
    assertSuppliesEachBinaryWhenZipped(Sedav2Service.getV23Instance());
  }

  @Test
  void testSedaJsonSuppliesEachBinaryWhenZipped() throws IOException {
    Path zipPath = outputDir.resolve("sip.zip");
    SedaJsonService.getInstance().write(createSip(this::supply), zipPath, jsonConfig);

    assertEquals(Collections.nCopies(UNITS, 0L), filesAtCall);
    assertEquals(0, countFiles(supplyDir));
    assertEquals(UNITS, countBinaries(zipPath, "content/"));
    // Le contrôle de l'archive retrouve chaque binaire par son URI, avec sa taille et son empreinte
    SedaJsonService.getInstance().validate(zipPath, jsonConfig);
  }

  @Test
  void testSedav2UsesSuppliedFilenameWhenFileInfoHasNone() throws IOException {
    ArchiveTransfer sip = createSip(this::supply);
    sip.getArchiveUnits().get(1).getFileInfo().setFilename("named.pdf");

    for (Sedav2Service service : sedav2Services()) {
      String manifest;
      try (InputStream is = service.marshal(sip, sedaConfig)) {
        manifest = new String(is.readAllBytes(), StandardCharsets.UTF_8);
      }

      assertEquals(1, StringUtils.countMatches(manifest, "<Filename>named.pdf</Filename>"));
      // Les deux autres objets portent le nom du fichier fourni
      assertEquals(UNITS - 1, StringUtils.countMatches(manifest, "<Filename>supplied"));
    }
  }

  @Test
  void testSedaJsonUsesSuppliedFilenameWhenFileInfoHasNone() throws IOException {
    ArchiveTransfer sip = createSip(this::supply);
    sip.getArchiveUnits().get(1).getFileInfo().setFilename("named.pdf");

    JsonNode manifest;
    try (InputStream is = SedaJsonService.getInstance().marshal(sip, jsonConfig)) {
      manifest = new ObjectMapper().readTree(is);
    }

    List<String> filenames = new ArrayList<>();
    for (JsonNode unit : manifest.get("ArchiveUnits")) {
      JsonNode binary = unit.get("BinaryDataObjects").get(0);
      filenames.add(binary.get("FileInfo").get("Filename").asText());
      assertTrue(binary.get("Uri").asText().matches("content/\\d+_supplied.*\\.pdf"));
    }
    assertEquals("named.pdf", filenames.get(1));
    assertTrue(filenames.get(0).startsWith("supplied"));
    assertTrue(filenames.get(2).startsWith("supplied"));
  }

  @Test
  void testFailingSupplierStopsSupplying() throws IOException {
    // Le deuxième fournisseur échoue : le premier fichier est déjà zippé et supprimé, le troisième
    // n'est jamais demandé
    AtomicInteger calls = new AtomicInteger();
    Supplier<Path> failingOnSecond =
        () -> {
          if (calls.incrementAndGet() == 2) {
            throw new IllegalStateException("No binary");
          }
          return supply();
        };

    Path zipPath = outputDir.resolve("sip.zip");
    for (Sedav2Service service : sedav2Services()) {
      calls.set(0);
      SipException ex =
          assertThrows(
              SipException.class,
              () -> service.write(createSip(failingOnSecond), zipPath, sedaConfig));
      assertEquals("No binary", ExceptionUtils.getRootCause(ex).getMessage());
      assertEquals(2, calls.get());
      assertEquals(0, countFiles(supplyDir));
    }
  }

  private void assertSuppliesEachBinaryWhenZipped(Sedav2Service service) throws IOException {
    Path zipPath = outputDir.resolve("sip.zip");
    service.write(createSip(this::supply), zipPath, sedaConfig);

    // Appelés pendant le parcours des unités, les fournisseurs auraient vu 0, 1 puis 2 fichiers
    assertEquals(Collections.nCopies(UNITS, 0L), filesAtCall);
    assertEquals(0, countFiles(supplyDir));
    assertEquals(UNITS, countBinaries(zipPath, "Content/"));
    // Le contrôle de l'archive retrouve chaque binaire par son URI, avec sa taille et son empreinte
    service.validate(zipPath, sedaConfig);
  }

  private static List<Sedav2Service> sedav2Services() {
    return List.of(
        Sedav2Service.getInstance(),
        Sedav2Service.getV22Instance(),
        Sedav2Service.getV23Instance());
  }

  // Crée le fichier à l'appel, comme un fournisseur qui extrait le binaire d'un stockage
  private Path supply() {
    try {
      filesAtCall.add(countFiles(supplyDir));
      Path path = Files.createTempFile(supplyDir, "supplied", ".pdf");
      return Files.write(path, Files.readAllBytes(BINARY));
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private static ArchiveTransfer createSip(Supplier<Path> supplier) {
    ArchiveTransfer archiveTransfer = new ArchiveTransfer();
    archiveTransfer.setArchivalAgreement("My Archival Agreement");
    archiveTransfer.setArchivalAgency("AG001", "");
    archiveTransfer.setTransferringAgency("AG002", "");
    for (int i = 1; i <= UNITS; i++) {
      ArchiveUnit unit = new ArchiveUnit();
      unit.setBinaryPathSupplier(supplier);
      unit.addTitle("My_Title_" + i);
      archiveTransfer.addArchiveUnit(unit);
    }
    return archiveTransfer;
  }

  private static long countFiles(Path dir) throws IOException {
    try (Stream<Path> files = Files.list(dir)) {
      return files.count();
    }
  }

  private static long countBinaries(Path zipPath, String contentDir) throws IOException {
    try (ZipFile zipFile = new ZipFile(zipPath.toFile())) {
      return zipFile.stream()
          .filter(e -> !e.isDirectory() && e.getName().startsWith(contentDir))
          .count();
    }
  }
}
