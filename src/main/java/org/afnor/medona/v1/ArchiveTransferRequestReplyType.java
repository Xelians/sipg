//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.09.15 at 07:07:47 PM CEST
//
package org.afnor.medona.v1;

import jakarta.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Java class for ArchiveTransferRequestReplyType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArchiveTransferRequestReplyType">
 *   &lt;complexContent>
 *     &lt;extension base="{org:afnor:medona:v1.0}BusinessReplyMessageType">
 *       &lt;sequence>
 *         &lt;element name="TransferDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ArchivalAgency" type="{org:afnor:medona:v1.0}OrganizationType"/>
 *         &lt;element name="TransferringAgency" type="{org:afnor:medona:v1.0}OrganizationType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "ArchiveTransferRequestReplyType",
    propOrder = {"transferDate", "archivalAgency", "transferringAgency"})
public class ArchiveTransferRequestReplyType extends BusinessReplyMessageType {

  /** The Transfer date. */
  @XmlElement(name = "TransferDate")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar transferDate;

  /** The Archival agency. */
  @XmlElement(name = "ArchivalAgency", required = true)
  protected OrganizationType archivalAgency;

  /** The Transferring agency. */
  @XmlElement(name = "TransferringAgency", required = true)
  protected OrganizationType transferringAgency;

  /**
   * Gets the value of the transferDate property.
   *
   * @return possible object is {@link XMLGregorianCalendar }
   */
  public XMLGregorianCalendar getTransferDate() {
    return transferDate;
  }

  /**
   * Sets the value of the transferDate property.
   *
   * @param value allowed object is {@link XMLGregorianCalendar }
   */
  public void setTransferDate(XMLGregorianCalendar value) {
    this.transferDate = value;
  }

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
   * Gets the value of the transferringAgency property.
   *
   * @return possible object is {@link OrganizationType }
   */
  public OrganizationType getTransferringAgency() {
    return transferringAgency;
  }

  /**
   * Sets the value of the transferringAgency property.
   *
   * @param value allowed object is {@link OrganizationType }
   */
  public void setTransferringAgency(OrganizationType value) {
    this.transferringAgency = value;
  }
}
