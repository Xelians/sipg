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
package fr.xelians.sipg.service.sedav2;

import static fr.xelians.sipg.service.common.ProgressState.FAIL;
import static fr.xelians.sipg.service.common.ProgressState.SUCCESS;

import fr.xelians.sipg.model.ArchiveDeliveryRequestReply;
import fr.xelians.sipg.model.ArchiveTransfer;
import fr.xelians.sipg.service.common.ProgressEvent;
import fr.xelians.sipg.service.common.ProgressListener;
import fr.xelians.sipg.service.common.ProgressState;
import fr.xelians.sipg.service.sedav2.seda21.Sedav21Adapter;
import fr.xelians.sipg.service.sedav2.seda22.Sedav22Adapter;
import fr.xelians.sipg.service.sedav2.seda23.Sedav23Adapter;
import fr.xelians.sipg.utils.ByteArrayInOutStream;
import fr.xelians.sipg.utils.SipException;
import fr.xelians.sipg.utils.SipUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.Validate;
import org.xml.sax.SAXException;

/**
 * La classe Sedav2Service offre des API pour valider et sérialiser une archive dans un paquet zip
 * selon le standard SEDA v2.1. Le processus de sérialisation peut être contrôlé par configuration.
 * Il est possible d'opérer une validation supplémentaire du fichier de description de l'archive
 * selon un schéma RNG. Note. La classe Sedav2Service est thread-safe.
 *
 * @author Emmanuel Deviller
 * @see ArchiveTransfer
 * @see SedaConfig
 */
public class Sedav2Service {

  private static final Sedav2Service SEDA_V21 = new Sedav2Service(SedaVersion.V2_1);
  private static final Sedav2Service SEDA_V22 = new Sedav2Service(SedaVersion.V2_2);
  private static final Sedav2Service SEDA_V23 = new Sedav2Service(SedaVersion.V2_3);

  private final SedaAdapter sedaAdapter;

  private Sedav2Service(SedaVersion version) {
    this.sedaAdapter =
        switch (version) {
          case V2_1 -> Sedav21Adapter.INSTANCE;
          case V2_2 -> Sedav22Adapter.INSTANCE;
          case V2_3 -> Sedav23Adapter.INSTANCE;
        };
  }

  /**
   * Retourne l'instance singleton de la classe Sedav2Service v2.1.
   *
   * @return l 'instance singleton
   */
  public static Sedav2Service getInstance() {
    return SEDA_V21;
  }

  /**
   * Retourne l'instance singleton de la classe Sedav2Service v2.2.
   *
   * @return l 'instance singleton
   */
  public static Sedav2Service getV22Instance() {
    return SEDA_V22;
  }

  /**
   * Retourne l'instance singleton de la classe Sedav2Service v2.3.
   *
   * @return l 'instance singleton
   */
  public static Sedav2Service getV23Instance() {
    return SEDA_V23;
  }

  /**
   * Sérialise l'archive dans un paquet zip au format SEDA v2.1.
   *
   * @param archive l'archive à sérialiser
   * @param zipPath le path du paquet zip
   */
  public void write(ArchiveTransfer archive, Path zipPath) {
    write(archive, zipPath, null, SedaConfig.DEFAULT);
  }

  /**
   * Sérialise l'archive dans un paquet zip au format SEDA v2.1. Le fichier XML de description de
   * l'archive peut être validé selon le schéma RNG défini par le Validator.
   *
   * <p>Note. L'objet Validator n'est pas thread safe, il est de la responsabilité de l'application
   * appelante de s'assurer que l'objet {@link Validator} n'est utilisé à tout moment que par une
   * seule et même thread.
   *
   * @param archive l'archive à sérialiser
   * @param zipPath le path du paquet zip
   * @param validator le validateur RNG
   */
  public void write(ArchiveTransfer archive, Path zipPath, Validator validator) {
    write(archive, zipPath, validator, SedaConfig.DEFAULT);
  }

  /**
   * Sérialise l'archive dans un paquet zip au format SEDA v2.1. La configuration permet de
   * contrôler le processus de sérialisation.
   *
   * @param archive l'archive à sérialiser
   * @param zipPath le path du paquet zip
   * @param config la configuration utilisée lors de la sérialisation
   */
  public void write(ArchiveTransfer archive, Path zipPath, SedaConfig config) {
    write(archive, zipPath, null, config);
  }

