//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.01.15 at 09:28:44 PM CET 
//


package fr.gouv.culture.archivesdefrance.seda.v2;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Objet-données numérique.
 * 
 * Métadonnées techniques minimales : URI, Digest, Poids.
 * 
 * <p>Java class for BinaryDataObjectType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BinaryDataObjectType">
 *   &lt;complexContent>
 *     &lt;extension base="{fr:gouv:culture:archivesdefrance:seda:v2.1}MinimalDataObjectType">
 *       &lt;sequence>
 *         &lt;group ref="{fr:gouv:culture:archivesdefrance:seda:v2.1}MinimalBinaryDataObjectGroup" minOccurs="0"/>
 *         &lt;element name="Size" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}SizeInBytesType" minOccurs="0"/>
 *         &lt;element name="Compressed" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}CompressedType" minOccurs="0"/>
 *         &lt;group ref="{fr:gouv:culture:archivesdefrance:seda:v2.1}BinaryTechnicalDescriptionGroup"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BinaryDataObjectType", propOrder = {
    "attachment",
    "uri",
    "messageDigest",
    "size",
    "compressed",
    "formatIdentification",
    "fileInfo",
    "metadata",
    "otherMetadata"
})
public class BinaryDataObjectType
    extends MinimalDataObjectType
{

    @XmlElement(name = "Attachment")
    protected BinaryObjectType attachment;
    @XmlElement(name = "Uri")
    @XmlSchemaType(name = "anyURI")
    protected String uri;
    @XmlElement(name = "MessageDigest")
    protected MessageDigestBinaryObjectType messageDigest;
    @XmlElement(name = "Size")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger size;
    @XmlElement(name = "Compressed")
    protected CompressedType compressed;
    @XmlElement(name = "FormatIdentification")
    protected FormatIdentificationType formatIdentification;
    @XmlElement(name = "FileInfo")
    protected FileInfoType fileInfo;
    @XmlElement(name = "Metadata")
    protected CoreMetadataType metadata;
    @XmlElement(name = "OtherMetadata")
    protected DescriptiveTechnicalMetadataType otherMetadata;

    /**
     * Gets the value of the attachment property.
     * 
     * @return
     *     possible object is
     *     {@link BinaryObjectType }
     *     
     */
    public BinaryObjectType getAttachment() {
        return attachment;
    }

    /**
     * Sets the value of the attachment property.
     * 
     * @param value
     *     allowed object is
     *     {@link BinaryObjectType }
     *     
     */
    public void setAttachment(BinaryObjectType value) {
        this.attachment = value;
    }

    /**
     * Gets the value of the uri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets the value of the uri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUri(String value) {
        this.uri = value;
    }

    /**
     * Gets the value of the messageDigest property.
     * 
     * @return
     *     possible object is
     *     {@link MessageDigestBinaryObjectType }
     *     
     */
    public MessageDigestBinaryObjectType getMessageDigest() {
        return messageDigest;
    }

    /**
     * Sets the value of the messageDigest property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageDigestBinaryObjectType }
     *     
     */
    public void setMessageDigest(MessageDigestBinaryObjectType value) {
        this.messageDigest = value;
    }

    /**
     * Gets the value of the size property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSize(BigInteger value) {
        this.size = value;
    }

    /**
     * Gets the value of the compressed property.
     * 
     * @return
     *     possible object is
     *     {@link CompressedType }
     *     
     */
    public CompressedType getCompressed() {
        return compressed;
    }

    /**
     * Sets the value of the compressed property.
     * 
     * @param value
     *     allowed object is
     *     {@link CompressedType }
     *     
     */
    public void setCompressed(CompressedType value) {
        this.compressed = value;
    }

    /**
     * Gets the value of the formatIdentification property.
     * 
     * @return
     *     possible object is
     *     {@link FormatIdentificationType }
     *     
     */
    public FormatIdentificationType getFormatIdentification() {
        return formatIdentification;
    }

    /**
     * Sets the value of the formatIdentification property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormatIdentificationType }
     *     
     */
    public void setFormatIdentification(FormatIdentificationType value) {
        this.formatIdentification = value;
    }

    /**
     * Gets the value of the fileInfo property.
     * 
     * @return
     *     possible object is
     *     {@link FileInfoType }
     *     
     */
    public FileInfoType getFileInfo() {
        return fileInfo;
    }

    /**
     * Sets the value of the fileInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileInfoType }
     *     
     */
    public void setFileInfo(FileInfoType value) {
        this.fileInfo = value;
    }

    /**
     * Gets the value of the metadata property.
     * 
     * @return
     *     possible object is
     *     {@link CoreMetadataType }
     *     
     */
    public CoreMetadataType getMetadata() {
        return metadata;
    }

    /**
     * Sets the value of the metadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoreMetadataType }
     *     
     */
    public void setMetadata(CoreMetadataType value) {
        this.metadata = value;
    }

    /**
     * Gets the value of the otherMetadata property.
     * 
     * @return
     *     possible object is
     *     {@link DescriptiveTechnicalMetadataType }
     *     
     */
    public DescriptiveTechnicalMetadataType getOtherMetadata() {
        return otherMetadata;
    }

    /**
     * Sets the value of the otherMetadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link DescriptiveTechnicalMetadataType }
     *     
     */
    public void setOtherMetadata(DescriptiveTechnicalMetadataType value) {
        this.otherMetadata = value;
    }

}
