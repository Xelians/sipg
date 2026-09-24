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
package fr.xelians.sipg.service.pronom;

import static org.junit.jupiter.api.Assertions.*;

import fr.xelians.sipg.TestInit;
import fr.xelians.sipg.utils.DroidUtils;
import fr.xelians.sipg.utils.SipException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * The PRONOM file format referential test.
 *
 * @author Julien Cornille
 */
@ExtendWith(TestInit.class)
class PronomServiceTest {

  public static final String PRONOM = TestInit.TEST_RESOURCES + "pronom/";

  private final PronomService pronomService = PronomService.getInstance();

  /** Test the default referential is the bundled DROID signature file. */
  @Test
  void testDefaultSignatureFile() {
    PronomSignatureFile file = pronomService.getDefault();

    assertEquals("97", file.version());
    assertEquals(1899, file.fileFormats().size());
    Set<String> puids = new HashSet<>();
    for (PronomFileFormat format : file.fileFormats()) {
      assertTrue(puids.add(format.puid()), format.puid());
      assertTrue(StringUtils.isNotBlank(format.name()), format.puid());
      // The referential and the format identification share the same signature file
      assertTrue(DroidUtils.isSupportedFormat(format.puid()), format.puid());
    }

    PronomFileFormat pdf14 = byPuid(file, "fmt/18");
    assertEquals("Acrobat PDF 1.4 - Portable Document Format", pdf14.name());
    assertEquals("application/pdf", pdf14.mimeType());
    assertEquals("1.4", pdf14.version());
    assertEquals(List.of("pdf"), pdf14.extensions());

    // Priorities are resolved to PUIDs, never left as positional ids
    List<String> priorities =
        file.fileFormats().stream()
            .flatMap(f -> f.hasPriorityOverFileFormatIds().stream())
            .toList();
    assertFalse(priorities.isEmpty());
    assertTrue(priorities.stream().allMatch(puid -> puid.contains("fmt/")));

    assertSame(file, pronomService.getDefault());
  }

  /** Test a reduced signature file with internal signatures. */
  @Test
  void testReducedSignatureFile() {
    Path path = Paths.get(PRONOM + "OK_fileformats_reduced.xml");
    pronomService.validate(path);

    PronomSignatureFile file = pronomService.parse(path);
    assertEquals("102", file.version());
    assertEquals(List.of("fmt/18", "fmt/11"), puids(file));
    assertEquals(List.of("png"), byPuid(file, "fmt/11").extensions());
  }

  /** Test numeric priority references are resolved to PUIDs and unknown ones dropped. */
  @Test
  void testXamSignatureFile() {
    PronomSignatureFile file = pronomService.parse(Paths.get(PRONOM + "OK_fileformats_xam.xml"));

    assertEquals("101", file.version());
    assertEquals(List.of("fmt/18", "fmt/17", "fmt/11", "EXTERNAL_XELIANS"), puids(file));

    assertEquals(List.of("fmt/17"), byPuid(file, "fmt/18").hasPriorityOverFileFormatIds());
    PronomFileFormat external = byPuid(file, "EXTERNAL_XELIANS");
    assertEquals(List.of("xel", "xelians"), external.extensions());
    assertTrue(external.hasPriorityOverFileFormatIds().isEmpty());
    assertNull(external.version());
  }

  /** Test a non numeric priority reference is kept as a PUID. */
  @Test
  void testPuidPriority() {
    String xml =
        """
        <FFSignatureFile Version="1">
          <FileFormatCollection>
            <FileFormat Name="A" PUID="fmt/1">
              <HasPriorityOverFileFormatID>fmt/2</HasPriorityOverFileFormatID>
              <HasPriorityOverFileFormatID>fmt/1</HasPriorityOverFileFormatID>
            </FileFormat>
            <FileFormat Name="B" PUID="fmt/2"/>
          </FileFormatCollection>
        </FFSignatureFile>
        """;

    PronomSignatureFile file = pronomService.parse(xml.getBytes(StandardCharsets.UTF_8));

    // A self reference is dropped
    assertEquals(List.of("fmt/2"), byPuid(file, "fmt/1").hasPriorityOverFileFormatIds());
  }

