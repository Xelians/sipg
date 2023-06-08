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
 * La classe UpdateOperation représente une opération de mise à jour d'une unité d'archive. (Not implemented yet)
 *
 * <p>
 * Supporté en SEDA v2.1.
 * </p>
 *
 * @author Emmanuel Deviller
 */
public class UpdateOperation {

    private String systemId;
    private String metadataName;
    private String metadataValue;

    /**
     * Instantiates a new Update operation.
     *
     * @param systemId the system id
     */
    public UpdateOperation(String systemId) {
        this.systemId = systemId;
    }

    /**
     * Instantiates a new Update operation.
     *
     * @param metadataName  the metadata name
     * @param metadataValue the metadata value
     */
    @JsonCreator
    public UpdateOperation(@JsonProperty("metadataName") String metadataName,
                           @JsonProperty("metadataValue") String metadataValue) {
        this.metadataName = metadataName;
        this.metadataValue = metadataValue;
    }

    /**
     * Gets system id.
     *
     * @return the system id
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * Gets metadata name.
     *
     * @return the metadata name
     */
    public String getMetadataName() {
        return metadataName;
    }

    /**
     * Gets metadata value.
     *
     * @return the metadata value
     */
    public String getMetadataValue() {
        return metadataValue;
    }

}
