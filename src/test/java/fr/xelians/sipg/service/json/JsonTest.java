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
package fr.xelians.sipg.service.json;

import static org.junit.jupiter.api.Assertions.*;

import com.google.common.jimfs.Jimfs;
import fr.xelians.sipg.SipFactory;
import fr.xelians.sipg.TestInit;
import fr.xelians.sipg.model.ArchiveTransfer;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * The JSON integration test.
 *
 * @author Emmanuel Deviller
 */
@ExtendWith(TestInit.class)
class JsonTest {

  private final JsonConfig jsonConfig = JsonConfigBuilder.builder().format(true).build();
  private final JsonService jsonService = JsonService.getInstance();

  /** Test create mini json. */
  @Test
  void testCreateMiniJson() {
    Path jsonPath = Paths.get(TestInit.TEST_RESULTS + "minisip_serial.json");
    ArchiveTransfer archiveTransfer = SipFactory.createMiniSip();
    jsonService.write(archiveTransfer, jsonPath, jsonConfig);
    assertTrue(Files.exists(jsonPath));

    ArchiveTransfer newArchiveTransfer = jsonService.read(jsonPath);
    String str = archiveTransfer.getArchiveUnits().getFirst().getBinaryPath().toString();
    String newStr = newArchiveTransfer.getArchiveUnits().getFirst().getBinaryPath().toString();

    assertTrue(newStr.contains(str));
  }

  /** Test read mini json. */
  @Test
  void testReadMiniJson() {
    Path jsonPath = Paths.get(TestInit.TEST_RESOURCES + "minisip.json");
    ArchiveTransfer archiveTransfer = jsonService.read(jsonPath);
    Path output = Paths.get(TestInit.TEST_RESULTS + "minisip_deserial.json");
    jsonService.write(archiveTransfer, output, jsonConfig);
    assertTrue(Files.exists(output));
  }

  /** Test create full vitam. */
  @Test
  void testCreateFullVitam() {
    ArchiveTransfer archiveTransfer = SipFactory.createSipFullVitam();
    Path output = Paths.get(TestInit.TEST_RESULTS + "sip_vitam_full.json");
    jsonService.write(archiveTransfer, output, jsonConfig);
    assertTrue(Files.exists(output));
  }

  /** Test create simple json. */
  @Test
  void testCreateSimpleJson() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createComplexSip(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "simplesip_serial.json");
      jsonService.write(archiveTransfer, output, jsonConfig);
      assertTrue(Files.exists(output));
    }
  }

  /** Test read simple json. */
  @Test
  void testReadSimpleJson() {
    Path jsonPath = Paths.get(TestInit.TEST_RESOURCES + "simplesip.json");
    ArchiveTransfer archiveTransfer = jsonService.read(jsonPath);
    Path output = Paths.get(TestInit.TEST_RESULTS + "simplesip_deserial.json");
    jsonService.write(archiveTransfer, output, jsonConfig);
    assertTrue(Files.exists(output));
  }

  /** Test create read simple json. */
  @Test
  void testCreateReadSimpleJson() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      String serialized = jsonService.write(SipFactory.createComplexSip(fs), jsonConfig);
      ArchiveTransfer archiveTransfer = jsonService.read(serialized);
      Path output = Paths.get(TestInit.TEST_RESULTS + "simplesip_serial_deserial.json");
      jsonService.write(archiveTransfer, output, jsonConfig);
      assertTrue(Files.exists(output));
    }
  }

  /** Test read freemarker json. */
  @Test
  void testReadFreemarkerJson() throws Exception {
    String jsonString = SipFactory.createJsonString();
    ArchiveTransfer archiveTransfer = jsonService.read(jsonString);
    Path output = Paths.get(TestInit.TEST_RESULTS + "freemarker_deserial.json");
    jsonService.write(archiveTransfer, output, jsonConfig);
    assertTrue(Files.exists(output));
  }

  /** Test create full text json. */
  @Test
  void testCreateFullTextJson() {
    ArchiveTransfer archiveTransfer = SipFactory.createFullTextSip();
    Path output = Paths.get(TestInit.TEST_RESULTS + "full_serial.json");
    jsonService.write(archiveTransfer, output, jsonConfig);
    assertTrue(Files.exists(output));
  }

  /** Test read full text json. */
  @Test
  void testReadFullTextJson() {
    String serialized = jsonService.write(SipFactory.createFullTextSip(), jsonConfig);
    ArchiveTransfer archiveTransfer = jsonService.read(serialized);
    Path output = Paths.get(TestInit.TEST_RESULTS + "full_deserial.json");
    jsonService.write(archiveTransfer, output, jsonConfig);
    assertTrue(Files.exists(output));
  }

  /** Test create small json. */
  @Test
  void testCreateSmallJson() {
    ArchiveTransfer archiveTransfer = SipFactory.createSmallSip();
    Path zipPath = Paths.get(TestInit.TEST_RESULTS + "smallsip_serial.json");
    jsonService.write(archiveTransfer, zipPath, jsonConfig);
    assertTrue(Files.exists(zipPath));
  }

  /** Test read small json. */
  @Test
  void testReadSmallJson() {
    Path jsonPath = Paths.get(TestInit.TEST_RESOURCES + "smallsip.json");
    ArchiveTransfer archiveTransfer = jsonService.read(jsonPath);
    Path output = Paths.get(TestInit.TEST_RESULTS + "smallsip_deserial.json");
    jsonService.write(archiveTransfer, output, jsonConfig);
    assertTrue(Files.exists(output));
  }

  /** Test create large json. */
  @Test
  void testCreateLargeJson() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createLargeSip(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "largesip_serial.json");
      jsonService.write(archiveTransfer, output, jsonConfig);
      assertTrue(Files.exists(output));
    }
  }

  /** Test read large json. */
  @Test
  void testReadLargeJson() {
    Path jsonPath = Paths.get(TestInit.TEST_RESOURCES + "largesip.json");
    ArchiveTransfer archiveTransfer = jsonService.read(jsonPath);
    Path output = Paths.get(TestInit.TEST_RESULTS + "largesip_deserial.json");
    jsonService.write(archiveTransfer, output, jsonConfig);
    assertTrue(Files.exists(output));
  }

  /** Test create deep json. */
  @Test
  void testCreateDeepJson() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createDeepSip(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "deepsip_serial.json");
      jsonService.write(archiveTransfer, output, jsonConfig);
      assertTrue(Files.exists(output));
    }
  }

  /** Test read deep json. */
  @Test
  void testReadDeepJson() {
    Path jsonPath = Paths.get(TestInit.TEST_RESOURCES + "deepsip.json");
    ArchiveTransfer archiveTransfer = jsonService.read(jsonPath);
    Path output = Paths.get(TestInit.TEST_RESULTS + "deepsip_deserial.json");
    jsonService.write(archiveTransfer, output, jsonConfig);
    assertTrue(Files.exists(output));
  }
}
