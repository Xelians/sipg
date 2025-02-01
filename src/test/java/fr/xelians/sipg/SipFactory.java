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
package fr.xelians.sipg;

import fr.xelians.sipg.model.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * The type Sip factory.
 *
 * @author Emmanuel Deviller
 */
public class SipFactory {

  private static ArchiveUnit visit(Path dir) throws IOException {
    ArchiveUnit au = new ArchiveUnit();
    au.addTitle(dir.getFileName().toString());
    try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
      for (Path path : stream) {
        au.addArchiveUnit(Files.isRegularFile(path) ? toUnit(path) : visit(path));
      }
    }
    return au;
  }

  private static ArchiveUnit toUnit(Path path) {
    ArchiveUnit unit = new ArchiveUnit();
    unit.setBinaryPath(path);
    unit.addTitle(path.getFileName().toString());
    return unit;
  }

  /**
   * Create sip from dir archive transfer.
   *
   * @return the archive transfer
   * @throws IOException the io exception
   */
  public static ArchiveTransfer createSipFromDir() throws IOException {
    ArchiveTransfer archiveTransfer = new ArchiveTransfer();
    archiveTransfer.setArchivalAgreement("My Agreement");
    archiveTransfer.setArchivalAgency("AG001", "My Archive Agency");
    archiveTransfer.setTransferringAgency("AG002", "My Transfer Agency");

    Path dirPath = Paths.get(TestInit.TEST_RESOURCES + "root");
    archiveTransfer.addArchiveUnit(visit(dirPath));
    return archiveTransfer;
  }

  /**
   * Create without agency sip archive transfer.
   *
   * @return the archive transfer
   */
  public static ArchiveTransfer createWithoutAgencySip() {
    Path binaryPath = Paths.get(TestInit.TEST_RESOURCES + "dummy.pdf");

    ArchiveUnit unit = new ArchiveUnit();
    unit.setBinaryPath(binaryPath);
    unit.addTitle("MyTitle");

    ArchiveTransfer archiveTransfer = new ArchiveTransfer();
    archiveTransfer.setArchivalAgreement("My Archival Agreement");
    archiveTransfer.addArchiveUnit(unit);
    return archiveTransfer;
  }

  /**
   * Create json string.
   *
   * @return the string
   * @throws IOException the io exception
   * @throws TemplateException the template exception
   */
  public static String createJsonString() throws IOException, TemplateException {
    Map<String, Object> input = new HashMap<>();
    input.put("Title", "My Great Title");
    input.put("ArchivalAgreement", "My Super Contract");
    input.put("BinaryPath", TestInit.TEST_RESOURCES + "dummy.pdf");

    Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
    cfg.setDefaultEncoding("UTF-8");
    cfg.setDirectoryForTemplateLoading(new File(TestInit.TEST_RESOURCES));
    Template template = cfg.getTemplate("minisip.ftl");
    StringWriter writer = new StringWriter();
    template.process(input, writer);
    return writer.toString();
  }

  /**
   * Create csv sip archive transfer.
   *
   * @return the archive transfer
   * @throws IOException the io exception
   */
  public static ArchiveTransfer createCsvSip() throws IOException {
    Path csvPath = Paths.get(TestInit.TEST_RESOURCES + "test.csv");

    ArchiveTransfer archiveTransfer = new ArchiveTransfer();
    archiveTransfer.setArchivalAgreement("My Agreement");
    archiveTransfer.setArchivalAgency("AG001", "My Archive Agency");
    archiveTransfer.setTransferringAgency("AG002", "My Transfer Agency");

    // association d'une valeur à une autre. clé = String et valeur = ArchiveUnit
    HashMap<String, ArchiveUnit> map = new HashMap<>();

    try (BufferedReader reader = Files.newBufferedReader(csvPath)) {
      CSVParser parser = CSVFormat.DEFAULT.builder().setDelimiter(';').build().parse(reader);
      for (CSVRecord csvRecord : parser) {
        ArchiveUnit unit = new ArchiveUnit();
        unit.setBinaryPath(Paths.get(TestInit.TEST_RESOURCES + csvRecord.get(2)));
        unit.setDescriptionLevel("File");

        unit.addTitle(csvRecord.get(3));
        unit.addTag(csvRecord.get(4));

        String parentId = csvRecord.get(1);
        if (parentId.isEmpty()) {
          archiveTransfer.addArchiveUnit(unit);
        } else {
          ArchiveUnit parentUnit = map.get(parentId);
          parentUnit.addArchiveUnit(unit);
        }
        map.put(csvRecord.get(0), unit);
      }
    }

    return archiveTransfer;
  }

  /**
   * Create full text sip archive transfer.
   *
   * @return the archive transfer
   */
  public static ArchiveTransfer createFullTextSip() {
    Path binaryPath = Paths.get(TestInit.TEST_RESOURCES + "citizenfour.pdf");

    ArchiveUnit unit = new ArchiveUnit();
    unit.setBinaryPath(binaryPath);
    unit.addTitle("Citoyen Quatre", "fr");
    unit.addTitle("Citizen Four", "en");
    unit.addDescription(TestUtils.extractTextFromPDF(binaryPath), "en");

    unit.addWriter(
        AgentBuilder.builder().withFullName("Marie-Sarah Deviller").addNationality("Fr").build());

    unit.addLanguage("en");
    unit.setDescriptionLanguage("en");

    LocalDate today = LocalDate.now();
    unit.setCreatedDate(today);
    unit.setSentDate(today);
    unit.setAcquiredDate(today);
    unit.setReceivedDate(today);
    unit.setRegisteredDate(today);

    ArchiveTransfer archiveTransfer = new ArchiveTransfer();
    archiveTransfer.setArchivalAgreement("NSA Agreement");
    archiveTransfer.setArchivalAgency("AG001", "");
    archiveTransfer.setTransferringAgency("AG002", "");
    archiveTransfer.addArchiveUnit(unit);

    return archiveTransfer;
  }

  /**
   * Create mini sip archive transfer.
   *
   * @return the archive transfer
   */
  public static ArchiveTransfer createMiniSip() {
    Path binaryPath = Paths.get(TestInit.TEST_RESOURCES + "dummy.pdf");

    ArchiveUnit unit = new ArchiveUnit();
    unit.setBinaryPath(binaryPath);
    unit.addTitle("My_Title");
    unit.addTag("This-is_a*tag");

    ArchiveTransfer archiveTransfer = new ArchiveTransfer();
    archiveTransfer.setArchivalAgreement("My Archival Agreement");
    archiveTransfer.setArchivalAgency("AG001", "");
    archiveTransfer.setTransferringAgency("AG002", "");
    archiveTransfer.addArchiveUnit(unit);

    return archiveTransfer;
  }

  /**
   * Create mini sip vitam archive transfer.
   *
   * @return the archive transfer
   */
  public static ArchiveTransfer createMiniSipVitam() {
    Path binaryPath = Paths.get(TestInit.TEST_RESOURCES + "enveloppe.docx");

    ArchiveUnit unit = new ArchiveUnit();
    unit.setBinaryPath(binaryPath);
    unit.setBinaryVersion("BinaryMaster_1");
    unit.addTitle("MyTitle");
    unit.addDescription("This is a description");

    ArchiveTransfer archiveTransfer = new ArchiveTransfer();
    archiveTransfer.setArchivalAgreement("My Archival Agreement");
    archiveTransfer.setArchivalAgency("AG001", "");
    archiveTransfer.setTransferringAgency("AG002", "");
    archiveTransfer.setOriginatingAgencyIdentifier("OriginatingAgencyId");
    archiveTransfer.setSubmissionAgencyIdentifier("SubmissionAgencyId");
    archiveTransfer.addArchiveUnit(unit);

    return archiveTransfer;
  }

  /**
   * Create sip full vitam archive transfer.
   *
   * @return the archive transfer
   */
  public static ArchiveTransfer createSipFullVitam() {
    Path binaryPath = Paths.get(TestInit.TEST_RESOURCES + "dummy.pdf");

    LocalDateTime todaytime = LocalDateTime.now();
    LocalDate today = LocalDate.now();

    ArchiveTransfer archiveTransfer = new ArchiveTransfer();
    archiveTransfer.setComment("Comment");
    archiveTransfer.setArchivalAgreement("IC-000001");
    archiveTransfer.setArchivalAgency("Identifier1", "My Archive Agency");
    archiveTransfer.setTransferringAgency("Identifier2", "My Transfer Agency");
    archiveTransfer.setOriginatingAgencyIdentifier("Identifier2");
    archiveTransfer.setSubmissionAgencyIdentifier("Identifier2");
    archiveTransfer.setArchivalProfile("PR-000001");
    archiveTransfer.setServiceLevel("Level1");
    archiveTransfer.setAcquisitionInformation("Don");
    archiveTransfer.setLegalStatus("Public Archive");

    ArchiveUnit unit1 = new ArchiveUnit();
    unit1.addTitle("AU de test VITAM niveau 1");
    unit1.addCustodialItem("Premier Custodial", todaytime);
    unit1.addCustodialItem("Second Custodial", todaytime);
    unit1.addDescription("Ceci est une description");
    unit1.setDocumentType("DocType");
    unit1.addTag("cle", "valeur");
    unit1.addTag("juste de l'indexation");
    unit1.addSystemId("SystemId");

    // Règles de gestion
    AccessRules accessRule = new AccessRules();
    accessRule.addRule("AR038", today);
    unit1.setAccessRules(accessRule);

    AppraisalRules appraisalRule = new AppraisalRules();
    appraisalRule.addRule("P01Y", today);
    appraisalRule.setFinalAction("Destroy");
    unit1.setAppraisalRules(appraisalRule);

    // AgentType
    unit1.addWriter(
        AgentBuilder.builder()
            .withFirstName("Baptiste")
            .withFullName("Nichele")
            .withBirthDate(LocalDate.ofEpochDay(0))
            .addActivity("Consultant")
            .addFunction("Tech")
            .withBirthPlace(PlaceBuilder.builder().withCountry("France").withCity("Paris").build())
            .build());

    // Extension d'ontologie
    Element extended = new Element("Extended", "Ontology extend 1");
    extended.addAttribute("Id", "attribute1");
    unit1.addElement(extended);
    unit1.addElement("<Extended2>Ontology extend 2</Extended2>");

    ArchiveUnit unit2 = new ArchiveUnit();
    unit2.setPhysicalId("physical-0002");
    unit2.setPhysicalVersion("PhysicalMaster");
    unit2.setMeasure(34);
    unit2.addTitle("AU PhysicalObject");

    ArchiveUnit unit3 = new ArchiveUnit();
    unit3.setBinaryPath(binaryPath);
    unit3.setBinaryVersion("BinaryMaster");
    unit3.setFormatId("fmt/19");
    unit3.setFormatName("PDF");
    unit3.setMimeType("application/pdf");
    unit3.setSignatureStatus("True");
    unit3.setFileInfo(
        FileInfoBuilder.builder()
            .withFilename("FileName.pdf")
            .withLastModified(todaytime)
            .withCreatingApplicationName("PdfBox")
            .build());
    unit3.addTitle("AU binaryObject");
    unit3.setUpdateOperation(new UpdateOperation("mySystemId"));

    ArchiveUnit unit4 = new ArchiveUnit();
    unit4.setBinaryPath(binaryPath);
    unit4.setPhysicalId("physical-0001");
    unit4.setPhysicalVersion("PhysicalMaster");
    unit4.addTitle("AU GOT binary and physical");
    unit4.setUpdateOperation(new UpdateOperation("MyTitle", "File1234"));

    unit1.addArchiveUnit(unit2);
    unit1.addArchiveUnit(unit3);
    unit1.addArchiveUnit(unit4);
    archiveTransfer.addArchiveUnit(unit1);

    unit1.addReference(new ArchiveUnitRef(unit2));
    unit1.addReference(new ArchiveUnitRef(unit3));

    return archiveTransfer;
  }

  /**
   * Create small sip archive transfer.
   *
   * @return the archive transfer
   */
  public static ArchiveTransfer createSmallSip() {
    Path binaryPath = Paths.get(TestInit.TEST_RESOURCES + "dummy.pdf");

    ArchiveTransfer archiveTransfer = new ArchiveTransfer();
    archiveTransfer.setArchivalAgreement("My Archival Agreement");
    archiveTransfer.setArchivalAgency("AG001", "My Archive Agency");
    archiveTransfer.setTransferringAgency("AG002", "My Transfer Agency");
    archiveTransfer.setArchivalProfile("MyArchivalProfile");
    archiveTransfer.setServiceLevel("Level1");

    ArchiveUnit unit = new ArchiveUnit();
    unit.setBinaryPath(binaryPath);
    unit.setFormatId("pdf");

    unit.addTitle("MyTitle1");
    unit.setDocumentType("DOC001");
    unit.addTag("MyKey1", "MyValue1");

    archiveTransfer.addArchiveUnit(unit);

    return archiveTransfer;
  }

  /**
   * Create simple sip archive transfer.
   *
   * @param fs the fs
   * @return the archive transfer
   */
  public static ArchiveTransfer createComplexSip(FileSystem fs) {
    Path binaryPath1 = fs.getPath("helloworld_1.pdf");
    TestUtils.createPdf("Hello World 1", binaryPath1);

    Path binaryPath2 = fs.getPath("helloworld_2.pdf");
    TestUtils.createPdf("Hello World 2", binaryPath2);

    ArchiveUnit unit1 = new ArchiveUnit();

    UpdateOperation updateOperation =
        new UpdateOperation("CleDeRattachement", "ValeurDeRattachement");
    unit1.setUpdateOperation(updateOperation);

    unit1.setPhysicalId("physical-0001");
    unit1.setPhysicalVersion("PhysicalMaster_1");
    unit1.setMeasure(26);

    LocalDate today = LocalDate.now();
    unit1.setAccessRules("AccessRule", today);

    AppraisalRules aRule1 = new AppraisalRules();
    aRule1.addRule("RuleName a1", today);
    aRule1.addRule("RuleName a2", today);
    aRule1.addRule("RuleName a3", today);
    aRule1.setPreventInheritance(true);
    aRule1.setFinalAction("Destroy");
    unit1.setAppraisalRules(aRule1);

    StorageRules sRule1 = new StorageRules();
    sRule1.addRule("RuleName s1", today);
    sRule1.addRule("RuleName s2", today);
    sRule1.addRule("RuleName s3", today);
    sRule1.setPreventInheritance(false);
    sRule1.setFinalAction("Copy");
    unit1.setStorageRules(sRule1);

    DisseminationRules dRule1 = new DisseminationRules();
    dRule1.addRule("RuleName d1", today);
    dRule1.addRule("RuleName d2", today);
    dRule1.addRule("RuleName d3", today);
    dRule1.addPreventRuleName("RuleName d4");
    dRule1.addPreventRuleName("RuleName d5");
    unit1.setDisseminationRules(dRule1);

    ReuseRules rRule1 = new ReuseRules();
    rRule1.addRule("RuleName r1", today);
    rRule1.addRule("RuleName r2", today);
    rRule1.addRule("RuleName r3", today);
    rRule1.addPreventRuleName("RuleName r4");
    rRule1.addPreventRuleName("RuleName r5");
    unit1.setReuseRules(rRule1);

    HoldRules hRule1 = new HoldRules();
    hRule1.addRule("RuleName h1", today, today, "MySelf1", "Maybe1", today, false);
    hRule1.addRule("RuleName h2", today, today, "MySelf2", null, today, false);
    hRule1.addRule("RuleName h3", today, today, "MySelf3", "Maybe3", null, false);
    hRule1.addPreventRuleName("RuleName r4");
    hRule1.addPreventRuleName("RuleName r5");
    unit1.setHoldRules(hRule1);

    LocalDateTime todaytime = LocalDateTime.now();

    Event event1 =
        EventBuilder.builder()
            .withDateTime(todaytime)
            .withDetail("MyDetails1")
            .withDetailData("MyDetailsData1")
            .withIdentifier("MyIdentifier1")
            .withOutcome("MyOutcome1")
            .withOutcomeDetail("MyOutcomeDetail1")
            .withType("MyType1")
            .withTypeCode("MyTypeCode1")
            .build();

    Event event2 =
        EventBuilder.builder()
            .withDateTime(todaytime)
            .withDetail("MyDetails2")
            .withDetailData("MyDetailsData2")
            .withIdentifier("MyIdentifier2")
            .withOutcome("MyOutcome2")
            .withOutcomeDetail("MyOutcomeDetail2")
            .withType("MyType2")
            .withTypeCode("MyTypeCode2")
            .build();

    Event event3 =
        EventBuilder.builder()
            .withDateTime(todaytime)
            .withDetail("MyDetails3")
            .withDetailData("MyDetailsData3")
            .withIdentifier("MyIdentifier3")
            .withOutcome("MyOutcome3")
            .withOutcomeDetail("MyOutcomeDetail3")
            .withType("MyType3")
            .withTypeCode("MyTypeCode3")
            .build();

    unit1.addLogEvent(event1);
    unit1.addLogEvent(event2);
    unit1.addLogEvent(event3);

    unit1.setDocumentType("DOC001");

    unit1.addCustodialItem("My Message1", todaytime);
    unit1.addCustodialItem("My Message2", todaytime);

    unit1.addSystemId("000001");
    unit1.addTitle("MyTitle1");
    unit1.addTag("MyKey11", "MyValue11");
    unit1.addTag("MyKey12", "MyValue12");
    unit1.addTag("MyValue13");

    Agency oriAgency = new Agency("AG002", "My Transfer Agency");
    oriAgency.addElement("Address", "Rue de la Jarry - Vincennes");

    unit1.setOriginatingAgency(oriAgency);
    unit1.setSubmissionAgency(new Agency("AG001", "My Archive Agency"));

    unit1.addAgent(
        AgentBuilder.builder()
            .withFirstName("Bernard")
            .withFullName("Campagnole")
            .withBirthDate(today)
            .addActivity("Actor")
            .addFunction("Senior")
            .withBirthPlace(
                PlaceBuilder.builder()
                    .withAddress("71 Cinema Street")
                    .withGeogName("GEOName")
                    .withPostalCode("91200")
                    .withRegion("Madrid")
                    .withCountry("Spain")
                    .withCity("MA")
                    .build())
            .build());

    unit1.addAddressee(
        AgentBuilder.builder()
            .withFirstName("Marc")
            .withFullName("Lavolle")
            .withBirthDate(today)
            .addActivity("Sword")
            .addFunction("Jedi")
            .withBirthPlace(
                PlaceBuilder.builder()
                    .withAddress("19 Holliday Street")
                    .withGeogName("GEOName")
                    .withPostalCode("94300")
                    .withRegion("Oregon")
                    .withCountry("USA")
                    .withCity("NY")
                    .build())
            .build());

    unit1.setSource("My Source1");

    Signer signer =
        SignerBuilder.builder()
            .withFirstName("Luc")
            .withFullName("Maroille")
            .withBirthDate(today)
            .addActivity("Sword")
            .addFunction("Jedi")
            .addMandate("Signer")
            .withBirthPlace(PlaceBuilder.builder().withCountry("USA").withCity("NY").build())
            .withSigningTime(todaytime)
            .build();

    Validator validator =
        ValidatorBuilder.builder()
            .withFirstName("Thomas")
            .withFullName("Pirlouette")
            .withBirthDate(today)
            .addActivity("Sword")
            .addFunction("Jedi")
            .addMandate("Validator")
            .withBirthPlace(PlaceBuilder.builder().withCountry("USA").withCity("NY").build())
            .withValidationTime(todaytime)
            .build();

    SigningInformation si = new SigningInformation();
    si.getSigningRoles().add(SigningRole.SIGNED_DOCUMENT);
    si.getDetachedSigningRoles().add(DetachedSigningRole.SIGNATURE);
    si.getSignatureDescriptions()
        .add(new SignatureDescription(signer, validator, "Tampon virtuel"));
    si.getAdditionalProofInformation().add("Ceci n'est pas une signature forgée");
    si.getTimestampingInformations().add(new TimestampingInformation(LocalDateTime.now(), null));
    //        si.getSignedDocumentReferenceIds().add("ID1245") ;
    unit1.setSigningInformation(si);

    ArchiveUnit unit2 = new ArchiveUnit();
    unit2.setPhysicalId("physical-0002");
    unit2.setVersion("Version2");
    unit2.setPhysicalVersion("PhysicalMaster_1");
    unit2.setMeasure(236);

    unit2.setBinaryPath(binaryPath1);
    unit2.setVersion("BinaryMaster_1");
    unit2.setFormatId("fmt/18");
    unit2.setFormatName("Portable Document Format");
    unit2.setFileInfo(
        FileInfoBuilder.builder()
            .withFilename("MyFile.pdf")
            .withLastModified(todaytime)
            .withCreatingApplicationName("PdfBox")
            .build());

    unit2.setDisseminationPath(binaryPath2);
    unit2.setDisseminationFormatId("fmt/18");
    unit2.setDisseminationFormatName("Portable Document Format");
    unit2.setDisseminationFileInfo(
        FileInfoBuilder.builder()
            .withFilename("MyFile.pdf")
            .withLastModified(todaytime)
            .withCreatingApplicationName("PdfBoxDissemination")
            .build());

    unit2.setThumbnailPath(binaryPath2);
    unit2.setThumbnailVersion("Thumbnail_2");
    unit2.setThumbnailFormatId("fmt/18");
    unit2.setThumbnailFormatName("Portable Document Format");
    unit2.setThumbnailFileInfo(
        FileInfoBuilder.builder()
            .withFilename("MyFile.pdf")
            .withLastModified(todaytime)
            .withCreatingApplicationName("PdfBoxThumbnail")
            .build());

    unit2.setTextContentPath(binaryPath2);
    unit2.setTextContentVersion("TextContent_1");
    unit2.setTextContentFormatId("pdf");
    unit2.setTextContentFormatName("Portable Document Format");
    unit2.setTextContentFileInfo(
        FileInfoBuilder.builder()
            .withFilename("MyFile.pdf")
            .withLastModified(todaytime)
            .withCreatingApplicationName("PdfBoxTextContent")
            .build());

    unit2.setSignatureStatus("No Signature");

    AccessRules aRule2 = new AccessRules();
    aRule2.addRule("RuleName 1", today);
    aRule2.addRule("RuleName 2", today);
    aRule2.addRule("RuleName 3", today);
    aRule2.addPreventRuleName("RuleName 4");
    aRule2.addPreventRuleName("RuleName 5");
    unit2.setAccessRules(aRule2);

    ClassificationRules cRule2 = new ClassificationRules();
    cRule2.addRule("RuleName 1", today);
    cRule2.addRule("RuleName 2", today);
    cRule2.addRule("RuleName 3", today);
    cRule2.addPreventRuleName("RuleName 4");
    cRule2.addPreventRuleName("RuleName 5");
    cRule2.setClassificationAudience("EveryOne");
    cRule2.setClassificationOwner("Captain Deviller");
    cRule2.setClassificationLevel("TOP SECRET");
    cRule2.setClassificationReassessingDate(today);
    cRule2.setNeedReassessingAuthorization(Boolean.FALSE);
    unit2.setClassificationRules(cRule2);

    unit2.setGpsAltitude("60");
    unit2.setGpsLatitude("48.8534");
    unit2.setGpsLongitude("2.3488");

    unit2.setArchiveUnitProfile("My Archive Unit Profile 2");
    unit2.setDocumentType("DOC002");
    unit2.addSystemId("000002");
    unit2.addTitle("MyTitle2");
    unit2.addTag("MyKey21", "MyValue21");
    unit2.addTag("MyKey22", "MyValue22");
    unit2.addTag("MyValue23");

    unit2.addAuthorizedAgent(
        AgentBuilder.builder()
            .withFirstName("Jacques")
            .withFullName("Terner")
            .withBirthDate(today)
            .withBirthName("Jacky Ho")
            .withGivenName("Joe")
            .withGender("Male")
            .addIdentifier("JH")
            .withDeathDate(today)
            .addActivity("Controller")
            .addFunction("BOSS")
            .addPosition("High")
            .addNationality("French")
            .addRole("SmallBoss")
            .addMandate("Mandataire")
            .withBirthPlace(
                PlaceBuilder.builder()
                    .withAddress("MyAddress")
                    .withGeogName("LND")
                    .withRegion("Sussex")
                    .withPostalCode("98765")
                    .withCountry("England")
                    .withCity("London")
                    .build())
            .withDeathPlace(
                PlaceBuilder.builder()
                    .withAddress("MyAddress")
                    .withGeogName("PRS")
                    .withRegion("Seine")
                    .withPostalCode("75012")
                    .withCountry("France")
                    .withCity("Paris")
                    .build())
            .build());

    unit2.addWriter(
        AgentBuilder.builder()
            .withFirstName("Emmanuel")
            .withFullName("Deviller")
            .withBirthDate(today)
            .addActivity("Developper")
            .addFunction("CTO")
            .withBirthPlace(PlaceBuilder.builder().withCountry("France").withCity("Paris").build())
            .build());

    unit2.addWriter(
        AgentBuilder.builder()
            .withFirstName("Baptiste")
            .withFullName("Nichele")
            .withBirthDate(today)
            .addActivity("Consultant")
            .addFunction("Expert")
            .withBirthPlace(
                PlaceBuilder.builder().withCountry("Germany").withCity("Baden-Baden").build())
            .build());

    unit2.addAddressee(
        AgentBuilder.builder()
            .withFirstName("Marc")
            .withFullName("Lavolle")
            .withBirthDate(today)
            .addActivity("Sword")
            .addFunction("Jedi")
            .withBirthPlace(PlaceBuilder.builder().withCountry("USA").withCity("NY").build())
            .build());

    unit2.addRecipient(
        AgentBuilder.builder()
            .withFirstName("Tom")
            .withFullName("Johns")
            .withBirthDate(today)
            .addActivity("Sword")
            .addFunction("Guerrier")
            .withBirthPlace(PlaceBuilder.builder().withCountry("USA").withCity("NY").build())
            .build());

    unit2.addTransmitter(
        AgentBuilder.builder()
            .withFirstName("Jacques")
            .withFullName("Garel")
            .withBirthDate(today)
            .addActivity("Lance")
            .addFunction("Magicien")
            .withBirthPlace(
                PlaceBuilder.builder().withCountry("Espagne").withCity("Madrid").build())
            .build());

    unit2.addSender(
        AgentBuilder.builder()
            .withFirstName("Ben")
            .withFullName("Targatien")
            .withBirthDate(today)
            .addActivity("Dague")
            .addFunction("Voleur")
            .withBirthPlace(
                PlaceBuilder.builder().withCountry("Royaume-Uni").withCity("Londres").build())
            .build());

    Element e1 = new Element("MyExtTag1", "MyExtValue1");
    e1.addAttribute("attr1", "val1");
    e1.addAttribute("attr12", "val12");

    Element e2 = new Element("MyExtTag2");
    e2.addAttribute("attr2", "val2");

    Element e3 = new Element("MyExtTag3", "MyExtValue3");
    e2.addElement(e3);

    unit2.addElement(e1);
    unit2.addElement(e2);
    unit2.addElement("<MyExtTag4>MyValue</MyExtTag4>");

    RelatedObjectRef ror = new RelatedObjectRef();
    ror.addVersionOf(new ArchiveUnitRef(unit1));
    ror.addVersionOf(new DataObjectRef(unit1));
    ror.addVersionOf(new RepositoryArchiveUnitPID("repo archive pid"));
    ror.addVersionOf(new RepositoryObjectPID("repo object pid"));
    ror.addVersionOf(new ExternalReference("Test external ref"));
    unit2.setRelation(ror);

    unit1.addArchiveUnit(unit2);

    ArchiveUnit unit3 = new ArchiveUnit();
    unit3.setBinaryPath(binaryPath2);
    unit3.setFormatId("pdf");
    unit3.setSignatureStatus("No Signature");
    unit3.setDocumentType("DOC003");
    unit3.addSystemId("000003");
    unit3.addTitle("MyTitle3");
    unit3.addTag("MyKey11", "MyValue11");
    unit3.addTag("MyValue13");
    unit3.setPhysicalId("physical-0001");

    AppraisalRules aRule3 = new AppraisalRules();
    aRule3.addRule("RuleName 31", today);
    aRule3.addRule("RuleName 32", today);
    aRule3.setPreventInheritance(true);
    aRule3.setFinalAction("Keep");
    unit3.setAppraisalRules(aRule3);

    Signature signature = new Signature();
    signature.addSigner(
        SignerBuilder.builder()
            .withFirstName("Marc")
            .withFullName("Lavolle")
            .withBirthDate(today)
            .addActivity("Sword")
            .addFunction("Jedi")
            .addMandate("Signer")
            .withBirthPlace(PlaceBuilder.builder().withCountry("USA").withCity("NY").build())
            .withSigningTime(todaytime)
            .build());
    signature.setValidator(
        ValidatorBuilder.builder()
            .withFirstName("Marc")
            .withFullName("Lavolle")
            .withBirthDate(today)
            .addActivity("Sword")
            .addFunction("Jedi")
            .addMandate("Validator")
            .withBirthPlace(PlaceBuilder.builder().withCountry("USA").withCity("NY").build())
            .withValidationTime(todaytime)
            .build());
    unit3.addSignature(signature);

    RelatedObjectRef relation = new RelatedObjectRef();
    relation.addRequire(new ArchiveUnitRef(unit1));
    relation.addPartOf(new ExternalReference("ExternalRef"));
    relation.addReference(new RepositoryArchiveUnitPID("RepoArcUnitPid"));
    relation.addReplace(new DataObjectRef(unit2));
    unit3.setRelation(relation);

    unit1.addArchiveUnit(unit3);

    CodeListVersions clvs = new CodeListVersions();
    clvs.setFileFormatCodeListVersion("Pronom Codes");
    clvs.setReplyCodeListVersion("Reply Codes");

    ArchiveTransfer archiveTransfer = new ArchiveTransfer();
    archiveTransfer.setMessageIdentifier("MSG001");
    archiveTransfer.setDate(todaytime);
    archiveTransfer.setComment("My Archive Transfer");
    archiveTransfer.setSignature("org.afnor.signformat.PKCS-7");
    archiveTransfer.setCodeListVersions(clvs);
    archiveTransfer.setArchivalAgreement("My Archival Agreement");
    archiveTransfer.setArchivalAgency(new Agency("AG001", "My Archive Agency"));
    archiveTransfer.setTransferringAgency(new Agency("AG002", "My Transfer Agency"));
    archiveTransfer.setOriginatingAgencyIdentifier("OriginatingAgencyId");
    archiveTransfer.setSubmissionAgencyIdentifier("SubmissionAgencyId");
    archiveTransfer.setArchivalProfile("My Archival Profile");
    archiveTransfer.setLegalStatus("Public Archive");
    archiveTransfer.setServiceLevel("My Service Level");
    archiveTransfer.addArchiveUnit(unit1);

    return archiveTransfer;
  }

  /**
   * Create large sip archive transfer.
   *
   * @param fs the fs
   * @return the archive transfer
   */
  public static ArchiveTransfer createLargeSip(FileSystem fs) {
    LocalDate today = LocalDate.now();
    LocalDateTime todaytime = LocalDateTime.now();

    ArchiveTransfer archiveTransfer = new ArchiveTransfer();
    archiveTransfer.setMessageIdentifier("MSG001");
    archiveTransfer.setDate(todaytime);
    archiveTransfer.setComment("My Archive Transfer");
    archiveTransfer.setArchivalAgreement("My Archival Agreement");
    archiveTransfer.setArchivalAgency(new Agency("AG001", "My Archive Agency"));
    archiveTransfer.setTransferringAgency(new Agency("AG002", "My Transfer Agency"));

    for (int i = 0; i < 100; i++) {
      Path binaryPath = fs.getPath("helloworld_" + i + ".pdf");
      TestUtils.createPdfWithImage("Hello World " + i, binaryPath);

      ArchiveUnit unit = new ArchiveUnit();
      unit.setBinaryPath(binaryPath);
      unit.setFormatId("pdf");
      unit.setSignatureStatus("No Signature");

      unit.setAccessRules("AccessRule", today);
      unit.setAppraisalRules("RuleName", today);
      unit.getAppraisalRules().setDuration("P2DT12H30M0S");
      unit.getAppraisalRules().setFinalAction("Destroy");

      unit.setDocumentType("DOC00" + i);
      unit.addSystemId(String.valueOf(i));
      unit.addTitle("MyTitle " + i);
      unit.addTag("MyKey1", "MyValue1_" + i);
      unit.addTag("MyKey1", "MyValue1_" + i);
      unit.addTag("MyValue13");

      archiveTransfer.addArchiveUnit(unit);
    }
    return archiveTransfer;
  }

  /**
   * Create deep sip archive transfer.
   *
   * @param fs the fs
   * @return the archive transfer
   */
  public static ArchiveTransfer createDeepSip(FileSystem fs) {
    LocalDate today = LocalDate.now();
    LocalDateTime todaytime = LocalDateTime.now();

    ArchiveTransfer archiveTransfer = new ArchiveTransfer();
    archiveTransfer.setMessageIdentifier("MSG001");
    archiveTransfer.setDate(todaytime);
    archiveTransfer.setComment("My Archive Transfer");
    archiveTransfer.setArchivalAgreement("My Archival Agreement");
    archiveTransfer.setArchivalAgency(new Agency("AG001", "My Archive Agency"));
    archiveTransfer.setTransferringAgency(new Agency("AG002", "My Transfer Agency"));

    ArchiveUnitContainer parentUnit = archiveTransfer;

    for (int i = 0; i < 32; i++) {
      Path binaryPath = fs.getPath("helloworld_" + i + ".pdf");
      TestUtils.createPdf("Hello World " + i, binaryPath);

      ArchiveUnit unit = new ArchiveUnit();
      unit.setBinaryPath(binaryPath);
      unit.setFormatId("pdf");
      unit.setSignatureStatus("No Signature");

      unit.setAccessRules("AccessRule", today);
      unit.setAppraisalRules("RuleName", today);
      unit.getAppraisalRules().setFinalAction("Keep");

      unit.setDocumentType("DOC00" + i);
      unit.addSystemId(String.valueOf(i));
      unit.addTitle("MyTitle " + i);
      unit.addTag("MyKey1", "MyValue1_" + i);

      parentUnit.addArchiveUnit(unit);
      parentUnit = unit;
    }
    return archiveTransfer;
  }

  /**
   * Create simple sip archive transfer.
   *
   * @param fs the fs
   * @return the archive transfer
   */
  public static ArchiveDeliveryRequestReply createComplexDelivery(FileSystem fs) {
    Path binaryPath1 = fs.getPath("helloworld_1.pdf");
    TestUtils.createPdf("Hello World 1", binaryPath1);

    Path binaryPath2 = fs.getPath("helloworld_2.pdf");
    TestUtils.createPdf("Hello World 2", binaryPath2);

    ArchiveUnit unit1 = new ArchiveUnit();

    UpdateOperation updateOperation =
        new UpdateOperation("CleDeRattachement", "ValeurDeRattachement");
    unit1.setUpdateOperation(updateOperation);

    unit1.setPhysicalId("physical-0001");
    unit1.setPhysicalVersion("PhysicalMaster_1");
    unit1.setMeasure(26);

    LocalDate today = LocalDate.now();
    unit1.setAccessRules("AccessRule", today);

    AppraisalRules aRule1 = new AppraisalRules();
    aRule1.addRule("RuleName a1", today);
    aRule1.addRule("RuleName a2", today);
    aRule1.addRule("RuleName a3", today);
    aRule1.setPreventInheritance(true);
    aRule1.setFinalAction("Destroy");
    unit1.setAppraisalRules(aRule1);

    StorageRules sRule1 = new StorageRules();
    sRule1.addRule("RuleName s1", today);
    sRule1.addRule("RuleName s2", today);
    sRule1.addRule("RuleName s3", today);
    sRule1.setPreventInheritance(false);
    sRule1.setFinalAction("Copy");
    unit1.setStorageRules(sRule1);

    DisseminationRules dRule1 = new DisseminationRules();
    dRule1.addRule("RuleName d1", today);
    dRule1.addRule("RuleName d2", today);
    dRule1.addRule("RuleName d3", today);
    dRule1.addPreventRuleName("RuleName d4");
    dRule1.addPreventRuleName("RuleName d5");
    unit1.setDisseminationRules(dRule1);

    ReuseRules rRule1 = new ReuseRules();
    rRule1.addRule("RuleName r1", today);
    rRule1.addRule("RuleName r2", today);
    rRule1.addRule("RuleName r3", today);
    rRule1.addPreventRuleName("RuleName r4");
    rRule1.addPreventRuleName("RuleName r5");
    unit1.setReuseRules(rRule1);

    HoldRules hRule1 = new HoldRules();
    hRule1.addRule("RuleName h1", today, today, "MySelf1", "Maybe1", today, false);
    hRule1.addRule("RuleName h2", today, today, "MySelf2", null, today, false);
    hRule1.addRule("RuleName h3", today, today, "MySelf3", "Maybe3", null, false);
    hRule1.addPreventRuleName("RuleName r4");
    hRule1.addPreventRuleName("RuleName r5");
    unit1.setHoldRules(hRule1);

    LocalDateTime todaytime = LocalDateTime.now();

    Event event1 =
        EventBuilder.builder()
            .withDateTime(todaytime)
            .withDetail("MyDetails1")
            .withDetailData("MyDetailsData1")
            .withIdentifier("MyIdentifier1")
            .withOutcome("MyOutcome1")
            .withOutcomeDetail("MyOutcomeDetail1")
            .withType("MyType1")
            .withTypeCode("MyTypeCode1")
            .build();

    Event event2 =
        EventBuilder.builder()
            .withDateTime(todaytime)
            .withDetail("MyDetails2")
            .withDetailData("MyDetailsData2")
            .withIdentifier("MyIdentifier2")
            .withOutcome("MyOutcome2")
            .withOutcomeDetail("MyOutcomeDetail2")
            .withType("MyType2")
            .withTypeCode("MyTypeCode2")
            .build();

    Event event3 =
        EventBuilder.builder()
            .withDateTime(todaytime)
            .withDetail("MyDetails3")
            .withDetailData("MyDetailsData3")
            .withIdentifier("MyIdentifier3")
            .withOutcome("MyOutcome3")
            .withOutcomeDetail("MyOutcomeDetail3")
            .withType("MyType3")
            .withTypeCode("MyTypeCode3")
            .build();

    unit1.addLogEvent(event1);
    unit1.addLogEvent(event2);
    unit1.addLogEvent(event3);

    unit1.setDocumentType("DOC001");

    unit1.addCustodialItem("My Message1", todaytime);
    unit1.addCustodialItem("My Message2", todaytime);

    unit1.addSystemId("000001");
    unit1.addTitle("MyTitle1");
    unit1.addTag("MyKey11", "MyValue11");
    unit1.addTag("MyKey12", "MyValue12");
    unit1.addTag("MyValue13");

    Agency oriAgency = new Agency("AG002", "My Transfer Agency");
    oriAgency.addElement("Address", "Rue de la Jarry - Vincennes");

    unit1.setOriginatingAgency(oriAgency);
    unit1.setSubmissionAgency(new Agency("AG001", "My Archive Agency"));

    unit1.addAddressee(
        AgentBuilder.builder()
            .withFirstName("Marc")
            .withFullName("Lavolle")
            .withBirthDate(today)
            .addActivity("Sword")
            .addFunction("Jedi")
            .withBirthPlace(
                PlaceBuilder.builder()
                    .withAddress("19 Holliday Street")
                    .withGeogName("GEOName")
                    .withPostalCode("94300")
                    .withRegion("Oregon")
                    .withCountry("USA")
                    .withCity("NY")
                    .build())
            .build());

    unit1.setSource("My Source1");

    ArchiveUnit unit2 = new ArchiveUnit();
    unit2.setPhysicalId("physical-0002");
    unit2.setVersion("Version2");
    unit2.setPhysicalVersion("PhysicalMaster_1");
    unit2.setMeasure(236);

    unit2.setBinaryPath(binaryPath1);
    unit2.setVersion("BinaryMaster_1");
    unit2.setFormatId("fmt/18");
    unit2.setFormatName("Portable Document Format");
    unit2.setFileInfo(
        FileInfoBuilder.builder()
            .withFilename("MyFile.pdf")
            .withLastModified(todaytime)
            .withCreatingApplicationName("PdfBox")
            .build());

    unit2.setDisseminationPath(binaryPath2);
    unit2.setDisseminationFormatId("fmt/18");
    unit2.setDisseminationFormatName("Portable Document Format");
    unit2.setDisseminationFileInfo(
        FileInfoBuilder.builder()
            .withFilename("MyFile.pdf")
            .withLastModified(todaytime)
            .withCreatingApplicationName("PdfBoxDissemination")
            .build());

    unit2.setThumbnailPath(binaryPath2);
    unit2.setThumbnailVersion("Thumbnail_2");
    unit2.setThumbnailFormatId("fmt/18");
    unit2.setThumbnailFormatName("Portable Document Format");
    unit2.setThumbnailFileInfo(
        FileInfoBuilder.builder()
            .withFilename("MyFile.pdf")
            .withLastModified(todaytime)
            .withCreatingApplicationName("PdfBoxThumbnail")
            .build());

    unit2.setTextContentPath(binaryPath2);
    unit2.setTextContentVersion("TextContent_1");
    unit2.setTextContentFormatId("pdf");
    unit2.setTextContentFormatName("Portable Document Format");
    unit2.setTextContentFileInfo(
        FileInfoBuilder.builder()
            .withFilename("MyFile.pdf")
            .withLastModified(todaytime)
            .withCreatingApplicationName("PdfBoxTextContent")
            .build());

    unit2.setSignatureStatus("No Signature");

    AccessRules aRule2 = new AccessRules();
    aRule2.addRule("RuleName 1", today);
    aRule2.addRule("RuleName 2", today);
    aRule2.addRule("RuleName 3", today);
    aRule2.addPreventRuleName("RuleName 4");
    aRule2.addPreventRuleName("RuleName 5");
    unit2.setAccessRules(aRule2);

    ClassificationRules cRule2 = new ClassificationRules();
    cRule2.addRule("RuleName 1", today);
    cRule2.addRule("RuleName 2", today);
    cRule2.addRule("RuleName 3", today);
    cRule2.addPreventRuleName("RuleName 4");
    cRule2.addPreventRuleName("RuleName 5");
    cRule2.setClassificationAudience("EveryOne");
    cRule2.setClassificationOwner("Captain Deviller");
    cRule2.setClassificationLevel("TOP SECRET");
    cRule2.setClassificationReassessingDate(today);
    cRule2.setNeedReassessingAuthorization(Boolean.FALSE);
    unit2.setClassificationRules(cRule2);

    unit2.setGpsAltitude("60");
    unit2.setGpsLatitude("48.8534");
    unit2.setGpsLongitude("2.3488");

    unit2.setArchiveUnitProfile("My Archive Unit Profile 2");
    unit2.setDocumentType("DOC002");
    unit2.addSystemId("000002");
    unit2.addTitle("MyTitle2");
    unit2.addTag("MyKey21", "MyValue21");
    unit2.addTag("MyKey22", "MyValue22");
    unit2.addTag("MyValue23");

    unit2.addAuthorizedAgent(
        AgentBuilder.builder()
            .withFirstName("Jacques")
            .withFullName("Terner")
            .withBirthDate(today)
            .withBirthName("Jacky Ho")
            .withGivenName("Joe")
            .withGender("Male")
            .addIdentifier("JH")
            .withDeathDate(today)
            .addActivity("Controller")
            .addFunction("BOSS")
            .addPosition("High")
            .addNationality("French")
            .addRole("SmallBoss")
            .addMandate("Mandataire")
            .withBirthPlace(
                PlaceBuilder.builder()
                    .withAddress("MyAddress")
                    .withGeogName("LND")
                    .withRegion("Sussex")
                    .withPostalCode("98765")
                    .withCountry("England")
                    .withCity("London")
                    .build())
            .withDeathPlace(
                PlaceBuilder.builder()
                    .withAddress("MyAddress")
                    .withGeogName("PRS")
                    .withRegion("Seine")
                    .withPostalCode("75012")
                    .withCountry("France")
                    .withCity("Paris")
                    .build())
            .build());

    unit2.addWriter(
        AgentBuilder.builder()
            .withFirstName("Emmanuel")
            .withFullName("Deviller")
            .withBirthDate(today)
            .addActivity("Developper")
            .addFunction("CTO")
            .withBirthPlace(PlaceBuilder.builder().withCountry("France").withCity("Paris").build())
            .build());

    unit2.addWriter(
        AgentBuilder.builder()
            .withFirstName("Baptiste")
            .withFullName("Nichele")
            .withBirthDate(today)
            .addActivity("Consultant")
            .addFunction("Expert")
            .withBirthPlace(
                PlaceBuilder.builder().withCountry("Germany").withCity("Baden-Baden").build())
            .build());

    unit2.addAddressee(
        AgentBuilder.builder()
            .withFirstName("Marc")
            .withFullName("Lavolle")
            .withBirthDate(today)
            .addActivity("Sword")
            .addFunction("Jedi")
            .withBirthPlace(PlaceBuilder.builder().withCountry("USA").withCity("NY").build())
            .build());

    unit2.addRecipient(
        AgentBuilder.builder()
            .withFirstName("Tom")
            .withFullName("Johns")
            .withBirthDate(today)
            .addActivity("Sword")
            .addFunction("Guerrier")
            .withBirthPlace(PlaceBuilder.builder().withCountry("USA").withCity("NY").build())
            .build());

    unit2.addTransmitter(
        AgentBuilder.builder()
            .withFirstName("Jacques")
            .withFullName("Garel")
            .withBirthDate(today)
            .addActivity("Lance")
            .addFunction("Magicien")
            .withBirthPlace(
                PlaceBuilder.builder().withCountry("Espagne").withCity("Madrid").build())
            .build());

    unit2.addSender(
        AgentBuilder.builder()
            .withFirstName("Ben")
            .withFullName("Targatien")
            .withBirthDate(today)
            .addActivity("Dague")
            .addFunction("Voleur")
            .withBirthPlace(
                PlaceBuilder.builder().withCountry("Royaume-Uni").withCity("Londres").build())
            .build());

    Element e1 = new Element("MyExtTag1", "MyExtValue1");
    e1.addAttribute("attr1", "val1");
    e1.addAttribute("attr12", "val12");

    Element e2 = new Element("MyExtTag2");
    e2.addAttribute("attr2", "val2");

    Element e3 = new Element("MyExtTag3", "MyExtValue3");
    e2.addElement(e3);

    unit2.addElement(e1);
    unit2.addElement(e2);
    unit2.addElement("<MyExtTag4>MyValue</MyExtTag4>");

    RelatedObjectRef ror = new RelatedObjectRef();
    ror.addVersionOf(new ArchiveUnitRef(unit1));
    ror.addVersionOf(new DataObjectRef(unit1));
    ror.addVersionOf(new RepositoryArchiveUnitPID("repo archive pid"));
    ror.addVersionOf(new RepositoryObjectPID("repo object pid"));
    ror.addVersionOf(new ExternalReference("Test external ref"));
    unit2.setRelation(ror);

    unit1.addArchiveUnit(unit2);

    ArchiveUnit unit3 = new ArchiveUnit();
    unit3.setBinaryPath(binaryPath2);
    unit3.setFormatId("pdf");
    unit3.setSignatureStatus("No Signature");
    unit3.setDocumentType("DOC003");
    unit3.addSystemId("000003");
    unit3.addTitle("MyTitle3");
    unit3.addTag("MyKey11", "MyValue11");
    unit3.addTag("MyValue13");
    unit3.setPhysicalId("physical-0001");

    AppraisalRules aRule3 = new AppraisalRules();
    aRule3.addRule("RuleName 31", today);
    aRule3.addRule("RuleName 32", today);
    aRule3.setPreventInheritance(true);
    aRule3.setFinalAction("Keep");
    unit3.setAppraisalRules(aRule3);

    Signer signer =
        SignerBuilder.builder()
            .withFirstName("Marc")
            .withFullName("Lavolle")
            .withBirthDate(today)
            .addActivity("Sword")
            .addFunction("Jedi")
            .addMandate("Signer")
            .withBirthPlace(PlaceBuilder.builder().withCountry("USA").withCity("NY").build())
            .withSigningTime(todaytime)
            .build();

    Validator validator =
        ValidatorBuilder.builder()
            .withFirstName("Marc")
            .withFullName("Lavolle")
            .withBirthDate(today)
            .addActivity("Sword")
            .addFunction("Jedi")
            .addMandate("Validator")
            .withBirthPlace(PlaceBuilder.builder().withCountry("USA").withCity("NY").build())
            .withValidationTime(todaytime)
            .build();

    Signature signature = new Signature();
    signature.addSigner(signer);
    signature.setValidator(validator);
    unit3.addSignature(signature);

    RelatedObjectRef relation = new RelatedObjectRef();
    relation.addRequire(new ArchiveUnitRef(unit1));
    relation.addPartOf(new ExternalReference("ExternalRef"));
    relation.addReference(new RepositoryArchiveUnitPID("RepoArcUnitPid"));
    relation.addReplace(new DataObjectRef(unit2));
    unit3.setRelation(relation);

    unit1.addArchiveUnit(unit3);

    CodeListVersions clvs = new CodeListVersions();
    clvs.setFileFormatCodeListVersion("Pronom Codes");
    clvs.setReplyCodeListVersion("Reply Codes");

    ArchiveDeliveryRequestReply archiveDelivery = new ArchiveDeliveryRequestReply();
    archiveDelivery.setMessageIdentifier("MSG001");
    archiveDelivery.setDate(todaytime);
    archiveDelivery.setComment("My Archive Transfer");
    archiveDelivery.setSignature("org.afnor.signformat.PKCS-7");
    archiveDelivery.setCodeListVersions(clvs);
    archiveDelivery.setArchivalAgreement("My Archival Agreement");
    archiveDelivery.setArchivalAgency(new Agency("AG001", "My Archive Agency"));
    archiveDelivery.setUnitIdentifier("UNKNOWN_UNIT");
    archiveDelivery.setMessageRequestIdentifier("REQ-0001");
    archiveDelivery.setReplyCode("OK");
    archiveDelivery.setRequester(new Agency("REQUESTER-001", "TheRequester"));

    archiveDelivery.addArchiveUnit(unit1);
    return archiveDelivery;
  }

  /**
   * Create large sip archive transfer.
   *
   * @param fs the fs
   * @return the archive transfer
   */
  public static ArchiveDeliveryRequestReply createLargeDelivery(FileSystem fs) {
    LocalDate today = LocalDate.now();
    LocalDateTime todaytime = LocalDateTime.now();

    ArchiveDeliveryRequestReply archiveDelivery = new ArchiveDeliveryRequestReply();
    archiveDelivery.setMessageIdentifier("MSG001");
    archiveDelivery.setDate(todaytime);
    archiveDelivery.setComment("My Archive Transfer");
    archiveDelivery.setArchivalAgreement("My Archival Agreement");
    archiveDelivery.setArchivalAgency(new Agency("AG001", "My Archive Agency"));

    archiveDelivery.setUnitIdentifier("UNKNOWN_UNIT");
    archiveDelivery.setMessageRequestIdentifier("REQ-0001");
    archiveDelivery.setReplyCode("OK");
    archiveDelivery.setRequester(new Agency("REQUESTER-001", null));

    for (int i = 0; i < 100; i++) {
      Path binaryPath = fs.getPath("helloworld_" + i + ".pdf");
      TestUtils.createPdfWithImage("Hello World " + i, binaryPath);

      ArchiveUnit unit = new ArchiveUnit();
      unit.setBinaryPath(binaryPath);
      unit.setFormatId("pdf");
      unit.setSignatureStatus("No Signature");

      unit.setAccessRules("AccessRule", today);
      unit.setAppraisalRules("RuleName", today);
      unit.getAppraisalRules().setDuration("P2DT12H30M0S");
      unit.getAppraisalRules().setFinalAction("Destroy");

      unit.setDocumentType("DOC00" + i);
      unit.addSystemId(String.valueOf(i));
      unit.addTitle("MyTitle " + i);
      unit.addTag("MyKey1", "MyValue1_" + i);
      unit.addTag("MyKey1", "MyValue1_" + i);
      unit.addTag("MyValue13");

      archiveDelivery.addArchiveUnit(unit);
    }
    return archiveDelivery;
  }
}
