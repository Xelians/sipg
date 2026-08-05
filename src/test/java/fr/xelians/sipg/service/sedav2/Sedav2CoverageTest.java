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

import static org.junit.jupiter.api.Assertions.*;

import com.google.common.base.Throwables;
import fr.xelians.sipg.TestInit;
import fr.xelians.sipg.TestUtils;
import fr.xelians.sipg.model.ArchiveTransfer;
import fr.xelians.sipg.model.ArchiveUnit;
import fr.xelians.sipg.model.Element;
import fr.xelians.sipg.utils.SipException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * The SEDA v2 Coverage group test.
 *
 * @author Emmanuel Deviller
 */
@ExtendWith(TestInit.class)
class Sedav2CoverageTest {

  private final SedaConfig sedaConfig =
      SedaConfigBuilder.builder().format(true).validate(true).strict(false).build();

  private static ArchiveTransfer transferWithCoverage() {
    ArchiveUnit unit = newUnit();
    unit.addSpatialCoverage("Paris");
    unit.addSpatialCoverage("Vincennes");
    unit.addTemporalCoverage("XIXe siecle");
    unit.addJuridictionalCoverage("Tribunal de Paris");
    return newTransfer(unit);
  }

  private static ArchiveUnit newUnit() {
    ArchiveUnit unit = new ArchiveUnit();
    unit.setBinaryPath(Paths.get(TestInit.TEST_RESOURCES + "dummy.pdf"));
    unit.addTitle("My_Title");
    return unit;
  }

  private static ArchiveTransfer newTransfer(ArchiveUnit unit) {
    ArchiveTransfer transfer = new ArchiveTransfer();
    transfer.setArchivalAgreement("My Archival Agreement");
    transfer.setArchivalAgency("AG001", "");
    transfer.setTransferringAgency("AG002", "");
    transfer.addArchiveUnit(unit);
    return transfer;
  }

  private static String marshal(Supplier<Sedav2Service> service, ArchiveTransfer transfer)
      throws Exception {
    try (InputStream is = service.get().marshal(transfer)) {
      return TestUtils.readAsString(is);
    }
  }

  /** The Coverage group is marshalled in the declared slot for every SEDA version. */
  @Test
  void testCoverageIsMarshalled() throws Exception {
    List<Supplier<Sedav2Service>> services =
        List.of(
            Sedav2Service::getInstance,
            Sedav2Service::getV22Instance,
            Sedav2Service::getV23Instance);

    for (Supplier<Sedav2Service> service : services) {
      String manifest = marshal(service, transferWithCoverage());
      assertTrue(
          manifest.contains(
              "<Coverage><Spatial>Paris</Spatial><Spatial>Vincennes</Spatial>"
                  + "<Temporal>XIXe siecle</Temporal>"
                  + "<Juridictional>Tribunal de Paris</Juridictional></Coverage>"),
          "Coverage manquant ou mal ordonne dans " + manifest);
    }
  }

  /** A unit without coverage must not emit an empty Coverage element. */
  @Test
  void testNoCoverageEmitsNoElement() throws Exception {
    String manifest = marshal(Sedav2Service::getV22Instance, newTransfer(newUnit()));
    assertFalse(manifest.contains("<Coverage"), "Coverage vide emis a tort");
  }

  /**
   * A typed Coverage passes XSD 1.1 validation on write, for every SEDA version. Before Coverage
   * was supported, an esafe extended metadata of that name landed in the {@code <xsd:any>}
   * extension slot, which broke the DIP export with cos-element-consistent.4.
   */
  @Test
  void testTypedCoverageIsValid() {
    assertDoesNotThrow(
        () -> {
          Sedav2Service.getInstance()
              .write(
                  transferWithCoverage(),
                  Paths.get(TestInit.TEST_RESULTS + "coverage21_seda.zip"),
                  sedaConfig);
          Sedav2Service.getV22Instance()
              .write(
                  transferWithCoverage(),
                  Paths.get(TestInit.TEST_RESULTS + "coverage22_seda.zip"),
                  sedaConfig);
          Sedav2Service.getV23Instance()
              .write(
                  transferWithCoverage(),
                  Paths.get(TestInit.TEST_RESULTS + "coverage23_seda.zip"),
                  sedaConfig);
        });
  }

  /**
   * The same name emitted as a raw extension stays rejected — which is why the typed support above
   * is the only way to carry a Coverage. The exact violation depends on where the raw element lands
   * in the content model: {@code cvc-complex-type.2.3} when it is matched by the declared particle,
   * {@code cos-element-consistent.4} when it falls through to the {@code <xsd:any>} slot. Only the
   * rejection itself is asserted.
   */
  @Test
  void testRawCoverageExtensionIsRejected() {
    ArchiveUnit unit = newUnit();
    unit.addElement(new Element("Coverage", "Paris"));
    Path output = Paths.get(TestInit.TEST_RESULTS + "coverage_raw_seda.zip");

    SipException ex =
        assertThrows(
            SipException.class,
            () -> Sedav2Service.getV22Instance().write(newTransfer(unit), output, sedaConfig));
    assertTrue(
        Throwables.getStackTraceAsString(ex).contains("Coverage"),
        "Le rejet doit designer l'element Coverage");
  }

  /** The model keeps the three coverage lists independent. */
  @Test
  void testCoverageModel() {
    ArchiveUnit unit = new ArchiveUnit();
    unit.addSpatialCoverage("Paris");
    unit.addTemporalCoverage("XIXe siecle");
    unit.addJuridictionalCoverage("Tribunal de Paris");

    assertEquals(List.of("Paris"), unit.getSpatialCoverages());
    assertEquals(List.of("XIXe siecle"), unit.getTemporalCoverages());
    assertEquals(List.of("Tribunal de Paris"), unit.getJuridictionalCoverages());

    assertTrue(unit.removeSpatialCoverage("Paris"));
    assertFalse(unit.removeSpatialCoverage("Paris"));
    assertTrue(unit.getSpatialCoverages().isEmpty());
    assertEquals(1, unit.getTemporalCoverages().size());

    assertThrows(NullPointerException.class, () -> unit.addSpatialCoverage(null));
  }
}
