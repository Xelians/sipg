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

import java.util.ArrayList;
import java.util.List;

/** Contient toutes les informations relatives à la signature. */
public class SigningInformation {

  /**
   * Liste des rôles de l'unité d'archives dans un contexte de signature. Quatre rôles (étiquettes)
   * ont été identifiés : document signé, signature, horodatage et preuves complémentaires.
   */
  protected List<SigningRole> signingRoles = new ArrayList<>();

  /**
   * Liste des références aux rôles des unités d'archives encapsulées sous la racine contenant le
   * document signé.
   */
  protected List<DetachedSigningRole> detachedSigningRoles = new ArrayList<>();

  /**
   * Liste des relations techniques à l'unité d'archives racine décrivant le document signé à partir
   * des unités d'archives encapsulées contenant les autres rôles d'un contexte de signature.
   */
  protected List<String> signedDocumentReferenceIds = new ArrayList<>();

  /** Liste des informations d'horodatage dans un contexte de signature. */
  protected List<TimestampingInformation> timestampingInformations = new ArrayList<>();

  /**
   * Liste des informations relatives aux preuves complémentaires archivées dans un contexte de
   * signature.
   */
  protected List<String> additionalProofInformation = new ArrayList<>();

  /** Liste permettant de décrire la signature dans un contexte de signature. */
  protected List<SignatureDescription> signatureDescriptions = new ArrayList<>();

  /** Instancie la classe. */
  public SigningInformation() {
    // Needed for jackson
  }

  /**
   * Gets signing roles.
   *
   * @return the signing roles
   */
  public List<SigningRole> getSigningRoles() {
    return signingRoles;
  }

  /**
   * Sets signing roles.
   *
   * @param signingRoles the signing roles
   */
  public void setSigningRoles(List<SigningRole> signingRoles) {
    this.signingRoles = signingRoles;
  }

  /**
   * Gets detached signing roles.
   *
   * @return the detached signing roles
   */
  public List<DetachedSigningRole> getDetachedSigningRoles() {
    return detachedSigningRoles;
  }

  /**
   * Sets detached signing roles.
   *
   * @param detachedSigningRoles the detached signing roles
   */
  public void setDetachedSigningRoles(List<DetachedSigningRole> detachedSigningRoles) {
    this.detachedSigningRoles = detachedSigningRoles;
  }

  /**
   * Gets signed document reference ids.
   *
   * @return the signed document reference ids
   */
  public List<String> getSignedDocumentReferenceIds() {
    return signedDocumentReferenceIds;
  }

  /**
   * Sets signed document reference ids.
   *
   * @param signedDocumentReferenceIds the signed document reference ids
   */
  public void setSignedDocumentReferenceIds(List<String> signedDocumentReferenceIds) {
    this.signedDocumentReferenceIds = signedDocumentReferenceIds;
  }

  /**
   * Gets timestamping informations.
   *
   * @return the timestamping informations
   */
  public List<TimestampingInformation> getTimestampingInformations() {
    return timestampingInformations;
  }

  /**
   * Sets timestamping informations.
   *
   * @param timestampingInformations the timestamping informations
   */
  public void setTimestampingInformations(List<TimestampingInformation> timestampingInformations) {
    this.timestampingInformations = timestampingInformations;
  }

  /**
   * Gets additional proof information.
   *
   * @return the additional proof information
   */
  public List<String> getAdditionalProofInformation() {
    return additionalProofInformation;
  }

  /**
   * Sets additional proof information.
   *
   * @param additionalProofInformation the additional proof information
   */
  public void setAdditionalProofInformation(List<String> additionalProofInformation) {
    this.additionalProofInformation = additionalProofInformation;
  }

  /**
   * Gets signature descriptions.
   *
   * @return the signature descriptions
   */
  public List<SignatureDescription> getSignatureDescriptions() {
    return signatureDescriptions;
  }

  /**
   * Sets signature descriptions.
   *
   * @param signatureDescriptions the signature descriptions
   */
  public void setSignatureDescriptions(List<SignatureDescription> signatureDescriptions) {
    this.signatureDescriptions = signatureDescriptions;
  }
}
