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

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.xelians.sipg.utils.SipUtils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Validate;

/**
 * La classe HoldRules représente les règles de gel des archives.
 *
 * <p>En SEDA 2.1, chaque règle a une startDate et un Identifiant de règle de référence pour la
 * durée. Si aucune règle n'est spécifiée et que la date actuelle est dans la StarDate, la réponse
 * de restriction est "Aucune restriction". Si la date est vide, la réponse de restriction est
 * "Restreint" car il n'y a aucun moyen de calculer la date de fin. Si une règle et une date sont
 * précisées, alors la règle est valable (restriction appliquée).
 *
 * <p>Supporté en SEDA v2.2.
 *
 * @author Emmanuel Deviller
 */
public class HoldRules extends AbstractRules {

  /** La liste des règles. */
  protected final List<HoldRule> rules = new ArrayList<>();

  /** Instancie la classe. */
  public HoldRules() {
    super();
  }

  /**
   * Instancie la classe avec la règle spécifiée par les paramètres.
   *
   * @param name la référence de la règle de diffusion
   * @param startDate date de départ de calcul de la règle de diffusion
   */
  public HoldRules(
      String name,
      LocalDate startDate,
      LocalDate holdEndDate,
      String holdOwner,
      String holdReason,
      LocalDate holdReassessingDate,
      Boolean preventRearrangement) {
    super();
    addRule(
        name,
        startDate,
        holdEndDate,
        holdOwner,
        holdReason,
        holdReassessingDate,
        preventRearrangement);
  }

  /**
   * Ajoute une règle de communicabilité.
   *
   * @param name la référence de la règle communicabilité à ajouter
   * @param startDate date de départ de calcul de la règle de communicabilité
   */
  public void addRule(
      String name,
      LocalDate startDate,
      LocalDate holdEndDate,
      String holdOwner,
      String holdReason,
      LocalDate holdReassessingDate,
      Boolean preventRearrangement) {
    HoldRule rule =
        new HoldRule(
            name,
            startDate,
            holdEndDate,
            holdOwner,
            holdReason,
            holdReassessingDate,
            preventRearrangement);
    rules.add(rule);
  }

  /**
   * Ajoute une règle de communicabilité.
   *
   * @param rule règle de communicabilité
   */
  public void addRule(HoldRule rule) {
    Validate.notNull(rule, SipUtils.NOT_NULL, "rule");
    rules.add(rule);
  }

  /**
   * Supprime une règle de communicabilité.
   *
   * @param rule la règle de communicabilité à supprimer
   * @return true si la suppression de la règle a réalisé avec succès, false sinon
   */
  public boolean removeRule(HoldRule rule) {
    Validate.notNull(rule, SipUtils.NOT_NULL, "rule");
    return rules.remove(rule);
  }

  /**
   * Fournit la liste des règles de gel.
   *
   * @return la liste des règles de gel
   */
  public List<HoldRule> getRules() {
    return new ArrayList<>(rules);
  }

  /**
   * Indique la date de départ de calcul de la première règle de communicabilité.
   *
   * @return la date
   */
  @JsonIgnore
  public String getRuleName() {
    return rules.isEmpty() ? null : rules.get(0).getName();
  }

  /**
   * Indique la date de départ de calcul de la première règle de communicabilité.
   *
   * @return la date
   */
  @JsonIgnore
  public LocalDate getStartDate() {
    return rules.isEmpty() ? null : rules.get(0).getStartDate();
  }
}
