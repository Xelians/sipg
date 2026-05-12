//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:09:37 PM CEST
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
 * <p>Classe Java pour CodeListVersionsType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
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
     * &lt;pre&gt;
     * &amp;lt;?xml version="1.0" encoding="UTF-8"?&amp;gt;&amp;lt;div xmlns="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&amp;gt;&amp;lt;h3&amp;gt;id (as an attribute name)&amp;lt;/h3&amp;gt;&amp;lt;p&amp;gt;
     *        denotes an attribute whose value
     *        should be interpreted as if declared to be of type ID.
     *        This name is reserved by virtue of its definition in the
     *        xml:id specification.&amp;lt;/p&amp;gt;&amp;lt;p&amp;gt;
     *       See &amp;lt;a href="http://www.w3.org/TR/xml-id/"&amp;gt;http://www.w3.org/TR/xml-id/&amp;lt;/a&amp;gt;
     *       for information about this attribute.
     *      &amp;lt;/p&amp;gt;&amp;lt;/div&amp;gt;
     * &lt;/pre&gt;
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
     * Définit la valeur de la propriété replyCodeListVersion.
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
     * Définit la valeur de la propriété messageDigestAlgorithmCodeListVersion.
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
     * Définit la valeur de la propriété mimeTypeCodeListVersion.
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
     * Définit la valeur de la propriété encodingCodeListVersion.
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
     * Définit la valeur de la propriété fileFormatCodeListVersion.
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
     * Définit la valeur de la propriété compressionAlgorithmCodeListVersion.
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
     * Définit la valeur de la propriété dataObjectVersionCodeListVersion.
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
     * Définit la valeur de la propriété storageRuleCodeListVersion.
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
     * Définit la valeur de la propriété appraisalRuleCodeListVersion.
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
     * Définit la valeur de la propriété accessRuleCodeListVersion.
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
     * Définit la valeur de la propriété disseminationRuleCodeListVersion.
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
     * Définit la valeur de la propriété reuseRuleCodeListVersion.
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
     * Définit la valeur de la propriété classificationRuleCodeListVersion.
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
     * Définit la valeur de la propriété holdRuleCodeListVersion.
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
     * Définit la valeur de la propriété acquisitionInformationCodeListVersion.
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
     * Définit la valeur de la propriété authorizationReasonCodeListVersion.
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
     * Définit la valeur de la propriété relationshipCodeListVersion.
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
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the any property.</p>
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
     * &lt;pre&gt;
     * &amp;lt;?xml version="1.0" encoding="UTF-8"?&amp;gt;&amp;lt;div xmlns="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&amp;gt;&amp;lt;h3&amp;gt;id (as an attribute name)&amp;lt;/h3&amp;gt;&amp;lt;p&amp;gt;
     *        denotes an attribute whose value
     *        should be interpreted as if declared to be of type ID.
     *        This name is reserved by virtue of its definition in the
     *        xml:id specification.&amp;lt;/p&amp;gt;&amp;lt;p&amp;gt;
     *       See &amp;lt;a href="http://www.w3.org/TR/xml-id/"&amp;gt;http://www.w3.org/TR/xml-id/&amp;lt;/a&amp;gt;
     *       for information about this attribute.
     *      &amp;lt;/p&amp;gt;&amp;lt;/div&amp;gt;
     * &lt;/pre&gt;
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
     * Définit la valeur de la propriété id.
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
