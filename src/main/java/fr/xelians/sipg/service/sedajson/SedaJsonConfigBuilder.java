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
package fr.xelians.sipg.service.sedajson;

/**
 * La classe SedaJsonConfigBuilder facilite la création d'un objet SedaJsonConfig en suivant le
 * principe de conception du pattern builder.
 *
 * @author Emmanuel Deviller
 * @see SedaJsonConfig
 */
public class SedaJsonConfigBuilder {

  /** La taille maximale par défaut du manifeste lors de la validation (128 Mio). */
  public static final long DEFAULT_MAX_MANIFEST_SIZE = 134_217_728L;

  private boolean validate = true;
  private boolean format = false;
  private int indent = 2;
  private int thread = 0;
  private boolean strict = true;
  private boolean checkBinary = true;
  private boolean checkSize = true;
  private boolean checkDigest = true;
  private boolean useMemory = false;
  private boolean identifyFileFormat = true;
  private boolean xmlId = true;
  private long maxManifestSize = DEFAULT_MAX_MANIFEST_SIZE;

  private SedaJsonConfigBuilder() {}

  /**
   * Instancie le builder.
   *
   * @return le builder
   */
  public static SedaJsonConfigBuilder builder() {
    return new SedaJsonConfigBuilder();
  }

  /**
   * Spécifie si une validation par schéma JSON doit être réalisée lors de la conversion. True par
   * défaut.
   *
   * @param validate si une validation doit être réalisée
   * @return le builder
   */
  public SedaJsonConfigBuilder validate(boolean validate) {
    this.validate = validate;
    return this;
  }

  /**
   * Spécifie si le manifeste doit être formaté (pretty-print). False par défaut.
   *
   * @param format si le manifeste doit être formaté
   * @return le builder
   */
  public SedaJsonConfigBuilder format(boolean format) {
    this.format = format;
    return this;
  }

  /**
   * Spécifie la valeur de l'indentation lors du formatage. Par défaut 2.
   *
   * @param indent la valeur de l'indentation
   * @return le builder
   */
  public SedaJsonConfigBuilder indent(int indent) {
    this.indent = indent;
    return this;
  }

  /**
   * Spécifie le nombre de threads à utiliser lors de la conversion. Le nombre de cœurs du CPU par
   * défaut.
   *
   * @param thread le nombre de threads
   * @return le builder
   */
  public SedaJsonConfigBuilder thread(int thread) {
    this.thread = thread;
    return this;
  }

  /**
   * Spécifie si la conversion doit être stricte. En mode strict, les constructions du modèle non
   * représentables en SEDA JSON (références entre unités, signature au niveau du message, etc.)
   * provoquent une exception. En mode non strict, elles sont ignorées avec un avertissement. True
   * par défaut.
   *
   * @param strict si la conversion doit être stricte
   * @return le builder
   */
  public SedaJsonConfigBuilder strict(boolean strict) {
    this.strict = strict;
    return this;
  }

  /**
   * Spécifie si la validation doit vérifier les objets binaires.
   *
   * @param checkBinary si la validation doit vérifier les objets binaires
   * @return le builder
   */
  public SedaJsonConfigBuilder checkBinary(boolean checkBinary) {
    this.checkBinary = checkBinary;
    return this;
  }

  /**
   * Spécifie si la validation doit vérifier la taille des objets binaires.
   *
   * @param checkSize si la validation doit vérifier la taille des objets binaires
   * @return le builder
   */
  public SedaJsonConfigBuilder checkSize(boolean checkSize) {
    this.checkSize = checkSize;
    return this;
  }

  /**
   * Spécifie si la validation doit vérifier l'empreinte des objets binaires.
   *
   * @param checkDigest si la validation doit vérifier l'empreinte des objets binaires
   * @return le builder
   */
  public SedaJsonConfigBuilder checkDigest(boolean checkDigest) {
    this.checkDigest = checkDigest;
    return this;
  }

  /**
   * Spécifie si la génération du sip utilise la mémoire ou le disque. La génération en mémoire peut
   * s'avérer légèrement plus rapide, mais peut entrainer un dépassement de la mémoire de la machine
   * virtuelle. Il est fortement conseiller de s'assurer de connaitre la capacité mémoire de la
   * machine virtuelle et des sip à générer avant d'utiliser cette option.
   *
   * @param useMemory si la genération du sip doit se faire en mémoire
   * @return le builder
   */
  public SedaJsonConfigBuilder useMemory(boolean useMemory) {
    this.useMemory = useMemory;
    return this;
  }

  /**
   * Spécifie si le format de fichier des objets binaires doit etre identifié.
   *
   * @param identifyFileFormat si le format de fichier des objets binaires doit etre identifié.
   * @return le builder
   */
  public SedaJsonConfigBuilder identifyFileFormat(boolean identifyFileFormat) {
    this.identifyFileFormat = identifyFileFormat;
    return this;
  }

  /**
   * Spécifie si un identifiant unique XmlId est généré pour chaque unité d'archive (préfixe {@code
   * AU}), chaque objet de données binaire (préfixe {@code BDO}) et chaque objet de données physique
   * (préfixe {@code PDO}). L'identifiant est unique dans la totalité du manifeste. True par défaut.
   *
   * @param xmlId si un identifiant unique XmlId doit être généré
   * @return le builder
   */
  public SedaJsonConfigBuilder xmlId(boolean xmlId) {
    this.xmlId = xmlId;
    return this;
  }

  /**
   * Spécifie la taille maximale en octets du manifeste lors de la validation. La validation par
   * schéma JSON matérialise le manifeste en mémoire (contrairement à la validation XSD qui opère en
   * streaming), d'où cette limite. 128 Mio par défaut.
   *
   * @param maxManifestSize la taille maximale du manifeste en octets
   * @return le builder
   */
  public SedaJsonConfigBuilder maxManifestSize(long maxManifestSize) {
    this.maxManifestSize = maxManifestSize;
    return this;
  }

  /**
   * Instancie la classe SedaJsonConfig selon les paramètres précédemment spécifiés dans le builder.
   *
   * @return la configuration SEDA JSON
   */
  public SedaJsonConfig build() {
    return new SedaJsonConfig(
        validate,
        format,
        indent,
        thread,
        strict,
        checkBinary,
        checkSize,
        checkDigest,
        useMemory,
        identifyFileFormat,
        xmlId,
        maxManifestSize);
  }
}
