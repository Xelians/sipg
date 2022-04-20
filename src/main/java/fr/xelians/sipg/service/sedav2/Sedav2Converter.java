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

import fr.gouv.culture.archivesdefrance.seda.v2.*;
import fr.xelians.sipg.model.*;
import fr.xelians.sipg.utils.SipUtils;
import fr.xelians.sipg.utils.SipgException;
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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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

/**
 * La classe Sedav2Converter contient les informations et fonctions nécessaires à la conversion d'une archive au format
 * SEDA v2.1. Cette classe ne peut être être instanciée qu'à travers les méthodes statiques convert(...). Note. la
 * classe n'est pas thread-safe et un nouvel objet est systématiquement créé à chaque conversion.
 *
 * @author Emmanuel Deviller
 * @see ArchiveTransfer
 * @see Sedav2Config
 */
class Sedav2Converter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sedav2Converter.class);

    private final List<Callable<Void>> tasks = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger();

    private static final String EXT_NS = "fr:gouv:culture:archivesdefrance:seda:v2.1";
    private final ObjectFactory sedav2Factory = new ObjectFactory();
    private final DocumentBuilder documentBuilder;
    private final FileSystem zipArchive;
    private final boolean isStrict;

    private final HashMap<ArchiveUnit, ArchiveUnitType> archiveMap = new HashMap<>();
    private final ArrayList<Runnable> postProcessors = new ArrayList<>();

    private Sedav2Converter(FileSystem zipArchive, Sedav2Config config) {
        this.zipArchive = zipArchive;
        this.isStrict = config.isStrict();

        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            throw new SipgException("Unable to create DOM document builder", ex);
        }
    }

    /**
     * Convertit une archive en archive SEDA v2.1. Cette fonction permet de s'assurer que la structure de l'archive est
     * valide.
     *
     * @param archiveTransfer l'archive à convertir
     * @return l 'archive au format SEDA v2.1
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    static ArchiveTransferType convert(ArchiveTransfer archiveTransfer) throws ExecutionException, InterruptedException {
        return convert(archiveTransfer, null, Sedav2Config.DEFAULT);
    }

    /**
     * Convertit une archive en archive SEDA v2.1. Cette fonction permet de s'assurer que la structure de l'archive est
     * valide.
     *
     * @param archiveTransfer l'archive à convertir
     * @param config          la configuration utilisée lors de la conversion
     * @return l 'archive au format SEDA v2.1
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    static ArchiveTransferType convert(ArchiveTransfer archiveTransfer, Sedav2Config config) throws ExecutionException, InterruptedException {
        return convert(archiveTransfer, null, config);
    }

    /**
     * Convertit une archive en archive SEDA v2.1. L'archive convertie est créée dans le paquet zip spécifié par la
     * paramètre zipArchive.
     *
     * @param archiveTransfer l'archive à convertir
     * @param zipArchive      le paquet zip de destination
     * @return l 'archive au format SEDA v2.1
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    static ArchiveTransferType convert(ArchiveTransfer archiveTransfer, FileSystem zipArchive) throws ExecutionException, InterruptedException {
        return convert(archiveTransfer, zipArchive, Sedav2Config.DEFAULT);
    }

    /**
     * Convertit une archive en archive SEDA v2.1. L'archive convertie est créée dans le paquet zip spécifié par la
     * paramètre zipArchive.
     *
     * @param archiveTransfer l'archive à convertir
     * @param zipArchive      le paquet zip de destination
     * @param config          la configuration utilisée lors de la conversion
     * @return l 'archive au format SEDA v2.1
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    static ArchiveTransferType convert(ArchiveTransfer archiveTransfer, FileSystem zipArchive, Sedav2Config config) throws ExecutionException, InterruptedException {
        Validate.notNull(archiveTransfer, SipUtils.NOT_NULL, "archiveTransfer");
        Validate.notNull(config, SipUtils.NOT_NULL, "config");

        Sedav2Converter converter = new Sedav2Converter(zipArchive, config);
        ArchiveTransferType att = converter.toArchiveTransferType(archiveTransfer);
        executeAndWait(converter, config) ;
        return att;
    }

    private static void executeAndWait(Sedav2Converter converter, Sedav2Config config) throws ExecutionException, InterruptedException {
        if ( !converter.tasks.isEmpty() ) {
            ExecutorService executor = Executors.newFixedThreadPool(SipUtils.getPoolSize(config.getThread()));
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

        String mi = SipUtils.getIfBlank(transfer.getMessageIdentifier(), RandomStringUtils.randomAlphabetic(32).toLowerCase());
        att.setMessageIdentifier(toIdentifierType(mi));

        LocalDateTime gcd = SipUtils.getIfNull(transfer.getDate(), LocalDateTime.now());
        att.setDate(SipUtils.toXmlDateTime(gcd));

        CodeListVersions clv = SipUtils.getIfNull(transfer.getCodeListVersions(), new CodeListVersions());
        att.setCodeListVersions(toCodeListVersionsType(clv));

        SipUtils.acceptIfNotNull(transfer.getComment(), e -> att.getComment().add(toTextType(e)));
        SipUtils.acceptIfNotNull(transfer.getArchivalAgreement(), e -> att.setArchivalAgreement(toIdentifierType(e)));
        SipUtils.acceptIfNotNull(transfer.getArchivalAgency(), e -> att.setArchivalAgency(toOrganizationWithIdType(e)));
        SipUtils.acceptIfNotNull(transfer.getTransferringAgency(), e -> att.setTransferringAgency(toOrganizationWithIdType(e)));

        if (StringUtils.isNotBlank(transfer.getSignature()) || !transfer.getSignatureElements().isEmpty()) {
            att.setSignature(toSignatureMessageType(transfer));
        }

        DataObjectPackageType dopt = sedav2Factory.createDataObjectPackageType();

        DescriptiveMetadataType dmt = sedav2Factory.createDescriptiveMetadataType();
        transfer.getArchiveUnits().forEach(unit -> dmt.getArchiveUnit().add(toArchiveUnitType(unit, dopt)));
        dopt.setDescriptiveMetadata(dmt);

        ManagementMetadataType mmt = sedav2Factory.createManagementMetadataType();
        SipUtils.acceptIfNotNull(transfer.getArchivalProfile(), e -> mmt.setArchivalProfile(toIdentifierType(e)));
        SipUtils.acceptIfNotNull(transfer.getServiceLevel(), e -> mmt.setServiceLevel(toIdentifierType(e)));
        SipUtils.acceptIfNotNull(transfer.getAcquisitionInformation(), mmt::setAcquisitionInformation);
        SipUtils.acceptIfNotNull(transfer.getLegalStatus(), e -> mmt.setLegalStatus(toLegalStatus(e)));
        SipUtils.acceptIfNotNull(transfer.getOriginatingAgencyIdentifier(), e -> mmt.setOriginatingAgencyIdentifier(toIdentifierType(e)));
        SipUtils.acceptIfNotNull(transfer.getSubmissionAgencyIdentifier(), e -> mmt.setSubmissionAgencyIdentifier(toIdentifierType(e)));
        dopt.setManagementMetadata(mmt);

        att.setDataObjectPackage(dopt);

        // 2nd pass
        postProcessors.forEach(Runnable::run);

        return att;
    }

    private void addPhysicalDataObjectType(DataObjectGroupType dogt, DataObjectRefType dor, ArchiveUnit unit) {
        PhysicalDataObjectType pdot = sedav2Factory.createPhysicalDataObjectType();
        pdot.setId(incAndGetCounter());
        pdot.setPhysicalId(toIdentifierType(unit.getPhysicalId()));
        pdot.setDataObjectVersion(unit.getPhysicalVersion());

        dor.setDataObjectGroupReferenceId(dogt);
        dogt.getBinaryDataObjectOrPhysicalDataObject().add(pdot);
    }

    private void addBinaryDataObjectType(DataObjectGroupType dogt, DataObjectRefType dor, ArchiveUnit unit) {
        Path binaryPath = unit.getBinaryPath();

        MessageDigestBinaryObjectType mdbot = sedav2Factory.createMessageDigestBinaryObjectType();
        mdbot.setAlgorithm(unit.getDigestAlgorithm());

        BinaryDataObjectType bdot = sedav2Factory.createBinaryDataObjectType();
        bdot.setId(incAndGetCounter());
        bdot.setDataObjectVersion(unit.getBinaryVersion());
        bdot.setMessageDigest(mdbot);

        if (StringUtils.isNotBlank(unit.getFormatId())) {
            bdot.setFormatIdentification(toFormatIdentificationType(unit.getFormatId(), unit.getFormatName(), unit.getMimeType()));
        }

        if (unit.getFileInfo() != null) {
            bdot.setFileInfo(toFileInfoType(unit.getFileInfo(), binaryPath.getFileName().toString()));
        }

        dor.setDataObjectGroupReferenceId(dogt);
        dogt.getBinaryDataObjectOrPhysicalDataObject().add(bdot);

        tasks.add(new ZipTask(binaryPath, bdot));
    }

    private ArchiveUnitType toArchiveUnitType(ArchiveUnit unit, DataObjectPackageType dopt) {

        ArchiveUnitType aut = sedav2Factory.createArchiveUnitType();
        // if no archive unit id set we use auto inc ids
        String id = StringUtils.isNotEmpty(unit.getId()) ? unit.getId() : incAndGetCounter();
        aut.setId(id);
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
        Path binaryPath = unit.getBinaryPath();
        if (binaryPath != null) {
            addBinaryDataObjectType(dogt, dor, unit);
        }

        if (dor.getDataObjectGroupReferenceId() != null) {
            aut.getArchiveUnitOrDataObjectReferenceOrDataObjectGroup().add(dor);
        }

        if (!dogt.getBinaryDataObjectOrPhysicalDataObject().isEmpty()) {
            dopt.getDataObjectGroupOrBinaryDataObjectOrPhysicalDataObject().add(dogt);
        }

        // ArchiveUnitProfile
        SipUtils.acceptIfNotNull(unit.getArchiveUnitProfile(), e -> aut.setArchiveUnitProfile(toIdentifierType(unit.getArchiveUnitProfile())));

        // Process Management
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

        if (mt.getUpdateOperation() != null || mt.getAccessRule() != null || mt.getAppraisalRule() != null
                || mt.getDisseminationRule() != null || mt.getReuseRule() != null
                || mt.getStorageRule() != null || mt.getClassificationRule() != null
                || mt.getLogBook() != null) {

            aut.setManagement(mt);
        }

        // Content
        DescriptiveMetadataContentType dmct = sedav2Factory.createDescriptiveMetadataContentType();

        // LevelGroup
        SipUtils.acceptIfNotNull(unit.getDescriptionLevel(), e -> dmct.setDescriptionLevel(toLevelType(e)));

        // Title Group
        unit.getTitles().forEach(t -> dmct.getTitle().add(toTextType(t)));

        // Identifier Group
        SipUtils.acceptIfNotNull(unit.getFilePlanPosition(), e -> dmct.getFilePlanPosition().add(e));
        SipUtils.acceptIfNotNull(unit.getSystemId(), e -> dmct.getSystemId().add(e));
        SipUtils.acceptIfNotNull(unit.getOriginatingSystemId(), e -> dmct.getOriginatingSystemId().add(e));
        SipUtils.acceptIfNotNull(unit.getOriginatingAgencyArchiveUnitIdentifier(), e -> dmct.getOriginatingAgencyArchiveUnitIdentifier().add(e));
        SipUtils.acceptIfNotNull(unit.getArchivalAgencyArchiveUnitIdentifier(), e -> dmct.getArchivalAgencyArchiveUnitIdentifier().add(e));
        SipUtils.acceptIfNotNull(unit.getTransferringAgencyArchiveUnitIdentifier(), e -> dmct.getTransferringAgencyArchiveUnitIdentifier().add(e));
        //  acceptIfNotNull(unit.getPhysicalId(), e -> dmct.getPhysicalId().add(toTextType(e)));
        if (isStrict && unit.getDataObjectSystemId() != null) {
            throw new SipgException("SEDA 2.1 does not support DataObjectSystemId");
        }

        // Description Group
        unit.getDescriptions().forEach(d -> dmct.getDescription().add(toTextType(d)));

        // CustodialHistory Group
        if (!unit.getCustodialItems().isEmpty()) {
            CustodialHistoryType cht = sedav2Factory.createCustodialHistoryType();
            unit.getCustodialItems().forEach(e -> cht.getCustodialHistoryItem().add(toCustodialHistoryItemType(e)));
            dmct.setCustodialHistory(cht);
        }

        // Type Group
        SipUtils.acceptIfNotNull(unit.getType(), e -> dmct.setType(toTextType(e)));
        SipUtils.acceptIfNotNull(unit.getDocumentType(), e -> dmct.setDocumentType(toTextType(e)));

        // Language Group
        unit.getLanguages().forEach(e -> dmct.getLanguage().add(e));
        SipUtils.acceptIfNotNull(unit.getDescriptionLanguage(), dmct::setDescriptionLanguage);

        // Status Group
        SipUtils.acceptIfNotNull(unit.getStatus(), dmct::setStatus);

        // Version Group
        SipUtils.acceptIfNotNull(unit.getVersion(), dmct::setVersion);

        // Keyword Group
        unit.getTags().forEach(tag -> {
            if (StringUtils.isBlank(tag.getKey())) {
                dmct.getTag().add(tag.getValue());
            } else {
                dmct.getKeyword().add(toKeywordType(tag));
            }
        });

        // Coverage Group
        // Originating & Submission Agency Group
        SipUtils.acceptIfNotNull(unit.getOriginatingAgency(), e -> dmct.setOriginatingAgency(toOrganizationWithIdType(e)));
        SipUtils.acceptIfNotNull(unit.getSubmissionAgency(), e -> dmct.setSubmissionAgency(toOrganizationWithIdType(e)));

        // Authorized Agent & Writing  Group
        unit.getAuthorizedAgents().forEach(agent -> dmct.getAuthorizedAgent().add(toAgentType(agent)));
        unit.getWriters().forEach(writer -> dmct.getWriter().add(toAgentType(writer)));

        // Audience Group
        unit.getAddressees().forEach(addressee -> dmct.getAddressee().add(toAgentType(addressee)));
        unit.getRecipients().forEach(recipient -> dmct.getRecipient().add(toAgentType(recipient)));
        unit.getTransmitters().forEach(transmitter -> dmct.getTransmitter().add(toAgentType(transmitter)));
        unit.getSenders().forEach(sender -> dmct.getSender().add(toAgentType(sender)));

        // Source Group
        SipUtils.acceptIfNotNull(unit.getSource(), dmct::setSource);

        // Relation Group
        SipUtils.acceptIfNotNull(unit.getRelation(), ror -> dmct.setRelatedObjectReference(toRelatedObjectReference(ror)));

        // Date Group
        SipUtils.acceptIfNotNull(unit.getCreatedDate(), d -> dmct.setCreatedDate(SipUtils.toXmlDate(d).toString()));
        SipUtils.acceptIfNotNull(unit.getTransactedDate(), d -> dmct.setTransactedDate(SipUtils.toXmlDate(d).toString()));
        SipUtils.acceptIfNotNull(unit.getAcquiredDate(), d -> dmct.setAcquiredDate(SipUtils.toXmlDate(d).toString()));
        SipUtils.acceptIfNotNull(unit.getSentDate(), d -> dmct.setSentDate(SipUtils.toXmlDate(d).toString()));
        SipUtils.acceptIfNotNull(unit.getReceivedDate(), d -> dmct.setReceivedDate(SipUtils.toXmlDate(d).toString()));
        SipUtils.acceptIfNotNull(unit.getRegisteredDate(), d -> dmct.setRegisteredDate(SipUtils.toXmlDate(d).toString()));
        SipUtils.acceptIfNotNull(unit.getStartDate(), d -> dmct.setStartDate(SipUtils.toXmlDate(d).toString()));
        SipUtils.acceptIfNotNull(unit.getEndDate(), d -> dmct.setEndDate(SipUtils.toXmlDate(d).toString()));

        // Event Group
        if (!unit.getLogEvents().isEmpty()) {
            LogBookType lbt = sedav2Factory.createLogBookType();
            unit.getLogEvents().forEach(event -> lbt.getEvent().add(toEventType(event)));
            mt.setLogBook(lbt);
        }

        // Signature Group
        unit.getSignatures().forEach(signature -> dmct.getSignature().add(toSignatureType(signature, dogt)));

        // GPS Group
        if (unit.getGpsVersionID() != null || unit.getGpsDateStamp() != null
                || unit.getGpsAltitude() != null || unit.getGpsAltitudeRef() != null
                || unit.getGpsLatitude() != null || unit.getGpsLatitudeRef() != null
                || unit.getGpsLongitude() != null || unit.getGpsLongitudeRef() != null) {

            final GpsType gps = new GpsType();
            SipUtils.acceptIfNotNull(unit.getGpsVersionID(), gps::setGpsVersionID);
            SipUtils.acceptIfNotNull(unit.getGpsAltitude(), s -> gps.setGpsAltitude(new BigInteger(s)));
            SipUtils.acceptIfNotNull(unit.getGpsAltitudeRef(), gps::setGpsAltitudeRef);
            SipUtils.acceptIfNotNull(unit.getGpsDateStamp(), gps::setGpsDateStamp);
            SipUtils.acceptIfNotNull(unit.getGpsLatitude(), gps::setGpsLatitude);
            SipUtils.acceptIfNotNull(unit.getGpsLatitudeRef(), gps::setGpsLatitudeRef);
            SipUtils.acceptIfNotNull(unit.getGpsLongitude(), gps::setGpsLongitude);
            SipUtils.acceptIfNotNull(unit.getGpsLongitudeRef(), gps::setGpsLongitudeRef);
            dmct.setGps(gps);
        }

        // Any Metadata
        for (Object e : unit.getElements()) {
            if (e instanceof String) {
                dmct.getAny().add(toNode((String) e));
            } else if (e instanceof Element) {
                dmct.getAny().add(toNode((Element) e));
            }
        }

        aut.setContent(dmct);
        unit.getArchiveUnits().forEach(u -> aut.getArchiveUnitOrDataObjectReferenceOrDataObjectGroup().add(toArchiveUnitType(u, dopt)));

        return aut;
    }

    private Node toNode(String fragment) {
        return toNode(fragment, documentBuilder);
    }

    private Node toNode(String fragment, DocumentBuilder docBuilder) {
        try {
            Document doc = docBuilder.newDocument();
            org.w3c.dom.Element element = docBuilder.parse(new InputSource(new StringReader(fragment))).getDocumentElement();
            element.setAttribute("xmlns", EXT_NS);
            return doc.importNode(element, true);
        } catch (SAXException | IOException ex) {
            throw new SipgException("Unable to create Node from document builder", ex);
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
        relation.getPartOfs().forEach(r -> rort.getIsPartOf().add(toDataObjectOrArchiveUnitReferenceType(r)));
        relation.getReferences().forEach(r -> rort.getReferences().add(toDataObjectOrArchiveUnitReferenceType(r)));
        relation.getReplaces().forEach(r -> rort.getReplaces().add(toDataObjectOrArchiveUnitReferenceType(r)));
        relation.getRequires().forEach(r -> rort.getRequires().add(toDataObjectOrArchiveUnitReferenceType(r)));
        relation.getVersionOfs().forEach(r -> rort.getIsVersionOf().add(toDataObjectOrArchiveUnitReferenceType(r)));
        return rort;
    }

    private DataObjectOrArchiveUnitReferenceType toDataObjectOrArchiveUnitReferenceType(RelationRef relationRef) {

        DataObjectOrArchiveUnitReferenceType dooaurt = sedav2Factory.createDataObjectOrArchiveUnitReferenceType();

        if (relationRef instanceof ArchiveUnitRef) {
            postProcessors.add(() -> {
                ArchiveUnit au = (ArchiveUnit) relationRef.getReference();
                ArchiveUnitType aut = archiveMap.get(au);
                if (aut == null) {
                    throw new SipgException("The related referenced archive unit does not exist in this archive");
                }
                dooaurt.setArchiveUnitRefId(aut);
            });
        } else if (relationRef instanceof DataObjectRef) {
            postProcessors.add(() -> {
                ArchiveUnit au = (ArchiveUnit) relationRef.getReference();
                ArchiveUnitType aut = archiveMap.get(au);
                if (aut == null) {
                    throw new SipgException("The related referenced archive unit does not exist in this archive");
                }
                List<Object> objs = aut.getArchiveUnitOrDataObjectReferenceOrDataObjectGroup();
                Optional<DataObjectRefType> opt = objs.stream().filter(e -> e instanceof DataObjectRefType).map(e -> (DataObjectRefType) e).findFirst();
                dooaurt.setDataObjectReference(opt.orElseThrow(() -> new SipgException("The related referenced data object does not exist in this archive")));
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
        SipUtils.acceptIfNotNull(custodialItem.getValue(), chit::setValue);
        SipUtils.acceptIfNotNull(custodialItem.getWhen(), e -> chit.setWhen(SipUtils.toXmlDateTime(e).toString()));
        return chit;
    }

    private FormatIdentificationType toFormatIdentificationType(String formatId, String formatLitteral, String mimeType) {
        FormatIdentificationType fit = sedav2Factory.createFormatIdentificationType();
        SipUtils.acceptIfNotNull(formatId, fit::setFormatId);
        SipUtils.acceptIfNotNull(formatLitteral, fit::setFormatLitteral);
        SipUtils.acceptIfNotNull(mimeType, fit::setMimeType);
        return fit;
    }

    private FileInfoType toFileInfoType(FileInfo fileInfo, String binaryName) {
        FileInfoType fit = sedav2Factory.createFileInfoType();

        fit.setFilename(StringUtils.isBlank(fileInfo.getFilename()) ? binaryName : fileInfo.getFilename());
        SipUtils.acceptIfNotNull(fileInfo.getCreatingApplicationName(), fit::setCreatingApplicationName);
        SipUtils.acceptIfNotNull(fileInfo.getCreatingApplicationVersion(), fit::setCreatingApplicationVersion);
        SipUtils.acceptIfNotNull(fileInfo.getCreatingOs(), fit::setCreatingOs);
        SipUtils.acceptIfNotNull(fileInfo.getCreatingOsVersion(), fit::setCreatingOsVersion);
        SipUtils.acceptIfNotNull(fileInfo.getDateCreatedByApplication(), e -> fit.setDateCreatedByApplication(SipUtils.toXmlDateTime(e)));
        SipUtils.acceptIfNotNull(fileInfo.getLastModified(), e -> fit.setLastModified(SipUtils.toXmlDateTime(e)));
        return fit;
    }

    private UpdateOperationType toUpdateOperationType(UpdateOperation updateOperation) {
        UpdateOperationType uopt = sedav2Factory.createUpdateOperationType();
        if (StringUtils.isNotBlank(updateOperation.getSystemId())) {
            uopt.setSystemId(updateOperation.getSystemId());
        } else if (StringUtils.isNotBlank(updateOperation.getMetadataName()) && StringUtils.isNotBlank(updateOperation.getMetadataValue())) {
            ArchiveUnitIdentifierKeyType auikt = sedav2Factory.createArchiveUnitIdentifierKeyType();
            auikt.setMetadataName(updateOperation.getMetadataName());
            auikt.setMetadataValue(updateOperation.getMetadataValue());
            uopt.setArchiveUnitIdentifierKey(auikt);
        } else {
            throw new SipgException("UpdateOperation mandates non blank values");
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
        SipUtils.acceptIfNotNull(accessRule.isPreventInheritance(), art::setPreventInheritance);
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
        SipUtils.acceptIfNotNull(classificationRule.isPreventInheritance(), art::setPreventInheritance);
        classificationRule.getPreventRuleNames().forEach(e -> art.getRefNonRuleId().add(toRuleIdType(e)));

        SipUtils.acceptIfNotNull(classificationRule.getClassificationLevel(), art::setClassificationLevel);
        SipUtils.acceptIfNotNull(classificationRule.getClassificationOwner(), art::setClassificationOwner);
        SipUtils.acceptIfNotNull(classificationRule.getClassificationAudience(), art::setClassificationAudience);
        SipUtils.acceptIfNotNull(classificationRule.getClassificationReassessingDate(), s -> art.setClassificationReassessingDate(SipUtils.toXmlDate(s)));
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
        SipUtils.acceptIfNotNull(disseminationRule.isPreventInheritance(), art::setPreventInheritance);
        disseminationRule.getPreventRuleNames().forEach(e -> art.getRefNonRuleId().add(toRuleIdType(e)));
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
        SipUtils.acceptIfNotNull(reuseRule.isPreventInheritance(), art::setPreventInheritance);
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
        SipUtils.acceptIfNotNull(appraisalRule.isPreventInheritance(), art::setPreventInheritance);
        appraisalRule.getPreventRuleNames().forEach(e -> art.getRefNonRuleId().add(toRuleIdType(e)));

        String action = appraisalRule.getFinalAction();
        if ("Keep".equals(action)) {
            art.setFinalAction(FinalActionAppraisalCodeType.KEEP);
        } else if ("Destroy".equals(action)) {
            art.setFinalAction(FinalActionAppraisalCodeType.DESTROY);
        }

        if (isStrict) {
            if (appraisalRule.getDuration() != null) {
                throw new SipgException("SEDA 2.1 does not support Duration");
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
        SipUtils.acceptIfNotNull(storageRule.isPreventInheritance(), art::setPreventInheritance);
        storageRule.getPreventRuleNames().forEach(e -> art.getRefNonRuleId().add(toRuleIdType(e)));

        String action = storageRule.getFinalAction();
        if (action != null) {
            switch (action) {
                case "Copy":
                    art.setFinalAction(FinalActionStorageCodeType.COPY);
                    break;
                case "RestrictAccess":
                    art.setFinalAction(FinalActionStorageCodeType.RESTRICT_ACCESS);
                    break;
                case "Transfer":
                    art.setFinalAction(FinalActionStorageCodeType.TRANSFER);
                    break;
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
        SipUtils.acceptIfNotNull(event.getIdentifier(), et::setEventIdentifier);
        SipUtils.acceptIfNotNull(event.getDateTime(), e -> et.setEventDateTime(SipUtils.toXmlDateTime(e).toString()));
        SipUtils.acceptIfNotNull(event.getDetail(), e -> et.setEventDetail(toTextType(e)));
        SipUtils.acceptIfNotNull(event.getDetailData(), et::setEventDetailData);
        SipUtils.acceptIfNotNull(event.getOutcome(), et::setOutcome);
        SipUtils.acceptIfNotNull(event.getOutcomeDetail(), et::setOutcomeDetail);
        SipUtils.acceptIfNotNull(event.getOutcomeDetailMessage(), et::setOutcomeDetailMessage);
        SipUtils.acceptIfNotNull(event.getType(), et::setEventType);
        SipUtils.acceptIfNotNull(event.getTypeCode(), et::setEventTypeCode);
        return et;
    }

    private AgentType toAgentType(Agent agent) {
        AgentType at = sedav2Factory.createAgentType();
        SipUtils.acceptIfNotBlank(agent.getFirstName(), at::setFirstName);
        SipUtils.acceptIfNotBlank(agent.getBirthName(), at::setBirthName);
        SipUtils.acceptIfNotBlank(agent.getFullName(), at::setFullName);
        SipUtils.acceptIfNotBlank(agent.getGivenName(), at::setGivenName);
        SipUtils.acceptIfNotBlank(agent.getGender(), at::setGender);
        SipUtils.acceptIfNotBlank(agent.getCorpname(), at::setCorpname);
        SipUtils.acceptIfNotNull(agent.getBirthDate(), e -> at.setBirthDate(SipUtils.toXmlDate(e)));
        SipUtils.acceptIfNotNull(agent.getDeathDate(), e -> at.setDeathDate(SipUtils.toXmlDate(e)));
        SipUtils.acceptIfNotNull(agent.getBirthPlace(), e -> at.setBirthPlace(toPlaceType(e)));
        SipUtils.acceptIfNotNull(agent.getDeathPlace(), e -> at.setDeathPlace(toPlaceType(e)));
        at.getNationality().addAll(agent.getNationalities());
        at.getIdentifier().addAll(agent.getIdentifiers());
        agent.getFunctions().forEach(e -> at.getFunction().add(toTextType(e)));
        agent.getActivities().forEach(e -> at.getActivity().add(toTextType(e)));
        agent.getPositions().forEach(e -> at.getPosition().add(toTextType(e)));
        agent.getRoles().forEach(e -> at.getRole().add(toTextType(e)));
        agent.getMandates().forEach(e -> at.getMandate().add(toTextType(e)));
        return at;
    }

    private SignatureType toSignatureType(Signature signature, DataObjectGroupType dogt) {
        ReferencedObjectType rot = sedav2Factory.createReferencedObjectType();
        if (dogt.getBinaryDataObjectOrPhysicalDataObject().isEmpty()) {
            throw new SipgException("The signed referenced object does not exist in this archive");
        }
        rot.setSignedObjectId(dogt);

        MessageDigestBinaryObjectType mdbot = sedav2Factory.createMessageDigestBinaryObjectType();
        mdbot.setAlgorithm(signature.getDigestAlgorithm());
        SipUtils.acceptIfNotNull(signature.getDigestValue(), mdbot::setValue);
        rot.setSignedObjectDigest(mdbot);

        SignatureType st = sedav2Factory.createSignatureType();
        signature.getSigners().forEach(e -> st.getSigner().add(toSignerType(e)));
        SipUtils.acceptIfNotNull(signature.getValidator(), e -> st.setValidator(toValidatorType(e)));
        st.setReferencedObject(rot);

        return st;
    }

    private SignerType toSignerType(Signer signer) {
        SignerType st = sedav2Factory.createSignerType();
        SipUtils.acceptIfNotBlank(signer.getFirstName(), st::setFirstName);
        SipUtils.acceptIfNotBlank(signer.getBirthName(), st::setBirthName);
        SipUtils.acceptIfNotBlank(signer.getFullName(), st::setFullName);
        SipUtils.acceptIfNotBlank(signer.getGivenName(), st::setGivenName);
        SipUtils.acceptIfNotBlank(signer.getGender(), st::setGender);
        SipUtils.acceptIfNotBlank(signer.getCorpname(), st::setCorpname);
        SipUtils.acceptIfNotNull(signer.getBirthDate(), e -> st.setBirthDate(SipUtils.toXmlDate(e)));
        SipUtils.acceptIfNotNull(signer.getDeathDate(), e -> st.setDeathDate(SipUtils.toXmlDate(e)));
        SipUtils.acceptIfNotNull(signer.getBirthPlace(), e -> st.setBirthPlace(toPlaceType(e)));
        SipUtils.acceptIfNotNull(signer.getDeathPlace(), e -> st.setDeathPlace(toPlaceType(e)));
        SipUtils.acceptIfNotNull(signer.getSigningTime(), e -> st.setSigningTime(SipUtils.toXmlDateTime(e)));

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
        SipUtils.acceptIfNotBlank(validator.getFirstName(), vt::setFirstName);
        SipUtils.acceptIfNotBlank(validator.getBirthName(), vt::setBirthName);
        SipUtils.acceptIfNotBlank(validator.getFullName(), vt::setFullName);
        SipUtils.acceptIfNotBlank(validator.getGivenName(), vt::setGivenName);
        SipUtils.acceptIfNotBlank(validator.getGender(), vt::setGender);
        SipUtils.acceptIfNotBlank(validator.getCorpname(), vt::setCorpname);
        SipUtils.acceptIfNotNull(validator.getBirthDate(), e -> vt.setBirthDate(SipUtils.toXmlDate(e)));
        SipUtils.acceptIfNotNull(validator.getDeathDate(), e -> vt.setDeathDate(SipUtils.toXmlDate(e)));
        SipUtils.acceptIfNotNull(validator.getBirthPlace(), e -> vt.setBirthPlace(toPlaceType(e)));
        SipUtils.acceptIfNotNull(validator.getDeathPlace(), e -> vt.setDeathPlace(toPlaceType(e)));
        SipUtils.acceptIfNotNull(validator.getValidationTime(), e -> vt.setValidationTime(SipUtils.toXmlDateTime(e)));

        vt.getNationality().addAll(validator.getNationalities());
        vt.getIdentifier().addAll(validator.getIdentifiers());
        validator.getFunctions().forEach(e -> vt.getFunction().add(toTextType(e)));
        validator.getActivities().forEach(e -> vt.getActivity().add(toTextType(e)));
        validator.getPositions().forEach(e -> vt.getPosition().add(toTextType(e)));
        validator.getRoles().forEach(e -> vt.getRole().add(toTextType(e)));
        validator.getMandates().forEach(e -> vt.getMandate().add(toTextType(e)));
        return vt;
    }

    private BirthOrDeathPlaceType toPlaceType(Place place) {
        BirthOrDeathPlaceType bodpt = sedav2Factory.createBirthOrDeathPlaceType();
        SipUtils.acceptIfNotBlank(place.getAddress(), bodpt::setAddress);
        SipUtils.acceptIfNotBlank(place.getCity(), bodpt::setCity);
        SipUtils.acceptIfNotBlank(place.getCountry(), bodpt::setCountry);
        SipUtils.acceptIfNotBlank(place.getGeogname(), bodpt::setGeogname);
        SipUtils.acceptIfNotBlank(place.getPostalCode(), bodpt::setPostalCode);
        SipUtils.acceptIfNotBlank(place.getRegion(), bodpt::setRegion);
        return bodpt;
    }

    private CodeListVersionsType toCodeListVersionsType(CodeListVersions code) {
        CodeListVersionsType clvt = sedav2Factory.createCodeListVersionsType();
        clvt.setId(code.getId());

        if (isStrict) {
            if (code.getSignatureStatusCodeListVersion() != null) {
                throw new SipgException("SEDA 2.1 does not support SignatureStatusCodeListVersion");
            }
            if (code.getFileEncodingCodeListVersion() != null) {
                throw new SipgException("SEDA 2.1 does not support FileEncodingCodeListVersion");
            }
        }

        SipUtils.acceptIfNotBlank(code.getAuthorizationReasonCodeListVersion(), e -> clvt.setAuthorizationReasonCodeListVersion(toCodeType(e)));
        SipUtils.acceptIfNotBlank(code.getFileFormatCodeListVersion(), e -> clvt.setFileFormatCodeListVersion(toCodeType(e)));
        SipUtils.acceptIfNotBlank(code.getMessageDigestAlgorithmCodeListVersion(), e -> clvt.setMessageDigestAlgorithmCodeListVersion(toCodeType(e)));
        SipUtils.acceptIfNotBlank(code.getRelationshipCodeListVersion(), e -> clvt.setRelationshipCodeListVersion(toCodeType(e)));
        SipUtils.acceptIfNotBlank(code.getReplyCodeListVersion(), e -> clvt.setReplyCodeListVersion(toCodeType(e)));

        SipUtils.acceptIfNotBlank(code.getMimeTypeCodeListVersion(), e -> clvt.setMimeTypeCodeListVersion(toCodeType(e)));
        SipUtils.acceptIfNotBlank(code.getEncodingCodeListVersion(), e -> clvt.setEncodingCodeListVersion(toCodeType(e)));
        SipUtils.acceptIfNotBlank(code.getCompressionAlgorithmCodeListVersion(), e -> clvt.setCompressionAlgorithmCodeListVersion(toCodeType(e)));
        SipUtils.acceptIfNotBlank(code.getDataObjectVersionCodeListVersion(), e -> clvt.setDataObjectVersionCodeListVersion(toCodeType(e)));
        SipUtils.acceptIfNotBlank(code.getStorageRuleCodeListVersion(), e -> clvt.setStorageRuleCodeListVersion(toCodeType(e)));
        SipUtils.acceptIfNotBlank(code.getAppraisalRuleCodeListVersion(), e -> clvt.setAppraisalRuleCodeListVersion(toCodeType(e)));
        SipUtils.acceptIfNotBlank(code.getAccessRuleCodeListVersion(), e -> clvt.setAccessRuleCodeListVersion(toCodeType(e)));
        SipUtils.acceptIfNotBlank(code.getDisseminationRuleCodeListVersion(), e -> clvt.setDisseminationRuleCodeListVersion(toCodeType(e)));
        SipUtils.acceptIfNotBlank(code.getReuseRuleCodeListVersion(), e -> clvt.setReuseRuleCodeListVersion(toCodeType(e)));
        SipUtils.acceptIfNotBlank(code.getClassificationRuleCodeListVersion(), e -> clvt.setClassificationRuleCodeListVersion(toCodeType(e)));
        SipUtils.acceptIfNotBlank(code.getAcquisitionInformationCodeListVersion(), e -> clvt.setAcquisitionInformationCodeListVersion(toCodeType(e)));

        return clvt;
    }

    private LevelType toLevelType(String levelType) {
        try {
            return LevelType.fromValue(levelType);
        } catch (IllegalArgumentException iae) {
            throw new SipgException("Seda 2.1 does not support the level type: " + levelType, iae);
        }
    }

    private LegalStatusType toLegalStatus(String legalStatusType) {
        try {
            return LegalStatusType.fromValue(legalStatusType);
        } catch (IllegalArgumentException iae) {
            throw new SipgException("Seda 2.1 does not support the legal status : " + legalStatusType, iae);
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
            OrganizationDescriptiveMetadataType odmt = sedav2Factory.createOrganizationDescriptiveMetadataType();

            SipUtils.acceptIfNotNull(agency.getName(), e -> odmt.getAny().add(toNode(new Element("Name", e))));
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
        SipUtils.acceptIfNotNull(transfer.getSignature(), e -> st.getAny().add(toNode(new Element("Format", e))));
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
        kt.setKeywordReference(toIdentifierType(tag.getKey()));
        kt.setKeywordContent(toTextType(tag.getValue()));
        return kt;
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
         * @param bdot       the bdot
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
                String digest = SipUtils.digestHex(binaryPath, mdbot.getAlgorithm());
                mdbot.setValue(digest);

                // Add binary file to zip
                if (zipArchive != null) {
                    Path zipEntry = zip(binaryPath, digest + "_" + Files.getLastModifiedTime(binaryPath).toMillis() + "_" + binaryPath.getFileName());
                    long size = (long) Files.getAttribute(zipEntry, "zip:size");
                    bdot.setSize(BigInteger.valueOf(size));
                    bdot.setUri(zipEntry.toString());
                } else {
                    bdot.setSize(BigInteger.valueOf(Files.size(binaryPath)));
                    bdot.setUri("Content/" + digest + "_" + Files.getLastModifiedTime(binaryPath).toMillis() + "_" + binaryPath.getFileName());
                }

                // Note. The Signature Identifier does not fully support NIO2 (ie. does not work with jimfs)
                FormatIdentificationType fit = bdot.getFormatIdentification();
                if (fit == null || StringUtils.isBlank(fit.getFormatId())) {
                    List<IdentificationResult> results = SipUtils.matchBinarySignatures(binaryPath);
                    if (results.isEmpty()) {
                        bdot.setFormatIdentification(toFormatIdentificationType("Unknown", null, null));
                    } else {
                        IdentificationResult r = results.get(0);
                        String name = StringUtils.isAllBlank(r.getName(), r.getVersion()) ? null : StringUtils.trim(r.getName() + " " + r.getVersion());
                        String mimeType = StringUtils.isBlank(r.getMimeType()) ? null : r.getMimeType();
                        bdot.setFormatIdentification(toFormatIdentificationType(r.getPuid(), name, mimeType));
                    }
                }
            } catch (Exception ex) {
                throw new SipgException("Fail to complete ZipTask for " + binaryPath, ex);
            }

            // Void
            return null;
        }
    }

}
