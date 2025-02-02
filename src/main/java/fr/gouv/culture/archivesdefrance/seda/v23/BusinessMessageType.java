//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Java class for BusinessMessageType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="BusinessMessageType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2.3}MessageType">
 *       <sequence>
 *         <element name="ArchivalAgreement" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}IdentifierType" minOccurs="0"/>
 *         <element name="CodeListVersions" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}CodeListVersionsType"/>
 *         <element name="DataObjectPackage" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}DataObjectPackageType" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "BusinessMessageType",
    propOrder = {"archivalAgreement", "codeListVersions", "dataObjectPackage"})
@XmlSeeAlso({
  BusinessRequestMessageType.class,
  BusinessReplyMessageType.class,
  BusinessNotificationMessageType.class
})
public abstract class BusinessMessageType extends MessageType {

  /** Accord de service. */
  @XmlElement(name = "ArchivalAgreement")
  protected IdentifierType archivalAgreement;

  /** Listes de codes de références utilisés dans le message. */
  @XmlElement(name = "CodeListVersions", required = true)
  protected CodeListVersionsType codeListVersions;

  /** Objets-données échangés dans le message. */
  @XmlElement(name = "DataObjectPackage")
  protected DataObjectPackageType dataObjectPackage;

  /**
   * Accord de service.
   *
   * @return possible object is {@link IdentifierType }
   */
  public IdentifierType getArchivalAgreement() {
    return archivalAgreement;
  }

  /**
   * Sets the value of the archivalAgreement property.
   *
   * @param value allowed object is {@link IdentifierType }
   * @see #getArchivalAgreement()
   */
  public void setArchivalAgreement(IdentifierType value) {
    this.archivalAgreement = value;
  }

  /**
   * Listes de codes de références utilisés dans le message.
   *
   * @return possible object is {@link CodeListVersionsType }
   */
  public CodeListVersionsType getCodeListVersions() {
    return codeListVersions;
  }

  /**
   * Sets the value of the codeListVersions property.
   *
   * @param value allowed object is {@link CodeListVersionsType }
   * @see #getCodeListVersions()
   */
  public void setCodeListVersions(CodeListVersionsType value) {
    this.codeListVersions = value;
  }

  /**
   * Objets-données échangés dans le message.
   *
   * @return possible object is {@link DataObjectPackageType }
   */
  public DataObjectPackageType getDataObjectPackage() {
    return dataObjectPackage;
  }

  /**
   * Sets the value of the dataObjectPackage property.
   *
   * @param value allowed object is {@link DataObjectPackageType }
   * @see #getDataObjectPackage()
   */
  public void setDataObjectPackage(DataObjectPackageType value) {
    this.dataObjectPackage = value;
  }
}
