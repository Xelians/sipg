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
 * Références : descriptionlevel_code SEDA 1.0
 *
 * <p>Java class for LevelType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <simpleType name="LevelType">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     <enumeration value="Fonds"/>
 *     <enumeration value="Subfonds"/>
 *     <enumeration value="Class"/>
 *     <enumeration value="Collection"/>
 *     <enumeration value="Series"/>
 *     <enumeration value="Subseries"/>
 *     <enumeration value="RecordGrp"/>
 *     <enumeration value="SubGrp"/>
 *     <enumeration value="File"/>
 *     <enumeration value="Item"/>
 *     <enumeration value="OtherLevel"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 */
@XmlType(name = "LevelType")
@XmlEnum
public enum LevelType {
  @XmlEnumValue("Fonds")
  FONDS("Fonds"),
  @XmlEnumValue("Subfonds")
  SUBFONDS("Subfonds"),
  @XmlEnumValue("Class")
  CLASS("Class"),
  @XmlEnumValue("Collection")
  COLLECTION("Collection"),
  @XmlEnumValue("Series")
  SERIES("Series"),
  @XmlEnumValue("Subseries")
  SUBSERIES("Subseries"),
  @XmlEnumValue("RecordGrp")
  RECORD_GRP("RecordGrp"),
  @XmlEnumValue("SubGrp")
  SUB_GRP("SubGrp"),
  @XmlEnumValue("File")
  FILE("File"),
  @XmlEnumValue("Item")
  ITEM("Item"),
  @XmlEnumValue("OtherLevel")
  OTHER_LEVEL("OtherLevel");
  private final String value;

  LevelType(String v) {
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
  public static LevelType fromValue(String v) {
    for (LevelType c : LevelType.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }
}
