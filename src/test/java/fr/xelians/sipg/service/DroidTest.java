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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.xelians.sipg.TestInit;
import fr.xelians.sipg.utils.DroidUtils;
import fr.xelians.sipg.utils.SipUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import uk.gov.nationalarchives.droid.container.ContainerSignatureSaxParser;
import uk.gov.nationalarchives.droid.container.FileFormatMapping;
import uk.gov.nationalarchives.droid.core.interfaces.IdentificationResult;

/**
 * The DROID integration test.
 *
 * @author Emmanuel Deviller
 */
@ExtendWith(TestInit.class)
class DroidTest {

  /** Test small pdf droid. */
  @Test
  void testSmallPdfDroid() {
    Path path = Paths.get(TestInit.TEST_RESOURCES + "dummy.pdf");
    List<IdentificationResult> results = DroidUtils.matchBinarySignatures(path, "pdf", true);
    assertEquals(1, results.size());
    assertEquals("fmt/18", results.getFirst().getPuid());
  }

  /** Test medium pdf droid. */
  @Test
  void testMediumPdfDroid() {
    Path path = Paths.get(TestInit.TEST_RESOURCES + "citizenfour.pdf");
    List<IdentificationResult> results = DroidUtils.matchBinarySignatures(path, "pdf", true);
    assertEquals(1, results.size());
    assertEquals("fmt/19", results.getFirst().getPuid());
  }

  @Test
  void testPlainTextDroid() {
    Path path = Paths.get(TestInit.TEST_RESOURCES + "dummy.txt");
    List<IdentificationResult> results = DroidUtils.matchBinarySignatures(path, "txt", true);
    assertEquals("x-fmt/111", results.getFirst().getPuid());
  }

  @Test
  void testJsonDroid() {
    Path path = Paths.get(TestInit.TEST_RESOURCES + "minisip.json");
    List<IdentificationResult> results = DroidUtils.matchBinarySignatures(path, "json", true);
    assertEquals("fmt/817", results.getFirst().getPuid());
  }

  /** Test a docx gets its specific format and mime type from the container signatures. */
  @Test
  void testDocxDroid() {
    Path path = Paths.get(TestInit.TEST_RESOURCES + "enveloppe.docx");
    for (String extension : new String[] {"docx", null}) {
      IdentificationResult result =
          DroidUtils.matchBinarySignatures(path, extension, false).getFirst();
      assertEquals("fmt/412", result.getPuid());
      assertEquals("Microsoft Word for Windows", result.getName());
      assertEquals(
          "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
          result.getMimeType());
    }
  }

  /** Test every format of the container signatures is known to the binary signatures. */
  @Test
  void testContainerSignaturesPairing() throws Exception {
    try (InputStream is = SipUtils.resourceAsStream(DroidUtils.CONTAINER_SIGNATURE_FILE)) {
      for (FileFormatMapping mapping : new ContainerSignatureSaxParser().parse(is).getFormats()) {
        assertTrue(DroidUtils.isSupportedFormat(mapping.getPuid()), mapping.getPuid());
      }
    }
  }

  /** Test a damaged container keeps its generic format instead of failing. */
  @Test
  void testDamagedDocxDroid(@TempDir Path tmpDir) throws IOException {
    byte[] docx = Files.readAllBytes(Paths.get(TestInit.TEST_RESOURCES + "enveloppe.docx"));
    // Zeroes the zip entries but keeps the signatures at the start and end of the file
    Arrays.fill(docx, 64, docx.length - 2048, (byte) 0);
    Path path = tmpDir.resolve("damaged.docx");
    Files.write(path, docx);
    List<IdentificationResult> results = DroidUtils.matchBinarySignatures(path, "docx", false);
    assertEquals("fmt/189", results.getFirst().getPuid());
  }

  /** Test an OLE2 container goes through the container signatures. */
  @Test
  void testOle2Droid(@TempDir Path tmpDir) throws IOException {
    Path path = tmpDir.resolve("empty.ole2");
    try (POIFSFileSystem fs = new POIFSFileSystem();
        OutputStream os = Files.newOutputStream(path)) {
      fs.writeFilesystem(os);
    }
    List<IdentificationResult> results = DroidUtils.matchBinarySignatures(path, null, false);
    assertEquals("fmt/111", results.getFirst().getPuid());
  }

  /** Test supported extension. */
  @Test
  void testSupportedExtension() {
    assertTrue(DroidUtils.isSupportedExtension("pdf"));
    assertTrue(DroidUtils.isSupportedExtension("PDF"));
    assertFalse(DroidUtils.isSupportedExtension("not_an_extension"));
    assertFalse(DroidUtils.isSupportedExtension(null));
  }

  /** Test supported extension is known before the signatures are used. */
  @Test
  void testSupportedExtensionFirstCall() throws Exception {
    // A child first class loader gives a DroidUtils class whose signatures were never loaded,
    // whatever the tests run before
    URL classes = DroidUtils.class.getProtectionDomain().getCodeSource().getLocation();
    try (URLClassLoader loader =
        new URLClassLoader(new URL[] {classes}, getClass().getClassLoader()) {
          @Override
          protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            if (!name.startsWith("fr.xelians.sipg.")) {
              return super.loadClass(name, resolve);
            }
            synchronized (getClassLoadingLock(name)) {
              Class<?> loaded = findLoadedClass(name);
              return loaded != null ? loaded : findClass(name);
            }
          }
        }) {
      Class<?> droidUtils = loader.loadClass(DroidUtils.class.getName());
      Method method = droidUtils.getMethod("isSupportedExtension", String.class);
      assertEquals(Boolean.TRUE, method.invoke(null, "pdf"));
    }
  }

  /** Test loop droid. */
  @Test
  void testLoopDroid() {
    Path path = Paths.get(TestInit.TEST_RESOURCES + "citizenfour.pdf");

    for (int i = 0; i < 1000; i++) {
      List<IdentificationResult> results = DroidUtils.matchBinarySignatures(path, "pdf", true);
      assertEquals("fmt/19", results.getFirst().getPuid());
    }
  }
}
