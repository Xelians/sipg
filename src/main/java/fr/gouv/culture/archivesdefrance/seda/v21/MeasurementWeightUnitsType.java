//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.10.29 at 01:17:12 AM CET
//
package fr.gouv.culture.archivesdefrance.seda.v21;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Java class for MeasurementWeightUnitsType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <p>< pre> &lt;simpleType name="MeasurementWeightUnitsType"> &lt;restriction
 * base="{http://www.w3.org/2001/XMLSchema}string"> &lt;enumeration value="microgram"/>
 * &lt;enumeration value="MC"/> &lt;enumeration value="milligram"/> &lt;enumeration value="MGM"/>
 * &lt;enumeration value="gram"/> &lt;enumeration value="GRM"/> &lt;enumeration value="kilogram"/>
 * &lt;enumeration value="KGM"/> &lt;/restriction> &lt;/simpleType> </pre>
 */
@XmlType(name = "MeasurementWeightUnitsType")
@XmlEnum
public enum MeasurementWeightUnitsType {

  /** Microgram measurement weight units type. */
  @XmlEnumValue("microgram")
  MICROGRAM("microgram"),
  /** Mc measurement weight units type. */
  MC("MC"),
  /** Milligram measurement weight units type. */
  @XmlEnumValue("milligram")
  MILLIGRAM("milligram"),
  /** Mgm measurement weight units type. */
  MGM("MGM"),
  /** Gram measurement weight units type. */
  @XmlEnumValue("gram")
  GRAM("gram"),
  /** Grm measurement weight units type. */
  GRM("GRM"),
  /** Kilogram measurement weight units type. */
  @XmlEnumValue("kilogram")
  KILOGRAM("kilogram"),
  /** Kgm measurement weight units type. */
  KGM("KGM");
  private final String value;

  MeasurementWeightUnitsType(String v) {
    value = v;
  }

  /**
   * Value string.
   *
   * @return the string
   */
  public String value() {
    return value;
  }

  /**
   * From value measurement weight units type.
   *
   * @param v the v
   * @return the measurement weight units type
   */
  public static MeasurementWeightUnitsType fromValue(String v) {
    for (MeasurementWeightUnitsType c : MeasurementWeightUnitsType.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }
}
