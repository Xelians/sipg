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
package fr.xelians.sipg.service.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fr.xelians.sipg.model.ArchiveTransfer;
import fr.xelians.sipg.utils.SipException;
import fr.xelians.sipg.utils.SipUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.commons.lang3.Validate;

/**
 * La classe JsonService offre des API pour sérialiser ou désérialiser une archive en json. Le
 * processus de sérialisation peut être contrôlé par configuration. La structure du json dépend du
 * modèle sous-jacent de l'archive. Il peut changer dans une version future. Note. La classe
 * JsonService est thread safe.
 *
 * @author Emmanuel Deviller
 * @see ArchiveTransfer
 * @see JsonConfig
 */
public class JsonService {

  private static final JsonService INSTANCE = new JsonService();

  private final ObjectWriter objectWriter;
  private final ObjectWriter indentWriter;
  private final ObjectReader objectReader;

  private JsonService() {
    ObjectMapper writeMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
    writeMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    writeMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    writeMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
    objectWriter = writeMapper.writer();
    indentWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);

    ObjectMapper readMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
    readMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    readMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
    objectReader = readMapper.reader();
  }

  /**
   * Retourne l'instance singleton de la classe JsonService.
   *
   * @return l 'instance singleton
   */
  public static JsonService getInstance() {
    return INSTANCE;
  }

  /**
   * Sérialise l'archive dans un fichier json.
   *
   * @param archive l'archive à sérialiser
   * @param jsonPath le path du fichier json
   */
  public void write(ArchiveTransfer archive, Path jsonPath) {
    write(archive, jsonPath, JsonConfig.DEFAULT);
  }

  /**
   * Sérialise l'archive dans un fichier json. La configuration permet de contrôler le processus de
   * sérialisation.
   *
   * @param archive l'archive à sérialiser
   * @param jsonPath le path du fichier json
   * @param config la configuration utilisée lors de la sérialisation
   */
  public void write(ArchiveTransfer archive, Path jsonPath, JsonConfig config) {
    Validate.notNull(archive, SipUtils.NOT_NULL, "archive");
    Validate.notNull(jsonPath, SipUtils.NOT_NULL, "jsonPath");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    try {
      Files.deleteIfExists(jsonPath);
    } catch (IOException ex) {
      throw new SipException("Unable to delete file " + jsonPath, ex);
    }

    ObjectWriter writer = config.format() ? indentWriter : objectWriter;

    try (OutputStream os = Files.newOutputStream(jsonPath)) {
      writer.writeValue(os, archive);
    } catch (IOException ex) {
      throw new SipException("Unable to create json " + jsonPath, ex);
    }
  }

  /**
   * Sérialise l'archive en json dans une chaîne de caractère.
   *
   * @param archive l'archive à sérialiser
   * @return la chaîne de caractère en json de l'archive sérialisée
   */
  public String write(ArchiveTransfer archive) {
    return write(archive, JsonConfig.DEFAULT);
  }

  /**
   * Sérialise l'archive en json dans une chaîne de caractère.
   *
   * @param archive l'archive à sérialiser
   * @param config la configuration utilisée lors de la sérialisation
   * @return la chaîne de caractère en json de l'archive sérialisée
   */
  public String write(ArchiveTransfer archive, JsonConfig config) {
    Validate.notNull(archive, SipUtils.NOT_NULL, "archive");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    ObjectWriter writer = config.format() ? indentWriter : objectWriter;

    try {
      return writer.writeValueAsString(archive);
    } catch (JsonProcessingException ex) {
      throw new SipException("Unable to create json String", ex);
    }
  }

  /**
   * Désérialise la chaîne de caractère json en archive.
   *
   * @param json la chaîne de caractère json
   * @return l'archive désérialisée
   */
  public ArchiveTransfer read(String json) {
    return read(json, JsonConfig.DEFAULT);
  }

  /**
   * Désérialise la chaîne de caractère json en archive.
   *
   * @param json la chaîne de caractère json
   * @param config la configuration utilisée lors de la désérialisation
   * @return l'archive désérialisée
   */
  public ArchiveTransfer read(String json, JsonConfig config) {
    Validate.notNull(json, SipUtils.NOT_NULL, "jsonPath");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    try {
      return objectReader.readValue(json, ArchiveTransfer.class);
    } catch (IOException ex) {
      throw new SipException("Unable to create ArchiveTransfer from json " + json, ex);
    }
  }

  /**
   * Désérialise le fichier json spécifié par le path en archive.
   *
   * @param jsonPath le path du fichier json
   * @return l'archive désérialisée
   */
  public ArchiveTransfer read(Path jsonPath) {
    return read(jsonPath, JsonConfig.DEFAULT);
  }

  /**
   * Désérialise le fichier json spécifié par le path en archive.
   *
   * @param jsonPath le path du fichier json
   * @param config la configuration utilisée lors de la désérialisation
   * @return l'archive désérialisée
   */
  public ArchiveTransfer read(Path jsonPath, JsonConfig config) {
    Validate.notNull(jsonPath, SipUtils.NOT_NULL, "jsonPath");
    Validate.notNull(config, SipUtils.NOT_NULL, "config");

    try (InputStream is = Files.newInputStream(jsonPath)) {
      return objectReader.readValue(is, ArchiveTransfer.class);
    } catch (IOException ex) {
      throw new SipException("Unable to create ArchiveTransfer from json " + jsonPath, ex);
    }
  }
}
