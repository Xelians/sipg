//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v22;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Java class for KeyType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="KeyType">
 *   <simpleContent>
 *     <extension base="<fr:gouv:culture:archivesdefrance:seda:v2.2>CodeKeywordType">
 *       <attribute name="listVersionID" type="{http://www.w3.org/2001/XMLSchema}token" default="edition 2009" />
 *     </extension>
 *   </simpleContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "KeyType",
    propOrder = {"value"})
public class KeyType {

  /** Table des types de mots-clés. */
  @XmlValue protected CodeKeywordType value;

  @XmlAttribute(name = "listVersionID")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String listVersionID;

  /**
   * Table des types de mots-clés.
   *
   * @return possible object is {@link CodeKeywordType }
   */
  public CodeKeywordType getValue() {
    return value;
  }

  /**
   * Sets the value of the value property.
   *
   * @param value allowed object is {@link CodeKeywordType }
   * @see #getValue()
   */
  public void setValue(CodeKeywordType value) {
    this.value = value;
  }

  /**
   * Gets the value of the listVersionID property.
   *
   * @return possible object is {@link String }
   */
  public String getListVersionID() {
    if (listVersionID == null) {
      return "edition 2009";
    } else {
      return listVersionID;
    }
  }

  /**
   * Sets the value of the listVersionID property.
   *
   * @param value allowed object is {@link String }
   */
  public void setListVersionID(String value) {
    this.listVersionID = value;
  }
}
