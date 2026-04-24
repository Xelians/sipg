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

import fr.xelians.sipg.model.ArchiveDeliveryRequestReply;
import fr.xelians.sipg.model.ArchiveTransfer;
import jakarta.xml.bind.JAXBException;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import javax.xml.transform.Source;
import javax.xml.validation.Validator;

/**
 * L'interface SedaAdapter définit les opérations d'écriture, de validation et de
 * sérialisation/désérialisation d'archives conformes au standard SEDA. Chaque version du standard
 * (v2.1, v2.2, v2.3) fournit sa propre implémentation.
 *
 * @author Emmanuel Deviller
 * @see Sedav2Service
 */
public interface SedaAdapter {

  /**
   * Écrit l'archive de transfert dans un fichier ZIP au chemin spécifié.
   *
   * @param archive l'archive de transfert à écrire
   * @param validator le validateur XSD/RNG, peut être {@code null}
   * @param zipPath le chemin du fichier ZIP de destination
   * @param config la configuration SEDA
   */
  void write(ArchiveTransfer archive, Validator validator, Path zipPath, SedaConfig config);

  /**
   * Écrit la réponse de demande de communication dans un fichier ZIP au chemin spécifié.
   *
   * @param archive la réponse de demande de communication à écrire
   * @param validator le validateur XSD/RNG, peut être {@code null}
   * @param zipPath le chemin du fichier ZIP de destination
   * @param config la configuration SEDA
   */
  void write(
      ArchiveDeliveryRequestReply archive, Validator validator, Path zipPath, SedaConfig config);

  /**
   * Valide l'archive de transfert selon le standard SEDA.
   *
   * @param archive l'archive de transfert à valider
   * @param validator le validateur XSD/RNG, peut être {@code null}
   * @param config la configuration SEDA
   */
  void validate(ArchiveTransfer archive, Validator validator, SedaConfig config);

  /**
   * Valide une source XML selon le standard SEDA.
   *
   * @param source la source XML à valider
   * @param config la configuration SEDA
   */
  void validate(Source source, SedaConfig config);

  /**
   * Sérialise l'archive de transfert en un flux XML.
   *
   * @param archiveTransfer l'archive de transfert à sérialiser
   * @param config la configuration SEDA
   * @return le flux d'entrée contenant le XML généré
   */
  InputStream marshal(ArchiveTransfer archiveTransfer, SedaConfig config);

  /**
   * Désérialise un flux XML en un objet du type spécifié.
   *
   * @param <T> le type de l'objet à désérialiser
   * @param stream le flux d'entrée XML
   * @param clazz la classe cible de la désérialisation
   * @param config la configuration SEDA
   * @return l'objet désérialisé
   * @throws JAXBException en cas d'erreur de désérialisation JAXB
   */
  <T> T unmarshal(InputStream stream, Class<T> clazz, SedaConfig config) throws JAXBException;
}
