//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.10.29 at 01:17:12 AM CET 
//
package fr.gouv.culture.archivesdefrance.seda.v2;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

/**
 * <p>
 * Java class for MeasurementType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="MeasurementType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>decimal">
 *       &lt;attribute name="unit" use="required" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}MeasurementUnitsType" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MeasurementType", propOrder = {
    "value"
})
public class MeasurementType {

    /**
     * The Value.
     */
    @XmlValue
    protected BigDecimal value;
    /**
     * The Unit.
     */
    @XmlAttribute(name = "unit", required = true)
    protected String unit;

    /**
     * Gets the value of the value property.
     *
     * @return possible object is {@link BigDecimal }
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value allowed object is {@link BigDecimal }
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * Gets the value of the unit property.
     *
     * @return possible object is {@link String }
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     *
     * @param value allowed object is {@link String }
     */
    public void setUnit(String value) {
        this.unit = value;
    }

}
