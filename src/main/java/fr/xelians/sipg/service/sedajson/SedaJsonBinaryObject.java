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
 * La classe SedaJsonBinaryObject représente un objet binaire extrait du manifeste SEDA JSON lors du
 * parsing en streaming. Elle est utilisée lors de la validation pour vérifier l'existence, la
 * taille et l'empreinte des objets binaires du paquet zip.
 *
 * @param uri l'URI de l'objet binaire dans le paquet zip
 * @param size la taille de l'objet binaire
 * @param digest l'empreinte de l'objet binaire
 * @param algorithm l'algorithme de l'empreinte
 * @author Emmanuel Deviller
 */
record SedaJsonBinaryObject(String uri, long size, String digest, String algorithm) {}
