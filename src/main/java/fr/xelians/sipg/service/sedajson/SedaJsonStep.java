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

/**
 * L'énumération SedaJsonStep représente les étapes de la validation d'une archive SEDA JSON. Ces
 * étapes sont notifiées au callback de progression lors de la validation.
 *
 * @author Emmanuel Deviller
 * @see fr.xelians.sipg.service.common.ProgressListener
 */
public enum SedaJsonStep {

  /** Le début de la validation. */
  START,

  /** La vérification de l'existence de l'archive. */
  ARCHIVE_EXIST,

  /** La vérification de la lisibilité de l'archive. */
  ARCHIVE_READABLE,

  /** La vérification de l'ouverture de l'archive zip. */
  ARCHIVE_UNZIP,

  /** La vérification de l'existence du manifeste. */
  MANIFEST_EXIST,

  /** La vérification de la taille maximale du manifeste. */
  MANIFEST_SIZE,

  /** La validation du manifeste selon le schéma JSON du standard. */
  MANIFEST_SCHEMA,

  /** Le parsing en streaming du manifeste (ordre des clés et objets binaires). */
  MANIFEST_PARSE,

  /** La vérification de l'existence de l'objet binaire. */
  BINARY_EXIST,

  /** La vérification du dossier de l'objet binaire. */
  BINARY_FOLDER,

  /** La vérification de la taille de l'objet binaire. */
  BINARY_SIZE,

  /** La vérification de l'empreinte de l'objet binaire. */
  BINARY_DIGEST,

  /** La fin de la validation. */
  COMPLETE
}
