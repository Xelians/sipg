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
package fr.xelians.sipg.service.sedajson;

import fr.xelians.sipg.utils.SipException;

/**
 * L'énumération SedaJsonVersion représente les versions supportées du standard SEDA JSON. Chaque
 * version est associée au schéma JSON embarqué qui définit l'ontologie complète du standard.
 *
 * @author Emmanuel Deviller
 */
public enum SedaJsonVersion {

  /** La version 2.0 du standard SEDA JSON. */
  V2_0("2.0", "seda-json-2.0-schema.json");

  private final String version;
  private final String schema;

  SedaJsonVersion(String version, String schema) {
    this.version = version;
    this.schema = schema;
  }

  /**
   * Indique la version du standard telle qu'elle apparaît dans la clé {@code SedaJsonVersion} du
   * manifeste.
   *
   * @return la version du standard
   */
  public String getVersion() {
    return version;
  }

  /**
   * Indique le nom de la ressource du schéma JSON embarqué associé à cette version.
   *
   * @return le nom de la ressource du schéma
   */
  public String getSchema() {
    return schema;
  }

  /**
   * Retourne la version du standard SEDA JSON correspondant à la valeur spécifiée.
   *
   * @param version la valeur de la version (ex. "2.0")
   * @return la version du standard
   */
  public static SedaJsonVersion ofVersion(String version) {
    for (SedaJsonVersion value : values()) {
      if (value.version.equals(version)) {
        return value;
      }
    }
    throw new SipException(String.format("SEDA JSON version '%s' is not supported", version));
  }

  @Override
  public String toString() {
    return "JSON_" + version;
  }
}
