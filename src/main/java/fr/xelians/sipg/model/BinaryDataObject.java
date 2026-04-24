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

import static fr.xelians.sipg.utils.SipUtils.NOT_NULL;

import java.nio.file.Path;
import java.util.function.Supplier;
import org.apache.commons.lang3.Validate;

public abstract class BinaryDataObject {

  public static final String BINARY_MASTER = "BinaryMaster";
  public static final String DISSEMINATION = "Dissemination";
  public static final String THUMBNAIL = "Thumbnail";
  public static final String TEXT_CONTENT = "TextContent";

  //    @JsonIgnore
  protected String xmlId;

  /** Le path de l'objet binaire. */
  protected Path binaryPath;

  protected Supplier<Path> binaryPathSupplier;

  /** La version de l'objet binaire. */
  protected String binaryVersion;

  protected FormatIdentification formatIdentification = new FormatIdentification();

  protected long size;

  /** L'algorithme utilisé pour générer l'empreinte (hash) de l'objet binaire. */
  protected final String digestAlgorithm = "SHA-512";

  /** Les informations techniques de l'objet binaire. */
  protected FileInfo fileInfo = new FileInfo();

  /**
   * Instancie la classe.
   *
   * @param qualifier the qualifier
   */
  protected BinaryDataObject(String qualifier) {
    this.binaryVersion = qualifier;
  }

  /**
   * Indique le fournisseur du path de l'objet binaire.
   *
   * @return le fournisseur du path de l'objet binaire
   */
  public Supplier<Path> getBinaryPathSupplier() {
    return binaryPathSupplier;
  }

  /**
   * Spécifie le fournisseur du path de l'objet binaire.
   *
   * @param binaryPathSupplier le fournisseur du path de l'objet binaire
   */
  public void setBinaryPathSupplier(Supplier<Path> binaryPathSupplier) {
    this.binaryPathSupplier = binaryPathSupplier;
  }

  /**
   * Indique le path de l'objet binaire.
   *
   * @return le path de l'objet binaire
   */
  public Path getBinaryPath() {
    return binaryPath;
  }

  /**
   * Spécifie le path de l'objet binaire.
   *
   * @param binaryPath le path de l'objet binaire
   */
  public void setBinaryPath(Path binaryPath) {
    this.binaryPath = binaryPath;
  }

  /**
   * Indique la version de l'objet binaire.
   *
   * @return la version de l'objet binaire
   */
  public String getBinaryVersion() {
    return binaryVersion;
  }

  /**
   * Spécifie la version de l'objet binaire.
   *
   * @param objectVersion la version de l'objet binaire
   */
  public abstract void setBinaryVersion(String objectVersion);

  /**
   * Indique l'algorithme utilisé pour générer l'empreinte (hash) de l'objet binaire.
   *
   * @return l'algorithme utilisé
   */
  public String getDigestAlgorithm() {
    return digestAlgorithm;
  }

  /**
   * Indique les informations techniques de l'objet binaire.
   *
   * @return les informations techniques
   */
  public FileInfo getFileInfo() {
    return fileInfo;
  }

  /**
   * Spécifie les informations techniques de l'objet binaire.
   *
   * @param fileInfo les informations techniques
   */
  public void setFileInfo(FileInfo fileInfo) {
    Validate.notNull(fileInfo, NOT_NULL, "fileInfo");
    this.fileInfo = fileInfo;
  }

  /**
   * Indique les informations de format de l'objet binaire.
   *
   * @return les informations techniques
   */
  public FormatIdentification getFormatIdentification() {
    return formatIdentification;
  }

  /**
   * Spécifie les informations de format de l'objet binaire.
   *
   * @param formatIdentification les informations de format
   */
  public void setFormatIdentification(FormatIdentification formatIdentification) {
    Validate.notNull(formatIdentification, NOT_NULL, "formatIdentification");
    this.formatIdentification = formatIdentification;
  }
}
