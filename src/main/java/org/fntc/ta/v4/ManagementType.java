//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.09.15 at 07:07:47 PM CEST
//
package org.fntc.ta.v4;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Java class for ManagementType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ManagementType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;group ref="{org:fntc:ta:v4.0}ManagementGroup"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "ManagementType",
    propOrder = {"accessRule", "appraisalRule", "logBook", "extended"})
public class ManagementType {

  /** The Access rule. */
  @XmlElement(name = "AccessRule")
  protected AccessRuleType accessRule;

  /** The Appraisal rule. */
  @XmlElement(name = "AppraisalRule")
  protected AppraisalRuleType appraisalRule;

  /** The Log book. */
  @XmlElement(name = "LogBook")
  protected LogBookType logBook;

  /** The Extended. */
  @XmlElement(name = "Extended")
  protected ExtendedType extended;

  /**
   * Gets the value of the accessRule property.
   *
   * @return possible object is {@link AccessRuleType }
   */
  public AccessRuleType getAccessRule() {
    return accessRule;
  }

  /**
   * Sets the value of the accessRule property.
   *
   * @param value allowed object is {@link AccessRuleType }
   */
  public void setAccessRule(AccessRuleType value) {
    this.accessRule = value;
  }

  /**
   * Gets the value of the appraisalRule property.
   *
   * @return possible object is {@link AppraisalRuleType }
   */
  public AppraisalRuleType getAppraisalRule() {
    return appraisalRule;
  }

  /**
   * Sets the value of the appraisalRule property.
   *
   * @param value allowed object is {@link AppraisalRuleType }
   */
  public void setAppraisalRule(AppraisalRuleType value) {
    this.appraisalRule = value;
  }

  /**
   * Gets the value of the logBook property.
   *
   * @return possible object is {@link LogBookType }
   */
  public LogBookType getLogBook() {
    return logBook;
  }

  /**
   * Sets the value of the logBook property.
   *
   * @param value allowed object is {@link LogBookType }
   */
  public void setLogBook(LogBookType value) {
    this.logBook = value;
  }

  /**
   * Gets the value of the extended property.
   *
   * @return possible object is {@link ExtendedType }
   */
  public ExtendedType getExtended() {
    return extended;
  }

  /**
   * Sets the value of the extended property.
   *
   * @param value allowed object is {@link ExtendedType }
   */
  public void setExtended(ExtendedType value) {
    this.extended = value;
  }
}
