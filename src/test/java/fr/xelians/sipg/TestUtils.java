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
package fr.xelians.sipg;

import fr.xelians.sipg.utils.SipException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Test utils.
 *
 * @author Emmanuel Deviller
 */
public class TestUtils {

  /**
   * The constant TEST.
   */
  public static final String TEST = "**************** Testing ";
  /**
   * The constant FAIL.
   */
  public static final String FAIL = "Fail to complete ";
  private static final Logger LOGGER = LoggerFactory.getLogger(TestUtils.class);

  private static final byte[] img100k = loadImage("100k.jpg") ;

  private TestUtils() {
  }

  /**
   * Gets method.
   *
   * @param testInfo the test info
   * @return the method
   */
  public static String getMethod(TestInfo testInfo) {
    return testInfo.getTestMethod().get().getName();
  }

  /**
   * Create pdf.
   *
   * @param message the message
   * @param path    the path
   */
  public static void createPdf(String message, Path path) {
    createPdf(message, path, false);
  }

  public static void createPdfWithImage(String message, Path path) {
    createPdf(message, path, true);
  }

  private static void createPdf(String message, Path path, boolean withImage) {

    try (PDDocument doc = new PDDocument()) {
      PDPage page = new PDPage();
      doc.addPage(page);
      PDFont font = PDType1Font.HELVETICA_BOLD;

      try (PDPageContentStream content = new PDPageContentStream(doc, page)) {
        content.beginText();
        content.setFont(font, 24);
        content.newLineAtOffset(100, 700);
        content.showText(message);
        content.endText();
        if (withImage) {
          PDImageXObject pdfImage = PDImageXObject.createFromByteArray(doc, img100k, "image");
          float w = page.getMediaBox().getWidth() - 40;
          float h = pdfImage.getHeight() * w / pdfImage.getWidth();
          content.drawImage(pdfImage, 20, 20, w, h);
        }
      }

      try (OutputStream os = Files.newOutputStream(path)) {
        doc.save(os);
      }
    } catch (IOException ex) {
      String msg = String.format("Unable to create PDF %s", path);
      LOGGER.warn(msg, ex);
      throw new SipException(msg, ex);
    }
  }

  /**
   * Extract text from pdf string.
   *
   * @param path the path
   * @return the string
   */
  public static String extractTextFromPDF(Path path) {

    try (InputStream is = Files.newInputStream(path);
        PDDocument document = PDDocument.load(is)) {
      return new PDFTextStripper().getText(document);
    } catch (IOException ex) {
      String msg = String.format("Unable to extract text from PDF %s", path);
      LOGGER.warn(msg, ex);
      throw new SipException(msg, ex);
    }
  }

  /**
   * Load image byte [ ].
   *
   * @param image the image
   * @return the byte [ ]
   */
  public static byte[] loadImage(String image) {
    try (InputStream is = ClassLoader.getSystemResourceAsStream(image)) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        IOUtils.copy(is, baos);
        return baos.toByteArray();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
  }

}
