//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.09.15 at 07:07:47 PM CEST 
//
package org.afnor.medona.v1;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.math.BigDecimal;

/**
 * <p>
 * Java class for BinaryDataObjectType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="BinaryDataObjectType">
 *   &lt;complexContent>
 *     &lt;extension base="{org:afnor:medona:v1.0}DataObjectType">
 *       &lt;sequence>
 *         &lt;element name="Attachment" type="{org:afnor:medona:v1.0}BinaryObjectType"/>
 *         &lt;element name="Format" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="MessageDigest" type="{org:afnor:medona:v1.0}MessageDigestBinaryObjectType"/>
 *         &lt;element name="SignatureStatus" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="Size" type="{org:afnor:medona:v1.0}SizeInBytesType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BinaryDataObjectType", propOrder = {
    "attachment",
    "format",
    "messageDigest",
    "signatureStatus",
    "size"
})
public class BinaryDataObjectType
        extends DataObjectType {

    /**
     * The Attachment.
     */
    @XmlElement(name = "Attachment", required = true)
    protected BinaryObjectType attachment;
    /**
     * The Format.
     */
    @XmlElement(name = "Format", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String format;
    /**
     * The Message digest.
     */
    @XmlElement(name = "MessageDigest", required = true)
    protected MessageDigestBinaryObjectType messageDigest;
    /**
     * The Signature status.
     */
    @XmlElement(name = "SignatureStatus", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String signatureStatus;
    /**
     * The Size.
     */
    @XmlElement(name = "Size", required = true)
    protected BigDecimal size;

    /**
     * Gets the value of the attachment property.
     *
     * @return possible object is {@link BinaryObjectType }
     */
    public BinaryObjectType getAttachment() {
        return attachment;
    }

    /**
     * Sets the value of the attachment property.
     *
     * @param value allowed object is {@link BinaryObjectType }
     */
    public void setAttachment(BinaryObjectType value) {
        this.attachment = value;
    }

    /**
     * Gets the value of the format property.
     *
     * @return possible object is {@link String }
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the value of the format property.
     *
     * @param value allowed object is {@link String }
     */
    public void setFormat(String value) {
        this.format = value;
    }

    /**
     * Gets the value of the messageDigest property.
     *
     * @return possible object is {@link MessageDigestBinaryObjectType }
     */
    public MessageDigestBinaryObjectType getMessageDigest() {
        return messageDigest;
    }

    /**
     * Sets the value of the messageDigest property.
     *
     * @param value allowed object is {@link MessageDigestBinaryObjectType }
     */
    public void setMessageDigest(MessageDigestBinaryObjectType value) {
        this.messageDigest = value;
    }

    /**
     * Gets the value of the signatureStatus property.
     *
     * @return possible object is {@link String }
     */
    public String getSignatureStatus() {
        return signatureStatus;
    }

    /**
     * Sets the value of the signatureStatus property.
     *
     * @param value allowed object is {@link String }
     */
    public void setSignatureStatus(String value) {
        this.signatureStatus = value;
    }

    /**
     * Gets the value of the size property.
     *
     * @return possible object is {@link BigDecimal }
     */
    public BigDecimal getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     *
     * @param value allowed object is {@link BigDecimal }
     */
    public void setSize(BigDecimal value) {
        this.size = value;
    }

}
