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
public class ArchiveTransfer extends Message implements ArchiveUnitContainer {

  /** Liste des unités d'archives contenues dans l'archive. */
  protected final ArrayList<ArchiveUnit> archiveUnits;

  /** Listes de codes de références utilisés dans le message. */
  protected CodeListVersions codeListVersions;

  /** Accord de service. */
  protected String archivalAgreement;

  /** Service d'archives responsable du transfert. */
  protected Agency archivalAgency;

  /** Service versant chargé de réaliser le transport. */
  protected Agency transferringAgency;

  /** Profil d’archivage applicable aux unités d'archives. */
  protected String archivalProfile;

  /** Niveau de service applicable aux unités d’archives. */
  protected String serviceLevel;

  /** Modalités d'entrée des archives. */
  protected String acquisitionInformation;

  /** Statut légal des archives. */
  protected String legalStatus;

  /**
   * Identifiant du service producteur. Information de gestion à ne pas confondre avec
   * OriginatingAgency dans les métadonnées de description.
   */
  protected String originatingAgencyIdentifier;

  /**
   * Identifiant du service versant. Information de gestion à ne pas confondre avec SubmissionAgency
   * dans les métadonnées de description.
   */
  protected String submissionAgencyIdentifier;

  /** Instancie la classe. */
  public ArchiveTransfer() {
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

  /**
   * Indique le service versant chargé de réaliser le transport.
   *
   * @return le service versant
   */
  public Agency getTransferringAgency() {
    return transferringAgency;
  }

  /**
   * Spécifie le service versant chargé de réaliser le transport.
   *
   * @param transferringAgency le service versant
   */
  public void setTransferringAgency(Agency transferringAgency) {
    this.transferringAgency = transferringAgency;
  }

  /**
   * Spécifie le service versant chargé de réaliser le transport.
   *
   * @param agencyIdentifier l'identifiant du service versant
   * @param agencyName le nom du service versant
   */
  public void setTransferringAgency(String agencyIdentifier, String agencyName) {
    this.transferringAgency = new Agency(agencyIdentifier, agencyName);
  }

  /**
   * Indique le profil d’archivage applicable aux unités d'archives.
   *
   * @return le profil d’archivage
   */
  public String getArchivalProfile() {
    return archivalProfile;
  }

  /**
   * Spécifie le profil d’archivage applicable aux unités d'archives.
   *
   * @param archivalProfile le profil d’archivage
   */
  public void setArchivalProfile(String archivalProfile) {
    this.archivalProfile = archivalProfile;
  }

  /**
   * Indique le niveau de service applicable aux unités d’archives.
   *
   * @return le niveau de service
   */
  public String getServiceLevel() {
    return serviceLevel;
  }

  /**
   * Spécifie le niveau de service applicable aux unités d’archives.
   *
   * @param serviceLevel le niveau de service
   */
  public void setServiceLevel(String serviceLevel) {
    this.serviceLevel = serviceLevel;
  }

  /**
   * Indique les modalités d'entrée des archives.
   *
   * @return les modalités d'entrée
   */
  public String getAcquisitionInformation() {
    return acquisitionInformation;
  }

  /**
   * Spécifie les modalités d'entrée des archives.
   *
   * @param acquisitionInformation les modalités d'entrée
   */
  public void setAcquisitionInformation(String acquisitionInformation) {
    this.acquisitionInformation = acquisitionInformation;
  }

  /**
   * Indique le statut légal des archives.
   *
   * @return le statut légal
   */
  public String getLegalStatus() {
    return legalStatus;
  }

  /**
   * Spécifie le statut légal des archives.
   *
   * @param legalStatus le statut des archives
   */
  public void setLegalStatus(String legalStatus) {
    this.legalStatus = legalStatus;
  }

  /**
   * Indique l'identifiant du service producteur. Information de gestion à ne pas confondre avec
   * OriginatingAgency dans les métadonnées de description.
   *
   * @return l'identifiant du service producteur
   */
  public String getOriginatingAgencyIdentifier() {
    return originatingAgencyIdentifier;
  }

  /**
   * Spécifie l'identifiant du service producteur. Iinformation de gestion à ne pas confondre avec
   * OriginatingAgency dans les métadonnées de description.
   *
   * @param originatingAgencyIdentifier l'identifiant du service producteur
   */
  public void setOriginatingAgencyIdentifier(String originatingAgencyIdentifier) {
    this.originatingAgencyIdentifier = originatingAgencyIdentifier;
  }

  /**
   * Indique l'identifiant du service versant. Information de gestion à ne pas confondre avec
   * SubmissionAgency dans les métadonnées de description.
   *
   * @return l'identifiant du service versant
   */
  public String getSubmissionAgencyIdentifier() {
    return submissionAgencyIdentifier;
  }

  /**
   * Spécifie l'identifiant du service versant. Information de gestion à ne pas confondre avec
   * SubmissionAgency dans les métadonnées de description.
   *
   * @param submissionAgencyIdentifier l'identifiant du service versant
   */
  public void setSubmissionAgencyIdentifier(String submissionAgencyIdentifier) {
    this.submissionAgencyIdentifier = submissionAgencyIdentifier;
  }
}
