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

/**
 * La classe Fntcv4Step représente les étapes du processus de validation en FNTC v4.
 *
 * @author Emmanuel Deviller
 */
public enum Fntcv4Step {
    /**
     * Start FNTC v4 step.
     */
    START,
    /**
     * Archive exist FNTC v4 step.
     */
    ARCHIVE_EXIST,
    /**
     * Archive readable FNTC v4 step.
     */
    ARCHIVE_READABLE,
    /**
     * Archive unzip FNTC v4 step.
     */
    ARCHIVE_UNZIP,
    /**
     * Manifest exist FNTC v4 step.
     */
    MANIFEST_EXIST,
    /**
     * Manifest fntc FNTC v4 step.
     */
    MANIFEST_FNTC,
    /**
     * Manifest validator FNTC v4 step.
     */
    MANIFEST_VALIDATOR,
    /**
     * Manifest parse fntc v4 step.
     */
    MANIFEST_PARSE,
    /**
     * Binary exist FNTC v4 step.
     */
    BINARY_EXIST,
    /**
     * Binary folder FNTC v4 step.
     */
    BINARY_FOLDER,
    /**
     * Binary size FNTC v4 step.
     */
    BINARY_SIZE,
    /**
     * Binary digest FNTC v4 step.
     */
    BINARY_DIGEST,
    /**
     * Complete FNTC v4 step.
     */
    COMPLETE
}
