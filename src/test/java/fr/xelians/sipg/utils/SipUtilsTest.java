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

import fr.xelians.sipg.TestInit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/** The SipUtils test. */
@ExtendWith(TestInit.class)
class SipUtilsTest {

  /** Test a plain file name is kept. */
  @Test
  void testSanitizeFileNameKeepsPlainName() {
    assertEquals("siteb.pdf", SipUtils.sanitizeFileName("siteb.pdf"));
    assertEquals("my-file_v1.2.pdf", SipUtils.sanitizeFileName("my-file_v1.2.pdf"));
  }

  /** Test consecutive dots are merged, as Vitam rejects a zip entry containing "..". */
  @Test
  void testSanitizeFileNameMergesConsecutiveDots() {
    assertEquals("siteb.pdf", SipUtils.sanitizeFileName("siteb..pdf"));
    assertEquals("siteb.pdf", SipUtils.sanitizeFileName("siteb...pdf"));
    assertEquals("a.b.c", SipUtils.sanitizeFileName("a..b....c"));
    assertEquals(".", SipUtils.sanitizeFileName(".."));
  }

  /** Test accents and special characters are still replaced. */
  @Test
  void testSanitizeFileNameReplacesAccentsAndSpecialCharacters() {
    assertEquals("ete_2024.pdf", SipUtils.sanitizeFileName("été 2024.pdf"));
    assertEquals("a_b_c.pdf", SipUtils.sanitizeFileName("a/b\\c.pdf"));
  }

  /** Test a null file name is rejected. */
  @Test
  void testSanitizeFileNameRejectsNull() {
    assertThrows(NullPointerException.class, () -> SipUtils.sanitizeFileName(null));
  }
}
