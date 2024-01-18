//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.10.29 at 01:17:12 AM CET 
//
package fr.gouv.culture.archivesdefrance.seda.v21;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for ToDeleteType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ToDeleteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;element name="ArchiveUnitRefId" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}ArchiveUnitRefIdType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ToDeleteType", propOrder = {
    "archiveUnitRefId"
})
public class ToDeleteType {

    /**
     * The Archive unit ref id.
     */
    @XmlElementRef(name = "ArchiveUnitRefId", namespace = "fr:gouv:culture:archivesdefrance:seda:v2.1", type = JAXBElement.class)
    protected List<JAXBElement<Object>> archiveUnitRefId;

    /**
     * Gets the value of the archiveUnitRefId property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the archiveUnitRefId property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArchiveUnitRefId().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link JAXBElement }{@code <}{@link Object }{@code >}
     *
     * @return the archive unit ref id
     */
    public List<JAXBElement<Object>> getArchiveUnitRefId() {
        if (archiveUnitRefId == null) {
            archiveUnitRefId = new ArrayList<JAXBElement<Object>>();
        }
        return this.archiveUnitRefId;
    }

}