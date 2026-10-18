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

import fr.xelians.sipg.utils.SipException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.io.input.BrokenInputStream;
import org.junit.jupiter.api.Test;

/**
 * The SEDA JSON schema validator test.
 *
 * @author Emmanuel Deviller
 */
class SedaJsonSchemaValidatorTest {

  private static final String MINIMAL_MANIFEST =
      """
      { "SedaJsonVersion": "2.0", "ArchivalAgreement": "IC-000001",
        "ArchivalAgency": { "Identifier": "AG-000001" } }
      """;

  private final SedaJsonSchemaValidator validator =
      SedaJsonSchemaValidator.getInstance(SedaJsonVersion.V2_0);

  private static InputStream stream(String json) {
    return new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
  }

  private static InputStream fixture(String name) throws IOException {
    return Files.newInputStream(Paths.get(SedaJsonTest.SEDA_JSON + name));
  }

  private void assertRejected(InputStream manifest, String fragment) {
    SipException ex = assertThrows(SipException.class, () -> validator.validate(manifest));
    assertTrue(ex.getMessage().contains(fragment), ex.getMessage());
  }

  /** Test a conforming manifest is accepted. */
  @Test
  void testValidManifest() {
    assertDoesNotThrow(() -> validator.validate(stream(MINIMAL_MANIFEST)));
  }

  /** Test a schema violation is rejected with the location of the faulty value. */
  @Test
  void testSchemaViolationFail() throws IOException {
    String manifest =
        """
        { "SedaJsonVersion": "2.0", "ArchivalAgreement": "IC-000001",
          "ArchiveUnits": [ { "Content": { "Title": "" } } ],
          "ArchivalAgency": { "Identifier": "AG-000001" } }
        """;

    assertRejected(stream(manifest), "SEDA JSON 2.0 schema: $.ArchiveUnits[0].Content.Title: ");
    assertRejected(fixture("unknown-key.json"), "SEDA JSON 2.0 schema: ");
  }

  /** Test a missing version or a version that is not a supported json string is rejected. */
  @Test
  void testUnsupportedVersionFail() throws IOException {
    assertRejected(fixture("bad-version.json"), "is not supported");
    assertRejected(fixture("missing-version.json"), "is not supported");
    for (String version : new String[] {"{}", "[\"2.0\"]", "2.0", "null"}) {
      assertRejected(stream("{ \"SedaJsonVersion\": %s }".formatted(version)), "is not supported");
    }
  }

  /** Test a manifest that is not a json object is rejected. */
  @Test
  void testNonObjectManifestFail() {
    assertRejected(stream("[1, 2, 3]"), "must be a json object");
  }

  /** Test a manifest that is not valid json is rejected by the hardened parser. */
  @Test
  void testInvalidJsonFail() throws IOException {
    int depth = SedaJsonParser.MAX_NESTING_DEPTH + 1;
    assertRejected(stream("{ not json"), "not a valid json document");
    assertRejected(stream("[".repeat(depth) + "]".repeat(depth)), "not a valid json document");
    assertRejected(fixture("duplicate-key.json"), "not a valid json document");
  }

  /** Test a failure to read the manifest is not reported as invalid json. */
  @Test
  void testUnreadableManifestFail() {
    assertRejected(
        new BrokenInputStream(new IOException("disk failure")), "Unable to read manifest");
  }
}
