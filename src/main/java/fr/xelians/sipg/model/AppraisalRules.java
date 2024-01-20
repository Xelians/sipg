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
 * La classe AppraisalRules représente les règles de durée d’utilité administrative des archives.
 * </p>
 *
 * <p>
 * Supporté en SEDA v2.1 et FNTC v4.
 * </p>
 *
 * @author Emmanuel Deviller
 */
public class AppraisalRules extends AbstractSimpleRules {


    /**
     * Action à entreprendre au terme de la durée d’utilité administrative. Cette propriété est ignorée en FNTC v4.
     */
    protected String finalAction;

    /**
     * La durée de d’utilité administrative. Cette propriété est ignorée en SEDA v2.1.
     */
    protected String duration;

    /**
     * Instancie la classe.
     */
    public AppraisalRules() {
        super();
    }

    /**
     * Instancie la classe avec la règle spécifiée par les paramètres.
     *
     * @param name      la référence de la règle communicabilité
     * @param startDate date de départ de calcul de la règle de communicabilité
     */
    public AppraisalRules(String name, LocalDate startDate) {
        super(name, startDate);
    }

    /**
     * Indique l'action à entreprendre au terme de la durée d’utilité administrative.
     *
     * @return l'action à entreprendre
     */
    public String getFinalAction() {
        return finalAction;
    }

    /**
     * Spécifie l'action à entreprendre au terme de la durée d’utilité administrative.
     *
     * @param finalAction l'action à entreprendre
     */
    public void setFinalAction(String finalAction) {
        this.finalAction = finalAction;
    }

    /**
     * Indique la durée de d’utilité administrative.
     *
     * @return la durée de d’utilité administrative
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Spécifie la durée de d’utilité administrative.
     *
     * @param duration la durée de d’utilité administrative
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

}
