//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package fr.gouv.culture.archivesdefrance.seda.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthorizationOriginatingAgencyRequestReplyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="AuthorizationOriginatingAgencyRequestReplyType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2}BusinessAuthorizationRequestReplyMessageType">
 *       <sequence>
 *         <element name="ArchivalAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2}OrganizationWithIdType"/>
 *         <element name="OriginatingAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2}OrganizationWithIdType"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthorizationOriginatingAgencyRequestReplyType", propOrder = {
    "archivalAgency",
    "originatingAgency"
})
public class AuthorizationOriginatingAgencyRequestReplyType
    extends BusinessAuthorizationRequestReplyMessageType
{

    @XmlElement(name = "ArchivalAgency", required = true)
    protected OrganizationWithIdType archivalAgency;
    @XmlElement(name = "OriginatingAgency", required = true)
    protected OrganizationWithIdType originatingAgency;

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
     * Gets the value of the originatingAgency property.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationWithIdType }
     *     
     */
    public OrganizationWithIdType getOriginatingAgency() {
        return originatingAgency;
    }

    /**
     * Sets the value of the originatingAgency property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationWithIdType }
     *     
     */
    public void setOriginatingAgency(OrganizationWithIdType value) {
        this.originatingAgency = value;
    }

}
