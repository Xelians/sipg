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

import fr.xelians.sipg.service.sedav2.Sedav2ConfigBuilder;

/**
 * La classe Fntcv4ConfigBuilder facilite la création d'un objet Fntcv4Config en suivant le principe de conception du
 * pattern builder.
 *
 * @author Emmanuel Deviller
 * @see Fntcv4Config
 */
public class Fntcv4ConfigBuilder {

    private boolean validate = true;
    private boolean format = false;
    private int indent = 3;
    private int thread = 0;
    private boolean strict = true;
    private boolean checkBinary = true;
    private boolean checkSize = true;
    private boolean checkDigest = true;
    private boolean useMemory = false;

    private Fntcv4ConfigBuilder() {
    }

    /**
     * Instancie le builder.
     *
     * @return le builder
     */
    public static Fntcv4ConfigBuilder builder() {
        return new Fntcv4ConfigBuilder();
    }

    /**
     * Spécifie si une validation doit être réalisée lors de la conversion. True par défaut.
     *
     * @param validate si une validation doit être réalisée
     * @return le builder
     */
    public Fntcv4ConfigBuilder validate(boolean validate) {
        this.validate = validate;
        return this;
    }

    /**
     * Spécifie si le fichier de description doit être formaté (pretty-print). False par défaut.
     *
     * @param format si le fichier de description doit être formaté
     * @return le builder
     */
    public Fntcv4ConfigBuilder format(boolean format) {
        this.format = format;
        return this;
    }

    /**
     * Spécifie la valeur de l'indentation lors du formatage. 3 par défaut.
     *
     * @param indent la valeur de l'indentation
     * @return le builder
     */
    public Fntcv4ConfigBuilder indent(int indent) {
        this.indent = indent;
        return this;
    }

    /**
     * Spécifie le nombre de threads à utiliser lors de la conversion. Le nombre de cœurs du CPU par défaut.
     *
     * @param thread le nombre de threads
     * @return le builder
     */
    public Fntcv4ConfigBuilder thread(int thread) {
        this.thread = thread;
        return this;
    }

    /**
     * Spécifie si la conversion doit être stricte. False par défaut.
     *
     * @param strict si la conversion doit être stricte
     * @return le builder
     */
    public Fntcv4ConfigBuilder strict(boolean strict) {
        this.strict = strict;
        return this;
    }

    /**
     * Spécifie si la validation doit vérifier les objets binaires.
     *
     * @param checkBinary si la validation doit vérifier les objets binaires
     * @return le builder
     */
    public Fntcv4ConfigBuilder checkBinary(boolean checkBinary) {
        this.checkBinary = checkBinary;
        return this;
    }

    /**
     * Spécifie si la validation doit vérifier la taille des objets binaires.
     *
     * @param checkSize si la validation doit vérifier la taille des objets binaires
     * @return le builder
     */
    public Fntcv4ConfigBuilder checkSize(boolean checkSize) {
        this.checkSize = checkSize;
        return this;
    }

    /**
     * Spécifie si la validation doit vérifier l'empreinte des objets binaires.
     *
     * @param checkDigest si la validation doit vérifier l'empreinte des objets binaires
     * @return le builder
     */
    public Fntcv4ConfigBuilder checkDigest(boolean checkDigest) {
        this.checkDigest = checkDigest;
        return this;
    }

    /**
     * Spécifie si la génération du sip utilise la mémoire ou le disque. La génération en mémoire
     * peut s'avérer légèrement plus rapide, mais peut entrainer un dépassement de la mémoire
     * de la machine virtuelle. Il est fortement conseiller de s'assurer de connaitre la capacité
     * mémoire de la machine virtuelle et des sip à générer avant d'utiliser cette option.
     *
     * @param useMemory si la genération du sip doit se faire en mémoire
     * @return le builder
     */
    public Fntcv4ConfigBuilder useMemory(boolean useMemory) {
        this.useMemory = useMemory;
        return this;
    }

    /**
     * Instancie la classe Fntcv4Config selon les paramètres précédemment spécifiés dans le builder.
     *
     * @return la configuration FNTC v4
     */
    public Fntcv4Config build() {
        return new Fntcv4Config(validate, format, indent, thread, strict, checkBinary, checkSize, checkDigest, useMemory);
    }
}