  /**
   * Sérialise l'archive dans un paquet zip au format SEDA v2.1. Le fichier XML de description de
   * l'archive peut être validé selon le schéma RNG défini par le Validator. La configuration permet
   * de contrôler le processus de conversion et de sérialisation.
   *
   * <p>Note. L'objet Validator n'est pas thread safe, il est de la responsabilité de l'application
   * appelante de s'assurer que l'objet {@link Validator} n'est utilisé à tout moment que par une
   * seule et même thread.
   *
   * @param archive l'archive à sérialiser
   * @param zipPath le path du paquet zip
   * @param validator le validateur RNG
   * @param config la configuration utilisée lors du processus de sérialisation
   */
  public void write(ArchiveTransfer archive, Path zipPath, Validator validator, SedaConfig config) {
    Validate.notNull(archive, SipUtils.NOT_NULL, "archiveTransfer");
    Validate.notNull(zipPath, SipUtils.NOT_NULL, "zipPath");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    try {
      Files.deleteIfExists(zipPath);
    } catch (IOException ex) {
      throw new SipException("Unable to delete file " + zipPath, ex);
    }

    sedaAdapter.write(archive, validator, zipPath, config);
  }

  /**
   * Sérialise la réponse dans un paquet zip au format SEDA v2.1.
   *
   * @param archive l'archive à sérialiser
   * @param zipPath le path du paquet zip
   */
  public void write(ArchiveDeliveryRequestReply archive, Path zipPath) {
    write(archive, zipPath, null, SedaConfig.DEFAULT);
  }

  /**
   * Sérialise la réponse dans un paquet zip au format SEDA v2.1. Le fichier XML de description de
   * l'archive peut être validé selon le schéma RNG défini par le Validator.
   *
   * <p>Note. L'objet Validator n'est pas thread-safe, il est de la responsabilité de l'application
   * appelante de s'assurer que l'objet {@link Validator} n'est utilisé à tout moment que par une
   * seule et même thread.
   *
   * @param archive l'archive à sérialiser
   * @param zipPath le path du paquet zip
   * @param validator le validateur RNG
   */
  public void write(ArchiveDeliveryRequestReply archive, Path zipPath, Validator validator) {
    write(archive, zipPath, validator, SedaConfig.DEFAULT);
  }

  /**
   * Sérialise la réponse dans un paquet zip au format SEDA v2.1. La configuration permet de
   * contrôler le processus de sérialisation.
   *
   * @param archive l'archive à sérialiser
   * @param zipPath le path du paquet zip
   * @param config la configuration utilisée lors de la sérialisation
   */
  public void write(ArchiveDeliveryRequestReply archive, Path zipPath, SedaConfig config) {
    write(archive, zipPath, null, config);
  }

  /**
   * Sérialise la réponse dans un paquet zip au format SEDA v2.1. Le fichier XML de description de
   * l'archive peut être validé selon le schéma RNG défini par le Validator. La configuration permet
   * de contrôler le processus de conversion et de sérialisation.
   *
   * <p>Note. L'objet Validator n'est pas thread-safe, il est de la responsabilité de l'application
   * appelante de s'assurer que l'objet {@link Validator} n'est utilisé à tout moment que par une
   * seule et même thread.
   *
   * @param archive l'archive à sérialiser
   * @param zipPath le path du paquet zip
   * @param validator le validateur RNG
   * @param config la configuration utilisée lors du processus de sérialisation
   */
  public void write(
      ArchiveDeliveryRequestReply archive, Path zipPath, Validator validator, SedaConfig config) {
    Validate.notNull(archive, SipUtils.NOT_NULL, "archiveTransfer");
    Validate.notNull(zipPath, SipUtils.NOT_NULL, "zipPath");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    try {
      Files.deleteIfExists(zipPath);
    } catch (IOException ex) {
      throw new SipException("Unable to delete file " + zipPath, ex);
    }

    sedaAdapter.write(archive, validator, zipPath, config);
  }

