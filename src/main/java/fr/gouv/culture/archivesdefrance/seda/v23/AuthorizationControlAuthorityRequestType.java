//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Java class for AuthorizationControlAuthorityRequestType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="AuthorizationControlAuthorityRequestType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2.3}BusinessAuthorizationRequestMessageType">
 *       <sequence>
 *         <element name="ArchivalAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}OrganizationWithIdType"/>
 *         <element name="ControlAuthority" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}OrganizationWithIdType"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "AuthorizationControlAuthorityRequestType",
    propOrder = {"archivalAgency", "controlAuthority"})
public class AuthorizationControlAuthorityRequestType
    extends BusinessAuthorizationRequestMessageType {

  /** Service d'archives responsable de la demande d'autorisation. */
  @XmlElement(name = "ArchivalAgency", required = true)
  protected OrganizationWithIdType archivalAgency;

  /** Autorité de contrôle. */
  @XmlElement(name = "ControlAuthority", required = true)
  protected OrganizationWithIdType controlAuthority;

  /**
   * Service d'archives responsable de la demande d'autorisation.
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
   * @see #getArchivalAgency()
   */
  public void setArchivalAgency(OrganizationWithIdType value) {
    this.archivalAgency = value;
  }

  /**
   * Autorité de contrôle.
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
   * @see #getControlAuthority()
   */
  public void setControlAuthority(OrganizationWithIdType value) {
    this.controlAuthority = value;
  }
}