  /** Test a signature file without any file format. */
  @Test
  void testEmptySignatureFile() {
    String xml = "<FFSignatureFile Version=\"5\"><FileFormatCollection/></FFSignatureFile>";

    PronomSignatureFile file = pronomService.parse(xml.getBytes(StandardCharsets.UTF_8));

    assertEquals("5", file.version());
    assertTrue(file.fileFormats().isEmpty());
  }

  /** Test invalid signature files. */
  @Test
  void testInvalidSignatureFiles() {
    for (String fixture :
        List.of(
            "KO_fileformats_duplicate_puid.xml",
            "KO_fileformats_missing_puid.xml",
            "KO_fileformats_not_signature_file.xml",
            "KO_fileformats_malformed.xml")) {
      Path path = Paths.get(PRONOM + fixture);
      assertThrows(SipException.class, () -> pronomService.parse(path), fixture);
      assertThrows(SipException.class, () -> pronomService.validate(path), fixture);
    }
  }

  /** Test a file format with an invalid PUID. */
  @Test
  void testInvalidPuidFail() {
    String xml =
        """
        <FFSignatureFile Version="1">
          <FileFormatCollection>
            <FileFormat Name="A" PUID="fmt//1"/>
          </FileFormatCollection>
        </FFSignatureFile>
        """;

    byte[] bytes = xml.getBytes(StandardCharsets.UTF_8);
    SipException ex = assertThrows(SipException.class, () -> pronomService.parse(bytes));
    assertTrue(ex.getMessage().contains("'fmt//1' is not a valid PUID"), ex.getMessage());
  }

  /** Test a document declaring an external entity fail. */
  @Test
  void testExternalEntityFail() {
    String xml =
        """
        <?xml version="1.0"?>
        <!DOCTYPE FFSignatureFile [ <!ENTITY xxe SYSTEM "file:///etc/hostname"> ]>
        <FFSignatureFile Version="1">
          <FileFormatCollection>
            <FileFormat Name="&xxe;" PUID="fmt/1"/>
          </FileFormatCollection>
        </FFSignatureFile>
        """;

    byte[] bytes = xml.getBytes(StandardCharsets.UTF_8);
    assertThrows(SipException.class, () -> pronomService.parse(bytes));
  }

  /** Test a missing signature file fail. */
  @Test
  void testMissingSignatureFileFail() {
    Path path = Paths.get(PRONOM + "missing.xml");
    assertThrows(SipException.class, () -> pronomService.parse(path));
  }

  /** Test valid PUIDs. */
  @Test
  void testValidPuid() {
    for (String puid : List.of("fmt/14", "x-fmt/111", "EXTERNAL_MYFORMAT", "a/b/c", "fmt/v1.2-b")) {
      assertTrue(PronomService.isValidPuid(puid), puid);
    }
    // Thousands of segments must not overflow the stack, unlike a nested repetition
    assertTrue(PronomService.isValidPuid("a/".repeat(100_000) + "a"));
  }

  /** Test invalid PUIDs. */
  @Test
  void testInvalidPuid() {
    assertFalse(PronomService.isValidPuid(null));
    for (String puid :
        List.of("", "/fmt", "fmt/", "fmt//14", "fmt/.14", "fmt/14 ", "fmt\\14", "fmt/14é")) {
      assertFalse(PronomService.isValidPuid(puid), puid);
    }
  }

  private static List<String> puids(PronomSignatureFile file) {
    return file.fileFormats().stream().map(PronomFileFormat::puid).toList();
  }

  private static PronomFileFormat byPuid(PronomSignatureFile file, String puid) {
    return file.fileFormats().stream()
        .filter(f -> f.puid().equals(puid))
        .findFirst()
        .orElseThrow(() -> new AssertionError("PUID not found: " + puid));
  }
}
