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

import static fr.xelians.sipg.service.sedajson.SedaJsonKeys.*;
import static fr.xelians.sipg.utils.SipUtils.ifNotBlank;
import static fr.xelians.sipg.utils.SipUtils.ifNotNull;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.xelians.sipg.model.*;
import fr.xelians.sipg.utils.DroidUtils;
import fr.xelians.sipg.utils.SipException;
import fr.xelians.sipg.utils.SipUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.math.BigInteger;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import uk.gov.nationalarchives.droid.core.interfaces.IdentificationResult;

/**
 * La classe SedaJsonConverter contient les informations et fonctions nécessaires à la conversion
 * d'une archive au standard SEDA JSON. L'ordre d'insertion des clés dans les objets JSON correspond
 * à l'ordre normatif du standard (miroir de la séquence du XSD SEDA). Cette classe ne peut être
 * instanciée qu'à travers les méthodes statiques convert(...). Note. La classe n'est pas thread
 * safe et un nouvel objet est systématiquement créé à chaque conversion.
 *
 * @author Emmanuel Deviller
 * @see ArchiveTransfer
 * @see SedaJsonConfig
 */
class SedaJsonConverter {

  private static final Logger LOGGER = LoggerFactory.getLogger(SedaJsonConverter.class);
  private static final JsonNodeFactory NF = JsonNodeFactory.instance;
  private static final DateTimeFormatter YYYYMMDD_HMS =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

  private final List<Callable<Void>> tasks = new ArrayList<>();
  private final AtomicInteger idCounter = new AtomicInteger();
  private final AtomicInteger auCounter = new AtomicInteger();
  private final AtomicInteger bdoCounter = new AtomicInteger();
  private final AtomicInteger pdoCounter = new AtomicInteger();
  private final DocumentBuilder documentBuilder;
  private final FileSystem zipArchive;
  private final boolean isStrict;
  private final boolean identifyFileFormat;
  private final boolean xmlId;

  private SedaJsonConverter(FileSystem zipArchive, SedaJsonConfig config) {
    this.zipArchive = zipArchive;
    this.isStrict = config.strict();
    this.identifyFileFormat = config.identifyFileFormat();
    this.xmlId = config.xmlId();

    try {
      documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    } catch (ParserConfigurationException ex) {
      throw new SipException("Unable to create DOM document builder", ex);
    }
  }

  /**
   * Convertit une archive en manifeste SEDA JSON. Cette fonction permet de s'assurer que la
   * structure de l'archive est valide. Les empreintes et tailles des objets binaires sont calculées
   * mais aucun objet binaire n'est copié.
   *
   * @param archiveTransfer l'archive à convertir
   * @param config la configuration utilisée lors de la conversion
   * @return le manifeste SEDA JSON
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  static ObjectNode convert(ArchiveTransfer archiveTransfer, SedaJsonConfig config)
      throws ExecutionException, InterruptedException {
    return convert(archiveTransfer, null, config);
  }

  /**
   * Convertit une archive en manifeste SEDA JSON. Les objets binaires de l'archive convertie sont
   * créés dans le paquet zip spécifié par le paramètre zipArchive.
   *
   * @param archiveTransfer l'archive à convertir
   * @param zipArchive le paquet zip de destination
   * @param config la configuration utilisée lors de la conversion
   * @return le manifeste SEDA JSON
   * @throws ExecutionException the execution exception
   * @throws InterruptedException the interrupted exception
   */
  static ObjectNode convert(
      ArchiveTransfer archiveTransfer, FileSystem zipArchive, SedaJsonConfig config)
      throws ExecutionException, InterruptedException {
    Validate.notNull(archiveTransfer, SipUtils.NOT_NULL, "archiveTransfer");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    SedaJsonConverter converter = new SedaJsonConverter(zipArchive, config);
    ObjectNode manifest = converter.toManifest(archiveTransfer);
    executeAndWait(converter, config);
    return manifest;
  }

  private static void executeAndWait(SedaJsonConverter converter, SedaJsonConfig config)
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

  private ObjectNode toManifest(ArchiveTransfer transfer) {
    if (StringUtils.isNotBlank(transfer.getSignature())
        || !transfer.getSignatureElements().isEmpty()) {
      reject("the message level Signature");
    }
    if (transfer.getDate() != null) {
      LOGGER.debug("The SEDA JSON standard does not include the message Date - ignored");
    }
    if (transfer.getCodeListVersions() != null) {
      LOGGER.debug("The SEDA JSON standard does not include CodeListVersions - ignored");
    }
    if (StringUtils.isBlank(transfer.getArchivalAgreement())) {
      throw new SipException("The SEDA JSON standard mandates a non blank ArchivalAgreement");
    }
    if (transfer.getArchivalAgency() == null
        || StringUtils.isBlank(transfer.getArchivalAgency().getIdentifier())) {
      throw new SipException("The SEDA JSON standard mandates an ArchivalAgency with identifier");
    }

    ObjectNode root = NF.objectNode();
    root.put(SEDA_JSON_VERSION, SedaJsonVersion.V2_0.getVersion());

    String mi =
        SipUtils.getIfBlank(
            transfer.getMessageIdentifier(),
            RandomStringUtils.secure().nextAlphabetic(32).toLowerCase());
    root.put(MESSAGE_IDENTIFIER, mi);

    ifNotBlank(transfer.getComment(), e -> root.put(COMMENT, e));
    root.put(ARCHIVAL_AGREEMENT, transfer.getArchivalAgreement());

    if (!transfer.getArchiveUnits().isEmpty()) {
      ArrayNode units = root.putArray(ARCHIVE_UNITS);
      transfer.getArchiveUnits().forEach(unit -> units.add(toArchiveUnit(unit)));
    }

    ObjectNode metadata = NF.objectNode();
    ifNotBlank(transfer.getArchivalProfile(), e -> metadata.put(ARCHIVAL_PROFILE, e));
    ifNotBlank(transfer.getServiceLevel(), e -> metadata.put(SERVICE_LEVEL, e));
    ifNotBlank(transfer.getAcquisitionInformation(), e -> metadata.put(ACQUISITION_INFORMATION, e));
    ifNotBlank(transfer.getLegalStatus(), e -> metadata.put(LEGAL_STATUS, e));
    ifNotBlank(
        transfer.getOriginatingAgencyIdentifier(),
        e -> metadata.put(ORIGINATING_AGENCY_IDENTIFIER, e));
    ifNotBlank(
        transfer.getSubmissionAgencyIdentifier(),
        e -> metadata.put(SUBMISSION_AGENCY_IDENTIFIER, e));
    if (!metadata.isEmpty()) {
      root.set(MANAGEMENT_METADATA, metadata);
    }

    root.set(ARCHIVAL_AGENCY, toAgency(transfer.getArchivalAgency()));
    ifNotNull(transfer.getTransferringAgency(), e -> root.set(TRANSFERRING_AGENCY, toAgency(e)));
    return root;
  }

