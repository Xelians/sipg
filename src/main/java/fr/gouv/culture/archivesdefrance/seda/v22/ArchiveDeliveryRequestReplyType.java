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
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArchiveDeliveryRequestReplyType complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="ArchiveDeliveryRequestReplyType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2.2}BusinessReplyMessageType">
 *       <sequence>
 *         <element name="AuthorizationRequestReplyIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}IdentifierType" minOccurs="0"/>
 *         <element name="UnitIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}IdentifierType" maxOccurs="unbounded"/>
 *         <element name="ArchivalAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}OrganizationWithIdType"/>
 *         <element name="Requester" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}OrganizationWithIdType"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArchiveDeliveryRequestReplyType", propOrder = {
    "authorizationRequestReplyIdentifier",
    "unitIdentifier",
    "archivalAgency",
    "requester"
})
@XmlRootElement(name = "ArchiveDeliveryRequestReply")
public class ArchiveDeliveryRequestReplyType
    extends BusinessReplyMessageType
{

    /**
     * Identifiant de la réponse à une demande
     *                                 d’autorisation.
     * 
     */
    @XmlElement(name = "AuthorizationRequestReplyIdentifier")
    protected IdentifierType authorizationRequestReplyIdentifier;
    /**
     * Identifiant de l'unité d'archive.
     * 
     */
    @XmlElement(name = "UnitIdentifier", required = true)
    protected List<IdentifierType> unitIdentifier;
    /**
     * Service d'archives responsable de la demande de
     *                                 communication.
     * 
     */
    @XmlElement(name = "ArchivalAgency", required = true)
    protected OrganizationWithIdType archivalAgency;
    /**
     * Demandeur de la communication.
     * 
     */
    @XmlElement(name = "Requester", required = true)
    protected OrganizationWithIdType requester;

    /**
     * Identifiant de la réponse à une demande
     *                                 d’autorisation.
     * 
     * @return
     *     possible object is
     *     {@link IdentifierType }
     *     
     */
    public IdentifierType getAuthorizationRequestReplyIdentifier() {
        return authorizationRequestReplyIdentifier;
    }

    /**
     * Sets the value of the authorizationRequestReplyIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifierType }
     *     
     * @see #getAuthorizationRequestReplyIdentifier()
     */
    public void setAuthorizationRequestReplyIdentifier(IdentifierType value) {
        this.authorizationRequestReplyIdentifier = value;
    }

    /**
     * Identifiant de l'unité d'archive.
     * 
     * Gets the value of the unitIdentifier property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the unitIdentifier property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getUnitIdentifier().add(newItem);
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
     *     The value of the unitIdentifier property.
     */
    public List<IdentifierType> getUnitIdentifier() {
        if (unitIdentifier == null) {
            unitIdentifier = new ArrayList<>();
        }
        return this.unitIdentifier;
    }

    /**
     * Service d'archives responsable de la demande de
     *                                 communication.
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
     * Demandeur de la communication.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationWithIdType }
     *     
     */
    public OrganizationWithIdType getRequester() {
        return requester;
    }

    /**
     * Sets the value of the requester property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationWithIdType }
     *     
     * @see #getRequester()
     */
    public void setRequester(OrganizationWithIdType value) {
        this.requester = value;
    }

}
