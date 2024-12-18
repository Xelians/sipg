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

import fr.xelians.sipg.utils.SipUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Validate;

/**
 * La classe Signature contient les informations relatives à une signature.
 *
 * <p>Supporté en SEDA v2.1
 *
 * @author Emmanuel Deviller
 * @see Signer
 * @see Validator
 */
public class Signature {

  /** La liste des signataires. */
  protected final List<Signer> signers = new ArrayList<>();

  /** La validateur de la signature. */
  protected Validator validator;

  /** L'algorithme utilisé par la signature. */
  protected String digestAlgorithm = "SHA-512";

  /** La condensat de la signature. */
  protected String digestValue;

  /** Instancie la classe. */
  public Signature() {
    // Do nothing here
  }

  /**
   * Ajoute un signataire à la liste des signataires.
   *
   * @param signer le signataire à ajouter
   */
  public void addSigner(Signer signer) {
    Validate.notNull(signer, SipUtils.NOT_NULL, "signer");
    signers.add(signer);
  }

  /**
   * Supprime un signataire de la liste des signataires.
   *
   * @param signer le signataire à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeSigner(Signer signer) {
    Validate.notNull(signer, SipUtils.NOT_NULL, "signer");
    return signers.remove(signer);
  }

  /**
   * Fournit les signataires de la signature.
   *
   * @return la liste des signataires
   */
  public List<Signer> getSigners() {
    return new ArrayList<>(signers);
  }

  /**
   * Indique le validateur.
   *
   * @return le validateur
   */
  public Validator getValidator() {
    return validator;
  }

  /**
   * Spécifie le validateur.
   *
   * @param validator le validateur
   */
  public void setValidator(Validator validator) {
    this.validator = validator;
  }

  /**
   * Indique l'algorithme utilisé par la signature.
   *
   * @return l 'algorithm
   */
  public String getDigestAlgorithm() {
    return digestAlgorithm;
  }

  /**
   * Indique l'algorithme utilisé par la signature.
   *
   * @param digestAlgorithm l'algorithme
   */
  public void setDigestAlgorithm(String digestAlgorithm) {
    this.digestAlgorithm = digestAlgorithm;
  }

  /**
   * Indique le condensat de la signature.
   *
   * @return le condensat
   */
  public String getDigestValue() {
    return digestValue;
  }

  /**
   * Spécifie le condensat de la signature.
   *
   * @param digestValue le condensat
   */
  public void setDigestValue(String digestValue) {
    this.digestValue = digestValue;
  }
}
