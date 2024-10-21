//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import java.math.BigDecimal;

/**
 * Java class for MeasurementType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="MeasurementType">
 *   <simpleContent>
 *     <extension base="<http://www.w3.org/2001/XMLSchema>decimal">
 *       <attribute name="unit" use="required" type="{fr:gouv:culture:archivesdefrance:seda:v2}MeasurementUnitsType" />
 *     </extension>
 *   </simpleContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "MeasurementType",
    propOrder = {"value"})
public class MeasurementType {

  @XmlValue protected BigDecimal value;

  /** Références : Voir UNECE_MeasurementUnitCommonCode_8.xsd */
  @XmlAttribute(name = "unit", required = true)
  protected String unit;

  /**
   * Gets the value of the value property.
   *
   * @return possible object is {@link BigDecimal }
   */
  public BigDecimal getValue() {
    return value;
  }

  /**
   * Sets the value of the value property.
   *
   * @param value allowed object is {@link BigDecimal }
   */
  public void setValue(BigDecimal value) {
    this.value = value;
  }

  /**
   * Références : Voir UNECE_MeasurementUnitCommonCode_8.xsd
   *
   * @return possible object is {@link String }
   */
  public String getUnit() {
    return unit;
  }

  /**
   * Sets the value of the unit property.
   *
   * @param value allowed object is {@link String }
   * @see #getUnit()
   */
  public void setUnit(String value) {
    this.unit = value;
  }
}
