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

import java.util.Objects;

/**
 * La classe CodeListVersions représente les versions des listes de codes utilisées dans l'archive.
 *
 * <p>Supporté en SEDA v2.1 et en FNTC v4.
 *
 * @author Emmanuel Deviller
 */
public class CodeListVersions {

  /** L'identifiant des versions des listes de codes. */
  protected String id;

  /** La version de la liste de codes d'autorisation. */
  protected String authorizationReasonCodeListVersion;

  /** La version de la liste des codes des encodages de fichier */
  protected String fileEncodingCodeListVersion;

  /** La version de la liste de code d'identification du format. */
  protected String fileFormatCodeListVersion;

  /** La version de la liste de l'algorithme de hachage utilisé dans le message. */
  protected String messageDigestAlgorithmCodeListVersion;

  /** La version de liste des relations. */
  protected String relationshipCodeListVersion;

  /** La version de la liste des codes de réponses à utiliser. */
  protected String replyCodeListVersion;

  /** La version de la liste des statuts des signatures. */
  protected String signatureStatusCodeListVersion;

  /** La version de la liste de code du type Mime. */
  protected String mimeTypeCodeListVersion;

  /** La version de la liste de code d'encodage du fichier. */
  protected String encodingCodeListVersion;

  /** La version de la liste de code de l'algorithme de compression. */
  protected String compressionAlgorithmCodeListVersion;

  /**
   * La version de la liste de codes correspondant aux diverses versions d'un objet-données au sein
   * d’un groupe d'objets-données (ex. original papier, conservation, diffusion, vignette, txt).
   */
  protected String dataObjectVersionCodeListVersion;

  /** La version de la liste des codes pour les règles de durée d'utilité courante. */
  protected String storageRuleCodeListVersion;

  /** La version de la liste des codes pour les règles de durée d'utilité administrative.. */
  protected String appraisalRuleCodeListVersion;

  /** La version de la liste des codes pour les règles de communicabilité. */
  protected String accessRuleCodeListVersion;

  /** La version de la liste des codes pour les règles de diffusion. */
  protected String disseminationRuleCodeListVersion;

  /** La version de liste des codes pour les règles de réutilisation. */
  protected String reuseRuleCodeListVersion;

  /** La version de la liste des codes pour les règles de classification. */
  protected String classificationRuleCodeListVersion;

  /** La version de la liste de codes des modalités d'entrée. */
  protected String acquisitionInformationCodeListVersion;

  /** Instancie l'objet. */
  public CodeListVersions() {
    // Nothing to do here
  }

  /**
   * Indique l'identifiant des versions des listes de codes.
   *
   * @return l 'identifiant
   */
  public String getId() {
    return id;
  }

  /**
   * Spécifie l'identifiant des versions des listes de codes.
   *
   * @param id l'identifiant
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Indique la version de la liste de codes d'autorisation.
   *
   * @return la version de la liste de codes d'autorisation
   */
  public String getAuthorizationReasonCodeListVersion() {
    return authorizationReasonCodeListVersion;
  }

  /**
   * Spécifie la version de la liste de codes d'autorisation.
   *
   * @param authorizationReasonCodeListVersion la version de la liste de codes d'autorisation
   */
  public void setAuthorizationReasonCodeListVersion(String authorizationReasonCodeListVersion) {
    this.authorizationReasonCodeListVersion = authorizationReasonCodeListVersion;
  }

  /**
   * Indique la version de la liste des codes des encodages de fichier.
   *
   * @return la version de la liste des codes des encodages de fichier.
   */
  public String getFileEncodingCodeListVersion() {
    return fileEncodingCodeListVersion;
  }

  /**
   * Spécifie la version de la liste des codes des encodages de fichier.
   *
   * @param fileEncodingCodeListVersion la version de la liste des codes des encodages de fichier
   */
  public void setFileEncodingCodeListVersion(String fileEncodingCodeListVersion) {
    this.fileEncodingCodeListVersion = fileEncodingCodeListVersion;
  }

