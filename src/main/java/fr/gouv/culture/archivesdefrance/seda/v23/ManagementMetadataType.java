//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package fr.gouv.culture.archivesdefrance.seda.v23;

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
 * <p>Java class for ManagementMetadataType complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="ManagementMetadataType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="ArchivalProfile" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}IdentifierType" minOccurs="0"/>
 *         <element name="ServiceLevel" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}IdentifierType" minOccurs="0"/>
 *         <element name="AcquisitionInformation" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType" minOccurs="0"/>
 *         <element name="LegalStatus" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}LegalStatusType" minOccurs="0"/>
 *         <element name="OriginatingAgencyIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}IdentifierType" minOccurs="0"/>
 *         <element name="SubmissionAgencyIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}IdentifierType" minOccurs="0"/>
 *         <group ref="{fr:gouv:culture:archivesdefrance:seda:v2.3}ManagementGroup" minOccurs="0"/>
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
@XmlType(name = "ManagementMetadataType", propOrder = {
    "archivalProfile",
    "serviceLevel",
    "acquisitionInformation",
    "legalStatus",
    "originatingAgencyIdentifier",
    "submissionAgencyIdentifier",
    "storageRule",
    "appraisalRule",
    "accessRule",
    "disseminationRule",
    "reuseRule",
    "classificationRule",
    "logBook",
    "needAuthorization",
    "holdRule",
    "updateOperation",
    "any"
})
public class ManagementMetadataType {

    /**
     * Profil d’archivage applicable aux
     *                         ArchiveUnit.
     * 
     */
    @XmlElement(name = "ArchivalProfile")
    protected IdentifierType archivalProfile;
    /**
     * Niveau de service applicable aux unités
     *                         d’archives.
     * 
     */
    @XmlElement(name = "ServiceLevel")
    protected IdentifierType serviceLevel;
    /**
     * Modalités d'entrée des archives.
     * 
     */
    @XmlElement(name = "AcquisitionInformation")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String acquisitionInformation;
    /**
     * Statut des archives échangées.
     * 
     */
    @XmlElement(name = "LegalStatus")
    @XmlSchemaType(name = "token")
    protected LegalStatusType legalStatus;
    /**
     * Identifiant du service producteur - information de gestion à
     *                         ne pas confondre avec OriginatingAgency dans les métadonnées de
     *                         description.
     * 
     */
    @XmlElement(name = "OriginatingAgencyIdentifier")
    protected IdentifierType originatingAgencyIdentifier;
    /**
     * Identifiant du service versant - information de gestion à ne
     *                         pas confondre avec SubmissionAgency dans les métadonnées de
     *                         description.
     * 
     */
    @XmlElement(name = "SubmissionAgencyIdentifier")
    protected IdentifierType submissionAgencyIdentifier;
    /**
     * Gestion de la durée d’utilité courante.
     * 
     */
    @XmlElement(name = "StorageRule")
    protected StorageRuleType storageRule;
    /**
     * Gestion de la durée d’utilité administrative.
     * 
     */
    @XmlElement(name = "AppraisalRule")
    protected AppraisalRuleType appraisalRule;
    /**
     * Gestion de la communicabilité.
     * 
     */
    @XmlElement(name = "AccessRule")
    protected AccessRuleType accessRule;
    /**
     * Gestion de la diffusion.
     * 
     */
    @XmlElement(name = "DisseminationRule")
    protected DisseminationRuleType disseminationRule;
    /**
     * Gestion de la réutilisation.
     * 
     */
    @XmlElement(name = "ReuseRule")
    protected ReuseRuleType reuseRule;
    /**
     * Gestion de la classification.
     * 
     */
    @XmlElement(name = "ClassificationRule")
    protected ClassificationRuleType classificationRule;
    /**
     * Gestion des traces.
     * 
     */
    @XmlElement(name = "LogBook")
    protected LogBookType logBook;
    /**
     * Indique si une autorisation humaine est nécessaire pour vérifier ou valider les opérations de gestion des ArchiveUnit.
     * 
     */
    @XmlElement(name = "NeedAuthorization")
    protected Boolean needAuthorization;
    /**
     * Gestion de la durée de gel des ArchiveUnits.
     * 
     */
    @XmlElement(name = "HoldRule")
    protected HoldRuleType holdRule;
    /**
     * Gestion des opérations sur un ArchiveUnit.
     * 
     */
    @XmlElement(name = "UpdateOperation")
    protected UpdateOperationType updateOperation;
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
     * Profil d’archivage applicable aux
     *                         ArchiveUnit.
     * 
     * @return
     *     possible object is
     *     {@link IdentifierType }
     *     
     */
    public IdentifierType getArchivalProfile() {
        return archivalProfile;
    }

