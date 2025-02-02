//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v22;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Code correspondant à l’action à entreprendre au terme de la durée d’utilité courante.
 *
 * <p>Java class for FinalActionStorageCodeType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <simpleType name="FinalActionStorageCodeType">
 *   <restriction base="{fr:gouv:culture:archivesdefrance:seda:v2.2}NonEmptyTokenType">
 *     <enumeration value="RestrictAccess"/>
 *     <enumeration value="Transfer"/>
 *     <enumeration value="Copy"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 */
@XmlType(name = "FinalActionStorageCodeType")
@XmlEnum
public enum FinalActionStorageCodeType {
  @XmlEnumValue("RestrictAccess")
  RESTRICT_ACCESS("RestrictAccess"),
  @XmlEnumValue("Transfer")
  TRANSFER("Transfer"),
  @XmlEnumValue("Copy")
  COPY("Copy");
  private final String value;

  FinalActionStorageCodeType(String v) {
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
  public static FinalActionStorageCodeType fromValue(String v) {
    for (FinalActionStorageCodeType c : FinalActionStorageCodeType.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }
}
