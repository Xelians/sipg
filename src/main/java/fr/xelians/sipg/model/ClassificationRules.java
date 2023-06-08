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

/**
 * <p>
 * La classe ClassificationRules représente les règles de classification des archives.
 * </p>
 *
 * <p>
 * En SEDA 2.1, chaque règle a une startDate et un Identifiant de règle de référence pour la durée. Si aucune règle
 * n'est spécifiée et que la date actuelle est dans la StarDate, la réponse de restriction est "Aucune restriction". Si
 * la date est vide, la réponse de restriction est "Restreint" car il n'y a aucun moyen de calculer la date de fin. Si
 * une règle et une date sont précisées, alors la règle est valable (restriction appliquée).
 * </p>
 *
 * <p>
 * Supporté en SEDA v2.1.
 * </p>
 *
 * @author Emmanuel Deviller
 */
public class ClassificationRules extends AbstractRules {

    /**
     * L'audience qui permet de gérer les questions de "diffusion restreinte", de "spécial France" et de "Confidentiel
     * Industrie".
     */
    protected String classificationAudience;

    /**
     * La référence au niveau de classification.
     */
    protected String classificationLevel;

    /**
     * Le propriétaire de la classification (service émetteur au sens de l’IGI 1300).
     */
    protected String classificationOwner;

    /**
     * La date de réévaluation de la classification.
     */
    protected LocalDate classificationReassessingDate;

    /**
     * Valeur indiquant si une autorisation humaine est nécessaire pour réévaluer la classification.
     */
    protected Boolean needReassessingAuthorization;

    /**
     * Instancie la classe.
     */
    public ClassificationRules() {
        super();
    }

    /**
     * Instancie la classe avec une règle spécifiée par les paramètres.
     *
     * @param name      la référence de la règle classification
     * @param startDate date de départ de calcul de la règle de classification
     */
    public ClassificationRules(String name, LocalDate startDate) {
        super(name, startDate);
    }

    /**
     * Indique l'audience qui permet de gérer les questions de "diffusion restreinte", de "spécial France" et de
     * "Confidentiel Industrie".
     *
     * @return l'audience
     */
    public String getClassificationAudience() {
        return classificationAudience;
    }

    /**
     * Spécifie l'audience qui permet de gérer les questions de "diffusion restreinte", de "spécial France" et de
     * "Confidentiel Industrie".
     *
     * @param classificationAudience l'audience
     */
    public void setClassificationAudience(String classificationAudience) {
        this.classificationAudience = classificationAudience;
    }

    /**
     * Indique la référence au niveau de classification.
     *
     * @return classificationLevel le niveau de classification
     */
    public String getClassificationLevel() {
        return classificationLevel;
    }

    /**
     * Spécifie la référence au niveau de classification.
     *
     * @param classificationLevel le niveau de classification
     */
    public void setClassificationLevel(String classificationLevel) {
        this.classificationLevel = classificationLevel;
    }

    /**
     * Indique le propriétaire de la classification. Service émetteur au sens de l’IGI 1300.
     *
     * @return le propriétaire de la classification
     */
    public String getClassificationOwner() {
        return classificationOwner;
    }

    /**
     * Spécifie le propriétaire de la classification. Service émetteur au sens de l’IGI 1300.
     *
     * @param classificationOwner le propriétaire de la classification
     */
    public void setClassificationOwner(String classificationOwner) {
        this.classificationOwner = classificationOwner;
    }

    /**
     * Indique la date de réévaluation de la classification.
     *
     * @return la date de réévaluation de la classification
     */
    public LocalDate getClassificationReassessingDate() {
        return classificationReassessingDate;
    }

    /**
     * Spécifie la date de réévaluation de la classification.
     *
     * @param classificationReassessingDate la date de réévaluation de la classification
     */
    public void setClassificationReassessingDate(LocalDate classificationReassessingDate) {
        this.classificationReassessingDate = classificationReassessingDate;
    }

    /**
     * Indique si une autorisation humaine est nécessaire pour réévaluer la classification.
     *
     * @return l'autorisation de réévaluation
     */
    public Boolean getNeedReassessingAuthorization() {
        return needReassessingAuthorization;
    }

    /**
     * Spécifie si une autorisation humaine est nécessaire pour réévaluer la classification.
     *
     * @param needReassessingAuthorization l'autorisation de réévaluation
     */
    public void setNeedReassessingAuthorization(Boolean needReassessingAuthorization) {
        this.needReassessingAuthorization = needReassessingAuthorization;
    }

}
