//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package fr.gouv.culture.archivesdefrance.seda.v2;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArchiveDeliveryRequestReplyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="ArchiveDeliveryRequestReplyType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2}BusinessReplyMessageType">
 *       <sequence>
 *         <element name="AuthorizationRequestReplyIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2}IdentifierType" minOccurs="0"/>
 *         <element name="UnitIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2}IdentifierType" maxOccurs="unbounded"/>
 *         <element name="ArchivalAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2}OrganizationWithIdType"/>
 *         <element name="Requester" type="{fr:gouv:culture:archivesdefrance:seda:v2}OrganizationWithIdType"/>
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
public class ArchiveDeliveryRequestReplyType
    extends BusinessReplyMessageType
{

    @XmlElement(name = "AuthorizationRequestReplyIdentifier")
    protected IdentifierType authorizationRequestReplyIdentifier;
    @XmlElement(name = "UnitIdentifier", required = true)
    protected List<IdentifierType> unitIdentifier;
    @XmlElement(name = "ArchivalAgency", required = true)
    protected OrganizationWithIdType archivalAgency;
    @XmlElement(name = "Requester", required = true)
    protected OrganizationWithIdType requester;

    /**
     * Gets the value of the authorizationRequestReplyIdentifier property.
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
     */
    public void setAuthorizationRequestReplyIdentifier(IdentifierType value) {
        this.authorizationRequestReplyIdentifier = value;
    }

    /**
     * Gets the value of the unitIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the unitIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnitIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdentifierType }
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
     * Gets the value of the archivalAgency property.
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
     */
    public void setArchivalAgency(OrganizationWithIdType value) {
        this.archivalAgency = value;
    }

    /**
     * Gets the value of the requester property.
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
     */
    public void setRequester(OrganizationWithIdType value) {
        this.requester = value;
    }

}
