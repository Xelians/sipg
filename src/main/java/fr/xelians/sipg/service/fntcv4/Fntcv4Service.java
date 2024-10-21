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

import fr.xelians.sipg.model.ArchiveTransfer;
import fr.xelians.sipg.service.common.ProgressEvent;
import fr.xelians.sipg.service.common.ProgressListener;
import fr.xelians.sipg.service.common.ProgressState;
import fr.xelians.sipg.utils.ByteArrayInOutStream;
import fr.xelians.sipg.utils.SipException;
import fr.xelians.sipg.utils.SipUtils;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.util.JAXBSource;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.afnor.medona.v1.ArchiveTransferType;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * La classe Fntcv4Service offre des API pour valider et sérialiser une archive dans un paquet zip
 * selon le standard FNTC v4. Le processus de sérialisation peut être contrôlé par configuration. Il
 * est aussi possible d'opérer une validation supplémentaire du fichier de description de l'archive
 * selon un schéma RNG. Note. La classe Fntcv4Service est thread-safe.
 *
 * @author Emmanuel Deviller
 * @see ArchiveTransfer
 * @see Fntcv4Config
 */
public class Fntcv4Service {

  public static final String HTTP_APACHE_ORG_XML_FEATURES_DISALLOW_DOCTYPE_DECL =
      "http://apache.org/xml/features/disallow-doctype-decl";

  private static final Logger LOGGER = LoggerFactory.getLogger(Fntcv4Service.class);
  private static final Fntcv4Service INSTANCE = new Fntcv4Service();

  private final JAXBContext fntcContext;
  private final Schema fntcSchema;

  private Fntcv4Service() {

    // Load provided schemas from resource
    try (InputStream is1 = SipUtils.resourceAsStream("medona-1.0.xsd");
        InputStream is2 = SipUtils.resourceAsStream("fntcta-4.0.xsd");
        InputStream is3 = SipUtils.resourceAsStream("xml.xsd");
        InputStream is4 = SipUtils.resourceAsStream("xlink.xsd")) {

      SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      sf.setFeature(HTTP_APACHE_ORG_XML_FEATURES_DISALLOW_DOCTYPE_DECL, true); // Avoid XXE
      sf.setResourceResolver(new Fntcv4Resolver(is2, is3, is4));
      fntcSchema = sf.newSchema(new StreamSource(is1));
      fntcContext =
          JAXBContext.newInstance(
              org.afnor.medona.v1.ObjectFactory.class, org.fntc.ta.v4.ObjectFactory.class);
    } catch (IOException | JAXBException | SAXException ex) {
      LOGGER.warn("Unable to initialize XSD Schemas, JAXBContext and Marshaller");
      throw new SipException(ex);
    }
  }

  /**
   * Retourne l'instance singleton de la classe Fntcv4Service.
   *
   * @return l 'instance singleton
   */
  public static Fntcv4Service getInstance() {
    return INSTANCE;
  }

  /**
   * Sérialise l'archive dans un paquet zip au format FNTC v4.
   *
   * @param archive l'archive à sérialiser
   * @param zipPath le path du paquet zip
   */
  public void write(ArchiveTransfer archive, Path zipPath) {
    this.write(archive, zipPath, null, Fntcv4Config.DEFAULT);
  }

  /**
   * Sérialise l'archive dans un paquet zip au format FNTC v4. Le fichier XML de description de
   * l'archive peut être validé selon le schéma RNG défini par le Validator.
   *
   * <p>Note. L'objet Validator n'est pas thread-safe, il est de la responsabilité de l'application
   * appelante de s'assurer que l'objet {@link Validator} n'est utilisé à tout moment que par une
   * seule et mếme thread.
   *
   * @param archive l'archive à sérialiser
   * @param zipPath le path du paquet zip
   * @param validator le validateur RNG
   */
  public void write(ArchiveTransfer archive, Path zipPath, Validator validator) {
    this.write(archive, zipPath, validator, Fntcv4Config.DEFAULT);
  }

  /**
   * Sérialise l'archive dans un paquet zip au format FNTC v4. La configuration permet de contrôler
   * le processus de sérialisation.
   *
   * @param archive l'archive à sérialiser
   * @param zipPath le path du paquet zip
   * @param config la configuration utilisée lors de la sérialisation
   */
  public void write(ArchiveTransfer archive, Path zipPath, Fntcv4Config config) {
    this.write(archive, zipPath, null, config);
  }

