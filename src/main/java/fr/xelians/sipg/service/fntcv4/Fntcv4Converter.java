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

import static fr.xelians.sipg.utils.SipUtils.ifNotNull;

import fr.xelians.sipg.model.*;
import fr.xelians.sipg.utils.DroidUtils;
import fr.xelians.sipg.utils.SipException;
import fr.xelians.sipg.utils.SipUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.afnor.medona.v1.*;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.fntc.ta.v4.*;
import org.fntc.ta.v4.AccessRuleType;
import org.fntc.ta.v4.AppraisalRuleType;
import org.fntc.ta.v4.TextType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import uk.gov.nationalarchives.droid.core.interfaces.IdentificationResult;

/**
 * La classe Fntcv4Converter contient les informations et fonctions nécessaires à la conversion
 * d'une archive au format FNTC v4. Cette classe ne peut être instanciée qu'à travers les méthodes
 * statiques convert(...). Note. La classe n'est pas thread-safe et un nouvel objet est
 * systématiquement créé à chaque conversion.
 *
 * @author Emmanuel Deviller
 * @see ArchiveTransfer
 * @see Fntcv4Config
 */
class Fntcv4Converter {

  private static final Logger LOGGER = LoggerFactory.getLogger(Fntcv4Converter.class);

  private static final String EXT_NS = "ext";

  private final List<Callable<Void>> tasks = new ArrayList<>();
  private final AtomicInteger idCounter = new AtomicInteger();

  private final org.fntc.ta.v4.ObjectFactory fntcv4Factory = new org.fntc.ta.v4.ObjectFactory();
  private final org.afnor.medona.v1.ObjectFactory medonaFactory =
      new org.afnor.medona.v1.ObjectFactory();
  private final DocumentBuilder documentBuilder;

  private final ExecutorService executor;
  private final FileSystem zipArchive;
  private final boolean isStrict;

  private Fntcv4Converter(FileSystem zipArchive, Fntcv4Config config) {
    this.zipArchive = zipArchive;
    this.executor = Executors.newFixedThreadPool(SipUtils.getPoolSize(config.thread()));
    this.isStrict = config.strict();

    try {
      this.documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    } catch (ParserConfigurationException ex) {
      LOGGER.warn("Unable to create DOM document builder");
      throw new SipException(ex);
    }
  }