  /**
   * Valide le XML de description de l'archive selon le schéma défini par le standard SEDA v2.1.
   *
   * @param archive l'archive à valider
   */
  public void validate(ArchiveTransfer archive) {
    validate(archive, null, SedaConfig.DEFAULT);
  }

  /**
   * Valide le XML de description de l'archive selon le schéma défini par le standard SEDA v2.1. La
   * configuration permet de contrôler le processus de validation.
   *
   * @param archive l'archive à valider
   * @param config la configuration utilisée lors du processus de validation
   */
  public void validate(ArchiveTransfer archive, SedaConfig config) {
    validate(archive, null, config);
  }

  /**
   * Valide le XML de description de l'archive selon le schéma défini par le standard SEDA v2.1. Le
   * XML de description de l'archive peut être validé selon le schéma RNG défini par le Validator.
   *
   * <p>Note. L'objet Validator n'est pas thread-safe, il est de la responsabilité de l'application
   * appelante de s'assurer que l'objet {@link Validator} n'est utilisé à tout moment que par une
   * seule et même thread.
   *
   * @param archive l'archive à valider
   * @param validator le validateur RNG
   */
  public void validate(ArchiveTransfer archive, Validator validator) {
    validate(archive, validator, SedaConfig.DEFAULT);
  }

  /**
   * Valide le XML de description de l'archive selon le schéma défini par le standard SEDA v2.1. Le
   * XML de description de l'archive peut être validé selon le schéma RNG défini par le Validator.
   * La configuration permet de contrôler le processus de validation.
   *
   * <p>Note. L'objet Validator n'est pas thread-safe, il est de la responsabilité de l'application
   * appelante de s'assurer que l'objet {@link Validator} n'est utilisé à tout moment que par une
   * seule et même thread.
   *
   * @param archive l'archive à valider
   * @param validator le validateur RNG
   * @param config la configuration utilisée lors du processus de validation
   */
  public void validate(ArchiveTransfer archive, Validator validator, SedaConfig config) {
    Validate.notNull(archive, SipUtils.NOT_NULL, "archive");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    sedaAdapter.validate(archive, validator, config);
  }

  /**
   * Valide le fichier XML ou l'archive selon le schéma défini par le standard SEDA v2.1.
   *
   * @param path le path du fichier XML à valider
   */
  public void validate(Path path) {
    Validate.notNull(path, SipUtils.NOT_NULL, "path");
    validate(path, null, SedaConfig.DEFAULT, null);
  }

  /**
   * Valide le fichier XML ou l'archive selon le schéma défini par le standard SEDA v2.1. L'objet
   * Validator n'est pas thread-safe, il est de la responsabilité de l'application appelante de
   * s'assurer que l'objet {@link Validator} n'est utilisé à tout moment que par une seule et même
   * thread.
   *
   * @param path le path du fichier XML à valider
   * @param validator le validateur RNG
   */
  public void validate(Path path, Validator validator) {
    Validate.notNull(path, SipUtils.NOT_NULL, "path");

    validate(path, validator, SedaConfig.DEFAULT, null);
  }

  /**
   * Valide le fichier XML ou l'archive selon le schéma défini par le standard SEDA v2.1.
   *
   * @param path le path du fichier XML à valider
   * @param config la configuration utilisée lors du processus de validation
   */
  public void validate(Path path, SedaConfig config) {
    Validate.notNull(path, SipUtils.NOT_NULL, "path");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    validate(path, null, config, null);
  }

  /**
   * Valide le fichier XML ou l'archive selon le schéma défini par le standard SEDA v2.1. L'objet
   * Validator n'est pas thread safe, il est de la responsabilité de l'application appelante de
   * s'assurer que l'objet {@link Validator} n'est utilisé à tout moment que par une seule et même
   * thread.
   *
   * @param path le path du fichier XML à valider
   * @param validator le validateur RNG
   * @param config the config
   */
  public void validate(Path path, Validator validator, SedaConfig config) {
    Validate.notNull(path, SipUtils.NOT_NULL, "path");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    validate(path, validator, config, null);
  }