  private ObjectNode toArchiveUnit(ArchiveUnit unit) {
    if (!unit.getReferences().isEmpty()) {
      reject("references between archive units (ArchiveUnitRefId)");
    }
    if (!unit.getDataObjectSystemIds().isEmpty()) {
      reject("the deprecated DataObjectSystemId");
    }
    if (StringUtils.isNotBlank(unit.getSignatureStatus())) {
      LOGGER.debug("The SEDA JSON standard does not include SignatureStatus - ignored");
    }

    ObjectNode node = NF.objectNode();

    // L'XmlId, lorsqu'il est activé, est la première clé de l'unité afin d'identifier de manière
    // unique l'unité dans la totalité du manifeste (préfixe AU)
    if (xmlId) {
      node.put(XML_ID, "AU" + auCounter.incrementAndGet());
    }

    ObjectNode management = toManagement(unit);
    if (!management.isEmpty()) {
      node.set(MANAGEMENT, management);
    }

    node.set(CONTENT, toContent(unit));

    ArrayNode binaryObjects = toBinaryDataObjects(unit);
    if (!binaryObjects.isEmpty()) {
      node.set(BINARY_DATA_OBJECTS, binaryObjects);
    }

    if (StringUtils.isNotBlank(unit.getPhysicalId())) {
      ArrayNode physicalObjects = node.putArray(PHYSICAL_DATA_OBJECTS);
      ObjectNode pdo = physicalObjects.addObject();
      if (xmlId) {
        pdo.put(XML_ID, "PDO" + pdoCounter.incrementAndGet());
      }
      ifNotBlank(unit.getPhysicalVersion(), e -> pdo.put(DATA_OBJECT_VERSION, e));
      pdo.put(PHYSICAL_ID, unit.getPhysicalId());
      if (unit.getMeasure() > 0) {
        pdo.put(MEASURE, unit.getMeasure());
      }
    }

    if (!unit.getArchiveUnits().isEmpty()) {
      ArrayNode children = node.putArray(ARCHIVE_UNITS);
      unit.getArchiveUnits().forEach(child -> children.add(toArchiveUnit(child)));
    }
    return node;
  }

