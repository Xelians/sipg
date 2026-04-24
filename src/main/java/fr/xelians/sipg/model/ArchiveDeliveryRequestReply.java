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

/**
 * La classe ArchiveTransfer représente une archive à transférer. Elle peut contenir une ou
 * plusieurs unités d'archives.
 *
 * <p>Supporté en SEDA v2.1 et FNTC v4.
 *
 * @author Emmanuel Deviller
 * @see Message
 * @see ArchiveUnit
 */
// Note. Add @XmlRootElement(name = "ArchiveTransfer") on ArchiveTransferType
public class ArchiveDeliveryRequestReply extends Message implements ArchiveUnitContainer {

  /** Liste des unités d'archives contenues dans l'archive. */
  protected final ArrayList<ArchiveUnit> archiveUnits;

  /** Listes de codes de références utilisés dans le message. */
  protected CodeListVersions codeListVersions;

  /** Accord de service. */
  protected String archivalAgreement;

  /** Service d'archives responsable du transfert. */
  protected Agency archivalAgency;

  /** Le code de retour (OK, KO, etc.) */
  protected String replyCode;

  /** Identifiant de la requête qui a initié la réponse. */
  protected String messageRequestIdentifier;

  /** Identifiant de l'archive unit */
  protected String unitIdentifier;

  /** Identifient de l'agence à l'origine du SIP */
  protected String originatingAgencyIdentifier;

  public String getOriginatingAgencyIdentifier() {
    return originatingAgencyIdentifier;
  }

  public void setOriginatingAgencyIdentifier(String originatingAgencyIdentifier) {
    this.originatingAgencyIdentifier = originatingAgencyIdentifier;
  }

  /** Demandeur */
  protected Agency requester;

  /** Instancie la classe. */
  public ArchiveDeliveryRequestReply() {
    super();
    archiveUnits = new ArrayList<>();
    codeListVersions = new CodeListVersions();
  }

  /**
   * Indique les codes de références utilisés dans le message.
   *
   * @return les codes de références
   */
  public CodeListVersions getCodeListVersions() {
    return codeListVersions;
  }

  /**
   * Spécifie les codes de références utilisés dans le message.
   *
   * @param codeListVersions les codes de références
   */
  public void setCodeListVersions(CodeListVersions codeListVersions) {
    this.codeListVersions = codeListVersions;
  }

  /**
   * Indique l'accord de service.
   *
   * @return l 'accord de service
   */
  public String getArchivalAgreement() {
    return archivalAgreement;
  }

  /**
   * Spécifie l'accord de service.
   *
   * @param archivalAgreement l'accord de service
   */
  public void setArchivalAgreement(String archivalAgreement) {
    this.archivalAgreement = archivalAgreement;
  }

  /**
   * Ajoute une unité d'archive à l'archive.
   *
   * @param archiveUnit l'unité d'archive
   */
  @Override
  public void addArchiveUnit(ArchiveUnit archiveUnit) {
    archiveUnits.add(archiveUnit);
  }

  /**
   * Supprime une unité d'archive de l'archive.
   *
   * @param archiveUnit l'unité d'archive
   * @return true si la suppression de l'élément a été réalisée avec succès, false sinon
   */
  @Override
  public boolean removeArchiveUnit(ArchiveUnit archiveUnit) {
    return archiveUnits.remove(archiveUnit);
  }

  /**
   * Fournit la liste des unités d'archives contenues dans l'archive.
   *
   * @return la liste des unités d'archives
   */
  @Override
  public ArrayList<ArchiveUnit> getArchiveUnits() {
    return new ArrayList<>(archiveUnits);
  }

  /**
   * Indique le service d'archives responsable du transfert.
   *
   * @return le service d'archives
   */
  public Agency getArchivalAgency() {
    return archivalAgency;
  }

  /**
   * Spécifie le service d'archives responsable du transfert.
   *
   * @param archivalAgency le service d'archives
   */
  public void setArchivalAgency(Agency archivalAgency) {
    this.archivalAgency = archivalAgency;
  }

  /**
   * Spécifie le service d'archives responsable du transfert.
   *
   * @param agencyIdentifier l'identifiant du service d'archives
   * @param agencyName le nom du service d'archives
   */
  public void setArchivalAgency(String agencyIdentifier, String agencyName) {
    this.archivalAgency = new Agency(agencyIdentifier, agencyName);
  }

  public String getReplyCode() {
    return replyCode;
  }

  public void setReplyCode(String replyCode) {
    this.replyCode = replyCode;
  }

  public String getMessageRequestIdentifier() {
    return messageRequestIdentifier;
  }

  public void setMessageRequestIdentifier(String messageRequestIdentifier) {
    this.messageRequestIdentifier = messageRequestIdentifier;
  }

  public String getUnitIdentifier() {
    return unitIdentifier;
  }

  public void setUnitIdentifier(String unitIdentifier) {
    this.unitIdentifier = unitIdentifier;
  }

  public Agency getRequester() {
    return requester;
  }

  public void setRequester(Agency requester) {
    this.requester = requester;
  }
}