  /**
   * Convertit une archive en archive FNTC v4. Cette fonction permet de s'assurer que la structure
   * de l'archive est valide.
   *
   * @param archiveTransfer l'archive à convertir
   * @return l 'archive au format FNTC v4
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  static ArchiveTransferType convert(ArchiveTransfer archiveTransfer)
      throws ExecutionException, InterruptedException {
    return convert(archiveTransfer, null, Fntcv4Config.DEFAULT);
  }

  /**
   * Convertit une archive en archive FNTC v4. Cette fonction permet de s'assurer que la structure
   * de l'archive est valide.
   *
   * @param archiveTransfer l'archive à convertir
   * @param config la configuration utilisée lors de la conversion
   * @return l 'archive au format FNTC v4
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  static ArchiveTransferType convert(ArchiveTransfer archiveTransfer, Fntcv4Config config)
      throws ExecutionException, InterruptedException {
    return convert(archiveTransfer, null, config);
  }

  /**
   * Convertit une archive en archive FNTC v4. L'archive convertie est créée dans le paquet zip
   * spécifié par le paramètre zipArchive.
   *
   * @param archiveTransfer l'archive à convertir
   * @param zipArchive le paquet zip de destination
   * @return l 'archive au format FNTC v4
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  static ArchiveTransferType convert(ArchiveTransfer archiveTransfer, FileSystem zipArchive)
      throws ExecutionException, InterruptedException {
    return convert(archiveTransfer, zipArchive, Fntcv4Config.DEFAULT);
  }

  /**
   * Convertit une archive en archive FNTC v4. L'archive convertie est créée dans le paquet zip
   * spécifié par le paramètre zipArchive.
   *
   * @param archiveTransfer l'archive à convertir
   * @param zipArchive le paquet zip de destination
   * @param config la configuration utilisée lors de la conversion
   * @return l 'archive au format FNTC v4
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  static ArchiveTransferType convert(
      ArchiveTransfer archiveTransfer, FileSystem zipArchive, Fntcv4Config config)
      throws ExecutionException, InterruptedException {
    Validate.notNull(archiveTransfer, SipUtils.NOT_NULL, "archiveTransfer");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    Fntcv4Converter converter = new Fntcv4Converter(zipArchive, config);
    ArchiveTransferType att = converter.toArchiveTransferType(archiveTransfer);

    // Execute tasks and wait for all to complete
    List<Future<Void>> futures = converter.executor.invokeAll(converter.tasks);

    // Throw an exception if one task has failed (that's a bit... but well it's in the offcial doc)
    for (Future<Void> future : futures) {
      future.get();
    }

    return att;
  }

  private ArchiveTransferType toArchiveTransferType(ArchiveTransfer transfer) {
    ArchiveTransferType att = medonaFactory.createArchiveTransferType();

    String mi =
        SipUtils.getIfBlank(
            transfer.getMessageIdentifier(),
            RandomStringUtils.secure().nextAlphabetic(32).toLowerCase());
    att.setMessageIdentifier(toIdentifierType(mi));

    LocalDateTime gcd = SipUtils.getIfNull(transfer.getDate(), LocalDateTime.now());
    att.setDate(SipUtils.toXmlDateTime(gcd));

    CodeListVersions clv =
        SipUtils.getIfNull(transfer.getCodeListVersions(), new CodeListVersions());
    att.setCodeListVersions(toCodeListVersionsType(clv));

    ifNotNull(transfer.getComment(), e -> att.getComment().add(toMedonaTextType(e)));
    ifNotNull(transfer.getSignature(), e -> att.setSignature(toSignatureType(e)));
    ifNotNull(transfer.getArchivalAgreement(), e -> att.setArchivalAgreement(toIdentifierType(e)));
    ifNotNull(transfer.getArchivalAgency(), e -> att.setArchivalAgency(toOrganizationType(e)));
    ifNotNull(
        transfer.getTransferringAgency(), e -> att.setTransferringAgency(toOrganizationType(e)));

    DataObjectPackageType dopt = medonaFactory.createDataObjectPackageType();

    DescriptiveMetadataType dmt = medonaFactory.createDescriptiveMetadataType();
    dmt.setAny(
        fntcv4Factory.createArchiveUnits(toArchiveUnitsType(transfer.getArchiveUnits(), dopt)));
    dopt.setDescriptiveMetadata(dmt);

    ManagementMetadataType mmt = medonaFactory.createManagementMetadataType();
    ifNotNull(transfer.getArchivalProfile(), e -> mmt.setArchivalProfile(toIdentifierType(e)));
    ifNotNull(transfer.getServiceLevel(), e -> mmt.setServiceLevel(toIdentifierType(e)));
    dopt.setManagementMetadata(mmt);

    att.setDataObjectPackage(dopt);
    return att;
  }

  private ArchiveUnitsType toArchiveUnitsType(
      ArrayList<ArchiveUnit> units, DataObjectPackageType dopt) {
    ArchiveUnitsType auts = fntcv4Factory.createArchiveUnitsType();
    units.forEach(unit -> auts.getArchiveUnit().add(toArchiveUnitType(unit, dopt)));
    return auts;
  }

  private MeasureType toMeasureType(double measure) {
    MeasureType mt = medonaFactory.createMeasureType();
    mt.setValue(BigDecimal.valueOf(measure));
    return mt;
  }

  private ArchiveUnitType toArchiveUnitType(ArchiveUnit unit, DataObjectPackageType dopt) {

    ArchiveUnitType aut = fntcv4Factory.createArchiveUnitType();
    aut.setId(incAndGetCounter());

    // Process Physical
    String physicalId = unit.getPhysicalId();
    if (physicalId != null) {

      PhysicalDataObjectType pdot = medonaFactory.createPhysicalDataObjectType();
      pdot.setId(incAndGetCounter());
      pdot.setSize(toMeasureType(unit.getMeasure()));

      ArchiveUnitType.DataObjectReference dor =
          fntcv4Factory.createArchiveUnitTypeDataObjectReference();
      dor.getDataObjectReferenceId()
          .add(fntcv4Factory.createArchiveUnitTypeDataObjectReferenceDataObjectReferenceId(pdot));
      aut.setDataObjectReference(dor);

      dopt.getBinaryDataObjectOrPhysicalDataObject().add(pdot);
    }

    // Process Binary
    Path binaryPath = unit.getBinaryPath();
    if (binaryPath != null) {

      BinaryObjectType bot = medonaFactory.createBinaryObjectType();
      if (unit.getFileInfo() != null && StringUtils.isNotBlank(unit.getFileInfo().getFilename())) {
        bot.setFilename(unit.getFileInfo().getFilename());
      } else {
        bot.setFilename(binaryPath.getFileName().toString());
      }

      MessageDigestBinaryObjectType mdbot = medonaFactory.createMessageDigestBinaryObjectType();
      mdbot.setAlgorithm(unit.getDigestAlgorithm());

      BinaryDataObjectType bdot = medonaFactory.createBinaryDataObjectType();
      bdot.setId(incAndGetCounter());
      bdot.setAttachment(bot);

      if (StringUtils.isNotBlank(unit.getFormatId())) {
        bdot.setFormat(unit.getFormatId());
      }

      String theSig =
          StringUtils.isBlank(unit.getSignatureStatus()) ? "nil" : unit.getSignatureStatus();
      bdot.setSignatureStatus(theSig);

      bdot.setMessageDigest(mdbot);

      ArchiveUnitType.DataObjectReference dor = aut.getDataObjectReference();
      if (dor == null) {
        dor = fntcv4Factory.createArchiveUnitTypeDataObjectReference();
      }
      dor.getDataObjectReferenceId()
          .add(fntcv4Factory.createArchiveUnitTypeDataObjectReferenceDataObjectReferenceId(bdot));
      aut.setDataObjectReference(dor);

      dopt.getBinaryDataObjectOrPhysicalDataObject().add(bdot);

      tasks.add(new ZipTask(binaryPath, bdot));
    }

    // Process Management
    ManagementType mt = fntcv4Factory.createManagementType();

    if (unit.getAccessRules() != null) {
      mt.setAccessRule(toAccessRuleType(unit.getAccessRules()));
    }

    if (unit.getAppraisalRules() != null) {
      mt.setAppraisalRule(toAppraisalRuleType(unit.getAppraisalRules()));
    }

    if (isStrict) {
      if (unit.getStorageRules() != null) {
        throw new SipException("FNTC V4 does not support StorageRules");
      }
      if (unit.getDisseminationRules() != null) {
        throw new SipException("FNTC V4 does not support DisseminationRules");
      }
      if (unit.getReuseRules() != null) {
        throw new SipException("FNTC V4 does not support ReuseRules");
      }
      if (unit.getClassificationRules() != null) {
        throw new SipException("FNTC V4 does not support ClassificationRules");
      }
    }

    if (!unit.getLogEvents().isEmpty()) {
      LogBookType lbt = fntcv4Factory.createLogBookType();
      unit.getLogEvents().forEach(event -> lbt.getEvent().add(toEventType(event)));
      mt.setLogBook(lbt);
    }

    if (mt.getAccessRule() != null || mt.getAppraisalRule() != null || mt.getLogBook() != null) {
      aut.setManagement(mt);
    }

    // Content
    DescriptiveMetadataContentType dmct = fntcv4Factory.createDescriptiveMetadataContentType();
    ExtendedType emt = fntcv4Factory.createExtendedType();

    // ArchiveUnitProfile
    ifNotNull(
        unit.getArchiveUnitProfile(),
        e -> emt.getAny().add(toNode(new Element("ArchiveUnitProfile", e))));

    // LevelGroup
    ifNotNull(unit.getDescriptionLevel(), e -> dmct.setDescriptionLevel(toTextType(e)));

    // Title Group
    unit.getTitles().forEach(t -> dmct.getTitle().add(toTextType(t)));

    // Content IdentifierGroup
    ifNotNull(unit.getPhysicalId(), e -> dmct.getPhysicalId().add(toTextType(e)));

    unit.getFilePlanPositions().forEach(e -> dmct.getFilePlanPosition().add(toTextType(e)));
    unit.getSystemIds().forEach(e -> dmct.getSystemId().add(toTextType(e)));
    unit.getDataObjectSystemIds().forEach(e -> dmct.getDataObjectSystemId().add(toTextType(e)));
    unit.getOriginatingSystemIds().forEach(e -> dmct.getOriginatingSystemId().add(toTextType(e)));
    unit.getOriginatingAgencyArchiveUnitIdentifiers()
        .forEach(e -> dmct.getOriginatingAgencyArchiveUnitIdentifier().add(toTextType(e)));
    unit.getArchivalAgencyArchiveUnitIdentifiers()
        .forEach(e -> dmct.getArchivalAgencyArchiveUnitIdentifier().add(toTextType(e)));
    unit.getTransferringAgencyArchiveUnitIdentifiers()
        .forEach(e -> dmct.getTransferringAgencyArchiveUnitIdentifier().add(toTextType(e)));

    // Description Group
    unit.getDescriptions().forEach(d -> dmct.getDescription().add(toTextType(d)));

    // CustodialHistory Group
    if (!unit.getCustodialItems().isEmpty()) {
      emt.getAny().add(toNode(toCustodialItemElement(unit)));
    }

    // Type Group
    ifNotNull(unit.getType(), e -> emt.getAny().add(toNode(new Element("Type", e))));
    ifNotNull(unit.getDocumentType(), e -> dmct.setDocumentType(toTextType(e)));

    // Language Group
    unit.getLanguages().forEach(e -> emt.getAny().add(toNode(new Element("Language", e))));
    ifNotNull(
        unit.getDescriptionLanguage(),
        e -> emt.getAny().add(toNode(new Element("DescriptionLanguage", e))));

    // Status Group
    ifNotNull(unit.getStatus(), e -> dmct.setStatus(toTextType(e)));

    // Version Group
    ifNotNull(unit.getVersion(), e -> emt.getAny().add(toNode(new Element("Version", e))));

    // Keyword Group
    unit.getTags().forEach(tag -> dmct.getTag().add(toTagType(tag)));

    // Coverage Group
    // Originating & Submission Agency Group
    ifNotNull(
        unit.getOriginatingAgency(),
        e -> emt.getAny().add(toNode(toOrganizationElement(e, "OriginatingAgency"))));
    ifNotNull(
        unit.getSubmissionAgency(),
        e -> emt.getAny().add(toNode(toOrganizationElement(e, "SubmissionAgency"))));

    // Authorized Agent & writing Group
    unit.getAuthorizedAgents()
        .forEach(agent -> emt.getAny().add(toNode(toAgentElement(agent, "AuthorizedAgent"))));
    unit.getWriters().forEach(writer -> emt.getAny().add(toNode(toAgentElement(writer, "Writer"))));

    // Audience Group
    unit.getAddressees()
        .forEach(addressee -> emt.getAny().add(toNode(toAgentElement(addressee, "Addressee"))));
    unit.getRecipients()
        .forEach(recipient -> emt.getAny().add(toNode(toAgentElement(recipient, "Recipient"))));
    unit.getTransmitters()
        .forEach(
            transmitter -> emt.getAny().add(toNode(toAgentElement(transmitter, "Transmitter"))));
    unit.getSenders().forEach(sender -> emt.getAny().add(toNode(toAgentElement(sender, "Sender"))));

    // Source Group
    ifNotNull(unit.getSource(), e -> emt.getAny().add(toNode(new Element("Source", e))));

    // TODO Relation Group
    // TODO Signature Group
    // GPS Group
    if (unit.getGpsVersionID() != null
        || unit.getGpsDateStamp() != null
        || unit.getGpsAltitude() != null
        || unit.getGpsAltitudeRef() != null
        || unit.getGpsLatitude() != null
        || unit.getGpsLatitudeRef() != null
        || unit.getGpsLongitude() != null
        || unit.getGpsLongitudeRef() != null) {

      final Element gps = new Element("Gps");
      ifNotNull(unit.getGpsVersionID(), s -> gps.addElement("GpsVersionID", s));
      ifNotNull(unit.getGpsAltitude(), s -> gps.addElement("GpsAltitude", s));
      ifNotNull(unit.getGpsAltitudeRef(), s -> gps.addElement("GpsAltitudeRef", s));
      ifNotNull(unit.getGpsDateStamp(), s -> gps.addElement("GpsDateStamp", s));
      ifNotNull(unit.getGpsLatitude(), s -> gps.addElement("GpsLatitude", s));
      ifNotNull(unit.getGpsLatitudeRef(), s -> gps.addElement("GpsLatitudeRef", s));
      ifNotNull(unit.getGpsLongitude(), s -> gps.addElement("GpsLongitude", s));
      ifNotNull(unit.getGpsLongitudeRef(), s -> gps.addElement("GpsLongitudeRef", s));
      emt.getAny().add(toNode(gps));
    }

    // DateGroup
    ifNotNull(unit.getCreatedDate(), d -> dmct.setCreatedDate(SipUtils.toXmlDate(d).toString()));
    ifNotNull(
        unit.getTransactedDate(), d -> dmct.setTransactedDate(SipUtils.toXmlDate(d).toString()));
    ifNotNull(unit.getAcquiredDate(), d -> dmct.setAcquiredDate(SipUtils.toXmlDate(d).toString()));
    ifNotNull(unit.getSentDate(), d -> dmct.setSentDate(SipUtils.toXmlDate(d).toString()));
    ifNotNull(unit.getReceivedDate(), d -> dmct.setReceivedDate(SipUtils.toXmlDate(d).toString()));
    ifNotNull(
        unit.getRegisteredDate(), d -> dmct.setRegisteredDate(SipUtils.toXmlDate(d).toString()));
    ifNotNull(unit.getStartDate(), d -> dmct.setStartDate(SipUtils.toXmlDate(d).toString()));
    ifNotNull(unit.getEndDate(), d -> dmct.setEndDate(SipUtils.toXmlDate(d).toString()));

    // Extended Metadata
    for (Object e : unit.getElements()) {
      if (e instanceof String) {
        emt.getAny().add(toNode((String) e));
      } else if (e instanceof Element) {
        emt.getAny().add(toNode((Element) e));
      }
    }

    if (!emt.getAny().isEmpty()) {
      dmct.setExtended(emt);
    }

    aut.setContent(dmct);

    unit.getArchiveUnits().forEach(u -> aut.getArchiveUnit().add(toArchiveUnitType(u, dopt)));

    return aut;
  }

  private Element toAgentElement(Agent agent, String name) {
    Element ae = new Element(name);
    SipUtils.ifNotBlank(agent.getFirstName(), e -> ae.addElement("FirstName", e));
    SipUtils.ifNotBlank(agent.getBirthName(), e -> ae.addElement("BirthName", e));
    SipUtils.ifNotBlank(agent.getFullName(), e -> ae.addElement("FullName", e));
    SipUtils.ifNotBlank(agent.getGivenName(), e -> ae.addElement("GivenName", e));
    SipUtils.ifNotBlank(agent.getGender(), e -> ae.addElement("Gender", e));
    SipUtils.ifNotBlank(agent.getCorpName(), e -> ae.addElement("CorpName", e));

    ifNotNull(
        agent.getBirthDate(), e -> ae.addElement("BirthDate", SipUtils.toXmlDate(e).toString()));
    ifNotNull(
        agent.getDeathDate(), e -> ae.addElement("DeathDate", SipUtils.toXmlDate(e).toString()));

    ifNotNull(agent.getBirthPlace(), e -> ae.addElement(toPlaceElement(e, "BirthPlace")));
    ifNotNull(agent.getDeathPlace(), e -> ae.addElement(toPlaceElement(e, "DeathPlace")));

    agent.getNationalities().forEach(e -> ae.addElement("Nationality", e));
    agent.getIdentifiers().forEach(e -> ae.addElement("Identifier", e));
    agent.getFunctions().forEach(e -> ae.addElement("Function", e));
    agent.getActivities().forEach(e -> ae.addElement("Activity", e));
    agent.getPositions().forEach(e -> ae.addElement("Position", e));
    agent.getRoles().forEach(e -> ae.addElement("Role", e));
    agent.getMandates().forEach(e -> ae.addElement("Mandate", e));
    return ae;
  }

  private Element toPlaceElement(Place place, String name) {
    Element pe = new Element(name);
    SipUtils.ifNotBlank(place.getAddress(), e -> pe.addElement("Address", e));
    SipUtils.ifNotBlank(place.getCity(), e -> pe.addElement("City", e));
    SipUtils.ifNotBlank(place.getCountry(), e -> pe.addElement("Country", e));
    SipUtils.ifNotBlank(place.getGeogName(), e -> pe.addElement("GeogName", e));
    SipUtils.ifNotBlank(place.getPostalCode(), e -> pe.addElement("PostalCode", e));
    SipUtils.ifNotBlank(place.getRegion(), e -> pe.addElement("Region", e));
    return pe;
  }

  private Node toNode(String fragment) {
    return toNode(fragment, documentBuilder);
  }

  private Node toNode(String fragment, DocumentBuilder docBuilder) {
    try {
      Document doc = docBuilder.newDocument();
      org.w3c.dom.Element node =
          docBuilder.parse(new InputSource(new StringReader(fragment))).getDocumentElement();
      node.setAttribute("xmlns", EXT_NS);
      return doc.importNode(node, true);

    } catch (SAXException | IOException ex) {
      LOGGER.warn("Unable to create Node from document builder");
      throw new SipException(ex);
    }
  }

  private Node toNode(Element element) {
    return toNode(element, documentBuilder.newDocument());
  }

  private Node toNode(Element element, Document doc) {
    org.w3c.dom.Element node = doc.createElementNS(EXT_NS, element.getName());
    node.setTextContent(element.getValue());
    element.getAttributes().forEach(node::setAttribute);
    element.getElements().forEach(e -> node.appendChild(toNode(e, doc)));
    return node;
  }

  private AccessRuleType toAccessRuleType(AccessRules accessRule) {
    AccessRuleType art = fntcv4Factory.createAccessRuleType();
    SipUtils.ifNotBlank(accessRule.getRuleName(), art::setRule);
    ifNotNull(accessRule.getStartDate(), e -> art.setStartDate(SipUtils.toXmlDate(e)));
    return art;
  }

  private AppraisalRuleType toAppraisalRuleType(AppraisalRules appraisalRule) {
    AppraisalRuleType art = fntcv4Factory.createAppraisalRuleType();
    SipUtils.ifNotBlank(appraisalRule.getRuleName(), art::setAppraisalCode);
    SipUtils.ifNotBlank(appraisalRule.getDuration(), e -> art.setDuration(SipUtils.toDuration(e)));
    ifNotNull(appraisalRule.getStartDate(), e -> art.setStartDate(SipUtils.toXmlDate(e)));
    return art;
  }

  private EventType toEventType(Event event) {
    EventType et = fntcv4Factory.createEventType();
    ifNotNull(event.getIdentifier(), e -> et.setEventIdentifier(toTextType(e)));
    ifNotNull(event.getDateTime(), e -> et.setEventDateTime(SipUtils.toXmlDateTime(e)));
    ifNotNull(event.getDetail(), e -> et.setEventDetail(toTextType(e)));
    ifNotNull(event.getDetailData(), e -> et.setEventDetailData(toTextType(e)));
    ifNotNull(event.getOutcome(), e -> et.setOutcome(toTextType(e)));
    ifNotNull(event.getOutcomeDetail(), e -> et.setOutcomeDetail(toTextType(e)));
    ifNotNull(event.getOutcomeDetailMessage(), e -> et.setOutcomeDetailMessage(toTextType(e)));
    ifNotNull(event.getType(), e -> et.setEventType(toTextType(e)));
    ifNotNull(event.getTypeCode(), e -> et.setEventTypeCode(toTextType(e)));
    return et;
  }

  private CodeListVersionsType toCodeListVersionsType(CodeListVersions code) {
    CodeListVersionsType clvt = medonaFactory.createCodeListVersionsType();
    clvt.setId(code.getId());

    if (isStrict) {
      if (code.getSignatureStatusCodeListVersion() != null) {
        throw new SipException("FNTC V4 does not support SignatureStatusCodeListVersion");
      }
      if (code.getMimeTypeCodeListVersion() != null) {
        throw new SipException("FNTC V4 does not support MimeTypeCodeListVersion");
      }
      if (code.getEncodingCodeListVersion() != null) {
        throw new SipException("FNTC V4 does not support EncodingCodeListVersion");
      }
      if (code.getCompressionAlgorithmCodeListVersion() != null) {
        throw new SipException("FNTC V4 does not support CompressionAlgorithmCodeListVersion");
      }
      if (code.getDataObjectVersionCodeListVersion() != null) {
        throw new SipException("FNTC V4 does not support DataObjectVersionCodeListVersion");
      }
      if (code.getStorageRuleCodeListVersion() != null) {
        throw new SipException("FNTC V4 does not support StorageRuleCodeListVersion");
      }
      if (code.getAppraisalRuleCodeListVersion() != null) {
        throw new SipException("FNTC V4 does not support AppraisalRuleCodeListVersion");
      }
      if (code.getAccessRuleCodeListVersion() != null) {
        throw new SipException("FNTC V4 does not support AccessRuleCodeListVersion");
      }
      if (code.getReuseRuleCodeListVersion() != null) {
        throw new SipException("FNTC V4 does not support ReuseRuleCodeListVersion");
      }
      if (code.getClassificationRuleCodeListVersion() != null) {
        throw new SipException("FNTC V4 does not support ClassificationRuleCodeListVersion");
      }
      if (code.getAcquisitionInformationCodeListVersion() != null) {
        throw new SipException("FNTC V4 does not support AcquisitionInformationCodeListVersion");
      }
    }

    SipUtils.ifNotBlank(
        code.getAuthorizationReasonCodeListVersion(),
        e -> clvt.setAuthorizationReasonCodeListVersion(toCodeType(e)));
    SipUtils.ifNotBlank(
        code.getFileEncodingCodeListVersion(),
        e -> clvt.setFileEncodingCodeListVersion(toCodeType(e)));
    SipUtils.ifNotBlank(
        code.getFileFormatCodeListVersion(), e -> clvt.setFileFormatCodeListVersion(toCodeType(e)));
    SipUtils.ifNotBlank(
        code.getMessageDigestAlgorithmCodeListVersion(),
        e -> clvt.setMessageDigestAlgorithmCodeListVersion(toCodeType(e)));
    SipUtils.ifNotBlank(
        code.getRelationshipCodeListVersion(),
        e -> clvt.setRelationshipCodeListVersion(toCodeType(e)));
    SipUtils.ifNotBlank(
        code.getReplyCodeListVersion(), e -> clvt.setReplyCodeListVersion(toCodeType(e)));
    SipUtils.ifNotBlank(
        code.getSignatureStatusCodeListVersion(),
        e -> clvt.setSignatureStatusCodeListVersion(toCodeType(e)));
    return clvt;
  }

  private CodeType toCodeType(String codeType) {
    CodeType ct = medonaFactory.createCodeType();
    ct.setValue(codeType);
    return ct;
  }

  private Element toCustodialItemElement(ArchiveUnit unit) {
    Element cht = new Element("CustodialHistory");
    unit.getCustodialItems()
        .forEach(
            e -> {
              Element chit = new Element("CustodialHistoryItem", e.getValue());
              chit.addAttribute("when", SipUtils.toXmlDateTime(e.getWhen()).toString());
              cht.addElement(chit);
            });
    return cht;
  }

  private Element toOrganizationElement(Agency agency, String name) {
    Element ae = new Element(name);
    SipUtils.ifNotBlank(agency.getIdentifier(), e -> ae.addElement("Identifier", e));

    if (StringUtils.isNotBlank(agency.getName()) || !agency.getElements().isEmpty()) {
      Element odm = new Element("OrganizationDescriptiveMetadata");
      SipUtils.ifNotBlank(agency.getName(), e -> odm.addElement("Name", e));
      agency.getElements().forEach(odm::addElement);
      ae.addElement(odm);
    }
    return ae;
  }

  private OrganizationType toOrganizationType(Agency agency) {
    OrganizationType ot = medonaFactory.createOrganizationType();
    ot.setIdentifier(toIdentifierType(agency.getIdentifier()));
    if (StringUtils.isNotBlank(agency.getName())) {
      OrganizationDescriptiveMetadataType odmt =
          medonaFactory.createOrganizationDescriptiveMetadataType();
      odmt.setAny(fntcv4Factory.createName(toTextType(agency.getName())));
      ot.setOrganizationDescriptiveMetadata(odmt);
    }
    if (isStrict && !agency.getElements().isEmpty()) {
      throw new SipException(
          "FNTC V4 does not support multiple elements in OrganizationDescriptiveMetadata");
    }
    return ot;
  }

  private IdentifierType toIdentifierType(String identifier) {
    IdentifierType it = medonaFactory.createIdentifierType();
    it.setValue(identifier);
    return it;
  }

  private SignatureType toSignatureType(String signature) {
    SignatureType st = medonaFactory.createSignatureType();
    st.setAny(fntcv4Factory.createFormat(toTextType(signature)));
    return st;
  }

  private org.afnor.medona.v1.TextType toMedonaTextType(String text) {
    org.afnor.medona.v1.TextType tt = medonaFactory.createTextType();
    tt.setValue(text);
    return tt;
  }

  private TextType toTextType(String text) {
    TextType tt = fntcv4Factory.createTextType();
    tt.setValue(text);
    return tt;
  }

  private TextType toTextType(Text text) {
    TextType tt = fntcv4Factory.createTextType();
    tt.setValue(text.getMessage());
    tt.setLang(text.getLang());
    return tt;
  }

  private TagType toTagType(Tag tag) {
    TagType tt = fntcv4Factory.createTagType();
    if (StringUtils.isNotBlank(tag.key())) {
      tt.setKey(tag.key());
    }

    tt.setValue(tag.value());
    return tt;
  }

  private String incAndGetCounter() {
    return "ID" + idCounter.incrementAndGet();
  }

  private class ZipTask implements Callable<Void> {

    private final Path binaryPath;
    private final BinaryDataObjectType bdot;

    /**
     * Instantiates a new Zip task.
     *
     * @param binaryPath the binary path
     * @param bdot the bdot
     */
    public ZipTask(Path binaryPath, BinaryDataObjectType bdot) {
      this.binaryPath = binaryPath;
      this.bdot = bdot;
    }

