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
package fr.xelians.sipg.service.sedajson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SchemaValidatorsConfig;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import com.networknt.schema.resource.DisallowSchemaLoader;
import fr.xelians.sipg.utils.SipException;
import fr.xelians.sipg.utils.SipUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

/**
 * La classe SedaJsonSchemaValidator valide la conformité d'un manifeste au schéma JSON embarqué du
 * standard SEDA JSON : structure, clés autorisées, types et longueurs maximales. Contrairement à la
 * validation XSD qui opère en streaming, la validation par schéma JSON matérialise le manifeste en
 * mémoire ; les appelants doivent donc contrôler au préalable la taille du manifeste. Note. La
 * classe est thread-safe.
 *
 * @author Emmanuel Deviller
 */
final class SedaJsonSchemaValidator {

  private static final int MAX_MESSAGES = 10;

  // Le parsing du manifeste est durci : profondeur, longueurs des chaînes, des clés et des
  // nombres bornées, clés dupliquées rejetées
  private static final JsonMapper MAPPER =
      JsonMapper.builder(
              JsonFactory.builder()
                  .streamReadConstraints(
                      StreamReadConstraints.builder()
                          .maxNestingDepth(SedaJsonParser.MAX_NESTING_DEPTH)
                          .maxStringLength(SedaJsonParser.MAX_STRING_LENGTH)
                          .maxNameLength(SedaJsonParser.MAX_NAME_LENGTH)
                          .maxNumberLength(SedaJsonParser.MAX_NUMBER_LENGTH)
                          .build())
                  .build())
          .enable(StreamReadFeature.STRICT_DUPLICATE_DETECTION)
          .build();

  private static final SedaJsonSchemaValidator V10_INSTANCE =
      new SedaJsonSchemaValidator(SedaJsonVersion.V1_0);

  private final SedaJsonVersion version;
  private final JsonSchema schema;

  private SedaJsonSchemaValidator(SedaJsonVersion version) {
    this.version = version;
    try (InputStream is = SipUtils.resourceAsStream(version.getSchema())) {
      JsonNode schemaNode = MAPPER.readTree(is);
      JsonSchemaFactory factory =
          JsonSchemaFactory.getInstance(
              SpecVersion.VersionFlag.V202012,
              builder ->
                  builder.schemaLoaders(
                      loaders -> loaders.add(DisallowSchemaLoader.getInstance())));
      SchemaValidatorsConfig config =
          SchemaValidatorsConfig.builder().formatAssertionsEnabled(true).build();
      this.schema = factory.getSchema(schemaNode, config);
      // Force la résolution de toutes les $refs à l'initialisation
      this.schema.initializeValidators();
    } catch (IOException ex) {
      throw new SipException(
          String.format("Failed to initialize SEDA JSON schema '%s'", version.getSchema()), ex);
    }
  }

  /**
   * Retourne l'instance du validateur de la version spécifiée.
   *
   * @param version la version du standard SEDA JSON
   * @return l'instance du validateur
   */
  static SedaJsonSchemaValidator getInstance(SedaJsonVersion version) {
    return switch (version) {
      case V1_0 -> V10_INSTANCE;
    };
  }

  /**
   * Valide le manifeste selon le schéma JSON embarqué du standard SEDA JSON.
   *
   * @param manifest le manifeste à valider
   */
  void validate(JsonNode manifest) {
    if (manifest == null || !manifest.isObject()) {
      throw new SipException("Manifest must be a json object");
    }

    JsonNode versionNode = manifest.path(SedaJsonKeys.SEDA_JSON_VERSION);
    if (!versionNode.isTextual()
        || SedaJsonVersion.ofVersion(versionNode.asText()) != this.version) {
      throw new SipException(
          String.format(
              "SEDA JSON version '%s' is not supported by this validator", versionNode.asText()));
    }

    Set<ValidationMessage> messages = schema.validate(manifest);
    if (!messages.isEmpty()) {
      throw new SipException(
          String.format(
              "Manifest does not conform to the SEDA JSON %s schema: %s",
              version.getVersion(), formatMessages(messages)));
    }
  }

  /**
   * Valide le flux du manifeste selon le schéma JSON embarqué du standard SEDA JSON. Le flux est
   * matérialisé en mémoire : l'appelant doit contrôler au préalable la taille du manifeste.
   *
   * @param is le flux du manifeste à valider
   */
  void validate(InputStream is) {
    JsonNode manifest;
    try {
      manifest = MAPPER.readTree(is);
    } catch (JacksonException ex) {
      throw new SipException("Manifest is not a valid json document", ex);
    } catch (IOException ex) {
      throw new SipException("Unable to read manifest", ex);
    }
    validate(manifest);
  }

  private static String formatMessages(Set<ValidationMessage> messages) {
    return messages.stream()
        .limit(MAX_MESSAGES)
        .map(ValidationMessage::getMessage)
        .reduce((a, b) -> a + "; " + b)
        .orElse("");
  }
}
