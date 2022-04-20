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
 * La classe RelatedObjectRef représente la référence à un ensemble d'objets faisant ou ne faisant pas partie du présent
 * paquet d'information.
 *
 * <p>
 * Supporté en SEDA v2.1.
 * </p>
 *
 * @author Emmanuel Deviller
 * @see RelationRef
 */
public class RelatedObjectRef {

    /**
     * La liste des références de type "Est une version de". Cette relation permet d'indiquer les modifications dans le
     * contenu présent paquet d'information (édition, adaptation, traduction).
     */
    protected final List<RelationRef> isVersionOfs;

    /**
     * La liste des références de type "Remplace". Cette relation permet d'indiquer les objets remplacés par le niveau
     * courant de description.
     */
    protected final List<RelationRef> replaces;

    /**
     * La liste des références de type "Requiert". Cette relation permet d'indiquer les objets nécessaire à la
     * compréhension du niveau courant de description.
     */
    protected final List<RelationRef> requires;

    /**
     * La liste des références de type "Est une partie de". Cette relation permet d'indiquer qu'un objet est une partie
     * d'un autre.
     */
    protected final List<RelationRef> isPartOfs;

    /**
     * La liste des références de type "Référence". Cette relation permet d'indiquer qu'un objet en référence un autre.
     */
    protected final List<RelationRef> references;

    /**
     * Instancie la classe.
     */
    public RelatedObjectRef() {
        isVersionOfs = new ArrayList<>();
        replaces = new ArrayList<>();
        requires = new ArrayList<>();
        isPartOfs = new ArrayList<>();
        references = new ArrayList<>();
    }

    /**
     * Ajoute une référence à la liste des références de type "Est une version de". Cette relation permet d'indiquer les
     * modifications dans le contenu présent paquet d'information (édition, adaptation, traduction).
     *
     * @param isVersionOf la référence à ajouter
     */
    public void addVersionOf(RelationRef isVersionOf) {
        Validate.notNull(isVersionOf, SipUtils.NOT_NULL, "isVersionOf");
        isVersionOfs.add(isVersionOf);
    }

    /**
     * Supprime une référence de la liste des références de type "Est une version de"..
     *
     * @param isVersionOf la référence à supprimer
     * @return true si la suppression a été réalisée avec succès, sinon false
     */
    public boolean removeVersionOf(RelationRef isVersionOf) {
        Validate.notNull(isVersionOf, SipUtils.NOT_NULL, "isVersionOf");
        return isVersionOfs.remove(isVersionOf);
    }

    /**
     * Fournit la liste des références de type "Est une version de". Cette relation permet d'indiquer les modifications
     * dans le contenu présent paquet d'information (édition, adaptation, traduction).
     *
     * @return la liste des références de type "Est une version de"
     */
    public List<RelationRef> getVersionOfs() {
        return new ArrayList<>(isVersionOfs);
    }

    /**
     * Ajoute une référence à la liste des références de type "Remplace". Cette relation permet d'indiquer les objets
     * remplacés par le niveau courant de description.
     *
     * @param replace la référence à ajouter
     */
    public void addReplace(RelationRef replace) {
        Validate.notNull(replace, SipUtils.NOT_NULL, "replace");
        replaces.add(replace);
    }

    /**
     * Supprime une référence de la liste des références de type "Remplace".
     *
     * @param replace la référence à supprimer
     * @return true si la suppression a été réalisée avec succès, sinon false
     */
    public boolean removeReplace(RelationRef replace) {
        Validate.notNull(replace, SipUtils.NOT_NULL, "replace");
        return replaces.remove(replace);
    }

    /**
     * Fournit la liste des références de type "Remplace". Cette relation permet d'indiquer les objets remplacés par le
     * niveau courant de description.
     *
     * @return la liste des références de type "Remplace"
     */
    public List<RelationRef> getReplaces() {
        return new ArrayList<>(replaces);
    }

    /**
     * Ajoute une référence à la liste des références de type "Requiert". Cette relation permet d'indiquer les objets
     * nécessaire à la compréhension du niveau courant de description.
     *
     * @param require la référence à ajouter
     */
    public void addRequire(RelationRef require) {
        Validate.notNull(require, SipUtils.NOT_NULL, "require");
        requires.add(require);
    }

    /**
     * Supprime une référence de la liste des références de type "Requiert".
     *
     * @param require la référence à supprimer
     * @return true si la suppression a été réalisée avec succès, sinon false
     */
    public boolean removeRequire(RelationRef require) {
        Validate.notNull(require, SipUtils.NOT_NULL, "require");
        return requires.remove(require);
    }

    /**
     * Fournit la liste des références de type "Requiert". Cette relation permet d'indiquer les objets nécessaire à la
     * compréhension du niveau courant de description.
     *
     * @return la liste des références de type "Requiert"
     */
    public List<RelationRef> getRequires() {
        return new ArrayList<>(requires);
    }

    /**
     * Ajoute une référence à la liste des références de type "Est une partie de". Cette relation permet d'indiquer
     * qu'un objet est une partie d'un autre.
     *
     * @param isPartOf la référence à ajouter
     */
    public void addPartOf(RelationRef isPartOf) {
        Validate.notNull(isPartOf, SipUtils.NOT_NULL, "isPartOf");
        isPartOfs.add(isPartOf);
    }

    /**
     * Supprime une référence de la liste des références de type "Est une partie de".
     *
     * @param isPartOf la référence à supprimer
     * @return true si la suppression a été réalisée avec succès, sinon false
     */
    public boolean removePartOf(RelationRef isPartOf) {
        Validate.notNull(isPartOf, SipUtils.NOT_NULL, "isPartOf");
        return isPartOfs.remove(isPartOf);
    }

    /**
     * Fournit la liste des références de type "Est une partie de". Cette relation permet d'indiquer qu'un objet est une
     * partie d'un autre.
     *
     * @return la liste des références de type "Est une partie de"
     */
    public List<RelationRef> getPartOfs() {
        return new ArrayList<>(isPartOfs);
    }

    /**
     * Ajoute une référence à la liste des références de type "Référence". Cette relation permet d'indiquer qu'un objet
     * en référence un autre.
     *
     * @param reference la référence à ajouter
     */
    public void addReference(RelationRef reference) {
        Validate.notNull(reference, SipUtils.NOT_NULL, "reference");
        references.add(reference);
    }

    /**
     * Supprime une référence de la liste des références de type "Référence".
     *
     * @param reference la référence à supprimer
     * @return true si la suppression a été réalisée avec succès, sinon false
     */
    public boolean removeReference(RelationRef reference) {
        Validate.notNull(reference, SipUtils.NOT_NULL, "reference");
        return references.remove(reference);
    }

    /**
     * Fournit la liste des références de type "Référence". Cette relation permet d'indiquer qu'un objet en référence un
     * autre.
     *
     * @return la liste des références de type "Référence"
     */
    public List<RelationRef> getReferences() {
        return new ArrayList<>(references);
    }
}