  /**
   * Indique La version de la liste de code d'identification du format.
   *
   * @return La version de la liste de code d'identification du format
   */
  public String getFileFormatCodeListVersion() {
    return fileFormatCodeListVersion;
  }

  /**
   * Spécifie la version de la liste de code d'identification du format.
   *
   * @param fileFormatCodeListVersion la version de la liste de code d'identification du format
   */
  public void setFileFormatCodeListVersion(String fileFormatCodeListVersion) {
    this.fileFormatCodeListVersion = fileFormatCodeListVersion;
  }

  /**
   * Indique la version de la liste de l'algorithme de hachage utilisé dans le message.
   *
   * @return la version de la liste de l'algorithme de hachage utilisé dans le message
   */
  public String getMessageDigestAlgorithmCodeListVersion() {
    return messageDigestAlgorithmCodeListVersion;
  }

  /**
   * Spécifie la version de la liste de l'algorithme de hachage utilisé dans le message.
   *
   * @param messageDigestAlgorithmCodeListVersion la version de la liste de l'algorithme de hachage
   *     utilisé dans le message
   */
  public void setMessageDigestAlgorithmCodeListVersion(
      String messageDigestAlgorithmCodeListVersion) {
    this.messageDigestAlgorithmCodeListVersion = messageDigestAlgorithmCodeListVersion;
  }

  /**
   * Indique la version de liste des relations.
   *
   * @return la version de liste des relations
   */
  public String getRelationshipCodeListVersion() {
    return relationshipCodeListVersion;
  }

  /**
   * Spécifie la version de liste des relations.
   *
   * @param relationshipCodeListVersion la version de liste des relations
   */
  public void setRelationshipCodeListVersion(String relationshipCodeListVersion) {
    this.relationshipCodeListVersion = relationshipCodeListVersion;
  }

  /**
   * Indique la version de la liste des codes de réponses à utiliser.
   *
   * @return la version de la liste des codes de réponses à utiliser
   */
  public String getReplyCodeListVersion() {
    return replyCodeListVersion;
  }

  /**
   * Spécifie la version de la liste des codes de réponses à utiliser.
   *
   * @param replyCodeListVersion la version de la liste des codes de réponses à utiliser
   */
  public void setReplyCodeListVersion(String replyCodeListVersion) {
    this.replyCodeListVersion = replyCodeListVersion;
  }

  /**
   * Indique la version de la liste des statuts des signatures.
   *
   * @return la version de la liste des statuts des signatures
   */
  public String getSignatureStatusCodeListVersion() {
    return signatureStatusCodeListVersion;
  }

  /**
   * Spécifie la version de la liste des statuts des signatures.
   *
   * @param signatureStatusCodeListVersion la version de la liste des statuts des signatures
   */
  public void setSignatureStatusCodeListVersion(String signatureStatusCodeListVersion) {
    this.signatureStatusCodeListVersion = signatureStatusCodeListVersion;
  }

  /**
   * Indique la version de la liste de code du type Mime.
   *
   * @return la version de la liste de code du type Mime
   */
  public String getMimeTypeCodeListVersion() {
    return mimeTypeCodeListVersion;
  }

  /**
   * Spécifie la version de la liste de code du type Mime.
   *
   * @param mimeTypeCodeListVersion la version de la liste de code du type Mime
   */
  public void setMimeTypeCodeListVersion(String mimeTypeCodeListVersion) {
    this.mimeTypeCodeListVersion = mimeTypeCodeListVersion;
  }

  /**
   * Indique la version de la liste de code d'encodage du fichier.
   *
   * @return la version de la liste de code d'encodage du fichier
   */
  public String getEncodingCodeListVersion() {
    return encodingCodeListVersion;
  }

  /**
   * Spécifie la version de la liste de code d'encodage du fichier.
   *
   * @param encodingCodeListVersion la version de la liste de code d'encodage du fichier
   */
  public void setEncodingCodeListVersion(String encodingCodeListVersion) {
    this.encodingCodeListVersion = encodingCodeListVersion;
  }

  /**
   * Indique la version de la liste de code de l'algorithme de compression.
   *
   * @return la version de la liste de code de l'algorithme de compression
   */
  public String getCompressionAlgorithmCodeListVersion() {
    return compressionAlgorithmCodeListVersion;
  }

