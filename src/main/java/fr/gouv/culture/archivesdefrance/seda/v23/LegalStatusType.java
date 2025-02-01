//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Valeurs de LegalStatus.
 *
 * <p>Java class for LegalStatusType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <simpleType name="LegalStatusType">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     <enumeration value="Public Archive"/>
 *     <enumeration value="Private Archive"/>
 *     <enumeration value="Public and Private Archive"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 */
@XmlType(name = "LegalStatusType")
@XmlEnum
public enum LegalStatusType {
  @XmlEnumValue("Public Archive")
  PUBLIC_ARCHIVE("Public Archive"),
  @XmlEnumValue("Private Archive")
  PRIVATE_ARCHIVE("Private Archive"),
  @XmlEnumValue("Public and Private Archive")
  PUBLIC_AND_PRIVATE_ARCHIVE("Public and Private Archive");
  private final String value;

  LegalStatusType(String v) {
    value = v;
  }

  /**
   * Gets the value associated to the enum constant.
   *
   * @return The value linked to the enum.
   */
  public String value() {
    return value;
  }

  /**
   * Gets the enum associated to the value passed as parameter.
   *
   * @param v The value to get the enum from.
   * @return The enum which corresponds to the value, if it exists.
   * @throws IllegalArgumentException If no value matches in the enum declaration.
   */
  public static LegalStatusType fromValue(String v) {
    for (LegalStatusType c : LegalStatusType.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }
}