    private Path zip(Path binaryPath, String entryName) throws IOException {

      Path docEntry = zipArchive.getPath("Content");
      if (Files.notExists(docEntry)) {
        try {
          Files.createDirectories(docEntry);
        } catch (FileAlreadyExistsException ex) {
          LOGGER.warn("zip: ", ex);
        }
      }

      Path zipEntry = docEntry.resolve(entryName);
      if (Files.notExists(zipEntry)) {
        try (OutputStream out = Files.newOutputStream(zipEntry)) {
          Files.copy(binaryPath, out);
        } catch (FileAlreadyExistsException ex) {
          LOGGER.warn("zip: ", ex);
        }
      }

      return zipEntry;
    }

    @Override
    public Void call() {
      MessageDigestBinaryObjectType mdbot = bdot.getMessageDigest();

      try {
        // Compute Digest
        byte[] d = SipUtils.digest(binaryPath, mdbot.getAlgorithm());
        mdbot.setValue(d); // The digest is encoded to Base64 by JAXB in the XML

        // Add binary file to zip
        if (zipArchive != null) {
          Path zipEntry =
              zip(
                  binaryPath,
                  Hex.encodeHexString(d)
                      + "_"
                      + Files.getLastModifiedTime(binaryPath).toMillis()
                      + "_"
                      + binaryPath.getFileName());
          long size = (long) Files.getAttribute(zipEntry, "zip:size");
          bdot.setSize(new BigDecimal(size));
          bdot.getAttachment().setUri(zipEntry.toString());
        } else {
          bdot.setSize(new BigDecimal(Files.size(binaryPath)));
          bdot.getAttachment()
              .setUri(
                  "Content/"
                      + Hex.encodeHexString(d)
                      + "_"
                      + Files.getLastModifiedTime(binaryPath).toMillis()
                      + "_"
                      + binaryPath.getFileName());
        }

        // Note. The Signature Identifier does not fully support NIO2 (i.e. does not work with
        // jimfs)
        if (StringUtils.isBlank(bdot.getFormat())) {
          String ext = FilenameUtils.getExtension(binaryPath.getFileName().toString());
          List<IdentificationResult> results = DroidUtils.matchBinarySignatures(binaryPath, ext);
          String format = results.isEmpty() ? "Unknown Format" : results.get(0).getPuid();
          bdot.setFormat(format);
        }
      } catch (Exception ex) {
        executor.shutdownNow();
        throw new SipException("Fail to complete ZipTask for " + binaryPath, ex);
      }

      // Void
      return null;
    }
  }
}