  /**
   * Spécifie la version de la liste de code de l'algorithme de compression.
   *
   * @param compressionAlgorithmCodeListVersion la version de la liste de code de l'algorithme de
   *     compression
   */
  public void setCompressionAlgorithmCodeListVersion(String compressionAlgorithmCodeListVersion) {
    this.compressionAlgorithmCodeListVersion = compressionAlgorithmCodeListVersion;
  }

  /**
   * Indique la version de la liste de codes correspondant aux versions d'un objet-données au sein
   * d’un groupe d'objets-données (ex. original papier, conservation, diffusion, vignette, txt).
   *
   * @return la liste de codes correspondant aux versions
   */
  public String getDataObjectVersionCodeListVersion() {
    return dataObjectVersionCodeListVersion;
  }

  /**
   * Spécifie la version de la liste de codes correspondant aux versions d'un objet-données au sein
   * d’un groupe d'objets-données (ex. original papier, conservation, diffusion, vignette, txt).
   *
   * @param dataObjectVersionCodeListVersion la liste de codes correspondant aux versions
   */
  public void setDataObjectVersionCodeListVersion(String dataObjectVersionCodeListVersion) {
    this.dataObjectVersionCodeListVersion = dataObjectVersionCodeListVersion;
  }

  /**
   * Indique la version de la liste des codes pour les règles de durée d'utilité courante.
   *
   * @return la version de la liste des codes pour les règles de durée d'utilité courante
   */
  public String getStorageRuleCodeListVersion() {
    return storageRuleCodeListVersion;
  }

  /**
   * Spécifie la version de la liste des codes pour les règles de durée d'utilité courante.
   *
   * @param storageRuleCodeListVersion la version de la liste des codes pour les règles de durée
   *     d'utilité courante
   */
  public void setStorageRuleCodeListVersion(String storageRuleCodeListVersion) {
    this.storageRuleCodeListVersion = storageRuleCodeListVersion;
  }

  /**
   * Indique la version de la liste des codes pour les règles de durée d'utilité administrative.
   *
   * @return la version de la liste des codes pour les règles de durée d'utilité administrative
   */
  public String getAppraisalRuleCodeListVersion() {
    return appraisalRuleCodeListVersion;
  }

  /**
   * Spécifie la version de la liste des codes pour les règles de durée d'utilité administrative.
   *
   * @param appraisalRuleCodeListVersion la version de la liste des codes pour les règles de durée
   *     d'utilité administrative
   */
  public void setAppraisalRuleCodeListVersion(String appraisalRuleCodeListVersion) {
    this.appraisalRuleCodeListVersion = appraisalRuleCodeListVersion;
  }

  /**
   * Indique la version de la liste des codes pour les règles de communicabilité.
   *
   * @return la version de la liste des codes pour les règles de communicabilité
   */
  public String getAccessRuleCodeListVersion() {
    return accessRuleCodeListVersion;
  }

  /**
   * Spécifie la version de la liste des codes pour les règles de communicabilité.
   *
   * @param accessRuleCodeListVersion la version de la liste des codes pour les règles de
   *     communicabilité
   */
  public void setAccessRuleCodeListVersion(String accessRuleCodeListVersion) {
    this.accessRuleCodeListVersion = accessRuleCodeListVersion;
  }

  /**
   * Indique la version de la liste des codes pour les règles de diffusion.
   *
   * @return la version de la liste des codes pour les règles de diffusion.
   */
  public String getDisseminationRuleCodeListVersion() {
    return disseminationRuleCodeListVersion;
  }

  /**
   * Spécifie la version de la liste des codes pour les règles de diffusion.
   *
   * @param disseminationRuleCodeListVersion la version de la liste des codes pour les règles de
   *     diffusion
   */
  public void setDisseminationRuleCodeListVersion(String disseminationRuleCodeListVersion) {
    this.disseminationRuleCodeListVersion = disseminationRuleCodeListVersion;
  }

