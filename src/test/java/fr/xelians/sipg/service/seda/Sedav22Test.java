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
package fr.xelians.sipg.service.seda;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import com.google.common.jimfs.Jimfs;
import fr.xelians.sipg.SipFactory;
import fr.xelians.sipg.TestInit;
import fr.xelians.sipg.TestUtils;
import fr.xelians.sipg.model.ArchiveDeliveryRequestReply;
import fr.xelians.sipg.model.ArchiveTransfer;
import fr.xelians.sipg.service.json.JsonService;
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
 * The SEDA v2 integration test.
 *
 * @author Emmanuel Deviller
 */
@ExtendWith(TestInit.class)
public class Sedav22Test {

  public static final String SEDA22 = TestInit.TEST_RESOURCES + "seda-2.2/";

  private static final Logger LOGGER = LoggerFactory.getLogger(Sedav22Test.class);

  private final SedaConfig sedaConfig =
      SedaConfigBuilder.builder().format(true).validate(true).strict(false).build();
  private final SedaService sedaService = SedaService.getV22Instance();

  /**
   * Test validate xml.
   *
   * @param testInfo the test info
   */
  @Test
  void testValidateXml(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    try {
      Path path = Paths.get(SEDA22 + "seda_small.xml");
      sedaService.validate(path, sedaConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
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
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createComplexSip(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "simplesip2_seda.zip");
      sedaService.write(archiveTransfer, output, sedaConfig);

      sedaService.validate(output, null, sedaConfig, e -> LOGGER.info(e.toString()));
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
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
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createLargeSip(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "largesip2_seda.zip");
      sedaService.write(archiveTransfer, output, sedaConfig);
      sedaService.validate(output, sedaConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
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
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    try {
      ArchiveTransfer archiveTransfer = SipFactory.createSipFromDir();
      sedaService.write(
          archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "dirsip_seda.zip"), sedaConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
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
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    ArchiveTransfer archiveTransfer = SipFactory.createWithoutAgencySip();
    Path outputPath = Paths.get(TestInit.TEST_RESULTS + "fail_seda.zip");
    assertThrows(
        SipException.class, () -> sedaService.write(archiveTransfer, outputPath, sedaConfig));
  }

  /**
   * Test create csv sip.
   *
   * @param testInfo the test info
   */
  @Test
  void testCreateCsvSip(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    try {
      ArchiveTransfer archiveTransfer = SipFactory.createCsvSip();
      sedaService.write(
          archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "csvsip_seda.zip"), sedaConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
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
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));
    try {
      String jsonString = SipFactory.createJsonString();
      ArchiveTransfer archiveTransfer = JsonService.getInstance().read(jsonString);
      SedaService.getInstance()
          .write(
              archiveTransfer,
              Paths.get(TestInit.TEST_RESULTS + "freemarker_seda.zip"),
              sedaConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
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
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    try {
      ArchiveTransfer archiveTransfer = SipFactory.createFullTextSip();
      sedaService.write(
          archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "fulltextsip_seda.zip"), sedaConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
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
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    try {
      ArchiveTransfer archiveTransfer = SipFactory.createMiniSip();
      sedaService.write(
          archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "minisip_seda.zip"), sedaConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test validate mini sip.
   *
   * @param testInfo the test info
   */
  @Test
  void testValidateMiniSip(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    try {
      ArchiveTransfer archiveTransfer = SipFactory.createMiniSip();
      sedaService.validate(archiveTransfer, sedaConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test create sip full vitam.
   *
   * @param testInfo the test info
   */
  @Test
  void testCreateSipFullVitam(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    try {
      ArchiveTransfer archiveTransfer = SipFactory.createSipFullVitam();
      Path zipPath = Paths.get(TestInit.TEST_RESULTS + "sip_vitam_full.zip");
      sedaService.write(archiveTransfer, zipPath, sedaConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test create mini sip vitam.
   *
   * @param testInfo the test info
   */
  @Test
  void testCreateMiniSipVitam(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    try {
      ArchiveTransfer archiveTransfer = SipFactory.createMiniSipVitam();
      Path zipPath = Paths.get(TestInit.TEST_RESULTS + "MiniSipVitam_seda.zip");
      Path rngPath = Paths.get(SEDA22, "Profil_VITAM_base.rng");
      Validator rngValidator = Validators.getRngValidator(rngPath);
      sedaService.write(archiveTransfer, zipPath, rngValidator, sedaConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test validate mini sip vitam.
   *
   * @param testInfo the test info
   */
  @Test
  void testValidateMiniSipVitam(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    try {
      ArchiveTransfer archiveTransfer = SipFactory.createMiniSipVitam();
      Path rngPath = Paths.get(SEDA22, "Profil_VITAM_base.rng");
      Validator rngValidator = Validators.getRngValidator(rngPath);
      sedaService.validate(archiveTransfer, rngValidator, sedaConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
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
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createComplexSip(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "simplesip_seda.zip");
      sedaService.write(archiveTransfer, output, sedaConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
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
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createComplexSip(fs);
      sedaService.validate(archiveTransfer, sedaConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
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
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    try {
      ArchiveTransfer archiveTransfer = SipFactory.createSmallSip();
      Path zipPath = Paths.get(TestInit.TEST_RESULTS + "smallsip_seda.zip");
      sedaService.write(archiveTransfer, zipPath, sedaConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
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
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    try {
      ArchiveTransfer archiveTransfer = SipFactory.createSmallSip();
      sedaService.validate(archiveTransfer, sedaConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
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
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    SedaConfig config = SedaConfigBuilder.builder().useMemory(false).strict(false).build();

    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createLargeSip(fs);
      sedaService.write(
          archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "largesip_seda.zip"), config);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test validate large sip.
   *
   * @param testInfo the test info
   */
  @Test
  void testValidateLargeSip(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createLargeSip(fs);
      sedaService.validate(archiveTransfer, sedaConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
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
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createDeepSip(fs);
      sedaService.write(
          archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "deepsip_seda.zip"), sedaConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
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
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createDeepSip(fs);
      sedaService.validate(archiveTransfer, sedaConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test create simple dip.
   *
   * @param testInfo the test info
   */
  @Test
  void testCreateSimpleDelivery(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveDeliveryRequestReply archiveDelivery = SipFactory.createComplexDelivery(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "simpledelivery_seda.zip");
      sedaService.write(archiveDelivery, output, sedaConfig);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
      fail(msg);
    }
  }

  /**
   * Test create large dip.
   *
   * @param testInfo the test info
   */
  @Test
  void testCreateLargeDelivery(TestInfo testInfo) {
    LOGGER.info(TestUtils.TEST, TestUtils.getMethod(testInfo));

    SedaConfig config = SedaConfigBuilder.builder().useMemory(false).strict(false).build();

    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveDeliveryRequestReply archiveDelivery = SipFactory.createLargeDelivery(fs);
      sedaService.write(
          archiveDelivery, Paths.get(TestInit.TEST_RESULTS + "largedelivery_seda.zip"), config);
    } catch (Exception ex) {
      String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
      LOGGER.error(msg, ex);
      fail(msg);
    }
  }
}
