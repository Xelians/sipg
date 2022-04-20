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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.xelians.sipg.utils.SipUtils;
import org.apache.commons.lang3.Validate;

import java.time.LocalDate;
import java.util.Objects;

/**
 * La classe Rule représente une règle de cycle de vie.
 *
 * <p>
 * Supporté en SEDA v2.1 .
 * </p>
 *
 * @author Emmanuel Deviller
 */
public class Rule {

    /**
     * Le nom de la règle.
     */
    protected final String name;

    /**
     * La date de départ du calcul de la règle.
     */
    protected final LocalDate startDate;

    /**
     * Instancie la classe avec le arguments spécifiés.
     *
     * @param name      le nom
     * @param startDate la date de départ du calcul
     */
    @JsonCreator
    public Rule(@JsonProperty("name") String name, @JsonProperty("startDate") LocalDate startDate) {
        Validate.notNull(name, SipUtils.NOT_NULL, "name");
        this.name = name;
        this.startDate = startDate;
    }

    /**
     * Indique la nom de la règle
     *
     * @return le nom de la règle
     */
    public String getName() {
        return name;
    }

    /**
     * Indique la date de départ du calcul de la règle.
     *
     * @return la date de départ du calcul
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Indique si un autre objet est égal à celui-ci.
     *
     * @param obj l'objet à vérifier
     * @return true si l'objet est identique, false sinon
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Rule rule = (Rule) obj;
        return name.equals(rule.name)
                && Objects.equals(startDate, rule.startDate);
    }

    /**
     * Indique la valeur du hash code de l'objet.
     *
     * @return le hash code de l'objet
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, startDate);
    }

    /**
     * Indique la représentation en tant que String de l'objet.
     *
     * @return la représentation en tant que String
     */
    @Override
    public String toString() {
        return "Rule{" + "name=" + name + ", startDate=" + startDate + '}';
    }
}
