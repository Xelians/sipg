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
package fr.xelians.sipg.service.fntcv4;

/**
 * La classe Fntcv4Config représente la configuration utilisée lors de la sérialisation d'une
 * archive au format FNTC v4. Les valeurs par défaut sont définies dans la classe
 * Fntcv4ConfigBuilder.
 *
 * @author Emmanuel Deviller
 * @see Fntcv4ConfigBuilder
 */
public record Fntcv4Config(
    boolean validate,
    boolean format,
    int indent,
    int thread,
    boolean strict,
    boolean checkBinary,
    boolean checkSize,
    boolean checkDigest,
    boolean useMemory) {

  /** The constant DEFAULT. */
  public static final Fntcv4Config DEFAULT = Fntcv4ConfigBuilder.builder().build();

  /**
   * Instancie la classe.
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
   */
  public Fntcv4Config {}

  /**
   * Indique si une validation doit être réalisée lors de la conversion.
   *
   * @return si une validation doit être réalisée
   */
  @Override
  public boolean validate() {
    return validate;
  }

  /**
   * Indique si le fichier de description doit être formaté (pretty-print) lors de la conversion.
   *
   * @return si le fichier de description doit être formaté
   */
  @Override
  public boolean format() {
    return format;
  }

  /**
   * Indique la valeur de l'indentation lors du formatage.
   *
   * @return la valeur de l'indentation lors du formatage
   */
  @Override
  public int indent() {
    return indent;
  }

  /**
   * Indique le nombre de threads à utiliser lors de la conversion.
   *
   * @return le nombre de threads
   */
  @Override
  public int thread() {
    return thread;
  }

  /**
   * Indique si la conversion doit être stricte.
   *
   * @return si la conversion doit être stricte
   */
  @Override
  public boolean strict() {
    return strict;
  }

  /**
   * Indique si la validation vérifie les objets binaires.
   *
   * @return si la validation vérifie les objets binaires
   */
  @Override
  public boolean checkBinary() {
    return checkBinary;
  }

  /**
   * Indique si la validation vérifie la taille des objets binaires.
   *
   * @return si la validation vérifie la taille des objets binaires
   */
  @Override
  public boolean checkSize() {
    return checkSize;
  }

  /**
   * Indique si la validation vérifie l'empreinte des objets binaires.
   *
   * @return si la validation vérifie l'empreinte des objets binaires
   */
  @Override
  public boolean checkDigest() {
    return checkDigest;
  }

  /**
   * Indique si la génération du sip utilise la mémoire ou le disque
   *
   * @return si la génération du sip utilise la mémoire ou le disque
   */
  @Override
  public boolean useMemory() {
    return useMemory;
  }

  @Override
  public String toString() {
    return "Fntcv4Config{"
        + "validate="
        + validate
        + ", format="
        + format
        + ", indent="
        + indent
        + ", thread="
        + thread
        + ", strict="
        + strict
        + ", checkBinary="
        + checkBinary
        + ", checkSize="
        + checkSize
        + ", checkDigest="
        + checkDigest
        + ", useMemory="
        + useMemory
        + '}';
  }
}