    /**
     * Sets the value of the archivalProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifierType }
     *     
     * @see #getArchivalProfile()
     */
    public void setArchivalProfile(IdentifierType value) {
        this.archivalProfile = value;
    }

    /**
     * Niveau de service applicable aux unités
     *                         d’archives.
     * 
     * @return
     *     possible object is
     *     {@link IdentifierType }
     *     
     */
    public IdentifierType getServiceLevel() {
        return serviceLevel;
    }

    /**
     * Sets the value of the serviceLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifierType }
     *     
     * @see #getServiceLevel()
     */
    public void setServiceLevel(IdentifierType value) {
        this.serviceLevel = value;
    }

    /**
     * Modalités d'entrée des archives.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcquisitionInformation() {
        return acquisitionInformation;
    }

    /**
     * Sets the value of the acquisitionInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getAcquisitionInformation()
     */
    public void setAcquisitionInformation(String value) {
        this.acquisitionInformation = value;
    }

    /**
     * Statut des archives échangées.
     * 
     * @return
     *     possible object is
     *     {@link LegalStatusType }
     *     
     */
    public LegalStatusType getLegalStatus() {
        return legalStatus;
    }

    /**
     * Sets the value of the legalStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link LegalStatusType }
     *     
     * @see #getLegalStatus()
     */
    public void setLegalStatus(LegalStatusType value) {
        this.legalStatus = value;
    }

    /**
     * Identifiant du service producteur - information de gestion à
     *                         ne pas confondre avec OriginatingAgency dans les métadonnées de
     *                         description.
     * 
     * @return
     *     possible object is
     *     {@link IdentifierType }
     *     
     */
    public IdentifierType getOriginatingAgencyIdentifier() {
        return originatingAgencyIdentifier;
    }

    /**
     * Sets the value of the originatingAgencyIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifierType }
     *     
     * @see #getOriginatingAgencyIdentifier()
     */
    public void setOriginatingAgencyIdentifier(IdentifierType value) {
        this.originatingAgencyIdentifier = value;
    }

    /**
     * Identifiant du service versant - information de gestion à ne
     *                         pas confondre avec SubmissionAgency dans les métadonnées de
     *                         description.
     * 
     * @return
     *     possible object is
     *     {@link IdentifierType }
     *     
     */
    public IdentifierType getSubmissionAgencyIdentifier() {
        return submissionAgencyIdentifier;
    }

    /**
     * Sets the value of the submissionAgencyIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifierType }
     *     
     * @see #getSubmissionAgencyIdentifier()
     */
    public void setSubmissionAgencyIdentifier(IdentifierType value) {
        this.submissionAgencyIdentifier = value;
    }

    /**
     * Gestion de la durée d’utilité courante.
     * 
     * @return
     *     possible object is
     *     {@link StorageRuleType }
     *     
     */
    public StorageRuleType getStorageRule() {
        return storageRule;
    }

    /**
     * Sets the value of the storageRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link StorageRuleType }
     *     
     * @see #getStorageRule()
     */
    public void setStorageRule(StorageRuleType value) {
        this.storageRule = value;
    }

