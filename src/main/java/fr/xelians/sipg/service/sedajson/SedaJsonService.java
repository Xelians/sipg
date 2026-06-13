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

import static fr.xelians.sipg.service.common.ProgressState.FAIL;
import static fr.xelians.sipg.service.common.ProgressState.SUCCESS;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.xelians.sipg.model.ArchiveTransfer;
import fr.xelians.sipg.service.common.ProgressEvent;
import fr.xelians.sipg.service.common.ProgressListener;
import fr.xelians.sipg.service.common.ProgressState;
import fr.xelians.sipg.utils.ByteArrayInOutStream;
import fr.xelians.sipg.utils.SipException;
import fr.xelians.sipg.utils.SipUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.Validate;

/**
 * La classe SedaJsonService offre des API pour valider et sérialiser une archive dans un paquet zip
 * selon le standard SEDA JSON. Le paquet zip contient le manifeste {@code manifest.json} à la
 * racine et les objets binaires dans le dossier {@code content/}. Le processus de sérialisation
 * peut être contrôlé par configuration. Note. La classe SedaJsonService est thread-safe.
 *
 * @author Emmanuel Deviller
 * @see ArchiveTransfer
 * @see SedaJsonConfig
 */
public class SedaJsonService {

  private static final SedaJsonService SEDA_JSON_V10 = new SedaJsonService(SedaJsonVersion.V1_0);

  private static final JsonMapper MAPPER = JsonMapper.builder().build();

  private final SedaJsonVersion version;

  private SedaJsonService(SedaJsonVersion version) {
    this.version = version;
  }

  /**
   * Retourne l'instance singleton de la classe SedaJsonService v1.0.
   *
   * @return l 'instance singleton
   */
  public static SedaJsonService getInstance() {
    return SEDA_JSON_V10;
  }

  /**
   * Sérialise l'archive dans un paquet zip au standard SEDA JSON.
   *
   * @param archive l'archive à sérialiser
   * @param zipPath le path du paquet zip
   */
  public void write(ArchiveTransfer archive, Path zipPath) {
    write(archive, zipPath, SedaJsonConfig.DEFAULT);
  }

  /**
   * Sérialise l'archive dans un paquet zip au standard SEDA JSON. La configuration permet de
   * contrôler le processus de conversion et de sérialisation.
   *
   * @param archive l'archive à sérialiser
   * @param zipPath le path du paquet zip
   * @param config la configuration utilisée lors du processus de sérialisation
   */
  public void write(ArchiveTransfer archive, Path zipPath, SedaJsonConfig config) {
    Validate.notNull(archive, SipUtils.NOT_NULL, "archive");
    Validate.notNull(zipPath, SipUtils.NOT_NULL, "zipPath");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    try {
      Files.deleteIfExists(zipPath);
    } catch (IOException ex) {
      throw new SipException("Unable to delete file " + zipPath, ex);
    }

    try (FileSystem zipArchive = SipUtils.newZipFileSystem(zipPath, config.useMemory())) {
      ObjectNode manifest = SedaJsonConverter.convert(archive, zipArchive, config);
      if (config.validate()) {
        SedaJsonSchemaValidator.getInstance(version).validate(manifest);
      }
      try (OutputStream os =
          Files.newOutputStream(zipArchive.getPath(SedaJsonKeys.MANIFEST_JSON))) {
        getWriter(config).writeValue(os, manifest);
      }
    } catch (IOException ex) {
      throw new SipException("Unable to write archive to " + zipPath, ex);
    } catch (ExecutionException ex) {
      throw new SipException("Unable to write archive to " + zipPath, ex);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
      throw new SipException("Unable to write archive to " + zipPath, ex);
    }
  }

  /**
   * Valide le manifeste de l'archive selon le schéma JSON défini par le standard SEDA JSON.
   *
   * @param archive l'archive à valider
   */
  public void validate(ArchiveTransfer archive) {
    validate(archive, SedaJsonConfig.DEFAULT);
  }

