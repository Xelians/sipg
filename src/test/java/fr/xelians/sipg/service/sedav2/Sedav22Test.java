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
package fr.xelians.sipg.service.sedav2;

import static org.junit.jupiter.api.Assertions.*;

import com.google.common.jimfs.Jimfs;
import fr.gouv.culture.archivesdefrance.seda.v22.ArchiveTransferType;
import fr.xelians.sipg.SipFactory;
import fr.xelians.sipg.TestInit;
import fr.xelians.sipg.TestUtils;
import fr.xelians.sipg.model.ArchiveDeliveryRequestReply;
import fr.xelians.sipg.model.ArchiveTransfer;
import fr.xelians.sipg.service.json.JsonService;
import fr.xelians.sipg.utils.SipException;
import fr.xelians.sipg.utils.Validators;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.xml.validation.Validator;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Nodes;
import nu.xom.XPathContext;
import org.junit.jupiter.api.Test;
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
  private final Sedav2Service sedaService = Sedav2Service.getV22Instance();

  /** Test validate xml. */
  @Test
  void testValidateXml() {
    Path path = Paths.get(SEDA22 + "seda_small.xml");
    sedaService.validate(path, sedaConfig);
  }

  /** Test validate simple zip. */
  @Test
  void testValidateSimpleZip() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createComplexSip(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "simplesip2_seda.zip");
      sedaService.write(archiveTransfer, output, sedaConfig);
      assertTrue(Files.exists(output));

      sedaService.validate(output, null, sedaConfig, e -> LOGGER.info(e.toString()));
    }
  }

  /** Test validate large zip. */
  @Test
  void testValidateLargeZip() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createLargeSip(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "largesip2_seda.zip");
      sedaService.write(archiveTransfer, output, sedaConfig);
      assertTrue(Files.exists(output));
      sedaService.validate(output, sedaConfig);
    }
  }

  /** Test create dir sip. */
  @Test
  void testCreateDirSip() throws Exception {
    ArchiveTransfer archiveTransfer = SipFactory.createSipFromDir();
    Path output = Paths.get(TestInit.TEST_RESULTS + "dirsip_seda.zip");
    sedaService.write(archiveTransfer, output, sedaConfig);
    assertTrue(Files.exists(output));
  }

  /** Test without agency fail. */
  @Test
  void testWithoutAgencyFail() {
    ArchiveTransfer archiveTransfer = SipFactory.createWithoutAgencySip();
    Path outputPath = Paths.get(TestInit.TEST_RESULTS + "fail_seda.zip");
    assertThrows(
        SipException.class, () -> sedaService.write(archiveTransfer, outputPath, sedaConfig));
  }

  /** Test create csv sip. */
  @Test
  void testCreateCsvSip() throws Exception {
    ArchiveTransfer archiveTransfer = SipFactory.createCsvSip();
    Path output = Paths.get(TestInit.TEST_RESULTS + "csvsip_seda.zip");
    sedaService.write(archiveTransfer, output, sedaConfig);
    assertTrue(Files.exists(output));
  }

  /** Test read freemarker json. */
  @Test
  void testReadFreemarkerJson() throws Exception {
    String jsonString = SipFactory.createJsonString();
    ArchiveTransfer archiveTransfer = JsonService.getInstance().read(jsonString);
    Path output = Paths.get(TestInit.TEST_RESULTS + "freemarker_seda.zip");
    Sedav2Service.getInstance().write(archiveTransfer, output, sedaConfig);
    assertTrue(Files.exists(output));
  }

  /** Test create full text sip. */
  @Test
  void testCreateFullTextSip() {
    ArchiveTransfer archiveTransfer = SipFactory.createFullTextSip();
    Path output = Paths.get(TestInit.TEST_RESULTS + "fulltextsip_seda.zip");
    sedaService.write(archiveTransfer, output, sedaConfig);
    assertTrue(Files.exists(output));
  }

  /** Test create mini sip. */
  @Test
  void testCreateMiniSip() {
    ArchiveTransfer archiveTransfer = SipFactory.createMiniSip();
    Path output = Paths.get(TestInit.TEST_RESULTS + "minisip_seda.zip");
    sedaService.write(archiveTransfer, output, sedaConfig);
    assertTrue(Files.exists(output));
  }

  /** Test validate mini sip. */
  @Test
  void testValidateMiniSip() {
    ArchiveTransfer archiveTransfer = SipFactory.createMiniSip();
    sedaService.validate(archiveTransfer, sedaConfig);
  }

  /** Test create sip full vitam. */
  @Test
  void testCreateSipFullVitam() {
    ArchiveTransfer archiveTransfer = SipFactory.createSipFullVitam();
    Path zipPath = Paths.get(TestInit.TEST_RESULTS + "sip_vitam_full.zip");
    sedaService.write(archiveTransfer, zipPath, sedaConfig);
    assertTrue(Files.exists(zipPath));
  }

  /** Test create mini sip vitam. */
  @Test
  void testCreateMiniSipVitam() {
    ArchiveTransfer archiveTransfer = SipFactory.createMiniSipVitam();
    Path zipPath = Paths.get(TestInit.TEST_RESULTS + "MiniSipVitam_seda.zip");
    Path rngPath = Paths.get(SEDA22, "Profil_VITAM_base.rng");
    Validator rngValidator = Validators.getRngValidator(rngPath);
    sedaService.write(archiveTransfer, zipPath, rngValidator, sedaConfig);
    assertTrue(Files.exists(zipPath));
  }

  /** Test validate mini sip vitam. */
  @Test
  void testValidateMiniSipVitam() {
    ArchiveTransfer archiveTransfer = SipFactory.createMiniSipVitam();
    Path rngPath = Paths.get(SEDA22, "Profil_VITAM_base.rng");
    Validator rngValidator = Validators.getRngValidator(rngPath);
    sedaService.validate(archiveTransfer, rngValidator, sedaConfig);
  }

  /** Test create simple sip. */
  @Test
  void testCreateComplexSip() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createComplexSip(fs);
      Path sipPath = Paths.get(TestInit.TEST_RESULTS + "complexsip_seda.zip");
      sedaService.write(archiveTransfer, sipPath, sedaConfig);

      assertTrue(Files.exists(sipPath));
      assertTrue(Files.size(sipPath) > 0);

      try (ZipFile zipFile = new ZipFile(sipPath.toFile())) {
        ZipEntry entry = zipFile.getEntry("manifest.xml");
        assertNotNull(entry);

        try (InputStream is = zipFile.getInputStream(entry)) {
          Builder builder = new Builder();
          Document doc = builder.build(is);
          XPathContext context =
              new XPathContext("seda", "fr:gouv:culture:archivesdefrance:seda:v2.2");

          Nodes messageIdentifier = doc.query("//seda:MessageIdentifier", context);
          assertEquals("MSG001", messageIdentifier.get(0).getValue());

          Nodes archivalAgreement = doc.query("//seda:ArchivalAgreement", context);
          assertEquals("My Archival Agreement", archivalAgreement.get(0).getValue());

          Nodes archivalAgency = doc.query("//seda:ArchivalAgency/seda:Identifier", context);
          assertEquals("AG001", archivalAgency.get(0).getValue());

          Nodes transferringAgency =
              doc.query("//seda:TransferringAgency/seda:Identifier", context);
          assertEquals("AG002", transferringAgency.get(0).getValue());

          // ArchiveUnit tests
          Nodes archiveUnits = doc.query("//seda:ArchiveUnit", context);
          assertTrue(archiveUnits.size() >= 3); // unit1, unit2, unit3

          // Test unit1
          Nodes unit1Title =
              doc.query("//seda:ArchiveUnit[seda:Content/seda:Title='MyTitle1']", context);
          assertEquals(1, unit1Title.size());

          Nodes unit1DocType =
              doc.query(
                  "//seda:ArchiveUnit[seda:Content/seda:Title='MyTitle1']/seda:Content/seda:DocumentType",
                  context);
          assertEquals("DOC001", unit1DocType.get(0).getValue());

          // AccessRule on unit1
          Nodes unit1AccessRule =
              doc.query(
                  "//seda:ArchiveUnit[seda:Content/seda:Title='MyTitle1']/seda:Management/seda:AccessRule/seda:Rule",
                  context);
          assertEquals("AccessRule", unit1AccessRule.get(0).getValue());

          Nodes unit1AccessDate =
              doc.query(
                  "//seda:ArchiveUnit[seda:Content/seda:Title='MyTitle1']/seda:Management/seda:AccessRule/seda:StartDate",
                  context);
          assertEquals("2026-03-10", unit1AccessDate.get(0).getValue());

          // AppraisalRule on unit1
          Nodes unit1AppraisalFinalAction =
              doc.query(
                  "//seda:ArchiveUnit[seda:Content/seda:Title='MyTitle1']/seda:Management/seda:AppraisalRule/seda:FinalAction",
                  context);
          assertEquals("Destroy", unit1AppraisalFinalAction.get(0).getValue());

          // StorageRule on unit1
          Nodes unit1StorageFinalAction =
              doc.query(
                  "//seda:ArchiveUnit[seda:Content/seda:Title='MyTitle1']/seda:Management/seda:StorageRule/seda:FinalAction",
                  context);
          assertEquals("Copy", unit1StorageFinalAction.get(0).getValue());

          // DisseminationRule on unit1
          Nodes unit1DisseminationRule =
              doc.query(
                  "//seda:ArchiveUnit[seda:Content/seda:Title='MyTitle1']/seda:Management/seda:DisseminationRule/seda:Rule",
                  context);
          assertTrue(unit1DisseminationRule.size() >= 3);

          // ReuseRule on unit1
          Nodes unit1ReuseRule =
              doc.query(
                  "//seda:ArchiveUnit[seda:Content/seda:Title='MyTitle1']/seda:Management/seda:ReuseRule/seda:Rule",
                  context);
          assertTrue(unit1ReuseRule.size() >= 3);

          // Test unit2 (child of unit1)
          Nodes unit2Title =
              doc.query(
                  "//seda:ArchiveUnit[seda:Content/seda:Title='MyTitle1']/seda:ArchiveUnit[seda:Content/seda:Title='MyTitle2']",
                  context);
          assertEquals(1, unit2Title.size());

          Nodes unit2DocType =
              doc.query(
                  "//seda:ArchiveUnit[seda:Content/seda:Title='MyTitle2']/seda:Content/seda:DocumentType",
                  context);
          assertEquals("DOC002", unit2DocType.get(0).getValue());
        }
      }
    }
  }

  /** Test validate simple sip. */
  @Test
  void testValidateComplexSip() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createComplexSip(fs);
      sedaService.validate(archiveTransfer, sedaConfig);
    }
  }

  /** Test create small sip. */
  @Test
  void testCreateSmallSip() {
    ArchiveTransfer archiveTransfer = SipFactory.createSmallSip();
    Path zipPath = Paths.get(TestInit.TEST_RESULTS + "smallsip_seda.zip");
    sedaService.write(archiveTransfer, zipPath, sedaConfig);
    assertTrue(Files.exists(zipPath));
  }

  /** Test validate small sip. */
  @Test
  void testValidateSmallSip() {
    ArchiveTransfer archiveTransfer = SipFactory.createSmallSip();
    sedaService.validate(archiveTransfer, sedaConfig);
  }

  /** Test create large sip. */
  @Test
  void testCreateLargeSip() throws Exception {
    SedaConfig config = SedaConfigBuilder.builder().useMemory(false).strict(false).build();

    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createLargeSip(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "largesip_seda.zip");
      sedaService.write(archiveTransfer, output, config);
      assertTrue(Files.exists(output));
    }
  }

  /** Test validate large sip. */
  @Test
  void testValidateLargeSip() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createLargeSip(fs);
      sedaService.validate(archiveTransfer, sedaConfig);
    }
  }

  /** Test create deep sip. */
  @Test
  void testCreateDeepSip() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createDeepSip(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "deepsip_seda.zip");
      sedaService.write(archiveTransfer, output, sedaConfig);
      assertTrue(Files.exists(output));
    }
  }

  /** Test validate deep sip. */
  @Test
  void testValidateDeepSip() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveTransfer archiveTransfer = SipFactory.createDeepSip(fs);
      sedaService.validate(archiveTransfer, sedaConfig);
    }
  }

  /** Test create simple dip. */
  @Test
  void testCreateSimpleDelivery() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveDeliveryRequestReply archiveDelivery = SipFactory.createComplexDelivery(fs);
      Path output = Paths.get(TestInit.TEST_RESULTS + "simpledelivery_seda.zip");
      sedaService.write(archiveDelivery, output, sedaConfig);
      assertTrue(Files.exists(output));
    }
  }

  /** Test create large dip. */
  @Test
  void testCreateLargeDelivery() throws Exception {
    SedaConfig config = SedaConfigBuilder.builder().useMemory(false).strict(false).build();

    try (FileSystem fs = Jimfs.newFileSystem()) {
      ArchiveDeliveryRequestReply archiveDelivery = SipFactory.createLargeDelivery(fs);
      Path sipPath = Paths.get(TestInit.TEST_RESULTS + "largedelivery_seda.zip");
      sedaService.write(archiveDelivery, sipPath, config);
      assertTrue(Files.exists(sipPath));
    }
  }

  @Test
  void testMarshalComplexArchiveTransfer() throws Exception {
    try (FileSystem fs = Jimfs.newFileSystem();
        InputStream stream =
            getClass().getClassLoader().getResourceAsStream("seda-2.2/seda_complex.xml")) {
      ArchiveTransfer archiveTransfer = SipFactory.createComplexSip(fs);
      InputStream atMarshalled =
          Sedav2Service.getV22Instance().marshal(archiveTransfer, sedaConfig);
      assertNotNull(atMarshalled);
      assertNotNull(stream);
      assertEquals(TestUtils.readAsString(stream), TestUtils.readAsString(atMarshalled));
    }
  }

  @Test
  void testUnMarshalSmallArchiveTransferType() throws Exception {
    try (InputStream sedaStream =
        getClass().getClassLoader().getResourceAsStream("seda-2.2/seda_small.xml")) {
      ArchiveTransferType attUnMarshalled =
          Sedav2Service.getV22Instance()
              .unmarshal(sedaStream, ArchiveTransferType.class, sedaConfig);
      assertNotNull(attUnMarshalled);
    }
  }
}
