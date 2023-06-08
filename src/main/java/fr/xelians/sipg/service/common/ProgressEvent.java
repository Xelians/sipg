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
package fr.xelians.sipg.service.common;

/**
 * La classe ProgressEvent représente un évènement associé à la progression d'un processus.
 *
 * @param <T> le type de l'étape de l'événement
 * @author Emmanuel Deviller
 */
public class ProgressEvent<T> {

    private final String id;
    private final ProgressState status;
    private final T step;
    private final String message;

    /**
     * Instancie la classe.
     *
     * @param id      l'identifiant de l'événement
     * @param status  le statut de l'événement
     * @param step    l'étape de l'événement
     * @param message le message de l'événement
     */
    public ProgressEvent(String id, ProgressState status, T step, String message) {
        this.id = id;
        this.status = status;
        this.step = step;
        this.message = message;
    }

    /**
     * Indique l'identifiant de l'événement.
     *
     * @return l'identifiant de l'événement
     */
    public String getId() {
        return id;
    }

    /**
     * Indique le statut de l'événement.
     *
     * @return le statut
     */
    public ProgressState getStatus() {
        return status;
    }

    /**
     * Indique l'étape de l'événement.
     *
     * @return l'étape
     */
    public T getStep() {
        return step;
    }

    /**
     * Indique le message de l'événement.
     *
     * @return le message
     */
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ProgressEvent{" + "id=" + id + ", status=" + status + ", step=" + step + ", message=" + message + '}';
    }

}
