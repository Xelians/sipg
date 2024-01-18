//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package fr.gouv.culture.archivesdefrance.seda.v2;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LegalStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>{@code
 * <simpleType name="LegalStatusType">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     <enumeration value="Public Archive"/>
 *     <enumeration value="Private Archive"/>
 *     <enumeration value="Public and Private Archive"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
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

    public String value() {
        return value;
    }

    public static LegalStatusType fromValue(String v) {
        for (LegalStatusType c: LegalStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
