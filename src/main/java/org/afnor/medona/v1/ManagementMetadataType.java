//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.09.15 at 07:07:47 PM CEST
//
package org.afnor.medona.v1;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Java class for ManagementMetadataType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ManagementMetadataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ArchivalProfile" type="{org:afnor:medona:v1.0}IdentifierType" minOccurs="0"/>
 *         &lt;element name="ServiceLevel" type="{org:afnor:medona:v1.0}IdentifierType" minOccurs="0"/>
 *         &lt;element name="AccessRule" type="{org:afnor:medona:v1.0}AccessRuleType" minOccurs="0"/>
 *         &lt;element name="AppraisalRule" type="{org:afnor:medona:v1.0}AppraisalRuleType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}id"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "ManagementMetadataType",
    propOrder = {"archivalProfile", "serviceLevel", "accessRule", "appraisalRule"})
public class ManagementMetadataType {

  /** The Archival profile. */
  @XmlElement(name = "ArchivalProfile")
  protected IdentifierType archivalProfile;

  /** The Service level. */
  @XmlElement(name = "ServiceLevel")
  protected IdentifierType serviceLevel;

  /** The Access rule. */
  @XmlElement(name = "AccessRule")
  protected AccessRuleType accessRule;

  /** The Appraisal rule. */
  @XmlElement(name = "AppraisalRule")
  protected AppraisalRuleType appraisalRule;

  /** The Id. */
  @XmlAttribute(name = "id", namespace = "http://www.w3.org/XML/1998/namespace")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;

  /**
   * Gets the value of the archivalProfile property.
   *
   * @return possible object is {@link IdentifierType }
   */
  public IdentifierType getArchivalProfile() {
    return archivalProfile;
  }

  /**
   * Sets the value of the archivalProfile property.
   *
   * @param value allowed object is {@link IdentifierType }
   */
  public void setArchivalProfile(IdentifierType value) {
    this.archivalProfile = value;
  }

  /**
   * Gets the value of the serviceLevel property.
   *
   * @return possible object is {@link IdentifierType }
   */
  public IdentifierType getServiceLevel() {
    return serviceLevel;
  }

  /**
   * Sets the value of the serviceLevel property.
   *
   * @param value allowed object is {@link IdentifierType }
   */
  public void setServiceLevel(IdentifierType value) {
    this.serviceLevel = value;
  }

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
