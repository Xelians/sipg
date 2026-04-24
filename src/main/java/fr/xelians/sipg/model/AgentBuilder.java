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
import java.util.ArrayList;
import java.util.List;

/**
 * La classe AgentBuilder facilite la création d'un objet Agent en suivant le principe de conception
 * du pattern builder.
 *
 * @author Emmanuel Deviller
 * @see Agent
 */
public class AgentBuilder {

  private String firstName;
  private String birthName;
  private String fullName;
  private String givenName;
  private String gender;
  private LocalDate birthDate;
  private Place birthPlace;
  private LocalDate deathDate;
  private Place deathPlace;
  private String corpName;

  private List<String> nationalities = new ArrayList<>();
  private List<String> identifiers = new ArrayList<>();
  private List<String> functions = new ArrayList<>();
  private List<String> activities = new ArrayList<>();
  private List<String> positions = new ArrayList<>();
  private List<String> roles = new ArrayList<>();
  private List<String> mandates = new ArrayList<>();

  private AgentBuilder() {}

  /**
   * Instancie le builder.
   *
   * @return le builder
   */
  public static AgentBuilder builder() {
    return new AgentBuilder();
  }

  /**
   * Spécifie le prénom de la personne.
   *
   * @param firstName le prénom
   * @return le builder
   */
  public AgentBuilder withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Spécifie le nom de naissance de la personne.
   *
   * @param birthName le nom de naissance
   * @return le builder
   */
  public AgentBuilder withBirthName(String birthName) {
    this.birthName = birthName;
    return this;
  }

  /**
   * Spécifie le nom de complet de la personne.
   *
   * @param fullName le nom complet
   * @return le builder
   */
  public AgentBuilder withFullName(String fullName) {
    this.fullName = fullName;
    return this;
  }

  /**
   * Spécifie le nom d'usage de la personne.
   *
   * @param givenName le nom d'usage
   * @return le builder
   */
  public AgentBuilder withGivenName(String givenName) {
    this.givenName = givenName;
    return this;
  }

  /**
   * Spécifie le sexe de la personne.
   *
   * @param gender le sexe
   * @return le builder
   */
  public AgentBuilder withGender(String gender) {
    this.gender = gender;
    return this;
  }

  /**
   * Spécifie la date de naissance de la personne.
   *
   * @param birthDate la date de naissance
   * @return le builder
   */
  public AgentBuilder withBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  /**
   * Spécifie le lieu de naissance de la personne.
   *
   * @param birthPlace le lieu de naissance
   * @return le builder
   */
  public AgentBuilder withBirthPlace(Place birthPlace) {
    this.birthPlace = birthPlace;
    return this;
  }

  /**
   * Spécifie la date de décès de la personne.
   *
   * @param deathDate la date de décès
   * @return le builder
   */
  public AgentBuilder withDeathDate(LocalDate deathDate) {
    this.deathDate = deathDate;
    return this;
  }

  /**
   * Spécifie le lieu de décès de la personne.
   *
   * @param deathPlace le lieu de décès
   * @return le builder
   */
  public AgentBuilder withDeathPlace(Place deathPlace) {
    this.deathPlace = deathPlace;
    return this;
  }

  /**
   * Spécifie l'entité d'appartenance de la personne.
   *
   * @param corpName l'entité
   * @return le builder
   */
  public AgentBuilder withCorpName(String corpName) {
    this.corpName = corpName;
    return this;
  }

  /**
   * Spécifie la liste des nationalités de la personne.
   *
   * @param list la liste des nationalités
   * @return le builder
   */
  public AgentBuilder withNationalities(List<String> list) {
    nationalities = list;
    return this;
  }

  /**
   * Spécifie la liste des identifiants de la personne.
   *
   * @param list la liste des identifiants
   * @return le builder
   */
  public AgentBuilder withIdentifiers(List<String> list) {
    identifiers = list;
    return this;
  }

  /**
   * Spécifie la liste des fonctions de la personne.
   *
   * @param list la liste des fonctions
   * @return le builder
   */
  public AgentBuilder withFunctions(List<String> list) {
    functions = list;
    return this;
  }

  /**
   * Spécifie la liste des activités de la personne.
   *
   * @param list la liste des activités
   * @return le builder
   */
  public AgentBuilder withActivities(List<String> list) {
    activities = list;
    return this;
  }

  /**
   * Spécifie la liste des positions de la personne.
   *
   * @param list la liste des positions
   * @return le builder
   */
  public AgentBuilder withPositions(List<String> list) {
    positions = list;
    return this;
  }

  /**
   * Spécifie la liste des rôles de la personne.
   *
   * @param list la liste des rôles
   * @return le builder
   */
  public AgentBuilder withRoles(List<String> list) {
    roles = list;
    return this;
  }

  /**
   * Spécifie la liste des propriétés intellectuelles ou artistiques de la personne.
   *
   * @param list la liste des propriétés intellectuelles ou artistiques
   * @return le builder
   */
  public AgentBuilder withMandates(List<String> list) {
    mandates = list;
    return this;
  }

  /**
   * Ajoute une nationalité à la personne.
   *
   * @param value la nationalité à ajouter
   * @return le builder
   */
  public AgentBuilder addNationality(String value) {
    nationalities.add(value);
    return this;
  }

  /**
   * Ajoute un identifiant à la personne.
   *
   * @param value l'identifiant à ajouter
   * @return le builder
   */
  public AgentBuilder addIdentifier(String value) {
    identifiers.add(value);
    return this;
  }

  /**
   * Ajoute une fonction à la personne.
   *
   * @param value la fonction à ajouter
   * @return le builder
   */
  public AgentBuilder addFunction(String value) {
    functions.add(value);
    return this;
  }

  /**
   * Ajoute une activité à la personne.
   *
   * @param value l'activité à ajouter
   * @return le builder
   */
  public AgentBuilder addActivity(String value) {
    activities.add(value);
    return this;
  }

  /**
   * Ajoute une position à la personne.
   *
   * @param value la position à ajouter
   * @return le builder
   */
  public AgentBuilder addPosition(String value) {
    positions.add(value);
    return this;
  }

  /**
   * Ajoute un rôle à la personne.
   *
   * @param value le rôle à ajouter
   * @return le builder
   */
  public AgentBuilder addRole(String value) {
    roles.add(value);
    return this;
  }

  /**
   * Ajoute une propriété intellectuelle ou artistique à la personne.
   *
   * @param value la propriété à ajouter
   * @return le builder
   */
  public AgentBuilder addMandate(String value) {
    mandates.add(value);
    return this;
  }

  /**
   * Instancie la classe Agent selon les paramètres précédemment spécifiés dans le builder.
   *
   * @return l'agent
   */
  public Agent build() {
    Agent agent =
        new Agent(
            firstName,
            birthName,
            fullName,
            givenName,
            gender,
            birthDate,
            birthPlace,
            deathDate,
            deathPlace,
            corpName);
    nationalities.forEach(agent::addNationality);
    identifiers.forEach(agent::addIdentifier);
    functions.forEach(agent::addFunction);
    activities.forEach(agent::addActivity);
    positions.forEach(agent::addPosition);
    roles.forEach(agent::addRole);
    mandates.forEach(agent::addMandate);
    return agent;
  }
}
