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

import fr.xelians.sipg.service.common.LSInputImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;

import java.io.InputStream;

/**
 * La classe Fntcv4Resolver permet de résoudre les accès aux schémas inclus dans les XSD utilisés lors de la conversion
 * en FNTC v4.
 *
 * @author Emmanuel Deviller
 */
public class Fntcv4Resolver implements LSResourceResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Fntcv4Resolver.class);

    private final InputStream fntcInputStream;
    private final InputStream xmlInputStream;
    private final InputStream xlinkInputStream;

    /**
     * Instantiates a new Fntcv 4 resolver.
     *
     * @param fntcInputStream  the fntc input stream
     * @param xmlInputStream   the xml input stream
     * @param xlinkInputStream the xlink input stream
     */
    public Fntcv4Resolver(InputStream fntcInputStream, InputStream xmlInputStream, InputStream xlinkInputStream) {
        this.fntcInputStream = fntcInputStream;
        this.xmlInputStream = xmlInputStream;
        this.xlinkInputStream = xlinkInputStream;
    }

    @Override
    public LSInput resolveResource(final String type, final String namespaceURI, final String publicId, String systemId,
                                   final String baseURI) {

        switch (systemId) {
            case "fntcta-4.0.xsd":
                return new LSInputImpl(publicId, systemId, fntcInputStream);
            case "http://www.w3.org/2001/xml.xsd":
                return new LSInputImpl(publicId, systemId, xmlInputStream);
            case "http://www.w3.org/1999/xlink.xsd":
                return new LSInputImpl(publicId, systemId, xlinkInputStream);
            default:
                LOGGER.info("Unable to resolve resource {}", systemId);
        }
        return null;
    }
}
