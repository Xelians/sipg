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

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * La classe CustodialItem représente la description d'une période ou d'un événement précis dans l'historique.
 *
 * <p>
 * Supporté en SEDA v2.1 et FNTC v4 (étendu).
 * </p>
 *
 * @author Emmanuel Deviller
 */
public class CustodialItem {

    /**
     * L'évènement.
     */
    protected final String value;

    /**
     * La date de l'évènement.
     */
    protected final LocalDateTime when;

    /**
     * Instancie la classe avec l'évènement spécifié.
     *
     * @param value l'évènement
     */
    public CustodialItem(String value) {
        this(value, null);
    }

    /**
     * Instancie la classe avec l'évènement et la date de l'évènement.
     *
     * @param value l'évènement
     * @param when  la date
     */
    @JsonCreator
    public CustodialItem(@JsonProperty("value") String value, @JsonProperty("when") LocalDateTime when) {
        Validate.notNull(value, SipUtils.NOT_NULL, "value");
        this.value = value;
        this.when = when;
    }

    /**
     * Indique l'évènement.
     *
     * @return l'évènement
     */
    public String getValue() {
        return value;
    }

    /**
     * Indique la date de l'évènement.
     *
     * @return la date
     */
    public LocalDateTime getWhen() {
        return when;
    }

    /**
     * Indique la valeur du hash code de l'objet.
     *
     * @return le hash code de l'objet
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.value);
        hash = 79 * hash + Objects.hashCode(this.when);
        return hash;
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
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CustodialItem other = (CustodialItem) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return Objects.equals(this.when, other.when);
    }

    /**
     * Indique la représentation en tant que String de l'objet.
     *
     * @return la représentation en tant que String
     */
    @Override
    public String toString() {
        return "CustodialItem{" + "value=" + value + ", when=" + when + '}';
    }
}
