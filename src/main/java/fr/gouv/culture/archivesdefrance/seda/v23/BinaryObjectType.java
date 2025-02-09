//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

/**
 * Java class for BinaryObjectType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="BinaryObjectType">
 *   <simpleContent>
 *     <extension base="<http://www.w3.org/2001/XMLSchema>base64Binary">
 *       <attribute name="filename" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="uri" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     </extension>
 *   </simpleContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "BinaryObjectType",
    propOrder = {"value"})
public class BinaryObjectType {

  @XmlValue protected byte[] value;

  @XmlAttribute(name = "filename")
  protected String filename;

  @XmlAttribute(name = "uri")
  @XmlSchemaType(name = "anyURI")
  protected String uri;

  /**
   * Gets the value of the value property.
   *
   * @return possible object is byte[]
   */
  public byte[] getValue() {
    return value;
  }

  /**
   * Sets the value of the value property.
   *
   * @param value allowed object is byte[]
   */
  public void setValue(byte[] value) {
    this.value = value;
  }

  /**
   * Gets the value of the filename property.
   *
   * @return possible object is {@link String }
   */
  public String getFilename() {
    return filename;
  }

  /**
   * Sets the value of the filename property.
   *
   * @param value allowed object is {@link String }
   */
  public void setFilename(String value) {
    this.filename = value;
  }

  /**
   * Gets the value of the uri property.
   *
   * @return possible object is {@link String }
   */
  public String getUri() {
    return uri;
  }

  /**
   * Sets the value of the uri property.
   *
   * @param value allowed object is {@link String }
   */
  public void setUri(String value) {
    this.uri = value;
  }
}
