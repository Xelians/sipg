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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * La classe DataObjectRef représente une référence à un objet-données ou à un groupe
 * d'objets-données interne(s).
 *
 * <p>Supporté en SEDA v2.1.
 *
 * @author Emmanuel Deviller
 * @see RelationRef
 */
public class DataObjectRef extends RelationRef<ArchiveUnit> {

  /**
   * Instancie la classe avec l'argument spécifié.
   *
   * @param reference l'unité d'archive interne contenant l'objet référencée
   */
  @JsonCreator
  public DataObjectRef(@JsonProperty("reference") ArchiveUnit reference) {
    super(reference);
  }
}
