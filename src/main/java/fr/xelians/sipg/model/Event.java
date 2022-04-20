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

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * La classe Event représente un évènement, généralement issu d'un journal des opérations ou d'un journal du cycle de
 * vie, et qui est associé à une unité d'archive.
 *
 * <p>
 * Supporté en SEDA v2.1 .
 * </p>
 *
 * @author Emmanuel Deviller
 */
@JsonDeserialize(builder = EventBuilder.class)
public class Event {

    /**
     * L'identifiant de l'événement.
     */
    protected String identifier;

    /**
     * Le code du type d'événement.
     */
    protected String typeCode;

    /**
     * Le type d'événement.
     */
    protected String type;

    /**
     * La date et heure de l'événement.
     */
    protected LocalDateTime dateTime;

    /**
     * Le détail sur l'événement.
     */
    protected String detail;

    /**
     * Le résultat du traitement.
     */
    protected String outcome;

    /**
     * Le détail du résultat du traitement.
     */
    protected String outcomeDetail;

    /**
     * Le message détaillé sur le résultat du traitement.
     */
    protected String outcomeDetailMessage;

    /**
     * Le message technique détaillant l'erreur.
     */
    protected String detailData;

    /**
     * Instancie la classe.
     *
     * @param identifier           l'identifiant
     * @param typeCode             le code du type
     * @param type                 le type
     * @param dateTime             la date et l'heure
     * @param detail               le détail
     * @param outcome              Le résultat du traitement
     * @param outcomeDetail        Le détail du résultat du traitement
     * @param outcomeDetailMessage Le message détaillé sur le résultat du traitement
     * @param detailData           Le message technique détaillant l'erreur
     */
    public Event(String identifier, String typeCode, String type, LocalDateTime dateTime,
                 String detail, String outcome, String outcomeDetail,
                 String outcomeDetailMessage, String detailData) {

        this.identifier = identifier;
        this.typeCode = typeCode;
        this.type = type;
        this.dateTime = dateTime;
        this.detail = detail;
        this.outcome = outcome;
        this.outcomeDetail = outcomeDetail;
        this.outcomeDetailMessage = outcomeDetailMessage;
        this.detailData = detailData;
    }

    /**
     * Indique l'identifiant de l'événement.
     *
     * @return l 'identifiant.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Spécifie l'identifiant de l'événement.
     *
     * @param identifier l'identifiant
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Indique le code du type de l'événement.
     *
     * @return le code du type
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * Spécifie le code du type de l'événement.
     *
     * @param typeCode le code du type
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * Indique le type de l'événement.
     *
     * @return le type
     */
    public String getType() {
        return type;
    }

    /**
     * Spécifie le type de l'événement.
     *
     * @param type le type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Indique la date et l'heure de l'événement.
     *
     * @return la date et l'heure
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Spécifie la date et l'heure.
     *
     * @param dateTime la date et l'heure
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Indique le détail de l'événement.
     *
     * @return le détail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Spécifie le détail de l'événement.
     *
     * @param detail le détail
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * Indique le résultat de l'événement.
     *
     * @return le résultat
     */
    public String getOutcome() {
        return outcome;
    }

    /**
     * Spécifie le résultat de l'événement.
     *
     * @param outcome le résultat
     */
    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    /**
     * Indique le détail du résultat de l'événement.
     *
     * @return le détail du résultat
     */
    public String getOutcomeDetail() {
        return outcomeDetail;
    }

    /**
     * Spécifie le détail du résultat de l'événement.
     *
     * @param outcomeDetail le détail du résultat
     */
    public void setOutcomeDetail(String outcomeDetail) {
        this.outcomeDetail = outcomeDetail;
    }

    /**
     * Indique le message détail du résultat de l'événement.
     *
     * @return le message détail du résultat
     */
    public String getOutcomeDetailMessage() {
        return outcomeDetailMessage;
    }

    /**
     * Spécifie le message détail du résultat de l'événement.
     *
     * @param outcomeDetailMessage le message détail du résultat
     */
    public void setOutcomeDetailMessage(String outcomeDetailMessage) {
        this.outcomeDetailMessage = outcomeDetailMessage;
    }

    /**
     * Indique le message technique détaillant l'erreur de l'événement.
     *
     * @return le message technique de l'erreur
     */
    public String getDetailData() {
        return detailData;
    }

    /**
     * Spécifie le message technique détaillant l'erreur de l'événement.
     *
     * @param detailData le message technique de l'erreur
     */
    public void setDetailData(String detailData) {
        this.detailData = detailData;
    }

    /**
     * Fournit une copie de la liste des éléments enfants.
     *
     * @return la liste des éléments
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.identifier);
        hash = 11 * hash + Objects.hashCode(this.typeCode);
        hash = 11 * hash + Objects.hashCode(this.type);
        hash = 11 * hash + Objects.hashCode(this.dateTime);
        hash = 11 * hash + Objects.hashCode(this.detail);
        hash = 11 * hash + Objects.hashCode(this.outcome);
        hash = 11 * hash + Objects.hashCode(this.outcomeDetail);
        hash = 11 * hash + Objects.hashCode(this.outcomeDetailMessage);
        hash = 11 * hash + Objects.hashCode(this.detailData);
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
        final Event other = (Event) obj;
        if (!Objects.equals(this.identifier, other.identifier)) {
            return false;
        }
        if (!Objects.equals(this.typeCode, other.typeCode)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.detail, other.detail)) {
            return false;
        }
        if (!Objects.equals(this.outcome, other.outcome)) {
            return false;
        }
        if (!Objects.equals(this.outcomeDetail, other.outcomeDetail)) {
            return false;
        }
        if (!Objects.equals(this.outcomeDetailMessage, other.outcomeDetailMessage)) {
            return false;
        }
        if (!Objects.equals(this.detailData, other.detailData)) {
            return false;
        }
        return Objects.equals(this.dateTime, other.dateTime);
    }

    /**
     * Indique la représentation en tant que String de l'objet.
     *
     * @return la représentation en tant que String
     */
    @Override
    public String toString() {
        return "Event{" + "identifier=" + identifier + ", typeCode=" + typeCode + ", type=" + type + ", dateTime=" + dateTime
                + ", detail=" + detail + ", outcome=" + outcome + ", outcomeDetail=" + outcomeDetail + ", outcomeDetailMessage="
                + outcomeDetailMessage + ", detailData=" + detailData + '}';
    }

}
