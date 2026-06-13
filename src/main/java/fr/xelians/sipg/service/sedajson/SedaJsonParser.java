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

import static fr.xelians.sipg.service.sedajson.SedaJsonKeys.*;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.core.StreamReadFeature;
import fr.xelians.sipg.utils.SipException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * La classe SedaJsonParser réalise un parsing en streaming et en une seule passe du manifeste SEDA
 * JSON. Le parser vérifie l'ordre des clés imposé par le standard (au niveau de la racine et des
 * unités d'archives), rejette les clés inconnues hors de l'objet Content et extrait les objets
 * binaires nécessaires à la validation du paquet zip. Le parser est durci pour traiter des contenus
 * non sûrs : profondeur, longueurs des chaînes, des clés et des nombres bornées, clés dupliquées
 * rejetées. C'est le pendant JSON du parser SAX utilisé par le standard SEDA XML.
 *
 * @author Emmanuel Deviller
 */
final class SedaJsonParser {

  static final int MAX_STRING_LENGTH = 200_000;
  static final int MAX_NAME_LENGTH = 1024;
  static final int MAX_NUMBER_LENGTH = 100;
  static final int MAX_NESTING_DEPTH = 200;
  static final int MAX_UNIT_DEPTH = 50;

  private static final String ROOT_CONTEXT = "root";

  private static final JsonFactory FACTORY =
      JsonFactory.builder()
          .streamReadConstraints(
              StreamReadConstraints.builder()
                  .maxNestingDepth(MAX_NESTING_DEPTH)
                  .maxStringLength(MAX_STRING_LENGTH)
                  .maxNameLength(MAX_NAME_LENGTH)
                  .maxNumberLength(MAX_NUMBER_LENGTH)
                  .build())
          .enable(StreamReadFeature.STRICT_DUPLICATE_DETECTION)
          .build();

  private final ArrayList<SedaJsonBinaryObject> binaryObjects = new ArrayList<>();

  private SedaJsonParser() {}

  /**
   * Parse le manifeste SEDA JSON en streaming et retourne les objets binaires extraits.
   *
   * @param is le flux du manifeste à parser
   * @return la liste des objets binaires du manifeste
   * @throws IOException génère une exception d'entrée-sortie
   */
  static ArrayList<SedaJsonBinaryObject> parse(InputStream is) throws IOException {
    SedaJsonParser parser = new SedaJsonParser();
    try (JsonParser jp = FACTORY.createParser(is)) {
      parser.readManifest(jp);
    }
    return parser.binaryObjects;
  }

  private void readManifest(JsonParser jp) throws IOException {
    if (jp.nextToken() != JsonToken.START_OBJECT) {
      throw parseException(ROOT_CONTEXT, "manifest must be a json object");
    }

    int lastRank = 0;
    while (jp.nextToken() != JsonToken.END_OBJECT) {
      String name = jp.currentName();
      jp.nextToken();

      int rank = rootRankOf(name);
      if (rank <= lastRank) {
        throw orderException(ROOT_CONTEXT, name);
      }
      lastRank = rank;

      switch (name) {
        case SEDA_JSON_VERSION -> {
          if (jp.currentToken() != JsonToken.VALUE_STRING) {
            throw parseException(SEDA_JSON_VERSION, "must be a json string");
          }
          SedaJsonVersion.ofVersion(jp.getText());
        }
        case ARCHIVE_UNITS -> readArchiveUnits(jp, 1);
        default -> jp.skipChildren();
      }
    }
  }

  private static int rootRankOf(String name) {
    return switch (name) {
      case SEDA_JSON_VERSION -> 1;
      case MESSAGE_IDENTIFIER -> 2;
      case COMMENT -> 3;
      case ARCHIVAL_AGREEMENT -> 4;
      case ARCHIVE_UNITS -> 5;
      case MANAGEMENT_METADATA -> 6;
      case ARCHIVAL_AGENCY -> 7;
      case TRANSFERRING_AGENCY -> 8;
      default -> throw unknownKeyException(ROOT_CONTEXT, name);
    };
  }

