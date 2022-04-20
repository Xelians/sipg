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

import java.util.Objects;

/**
 * La classe Tag représente un mot-clé.
 *
 * <p>
 * Supporté en SEDA v2.1 .
 * </p>
 *
 * @author Emmanuel Deviller
 */
public class Tag {

    /**
     * La clé du mot-clé.
     */
    protected final String key;

    /**
     * La valeur du mot-clé.
     */
    protected final String value;

    /**
     * Instancie la classe.
     *
     * @param key   le clé du mot-clé
     * @param value la valeur du mot-clé
     */
    @JsonCreator
    public Tag(@JsonProperty("key") String key, @JsonProperty("value") String value) {
        Validate.notNull(value, SipUtils.NOT_NULL, "value");

        this.key = key;
        this.value = value;
    }

    /**
     * Indique la clé du mot-clé.
     *
     * @return la clé
     */
    public String getKey() {
        return key;
    }

    /**
     * Indique la valeur du mot-clé.
     *
     * @return la valeur
     */
    public String getValue() {
        return value;
    }

    /**
     * Indique si un autre objet est égal à celui-ci.
     *
     * @param o l'objet à vérifier
     * @return true si l'objet est identique, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tag stringTag = (Tag) o;
        return Objects.equals(key, stringTag.key)
                && Objects.equals(value, stringTag.value);
    }

    /**
     * Indique la valeur du hash code de l'objet.
     *
     * @return le hash code de l'objet
     */
    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    /**
     * Indique la représentation en tant que String de l'objet.
     *
     * @return la représentation en tant que String
     */
    @Override
    public String toString() {
        return "Tag{" + "key=" + key + ", value=" + value + '}';
    }

}
