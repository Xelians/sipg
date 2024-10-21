//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.10.29 at 01:17:12 AM CET
//
package fr.gouv.culture.archivesdefrance.seda.v21;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Java class for AuthorizationControlAuthorityRequestReplyType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AuthorizationControlAuthorityRequestReplyType">
 *   &lt;complexContent>
 *     &lt;extension base="{fr:gouv:culture:archivesdefrance:seda:v2.1}BusinessAuthorizationRequestReplyMessageType">
 *       &lt;sequence>
 *         &lt;element name="ArchivalAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}OrganizationWithIdType"/>
 *         &lt;element name="ControlAuthority" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}OrganizationWithIdType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "AuthorizationControlAuthorityRequestReplyType",
    propOrder = {"archivalAgency", "controlAuthority"})
public class AuthorizationControlAuthorityRequestReplyType
    extends BusinessAuthorizationRequestReplyMessageType {

  /** The Archival agency. */
  @XmlElement(name = "ArchivalAgency", required = true)
  protected OrganizationWithIdType archivalAgency;

  /** The Control authority. */
  @XmlElement(name = "ControlAuthority", required = true)
  protected OrganizationWithIdType controlAuthority;

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
   * Gets the value of the controlAuthority property.
   *
   * @return possible object is {@link OrganizationWithIdType }
   */
  public OrganizationWithIdType getControlAuthority() {
    return controlAuthority;
  }

  /**
   * Sets the value of the controlAuthority property.
   *
   * @param value allowed object is {@link OrganizationWithIdType }
   */
  public void setControlAuthority(OrganizationWithIdType value) {
    this.controlAuthority = value;
  }
}
