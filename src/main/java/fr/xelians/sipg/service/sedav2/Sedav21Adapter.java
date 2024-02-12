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

import fr.gouv.culture.archivesdefrance.seda.v21.ArchiveTransferType;
import fr.xelians.sipg.model.ArchiveTransfer;
import fr.xelians.sipg.utils.ByteArrayInOutStream;
import fr.xelians.sipg.utils.SipException;
import fr.xelians.sipg.utils.SipUtils;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.util.JAXBSource;
import org.glassfish.jaxb.runtime.marshaller.NamespacePrefixMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutionException;

public class Sedav21Adapter implements Sedav2Adapter {

    private static final String HTTP_WWW_W3_ORG_XML_XML_SCHEMA_V1_1 = "http://www.w3.org/XML/XMLSchema/v1.1";
    public static final String HTTP_APACHE_ORG_XML_FEATURES_DISALLOW_DOCTYPE_DECL = "http://apache.org/xml/features/disallow-doctype-decl";

    private static final Logger LOGGER = LoggerFactory.getLogger(Sedav21Adapter.class);

    public static final Sedav21Adapter INSTANCE = new Sedav21Adapter() ;

    private static final JAXBContext sedaContext;
    private static final Schema sedaSchema;

    private static final NamespacePrefixMapper namespaceMapper = new NamespacePrefixMapper() {
        public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
            return !requirePrefix && "fr:gouv:culture:archivesdefrance:seda:v2.1".equals(namespaceUri) ? "" : "ns";
        }
    };

    static {
        // Provide flattened schema in resource
        try (InputStream is1 = SipUtils.resourceAsStream("seda-vitam-2.1-full.xsd"); InputStream is2 = SipUtils.resourceAsStream("xml.xsd"); InputStream is3 = SipUtils.resourceAsStream("xlink.xsd")) {
            SchemaFactory sf = SchemaFactory.newInstance(HTTP_WWW_W3_ORG_XML_XML_SCHEMA_V1_1);
            sf.setFeature(HTTP_APACHE_ORG_XML_FEATURES_DISALLOW_DOCTYPE_DECL, true); // Avoid XXE
            sf.setResourceResolver(new Sedav2Resolver(is2, is3));
            sedaSchema = sf.newSchema(new StreamSource(is1));
            sedaContext = JAXBContext.newInstance(fr.gouv.culture.archivesdefrance.seda.v21.ObjectFactory.class);
        } catch (IOException | JAXBException | SAXException ex) {
            throw new SipException("Unable to initialize XSD Schemas, JAXBContext and Marshaller", ex);
        }
    }

    private Sedav21Adapter() {
    }

    @Override
    public void write(ArchiveTransfer archive, Validator validator, Path zipPath, Sedav2Config config) {

        try (FileSystem zipArchive = SipUtils.newZipFileSystem(zipPath, config.useMemory())) {
            ArchiveTransferType att = Sedav21Converter.convert(archive, zipArchive, config);
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
        } catch (IOException | JAXBException | SAXException | ExecutionException |
                 InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new SipException("Failed to write archive to " + zipPath, ex);
        }
    }

    @Override
    public void validate(ArchiveTransfer archive, Validator validator, Sedav2Config config) {
        try {
            ArchiveTransferType att = Sedav21Converter.convert(archive, config);
            JAXBSource source = new JAXBSource(sedaContext, att);

            Validator sedaValidator = sedaSchema.newValidator();
            sedaValidator.setFeature(HTTP_APACHE_ORG_XML_FEATURES_DISALLOW_DOCTYPE_DECL, true);
            sedaValidator.validate(source);

            if (validator != null) {
                validator.validate(source);
            }
        } catch (SAXException | IOException | ExecutionException | InterruptedException |
                 JAXBException ex) {
            Thread.currentThread().interrupt();
            throw new SipException("Unable to validate archive", ex);
        }
    }

    @Override
    public void validate(Source source, Sedav2Config config) {
        try {
            Validator sedaValidator = sedaSchema.newValidator();
            sedaValidator.setFeature(HTTP_APACHE_ORG_XML_FEATURES_DISALLOW_DOCTYPE_DECL, true);
            sedaValidator.validate(source);
        } catch (SAXException | IOException ex) {
            throw new SipException("Unable to validate archive", ex);
        }
    }
}