  /**
   * Valide le manifeste de l'archive selon le schéma JSON défini par le standard SEDA JSON. La
   * configuration permet de contrôler le processus de validation. Les empreintes et tailles des
   * objets binaires sont calculées mais aucun objet binaire n'est copié.
   *
   * @param archive l'archive à valider
   * @param config la configuration utilisée lors du processus de validation
   */
  public void validate(ArchiveTransfer archive, SedaJsonConfig config) {
    Validate.notNull(archive, SipUtils.NOT_NULL, "archive");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    try {
      ObjectNode manifest = SedaJsonConverter.convert(archive, config);
      SedaJsonSchemaValidator.getInstance(version).validate(manifest);
    } catch (ExecutionException ex) {
      throw new SipException("Unable to validate archive", ex);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
      throw new SipException("Unable to validate archive", ex);
    }
  }

  /**
   * Valide le fichier manifeste JSON ou l'archive zip selon le standard SEDA JSON.
   *
   * @param path le path du fichier JSON ou du paquet zip à valider
   */
  public void validate(Path path) {
    validate(path, SedaJsonConfig.DEFAULT, null);
  }

  /**
   * Valide le fichier manifeste JSON ou l'archive zip selon le standard SEDA JSON.
   *
   * @param path le path du fichier JSON ou du paquet zip à valider
   * @param config la configuration utilisée lors du processus de validation
   */
  public void validate(Path path, SedaJsonConfig config) {
    validate(path, config, null);
  }

  /**
   * Valide le fichier manifeste JSON ou l'archive zip selon le standard SEDA JSON. La validation
   * vérifie la taille maximale du manifeste, sa conformité au schéma JSON embarqué du standard,
   * l'ordre des clés imposé par le standard puis l'existence, la taille et l'empreinte des objets
   * binaires du paquet zip.
   *
   * @param path le path du fichier JSON ou du paquet zip à valider
   * @param config la configuration utilisée lors du processus de validation
   * @param listener la méthode de callback pour suivre la progression de la validation
   */
  public void validate(Path path, SedaJsonConfig config, ProgressListener<SedaJsonStep> listener) {
    Validate.notNull(path, SipUtils.NOT_NULL, "path");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    String name = path.toString().toLowerCase();
    if (name.endsWith(".json")) {
      validateManifest(path, config);
    } else {
      validateZip(path, config, listener);
    }
  }

  /**
   * Sérialise le manifeste de l'archive au standard SEDA JSON. Les empreintes et tailles des objets
   * binaires sont calculées mais aucun objet binaire n'est copié.
   *
   * @param archive l'archive à sérialiser
   * @return le flux du manifeste SEDA JSON
   */
  public InputStream marshal(ArchiveTransfer archive) {
    return marshal(archive, SedaJsonConfig.DEFAULT);
  }

  /**
   * Sérialise le manifeste de l'archive au standard SEDA JSON. Les empreintes et tailles des objets
   * binaires sont calculées mais aucun objet binaire n'est copié.
   *
   * @param archive l'archive à sérialiser
   * @param config la configuration utilisée lors du processus de sérialisation
   * @return le flux du manifeste SEDA JSON
   */
  public InputStream marshal(ArchiveTransfer archive, SedaJsonConfig config) {
    Validate.notNull(archive, SipUtils.NOT_NULL, "archive");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    try {
      ObjectNode manifest = SedaJsonConverter.convert(archive, config);
      if (config.validate()) {
        SedaJsonSchemaValidator.getInstance(version).validate(manifest);
      }
      ByteArrayInOutStream baios = new ByteArrayInOutStream(1024);
      getWriter(config).writeValue(baios, manifest);
      return baios.getInputStream();
    } catch (IOException ex) {
      throw new SipException("Unable to marshal archive", ex);
    } catch (ExecutionException ex) {
      throw new SipException("Unable to marshal archive", ex);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
      throw new SipException("Unable to marshal archive", ex);
    }
  }