  /**
   * Sérialise l'archive dans un paquet zip au format FNTC v4. Le fichier XML de description de
   * l'archive peut être validé selon le schéma RNG défini par le Validator. La configuration permet
   * de contrôler le processus de sérialisation.
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
      ArchiveTransfer archive, Path zipPath, Validator validator, Fntcv4Config config) {
    Validate.notNull(archive, SipUtils.NOT_NULL, "archiveTransfer");
    Validate.notNull(zipPath, SipUtils.NOT_NULL, "zipPath");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    try {
      Files.deleteIfExists(zipPath);
    } catch (IOException ex) {
      LOGGER.warn("Unable to delete file {}", zipPath);
      throw new SipException(ex);
    }

    try (FileSystem zipArchive = SipUtils.newZipFileSystem(zipPath, config.useMemory())) {
      ArchiveTransferType att = Fntcv4Converter.convert(archive, zipArchive, config);
      final Path zipEntryPath = zipArchive.getPath("manifest.xml");
      try (OutputStream os = Files.newOutputStream(zipEntryPath);
          BufferedOutputStream bos = new BufferedOutputStream(os, 4096)) {

        // Set External Validator
        if (validator != null) {
          validator.validate(new JAXBSource(fntcContext, att));
        }

        Marshaller fntcMarshaller = fntcContext.createMarshaller();
        fntcMarshaller.setSchema(config.validate() ? fntcSchema : null);
        fntcMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);

        // Marshall & prettyPrint
        if (config.format()) {
          // JAXB_FORMATTED_OUTPUT is buggy and does not format XML with DOM nodes. Hence, this ugly
          // hack...
          ByteArrayInOutStream baios = new ByteArrayInOutStream(1024);
          fntcMarshaller.marshal(att, baios);
          SipUtils.formatXml(baios.getInputStream(), bos, config.indent());
        } else {
          fntcMarshaller.marshal(att, bos);
        }
      }
    } catch (IOException
        | ExecutionException
        | InterruptedException
        | JAXBException
        | SAXException ex) {
      Thread.currentThread().interrupt();
      throw new SipException("Unable to serialize archive " + zipPath, ex);
    }
  }

  /**
   * Valide le XML de description de l'archive selon le schéma défini par le standard FNTC v4.
   *
   * @param archive l'archive à valider
   */
  public void validate(ArchiveTransfer archive) {
    this.validate(archive, null, Fntcv4Config.DEFAULT, null);
  }

  /**
   * Valide le XML de description de l'archive selon le schéma défini par le standard FNTC v4. La
   * configuration permet de contrôler le processus de validation.
   *
   * @param archive l'archive à valider
   * @param config la configuration utilisée lors du processus de validation
   */
  public void validate(ArchiveTransfer archive, Fntcv4Config config) {
    this.validate(archive, null, config, null);
  }

  /**
   * Valide le XML de description de l'archive selon le schéma défini par le standard FNTC v4. Le
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
    this.validate(archive, validator, Fntcv4Config.DEFAULT, null);
  }

  /**
   * Valide le XML de description de l'archive selon le schéma défini par le standard FNTC v4. Le
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
   * @param result le résultat de la validation
   */
  public void validate(
      ArchiveTransfer archive, Validator validator, Fntcv4Config config, Result result) {
    Validate.notNull(archive, SipUtils.NOT_NULL, "archiveTransfer");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    try {
      ArchiveTransferType att = Fntcv4Converter.convert(archive, config);
      JAXBSource source = new JAXBSource(fntcContext, att);
      fntcSchema.newValidator().validate(source, result);

      if (validator != null) {
        validator.validate(source, result);
      }

    } catch (IOException
        | ExecutionException
        | InterruptedException
        | JAXBException
        | SAXException ex) {
      throw new SipException("Unable to validate archive with validator", ex);
    }
  }

  /**
   * Valide le fichier XML ou l'archive selon le schéma défini par le standard FNTC v4.
   *
   * @param path le path du fichier XML à valider
   */
  public void validate(Path path) {
    Validate.notNull(path, SipUtils.NOT_NULL, "path");
    validate(path, null, Fntcv4Config.DEFAULT, null);
  }

  /**
   * Valide le fichier XML ou l'archive selon le schéma défini par le standard FNTC v4. L'objet
   * Validator n'est pas thread-safe, il est de la responsabilité de l'application appelante de
   * s'assurer que l'objet {@link Validator} n'est utilisé à tout moment que par une seule et même
   * thread.
   *
   * @param path le path du fichier XML à valider
   * @param validator le validateur RNG
   */
  public void validate(Path path, Validator validator) {
    Validate.notNull(path, SipUtils.NOT_NULL, "path");

    validate(path, validator, Fntcv4Config.DEFAULT, null);
  }

  /**
   * Valide le fichier XML ou l'archive selon le schéma défini par le standard FNTC v4.
   *
   * @param path le path du fichier XML à valider
   * @param config la configuration utilisée lors du processus de validation
   */
  public void validate(Path path, Fntcv4Config config) {
    Validate.notNull(path, SipUtils.NOT_NULL, "path");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    validate(path, null, config, null);
  }