  private ObjectNode toManagement(ArchiveUnit unit) {
    ObjectNode node = NF.objectNode();

    StorageRules storageRules = unit.getStorageRules();
    if (storageRules != null) {
      ObjectNode rules = toSimpleRules(storageRules);
      String action = storageRules.getFinalAction();
      if (action != null) {
        switch (action) {
          case "Copy", "RestrictAccess", "Transfer" -> rules.put(FINAL_ACTION, action);
          default -> throw new SipException(String.format("Unknown final action %s", action));
        }
      }
      node.set(STORAGE_RULE, rules);
    }

    AppraisalRules appraisalRules = unit.getAppraisalRules();
    if (appraisalRules != null) {
      ObjectNode rules = toSimpleRules(appraisalRules);
      ifNotBlank(appraisalRules.getDuration(), e -> rules.put(DURATION, e));
      String action = appraisalRules.getFinalAction();
      if ("Keep".equals(action) || "Destroy".equals(action)) {
        rules.put(FINAL_ACTION, action);
      }
      node.set(APPRAISAL_RULE, rules);
    }

    if (unit.getAccessRules() != null) {
      node.set(ACCESS_RULE, toSimpleRules(unit.getAccessRules()));
    }

    if (unit.getDisseminationRules() != null) {
      node.set(DISSEMINATION_RULE, toSimpleRules(unit.getDisseminationRules()));
    }

    if (unit.getReuseRules() != null) {
      node.set(REUSE_RULE, toSimpleRules(unit.getReuseRules()));
    }

    ClassificationRules classificationRules = unit.getClassificationRules();
    if (classificationRules != null) {
      ObjectNode rules = toSimpleRules(classificationRules);
      ifNotBlank(
          classificationRules.getClassificationLevel(), e -> rules.put(CLASSIFICATION_LEVEL, e));
      ifNotBlank(
          classificationRules.getClassificationOwner(), e -> rules.put(CLASSIFICATION_OWNER, e));
      ifNotBlank(
          classificationRules.getClassificationAudience(),
          e -> rules.put(CLASSIFICATION_AUDIENCE, e));
      ifNotNull(
          classificationRules.getClassificationReassessingDate(),
          e -> rules.put(CLASSIFICATION_REASSESSING_DATE, toJsonDate(e)));
      ifNotNull(
          classificationRules.getNeedReassessingAuthorization(),
          e -> rules.put(NEED_REASSESSING_AUTHORIZATION, e));
      node.set(CLASSIFICATION_RULE, rules);
    }

    if (!unit.getLogbookEvents().isEmpty()) {
      ArrayNode logBook = node.putArray(LOG_BOOK);
      unit.getLogbookEvents().forEach(event -> logBook.add(toEvent(event)));
    }

    ifNotNull(unit.getNeedAuthorization(), e -> node.put(NEED_AUTHORIZATION, e));

    HoldRules holdRules = unit.getHoldRules();
    if (holdRules != null) {
      ObjectNode rules = NF.objectNode();
      if (!holdRules.getRules().isEmpty()) {
        ArrayNode items = rules.putArray(RULES);
        for (HoldRule rule : holdRules.getRules()) {
          ObjectNode item = items.addObject();
          item.put(RULE, rule.getName());
          ifNotNull(rule.getStartDate(), e -> item.put(START_DATE, toJsonDate(e)));
          ifNotNull(rule.getHoldEndDate(), e -> item.put(HOLD_END_DATE, toJsonDate(e)));
          ifNotBlank(rule.getHoldOwner(), e -> item.put(HOLD_OWNER, e));
          ifNotNull(rule.getHoldReassessingDate(), e -> item.put(REASSESSING_DATE, toJsonDate(e)));
          ifNotBlank(rule.getHoldReason(), e -> item.put(HOLD_REASON, e));
          ifNotNull(rule.getPreventRearrangement(), e -> item.put(PREVENT_REARRANGEMENT, e));
        }
      }
      setPreventInheritance(rules, holdRules);
      node.set(HOLD_RULE, rules);
    }

    UpdateOperation updateOperation = unit.getUpdateOperation();
    if (updateOperation != null) {
      ObjectNode operation = node.putObject(UPDATE_OPERATION);
      if (StringUtils.isNotBlank(updateOperation.getSystemId())) {
        operation.put(SYSTEM_ID, updateOperation.getSystemId());
      } else if (StringUtils.isNotBlank(updateOperation.getMetadataName())
          && StringUtils.isNotBlank(updateOperation.getMetadataValue())) {
        operation.put(METADATA_NAME, updateOperation.getMetadataName());
        operation.put(METADATA_VALUE, updateOperation.getMetadataValue());
      } else {
        throw new SipException("UpdateOperation mandates non blank values");
      }
    }

    return node;
  }

  private ObjectNode toSimpleRules(AbstractSimpleRules simpleRules) {
    ObjectNode node = NF.objectNode();
    if (!simpleRules.getRules().isEmpty()) {
      ArrayNode items = node.putArray(RULES);
      for (Rule rule : simpleRules.getRules()) {
        ObjectNode item = items.addObject();
        item.put(RULE, rule.getName());
        ifNotNull(rule.getStartDate(), e -> item.put(START_DATE, toJsonDate(e)));
      }
    }
    setPreventInheritance(node, simpleRules);
    return node;
  }

  private void setPreventInheritance(ObjectNode node, AbstractRules rules) {
    // xsd:choice : you cannot set PreventInheritance and PreventRuleNames
    if (rules.isPreventInheritance() != null && !rules.getPreventRuleNames().isEmpty()) {
      throw new SipException("You cannot set both PreventInheritance and PreventRuleNames");
    }
    ifNotNull(rules.isPreventInheritance(), e -> node.put(PREVENT_INHERITANCE, e));
    if (!rules.getPreventRuleNames().isEmpty()) {
      ArrayNode names = node.putArray(PREVENT_RULE_NAMES);
      rules.getPreventRuleNames().forEach(names::add);
    }
  }

