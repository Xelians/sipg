//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:05:56 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Informations sur le fichier lui-même (d'un point de vue technique).
 * 
 * <p>Classe Java pour FileInfoType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
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

    /**
     * Nom du fichier d'origine.
     * 
     */
    @XmlElement(name = "Filename", required = true)
    protected String filename;
    /**
     * Nom de l'application utilisée pour créer le fichier.
     * 
     */
    @XmlElement(name = "CreatingApplicationName")
    protected String creatingApplicationName;
    /**
     * Version de l'application utilisée pour créer le fichier.
     * 
     */
    @XmlElement(name = "CreatingApplicationVersion")
    protected String creatingApplicationVersion;
    /**
     * Date de création du fichier.
     * 
     */
    @XmlElement(name = "DateCreatedByApplication")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateCreatedByApplication;
    /**
     * Système d’exploitation utilisé pour créer le fichier.
     * 
     */
    @XmlElement(name = "CreatingOs")
    protected String creatingOs;
    /**
     * Version du système d'exploitation utilisé pour créer le fichier.
     * 
     */
    @XmlElement(name = "CreatingOsVersion")
    protected String creatingOsVersion;
    /**
     * Date de la dernière modification du fichier.
     * 
     */
    @XmlElement(name = "LastModified")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModified;

    /**
     * Nom du fichier d'origine.
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
     * Définit la valeur de la propriété filename.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getFilename()
     */
    public void setFilename(String value) {
        this.filename = value;
    }

    /**
     * Nom de l'application utilisée pour créer le fichier.
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
     * Définit la valeur de la propriété creatingApplicationName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getCreatingApplicationName()
     */
    public void setCreatingApplicationName(String value) {
        this.creatingApplicationName = value;
    }

    /**
     * Version de l'application utilisée pour créer le fichier.
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
     * Définit la valeur de la propriété creatingApplicationVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getCreatingApplicationVersion()
     */
    public void setCreatingApplicationVersion(String value) {
        this.creatingApplicationVersion = value;
    }

    /**
     * Date de création du fichier.
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
     * Définit la valeur de la propriété dateCreatedByApplication.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     * @see #getDateCreatedByApplication()
     */
    public void setDateCreatedByApplication(XMLGregorianCalendar value) {
        this.dateCreatedByApplication = value;
    }

    /**
     * Système d’exploitation utilisé pour créer le fichier.
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
     * Définit la valeur de la propriété creatingOs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getCreatingOs()
     */
    public void setCreatingOs(String value) {
        this.creatingOs = value;
    }

    /**
     * Version du système d'exploitation utilisé pour créer le fichier.
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
     * Définit la valeur de la propriété creatingOsVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getCreatingOsVersion()
     */
    public void setCreatingOsVersion(String value) {
        this.creatingOsVersion = value;
    }

    /**
     * Date de la dernière modification du fichier.
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
     * Définit la valeur de la propriété lastModified.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     * @see #getLastModified()
     */
    public void setLastModified(XMLGregorianCalendar value) {
        this.lastModified = value;
    }

}
