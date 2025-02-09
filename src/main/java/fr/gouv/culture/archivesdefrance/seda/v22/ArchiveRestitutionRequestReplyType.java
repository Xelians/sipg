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
 * Java class for ArchiveRestitutionRequestReplyType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="ArchiveRestitutionRequestReplyType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2.2}BusinessReplyMessageType">
 *       <sequence>
 *         <element name="UnitIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}IdentifierType" maxOccurs="unbounded"/>
 *         <element name="ArchivalAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}OrganizationWithIdType"/>
 *         <element name="OriginatingAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}OrganizationWithIdType"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "ArchiveRestitutionRequestReplyType",
    propOrder = {"unitIdentifier", "archivalAgency", "originatingAgency"})
public class ArchiveRestitutionRequestReplyType extends BusinessReplyMessageType {

  /** Identifiant de l'unité d'archive. */
  @XmlElement(name = "UnitIdentifier", required = true)
  protected List<IdentifierType> unitIdentifier;

  /** Service d'archives responsable de la demande de restitution. */
  @XmlElement(name = "ArchivalAgency", required = true)
  protected OrganizationWithIdType archivalAgency;

  /** Service producteur responsable de la demande de restitution. */
  @XmlElement(name = "OriginatingAgency", required = true)
  protected OrganizationWithIdType originatingAgency;

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
   * Service d'archives responsable de la demande de restitution.
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
   * Service producteur responsable de la demande de restitution.
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
   * @see #getOriginatingAgency()
   */
  public void setOriginatingAgency(OrganizationWithIdType value) {
    this.originatingAgency = value;
  }
}