  private ObjectWriter getWriter(SedaJsonConfig config) {
    if (!config.format()) {
      return MAPPER.writer();
    }
    DefaultIndenter indenter = new DefaultIndenter(" ".repeat(Math.abs(config.indent())), "\n");
    DefaultPrettyPrinter printer =
        new DefaultPrettyPrinter().withObjectIndenter(indenter).withArrayIndenter(indenter);
    return MAPPER.writer(printer);
  }

  private void validateManifest(Path manifestPath, SedaJsonConfig config) {
    try {
      long size = Files.size(manifestPath);
      if (size > config.maxManifestSize()) {
        throw new SipException(
            String.format(
                "Manifest size '%d' exceeds the maximum allowed size '%d'",
                size, config.maxManifestSize()));
      }

      try (InputStream is = Files.newInputStream(manifestPath)) {
        SedaJsonSchemaValidator.getInstance(version).validate(is);
      }
      try (InputStream is = Files.newInputStream(manifestPath)) {
        SedaJsonParser.parse(is);
      }
    } catch (IOException ex) {
      throw new SipException("Unable to validate " + manifestPath, ex);
    }
  }

  private void validateZip(
      Path zipPath, SedaJsonConfig config, ProgressListener<SedaJsonStep> listener) {

    String id = FilenameUtils.removeExtension(zipPath.getFileName().toString());
    updateListener(listener, id, SUCCESS, SedaJsonStep.START, "Archive: " + zipPath);

    // Check zip exists
    if (Files.notExists(zipPath)) {
      String msg = "Archive does not exist: " + zipPath;
      updateListener(listener, id, FAIL, SedaJsonStep.ARCHIVE_EXIST, msg);
      throw new SipException(msg);
    }
    updateListener(listener, id, SUCCESS, SedaJsonStep.ARCHIVE_EXIST, "Archive exists");

    // Check zip is valid
    if (!Files.isReadable(zipPath) || Files.isDirectory(zipPath)) {
      String msg = "Archive is not a readable zip: " + zipPath;
      updateListener(listener, id, FAIL, SedaJsonStep.ARCHIVE_READABLE, msg);
      throw new SipException(msg);
    }
    updateListener(listener, id, SUCCESS, SedaJsonStep.ARCHIVE_READABLE, "Archive is readable");

    // Check zip is really a zip
    URI zipUri = SipUtils.createZipURI(zipPath);
    try (FileSystem zipArchive = FileSystems.newFileSystem(zipUri, Collections.emptyMap())) {
      updateListener(listener, id, SUCCESS, SedaJsonStep.ARCHIVE_UNZIP, "Archive is opened");

      Path manifestPath = zipArchive.getPath(SedaJsonKeys.MANIFEST_JSON);
      if (Files.notExists(manifestPath) || Files.isDirectory(manifestPath)) {
        String msg = "Archive does not contain a manifest: " + zipPath;
        updateListener(listener, id, FAIL, SedaJsonStep.MANIFEST_EXIST, msg);
        throw new SipException(msg);
      }
      updateListener(listener, id, SUCCESS, SedaJsonStep.MANIFEST_EXIST, "Manifest exists");

      // Check manifest size. The JSON schema validation materializes the manifest in memory
      long size = Files.size(manifestPath);
      if (size > config.maxManifestSize()) {
        String msg =
            String.format(
                "Manifest size '%d' exceeds the maximum allowed size '%d': %s",
                size, config.maxManifestSize(), zipPath);
        updateListener(listener, id, FAIL, SedaJsonStep.MANIFEST_SIZE, msg);
        throw new SipException(msg);
      }
      updateListener(listener, id, SUCCESS, SedaJsonStep.MANIFEST_SIZE, "Manifest size is valid");

      ByteArrayInOutStream manifest = new ByteArrayInOutStream(1024);
      Files.copy(manifestPath, manifest);

      // Check manifest is valid against the embedded JSON schema
      try {
        SedaJsonSchemaValidator.getInstance(version).validate(manifest.getInputStream());
      } catch (SipException ex) {
        String msg = "Unable to validate manifest: " + zipPath;
        updateListener(listener, id, FAIL, SedaJsonStep.MANIFEST_SCHEMA, msg);
        throw ex;
      }
      updateListener(
          listener, id, SUCCESS, SedaJsonStep.MANIFEST_SCHEMA, "Manifest conforms to SEDA JSON");

      // Parse the manifest to check the key order and look for binary path
      if (config.checkBinary()) {
        ArrayList<SedaJsonBinaryObject> binaryObjects;
        try {
          binaryObjects = SedaJsonParser.parse(manifest.getInputStream());
        } catch (IOException ex) {
          String msg = "Unable to parse manifest: " + zipPath;
          updateListener(listener, id, FAIL, SedaJsonStep.MANIFEST_PARSE, msg);
          throw new SipException(msg, ex);
        } catch (SipException ex) {
          updateListener(listener, id, FAIL, SedaJsonStep.MANIFEST_PARSE, ex.getMessage());
          throw ex;
        }
        updateListener(listener, id, SUCCESS, SedaJsonStep.MANIFEST_PARSE, "Manifest is parsed");

        for (SedaJsonBinaryObject binaryObject : binaryObjects) {

          // Check binaryPath exists
          Path binaryPath = zipArchive.getPath(binaryObject.uri());
          if (Files.notExists(binaryPath) || Files.isDirectory(binaryPath)) {
            String msg = "Binary object does not exist: " + zipPath + "!" + binaryPath;
            updateListener(listener, id, FAIL, SedaJsonStep.BINARY_EXIST, msg);
            throw new SipException(msg);
          }
          updateListener(
              listener,
              id,
              SUCCESS,
              SedaJsonStep.BINARY_EXIST,
              "Binary object exists: " + binaryPath);

          // Check binaryPath Directory is "content"
          Path parentPath = binaryPath.getParent();
          if (parentPath == null
              || !SedaJsonKeys.CONTENT_DIR.equals(parentPath.getFileName().toString())) {
            String msg = "Binary object folder is not valid: " + zipPath + "!" + binaryPath;
            updateListener(listener, id, FAIL, SedaJsonStep.BINARY_FOLDER, msg);
            throw new SipException(msg);
          }
          updateListener(
              listener, id, SUCCESS, SedaJsonStep.BINARY_FOLDER, "Binary object folder is valid");

          // Check binaryPath size
          if (config.checkSize()) {
            if (Files.size(binaryPath) != binaryObject.size()) {
              String msg = "Binary object size is not valid: " + zipPath + "!" + binaryPath;
              updateListener(listener, id, FAIL, SedaJsonStep.BINARY_SIZE, msg);
              throw new SipException(msg);
            }
            updateListener(
                listener, id, SUCCESS, SedaJsonStep.BINARY_SIZE, "Binary object size is valid");
          }

          // Check binaryPath hash equals the given hash
          if (config.checkDigest()) {
            String digest = SipUtils.digestHex(binaryPath, binaryObject.algorithm());
            if (!digest.equals(binaryObject.digest())) {
              String msg = "Binary object digest is not valid: " + zipPath + "!" + binaryPath;
              updateListener(listener, id, FAIL, SedaJsonStep.BINARY_DIGEST, msg);
              throw new SipException(msg);
            }
            updateListener(
                listener, id, SUCCESS, SedaJsonStep.BINARY_DIGEST, "Binary object digest is valid");
          }
        }
      }
    } catch (IOException ex) {
      String msg = "Unable to open: " + zipPath;
      updateListener(listener, id, FAIL, SedaJsonStep.ARCHIVE_UNZIP, msg);
      throw new SipException(msg, ex);
    }

    updateListener(listener, id, SUCCESS, SedaJsonStep.COMPLETE, "Archive is valid");
  }

  private void updateListener(
      ProgressListener<SedaJsonStep> listener,
      String id,
      ProgressState status,
      SedaJsonStep step,
      String message) {
    if (listener != null) {
      listener.progressChanged(new ProgressEvent<>(id, status, step, message));
    }
  }
}
