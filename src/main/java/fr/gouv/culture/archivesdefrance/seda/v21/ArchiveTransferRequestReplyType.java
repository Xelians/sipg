//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.10.29 at 01:17:12 AM CET
//
package fr.gouv.culture.archivesdefrance.seda.v21;

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
 *     &lt;extension base="{fr:gouv:culture:archivesdefrance:seda:v2.1}BusinessReplyMessageType">
 *       &lt;sequence>
 *         &lt;element name="TransferDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ArchivalAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}OrganizationWithIdType"/>
 *         &lt;element name="TransferringAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}OrganizationWithIdType"/>
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
  protected OrganizationWithIdType archivalAgency;

  /** The Transferring agency. */
  @XmlElement(name = "TransferringAgency", required = true)
  protected OrganizationWithIdType transferringAgency;

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