  private ObjectNode toContent(ArchiveUnit unit) {
    ObjectNode node = NF.objectNode();

    ifNotBlank(unit.getDescriptionLevel(), e -> node.put(DESCRIPTION_LEVEL, e));
    setTexts(node, TITLE, TITLES, unit.getTitles());
    if (!node.has(TITLE)) {
      throw new SipException("The SEDA JSON standard mandates a non blank archive unit Title");
    }

    setStringArray(node, FILE_PLAN_POSITIONS, unit.getFilePlanPositions());
    setStringArray(node, SYSTEM_IDS, unit.getSystemIds());
    setStringArray(node, ORIGINATING_SYSTEM_IDS, unit.getOriginatingSystemIds());
    setStringArray(
        node,
        ARCHIVAL_AGENCY_ARCHIVE_UNIT_IDENTIFIERS,
        unit.getArchivalAgencyArchiveUnitIdentifiers());
    setStringArray(
        node,
        ORIGINATING_AGENCY_ARCHIVE_UNIT_IDENTIFIERS,
        unit.getOriginatingAgencyArchiveUnitIdentifiers());
    setStringArray(
        node,
        TRANSFERRING_AGENCY_ARCHIVE_UNIT_IDENTIFIERS,
        unit.getTransferringAgencyArchiveUnitIdentifiers());

    setTexts(node, DESCRIPTION, DESCRIPTIONS, unit.getDescriptions());

    if (!unit.getCustodialItems().isEmpty()) {
      ArrayNode items = node.putArray(CUSTODIAL_HISTORY_ITEMS);
      for (CustodialItem custodialItem : unit.getCustodialItems()) {
        if (custodialItem.getWhen() == null) {
          items.add(custodialItem.getValue());
        } else {
          ObjectNode item = items.addObject();
          item.put(VALUE, custodialItem.getValue());
          item.put(WHEN, toJsonDateTime(custodialItem.getWhen()));
        }
      }
    }

    ifNotBlank(unit.getArchiveUnitProfile(), e -> node.put(ARCHIVE_UNIT_PROFILE, e));
    ifNotBlank(unit.getType(), e -> node.put(TYPE, e));
    ifNotBlank(unit.getDocumentType(), e -> node.put(DOCUMENT_TYPE, e));
    setStringArray(node, LANGUAGES, unit.getLanguages());
    ifNotBlank(unit.getDescriptionLanguage(), e -> node.put(DESCRIPTION_LANGUAGE, e));
    ifNotBlank(unit.getStatus(), e -> node.put(STATUS, e));
    ifNotBlank(unit.getVersion(), e -> node.put(VERSION, e));

    List<String> tags = new ArrayList<>();
    List<Tag> keywords = new ArrayList<>();
    for (Tag tag : unit.getTags()) {
      if (StringUtils.isBlank(tag.key())) {
        tags.add(tag.value());
      } else {
        keywords.add(tag);
      }
    }
    setStringArray(node, TAGS, tags);
    if (!keywords.isEmpty()) {
      ArrayNode keywordArray = node.putArray(KEYWORDS);
      for (Tag keyword : keywords) {
        ObjectNode keywordNode = keywordArray.addObject();
        keywordNode.put(KEYWORD_REFERENCE, keyword.key());
        keywordNode.put(KEYWORD_CONTENT, keyword.value());
      }
    }

    ifNotNull(unit.getOriginatingAgency(), e -> node.set(ORIGINATING_AGENCY, toAgency(e)));
    ifNotNull(unit.getSubmissionAgency(), e -> node.set(SUBMISSION_AGENCY, toAgency(e)));

    setAgents(node, AGENTS, unit.getAgents());
    setAgents(node, AUTHORIZED_AGENTS, unit.getAuthorizedAgents());
    setAgents(node, WRITERS, unit.getWriters());
    setAgents(node, ADDRESSEES, unit.getAddressees());
    setAgents(node, RECIPIENTS, unit.getRecipients());
    setAgents(node, TRANSMITTERS, unit.getTransmitters());
    setAgents(node, SENDERS, unit.getSenders());

    ifNotBlank(unit.getSource(), e -> node.put(SOURCE, e));

    ifNotNull(unit.getRelation(), e -> node.set(RELATED_OBJECT_REFERENCE, toRelation(e)));

    ifNotNull(unit.getCreatedDate(), e -> node.put(CREATED_DATE, toJsonDate(e)));
    ifNotNull(unit.getTransactedDate(), e -> node.put(TRANSACTED_DATE, toJsonDate(e)));
    ifNotNull(unit.getAcquiredDate(), e -> node.put(ACQUIRED_DATE, toJsonDate(e)));
    ifNotNull(unit.getSentDate(), e -> node.put(SENT_DATE, toJsonDate(e)));
    ifNotNull(unit.getReceivedDate(), e -> node.put(RECEIVED_DATE, toJsonDate(e)));
    ifNotNull(unit.getRegisteredDate(), e -> node.put(REGISTERED_DATE, toJsonDate(e)));
    ifNotNull(unit.getStartDate(), e -> node.put(START_DATE, toJsonDate(e)));
    ifNotNull(unit.getEndDate(), e -> node.put(END_DATE, toJsonDate(e)));

    if (!unit.getEvents().isEmpty()) {
      ArrayNode events = node.putArray(EVENTS);
      unit.getEvents().forEach(event -> events.add(toEvent(event)));
    }

    if (!unit.getSignatures().isEmpty()) {
      ArrayNode signatures = node.putArray(SIGNATURES);
      unit.getSignatures().forEach(signature -> signatures.add(toSignature(signature, unit)));
    }

    ifNotNull(unit.getSigningInformation(), e -> node.set(SIGNING_INFORMATION, toSigning(e)));

    setGps(node, unit);

    // Extended ontology elements
    for (Object element : unit.getElements()) {
      if (element instanceof Element elt) {
        appendElement(node, elt);
      } else if (element instanceof String str) {
        appendXmlFragment(node, str);
      }
    }

    return node;
  }

  /*
   * Le standard SEDA JSON impose une clé Title de type chaîne. Le premier texte sans langue (à
   * défaut le premier texte) devient la clé simple, les autres textes sont placés dans le tableau
   * pluriel (objets {Lang, Value} si une langue est définie, chaînes sinon).
   */
  private void setTexts(ObjectNode node, String key, String pluralKey, List<Text> texts) {
    if (texts == null || texts.isEmpty()) {
      return;
    }

    Text first =
        texts.stream().filter(t -> StringUtils.isBlank(t.getLang())).findFirst().orElse(null);
    if (first == null) {
      first = texts.getFirst();
    }
    node.put(key, first.getMessage());

    ArrayNode others = NF.arrayNode();
    for (Text text : texts) {
      if (text != first) {
        if (StringUtils.isBlank(text.getLang())) {
          others.add(text.getMessage());
        } else {
          ObjectNode item = others.addObject();
          item.put(LANG, text.getLang());
          item.put(VALUE, text.getMessage());
        }
      }
    }
    if (!others.isEmpty()) {
      node.set(pluralKey, others);
    }
  }

  private void setStringArray(ObjectNode node, String key, List<String> values) {
    if (values != null && !values.isEmpty()) {
      ArrayNode array = node.putArray(key);
      values.forEach(array::add);
    }
  }

