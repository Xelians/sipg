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
package fr.xelians.sipg.service.common;

import fr.xelians.sipg.service.fntcv4.Fntcv4Resolver;
import fr.xelians.sipg.service.sedav2.Sedav2Resolver;
import org.w3c.dom.ls.LSInput;

import java.io.InputStream;
import java.io.Reader;

/**
 * La classe LSInputImpl définit une implémentation de l'interface LSInput. Elle est utilisée dans les classes de type
 * Resolver pour résoudre les accès aux schémas inclus dans les XSD. Note. Il est fortement déconseillé d'utiliser cette
 * classe en dehors de la librairie.
 *
 * @author Emmanuel Deviller
 * @see Fntcv4Resolver
 * @see Sedav2Resolver
 */
public class LSInputImpl implements LSInput {

    /**
     * L'identifiant public du schéma.
     */
    protected String publicId;

    /**
     * L'identifiant système du schéma.
     */
    protected String systemId;

    /**
     * L'identifiant de base du système du schéma.
     */
    protected String baseSystemId;

    /**
     * Le flux d'entrée binaire.
     */
    protected InputStream byteStream;

    /**
     * Le flux d'entrée des caractères.
     */
    protected Reader charStream;

    /**
     * Les données sous forme de chaîne de caractères.
     */
    protected String data;

    /**
     * L'encodage.
     */
    protected String encoding;

    /**
     * Le texte certifié.
     */
    protected boolean certifiedText;

    /**
     * Instantiates a new Ls input.
     */
    public LSInputImpl() {
    }

    /**
     * Instancie la classe.
     *
     * @param publicId   l'identifiant public du schéma
     * @param systemId   l'identifiant système du schéma
     * @param byteStream le flux d'entrée binaire
     */
    public LSInputImpl(String publicId, String systemId, InputStream byteStream) {
        this.publicId = publicId;
        this.systemId = systemId;
        this.byteStream = byteStream;
    }

    @Override
    public InputStream getByteStream() {
        return byteStream;
    }

    @Override
    public void setByteStream(InputStream byteStream) {
        this.byteStream = byteStream;
    }

    @Override
    public Reader getCharacterStream() {
        return charStream;
    }

    @Override
    public void setCharacterStream(Reader characterStream) {
        charStream = characterStream;
    }

    @Override
    public String getStringData() {
        return data;
    }

    @Override
    public void setStringData(String stringData) {
        data = stringData;
    }

    @Override
    public String getEncoding() {
        return encoding;
    }

    @Override
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @Override
    public String getPublicId() {
        return publicId;
    }

    @Override
    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    @Override
    public String getSystemId() {
        return systemId;
    }

    @Override
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    @Override
    public String getBaseURI() {
        return baseSystemId;
    }

    @Override
    public void setBaseURI(String baseURI) {
        baseSystemId = baseURI;
    }

    @Override
    public boolean getCertifiedText() {
        return certifiedText;
    }

    @Override
    public void setCertifiedText(boolean certifiedText) {
        this.certifiedText = certifiedText;
    }

}
