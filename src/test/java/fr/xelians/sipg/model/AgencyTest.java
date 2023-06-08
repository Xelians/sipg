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
package fr.xelians.sipg.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * The type Agency test.
 *
 * @author Emmanuel Deviller
 */
class AgencyTest {

  /**
   * Test get identifier not null.
   */
  @Test
  void testGetIdentifierNotNull() {
    assertThrows(NullPointerException.class, () -> new Agency(null, ""));
  }

  /**
   * Test of getIdentifier method, of class Agency.
   */
  @Test
  void testGetIdentifier() {
    String expResult = "identifier";
    Agency instance = new Agency(expResult, "");
    String result = instance.getIdentifier();
    assertEquals(expResult, result);
  }

  /**
   * Test of getName method, of class Agency.
   */
  @Test
  void testGetName() {
    String expResult = "name";
    Agency instance = new Agency("", expResult);
    String result = instance.getName();
    assertEquals(expResult, result);
  }

}
