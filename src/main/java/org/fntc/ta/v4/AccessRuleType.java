//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.09.15 at 07:07:47 PM CEST
//
package org.fntc.ta.v4;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Java class for AccessRuleType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AccessRuleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Rule" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}id"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "AccessRuleType",
    propOrder = {"rule", "startDate"})
public class AccessRuleType {

  /** The Rule. */
  @XmlElement(name = "Rule")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String rule;

  /** The Start date. */
  @XmlElement(name = "StartDate")
  @XmlSchemaType(name = "date")
  protected XMLGregorianCalendar startDate;

  /** The Id. */
  @XmlAttribute(name = "id", namespace = "http://www.w3.org/XML/1998/namespace")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;

  /**
   * Gets the value of the rule property.
   *
   * @return possible object is {@link String }
   */
  public String getRule() {
    return rule;
  }

  /**
   * Sets the value of the rule property.
   *
   * @param value allowed object is {@link String }
   */
  public void setRule(String value) {
    this.rule = value;
  }

  /**
   * Gets the value of the startDate property.
   *
   * @return possible object is {@link XMLGregorianCalendar }
   */
  public XMLGregorianCalendar getStartDate() {
    return startDate;
  }

  /**
   * Sets the value of the startDate property.
   *
   * @param value allowed object is {@link XMLGregorianCalendar }
   */
  public void setStartDate(XMLGregorianCalendar value) {
    this.startDate = value;
  }

  /**
   * Gets the value of the id property.
   *
   * @return possible object is {@link String }
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the value of the id property.
   *
   * @param value allowed object is {@link String }
   */
  public void setId(String value) {
    this.id = value;
  }
}