  /**
   * Valide le fichier XML ou l'archive selon le schéma défini par le standard FNTC v4. L'objet
   * Validator n'est pas thread-safe, il est de la responsabilité de l'application appelante de
   * s'assurer que l'objet {@link Validator} n'est utilisé à tout moment que par une seule et même
   * thread.
   *
   * @param path le path du fichier XML à valider
   * @param validator le validateur RNG
   * @param config the config
   */
  public void validate(Path path, Validator validator, Fntcv4Config config) {
    Validate.notNull(path, SipUtils.NOT_NULL, "path");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    validate(path, validator, config, null);
  }

  /**
   * Valide le fichier XML ou l'archive selon le schéma défini par le standard FNTC v4. Le {@link
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
      Path path, Validator validator, Fntcv4Config config, ProgressListener<Fntcv4Step> listener) {
    Validate.notNull(path, SipUtils.NOT_NULL, "path");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    String name = path.toString().toLowerCase();
    if (name.endsWith(".xml")) {
      validateXml(path, validator);
    } else {
      validateZip(path, validator, config, listener);
    }
  }

  private void validateXml(Path xmlPath, Validator validator) {
    Validate.notNull(xmlPath, SipUtils.NOT_NULL, "path");

    try (InputStream is = Files.newInputStream(xmlPath)) {
      this.validate(new StreamSource(is));
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
   * Valide la source XML source selon le schéma défini par le standard FNTC v4.
   *
   * @param source la source XML à valider
   */
  private void validate(Source source) {
    Validate.notNull(source, SipUtils.NOT_NULL, "source");

    try {
      Validator fntcValidator = fntcSchema.newValidator();
      fntcValidator.setFeature(HTTP_APACHE_ORG_XML_FEATURES_DISALLOW_DOCTYPE_DECL, true);
      fntcValidator.validate(source);
    } catch (IOException | SAXException ex) {
      throw new SipException("Unable to validate " + source, ex);
    }
  }