  private void setAgents(ObjectNode node, String key, List<Agent> agents) {
    if (agents != null && !agents.isEmpty()) {
      ArrayNode array = node.putArray(key);
      agents.forEach(agent -> array.add(toAgent(agent)));
    }
  }

  private ObjectNode toAgent(Agent agent) {
    ObjectNode node = NF.objectNode();
    setAgentProperties(node, agent);
    return node;
  }

  private void setAgentProperties(ObjectNode node, Agent agent) {
    ifNotBlank(agent.getFirstName(), e -> node.put(FIRST_NAME, e));
    ifNotBlank(agent.getBirthName(), e -> node.put(BIRTH_NAME, e));
    ifNotBlank(agent.getFullName(), e -> node.put(FULL_NAME, e));
    ifNotBlank(agent.getGivenName(), e -> node.put(GIVEN_NAME, e));
    ifNotBlank(agent.getGender(), e -> node.put(GENDER, e));
    ifNotNull(agent.getBirthDate(), e -> node.put(BIRTH_DATE, toJsonDate(e)));
    ifNotNull(agent.getBirthPlace(), e -> node.set(BIRTH_PLACE, toPlace(e)));
    ifNotNull(agent.getDeathDate(), e -> node.put(DEATH_DATE, toJsonDate(e)));
    ifNotNull(agent.getDeathPlace(), e -> node.set(DEATH_PLACE, toPlace(e)));
    setStringArray(node, NATIONALITIES, agent.getNationalities());
    ifNotBlank(agent.getCorpName(), e -> node.put(CORPNAME, e));
    setStringArray(node, IDENTIFIERS, agent.getIdentifiers());
    setStringArray(node, FUNCTIONS, agent.getFunctions());
    setStringArray(node, ACTIVITIES, agent.getActivities());
    setStringArray(node, POSITIONS, agent.getPositions());
    setStringArray(node, ROLES, agent.getRoles());
    setStringArray(node, MANDATES, agent.getMandates());
  }

  private ObjectNode toSigner(Signer signer) {
    ObjectNode node = NF.objectNode();
    setAgentProperties(node, signer);
    ifNotNull(signer.getSigningTime(), e -> node.put(SIGNING_TIME, toJsonDateTime(e)));
    return node;
  }

  private ObjectNode toValidator(fr.xelians.sipg.model.Validator validator) {
    ObjectNode node = NF.objectNode();
    setAgentProperties(node, validator);
    ifNotNull(validator.getValidationTime(), e -> node.put(VALIDATION_TIME, toJsonDateTime(e)));
    return node;
  }

  private ObjectNode toPlace(Place place) {
    ObjectNode node = NF.objectNode();
    ifNotBlank(place.getGeogName(), e -> node.put(GEOGNAME, e));
    ifNotBlank(place.getAddress(), e -> node.put(ADDRESS, e));
    ifNotBlank(place.getPostalCode(), e -> node.put(POSTAL_CODE, e));
    ifNotBlank(place.getCity(), e -> node.put(CITY, e));
    ifNotBlank(place.getRegion(), e -> node.put(REGION, e));
    ifNotBlank(place.getCountry(), e -> node.put(COUNTRY, e));
    return node;
  }

  private ObjectNode toRelation(RelatedObjectRef relation) {
    ObjectNode node = NF.objectNode();
    setRelationItems(node, IS_VERSION_OFS, relation.getVersionOfs());
    setRelationItems(node, REPLACES, relation.getReplaces());
    setRelationItems(node, REQUIRES, relation.getRequires());
    setRelationItems(node, IS_PART_OFS, relation.getPartOfs());
    setRelationItems(node, REFERENCES, relation.getReferences());
    return node;
  }

  private void setRelationItems(ObjectNode node, String key, List<RelationRef> refs) {
    ArrayNode array = NF.arrayNode();
    for (RelationRef<?> ref : refs) {
      if (ref instanceof RepositoryArchiveUnitPID pid) {
        array.addObject().put(REPOSITORY_ARCHIVE_UNIT_PID, pid.getReference());
      } else if (ref instanceof RepositoryObjectPID pid) {
        array.addObject().put(REPOSITORY_OBJECT_PID, pid.getReference());
      } else if (ref instanceof ExternalReference ext) {
        array.addObject().put(EXTERNAL_REFERENCE, ext.getReference());
      } else {
        // ArchiveUnitRef et DataObjectRef sont des références internes au paquet
        reject("references between archive units or data objects in RelatedObjectReference");
      }
    }
    if (!array.isEmpty()) {
      node.set(key, array);
    }
  }

  private ObjectNode toEvent(Event event) {
    ObjectNode node = NF.objectNode();
    ifNotBlank(event.getIdentifier(), e -> node.put(EVENT_IDENTIFIER, e));
    ifNotBlank(event.getTypeCode(), e -> node.put(EVENT_TYPE_CODE, e));
    ifNotBlank(event.getType(), e -> node.put(EVENT_TYPE, e));
    ifNotNull(event.getDateTime(), e -> node.put(EVENT_DATE_TIME, toJsonDateTime(e)));
    ifNotBlank(event.getDetail(), e -> node.put(EVENT_DETAIL, e));
    ifNotBlank(event.getOutcome(), e -> node.put(OUTCOME, e));
    ifNotBlank(event.getOutcomeDetail(), e -> node.put(OUTCOME_DETAIL, e));
    ifNotBlank(event.getOutcomeDetailMessage(), e -> node.put(OUTCOME_DETAIL_MESSAGE, e));
    ifNotBlank(event.getDetailData(), e -> node.put(EVENT_DETAIL_DATA, e));
    return node;
  }