    /**
     * Gestion de la durée d’utilité administrative.
     * 
     * @return
     *     possible object is
     *     {@link AppraisalRuleType }
     *     
     */
    public AppraisalRuleType getAppraisalRule() {
        return appraisalRule;
    }

    /**
     * Sets the value of the appraisalRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppraisalRuleType }
     *     
     * @see #getAppraisalRule()
     */
    public void setAppraisalRule(AppraisalRuleType value) {
        this.appraisalRule = value;
    }

    /**
     * Gestion de la communicabilité.
     * 
     * @return
     *     possible object is
     *     {@link AccessRuleType }
     *     
     */
    public AccessRuleType getAccessRule() {
        return accessRule;
    }

    /**
     * Sets the value of the accessRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessRuleType }
     *     
     * @see #getAccessRule()
     */
    public void setAccessRule(AccessRuleType value) {
        this.accessRule = value;
    }

    /**
     * Gestion de la diffusion.
     * 
     * @return
     *     possible object is
     *     {@link DisseminationRuleType }
     *     
     */
    public DisseminationRuleType getDisseminationRule() {
        return disseminationRule;
    }

    /**
     * Sets the value of the disseminationRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link DisseminationRuleType }
     *     
     * @see #getDisseminationRule()
     */
    public void setDisseminationRule(DisseminationRuleType value) {
        this.disseminationRule = value;
    }

    /**
     * Gestion de la réutilisation.
     * 
     * @return
     *     possible object is
     *     {@link ReuseRuleType }
     *     
     */
    public ReuseRuleType getReuseRule() {
        return reuseRule;
    }

    /**
     * Sets the value of the reuseRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReuseRuleType }
     *     
     * @see #getReuseRule()
     */
    public void setReuseRule(ReuseRuleType value) {
        this.reuseRule = value;
    }

    /**
     * Gestion de la classification.
     * 
     * @return
     *     possible object is
     *     {@link ClassificationRuleType }
     *     
     */
    public ClassificationRuleType getClassificationRule() {
        return classificationRule;
    }

    /**
     * Sets the value of the classificationRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassificationRuleType }
     *     
     * @see #getClassificationRule()
     */
    public void setClassificationRule(ClassificationRuleType value) {
        this.classificationRule = value;
    }

    /**
     * Gestion des traces.
     * 
     * @return
     *     possible object is
     *     {@link LogBookType }
     *     
     */
    public LogBookType getLogBook() {
        return logBook;
    }

    /**
     * Sets the value of the logBook property.
     * 
     * @param value
     *     allowed object is
     *     {@link LogBookType }
     *     
     * @see #getLogBook()
     */
    public void setLogBook(LogBookType value) {
        this.logBook = value;
    }

    /**
     * Indique si une autorisation humaine est nécessaire pour vérifier ou valider les opérations de gestion des ArchiveUnit.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNeedAuthorization() {
        return needAuthorization;
    }

    /**
     * Sets the value of the needAuthorization property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     * @see #isNeedAuthorization()
     */
    public void setNeedAuthorization(Boolean value) {
        this.needAuthorization = value;
    }

    /**
     * Gestion de la durée de gel des ArchiveUnits.
     * 
     * @return
     *     possible object is
     *     {@link HoldRuleType }
     *     
     */
    public HoldRuleType getHoldRule() {
        return holdRule;
    }

    /**
     * Sets the value of the holdRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link HoldRuleType }
     *     
     * @see #getHoldRule()
     */
    public void setHoldRule(HoldRuleType value) {
        this.holdRule = value;
    }

    /**
     * Gestion des opérations sur un ArchiveUnit.
     * 
     * @return
     *     possible object is
     *     {@link UpdateOperationType }
     *     
     */
    public UpdateOperationType getUpdateOperation() {
        return updateOperation;
    }

    /**
     * Sets the value of the updateOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateOperationType }
     *     
     * @see #getUpdateOperation()
     */
    public void setUpdateOperation(UpdateOperationType value) {
        this.updateOperation = value;
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
