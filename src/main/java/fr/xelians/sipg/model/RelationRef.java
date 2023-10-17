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

import fr.xelians.sipg.utils.SipUtils;
import org.apache.commons.lang3.Validate;

/**
 * La classe abstraite RelationRef représente une relation de référence à une entité faisant ou ne faisant pas partie du
 * présent paquet d'information.
 *
 * @param <T> le type de la référence
 * @author Emmanuel Deviller
 */
public abstract class RelationRef<T> {

    /**
     * The Reference.
     */
    protected final T reference;

    /**
     * Instancie la relation de référence
     *
     * @param reference la référence
     */
    protected RelationRef(T reference) {
        Validate.notNull(reference, SipUtils.NOT_NULL, "reference");
        this.reference = reference;
    }

    /**
     * Indique la référence de la relation
     *
     * @return la référence
     */
    public T getReference() {
        return reference;
    }

}
