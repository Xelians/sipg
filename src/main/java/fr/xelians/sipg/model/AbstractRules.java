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
import org.apache.commons.lang3.Validate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe AbstractRules représente la liste des règles des archives.
 *
 * @author Emmanuel Deviller
 */
public abstract class AbstractRules {

    /**
     * La liste des règles.
     */
    protected final List<Rule> rules = new ArrayList<>();

    /**
     * La liste des identifiants des règles qui pourront être retirées de l'héritage dans ce nœud. Cette propriété est
     * ignorée en FNTC v4.
     */
    protected final List<String> preventRuleNames = new ArrayList<>();

    /**
     * Indique si les règles de gestion héritées des unités d'archives parentes doivent être ignorées pour l’unité d'archive
     * concernée. Cette propriété est ignorée en FNTC v4.
     */
    protected Boolean preventInheritance;

    /**
     * Instancie la classe.
     */
    protected AbstractRules() {
    }

    /**
     * Instancie la classe avec une règle spécifiée par les paramètres.
     *
     * @param name      la référence de la règle communicabilité
     * @param startDate date de départ de calcul de la règle de communicabilité
     */
    protected AbstractRules(String name, LocalDate startDate) {
        this.rules.add(new Rule(name, startDate));
    }

    /**
     * Ajoute une règle de communicabilité.
     *
     * @param name      la référence de la règle communicabilité à ajouter
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
     * Ajoute l'identifiant de la règle qui pourra être retirée de l'héritage dans ce nœud.
     *
     * @param ruleName l'identifiant de la règle
     */
    public void addPreventRuleName(String ruleName) {
        Validate.notNull(ruleName, SipUtils.NOT_NULL, "ruleName");
        preventRuleNames.add(ruleName);
    }

    /**
     * Supprime l'identifiant de la règle qui pourra être retirée de l'héritage dans ce nœud.
     *
     * @param ruleName l'identifiant de la règle
     * @return true si la suppression de l'identifiant a réalisé avec succès, false sinon
     */
    public boolean removePreventRuleName(String ruleName) {
        Validate.notNull(ruleName, SipUtils.NOT_NULL, "ruleName");
        return preventRuleNames.remove(ruleName);
    }

    /**
     * Fournit la liste des identifiants des règles qui pourront être retirées de l'héritage dans ce nœud.
     *
     * @return la liste des identifiants
     */
    public List<String> getPreventRuleNames() {
        return new ArrayList<>(preventRuleNames);
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

    /**
     * Indique si les règles de gestion héritées des unités d'archives parentes doivent être ignorées pour l’unité d'archive
     * concernée.
     *
     * @return l'interdiction d'hériter
     */
    public Boolean isPreventInheritance() {
        return preventInheritance;
    }

    /**
     * Spécifie si les règles de gestion héritées des unités d'archives parentes doivent être ignorées pour l’unité
     * d'archive concernée.
     *
     * @param preventInheritance interdiction d'hériter
     */
    public void setPreventInheritance(Boolean preventInheritance) {
        this.preventInheritance = preventInheritance;
    }

}
