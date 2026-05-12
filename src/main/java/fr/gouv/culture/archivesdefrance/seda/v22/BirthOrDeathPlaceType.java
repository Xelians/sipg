//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:09:37 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v22;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour BirthOrDeathPlaceType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="BirthOrDeathPlaceType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <group ref="{fr:gouv:culture:archivesdefrance:seda:v2.2}LocationGroup"/>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BirthOrDeathPlaceType", propOrder = {
    "geogname",
    "address",
    "postalCode",
    "city",
    "region",
    "country"
})
public class BirthOrDeathPlaceType {

    /**
     * Références : ead.geogname
     * 
     */
    @XmlElement(name = "Geogname")
    protected String geogname;
    /**
     * Références : ead.address
     * 
     */
    @XmlElement(name = "Address")
    protected String address;
    /**
     * En plus des balises Tag et Keyword, il est possible d'indexer les objets avec des éléments pré-définis : Code postal.
     * 
     */
    @XmlElement(name = "PostalCode")
    protected String postalCode;
    /**
     * En plus des balises Tag et Keyword, il est possible d'indexer les objets avec des éléments pré-définis : Ville.
     * 
     */
    @XmlElement(name = "City")
    protected String city;
    /**
     * En plus des balises Tag et Keyword, il est possible d'indexer les objets avec des éléments pré-définis : Région.
     * 
     */
    @XmlElement(name = "Region")
    protected String region;
    /**
     * En plus des balises Tag et Keyword, il est possible d'indexer les objets avec des éléments pré-définis : Pays.
     * 
     */
    @XmlElement(name = "Country")
    protected String country;

    /**
     * Références : ead.geogname
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeogname() {
        return geogname;
    }

    /**
     * Définit la valeur de la propriété geogname.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getGeogname()
     */
    public void setGeogname(String value) {
        this.geogname = value;
    }

    /**
     * Références : ead.address
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Définit la valeur de la propriété address.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getAddress()
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * En plus des balises Tag et Keyword, il est possible d'indexer les objets avec des éléments pré-définis : Code postal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Définit la valeur de la propriété postalCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getPostalCode()
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * En plus des balises Tag et Keyword, il est possible d'indexer les objets avec des éléments pré-définis : Ville.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Définit la valeur de la propriété city.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getCity()
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * En plus des balises Tag et Keyword, il est possible d'indexer les objets avec des éléments pré-définis : Région.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegion() {
        return region;
    }

    /**
     * Définit la valeur de la propriété region.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getRegion()
     */
    public void setRegion(String value) {
        this.region = value;
    }

    /**
     * En plus des balises Tag et Keyword, il est possible d'indexer les objets avec des éléments pré-définis : Pays.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Définit la valeur de la propriété country.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getCountry()
     */
    public void setCountry(String value) {
        this.country = value;
    }

}
