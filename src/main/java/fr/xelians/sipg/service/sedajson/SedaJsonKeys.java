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

/**
 * La classe SedaJsonKeys définit l'ensemble des clés du standard SEDA JSON. Les clés sont en
 * PascalCase et reprennent les noms des éléments SEDA XML. Cette classe est l'unique source de
 * vérité de l'orthographe des clés pour le convertisseur, le parser et le schéma.
 *
 * @author Emmanuel Deviller
 */
final class SedaJsonKeys {

  // Racine
  static final String SEDA_JSON_VERSION = "SedaJsonVersion";
  static final String MESSAGE_IDENTIFIER = "MessageIdentifier";
  static final String COMMENT = "Comment";
  static final String ARCHIVAL_AGREEMENT = "ArchivalAgreement";
  static final String ARCHIVE_UNITS = "ArchiveUnits";
  static final String MANAGEMENT_METADATA = "ManagementMetadata";
  static final String ARCHIVAL_AGENCY = "ArchivalAgency";
  static final String TRANSFERRING_AGENCY = "TransferringAgency";

  // ManagementMetadata
  static final String ARCHIVAL_PROFILE = "ArchivalProfile";
  static final String SERVICE_LEVEL = "ServiceLevel";
  static final String ACQUISITION_INFORMATION = "AcquisitionInformation";
  static final String LEGAL_STATUS = "LegalStatus";
  static final String ORIGINATING_AGENCY_IDENTIFIER = "OriginatingAgencyIdentifier";
  static final String SUBMISSION_AGENCY_IDENTIFIER = "SubmissionAgencyIdentifier";

  // Agence
  static final String IDENTIFIER = "Identifier";
  static final String ORGANIZATION_DESCRIPTIVE_METADATA = "OrganizationDescriptiveMetadata";
  static final String NAME = "Name";

  // Unité d'archive
  static final String XML_ID = "XmlId";
  static final String MANAGEMENT = "Management";
  static final String CONTENT = "Content";
  static final String BINARY_DATA_OBJECTS = "BinaryDataObjects";
  static final String PHYSICAL_DATA_OBJECTS = "PhysicalDataObjects";

  // Management
  static final String STORAGE_RULE = "StorageRule";
  static final String APPRAISAL_RULE = "AppraisalRule";
  static final String ACCESS_RULE = "AccessRule";
  static final String DISSEMINATION_RULE = "DisseminationRule";
  static final String REUSE_RULE = "ReuseRule";
  static final String CLASSIFICATION_RULE = "ClassificationRule";
  static final String LOG_BOOK = "LogBook";
  static final String NEED_AUTHORIZATION = "NeedAuthorization";
  static final String HOLD_RULE = "HoldRule";
  static final String UPDATE_OPERATION = "UpdateOperation";

  // Règles
  static final String RULES = "Rules";
  static final String RULE = "Rule";
  static final String START_DATE = "StartDate";
  static final String PREVENT_INHERITANCE = "PreventInheritance";
  static final String PREVENT_RULE_NAMES = "PreventRuleNames";
  static final String DURATION = "Duration";
  static final String FINAL_ACTION = "FinalAction";
  static final String CLASSIFICATION_LEVEL = "ClassificationLevel";
  static final String CLASSIFICATION_OWNER = "ClassificationOwner";
  static final String CLASSIFICATION_AUDIENCE = "ClassificationAudience";
  static final String CLASSIFICATION_REASSESSING_DATE = "ClassificationReassessingDate";
  static final String NEED_REASSESSING_AUTHORIZATION = "NeedReassessingAuthorization";
  static final String HOLD_END_DATE = "HoldEndDate";
  static final String HOLD_OWNER = "HoldOwner";
  static final String REASSESSING_DATE = "ReassessingDate";
  static final String HOLD_REASON = "HoldReason";
  static final String PREVENT_REARRANGEMENT = "PreventRearrangement";

  // UpdateOperation
  static final String SYSTEM_ID = "SystemId";
  static final String METADATA_NAME = "MetadataName";
  static final String METADATA_VALUE = "MetadataValue";

