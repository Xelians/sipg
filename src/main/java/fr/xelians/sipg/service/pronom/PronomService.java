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
package fr.xelians.sipg.service.pronom;

import fr.xelians.sipg.utils.DroidUtils;
import fr.xelians.sipg.utils.SipException;
import fr.xelians.sipg.utils.SipUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import org.apache.commons.lang3.Validate;
import org.jspecify.annotations.Nullable;

/**
 * La classe PronomService permet de lire et de valider un référentiel des formats au standard
 * PRONOM, c'est-à-dire la collection des formats d'un fichier de signatures DROID. Le référentiel
 * peut être envoyé par un client ou être le référentiel par défaut embarqué dans sipg, qui est le
 * fichier de signatures utilisé pour identifier les formats des objets binaires.
 *
 * <p>Les référentiels lus sont immuables et peuvent être partagés entre threads.
 *
 * @author Emmanuel Deviller
 * @author Julien Cornille
 * @see PronomSignatureFile
 */
public class PronomService {

  private static final PronomService INSTANCE = new PronomService();

  // A PUID is made of technical key segments separated by single slashes
  private static final Pattern PUID_SEGMENT = Pattern.compile("^[A-Za-z0-9][A-Za-z0-9._-]*$");

  private PronomService() {}

  /**
   * Retourne l'instance singleton de la classe PronomService.
   *
   * @return l'instance singleton
   */
  public static PronomService getInstance() {
    return INSTANCE;
  }

  /**
   * Lit et valide le référentiel des formats d'un fichier de signatures PRONOM. Le flux n'est pas
   * fermé.
   *
   * @param inputStream le flux du fichier de signatures
   * @return le référentiel des formats
   * @throws SipException si le fichier n'est pas un fichier de signatures valide
   */
  public PronomSignatureFile parse(InputStream inputStream) {
    Validate.notNull(inputStream, SipUtils.NOT_NULL, "inputStream");
    return PronomSignatureFileParser.parse(inputStream);
  }

  /**
   * Lit et valide le référentiel des formats d'un fichier de signatures PRONOM.
   *
   * @param signatureFile le contenu du fichier de signatures
   * @return le référentiel des formats
   * @throws SipException si le fichier n'est pas un fichier de signatures valide
   */
  public PronomSignatureFile parse(byte[] signatureFile) {
    Validate.notNull(signatureFile, SipUtils.NOT_NULL, "signatureFile");
    return PronomSignatureFileParser.parse(new ByteArrayInputStream(signatureFile));
  }

  /**
   * Lit et valide le référentiel des formats d'un fichier de signatures PRONOM.
   *
   * @param path le path du fichier de signatures
   * @return le référentiel des formats
   * @throws SipException si le fichier ne peut pas être lu ou n'est pas un fichier de signatures
   *     valide
   */
  public PronomSignatureFile parse(Path path) {
    Validate.notNull(path, SipUtils.NOT_NULL, "path");
    try (InputStream is = Files.newInputStream(path)) {
      return PronomSignatureFileParser.parse(is);
    } catch (IOException ex) {
      throw new SipException("Unable to read PRONOM signature file " + path, ex);
    }
  }

  /**
   * Valide le référentiel des formats d'un fichier de signatures PRONOM.
   *
   * @param path le path du fichier de signatures
   * @throws SipException si le fichier ne peut pas être lu ou n'est pas un fichier de signatures
   *     valide
   */
  public void validate(Path path) {
    parse(path);
  }

  /**
   * Retourne le référentiel des formats par défaut, lu depuis le fichier de signatures DROID
   * embarqué dans sipg. Le référentiel est lu à la première demande puis conservé en mémoire.
   *
   * @return le référentiel des formats par défaut
   */
  public PronomSignatureFile getDefault() {
    return DefaultSignatureFileHolder.INSTANCE;
  }

  /**
   * Indique si la valeur est un PUID valide. Un PUID PRONOM ({@code fmt/14}, {@code x-fmt/111}) ou
   * la clé d'un format client ({@code EXTERNAL_MYFORMAT}) est composé de segments séparés par un
   * seul {@code /}. Chaque segment commence par une lettre ou un chiffre et ne contient que des
   * lettres, des chiffres, {@code .}, {@code _} ou {@code -}.
   *
   * @param value la valeur à vérifier
   * @return true si la valeur est un PUID valide, false sinon ou si la valeur est null
   */
  public static boolean isValidPuid(@Nullable String value) {
    if (value == null) {
      return false;
    }
    // Checked segment by segment rather than with one pattern: a nested repetition ((/segment)*)
    // makes the regex engine recurse once per segment and overflow the stack on a large input.
    // Limit -1 keeps a trailing empty segment ("fmt/"), rejected like a leading one ("/fmt").
    for (String segment : value.split("/", -1)) {
      if (!PUID_SEGMENT.matcher(segment).matches()) {
        return false;
      }
    }
    return true;
  }

  /** Allow lazy loading of the default signature file */
  private static class DefaultSignatureFileHolder {

    // Parsed like a client file rather than built from the DROID model of DroidUtils: reading the
    // file formats does not require compiling the DROID signatures, and both referentials follow
    // the same validation rules

    private static final PronomSignatureFile INSTANCE = load();

    private static PronomSignatureFile load() {
      try (InputStream is = SipUtils.resourceAsStream(DroidUtils.DEFAULT_SIGNATURE_FILE)) {
        if (is == null) {
          throw new SipException(
              "Unable to find the default signature file " + DroidUtils.DEFAULT_SIGNATURE_FILE);
        }
        return PronomSignatureFileParser.parse(is);
      } catch (IOException ex) {
        throw new SipException("Unable to load the default signature file", ex);
      }
    }
  }
}
