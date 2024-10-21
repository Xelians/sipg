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
 * Java class for LevelType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <p>< pre> &lt;simpleType name="LevelType"> &lt;restriction
 * base="{http://www.w3.org/2001/XMLSchema}token"> &lt;enumeration value="Fonds"/> &lt;enumeration
 * value="Subfonds"/> &lt;enumeration value="Class"/> &lt;enumeration value="Collection"/>
 * &lt;enumeration value="Series"/> &lt;enumeration value="Subseries"/> &lt;enumeration
 * value="RecordGrp"/> &lt;enumeration value="SubGrp"/> &lt;enumeration value="File"/>
 * &lt;enumeration value="Item"/> &lt;enumeration value="OtherLevel"/> &lt;/restriction>
 * &lt;/simpleType> </pre>
 */
@XmlType(name = "LevelType")
@XmlEnum
public enum LevelType {

  /** Fonds level type. */
  @XmlEnumValue("Fonds")
  FONDS("Fonds"),
  /** Subfonds level type. */
  @XmlEnumValue("Subfonds")
  SUBFONDS("Subfonds"),
  /** Class level type. */
  @XmlEnumValue("Class")
  CLASS("Class"),
  /** Collection level type. */
  @XmlEnumValue("Collection")
  COLLECTION("Collection"),
  /** Series level type. */
  @XmlEnumValue("Series")
  SERIES("Series"),
  /** Subseries level type. */
  @XmlEnumValue("Subseries")
  SUBSERIES("Subseries"),
  /** Record grp level type. */
  @XmlEnumValue("RecordGrp")
  RECORD_GRP("RecordGrp"),
  /** Sub grp level type. */
  @XmlEnumValue("SubGrp")
  SUB_GRP("SubGrp"),
  /** File level type. */
  @XmlEnumValue("File")
  FILE("File"),
  /** Item level type. */
  @XmlEnumValue("Item")
  ITEM("Item"),
  /** Other level level type. */
  @XmlEnumValue("OtherLevel")
  OTHER_LEVEL("OtherLevel");
  private final String value;

  LevelType(String v) {
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
   * From value level type.
   *
   * @param v the v
   * @return the level type
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
