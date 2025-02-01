//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v22;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Java class for UpdateOperationType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="UpdateOperationType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <choice>
 *           <element name="SystemId" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}NonEmptyTokenType"/>
 *           <element name="ArchiveUnitIdentifierKey" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}ArchiveUnitIdentifierKeyType"/>
 *         </choice>
 *         <element name="ToDelete" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}ToDeleteType" minOccurs="0"/>
 *         <element name="FullUpdate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "UpdateOperationType",
    propOrder = {"systemId", "archiveUnitIdentifierKey", "toDelete", "fullUpdate"})
public class UpdateOperationType {

  /**
   * Identifiant attribué à l'ArchiveUnit. Il est attribué par le SAE et correspond à un identifiant
   * interne.
   */
  @XmlElement(name = "SystemId")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String systemId;

  /**
   * Identifiant attribué à l'ArchiveUnit. Il est attribué par le SAE et correspond à un nom et une
   * valeur d'une métadonnée qui identifié d'une manière unique une ArchiveUnit existante.
   */
  @XmlElement(name = "ArchiveUnitIdentifierKey")
  protected ArchiveUnitIdentifierKeyType archiveUnitIdentifierKey;

  @XmlElement(name = "ToDelete")
  protected ToDeleteType toDelete;

  /**
   * Point d'attention : dans le cas d'une Règle de gestion dans Management, le retrait d'une date
   * de début (et donc de la date de fin) se fait en positionnant une valeur vide à StartDate.
   */
  @XmlElement(name = "FullUpdate", defaultValue = "false")
  protected Boolean fullUpdate;

  /**
   * Identifiant attribué à l'ArchiveUnit. Il est attribué par le SAE et correspond à un identifiant
   * interne.
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
   * @see #getSystemId()
   */
  public void setSystemId(String value) {
    this.systemId = value;
  }

  /**
   * Identifiant attribué à l'ArchiveUnit. Il est attribué par le SAE et correspond à un nom et une
   * valeur d'une métadonnée qui identifié d'une manière unique une ArchiveUnit existante.
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
   * @see #getArchiveUnitIdentifierKey()
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
   * Point d'attention : dans le cas d'une Règle de gestion dans Management, le retrait d'une date
   * de début (et donc de la date de fin) se fait en positionnant une valeur vide à StartDate.
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
   * @see #isFullUpdate()
   */
  public void setFullUpdate(Boolean value) {
    this.fullUpdate = value;
  }
}
