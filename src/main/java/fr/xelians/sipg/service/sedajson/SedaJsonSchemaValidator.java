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

import com.networknt.schema.Error;
import com.networknt.schema.Schema;
import com.networknt.schema.SchemaException;
import com.networknt.schema.SchemaRegistry;
import com.networknt.schema.SchemaRegistryConfig;
import com.networknt.schema.SpecificationVersion;
import com.networknt.schema.path.PathType;
import fr.xelians.sipg.utils.SipException;
import fr.xelians.sipg.utils.SipUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import tools.jackson.core.JacksonException;
import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.StreamReadFeature;
import tools.jackson.core.exc.JacksonIOException;
import tools.jackson.core.json.JsonFactory;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

/**
 * La classe SedaJsonSchemaValidator valide la conformité d'un manifeste au schéma JSON embarqué du
 * standard SEDA JSON : structure, clés autorisées, types et longueurs maximales. Contrairement à la
 * validation XSD qui opère en streaming, la validation par schéma JSON matérialise le manifeste en
 * mémoire ; les appelants doivent donc contrôler au préalable la taille du manifeste. Note. La
 * classe est thread-safe.
 *
 * <p>Le validateur de schéma JSON repose sur Jackson 3 (tools.jackson), alors que le reste de la
 * bibliothèque utilise Jackson 2 (com.fasterxml) : il ne lit donc que des manifestes sérialisés,
 * les manifestes générés étant validés sur les octets qui sont livrés.
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
                  .enable(StreamReadFeature.STRICT_DUPLICATE_DETECTION)
                  .build())
          .build();

  private static final SedaJsonSchemaValidator V20_INSTANCE =
      new SedaJsonSchemaValidator(SedaJsonVersion.V2_0);

  private final SedaJsonVersion version;
  private final Schema schema;

  private SedaJsonSchemaValidator(SedaJsonVersion version) {
    this.version = version;
    try (InputStream is = SipUtils.resourceAsStream(version.getSchema())) {
      SchemaRegistryConfig config =
          SchemaRegistryConfig.builder()
              .formatAssertionsEnabled(true)
              // Les messages localisent la valeur fautive sous la forme '$.ArchiveUnits[0]'
              .pathType(PathType.LEGACY)
              .build();
      SchemaRegistry registry =
          SchemaRegistry.withDefaultDialect(
              SpecificationVersion.DRAFT_2020_12,
              builder ->
                  builder
                      .schemaRegistryConfig(config)
                      .schemaLoader(loader -> loader.allow(iri -> false)));
      this.schema = registry.getSchema(MAPPER.readTree(is));
      // Force la résolution de toutes les $refs à l'initialisation
      this.schema.initializeValidators();
    } catch (IOException | JacksonException | SchemaException ex) {
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
      case V2_0 -> V20_INSTANCE;
    };
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
    } catch (JacksonIOException ex) {
      throw new SipException("Unable to read manifest", ex);
    } catch (JacksonException ex) {
      throw new SipException("Manifest is not a valid json document", ex);
    }

    if (!manifest.isObject()) {
      throw new SipException("Manifest must be a json object");
    }

    JsonNode versionNode = manifest.path(SedaJsonKeys.SEDA_JSON_VERSION);
    if (!versionNode.isString()
        || SedaJsonVersion.ofVersion(versionNode.asString()) != this.version) {
      throw new SipException(
          String.format(
              "SEDA JSON version '%s' is not supported by this validator",
              versionNode.asString("")));
    }

    List<Error> errors = schema.validate(manifest);
    if (!errors.isEmpty()) {
      throw new SipException(
          String.format(
              "Manifest does not conform to the SEDA JSON %s schema: %s",
              version.getVersion(), formatErrors(errors)));
    }
  }

  // Un message est la localisation de la valeur fautive suivie de la violation : le message seul
  // ne porte pas la localisation
  private static String formatErrors(List<Error> errors) {
    return errors.stream()
        .limit(MAX_MESSAGES)
        .map(Error::toString)
        .collect(Collectors.joining("; "));
  }
}
