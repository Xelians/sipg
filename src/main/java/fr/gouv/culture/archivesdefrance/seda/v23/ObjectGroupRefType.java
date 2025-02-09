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
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Référence à un objet-données ou à un groupe d'objets-données existant.
 *
 * <p>Java class for ObjectGroupRefType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="ObjectGroupRefType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="DataObjectReference" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}DataObjectRefType" minOccurs="0"/>
 *         <element name="DataObjectGroupExistingReferenceId" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "ObjectGroupRefType",
    propOrder = {"dataObjectReference", "dataObjectGroupExistingReferenceId"})
public class ObjectGroupRefType {

  /** Permet de faire référence à un objet-donnée binaire ou physique déjà existant. */
  @XmlElement(name = "DataObjectReference")
  protected DataObjectRefType dataObjectReference;

  /** Référence à un groupe d'objets-données déjà existants. */
  @XmlElement(name = "DataObjectGroupExistingReferenceId", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String dataObjectGroupExistingReferenceId;

  /**
   * Permet de faire référence à un objet-donnée binaire ou physique déjà existant.
   *
   * @return possible object is {@link DataObjectRefType }
   */
  public DataObjectRefType getDataObjectReference() {
    return dataObjectReference;
  }

  /**
   * Sets the value of the dataObjectReference property.
   *
   * @param value allowed object is {@link DataObjectRefType }
   * @see #getDataObjectReference()
   */
  public void setDataObjectReference(DataObjectRefType value) {
    this.dataObjectReference = value;
  }

  /**
   * Référence à un groupe d'objets-données déjà existants.
   *
   * @return possible object is {@link String }
   */
  public String getDataObjectGroupExistingReferenceId() {
    return dataObjectGroupExistingReferenceId;
  }

  /**
   * Sets the value of the dataObjectGroupExistingReferenceId property.
   *
   * @param value allowed object is {@link String }
   * @see #getDataObjectGroupExistingReferenceId()
   */
  public void setDataObjectGroupExistingReferenceId(String value) {
    this.dataObjectGroupExistingReferenceId = value;
  }
}
