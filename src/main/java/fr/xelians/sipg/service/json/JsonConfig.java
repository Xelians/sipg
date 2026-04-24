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
package fr.xelians.sipg.service.json;

/**
 * La classe JsonConfig représente la configuration utilisée lors de la sérialisation d'une archive
 * en json. Les valeurs par défaut sont définies dans la classe JsonConfigBuilder.
 *
 * @author Emmanuel Deviller
 * @see JsonConfigBuilder
 */
public record JsonConfig(boolean format) {

  /** The constant DEFAULT. */
  public static final JsonConfig DEFAULT = JsonConfigBuilder.builder().build();
}