  // Événements
  static final String EVENT_IDENTIFIER = "EventIdentifier";
  static final String EVENT_TYPE_CODE = "EventTypeCode";
  static final String EVENT_TYPE = "EventType";
  static final String EVENT_DATE_TIME = "EventDateTime";
  static final String EVENT_DETAIL = "EventDetail";
  static final String OUTCOME = "Outcome";
  static final String OUTCOME_DETAIL = "OutcomeDetail";
  static final String OUTCOME_DETAIL_MESSAGE = "OutcomeDetailMessage";
  static final String EVENT_DETAIL_DATA = "EventDetailData";

  // Content (ordre du XSD SEDA)
  static final String DESCRIPTION_LEVEL = "DescriptionLevel";
  static final String TITLE = "Title";
  static final String TITLES = "Titles";
  static final String FILE_PLAN_POSITIONS = "FilePlanPositions";
  static final String SYSTEM_IDS = "SystemIds";
  static final String ORIGINATING_SYSTEM_IDS = "OriginatingSystemIds";
  static final String ARCHIVAL_AGENCY_ARCHIVE_UNIT_IDENTIFIERS =
      "ArchivalAgencyArchiveUnitIdentifiers";
  static final String ORIGINATING_AGENCY_ARCHIVE_UNIT_IDENTIFIERS =
      "OriginatingAgencyArchiveUnitIdentifiers";
  static final String TRANSFERRING_AGENCY_ARCHIVE_UNIT_IDENTIFIERS =
      "TransferringAgencyArchiveUnitIdentifiers";
  static final String DESCRIPTION = "Description";
  static final String DESCRIPTIONS = "Descriptions";
  static final String CUSTODIAL_HISTORY_ITEMS = "CustodialHistoryItems";
  static final String ARCHIVE_UNIT_PROFILE = "ArchiveUnitProfile";
  static final String TYPE = "Type";
  static final String DOCUMENT_TYPE = "DocumentType";
  static final String LANGUAGES = "Languages";
  static final String DESCRIPTION_LANGUAGE = "DescriptionLanguage";
  static final String STATUS = "Status";
  static final String VERSION = "Version";
  static final String TAGS = "Tags";
  static final String KEYWORDS = "Keywords";
  static final String ORIGINATING_AGENCY = "OriginatingAgency";
  static final String SUBMISSION_AGENCY = "SubmissionAgency";
  static final String AGENTS = "Agents";
  static final String AUTHORIZED_AGENTS = "AuthorizedAgents";
  static final String WRITERS = "Writers";
  static final String ADDRESSEES = "Addressees";
  static final String RECIPIENTS = "Recipients";
  static final String TRANSMITTERS = "Transmitters";
  static final String SENDERS = "Senders";
  static final String SOURCE = "Source";
  static final String RELATED_OBJECT_REFERENCE = "RelatedObjectReference";
  static final String CREATED_DATE = "CreatedDate";
  static final String TRANSACTED_DATE = "TransactedDate";
  static final String ACQUIRED_DATE = "AcquiredDate";
  static final String SENT_DATE = "SentDate";
  static final String RECEIVED_DATE = "ReceivedDate";
  static final String REGISTERED_DATE = "RegisteredDate";
  static final String END_DATE = "EndDate";
  static final String EVENTS = "Events";
  static final String SIGNATURES = "Signatures";
  static final String SIGNING_INFORMATION = "SigningInformation";
  static final String GPS = "Gps";

  // Texte multilingue
  static final String LANG = "Lang";
  static final String VALUE = "Value";

  // Mots-clés
  static final String KEYWORD_REFERENCE = "KeywordReference";
  static final String KEYWORD_CONTENT = "KeywordContent";

  // Historique de conservation
  static final String WHEN = "When";

  // Agents, signataires et valideurs
  static final String FIRST_NAME = "FirstName";
  static final String BIRTH_NAME = "BirthName";
  static final String FULL_NAME = "FullName";
  static final String GIVEN_NAME = "GivenName";
  static final String GENDER = "Gender";
  static final String BIRTH_DATE = "BirthDate";
  static final String BIRTH_PLACE = "BirthPlace";
  static final String DEATH_DATE = "DeathDate";
  static final String DEATH_PLACE = "DeathPlace";
  static final String NATIONALITIES = "Nationalities";
  static final String CORPNAME = "Corpname";
  static final String IDENTIFIERS = "Identifiers";
  static final String FUNCTIONS = "Functions";
  static final String ACTIVITIES = "Activities";
  static final String POSITIONS = "Positions";
  static final String ROLES = "Roles";
  static final String MANDATES = "Mandates";
  static final String SIGNING_TIME = "SigningTime";
  static final String VALIDATION_TIME = "ValidationTime";

