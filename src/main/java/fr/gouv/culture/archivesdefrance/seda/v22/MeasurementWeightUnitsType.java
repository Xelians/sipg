//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:09:37 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v22;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Classe Java pour MeasurementWeightUnitsType.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
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

    /**
     * Gets the value associated to the enum constant.
     * 
     * @return
     *     The value linked to the enum.
     */
    public String value() {
        return value;
    }

    /**
     * Gets the enum associated to the value passed as parameter.
     * 
     * @param v
     *     The value to get the enum from.
     * @return
     *     The enum which corresponds to the value, if it exists.
     * @throws IllegalArgumentException
     *     If no value matches in the enum declaration.
     */
    public static MeasurementWeightUnitsType fromValue(String v) {
        for (MeasurementWeightUnitsType c: MeasurementWeightUnitsType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
