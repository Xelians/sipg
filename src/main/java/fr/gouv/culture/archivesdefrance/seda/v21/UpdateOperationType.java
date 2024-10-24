//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.10.29 at 01:17:12 AM CET
//
package fr.gouv.culture.archivesdefrance.seda.v21;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Java class for UpdateOperationType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="UpdateOperationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="SystemId" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}NonEmptyTokenType"/>
 *           &lt;element name="ArchiveUnitIdentifierKey" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}ArchiveUnitIdentifierKeyType"/>
 *         &lt;/choice>
 *         &lt;element name="ToDelete" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}ToDeleteType" minOccurs="0"/>
 *         &lt;element name="FullUpdate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "UpdateOperationType",
    propOrder = {"systemId", "archiveUnitIdentifierKey", "toDelete", "fullUpdate"})
public class UpdateOperationType {

  /** The System id. */
  @XmlElement(name = "SystemId")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String systemId;

  /** The Archive unit identifier key. */
  @XmlElement(name = "ArchiveUnitIdentifierKey")
  protected ArchiveUnitIdentifierKeyType archiveUnitIdentifierKey;

  /** The To delete. */
  @XmlElement(name = "ToDelete")
  protected ToDeleteType toDelete;

  /** The Full update. */
  @XmlElement(name = "FullUpdate", defaultValue = "false")
  protected Boolean fullUpdate;

  /**
   * Gets the value of the systemId property.
   *
   * @return possible object is {@link String }
   */
  public String getSystemId() {
    return systemId;
  }

  /**
   * Sets the value of the systemId property.
   *
   * @param value allowed object is {@link String }
   */
  public void setSystemId(String value) {
    this.systemId = value;
  }

  /**
   * Gets the value of the archiveUnitIdentifierKey property.
   *
   * @return possible object is {@link ArchiveUnitIdentifierKeyType }
   */
  public ArchiveUnitIdentifierKeyType getArchiveUnitIdentifierKey() {
    return archiveUnitIdentifierKey;
  }

  /**
   * Sets the value of the archiveUnitIdentifierKey property.
   *
   * @param value allowed object is {@link ArchiveUnitIdentifierKeyType }
   */
  public void setArchiveUnitIdentifierKey(ArchiveUnitIdentifierKeyType value) {
    this.archiveUnitIdentifierKey = value;
  }

  /**
   * Gets the value of the toDelete property.
   *
   * @return possible object is {@link ToDeleteType }
   */
  public ToDeleteType getToDelete() {
    return toDelete;
  }

  /**
   * Sets the value of the toDelete property.
   *
   * @param value allowed object is {@link ToDeleteType }
   */
  public void setToDelete(ToDeleteType value) {
    this.toDelete = value;
  }

  /**
   * Gets the value of the fullUpdate property.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isFullUpdate() {
    return fullUpdate;
  }

  /**
   * Sets the value of the fullUpdate property.
   *
   * @param value allowed object is {@link Boolean }
   */
  public void setFullUpdate(Boolean value) {
    this.fullUpdate = value;
  }
}
