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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * La classe Agency représente un service agent. Il existe plusieurs types de services agents. ArchivalAgency : service
 * d'archives responsable de la demande de communication ou de restitution. OriginatingAgency : service producteur ou
 * "Personne physique ou morale, publique ou privée, qui a produit, reçu et conservé des archives dans l'exercice de son
 * activité" (Dictionnaire de terminologie archivistique, direction des archives de France, 2002). SubmissionAgency :
 * service versant responsable du transfert des données. TransferringAgency : service versant chargé de réaliser le
 * transport.
 * </p>
 *
 * <p>
 * Supporté en SEDA v2.1 et FNTC v4.
 * </p>
 *
 * @author Emmanuel Deviller
 */
public class Agency {

    /**
     * Identifiant du service agent
     */
    protected final String identifier;

    /**
     * Nom du service agent
     */
    protected final String name;

    /**
     * Liste des éléments métadonnées de description du service agent
     */
    protected final ArrayList<Element> elements;

    /**
     * Instancie la classe selon les paramètres indiqués.
     *
     * @param identifier l'identifiant du service
     * @param name       le nom du service
     */
    @JsonCreator
    public Agency(@JsonProperty("identifier") String identifier, @JsonProperty("name") String name) {
        Validate.notNull(identifier, SipUtils.NOT_NULL, "identifier");

        this.identifier = identifier;
        this.name = name;
        this.elements = new ArrayList<>();
    }

    /**
     * Indique l'identifiant du service agent.
     *
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Indique le nom du service agent.
     *
     * @return le nom
     */
    public String getName() {
        return name;
    }

    /**
     * Ajoute un élément métadonnée de description du service agent selon les paramètres indiqués.
     *
     * @param name  le nom de la métadonnée
     * @param value la valeur de la métadonnée
     */
    public void addElement(String name, String value) {
        elements.add(new Element(name, value));
    }

    /**
     * Ajoute un élément métadonnée de description du service agent.
     *
     * @param element l'élément de métadonnée
     */
    public void addElement(Element element) {
        elements.add(element);
    }

    /**
     * Supprime un élément métadonnée de description.
     *
     * @param element l'élément de métadonnée à supprimer
     * @return true si la suppression de l'élément a été réalisée avec succès, false sinon
     */
    public boolean removeElement(Element element) {
        return elements.remove(element);
    }

    /**
     * Fournit la liste des éléments métadonnées de description du service agent.
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
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.identifier);
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.elements);
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
        final Agency other = (Agency) obj;
        if (!Objects.equals(this.identifier, other.identifier)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
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
        return "Agency{" + "identifier=" + identifier + ", name=" + name + ", elements=" + elements + '}';
    }

}
