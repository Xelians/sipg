//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.09.15 at 07:07:47 PM CEST 
//
package org.afnor.medona.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for PhysicalDataObjectType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="PhysicalDataObjectType">
 *   &lt;complexContent>
 *     &lt;extension base="{org:afnor:medona:v1.0}DataObjectType">
 *       &lt;sequence>
 *         &lt;element name="Size" type="{org:afnor:medona:v1.0}MeasureType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PhysicalDataObjectType", propOrder = {
    "size"
})
public class PhysicalDataObjectType
        extends DataObjectType {

    /**
     * The Size.
     */
    @XmlElement(name = "Size", required = true)
    protected MeasureType size;

    /**
     * Gets the value of the size property.
     *
     * @return possible object is {@link MeasureType }
     */
    public MeasureType getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     *
     * @param value allowed object is {@link MeasureType }
     */
    public void setSize(MeasureType value) {
        this.size = value;
    }

}
