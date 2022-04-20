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
package fr.xelians.sipg.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe abstraite Message représente le message dans le protocole d'échange  SEDA.
 *
 * <p>
 * Supporté en SEDA v2.1 .
 * </p>
 *
 * @author Emmanuel Deviller
 * @see ArchiveTransfer
 */
public abstract class Message {

    /**
     * La date du message.
     */
    protected LocalDateTime date;

    /**
     * L'identifiant du message.
     */
    protected String messageIdentifier;

    /**
     * Le commentaire sur le message.
     */
    protected String comment;

    /**
     * La signature du message.
     */
    protected String signature;

    /**
     * La liste des éléments signatures du message.
     */
    protected final List<Element> signatureElements = new ArrayList<>();

    /**
     * Indique la date du message.
     *
     * @return la date du message
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Spécifie la date du message.
     *
     * @param date la date du message
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Indique l'identifiant du message.
     *
     * @return l 'identifiant du message
     */
    public String getMessageIdentifier() {
        return messageIdentifier;
    }

    /**
     * Spécifie l'identifiant du message.
     *
     * @param messageIdentifier l'identifiant du message
     */
    public void setMessageIdentifier(String messageIdentifier) {
        this.messageIdentifier = messageIdentifier;
    }

    /**
     * Indique le commentaire sur le message.
     *
     * @return le commentaire
     */
    public String getComment() {
        return comment;
    }

    /**
     * Spécifie le commentaire sur le message.
     *
     * @param comment le commentaire
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Indique la signature du message.
     *
     * @return la signature du message
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Spécifie la signature du message.
     *
     * @param signature la signature du message
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * Ajoute une signature à la liste des signatures du message.
     *
     * @param name  le nom de la signature
     * @param value la valeur de la signature
     */
    public void addSignatureElement(String name, String value) {
        signatureElements.add(new Element(name, value));
    }

    /**
     * Ajoute une signature à la liste des signatures du message.
     *
     * @param element l'élément signature
     */
    public void addSignatureElement(Element element) {
        signatureElements.add(element);
    }

    /**
     * Supprime une signature de la liste des signatures du message.
     *
     * @param element l'élément signature
     * @return true si la suppression a été réalisée avec succès, sinon false
     */
    public boolean removeSignatureElement(Element element) {
        return signatureElements.remove(element);
    }

    /**
     * Fournit la liste des signatures du message.
     *
     * @return la liste des éléments signatures
     */
    public List<Element> getSignatureElements() {
        return new ArrayList<>(signatureElements);
    }
}