  private void readArchiveUnits(JsonParser jp, int depth) throws IOException {
    expectArray(jp, ARCHIVE_UNITS);
    while (jp.nextToken() != JsonToken.END_ARRAY) {
      expectObject(jp, ARCHIVE_UNITS);
      readArchiveUnit(jp, depth);
    }
  }

  private void readArchiveUnit(JsonParser jp, int depth) throws IOException {
    if (depth > MAX_UNIT_DEPTH) {
      throw new SipException("ArchiveUnit nesting exceeds maximum depth of " + MAX_UNIT_DEPTH);
    }

    int lastRank = 0;
    while (jp.nextToken() != JsonToken.END_OBJECT) {
      String name = jp.currentName();
      jp.nextToken();

      int rank = unitRankOf(name);
      if (rank <= lastRank) {
        throw orderException("ArchiveUnit", name);
      }
      lastRank = rank;

      switch (name) {
        case BINARY_DATA_OBJECTS -> {
          expectArray(jp, name);
          while (jp.nextToken() != JsonToken.END_ARRAY) {
            expectObject(jp, name);
            readBinaryObject(jp);
          }
        }
        case ARCHIVE_UNITS -> readArchiveUnits(jp, depth + 1);
        default -> jp.skipChildren();
      }
    }
  }

  private static int unitRankOf(String name) {
    return switch (name) {
      case MANAGEMENT -> 1;
      case CONTENT -> 2;
      case BINARY_DATA_OBJECTS -> 3;
      case PHYSICAL_DATA_OBJECTS -> 4;
      case ARCHIVE_UNITS -> 5;
      default -> throw unknownKeyException("ArchiveUnit", name);
    };
  }

  private void readBinaryObject(JsonParser jp) throws IOException {
    String uri = null;
    long size = -1;
    String digest = null;
    String algorithm = null;

    while (jp.nextToken() != JsonToken.END_OBJECT) {
      String name = jp.currentName();
      jp.nextToken();

      switch (name) {
        case URI -> uri = readString(jp, name);
        case SIZE -> {
          if (jp.currentToken() != JsonToken.VALUE_NUMBER_INT) {
            throw parseException(name, "must be a json integer");
          }
          size = jp.getLongValue();
        }
        case MESSAGE_DIGEST -> {
          expectObject(jp, name);
          while (jp.nextToken() != JsonToken.END_OBJECT) {
            String key = jp.currentName();
            jp.nextToken();
            switch (key) {
              case ALGORITHM -> algorithm = readString(jp, key);
              case VALUE -> digest = readString(jp, key);
              default -> jp.skipChildren();
            }
          }
        }
        default -> jp.skipChildren();
      }
    }

    binaryObjects.add(new SedaJsonBinaryObject(uri, size, digest, algorithm));
  }

  private static String readString(JsonParser jp, String name) throws IOException {
    if (jp.currentToken() != JsonToken.VALUE_STRING) {
      throw parseException(name, "must be a json string");
    }
    return jp.getText();
  }

  private static void expectObject(JsonParser jp, String name) {
    if (jp.currentToken() != JsonToken.START_OBJECT) {
      throw parseException(name, "must be a json object");
    }
  }

  private static void expectArray(JsonParser jp, String name) {
    if (jp.currentToken() != JsonToken.START_ARRAY) {
      throw parseException(name, "must be a json array");
    }
  }

  private static SipException parseException(String name, String detail) {
    return new SipException(String.format("Failed to parse manifest: '%s' %s", name, detail));
  }

  private static SipException unknownKeyException(String context, String name) {
    return new SipException(
        String.format("Failed to parse manifest: unexpected key '%s' in %s", name, context));
  }

  private static SipException orderException(String context, String name) {
    return new SipException(
        String.format(
            "Failed to parse manifest: key '%s' in %s is misplaced"
                + " (the SEDA JSON standard mandates the key order)",
            name, context));
  }
}
