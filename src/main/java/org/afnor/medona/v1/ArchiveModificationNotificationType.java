//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.09.15 at 07:07:47 PM CEST
//
package org.afnor.medona.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Java class for ArchiveModificationNotificationType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArchiveModificationNotificationType">
 *   &lt;complexContent>
 *     &lt;extension base="{org:afnor:medona:v1.0}BusinessNotificationMessageType">
 *       &lt;sequence>
 *         &lt;element name="UnitIdentifier" type="{org:afnor:medona:v1.0}IdentifierType" maxOccurs="unbounded"/>
 *         &lt;element name="ArchivalAgency" type="{org:afnor:medona:v1.0}OrganizationType"/>
 *         &lt;element name="OriginatingAgency" type="{org:afnor:medona:v1.0}OrganizationType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "ArchiveModificationNotificationType",
    propOrder = {"unitIdentifier", "archivalAgency", "originatingAgency"})
public class ArchiveModificationNotificationType extends BusinessNotificationMessageType {

  /** The Unit identifier. */
  @XmlElement(name = "UnitIdentifier", required = true)
  protected List<IdentifierType> unitIdentifier;

  /** The Archival agency. */
  @XmlElement(name = "ArchivalAgency", required = true)
  protected OrganizationType archivalAgency;

  /** The Originating agency. */
  @XmlElement(name = "OriginatingAgency", required = true)
  protected OrganizationType originatingAgency;

  /**
   * Gets the value of the unitIdentifier property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the unitIdentifier property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getUnitIdentifier().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link IdentifierType }*
   *
   * @return the unit identifier
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
   * Gets the value of the originatingAgency property.
   *
   * @return possible object is {@link OrganizationType }
   */
  public OrganizationType getOriginatingAgency() {
    return originatingAgency;
  }

  /**
   * Sets the value of the originatingAgency property.
   *
   * @param value allowed object is {@link OrganizationType }
   */
  public void setOriginatingAgency(OrganizationType value) {
    this.originatingAgency = value;
  }
}
