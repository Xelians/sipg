//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.10.29 at 01:17:12 AM CET 
//
package fr.gouv.culture.archivesdefrance.seda.v2;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ArchiveTransferType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArchiveTransferType">
 *   &lt;complexContent>
 *     &lt;extension base="{fr:gouv:culture:archivesdefrance:seda:v2.1}BusinessRequestMessageType">
 *       &lt;sequence>
 *         &lt;element name="RelatedTransferReference" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}IdentifierType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TransferRequestReplyIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}IdentifierType" minOccurs="0"/>
 *         &lt;element name="ArchivalAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}OrganizationWithIdType"/>
 *         &lt;element name="TransferringAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}OrganizationWithIdType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlRootElement(name = "ArchiveTransfer")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArchiveTransferType", propOrder = {
    "relatedTransferReference",
    "transferRequestReplyIdentifier",
    "archivalAgency",
    "transferringAgency"
})
public class ArchiveTransferType
        extends BusinessRequestMessageType {

    /**
     * The Related transfer reference.
     */
    @XmlElement(name = "RelatedTransferReference")
    protected List<IdentifierType> relatedTransferReference;
    /**
     * The Transfer request reply identifier.
     */
    @XmlElement(name = "TransferRequestReplyIdentifier")
    protected IdentifierType transferRequestReplyIdentifier;
    /**
     * The Archival agency.
     */
    @XmlElement(name = "ArchivalAgency", required = true)
    protected OrganizationWithIdType archivalAgency;
    /**
     * The Transferring agency.
     */
    @XmlElement(name = "TransferringAgency", required = true)
    protected OrganizationWithIdType transferringAgency;

    /**
     * Gets the value of the relatedTransferReference property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the relatedTransferReference property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedTransferReference().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link IdentifierType
     * }*
     *
     * @return the related transfer reference
     */
    public List<IdentifierType> getRelatedTransferReference() {
        if (relatedTransferReference == null) {
            relatedTransferReference = new ArrayList<IdentifierType>();
        }
        return this.relatedTransferReference;
    }

    /**
     * Gets the value of the transferRequestReplyIdentifier property.
     *
     * @return possible object is {@link IdentifierType }
     */
    public IdentifierType getTransferRequestReplyIdentifier() {
        return transferRequestReplyIdentifier;
    }

    /**
     * Sets the value of the transferRequestReplyIdentifier property.
     *
     * @param value allowed object is {@link IdentifierType }
     */
    public void setTransferRequestReplyIdentifier(IdentifierType value) {
        this.transferRequestReplyIdentifier = value;
    }

    /**
     * Gets the value of the archivalAgency property.
     *
     * @return possible object is {@link OrganizationWithIdType }
     */
    public OrganizationWithIdType getArchivalAgency() {
        return archivalAgency;
    }

    /**
     * Sets the value of the archivalAgency property.
     *
     * @param value allowed object is {@link OrganizationWithIdType }
     */
    public void setArchivalAgency(OrganizationWithIdType value) {
        this.archivalAgency = value;
    }

    /**
     * Gets the value of the transferringAgency property.
     *
     * @return possible object is {@link OrganizationWithIdType }
     */
    public OrganizationWithIdType getTransferringAgency() {
        return transferringAgency;
    }

    /**
     * Sets the value of the transferringAgency property.
     *
     * @param value allowed object is {@link OrganizationWithIdType }
     */
    public void setTransferringAgency(OrganizationWithIdType value) {
        this.transferringAgency = value;
    }

}
