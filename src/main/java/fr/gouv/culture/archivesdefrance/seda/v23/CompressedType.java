//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigInteger;

/**
 * Java class for CompressedType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="CompressedType">
 *   <simpleContent>
 *     <extension base="<http://www.w3.org/2001/XMLSchema>string">
 *       <attribute name="algorithm" use="required" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}CompressionAlgorithmType" />
 *       <attribute name="uncompressedSize" use="required" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}SizeInBytesType" />
 *     </extension>
 *   </simpleContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "CompressedType",
    propOrder = {"value"})
public class CompressedType {

  @XmlValue protected String value;

  @XmlAttribute(name = "algorithm", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String algorithm;

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