  /**
   * Indique la version de liste des codes pour les règles de réutilisation.
   *
   * @return la version de liste des codes pour les règles de réutilisation
   */
  public String getReuseRuleCodeListVersion() {
    return reuseRuleCodeListVersion;
  }

  /**
   * Spécifie la version de liste des codes pour les règles de réutilisation.
   *
   * @param reuseRuleCodeListVersion la version de liste des codes pour les règles de réutilisation
   */
  public void setReuseRuleCodeListVersion(String reuseRuleCodeListVersion) {
    this.reuseRuleCodeListVersion = reuseRuleCodeListVersion;
  }

  /**
   * Indique la version de la liste des codes pour les règles de classification.
   *
   * @return la version de la liste des codes pour les règles de classification
   */
  public String getClassificationRuleCodeListVersion() {
    return classificationRuleCodeListVersion;
  }

  /**
   * Spécifie la version de la liste des codes pour les règles de classification.
   *
   * @param classificationRuleCodeListVersion la version de la liste des codes pour les règles de
   *     classification
   */
  public void setClassificationRuleCodeListVersion(String classificationRuleCodeListVersion) {
    this.classificationRuleCodeListVersion = classificationRuleCodeListVersion;
  }

  /**
   * Indique la version de la liste de codes des modalités d'entrée.
   *
   * @return la version de la liste de codes des modalités d'entrée
   */
  public String getAcquisitionInformationCodeListVersion() {
    return acquisitionInformationCodeListVersion;
  }

  /**
   * Spécifie la version de la liste de codes des modalités d'entrée.
   *
   * @param acquisitionInformationCodeListVersion la version de la liste de codes des modalités
   *     d'entrée
   */
  public void setAcquisitionInformationCodeListVersion(
      String acquisitionInformationCodeListVersion) {
    this.acquisitionInformationCodeListVersion = acquisitionInformationCodeListVersion;
  }

