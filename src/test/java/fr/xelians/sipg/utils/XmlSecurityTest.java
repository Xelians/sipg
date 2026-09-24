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

import static org.junit.jupiter.api.Assertions.*;

import fr.xelians.sipg.SipFactory;
import fr.xelians.sipg.TestInit;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * The XML security test. Each entry point receives a document declaring an external entity that
 * targets a secret file: a vulnerable parser would resolve it instead of rejecting the document.
 *
 * @author Emmanuel Deviller
 */
@ExtendWith(TestInit.class)
class XmlSecurityTest {

  // Accepts any root element with a text content
  private static final String ANY_RNG =
      """
      <grammar xmlns="http://relaxng.org/ns/structure/1.0">
        <start><element><anyName/><text/></element></start>
      </grammar>
      """;

  /** Test a stream source is read by a hardened parser, other sources are kept. */
  @Test
  void testHardenSource() {
    Source source = XmlSecurity.harden(new StreamSource(new StringReader("<Ext/>")));
    assertInstanceOf(SAXSource.class, source);
    assertNotNull(((SAXSource) source).getXMLReader());

    DOMSource domSource = new DOMSource();
    assertSame(domSource, XmlSecurity.harden(domSource));
  }

  /** Test RNG validation with external entity fail. */
  @Test
  void testRngValidationExternalEntityFail() throws Exception {
    Path xmlPath = Paths.get(TestInit.TEST_RESULTS + "xxe_rng.xml");
    Files.writeString(xmlPath, SipFactory.createExternalEntityXml("Ext"));
    Validator validator = Validators.getRngValidator(new StringReader(ANY_RNG));

    assertThrows(SipException.class, () -> Validators.validate(xmlPath, validator));
  }

  /** Test RNG schema with external entity fail. */
  @Test
  void testRngSchemaExternalEntityFail() throws Exception {
    String rng =
        """
        <!DOCTYPE grammar [<!ENTITY e SYSTEM "%s">]>
        <grammar xmlns="http://relaxng.org/ns/structure/1.0">
          <start><element name="Ext"><value>&e;</value></element></start>
        </grammar>
        """
            .formatted(SipFactory.createExternalEntitySecret().toUri());

    assertThrows(SipException.class, () -> Validators.getRngValidator(new StringReader(rng)));
  }

  /** Test XML formatting with external entity fail. */
  @Test
  void testFormatXmlExternalEntityFail() throws Exception {
    byte[] xml = SipFactory.createExternalEntityXml("Ext").getBytes(StandardCharsets.UTF_8);
    ByteArrayOutputStream os = new ByteArrayOutputStream();

    assertThrows(
        SipException.class, () -> SipUtils.formatXml(new ByteArrayInputStream(xml), os, 2));
  }
}
