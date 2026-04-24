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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * La classe Agent représente une personne physique ou morale.
 *
 * <p>Supporté en SEDA v2.1 et FNTC v4 (étendu).
 *
 * @author Emmanuel Deviller
 * @see AgentBuilder
 * @see Validator
 * @see Signer
 */
@JsonDeserialize(builder = AgentBuilder.class)
public class Agent {

  /** Nationalités de la personne. */
  protected final List<String> nationalities = new ArrayList<>();

  /** Identifiants de la personne. */
  protected final List<String> identifiers = new ArrayList<>();

  /** Fonctions de la personne. */
  protected final List<String> functions = new ArrayList<>();

  /** Activités de la personne. */
  protected final List<String> activities = new ArrayList<>();

  /** Positions ou intitulés du poste de travail occupé par la personne. */
  protected final List<String> positions = new ArrayList<>();

  /**
   * Rôles ou droits avec lesquels une personne a réalisé une opération, notamment dans une
   * application.
   */
  protected final List<String> roles = new ArrayList<>();

  /** Propriétés intellectuelles et artistiques de la personne. */
  protected final List<String> mandates = new ArrayList<>();

  /** Prénom de la personne. */
  protected String firstName;

  /** Nom de naissance de la personne. */
  protected String birthName;

  /** Nom complet de la personne. */
  protected String fullName;

  /** Nom d'usage de la personne. */
  protected String givenName;

  /** Sexe de la personne. */
  protected String gender;

  /** Date de naissance de la personne. */
  protected LocalDate birthDate;

  /** Lieu de naissance de la personne. */
  protected Place birthPlace;

  /** Date de décès de la personne. */
  protected LocalDate deathDate;

  /** Lieu de décès de la personne. */
  protected Place deathPlace;

  /**
   * Entité d'appartenance de la personne. Il peut s'agir d'une organisation, d'une société, etc.
   */
  protected String corpName;

  /** Instancie la classe. */
  public Agent() {}

  /**
   * Instancie la classe avec l'identifiant de l'agent.
   *
   * @param identifier l'identifiant
   */
  public Agent(String identifier) {
    identifiers.add(identifier);
  }

  /**
   * Instancie la classe selon les paramètres indiqués.
   *
   * @param firstName le prénom
   * @param birthName le nom de naissance
   * @param fullName le nom complet
   * @param givenName le nom d'usage
   * @param gender le sexe
   * @param birthDate la date de naissance
   * @param birthPlace le lieu de naissance
   * @param deathDate la date de décès
   * @param deathPlace le lieu de décès
   * @param corpName l'entité
   */
  public Agent(
      String firstName,
      String birthName,
      String fullName,
      String givenName,
      String gender,
      LocalDate birthDate,
      Place birthPlace,
      LocalDate deathDate,
      Place deathPlace,
      String corpName) {

    this.firstName = firstName;
    this.birthName = birthName;
    this.fullName = fullName;
    this.givenName = givenName;
    this.gender = gender;
    this.birthDate = birthDate;
    this.birthPlace = birthPlace;
    this.deathDate = deathDate;
    this.deathPlace = deathPlace;
    this.corpName = corpName;
  }

  /**
   * Indique le prénom de la personne.
   *
   * @return le prénom
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Spécifie le prénom de la personne.
   *
   * @param value le prénom
   */
  public void setFirstName(String value) {
    this.firstName = value;
  }

  /**
   * Indique le nom de naissance de la personne.
   *
   * @return le nom de naissance
   */
  public String getBirthName() {
    return birthName;
  }

  /**
   * Spécifie le nom de naissance de la personne.
   *
   * @param value le nom de naissance
   */
  public void setBirthName(String value) {
    this.birthName = value;
  }

  /**
   * Indique le nom de complet de la personne.
   *
   * @return le nom complet
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * Spécifie le nom de complet de la personne.
   *
   * @param value le nom complet
   */
  public void setFullName(String value) {
    this.fullName = value;
  }

  /**
   * Indique le nom d'usage de la personne.
   *
   * @return le nom complet
   */
  public String getGivenName() {
    return givenName;
  }

  /**
   * Spécifie le nom d'usage de la personne.
   *
   * @param value le nom d'usage
   */
  public void setGivenName(String value) {
    this.givenName = value;
  }

  /**
   * Indique le sexe de la personne.
   *
   * @return le sexe
   */
  public String getGender() {
    return gender;
  }

  /**
   * Spécifie le sexe de la personne.
   *
   * @param value le sexe
   */
  public void setGender(String value) {
    this.gender = value;
  }

