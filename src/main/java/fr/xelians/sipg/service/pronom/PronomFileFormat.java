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
package fr.xelians.sipg.service.pronom;

import java.util.List;
import org.jspecify.annotations.Nullable;

/**
 * Un élément {@code FileFormat} d'un fichier de signatures PRONOM, tel que lu. Les priorités sont
 * exprimées en PUID : le parseur résout les références numériques du fichier, car l'{@code ID}
 * numérique est positionnel et change d'un producteur de fichier à l'autre.
 *
 * @param puid l'identifiant PRONOM du format ({@code fmt/18}) ou la clé d'un format client
 * @param name le nom du format
 * @param mimeType le type MIME du format
 * @param version la version du format
 * @param extensions les extensions de fichier du format, sans doublon
 * @param hasPriorityOverFileFormatIds les PUID des formats sur lesquels ce format est prioritaire
 * @author Julien Cornille
 */
public record PronomFileFormat(
    String puid,
    String name,
    @Nullable String mimeType,
    @Nullable String version,
    List<String> extensions,
    List<String> hasPriorityOverFileFormatIds) {}
