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
 * <p>Java class for UpdateOperationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="UpdateOperationType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <choice>
 *           <element name="SystemId" type="{fr:gouv:culture:archivesdefrance:seda:v2}NonEmptyTokenType"/>
 *           <element name="ArchiveUnitIdentifierKey" type="{fr:gouv:culture:archivesdefrance:seda:v2}ArchiveUnitIdentifierKeyType"/>
 *         </choice>
 *         <element name="ToDelete" type="{fr:gouv:culture:archivesdefrance:seda:v2}ToDeleteType" minOccurs="0"/>
 *         <element name="FullUpdate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateOperationType", propOrder = {
    "systemId",
    "archiveUnitIdentifierKey",
    "toDelete",
    "fullUpdate"
})
public class UpdateOperationType {

    @XmlElement(name = "SystemId")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String systemId;
    @XmlElement(name = "ArchiveUnitIdentifierKey")
    protected ArchiveUnitIdentifierKeyType archiveUnitIdentifierKey;
    @XmlElement(name = "ToDelete")
    protected ToDeleteType toDelete;
    @XmlElement(name = "FullUpdate", defaultValue = "false")
    protected Boolean fullUpdate;

    /**
     * Gets the value of the systemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * Sets the value of the systemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemId(String value) {
        this.systemId = value;
    }

    /**
     * Gets the value of the archiveUnitIdentifierKey property.
     * 
     * @return
     *     possible object is
     *     {@link ArchiveUnitIdentifierKeyType }
     *     
     */
    public ArchiveUnitIdentifierKeyType getArchiveUnitIdentifierKey() {
        return archiveUnitIdentifierKey;
    }

    /**
     * Sets the value of the archiveUnitIdentifierKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArchiveUnitIdentifierKeyType }
     *     
     */
    public void setArchiveUnitIdentifierKey(ArchiveUnitIdentifierKeyType value) {
        this.archiveUnitIdentifierKey = value;
    }

    /**
     * Gets the value of the toDelete property.
     * 
     * @return
     *     possible object is
     *     {@link ToDeleteType }
     *     
     */
    public ToDeleteType getToDelete() {
        return toDelete;
    }

    /**
     * Sets the value of the toDelete property.
     * 
     * @param value
     *     allowed object is
     *     {@link ToDeleteType }
     *     
     */
    public void setToDelete(ToDeleteType value) {
        this.toDelete = value;
    }

    /**
     * Gets the value of the fullUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFullUpdate() {
        return fullUpdate;
    }

    /**
     * Sets the value of the fullUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFullUpdate(Boolean value) {
        this.fullUpdate = value;
    }

}
