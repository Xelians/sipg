//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:09:37 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v22;

import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour GpsType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="GpsType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="GpsVersionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="GpsAltitude" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         <element name="GpsAltitudeRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="GpsLatitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="GpsLatitudeRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="GpsLongitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="GpsLongitudeRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="GpsDateStamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GpsType", propOrder = {
    "gpsVersionID",
    "gpsAltitude",
    "gpsAltitudeRef",
    "gpsLatitude",
    "gpsLatitudeRef",
    "gpsLongitude",
    "gpsLongitudeRef",
    "gpsDateStamp"
})
public class GpsType {

    /**
     * Identifiant de la version du GPS.
     * 
     */
    @XmlElement(name = "GpsVersionID")
    protected String gpsVersionID;
    /**
     * Indique l'altitude basée sur la référence dans GPSAltitudeRef. L'altitude est exprimée en mètres.
     * 
     */
    @XmlElement(name = "GpsAltitude")
    protected BigInteger gpsAltitude;
    /**
     * Si l'altitude est au-dessous du niveau de la mer, la veleur 1 est normalement donnée.
     * 
     */
    @XmlElement(name = "GpsAltitudeRef")
    protected String gpsAltitudeRef;
    /**
     *  2)Si la latitude est exprimée en degrés, minutes et secondes, le format type est dd, mm, ss. Par ex: "45 18 46.922".
     * 
     */
    @XmlElement(name = "GpsLatitude")
    protected String gpsLatitude;
    /**
     * Indique si la latitude est nord ou sud. La valeur 'N' indique la latitude nord, et 'S' indique la latitude sud.
     * 
     */
    @XmlElement(name = "GpsLatitudeRef")
    protected String gpsLatitudeRef;
    /**
     *  2)Si la longitude est exprimée en degrés, minutes et secondes, le format type est dd, mm, ss. Par ex: "5 23 32.229".
     * 
     */
    @XmlElement(name = "GpsLongitude")
    protected String gpsLongitude;
    /**
     * Indique si la longitude est est ou ouest. La valeur 'E' indique la longitude est, et 'W' indique la longitude Ouest.
     * 
     */
    @XmlElement(name = "GpsLongitudeRef")
    protected String gpsLongitudeRef;
    /**
     * Heure et Date de la position GPS.
     * 
     */
    @XmlElement(name = "GpsDateStamp")
    protected String gpsDateStamp;

    /**
     * Identifiant de la version du GPS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGpsVersionID() {
        return gpsVersionID;
    }

    /**
     * Définit la valeur de la propriété gpsVersionID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getGpsVersionID()
     */
    public void setGpsVersionID(String value) {
        this.gpsVersionID = value;
    }

    /**
     * Indique l'altitude basée sur la référence dans GPSAltitudeRef. L'altitude est exprimée en mètres.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getGpsAltitude() {
        return gpsAltitude;
    }

    /**
     * Définit la valeur de la propriété gpsAltitude.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     * @see #getGpsAltitude()
     */
    public void setGpsAltitude(BigInteger value) {
        this.gpsAltitude = value;
    }

    /**
     * Si l'altitude est au-dessous du niveau de la mer, la veleur 1 est normalement donnée.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGpsAltitudeRef() {
        return gpsAltitudeRef;
    }

    /**
     * Définit la valeur de la propriété gpsAltitudeRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getGpsAltitudeRef()
     */
    public void setGpsAltitudeRef(String value) {
        this.gpsAltitudeRef = value;
    }

    /**
     *  2)Si la latitude est exprimée en degrés, minutes et secondes, le format type est dd, mm, ss. Par ex: "45 18 46.922".
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGpsLatitude() {
        return gpsLatitude;
    }

    /**
     * Définit la valeur de la propriété gpsLatitude.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getGpsLatitude()
     */
    public void setGpsLatitude(String value) {
        this.gpsLatitude = value;
    }

    /**
     * Indique si la latitude est nord ou sud. La valeur 'N' indique la latitude nord, et 'S' indique la latitude sud.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGpsLatitudeRef() {
        return gpsLatitudeRef;
    }

    /**
     * Définit la valeur de la propriété gpsLatitudeRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getGpsLatitudeRef()
     */
    public void setGpsLatitudeRef(String value) {
        this.gpsLatitudeRef = value;
    }

    /**
     *  2)Si la longitude est exprimée en degrés, minutes et secondes, le format type est dd, mm, ss. Par ex: "5 23 32.229".
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGpsLongitude() {
        return gpsLongitude;
    }

    /**
     * Définit la valeur de la propriété gpsLongitude.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getGpsLongitude()
     */
    public void setGpsLongitude(String value) {
        this.gpsLongitude = value;
    }

    /**
     * Indique si la longitude est est ou ouest. La valeur 'E' indique la longitude est, et 'W' indique la longitude Ouest.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGpsLongitudeRef() {
        return gpsLongitudeRef;
    }

    /**
     * Définit la valeur de la propriété gpsLongitudeRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getGpsLongitudeRef()
     */
    public void setGpsLongitudeRef(String value) {
        this.gpsLongitudeRef = value;
    }

    /**
     * Heure et Date de la position GPS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGpsDateStamp() {
        return gpsDateStamp;
    }

    /**
     * Définit la valeur de la propriété gpsDateStamp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getGpsDateStamp()
     */
    public void setGpsDateStamp(String value) {
        this.gpsDateStamp = value;
    }

}
