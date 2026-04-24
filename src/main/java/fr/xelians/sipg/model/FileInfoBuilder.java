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

import java.time.LocalDateTime;

/**
 * La classe FileInfoBuilder facilite la création d'un objet FileInfo en suivant le principe de
 * conception du pattern builder.
 *
 * @author Emmanuel Deviller
 * @see FileInfo
 */
public class FileInfoBuilder {

  private String filename;
  private String creatingApplicationName;
  private String creatingApplicationVersion;
  private LocalDateTime dateCreatedByApplication;
  private String creatingOs;
  private String creatingOsVersion;
  private LocalDateTime lastModified;

  private FileInfoBuilder() {}

  /**
   * Instancie le builder.
   *
   * @return le builder
   */
  public static FileInfoBuilder builder() {
    return new FileInfoBuilder();
  }

  /**
   * Spécifie le nom du fichier d'origine.
   *
   * @param filename le nom du fichier d'origine
   * @return le builder
   */
  public FileInfoBuilder withFilename(String filename) {
    this.filename = filename;
    return this;
  }

  /**
   * Spécifie le nom de l'application utilisée pour créer le fichier.
   *
   * @param creatingApplicationName le nom de l'application utilisée pour créer le fichier
   * @return le builder
   */
  public FileInfoBuilder withCreatingApplicationName(String creatingApplicationName) {
    this.creatingApplicationName = creatingApplicationName;
    return this;
  }

  /**
   * Spécifie la version de l'application utilisée pour créer le fichier.
   *
   * @param creatingApplicationVersion la version de l'application utilisée pour créer le fichier
   * @return le builder
   */
  public FileInfoBuilder withCreatingApplicationVersion(String creatingApplicationVersion) {
    this.creatingApplicationVersion = creatingApplicationVersion;
    return this;
  }

  /**
   * Spécifie la date de création du fichier.
   *
   * @param dateCreatedByApplication la date de création du fichier
   * @return le builder
   */
  public FileInfoBuilder withDateCreatedByApplication(LocalDateTime dateCreatedByApplication) {
    this.dateCreatedByApplication = dateCreatedByApplication;
    return this;
  }

  /**
   * Spécifie le système d’exploitation utilisé pour créer le fichier.
   *
   * @param creatingOs le système d’exploitation utilisé pour créer le fichier
   * @return le builder
   */
  public FileInfoBuilder withCreatingOs(String creatingOs) {
    this.creatingOs = creatingOs;
    return this;
  }

  /**
   * Spécifie la version du système d'exploitation utilisé pour créer le fichier.
   *
   * @param creatingOsVersion la version du système d'exploitation utilisé pour créer le fichier
   * @return le builder
   */
  public FileInfoBuilder withCreatingOsVersion(String creatingOsVersion) {
    this.creatingOsVersion = creatingOsVersion;
    return this;
  }

  /**
   * Spécifie la date de la dernière modification du fichier.
   *
   * @param lastModified la date de la dernière modification du fichier
   * @return le builder
   */
  public FileInfoBuilder withLastModified(LocalDateTime lastModified) {
    this.lastModified = lastModified;
    return this;
  }

  /**
   * Instancie la classe FileInfo selon les paramètres précédemment spécifiés dans le builder.
   *
   * @return le fileinfo
   */
  public FileInfo build() {
    return new FileInfo(
        filename,
        creatingApplicationName,
        creatingApplicationVersion,
        dateCreatedByApplication,
        creatingOs,
        creatingOsVersion,
        lastModified);
  }
}
