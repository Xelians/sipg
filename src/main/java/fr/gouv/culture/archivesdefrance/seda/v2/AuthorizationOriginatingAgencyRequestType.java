//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.10.29 at 01:17:12 AM CET 
//
package fr.gouv.culture.archivesdefrance.seda.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for AuthorizationOriginatingAgencyRequestType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AuthorizationOriginatingAgencyRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{fr:gouv:culture:archivesdefrance:seda:v2.1}BusinessAuthorizationRequestMessageType">
 *       &lt;sequence>
 *         &lt;element name="ArchivalAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}OrganizationWithIdType"/>
 *         &lt;element name="OriginatingAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}OrganizationWithIdType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthorizationOriginatingAgencyRequestType", propOrder = {
    "archivalAgency",
    "originatingAgency"
})
public class AuthorizationOriginatingAgencyRequestType
        extends BusinessAuthorizationRequestMessageType {

    /**
     * The Archival agency.
     */
    @XmlElement(name = "ArchivalAgency", required = true)
    protected OrganizationWithIdType archivalAgency;
    /**
     * The Originating agency.
     */
    @XmlElement(name = "OriginatingAgency", required = true)
    protected OrganizationWithIdType originatingAgency;

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
     * Gets the value of the originatingAgency property.
     *
     * @return possible object is {@link OrganizationWithIdType }
     */
    public OrganizationWithIdType getOriginatingAgency() {
        return originatingAgency;
    }

    /**
     * Sets the value of the originatingAgency property.
     *
     * @param value allowed object is {@link OrganizationWithIdType }
     */
    public void setOriginatingAgency(OrganizationWithIdType value) {
        this.originatingAgency = value;
    }

}
