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
 * La classe AccessRules représente les règles de communicabilité (accessibilité) des archives.
 *
 * <p>Supporté en SEDA v2.1 et FNTC v4.
 *
 * @author Emmanuel Deviller
 */
public class AccessRules extends AbstractSimpleRules {

  /** Instancie la classe. */
  public AccessRules() {
    super();
  }

  /**
   * Instancie la classe avec une règle spécifiée par les paramètres.
   *
   * @param name la référence de la règle communicabilité
   * @param startDate date de départ de calcul de la règle de communicabilité
   */
  public AccessRules(String name, LocalDate startDate) {
    super(name, startDate);
  }
}