  /**
   * Indique la date de naissance de la personne.
   *
   * @return la date de naissance
   */
  public LocalDate getBirthDate() {
    return birthDate;
  }

  /**
   * Spécifie la date de naissance de la personne.
   *
   * @param value la date de naissance
   */
  public void setBirthDate(LocalDate value) {
    this.birthDate = value;
  }

  /**
   * Indique le lieu de naissance de la personne.
   *
   * @return le lieu de naissance
   */
  public Place getBirthPlace() {
    return birthPlace;
  }

  /**
   * Spécifie le lieu de naissance de la personne.
   *
   * @param value le lieu de naissance
   */
  public void setBirthPlace(Place value) {
    this.birthPlace = value;
  }

  /**
   * Indique la date de décès de la personne.
   *
   * @return la date de décès
   */
  public LocalDate getDeathDate() {
    return deathDate;
  }

  /**
   * Spécifie la date de décès de la personne.
   *
   * @param value la date de décès
   */
  public void setDeathDate(LocalDate value) {
    this.deathDate = value;
  }

  /**
   * Indique le lieu de décès de la personne.
   *
   * @return le lieu de décès
   */
  public Place getDeathPlace() {
    return deathPlace;
  }

  /**
   * Spécifie le lieu de décès de la personne.
   *
   * @param value le lieu de décès
   */
  public void setDeathPlace(Place value) {
    this.deathPlace = value;
  }

  /**
   * Fournit les nationalités de la personne.
   *
   * @return la liste des nationalités
   */
  public List<String> getNationalities() {
    return new ArrayList<>(nationalities);
  }

  /**
   * Ajoute une nationalité à la personne.
   *
   * @param value la nationalité à ajouter
   */
  public void addNationality(String value) {
    nationalities.add(value);
  }

  /**
   * Supprime une nationalité de la personne.
   *
   * @param value la nationalité à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeNationality(String value) {
    return nationalities.remove(value);
  }

  /**
   * Indique l'entité d'appartenance de la personne.
   *
   * @return l'entité
   */
  public String getCorpName() {
    return corpName;
  }

  /**
   * Spécifie l'entité d'appartenance de la personne.
   *
   * @param value l'entité
   */
  public void setCorpName(String value) {
    this.corpName = value;
  }

  /**
   * Fournit les identifiants de la personne.
   *
   * @return la liste des identifiants
   */
  public List<String> getIdentifiers() {
    return new ArrayList<>(identifiers);
  }

  /**
   * Ajoute un identifiant à la personne.
   *
   * @param value l'identifiant à ajouter
   */
  public void addIdentifier(String value) {
    identifiers.add(value);
  }

  /**
   * Supprime un identifiants de la personne.
   *
   * @param value l'identifiant à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeIdentifier(String value) {
    return identifiers.remove(value);
  }

  /**
   * Fournit les fonctions de la personne.
   *
   * @return la liste des fonctions
   */
  public List<String> getFunctions() {
    return new ArrayList<>(functions);
  }

  /**
   * Ajoute une fonction à la personne.
   *
   * @param value la fonction à ajouter
   */
  public void addFunction(String value) {
    functions.add(value);
  }

  /**
   * Supprime une fonction de la personne.
   *
   * @param value la fonction à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeFunction(String value) {
    return functions.remove(value);
  }

  /**
   * Fournit les activités de la personne.
   *
   * @return la liste des activités
   */
  public List<String> getActivities() {
    return new ArrayList<>(activities);
  }

  /**
   * Ajoute une activité à la personne.
   *
   * @param value l'activité à ajouter
   */
  public void addActivity(String value) {
    activities.add(value);
  }

  /**
   * Supprime une activité de la personne.
   *
   * @param value l'activité à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeActivity(String value) {
    return activities.remove(value);
  }

  /**
   * Fournit les positions de la personne.
   *
   * @return la liste des positions
   */
  public List<String> getPositions() {
    return new ArrayList<>(positions);
  }

  /**
   * Ajoute une position à la personne.
   *
   * @param value la position à ajouter
   */
  public void addPosition(String value) {
    positions.add(value);
  }

  /**
   * Supprime une position de la personne.
   *
   * @param value la position à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removePosition(String value) {
    return positions.remove(value);
  }

  /**
   * Fournit les rôles de la personne.
   *
   * @return la liste des rôles
   */
  public List<String> getRoles() {
    return new ArrayList<>(roles);
  }

  /**
   * Ajoute un rôle à la personne.
   *
   * @param value le rôle à ajouter
   */
  public void addRole(String value) {
    roles.add(value);
  }

