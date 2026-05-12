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
 * Code correspondant à l’action à entreprendre au terme de la durée d’utilité courante.
 * 
 * <p>Classe Java pour FinalActionStorageCodeType.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * <pre>{@code
 * <simpleType name="FinalActionStorageCodeType">
 *   <restriction base="{fr:gouv:culture:archivesdefrance:seda:v2.2}NonEmptyTokenType">
 *     <enumeration value="RestrictAccess"/>
 *     <enumeration value="Transfer"/>
 *     <enumeration value="Copy"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
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
    public static FinalActionStorageCodeType fromValue(String v) {
        for (FinalActionStorageCodeType c: FinalActionStorageCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
