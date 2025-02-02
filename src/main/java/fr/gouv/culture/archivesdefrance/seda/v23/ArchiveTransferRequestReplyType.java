//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Java class for ArchiveTransferRequestReplyType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="ArchiveTransferRequestReplyType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2.3}BusinessReplyMessageType">
 *       <sequence>
 *         <element name="TransferDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="ArchivalAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}OrganizationWithIdType"/>
 *         <element name="TransferringAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}OrganizationWithIdType"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "ArchiveTransferRequestReplyType",
    propOrder = {"transferDate", "archivalAgency", "transferringAgency"})
public class ArchiveTransferRequestReplyType extends BusinessReplyMessageType {

  /** Date de transfert. */
  @XmlElement(name = "TransferDate")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar transferDate;

  /** Service d'archives responsable de la réponse à une demande de transfert. */
  @XmlElement(name = "ArchivalAgency", required = true)
  protected OrganizationWithIdType archivalAgency;

  /** Service versant responsable de la réponse à une demande de transfert. */
  @XmlElement(name = "TransferringAgency", required = true)
  protected OrganizationWithIdType transferringAgency;

  /**
   * Date de transfert.
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
   * @see #getTransferDate()
   */
  public void setTransferDate(XMLGregorianCalendar value) {
    this.transferDate = value;
  }

  /**
   * Service d'archives responsable de la réponse à une demande de transfert.
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
   * Service versant responsable de la réponse à une demande de transfert.
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
   * @see #getTransferringAgency()
   */
  public void setTransferringAgency(OrganizationWithIdType value) {
    this.transferringAgency = value;
  }
}
