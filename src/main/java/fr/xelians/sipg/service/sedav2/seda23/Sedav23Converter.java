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
package fr.xelians.sipg.service.sedav2.seda23;

import static fr.xelians.sipg.utils.SipUtils.*;

import fr.gouv.culture.archivesdefrance.seda.v23.*;
import fr.xelians.sipg.model.*;
import fr.xelians.sipg.service.sedav2.SedaConfig;
import fr.xelians.sipg.utils.DroidUtils;
import fr.xelians.sipg.utils.SipException;
import fr.xelians.sipg.utils.SipUtils;
import jakarta.xml.bind.JAXBElement;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.math.BigInteger;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import uk.gov.nationalarchives.droid.core.interfaces.IdentificationResult;

/**
 * La classe Sedav2Converter contient les informations et fonctions nécessaires à la conversion
 * d'une archive au format SEDA v2.2. Cette classe ne peut être instanciée qu'à travers les méthodes
 * statiques convert(...). Note. La classe n'est pas thread safe et un nouvel objet est
 * systématiquement créé à chaque conversion.
 *
 * @author Emmanuel Deviller
 * @see ArchiveTransfer
 * @see SedaConfig
 */
class Sedav23Converter {

  private static final Logger LOGGER = LoggerFactory.getLogger(Sedav23Converter.class);
  private static final String EXT_NS = "fr:gouv:culture:archivesdefrance:seda:v2.3";
  public static final String XMLNS = "xmlns";
  private final List<Callable<Void>> tasks = new ArrayList<>();
  private final AtomicInteger idCounter = new AtomicInteger();
  private final ObjectFactory sedav2Factory = new ObjectFactory();
  private final DocumentBuilder documentBuilder;
  private final FileSystem zipArchive;
  private final boolean isStrict;
  private final boolean identifyFileFormat;

  private final HashMap<ArchiveUnit, ArchiveUnitType> archiveMap = new HashMap<>();
  private final ArrayList<Runnable> postProcessors = new ArrayList<>();

  private Sedav23Converter(FileSystem zipArchive, SedaConfig config) {
    this.zipArchive = zipArchive;
    this.isStrict = config.strict();
    this.identifyFileFormat = config.identifyFileFormat();

    try {
      documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    } catch (ParserConfigurationException ex) {
      throw new SipException("Unable to create DOM document builder", ex);
    }
  }

