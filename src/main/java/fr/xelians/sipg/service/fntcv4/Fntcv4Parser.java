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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * La classe Fntcv4Parser permet de parser le manifeste XML d'une archive au format FNTC v4.
 *
 * @author Emmanuel Deviller
 */
class Fntcv4Parser extends DefaultHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Fntcv4Parser.class);

    private final ArrayList<Fntcv4BinaryObject> binaryObjects = new ArrayList<>();
    private Fntcv4BinaryObject binaryObject;
    private StringBuilder buffer;

    private Fntcv4Parser() {
    }

    /**
     * Parse array list.
     *
     * @param is the is
     * @return the array list
     * @throws IOException                  the io exception
     * @throws ParserConfigurationException the parser configuration exception
     * @throws SAXException                 the sax exception
     */
    static ArrayList<Fntcv4BinaryObject> parse(InputStream is)
            throws IOException, ParserConfigurationException, SAXException {
        Fntcv4Parser parser = new Fntcv4Parser();
        XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        reader.setContentHandler(parser);
        reader.setErrorHandler(parser);
        reader.setFeature("http://xml.org/sax/features/validation", false);
        reader.setFeature("http://xml.org/sax/features/namespaces", false);

        // Avoid XXE
        reader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        reader.setFeature("http://xml.org/sax/features/external-general-entities", false);
        reader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);

        reader.parse(new InputSource(is));
        return parser.binaryObjects;
    }

    @Override
    public void startElement(String uri, String local, String raw, Attributes attrs) {
        if ("BinaryDataObject".equals(raw)) {
            binaryObject = new Fntcv4BinaryObject();
            return;
        }

        if (binaryObject == null) {
            return;
        }

        buffer = new StringBuilder();

        if ("Attachment".equals(raw)) {
            for (int i = 0; i < attrs.getLength(); i++) {
                if ("uri".equals(attrs.getQName(i))) {
                    binaryObject.setUri(attrs.getValue(i));
                }
            }
            return;
        }

        if ("MessageDigest".equals(raw)) {
            for (int i = 0; i < attrs.getLength(); i++) {
                if ("algorithm".equals(attrs.getQName(i))) {
                    binaryObject.setAlgorithm(attrs.getValue(i));
                }
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (buffer != null) {
            buffer.append(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String local, String raw) {
        if (binaryObject != null) {

            if ("MessageDigest".equals(raw)) {
                binaryObject.setDigest(buffer.toString());
                buffer = null;
                return;
            }

            if ("Size".equals(raw)) {
                binaryObject.setSize(Long.parseLong(buffer.toString()));
                buffer = null;
                return;
            }

            if ("Format".equals(raw)) {
                binaryObject.setFormat(buffer.toString());
                buffer = null;
                return;
            }

            if ("BinaryDataObject".equals(raw)) {
                binaryObjects.add(binaryObject);
                binaryObject = null;
                buffer = null;
                return;
            }
        }

        buffer = null;
    }

    @Override
    public void warning(SAXParseException ex) {
        LOGGER.warn(getLocationString(ex), ex);
    }

    @Override
    public void error(SAXParseException ex) {
        LOGGER.warn(getLocationString(ex), ex);
    }

    @Override
    public void fatalError(SAXParseException ex) {
        LOGGER.warn(getLocationString(ex), ex);
    }

    // Returns a string of the location.
    private String getLocationString(SAXParseException ex) {
        StringBuilder str = new StringBuilder();

        String systemId = ex.getSystemId();
        if (systemId != null) {
            int index = systemId.lastIndexOf('/');
            if (index != -1) {
                systemId = systemId.substring(index + 1);
            }
            str.append(systemId);
        }
        str.append(':').append(ex.getLineNumber());
        str.append(':').append(ex.getColumnNumber());
        return str.toString();
    }

}
