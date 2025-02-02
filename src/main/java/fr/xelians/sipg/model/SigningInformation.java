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

/**
 * La classe SigningInformation contient toutes les informations relatives à la signature.
 *
 * <p>Supporté en SEDA v2.3
 *
 * @author Emmanuel Deviller
 * @see Signer
 * @see Validator
 */
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
   * Indique la liste des rôles de l'unité d'archives.
   *
   * @return la liste des rôles
   */
  public List<SigningRole> getSigningRoles() {
    return signingRoles;
  }

  /**
   * Indique la liste des références aux rôles des unités d'archives.
   *
   * @return la liste des références aux rôles
   */
  public List<DetachedSigningRole> getDetachedSigningRoles() {
    return detachedSigningRoles;
  }

  /**
   * Indique la liste des relations techniques à l'unité d'archives racine décrivant le document signé à partir des unités d'archives encapsulées contenant les autres rôles d'un contexte de signature.
   *
   * @return la liste des relations techniques
   */
  public List<String> getSignedDocumentReferenceIds() {
    return signedDocumentReferenceIds;
  }

  /**
   * Indique la liste des informations d'horodatage dans un contexte de signature.
   *
   * @return la liste des informations d'horodatage
   */
  public List<TimestampingInformation> getTimestampingInformations() {
    return timestampingInformations;
  }

  /**
   * Indique la liste des informations relatives aux preuves complémentaires archivées dans un contexte de signature.
   *
   * @return la liste des informations relatives aux preuves complémentaires
   */
  public List<String> getAdditionalProofInformation() {
    return additionalProofInformation;
  }

  /**
   * Indique la liste permettant de décrire la signature dans un contexte de signature.
   *
   * @return la liste permettant de décrire la signature
   */
  public List<SignatureDescription> getSignatureDescriptions() {
    return signatureDescriptions;
  }

}