  /**
   * Indique la valeur du hash code de l'objet.
   *
   * @return le hash code de l'objet
   */
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 43 * hash + Objects.hashCode(this.id);
    hash = 43 * hash + Objects.hashCode(this.authorizationReasonCodeListVersion);
    hash = 43 * hash + Objects.hashCode(this.fileEncodingCodeListVersion);
    hash = 43 * hash + Objects.hashCode(this.fileFormatCodeListVersion);
    hash = 43 * hash + Objects.hashCode(this.messageDigestAlgorithmCodeListVersion);
    hash = 43 * hash + Objects.hashCode(this.relationshipCodeListVersion);
    hash = 43 * hash + Objects.hashCode(this.replyCodeListVersion);
    hash = 43 * hash + Objects.hashCode(this.signatureStatusCodeListVersion);
    hash = 43 * hash + Objects.hashCode(this.mimeTypeCodeListVersion);
    hash = 43 * hash + Objects.hashCode(this.encodingCodeListVersion);
    hash = 43 * hash + Objects.hashCode(this.compressionAlgorithmCodeListVersion);
    hash = 43 * hash + Objects.hashCode(this.dataObjectVersionCodeListVersion);
    hash = 43 * hash + Objects.hashCode(this.storageRuleCodeListVersion);
    hash = 43 * hash + Objects.hashCode(this.appraisalRuleCodeListVersion);
    hash = 43 * hash + Objects.hashCode(this.accessRuleCodeListVersion);
    hash = 43 * hash + Objects.hashCode(this.disseminationRuleCodeListVersion);
    hash = 43 * hash + Objects.hashCode(this.reuseRuleCodeListVersion);
    hash = 43 * hash + Objects.hashCode(this.classificationRuleCodeListVersion);
    hash = 43 * hash + Objects.hashCode(this.acquisitionInformationCodeListVersion);
    return hash;
  }

  /**
   * Indique si un autre objet est égal à celui-ci.
   *
   * @param obj l'objet à vérifier
   * @return true si l'objet est identique, false sinon
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final CodeListVersions other = (CodeListVersions) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    if (!Objects.equals(
        this.authorizationReasonCodeListVersion, other.authorizationReasonCodeListVersion)) {
      return false;
    }
    if (!Objects.equals(this.fileEncodingCodeListVersion, other.fileEncodingCodeListVersion)) {
      return false;
    }
    if (!Objects.equals(this.fileFormatCodeListVersion, other.fileFormatCodeListVersion)) {
      return false;
    }
    if (!Objects.equals(
        this.messageDigestAlgorithmCodeListVersion, other.messageDigestAlgorithmCodeListVersion)) {
      return false;
    }
    if (!Objects.equals(this.relationshipCodeListVersion, other.relationshipCodeListVersion)) {
      return false;
    }
    if (!Objects.equals(this.replyCodeListVersion, other.replyCodeListVersion)) {
      return false;
    }
    if (!Objects.equals(
        this.signatureStatusCodeListVersion, other.signatureStatusCodeListVersion)) {
      return false;
    }
    if (!Objects.equals(this.mimeTypeCodeListVersion, other.mimeTypeCodeListVersion)) {
      return false;
    }
    if (!Objects.equals(this.encodingCodeListVersion, other.encodingCodeListVersion)) {
      return false;
    }
    if (!Objects.equals(
        this.compressionAlgorithmCodeListVersion, other.compressionAlgorithmCodeListVersion)) {
      return false;
    }
    if (!Objects.equals(
        this.dataObjectVersionCodeListVersion, other.dataObjectVersionCodeListVersion)) {
      return false;
    }
    if (!Objects.equals(this.storageRuleCodeListVersion, other.storageRuleCodeListVersion)) {
      return false;
    }
    if (!Objects.equals(this.appraisalRuleCodeListVersion, other.appraisalRuleCodeListVersion)) {
      return false;
    }
    if (!Objects.equals(this.accessRuleCodeListVersion, other.accessRuleCodeListVersion)) {
      return false;
    }
    if (!Objects.equals(
        this.disseminationRuleCodeListVersion, other.disseminationRuleCodeListVersion)) {
      return false;
    }
    if (!Objects.equals(this.reuseRuleCodeListVersion, other.reuseRuleCodeListVersion)) {
      return false;
    }
    if (!Objects.equals(
        this.classificationRuleCodeListVersion, other.classificationRuleCodeListVersion)) {
      return false;
    }
    return Objects.equals(
        this.acquisitionInformationCodeListVersion, other.acquisitionInformationCodeListVersion);
  }

  /**
   * Indique la représentation en tant que String de l'objet.
   *
   * @return la représentation en tant que String
   */
  @Override
  public String toString() {
    return "CodeListVersions{"
        + "id="
        + id
        + ", authorizationReasonCodeListVersion="
        + authorizationReasonCodeListVersion
        + ", fileEncodingCodeListVersion="
        + fileEncodingCodeListVersion
        + ", fileFormatCodeListVersion="
        + fileFormatCodeListVersion
        + ", messageDigestAlgorithmCodeListVersion="
        + messageDigestAlgorithmCodeListVersion
        + ", relationshipCodeListVersion="
        + relationshipCodeListVersion
        + ", replyCodeListVersion="
        + replyCodeListVersion
        + ", signatureStatusCodeListVersion="
        + signatureStatusCodeListVersion
        + ", mimeTypeCodeListVersion="
        + mimeTypeCodeListVersion
        + ", encodingCodeListVersion="
        + encodingCodeListVersion
        + ", compressionAlgorithmCodeListVersion="
        + compressionAlgorithmCodeListVersion
        + ", dataObjectVersionCodeListVersion="
        + dataObjectVersionCodeListVersion
        + ", storageRuleCodeListVersion="
        + storageRuleCodeListVersion
        + ", appraisalRuleCodeListVersion="
        + appraisalRuleCodeListVersion
        + ", accessRuleCodeListVersion="
        + accessRuleCodeListVersion
        + ", disseminationRuleCodeListVersion="
        + disseminationRuleCodeListVersion
        + ", reuseRuleCodeListVersion="
        + reuseRuleCodeListVersion
        + ", classificationRuleCodeListVersion="
        + classificationRuleCodeListVersion
        + ", acquisitionInformationCodeListVersion="
        + acquisitionInformationCodeListVersion
        + '}';
  }
}
