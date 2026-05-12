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
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java pour FormatIdentificationType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="FormatIdentificationType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="FormatLitteral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="MimeType" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}MimeTypeType" minOccurs="0"/>
 *         <element name="FormatId" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}FileFormatType" minOccurs="0"/>
 *         <element name="Encoding" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}EncodingType" minOccurs="0"/>
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
     * Définit la valeur de la propriété formatLitteral.
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
     * Définit la valeur de la propriété mimeType.
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
     * Définit la valeur de la propriété formatId.
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
     * Définit la valeur de la propriété encoding.
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
