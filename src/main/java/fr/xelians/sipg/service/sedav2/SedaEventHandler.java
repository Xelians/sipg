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

package fr.xelians.sipg.service.sedav2;

import jakarta.xml.bind.ValidationEvent;
import jakarta.xml.bind.ValidationEventHandler;
import jakarta.xml.bind.ValidationEventLocator;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

public class SedaEventHandler implements ValidationEventHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(SedaEventHandler.class);

  public boolean handleEvent(ValidationEvent event) {

    if (event == null) {
      throw new IllegalArgumentException();
    }

    switch (event.getSeverity()) {
      case ValidationEvent.WARNING -> {
        LOGGER.info("Warning {} {}", event.getMessage(), getLocation(event));
        return true;
      }
      case ValidationEvent.ERROR -> {
        LOGGER.info("Error {} {}", event.getMessage(), getLocation(event));
        return false;
      }
      case ValidationEvent.FATAL_ERROR -> {
        LOGGER.info("FatalError {} {}", event.getMessage(), getLocation(event));
        return false;
      }
      default -> {
        assert false : "UnrecognizedSeverity";
      }
    }
    return false;
  }

  private String getLocation(ValidationEvent event) {
    StringBuilder msg = new StringBuilder();

    ValidationEventLocator locator = event.getLocator();

    if (locator != null) {
      URL url = locator.getURL();
      Object obj = locator.getObject();
      Node node = locator.getNode();
      int line = locator.getLineNumber();

      if (url != null || line != -1) {
        msg.append("line ").append(line);
        if (url != null) {
          msg.append(" of ").append(url);
        }
      } else if (obj != null) {
        msg.append(" obj: ").append(obj);
      } else if (node != null) {
        msg.append(" node: ").append(node);
      }
    }

    return msg.toString();
  }
}
