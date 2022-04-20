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

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Test init.
 *
 * @author Emmanuel Deviller
 */
public class TestInit implements BeforeAllCallback {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestInit.class);

    /**
     * The constant TEST_RESOURCES.
     */
    public static final String TEST_RESOURCES = "src/test/resources/";
    /**
     * The constant TEST_RESULTS.
     */
    public static final String TEST_RESULTS = "target/test-results/";

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        Path testDir = Paths.get(TEST_RESULTS);
        if (Files.notExists(testDir)) {
            LOGGER.info("Creating test results directory: " + TEST_RESULTS);
            Files.createDirectories(testDir);
        }
    }

}