  /**
   * Convertit une archive en archive SEDA v2.2. Cette fonction permet de s'assurer que la structure
   * de l'archive est valide.
   *
   * @param archiveTransfer l'archive à convertir
   * @return l 'archive au format SEDA v2.2
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  public static ArchiveTransferType convertToArchiveTransferType(ArchiveTransfer archiveTransfer)
      throws ExecutionException, InterruptedException {
    return convertToArchiveTransferType(archiveTransfer, null, SedaConfig.DEFAULT);
  }

  /**
   * Convertit une archive en transfert SEDA v2.2. Cette fonction permet de s'assurer que la
   * structure de l'archive est valide.
   *
   * @param archiveTransfer l'archive à convertir
   * @param config la configuration utilisée lors de la conversion
   * @return l 'archive au format SEDA v2.2
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  static ArchiveTransferType convertToArchiveTransferType(
      ArchiveTransfer archiveTransfer, SedaConfig config)
      throws ExecutionException, InterruptedException {
    return convertToArchiveTransferType(archiveTransfer, null, config);
  }

  /**
   * Convertit une archive en transfert SEDA v2.2. L'archive convertie est créée dans le paquet zip
   * spécifié par le paramètre zipArchive.
   *
   * @param archiveTransfer l'archive à convertir
   * @param zipArchive le paquet zip de destination
   * @return l 'archive au format SEDA v2.2
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  static ArchiveTransferType convertToArchiveTransferType(
      ArchiveTransfer archiveTransfer, FileSystem zipArchive)
      throws ExecutionException, InterruptedException {
    return convertToArchiveTransferType(archiveTransfer, zipArchive, SedaConfig.DEFAULT);
  }

  /**
   * Convertit une archive en transfert SEDA v2.2. L'archive convertie est créée dans le paquet zip
   * spécifié par le paramètre zipArchive.
   *
   * @param archiveTransfer l'archive à convertir
   * @param zipArchive le paquet zip de destination
   * @param config la configuration utilisée lors de la conversion
   * @return l 'archive au format SEDA v2.2
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  static ArchiveTransferType convertToArchiveTransferType(
      ArchiveTransfer archiveTransfer, FileSystem zipArchive, SedaConfig config)
      throws ExecutionException, InterruptedException {
    Validate.notNull(archiveTransfer, SipUtils.NOT_NULL, "archiveTransfer");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    Sedav23Converter converter = new Sedav23Converter(zipArchive, config);
    ArchiveTransferType att = converter.toArchiveTransferType(archiveTransfer);
    executeAndWait(converter, config);
    return att;
  }

  /**
   * Convertit une archive en delivery SEDA v2.2. L'archive convertie est créée dans le paquet zip
   * spécifié par le paramètre zipArchive.
   *
   * @param archiveDeliveryRequestReply l'archive à convertir
   * @param zipArchive le paquet zip de destination
   * @param config la configuration utilisée lors de la conversion
   * @return l 'archive au format SEDA v2.2
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  static ArchiveDeliveryRequestReplyType convertToArchiveDeliveryRequestReplyType(
      ArchiveDeliveryRequestReply archiveDeliveryRequestReply,
      FileSystem zipArchive,
      SedaConfig config)
      throws ExecutionException, InterruptedException {
    Validate.notNull(archiveDeliveryRequestReply, SipUtils.NOT_NULL, "archiveTransfer");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    Sedav23Converter converter = new Sedav23Converter(zipArchive, config);
    ArchiveDeliveryRequestReplyType del =
        converter.toArchiveDeliveryRequestReplyType(archiveDeliveryRequestReply);
    executeAndWait(converter, config);
    return del;
  }

  private static void executeAndWait(Sedav23Converter converter, SedaConfig config)
      throws ExecutionException, InterruptedException {
    if (!converter.tasks.isEmpty()) {
      ExecutorService executor =
          Executors.newFixedThreadPool(SipUtils.getPoolSize(config.thread()));
      try {
        List<Future<Void>> futures = executor.invokeAll(converter.tasks);
        // Join all threads and throw an exception if one task has failed
        for (Future<Void> future : futures) {
          future.get();
        }
      } finally {
        executor.shutdownNow();
      }
    }
  }

  private ArchiveTransferType toArchiveTransferType(ArchiveTransfer transfer) {
    ArchiveTransferType att = sedav2Factory.createArchiveTransferType();

    String mi =
        SipUtils.getIfBlank(
            transfer.getMessageIdentifier(),
            RandomStringUtils.secure().nextAlphabetic(32).toLowerCase());
    att.setMessageIdentifier(mi);

    LocalDateTime gcd = SipUtils.getIfNull(transfer.getDate(), LocalDateTime.now());
    att.setDate(SipUtils.toXmlDateTime(gcd));

    CodeListVersions clv =
        SipUtils.getIfNull(transfer.getCodeListVersions(), new CodeListVersions());
    att.setCodeListVersions(toCodeListVersionsType(clv));

    ifNotNull(transfer.getComment(), e -> att.getComment().add(toTextType(e)));
    ifNotNull(transfer.getArchivalAgreement(), e -> att.setArchivalAgreement(toIdentifierType(e)));
    ifNotNull(
        transfer.getArchivalAgency(), e -> att.setArchivalAgency(toOrganizationWithIdType(e)));
    ifNotNull(
        transfer.getTransferringAgency(),
        e -> att.setTransferringAgency(toOrganizationWithIdType(e)));

    if (StringUtils.isNotBlank(transfer.getSignature())
        || !transfer.getSignatureElements().isEmpty()) {
      att.setSignature(toSignatureMessageType(transfer));
    }

    DataObjectPackageType dopt = sedav2Factory.createDataObjectPackageType();

    DescriptiveMetadataType dmt = sedav2Factory.createDescriptiveMetadataType();
    transfer
        .getArchiveUnits()
        .forEach(unit -> dmt.getArchiveUnit().add(toArchiveUnitType(unit, dopt)));
    dopt.setDescriptiveMetadata(dmt);

    ManagementMetadataType mmt = sedav2Factory.createManagementMetadataType();
    ifNotNull(transfer.getArchivalProfile(), e -> mmt.setArchivalProfile(toIdentifierType(e)));
    ifNotNull(transfer.getServiceLevel(), e -> mmt.setServiceLevel(toIdentifierType(e)));
    ifNotNull(transfer.getAcquisitionInformation(), mmt::setAcquisitionInformation);
    ifNotNull(transfer.getLegalStatus(), e -> mmt.setLegalStatus(toLegalStatus(e)));
    ifNotNull(
        transfer.getOriginatingAgencyIdentifier(),
        e -> mmt.setOriginatingAgencyIdentifier(toIdentifierType(e)));
    ifNotNull(
        transfer.getSubmissionAgencyIdentifier(),
        e -> mmt.setSubmissionAgencyIdentifier(toIdentifierType(e)));
    dopt.setManagementMetadata(mmt);

    att.setDataObjectPackage(dopt);

    // 2nd pass
    postProcessors.forEach(Runnable::run);

    return att;
  }

  private ArchiveDeliveryRequestReplyType toArchiveDeliveryRequestReplyType(
      ArchiveDeliveryRequestReply delivery) {
    ArchiveDeliveryRequestReplyType del = sedav2Factory.createArchiveDeliveryRequestReplyType();

    String mi =
        SipUtils.getIfBlank(
            delivery.getMessageIdentifier(),
            RandomStringUtils.secure().nextAlphabetic(32).toLowerCase());
    del.setMessageIdentifier(mi);

    LocalDateTime gcd = SipUtils.getIfNull(delivery.getDate(), LocalDateTime.now());
    del.setDate(SipUtils.toXmlDateTime(gcd));

    CodeListVersions clv =
        SipUtils.getIfNull(delivery.getCodeListVersions(), new CodeListVersions());
    del.setCodeListVersions(toCodeListVersionsType(clv));

    ifNotNull(delivery.getComment(), e -> del.getComment().add(toTextType(e)));
    ifNotNull(delivery.getArchivalAgreement(), e -> del.setArchivalAgreement(toIdentifierType(e)));
    ifNotNull(
        delivery.getArchivalAgency(), e -> del.setArchivalAgency(toOrganizationWithIdType(e)));

    ifNotNull(delivery.getReplyCode(), del::setReplyCode);
    ifNotNull(
        delivery.getMessageRequestIdentifier(),
        e -> del.setMessageRequestIdentifier(toIdentifierType(e)));
    ifNotNull(delivery.getUnitIdentifier(), e -> del.getUnitIdentifier().add(toIdentifierType(e)));
    ifNotNull(delivery.getRequester(), e -> del.setRequester(toOrganizationWithIdType(e)));

    DataObjectPackageType dopt = sedav2Factory.createDataObjectPackageType();

    DescriptiveMetadataType dmt = sedav2Factory.createDescriptiveMetadataType();
    delivery
        .getArchiveUnits()
        .forEach(unit -> dmt.getArchiveUnit().add(toArchiveUnitType(unit, dopt)));
    dopt.setDescriptiveMetadata(dmt);

    ManagementMetadataType mmt = sedav2Factory.createManagementMetadataType();
    ifNotNull(
        delivery.getOriginatingAgencyIdentifier(),
        e -> mmt.setOriginatingAgencyIdentifier(toIdentifierType(e)));
    dopt.setManagementMetadata(mmt);

    del.setDataObjectPackage(dopt);

    // 2nd pass
    postProcessors.forEach(Runnable::run);

    return del;
  }

  private void addPhysicalDataObjectType(
      DataObjectGroupType dogt, DataObjectRefType dor, ArchiveUnit unit) {
    PhysicalDataObjectType pdot = sedav2Factory.createPhysicalDataObjectType();
    pdot.setId(incAndGetCounter());
    pdot.setPhysicalId(toIdentifierType(unit.getPhysicalId()));
    pdot.setDataObjectVersion(unit.getPhysicalVersion());

    dor.setDataObjectGroupReferenceId(dogt);
    dogt.getBinaryDataObjectOrPhysicalDataObject().add(pdot);
  }

  private void addBinaryDataObjectTypes(
      DataObjectGroupType dogt, DataObjectRefType dor, ArchiveUnit unit) {
    int size = dogt.getBinaryDataObjectOrPhysicalDataObject().size();

    if (unit.getBinaryMasterDataObject() != null) {
      addBinaryDataObjectType(dogt, unit.getBinaryMasterDataObject());
    }
    if (unit.getDisseminationDataObject() != null) {
      addBinaryDataObjectType(dogt, unit.getDisseminationDataObject());
    }
    if (unit.getThumbnailDataObject() != null) {
      addBinaryDataObjectType(dogt, unit.getThumbnailDataObject());
    }
    if (unit.getTextContentDataObject() != null) {
      addBinaryDataObjectType(dogt, unit.getTextContentDataObject());
    }

    if (dogt.getBinaryDataObjectOrPhysicalDataObject().size() != size) {
      dor.setDataObjectGroupReferenceId(dogt);
    }
  }

  private void addBinaryDataObjectType(DataObjectGroupType dogt, BinaryDataObject bdo) {
    boolean removePath = false;
    Path binaryPath = bdo.getBinaryPath();
    if (binaryPath == null) {
      Supplier<Path> bs = bdo.getBinaryPathSupplier();
      if (bs == null) {
        return;
      }
      binaryPath = bdo.getBinaryPathSupplier().get();
      removePath = true;
    }

    MessageDigestBinaryObjectType mdbot = sedav2Factory.createMessageDigestBinaryObjectType();
    mdbot.setAlgorithm(bdo.getDigestAlgorithm());

    BinaryDataObjectType bdot = sedav2Factory.createBinaryDataObjectType();
    bdot.setId(incAndGetCounter());
    bdot.setDataObjectVersion(bdo.getBinaryVersion());
    bdot.setMessageDigest(mdbot);

    FormatIdentification fmtId = bdo.getFormatIdentification();
    if (StringUtils.isNotBlank(fmtId.getFormatId())) {
      bdot.setFormatIdentification(
          toFormatIdentificationType(
              fmtId.getFormatId(), fmtId.getFormatName(), fmtId.getMimeType()));
    }

    FileInfo fileInfo = bdo.getFileInfo();
    if (fileInfo != null) {
      bdot.setFileInfo(toFileInfoType(fileInfo, binaryPath.getFileName().toString()));
    }

    dogt.getBinaryDataObjectOrPhysicalDataObject().add(bdot);
    tasks.add(new ZipTask(binaryPath, removePath, this.identifyFileFormat, bdot));
  }

  private ArchiveUnitType toArchiveUnitType(ArchiveUnit unit, DataObjectPackageType dopt) {

    if (isStrict) {
      if (!unit.getDataObjectSystemIds().isEmpty()) {
        throw new SipException("SEDA 2.3 does not support deprecated DataObjectSystemId.");
      }
      if (!unit.getSignatures().isEmpty()) {
        throw new SipException("SEDA 2.3 does not support deprecated Signature element.");
      }
    }

    ArchiveUnitType aut = sedav2Factory.createArchiveUnitType();
    // if no archive unit id set we use auto inc ids
    aut.setId(StringUtils.isNotEmpty(unit.getId()) ? unit.getId() : incAndGetCounter());
    archiveMap.put(unit, aut);

    DataObjectGroupType dogt = sedav2Factory.createDataObjectGroupType();
    dogt.setId(incAndGetCounter());

    DataObjectRefType dor = sedav2Factory.createDataObjectRefType();

    // Process Physical
    String physicalId = unit.getPhysicalId();
    if (physicalId != null) {
      addPhysicalDataObjectType(dogt, dor, unit);
    }

    // Process Binary
    addBinaryDataObjectTypes(dogt, dor, unit);

    if (dor.getDataObjectGroupReferenceId() != null) {
      aut.getArchiveUnitOrDataObjectReferenceOrDataObjectGroup().add(dor);
    }

    if (!dogt.getBinaryDataObjectOrPhysicalDataObject().isEmpty()) {
      dopt.getDataObjectGroupOrBinaryDataObjectOrPhysicalDataObject().add(dogt);
    }

    // ArchiveUnitProfile
    ifNotNull(
        unit.getArchiveUnitProfile(),
        e -> aut.setArchiveUnitProfile(toIdentifierType(unit.getArchiveUnitProfile())));

    // Management
    setManagement(unit, aut);

    // Content
    DescriptiveMetadataContentType dmct = sedav2Factory.createDescriptiveMetadataContentType();

    // LevelGroup
    ifNotNull(unit.getDescriptionLevel(), e -> dmct.setDescriptionLevel(toLevelType(e)));

    // Title Group
    unit.getTitles().forEach(t -> dmct.getTitle().add(toTextType(t)));

    // Identifier Group
    setIdentifierMetadata(unit, dmct);

    // Description Group
    unit.getDescriptions().forEach(d -> dmct.getDescription().add(toTextType(d)));

    // CustodialHistory Group
    setCustodialMetadata(unit, dmct);

    // Type Group
    ifNotNull(unit.getType(), e -> dmct.setType(toTextType(e)));
    ifNotNull(unit.getDocumentType(), e -> dmct.setDocumentType(toTextType(e)));

    // Language Group
    unit.getLanguages().forEach(e -> dmct.getLanguage().add(e));
    ifNotNull(unit.getDescriptionLanguage(), dmct::setDescriptionLanguage);

    // Status Group
    ifNotNull(unit.getStatus(), dmct::setStatus);

    // Version Group
    ifNotNull(unit.getVersion(), dmct::setVersion);

    // Keyword Group
    unit.getTags()
        .forEach(
            tag -> {
              if (StringUtils.isBlank(tag.key())) {
                dmct.getTag().add(tag.value());
              } else {
                dmct.getKeyword().add(toKeywordType(tag));
              }
            });

    // Coverage Group

    // Originating & Submission Agency Group
    ifNotNull(
        unit.getOriginatingAgency(), e -> dmct.setOriginatingAgency(toOrganizationWithIdType(e)));
    ifNotNull(
        unit.getSubmissionAgency(), e -> dmct.setSubmissionAgency(toOrganizationWithIdType(e)));

    // Agents
    setAgentMetadata(unit, dmct);

    // Source Group
    ifNotNull(unit.getSource(), dmct::setSource);

    // Relation Group
    ifNotNull(
        unit.getRelation(), ror -> dmct.setRelatedObjectReference(toRelatedObjectReference(ror)));

    // Date Group
    setDataMetadata(unit, dmct);

    // Event Group
    // setEventMetadata(unit, dmct);

    // The signature Group was deprecated
    setSigningMetadata(unit, dmct);

    // GPS Group
    setGpsMetadata(unit, dmct);

    // Any Metadata
    setExtendedMetadata(unit, dmct);

    aut.setContent(dmct);
    unit.getArchiveUnits()
        .forEach(
            u ->
                aut.getArchiveUnitOrDataObjectReferenceOrDataObjectGroup()
                    .add(toArchiveUnitType(u, dopt)));

    setArchiveUnitReferences(unit, aut);
    return aut;
  }

  private void setManagement(ArchiveUnit unit, ArchiveUnitType aut) {
    ManagementType mt = sedav2Factory.createManagementType();

    if (unit.getUpdateOperation() != null) {
      mt.setUpdateOperation(toUpdateOperationType(unit.getUpdateOperation()));
    }

    if (unit.getAccessRules() != null) {
      mt.setAccessRule(toAccessRuleType(unit.getAccessRules()));
    }

    if (unit.getDisseminationRules() != null) {
      mt.setDisseminationRule(toDisseminationRuleType(unit.getDisseminationRules()));
    }

    if (unit.getReuseRules() != null) {
      mt.setReuseRule(toReuseRuleType(unit.getReuseRules()));
    }

    if (unit.getAppraisalRules() != null) {
      mt.setAppraisalRule(toAppraisalRuleType(unit.getAppraisalRules()));
    }

    if (unit.getStorageRules() != null) {
      mt.setStorageRule(toStorageRuleType(unit.getStorageRules()));
    }

    if (unit.getClassificationRules() != null) {
      mt.setClassificationRule(toClassificationRuleType(unit.getClassificationRules()));
    }

    if (unit.getHoldRules() != null) {
      mt.setHoldRule(toHoldRuleType(unit.getHoldRules()));
    }

    if (!unit.getLogEvents().isEmpty()) {
      LogBookType lbt = sedav2Factory.createLogBookType();
      unit.getLogEvents().forEach(event -> lbt.getEvent().add(toEventType(event)));
      mt.setLogBook(lbt);
    }

    if (mt.getUpdateOperation() != null
        || mt.getAccessRule() != null
        || mt.getAppraisalRule() != null
        || mt.getDisseminationRule() != null
        || mt.getReuseRule() != null
        || mt.getStorageRule() != null
        || mt.getClassificationRule() != null
        || mt.getLogBook() != null) {
      aut.setManagement(mt);
    }
  }

  private void setAgentMetadata(ArchiveUnit unit, DescriptiveMetadataContentType dmct) {
    // Authorized Agent & Writing  Group
    unit.getAgents().forEach(agent -> dmct.getAgent().add(toAgentType(agent)));
    unit.getAuthorizedAgents().forEach(agent -> dmct.getAuthorizedAgent().add(toAgentType(agent)));
    unit.getWriters().forEach(writer -> dmct.getWriter().add(toAgentType(writer)));

    // Audience Group
    unit.getAddressees().forEach(addressee -> dmct.getAddressee().add(toAgentType(addressee)));
    unit.getRecipients().forEach(recipient -> dmct.getRecipient().add(toAgentType(recipient)));
    unit.getTransmitters()
        .forEach(transmitter -> dmct.getTransmitter().add(toAgentType(transmitter)));
    unit.getSenders().forEach(sender -> dmct.getSender().add(toAgentType(sender)));
  }

  private void setEventMetadata(ArchiveUnit unit, DescriptiveMetadataContentType dmct) {
    List<Event> events = unit.getLogEvents();
    if (!events.isEmpty()) {
      events.forEach(event -> dmct.getEvent().add(toEventType(event)));
    }
  }

  private static void setDataMetadata(ArchiveUnit unit, DescriptiveMetadataContentType dmct) {
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
  }

  private void setIdentifierMetadata(ArchiveUnit unit, DescriptiveMetadataContentType dmct) {
    unit.getFilePlanPositions().forEach(e -> dmct.getFilePlanPosition().add(e));
    unit.getSystemIds().forEach(e -> dmct.getSystemId().add(e));
    unit.getOriginatingSystemIds().forEach(e -> dmct.getOriginatingSystemId().add(e));
    unit.getOriginatingAgencyArchiveUnitIdentifiers()
        .forEach(e -> dmct.getOriginatingAgencyArchiveUnitIdentifier().add(e));
    unit.getArchivalAgencyArchiveUnitIdentifiers()
        .forEach(e -> dmct.getArchivalAgencyArchiveUnitIdentifier().add(e));
    unit.getTransferringAgencyArchiveUnitIdentifiers()
        .forEach(e -> dmct.getTransferringAgencyArchiveUnitIdentifier().add(e));
  }

  private void setCustodialMetadata(ArchiveUnit unit, DescriptiveMetadataContentType dmct) {
    if (!unit.getCustodialItems().isEmpty()) {
      CustodialHistoryType cht = sedav2Factory.createCustodialHistoryType();
      unit.getCustodialItems()
          .forEach(e -> cht.getCustodialHistoryItem().add(toCustodialHistoryItemType(e)));
      dmct.setCustodialHistory(cht);
    }
  }

  private void setSigningMetadata(ArchiveUnit unit, DescriptiveMetadataContentType dmct) {

    SigningInformation signing = unit.getSigningInformation();

    if (signing != null) {

      SigningInformationType sit = sedav2Factory.createSigningInformationType();

      List<SigningRole> roles = signing.getSigningRoles();
      roles.forEach(e -> sit.getSigningRole().add(SigningRoleType.valueOf(e.toString())));

      List<DetachedSigningRole> droles = signing.getDetachedSigningRoles();
      droles.forEach(
          e -> sit.getDetachedSigningRole().add(DetachedSigningRoleType.valueOf(e.toString())));

      signing
          .getSignedDocumentReferenceIds()
          .forEach(e -> sit.getSignedDocumentReferenceId().add(e));

      signing
          .getTimestampingInformations()
          .forEach(
              e -> {
                TimestampingInformationType tit = sedav2Factory.createTimestampingInformationType();
                ifNotNull(e.getTimeStamp(), f -> tit.setTimeStamp(toXmlDateTime(f)));
                ifNotNull(
                    e.getAdditionalTimestampingInformation(),
                    tit::setAdditionalTimestampingInformation);
                sit.getTimestampingInformation().add(tit);
              });

      if (!signing.getAdditionalProofInformation().isEmpty()) {
        AdditionalProofType apt = sedav2Factory.createAdditionalProofType();
        signing
            .getAdditionalProofInformation()
            .forEach(e -> apt.getAdditionalProofInformation().add(e));
        sit.getAdditionalProof().add(apt);
      }

      signing
          .getSignatureDescriptions()
          .forEach(
              e -> {
                SignatureDescriptionType sdt = sedav2Factory.createSignatureDescriptionType();
                ifNotNull(e.getSigningType(), sdt::setSigningType);
                ifNotNull(e.getSigner(), f -> sdt.setSigner(toSignerType(f)));
                ifNotNull(e.getValidator(), f -> sdt.setValidator(toValidatorType(f)));
                sit.getSignatureDescription().add(sdt);
              });

      if (!sit.getAdditionalProof().isEmpty()
          || !sit.getSigningRole().isEmpty()
          || !sit.getSignedDocumentReferenceId().isEmpty()
          || !sit.getDetachedSigningRole().isEmpty()
          || !sit.getSignatureDescription().isEmpty()
          || sit.getTimestampingInformation() != null) {
        dmct.setSigningInformation(sit);
      }
    }
  }

  private SignerType toSignerType(Signer signer) {
    SignerType st = sedav2Factory.createSignerType();
    ifNotBlank(signer.getFirstName(), st::setFirstName);
    ifNotBlank(signer.getBirthName(), st::setBirthName);
    ifNotBlank(signer.getFullName(), st::setFullName);
    ifNotBlank(signer.getGivenName(), st::setGivenName);
    ifNotBlank(signer.getGender(), st::setGender);
    ifNotBlank(signer.getCorpName(), st::setCorpname);
    ifNotNull(signer.getBirthDate(), e -> st.setBirthDate(SipUtils.toXmlDate(e)));
    ifNotNull(signer.getDeathDate(), e -> st.setDeathDate(SipUtils.toXmlDate(e)));
    ifNotNull(signer.getBirthPlace(), e -> st.setBirthPlace(toPlaceType(e)));
    ifNotNull(signer.getDeathPlace(), e -> st.setDeathPlace(toPlaceType(e)));
    ifNotNull(signer.getSigningTime(), e -> st.setSigningTime(SipUtils.toXmlDateTime(e)));

    st.getNationality().addAll(signer.getNationalities());
    st.getIdentifier().addAll(signer.getIdentifiers());
    signer.getFunctions().forEach(e -> st.getFunction().add(toTextType(e)));
    signer.getActivities().forEach(e -> st.getActivity().add(toTextType(e)));
    signer.getPositions().forEach(e -> st.getPosition().add(toTextType(e)));
    signer.getRoles().forEach(e -> st.getRole().add(toTextType(e)));
    signer.getMandates().forEach(e -> st.getMandate().add(toTextType(e)));
    return st;
  }

  private ValidatorType toValidatorType(Validator validator) {
    ValidatorType vt = sedav2Factory.createValidatorType();
    ifNotBlank(validator.getFirstName(), vt::setFirstName);
    ifNotBlank(validator.getBirthName(), vt::setBirthName);
    ifNotBlank(validator.getFullName(), vt::setFullName);
    ifNotBlank(validator.getGivenName(), vt::setGivenName);
    ifNotBlank(validator.getGender(), vt::setGender);
    ifNotBlank(validator.getCorpName(), vt::setCorpname);
    ifNotNull(validator.getBirthDate(), e -> vt.setBirthDate(SipUtils.toXmlDate(e)));
    ifNotNull(validator.getDeathDate(), e -> vt.setDeathDate(SipUtils.toXmlDate(e)));
    ifNotNull(validator.getBirthPlace(), e -> vt.setBirthPlace(toPlaceType(e)));
    ifNotNull(validator.getDeathPlace(), e -> vt.setDeathPlace(toPlaceType(e)));
    ifNotNull(validator.getValidationTime(), e -> vt.setValidationTime(SipUtils.toXmlDateTime(e)));

    vt.getNationality().addAll(validator.getNationalities());
    vt.getIdentifier().addAll(validator.getIdentifiers());
    validator.getFunctions().forEach(e -> vt.getFunction().add(toTextType(e)));
    validator.getActivities().forEach(e -> vt.getActivity().add(toTextType(e)));
    validator.getPositions().forEach(e -> vt.getPosition().add(toTextType(e)));
    validator.getRoles().forEach(e -> vt.getRole().add(toTextType(e)));
    validator.getMandates().forEach(e -> vt.getMandate().add(toTextType(e)));
    return vt;
  }

  private void setGpsMetadata(ArchiveUnit unit, DescriptiveMetadataContentType dmct) {
    if (unit.getGpsVersionID() != null
        || unit.getGpsDateStamp() != null
        || unit.getGpsAltitude() != null
        || unit.getGpsAltitudeRef() != null
        || unit.getGpsLatitude() != null
        || unit.getGpsLatitudeRef() != null
        || unit.getGpsLongitude() != null
        || unit.getGpsLongitudeRef() != null) {

      GpsType gps = sedav2Factory.createGpsType();
      ifNotNull(unit.getGpsVersionID(), gps::setGpsVersionID);
      ifNotNull(unit.getGpsAltitude(), s -> gps.setGpsAltitude(new BigInteger(s)));
      ifNotNull(unit.getGpsAltitudeRef(), gps::setGpsAltitudeRef);
      ifNotNull(unit.getGpsDateStamp(), gps::setGpsDateStamp);
      ifNotNull(unit.getGpsLatitude(), gps::setGpsLatitude);
      ifNotNull(unit.getGpsLatitudeRef(), gps::setGpsLatitudeRef);
      ifNotNull(unit.getGpsLongitude(), gps::setGpsLongitude);
      ifNotNull(unit.getGpsLongitudeRef(), gps::setGpsLongitudeRef);
      dmct.setGps(gps);
    }
  }

  private void setExtendedMetadata(ArchiveUnit unit, DescriptiveMetadataContentType dmct) {
    for (Object e : unit.getElements()) {
      if (e instanceof String str) {
        dmct.getAny().add(toNode(str));
      } else if (e instanceof Element elt) {
        dmct.getAny().add(toNode(elt));
      }
    }
  }

  private void setArchiveUnitReferences(ArchiveUnit unit, ArchiveUnitType aut) {
    for (ArchiveUnitRef ref : unit.getReferences()) {
      postProcessors.add(
          () -> {
            ArchiveUnit au = ref.getReference();
            ArchiveUnitType reference = archiveMap.get(au);
            if (reference == null) {
              throw new SipException(
                  "The related referenced archive unit does not exist in the sip");
            }
            ArchiveUnitType referenceArchiveUnitType = new ArchiveUnitType();

            referenceArchiveUnitType.setId(incAndGetCounter());
            referenceArchiveUnitType.setArchiveUnitRefId(reference);
            aut.getArchiveUnitOrDataObjectReferenceOrDataObjectGroup()
                .add(referenceArchiveUnitType);
          });
    }
  }

  private Node toNode(String fragment) {
    return toNode(fragment, documentBuilder);
  }

  private Node toNode(String fragment, DocumentBuilder docBuilder) {
    try {
      Document doc = docBuilder.newDocument();
      org.w3c.dom.Element element =
          docBuilder.parse(new InputSource(new StringReader(fragment))).getDocumentElement();
      element.setAttribute(XMLNS, EXT_NS);
      return doc.importNode(element, true);
    } catch (SAXException | IOException ex) {
      throw new SipException("Unable to create Node from document builder", ex);
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

  private RelatedObjectReferenceType toRelatedObjectReference(RelatedObjectRef relation) {
    RelatedObjectReferenceType rort = sedav2Factory.createRelatedObjectReferenceType();
    relation
        .getPartOfs()
        .forEach(r -> rort.getIsPartOf().add(toDataObjectOrArchiveUnitReferenceType(r)));
    relation
        .getReferences()
        .forEach(r -> rort.getReferences().add(toDataObjectOrArchiveUnitReferenceType(r)));
    relation
        .getReplaces()
        .forEach(r -> rort.getReplaces().add(toDataObjectOrArchiveUnitReferenceType(r)));
    relation
        .getRequires()
        .forEach(r -> rort.getRequires().add(toDataObjectOrArchiveUnitReferenceType(r)));
    relation
        .getVersionOfs()
        .forEach(r -> rort.getIsVersionOf().add(toDataObjectOrArchiveUnitReferenceType(r)));
    return rort;
  }

  private DataObjectOrArchiveUnitReferenceType toDataObjectOrArchiveUnitReferenceType(
      RelationRef<?> relationRef) {
    DataObjectOrArchiveUnitReferenceType dooaurt =
        sedav2Factory.createDataObjectOrArchiveUnitReferenceType();

    if (relationRef instanceof ArchiveUnitRef) {
      postProcessors.add(
          () -> {
            ArchiveUnit au = (ArchiveUnit) relationRef.getReference();
            ArchiveUnitType aut = archiveMap.get(au);
            if (aut == null) {
              throw new SipException(
                  "The related referenced archive unit does not exist in the sip");
            }
            dooaurt.setArchiveUnitRefId(aut);
          });
    } else if (relationRef instanceof DataObjectRef) {
      postProcessors.add(
          () -> {
            ArchiveUnit au = (ArchiveUnit) relationRef.getReference();
            ArchiveUnitType aut = archiveMap.get(au);
            if (aut == null) {
              throw new SipException(
                  "The related referenced archive unit does not exist in the sip");
            }
            List<Object> objs = aut.getArchiveUnitOrDataObjectReferenceOrDataObjectGroup();
            Optional<DataObjectRefType> opt =
                objs.stream()
                    .filter(DataObjectRefType.class::isInstance)
                    .map(e -> (DataObjectRefType) e)
                    .findFirst();
            dooaurt.setDataObjectReference(
                opt.orElseThrow(
                    () ->
                        new SipException(
                            "The related referenced data object does not exist in this archive")));
          });
    } else if (relationRef instanceof RepositoryArchiveUnitPID) {
      dooaurt.setRepositoryArchiveUnitPID((String) relationRef.getReference());
    } else if (relationRef instanceof RepositoryObjectPID) {
      dooaurt.setRepositoryObjectPID((String) relationRef.getReference());
    } else if (relationRef instanceof ExternalReference) {
      dooaurt.setExternalReference((String) relationRef.getReference());
    }

    return dooaurt;
  }

  private CustodialHistoryItemType toCustodialHistoryItemType(CustodialItem custodialItem) {
    CustodialHistoryItemType chit = sedav2Factory.createCustodialHistoryItemType();
    ifNotNull(custodialItem.getValue(), chit::setValue);
    ifNotNull(custodialItem.getWhen(), e -> chit.setWhen(SipUtils.toXmlDateTime(e).toString()));
    return chit;
  }

  private FormatIdentificationType toFormatIdentificationType(
      String formatId, String formatLitteral, String mimeType) {
    FormatIdentificationType fit = sedav2Factory.createFormatIdentificationType();
    ifNotNull(formatId, fit::setFormatId);
    ifNotNull(formatLitteral, fit::setFormatLitteral);
    ifNotNull(mimeType, fit::setMimeType);
    return fit;
  }

  private FileInfoType toFileInfoType(FileInfo fileInfo, String binaryName) {
    FileInfoType fit = sedav2Factory.createFileInfoType();

    fit.setFilename(
        StringUtils.isBlank(fileInfo.getFilename()) ? binaryName : fileInfo.getFilename());
    ifNotNull(fileInfo.getCreatingApplicationName(), fit::setCreatingApplicationName);
    ifNotNull(fileInfo.getCreatingApplicationVersion(), fit::setCreatingApplicationVersion);
    ifNotNull(fileInfo.getCreatingOs(), fit::setCreatingOs);
    ifNotNull(fileInfo.getCreatingOsVersion(), fit::setCreatingOsVersion);
    ifNotNull(
        fileInfo.getDateCreatedByApplication(),
        e -> fit.setDateCreatedByApplication(SipUtils.toXmlDateTime(e)));
    ifNotNull(fileInfo.getLastModified(), e -> fit.setLastModified(SipUtils.toXmlDateTime(e)));
    return fit;
  }

  private UpdateOperationType toUpdateOperationType(UpdateOperation updateOperation) {
    UpdateOperationType uopt = sedav2Factory.createUpdateOperationType();
    if (StringUtils.isNotBlank(updateOperation.getSystemId())) {
      uopt.setSystemId(updateOperation.getSystemId());
    } else if (StringUtils.isNotBlank(updateOperation.getMetadataName())
        && StringUtils.isNotBlank(updateOperation.getMetadataValue())) {
      ArchiveUnitIdentifierKeyType auikt = sedav2Factory.createArchiveUnitIdentifierKeyType();
      auikt.setMetadataName(updateOperation.getMetadataName());
      auikt.setMetadataValue(updateOperation.getMetadataValue());
      uopt.setArchiveUnitIdentifierKey(auikt);
    } else {
      throw new SipException("UpdateOperation mandates non blank values");
    }
    return uopt;
  }

  private AccessRuleType toAccessRuleType(AccessRules accessRule) {
    AccessRuleType art = sedav2Factory.createAccessRuleType();

    for (Rule rule : accessRule.getRules()) {
      art.getRuleAndStartDate().add(toRuleIdType(rule.getName()));
      if (rule.getStartDate() != null) {
        art.getRuleAndStartDate().add(SipUtils.toXmlDate(rule.getStartDate()));
      }
    }

    // xsd:choice : you cannot set PreventInheritance and PreventRuleNames
    ifNotNull(accessRule.isPreventInheritance(), art::setPreventInheritance);
    accessRule.getPreventRuleNames().forEach(e -> art.getRefNonRuleId().add(toRuleIdType(e)));
    return art;
  }

  private ClassificationRuleType toClassificationRuleType(ClassificationRules classificationRule) {
    ClassificationRuleType art = sedav2Factory.createClassificationRuleType();

    for (Rule rule : classificationRule.getRules()) {
      art.getRuleAndStartDate().add(toRuleIdType(rule.getName()));
      if (rule.getStartDate() != null) {
        art.getRuleAndStartDate().add(SipUtils.toXmlDate(rule.getStartDate()));
      }
    }

    // xsd:choice : you cannot set PreventInheritance and PreventRuleNames
    ifNotNull(classificationRule.isPreventInheritance(), art::setPreventInheritance);
    classificationRule
        .getPreventRuleNames()
        .forEach(e -> art.getRefNonRuleId().add(toRuleIdType(e)));

    ifNotNull(classificationRule.getClassificationLevel(), art::setClassificationLevel);
    ifNotNull(classificationRule.getClassificationOwner(), art::setClassificationOwner);
    ifNotNull(classificationRule.getClassificationAudience(), art::setClassificationAudience);
    ifNotNull(
        classificationRule.getClassificationReassessingDate(),
        s -> art.setClassificationReassessingDate(SipUtils.toXmlDate(s)));
    return art;
  }

  // Note the HoldRuleGroup was "unwrapped" in the HoldRuleType in the xsd
  private HoldRuleType toHoldRuleType(HoldRules holdRule) {
    HoldRuleType art = sedav2Factory.createHoldRuleType();

    for (HoldRule rule : holdRule.getRules()) {
      List<JAXBElement<?>> elts = art.getRuleAndStartDateAndHoldEndDate();

      QName qrule = new QName(EXT_NS, "Rule");
      String name = rule.getName();
      elts.add(new JAXBElement<>(qrule, String.class, name));
      if (rule.getStartDate() != null) {
        QName qstartdate = new QName(EXT_NS, "StartDate");
        XMLGregorianCalendar startDate = SipUtils.toXmlDate(rule.getStartDate());
        elts.add(new JAXBElement<>(qstartdate, XMLGregorianCalendar.class, startDate));
      }
      if (rule.getHoldEndDate() != null) {
        QName qholdenddate = new QName(EXT_NS, "HoldEndDate");
        XMLGregorianCalendar startDate = SipUtils.toXmlDate(rule.getHoldEndDate());
        elts.add(new JAXBElement<>(qholdenddate, XMLGregorianCalendar.class, startDate));
      }
      if (rule.getHoldOwner() != null) {
        QName qowner = new QName(EXT_NS, "HoldOwner");
        String owner = rule.getHoldOwner();
        elts.add(new JAXBElement<>(qowner, String.class, owner));
      }
      if (rule.getHoldReassessingDate() != null) {
        QName qreassessingDate = new QName(EXT_NS, "HoldReassessingDate");
        XMLGregorianCalendar reassessingDate = SipUtils.toXmlDate(rule.getHoldReassessingDate());
        elts.add(new JAXBElement<>(qreassessingDate, XMLGregorianCalendar.class, reassessingDate));
      }
      if (rule.getHoldReason() != null) {
        QName qreason = new QName(EXT_NS, "HoldReason");
        String reason = rule.getHoldOwner();
        elts.add(new JAXBElement<>(qreason, String.class, reason));
      }
      if (rule.getPreventRearrangement() != null) {
        QName qp = new QName(EXT_NS, "PreventRearrangement");
        Boolean p = rule.getPreventRearrangement();
        elts.add(new JAXBElement<>(qp, Boolean.class, p));
      }
    }

    // xsd:choice : you cannot set PreventInheritance and PreventRuleNames
    ifNotNull(holdRule.isPreventInheritance(), art::setPreventInheritance);
    holdRule.getPreventRuleNames().forEach(e -> art.getRefNonRuleId().add(toRuleIdType(e)));

    return art;
  }

  private DisseminationRuleType toDisseminationRuleType(DisseminationRules disseminationRule) {
    DisseminationRuleType art = sedav2Factory.createDisseminationRuleType();

    for (Rule rule : disseminationRule.getRules()) {
      art.getRuleAndStartDate().add(toRuleIdType(rule.getName()));
      if (rule.getStartDate() != null) {
        art.getRuleAndStartDate().add(SipUtils.toXmlDate(rule.getStartDate()));
      }
    }

    // xsd:choice : you cannot set PreventInheritance and PreventRuleNames
    ifNotNull(disseminationRule.isPreventInheritance(), art::setPreventInheritance);
    disseminationRule
        .getPreventRuleNames()
        .forEach(e -> art.getRefNonRuleId().add(toRuleIdType(e)));
    return art;
  }

  private ReuseRuleType toReuseRuleType(ReuseRules reuseRule) {
    ReuseRuleType art = sedav2Factory.createReuseRuleType();

    for (Rule rule : reuseRule.getRules()) {
      art.getRuleAndStartDate().add(toRuleIdType(rule.getName()));
      if (rule.getStartDate() != null) {
        art.getRuleAndStartDate().add(SipUtils.toXmlDate(rule.getStartDate()));
      }
    }

    // xsd:choice : you cannot set PreventInheritance and PreventRuleNames
    ifNotNull(reuseRule.isPreventInheritance(), art::setPreventInheritance);
    reuseRule.getPreventRuleNames().forEach(e -> art.getRefNonRuleId().add(toRuleIdType(e)));
    return art;
  }

  private AppraisalRuleType toAppraisalRuleType(AppraisalRules appraisalRule) {
    AppraisalRuleType art = sedav2Factory.createAppraisalRuleType();
    for (Rule rule : appraisalRule.getRules()) {
      art.getRuleAndStartDate().add(toRuleIdType(rule.getName()));
      if (rule.getStartDate() != null) {
        art.getRuleAndStartDate().add(SipUtils.toXmlDate(rule.getStartDate()));
      }
    }

    // xsd:choice : you cannot set PreventInheritance and PreventRuleNames
    ifNotNull(appraisalRule.isPreventInheritance(), art::setPreventInheritance);
    appraisalRule.getPreventRuleNames().forEach(e -> art.getRefNonRuleId().add(toRuleIdType(e)));

    String action = appraisalRule.getFinalAction();
    if ("Keep".equals(action)) {
      art.setFinalAction(FinalActionAppraisalCodeType.KEEP);
    } else if ("Destroy".equals(action)) {
      art.setFinalAction(FinalActionAppraisalCodeType.DESTROY);
    }

    if (isStrict) {
      if (appraisalRule.getDuration() != null) {
        throw new SipException("SEDA 2.3 does not support Duration");
      }
    }
    return art;
  }

  private StorageRuleType toStorageRuleType(StorageRules storageRule) {
    StorageRuleType art = sedav2Factory.createStorageRuleType();
    for (Rule rule : storageRule.getRules()) {
      art.getRuleAndStartDate().add(toRuleIdType(rule.getName()));
      if (rule.getStartDate() != null) {
        art.getRuleAndStartDate().add(SipUtils.toXmlDate(rule.getStartDate()));
      }
    }

    // xsd:choice : you cannot set PreventInheritance and PreventRuleNames
    ifNotNull(storageRule.isPreventInheritance(), art::setPreventInheritance);
    storageRule.getPreventRuleNames().forEach(e -> art.getRefNonRuleId().add(toRuleIdType(e)));

    String action = storageRule.getFinalAction();
    if (action != null) {
      switch (action) {
        case "Copy" -> art.setFinalAction(FinalActionStorageCodeType.COPY);
        case "RestrictAccess" -> art.setFinalAction(FinalActionStorageCodeType.RESTRICT_ACCESS);
        case "Transfer" -> art.setFinalAction(FinalActionStorageCodeType.TRANSFER);
        default -> throw new SipException(String.format("Unknown final action %s", action));
      }
    }

    return art;
  }

  private RuleIdType toRuleIdType(String value) {
    RuleIdType rit = sedav2Factory.createRuleIdType();
    rit.setValue(value);
    return rit;
  }

  private EventType toEventType(Event event) {
    EventType et = sedav2Factory.createEventType();
    ifNotNull(event.getIdentifier(), et::setEventIdentifier);
    ifNotNull(event.getDateTime(), e -> et.setEventDateTime(SipUtils.toXmlDateTime(e).toString()));
    ifNotNull(event.getDetail(), e -> et.setEventDetail(toTextType(e)));
    ifNotNull(event.getDetailData(), et::setEventDetailData);
    ifNotNull(event.getOutcome(), et::setOutcome);
    ifNotNull(event.getOutcomeDetail(), et::setOutcomeDetail);
    ifNotNull(event.getOutcomeDetailMessage(), et::setOutcomeDetailMessage);
    ifNotNull(event.getType(), et::setEventType);
    ifNotNull(event.getTypeCode(), et::setEventTypeCode);
    return et;
  }

  private AgentType toAgentType(Agent agent) {
    AgentType at = sedav2Factory.createAgentType();
    ifNotBlank(agent.getFirstName(), at::setFirstName);
    ifNotBlank(agent.getBirthName(), at::setBirthName);
    ifNotBlank(agent.getFullName(), at::setFullName);
    ifNotBlank(agent.getGivenName(), at::setGivenName);
    ifNotBlank(agent.getGender(), at::setGender);
    ifNotBlank(agent.getCorpName(), at::setCorpname);
    ifNotNull(agent.getBirthDate(), e -> at.setBirthDate(SipUtils.toXmlDate(e)));
    ifNotNull(agent.getDeathDate(), e -> at.setDeathDate(SipUtils.toXmlDate(e)));
    ifNotNull(agent.getBirthPlace(), e -> at.setBirthPlace(toPlaceType(e)));
    ifNotNull(agent.getDeathPlace(), e -> at.setDeathPlace(toPlaceType(e)));
    at.getNationality().addAll(agent.getNationalities());
    at.getIdentifier().addAll(agent.getIdentifiers());
    agent.getFunctions().forEach(e -> at.getFunction().add(toTextType(e)));
    agent.getActivities().forEach(e -> at.getActivity().add(toTextType(e)));
    agent.getPositions().forEach(e -> at.getPosition().add(toTextType(e)));
    agent.getRoles().forEach(e -> at.getRole().add(toTextType(e)));
    agent.getMandates().forEach(e -> at.getMandate().add(toTextType(e)));
    return at;
  }

  private BirthOrDeathPlaceType toPlaceType(Place place) {
    BirthOrDeathPlaceType bodpt = sedav2Factory.createBirthOrDeathPlaceType();
    ifNotBlank(place.getAddress(), bodpt::setAddress);
    ifNotBlank(place.getCity(), bodpt::setCity);
    ifNotBlank(place.getCountry(), bodpt::setCountry);
    ifNotBlank(place.getGeogName(), bodpt::setGeogname);
    ifNotBlank(place.getPostalCode(), bodpt::setPostalCode);
    ifNotBlank(place.getRegion(), bodpt::setRegion);
    return bodpt;
  }

  private CodeListVersionsType toCodeListVersionsType(CodeListVersions code) {
    CodeListVersionsType clvt = sedav2Factory.createCodeListVersionsType();
    clvt.setId(code.getId());

    if (isStrict) {
      if (code.getSignatureStatusCodeListVersion() != null) {
        throw new SipException("SEDA 2.3 does not support SignatureStatusCodeListVersion");
      }
      if (code.getFileEncodingCodeListVersion() != null) {
        throw new SipException("SEDA 2.3 does not support FileEncodingCodeListVersion");
      }
    }

    ifNotBlank(
        code.getAuthorizationReasonCodeListVersion(),
        e -> clvt.setAuthorizationReasonCodeListVersion(toCodeType(e)));
    ifNotBlank(
        code.getFileFormatCodeListVersion(), e -> clvt.setFileFormatCodeListVersion(toCodeType(e)));
    ifNotBlank(
        code.getMessageDigestAlgorithmCodeListVersion(),
        e -> clvt.setMessageDigestAlgorithmCodeListVersion(toCodeType(e)));
    ifNotBlank(
        code.getRelationshipCodeListVersion(),
        e -> clvt.setRelationshipCodeListVersion(toCodeType(e)));
    ifNotBlank(code.getReplyCodeListVersion(), e -> clvt.setReplyCodeListVersion(toCodeType(e)));
    ifNotBlank(
        code.getMimeTypeCodeListVersion(), e -> clvt.setMimeTypeCodeListVersion(toCodeType(e)));
    ifNotBlank(
        code.getEncodingCodeListVersion(), e -> clvt.setEncodingCodeListVersion(toCodeType(e)));
    ifNotBlank(
        code.getCompressionAlgorithmCodeListVersion(),
        e -> clvt.setCompressionAlgorithmCodeListVersion(toCodeType(e)));
    ifNotBlank(
        code.getDataObjectVersionCodeListVersion(),
        e -> clvt.setDataObjectVersionCodeListVersion(toCodeType(e)));
    ifNotBlank(
        code.getStorageRuleCodeListVersion(),
        e -> clvt.setStorageRuleCodeListVersion(toCodeType(e)));
    ifNotBlank(
        code.getAppraisalRuleCodeListVersion(),
        e -> clvt.setAppraisalRuleCodeListVersion(toCodeType(e)));
    ifNotBlank(
        code.getAccessRuleCodeListVersion(), e -> clvt.setAccessRuleCodeListVersion(toCodeType(e)));
    ifNotBlank(
        code.getDisseminationRuleCodeListVersion(),
        e -> clvt.setDisseminationRuleCodeListVersion(toCodeType(e)));
    ifNotBlank(
        code.getReuseRuleCodeListVersion(), e -> clvt.setReuseRuleCodeListVersion(toCodeType(e)));
    ifNotBlank(
        code.getClassificationRuleCodeListVersion(),
        e -> clvt.setClassificationRuleCodeListVersion(toCodeType(e)));
    ifNotBlank(
        code.getAcquisitionInformationCodeListVersion(),
        e -> clvt.setAcquisitionInformationCodeListVersion(toCodeType(e)));

    return clvt;
  }

  private LevelType toLevelType(String levelType) {
    try {
      return LevelType.fromValue(levelType);
    } catch (IllegalArgumentException iae) {
      throw new SipException("SEDA 2.3 does not support the level type: " + levelType, iae);
    }
  }

  private LegalStatusType toLegalStatus(String legalStatusType) {
    try {
      return LegalStatusType.fromValue(legalStatusType);
    } catch (IllegalArgumentException iae) {
      throw new SipException(
          "SEDA 2.3 does not support the legal status : " + legalStatusType, iae);
    }
  }

  private CodeType toCodeType(String codeType) {
    CodeType ct = sedav2Factory.createCodeType();
    ct.setValue(codeType);
    return ct;
  }

  private OrganizationWithIdType toOrganizationWithIdType(Agency agency) {
    OrganizationWithIdType ot = sedav2Factory.createOrganizationWithIdType();
    ot.setIdentifier(toIdentifierType(agency.getIdentifier()));
    if (StringUtils.isNotBlank(agency.getName())) {
      OrganizationDescriptiveMetadataType odmt =
          sedav2Factory.createOrganizationDescriptiveMetadataType();

      ifNotNull(agency.getName(), e -> odmt.getAny().add(toNode(new Element("Name", e))));
      agency.getElements().forEach(e -> odmt.getAny().add(toNode(e)));
      ot.setOrganizationDescriptiveMetadata(odmt);
    }
    return ot;
  }

  private IdentifierType toIdentifierType(String identifier) {
    IdentifierType it = sedav2Factory.createIdentifierType();
    it.setValue(identifier);
    return it;
  }

  private SignatureMessageType toSignatureMessageType(ArchiveTransfer transfer) {
    SignatureMessageType st = sedav2Factory.createSignatureMessageType();
    ifNotNull(transfer.getSignature(), e -> st.getAny().add(toNode(new Element("Format", e))));
    transfer.getSignatureElements().forEach(e -> st.getAny().add(toNode(e)));
    return st;
  }

  private TextType toTextType(String text) {
    TextType tt = sedav2Factory.createTextType();
    tt.setValue(text);
    return tt;
  }

  private TextType toTextType(Text text) {
    TextType tt = sedav2Factory.createTextType();
    tt.setValue(text.getMessage());
    tt.setLang(text.getLang());
    return tt;
  }

  private KeywordsType toKeywordType(Tag tag) {
    KeywordsType kt = sedav2Factory.createKeywordsType();
    kt.setKeywordReference(toIdentifierType(tag.key()));
    kt.setKeywordContent(toTextType(tag.value()));
    return kt;
  }

  private String incAndGetCounter() {
    return "ID" + idCounter.incrementAndGet();
  }

  private class ZipTask implements Callable<Void> {

    private final Path binaryPath;
    private final boolean removePath;
    private final BinaryDataObjectType bdot;
    private final boolean identifyFileFormat;

    /**
     * Instantiates a new Zip task.
     *
     * @param binaryPath the binary path
     * @param bdot the bdot
     */
    public ZipTask(
        Path binaryPath,
        boolean removePath,
        boolean identifyFileFormat,
        BinaryDataObjectType bdot) {
      this.binaryPath = binaryPath;
      this.removePath = removePath;
      this.identifyFileFormat = identifyFileFormat;
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
        String digest = SipUtils.digestHex(binaryPath, mdbot.getAlgorithm());
        mdbot.setValue(digest);

        // Add binary file to zip
        if (zipArchive != null) {
          Path zipEntry =
              zip(
                  binaryPath,
                  digest
                      + "_"
                      + Files.getLastModifiedTime(binaryPath).toMillis()
                      + "_"
                      + binaryPath.getFileName());
          long size = (long) Files.getAttribute(zipEntry, "zip:size");
          bdot.setSize(BigInteger.valueOf(size));
          bdot.setUri(zipEntry.toString());
        } else {
          bdot.setSize(BigInteger.valueOf(Files.size(binaryPath)));
          bdot.setUri(
              "Content/"
                  + digest
                  + "_"
                  + Files.getLastModifiedTime(binaryPath).toMillis()
                  + "_"
                  + binaryPath.getFileName());
        }

        FormatIdentificationType fit = bdot.getFormatIdentification();
        if (fit == null || StringUtils.isBlank(fit.getFormatId())) {
          processFileFormatIdentification();
        }

        if (removePath) Files.delete(binaryPath);
      } catch (Exception ex) {
        try {
          if (removePath) Files.deleteIfExists(binaryPath);
        } catch (IOException ioex) {
          // Ignore
        }
        throw new SipException("Fail to complete ZipTask for " + binaryPath, ex);
      }

      // Void
      return null;
    }

    private void processFileFormatIdentification() {
      if (identifyFileFormat) {
        // Note. The Signature Identifier does not fully support NIO2 (i.e. does not work with
        // jimfs)
        String ext = FilenameUtils.getExtension(binaryPath.getFileName().toString());
        List<IdentificationResult> results = DroidUtils.matchBinarySignatures(binaryPath, ext);
        if (results.isEmpty()) {
          bdot.setFormatIdentification(toFormatIdentificationType("Unknown", null, null));
        } else {
          IdentificationResult r = results.get(0);
          String name =
              StringUtils.isAllBlank(r.getName(), r.getVersion())
                  ? null
                  : StringUtils.trim(r.getName() + " " + r.getVersion());
          String mimeType = StringUtils.isBlank(r.getMimeType()) ? null : r.getMimeType();
          bdot.setFormatIdentification(toFormatIdentificationType(r.getPuid(), name, mimeType));
        }

      } else {
        bdot.setFormatIdentification(new FormatIdentificationType());
      }
    }
  }
}
