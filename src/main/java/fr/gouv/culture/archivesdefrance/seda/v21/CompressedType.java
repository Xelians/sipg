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
import java.math.BigInteger;

/**
 * Java class for CompressedType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CompressedType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="algorithm" use="required" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}CompressionAlgorithmType" />
 *       &lt;attribute name="uncompressedSize" use="required" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}SizeInBytesType" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "CompressedType",
    propOrder = {"value"})
public class CompressedType {

  /** The Value. */
  @XmlValue protected String value;

  /** The Algorithm. */
  @XmlAttribute(name = "algorithm", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String algorithm;

  /** The Uncompressed size. */
  @XmlAttribute(name = "uncompressedSize", required = true)
  protected BigInteger uncompressedSize;

  /**
   * Gets the value of the value property.
   *
   * @return possible object is {@link String }
   */
  public String getValue() {
    return value;
  }

  /**
   * Sets the value of the value property.
   *
   * @param value allowed object is {@link String }
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Gets the value of the algorithm property.
   *
   * @return possible object is {@link String }
   */
  public String getAlgorithm() {
    return algorithm;
  }

  /**
   * Sets the value of the algorithm property.
   *
   * @param value allowed object is {@link String }
   */
  public void setAlgorithm(String value) {
    this.algorithm = value;
  }

  /**
   * Gets the value of the uncompressedSize property.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getUncompressedSize() {
    return uncompressedSize;
  }

  /**
   * Sets the value of the uncompressedSize property.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setUncompressedSize(BigInteger value) {
    this.uncompressedSize = value;
  }
}