  private ObjectNode toSignature(Signature signature, ArchiveUnit unit) {
    if (unit.getBinaryMasterDataObject() == null
        && unit.getDisseminationDataObject() == null
        && unit.getThumbnailDataObject() == null
        && unit.getTextContentDataObject() == null
        && StringUtils.isBlank(unit.getPhysicalId())) {
      throw new SipException("The signed referenced object does not exist in this archive");
    }

    ObjectNode node = NF.objectNode();
    if (!signature.getSigners().isEmpty()) {
      ArrayNode signers = node.putArray(SIGNERS);
      signature.getSigners().forEach(signer -> signers.add(toSigner(signer)));
    }
    ifNotNull(signature.getValidator(), e -> node.set(VALIDATOR, toValidator(e)));

    // Le SignedObjectId est implicite : les objets de données sont déclarés dans l'unité
    ObjectNode referencedObject = node.putObject(REFERENCED_OBJECT);
    ObjectNode digest = referencedObject.putObject(SIGNED_OBJECT_DIGEST);
    digest.put(ALGORITHM, signature.getDigestAlgorithm());
    ifNotBlank(signature.getDigestValue(), e -> digest.put(MESSAGE_DIGEST, e));
    return node;
  }

  private ObjectNode toSigning(SigningInformation signing) {
    ObjectNode node = NF.objectNode();

    if (!signing.getSigningRoles().isEmpty()) {
      ArrayNode roles = node.putArray(SIGNING_ROLES);
      signing.getSigningRoles().forEach(e -> roles.add(toPascalCase(e.toString())));
    }

    if (!signing.getDetachedSigningRoles().isEmpty()) {
      ArrayNode roles = node.putArray(DETACHED_SIGNING_ROLES);
      signing.getDetachedSigningRoles().forEach(e -> roles.add(toPascalCase(e.toString())));
    }

    setStringArray(node, SIGNED_DOCUMENT_REFERENCE_IDS, signing.getSignedDocumentReferenceIds());

    if (!signing.getSignatureDescriptions().isEmpty()) {
      ArrayNode descriptions = node.putArray(SIGNATURE_DESCRIPTIONS);
      for (SignatureDescription description : signing.getSignatureDescriptions()) {
        ObjectNode item = descriptions.addObject();
        ifNotNull(description.getSigner(), e -> item.set(SIGNER, toSigner(e)));
        ifNotNull(description.getValidator(), e -> item.set(VALIDATOR, toValidator(e)));
        ifNotBlank(description.getSigningType(), e -> item.put(SIGNING_TYPE, e));
      }
    }

    if (!signing.getTimestampingInformations().isEmpty()) {
      ArrayNode timestamps = node.putArray(TIMESTAMPING_INFORMATIONS);
      for (TimestampingInformation timestamping : signing.getTimestampingInformations()) {
        ObjectNode item = timestamps.addObject();
        ifNotNull(timestamping.getTimeStamp(), e -> item.put(TIME_STAMP, toJsonDateTime(e)));
        ifNotBlank(
            timestamping.getAdditionalTimestampingInformation(),
            e -> item.put(ADDITIONAL_TIMESTAMPING_INFORMATION, e));
      }
    }

    // Les AdditionalProofInformation sont nichés sous un AdditionalProof (symétrie avec le XML
    // SEDA et l'ontologie eSafe SigningInformation.AdditionalProof.AdditionalProofInformation)
    if (!signing.getAdditionalProofInformation().isEmpty()) {
      ArrayNode proofs = node.putArray(ADDITIONAL_PROOF);
      ObjectNode item = proofs.addObject();
      setStringArray(item, ADDITIONAL_PROOF_INFORMATION, signing.getAdditionalProofInformation());
    }
    return node;
  }

  private void setGps(ObjectNode node, ArchiveUnit unit) {
    if (unit.getGpsVersionID() == null
        && unit.getGpsAltitude() == null
        && unit.getGpsAltitudeRef() == null
        && unit.getGpsLatitude() == null
        && unit.getGpsLatitudeRef() == null
        && unit.getGpsLongitude() == null
        && unit.getGpsLongitudeRef() == null
        && unit.getGpsDateStamp() == null) {
      return;
    }

    ObjectNode gps = node.putObject(GPS);
    ifNotNull(unit.getGpsVersionID(), e -> gps.put(GPS_VERSION_ID, e));
    ifNotNull(unit.getGpsAltitude(), e -> gps.put(GPS_ALTITUDE, new BigInteger(e)));
    ifNotNull(unit.getGpsAltitudeRef(), e -> gps.put(GPS_ALTITUDE_REF, e));
    ifNotNull(unit.getGpsLatitude(), e -> gps.put(GPS_LATITUDE, e));
    ifNotNull(unit.getGpsLatitudeRef(), e -> gps.put(GPS_LATITUDE_REF, e));
    ifNotNull(unit.getGpsLongitude(), e -> gps.put(GPS_LONGITUDE, e));
    ifNotNull(unit.getGpsLongitudeRef(), e -> gps.put(GPS_LONGITUDE_REF, e));
    ifNotNull(unit.getGpsDateStamp(), e -> gps.put(GPS_DATE_STAMP, e));
  }

  private ObjectNode toAgency(Agency agency) {
    ObjectNode node = NF.objectNode();
    node.put(IDENTIFIER, agency.getIdentifier());
    // Le Name est niché sous OrganizationDescriptiveMetadata (structure SEDA réelle, symétrie avec
    // le XML et l'ontologie eSafe OriginatingAgency.OrganizationDescriptiveMetadata.Name)
    ifNotBlank(
        agency.getName(), e -> node.putObject(ORGANIZATION_DESCRIPTIVE_METADATA).put(NAME, e));
    if (!agency.getElements().isEmpty()) {
      reject("extended elements in agencies");
    }
    return node;
  }

