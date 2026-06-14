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
package fr.xelians.sipg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import fr.xelians.sipg.TestInit;
import fr.xelians.sipg.utils.DroidUtils;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import uk.gov.nationalarchives.droid.core.interfaces.IdentificationResult;

/**
 * The DROID integration test.
 *
 * @author Emmanuel Deviller
 */
@ExtendWith(TestInit.class)
class DroidTest {

  /** Test small pdf droid. */
  @Test
  void testSmallPdfDroid() {
    Path path = Paths.get(TestInit.TEST_RESOURCES + "dummy.pdf");
    List<IdentificationResult> results = DroidUtils.matchBinarySignatures(path, "pdf");
    assertEquals(1, results.size());
    assertEquals("fmt/18", results.getFirst().getPuid());
  }

  /** Test medium pdf droid. */
  @Test
  void testMediumPdfDroid() {
    Path path = Paths.get(TestInit.TEST_RESOURCES + "citizenfour.pdf");
    List<IdentificationResult> results = DroidUtils.matchBinarySignatures(path, "pdf");
    assertEquals(1, results.size());
    assertEquals("fmt/19", results.getFirst().getPuid());
  }

  @Test
  void testPlainTextDroid() {
    Path path = Paths.get(TestInit.TEST_RESOURCES + "dummy.txt");
    List<IdentificationResult> results = DroidUtils.matchBinarySignatures(path, "txt");
    assertEquals("x-fmt/111", results.getFirst().getPuid());
  }

  @Test
  void testJsonDroid() {
    Path path = Paths.get(TestInit.TEST_RESOURCES + "minisip.json");
    List<IdentificationResult> results = DroidUtils.matchBinarySignatures(path, "json");
    assertEquals("fmt/817", results.getFirst().getPuid());
  }

  /** Test loop droid. */
  @Test
  void testLoopDroid() {
    Path path = Paths.get(TestInit.TEST_RESOURCES + "citizenfour.pdf");

    for (int i = 0; i < 1000; i++) {
      List<IdentificationResult> results = DroidUtils.matchBinarySignatures(path, "pdf");
      assertEquals("fmt/19", results.getFirst().getPuid());
    }
  }
}
