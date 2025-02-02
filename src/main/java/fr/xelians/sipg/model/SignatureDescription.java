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
 * La classe SignatureDescription contient la description de la signature.
 *
 * <p>Supporté en SEDA v2.3
 *
 * @author Emmanuel Deviller
 * @see SigningInformation
 */
public class SignatureDescription {

  /** Le signataire. */
  protected Signer signer;

  /** Le validateur de la signature. */
  protected Validator validator;

  /** Type de signature, au sens juridique du terme. Par exemple, simple, avancée, qualifiée. */
  protected String signingType;

  /** Instancie une nouvelle description de signature. */
  public SignatureDescription() {
    // Needed for jackson
  }

  /**
   * Instancie une nouvelle description de signature.
   *
   * @param signer le signataire
   * @param validator le validateur
   * @param signingType le type de signature
   */
  public SignatureDescription(Signer signer, Validator validator, String signingType) {
    this.signer = signer;
    this.validator = validator;
    this.signingType = signingType;
  }

  /**
   * Indique le signataire.
   *
   * @return le signataire
   */
  public Signer getSigner() {
    return signer;
  }

  /**
   * Spécifie le signataire.
   *
   * @param signer le signataire
   */
  public void setSigner(Signer signer) {
    this.signer = signer;
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
   * Indique le type de signature au sens juridique du terme. Par exemple, simple, avancée, qualifiée.
   *
   * @return le type de signature
   */
  public String getSigningType() {
    return signingType;
  }

  /**
   * Spécifie le type de signature au sens juridique du terme. Par exemple, simple, avancée, qualifiée.
   *
   * @param signingType le type de signature
   */
  public void setSigningType(String signingType) {
    this.signingType = signingType;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;

    SignatureDescription that = (SignatureDescription) o;
    return Objects.equals(getSigner(), that.getSigner())
        && Objects.equals(getValidator(), that.getValidator())
        && Objects.equals(getSigningType(), that.getSigningType());
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(getSigner());
    result = 31 * result + Objects.hashCode(getValidator());
    result = 31 * result + Objects.hashCode(getSigningType());
    return result;
  }
}
