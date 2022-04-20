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
package fr.xelians.sipg.service.json;

/**
 * La classe JsonConfigBuilder facilite la création d'un objet JsonConfig en suivant le principe de conception du
 * pattern builder.
 *
 * @author Emmanuel Deviller
 * @see JsonConfig
 */
public class JsonConfigBuilder {

    private Boolean format;

    private JsonConfigBuilder() {
        format = false;
    }

    /**
     * Instancie le builder.
     *
     * @return le builder
     */
    public static JsonConfigBuilder builder() {
        return new JsonConfigBuilder();
    }

    /**
     * Spécifie si le json doit être formaté (pretty-print). False par défaut.
     *
     * @param format true si le fichier de description doit être formaté, false sinon
     * @return le builder
     */
    public JsonConfigBuilder format(boolean format) {
        this.format = format;
        return this;
    }

    /**
     * Instancie la classe JsonConfig selon les paramètres précédemment spécifiés dans le builder.
     *
     * @return la configuration json
     */
    public JsonConfig build() {
        return new JsonConfig(format);
    }
}
