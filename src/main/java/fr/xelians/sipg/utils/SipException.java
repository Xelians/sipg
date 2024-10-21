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

/**
 * La classe SipException définit une exception spécifique à la librairie. Note. Il est fortement
 * déconseillé d'utiliser cette classe en dehors de la librairie.
 *
 * @author Emmanuel Deviller
 */
public class SipException extends RuntimeException {

  /** The Serial version uid. */
  static final long serialVersionUID = 6637885103769526334L;

  /**
   * Instancie une nouvelle exception avec un message.
   *
   * @param message le message
   */
  public SipException(String message) {
    super(message);
  }

  /**
   * Instancie une nouvelle exception avec une cause.
   *
   * @param cause la cause
   */
  public SipException(Throwable cause) {
    super(cause);
  }

  /**
   * Instancie une nouvelle exception avec un message et une cause.
   *
   * @param message le message
   * @param cause la cause
   */
  public SipException(String message, Throwable cause) {
    super(message, cause);
  }
}
