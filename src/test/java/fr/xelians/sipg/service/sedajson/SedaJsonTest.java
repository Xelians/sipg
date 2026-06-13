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
package fr.xelians.sipg.service.sedajson;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.jimfs.Jimfs;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import fr.xelians.sipg.SipFactory;
import fr.xelians.sipg.TestInit;
import fr.xelians.sipg.model.ArchiveTransfer;
import fr.xelians.sipg.model.ArchiveUnit;
import fr.xelians.sipg.model.ArchiveUnitRef;
import fr.xelians.sipg.service.common.ProgressEvent;
import fr.xelians.sipg.service.common.ProgressState;
import fr.xelians.sipg.utils.SipException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The SEDA JSON integration test.
 *
 * @author Emmanuel Deviller
 */
@ExtendWith(TestInit.class)
class SedaJsonTest {

  public static final String SEDA_JSON = TestInit.TEST_RESOURCES + "seda-json/";

  private static final Logger LOGGER = LoggerFactory.getLogger(SedaJsonTest.class);
  private static final ObjectMapper MAPPER = new ObjectMapper();

  private final SedaJsonConfig jsonConfig =
      SedaJsonConfigBuilder.builder().format(true).validate(true).strict(false).build();
  private final SedaJsonService jsonService = SedaJsonService.getInstance();

  private JsonNode readManifest(Path zipPath) throws Exception {
    try (FileSystem zipArchive =
        FileSystems.newFileSystem(zipPath, Collections.<String, Object>emptyMap())) {
      try (InputStream is = Files.newInputStream(zipArchive.getPath("manifest.json"))) {
        return MAPPER.readTree(is);
      }
    }
  }

  private static List<String> fieldNames(JsonNode node) {
    List<String> names = new ArrayList<>();
    for (Iterator<String> it = node.fieldNames(); it.hasNext(); ) {
      names.add(it.next());
    }
    return names;
  }

  /** Test create mini sip. */
  @Test
  void testCreateMiniSip() throws Exception {
    ArchiveTransfer archiveTransfer = SipFactory.createMiniSip();
    Path output = Paths.get(TestInit.TEST_RESULTS + "minisip_sedajson.zip");
    jsonService.write(archiveTransfer, output, jsonConfig);
    assertTrue(Files.exists(output));

    // L'ordre des clés de la racine et de l'unité est l'ordre normatif
    JsonNode manifest = readManifest(output);
    assertEquals(
        List.of(
            "SedaJsonVersion",
            "MessageIdentifier",
            "ArchivalAgreement",
            "ArchiveUnits",
            "ArchivalAgency",
            "TransferringAgency"),
        fieldNames(manifest));
    JsonNode unit = manifest.get("ArchiveUnits").get(0);
    assertEquals(List.of("Content", "BinaryDataObjects"), fieldNames(unit));
    assertEquals("My_Title", unit.get("Content").get("Title").asText());

    JsonNode binary = unit.get("BinaryDataObjects").get(0);
    assertEquals("content/1_dummy.pdf", binary.get("Uri").asText());
    assertEquals("SHA-512", binary.get("MessageDigest").get("Algorithm").asText());
    assertTrue(binary.get("Size").asLong() > 0);

    List<ProgressEvent<SedaJsonStep>> events = new ArrayList<>();
    jsonService.validate(output, jsonConfig, events::add);
    assertEquals(SedaJsonStep.START, events.getFirst().step());
    assertEquals(SedaJsonStep.COMPLETE, events.getLast().step());
    assertTrue(events.stream().allMatch(e -> e.status() == ProgressState.SUCCESS));
  }

  /** Test validate archive transfer. */
  @Test
  void testValidateArchiveTransfer() {
    ArchiveTransfer archiveTransfer = SipFactory.createMiniSip();
    assertDoesNotThrow(() -> jsonService.validate(archiveTransfer));
  }

  /** Test marshal mini sip. */
  @Test
  void testMarshalMiniSip() throws Exception {
    ArchiveTransfer archiveTransfer = SipFactory.createMiniSip();
    try (InputStream is = jsonService.marshal(archiveTransfer)) {
      String manifest = new String(is.readAllBytes(), StandardCharsets.UTF_8);
      assertTrue(manifest.startsWith("{\"SedaJsonVersion\":\"1.0\""));
      assertDoesNotThrow(() -> SedaJsonParser.parse(jsonService.marshal(archiveTransfer)));
    }
  }

  /** Test create complex sip. */
  @Test
  void testCreateComplexSip() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createComplexSip(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "complexsip_sedajson.zip");
      jsonService.write(archiveTransfer, output, jsonConfig);
      assertTrue(Files.exists(output));

      jsonService.validate(output, jsonConfig, e -> LOGGER.info(e.toString()));

      JsonNode manifest = readManifest(output);
      JsonNode unit1 = manifest.get("ArchiveUnits").get(0);

      // La règle de gel utilise la clé ReassessingDate (compatibilité esafe)
      JsonNode holdRule = unit1.get("Management").get("HoldRule").get("Rules").get(0);
      assertTrue(holdRule.has("ReassessingDate"));
      assertFalse(holdRule.has("HoldReassessingDate"));

