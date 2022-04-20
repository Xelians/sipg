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

import com.google.common.jimfs.Jimfs;

import java.nio.file.FileSystem;
import java.nio.file.Paths;

import fr.xelians.sipg.SipFactory;
import fr.xelians.sipg.TestInit;
import fr.xelians.sipg.model.ArchiveTransfer;

import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.fail;

import fr.xelians.sipg.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The JSON integration test.
 *
 * @author Emmanuel Deviller
 */
@ExtendWith(TestInit.class)
class JsonTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonTest.class);

    private final JsonConfig jsonConfig = JsonConfigBuilder.builder().format(true).build();
    private final JsonService jsonService = JsonService.getInstance();

    /**
     * Test create mini json.
     *
     * @param testInfo the test info
     */
    @Test
    void testCreateMiniJson(TestInfo testInfo) {
        LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));
        try {
            ArchiveTransfer archiveTransfer = SipFactory.createMiniSip();
            jsonService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "minisip_serial.json"), jsonConfig);
        }
        catch (Exception ex) {
            String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
            LOGGER.warn(msg, ex);
            fail(msg);
        }
    }

    /**
     * Test read mini json.
     *
     * @param testInfo the test info
     */
    @Test
    void testReadMiniJson(TestInfo testInfo) {
        LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));
        try {
            Path jsonPath = Paths.get(TestInit.TEST_RESOURCES + "minisip.json");
            ArchiveTransfer archiveTransfer = jsonService.read(jsonPath);
            jsonService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "minisip_deserial.json"), jsonConfig);
        }
        catch (Exception ex) {
            String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
            LOGGER.warn(msg, ex);
            fail(msg);
        }
    }

    /**
     * Test create full vitam.
     *
     * @param testInfo the test info
     */
    @Test
    void testCreateFullVitam(TestInfo testInfo) {
        LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));

        try {
            ArchiveTransfer archiveTransfer = SipFactory.createSipFullVitam();
            jsonService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "sip_vitam_full.json"), jsonConfig);
        }
        catch (Exception ex) {
            String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
            LOGGER.warn(msg, ex);
            fail(msg);
        }
    }

    /**
     * Test create simple json.
     *
     * @param testInfo the test info
     */
    @Test
    void testCreateSimpleJson(TestInfo testInfo) {
        LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));

        try (FileSystem fs = Jimfs.newFileSystem()) {
            ArchiveTransfer archiveTransfer = SipFactory.createSimpleSip(fs);
            jsonService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "simplesip_serial.json"), jsonConfig);
        }
        catch (Exception ex) {
            String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
            LOGGER.warn(msg, ex);
            fail(msg);
        }
    }

    /**
     * Test read simple json.
     *
     * @param testInfo the test info
     */
    @Test
    void testReadSimpleJson(TestInfo testInfo) {
        LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));
        try {
            Path jsonPath = Paths.get(TestInit.TEST_RESOURCES + "simplesip.json");
            ArchiveTransfer archiveTransfer = jsonService.read(jsonPath);
            jsonService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "simplesip_deserial.json"), jsonConfig);
        }
        catch (Exception ex) {
            String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
            LOGGER.warn(msg, ex);
            fail(msg);
        }
    }

    /**
     * Test create read simple json.
     *
     * @param testInfo the test info
     */
    @Test
    void testCreateReadSimpleJson(TestInfo testInfo) {
        LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));
        try (FileSystem fs = Jimfs.newFileSystem()) {
            String serialized = jsonService.write(SipFactory.createSimpleSip(fs), jsonConfig);
            ArchiveTransfer archiveTransfer = jsonService.read(serialized);
            jsonService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "simplesip_serial_deserial.json"), jsonConfig);
        }
        catch (Exception ex) {
            String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
            LOGGER.warn(msg, ex);
            fail(msg);
        }
    }

    /**
     * Test read freemarker json.
     *
     * @param testInfo the test info
     */
    @Test
    void testReadFreemarkerJson(TestInfo testInfo) {
        LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));
        try {
            String jsonString = SipFactory.createJsonString();
            ArchiveTransfer archiveTransfer = jsonService.read(jsonString);
            jsonService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "freemarker_deserial.json"), jsonConfig);
        }
        catch (Exception ex) {
            String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
            LOGGER.warn(msg, ex);
            fail(msg);
        }
    }

    /**
     * Test create full text json.
     *
     * @param testInfo the test info
     */
    @Test
    void testCreateFullTextJson(TestInfo testInfo) {
        LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));
        try {
            ArchiveTransfer archiveTransfer = SipFactory.createFullTextSip();
            jsonService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "full_serial.json"), jsonConfig);
        }
        catch (Exception ex) {
            String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
            LOGGER.warn(msg, ex);
            fail(msg);
        }
    }

    /**
     * Test read full text json.
     *
     * @param testInfo the test info
     */
    @Test
    void testReadFullTextJson(TestInfo testInfo) {
        LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));
        try {
            String serialized = jsonService.write(SipFactory.createFullTextSip(), jsonConfig);
            ArchiveTransfer archiveTransfer = jsonService.read(serialized);
            jsonService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "full_deserial.json"), jsonConfig);
        }
        catch (Exception ex) {
            String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
            LOGGER.warn(msg, ex);
            fail(msg);
        }
    }

    /**
     * Test create small json.
     *
     * @param testInfo the test info
     */
    @Test
    void testCreateSmallJson(TestInfo testInfo) {
        LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));
        try {
            ArchiveTransfer archiveTransfer = SipFactory.createSmallSip();
            Path zipPath = Paths.get(TestInit.TEST_RESULTS + "smallsip_serial.json");
            jsonService.write(archiveTransfer, zipPath, jsonConfig);
        }
        catch (Exception ex) {
            String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
            LOGGER.warn(msg, ex);
            fail(msg);
        }
    }

    /**
     * Test read small json.
     *
     * @param testInfo the test info
     */
    @Test
    void testReadSmallJson(TestInfo testInfo) {
        LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));
        try {
            Path jsonPath = Paths.get(TestInit.TEST_RESOURCES + "smallsip.json");
            ArchiveTransfer archiveTransfer = jsonService.read(jsonPath);
            jsonService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "smallsip_deserial.json"), jsonConfig);
        }
        catch (Exception ex) {
            String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
            LOGGER.warn(msg, ex);
            fail(msg);
        }
    }

    /**
     * Test create large json.
     *
     * @param testInfo the test info
     */
    @Test
    void testCreateLargeJson(TestInfo testInfo) {
        LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));
        try (FileSystem fs = Jimfs.newFileSystem()) {
            ArchiveTransfer archiveTransfer = SipFactory.createLargeSip(fs);
            jsonService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "largesip_serial.json"), jsonConfig);
        }
        catch (Exception ex) {
            String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
            LOGGER.warn(msg, ex);
            fail(msg);
        }
    }

    /**
     * Test read large json.
     *
     * @param testInfo the test info
     */
    @Test
    void testReadLargeJson(TestInfo testInfo) {
        LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));
        try {
            Path jsonPath = Paths.get(TestInit.TEST_RESOURCES + "largesip.json");
            ArchiveTransfer archiveTransfer = jsonService.read(jsonPath);
            jsonService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "largesip_deserial.json"), jsonConfig);
        }
        catch (Exception ex) {
            String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
            LOGGER.warn(msg, ex);
            fail(msg);
        }
    }

    /**
     * Test create deep json.
     *
     * @param testInfo the test info
     */
    @Test
    void testCreateDeepJson(TestInfo testInfo) {
        LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));
        try (FileSystem fs = Jimfs.newFileSystem()) {
            ArchiveTransfer archiveTransfer = SipFactory.createDeepSip(fs);
            jsonService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "deepsip_serial.json"), jsonConfig);
        }
        catch (Exception ex) {
            String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
            LOGGER.warn(msg, ex);
            fail(msg);
        }
    }

    /**
     * Test read deep json.
     *
     * @param testInfo the test info
     */
    @Test
    void testReadDeepJson(TestInfo testInfo) {
        LOGGER.info(TestUtils.TEST + TestUtils.getMethod(testInfo));
        try {
            Path jsonPath = Paths.get(TestInit.TEST_RESOURCES + "deepsip.json");
            ArchiveTransfer archiveTransfer = jsonService.read(jsonPath);
            jsonService.write(archiveTransfer, Paths.get(TestInit.TEST_RESULTS + "deepsip_deserial.json"), jsonConfig);
        }
        catch (Exception ex) {
            String msg = TestUtils.FAIL + TestUtils.getMethod(testInfo);
            LOGGER.warn(msg, ex);
            fail(msg);
        }
    }
}
