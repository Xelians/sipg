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
 * Java class for CodeListVersionsType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CodeListVersionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AuthorizationReasonCodeListVersion" type="{org:afnor:medona:v1.0}CodeType" minOccurs="0"/>
 *         &lt;element name="FileEncodingCodeListVersion" type="{org:afnor:medona:v1.0}CodeType" minOccurs="0"/>
 *         &lt;element name="FileFormatCodeListVersion" type="{org:afnor:medona:v1.0}CodeType" minOccurs="0"/>
 *         &lt;element name="MessageDigestAlgorithmCodeListVersion" type="{org:afnor:medona:v1.0}CodeType" minOccurs="0"/>
 *         &lt;element name="RelationshipCodeListVersion" type="{org:afnor:medona:v1.0}CodeType" minOccurs="0"/>
 *         &lt;element name="ReplyCodeListVersion" type="{org:afnor:medona:v1.0}CodeType" minOccurs="0"/>
 *         &lt;element name="SignatureStatusCodeListVersion" type="{org:afnor:medona:v1.0}CodeType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}id"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "CodeListVersionsType",
    propOrder = {
      "authorizationReasonCodeListVersion",
      "fileEncodingCodeListVersion",
      "fileFormatCodeListVersion",
      "messageDigestAlgorithmCodeListVersion",
      "relationshipCodeListVersion",
      "replyCodeListVersion",
      "signatureStatusCodeListVersion"
    })
public class CodeListVersionsType {

  /** The Authorization reason code list version. */
  @XmlElement(name = "AuthorizationReasonCodeListVersion")
  protected CodeType authorizationReasonCodeListVersion;

  /** The File encoding code list version. */
  @XmlElement(name = "FileEncodingCodeListVersion")
  protected CodeType fileEncodingCodeListVersion;

  /** The File format code list version. */
  @XmlElement(name = "FileFormatCodeListVersion")
  protected CodeType fileFormatCodeListVersion;

  /** The Message digest algorithm code list version. */
  @XmlElement(name = "MessageDigestAlgorithmCodeListVersion")
  protected CodeType messageDigestAlgorithmCodeListVersion;

  /** The Relationship code list version. */
  @XmlElement(name = "RelationshipCodeListVersion")
  protected CodeType relationshipCodeListVersion;

  /** The Reply code list version. */
  @XmlElement(name = "ReplyCodeListVersion")
  protected CodeType replyCodeListVersion;

  /** The Signature status code list version. */
  @XmlElement(name = "SignatureStatusCodeListVersion")
  protected CodeType signatureStatusCodeListVersion;

  /** The Id. */
  @XmlAttribute(name = "id", namespace = "http://www.w3.org/XML/1998/namespace")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  @XmlSchemaType(name = "ID")
  protected String id;

  /**
   * Gets the value of the authorizationReasonCodeListVersion property.
   *
   * @return possible object is {@link CodeType }
   */
  public CodeType getAuthorizationReasonCodeListVersion() {
    return authorizationReasonCodeListVersion;
  }

  /**
   * Sets the value of the authorizationReasonCodeListVersion property.
   *
   * @param value allowed object is {@link CodeType }
   */
  public void setAuthorizationReasonCodeListVersion(CodeType value) {
    this.authorizationReasonCodeListVersion = value;
  }

  /**
   * Gets the value of the fileEncodingCodeListVersion property.
   *
   * @return possible object is {@link CodeType }
   */
  public CodeType getFileEncodingCodeListVersion() {
    return fileEncodingCodeListVersion;
  }

  /**
   * Sets the value of the fileEncodingCodeListVersion property.
   *
   * @param value allowed object is {@link CodeType }
   */
  public void setFileEncodingCodeListVersion(CodeType value) {
    this.fileEncodingCodeListVersion = value;
  }

  /**
   * Gets the value of the fileFormatCodeListVersion property.
   *
   * @return possible object is {@link CodeType }
   */
  public CodeType getFileFormatCodeListVersion() {
    return fileFormatCodeListVersion;
  }

  /**
   * Sets the value of the fileFormatCodeListVersion property.
   *
   * @param value allowed object is {@link CodeType }
   */
  public void setFileFormatCodeListVersion(CodeType value) {
    this.fileFormatCodeListVersion = value;
  }

  /**
   * Gets the value of the messageDigestAlgorithmCodeListVersion property.
   *
   * @return possible object is {@link CodeType }
   */
  public CodeType getMessageDigestAlgorithmCodeListVersion() {
    return messageDigestAlgorithmCodeListVersion;
  }

  /**
   * Sets the value of the messageDigestAlgorithmCodeListVersion property.
   *
   * @param value allowed object is {@link CodeType }
   */
  public void setMessageDigestAlgorithmCodeListVersion(CodeType value) {
    this.messageDigestAlgorithmCodeListVersion = value;
  }

  /**
   * Gets the value of the relationshipCodeListVersion property.
   *
   * @return possible object is {@link CodeType }
   */
  public CodeType getRelationshipCodeListVersion() {
    return relationshipCodeListVersion;
  }

  /**
   * Sets the value of the relationshipCodeListVersion property.
   *
   * @param value allowed object is {@link CodeType }
   */
  public void setRelationshipCodeListVersion(CodeType value) {
    this.relationshipCodeListVersion = value;
  }

  /**
   * Gets the value of the replyCodeListVersion property.
   *
   * @return possible object is {@link CodeType }
   */
  public CodeType getReplyCodeListVersion() {
    return replyCodeListVersion;
  }

  /**
   * Sets the value of the replyCodeListVersion property.
   *
   * @param value allowed object is {@link CodeType }
   */
  public void setReplyCodeListVersion(CodeType value) {
    this.replyCodeListVersion = value;
  }

  /**
   * Gets the value of the signatureStatusCodeListVersion property.
   *
   * @return possible object is {@link CodeType }
   */
  public CodeType getSignatureStatusCodeListVersion() {
    return signatureStatusCodeListVersion;
  }

  /**
   * Sets the value of the signatureStatusCodeListVersion property.
   *
   * @param value allowed object is {@link CodeType }
   */
  public void setSignatureStatusCodeListVersion(CodeType value) {
    this.signatureStatusCodeListVersion = value;
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