      // Les éléments étendus sont déclarés dans Content : attributs et valeur texte
      JsonNode unit2 = unit1.get("ArchiveUnits").get(0);
      JsonNode extTag1 = unit2.get("Content").get("MyExtTag1");
      assertEquals("val1", extTag1.get("attr1").asText());
      assertEquals("MyExtValue1", extTag1.get("Value").asText());
      JsonNode extTag2 = unit2.get("Content").get("MyExtTag2");
      assertEquals("MyExtValue3", extTag2.get("MyExtTag3").asText());
      assertEquals("MyValue", unit2.get("Content").get("MyExtTag4").asText());

      // Les relations internes sont ignorées en mode non strict, les autres sont conservées
      JsonNode versionOfs = unit2.get("Content").get("RelatedObjectReference").get("IsVersionOfs");
      assertEquals(3, versionOfs.size());

      // SigningInformation est transposé avec les tokens XML
      JsonNode signing = unit1.get("Content").get("SigningInformation");
      assertEquals("SignedDocument", signing.get("SigningRoles").get(0).asText());

      // Les AdditionalProofInformation sont nichés sous un AdditionalProof (symétrie XML/ontologie)
      assertEquals(
          "Ceci n'est pas une signature forgée",
          signing.get("AdditionalProof").get(0).get("AdditionalProofInformation").get(0).asText());

