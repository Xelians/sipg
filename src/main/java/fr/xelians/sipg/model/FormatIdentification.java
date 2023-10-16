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

public class FormatIdentification {

    /**
     * L'identifiant du format de l'objet binaire. Il est fortement conseillé d'identifier le format de l'objet binaire
     * selon le référentiel Pronom distribué par The British National Archives.
     */
    protected String formatId;

    /**
     * Le nom du format de l'objet binaire. Il est fortement conseillé de nommer le format de l'objet binaire selon le
     * référentiel Pronom distribué par The British National Archives.
     */
    protected String formatName;

    /**
     * Le type MIME (Multipurpose Internet Mail Extensions) de l'objet binaire.
     */
    protected String mimeType;

    /**
     * Instancie la classe.
     */
    public FormatIdentification() {
    }

    /**
     * Instancie la classe.
     *
     * @param formatId   L'id du format
     * @param formatName Le nom du format
     * @param mimeType   Le type mime
     */
    public FormatIdentification(String formatId, String formatName, String mimeType) {
        this.formatId = formatId;
        this.formatName = formatName;
        this.mimeType = mimeType;
    }

    /**
     * Indique le type MIME (Multipurpose Internet Mail Extensions) de l'objet binaire.
     *
     * @return le type MIME
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Spécifie le type MIME (Multipurpose Internet Mail Extensions) de l'objet binaire.
     *
     * @param mimeType le type MIME
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * Indique l'identifiant du format de l'objet binaire. Il est fortement conseillé d'identifier le format de l'objet
     * binaire selon le référentiel Pronom édité par The British National Archives.
     *
     * @return l 'identifiant du format de l'objet binaire
     */
    public String getFormatId() {
        return formatId;
    }

    /**
     * Spécifie l'identifiant du format de l'objet binaire. Il est fortement conseillé d'identifier le format de l'objet
     * binaire selon le référentiel Pronom édité par The British National Archives.
     *
     * @param formatId l'identifiant du format de l'objet binaire
     */
    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    /**
     * Indique le nom du format de l'objet binaire. Il est fortement conseillé de nommer le format de l'objet binaire
     * selon le référentiel Pronom édité par The British National Archives.
     *
     * @return le nom du format de l'objet binaire
     */
    public String getFormatName() {
        return formatName;
    }

    /**
     * Spécifie le nom du format de l'objet binaire. Il est fortement conseillé de nommer le format de l'objet binaire
     * selon le référentiel Pronom édite par The British National Archives.
     *
     * @param formatName le nom du format de l'objet binaire
     */
    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

}
