//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.09.15 at 07:07:47 PM CEST
//
package org.afnor.medona.v1;

import jakarta.xml.bind.annotation.*;

/**
 * Java class for BusinessAuthorizationRequestMessageType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="BusinessAuthorizationRequestMessageType">
 *   &lt;complexContent>
 *     &lt;extension base="{org:afnor:medona:v1.0}BusinessRequestMessageType">
 *       &lt;sequence>
 *         &lt;element name="AuthorizationRequestContent" type="{org:afnor:medona:v1.0}AuthorizationRequestContentType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "BusinessAuthorizationRequestMessageType",
    propOrder = {"authorizationRequestContent"})
@XmlSeeAlso({
  AuthorizationControlAuthorityRequestType.class,
  AuthorizationOriginatingAgencyRequestType.class
})
public abstract class BusinessAuthorizationRequestMessageType extends BusinessRequestMessageType {

  /** The Authorization request content. */
  @XmlElement(name = "AuthorizationRequestContent", required = true)
  protected AuthorizationRequestContentType authorizationRequestContent;

  /**
   * Gets the value of the authorizationRequestContent property.
   *
   * @return possible object is {@link AuthorizationRequestContentType }
   */
  public AuthorizationRequestContentType getAuthorizationRequestContent() {
    return authorizationRequestContent;
  }

  /**
   * Sets the value of the authorizationRequestContent property.
   *
   * @param value allowed object is {@link AuthorizationRequestContentType }
   */
  public void setAuthorizationRequestContent(AuthorizationRequestContentType value) {
    this.authorizationRequestContent = value;
  }
}
