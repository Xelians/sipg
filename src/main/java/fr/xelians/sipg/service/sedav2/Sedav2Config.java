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

/**
 * La classe Sedav2Config représente la configuration utilisée lors de la conversion d'une archive au format SEDA v2.1.
 * Les valeurs par défaut sont définies dans la classe Sedav2ConfigBuilder.
 *
 * @author Emmanuel Deviller
 * @see Sedav2ConfigBuilder
 */
public class Sedav2Config {

    /**
     * The constant DEFAULT.
     */
    public static final Sedav2Config DEFAULT = Sedav2ConfigBuilder.builder().build();

    private final boolean validate;
    private final boolean format;
    private final int indent;
    private final int thread;
    private final boolean strict;
    private final boolean checkBinary;
    private final boolean checkSize;
    private final boolean checkDigest;

    /**
     * Instancie la classe.
     *
     * @param validate    spécifie si une validation doit être réalisée lors de la conversion
     * @param format      spécifie si le fichier de description doit être formaté (pretty-print)
     * @param indent      spécifie la valeur de l'indentation lors du formatage
     * @param thread      spécifie le nombre de threads à utiliser lors de la conversion
     * @param strict      spécifie si la conversion doit être stricte
     * @param checkBinary spécifie si la validation vérifie les objets binaires
     * @param checkSize   spécifie si la validation la taille des objets binaires
     * @param checkDigest spécifie si la validation vérifie l'empreinte des objets binaires
     */
    public Sedav2Config(boolean validate, boolean format, int indent, int thread, boolean strict, boolean checkBinary,
                        boolean checkSize, boolean checkDigest) {
        this.validate = validate;
        this.format = format;
        this.indent = indent;
        this.thread = thread;
        this.strict = strict;
        this.checkBinary = checkBinary;
        this.checkSize = checkSize;
        this.checkDigest = checkDigest;
    }

    /**
     * Indique si une validation doit être réalisée lors de la conversion.
     *
     * @return si une validation doit être réalisée
     */
    public boolean isValidate() {
        return validate;
    }

    /**
     * Indique si le fichier de description doit être formaté (pretty-print) lors de la conversion.
     *
     * @return si le fichier de description doit être formaté
     */
    public boolean isFormat() {
        return format;
    }

    /**
     * Indique la valeur de l'indentation lors du formatage.
     *
     * @return la valeur de l'indentation lors du formatage
     */
    public int getIndent() {
        return indent;
    }

    /**
     * Indique le nombre de threads à utiliser lors de la conversion.
     *
     * @return le nombre de threads
     */
    public int getThread() {
        return thread;
    }

    /**
     * Indique si la conversion doit être stricte.
     *
     * @return si la conversion doit être stricte
     */
    public boolean isStrict() {
        return strict;
    }

    /**
     * Indique si la validation vérifie les objets binaires.
     *
     * @return si la validation vérifie les objets binaires
     */
    public boolean checkBinary() {
        return checkBinary;
    }

    /**
     * Indique si la validation vérifie la taille des objets binaires.
     *
     * @return si la validation vérifie la taille des objets binaires
     */
    public boolean checkSize() {
        return checkSize;
    }

    /**
     * Indique si la validation vérifie l'empreinte des objets binaires.
     *
     * @return si la validation vérifie l'empreinte des objets binaires
     */
    public boolean checkDigest() {
        return checkDigest;
    }

    @Override
    public String toString() {
        return "Sedav2Config{" +
                "validate=" + validate +
                ", format=" + format +
                ", indent=" + indent +
                ", thread=" + thread +
                ", strict=" + strict +
                ", checkBinary=" + checkBinary +
                ", checkSize=" + checkSize +
                ", checkDigest=" + checkDigest +
                '}';
    }
}
