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
package fr.xelians.sipg.service.sedav2;

/**
 * La classe Sedav2Config représente la configuration utilisée lors de la conversion d'une archive
 * au format SEDA v2.1. Les valeurs par défaut sont définies dans la classe Sedav2ConfigBuilder.
 *
 * @param validate spécifie si une validation doit être réalisée lors de la conversion
 * @param format spécifie si le fichier de description doit être formaté (pretty-print)
 * @param indent spécifie la valeur de l'indentation lors du formatage
 * @param thread spécifie le nombre de threads à utiliser lors de la conversion
 * @param strict spécifie si la conversion doit être stricte
 * @param checkBinary spécifie si la validation vérifie les objets binaires
 * @param checkSize spécifie si la validation la taille des objets binaires
 * @param checkDigest spécifie si la validation vérifie l'empreinte des objets binaires
 * @param useMemory spécifie si la génération du sip utilise la mémoire ou le disque
 * @author Emmanuel Deviller
 * @see SedaConfigBuilder
 */
public record SedaConfig(
    boolean validate,
    boolean format,
    int indent,
    int thread,
    boolean strict,
    boolean checkBinary,
    boolean checkSize,
    boolean checkDigest,
    boolean useMemory,
    boolean identifyFileFormat) {

  /** The constant DEFAULT. */
  public static final SedaConfig DEFAULT = SedaConfigBuilder.builder().build();
}
