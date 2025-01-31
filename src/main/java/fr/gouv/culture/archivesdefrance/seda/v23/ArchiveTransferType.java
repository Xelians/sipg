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
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArchiveTransferType complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="ArchiveTransferType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2.3}BusinessRequestMessageType">
 *       <sequence>
 *         <element name="RelatedTransferReference" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}IdentifierType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="TransferRequestReplyIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}IdentifierType" minOccurs="0"/>
 *         <element name="ArchivalAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}OrganizationWithIdType"/>
 *         <element name="TransferringAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}OrganizationWithIdType"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArchiveTransferType", propOrder = {
    "relatedTransferReference",
    "transferRequestReplyIdentifier",
    "archivalAgency",
    "transferringAgency"
})
@XmlRootElement(name = "ArchiveTransfer")
public class ArchiveTransferType
    extends BusinessRequestMessageType
{

    /**
     * Identifiant d’un transfert
     *                                 associé.
     * 
     */
    @XmlElement(name = "RelatedTransferReference")
    protected List<IdentifierType> relatedTransferReference;
    /**
     * Identifiant de la réponse à une demande de
     *                                 transfert.
     * 
     */
    @XmlElement(name = "TransferRequestReplyIdentifier")
    protected IdentifierType transferRequestReplyIdentifier;
    /**
     * Service d'archives responsable du
     *                                 transfert.
     * 
     */
    @XmlElement(name = "ArchivalAgency", required = true)
    protected OrganizationWithIdType archivalAgency;
    /**
     * Service versant chargé de réaliser le
     *                                 transport.
     * 
     */
    @XmlElement(name = "TransferringAgency", required = true)
    protected OrganizationWithIdType transferringAgency;

    /**
     * Identifiant d’un transfert
     *                                 associé.
     * 
     * Gets the value of the relatedTransferReference property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedTransferReference property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getRelatedTransferReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdentifierType }
     * </p>
     * 
     * 
     * @return
     *     The value of the relatedTransferReference property.
     */
    public List<IdentifierType> getRelatedTransferReference() {
        if (relatedTransferReference == null) {
            relatedTransferReference = new ArrayList<>();
        }
        return this.relatedTransferReference;
    }

    /**
     * Identifiant de la réponse à une demande de
     *                                 transfert.
     * 
     * @return
     *     possible object is
     *     {@link IdentifierType }
     *     
     */
    public IdentifierType getTransferRequestReplyIdentifier() {
        return transferRequestReplyIdentifier;
    }

    /**
     * Sets the value of the transferRequestReplyIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifierType }
     *     
     * @see #getTransferRequestReplyIdentifier()
     */
    public void setTransferRequestReplyIdentifier(IdentifierType value) {
        this.transferRequestReplyIdentifier = value;
    }

    /**
     * Service d'archives responsable du
     *                                 transfert.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationWithIdType }
     *     
     */
    public OrganizationWithIdType getArchivalAgency() {
        return archivalAgency;
    }

    /**
     * Sets the value of the archivalAgency property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationWithIdType }
     *     
     * @see #getArchivalAgency()
     */
    public void setArchivalAgency(OrganizationWithIdType value) {
        this.archivalAgency = value;
    }

    /**
     * Service versant chargé de réaliser le
     *                                 transport.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationWithIdType }
     *     
     */
    public OrganizationWithIdType getTransferringAgency() {
        return transferringAgency;
    }

    /**
     * Sets the value of the transferringAgency property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationWithIdType }
     *     
     * @see #getTransferringAgency()
     */
    public void setTransferringAgency(OrganizationWithIdType value) {
        this.transferringAgency = value;
    }

}
