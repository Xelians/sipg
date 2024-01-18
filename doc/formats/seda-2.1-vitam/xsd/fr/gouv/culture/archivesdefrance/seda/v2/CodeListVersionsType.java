//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.01.15 at 09:28:44 PM CET 
//


package fr.gouv.culture.archivesdefrance.seda.v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3c.dom.Element;


/**
 * <p>Java class for CodeListVersionsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CodeListVersionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{fr:gouv:culture:archivesdefrance:seda:v2.1}TransportCodeListsGroup"/>
 *         &lt;group ref="{fr:gouv:culture:archivesdefrance:seda:v2.1}TechnicalCodeListsGroup"/>
 *         &lt;group ref="{fr:gouv:culture:archivesdefrance:seda:v2.1}ManagementCodeListsGroup"/>
 *         &lt;element name="AcquisitionInformationCodeListVersion" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}CodeType" minOccurs="0"/>
 *         &lt;element name="AuthorizationReasonCodeListVersion" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}CodeType" minOccurs="0"/>
 *         &lt;element name="RelationshipCodeListVersion" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}CodeType" minOccurs="0"/>
 *         &lt;any processContents='lax' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}id"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CodeListVersionsType", propOrder = {
    "replyCodeListVersion",
    "messageDigestAlgorithmCodeListVersion",
    "mimeTypeCodeListVersion",
    "encodingCodeListVersion",
    "fileFormatCodeListVersion",
    "compressionAlgorithmCodeListVersion",
    "dataObjectVersionCodeListVersion",
    "storageRuleCodeListVersion",
    "appraisalRuleCodeListVersion",
    "accessRuleCodeListVersion",
    "disseminationRuleCodeListVersion",
    "reuseRuleCodeListVersion",
    "classificationRuleCodeListVersion",
    "acquisitionInformationCodeListVersion",
    "authorizationReasonCodeListVersion",
    "relationshipCodeListVersion",
    "any"
})
public class CodeListVersionsType {

    @XmlElement(name = "ReplyCodeListVersion")
    protected CodeType replyCodeListVersion;
    @XmlElement(name = "MessageDigestAlgorithmCodeListVersion")
    protected CodeType messageDigestAlgorithmCodeListVersion;
    @XmlElement(name = "MimeTypeCodeListVersion")
    protected CodeType mimeTypeCodeListVersion;
    @XmlElement(name = "EncodingCodeListVersion")
    protected CodeType encodingCodeListVersion;
    @XmlElement(name = "FileFormatCodeListVersion")
    protected CodeType fileFormatCodeListVersion;
    @XmlElement(name = "CompressionAlgorithmCodeListVersion")
    protected CodeType compressionAlgorithmCodeListVersion;
    @XmlElement(name = "DataObjectVersionCodeListVersion")
    protected CodeType dataObjectVersionCodeListVersion;
    @XmlElement(name = "StorageRuleCodeListVersion")
    protected CodeType storageRuleCodeListVersion;
    @XmlElement(name = "AppraisalRuleCodeListVersion")
    protected CodeType appraisalRuleCodeListVersion;
    @XmlElement(name = "AccessRuleCodeListVersion")
    protected CodeType accessRuleCodeListVersion;
    @XmlElement(name = "DisseminationRuleCodeListVersion")
    protected CodeType disseminationRuleCodeListVersion;
    @XmlElement(name = "ReuseRuleCodeListVersion")
    protected CodeType reuseRuleCodeListVersion;
    @XmlElement(name = "ClassificationRuleCodeListVersion")
    protected CodeType classificationRuleCodeListVersion;
    @XmlElement(name = "AcquisitionInformationCodeListVersion")
    protected CodeType acquisitionInformationCodeListVersion;
    @XmlElement(name = "AuthorizationReasonCodeListVersion")
    protected CodeType authorizationReasonCodeListVersion;
    @XmlElement(name = "RelationshipCodeListVersion")
    protected CodeType relationshipCodeListVersion;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "id", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the replyCodeListVersion property.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getReplyCodeListVersion() {
        return replyCodeListVersion;
    }

    /**
     * Sets the value of the replyCodeListVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setReplyCodeListVersion(CodeType value) {
        this.replyCodeListVersion = value;
    }

    /**
     * Gets the value of the messageDigestAlgorithmCodeListVersion property.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getMessageDigestAlgorithmCodeListVersion() {
        return messageDigestAlgorithmCodeListVersion;
    }

    /**
     * Sets the value of the messageDigestAlgorithmCodeListVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setMessageDigestAlgorithmCodeListVersion(CodeType value) {
        this.messageDigestAlgorithmCodeListVersion = value;
    }

    /**
     * Gets the value of the mimeTypeCodeListVersion property.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getMimeTypeCodeListVersion() {
        return mimeTypeCodeListVersion;
    }

    /**
     * Sets the value of the mimeTypeCodeListVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setMimeTypeCodeListVersion(CodeType value) {
        this.mimeTypeCodeListVersion = value;
    }

    /**
     * Gets the value of the encodingCodeListVersion property.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getEncodingCodeListVersion() {
        return encodingCodeListVersion;
    }

    /**
     * Sets the value of the encodingCodeListVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setEncodingCodeListVersion(CodeType value) {
        this.encodingCodeListVersion = value;
    }

    /**
     * Gets the value of the fileFormatCodeListVersion property.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getFileFormatCodeListVersion() {
        return fileFormatCodeListVersion;
    }

    /**
     * Sets the value of the fileFormatCodeListVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setFileFormatCodeListVersion(CodeType value) {
        this.fileFormatCodeListVersion = value;
    }

    /**
     * Gets the value of the compressionAlgorithmCodeListVersion property.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getCompressionAlgorithmCodeListVersion() {
        return compressionAlgorithmCodeListVersion;
    }

    /**
     * Sets the value of the compressionAlgorithmCodeListVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setCompressionAlgorithmCodeListVersion(CodeType value) {
        this.compressionAlgorithmCodeListVersion = value;
    }

    /**
     * Gets the value of the dataObjectVersionCodeListVersion property.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getDataObjectVersionCodeListVersion() {
        return dataObjectVersionCodeListVersion;
    }

    /**
     * Sets the value of the dataObjectVersionCodeListVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setDataObjectVersionCodeListVersion(CodeType value) {
        this.dataObjectVersionCodeListVersion = value;
    }

    /**
     * Gets the value of the storageRuleCodeListVersion property.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getStorageRuleCodeListVersion() {
        return storageRuleCodeListVersion;
    }

    /**
     * Sets the value of the storageRuleCodeListVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setStorageRuleCodeListVersion(CodeType value) {
        this.storageRuleCodeListVersion = value;
    }

    /**
     * Gets the value of the appraisalRuleCodeListVersion property.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getAppraisalRuleCodeListVersion() {
        return appraisalRuleCodeListVersion;
    }

    /**
     * Sets the value of the appraisalRuleCodeListVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setAppraisalRuleCodeListVersion(CodeType value) {
        this.appraisalRuleCodeListVersion = value;
    }

    /**
     * Gets the value of the accessRuleCodeListVersion property.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getAccessRuleCodeListVersion() {
        return accessRuleCodeListVersion;
    }

    /**
     * Sets the value of the accessRuleCodeListVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setAccessRuleCodeListVersion(CodeType value) {
        this.accessRuleCodeListVersion = value;
    }

    /**
     * Gets the value of the disseminationRuleCodeListVersion property.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getDisseminationRuleCodeListVersion() {
        return disseminationRuleCodeListVersion;
    }

    /**
     * Sets the value of the disseminationRuleCodeListVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setDisseminationRuleCodeListVersion(CodeType value) {
        this.disseminationRuleCodeListVersion = value;
    }

    /**
     * Gets the value of the reuseRuleCodeListVersion property.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getReuseRuleCodeListVersion() {
        return reuseRuleCodeListVersion;
    }

    /**
     * Sets the value of the reuseRuleCodeListVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setReuseRuleCodeListVersion(CodeType value) {
        this.reuseRuleCodeListVersion = value;
    }

    /**
     * Gets the value of the classificationRuleCodeListVersion property.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getClassificationRuleCodeListVersion() {
        return classificationRuleCodeListVersion;
    }

    /**
     * Sets the value of the classificationRuleCodeListVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setClassificationRuleCodeListVersion(CodeType value) {
        this.classificationRuleCodeListVersion = value;
    }

    /**
     * Gets the value of the acquisitionInformationCodeListVersion property.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getAcquisitionInformationCodeListVersion() {
        return acquisitionInformationCodeListVersion;
    }

    /**
     * Sets the value of the acquisitionInformationCodeListVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setAcquisitionInformationCodeListVersion(CodeType value) {
        this.acquisitionInformationCodeListVersion = value;
    }

    /**
     * Gets the value of the authorizationReasonCodeListVersion property.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getAuthorizationReasonCodeListVersion() {
        return authorizationReasonCodeListVersion;
    }

    /**
     * Sets the value of the authorizationReasonCodeListVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setAuthorizationReasonCodeListVersion(CodeType value) {
        this.authorizationReasonCodeListVersion = value;
    }

    /**
     * Gets the value of the relationshipCodeListVersion property.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getRelationshipCodeListVersion() {
        return relationshipCodeListVersion;
    }

    /**
     * Sets the value of the relationshipCodeListVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setRelationshipCodeListVersion(CodeType value) {
        this.relationshipCodeListVersion = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     * {@link Object }
     * 
     * 
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
