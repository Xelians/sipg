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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * La classe Validator représente le validateur de la signature.
 *
 * <p>Supporté en SEDA v2.1
 *
 * @author Emmanuel Deviller
 * @see Agent
 */
@JsonDeserialize(builder = ValidatorBuilder.class)
public class Validator extends Agent {

  /** Date et heure de validation de la signature. */
  protected LocalDateTime validationTime;

  /** Instancie la classe. */
  public Validator() {
    super();
  }

  /**
   * Instancie la classe avec l'identifiant de l'agent.
   *
   * @param validationTime la date et heure de validation
   */
  public Validator(LocalDateTime validationTime) {
    super();
    this.validationTime = validationTime;
  }

  /**
   * Instancie la classe avec l'identifiant de l'agent.
   *
   * @param identifier l'identifiant
   * @param validationTime la date et heure de validation
   */
  public Validator(String identifier, LocalDateTime validationTime) {
    super(identifier);
    this.validationTime = validationTime;
  }

  /**
   * Instancie la classe selon les paramètres indiqués.
   *
   * @param firstName le prénom
   * @param birthName le nom de naissance
   * @param fullName le nom complet
   * @param givenName le nom d'usage
   * @param gender le sexe
   * @param birthDate la date de naissance
   * @param birthPlace le lieu de naissance
   * @param deathDate la date de décès
   * @param deathPlace le lieu de décès
   * @param corpName l'entité
   * @param validationTime la date et heure de validation
   */
  public Validator(
      String firstName,
      String birthName,
      String fullName,
      String givenName,
      String gender,
      LocalDate birthDate,
      Place birthPlace,
      LocalDate deathDate,
      Place deathPlace,
      String corpName,
      LocalDateTime validationTime) {

    super(
        firstName,
        birthName,
        fullName,
        givenName,
        gender,
        birthDate,
        birthPlace,
        deathDate,
        deathPlace,
        corpName);
    this.validationTime = validationTime;
  }

  /**
   * Indique la date et l'heure de validation de la signature.
   *
   * @return la date et heure de validation
   */
  public LocalDateTime getValidationTime() {
    return validationTime;
  }

  /**
   * Spécifie la date et l'heure de validation de la signature.
   *
   * @param validationTime la date et heure de validation
   */
  public void setValidationTime(LocalDateTime validationTime) {
    this.validationTime = validationTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Validator validator = (Validator) o;
    return Objects.equals(validationTime, validator.validationTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), validationTime);
  }
}
