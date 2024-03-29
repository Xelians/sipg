//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package fr.gouv.culture.archivesdefrance.seda.v2;

import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GpsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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

    @XmlElement(name = "GpsVersionID")
    protected String gpsVersionID;
    @XmlElement(name = "GpsAltitude")
    protected BigInteger gpsAltitude;
    @XmlElement(name = "GpsAltitudeRef")
    protected String gpsAltitudeRef;
    @XmlElement(name = "GpsLatitude")
    protected String gpsLatitude;
    @XmlElement(name = "GpsLatitudeRef")
    protected String gpsLatitudeRef;
    @XmlElement(name = "GpsLongitude")
    protected String gpsLongitude;
    @XmlElement(name = "GpsLongitudeRef")
    protected String gpsLongitudeRef;
    @XmlElement(name = "GpsDateStamp")
    protected String gpsDateStamp;

    /**
     * Gets the value of the gpsVersionID property.
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
     * Sets the value of the gpsVersionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGpsVersionID(String value) {
        this.gpsVersionID = value;
    }

    /**
     * Gets the value of the gpsAltitude property.
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
     * Sets the value of the gpsAltitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setGpsAltitude(BigInteger value) {
        this.gpsAltitude = value;
    }

    /**
     * Gets the value of the gpsAltitudeRef property.
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
     * Sets the value of the gpsAltitudeRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGpsAltitudeRef(String value) {
        this.gpsAltitudeRef = value;
    }

    /**
     * Gets the value of the gpsLatitude property.
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
     * Sets the value of the gpsLatitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGpsLatitude(String value) {
        this.gpsLatitude = value;
    }

    /**
     * Gets the value of the gpsLatitudeRef property.
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
     * Sets the value of the gpsLatitudeRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGpsLatitudeRef(String value) {
        this.gpsLatitudeRef = value;
    }

    /**
     * Gets the value of the gpsLongitude property.
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
     * Sets the value of the gpsLongitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGpsLongitude(String value) {
        this.gpsLongitude = value;
    }

    /**
     * Gets the value of the gpsLongitudeRef property.
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
     * Sets the value of the gpsLongitudeRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGpsLongitudeRef(String value) {
        this.gpsLongitudeRef = value;
    }

    /**
     * Gets the value of the gpsDateStamp property.
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
     * Sets the value of the gpsDateStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGpsDateStamp(String value) {
        this.gpsDateStamp = value;
    }

}