  /**
   * Supprime un rôle de la personne.
   *
   * @param value le rôle à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeRole(String value) {
    return roles.remove(value);
  }

  /**
   * Fournit les propriétés intellectuelles et artistiques de la personne.
   *
   * @return la liste des propriétés intellectuelles et artistiques
   */
  public List<String> getMandates() {
    return new ArrayList<>(mandates);
  }

  /**
   * Ajoute une propriété intellectuelle ou artistique à la personne.
   *
   * @param value la propriété à ajouter
   */
  public void addMandate(String value) {
    mandates.add(value);
  }

  /**
   * Supprime une propriété intellectuelle ou artistique de la personne.
   *
   * @param value la propriété à supprimer
   * @return true si la suppression a été réalisée avec succès, sinon false
   */
  public boolean removeMandate(String value) {
    return mandates.remove(value);
  }

  /**
   * Indique la valeur du hash code de l'objet.
   *
   * @return le hash code de l'objet
   */
  @Override
  public int hashCode() {
    int hash = 3;
    hash = 79 * hash + Objects.hashCode(this.firstName);
    hash = 79 * hash + Objects.hashCode(this.birthName);
    hash = 79 * hash + Objects.hashCode(this.fullName);
    hash = 79 * hash + Objects.hashCode(this.givenName);
    hash = 79 * hash + Objects.hashCode(this.gender);
    hash = 79 * hash + Objects.hashCode(this.birthDate);
    hash = 79 * hash + Objects.hashCode(this.birthPlace);
    hash = 79 * hash + Objects.hashCode(this.deathDate);
    hash = 79 * hash + Objects.hashCode(this.deathPlace);
    hash = 79 * hash + Objects.hashCode(this.nationalities);
    hash = 79 * hash + Objects.hashCode(this.corpName);
    hash = 79 * hash + Objects.hashCode(this.identifiers);
    hash = 79 * hash + Objects.hashCode(this.functions);
    hash = 79 * hash + Objects.hashCode(this.activities);
    hash = 79 * hash + Objects.hashCode(this.positions);
    hash = 79 * hash + Objects.hashCode(this.roles);
    hash = 79 * hash + Objects.hashCode(this.mandates);
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
    final Agent other = (Agent) obj;
    if (!Objects.equals(this.firstName, other.firstName)) {
      return false;
    }
    if (!Objects.equals(this.birthName, other.birthName)) {
      return false;
    }
    if (!Objects.equals(this.fullName, other.fullName)) {
      return false;
    }
    if (!Objects.equals(this.givenName, other.givenName)) {
      return false;
    }
    if (!Objects.equals(this.gender, other.gender)) {
      return false;
    }
    if (!Objects.equals(this.corpName, other.corpName)) {
      return false;
    }
    if (!Objects.equals(this.birthDate, other.birthDate)) {
      return false;
    }
    if (!Objects.equals(this.birthPlace, other.birthPlace)) {
      return false;
    }
    if (!Objects.equals(this.deathDate, other.deathDate)) {
      return false;
    }
    if (!Objects.equals(this.deathPlace, other.deathPlace)) {
      return false;
    }
    if (!Objects.equals(this.nationalities, other.nationalities)) {
      return false;
    }
    if (!Objects.equals(this.identifiers, other.identifiers)) {
      return false;
    }
    if (!Objects.equals(this.functions, other.functions)) {
      return false;
    }
    if (!Objects.equals(this.activities, other.activities)) {
      return false;
    }
    if (!Objects.equals(this.positions, other.positions)) {
      return false;
    }
    if (!Objects.equals(this.roles, other.roles)) {
      return false;
    }
    return Objects.equals(this.mandates, other.mandates);
  }

  /**
   * Indique la représentation en tant que String de l'objet.
   *
   * @return la représentation en tant que String
   */
  @Override
  public String toString() {
    return "Agent{"
        + "firstName="
        + firstName
        + ", birthName="
        + birthName
        + ", fullName="
        + fullName
        + ", givenName="
        + givenName
        + ", gender="
        + gender
        + ", birthDate="
        + birthDate
        + ", birthPlace="
        + birthPlace
        + ", deathDate="
        + deathDate
        + ", deathPlace="
        + deathPlace
        + ", nationality="
        + nationalities
        + ", corpName="
        + corpName
        + ", identifiers="
        + identifiers
        + ", functions="
        + functions
        + ", activities="
        + activities
        + ", positions="
        + positions
        + ", roles="
        + roles
        + ", mandates="
        + mandates
        + '}';
  }
}
