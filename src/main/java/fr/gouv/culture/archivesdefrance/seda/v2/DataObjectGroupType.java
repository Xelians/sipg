//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package fr.gouv.culture.archivesdefrance.seda.v2;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for DataObjectGroupType complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="DataObjectGroupType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <choice maxOccurs="unbounded" minOccurs="0">
 *           <element name="BinaryDataObject" type="{fr:gouv:culture:archivesdefrance:seda:v2}BinaryDataObjectType"/>
 *           <element name="PhysicalDataObject" type="{fr:gouv:culture:archivesdefrance:seda:v2}PhysicalDataObjectType"/>
 *         </choice>
 *         <element name="LogBook" type="{fr:gouv:culture:archivesdefrance:seda:v2}LogBookOgType" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="id" use="required" type="{fr:gouv:culture:archivesdefrance:seda:v2}GroupIdType" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataObjectGroupType", propOrder = {
    "binaryDataObjectOrPhysicalDataObject",
    "logBook"
})
public class DataObjectGroupType {

    @XmlElements({
        @XmlElement(name = "BinaryDataObject", type = BinaryDataObjectType.class),
        @XmlElement(name = "PhysicalDataObject", type = PhysicalDataObjectType.class)
    })
    protected List<MinimalDataObjectType> binaryDataObjectOrPhysicalDataObject;
    @XmlElement(name = "LogBook")
    protected LogBookOgType logBook;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the binaryDataObjectOrPhysicalDataObject property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the binaryDataObjectOrPhysicalDataObject property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getBinaryDataObjectOrPhysicalDataObject().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BinaryDataObjectType }
     * {@link PhysicalDataObjectType }
     * </p>
     * 
     * 
     * @return
     *     The value of the binaryDataObjectOrPhysicalDataObject property.
     */
    public List<MinimalDataObjectType> getBinaryDataObjectOrPhysicalDataObject() {
        if (binaryDataObjectOrPhysicalDataObject == null) {
            binaryDataObjectOrPhysicalDataObject = new ArrayList<>();
        }
        return this.binaryDataObjectOrPhysicalDataObject;
    }

    /**
     * Gets the value of the logBook property.
     * 
     * @return
     *     possible object is
     *     {@link LogBookOgType }
     *     
     */
    public LogBookOgType getLogBook() {
        return logBook;
    }

    /**
     * Sets the value of the logBook property.
     * 
     * @param value
     *     allowed object is
     *     {@link LogBookOgType }
     *     
     */
    public void setLogBook(LogBookOgType value) {
        this.logBook = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
