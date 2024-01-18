//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.01.15 at 09:28:44 PM CET 
//


package fr.gouv.culture.archivesdefrance.seda.v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for MinimalDataObjectType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MinimalDataObjectType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataObjectSystemId" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}NonEmptyTokenType" minOccurs="0"/>
 *         &lt;element name="DataObjectGroupSystemId" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}NonEmptyTokenType" minOccurs="0"/>
 *         &lt;element name="Relationship" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}RelationshipType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;group ref="{fr:gouv:culture:archivesdefrance:seda:v2.1}DataObjectVersionGroup" minOccurs="0"/>
 *         &lt;element name="DataObjectVersion" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}VersionIdType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}DataObjectIdType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MinimalDataObjectType", propOrder = {
    "dataObjectSystemId",
    "dataObjectGroupSystemId",
    "relationship",
    "dataObjectGroupReferenceId",
    "dataObjectGroupId",
    "dataObjectVersion"
})
@XmlSeeAlso({
    PhysicalDataObjectType.class,
    BinaryDataObjectType.class
})
public abstract class MinimalDataObjectType {

    @XmlElement(name = "DataObjectSystemId")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String dataObjectSystemId;
    @XmlElement(name = "DataObjectGroupSystemId")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String dataObjectGroupSystemId;
    @XmlElement(name = "Relationship")
    protected List<RelationshipType> relationship;
    @XmlElement(name = "DataObjectGroupReferenceId")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object dataObjectGroupReferenceId;
    @XmlElement(name = "DataObjectGroupId")
    protected String dataObjectGroupId;
    @XmlElement(name = "DataObjectVersion")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String dataObjectVersion;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;

    /**
     * Gets the value of the dataObjectSystemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataObjectSystemId() {
        return dataObjectSystemId;
    }

    /**
     * Sets the value of the dataObjectSystemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataObjectSystemId(String value) {
        this.dataObjectSystemId = value;
    }

    /**
     * Gets the value of the dataObjectGroupSystemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataObjectGroupSystemId() {
        return dataObjectGroupSystemId;
    }

    /**
     * Sets the value of the dataObjectGroupSystemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataObjectGroupSystemId(String value) {
        this.dataObjectGroupSystemId = value;
    }

    /**
     * Gets the value of the relationship property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relationship property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelationship().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RelationshipType }
     * 
     * 
     */
    public List<RelationshipType> getRelationship() {
        if (relationship == null) {
            relationship = new ArrayList<RelationshipType>();
        }
        return this.relationship;
    }

    /**
     * Gets the value of the dataObjectGroupReferenceId property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getDataObjectGroupReferenceId() {
        return dataObjectGroupReferenceId;
    }

    /**
     * Sets the value of the dataObjectGroupReferenceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setDataObjectGroupReferenceId(Object value) {
        this.dataObjectGroupReferenceId = value;
    }

    /**
     * Gets the value of the dataObjectGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataObjectGroupId() {
        return dataObjectGroupId;
    }

    /**
     * Sets the value of the dataObjectGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataObjectGroupId(String value) {
        this.dataObjectGroupId = value;
    }

    /**
     * Gets the value of the dataObjectVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataObjectVersion() {
        return dataObjectVersion;
    }

    /**
     * Sets the value of the dataObjectVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataObjectVersion(String value) {
        this.dataObjectVersion = value;
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
