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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.apache.commons.lang3.Validate;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/**
 * La classe Validators offre des méthodes pour obtenir des validateurs RNG et valider des documents
 * XML.
 *
 * @author Emmanuel Deviller
 */
public class Validators {

  /** Forbid class instantiation for static class */
  private Validators() {}

  /**
   * Fournit un validateur RNG. Note. Les objets {@link Validator} ne sont pas thread-safe.
   *
   * @param rngPath le path du fichier RNG
   * @return le validateur RNG
   */
  public static Validator getRngValidator(Path rngPath) {
    Validate.notNull(rngPath, SipUtils.NOT_NULL, "rngPath");

    try (BufferedReader rngReader = Files.newBufferedReader(rngPath, StandardCharsets.UTF_8)) {
      return getRngValidator(rngReader);
    } catch (IOException ex) {
      throw new SipException("Unable to create RNG validator for " + rngPath, ex);
    }
  }

  /**
   * Fournit un validateur RNG. Note. Les objets {@link Validator} ne sont pas thread-safe.
   *
   * @param rngReader le reader RNG
   * @return le validateur RNG
   */
  public static Validator getRngValidator(Reader rngReader) {
    Validate.notNull(rngReader, SipUtils.NOT_NULL, "rngReader");
    return getRngSchema(new StreamSource(rngReader)).newValidator();
  }

  private static Schema getRngSchema(StreamSource source) {
    try {
      // Initialize RNG validator through JAXP. SchemaFactory is not tread safe , so we create a new
      // one for each RNG schema. XXE mitigation is not supported
      System.setProperty(
          SchemaFactory.class.getName() + ":" + XMLConstants.RELAXNG_NS_URI,
          "com.thaiopensource.relaxng.jaxp.XMLSyntaxSchemaFactory");
      SchemaFactory rngSchemaFactory = SchemaFactory.newInstance(XMLConstants.RELAXNG_NS_URI);
      rngSchemaFactory.setProperty(
          "http://relaxng.org/properties/datatype-library-factory",
          new org.relaxng.datatype.helpers.DatatypeLibraryLoader());
      return rngSchemaFactory.newSchema(source);
    } catch (SAXNotRecognizedException | SAXNotSupportedException ex) {
      throw new SipException("Unable to initialize RNG Factory", ex);
    } catch (SAXException ex) {
      throw new SipException("Unable to create RNG Validator", ex);
    }
  }

  /**
   * Valide le fichier XML de description de l'archive selon le schéma défini par le Validator.
   *
   * <p>Note. L'objet {@link Validator} n'est pas thread-safe, il est de la responsabilité de
   * l'application appelante de s'assurer que l'objet Validator n'est utilisé à tout moment que par
   * une seule et même thread.
   *
   * @param path la source XML à valider
   * @param validator le validateur RNG
   */
  public static void validate(Path path, Validator validator) {
    Validate.notNull(path, SipUtils.NOT_NULL, "source");
    Validate.notNull(validator, SipUtils.NOT_NULL, "validator");

    try (InputStream is = Files.newInputStream(path)) {
      validate(new StreamSource(is), validator);
    } catch (IOException ex) {
      throw new SipException("Unable to validate " + path + " with validator", ex);
    }
  }

  /**
   * Valide le fichier XML de description de l'archive selon le schéma défini par le Validator.
   *
   * <p>Note. L'objet {@link Validator} n'est pas thread-safe, il est de la responsabilité de
   * l'application appelante de s'assurer que l'objet Validator n'est utilisé à tout moment que par
   * une seule et même thread.
   *
   * @param source la source XML à valider
   * @param validator le validateur RNG
   */
  public static void validate(Source source, Validator validator) {
    Validate.notNull(source, SipUtils.NOT_NULL, "source");
    Validate.notNull(validator, SipUtils.NOT_NULL, "validator");

    try {
      validator.validate(source);
    } catch (IOException | SAXException ex) {
      throw new SipException("Unable to validate " + source + " with validator", ex);
    }
  }
}
