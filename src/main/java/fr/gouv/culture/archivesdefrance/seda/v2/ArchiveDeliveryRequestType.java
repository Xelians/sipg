//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.10.29 at 01:17:12 AM CET 
//
package fr.gouv.culture.archivesdefrance.seda.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for ArchiveDeliveryRequestType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArchiveDeliveryRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{fr:gouv:culture:archivesdefrance:seda:v2.1}BusinessRequestMessageType">
 *       &lt;sequence>
 *         &lt;element name="Derogation" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="UnitIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}IdentifierType" maxOccurs="unbounded"/>
 *         &lt;element name="ArchivalAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}OrganizationWithIdType"/>
 *         &lt;element name="Requester" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}OrganizationWithIdType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArchiveDeliveryRequestType", propOrder = {
    "derogation",
    "unitIdentifier",
    "archivalAgency",
    "requester"
})
public class ArchiveDeliveryRequestType
        extends BusinessRequestMessageType {

    /**
     * The Derogation.
     */
    @XmlElement(name = "Derogation")
    protected boolean derogation;
    /**
     * The Unit identifier.
     */
    @XmlElement(name = "UnitIdentifier", required = true)
    protected List<IdentifierType> unitIdentifier;
    /**
     * The Archival agency.
     */
    @XmlElement(name = "ArchivalAgency", required = true)
    protected OrganizationWithIdType archivalAgency;
    /**
     * The Requester.
     */
    @XmlElement(name = "Requester", required = true)
    protected OrganizationWithIdType requester;

    /**
     * Gets the value of the derogation property.
     *
     * @return the boolean
     */
    public boolean isDerogation() {
        return derogation;
    }

    /**
     * Sets the value of the derogation property.
     *
     * @param value the value
     */
    public void setDerogation(boolean value) {
        this.derogation = value;
    }

    /**
     * Gets the value of the unitIdentifier property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the unitIdentifier property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnitIdentifier().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link IdentifierType
     * }*
     *
     * @return the unit identifier
     */
    public List<IdentifierType> getUnitIdentifier() {
        if (unitIdentifier == null) {
            unitIdentifier = new ArrayList<>();
        }
        return this.unitIdentifier;
    }

    /**
     * Gets the value of the archivalAgency property.
     *
     * @return possible object is {@link OrganizationWithIdType }
     */
    public OrganizationWithIdType getArchivalAgency() {
        return archivalAgency;
    }

    /**
     * Sets the value of the archivalAgency property.
     *
     * @param value allowed object is {@link OrganizationWithIdType }
     */
    public void setArchivalAgency(OrganizationWithIdType value) {
        this.archivalAgency = value;
    }

    /**
     * Gets the value of the requester property.
     *
     * @return possible object is {@link OrganizationWithIdType }
     */
    public OrganizationWithIdType getRequester() {
        return requester;
    }

    /**
     * Sets the value of the requester property.
     *
     * @param value allowed object is {@link OrganizationWithIdType }
     */
    public void setRequester(OrganizationWithIdType value) {
        this.requester = value;
    }

}
