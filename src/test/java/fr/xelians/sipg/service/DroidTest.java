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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.gov.nationalarchives.droid.core.interfaces.IdentificationResult;

/**
 * The DROID integration test.
 *
 * @author Emmanuel Deviller
 */
@ExtendWith(TestInit.class)
class DroidTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(DroidTest.class);

  /** Test small pdf droid. */
  @Test
  void testSmallPdfDroid() {
    Path path = Paths.get(TestInit.TEST_RESOURCES + "dummy.pdf");
    List<IdentificationResult> results = DroidUtils.matchBinarySignatures(path, "pdf");
    assertEquals(1, results.size());
    assertEquals("fmt/18", results.get(0).getPuid());
  }

  /**
   * Test medium pdf droid.
   *
   * @throws Exception the exception
   */
  @Test
  void testMediumPdfDroid() {
    Path path = Paths.get(TestInit.TEST_RESOURCES + "citizenfour.pdf");
    List<IdentificationResult> results = DroidUtils.matchBinarySignatures(path, "pdf");
    assertEquals(1, results.size());
    assertEquals("fmt/19", results.get(0).getPuid());
  }

  /**
   * Test loop droid.
   *
   * @throws Exception the exception
   */
  @Test
  void testLoopDroid() {
    Path path = Paths.get(TestInit.TEST_RESOURCES + "citizenfour.pdf");

    for (int i = 0; i < 1000; i++) {
      List<IdentificationResult> results = DroidUtils.matchBinarySignatures(path, "pdf");
      assertEquals("fmt/19", results.get(0).getPuid());
    }
  }
}