  /*
   * Éléments étendus : un élément feuille sans attribut devient une chaîne, un élément avec
   * attributs ou enfants devient un objet (les attributs deviennent des propriétés et la valeur
   * texte est placée sous la clé "Value"). Les noms répétés sont fusionnés en tableau.
   */
  private void appendElement(ObjectNode parentNode, Element element) {
    mergeValue(parentNode, element.getName(), toExtendedNode(element));
  }

  private JsonNode toExtendedNode(Element element) {
    Map<String, String> attributes = element.getAttributes();
    List<Element> children = element.getElements();

    if (attributes.isEmpty() && children.isEmpty()) {
      return NF.textNode(StringUtils.defaultString(element.getValue()));
    }

    ObjectNode node = NF.objectNode();
    // TreeMap pour garantir un ordre déterministe des attributs
    new TreeMap<>(attributes).forEach(node::put);
    if (StringUtils.isNotBlank(element.getValue())) {
      node.put(VALUE, element.getValue());
    }
    for (Element child : children) {
      if (attributes.containsKey(child.getName())) {
        reject(
            String.format(
                "the extended element '%s' with both an attribute and a child named '%s'",
                element.getName(), child.getName()));
        continue;
      }
      appendElement(node, child);
    }
    return node;
  }

  // sipg accepte aussi les éléments étendus sous forme de fragments XML bruts
  private void appendXmlFragment(ObjectNode parentNode, String fragment) {
    try {
      org.w3c.dom.Element root =
          documentBuilder.parse(new InputSource(new StringReader(fragment))).getDocumentElement();
      mergeValue(parentNode, root.getTagName(), toExtendedNode(root));
    } catch (SAXException | IOException ex) {
      throw new SipException("Unable to parse the extended element fragment: " + fragment, ex);
    }
  }

  private JsonNode toExtendedNode(org.w3c.dom.Element element) {
    List<org.w3c.dom.Element> children = new ArrayList<>();
    for (org.w3c.dom.Node child = element.getFirstChild();
        child != null;
        child = child.getNextSibling()) {
      if (child instanceof org.w3c.dom.Element childElement) {
        children.add(childElement);
      }
    }

    NamedNodeMap attributes = element.getAttributes();
    if (attributes.getLength() == 0 && children.isEmpty()) {
      return NF.textNode(StringUtils.defaultString(element.getTextContent()));
    }

    ObjectNode node = NF.objectNode();
    Map<String, String> attributeMap = new TreeMap<>();
    for (int i = 0; i < attributes.getLength(); i++) {
      org.w3c.dom.Node attribute = attributes.item(i);
      attributeMap.put(attribute.getNodeName(), attribute.getNodeValue());
    }
    attributeMap.forEach(node::put);

    if (children.isEmpty() && StringUtils.isNotBlank(element.getTextContent())) {
      node.put(VALUE, element.getTextContent());
    }
    for (org.w3c.dom.Element child : children) {
      if (attributeMap.containsKey(child.getTagName())) {
        reject(
            String.format(
                "the extended element '%s' with both an attribute and a child named '%s'",
                element.getTagName(), child.getTagName()));
        continue;
      }
      mergeValue(node, child.getTagName(), toExtendedNode(child));
    }
    return node;
  }

  private void mergeValue(ObjectNode parentNode, String name, JsonNode value) {
    JsonNode existing = parentNode.get(name);
    if (existing == null) {
      parentNode.set(name, value);
    } else if (existing.isArray()) {
      ((ArrayNode) existing).add(value);
    } else {
      parentNode.putArray(name).add(existing).add(value);
    }
  }

  private ArrayNode toBinaryDataObjects(ArchiveUnit unit) {
    ArrayNode array = NF.arrayNode();
    addBinaryDataObject(array, unit.getBinaryMasterDataObject());
    addBinaryDataObject(array, unit.getDisseminationDataObject());
    addBinaryDataObject(array, unit.getThumbnailDataObject());
    addBinaryDataObject(array, unit.getTextContentDataObject());
    return array;
  }

