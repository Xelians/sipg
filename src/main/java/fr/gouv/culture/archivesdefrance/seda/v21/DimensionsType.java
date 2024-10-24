//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.10.29 at 01:17:12 AM CET
//
package fr.gouv.culture.archivesdefrance.seda.v21;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Permet d'exprimer les mesures de dimensions basiques.
 *
 * <p>Java class for DimensionsType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DimensionsType">
 *   &lt;complexContent>
 *     &lt;extension base="{fr:gouv:culture:archivesdefrance:seda:v2.1}BaseDimensionsType">
 *       &lt;sequence>
 *         &lt;element name="Width" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}MeasurementType" minOccurs="0"/>
 *         &lt;element name="Height" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}MeasurementType" minOccurs="0"/>
 *         &lt;element name="Depth" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}MeasurementType" minOccurs="0"/>
 *         &lt;element name="Shape" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Diameter" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}MeasurementType" minOccurs="0"/>
 *         &lt;element name="Length" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}MeasurementType" minOccurs="0"/>
 *         &lt;element name="Thickness" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}MeasurementType" minOccurs="0"/>
 *         &lt;element name="Weight" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}MeasurementWeightType" minOccurs="0"/>
 *         &lt;element name="NumberOfPage" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "DimensionsType",
    propOrder = {
      "width",
      "height",
      "depth",
      "shape",
      "diameter",
      "length",
      "thickness",
      "weight",
      "numberOfPage"
    })
public class DimensionsType extends BaseDimensionsType {

  /** The Width. */
  @XmlElement(name = "Width")
  protected MeasurementType width;

  /** The Height. */
  @XmlElement(name = "Height")
  protected MeasurementType height;

  /** The Depth. */
  @XmlElement(name = "Depth")
  protected MeasurementType depth;

  /** The Shape. */
  @XmlElement(name = "Shape")
  protected String shape;

  /** The Diameter. */
  @XmlElement(name = "Diameter")
  protected MeasurementType diameter;

  /** The Length. */
  @XmlElement(name = "Length")
  protected MeasurementType length;

  /** The Thickness. */
  @XmlElement(name = "Thickness")
  protected MeasurementType thickness;

  /** The Weight. */
  @XmlElement(name = "Weight")
  protected MeasurementWeightType weight;

  /** The Number of page. */
  @XmlElement(name = "NumberOfPage")
  protected Integer numberOfPage;

  /**
   * Gets the value of the width property.
   *
   * @return possible object is {@link MeasurementType }
   */
  public MeasurementType getWidth() {
    return width;
  }

  /**
   * Sets the value of the width property.
   *
   * @param value allowed object is {@link MeasurementType }
   */
  public void setWidth(MeasurementType value) {
    this.width = value;
  }

  /**
   * Gets the value of the height property.
   *
   * @return possible object is {@link MeasurementType }
   */
  public MeasurementType getHeight() {
    return height;
  }

  /**
   * Sets the value of the height property.
   *
   * @param value allowed object is {@link MeasurementType }
   */
  public void setHeight(MeasurementType value) {
    this.height = value;
  }

  /**
   * Gets the value of the depth property.
   *
   * @return possible object is {@link MeasurementType }
   */
  public MeasurementType getDepth() {
    return depth;
  }

  /**
   * Sets the value of the depth property.
   *
   * @param value allowed object is {@link MeasurementType }
   */
  public void setDepth(MeasurementType value) {
    this.depth = value;
  }

  /**
   * Gets the value of the shape property.
   *
   * @return possible object is {@link String }
   */
  public String getShape() {
    return shape;
  }

  /**
   * Sets the value of the shape property.
   *
   * @param value allowed object is {@link String }
   */
  public void setShape(String value) {
    this.shape = value;
  }

  /**
   * Gets the value of the diameter property.
   *
   * @return possible object is {@link MeasurementType }
   */
  public MeasurementType getDiameter() {
    return diameter;
  }

  /**
   * Sets the value of the diameter property.
   *
   * @param value allowed object is {@link MeasurementType }
   */
  public void setDiameter(MeasurementType value) {
    this.diameter = value;
  }

  /**
   * Gets the value of the length property.
   *
   * @return possible object is {@link MeasurementType }
   */
  public MeasurementType getLength() {
    return length;
  }

  /**
   * Sets the value of the length property.
   *
   * @param value allowed object is {@link MeasurementType }
   */
  public void setLength(MeasurementType value) {
    this.length = value;
  }

  /**
   * Gets the value of the thickness property.
   *
   * @return possible object is {@link MeasurementType }
   */
  public MeasurementType getThickness() {
    return thickness;
  }

  /**
   * Sets the value of the thickness property.
   *
   * @param value allowed object is {@link MeasurementType }
   */
  public void setThickness(MeasurementType value) {
    this.thickness = value;
  }

  /**
   * Gets the value of the weight property.
   *
   * @return possible object is {@link MeasurementWeightType }
   */
  public MeasurementWeightType getWeight() {
    return weight;
  }

  /**
   * Sets the value of the weight property.
   *
   * @param value allowed object is {@link MeasurementWeightType }
   */
  public void setWeight(MeasurementWeightType value) {
    this.weight = value;
  }

  /**
   * Gets the value of the numberOfPage property.
   *
   * @return possible object is {@link Integer }
   */
  public Integer getNumberOfPage() {
    return numberOfPage;
  }

  /**
   * Sets the value of the numberOfPage property.
   *
   * @param value allowed object is {@link Integer }
   */
  public void setNumberOfPage(Integer value) {
    this.numberOfPage = value;
  }
}
