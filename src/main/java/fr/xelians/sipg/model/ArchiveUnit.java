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
package fr.xelians.sipg.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.xelians.sipg.utils.SipException;
import fr.xelians.sipg.utils.SipUtils;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import org.apache.commons.lang3.Validate;

/**
 * La classe ArchiveUnit représente une unité d'archive qui contient la référence de l'objet
 * physique ou numérique à archiver, les métadonnées de description, les règles et les métadonnées
 * de gestion de l'objet. Une unité d'archive peut contenir d'autres unités d'archives et constituer
 * ainsi une arborescence. L'unité d'archive racine de l'arborescence est rattachée à un
 * ArchiveTransfer.
 *
 * <p>Supporté en SEDA v2.1 et FNTC v4.
 *
 * @author Emmanuel Deviller
 * @see ArchiveTransfer
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class ArchiveUnit implements ArchiveUnitContainer {

  public static final String PHYSICAL_MASTER = "PhysicalMaster";

  /**
   * La liste des évènements de l'unité d'archive. Un évènement correspond à toute opération
   * concernant l'unité d'archive : opération de versement, de mise à jour de métadonnées, de
   * préservation, etc.
   */
  protected final List<Event> logEvents = new ArrayList<>();

  /** La liste des intitulés de l'unité d'archive. */
  protected final List<Text> titles = new ArrayList<>();

  /** La liste des descriptions de l'unité d'archive. */
  protected final List<Text> descriptions = new ArrayList<>();

  /**
   * La liste des changements successifs de propriété, de responsabilité et de conservation des
   * unités d'archives avant leur entrée dans le lieu de conservation. On peut notamment y indiquer
   * comment s'est effectué le passage de l'application d'origine au fichier archivable. Correspond
   * à l'historique de la conservation en ISAD(G).
   */
  protected final List<CustodialItem> custodialItems = new ArrayList<>();

  /** La liste des langues du contenu des objets binaires ou physiques. */
  protected final List<String> languages = new ArrayList<>();

  /** La liste de mots-clés de description. */
  protected final List<Tag> tags = new ArrayList<>();

  /** La liste des agents. */
  protected final List<Agent> agents = new ArrayList<>();

  /** La liste des titulaires des droits de propriété intellectuelle. */
  protected final List<Agent> authorizedAgents = new ArrayList<>();

  /** La liste des rédacteurs de l’unité d'archive. */
  protected final List<Agent> writers = new ArrayList<>();

  /**
   * La liste des destinataires pour action. Utilisé pour indiquer le nom du destinataire par
   * exemple dans un courrier électronique.
   */
  protected final List<Agent> addressees = new ArrayList<>();

  /**
   * La liste des destinataires pour information. Utilisé pour indiquer le nom du destinataire en
   * copie, pour information, par exemple dans un courrier électronique.
   */
  protected final List<Agent> recipients = new ArrayList<>();

  /** La liste des émetteurs du message. */
  protected final List<Agent> transmitters = new ArrayList<>();

  /** La liste des expéditeurs du message. */
  protected final List<Agent> senders = new ArrayList<>();

  /** La liste des informations relatives à la signature. */
  protected final List<Signature> signatures = new ArrayList<>();

  /** La liste des éléments étendus qui n'appartiennent pas à l'ontolgie standard. */
  protected final List<Object> elements = new ArrayList<>();

  /** La liste d'autres unités d'archives contenues dans cette unité d'archive. */
  protected final List<ArchiveUnit> archiveUnits = new ArrayList<>();

  /** La liste des archives unites référencées par cette unité d'archive. */
  protected final List<ArchiveUnitRef> references = new ArrayList<>();

  /** L'identifiant unique de l'archive unit dans le document. */
  protected String id;

  /** L'identifiant de l'objet physique. */
  protected String physicalId;

  /** La quantité ou taille de l'objet physique. */
  protected double measure;

  /** La version de l'objet physique. */
  protected String physicalVersion;

  /** L'objet binaire de type binary master. */
  protected BinaryMasterDataObject binaryMasterDataObject;

  /** L'objet binaire de type dissemination (publication). */
  protected DisseminationDataObject disseminationDataObject;

  /** L'objet binaire de type thumbnail (vignette). */
  protected ThumbnailDataObject thumbnailDataObject;

  /** L'objet binaire de type text content (contenu texte). */
  protected TextContentDataObject textContentDataObject;

  /** Le statut de la signature de l'objet binaire. */
  protected String signatureStatus;

  /** Le profile d’archivage applicable à l'unité d'archive. */
  protected String archiveUnitProfile;

  /** L'identifiant du service producteur. */
  protected String originatingAgencyIdentifier;

  /** L'identifiant du service versant. */
  protected String submissionAgencyIdentifier;

  /**
   * Indique si une autorisation humaine est nécessaire pour vérifier ou valider les opérations de
   * gestion des ArchiveUnit.
   */
  protected Boolean needAuthorization;

  /** Les règles de communicabilité (accessibilité). */
  protected AccessRules accessRules;

  /** Les règles de durée d’utilité administrative. */
  protected AppraisalRules appraisalRules;

  /** Les règles de diffusion. */
  protected DisseminationRules disseminationRules;

  /** Les règles de réutilisation. */
  protected ReuseRules reuseRules;

  /** Les règles de classification. */
  protected ClassificationRules classificationRules;

  /** Les règles de durée d'utilité courante. */
  protected StorageRules storageRules;

  /** Les règles de gel. */
  protected HoldRules holdRules;

  /** L'opération de mise à jour. */
  protected UpdateOperation updateOperation;

  /**
   * Le niveau de description au sens de la norme ISAD (G). Indique si l’unité d'archive correspond
   * à un fonds, à un sous-fonds, à une classe, à une série organique, à une sous-série organique, à
   * un dossier, à un sous-dossier ou à une pièce.
   */
  protected String descriptionLevel;

  /** La position de l’unité d'archive dans le plan de classement du service producteur. */
  protected final List<String> filePlanPositions = new ArrayList<>();

  /**
   * L'identifiant attribué aux objets. Il est attribué par le SAE et correspond à un identifiant
   * interne.
   */
  protected final List<String> systemIds = new ArrayList<>();

  /**
   * L'identifiant attribué aux objets de données. Il est attribué par le SAE et correspond à un
   * identifiant interne.
   */
  protected final List<String> dataObjectSystemIds = new ArrayList<>();

  /** L'identifiant système attribué à l’ArchiveUnit par l’application du service producteur. */
  protected final List<String> originatingSystemIds = new ArrayList<>();

  /**
   * L'identifiant métier attribué à l'unité d'archive par le service d'archives. Peut être comparé
   * à une cote.
   */
  protected final List<String> archivalAgencyArchiveUnitIdentifiers = new ArrayList<>();

  /** L'identifiant métier attribué à l’ArchiveUnit par le service producteur. */
  protected final List<String> originatingAgencyArchiveUnitIdentifiers = new ArrayList<>();

  /** L'identifiant attribué à l'ArchiveUnit par le service versant. */
  protected final List<String> transferringAgencyArchiveUnitIdentifiers = new ArrayList<>();

  /**
   * Le type d’information au sens de l’OAIS (information de représentation, information de
   * pérennisation, etc.).
   */
  protected String type;

  /**
   * Le type de document au sens diplomatique du terme (ex. compte rendu de réunion, note,
   * correspondance, etc.). Attention à ne pas confondre avec le Type.
   */
  protected String documentType;

  /** La langue utilisée pour les métadonnées de description. */
  protected String descriptionLanguage;

  /**
   * L'état de l'objet binaire ou physique (par rapport avec son cycle de vie). L'état permet par
   * exemple d'indiquer si la signature du fichier a été vérifiée avant le transfert aux archives.
   */
  protected String status;

  /** La version du document. */
  protected String version;

  /**
   * Le service producteur. Il s'agit d'une personne physique ou morale, publique ou privée, qui a
   * produit, reçu et conservé des archives dans l'exercice de son activité.
   */
  protected Agency originatingAgency;

  /** Le service versant responsable du transfert des données. */
  protected Agency submissionAgency;

  /** La référence à la source papier originale. */
  protected String source;

  /** La référence à un objet faisant ou ne faisant pas partie du présent paquet d'information. */
  // TODO: implement deserialization for RelatedObjectRef (serialization is done)
  @JsonIgnore protected RelatedObjectRef relation;

  /** L'identifiant de la version du GPS. */
  protected String gpsVersionID;

  /** L'altitude basée sur la référence dans GPSAltitudeRef. L'altitude est exprimée en mètres. */
  protected String gpsAltitude;

  /**
   * L'altitude utilisée comme altitude de référence. Si l'altitude est au-dessus du niveau de la
   * mer, la valeur 0 est normalement donnée. Si l'altitude est au-dessous du niveau de la mer, la
   * valeur 1 est donnée.
   */
  protected String gpsAltitudeRef;

  /**
   * La latitude qui peut être exprimée de deux manières différentes : degrés, décimaux ou degrés,
   * minutes et secondes. Si la latitude est exprimée en degrés, décimaux, le format type est dd,
   * dd. Par exemple : "45.3130339". Si la latitude est exprimée en degrés, minutes et secondes, le
   * format type est dd, mm, ss. Par exemple : "45 18 46.922".
   */
  protected String gpsLatitude;

  /**
   * Indique la latitude. La valeur 'N' indique la latitude nord, et 'S' indique la latitude sud.
   */
  protected String gpsLatitudeRef;

  /**
   * La longitude peut être exprimée de deux manières différentes : degrés, décimaux ou degrés,
   * minutes et secondes. Si la longitude est exprimée en degrés, décimaux, le format type est dd,
   * dd. Par exemple : "5.392285833333334". Si la longitude est exprimée en degrés, minutes et
   * secondes, le format type est dd, mm, ss. Par exemple : "5 23 32.229".
   */
  protected String gpsLongitude;

  /**
   * Indique la longitude. La valeur 'E' indique la longitude EST, et 'W' indique la longitude
   * OUEST.
   */
  protected String gpsLongitudeRef;

  /** L'heure et la date de la position GPS. */
  protected String gpsDateStamp;

  /** La date de création. */
  protected LocalDate createdDate;

  /** La date de la transaction. */
  protected LocalDate transactedDate;

  /** La date de numérisation. */
  protected LocalDate acquiredDate;

  /** La date d'envoi. */
  protected LocalDate sentDate;

  /** La date de réception. */
  protected LocalDate receivedDate;

  /** La date d'enregistrement. */
  protected LocalDate registeredDate;

  /** La date d'ouverture / date de début. */
  protected LocalDate startDate;

  /** La date de fermeture / Date de fin. */
  protected LocalDate endDate;

  /** Les informations relatives à la signature. */
  protected SigningInformation signingInformation;

  /** Instancie la classe. */
  public ArchiveUnit() {
    // Do nothing here
  }

  /**
   * Indique l'identifiant de l'objet physique.
   *
   * @return l'identifiant de l'objet physique
   */
  public String getPhysicalId() {
    return physicalId;
  }

  /**
   * Spécifie l'identifiant de l'objet physique.
   *
   * @param physicalId l'identifiant de l'objet physique
   */
  public void setPhysicalId(String physicalId) {
    this.physicalId = physicalId;
  }

  /**
   * Indique la quantité ou taille de l'objet physique.
   *
   * @return la quantité ou taille de l'objet physique
   */
  public double getMeasure() {
    return measure;
  }

  /**
   * Spécifie la quantité ou taille de l'objet physique.
   *
   * @param measure la quantité ou taille de l'objet physique
   */
  public void setMeasure(double measure) {
    this.measure = measure;
  }

  /**
   * Indique l'objet binaire de type binary master.
   *
   * @return l'objet binaire
   */
  public BinaryDataObject getBinaryMasterDataObject() {
    return binaryMasterDataObject;
  }

  /**
   * Indique l'objet binaire de type dissemination.
   *
   * @return l'objet binaire
   */
  public BinaryDataObject getDisseminationDataObject() {
    return disseminationDataObject;
  }

  /**
   * Indique l'objet binaire de type thumbnail.
   *
   * @return l'objet binaire
   */
  public BinaryDataObject getThumbnailDataObject() {
    return thumbnailDataObject;
  }

  /**
   * Indique l'objet binaire de type text content.
   *
   * @return l'objet binaire
   */
  public BinaryDataObject getTextContentDataObject() {
    return textContentDataObject;
  }

  /**
   * Fournit l'objet binaire de type "qualifier". Si l'objet n'existe pas, il est alors créé.
   *
   * @return l'objet binaire
   */
  public BinaryDataObject provideBinaryDataObject(String qualifier) {
    return switch (qualifier) {
      case BinaryDataObject.BINARY_MASTER -> provideBinaryMasterDataObject();
      case BinaryDataObject.DISSEMINATION -> provideDisseminationDataObject();
      case BinaryDataObject.THUMBNAIL -> provideThumbnailDataObject();
      case BinaryDataObject.TEXT_CONTENT -> provideTextContentDataObject();
      default -> throw new SipException(String.format("Unknown qualifier %s", qualifier));
    };
  }

  /**
   * Indique le fournisseur du path de l'objet binaire de type binary master.
   *
   * @return le fournisseur du path de l'objet binaire
   */
  @JsonIgnore
  public Supplier<Path> getBinaryPathSupplier() {
    return binaryMasterDataObject == null ? null : binaryMasterDataObject.getBinaryPathSupplier();
  }

  private BinaryMasterDataObject provideBinaryMasterDataObject() {
    if (binaryMasterDataObject == null) {
      binaryMasterDataObject = new BinaryMasterDataObject();
    }
    return binaryMasterDataObject;
  }

  /**
   * Spécifie le fournisseur du path de l'objet binaire de type binary master.
   *
   * @param binaryPathSupplier le fournisseur du path de l'objet binaire
   */
  public void setBinaryPathSupplier(Supplier<Path> binaryPathSupplier) {
    provideBinaryMasterDataObject().setBinaryPathSupplier(binaryPathSupplier);
  }

  /**
   * Indique le path de l'objet binaire de type binary master.
   *
   * @return le path de l'objet binaire
   */
  public Path getBinaryPath() {
    return binaryMasterDataObject == null ? null : binaryMasterDataObject.getBinaryPath();
  }

  /**
   * Spécifie le path de l'objet binaire de type binary master.
   *
   * @param binaryPath le path de l'objet binaire
   */
  public void setBinaryPath(Path binaryPath) {
    provideBinaryMasterDataObject().setBinaryPath(binaryPath);
  }

  /**
   * Indique la version de l'objet binaire de type binary master.
   *
   * @return la version de l'objet binaire
   */
  public String getBinaryVersion() {
    return binaryMasterDataObject == null ? null : binaryMasterDataObject.getBinaryVersion();
  }

  /**
   * Spécifie la version de l'objet binaire de type binary master.
   *
   * @param binaryVersion la version de l'objet binaire
   */
  public void setBinaryVersion(String binaryVersion) {
    provideBinaryMasterDataObject().setBinaryVersion(binaryVersion);
  }

  /**
   * Indique l'algorithme utilisé pour générer l'empreinte (hash) de l'objet binaire de type binary
   * master.
   *
   * @return l'algorithme utilisé
   */
  public String getDigestAlgorithm() {
    return binaryMasterDataObject == null ? null : binaryMasterDataObject.getDigestAlgorithm();
  }

  /**
   * Indique les informations techniques de l'objet binaire de type binary master.
   *
   * @return les informations techniques
   */
  public FileInfo getFileInfo() {
    return binaryMasterDataObject == null ? null : binaryMasterDataObject.getFileInfo();
  }

  /**
   * Spécifie les informations techniques de l'objet binaire de type binary master.
   *
   * @param fileInfo les informations techniques
   */
  public void setFileInfo(FileInfo fileInfo) {
    provideBinaryMasterDataObject().setFileInfo(fileInfo);
  }

  /**
   * Indique l'identifiant du format de l'objet binaire de type binary master. Il est fortement
   * conseillé d'identifier le format de l'objet binaire selon le référentiel Pronom édité par The
   * British National Archives.
   *
   * @return l 'identifiant du format de l'objet binaire
   */
  public String getFormatId() {
    return binaryMasterDataObject == null
        ? null
        : binaryMasterDataObject.getFormatIdentification().getFormatId();
  }

  /**
   * Spécifie l'identifiant du format de l'objet binaire de type binary master. Il est fortement
   * conseillé d'identifier le format de l'objet binaire selon le référentiel Pronom édité par The
   * British National Archives.
   *
   * @param formatId l'identifiant du format de l'objet binaire
   */
  public void setFormatId(String formatId) {
    provideBinaryMasterDataObject().getFormatIdentification().setFormatId(formatId);
  }

  /**
   * Indique le nom du format de l'objet binaire de type binary master. Il est fortement conseillé
   * de nommer le format de l'objet binaire selon le référentiel Pronom édité par The British
   * National Archives.
   *
   * @return le nom du format de l'objet binaire
   */
  public String getFormatName() {
    return binaryMasterDataObject == null
        ? null
        : binaryMasterDataObject.getFormatIdentification().getFormatName();
  }

  /**
   * Spécifie le nom du format de l'objet binaire de type binary master. Il est fortement conseillé
   * de nommer le format de l'objet binaire selon le référentiel Pronom édite par The British
   * National Archives.
   *
   * @param formatName le nom du format de l'objet binaire
   */
  public void setFormatName(String formatName) {
    provideBinaryMasterDataObject().getFormatIdentification().setFormatName(formatName);
  }

  /**
   * Indique le type MIME (Multipurpose Internet Mail Extensions) de l'objet binaire de type binary
   * master.
   *
   * @return le type MIME
   */
  public String getMimeType() {
    return binaryMasterDataObject == null
        ? null
        : binaryMasterDataObject.getFormatIdentification().getMimeType();
  }

  /**
   * Spécifie le type MIME (Multipurpose Internet Mail Extensions) de l'objet binaire de type binary
   * master.
   *
   * @param mimeType le type MIME
   */
  public void setMimeType(String mimeType) {
    provideBinaryMasterDataObject().getFormatIdentification().setMimeType(mimeType);
  }

  private DisseminationDataObject provideDisseminationDataObject() {
    if (disseminationDataObject == null) {
      disseminationDataObject = new DisseminationDataObject();
    }
    return disseminationDataObject;
  }

  /**
   * Indique le fournisseur du path de l'objet binaire de type dissemination (publication).
   *
   * @return le fournisseur du path de l'objet binaire
   */
  @JsonIgnore
  public Supplier<Path> getDisseminationPathSupplier() {
    return disseminationDataObject == null ? null : disseminationDataObject.getBinaryPathSupplier();
  }

  /**
   * Spécifie le fournisseur du path de l'objet binaire de type dissemination (publication).
   *
   * @param binaryPathSupplier le fournisseur du path de l'objet binaire
   */
  public void setDisseminationPathSupplier(Supplier<Path> binaryPathSupplier) {
    provideDisseminationDataObject().setBinaryPathSupplier(binaryPathSupplier);
  }

  /**
   * Indique le path de l'objet binaire de type dissemination (publication).
   *
   * @return le path de l'objet binaire
   */
  public Path getDisseminationPath() {
    return disseminationDataObject == null ? null : disseminationDataObject.getBinaryPath();
  }

  /**
   * Spécifie le path de l'objet binaire de type dissemination (publication).
   *
   * @param binaryPath le path de l'objet binaire
   */
  public void setDisseminationPath(Path binaryPath) {
    provideDisseminationDataObject().setBinaryPath(binaryPath);
  }

  /**
   * Indique la version de l'objet binaire de type dissemination (publication).
   *
   * @return la version de l'objet binaire
   */
  public String getDisseminationVersion() {
    return disseminationDataObject == null ? null : disseminationDataObject.getBinaryVersion();
  }

  /**
   * Spécifie la version de l'objet binaire de type dissemination (publication).
   *
   * @param binaryVersion la version de l'objet binaire
   */
  public void setDisseminationVersion(String binaryVersion) {
    provideDisseminationDataObject().setBinaryVersion(binaryVersion);
  }

  /**
   * Indique l'algorithme utilisé pour générer l'empreinte (hash) de l'objet binaire de type
   * dissemination (publication).
   *
   * @return l'algorithme utilisé
   */
  public String getDisseminationDigestAlgorithm() {
    return disseminationDataObject == null ? null : disseminationDataObject.getDigestAlgorithm();
  }

  /**
   * Indique les informations techniques de l'objet binaire de type dissemination (publication).
   *
   * @return les informations techniques
   */
  public FileInfo getDisseminationFileInfo() {
    return disseminationDataObject == null ? null : disseminationDataObject.getFileInfo();
  }

  /**
   * Spécifie les informations techniques de l'objet binaire de type dissemination (publication).
   *
   * @param fileInfo les informations techniques
   */
  public void setDisseminationFileInfo(FileInfo fileInfo) {
    provideDisseminationDataObject().setFileInfo(fileInfo);
  }

  /**
   * Indique l'identifiant du format de l'objet binaire de type dissemination (publication). Il est
   * fortement conseillé d'identifier le format de l'objet binaire selon le référentiel Pronom édité
   * par The British National Archives.
   *
   * @return l 'identifiant du format de l'objet binaire
   */
  public String getDisseminationFormatId() {
    return disseminationDataObject == null
        ? null
        : disseminationDataObject.getFormatIdentification().getFormatId();
  }

  /**
   * Spécifie l'identifiant du format de l'objet binaire de type dissemination (publication). Il est
   * fortement conseillé d'identifier le format de l'objet binaire selon le référentiel Pronom édité
   * par The British National Archives.
   *
   * @param formatId l'identifiant du format de l'objet binaire
   */
  public void setDisseminationFormatId(String formatId) {
    provideDisseminationDataObject().getFormatIdentification().setFormatId(formatId);
  }

  /**
   * Indique le nom du format de l'objet binaire de type dissemination (publication). Il est
   * fortement conseillé de nommer le format de l'objet binaire selon le référentiel Pronom édité
   * par The British National Archives.
   *
   * @return le nom du format de l'objet binaire
   */
  public String getDisseminationFormatName() {
    return disseminationDataObject == null
        ? null
        : disseminationDataObject.getFormatIdentification().getFormatName();
  }

  /**
   * Spécifie le nom du format de l'objet binaire de type dissemination (publication). Il est
   * fortement conseillé de nommer le format de l'objet binaire selon le référentiel Pronom édite
   * par The British National Archives.
   *
   * @param formatName le nom du format de l'objet binaire
   */
  public void setDisseminationFormatName(String formatName) {
    provideDisseminationDataObject().getFormatIdentification().setFormatName(formatName);
  }

  /**
   * Indique le type MIME (Multipurpose Internet Mail Extensions) de l'objet binaire de type
   * dissemination (publication).
   *
   * @return le type MIME
   */
  public String getDisseminationMimeType() {
    return disseminationDataObject == null
        ? null
        : disseminationDataObject.getFormatIdentification().getMimeType();
  }

  /**
   * Spécifie le type MIME (Multipurpose Internet Mail Extensions) de l'objet binaire de type
   * dissemination (publication).
   *
   * @param mimeType le type MIME
   */
  public void setDisseminationMimeType(String mimeType) {
    provideDisseminationDataObject().getFormatIdentification().setMimeType(mimeType);
  }

  private ThumbnailDataObject provideThumbnailDataObject() {
    if (thumbnailDataObject == null) {
      thumbnailDataObject = new ThumbnailDataObject();
    }
    return thumbnailDataObject;
  }

  /**
   * Indique le fournisseur du path de l'objet binaire de type thumbnail (vignette).
   *
   * @return le fournisseur du path de l'objet binaire
   */
  @JsonIgnore
  public Supplier<Path> getThumbnailPathSupplier() {
    return thumbnailDataObject == null ? null : thumbnailDataObject.getBinaryPathSupplier();
  }

  /**
   * Spécifie le fournisseur du path de l'objet binaire de type thumbnail (vignette).
   *
   * @param binaryPathSupplier le fournisseur du path de l'objet binaire
   */
  public void setThumbnailPathSupplier(Supplier<Path> binaryPathSupplier) {
    provideThumbnailDataObject().setBinaryPathSupplier(binaryPathSupplier);
  }

  /**
   * Indique le path de l'objet binaire de type thumbnail (vignette).
   *
   * @return le path de l'objet binaire
   */
  public Path getThumbnailPath() {
    return thumbnailDataObject == null ? null : thumbnailDataObject.getBinaryPath();
  }

  /**
   * Spécifie le path de l'objet binaire de type thumbnail (vignette).
   *
   * @param binaryPath le path de l'objet binaire
   */
  public void setThumbnailPath(Path binaryPath) {
    provideThumbnailDataObject().setBinaryPath(binaryPath);
  }

  /**
   * Indique la version de l'objet binaire de type thumbnail (vignette).
   *
   * @return la version de l'objet binaire
   */
  public String getThumbnailVersion() {
    return thumbnailDataObject == null ? null : thumbnailDataObject.getBinaryVersion();
  }

  /**
   * Spécifie la version de l'objet binaire de type thumbnail (vignette).
   *
   * @param binaryVersion la version de l'objet binaire
   */
  public void setThumbnailVersion(String binaryVersion) {
    provideThumbnailDataObject().setBinaryVersion(binaryVersion);
  }

  /**
   * Indique l'algorithme utilisé pour générer l'empreinte (hash) de l'objet binaire de type
   * thumbnail (vignette).
   *
   * @return l'algorithme utilisé
   */
  public String getThumbnailDigestAlgorithm() {
    return thumbnailDataObject == null ? null : thumbnailDataObject.getDigestAlgorithm();
  }

  /**
   * Indique les informations techniques de l'objet binaire de type thumbnail (vignette).
   *
   * @return les informations techniques
   */
  public FileInfo getThumbnailFileInfo() {
    return thumbnailDataObject == null ? null : thumbnailDataObject.getFileInfo();
  }

  /**
   * Spécifie les informations techniques de l'objet binaire de type thumbnail (vignette).
   *
   * @param fileInfo les informations techniques
   */
  public void setThumbnailFileInfo(FileInfo fileInfo) {
    provideThumbnailDataObject().setFileInfo(fileInfo);
  }

  /**
   * Indique l'identifiant du format de l'objet binaire de type thumbnail (vignette). Il est
   * fortement conseillé d'identifier le format de l'objet binaire selon le référentiel Pronom édité
   * par The British National Archives.
   *
   * @return l 'identifiant du format de l'objet binaire
   */
  public String getThumbnailFormatId() {
    return thumbnailDataObject == null
        ? null
        : thumbnailDataObject.getFormatIdentification().getFormatId();
  }

  /**
   * Spécifie l'identifiant du format de l'objet binaire de type thumbnail (vignette). Il est
   * fortement conseillé d'identifier le format de l'objet binaire selon le référentiel Pronom édité
   * par The British National Archives.
   *
   * @param formatId l'identifiant du format de l'objet binaire
   */
  public void setThumbnailFormatId(String formatId) {
    provideThumbnailDataObject().getFormatIdentification().setFormatId(formatId);
  }

  /**
   * Indique le nom du format de l'objet binaire de type thumbnail (vignette). Il est fortement
   * conseillé de nommer le format de l'objet binaire selon le référentiel Pronom édité par The
   * British National Archives.
   *
   * @return le nom du format de l'objet binaire
   */
  public String getThumbnailFormatName() {
    return thumbnailDataObject == null
        ? null
        : thumbnailDataObject.getFormatIdentification().getFormatName();
  }

  /**
   * Spécifie le nom du format de l'objet binaire de type thumbnail (vignette). Il est fortement
   * conseillé de nommer le format de l'objet binaire selon le référentiel Pronom édite par The
   * British National Archives.
   *
   * @param formatName le nom du format de l'objet binaire
   */
  public void setThumbnailFormatName(String formatName) {
    provideThumbnailDataObject().getFormatIdentification().setFormatName(formatName);
  }

  /**
   * Indique le type MIME (Multipurpose Internet Mail Extensions) de l'objet binaire de type
   * thumbnail (vignette).
   *
   * @return le type MIME
   */
  public String getThumbnailMimeType() {
    return thumbnailDataObject == null
        ? null
        : thumbnailDataObject.getFormatIdentification().getMimeType();
  }

  /**
   * Spécifie le type MIME (Multipurpose Internet Mail Extensions) de l'objet binaire de type
   * thumbnail (vignette).
   *
   * @param mimeType le type MIME
   */
  public void setThumbnailMimeType(String mimeType) {
    provideThumbnailDataObject().getFormatIdentification().setMimeType(mimeType);
  }

  private TextContentDataObject provideTextContentDataObject() {
    if (textContentDataObject == null) {
      textContentDataObject = new TextContentDataObject();
    }
    return textContentDataObject;
  }

  /**
   * Indique le fournisseur du path de l'objet binaire de type text content (contenu texte).
   *
   * @return le fournisseur du path de l'objet binaire
   */
  @JsonIgnore
  public Supplier<Path> getTextContentPathSupplier() {
    return textContentDataObject == null ? null : textContentDataObject.getBinaryPathSupplier();
  }

  /**
   * Spécifie le fournisseur du path de l'objet binaire de type text content (contenu texte).
   *
   * @param binaryPathSupplier le fournisseur du path de l'objet binaire
   */
  public void setTextContentPathSupplier(Supplier<Path> binaryPathSupplier) {
    provideTextContentDataObject().setBinaryPathSupplier(binaryPathSupplier);
  }

  /**
   * Indique le path de l'objet binaire de type text content (contenu texte).
   *
   * @return le path de l'objet binaire
   */
  public Path getTextContentPath() {
    return textContentDataObject == null ? null : textContentDataObject.getBinaryPath();
  }

  /**
   * Spécifie le path de l'objet binaire de type text content (contenu texte).
   *
   * @param binaryPath le path de l'objet binaire
   */
  public void setTextContentPath(Path binaryPath) {
    provideTextContentDataObject().setBinaryPath(binaryPath);
  }

  /**
   * Indique la version de l'objet binaire de type text content (contenu texte).
   *
   * @return la version de l'objet binaire
   */
  public String getTextContentVersion() {
    return textContentDataObject == null ? null : textContentDataObject.getBinaryVersion();
  }

  /**
   * Spécifie la version de l'objet binaire de type text content (contenu texte).
   *
   * @param binaryVersion la version de l'objet binaire
   */
  public void setTextContentVersion(String binaryVersion) {
    provideTextContentDataObject().setBinaryVersion(binaryVersion);
  }

  /**
   * Indique l'algorithme utilisé pour générer l'empreinte (hash) de l'objet binaire de type text
   * content (contenu texte).
   *
   * @return l'algorithme utilisé
   */
  public String getTextContentDigestAlgorithm() {
    return textContentDataObject == null ? null : textContentDataObject.getDigestAlgorithm();
  }

  /**
   * Indique les informations techniques de l'objet binaire de type text content (contenu texte).
   *
   * @return les informations techniques
   */
  public FileInfo getTextContentFileInfo() {
    return textContentDataObject == null ? null : textContentDataObject.getFileInfo();
  }

  /**
   * Spécifie les informations techniques de l'objet binaire de type text content (contenu texte).
   *
   * @param fileInfo les informations techniques
   */
  public void setTextContentFileInfo(FileInfo fileInfo) {
    provideTextContentDataObject().setFileInfo(fileInfo);
  }

  /**
   * Indique l'identifiant du format de l'objet binaire de type text content (contenu texte). Il est
   * fortement conseillé d'identifier le format de l'objet binaire selon le référentiel Pronom édité
   * par The British National Archives.
   *
   * @return l 'identifiant du format de l'objet binaire
   */
  public String getTextContentFormatId() {
    return textContentDataObject == null
        ? null
        : textContentDataObject.getFormatIdentification().getFormatId();
  }

  /**
   * Spécifie l'identifiant du format de l'objet binaire de type text content (contenu texte). Il
   * est fortement conseillé d'identifier le format de l'objet binaire selon le référentiel Pronom
   * édité par The British National Archives.
   *
   * @param formatId l'identifiant du format de l'objet binaire
   */
  public void setTextContentFormatId(String formatId) {
    provideTextContentDataObject().getFormatIdentification().setFormatId(formatId);
  }

  /**
   * Indique le nom du format de l'objet binaire de type text content (contenu texte). Il est
   * fortement conseillé de nommer le format de l'objet binaire selon le référentiel Pronom édité
   * par The British National Archives.
   *
   * @return le nom du format de l'objet binaire
   */
  public String getTextContentFormatName() {
    return textContentDataObject == null
        ? null
        : textContentDataObject.getFormatIdentification().getFormatName();
  }

  /**
   * Spécifie le nom du format de l'objet binaire de type text content (contenu texte). Il est
   * fortement conseillé de nommer le format de l'objet binaire selon le référentiel Pronom édite
   * par The British National Archives.
   *
   * @param formatName le nom du format de l'objet binaire
   */
  public void setTextContentFormatName(String formatName) {
    provideTextContentDataObject().getFormatIdentification().setFormatName(formatName);
  }

  /**
   * Indique le type MIME (Multipurpose Internet Mail Extensions) de l'objet binaire de type text
   * content (contenu texte).
   *
   * @return le type MIME
   */
  public String getTextContentMimeType() {
    return textContentDataObject == null
        ? null
        : textContentDataObject.getFormatIdentification().getMimeType();
  }

  /**
   * Spécifie le type MIME (Multipurpose Internet Mail Extensions) de l'objet binaire de type text
   * content (contenu texte).
   *
   * @param mimeType le type MIME
   */
  public void setTextContentMimeType(String mimeType) {
    provideTextContentDataObject().getFormatIdentification().setMimeType(mimeType);
  }

  /**
   * Indique la version de l'objet physique.
   *
   * @return la version de l'objet physique
   */
  public String getPhysicalVersion() {
    return physicalVersion;
  }

  /**
   * Spécifie la version de l'objet physique.
   *
   * @param physicalVersion la version de l'objet physique
   */
  public void setPhysicalVersion(String physicalVersion) {
    this.physicalVersion = physicalVersion;
  }

  /**
   * Indique le statut de la signature de l'objet binaire.
   *
   * @return le statut de la signature de l'objet binaire
   */
  public String getSignatureStatus() {
    return signatureStatus;
  }

  /**
   * Spécifie le statut de la signature de l'objet binaire.
   *
   * @param signatureStatus le statut de la signature de l'objet binaire
   */
  public void setSignatureStatus(String signatureStatus) {
    this.signatureStatus = signatureStatus;
  }

  /**
   * Indique le profile d’archivage applicable à l'unité d'archive.
   *
   * @return le profile d’archivage
   */
  public String getArchiveUnitProfile() {
    return archiveUnitProfile;
  }

  /**
   * Spécifie le profile d’archivage applicable à l'unité d'archive.
   *
   * @param achiveUnitProfile le profile d’archivage
   */
  public void setArchiveUnitProfile(String achiveUnitProfile) {
    this.archiveUnitProfile = achiveUnitProfile;
  }

  /**
   * Indique les règles de communicabilité (accessibilité).
   *
   * @return les règles de communicabilité
   */
  public AccessRules getAccessRules() {
    return accessRules;
  }

  /**
   * Spécifie les règles de communicabilité (accessibilité).
   *
   * @param accessRules les règles de communicabilité
   */
  public void setAccessRules(AccessRules accessRules) {
    this.accessRules = accessRules;
  }

  /**
   * Spécifie les règles de communicabilité (accessibilité).
   *
   * @param ruleName le nom de la règle
   * @param startDate la date de début
   */
  public void setAccessRules(String ruleName, LocalDate startDate) {
    this.accessRules = new AccessRules(ruleName, startDate);
  }

  /**
   * Indique les règles de diffusion.
   *
   * @return les règles de diffusion
   */
  public DisseminationRules getDisseminationRules() {
    return disseminationRules;
  }

  /**
   * Spécifie les règles de diffusion.
   *
   * @param disseminationRules les règles de diffusion.
   */
  public void setDisseminationRules(DisseminationRules disseminationRules) {
    this.disseminationRules = disseminationRules;
  }

  /**
   * Spécifie les règles de diffusion.
   *
   * @param ruleName le nom de la règle
   * @param startDate la date de début
   */
  public void setDisseminationRule(String ruleName, LocalDate startDate) {
    this.disseminationRules = new DisseminationRules(ruleName, startDate);
  }

  /**
   * Indique les règles de classification.
   *
   * @return les règles de classification
   */
  public ClassificationRules getClassificationRules() {
    return classificationRules;
  }

  /**
   * Spécifie les règles de classification.
   *
   * @param classificationRules les règles de classification
   */
  public void setClassificationRules(ClassificationRules classificationRules) {
    this.classificationRules = classificationRules;
  }

  /**
   * Spécifie les règles de classification.
   *
   * @param ruleName le nom de la règle
   * @param startDate la date de début
   */
  public void setClassificationRules(String ruleName, LocalDate startDate) {
    this.classificationRules = new ClassificationRules(ruleName, startDate);
  }

  /**
   * Indique les règles de réutilisation.
   *
   * @return les règles de réutilisation
   */
  public ReuseRules getReuseRules() {
    return reuseRules;
  }

  /**
   * Spécifie les règles de réutilisation.
   *
   * @param reuseRules les règles de réutilisation
   */
  public void setReuseRules(ReuseRules reuseRules) {
    this.reuseRules = reuseRules;
  }

  /**
   * Spécifie les règles de réutilisation.
   *
   * @param ruleName le nom de la règle
   * @param startDate la date de début
   */
  public void setReuseRules(String ruleName, LocalDate startDate) {
    this.reuseRules = new ReuseRules(ruleName, startDate);
  }

  /**
   * Indique les règles de durée d’utilité administrative.
   *
   * @return les règles de durée d’utilité administrative
   */
  public AppraisalRules getAppraisalRules() {
    return appraisalRules;
  }

  /**
   * Spécifie les règles de durée d’utilité administrative.
   *
   * @param appraisalRules les règles de durée d’utilité administrative
   */
  public void setAppraisalRules(AppraisalRules appraisalRules) {
    this.appraisalRules = appraisalRules;
  }

  /**
   * Spécifie les règles de durée d’utilité administrative.
   *
   * @param ruleName le nom de la règle
   * @param startDate la date de début
   */
  public void setAppraisalRules(String ruleName, LocalDate startDate) {
    this.appraisalRules = new AppraisalRules(ruleName, startDate);
  }

  /**
   * Indique les règles de durée d'utilité courante.
   *
   * @return les règles de durée d'utilité courante
   */
  public StorageRules getStorageRules() {
    return storageRules;
  }

  /**
   * Spécifie les règles de durée d'utilité courante.
   *
   * @param storageRules les règles de durée d'utilité courante
   */
  public void setStorageRules(StorageRules storageRules) {
    this.storageRules = storageRules;
  }

  /**
   * Spécifie les règles de durée d'utilité courante.
   *
   * @param ruleName le nom de la règle
   * @param startDate la date de début
   */
  public void setStorageRules(String ruleName, LocalDate startDate) {
    this.storageRules = new StorageRules(ruleName, startDate);
  }

  /**
   * Indique les règles de gel.
   *
   * @return les règles de gel
   */
  public HoldRules getHoldRules() {
    return holdRules;
  }

  /**
   * Spécifie les règles de gel.
   *
   * @param holdRules les règles de gel
   */
  public void setHoldRules(HoldRules holdRules) {
    this.holdRules = holdRules;
  }

  /**
   * Spécifie les règles de gel.
   *
   * @param ruleName le nom de la règle
   * @param startDate la date de début
   */
  public void setHoldRules(
      String ruleName,
      LocalDate startDate,
      LocalDate holdEndDate,
      String holdOwner,
      String holdReason,
      LocalDate holdReassessingDate,
      Boolean preventRearrangement) {
    this.holdRules =
        new HoldRules(
            ruleName,
            startDate,
            holdEndDate,
            holdOwner,
            holdReason,
            holdReassessingDate,
            preventRearrangement);
  }

  /**
   * Indique si une autorisation humaine est nécessaire pour vérifier ou valider les opérations de
   * gestion des ArchiveUnit.
   *
   * @return si une autorisation est nécessaire
   */
  public Boolean getNeedAuthorization() {
    return needAuthorization;
  }

  /**
   * Spécifie si une autorisation humaine est nécessaire pour vérifier ou valider les opérations de
   * gestion des ArchiveUnit.
   *
   * @param needAuthorization si une autorisation est nécessaire
   */
  public void setNeedAuthorization(Boolean needAuthorization) {
    this.needAuthorization = needAuthorization;
  }

  /**
   * Indique l'opération de mise à jour.
   *
   * @return l'opération de mise à jour
   */
  public UpdateOperation getUpdateOperation() {
    return updateOperation;
  }

  /**
   * Spécifie l'opération de mise à jour.
   *
   * @param updateOperation l'opération de mise à jour
   */
  public void setUpdateOperation(UpdateOperation updateOperation) {
    this.updateOperation = updateOperation;
  }

  /**
   * Ajoute un évènement à la liste des évènements de l'unité d'archive.
   *
   * @param event l'évènement à ajouter
   */
  public void addLogEvent(Event event) {
    Validate.notNull(event, SipUtils.NOT_NULL, "event");
    logEvents.add(event);
  }

  /**
   * Supprime un évènement de la liste des évènements de l'unité d'archive.
   *
   * @param event l'évènement à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeLogEvent(Event event) {
    Validate.notNull(event, SipUtils.NOT_NULL, "event");
    return logEvents.remove(event);
  }

  /**
   * Fournit la liste des évènements de l'unité d'archive. Un évènement correspond à toute opération
   * concernant l'unité d'archive : opération de versement, de mise à jour de métadonnées, de
   * préservation, etc.
   *
   * @return la liste des évènements de l'unité d'archive
   */
  public List<Event> getLogEvents() {
    return new ArrayList<>(logEvents);
  }

  /**
   * Indique le niveau de description au sens de la norme ISAD (G). Indique si l’unité d'archive
   * correspond à un fonds, à un sous-fonds, à une classe, à une série organique, à une sous-série
   * organique, à un dossier, à un sous-dossier ou à une pièce.
   *
   * @return le niveau de description
   */
  public String getDescriptionLevel() {
    return descriptionLevel;
  }

  /**
   * Spécifie le niveau de description au sens de la norme ISAD (G). Indique si l’unité d'archive
   * correspond à un fonds, à un sous-fonds, à une classe, à une série organique, à une sous-série
   * organique, à un dossier, à un sous-dossier ou à une pièce.
   *
   * @param descriptionLevel le niveau de description
   */
  public void setDescriptionLevel(String descriptionLevel) {
    this.descriptionLevel = descriptionLevel;
  }

  /**
   * Fournit la liste des intitulés de l'unité d'archive.
   *
   * @return la liste des intitulés
   */
  public List<Text> getTitles() {
    return new ArrayList<>(titles);
  }

  /**
   * Ajoute un intitulé à l'unité d'archive.
   *
   * @param message l'intitulé à ajouter
   */
  public void addTitle(String message) {
    titles.add(new Text(message));
  }

  /**
   * Ajoute un intitulé à l'unité d'archive.
   *
   * @param message l'intitulé à ajouter
   * @param lang la langue à ajouter
   */
  public void addTitle(String message, String lang) {
    titles.add(new Text(message, lang));
  }

  /**
   * Ajoute un intitulé à l'unité d'archive.
   *
   * @param title le texte de l'intitulé à ajouter
   */
  public void addTitle(Text title) {
    Validate.notNull(title, SipUtils.NOT_NULL, "title");
    titles.add(title);
  }

  /**
   * Supprime un intitulé de l'unité d'archive.
   *
   * @param title le texte de l'intitulé à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeTitle(Text title) {
    Validate.notNull(title, SipUtils.NOT_NULL, "title");
    return titles.remove(title);
  }

  /**
   * Fournit la liste des descriptions de l'unité d'archive.
   *
   * @return la liste des descriptions
   */
  public List<Text> getDescriptions() {
    return new ArrayList<>(descriptions);
  }

  /**
   * Ajoute une description à l'unité d'archive.
   *
   * @param message la description à ajouter
   */
  public void addDescription(String message) {
    descriptions.add(new Text(message));
  }

  /**
   * Ajoute une description à l'unité d'archive.
   *
   * @param message la description à ajouter
   * @param lang la langue à ajouter
   */
  public void addDescription(String message, String lang) {
    descriptions.add(new Text(message, lang));
  }

  /**
   * Ajoute une description à l'unité d'archive.
   *
   * @param description le texte de la description à ajouter
   */
  public void addDescription(Text description) {
    Validate.notNull(description, SipUtils.NOT_NULL, "description");
    descriptions.add(description);
  }

  /**
   * Indique l'identifiant de l'archive unit. Note. L'identifiant de l'archive unit est unique dans
   * le document.
   *
   * @return l'identifiant de l'archive unit.
   */
  public String getId() {
    return id;
  }

  /**
   * Spécifie l'attribut ID identifiant l'archive unit. Il est de la responsabilité du développeur
   * de s'assurer que l'attribut spécifié est unique dans le document. Pour éviter d'éventuels
   * conflits avec les attributs générés automatiquement, il est conseillé de préfixer l'attribut
   * une valeur différente de "ID" qui est celle utilisée par le générateur.
   *
   * <p>Note. Les attributs de type ID permettent d'identifier de manière unique un élément dans le
   * document. La valeur d'un attribut de type ID doit donc être unique parmi toutes les valeurs de
   * tous les attributs ID de tout le document. Dans le cas contraire le document XML n'est pas
   * valide. Si l'ID d'un élément n'est pas défini, le générateur crée automatiquement en se basant
   * sur une séquence un ID unique préfixé par "ID".
   *
   * @param id l'identifiant de l'archive unit
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Supprime une description de l'unité d'archive.
   *
   * @param description la description à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeDescription(Text description) {
    Validate.notNull(description, SipUtils.NOT_NULL, "description");
    return descriptions.remove(description);
  }

  /**
   * Indique les positions de l’unité d'archive dans le plan de classement du service producteur.
   *
   * @return la position
   */
  public List<String> getFilePlanPositions() {
    return new ArrayList<>(filePlanPositions);
  }

  /**
   * Ajoute la position de l’unité d'archive dans le plan de classement du service producteur.
   *
   * @param filePlanPosition la position à ajouter
   */
  public void addFilePlanPosition(String filePlanPosition) {
    Validate.notNull(filePlanPosition, SipUtils.NOT_NULL, "filePlanPosition");
    this.filePlanPositions.add(filePlanPosition);
  }

  /**
   * Supprime la position de l’unité d'archive.
   *
   * @param filePlanPosition la position à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeFilePlanPosition(String filePlanPosition) {
    Validate.notNull(filePlanPosition, SipUtils.NOT_NULL, "filePlanPosition");
    return filePlanPositions.remove(filePlanPosition);
  }

  /**
   * Indique les identifiants attribués à l'archive. Il est attribué par le SAE et correspond à un
   * identifiant interne.
   *
   * @return l 'identifiant
   */
  public List<String> getSystemIds() {
    return new ArrayList<>(systemIds);
  }

  /**
   * Ajoute l'identifiant.
   *
   * @param systemId la position à ajouter
   */
  public void addSystemId(String systemId) {
    Validate.notNull(systemId, SipUtils.NOT_NULL, "systemId");
    this.systemIds.add(systemId);
  }

  /**
   * Supprime l'identifiant.
   *
   * @param systemId la position à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeSystemId(String systemId) {
    Validate.notNull(systemId, SipUtils.NOT_NULL, "systemId");
    return systemIds.remove(systemId);
  }

  /**
   * Indique les identifiants attribués aux objets de données. Il est attribué par le SAE et
   * correspond à un identifiant interne.
   *
   * @return l 'identifiant
   */
  public List<String> getDataObjectSystemIds() {
    return dataObjectSystemIds;
  }

  /**
   * Ajoute l'identifiant.
   *
   * @param dataObjectSystemId la position à ajouter
   */
  public void addDataObjectSystemId(String dataObjectSystemId) {
    Validate.notNull(dataObjectSystemId, SipUtils.NOT_NULL, "dataObjectSystemId");
    this.dataObjectSystemIds.add(dataObjectSystemId);
  }

  /**
   * Supprime l'identifiant.
   *
   * @param dataObjectSystemId la position à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeDataObjectSystemId(String dataObjectSystemId) {
    Validate.notNull(dataObjectSystemId, SipUtils.NOT_NULL, "dataObjectSystemId");
    return dataObjectSystemIds.remove(dataObjectSystemId);
  }

  /**
   * Indique les identifiants système attribué à l’ArchiveUnit par l’application du service
   * producteur.
   *
   * @return l 'identifiant
   */
  public List<String> getOriginatingSystemIds() {
    return new ArrayList<>(originatingSystemIds);
  }

  /**
   * Ajoute l'identifiant système attribué à l’ArchiveUnit par l’application du service producteur.
   *
   * @param originatingSystemId l'identifiant à ajouter
   */
  public void addOriginatingSystemId(String originatingSystemId) {
    Validate.notNull(originatingSystemId, SipUtils.NOT_NULL, "originatingSystemId");
    this.originatingSystemIds.add(originatingSystemId);
  }

  /**
   * Supprime l'identifiant système à la liste.
   *
   * @param originatingSystemId l'identifiant à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeOriginatingSystemId(String originatingSystemId) {
    Validate.notNull(originatingSystemId, SipUtils.NOT_NULL, "originatingSystemId");
    return originatingSystemIds.remove(originatingSystemId);
  }

  /**
   * Indique les identifiants métier attribué à l'unité d'archive par le service d'archives. Peut
   * être comparé à une cote.
   *
   * @return l 'identifiant
   */
  public List<String> getArchivalAgencyArchiveUnitIdentifiers() {
    return new ArrayList<>(archivalAgencyArchiveUnitIdentifiers);
  }

  /**
   * Ajoute l'identifiant métier attribué à l’ArchiveUnit par l’application du service producteur.
   *
   * @param archivalAgencyArchiveUnitIdentifier l'identifiant à ajouter
   */
  public void addArchivalAgencyArchiveUnitIdentifier(String archivalAgencyArchiveUnitIdentifier) {
    Validate.notNull(
        archivalAgencyArchiveUnitIdentifier,
        SipUtils.NOT_NULL,
        "archivalAgencyArchiveUnitIdentifier");
    this.archivalAgencyArchiveUnitIdentifiers.add(archivalAgencyArchiveUnitIdentifier);
  }

  /**
   * Supprime l'identifiant métier à la liste.
   *
   * @param archivalAgencyArchiveUnitIdentifier l'identifiant à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeArchivalAgencyArchiveUnitIdentifier(
      String archivalAgencyArchiveUnitIdentifier) {
    Validate.notNull(
        archivalAgencyArchiveUnitIdentifier,
        SipUtils.NOT_NULL,
        "archivalAgencyArchiveUnitIdentifier");
    return archivalAgencyArchiveUnitIdentifiers.remove(archivalAgencyArchiveUnitIdentifier);
  }

  /**
   * Indique les identifiants métier attribués à l’ArchiveUnit par le service producteur.
   *
   * @return l 'identifiant
   */
  public List<String> getOriginatingAgencyArchiveUnitIdentifiers() {
    return new ArrayList<>(originatingAgencyArchiveUnitIdentifiers);
  }

  /**
   * Ajoute l'identifiant métier attribué à l’ArchiveUnit par l’application du service producteur.
   *
   * @param originatingAgencyArchiveUnitIdentifier l'identifiant à ajouter
   */
  public void addOriginatingAgencyArchiveUnitIdentifier(
      String originatingAgencyArchiveUnitIdentifier) {
    Validate.notNull(
        originatingAgencyArchiveUnitIdentifier,
        SipUtils.NOT_NULL,
        "originatingAgencyArchiveUnitIdentifier");
    this.originatingAgencyArchiveUnitIdentifiers.add(originatingAgencyArchiveUnitIdentifier);
  }

  /**
   * Supprime l'identifiant métier à la liste des langues du contenu des objets binaires ou
   * physiques.
   *
   * @param originatingAgencyArchiveUnitIdentifier l'identifiant à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeOriginatingAgencyArchiveUnitIdentifier(
      String originatingAgencyArchiveUnitIdentifier) {
    Validate.notNull(
        originatingAgencyArchiveUnitIdentifier,
        SipUtils.NOT_NULL,
        "archivalAgencyArchiveUnitIdentifier");
    return originatingAgencyArchiveUnitIdentifiers.remove(originatingAgencyArchiveUnitIdentifier);
  }

  /**
   * Indique les identifiants attribués à l'ArchiveUnit par le service versant.
   *
   * @return l 'identifiant
   */
  public List<String> getTransferringAgencyArchiveUnitIdentifiers() {
    return new ArrayList<>(transferringAgencyArchiveUnitIdentifiers);
  }

  /**
   * Ajoute l'identifiant attribué à l’ArchiveUnit par le service versant.
   *
   * @param transferringAgencyArchiveUnitIdentifier l'identifiant à ajouter
   */
  public void addTransferringAgencyArchiveUnitIdentifier(
      String transferringAgencyArchiveUnitIdentifier) {
    Validate.notNull(
        transferringAgencyArchiveUnitIdentifier,
        SipUtils.NOT_NULL,
        "transferringAgencyArchiveUnitIdentifier");
    this.transferringAgencyArchiveUnitIdentifiers.add(transferringAgencyArchiveUnitIdentifier);
  }

  /**
   * Supprime l'identifiant métier à la liste.
   *
   * @param transferringAgencyArchiveUnitIdentifier l'identifiant à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeTransferringAgencyArchiveUnitIdentifier(
      String transferringAgencyArchiveUnitIdentifier) {
    Validate.notNull(
        transferringAgencyArchiveUnitIdentifier,
        SipUtils.NOT_NULL,
        "transferringAgencyArchiveUnitIdentifier");
    return transferringAgencyArchiveUnitIdentifiers.remove(transferringAgencyArchiveUnitIdentifier);
  }

  /**
   * Ajoute un changement à la liste des changements. La liste contient les changements successifs
   * de propriété, de responsabilité et de conservation des unités d'archives avant leur entrée dans
   * le lieu de conservation. On peut notamment y indiquer comment s'est effectué le passage de
   * l'application d'origine au fichier archivable. Correspond à l'historique de la conservation en
   * ISAD(G).
   *
   * @param custodialItem le changement à ajouter
   */
  public void addCustodialItem(CustodialItem custodialItem) {
    Validate.notNull(custodialItem, SipUtils.NOT_NULL, "custodialItem");
    custodialItems.add(custodialItem);
  }

  /**
   * Ajoute un changement à la liste des changements. La liste contient les changements successifs
   * de propriété, de responsabilité et de conservation des unités d'archives avant leur entrée dans
   * le lieu de conservation. On peut notamment y indiquer comment s'est effectué le passage de
   * l'application d'origine au fichier archivable. Correspond à l'historique de la conservation en
   * ISAD(G).
   *
   * @param value l'intitulé du changement à ajouter
   * @param when la date du changement à ajouter
   */
  public void addCustodialItem(String value, LocalDateTime when) {
    custodialItems.add(new CustodialItem(value, when));
  }

  /**
   * Supprime un changement à la liste des changements. La liste contient les changements successifs
   * de propriété, de responsabilité et de conservation des unités d'archives avant leur entrée dans
   * le lieu de conservation. On peut notamment y indiquer comment s'est effectué le passage de
   * l'application d'origine au fichier archivable. Correspond à l'historique de la conservation en
   * ISAD(G).
   *
   * @param custodialItem le changement à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeCustodialItem(CustodialItem custodialItem) {
    Validate.notNull(custodialItem, SipUtils.NOT_NULL, "custodialItem");
    return custodialItems.remove(custodialItem);
  }

  /**
   * Fournit la liste des changements successifs de propriété, de responsabilité et de conservation
   * des unités d'archives avant leur entrée dans le lieu de conservation.
   *
   * @return la liste des changements
   */
  public List<CustodialItem> getCustodialItems() {
    return new ArrayList<>(custodialItems);
  }

  /**
   * Indique le type d’information au sens de l’OAIS (information de représentation, information de
   * pérennisation, etc.).
   *
   * @return le type
   */
  public String getType() {
    return type;
  }

  /**
   * Spécifie le type d’information au sens de l’OAIS (information de représentation, information de
   * pérennisation, etc.).
   *
   * @param type le type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Indique le type de document au sens diplomatique du terme (ex. compte rendu de réunion, note,
   * correspondance, etc.).
   *
   * @return le type de document
   */
  public String getDocumentType() {
    return documentType;
  }

  /**
   * Spécifie le type de document au sens diplomatique du terme (ex. compte rendu de réunion, note,
   * correspondance, etc.).
   *
   * @param documentType le type de document
   */
  public void setDocumentType(String documentType) {
    this.documentType = documentType;
  }

  /**
   * Ajoute la langue à la liste des langues du contenu des objets binaires ou physiques.
   *
   * @param language la langue à ajouter
   */
  public void addLanguage(String language) {
    Validate.notNull(language, SipUtils.NOT_NULL, "language");
    languages.add(language);
  }

  /**
   * Supprime la langue à la liste des langues du contenu des objets binaires ou physiques.
   *
   * @param language la langue à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeLanguage(String language) {
    Validate.notNull(language, SipUtils.NOT_NULL, "language");
    return languages.remove(language);
  }

  /**
   * Fournit la liste des langues du contenu des objets binaires ou physiques.
   *
   * @return la liste des langues
   */
  public List<String> getLanguages() {
    return new ArrayList<>(languages);
  }

  /**
   * Indique la langue utilisée pour les métadonnées de description.
   *
   * @return la langue
   */
  public String getDescriptionLanguage() {
    return descriptionLanguage;
  }

  /**
   * Spécifie la langue utilisée pour les métadonnées de description.
   *
   * @param descriptionLanguage la langue
   */
  public void setDescriptionLanguage(String descriptionLanguage) {
    this.descriptionLanguage = descriptionLanguage;
  }

  /**
   * Indique l'état de l'objet binaire ou physique (par rapport avec son cycle de vie). L'état
   * permet par exemple d'indiquer si la signature du fichier a été vérifiée avant le transfert aux
   * archives.
   *
   * @return la statut
   */
  public String getStatus() {
    return status;
  }

  /**
   * Spécifie l'état de l'objet binaire ou physique (par rapport avec son cycle de vie). L'état
   * permet par exemple d'indiquer si la signature du fichier a été vérifiée avant le transfert aux
   * archives.
   *
   * @param status la statut
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Indique la version du document.
   *
   * @return la version
   */
  public String getVersion() {
    return version;
  }

  /**
   * Spécifie la version du document.
   *
   * @param version la version
   */
  public void setVersion(String version) {
    this.version = version;
  }

  /**
   * Indique le service producteur. Il s'agit d'une personne physique ou morale, publique ou privée,
   * qui a produit, reçu et conservé des archives dans l'exercice de son activité.
   *
   * @return le service producteur
   */
  public Agency getOriginatingAgency() {
    return originatingAgency;
  }

  /**
   * Spécifie le service producteur. Il s'agit d'une personne physique ou morale, publique ou
   * privée, qui a produit, reçu et conservé des archives dans l'exercice de son activité.
   *
   * @param originatingAgency le service producteur
   */
  public void setOriginatingAgency(Agency originatingAgency) {
    this.originatingAgency = originatingAgency;
  }

  /**
   * Indique le service versant responsable du transfert des données.
   *
   * @return le service versant
   */
  public Agency getSubmissionAgency() {
    return submissionAgency;
  }

  /**
   * Spécifie le service versant responsable du transfert des données.
   *
   * @param submissionAgency le service versant
   */
  public void setSubmissionAgency(Agency submissionAgency) {
    this.submissionAgency = submissionAgency;
  }

  /**
   * Ajoute un agent à la liste des agents.
   *
   * @param agent l'agent à ajouter
   */
  public void addAgent(Agent agent) {
    Validate.notNull(agent, SipUtils.NOT_NULL, "agent");
    agents.add(agent);
  }

  /**
   * Supprime un agent de la liste des agents.
   *
   * @param agent l'agent à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeAgent(Agent agent) {
    Validate.notNull(agent, SipUtils.NOT_NULL, "agent");
    return agents.remove(agent);
  }

  /**
   * Fournit la liste agents.
   *
   * @return la liste des agents
   */
  public List<Agent> getAgents() {
    return new ArrayList<>(agents);
  }

  /**
   * Ajoute un titulaire à la liste des titulaires des droits de propriété intellectuelle.
   *
   * @param authorizedAgent le titulaire à ajouter
   */
  public void addAuthorizedAgent(Agent authorizedAgent) {
    Validate.notNull(authorizedAgent, SipUtils.NOT_NULL, "authorizedAgent");
    authorizedAgents.add(authorizedAgent);
  }

  /**
   * Supprime un titulaire à la liste des titulaires des droits de propriété intellectuelle.
   *
   * @param authorizedAgent le titulaire à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeAuthorizedAgent(Agent authorizedAgent) {
    Validate.notNull(authorizedAgent, SipUtils.NOT_NULL, "authorizedAgent");
    return authorizedAgents.remove(authorizedAgent);
  }

  /**
   * Fournit la liste des titulaires des droits de propriété intellectuelle.
   *
   * @return la liste des titulaires
   */
  public List<Agent> getAuthorizedAgents() {
    return new ArrayList<>(authorizedAgents);
  }

  /**
   * Ajoute un rédacteur à la liste des rédacteurs de l’unité d'archive.
   *
   * @param writer le rédacteur à ajouter
   */
  public void addWriter(Agent writer) {
    Validate.notNull(writer, SipUtils.NOT_NULL, "writer");
    writers.add(writer);
  }

  /**
   * Supprime un rédacteur de la liste des rédacteurs de l’unité d'archive.
   *
   * @param writer le rédacteur à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeWriter(Agent writer) {
    Validate.notNull(writer, SipUtils.NOT_NULL, "writer");
    return writers.remove(writer);
  }

  /**
   * Fournit la liste des rédacteurs de l’unité d'archive.
   *
   * @return la liste des rédacteurs
   */
  public List<Agent> getWriters() {
    return new ArrayList<>(writers);
  }

  /**
   * Ajoute un destinataire à la liste des destinataires pour action. Utilisé pour indiquer le nom
   * du destinataire par exemple dans un courrier électronique.
   *
   * @param addressee le destinataire à ajouter
   */
  public void addAddressee(Agent addressee) {
    Validate.notNull(addressee, SipUtils.NOT_NULL, "addressee");
    addressees.add(addressee);
  }

  /**
   * Supprime un destinataire de la liste des destinataires pour action. Utilisé pour indiquer le
   * nom du destinataire par exemple dans un courrier électronique.
   *
   * @param addressee le destinataire à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeAddressee(Agent addressee) {
    Validate.notNull(addressee, SipUtils.NOT_NULL, "addressee");
    return addressees.remove(addressee);
  }

  /**
   * Fournit la liste des destinataires pour action. Utilisé pour indiquer le nom du destinataire
   * par exemple dans un courrier électronique.
   *
   * @return la liste des destinataires pour action
   */
  public List<Agent> getAddressees() {
    return new ArrayList<>(addressees);
  }

  /**
   * Ajoute un destinataire à la liste des destinataires pour information. Utilisé pour indiquer le
   * nom du destinataire en copie, pour information, par exemple dans un courrier électronique.
   *
   * @param recipient le destinataire à ajouter
   */
  public void addRecipient(Agent recipient) {
    Validate.notNull(recipient, SipUtils.NOT_NULL, "recipient");
    recipients.add(recipient);
  }

  /**
   * Supprime un destinataire de la liste des destinataires pour information. Utilisé pour indiquer
   * le nom du destinataire en copie, pour information, par exemple dans un courrier électronique.
   *
   * @param recipient le destinataire à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeRecipient(Agent recipient) {
    Validate.notNull(recipient, SipUtils.NOT_NULL, "recipient");
    return recipients.remove(recipient);
  }

  /**
   * Fournit la liste des destinataires pour information. Utilisé pour indiquer le nom du
   * destinataire en copie, pour information, par exemple dans un courrier électronique.
   *
   * @return la liste des destinataires pour information
   */
  public List<Agent> getRecipients() {
    return new ArrayList<>(recipients);
  }

  /**
   * Ajoute un émetteur à la liste des émetteurs du message.
   *
   * @param transmitter l'émetteur du message à ajouter
   */
  public void addTransmitter(Agent transmitter) {
    Validate.notNull(transmitter, SipUtils.NOT_NULL, "transmitter");
    transmitters.add(transmitter);
  }

  /**
   * Supprime un émetteur de la liste des émetteurs du message.
   *
   * @param transmitter l'émetteur du message à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeTransmitter(Agent transmitter) {
    Validate.notNull(transmitter, SipUtils.NOT_NULL, "transmitter");
    return transmitters.remove(transmitter);
  }

  /**
   * Fournit la liste des émetteurs du message.
   *
   * @return la liste des émetteurs du message
   */
  public List<Agent> getTransmitters() {
    return new ArrayList<>(transmitters);
  }

  /**
   * Ajoute un expéditeur à la liste des expéditeurs du message.
   *
   * @param sender l'expéditeur à ajouter
   */
  public void addSender(Agent sender) {
    Validate.notNull(sender, SipUtils.NOT_NULL, "sender");
    senders.add(sender);
  }

  /**
   * Supprime un expéditeur de la liste des expéditeurs du message.
   *
   * @param sender l'expéditeur à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeSender(Agent sender) {
    Validate.notNull(sender, SipUtils.NOT_NULL, "sender");
    return senders.remove(sender);
  }

  /**
   * Fournit la liste des expéditeurs du message.
   *
   * @return la liste des expéditeurs du message
   */
  public List<Agent> getSenders() {
    return new ArrayList<>(senders);
  }

  /**
   * Indique la référence à la source papier originale.
   *
   * @return la source
   */
  public String getSource() {
    return source;
  }

  /**
   * Spécifie la référence à la source papier originale.
   *
   * @param source la source
   */
  public void setSource(String source) {
    this.source = source;
  }

  /**
   * Indique la référence à un objet faisant ou ne faisant pas partie du présent paquet
   * d'information.
   *
   * @return la référence
   */
  public RelatedObjectRef getRelation() {
    return relation;
  }

  /**
   * Spécifie la référence à un objet faisant ou ne faisant pas partie du présent paquet
   * d'information.
   *
   * @param relation la référence
   */
  public void setRelation(RelatedObjectRef relation) {
    this.relation = relation;
  }

  /**
   * Ajoute une signature à la liste des informations relatives à la signature.
   *
   * @param signature la signature à ajouter
   */
  public void addSignature(Signature signature) {
    Validate.notNull(signature, SipUtils.NOT_NULL, "signature");
    signatures.add(signature);
  }

  /**
   * Supprime une signature à la liste des informations relatives à la signature.
   *
   * @param signature la signature à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeSignature(Signature signature) {
    Validate.notNull(signature, SipUtils.NOT_NULL, "signature");
    return signatures.remove(signature);
  }

  /**
   * Fournit la liste des informations relatives à la signature.
   *
   * @return la liste des informations relatives à la signature
   */
  public List<Signature> getSignatures() {
    return new ArrayList<>(signatures);
  }

  /**
   * Indique l'identifiant de la version du GPS.
   *
   * @return l 'identifiant
   */
  public String getGpsVersionID() {
    return gpsVersionID;
  }

  /**
   * Spécifie l'identifiant de la version du GPS.
   *
   * @param gpsVersionID l'identifiant
   */
  public void setGpsVersionID(String gpsVersionID) {
    this.gpsVersionID = gpsVersionID;
  }

  /**
   * Indique l'altitude basée sur la référence dans GPSAltitudeRef. L'altitude est exprimée en
   * mètres.
   *
   * @return l 'altitude
   */
  public String getGpsAltitude() {
    return gpsAltitude;
  }

  /**
   * Spécifie l'altitude basée sur la référence dans GPSAltitudeRef. L'altitude est exprimée en
   * mètres.
   *
   * @param gpsAltitude l'altitude
   */
  public void setGpsAltitude(String gpsAltitude) {
    this.gpsAltitude = gpsAltitude;
  }

  /**
   * Indique l'altitude utilisée comme altitude de référence. Si l'altitude est au-dessus du niveau
   * de la mer, la valeur 0 est normalement donnée. Si l'altitude est au-dessous du niveau de la
   * mer, la valeur 1 est donnée.
   *
   * @return l'altitude de référence
   */
  public String getGpsAltitudeRef() {
    return gpsAltitudeRef;
  }

  /**
   * Spécifie l'altitude utilisée comme altitude de référence. Si l'altitude est au-dessus du niveau
   * de la mer, la valeur 0 est normalement donnée. Si l'altitude est au-dessous du niveau de la
   * mer, la valeur 1 est donnée.
   *
   * @param gpsAltitudeRef l'altitude de référence
   */
  public void setGpsAltitudeRef(String gpsAltitudeRef) {
    this.gpsAltitudeRef = gpsAltitudeRef;
  }

  /**
   * Indique la latitude. La latitude qui peut être exprimée de deux manières différentes : degrés,
   * décimaux ou degrés, minutes et secondes. Si la latitude est exprimée en degrés, décimaux, le
   * format type est dd, dd. Par exemple : "45.3130339". Si la latitude est exprimée en degrés,
   * minutes et secondes, le format type est dd, mm, ss. Par exemple : "45 18 46.922".
   *
   * @return la latitude
   */
  public String getGpsLatitude() {
    return gpsLatitude;
  }

  /**
   * Spécifie la latitude. La latitude qui peut être exprimée de deux manières différentes : degrés,
   * décimaux ou degrés, minutes et secondes. Si la latitude est exprimée en degrés, décimaux, le
   * format type est dd, dd. Par exemple : "45.3130339". Si la latitude est exprimée en degrés,
   * minutes et secondes, le format type est dd, mm, ss. Par exemple : "45 18 46.922".
   *
   * @param gpsLatitude la latitude
   */
  public void setGpsLatitude(String gpsLatitude) {
    this.gpsLatitude = gpsLatitude;
  }

  /**
   * Indique la latitude. La valeur 'N' indique la latitude nord, et 'S' indique la latitude sud.
   *
   * @return la latitude de référence
   */
  public String getGpsLatitudeRef() {
    return gpsLatitudeRef;
  }

  /**
   * Spécifie la latitude. La valeur 'N' indique la latitude nord, et 'S' indique la latitude sud.
   *
   * @param gpsLatitudeRef la latitude de référence
   */
  public void setGpsLatitudeRef(String gpsLatitudeRef) {
    this.gpsLatitudeRef = gpsLatitudeRef;
  }

  /**
   * Indique la longitude. La longitude peut être exprimée de deux manières différentes : degrés,
   * décimaux ou degrés, minutes et secondes. Si la longitude est exprimée en degrés, décimaux, le
   * format type est dd, dd. Par exemple : "5.392285833333334". Si la longitude est exprimée en
   * degrés, minutes et secondes, le format type est dd, mm, ss. Par exemple : "5 23 32.229".
   *
   * @return la longitude
   */
  public String getGpsLongitude() {
    return gpsLongitude;
  }

  /**
   * Spécifie la longitude. La longitude peut être exprimée de deux manières différentes : degrés,
   * décimaux ou degrés, minutes et secondes. Si la longitude est exprimée en degrés, décimaux, le
   * format type est dd, dd. Par exemple : "5.392285833333334". Si la longitude est exprimée en
   * degrés, minutes et secondes, le format type est dd, mm, ss. Par exemple : "5 23 32.229".
   *
   * @param gpsLongitude la longitude
   */
  public void setGpsLongitude(String gpsLongitude) {
    this.gpsLongitude = gpsLongitude;
  }

  /**
   * Indique la longitude. La valeur 'E' indique la longitude EST, et 'W' indique la longitude
   * OUEST.
   *
   * @return la longitude de référence
   */
  public String getGpsLongitudeRef() {
    return gpsLongitudeRef;
  }

  /**
   * Spécifie la longitude. La valeur 'E' indique la longitude EST, et 'W' indique la longitude
   * OUEST.
   *
   * @param gpsLongitudeRef la longitude de référence
   */
  public void setGpsLongitudeRef(String gpsLongitudeRef) {
    this.gpsLongitudeRef = gpsLongitudeRef;
  }

  /**
   * Indique l'heure et la date de la position GPS.
   *
   * @return la date et l'heure
   */
  public String getGpsDateStamp() {
    return gpsDateStamp;
  }

  /**
   * Spécifie l'heure et la date de la position GPS.
   *
   * @param gpsDateStamp la date et l'heure
   */
  public void setGpsDateStamp(String gpsDateStamp) {
    this.gpsDateStamp = gpsDateStamp;
  }

  /**
   * Indique la date de création.
   *
   * @return la date de création
   */
  public LocalDate getCreatedDate() {
    return createdDate;
  }

  /**
   * Spécifie la date de création.
   *
   * @param createdDate la date de création
   */
  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  /**
   * Indique la date de la transaction.
   *
   * @return la date de la transaction
   */
  public LocalDate getTransactedDate() {
    return transactedDate;
  }

  /**
   * Spécifie la date de la transaction.
   *
   * @param transactedDate la date de la transaction
   */
  public void setTransactedDate(LocalDate transactedDate) {
    this.transactedDate = transactedDate;
  }

  /**
   * Indique la date de numérisation.
   *
   * @return la date de numérisation
   */
  public LocalDate getAcquiredDate() {
    return acquiredDate;
  }

  /**
   * Spécifie la date de numérisation.
   *
   * @param acquiredDate la date de numérisation
   */
  public void setAcquiredDate(LocalDate acquiredDate) {
    this.acquiredDate = acquiredDate;
  }

  /**
   * Indique la date d'envoi.
   *
   * @return la date d'envoi
   */
  public LocalDate getSentDate() {
    return sentDate;
  }

  /**
   * Spécifie la date d'envoi.
   *
   * @param sentDate la date d'envoi
   */
  public void setSentDate(LocalDate sentDate) {
    this.sentDate = sentDate;
  }

  /**
   * Indique la date de réception.
   *
   * @return la date de réception
   */
  public LocalDate getReceivedDate() {
    return receivedDate;
  }

  /**
   * Spécifie la date de réception.
   *
   * @param receivedDate la date de réception
   */
  public void setReceivedDate(LocalDate receivedDate) {
    this.receivedDate = receivedDate;
  }

  /**
   * Indique la date d'enregistrement.
   *
   * @return la date d'enregistrement
   */
  public LocalDate getRegisteredDate() {
    return registeredDate;
  }

  /**
   * Spécifie la date d'enregistrement.
   *
   * @param registeredDate la date d'enregistrement.
   */
  public void setRegisteredDate(LocalDate registeredDate) {
    this.registeredDate = registeredDate;
  }

  /**
   * Indique la date d'ouverture / date de début.
   *
   * @return la date de début
   */
  public LocalDate getStartDate() {
    return startDate;
  }

  /**
   * Spécifie la date d'ouverture / date de début.
   *
   * @param startDate la date de début
   */
  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  /**
   * Indique la date de fermeture ou la date de fin.
   *
   * @return la date de fin
   */
  public LocalDate getEndDate() {
    return endDate;
  }

  /**
   * Spécifie la date de fermeture ou la date de fin.
   *
   * @param endDate la date de fin
   */
  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  /**
   * Ajoute des mot-clés à la liste de mots-clés de description.
   *
   * @param values les mot-clés à ajouter
   */
  public void addTags(String... values) {
    for (String value : values) {
      tags.add(new Tag(null, value));
    }
  }

  /**
   * Ajoute un mot-clé à la liste de mots-clés de description.
   *
   * @param tag le mot-clé à ajouter
   */
  public void addTag(Tag tag) {
    Validate.notNull(tag, SipUtils.NOT_NULL, "tag");
    tags.add(tag);
  }

  /**
   * Ajoute une valeur à la liste de mots-clés de description.
   *
   * @param value la valeur à ajouter
   */
  public void addTag(String value) {
    tags.add(new Tag(null, value));
  }

  /**
   * Ajoute une clé et une valeur à la liste de mots-clés de description.
   *
   * @param key la clé du mot-clé
   * @param value la valeur du mot-clé
   */
  public void addTag(String key, String value) {
    tags.add(new Tag(key, value));
  }

  /**
   * Supprime un mot-clé de la liste de mots-clés de description.
   *
   * @param tag le mot-clé à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeTag(Tag tag) {
    Validate.notNull(tag, SipUtils.NOT_NULL, "tag");
    return tags.remove(tag);
  }

  /**
   * Fournit la liste de mots-clés de description.
   *
   * @return la liste de mots-clés de description
   */
  public List<Tag> getTags() {
    return new ArrayList<>(tags);
  }

  /**
   * Fournit les informations relatives à la signature.
   *
   * @return les informations relatives à la signature
   */
  public SigningInformation getSigningInformation() {
    return signingInformation;
  }

  /**
   * Spécifie les informations relatives à la signature.
   *
   * @param signingInformation les informations relatives à la signature
   */
  public void setSigningInformation(SigningInformation signingInformation) {
    this.signingInformation = signingInformation;
  }

  /**
   * Ajoute une unité d'archive à la liste d'autres unités d'archives contenues dans cette unité
   * d'archive.
   *
   * @param archiveUnit l'unité d'archive
   */
  @Override
  public void addArchiveUnit(ArchiveUnit archiveUnit) {
    Validate.notNull(archiveUnit, SipUtils.NOT_NULL, "archiveUnit");
    archiveUnits.add(archiveUnit);
  }

  /**
   * Supprime une unité d'archive de la liste d'autres unités d'archives contenues dans cette unité
   * d'archive.
   *
   * @param archiveUnit l'unité d'archive à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  @Override
  public boolean removeArchiveUnit(ArchiveUnit archiveUnit) {
    Validate.notNull(archiveUnit, SipUtils.NOT_NULL, "archiveUnit");
    return archiveUnits.remove(archiveUnit);
  }

  /**
   * Fournit la liste d'autres unités d'archives contenues dans cette unité d'archive.
   *
   * @return la liste d'autres unités d'archives
   */
  @Override
  public List<ArchiveUnit> getArchiveUnits() {
    return new ArrayList<>(archiveUnits);
  }

  /**
   * Ajoute une référence d'unité d'archive à la liste d'autres unités d'archives référencées par
   * cette unité d'archive
   *
   * @param reference référence à l'unité d'archive
   */
  public void addReference(ArchiveUnitRef reference) {
    Validate.notNull(reference, SipUtils.NOT_NULL, "reference");
    references.add(reference);
  }

  /**
   * Supprime une référence d'unité d'archive de la liste d'autres références d'unités d'archives
   * contenues dans cette unité d'archive.
   *
   * @param reference la référence d'unité d'archive à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeReference(ArchiveUnitRef reference) {
    Validate.notNull(reference, SipUtils.NOT_NULL, "reference");
    return references.remove(reference);
  }

  /**
   * Fournit la liste d'autres références d'unités d'archives contenues dans cette unité d'archive.
   *
   * @return la liste d'autres références d'unités d'archives
   */
  public List<ArchiveUnitRef> getReferences() {
    return new ArrayList<>(references);
  }

  /**
   * Ajoute l'élément à la liste des éléments étendus qui n'appartiennent pas à l'ontolgie standard.
   *
   * @param element l'élément à ajouter
   */
  public void addElement(String element) {
    Validate.notNull(element, SipUtils.NOT_NULL, "element");
    elements.add(element);
  }

  /**
   * Supprime un élément de la liste des éléments étendus qui n'appartiennent pas à l'ontologie
   * standard.
   *
   * @param element l'élément à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeElement(String element) {
    Validate.notNull(element, SipUtils.NOT_NULL, "element");
    return elements.remove(element);
  }

  /**
   * Ajoute un élément à la liste des éléments étendus qui n'appartiennent pas à l'ontologie
   * standard.
   *
   * @param element l'élément à ajouter
   */
  public void addElement(Element element) {
    Validate.notNull(element, SipUtils.NOT_NULL, "element");
    elements.add(element);
  }

  /**
   * Fournit le premier élément dont le "name" correspond à "elementName" dans la liste des éléments
   * étendus. Note. La recherche s'effectue uniquement sur les objets de type Element (et pas de
   * type String).
   *
   * @param elementName le nom de l'élément recherché
   * @return l'élément trouvé sinon null
   */
  public Element getFirstElement(String elementName) {
    Validate.notNull(elementName, SipUtils.NOT_NULL, "elementName");

    for (Object e : elements) {
      if (e instanceof Element element) {
        if (elementName.equals(element.getName())) {
          return element;
        }
      }
    }
    return null;
  }

  /**
   * Fournit la liste des éléments dont le "name" correspond à "elementName" dans la liste des
   * éléments étendus. Note. La recherche s'effectue uniquement sur les objets de type Element (et
   * pas de type String).
   *
   * @param elementName le nom des éléments recherchés
   * @return la liste des éléments trouvés
   */
  public List<Element> getElements(String elementName) {
    Validate.notNull(elementName, SipUtils.NOT_NULL, "elementName");

    List<Element> els = new ArrayList<>();
    for (Object e : elements) {
      if (e instanceof Element element) {
        if (elementName.equals(element.getName())) {
          els.add(element);
        }
      }
    }
    return els;
  }

  /**
   * Supprime un élément de la liste des éléments étendus qui n'appartiennent pas à l'ontologie
   * standard.
   *
   * @param element l'élément à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeElement(Element element) {
    Validate.notNull(element, SipUtils.NOT_NULL, "element");
    return elements.remove(element);
  }

  /**
   * Fournit la liste des éléments étendus qui n'appartiennent pas à l'ontologie standard.
   *
   * @return la liste des éléments étendus
   */
  public List<Object> getElements() {
    return new ArrayList<>(elements);
  }
}
