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

import fr.gouv.culture.archivesdefrance.seda.v2.ArchiveTransferType;
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
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.Validate;
import org.glassfish.jaxb.runtime.marshaller.NamespacePrefixMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
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

import static fr.xelians.sipg.service.common.ProgressState.FAIL;
import static fr.xelians.sipg.service.common.ProgressState.SUCCESS;

/**
 * La classe Sedav2Service offre des API pour valider et sérialiser une archive dans un paquet zip selon le standard
 * SEDA v2.1. Le processus de sérialisation peut être contrôlé par configuration. Il est possible d'opérer une
 * validation supplémentaire du fichier de description de l'archive selon un schéma RNG. Note. La classe Sedav2Service
 * est thread-safe.
 *
 * @author Emmanuel Deviller
 * @see ArchiveTransfer
 * @see Sedav2Config
 */
public class Sedav2Service {

    private static final String HTTP_WWW_W3_ORG_XML_XML_SCHEMA_V1_1 = "http://www.w3.org/XML/XMLSchema/v1.1";
    public static final String HTTP_APACHE_ORG_XML_FEATURES_DISALLOW_DOCTYPE_DECL = "http://apache.org/xml/features/disallow-doctype-decl";

    private static final Logger LOGGER = LoggerFactory.getLogger(Sedav2Service.class);
    private static final Sedav2Service INSTANCE = new Sedav2Service();

    private final JAXBContext sedaContext;
    private final Schema sedaSchema;

