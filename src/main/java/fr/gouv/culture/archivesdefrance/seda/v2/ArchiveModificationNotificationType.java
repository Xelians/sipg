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
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArchiveModificationNotificationType complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="ArchiveModificationNotificationType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2}BusinessNotificationMessageType">
 *       <sequence>
 *         <element name="UnitIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2}IdentifierType" maxOccurs="unbounded"/>
 *         <element name="ArchivalAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2}OrganizationWithIdType"/>
 *         <element name="OriginatingAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2}OrganizationWithIdType"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArchiveModificationNotificationType", propOrder = {
    "unitIdentifier",
    "archivalAgency",
    "originatingAgency"
})
public class ArchiveModificationNotificationType
    extends BusinessNotificationMessageType
{

    /**
     * Identifiant de l'unité
     *                                 d'archives.
     * 
     */
    @XmlElement(name = "UnitIdentifier", required = true)
    protected List<IdentifierType> unitIdentifier;
    /**
     * Service d'archives responsable de la notification de
     *                                 modification.
     * 
     */
    @XmlElement(name = "ArchivalAgency", required = true)
    protected OrganizationWithIdType archivalAgency;
    /**
     * Service producteur responsable de la notification de
     *                                 modification.
     * 
     */
    @XmlElement(name = "OriginatingAgency", required = true)
    protected OrganizationWithIdType originatingAgency;

    /**
     * Identifiant de l'unité
     *                                 d'archives.
     * 
     * Gets the value of the unitIdentifier property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the unitIdentifier property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getUnitIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdentifierType }
     * </p>
     * 
     * 
     * @return
     *     The value of the unitIdentifier property.
     */
    public List<IdentifierType> getUnitIdentifier() {
        if (unitIdentifier == null) {
            unitIdentifier = new ArrayList<>();
        }
        return this.unitIdentifier;
    }

    /**
     * Service d'archives responsable de la notification de
     *                                 modification.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationWithIdType }
     *     
     */
    public OrganizationWithIdType getArchivalAgency() {
        return archivalAgency;
    }

    /**
     * Sets the value of the archivalAgency property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationWithIdType }
     *     
     * @see #getArchivalAgency()
     */
    public void setArchivalAgency(OrganizationWithIdType value) {
        this.archivalAgency = value;
    }

    /**
     * Service producteur responsable de la notification de
     *                                 modification.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationWithIdType }
     *     
     */
    public OrganizationWithIdType getOriginatingAgency() {
        return originatingAgency;
    }

    /**
     * Sets the value of the originatingAgency property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationWithIdType }
     *     
     * @see #getOriginatingAgency()
     */
    public void setOriginatingAgency(OrganizationWithIdType value) {
        this.originatingAgency = value;
    }

}