  private void validateZip(
      Path zipPath,
      Validator validator,
      Fntcv4Config config,
      ProgressListener<Fntcv4Step> listener) {

    String id = FilenameUtils.removeExtension(zipPath.getFileName().toString());
    updateListener(listener, id, ProgressState.SUCCESS, Fntcv4Step.START, "Archive: " + zipPath);

    // Check zip exists
    if (Files.notExists(zipPath)) {
      String msg = "Zip " + zipPath + " does not exist";
      updateListener(listener, id, ProgressState.FAIL, Fntcv4Step.ARCHIVE_EXIST, msg);
      throw new SipException(msg);
    }
    updateListener(listener, id, ProgressState.SUCCESS, Fntcv4Step.ARCHIVE_EXIST, "Archive exists");

    // Check zip is valid
    if (!Files.isReadable(zipPath) || Files.isDirectory(zipPath)) {
      String msg = zipPath + " is not a readable zip";
      updateListener(listener, id, ProgressState.FAIL, Fntcv4Step.ARCHIVE_READABLE, msg);
      throw new SipException(msg);
    }
    updateListener(
        listener, id, ProgressState.SUCCESS, Fntcv4Step.ARCHIVE_READABLE, "Archive is readable");

    // Check zip is really a zip
    URI zipUri = SipUtils.createZipURI(zipPath);
    try (FileSystem zipArchive = FileSystems.newFileSystem(zipUri, Collections.emptyMap())) {
      updateListener(
          listener, id, ProgressState.SUCCESS, Fntcv4Step.ARCHIVE_UNZIP, "Archive is opened");

      Path manifestPath = zipArchive.getPath("manifest.xml");
      if (Files.notExists(manifestPath) || Files.isDirectory(manifestPath)) {
        String msg = "Zip " + zipPath + " does not contain a valid manifest";
        updateListener(listener, id, ProgressState.FAIL, Fntcv4Step.MANIFEST_EXIST, msg);
        throw new SipException(msg);
      }
      updateListener(
          listener, id, ProgressState.SUCCESS, Fntcv4Step.MANIFEST_EXIST, "Manifest exists");

      ByteArrayInOutStream manifest = new ByteArrayInOutStream(1024);

      // Check manifest is valid against xsd
      try {
        Files.copy(manifestPath, manifest);
        Validator fntcValidator = fntcSchema.newValidator();
        fntcValidator.setFeature(HTTP_APACHE_ORG_XML_FEATURES_DISALLOW_DOCTYPE_DECL, true);
        fntcValidator.validate(new StreamSource(manifest.getInputStream()));
      } catch (IOException | SAXException ex) {
        String msg = "Unable to validate manifest for " + zipPath;
        updateListener(listener, id, ProgressState.FAIL, Fntcv4Step.MANIFEST_FNTC, msg);
        throw new SipException(msg, ex);
      }
      updateListener(
          listener,
          id,
          ProgressState.SUCCESS,
          Fntcv4Step.MANIFEST_FNTC,
          "Manifest conforms to FNTC");

      // Check manifest is valid against rng
      if (validator != null) {
        try {
          validator.validate(new StreamSource(manifest.getInputStream()));
        } catch (IOException | SAXException ex) {
          String msg = "Unable to validate manifest for " + zipPath;
          updateListener(listener, id, ProgressState.FAIL, Fntcv4Step.MANIFEST_VALIDATOR, msg);
          throw new SipException(msg, ex);
        }
        updateListener(
            listener,
            id,
            ProgressState.SUCCESS,
            Fntcv4Step.MANIFEST_VALIDATOR,
            "Manifest conforms to validator");
      }

      // Parse the manifest to look for binary path
      if (config.checkBinary()) {
        ArrayList<Fntcv4BinaryObject> binaryObjects;
        try {
          binaryObjects = Fntcv4Parser.parse(manifest.getInputStream());
        } catch (IOException | ParserConfigurationException | SAXException ex) {
          String msg = "Unable to parse manifest for " + zipPath;
          updateListener(listener, id, ProgressState.FAIL, Fntcv4Step.MANIFEST_PARSE, msg);
          throw new SipException(msg, ex);
        }
        updateListener(
            listener, id, ProgressState.SUCCESS, Fntcv4Step.MANIFEST_PARSE, "Manifest is parsed");

        for (Fntcv4BinaryObject binaryObject : binaryObjects) {

          // Check binaryPath exists
          Path binaryPath = zipArchive.getPath(binaryObject.getUri());
          if (Files.notExists(binaryPath) || Files.isDirectory(binaryPath)) {
            String msg = binaryPath + " does not exist for " + zipPath;
            updateListener(listener, id, ProgressState.FAIL, Fntcv4Step.BINARY_EXIST, msg);
            throw new SipException(msg);
          }
          updateListener(
              listener,
              id,
              ProgressState.SUCCESS,
              Fntcv4Step.BINARY_EXIST,
              "Binary object exists: " + binaryPath);

          // Check binaryPath Directory is "Content"
          Path parentPath = binaryPath.getParent();
          if (!"Content".equals(parentPath.getFileName().toString())) {
            String msg = binaryPath + " parent folder is not valid for " + zipPath;
            updateListener(listener, id, ProgressState.FAIL, Fntcv4Step.BINARY_FOLDER, msg);
            throw new SipException(msg);
          }
          updateListener(
              listener,
              id,
              ProgressState.SUCCESS,
              Fntcv4Step.BINARY_FOLDER,
              "Binary object folder is valid");

          // Check binaryPath size
          if (config.checkSize()) {
            if (Files.size(binaryPath) != binaryObject.getSize()) {
              String msg = binaryPath + " size is not valid for " + zipPath;
              updateListener(listener, id, ProgressState.FAIL, Fntcv4Step.BINARY_SIZE, msg);
              throw new SipException(msg);
            }
            updateListener(
                listener,
                id,
                ProgressState.SUCCESS,
                Fntcv4Step.BINARY_SIZE,
                "Binary object size is valid");
          }

          // Check binaryPath hash equals the given hash
          if (config.checkDigest()) {
            byte[] d = SipUtils.digest(binaryPath, binaryObject.getAlgorithm());
            String digest =
                Base64.getEncoder().encodeToString(d); // The hash is Base64 encoded in the XML

            if (!digest.equals(binaryObject.getDigest())) {
              String msg = binaryPath + " hash is not valid for " + zipPath;
              updateListener(listener, id, ProgressState.FAIL, Fntcv4Step.BINARY_DIGEST, msg);
              throw new SipException(msg);
            }
            updateListener(
                listener,
                id,
                ProgressState.SUCCESS,
                Fntcv4Step.BINARY_DIGEST,
                "Binary object digest is valid");
          }

          // Check binaryPath Format. The Droid library does not support NIO FileSystem.
        }
      }

    } catch (IOException ex) {
      String msg = "Unable to open zip " + zipPath;
      updateListener(listener, id, ProgressState.FAIL, Fntcv4Step.ARCHIVE_UNZIP, msg);
      throw new SipException(msg, ex);
    }

    updateListener(listener, id, ProgressState.SUCCESS, Fntcv4Step.COMPLETE, "Archive is valid");
  }

  private void updateListener(
      ProgressListener<Fntcv4Step> listener,
      String id,
      ProgressState status,
      Fntcv4Step step,
      String message) {
    if (listener != null) {
      listener.progressChanged(new ProgressEvent<>(id, status, step, message));
    }
  }
}
