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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe SignerBuilder facilite la création d'un objet Signer en suivant le principe de conception du pattern
 * builder.
 *
 * @author Emmanuel Deviller
 * @see Signer
 */
public class SignerBuilder {

    private String firstName;
    private String birthName;
    private String fullName;
    private String givenName;
    private String gender;
    private LocalDate birthDate;
    private Place birthPlace;
    private LocalDate deathDate;
    private Place deathPlace;
    private String corpname;
    private LocalDateTime signingTime;

    private List<String> nationality = new ArrayList<>();
    private List<String> identifier = new ArrayList<>();
    private List<String> function = new ArrayList<>();
    private List<String> activity = new ArrayList<>();
    private List<String> position = new ArrayList<>();
    private List<String> role = new ArrayList<>();
    private List<String> mandate = new ArrayList<>();

    private SignerBuilder() {
    }

    /**
     * Instancie le builder.
     *
     * @return le builder
     */
    public static SignerBuilder builder() {
        return new SignerBuilder();
    }

    /**
     * Spécifie le prénom de la personne.
     *
     * @param firstName le prénom
     * @return le builder
     */
    public SignerBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Spécifie le nom de naissance de la personne.
     *
     * @param birthName le nom de naissance
     * @return le builder
     */
    public SignerBuilder withBirthName(String birthName) {
        this.birthName = birthName;
        return this;
    }

    /**
     * Spécifie le nom de complet de la personne.
     *
     * @param fullName le nom complet
     * @return le builder
     */
    public SignerBuilder withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    /**
     * Spécifie le nom d'usage de la personne.
     *
     * @param givenName le nom d'usage
     * @return le builder
     */
    public SignerBuilder withGivenName(String givenName) {
        this.givenName = givenName;
        return this;
    }

    /**
     * Spécifie le sexe de la personne.
     *
     * @param gender le sexe
     * @return le builder
     */
    public SignerBuilder withGender(String gender) {
        this.gender = gender;
        return this;
    }

    /**
     * Spécifie la date de naissance de la personne.
     *
     * @param birthDate la date de naissance
     * @return le builder
     */
    public SignerBuilder withBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * Spécifie le lieu de naissance de la personne.
     *
     * @param birthPlace le lieu de naissance
     * @return le builder
     */
    public SignerBuilder withBirthPlace(Place birthPlace) {
        this.birthPlace = birthPlace;
        return this;
    }

    /**
     * Spécifie la date de décès de la personne.
     *
     * @param deathDate la date de décès
     * @return le builder
     */
    public SignerBuilder withDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
        return this;
    }

    /**
     * Spécifie le lieu de décès de la personne.
     *
     * @param deathPlace le lieu de décès
     * @return le builder
     */
    public SignerBuilder withDeathPlace(Place deathPlace) {
        this.deathPlace = deathPlace;
        return this;
    }

    /**
     * Spécifie l'entité d'appartenance de la personne.
     *
     * @param corpname l'entité
     * @return le builder
     */
    public SignerBuilder withCorpname(String corpname) {
        this.corpname = corpname;
        return this;
    }

    /**
     * Spécifie la date et l'heure de la signature.
     *
     * @param signingTime la date et l'heure
     * @return le builder
     */
    public SignerBuilder withSigningTime(LocalDateTime signingTime) {
        this.signingTime = signingTime;
        return this;
    }

    /**
     * Spécifie la liste des nationalités de la personne.
     *
     * @param list la liste des nationalités
     * @return le builder
     */
    public SignerBuilder withNationality(List<String> list) {
        nationality = list;
        return this;
    }

    /**
     * Spécifie la liste des identifiants de la personne.
     *
     * @param list la liste des identifiants
     * @return le builder
     */
    public SignerBuilder withIdentifier(List<String> list) {
        identifier = list;
        return this;
    }

    /**
     * Spécifie la liste des fonctions de la personne.
     *
     * @param list la liste des fonctions
     * @return le builder
     */
    public SignerBuilder withFunction(List<String> list) {
        function = list;
        return this;
    }

    /**
     * Spécifie la liste des activités de la personne.
     *
     * @param list la liste des activités
     * @return le builder
     */
    public SignerBuilder withActivity(List<String> list) {
        activity = list;
        return this;
    }

    /**
     * Spécifie la liste des positions de la personne.
     *
     * @param list la liste des position
     * @return le builder
     */
    public SignerBuilder withPosition(List<String> list) {
        position = list;
        return this;
    }

    /**
     * Spécifie la liste des rôles de la personne.
     *
     * @param list la liste des rôle
     * @return le builder
     */
    public SignerBuilder withRole(List<String> list) {
        role = list;
        return this;
    }

    /**
     * Spécifie la liste des propriétés intellectuelles ou artistiques de la personne.
     *
     * @param list la liste des propriétés intellectuelles ou artistiques
     * @return le builder
     */
    public SignerBuilder withMandate(List<String> list) {
        mandate = list;
        return this;
    }

    /**
     * Ajoute une nationalité à la personne.
     *
     * @param value la nationalité à ajouter
     * @return le builder
     */
    public SignerBuilder addNationality(String value) {
        nationality.add(value);
        return this;
    }

    /**
     * Ajoute un identifiant à la personne.
     *
     * @param value l'identifiant à ajouter
     * @return le builder
     */
    public SignerBuilder addIdentifier(String value) {
        identifier.add(value);
        return this;
    }

    /**
     * Ajoute une fonction à la personne.
     *
     * @param value la fonction à ajouter
     * @return le builder
     */
    public SignerBuilder addFunction(String value) {
        function.add(value);
        return this;
    }

    /**
     * Ajoute une activité à la personne.
     *
     * @param value l'activité à ajouter
     * @return le builder
     */
    public SignerBuilder addActivity(String value) {
        activity.add(value);
        return this;
    }

    /**
     * Ajoute une position à la personne.
     *
     * @param value la position à ajouter
     * @return le builder
     */
    public SignerBuilder addPosition(String value) {
        position.add(value);
        return this;
    }

    /**
     * Ajoute un rôle à la personne.
     *
     * @param value le rôle à ajouter
     * @return le builder
     */
    public SignerBuilder addRole(String value) {
        role.add(value);
        return this;
    }

    /**
     * Ajoute une propriété intellectuelle ou artistique à la personne.
     *
     * @param value la propriété à ajouter
     * @return le builder
     */
    public SignerBuilder addMandate(String value) {
        mandate.add(value);
        return this;
    }

    /**
     * Instancie la classe Signer selon les paramètres précédemment spécifiés dans le builder.
     *
     * @return le signataire
     */
    public Signer build() {
        Signer signer = new Signer(firstName, birthName, fullName, givenName, gender, birthDate, birthPlace, deathDate, deathPlace, corpname, signingTime);
        nationality.forEach(signer::addNationality);
        identifier.forEach(signer::addIdentifier);
        function.forEach(signer::addFunction);
        activity.forEach(signer::addActivity);
        position.forEach(signer::addPosition);
        role.forEach(signer::addRole);
        mandate.forEach(signer::addMandate);
        return signer;
    }
}
