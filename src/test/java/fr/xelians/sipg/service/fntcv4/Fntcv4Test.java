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
import static org.junit.jupiter.api.Assertions.fail;

import com.google.common.jimfs.Jimfs;
import fr.xelians.sipg.SipFactory;
import fr.xelians.sipg.TestInit;
import fr.xelians.sipg.TestUtils;
import fr.xelians.sipg.model.ArchiveTransfer;
import fr.xelians.sipg.service.json.JsonService;
import fr.xelians.sipg.service.sedav2.Sedav2Config;
import fr.xelians.sipg.service.sedav2.Sedav2ConfigBuilder;
import fr.xelians.sipg.utils.SipException;
import fr.xelians.sipg.utils.Validators;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.validation.Validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
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

  private final Fntcv4Config fntcConfig = Fntcv4ConfigBuilder.builder().format(true).validate(true).strict(false)
      .build();
  private final Fntcv4Service fntcService = Fntcv4Service.getInstance();

  /**
   * Test validate xml.
   *
   * @param testInfo the test info
   */
  @Test
  void testValidateXml(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));

    try {
      Path path = Paths.get(TestInit.TEST_RESOURCES + "fntc_small.xml");
      fntcService.validate(path);

      Path rngPath = Paths.get(TestInit.TEST_RESOURCES, "fntc_small.rng");
      Validator rngValidator = Validators.getRngValidator(rngPath);
      Validators.validate(path, rngValidator);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.warn(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test validate simple zip.
   *
   * @param testInfo the test info
   */
  @Test
  void testValidateSimpleZip(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));

    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createComplexSip(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "simplesip2_fntc.zip");
      fntcService.write(archiveTransfer, output, fntcConfig);

      fntcService.validate(output, null, fntcConfig, e -> LOGGER.info(e.toString()));
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.warn(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test validate large zip.
   *
   * @param testInfo the test info
   */
  @Test
  void testValidateLargeZip(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));

    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createLargeSip(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "largesip2_fntc.zip");
      fntcService.write(archiveTransfer, output, fntcConfig);
      fntcService.validate(output);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.warn(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test create full text sip.
   *
   * @param testInfo the test info
   */
  @Test
  void testCreateFullTextSip(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));

    try {
      ArchiveTransfer archiveTransfer = SipFactory.createFullTextSip();
      fntcService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "fulltextsip_fntc.zip"), fntcConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.warn(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test create dir sip.
   *
   * @param testInfo the test info
   */
  @Test
  void testCreateDirSip(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));

    try {
      ArchiveTransfer archiveTransfer = SipFactory.createSipFromDir();
      fntcService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "dirsip_fntc.zip"), fntcConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.warn(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test without agency fail.
   *
   * @param testInfo the test info
   */
  @Test
  void testWithoutAgencyFail(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));

    ArchiveTransfer archiveTransfer = SipFactory.createWithoutAgencySip();
    Path outputPath = Paths.get(TestInit.TEST_RESULTS + "fail_fntc.zip");
    assertThrows(SipException.class, () -> fntcService.write(archiveTransfer, outputPath));
  }

  /**
   * Test create csv sip.
   *
   * @param testInfo the test info
   */
  @Test
  void testCreateCsvSip(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));

    try {
      ArchiveTransfer archiveTransfer = SipFactory.createCsvSip();
      fntcService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "csvsip_fntc.zip"), fntcConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.warn(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test read freemarker json.
   *
   * @param testInfo the test info
   */
  @Test
  void testReadFreemarkerJson(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));
    try {
      String jsonString = SipFactory.createJsonString();
      ArchiveTransfer archiveTransfer = JsonService.getInstance().read(jsonString);
      Fntcv4Service.getInstance().write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "freemarker_fntc.zip"));
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.warn(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test create mini sip.
   *
   * @param testInfo the test info
   */
  @Test
  void testCreateMiniSip(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));

    try {
      ArchiveTransfer archiveTransfer = SipFactory.createMiniSip();
      fntcService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "minisip_fntc.zip"), fntcConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.warn(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test create simple sip.
   *
   * @param testInfo the test info
   */
  @Test
  void testCreateSimpleSip(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));

    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createComplexSip(fs);
      fntcService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "simplesip_fntc.zip"), fntcConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.warn(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test validate simple sip.
   *
   * @param testInfo the test info
   */
  @Test
  void testValidateSimpleSip(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));

    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createComplexSip(fs);
      fntcService.validate(archiveTransfer, fntcConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.warn(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test create small sip.
   *
   * @param testInfo the test info
   */
  @Test
  void testCreateSmallSip(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));

    try {
      ArchiveTransfer archiveTransfer = SipFactory.createSmallSip();
      Path zipPath = Paths.get(TestInit.TEST_RESULTS + "smallsip_fntc.zip");
      Path rngPath = Paths.get(TestInit.TEST_RESOURCES, "fntc_small.rng");
      Validator rngValidator = Validators.getRngValidator(rngPath);
      fntcService.write(archiveTransfer, zipPath, rngValidator, fntcConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.warn(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test validate small sip.
   *
   * @param testInfo the test info
   */
  @Test
  void testValidateSmallSip(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));

    try {
      ArchiveTransfer archiveTransfer = SipFactory.createSmallSip();
      Path rngPath = Paths.get(TestInit.TEST_RESOURCES, "fntc_small.rng");
      Validator rngValidator = Validators.getRngValidator(rngPath);
      fntcService.validate(archiveTransfer, rngValidator);
      fntcService.validate(archiveTransfer);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.warn(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test create large sip.
   *
   * @param testInfo the test info
   */
  @Test
  void testCreateLargeSip(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));

    Fntcv4Config config = Fntcv4ConfigBuilder.builder().useMemory(false).strict(false).build();

    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createLargeSip(fs);
      fntcService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "largesip_fntc.zip"), config);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.warn(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test validate rng small sip.
   *
   * @param testInfo the test info
   */
  @Test
  void testValidateRngSmallSip(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));

    try {
      ArchiveTransfer archiveTransfer = SipFactory.createSmallSip();
      fntcService.validate(archiveTransfer);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.warn(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test create deep sip.
   *
   * @param testInfo the test info
   */
  @Test
  void testCreateDeepSip(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));

    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createDeepSip(fs);
      fntcService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "deepsip_fntc.zip"), fntcConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.warn(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test validate deep sip.
   *
   * @param testInfo the test info
   */
  @Test
  void testValidateDeepSip(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));

    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createDeepSip(fs);
      fntcService.validate(archiveTransfer);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.warn(msg, ex);
      fail(msg);
    }
  }
}
