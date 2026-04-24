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

import java.time.LocalDateTime;

/**
 * La classe EventBuilder facilite la création d'un objet Event en suivant le principe de conception
 * du pattern builder.
 *
 * @author Emmanuel Deviller
 * @see Event
 */
public class EventBuilder {

  private String identifier;
  private String typeCode;
  private String type;
  private LocalDateTime dateTime;
  private String detail;
  private String outcome;
  private String outcomeDetail;
  private String outcomeDetailMessage;
  private String detailData;

  private EventBuilder() {}

  /**
   * Instancie le builder.
   *
   * @return le builder
   */
  public static EventBuilder builder() {
    return new EventBuilder();
  }

  /**
   * Spécifie l'identifiant de l'événement.
   *
   * @param identifier l'identifiant
   * @return le builder
   */
  public EventBuilder withIdentifier(String identifier) {
    this.identifier = identifier;
    return this;
  }

  /**
   * Spécifie le code du type de l'événement.
   *
   * @param typeCode le code du type
   * @return le builder
   */
  public EventBuilder withTypeCode(String typeCode) {
    this.typeCode = typeCode;
    return this;
  }

  /**
   * Spécifie le type de l'événement.
   *
   * @param type le type
   * @return le builder
   */
  public EventBuilder withType(String type) {
    this.type = type;
    return this;
  }

  /**
   * Spécifie la date et l'heure.
   *
   * @param dateTime la date et l'heure
   * @return le builder
   */
  public EventBuilder withDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
    return this;
  }

  /**
   * Spécifie le détail de l'événement.
   *
   * @param detail le détail
   * @return le builder
   */
  public EventBuilder withDetail(String detail) {
    this.detail = detail;
    return this;
  }

  /**
   * Spécifie le résultat de l'événement.
   *
   * @param outcome le résultat
   * @return le builder
   */
  public EventBuilder withOutcome(String outcome) {
    this.outcome = outcome;
    return this;
  }

  /**
   * Spécifie le détail du résultat de l'événement.
   *
   * @param outcomeDetail le détail du résultat
   * @return le builder
   */
  public EventBuilder withOutcomeDetail(String outcomeDetail) {
    this.outcomeDetail = outcomeDetail;
    return this;
  }

  /**
   * Spécifie le message détail du résultat de l'événement.
   *
   * @param outcomeDetailMessage le message détail du résultat
   * @return le builder
   */
  public EventBuilder withOutcomeDetailMessage(String outcomeDetailMessage) {
    this.outcomeDetailMessage = outcomeDetailMessage;
    return this;
  }

  /**
   * Spécifie le message technique détaillant l'erreur de l'événement.
   *
   * @param detailData le message technique de l'erreur
   * @return le builder
   */
  public EventBuilder withDetailData(String detailData) {
    this.detailData = detailData;
    return this;
  }

  /**
   * Instancie la classe Event selon les paramètres précédemment spécifiés dans le builder.
   *
   * @return l'évènement
   */
  public Event build() {
    return new Event(
        identifier,
        typeCode,
        type,
        dateTime,
        detail,
        outcome,
        outcomeDetail,
        outcomeDetailMessage,
        detailData);
  }
}