  /**
   * Valide le fichier XML ou l'archive selon le schéma défini par le standard SEDA v2.1. Le {@link
   * Validator} permet d'appliquer une validation supplémentaire. Note. L'objet Validator n'est pas
   * thread-safe, il est de la responsabilité de l'application appelante de s'assurer que l'objet
   * {@link Validator} n'est utilisé à tout moment que par une seule et même thread.
   *
   * @param path le path du fichier XML à valider
   * @param validator le validateur RNG
   * @param config la configuration utilisée lors du processus de validation
   * @param listener la méthode de callback pour suivre la progression de la validation
   */
  public void validate(
      Path path, Validator validator, SedaConfig config, ProgressListener<SedaStep> listener) {
    Validate.notNull(path, SipUtils.NOT_NULL, "path");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    String name = path.toString().toLowerCase();
    if (name.endsWith(".xml")) {
      validateXml(path, validator, config);
    } else {
      validateZip(path, validator, config, listener);
    }
  }

  private void validateXml(Path xmlPath, Validator validator, SedaConfig config) {
    Validate.notNull(xmlPath, SipUtils.NOT_NULL, "path");

    try (InputStream is = Files.newInputStream(xmlPath)) {
      this.validate(new StreamSource(is), config);
    } catch (IOException ex) {
      throw new SipException("Unable to validate " + xmlPath, ex);
    }

    // Check manifest is valid against rng
    if (validator != null) {
      try (InputStream is = Files.newInputStream(xmlPath)) {
        validator.validate(new StreamSource(is));
      } catch (IOException | SAXException ex) {
        throw new SipException("Unable to validate " + xmlPath, ex);
      }
    }
  }

  /**
   * Valide la source XML source selon le schéma défini par le standard SEDA v2.1.
   *
   * @param source la source XML à valider
   */
  private void validate(Source source, SedaConfig config) {
    Validate.notNull(source, SipUtils.NOT_NULL, "source");
    sedaAdapter.validate(source, config);
  }

