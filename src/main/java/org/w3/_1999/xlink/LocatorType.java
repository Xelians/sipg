//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.09.15 at 07:07:47 PM CEST 
//
package org.w3._1999.xlink;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Java class for locatorType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="locatorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;group ref="{http://www.w3.org/1999/xlink}locatorModel"/>
 *       &lt;attGroup ref="{http://www.w3.org/1999/xlink}locatorAttrs"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "locatorType", propOrder = {
    "title"
})
public class LocatorType {

    /**
     * The Title.
     */
    protected List<TitleEltType> title;
    /**
     * The Type.
     */
    @XmlAttribute(name = "type", namespace = "http://www.w3.org/1999/xlink", required = true)
    protected TypeType type;
    /**
     * The Href.
     */
    @XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink", required = true)
    protected String href;
    /**
     * The Role.
     */
    @XmlAttribute(name = "role", namespace = "http://www.w3.org/1999/xlink")
    protected String role;
    /**
     * The Title 1.
     */
    @XmlAttribute(name = "title", namespace = "http://www.w3.org/1999/xlink")
    protected String title1;
    /**
     * The Label.
     */
    @XmlAttribute(name = "label", namespace = "http://www.w3.org/1999/xlink")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String label;

    /**
     * Gets the value of the title property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the title property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTitle().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link TitleEltType
     * }*
     *
     * @return the title
     */
    public List<TitleEltType> getTitle() {
        if (title == null) {
            title = new ArrayList<TitleEltType>();
        }
        return this.title;
    }

    /**
     * Gets the value of the type property.
     *
     * @return possible object is {@link TypeType }
     */
    public TypeType getType() {
        if (type == null) {
            return TypeType.LOCATOR;
        }
        else {
            return type;
        }
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is {@link TypeType }
     */
    public void setType(TypeType value) {
        this.type = value;
    }

    /**
     * Gets the value of the href property.
     *
     * @return possible object is {@link String }
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the value of the href property.
     *
     * @param value allowed object is {@link String }
     */
    public void setHref(String value) {
        this.href = value;
    }

    /**
     * Gets the value of the role property.
     *
     * @return possible object is {@link String }
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     *
     * @param value allowed object is {@link String }
     */
    public void setRole(String value) {
        this.role = value;
    }

    /**
     * Gets the value of the title1 property.
     *
     * @return possible object is {@link String }
     */
    public String getTitle1() {
        return title1;
    }

    /**
     * Sets the value of the title1 property.
     *
     * @param value allowed object is {@link String }
     */
    public void setTitle1(String value) {
        this.title1 = value;
    }

    /**
     * label is not required, but locators have no particular XLink function if they are not labeled.
     *
     * @return possible object is {@link String }
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     *
     * @param value allowed object is {@link String }
     */
    public void setLabel(String value) {
        this.label = value;
    }

}
