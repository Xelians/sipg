//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.09.15 at 07:07:47 PM CEST 
//
package org.afnor.medona.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for AuthorizationControlAuthorityRequestType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AuthorizationControlAuthorityRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{org:afnor:medona:v1.0}BusinessAuthorizationRequestMessageType">
 *       &lt;sequence>
 *         &lt;element name="ArchivalAgency" type="{org:afnor:medona:v1.0}OrganizationType"/>
 *         &lt;element name="ControlAuthority" type="{org:afnor:medona:v1.0}OrganizationType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthorizationControlAuthorityRequestType", propOrder = {
    "archivalAgency",
    "controlAuthority"
})
public class AuthorizationControlAuthorityRequestType
        extends BusinessAuthorizationRequestMessageType {

    /**
     * The Archival agency.
     */
    @XmlElement(name = "ArchivalAgency", required = true)
    protected OrganizationType archivalAgency;
    /**
     * The Control authority.
     */
    @XmlElement(name = "ControlAuthority", required = true)
    protected OrganizationType controlAuthority;

    /**
     * Gets the value of the archivalAgency property.
     *
     * @return possible object is {@link OrganizationType }
     */
    public OrganizationType getArchivalAgency() {
        return archivalAgency;
    }

    /**
     * Sets the value of the archivalAgency property.
     *
     * @param value allowed object is {@link OrganizationType }
     */
    public void setArchivalAgency(OrganizationType value) {
        this.archivalAgency = value;
    }

    /**
     * Gets the value of the controlAuthority property.
     *
     * @return possible object is {@link OrganizationType }
     */
    public OrganizationType getControlAuthority() {
        return controlAuthority;
    }

    /**
     * Sets the value of the controlAuthority property.
     *
     * @param value allowed object is {@link OrganizationType }
     */
    public void setControlAuthority(OrganizationType value) {
        this.controlAuthority = value;
    }

}