//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package fr.gouv.culture.archivesdefrance.seda.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Permet d'exprimer les mesures de dimensions basiques.
 * 
 * <p>Java class for DimensionsType complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="DimensionsType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2}BaseDimensionsType">
 *       <sequence>
 *         <element name="Width" type="{fr:gouv:culture:archivesdefrance:seda:v2}MeasurementType" minOccurs="0"/>
 *         <element name="Height" type="{fr:gouv:culture:archivesdefrance:seda:v2}MeasurementType" minOccurs="0"/>
 *         <element name="Depth" type="{fr:gouv:culture:archivesdefrance:seda:v2}MeasurementType" minOccurs="0"/>
 *         <element name="Shape" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="Diameter" type="{fr:gouv:culture:archivesdefrance:seda:v2}MeasurementType" minOccurs="0"/>
 *         <element name="Length" type="{fr:gouv:culture:archivesdefrance:seda:v2}MeasurementType" minOccurs="0"/>
 *         <element name="Thickness" type="{fr:gouv:culture:archivesdefrance:seda:v2}MeasurementType" minOccurs="0"/>
 *         <element name="Weight" type="{fr:gouv:culture:archivesdefrance:seda:v2}MeasurementWeightType" minOccurs="0"/>
 *         <element name="NumberOfPage" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DimensionsType", propOrder = {
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
public class DimensionsType
    extends BaseDimensionsType
{

    /**
     * Métadonnée de dimension physique : largeur.
     * 
     */
    @XmlElement(name = "Width")
    protected MeasurementType width;
    /**
     * Métadonnée de dimension physique : hauteur.
     * 
     */
    @XmlElement(name = "Height")
    protected MeasurementType height;
    /**
     * Métadonnée de dimension physique : profondeur.
     * 
     */
    @XmlElement(name = "Depth")
    protected MeasurementType depth;
    /**
     * Métadonnée de dimension physique : forme.
     * 
     */
    @XmlElement(name = "Shape")
    protected String shape;
    /**
     * Métadonnée de dimension physique : diamètre.
     * 
     */
    @XmlElement(name = "Diameter")
    protected MeasurementType diameter;
    /**
     * Métadonnée de dimension physique : longueur.
     * 
     */
    @XmlElement(name = "Length")
    protected MeasurementType length;
    /**
     * Métadonnée de dimension physique : épaisseur.
     * 
     */
    @XmlElement(name = "Thickness")
    protected MeasurementType thickness;
    /**
     * Métadonnée de dimension physique : poids.
     * 
     */
    @XmlElement(name = "Weight")
    protected MeasurementWeightType weight;
    /**
     * Métadonnée de dimension physique : nombre de pages.
     * 
     */
    @XmlElement(name = "NumberOfPage")
    protected Integer numberOfPage;

    /**
     * Métadonnée de dimension physique : largeur.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementType }
     *     
     */
    public MeasurementType getWidth() {
        return width;
    }

    /**
     * Sets the value of the width property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementType }
     *     
     * @see #getWidth()
     */
    public void setWidth(MeasurementType value) {
        this.width = value;
    }

    /**
     * Métadonnée de dimension physique : hauteur.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementType }
     *     
     */
    public MeasurementType getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementType }
     *     
     * @see #getHeight()
     */
    public void setHeight(MeasurementType value) {
        this.height = value;
    }

    /**
     * Métadonnée de dimension physique : profondeur.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementType }
     *     
     */
    public MeasurementType getDepth() {
        return depth;
    }

    /**
     * Sets the value of the depth property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementType }
     *     
     * @see #getDepth()
     */
    public void setDepth(MeasurementType value) {
        this.depth = value;
    }

    /**
     * Métadonnée de dimension physique : forme.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShape() {
        return shape;
    }

    /**
     * Sets the value of the shape property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getShape()
     */
    public void setShape(String value) {
        this.shape = value;
    }

    /**
     * Métadonnée de dimension physique : diamètre.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementType }
     *     
     */
    public MeasurementType getDiameter() {
        return diameter;
    }

    /**
     * Sets the value of the diameter property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementType }
     *     
     * @see #getDiameter()
     */
    public void setDiameter(MeasurementType value) {
        this.diameter = value;
    }

    /**
     * Métadonnée de dimension physique : longueur.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementType }
     *     
     */
    public MeasurementType getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementType }
     *     
     * @see #getLength()
     */
    public void setLength(MeasurementType value) {
        this.length = value;
    }

    /**
     * Métadonnée de dimension physique : épaisseur.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementType }
     *     
     */
    public MeasurementType getThickness() {
        return thickness;
    }

    /**
     * Sets the value of the thickness property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementType }
     *     
     * @see #getThickness()
     */
    public void setThickness(MeasurementType value) {
        this.thickness = value;
    }

    /**
     * Métadonnée de dimension physique : poids.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementWeightType }
     *     
     */
    public MeasurementWeightType getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementWeightType }
     *     
     * @see #getWeight()
     */
    public void setWeight(MeasurementWeightType value) {
        this.weight = value;
    }

    /**
     * Métadonnée de dimension physique : nombre de pages.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfPage() {
        return numberOfPage;
    }

    /**
     * Sets the value of the numberOfPage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     * @see #getNumberOfPage()
     */
    public void setNumberOfPage(Integer value) {
        this.numberOfPage = value;
    }

}
