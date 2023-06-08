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

import java.util.List;

/**
 * L'interface ArchiveUnitContainer contractualise la gestion d'une liste d'unités d'archives.
 *
 * @author Emmanuel Deviller
 */
public interface ArchiveUnitContainer {

    /**
     * Ajoute une unité d'archive à la liste des unités d'archives.
     *
     * @param archiveUnit l'unité d'archive à ajouter
     */
    void addArchiveUnit(ArchiveUnit archiveUnit);

    /**
     * Supprime une unité d'archive de la liste des unités d'archives.
     *
     * @param archiveUnit l'unité d'archive à supprimer
     * @return true si la suppression a été réalisée avec succès, sinon false
     */
    boolean removeArchiveUnit(ArchiveUnit archiveUnit);

    /**
     * Fournit la liste des unités d'archives. Pour éviter d'exposer les données internes, il est conseillé de réaliser
     * une copie défensive de la liste.
     *
     * @return la liste des unités d'archives
     */
    List<ArchiveUnit> getArchiveUnits();

}
