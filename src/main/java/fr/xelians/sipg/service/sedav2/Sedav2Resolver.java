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

import fr.xelians.sipg.service.common.LSInputImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;

import java.io.InputStream;

/**
 * La classe Sedav2Resolver permet de résoudre les accès aux schémas inclus dans les XSD utilisés lors de la conversion
 * en SEDA v2.1.
 *
 * @author Emmanuel Deviller
 */
public class Sedav2Resolver implements LSResourceResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sedav2Resolver.class);

    private final InputStream xmlInputStream;
    private final InputStream xlinkInputStream;

    /**
     * Instantiates a new Sedav 2 resolver.
     *
     * @param xmlInputStream   the xml input stream
     * @param xlinkInputStream the xlink input stream
     */
    public Sedav2Resolver(InputStream xmlInputStream, InputStream xlinkInputStream) {
        this.xmlInputStream = xmlInputStream;
        this.xlinkInputStream = xlinkInputStream;
    }

    @Override
    public LSInput resolveResource(final String type, final String namespaceURI, final String publicId, String systemId,
            final String baseURI) {

        return switch (systemId) {
            case "http://www.w3.org/2001/xml.xsd" -> new LSInputImpl(publicId, systemId, xmlInputStream);
            case "http://www.w3.org/1999/xlink.xsd" -> new LSInputImpl(publicId, systemId, xlinkInputStream);
            default -> {
                LOGGER.info("Unable to resolve resource {}", systemId);
                yield null;
            }
        };
    }

}
