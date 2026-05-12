//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:09:37 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v22;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ArchiveRestitutionRequestType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ArchiveRestitutionRequestType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2.2}BusinessRequestMessageType">
 *       <sequence>
 *         <element name="UnitIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}IdentifierType" maxOccurs="unbounded"/>
 *         <element name="ArchivalAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}OrganizationWithIdType"/>
 *         <element name="OriginatingAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}OrganizationWithIdType"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArchiveRestitutionRequestType", propOrder = {
    "unitIdentifier",
    "archivalAgency",
    "originatingAgency"
})
public class ArchiveRestitutionRequestType
    extends BusinessRequestMessageType
{

    /**
     * Identifiant de l'unité
     *                                 d'archives.
     * 
     */
    @XmlElement(name = "UnitIdentifier", required = true)
    protected List<IdentifierType> unitIdentifier;
    /**
     * Service d'archives responsable de la
     *                                 restitution.
     * 
     */
    @XmlElement(name = "ArchivalAgency", required = true)
    protected OrganizationWithIdType archivalAgency;
    /**
     * Service producteur demandant la
     *                                 restitution.
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
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the unitIdentifier property.</p>
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
     * Service d'archives responsable de la
     *                                 restitution.
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
     * Définit la valeur de la propriété archivalAgency.
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
     * Service producteur demandant la
     *                                 restitution.
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
     * Définit la valeur de la propriété originatingAgency.
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
