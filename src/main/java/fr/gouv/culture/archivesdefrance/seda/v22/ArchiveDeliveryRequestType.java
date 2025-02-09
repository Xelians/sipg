//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v22;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Java class for ArchiveDeliveryRequestType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="ArchiveDeliveryRequestType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2.2}BusinessRequestMessageType">
 *       <sequence>
 *         <element name="Derogation" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         <element name="UnitIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}IdentifierType" maxOccurs="unbounded"/>
 *         <element name="ArchivalAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}OrganizationWithIdType"/>
 *         <element name="Requester" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}OrganizationWithIdType"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "ArchiveDeliveryRequestType",
    propOrder = {"derogation", "unitIdentifier", "archivalAgency", "requester"})
public class ArchiveDeliveryRequestType extends BusinessRequestMessageType {

  /**
   * Indique si une procédure de dérogation est nécessaire avant de communiquer l’unité d'archive.
   */
  @XmlElement(name = "Derogation")
  protected boolean derogation;

  /** Identifiant de l'unité d'archive. */
  @XmlElement(name = "UnitIdentifier", required = true)
  protected List<IdentifierType> unitIdentifier;

  /** Service d'archives responsable de la communication. */
  @XmlElement(name = "ArchivalAgency", required = true)
  protected OrganizationWithIdType archivalAgency;

  /** Demandeur de la communication. */
  @XmlElement(name = "Requester", required = true)
  protected OrganizationWithIdType requester;

  /**
   * Indique si une procédure de dérogation est nécessaire avant de communiquer l’unité d'archive.
   */
  public boolean isDerogation() {
    return derogation;
  }

  /** Sets the value of the derogation property. */
  public void setDerogation(boolean value) {
    this.derogation = value;
  }

  /**
   * Identifiant de l'unité d'archive.
   *
   * <p>Gets the value of the unitIdentifier property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the unitIdentifier property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   * getUnitIdentifier().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link IdentifierType }
   *
   * @return The value of the unitIdentifier property.
   */
  public List<IdentifierType> getUnitIdentifier() {
    if (unitIdentifier == null) {
      unitIdentifier = new ArrayList<>();
    }
    return this.unitIdentifier;
  }

  /**
   * Service d'archives responsable de la communication.
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
   * Demandeur de la communication.
   *
   * @return possible object is {@link OrganizationWithIdType }
   */
  public OrganizationWithIdType getRequester() {
    return requester;
  }

  /**
   * Sets the value of the requester property.
   *
   * @param value allowed object is {@link OrganizationWithIdType }
   * @see #getRequester()
   */
  public void setRequester(OrganizationWithIdType value) {
    this.requester = value;
  }
}
