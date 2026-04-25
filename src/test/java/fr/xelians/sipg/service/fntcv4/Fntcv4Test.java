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
package fr.xelians.sipg.service.fntcv4;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.jimfs.Jimfs;
import fr.xelians.sipg.SipFactory;
import fr.xelians.sipg.TestInit;
import fr.xelians.sipg.model.ArchiveTransfer;
import fr.xelians.sipg.service.json.JsonService;
import fr.xelians.sipg.utils.SipException;
import fr.xelians.sipg.utils.Validators;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The FNTC v4 integration test.
 *
 * @author Emmanuel Deviller
 */
@ExtendWith(TestInit.class)
class Fntcv4Test {

  private static final Logger LOGGER = LoggerFactory.getLogger(Fntcv4Test.class);

  private final Fntcv4Config fntcConfig =
      Fntcv4ConfigBuilder.builder().format(true).validate(true).strict(false).build();
  private final Fntcv4Service fntcService = Fntcv4Service.getInstance();

  /** Test validate xml. */
  @Test
  void testValidateXml() {
    Path path = Paths.get(TestInit.TEST_RESOURCES + "fntc_small.xml");
    fntcService.validate(path);

    Path rngPath = Paths.get(TestInit.TEST_RESOURCES, "fntc_small.rng");
    Validator rngValidator = Validators.getRngValidator(rngPath);
    Validators.validate(path, rngValidator);
  }

  /** Test validate simple zip. */
  @Test
  void testValidateSimpleZip() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createComplexSip(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "simplesip2_fntc.zip");
      fntcService.write(archiveTransfer, output, fntcConfig);
      assertTrue(Files.exists(output));

      fntcService.validate(output, null, fntcConfig, e -> LOGGER.info(e.toString()));
    }
  }

  /** Test validate large zip. */
  @Test
  void testValidateLargeZip() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createLargeSip(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "largesip2_fntc.zip");
      fntcService.write(archiveTransfer, output, fntcConfig);
      assertTrue(Files.exists(output));
      fntcService.validate(output);
    }
  }

  /** Test create full text sip. */
  @Test
  void testCreateFullTextSip() {
    ArchiveTransfer archiveTransfer = SipFactory.createFullTextSip();
    Path output = Paths.get(TestInit.TEST_RESULTS + "fulltextsip_fntc.zip");
    fntcService.write(archiveTransfer, output, fntcConfig);
    assertTrue(Files.exists(output));
  }

  /** Test create dir sip. */
  @Test
  void testCreateDirSip() throws Exception {
    ArchiveTransfer archiveTransfer = SipFactory.createSipFromDir();
    Path output = Paths.get(TestInit.TEST_RESULTS + "dirsip_fntc.zip");
    fntcService.write(archiveTransfer, output, fntcConfig);
    assertTrue(Files.exists(output));
  }

  /** Test without agency fail. */
  @Test
  void testWithoutAgencyFail() {
    ArchiveTransfer archiveTransfer = SipFactory.createWithoutAgencySip();
    Path outputPath = Paths.get(TestInit.TEST_RESULTS + "fail_fntc.zip");
    assertThrows(SipException.class, () -> fntcService.write(archiveTransfer, outputPath));
  }

  /** Test create csv sip. */
  @Test
  void testCreateCsvSip() throws Exception {
    ArchiveTransfer archiveTransfer = SipFactory.createCsvSip();
    Path output = Paths.get(TestInit.TEST_RESULTS + "csvsip_fntc.zip");
    fntcService.write(archiveTransfer, output, fntcConfig);
    assertTrue(Files.exists(output));
  }

  /** Test read freemarker json. */
  @Test
  void testReadFreemarkerJson() throws Exception {
    String jsonString = SipFactory.createJsonString();
    ArchiveTransfer archiveTransfer = JsonService.getInstance().read(jsonString);
    Path output = Paths.get(TestInit.TEST_RESULTS + "freemarker_fntc.zip");
    Fntcv4Service.getInstance().write(archiveTransfer, output);
    assertTrue(Files.exists(output));
  }

  /** Test create mini sip. */
  @Test
  void testCreateMiniSip() {
    ArchiveTransfer archiveTransfer = SipFactory.createMiniSip();
    Path output = Paths.get(TestInit.TEST_RESULTS + "minisip_fntc.zip");
    fntcService.write(archiveTransfer, output, fntcConfig);
    assertTrue(Files.exists(output));
  }

  /** Test create simple sip. */
  @Test
  void testCreateSimpleSip() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createComplexSip(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "simplesip_fntc.zip");
      fntcService.write(archiveTransfer, output, fntcConfig);
      assertTrue(Files.exists(output));
    }
  }

  /** Test validate simple sip. */
  @Test
  void testValidateSimpleSip() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createComplexSip(fs);
      fntcService.validate(archiveTransfer, fntcConfig);
    }
  }

  /** Test create small sip. */
  @Test
  void testCreateSmallSip() {
    ArchiveTransfer archiveTransfer = SipFactory.createSmallSip();
    Path zipPath = Paths.get(TestInit.TEST_RESULTS + "smallsip_fntc.zip");
    Path rngPath = Paths.get(TestInit.TEST_RESOURCES, "fntc_small.rng");
    Validator rngValidator = Validators.getRngValidator(rngPath);
    fntcService.write(archiveTransfer, zipPath, rngValidator, fntcConfig);
    assertTrue(Files.exists(zipPath));
  }

  /** Test validate small sip. */
  @Test
  void testValidateSmallSip() {
    ArchiveTransfer archiveTransfer = SipFactory.createSmallSip();
    Path rngPath = Paths.get(TestInit.TEST_RESOURCES, "fntc_small.rng");
    Validator rngValidator = Validators.getRngValidator(rngPath);
    fntcService.validate(archiveTransfer, rngValidator);
    fntcService.validate(archiveTransfer);
  }

  /** Test create large sip. */
  @Test
  void testCreateLargeSip() throws Exception {
    Fntcv4Config config = Fntcv4ConfigBuilder.builder().useMemory(false).strict(false).build();

    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createLargeSip(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "largesip_fntc.zip");
      fntcService.write(archiveTransfer, output, config);
      assertTrue(Files.exists(output));
    }
  }

  /** Test validate rng small sip. */
  @Test
  void testValidateRngSmallSip() {
    ArchiveTransfer archiveTransfer = SipFactory.createSmallSip();
    fntcService.validate(archiveTransfer);
  }

  /** Test create deep sip. */
  @Test
  void testCreateDeepSip() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createDeepSip(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "deepsip_fntc.zip");
      fntcService.write(archiveTransfer, output, fntcConfig);
      assertTrue(Files.exists(output));
    }
  }

  /** Test validate deep sip. */
  @Test
  void testValidateDeepSip() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createDeepSip(fs);
      fntcService.validate(archiveTransfer);
    }
  }
}
