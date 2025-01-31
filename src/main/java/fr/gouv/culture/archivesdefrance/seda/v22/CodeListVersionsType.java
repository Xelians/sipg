//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package fr.gouv.culture.archivesdefrance.seda.v22;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3c.dom.Element;


/**
 * <p>Java class for CodeListVersionsType complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="CodeListVersionsType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <group ref="{fr:gouv:culture:archivesdefrance:seda:v2.2}TransportCodeListsGroup"/>
 *         <group ref="{fr:gouv:culture:archivesdefrance:seda:v2.2}TechnicalCodeListsGroup"/>
 *         <group ref="{fr:gouv:culture:archivesdefrance:seda:v2.2}ManagementCodeListsGroup"/>
 *         <element name="AcquisitionInformationCodeListVersion" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}CodeType" minOccurs="0"/>
 *         <element name="AuthorizationReasonCodeListVersion" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}CodeType" minOccurs="0"/>
 *         <element name="RelationshipCodeListVersion" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}CodeType" minOccurs="0"/>
 *         <any processContents='lax' maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *       <attribute ref="{http://www.w3.org/XML/1998/namespace}id"/>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
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
    "holdRuleCodeListVersion",
    "acquisitionInformationCodeListVersion",
    "authorizationReasonCodeListVersion",
    "relationshipCodeListVersion",
    "any"
})
public class CodeListVersionsType {

    /**
     * Liste des codes de réponses à utiliser.
     * 
     */
    @XmlElement(name = "ReplyCodeListVersion")
    protected CodeType replyCodeListVersion;
    /**
     * Liste de l'algorithme de hachage utilisé dans le message.
     * 
     */
    @XmlElement(name = "MessageDigestAlgorithmCodeListVersion")
    protected CodeType messageDigestAlgorithmCodeListVersion;
    /**
     * Version de la liste de code du type Mime.
     * 
     */
    @XmlElement(name = "MimeTypeCodeListVersion")
    protected CodeType mimeTypeCodeListVersion;
    /**
     * Version de la liste de code d'encodage du
     *                         fichier.
     * 
     */
    @XmlElement(name = "EncodingCodeListVersion")
    protected CodeType encodingCodeListVersion;
    /**
     * Version de la liste de code d'identification du format.
     * 
     */
    @XmlElement(name = "FileFormatCodeListVersion")
    protected CodeType fileFormatCodeListVersion;
    /**
     * Version de la liste de code de l'algorithme de compression.
     * 
     */
    @XmlElement(name = "CompressionAlgorithmCodeListVersion")
    protected CodeType compressionAlgorithmCodeListVersion;
    /**
     * Liste de codes correspondant aux diverses versions d'un objet-données au sein d’un groupe d'objets-données (ex. original papier, conservation, diffusion, vignette, txt).
     * 
     */
    @XmlElement(name = "DataObjectVersionCodeListVersion")
    protected CodeType dataObjectVersionCodeListVersion;
    /**
     * Version des listes de codes pour les règles de durée d'utilité courante.
     * 
     */
    @XmlElement(name = "StorageRuleCodeListVersion")
    protected CodeType storageRuleCodeListVersion;
    /**
     * Version des listes de codes pour les règles de durée d'utilité administrative.
     * 
     */
    @XmlElement(name = "AppraisalRuleCodeListVersion")
    protected CodeType appraisalRuleCodeListVersion;
    /**
     * Version des listes de codes pour les règles de communicabilité.
     * 
     */
    @XmlElement(name = "AccessRuleCodeListVersion")
    protected CodeType accessRuleCodeListVersion;
    /**
     * Version des listes de codes pour les règles de diffusion.
     * 
     */
    @XmlElement(name = "DisseminationRuleCodeListVersion")
    protected CodeType disseminationRuleCodeListVersion;
    /**
     * Version des listes de codes pour les règles de réutilisation.
     * 
     */
    @XmlElement(name = "ReuseRuleCodeListVersion")
    protected CodeType reuseRuleCodeListVersion;
    /**
     * Version des listes de codes pour les règles de classification.
     * 
     */
    @XmlElement(name = "ClassificationRuleCodeListVersion")
    protected CodeType classificationRuleCodeListVersion;
    /**
     * Version des listes de codes pour les règles de gel.
     * 
     */
    @XmlElement(name = "HoldRuleCodeListVersion")
    protected CodeType holdRuleCodeListVersion;
    /**
     * Version de la liste de codes des modalités
     *                         d'entrée.
     * 
     */
    @XmlElement(name = "AcquisitionInformationCodeListVersion")
    protected CodeType acquisitionInformationCodeListVersion;
    /**
     * Version de la liste de codes
     *                         d'autorisation.
     * 
     */
    @XmlElement(name = "AuthorizationReasonCodeListVersion")
    protected CodeType authorizationReasonCodeListVersion;
    /**
     * Version de la liste de codes des
     *                         relations.
     * 
     */
    @XmlElement(name = "RelationshipCodeListVersion")
    protected CodeType relationshipCodeListVersion;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    /**
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;div xmlns="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;&lt;h3&gt;id (as an attribute name)&lt;/h3&gt;&lt;p&gt;
     *        denotes an attribute whose value
     *        should be interpreted as if declared to be of type ID.
     *        This name is reserved by virtue of its definition in the
     *        xml:id specification.&lt;/p&gt;&lt;p&gt;
     *       See &lt;a href="http://www.w3.org/TR/xml-id/"&gt;http://www.w3.org/TR/xml-id/&lt;/a&gt;
     *       for information about this attribute.&lt;/p&gt;&lt;/div&gt;
     * </pre>
     * 
     */
    @XmlAttribute(name = "id", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Liste des codes de réponses à utiliser.
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
     * @see #getReplyCodeListVersion()
     */
    public void setReplyCodeListVersion(CodeType value) {
        this.replyCodeListVersion = value;
    }

    /**
     * Liste de l'algorithme de hachage utilisé dans le message.
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
     * @see #getMessageDigestAlgorithmCodeListVersion()
     */
    public void setMessageDigestAlgorithmCodeListVersion(CodeType value) {
        this.messageDigestAlgorithmCodeListVersion = value;
    }

    /**
     * Version de la liste de code du type Mime.
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
     * @see #getMimeTypeCodeListVersion()
     */
    public void setMimeTypeCodeListVersion(CodeType value) {
        this.mimeTypeCodeListVersion = value;
    }

    /**
     * Version de la liste de code d'encodage du
     *                         fichier.
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
     * @see #getEncodingCodeListVersion()
     */
    public void setEncodingCodeListVersion(CodeType value) {
        this.encodingCodeListVersion = value;
    }

    /**
     * Version de la liste de code d'identification du format.
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
     * @see #getFileFormatCodeListVersion()
     */
    public void setFileFormatCodeListVersion(CodeType value) {
        this.fileFormatCodeListVersion = value;
    }

    /**
     * Version de la liste de code de l'algorithme de compression.
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
     * @see #getCompressionAlgorithmCodeListVersion()
     */
    public void setCompressionAlgorithmCodeListVersion(CodeType value) {
        this.compressionAlgorithmCodeListVersion = value;
    }

    /**
     * Liste de codes correspondant aux diverses versions d'un objet-données au sein d’un groupe d'objets-données (ex. original papier, conservation, diffusion, vignette, txt).
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
     * @see #getDataObjectVersionCodeListVersion()
     */
    public void setDataObjectVersionCodeListVersion(CodeType value) {
        this.dataObjectVersionCodeListVersion = value;
    }

    /**
     * Version des listes de codes pour les règles de durée d'utilité courante.
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
     * @see #getStorageRuleCodeListVersion()
     */
    public void setStorageRuleCodeListVersion(CodeType value) {
        this.storageRuleCodeListVersion = value;
    }

    /**
     * Version des listes de codes pour les règles de durée d'utilité administrative.
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
     * @see #getAppraisalRuleCodeListVersion()
     */
    public void setAppraisalRuleCodeListVersion(CodeType value) {
        this.appraisalRuleCodeListVersion = value;
    }

    /**
     * Version des listes de codes pour les règles de communicabilité.
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
     * @see #getAccessRuleCodeListVersion()
     */
    public void setAccessRuleCodeListVersion(CodeType value) {
        this.accessRuleCodeListVersion = value;
    }

    /**
     * Version des listes de codes pour les règles de diffusion.
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
     * @see #getDisseminationRuleCodeListVersion()
     */
    public void setDisseminationRuleCodeListVersion(CodeType value) {
        this.disseminationRuleCodeListVersion = value;
    }

    /**
     * Version des listes de codes pour les règles de réutilisation.
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
     * @see #getReuseRuleCodeListVersion()
     */
    public void setReuseRuleCodeListVersion(CodeType value) {
        this.reuseRuleCodeListVersion = value;
    }

    /**
     * Version des listes de codes pour les règles de classification.
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
     * @see #getClassificationRuleCodeListVersion()
     */
    public void setClassificationRuleCodeListVersion(CodeType value) {
        this.classificationRuleCodeListVersion = value;
    }

    /**
     * Version des listes de codes pour les règles de gel.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getHoldRuleCodeListVersion() {
        return holdRuleCodeListVersion;
    }

    /**
     * Sets the value of the holdRuleCodeListVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     * @see #getHoldRuleCodeListVersion()
     */
    public void setHoldRuleCodeListVersion(CodeType value) {
        this.holdRuleCodeListVersion = value;
    }

    /**
     * Version de la liste de codes des modalités
     *                         d'entrée.
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
     * @see #getAcquisitionInformationCodeListVersion()
     */
    public void setAcquisitionInformationCodeListVersion(CodeType value) {
        this.acquisitionInformationCodeListVersion = value;
    }

    /**
     * Version de la liste de codes
     *                         d'autorisation.
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
     * @see #getAuthorizationReasonCodeListVersion()
     */
    public void setAuthorizationReasonCodeListVersion(CodeType value) {
        this.authorizationReasonCodeListVersion = value;
    }

    /**
     * Version de la liste de codes des
     *                         relations.
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
     * @see #getRelationshipCodeListVersion()
     */
    public void setRelationshipCodeListVersion(CodeType value) {
        this.relationshipCodeListVersion = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * {@link Element }
     * </p>
     * 
     * 
     * @return
     *     The value of the any property.
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<>();
        }
        return this.any;
    }

    /**
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;div xmlns="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;&lt;h3&gt;id (as an attribute name)&lt;/h3&gt;&lt;p&gt;
     *        denotes an attribute whose value
     *        should be interpreted as if declared to be of type ID.
     *        This name is reserved by virtue of its definition in the
     *        xml:id specification.&lt;/p&gt;&lt;p&gt;
     *       See &lt;a href="http://www.w3.org/TR/xml-id/"&gt;http://www.w3.org/TR/xml-id/&lt;/a&gt;
     *       for information about this attribute.&lt;/p&gt;&lt;/div&gt;
     * </pre>
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
     * @see #getId()
     */
    public void setId(String value) {
        this.id = value;
    }

}
