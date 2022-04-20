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
 * La classe JsonConfig représente la configuration utilisée lors de la sérialisation d'une archive en json. Les valeurs
 * par défaut sont définies dans la classe JsonConfigBuilder.
 *
 * @author Emmanuel Deviller
 * @see JsonConfigBuilder
 */
public class JsonConfig {

    /**
     * The constant DEFAULT.
     */
    public static final JsonConfig DEFAULT = JsonConfigBuilder.builder().build();

    private final boolean format;

    /**
     * Instancie la classe.
     *
     * @param format true si le fichier json doit être formaté, false sinon
     */
    public JsonConfig(boolean format) {
        this.format = format;
    }

    /**
     * Indique si le fichier json doit être formaté.
     *
     * @return true si le fichier json doit être formaté, false sinon.
     */
    public boolean isFormat() {
        return format;
    }

    @Override
    public String toString() {
        return "JsonConfig{" + "format=" + format + "'}'";
    }

}
