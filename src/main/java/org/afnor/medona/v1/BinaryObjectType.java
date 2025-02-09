//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.09.15 at 07:07:47 PM CEST
//
package org.afnor.medona.v1;

import jakarta.xml.bind.annotation.*;

/**
 * Java class for BinaryObjectType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="BinaryObjectType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>base64Binary">
 *       &lt;attribute name="filename" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="uri" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "BinaryObjectType",
    propOrder = {"value"})
@XmlSeeAlso({MessageDigestBinaryObjectType.class})
public class BinaryObjectType {

  /** The Value. */
  @XmlValue protected byte[] value;

  /** The Filename. */
  @XmlAttribute(name = "filename")
  protected String filename;

  /** The Uri. */
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
