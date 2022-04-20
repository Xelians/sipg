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

import java.util.*;

/**
 * La classe Element représente un élément utilisé dans le cadre d'une extension de l'ontologie définie généralement par
 * le schéma XSD du standard d'échange. Il est composé d'un nom, d'une valeur et d'un ensemble d'attributs. Un élément
 * peut également contenir une liste d'éléments enfants.
 *
 * <p>
 * Supporté en SEDA v2.1 .
 * </p>
 *
 * @author Emmanuel Deviller
 */
public class Element {

    /**
     * Le nom de l'élément.
     */
    protected String name;

    /**
     * La valeur de l'élément.
     */
    protected String value;

    /**
     * Les attributs de l'élément.
     */
    protected final Map<String, String> attributes;

    /**
     * La liste des éléments enfants.
     */
    protected final List<Element> elements = new ArrayList<>();

    /**
     * Instancie la classe avec le nom du tag.
     *
     * @param name la nome
     */
    public Element(String name) {
        Validate.notNull(name, SipUtils.NOT_NULL, "name");

        this.name = name;
        this.attributes = new HashMap<>();
    }

    /**
     * Instancie la classe avec le nom et la valeur du tag.
     *
     * @param name  le nom
     * @param value la valeur
     */
    public Element(String name, String value) {
        Validate.notNull(name, SipUtils.NOT_NULL, "name");

        this.name = name;
        this.value = value;
        this.attributes = new HashMap<>();
    }

    /**
     * Instancie la classe avec le nom, la valeur et un ensemble d'attributs..
     *
     * @param name       le nom
     * @param value      la valeur
     * @param attributes l'ensemble d'attributs
     */
    @JsonCreator
    public Element(@JsonProperty("name") String name,
                   @JsonProperty("value") String value,
                   @JsonProperty("attributes") Map<String, String> attributes) {

        Validate.notNull(name, SipUtils.NOT_NULL, "name");

        this.name = name;
        this.value = value;
        this.attributes = attributes == null ? new HashMap<>() : new HashMap<>(attributes);
    }

    /**
     * Indique le nom de l'élément.
     *
     * @return le nom
     */
    public String getName() {
        return name;
    }

    /**
     * Spécifie le nom de l'élément.
     *
     * @param name le nom
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Indique la valeur de l'élément.
     *
     * @return la valeur
     */
    public String getValue() {
        return value;
    }

    /**
     * Spécifie la valeur de l'élément.
     *
     * @param value la valeur
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Ajoute un attribut à l'élément.
     *
     * @param key   la clé de l'attribut
     * @param value la valeur de l'attribut
     */
    public void addAttribute(String key, String value) {
        attributes.put(key, value);
    }

    /**
     * Recherche un attribut dans l'ensemble des attributs de l'élément.
     *
     * @param key la clé de l'élément recherché
     * @return l 'attribut recherché
     */
    public String getAttribute(String key) {
        return attributes.get(key);
    }

    /**
     * Supprime un attribut dans l'ensemble des attributs de l'élément.
     *
     * @param key la clé de l'élément à supprimer
     * @return la valeur précédemment associée à la clé ou nulle si la clé n'était associée à aucun attribut
     */
    public String removeAttribute(String key) {
        return attributes.remove(key);
    }

    /**
     * Fournit une copie de l'ensemble des attributs de l'élément.
     *
     * @return l 'ensemble des attributs
     */
    public Map<String, String> getAttributes() {
        return new HashMap<>(attributes);
    }

    /**
     * Ajoute un élément à la liste des éléments.
     *
     * @param element l'élément à ajouter
     */
    public void addElement(Element element) {
        elements.add(element);
    }

    /**
     * Ajoute un élément à la liste des éléments.
     *
     * @param name  le nom de l'élément à ajouter
     * @param value la valeur de l'élément à ajouter
     */
    public void addElement(String name, String value) {
        elements.add(new Element(name, value));
    }

    /**
     * Supprime un élément à la liste des éléments.
     *
     * @param element l'élément à supprimer
     * @return true si l'élément a été supprimé avec succès, false sinon.
     */
    public boolean removeElement(Element element) {
        return elements.remove(element);
    }

    /**
     * Fournit une copie de la liste des éléments enfants.
     *
     * @return la liste des éléments
     */
    public List<Element> getElements() {
        return new ArrayList<>(elements);
    }

    /**
     * Indique la valeur du hash code de l'objet.
     *
     * @return le hash code de l'objet
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.name);
        hash = 73 * hash + Objects.hashCode(this.value);
        hash = 73 * hash + Objects.hashCode(this.attributes);
        hash = 73 * hash + Objects.hashCode(this.elements);
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
        final Element other = (Element) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.attributes, other.attributes)) {
            return false;
        }
        return Objects.equals(this.elements, other.elements);
    }

    /**
     * Indique la représentation en tant que String de l'objet.
     *
     * @return la représentation en tant que String
     */
    @Override
    public String toString() {
        return "Element{" + "name=" + name + ", value=" + value + '}';
    }

}