  private void addBinaryDataObject(ArrayNode array, BinaryDataObject bdo) {
    if (bdo == null) {
      return;
    }

    boolean removePath = false;
    Path binaryPath = bdo.getBinaryPath();
    if (binaryPath == null) {
      Supplier<Path> bs = bdo.getBinaryPathSupplier();
      if (bs == null) {
        return;
      }
      binaryPath = bs.get();
      removePath = true;
    }

    ObjectNode node = array.addObject();
    if (xmlId) {
      node.put(XML_ID, "BDO" + bdoCounter.incrementAndGet());
    }
    ifNotBlank(bdo.getBinaryVersion(), e -> node.put(DATA_OBJECT_VERSION, e));

    String sanitizedFileName = SipUtils.sanitizeFileName(binaryPath.getFileName().toString());
    String binaryFileName = idCounter.incrementAndGet() + "_" + sanitizedFileName;
    node.put(URI, CONTENT_DIR + "/" + binaryFileName);

    ObjectNode digest = node.putObject(MESSAGE_DIGEST);
    digest.put(ALGORITHM, bdo.getDigestAlgorithm());
    digest.put(VALUE, "");

    node.put(SIZE, 0L);

    // L'ordre des clés étant fixé à la construction de l'arbre, le FormatIdentification est
    // toujours inséré avant le FileInfo puis complété par la tâche d'identification
    FormatIdentification fmtId = bdo.getFormatIdentification();
    if (StringUtils.isNotBlank(fmtId.getFormatId())) {
      ObjectNode format = node.putObject(FORMAT_IDENTIFICATION);
      format.put(FORMAT_ID, fmtId.getFormatId());
      ifNotBlank(fmtId.getFormatName(), e -> format.put(FORMAT_LITTERAL, e));
      ifNotBlank(fmtId.getMimeType(), e -> format.put(MIME_TYPE, e));
    } else if (identifyFileFormat) {
      node.putObject(FORMAT_IDENTIFICATION);
    }

    FileInfo fileInfo = bdo.getFileInfo();
    if (fileInfo != null) {
      ObjectNode info = node.putObject(FILE_INFO);
      info.put(
          FILENAME,
          StringUtils.isBlank(fileInfo.getFilename())
              ? binaryPath.getFileName().toString()
              : fileInfo.getFilename());
      ifNotBlank(
          fileInfo.getCreatingApplicationName(), e -> info.put(CREATING_APPLICATION_NAME, e));
      ifNotBlank(
          fileInfo.getCreatingApplicationVersion(), e -> info.put(CREATING_APPLICATION_VERSION, e));
      ifNotBlank(fileInfo.getCreatingOs(), e -> info.put(CREATING_OS, e));
      ifNotBlank(fileInfo.getCreatingOsVersion(), e -> info.put(CREATING_OS_VERSION, e));
      ifNotNull(
          fileInfo.getDateCreatedByApplication(),
          e -> info.put(DATE_CREATED_BY_APPLICATION, toJsonDateTime(e)));
      ifNotNull(fileInfo.getLastModified(), e -> info.put(LAST_MODIFIED, toJsonDateTime(e)));
    }

    tasks.add(new JsonZipTask(binaryPath, binaryFileName, removePath, identifyFileFormat, node));
  }

  private void reject(String what) {
    String msg = "The SEDA JSON standard does not support " + what;
    if (isStrict) {
      throw new SipException(msg);
    }
    LOGGER.warn("{} - ignored", msg);
  }

  private static String toJsonDate(LocalDate date) {
    return date.toString();
  }

  private static String toJsonDateTime(LocalDateTime dateTime) {
    return dateTime.format(YYYYMMDD_HMS);
  }

  // Convertit un nom d'énumération (ex. SIGNED_DOCUMENT) en token XML SEDA (ex. SignedDocument)
  private static String toPascalCase(String enumName) {
    StringBuilder builder = new StringBuilder(enumName.length());
    for (String part : enumName.split("_")) {
      builder.append(part.charAt(0)).append(part.substring(1).toLowerCase());
    }
    return builder.toString();
  }

  private class JsonZipTask implements Callable<Void> {

    private final Path binaryPath;
    private final String binaryFileName;
    private final boolean removePath;
    private final boolean identifyFileFormat;
    private final ObjectNode node;

    JsonZipTask(
        Path binaryPath,
        String binaryFileName,
        boolean removePath,
        boolean identifyFileFormat,
        ObjectNode node) {
      this.binaryPath = binaryPath;
      this.binaryFileName = binaryFileName;
      this.removePath = removePath;
      this.identifyFileFormat = identifyFileFormat;
      this.node = node;
    }

    private Path zip(Path binaryPath, String entryName) throws IOException {
      Path docEntry = zipArchive.getPath(CONTENT_DIR);
      if (Files.notExists(docEntry)) {
        try {
          Files.createDirectories(docEntry);
        } catch (FileAlreadyExistsException ex) {
          LOGGER.warn("zip: ", ex);
        }
      }

      Path zipEntry = docEntry.resolve(entryName);
      try (OutputStream out = Files.newOutputStream(zipEntry, StandardOpenOption.CREATE_NEW)) {
        Files.copy(binaryPath, out);
      }
      return zipEntry;
    }

    @Override
    public Void call() {
      try {
        // Compute Digest
        ObjectNode digest = (ObjectNode) node.get(MESSAGE_DIGEST);
        digest.put(VALUE, SipUtils.digestHex(binaryPath, digest.get(ALGORITHM).asText()));

        // L'identification du format opère sur le fichier source car la librairie Droid ne
        // supporte pas les systèmes de fichiers NIO (zip, jimfs)
        processFileFormatIdentification();

        // Add binary file to zip
        if (zipArchive != null) {
          Path zipEntry = zip(binaryPath, binaryFileName);
          long size = (long) Files.getAttribute(zipEntry, "zip:size");
          node.put(SIZE, size);
        } else {
          node.put(SIZE, Files.size(binaryPath));
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
      JsonNode formatNode = node.get(FORMAT_IDENTIFICATION);
      if (!identifyFileFormat || formatNode == null || !formatNode.isEmpty()) {
        return;
      }

      ObjectNode format = (ObjectNode) formatNode;
      String ext = FilenameUtils.getExtension(binaryPath.getFileName().toString());
      List<IdentificationResult> results = DroidUtils.matchBinarySignatures(binaryPath, ext);
      if (results.isEmpty()) {
        format.put(FORMAT_ID, "Unknown");
      } else {
        IdentificationResult r = results.getFirst();
        format.put(FORMAT_ID, r.getPuid());
        if (!StringUtils.isAllBlank(r.getName(), r.getVersion())) {
          format.put(FORMAT_LITTERAL, StringUtils.trim(r.getName() + " " + r.getVersion()));
        }
        ifNotBlank(r.getMimeType(), e -> format.put(MIME_TYPE, e));
      }
    }
  }
}
