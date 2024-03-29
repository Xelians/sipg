//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package fr.gouv.culture.archivesdefrance.seda.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Référence à un objet-données ou à un groupe d'objets-données existant.
 * 
 * <p>Java class for ObjectGroupRefType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="ObjectGroupRefType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="DataObjectReference" type="{fr:gouv:culture:archivesdefrance:seda:v2}DataObjectRefType" minOccurs="0"/>
 *         <element name="DataObjectGroupExistingReferenceId" type="{fr:gouv:culture:archivesdefrance:seda:v2}NonEmptyTokenType"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectGroupRefType", propOrder = {
    "dataObjectReference",
    "dataObjectGroupExistingReferenceId"
})
public class ObjectGroupRefType {

    @XmlElement(name = "DataObjectReference")
    protected DataObjectRefType dataObjectReference;
    @XmlElement(name = "DataObjectGroupExistingReferenceId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String dataObjectGroupExistingReferenceId;

    /**
     * Gets the value of the dataObjectReference property.
     * 
     * @return
     *     possible object is
     *     {@link DataObjectRefType }
     *     
     */
    public DataObjectRefType getDataObjectReference() {
        return dataObjectReference;
    }

    /**
     * Sets the value of the dataObjectReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataObjectRefType }
     *     
     */
    public void setDataObjectReference(DataObjectRefType value) {
        this.dataObjectReference = value;
    }

    /**
     * Gets the value of the dataObjectGroupExistingReferenceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataObjectGroupExistingReferenceId() {
        return dataObjectGroupExistingReferenceId;
    }

    /**
     * Sets the value of the dataObjectGroupExistingReferenceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataObjectGroupExistingReferenceId(String value) {
        this.dataObjectGroupExistingReferenceId = value;
    }

}
