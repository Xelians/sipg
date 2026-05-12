//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:05:56 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Table des types de mots-clés.
 * 
 * <p>Classe Java pour CodeKeywordType.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * <pre>{@code
 * <simpleType name="CodeKeywordType">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     <enumeration value="corpname"/>
 *     <enumeration value="famname"/>
 *     <enumeration value="geogname"/>
 *     <enumeration value="name"/>
 *     <enumeration value="occupation"/>
 *     <enumeration value="persname"/>
 *     <enumeration value="subject"/>
 *     <enumeration value="genreform"/>
 *     <enumeration value="function"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "CodeKeywordType")
@XmlEnum
public enum CodeKeywordType {


    /**
     * Références : ead.corpname
     * 
     */
    @XmlEnumValue("corpname")
    CORPNAME("corpname"),

    /**
     * Nom de famille.
     * 
     */
    @XmlEnumValue("famname")
    FAMNAME("famname"),

    /**
     * Nom géographique.
     * 
     */
    @XmlEnumValue("geogname")
    GEOGNAME("geogname"),

    /**
     * Nom.
     * 
     */
    @XmlEnumValue("name")
    NAME("name"),

    /**
     * Fonction.
     * 
     */
    @XmlEnumValue("occupation")
    OCCUPATION("occupation"),

    /**
     * Nom de personne.
     * 
     */
    @XmlEnumValue("persname")
    PERSNAME("persname"),

    /**
     * Mot-matière.
     * 
     */
    @XmlEnumValue("subject")
    SUBJECT("subject"),

    /**
     * Type de document.
     * 
     */
    @XmlEnumValue("genreform")
    GENREFORM("genreform"),

    /**
     * Références : ead.function
     * 
     */
    @XmlEnumValue("function")
    FUNCTION("function");
    private final String value;

    CodeKeywordType(String v) {
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
    public static CodeKeywordType fromValue(String v) {
        for (CodeKeywordType c: CodeKeywordType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
