//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package fr.gouv.culture.archivesdefrance.seda.v2;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Informations sur le fichier lui-même (d'un point de vue technique).
 * 
 * <p>Java class for FileInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="FileInfoType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="Filename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="CreatingApplicationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="CreatingApplicationVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="DateCreatedByApplication" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="CreatingOs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="CreatingOsVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="LastModified" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileInfoType", propOrder = {
    "filename",
    "creatingApplicationName",
    "creatingApplicationVersion",
    "dateCreatedByApplication",
    "creatingOs",
    "creatingOsVersion",
    "lastModified"
})
public class FileInfoType {

    @XmlElement(name = "Filename", required = true)
    protected String filename;
    @XmlElement(name = "CreatingApplicationName")
    protected String creatingApplicationName;
    @XmlElement(name = "CreatingApplicationVersion")
    protected String creatingApplicationVersion;
    @XmlElement(name = "DateCreatedByApplication")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateCreatedByApplication;
    @XmlElement(name = "CreatingOs")
    protected String creatingOs;
    @XmlElement(name = "CreatingOsVersion")
    protected String creatingOsVersion;
    @XmlElement(name = "LastModified")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModified;

    /**
     * Gets the value of the filename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Sets the value of the filename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilename(String value) {
        this.filename = value;
    }

    /**
     * Gets the value of the creatingApplicationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatingApplicationName() {
        return creatingApplicationName;
    }

    /**
     * Sets the value of the creatingApplicationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatingApplicationName(String value) {
        this.creatingApplicationName = value;
    }

    /**
     * Gets the value of the creatingApplicationVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatingApplicationVersion() {
        return creatingApplicationVersion;
    }

    /**
     * Sets the value of the creatingApplicationVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatingApplicationVersion(String value) {
        this.creatingApplicationVersion = value;
    }

    /**
     * Gets the value of the dateCreatedByApplication property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateCreatedByApplication() {
        return dateCreatedByApplication;
    }

    /**
     * Sets the value of the dateCreatedByApplication property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateCreatedByApplication(XMLGregorianCalendar value) {
        this.dateCreatedByApplication = value;
    }

    /**
     * Gets the value of the creatingOs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatingOs() {
        return creatingOs;
    }

    /**
     * Sets the value of the creatingOs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatingOs(String value) {
        this.creatingOs = value;
    }

    /**
     * Gets the value of the creatingOsVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatingOsVersion() {
        return creatingOsVersion;
    }

    /**
     * Sets the value of the creatingOsVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatingOsVersion(String value) {
        this.creatingOsVersion = value;
    }

    /**
     * Gets the value of the lastModified property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastModified() {
        return lastModified;
    }

    /**
     * Sets the value of the lastModified property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastModified(XMLGregorianCalendar value) {
        this.lastModified = value;
    }

}