      // L'unité 1 ne contient qu'un objet physique
      assertFalse(unit1.has("BinaryDataObjects"));
      assertEquals(
          "physical-0001", unit1.get("PhysicalDataObjects").get(0).get("PhysicalId").asText());
    }
  }

  /** Test create large sip. */
  @Test
  void testCreateLargeSip() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createLargeSip(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "largesip_sedajson.zip");
      jsonService.write(archiveTransfer, output, jsonConfig);
      assertTrue(Files.exists(output));
      jsonService.validate(output);
    }
  }

  /** Test create deep sip. */
  @Test
  void testCreateDeepSip() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createDeepSip(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "deepsip_sedajson.zip");
      jsonService.write(archiveTransfer, output, jsonConfig);
      assertTrue(Files.exists(output));
      jsonService.validate(output);
    }
  }

  /** Test create full text sip. */
  @Test
  void testCreateFullTextSip() {
    ArchiveTransfer archiveTransfer = SipFactory.createFullTextSip();
    Path output = Paths.get(TestInit.TEST_RESULTS + "fulltextsip_sedajson.zip");
    jsonService.write(archiveTransfer, output, jsonConfig);
    assertTrue(Files.exists(output));
    jsonService.validate(output);
  }

  /** Test create dir sip. */
  @Test
  void testCreateDirSip() throws Exception {
    ArchiveTransfer archiveTransfer = SipFactory.createSipFromDir();
    Path output = Paths.get(TestInit.TEST_RESULTS + "dirsip_sedajson.zip");
    jsonService.write(archiveTransfer, output, jsonConfig);
    assertTrue(Files.exists(output));
    jsonService.validate(output);
  }

  /** Test multilingual titles. */
  @Test
  void testMultilingualTitles() throws Exception {
    ArchiveUnit unit = new ArchiveUnit();
    unit.addTitle("Bonjour", "fr");
    unit.addTitle("Hello", "en");

    ArchiveTransfer archiveTransfer = new ArchiveTransfer();
    archiveTransfer.setArchivalAgreement("My Archival Agreement");
    archiveTransfer.setArchivalAgency("AG001", "");
    archiveTransfer.addArchiveUnit(unit);

    try (InputStream is = jsonService.marshal(archiveTransfer)) {
      JsonNode manifest = MAPPER.readTree(is);
      JsonNode content = manifest.get("ArchiveUnits").get(0).get("Content");
      assertEquals("Bonjour", content.get("Title").asText());
      assertEquals("en", content.get("Titles").get(0).get("Lang").asText());
      assertEquals("Hello", content.get("Titles").get(0).get("Value").asText());
    }
  }

  /** Test without agency fail. */
  @Test
  void testWithoutAgencyFail() {
    ArchiveTransfer archiveTransfer = SipFactory.createWithoutAgencySip();
    Path outputPath = Paths.get(TestInit.TEST_RESULTS + "fail_sedajson.zip");
    assertThrows(SipException.class, () -> jsonService.write(archiveTransfer, outputPath));
  }

  /** Test strict mode fails on non representable constructs. */
  @Test
  void testStrictComplexSipFail() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      // Le sip complexe contient une signature de message et des références entre unités
      ArchiveTransfer archiveTransfer = SipFactory.createComplexSip(fs);
      assertThrows(SipException.class, () -> jsonService.validate(archiveTransfer));
    }
  }

  /** Test strict mode fails on archive unit references. */
  @Test
  void testStrictReferenceFail() {
    ArchiveTransfer archiveTransfer = SipFactory.createMiniSip();
    ArchiveUnit unit = archiveTransfer.getArchiveUnits().getFirst();
    unit.addReference(new ArchiveUnitRef(unit));

    assertThrows(SipException.class, () -> jsonService.validate(archiveTransfer));

    SedaJsonConfig lenientConfig = SedaJsonConfigBuilder.builder().strict(false).build();
    assertDoesNotThrow(() -> jsonService.validate(archiveTransfer, lenientConfig));
  }

  /** Test invalid manifests. */
  @Test
  void testInvalidManifests() {
    List<String> fixtures =
        List.of(
            "wrong-root-order.json",
            "wrong-unit-order.json",
            "unknown-key.json",
            "bad-version.json",
            "missing-version.json",
            "duplicate-key.json");

    for (String fixture : fixtures) {
      Path path = Paths.get(SEDA_JSON + fixture);
      assertThrows(SipException.class, () -> jsonService.validate(path), fixture);
    }
  }

  /** Test manifest size limit. */
  @Test
  void testManifestTooBig() {
    ArchiveTransfer archiveTransfer = SipFactory.createMiniSip();
    Path output = Paths.get(TestInit.TEST_RESULTS + "toobig_sedajson.zip");
    jsonService.write(archiveTransfer, output, jsonConfig);

    SedaJsonConfig smallConfig = SedaJsonConfigBuilder.builder().maxManifestSize(16).build();
    List<ProgressEvent<SedaJsonStep>> events = new ArrayList<>();
    assertThrows(SipException.class, () -> jsonService.validate(output, smallConfig, events::add));
    assertEquals(SedaJsonStep.MANIFEST_SIZE, events.getLast().step());
    assertEquals(ProgressState.FAIL, events.getLast().status());
  }

  /** Test tampered binary digest. */
  @Test
  void testTamperedBinaryDigest() throws Exception {
    ArchiveTransfer archiveTransfer = SipFactory.createMiniSip();
    Path output = Paths.get(TestInit.TEST_RESULTS + "tampered_sedajson.zip");
    jsonService.write(archiveTransfer, output, jsonConfig);

    // Altère le contenu du binaire sans changer sa taille
    try (FileSystem zipArchive =
        FileSystems.newFileSystem(output, Collections.<String, Object>emptyMap())) {
      Path binaryPath = zipArchive.getPath("content/1_dummy.pdf");
      byte[] bytes = Files.readAllBytes(binaryPath);
      bytes[0] = (byte) ~bytes[0];
      Files.write(binaryPath, bytes);
    }

    List<ProgressEvent<SedaJsonStep>> events = new ArrayList<>();
    assertThrows(SipException.class, () -> jsonService.validate(output, jsonConfig, events::add));
    assertEquals(SedaJsonStep.BINARY_DIGEST, events.getLast().step());
    assertEquals(ProgressState.FAIL, events.getLast().status());
  }

  /** Test missing binary. */
  @Test
  void testMissingBinary() throws Exception {
    ArchiveTransfer archiveTransfer = SipFactory.createMiniSip();
    Path output = Paths.get(TestInit.TEST_RESULTS + "missing_sedajson.zip");
    jsonService.write(archiveTransfer, output, jsonConfig);

    try (FileSystem zipArchive =
        FileSystems.newFileSystem(output, Collections.<String, Object>emptyMap())) {
      Files.delete(zipArchive.getPath("content/1_dummy.pdf"));
    }

    List<ProgressEvent<SedaJsonStep>> events = new ArrayList<>();
    assertThrows(SipException.class, () -> jsonService.validate(output, jsonConfig, events::add));
    assertEquals(SedaJsonStep.BINARY_EXIST, events.getLast().step());
    assertEquals(ProgressState.FAIL, events.getLast().status());
  }

  /** Test the mini sip manifest validates against the esafe subset schema. */
  @Test
  void testEsafeSchemaCompatMiniSip() throws Exception {
    ArchiveTransfer archiveTransfer = SipFactory.createMiniSip();

    JsonSchema esafeSchema;
    Path schemaPath = Paths.get(SEDA_JSON + "esafe-seda-json-1.0-schema.json");
    try (InputStream is = Files.newInputStream(schemaPath)) {
      esafeSchema =
          JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012)
              .getSchema(MAPPER.readTree(is));
    }

    try (InputStream is = jsonService.marshal(archiveTransfer)) {
      Set<ValidationMessage> messages = esafeSchema.validate(MAPPER.readTree(is));
      assertTrue(messages.isEmpty(), messages.toString());
    }
  }

  /** Test the mini sip manifest validates against the esafe subset schema. */
  @Test
  void testEsafeSchemaCompatComplexSip() throws Exception {
    ArchiveTransfer archiveTransfer = SipFactory.createComplexSip(FileSystems.getDefault());

    JsonSchema esafeSchema;
    Path schemaPath = Paths.get(SEDA_JSON + "esafe-seda-json-1.0-schema.json");
    try (InputStream is = Files.newInputStream(schemaPath)) {
      esafeSchema =
          JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012)
              .getSchema(MAPPER.readTree(is));
    }

    try (InputStream is = jsonService.marshal(archiveTransfer, jsonConfig)) {
      Set<ValidationMessage> messages = esafeSchema.validate(MAPPER.readTree(is));
      assertTrue(messages.isEmpty(), messages.toString());
    }
  }
}
