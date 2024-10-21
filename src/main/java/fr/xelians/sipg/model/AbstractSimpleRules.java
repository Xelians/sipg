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

public abstract class AbstractSimpleRules extends AbstractRules {

  /** La liste des règles. */
  protected final List<Rule> rules = new ArrayList<>();

  /** Instancie la classe. */
  protected AbstractSimpleRules() {
    super();
  }

  /**
   * Instancie la classe avec une règle spécifiée par les paramètres.
   *
   * @param name la référence de la règle communicabilité
   * @param startDate date de départ de calcul de la règle de communicabilité
   */
  protected AbstractSimpleRules(String name, LocalDate startDate) {
    super();
    addRule(name, startDate);
  }

  /**
   * Ajoute une règle de communicabilité.
   *
   * @param name la référence de la règle communicabilité à ajouter
   * @param startDate date de départ de calcul de la règle de communicabilité
   */
  public void addRule(String name, LocalDate startDate) {
    Rule rule = new Rule(name, startDate);
    rules.add(rule);
  }

  /**
   * Ajoute une règle de communicabilité.
   *
   * @param rule règle de communicabilité
   */
  public void addRule(Rule rule) {
    Validate.notNull(rule, SipUtils.NOT_NULL, "rule");
    rules.add(rule);
  }

  /**
   * Supprime une règle de communicabilité.
   *
   * @param rule la règle de communicabilité à supprimer
   * @return true si la suppression de la règle a réalisé avec succès, false sinon
   */
  public boolean removeRule(Rule rule) {
    Validate.notNull(rule, SipUtils.NOT_NULL, "rule");
    return rules.remove(rule);
  }

  /**
   * Fournit la liste des règles de communicabilité.
   *
   * @return la liste des règles de communicabilité
   */
  public List<Rule> getRules() {
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
