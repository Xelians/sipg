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

import fr.xelians.sipg.utils.SipException;

public class TextContentDataObject extends BinaryDataObject {
  public TextContentDataObject() {
    super(TEXT_CONTENT);
  }

  @Override
  public void setBinaryVersion(String objectVersion) {
    if (objectVersion == null || !objectVersion.startsWith(TEXT_CONTENT)) {
      throw new SipException(
          String.format(
              "The qualifier of type %s cannot be modified to %s", binaryVersion, objectVersion));
    }
    this.binaryVersion = objectVersion;
  }
}
