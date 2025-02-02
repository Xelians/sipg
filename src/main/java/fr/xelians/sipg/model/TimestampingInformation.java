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
import java.util.Objects;

/**
 * La classe TimestampingInformation contient les informations d'horodatage dans un contexte de signature.
 *
 * <p>Supporté en SEDA v2.3
 *
 * @author Emmanuel Deviller
 * @see SigningInformation
 */
public class TimestampingInformation {

  /** Horodatage de la signature. */
  protected LocalDateTime timeStamp;

  /** Information d'horodatage additionnelle. */
  protected String additionalTimestampingInformation;

  /** Instancie la classe. */
  public TimestampingInformation() {
    // Needed for jackson
  }

  /**
   * Instancie la classe.
   *
   * @param timeStamp l'horodatage de la signature
   * @param additionalTimestampingInformation l'information d'horodatage additionnelle
   */
  public TimestampingInformation(
      LocalDateTime timeStamp, String additionalTimestampingInformation) {
    this.timeStamp = timeStamp;
    this.additionalTimestampingInformation = additionalTimestampingInformation;
  }

  /**
   * Indique l'horodatage de la signature
   *
   * @return l'horodatage de la signature.
   */
  public LocalDateTime getTimeStamp() {
    return timeStamp;
  }

  /**
   * Spécifie l'horodatage de la signature.
   *
   * @param timeStamp l'horodatage de la signature
   */
  public void setTimeStamp(LocalDateTime timeStamp) {
    this.timeStamp = timeStamp;
  }

  /**
   * Indique l'information d'horodatage additionnelle.
   *
   * @return l'information d'horodatage additionnelle
   */
  public String getAdditionalTimestampingInformation() {
    return additionalTimestampingInformation;
  }

  /**
   * Spécifie l'information d'horodatage additionnelle.
   *
   * @param additionalTimestampingInformation l'information d'horodatage additionnelle
   */
  public void setAdditionalTimestampingInformation(String additionalTimestampingInformation) {
    this.additionalTimestampingInformation = additionalTimestampingInformation;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;

    TimestampingInformation that = (TimestampingInformation) o;
    return Objects.equals(getTimeStamp(), that.getTimeStamp())
        && Objects.equals(
            getAdditionalTimestampingInformation(), that.getAdditionalTimestampingInformation());
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(getTimeStamp());
    result = 31 * result + Objects.hashCode(getAdditionalTimestampingInformation());
    return result;
  }
}