  private void validateZip(
      Path zipPath, Validator validator, SedaConfig config, ProgressListener<SedaStep> listener) {

    String id = FilenameUtils.removeExtension(zipPath.getFileName().toString());
    updateListener(listener, id, SUCCESS, SedaStep.START, "Archive: " + zipPath);

    // Check zip exists
    if (Files.notExists(zipPath)) {
      String msg = "Archive does not exist: " + zipPath;
      updateListener(listener, id, FAIL, SedaStep.ARCHIVE_EXIST, msg);
      throw new SipException(msg);
    }
    updateListener(listener, id, SUCCESS, SedaStep.ARCHIVE_EXIST, "Archive exists");

    // Check zip is valid
    if (!Files.isReadable(zipPath) || Files.isDirectory(zipPath)) {
      String msg = "Archive is not a readable zip: " + zipPath;
      updateListener(listener, id, FAIL, SedaStep.ARCHIVE_READABLE, msg);
      throw new SipException(msg);
    }
    updateListener(listener, id, SUCCESS, SedaStep.ARCHIVE_READABLE, "Archive is readable");

    // Check zip is really a zip
    URI zipUri = SipUtils.createZipURI(zipPath);
    try (FileSystem zipArchive = FileSystems.newFileSystem(zipUri, Collections.emptyMap())) {
      updateListener(listener, id, SUCCESS, SedaStep.ARCHIVE_UNZIP, "Archive is opened");

      Path manifestPath = zipArchive.getPath("manifest.xml");
      if (Files.notExists(manifestPath) || Files.isDirectory(manifestPath)) {
        String msg = "Archive does not contain a manifest: " + zipPath;
        updateListener(listener, id, FAIL, SedaStep.MANIFEST_EXIST, msg);
        throw new SipException(msg);
      }
      updateListener(listener, id, SUCCESS, SedaStep.MANIFEST_EXIST, "Manifest exists");

      ByteArrayInOutStream manifest = new ByteArrayInOutStream(1024);

      // Check manifest is valid against xsd
      Files.copy(manifestPath, manifest);

      try {
        validate(new StreamSource(manifest.getInputStream()), config);
      } catch (SipException ex) {
        String msg = "Unable to validate manifest: " + zipPath;
        updateListener(listener, id, FAIL, SedaStep.MANIFEST_SEDA, msg);
        throw ex;
      }

      updateListener(listener, id, SUCCESS, SedaStep.MANIFEST_SEDA, "Manifest conforms to SEDA");

      // Check manifest is valid against rng
      if (validator != null) {
        try {
          validator.validate(new StreamSource(manifest.getInputStream()));
        } catch (IOException | SAXException ex) {
          String msg = "Unable to validate manifest: " + zipPath;
          updateListener(listener, id, FAIL, SedaStep.MANIFEST_VALIDATOR, msg);
          throw new SipException(msg, ex);
        }
        updateListener(
            listener, id, SUCCESS, SedaStep.MANIFEST_VALIDATOR, "Manifest conforms to validator");
      }

      // Parse the manifest to look for binary path
      if (config.checkBinary()) {
        ArrayList<SedaBinaryObject> binaryObjects;
        try {
          binaryObjects = SedaParser.parse(manifest.getInputStream());
        } catch (IOException | ParserConfigurationException | SAXException ex) {
          String msg = "Unable to parse manifest: " + zipPath;
          updateListener(listener, id, FAIL, SedaStep.MANIFEST_PARSE, msg);
          throw new SipException(msg, ex);
        }
        updateListener(listener, id, SUCCESS, SedaStep.MANIFEST_PARSE, "Manifest is parsed");

        for (SedaBinaryObject binaryObject : binaryObjects) {

          // Check binaryPath exists
          Path binaryPath = zipArchive.getPath(binaryObject.getUri());
          if (Files.notExists(binaryPath) || Files.isDirectory(binaryPath)) {
            String msg = "Binary object does not exist: " + zipPath + "!" + binaryPath;
            updateListener(listener, id, FAIL, SedaStep.BINARY_EXIST, msg);
            throw new SipException(msg);
          }
          updateListener(
              listener, id, SUCCESS, SedaStep.BINARY_EXIST, "Binary object exists: " + binaryPath);

          // Check binaryPath Directory is "Content"
          Path parentPath = binaryPath.getParent();
          if (!"Content".equals(parentPath.getFileName().toString())) {
            String msg = "Binary object folder is not valid: " + zipPath + "!" + binaryPath;
            updateListener(listener, id, FAIL, SedaStep.BINARY_FOLDER, msg);
            throw new SipException(msg);
          }
          updateListener(
              listener, id, SUCCESS, SedaStep.BINARY_FOLDER, "Binary object folder is valid");

          // Check binaryPath size
          if (config.checkSize()) {
            if (Files.size(binaryPath) != binaryObject.getSize()) {
              String msg = "Binary object size is not valid: " + zipPath + "!" + binaryPath;
              updateListener(listener, id, FAIL, SedaStep.BINARY_SIZE, msg);
              throw new SipException(msg);
            }
            updateListener(
                listener, id, SUCCESS, SedaStep.BINARY_SIZE, "Binary object size is valid");
          }

          // Check binaryPath hash equals the given hash
          if (config.checkDigest()) {
            String digest = SipUtils.digestHex(binaryPath, binaryObject.getAlgorithm());
            if (!digest.equals(binaryObject.getDigest())) {
              String msg =
                  binaryPath + "Binary object  digest is not valid: " + zipPath + "!" + binaryPath;
              updateListener(listener, id, FAIL, SedaStep.BINARY_DIGEST, msg);
              throw new SipException(msg);
            }
            updateListener(
                listener, id, SUCCESS, SedaStep.BINARY_DIGEST, "Binary object digest is valid");
          }

          // Check binaryPath Format. The Droid library does not support NIO FileSystem.
        }
      }
    } catch (IOException ex) {
      String msg = "Unable to open: " + zipPath;
      updateListener(listener, id, FAIL, SedaStep.ARCHIVE_UNZIP, msg);
      throw new SipException(msg, ex);
    }

    updateListener(listener, id, SUCCESS, SedaStep.COMPLETE, "Archive is valid");
  }

  private void updateListener(
      ProgressListener<SedaStep> listener,
      String id,
      ProgressState status,
      SedaStep step,
      String message) {
    if (listener != null) {
      listener.progressChanged(new ProgressEvent<>(id, status, step, message));
    }
  }
}
