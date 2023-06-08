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
 * La classe ReuseRules représente les règles d’utilité courante des archives. Chaque règle a une startDate et un
 * identifiant de règle de référence pour la durée. Si aucune règle n'est spécifiée et que la date actuelle est dans la
 * StarDate, la réponse de restriction est "Aucune restriction". Si la date est vide, la réponse de restriction est
 * "Restreint" car il n'y a aucun moyen de calculer la date de fin. Si une règle et une date sont précisées, alors la
 * règle est valable (restriction appliquée).</p>
 *
 * <p>
 * Supporté en SEDA v2.1.
 * </p>
 *
 * @author Emmanuel Deviller
 */
public class StorageRules extends AbstractRules {

    /**
     * L'action à réaliser à l'échéance de la durée d’utilité courante.
     */
    protected String finalAction;

    /**
     * Instancie la classe.
     */
    public StorageRules() {
        super();
    }

    /**
     * Instancie la classe avec une règle spécifiée par les paramètres.
     *
     * @param name      la référence de la règle d’utilité courante
     * @param startDate date de départ de calcul de la règle d’utilité courante
     */
    public StorageRules(String name, LocalDate startDate) {
        super(name, startDate);
    }

    /**
     * Indique l'action à réaliser à l'échéance de la durée d’utilité courante.
     *
     * @return l'action à réaliser à l'échéance de la durée d’utilité courante
     */
    public String getFinalAction() {
        return finalAction;
    }

    /**
     * Spécifie l'action à réaliser à l'échéance de la durée d’utilité courante.
     *
     * @param finalAction l'action à réaliser à l'échéance de la durée d’utilité courante
     */
    public void setFinalAction(String finalAction) {
        this.finalAction = finalAction;
    }

}
