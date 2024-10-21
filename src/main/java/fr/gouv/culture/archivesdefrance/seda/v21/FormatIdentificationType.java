//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.10.29 at 01:17:12 AM CET
//
package fr.gouv.culture.archivesdefrance.seda.v21;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Java class for FormatIdentificationType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="FormatIdentificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FormatLitteral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MimeType" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}MimeTypeType" minOccurs="0"/>
 *         &lt;element name="FormatId" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}FileFormatType" minOccurs="0"/>
 *         &lt;element name="Encoding" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}EncodingType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "FormatIdentificationType",
    propOrder = {"formatLitteral", "mimeType", "formatId", "encoding"})
public class FormatIdentificationType {

  /** The Format litteral. */
  @XmlElement(name = "FormatLitteral")
  protected String formatLitteral;

  /** The Mime type. */
  @XmlElement(name = "MimeType")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String mimeType;

  /** The Format id. */
  @XmlElement(name = "FormatId")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String formatId;

  /** The Encoding. */
  @XmlElement(name = "Encoding")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String encoding;

  /**
   * Gets the value of the formatLitteral property.
   *
   * @return possible object is {@link String }
   */
  public String getFormatLitteral() {
    return formatLitteral;
  }

  /**
   * Sets the value of the formatLitteral property.
   *
   * @param value allowed object is {@link String }
   */
  public void setFormatLitteral(String value) {
    this.formatLitteral = value;
  }

  /**
   * Gets the value of the mimeType property.
   *
   * @return possible object is {@link String }
   */
  public String getMimeType() {
    return mimeType;
  }

  /**
   * Sets the value of the mimeType property.
   *
   * @param value allowed object is {@link String }
   */
  public void setMimeType(String value) {
    this.mimeType = value;
  }

  /**
   * Gets the value of the formatId property.
   *
   * @return possible object is {@link String }
   */
  public String getFormatId() {
    return formatId;
  }

  /**
   * Sets the value of the formatId property.
   *
   * @param value allowed object is {@link String }
   */
  public void setFormatId(String value) {
    this.formatId = value;
  }

  /**
   * Gets the value of the encoding property.
   *
   * @return possible object is {@link String }
   */
  public String getEncoding() {
    return encoding;
  }

  /**
   * Sets the value of the encoding property.
   *
   * @param value allowed object is {@link String }
   */
  public void setEncoding(String value) {
    this.encoding = value;
  }
}