  // Lieux
  static final String GEOGNAME = "Geogname";
  static final String ADDRESS = "Address";
  static final String POSTAL_CODE = "PostalCode";
  static final String CITY = "City";
  static final String REGION = "Region";
  static final String COUNTRY = "Country";

  // Relations
  static final String IS_PART_OFS = "IsPartOfs";
  static final String REFERENCES = "References";
  static final String REPLACES = "Replaces";
  static final String REQUIRES = "Requires";
  static final String IS_VERSION_OFS = "IsVersionOfs";
  static final String REPOSITORY_ARCHIVE_UNIT_PID = "RepositoryArchiveUnitPID";
  static final String REPOSITORY_OBJECT_PID = "RepositoryObjectPID";
  static final String EXTERNAL_REFERENCE = "ExternalReference";

  // Signatures
  static final String SIGNERS = "Signers";
  static final String VALIDATOR = "Validator";
  static final String REFERENCED_OBJECT = "ReferencedObject";
  static final String SIGNED_OBJECT_DIGEST = "SignedObjectDigest";

  // SigningInformation
  static final String SIGNING_ROLES = "SigningRoles";
  static final String DETACHED_SIGNING_ROLES = "DetachedSigningRoles";
  static final String SIGNED_DOCUMENT_REFERENCE_IDS = "SignedDocumentReferenceIds";
  static final String TIMESTAMPING_INFORMATIONS = "TimestampingInformations";
  static final String ADDITIONAL_PROOF = "AdditionalProof";
  static final String ADDITIONAL_PROOF_INFORMATION = "AdditionalProofInformation";
  static final String SIGNATURE_DESCRIPTIONS = "SignatureDescriptions";
  static final String TIME_STAMP = "TimeStamp";
  static final String ADDITIONAL_TIMESTAMPING_INFORMATION = "AdditionalTimestampingInformation";
  static final String SIGNER = "Signer";
  static final String SIGNING_TYPE = "SigningType";

  // GPS
  static final String GPS_VERSION_ID = "GpsVersionID";
  static final String GPS_ALTITUDE = "GpsAltitude";
  static final String GPS_ALTITUDE_REF = "GpsAltitudeRef";
  static final String GPS_LATITUDE = "GpsLatitude";
  static final String GPS_LATITUDE_REF = "GpsLatitudeRef";
  static final String GPS_LONGITUDE = "GpsLongitude";
  static final String GPS_LONGITUDE_REF = "GpsLongitudeRef";
  static final String GPS_DATE_STAMP = "GpsDateStamp";

  // Objets de données binaires
  static final String DATA_OBJECT_VERSION = "DataObjectVersion";
  static final String URI = "Uri";
  static final String MESSAGE_DIGEST = "MessageDigest";
  static final String ALGORITHM = "Algorithm";
  static final String SIZE = "Size";
  static final String FORMAT_IDENTIFICATION = "FormatIdentification";
  static final String FORMAT_ID = "FormatId";
  static final String FORMAT_LITTERAL = "FormatLitteral";
  static final String MIME_TYPE = "MimeType";
  static final String FILE_INFO = "FileInfo";
  static final String FILENAME = "Filename";
  static final String CREATING_APPLICATION_NAME = "CreatingApplicationName";
  static final String CREATING_APPLICATION_VERSION = "CreatingApplicationVersion";
  static final String CREATING_OS = "CreatingOs";
  static final String CREATING_OS_VERSION = "CreatingOsVersion";
  static final String DATE_CREATED_BY_APPLICATION = "DateCreatedByApplication";
  static final String LAST_MODIFIED = "LastModified";

  // Objets de données physiques
  static final String PHYSICAL_ID = "PhysicalId";
  static final String MEASURE = "Measure";

  // Divers
  static final String MANIFEST_JSON = "manifest.json";
  static final String CONTENT_DIR = "content";

  private SedaJsonKeys() {}
}