    private final NamespacePrefixMapper namespaceMapper = new NamespacePrefixMapper() {
        public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
            return !requirePrefix && "fr:gouv:culture:archivesdefrance:seda:v2.1".equals(namespaceUri) ? "" : "ns";
        }
    };

    private Sedav2Service() {
        // As the xsd 1.1 validator does not support included schemas, we provide a flattened schema
        try (InputStream is1 = SipUtils.resourceAsStream("seda-vitam-2.1-full.xsd");
             InputStream is2 = SipUtils.resourceAsStream("xml.xsd");
             InputStream is3 = SipUtils.resourceAsStream("xlink.xsd")) {

            SchemaFactory sf = SchemaFactory.newInstance(HTTP_WWW_W3_ORG_XML_XML_SCHEMA_V1_1);
            sf.setFeature(HTTP_APACHE_ORG_XML_FEATURES_DISALLOW_DOCTYPE_DECL, true); // Avoid XXE
            sf.setResourceResolver(new Sedav2Resolver(is2, is3));
            sedaSchema = sf.newSchema(new StreamSource(is1));
            sedaContext = JAXBContext.newInstance(fr.gouv.culture.archivesdefrance.seda.v2.ObjectFactory.class);
        } catch (IOException | JAXBException | SAXException ex) {
            throw new SipException("Unable to initialize XSD Schemas, JAXBContext and Marshaller", ex);
        }
    }

    /**
     * Retourne l'instance singleton de la classe Sedav2Service.
     *
     * @return l 'instance singleton
     */
    public static Sedav2Service getInstance() {
        return INSTANCE;
    }

    /**
     * Sérialise l'archive dans un paquet zip au format SEDA v2.1.
     *
     * @param archive l'archive à sérialiser
     * @param zipPath le path du paquet zip
     */
    public void write(ArchiveTransfer archive, Path zipPath) {
        write(archive, zipPath, null, Sedav2Config.DEFAULT);
    }

    /**
     * Sérialise l'archive dans un paquet zip au format SEDA v2.1. Le fichier XML de description de l'archive peut être
     * validé selon le schéma RNG défini par le Validator.
     * <p>
     * Note. L'objet Validator n'est pas thread-safe, il est de la responsabilité de l'application appelante de s'assurer
     * que l'objet {@link Validator} n'est utilisé à tout moment que par une seule et même thread.
     *
     * @param archive   l'archive à sérialiser
     * @param zipPath   le path du paquet zip
     * @param validator le validateur RNG
     */
    public void write(ArchiveTransfer archive, Path zipPath, Validator validator) {
        write(archive, zipPath, validator, Sedav2Config.DEFAULT);
    }

    /**
     * Sérialise l'archive dans un paquet zip au format SEDA v2.1. La configuration permet de contrôler le processus de
     * sérialisation.
     *
     * @param archive l'archive à sérialiser
     * @param zipPath le path du paquet zip
     * @param config  la configuration utilisée lors de la sérialisation
     */
    public void write(ArchiveTransfer archive, Path zipPath, Sedav2Config config) {
        write(archive, zipPath, null, config);
    }

    /**
     * Sérialise l'archive dans un paquet zip au format SEDA v2.1. Le fichier XML de description de l'archive peut être
     * validé selon le schéma RNG défini par le Validator. La configuration permet de contrôler le processus de conversion
     * et de sérialisation.
     * <p>
     * Note. L'objet Validator n'est pas thread-safe, il est de la responsabilité de l'application appelante de s'assurer
     * que l'objet {@link Validator} n'est utilisé à tout moment que par une seule et même thread.
     *
     * @param archive   l'archive à sérialiser
     * @param zipPath   le path du paquet zip
     * @param validator le validateur RNG
     * @param config    la configuration utilisée lors du processus de sérialisation
     */
    public void write(ArchiveTransfer archive, Path zipPath, Validator validator, Sedav2Config config) {
        Validate.notNull(archive, SipUtils.NOT_NULL, "archiveTransfer");
        Validate.notNull(zipPath, SipUtils.NOT_NULL, "zipPath");
        Validate.notNull(config, SipUtils.NOT_NULL, "config");

        try {
            Files.deleteIfExists(zipPath);
        } catch (IOException ex) {
            throw new SipException("Unable to delete file " + zipPath, ex);
        }

        try (FileSystem zipArchive = SipUtils.newZipFileSystem(zipPath)) {
            ArchiveTransferType att = Sedav2Converter.convert(archive, zipArchive, config);
            final Path zipEntryPath = zipArchive.getPath("manifest.xml");
            try (OutputStream os = Files.newOutputStream(zipEntryPath)) {

                // Set External Validator
                if (validator != null) {
                    validator.validate(new JAXBSource(sedaContext, att));
                }

                Marshaller sedaMarshaller = sedaContext.createMarshaller();
                sedaMarshaller.setSchema(config.isValidate() ? sedaSchema : null);
                sedaMarshaller.setProperty("org.glassfish.jaxb.namespacePrefixMapper", namespaceMapper);
                sedaMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);

                if (LOGGER.isDebugEnabled()) {
                    sedaMarshaller.setEventHandler(new Sedav2EventHandler());
                    sedaMarshaller.setListener(new Sedav2Listener());
                }

                // Marshall & prettyPrint
                if (config.isFormat()) {
                    // JAXB_FORMATTED_OUTPUT is buggy and does not format XML with DOM nodes. Hence, this ugly hack...
                    ByteArrayInOutStream baios = new ByteArrayInOutStream(1024);
                    sedaMarshaller.marshal(att, baios);
                    SipUtils.formatXml(baios.getInputStream(), os, config.getIndent());
                } else {
                    sedaMarshaller.marshal(att, os);
                }
            }
        } catch (IOException | ExecutionException | InterruptedException | JAXBException | SAXException ex) {
            throw new SipException("Unable to serialize archive " + zipPath, ex);
        }
    }

    /**
     * Valide le XML de description de l'archive selon le schéma défini par le standard SEDA v2.1.
     *
     * @param archive l'archive à valider
     */
    public void validate(ArchiveTransfer archive) {
        validate(archive, null, Sedav2Config.DEFAULT);
    }

    /**
     * Valide le XML de description de l'archive selon le schéma défini par le standard SEDA v2.1. La configuration permet
     * de contrôler le processus de validation.
     *
     * @param archive l'archive à valider
     * @param config  la configuration utilisée lors du processus de validation
     */
    public void validate(ArchiveTransfer archive, Sedav2Config config) {
        validate(archive, null, config);
    }

    /**
     * Valide le XML de description de l'archive selon le schéma défini par le standard SEDA v2.1. Le XML de description
     * de l'archive peut être validé selon le schéma RNG défini par le Validator.
     * <p>
     * Note. L'objet Validator n'est pas thread-safe, il est de la responsabilité de l'application appelante de s'assurer
     * que l'objet {@link Validator} n'est utilisé à tout moment que par une seule et même thread.
     *
     * @param archive   l'archive à valider
     * @param validator le validateur RNG
     */
    public void validate(ArchiveTransfer archive, Validator validator) {
        validate(archive, validator, Sedav2Config.DEFAULT);
    }

    /**
     * Valide le XML de description de l'archive selon le schéma défini par le standard SEDA v2.1. Le XML de description
     * de l'archive peut être validé selon le schéma RNG défini par le Validator. La configuration permet de contrôler le
     * processus de validation.
     * <p>
     * Note. L'objet Validator n'est pas thread-safe, il est de la responsabilité de l'application appelante de s'assurer
     * que l'objet {@link Validator} n'est utilisé à tout moment que par une seule et même thread.
     *
     * @param archive   l'archive à valider
     * @param validator le validateur RNG
     * @param config    la configuration utilisée lors du processus de validation
     */
    public void validate(ArchiveTransfer archive, Validator validator, Sedav2Config config) {
        Validate.notNull(archive, SipUtils.NOT_NULL, "archive");
        Validate.notNull(config, SipUtils.NOT_NULL, "config");

        try {
            ArchiveTransferType att = Sedav2Converter.convert(archive, config);
            JAXBSource source = new JAXBSource(sedaContext, att);
            sedaSchema.newValidator().validate(source);

            if (validator != null) {
                validator.validate(source);
            }
        } catch (IOException | ExecutionException | InterruptedException | JAXBException | SAXException ex) {
            Thread.currentThread().interrupt();
            throw new SipException("Unable to validate archive", ex);
        }
    }

    /**
     * Valide le fichier XML ou l'archive selon le schéma défini par le standard SEDA v2.1.
     *
     * @param path le path du fichier XML à valider
     */
    public void validate(Path path) {
        Validate.notNull(path, SipUtils.NOT_NULL, "path");
        validate(path, null, Sedav2Config.DEFAULT, null);
    }

    /**
     * Valide le fichier XML ou l'archive selon le schéma défini par le standard SEDA v2.1. L'objet Validator n'est pas
     * thread-safe, il est de la responsabilité de l'application appelante de s'assurer que l'objet {@link Validator}
     * n'est utilisé à tout moment que par une seule et même thread.
     *
     * @param path      le path du fichier XML à valider
     * @param validator le validateur RNG
     */
    public void validate(Path path, Validator validator) {
        Validate.notNull(path, SipUtils.NOT_NULL, "path");

        validate(path, validator, Sedav2Config.DEFAULT, null);
    }

    /**
     * Valide le fichier XML ou l'archive selon le schéma défini par le standard SEDA v2.1.
     *
     * @param path   le path du fichier XML à valider
     * @param config la configuration utilisée lors du processus de validation
     */
    public void validate(Path path, Sedav2Config config) {
        Validate.notNull(path, SipUtils.NOT_NULL, "path");
        Validate.notNull(config, SipUtils.NOT_NULL, "config");

        validate(path, null, config, null);
    }

    /**
     * Valide le fichier XML ou l'archive selon le schéma défini par le standard SEDA v2.1. L'objet Validator n'est pas
     * thread-safe, il est de la responsabilité de l'application appelante de s'assurer que l'objet {@link Validator}
     * n'est utilisé à tout moment que par une seule et même thread.
     *
     * @param path      le path du fichier XML à valider
     * @param validator le validateur RNG
     * @param config    the config
     */
    public void validate(Path path, Validator validator, Sedav2Config config) {
        Validate.notNull(path, SipUtils.NOT_NULL, "path");
        Validate.notNull(config, SipUtils.NOT_NULL, "config");

        validate(path, validator, config, null);
    }

    /**
     * Valide le fichier XML ou l'archive selon le schéma défini par le standard SEDA v2.1. Le {@link Validator} permet
     * d'appliquer une validation supplémentaire. Note. L'objet Validator n'est pas thread-safe, il est de la
     * responsabilité de l'application appelante de s'assurer que l'objet {@link Validator} n'est utilisé à tout moment
     * que par une seule et même thread.
     *
     * @param path      le path du fichier XML à valider
     * @param validator le validateur RNG
     * @param config    la configuration utilisée lors du processus de validation
     * @param listener  la méthode de callback pour suivre la progression de la validation
     */
    public void validate(Path path, Validator validator, Sedav2Config config, ProgressListener<Sedav2Step> listener) {
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
     * Valide la source XML source selon le schéma défini par le standard SEDA v2.1.
     *
     * @param source la source XML à valider
     */
    private void validate(Source source) {
        Validate.notNull(source, SipUtils.NOT_NULL, "source");

        try {
            Validator sedaValidator = sedaSchema.newValidator();
            sedaValidator.setFeature(HTTP_APACHE_ORG_XML_FEATURES_DISALLOW_DOCTYPE_DECL, true);
            sedaValidator.validate(source);
        } catch (IOException | SAXException ex) {
            throw new SipException("Unable to validate " + source, ex);
        }
    }

    private void validateZip(Path zipPath, Validator validator, Sedav2Config config,
                             ProgressListener<Sedav2Step> listener) {

        String id = FilenameUtils.removeExtension(zipPath.getFileName().toString());
        updateListener(listener, id, SUCCESS, Sedav2Step.START, "Archive: " + zipPath);

        // Check zip exists
        if (Files.notExists(zipPath)) {
            String msg = "Archive does not exist: " + zipPath;
            updateListener(listener, id, FAIL, Sedav2Step.ARCHIVE_EXIST, msg);
            throw new SipException(msg);
        }
        updateListener(listener, id, SUCCESS, Sedav2Step.ARCHIVE_EXIST, "Archive exists");

        // Check zip is valid
        if (!Files.isReadable(zipPath) || Files.isDirectory(zipPath)) {
            String msg = "Archive is not a readable zip: " + zipPath;
            updateListener(listener, id, FAIL, Sedav2Step.ARCHIVE_READABLE, msg);
            throw new SipException(msg);
        }
        updateListener(listener, id, SUCCESS, Sedav2Step.ARCHIVE_READABLE, "Archive is readable");

        // Check zip is really a zip
        URI zipUri = SipUtils.createZipURI(zipPath);
        try (FileSystem zipArchive = FileSystems.newFileSystem(zipUri, Collections.emptyMap())) {
            updateListener(listener, id, SUCCESS, Sedav2Step.ARCHIVE_UNZIP, "Archive is opened");

            Path manifestPath = zipArchive.getPath("manifest.xml");
            if (Files.notExists(manifestPath) || Files.isDirectory(manifestPath)) {
                String msg = "Archive does not contain a manifest: " + zipPath;
                updateListener(listener, id, FAIL, Sedav2Step.MANIFEST_EXIST, msg);
                throw new SipException(msg);
            }
            updateListener(listener, id, SUCCESS, Sedav2Step.MANIFEST_EXIST, "Manifest exists");

            ByteArrayInOutStream manifest = new ByteArrayInOutStream(1024);

            // Check manifest is valid against xsd
            try {
                Files.copy(manifestPath, manifest);
                Validator sedaValidator = sedaSchema.newValidator();
                sedaValidator.setFeature(HTTP_APACHE_ORG_XML_FEATURES_DISALLOW_DOCTYPE_DECL, true);
                sedaValidator.validate(new StreamSource(manifest.getInputStream()));
            } catch (IOException | SAXException ex) {
                String msg = "Unable to validate manifest: " + zipPath;
                updateListener(listener, id, FAIL, Sedav2Step.MANIFEST_SEDA, msg);
                throw new SipException(msg, ex);
            }
            updateListener(listener, id, SUCCESS, Sedav2Step.MANIFEST_SEDA, "Manifest conforms to SEDA");

            // Check manifest is valid against rng
            if (validator != null) {
                try {
                    validator.validate(new StreamSource(manifest.getInputStream()));
                } catch (IOException | SAXException ex) {
                    String msg = "Unable to validate manifest: " + zipPath;
                    updateListener(listener, id, FAIL, Sedav2Step.MANIFEST_VALIDATOR, msg);
                    throw new SipException(msg, ex);
                }
                updateListener(listener, id, SUCCESS, Sedav2Step.MANIFEST_VALIDATOR, "Manifest conforms to validator");
            }

            // Parse the manifest to look for binary path
            if (config.checkBinary()) {
                ArrayList<Sedav2BinaryObject> binaryObjects;
                try {
                    binaryObjects = Sedav2Parser.parse(manifest.getInputStream());
                } catch (IOException | ParserConfigurationException | SAXException ex) {
                    String msg = "Unable to parse manifest: " + zipPath;
                    updateListener(listener, id, FAIL, Sedav2Step.MANIFEST_PARSE, msg);
                    throw new SipException(msg, ex);
                }
                updateListener(listener, id, SUCCESS, Sedav2Step.MANIFEST_PARSE, "Manifest is parsed");

                for (Sedav2BinaryObject binaryObject : binaryObjects) {

                    // Check binaryPath exists
                    Path binaryPath = zipArchive.getPath(binaryObject.getUri());
                    if (Files.notExists(binaryPath) || Files.isDirectory(binaryPath)) {
                        String msg = "Binary object does not exist: " + zipPath + "!" + binaryPath;
                        updateListener(listener, id, FAIL, Sedav2Step.BINARY_EXIST, msg);
                        throw new SipException(msg);
                    }
                    updateListener(listener, id, SUCCESS, Sedav2Step.BINARY_EXIST, "Binary object exists: " + binaryPath);

                    // Check binaryPath Directory is "Content"
                    Path parentPath = binaryPath.getParent();
                    if (!"Content".equals(parentPath.getFileName().toString())) {
                        String msg = "Binary object folder is not valid: " + zipPath + "!" + binaryPath;
                        updateListener(listener, id, FAIL, Sedav2Step.BINARY_FOLDER, msg);
                        throw new SipException(msg);
                    }
                    updateListener(listener, id, SUCCESS, Sedav2Step.BINARY_FOLDER, "Binary object folder is valid");

                    // Check binaryPath size
                    if (config.checkSize()) {
                        if (Files.size(binaryPath) != binaryObject.getSize()) {
                            String msg = "Binary object size is not valid: " + zipPath + "!" + binaryPath;
                            updateListener(listener, id, FAIL, Sedav2Step.BINARY_SIZE, msg);
                            throw new SipException(msg);
                        }
                        updateListener(listener, id, SUCCESS, Sedav2Step.BINARY_SIZE, "Binary object size is valid");
                    }

                    // Check binaryPath hash equals the given hash
                    if (config.checkDigest()) {
                        String digest = SipUtils.digestHex(binaryPath, binaryObject.getAlgorithm());
                        if (!digest.equals(binaryObject.getDigest())) {
                            String msg = binaryPath + "Binary object  digest is not valid: " + zipPath + "!" + binaryPath;
                            updateListener(listener, id, FAIL, Sedav2Step.BINARY_DIGEST, msg);
                            throw new SipException(msg);
                        }
                        updateListener(listener, id, SUCCESS, Sedav2Step.BINARY_DIGEST, "Binary object digest is valid");
                    }

                    // Check binaryPath Format. The Droid library does not support NIO FileSystem.
                }
            }
        } catch (IOException ex) {
            String msg = "Unable to open: " + zipPath;
            updateListener(listener, id, FAIL, Sedav2Step.ARCHIVE_UNZIP, msg);
            throw new SipException(msg, ex);
        }

        updateListener(listener, id, SUCCESS, Sedav2Step.COMPLETE, "Archive is valid");
    }

    private void updateListener(ProgressListener<Sedav2Step> listener, String id, ProgressState status, Sedav2Step step,
                                String message) {
        if (listener != null) {
            listener.progressChanged(new ProgressEvent<>(id, status, step, message));
        }
    }

}
