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
 * La classe Sedav2Step représente les étapes du processus de validation en SEDA v2.
 *
 * @author Emmanuel Deviller
 */
public enum Sedav2Step {
  /** Start sedav 2 step. */
  START,
  /** Archive exist sedav 2 step. */
  ARCHIVE_EXIST,
  /** Archive readable sedav 2 step. */
  ARCHIVE_READABLE,
  /** Archive unzip sedav 2 step. */
  ARCHIVE_UNZIP,
  /** Manifest exist sedav 2 step. */
  MANIFEST_EXIST,
  /** Manifest seda sedav 2 step. */
  MANIFEST_SEDA,
  /** Manifest validator sedav 2 step. */
  MANIFEST_VALIDATOR,
  /** Manifest parse sedav 2 step. */
  MANIFEST_PARSE,
  /** Binary exist sedav 2 step. */
  BINARY_EXIST,
  /** Binary folder sedav 2 step. */
  BINARY_FOLDER,
  /** Binary size sedav 2 step. */
  BINARY_SIZE,
  /** Binary digest sedav 2 step. */
  BINARY_DIGEST,
  /** Complete sedav 2 step. */
  COMPLETE
}
