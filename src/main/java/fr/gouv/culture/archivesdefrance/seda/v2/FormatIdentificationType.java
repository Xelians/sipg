//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package fr.gouv.culture.archivesdefrance.seda.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for FormatIdentificationType complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="FormatIdentificationType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="FormatLitteral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="MimeType" type="{fr:gouv:culture:archivesdefrance:seda:v2}MimeTypeType" minOccurs="0"/>
 *         <element name="FormatId" type="{fr:gouv:culture:archivesdefrance:seda:v2}FileFormatType" minOccurs="0"/>
 *         <element name="Encoding" type="{fr:gouv:culture:archivesdefrance:seda:v2}EncodingType" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FormatIdentificationType", propOrder = {
    "formatLitteral",
    "mimeType",
    "formatId",
    "encoding"
})
public class FormatIdentificationType {

    /**
     * Exemple : Microsoft Word Document.
     * 
     */
    @XmlElement(name = "FormatLitteral")
    protected String formatLitteral;
    /**
     * Exemple : application/msword
     * 
     */
    @XmlElement(name = "MimeType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String mimeType;
    /**
     * Exemple : (Pronom)fmt/40
     * 
     */
    @XmlElement(name = "FormatId")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String formatId;
    /**
     * Exemple : Utf-8
     * 
     */
    @XmlElement(name = "Encoding")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String encoding;

    /**
     * Exemple : Microsoft Word Document.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatLitteral() {
        return formatLitteral;
    }

    /**
     * Sets the value of the formatLitteral property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getFormatLitteral()
     */
    public void setFormatLitteral(String value) {
        this.formatLitteral = value;
    }

    /**
     * Exemple : application/msword
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Sets the value of the mimeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getMimeType()
     */
    public void setMimeType(String value) {
        this.mimeType = value;
    }

    /**
     * Exemple : (Pronom)fmt/40
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatId() {
        return formatId;
    }

    /**
     * Sets the value of the formatId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getFormatId()
     */
    public void setFormatId(String value) {
        this.formatId = value;
    }

    /**
     * Exemple : Utf-8
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * Sets the value of the encoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getEncoding()
     */
    public void setEncoding(String value) {
        this.encoding = value;
    }

}
