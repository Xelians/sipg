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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;

/**
 * La classe FileInfo représente les propriétés techniques génériques du fichier de l'objet binaire (nom d’origine,
 * logiciel de création, système d’exploitation de création).
 *
 * <p>
 * Supporté en SEDA v2.1
 * </p>
 *
 * @author Emmanuel Deviller
 */
@JsonDeserialize(builder = FileInfoBuilder.class)
public class FileInfo {

    /**
     * Le nom du fichier d'origine.
     */
    protected String filename;

    /**
     * Le nom de l'application utilisée pour créer le fichier.
     */
    protected String creatingApplicationName;

    /**
     * La version de l'application utilisée pour créer le fichier.
     */
    protected String creatingApplicationVersion;

    /**
     * La date de création du fichier.
     */
    protected LocalDateTime dateCreatedByApplication;

    /**
     * Le système d’exploitation utilisé pour créer le fichier.
     */
    protected String creatingOs;

    /**
     * La version du système d'exploitation utilisé pour créer le fichier.
     */
    protected String creatingOsVersion;

    /**
     * La date de la dernière modification du fichier.
     */
    protected LocalDateTime lastModified;

    /**
     * Instancie la classe.
     */
    public FileInfo() {
    }

    /**
     * Instancie la classe.
     *
     * @param filename                   le nom du fichier d'origine
     * @param creatingApplicationName    le nom de l'application utilisée pour créer le fichier
     * @param creatingApplicationVersion la version de l'application utilisée pour créer le fichier
     * @param dateCreatedByApplication   la date de création du fichier
     * @param creatingOs                 le système d’exploitation utilisé pour créer le fichier
     * @param creatingOsVersion          la version du système d'exploitation utilisé pour créer le fichier
     * @param lastModified               la date de la dernière modification du fichier
     */
    public FileInfo(String filename, String creatingApplicationName, String creatingApplicationVersion,
                    LocalDateTime dateCreatedByApplication, String creatingOs, String creatingOsVersion, LocalDateTime lastModified) {

        this.filename = filename;
        this.creatingApplicationName = creatingApplicationName;
        this.creatingApplicationVersion = creatingApplicationVersion;
        this.dateCreatedByApplication = dateCreatedByApplication;
        this.creatingOs = creatingOs;
        this.creatingOsVersion = creatingOsVersion;
        this.lastModified = lastModified;
    }

    /**
     * Indique le nom du fichier d'origine.
     *
     * @return le nom du fichier d'origine
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Spécifie le nom du fichier d'origine.
     *
     * @param filename le nom du fichier d'origine
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Indique le nom de l'application utilisée pour créer le fichier.
     *
     * @return le nom de l'application utilisée pour créer le fichier
     */
    public String getCreatingApplicationName() {
        return creatingApplicationName;
    }

    /**
     * Spécifie le nom de l'application utilisée pour créer le fichier.
     *
     * @param creatingApplicationName le nom de l'application utilisée pour créer le fichier
     */
    public void setCreatingApplicationName(String creatingApplicationName) {
        this.creatingApplicationName = creatingApplicationName;
    }

    /**
     * Indique la version de l'application utilisée pour créer le fichier.
     *
     * @return la version de l'application utilisée pour créer le fichier
     */
    public String getCreatingApplicationVersion() {
        return creatingApplicationVersion;
    }

    /**
     * Spécifie la version de l'application utilisée pour créer le fichier.
     *
     * @param creatingApplicationVersion la version de l'application utilisée pour créer le fichier
     */
    public void setCreatingApplicationVersion(String creatingApplicationVersion) {
        this.creatingApplicationVersion = creatingApplicationVersion;
    }

    /**
     * Indique la date de création du fichier.
     *
     * @return la date de création du fichier
     */
    public LocalDateTime getDateCreatedByApplication() {
        return dateCreatedByApplication;
    }

    /**
     * Spécifie la date de création du fichier.
     *
     * @param dateCreatedByApplication la date de création du fichier
     */
    public void setDateCreatedByApplication(LocalDateTime dateCreatedByApplication) {
        this.dateCreatedByApplication = dateCreatedByApplication;
    }

    /**
     * Indique le système d’exploitation utilisé pour créer le fichier.
     *
     * @return le système d’exploitation utilisé pour créer le fichier
     */
    public String getCreatingOs() {
        return creatingOs;
    }

    /**
     * Spécifie le système d’exploitation utilisé pour créer le fichier.
     *
     * @param creatingOs le système d’exploitation utilisé pour créer le fichier
     */
    public void setCreatingOs(String creatingOs) {
        this.creatingOs = creatingOs;
    }

    /**
     * Indique la version du système d'exploitation utilisé pour créer le fichier.
     *
     * @return la version du système d'exploitation utilisé pour créer le fichier
     */
    public String getCreatingOsVersion() {
        return creatingOsVersion;
    }

    /**
     * Spécifie la version du système d'exploitation utilisé pour créer le fichier.
     *
     * @param creatingOsVersion la version du système d'exploitation utilisé pour créer le fichier
     */
    public void setCreatingOsVersion(String creatingOsVersion) {
        this.creatingOsVersion = creatingOsVersion;
    }

    /**
     * Indique la date de la dernière modification du fichier.
     *
     * @return la date de la dernière modification du fichier
     */
    public LocalDateTime getLastModified() {
        return lastModified;
    }

    /**
     * Spécifie la date de la dernière modification du fichier.
     *
     * @param lastModified la date de la dernière modification du fichier
     */
    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

}
