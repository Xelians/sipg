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
 * <p>Java class for MeasurementWeightUnitsType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>{@code
 * <simpleType name="MeasurementWeightUnitsType">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="microgram"/>
 *     <enumeration value="MC"/>
 *     <enumeration value="milligram"/>
 *     <enumeration value="MGM"/>
 *     <enumeration value="gram"/>
 *     <enumeration value="GRM"/>
 *     <enumeration value="kilogram"/>
 *     <enumeration value="KGM"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "MeasurementWeightUnitsType")
@XmlEnum
public enum MeasurementWeightUnitsType {

    @XmlEnumValue("microgram")
    MICROGRAM("microgram"),
    MC("MC"),
    @XmlEnumValue("milligram")
    MILLIGRAM("milligram"),
    MGM("MGM"),
    @XmlEnumValue("gram")
    GRAM("gram"),
    GRM("GRM"),
    @XmlEnumValue("kilogram")
    KILOGRAM("kilogram"),
    KGM("KGM");
    private final String value;

    MeasurementWeightUnitsType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MeasurementWeightUnitsType fromValue(String v) {
        for (MeasurementWeightUnitsType c: MeasurementWeightUnitsType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
