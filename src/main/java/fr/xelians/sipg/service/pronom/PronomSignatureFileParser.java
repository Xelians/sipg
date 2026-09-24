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
package fr.xelians.sipg.service.pronom;

import fr.xelians.sipg.utils.SipException;
import fr.xelians.sipg.utils.SipUtils;
import fr.xelians.sipg.utils.XmlSecurity;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parseur en flux (StAX) d'un fichier de signatures PRONOM (DROID), namespace {@code
 * http://www.nationalarchives.gov.uk/pronom/SignatureFile}. Seule la {@code FileFormatCollection}
 * est lue ; l'{@code InternalSignatureCollection}, qui représente l'essentiel du fichier, est
 * ignorée.
 *
 * <p>Le parseur est volontairement tolérant sur les namespaces et les éléments inconnus : le
 * fichier provient soit des National Archives, soit d'un client qui re-sérialise la collection (Xam
 * écrit une collection de signatures vide et déclare le namespace comme un simple attribut). Il est
 * strict sur ce dont le référentiel a besoin : un {@code PUID} valide et un {@code Name} non vide
 * par format, aucun {@code PUID} ni {@code ID} en double.
 *
 * <p>{@code HasPriorityOverFileFormatID} référence l'{@code ID} numérique d'un autre format du même
 * fichier. Cet identifiant est positionnel et régénéré par chaque producteur : il est donc résolu
 * ici en PUID. Une référence numérique vers un format absent du fichier est supprimée, une valeur
 * non numérique est conservée comme un PUID.
 *
 * @author Julien Cornille
 */
final class PronomSignatureFileParser {

  private static final Logger LOGGER = LoggerFactory.getLogger(PronomSignatureFileParser.class);

  private static final String ROOT_ELEMENT = "FFSignatureFile";
  private static final String FILE_FORMAT_ELEMENT = "FileFormat";
  private static final String EXTENSION_ELEMENT = "Extension";
  private static final String PRIORITY_ELEMENT = "HasPriorityOverFileFormatID";

  private static final XMLInputFactory INPUT_FACTORY;

  static {
    INPUT_FACTORY = XmlSecurity.harden(XMLInputFactory.newInstance());
    INPUT_FACTORY.setProperty(XMLInputFactory.IS_COALESCING, Boolean.TRUE);
  }

  private PronomSignatureFileParser() {}

  static PronomSignatureFile parse(InputStream inputStream) {
    XMLStreamReader reader = null;
    try {
      reader = INPUT_FACTORY.createXMLStreamReader(inputStream);
      return doParse(reader);
    } catch (XMLStreamException e) {
      throw new SipException(
          "Failed to parse PRONOM signature file - not a well-formed XML document", e);
    } finally {
      if (reader != null) {
        close(reader);
      }
    }
  }

  private static PronomSignatureFile doParse(XMLStreamReader reader) throws XMLStreamException {
    // Root element
    if (reader.nextTag() != XMLStreamConstants.START_ELEMENT
        || !ROOT_ELEMENT.equals(reader.getLocalName())) {
      throw invalid(String.format("root element must be '%s'", ROOT_ELEMENT));
    }
    String version = attribute(reader, "Version");

    // File formats, keyed by numeric ID (may be absent) and by PUID
    Map<String, String> puidById = new HashMap<>();
    Map<String, RawFileFormat> formats = new LinkedHashMap<>();

    while (reader.hasNext()) {
      int event = reader.next();
      if (event == XMLStreamConstants.START_ELEMENT
          && FILE_FORMAT_ELEMENT.equals(reader.getLocalName())) {
        RawFileFormat format = readFileFormat(reader);
        if (formats.put(format.puid, format) != null) {
          throw invalid(String.format("file format PUID '%s' is declared twice", format.puid));
        }
        if (format.id != null && puidById.put(format.id, format.puid) != null) {
          throw invalid(String.format("file format ID '%s' is declared twice", format.id));
        }
      }
    }

    List<PronomFileFormat> fileFormats =
        formats.values().stream().map(f -> f.toPronomFileFormat(puidById)).toList();
    return new PronomSignatureFile(version, fileFormats);
  }

  // The reader is positioned on the FileFormat start element; on return it is positioned on the
  // matching end element.
  private static RawFileFormat readFileFormat(XMLStreamReader reader) throws XMLStreamException {
    RawFileFormat format = new RawFileFormat();
    format.id = attribute(reader, "ID");
    format.puid = attribute(reader, "PUID");
    format.name = attribute(reader, "Name");
    format.mimeType = attribute(reader, "MIMEType");
    format.version = attribute(reader, "Version");

    if (format.puid == null) {
      throw invalid(String.format("file format with ID '%s' has no PUID", format.id));
    }
    if (!PronomService.isValidPuid(format.puid)) {
      throw invalid(String.format("file format PUID '%s' is not a valid PUID", format.puid));
    }
    if (format.name == null) {
      throw invalid(String.format("file format '%s' has no Name", format.puid));
    }

    while (reader.hasNext()) {
      int event = reader.next();
      if (event == XMLStreamConstants.END_ELEMENT) {
        return format;
      }
      if (event == XMLStreamConstants.START_ELEMENT) {
        switch (reader.getLocalName()) {
          case EXTENSION_ELEMENT ->
              SipUtils.ifNotNull(
                  StringUtils.trimToNull(reader.getElementText()), format.extensions::add);
          case PRIORITY_ELEMENT ->
              SipUtils.ifNotNull(
                  StringUtils.trimToNull(reader.getElementText()), format.priorities::add);
          default -> skipElement(reader);
        }
      }
    }
    throw invalid(String.format("file format '%s' is not closed", format.puid));
  }

  // Skips the element the reader is positioned on, whatever its content
  private static void skipElement(XMLStreamReader reader) throws XMLStreamException {
    int depth = 1;
    while (depth > 0 && reader.hasNext()) {
      int event = reader.next();
      if (event == XMLStreamConstants.START_ELEMENT) {
        depth++;
      } else if (event == XMLStreamConstants.END_ELEMENT) {
        depth--;
      }
    }
  }

  // Attributes of a signature file are unqualified; a null namespace looks them up by local name
  // only, which also accepts a writer qualifying them. A blank value is read as absent.
  private static String attribute(XMLStreamReader reader, String name) {
    return StringUtils.trimToNull(reader.getAttributeValue(null, name));
  }

  private static void close(XMLStreamReader reader) {
    try {
      reader.close();
    } catch (XMLStreamException e) {
      LOGGER.warn("Failed to close signature file reader", e);
    }
  }

  private static SipException invalid(String message) {
    return new SipException("Failed to parse PRONOM signature file - " + message);
  }

  private static final class RawFileFormat {
    String id;
    String puid;
    String name;
    String mimeType;
    String version;
    final LinkedHashSet<String> extensions = new LinkedHashSet<>();
    final List<String> priorities = new ArrayList<>();

    PronomFileFormat toPronomFileFormat(Map<String, String> puidById) {
      LinkedHashSet<String> priorityPuids = new LinkedHashSet<>();
      for (String reference : priorities) {
        // A numeric reference is resolved to the PUID of the referenced format (dropped when it is
        // not in the file), any other value is already a PUID
        String referencedPuid =
            StringUtils.isNumeric(reference) ? puidById.get(reference) : reference;
        if (referencedPuid != null && !referencedPuid.equals(puid)) {
          priorityPuids.add(referencedPuid);
        }
      }
      return new PronomFileFormat(
          puid, name, mimeType, version, List.copyOf(extensions), List.copyOf(priorityPuids));
    }
  }
}
