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

/**
 * Ce package contient les classes nécessaires à la génération et à la validation d'archives au
 * standard SEDA JSON 1.0. Le standard SEDA JSON est une transposition en JSON de l'ontologie SEDA
 * v2 : le paquet zip contient un manifeste {@code manifest.json} à la racine et les objets binaires
 * dans le dossier {@code content/}.
 *
 * <p>Contrairement au XML, le JSON ne comporte aucun mécanisme d'identifiant ni de référence : les
 * objets numériques et physiques sont déclarés directement dans leur unité d'archive et la
 * hiérarchie des unités s'exprime uniquement par imbrication. L'ordre des clés est imposé par le
 * standard (miroir de la séquence du XSD SEDA), ce qui permet un parsing en streaming en une seule
 * passe.
 */
package fr.xelians.sipg.service.sedajson;
