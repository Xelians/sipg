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
package fr.xelians.sipg.utils;

import java.io.InputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * La classe XmlSecurity centralise le durcissement XXE / SSRF (CWE-611, CWE-918) des fabriques de
 * parseurs JAXP.
 *
 * <p>Le rejet des déclarations DOCTYPE est la défense principale recommandée par l'OWASP : il
 * désactive aussi l'expansion des entités internes et externes. Les entités générales et paramètres
 * externes, le chargement des DTD externes et XInclude sont désactivés en défense en profondeur.
 * Regrouper les features à un seul endroit évite que les copies par parseur divergent.
 *
 * <p>Les fabriques par défaut ({@code *Factory.newInstance()}) résolvent les entités externes : une
 * fabrique brute ne doit jamais parser un XML fourni par l'appelant sans passer par {@code harden}.
 *
 * <p>Un unmarshaller JAXB, un validateur RNG ou un transformer qui reçoit une {@link StreamSource}
 * crée lui-même son parseur, qu'aucune fabrique ne permet de durcir : le XML fourni par l'appelant
 * doit alors lui être confié sous la forme d'une source SAX durcie ({@link #newSource(InputStream)}
 * ou {@link #harden(Source)}).
 *
 * @author Julien Cornille
 */
public final class XmlSecurity {

  /** La feature Xerces qui rejette les déclarations DOCTYPE. */
  public static final String DISALLOW_DOCTYPE =
      "http://apache.org/xml/features/disallow-doctype-decl";

  private static final String EXTERNAL_GENERAL_ENTITIES =
      "http://xml.org/sax/features/external-general-entities";
  private static final String EXTERNAL_PARAMETER_ENTITIES =
      "http://xml.org/sax/features/external-parameter-entities";
  private static final String LOAD_EXTERNAL_DTD =
      "http://apache.org/xml/features/nonvalidating/load-external-dtd";

  private XmlSecurity() {}

  /**
   * Durcit une fabrique de parseurs SAX et la retourne. La gestion des namespaces est laissée à
   * l'appelant.
   *
   * @param factory la fabrique à durcir
   * @return la fabrique durcie
   * @throws SAXException si une feature n'est pas supportée
   * @throws ParserConfigurationException si la fabrique ne peut pas être configurée
   */
  public static SAXParserFactory harden(SAXParserFactory factory)
      throws SAXException, ParserConfigurationException {
    factory.setFeature(DISALLOW_DOCTYPE, true);
    factory.setFeature(EXTERNAL_GENERAL_ENTITIES, false);
    factory.setFeature(EXTERNAL_PARAMETER_ENTITIES, false);
    factory.setFeature(LOAD_EXTERNAL_DTD, false);
    factory.setXIncludeAware(false);
    return factory;
  }

  /**
   * Durcit une fabrique de parseurs DOM et la retourne.
   *
   * @param factory la fabrique à durcir
   * @return la fabrique durcie
   * @throws ParserConfigurationException si une feature n'est pas supportée
   */
  public static DocumentBuilderFactory harden(DocumentBuilderFactory factory)
      throws ParserConfigurationException {
    factory.setFeature(DISALLOW_DOCTYPE, true);
    factory.setFeature(EXTERNAL_GENERAL_ENTITIES, false);
    factory.setFeature(EXTERNAL_PARAMETER_ENTITIES, false);
    factory.setFeature(LOAD_EXTERNAL_DTD, false);
    factory.setXIncludeAware(false);
    factory.setExpandEntityReferences(false);
    return factory;
  }

  /**
   * Durcit une fabrique de parseurs StAX et la retourne : aucune résolution d'entité externe, aucun
   * support des DTD (ce qui désactive aussi l'expansion des entités internes).
   *
   * @param factory la fabrique à durcir
   * @return la fabrique durcie
   */
  public static XMLInputFactory harden(XMLInputFactory factory) {
    factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
    factory.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
    return factory;
  }

  /**
   * Retourne une source SAX qui lit le flux XML avec un parseur SAX durci et sensible aux
   * namespaces.
   *
   * @param inputStream le flux XML
   * @return la source SAX durcie
   */
  public static SAXSource newSource(InputStream inputStream) {
    return newSource(new InputSource(inputStream));
  }

  /**
   * Durcit une source XML et la retourne. Une {@link StreamSource}, ou une {@link SAXSource} sans
   * {@code XMLReader}, est lue par un parseur SAX durci. Les autres sources (DOM, StAX, JAXB ou SAX
   * avec son propre {@code XMLReader}) ne sont pas parsées par leur consommateur et sont retournées
   * telles quelles.
   *
   * @param source la source à durcir
   * @return la source durcie
   */
  public static Source harden(Source source) {
    if (source instanceof StreamSource
        || (source instanceof SAXSource saxSource && saxSource.getXMLReader() == null)) {
      return newSource(SAXSource.sourceToInputSource(source));
    }
    return source;
  }

  private static SAXSource newSource(InputSource inputSource) {
    try {
      SAXParserFactory factory = harden(SAXParserFactory.newInstance());
      factory.setNamespaceAware(true);
      XMLReader reader = factory.newSAXParser().getXMLReader();
      // Fatal errors are thrown to the consumer instead of being printed by the parser as well; a
      // consumer may still set its own error handler
      reader.setErrorHandler(new DefaultHandler());
      return new SAXSource(reader, inputSource);
    } catch (SAXException | ParserConfigurationException ex) {
      throw new SipException("Unable to create a secure SAX parser", ex);
    }
  }

  /**
   * Durcit une fabrique de schémas en rejetant les déclarations DOCTYPE et la retourne.
   *
   * @param factory la fabrique à durcir
   * @return la fabrique durcie
   * @throws SAXException si la feature n'est pas supportée
   */
  public static SchemaFactory harden(SchemaFactory factory) throws SAXException {
    factory.setFeature(DISALLOW_DOCTYPE, true);
    return factory;
  }

  /**
   * Durcit un validateur en rejetant les déclarations DOCTYPE et le retourne.
   *
   * @param validator le validateur à durcir
   * @return le validateur durci
   * @throws SAXException si la feature n'est pas supportée
   */
  public static Validator harden(Validator validator) throws SAXException {
    validator.setFeature(DISALLOW_DOCTYPE, true);
    return validator;
  }
}
