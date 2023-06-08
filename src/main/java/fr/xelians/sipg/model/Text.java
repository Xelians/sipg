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

import java.util.Objects;

/**
 * La classe Text représente un message localisable.
 *
 * <p>
 * Supporté en SEDA v2.1 et FNTC v4.
 * </p>
 *
 * @author Emmanuel Deviller
 */
public class Text {

    /**
     * Le message
     */
    protected final String message;

    /**
     * La langue du message
     */
    protected final String lang;

    /**
     * Instancie la classe.
     *
     * @param message le message
     */
    public Text(String message) {
        this(message, null);
    }

    /**
     * Instancie la classe.
     *
     * @param message le message
     * @param lang    la langue
     */
    @JsonCreator
    public Text(@JsonProperty("message") String message, @JsonProperty("lang") String lang) {
        this.message = message;
        this.lang = lang;
    }

    /**
     * Indique le message.
     *
     * @return le message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Indique la langue du message.
     *
     * @return la langue
     */
    public String getLang() {
        return lang;
    }

    /**
     * Indique la valeur du hash code de l'objet.
     *
     * @return le hash code de l'objet
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.message);
        hash = 67 * hash + Objects.hashCode(this.lang);
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
        final Text other = (Text) obj;
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return Objects.equals(this.lang, other.lang);
    }

    /**
     * Indique la représentation en tant que String de l'objet.
     *
     * @return la représentation en tant que String
     */
    @Override
    public String toString() {
        return "Text{" + "message=" + message + ", lang=" + lang + '}';
    }

}
