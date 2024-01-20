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
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe AbstractRules représente la liste des règles des archives.
 *
 * @author Emmanuel Deviller
 */
public abstract class AbstractRules {


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
