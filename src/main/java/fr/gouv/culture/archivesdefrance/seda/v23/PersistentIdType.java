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
 * Informations décrivant un identifiant pérenne.
 *
 * <p>Java class for PersistentIdType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="PersistentIdType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="PersistentIdentifierType" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType"/>
 *         <element name="PersistentIdentifierOrigin" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType" minOccurs="0"/>
 *         <element name="PersistentIdentifierReference" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType" minOccurs="0"/>
 *         <element name="PersistentIdentifierContent" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "PersistentIdType",
    propOrder = {
      "persistentIdentifierType",
      "persistentIdentifierOrigin",
      "persistentIdentifierReference",
      "persistentIdentifierContent"
    })
public class PersistentIdType {

  /** Type d'identifiant pérenne. */
  @XmlElement(name = "PersistentIdentifierType", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String persistentIdentifierType;

  /** origine d'identifiant pérenne. */
  @XmlElement(name = "PersistentIdentifierOrigin")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String persistentIdentifierOrigin;

  /** Référence d'identifiant pérenne. */
  @XmlElement(name = "PersistentIdentifierReference")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String persistentIdentifierReference;

  /** valeur de l'identifiant pérenne. */
  @XmlElement(name = "PersistentIdentifierContent", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String persistentIdentifierContent;

  /**
   * Type d'identifiant pérenne.
   *
   * @return possible object is {@link String }
   */
  public String getPersistentIdentifierType() {
    return persistentIdentifierType;
  }

  /**
   * Sets the value of the persistentIdentifierType property.
   *
   * @param value allowed object is {@link String }
   * @see #getPersistentIdentifierType()
   */
  public void setPersistentIdentifierType(String value) {
    this.persistentIdentifierType = value;
  }

  /**
   * origine d'identifiant pérenne.
   *
   * @return possible object is {@link String }
   */
  public String getPersistentIdentifierOrigin() {
    return persistentIdentifierOrigin;
  }

  /**
   * Sets the value of the persistentIdentifierOrigin property.
   *
   * @param value allowed object is {@link String }
   * @see #getPersistentIdentifierOrigin()
   */
  public void setPersistentIdentifierOrigin(String value) {
    this.persistentIdentifierOrigin = value;
  }

  /**
   * Référence d'identifiant pérenne.
   *
   * @return possible object is {@link String }
   */
  public String getPersistentIdentifierReference() {
    return persistentIdentifierReference;
  }

  /**
   * Sets the value of the persistentIdentifierReference property.
   *
   * @param value allowed object is {@link String }
   * @see #getPersistentIdentifierReference()
   */
  public void setPersistentIdentifierReference(String value) {
    this.persistentIdentifierReference = value;
  }

  /**
   * valeur de l'identifiant pérenne.
   *
   * @return possible object is {@link String }
   */
  public String getPersistentIdentifierContent() {
    return persistentIdentifierContent;
  }

  /**
   * Sets the value of the persistentIdentifierContent property.
   *
   * @param value allowed object is {@link String }
   * @see #getPersistentIdentifierContent()
   */
  public void setPersistentIdentifierContent(String value) {
    this.persistentIdentifierContent = value;
  }
}
