//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.10.29 at 01:17:12 AM CET 
//
package fr.gouv.culture.archivesdefrance.seda.v2;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for CodeKeywordType.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <
 * pre>
 * &lt;simpleType name="CodeKeywordType"> &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 * &lt;enumeration value="corpname"/> &lt;enumeration value="famname"/> &lt;enumeration value="geogname"/>
 * &lt;enumeration value="name"/> &lt;enumeration value="occupation"/> &lt;enumeration value="persname"/>
 * &lt;enumeration value="subject"/> &lt;enumeration value="genreform"/> &lt;enumeration value="function"/>
 * &lt;/restriction> &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "CodeKeywordType")
@XmlEnum
public enum CodeKeywordType {

    /**
     * Références : ead.corpname
     */
    @XmlEnumValue("corpname")
    CORPNAME("corpname"),
    /**
     * Nom de famille.
     */
    @XmlEnumValue("famname")
    FAMNAME("famname"),
    /**
     * Nom géographique.
     */
    @XmlEnumValue("geogname")
    GEOGNAME("geogname"),
    /**
     * Nom.
     */
    @XmlEnumValue("name")
    NAME("name"),
    /**
     * Fonction.
     */
    @XmlEnumValue("occupation")
    OCCUPATION("occupation"),
    /**
     * Nom de personne.
     */
    @XmlEnumValue("persname")
    PERSNAME("persname"),
    /**
     * Mot-matière.
     */
    @XmlEnumValue("subject")
    SUBJECT("subject"),
    /**
     * Type de document.
     */
    @XmlEnumValue("genreform")
    GENREFORM("genreform"),
    /**
     * Références : ead.function
     */
    @XmlEnumValue("function")
    FUNCTION("function");
    private final String value;

    CodeKeywordType(String v) {
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
     * From value code keyword type.
     *
     * @param v the v
     * @return the code keyword type
     */
    public static CodeKeywordType fromValue(String v) {
        for (CodeKeywordType c : CodeKeywordType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
