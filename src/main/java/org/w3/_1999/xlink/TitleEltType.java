//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package org.w3._1999.xlink;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlMixed;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;

/**
 * Java class for titleEltType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="titleEltType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <group ref="{http://www.w3.org/1999/xlink}titleModel"/>
 *       <attGroup ref="{http://www.w3.org/1999/xlink}titleAttrs"/>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "titleEltType",
    propOrder = {"content"})
public class TitleEltType {

  @XmlMixed
  @XmlAnyElement(lax = true)
  protected List<Object> content;

  @XmlAttribute(name = "type", namespace = "http://www.w3.org/1999/xlink", required = true)
  protected TypeType type;

  /**
   * xml:lang is not required, but provides much of the motivation for title elements in addition to
   * attributes, and so is provided here for convenience.
   */
  @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
  protected String lang;

  /**
   * Gets the value of the content property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the content property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   * getContent().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link Object } {@link String }
   * {@link Element }
   *
   * @return The value of the content property.
   */
  public List<Object> getContent() {
    if (content == null) {
      content = new ArrayList<>();
    }
    return this.content;
  }

  /**
   * Gets the value of the type property.
   *
   * @return possible object is {@link TypeType }
   */
  public TypeType getType() {
    if (type == null) {
      return TypeType.TITLE;
    } else {
      return type;
    }
  }

  /**
   * Sets the value of the type property.
   *
   * @param value allowed object is {@link TypeType }
   */
  public void setType(TypeType value) {
    this.type = value;
  }

  /**
   * xml:lang is not required, but provides much of the motivation for title elements in addition to
   * attributes, and so is provided here for convenience.
   *
   * @return possible object is {@link String }
   */
  public String getLang() {
    return lang;
  }

  /**
   * Sets the value of the lang property.
   *
   * @param value allowed object is {@link String }
   * @see #getLang()
   */
  public void setLang(String value) {
    this.lang = value;
  }
}
