//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:09:37 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v22;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour OrganizationType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="OrganizationType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="Identifier" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}IdentifierType"/>
 *         <element name="OrganizationDescriptiveMetadata" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}OrganizationDescriptiveMetadataType" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganizationType", propOrder = {
    "identifier",
    "organizationDescriptiveMetadata"
})
@XmlSeeAlso({
    OrganizationWithIdType.class
})
public class OrganizationType {

    /**
     * Identifiant de l'organisation.
     * 
     */
    @XmlElement(name = "Identifier", required = true)
    protected IdentifierType identifier;
    /**
     * Métadonnées de description de l'organisation.
     * 
     */
    @XmlElement(name = "OrganizationDescriptiveMetadata")
    protected OrganizationDescriptiveMetadataType organizationDescriptiveMetadata;

    /**
     * Identifiant de l'organisation.
     * 
     * @return
     *     possible object is
     *     {@link IdentifierType }
     *     
     */
    public IdentifierType getIdentifier() {
        return identifier;
    }

    /**
     * Définit la valeur de la propriété identifier.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifierType }
     *     
     * @see #getIdentifier()
     */
    public void setIdentifier(IdentifierType value) {
        this.identifier = value;
    }

    /**
     * Métadonnées de description de l'organisation.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationDescriptiveMetadataType }
     *     
     */
    public OrganizationDescriptiveMetadataType getOrganizationDescriptiveMetadata() {
        return organizationDescriptiveMetadata;
    }

    /**
     * Définit la valeur de la propriété organizationDescriptiveMetadata.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationDescriptiveMetadataType }
     *     
     * @see #getOrganizationDescriptiveMetadata()
     */
    public void setOrganizationDescriptiveMetadata(OrganizationDescriptiveMetadataType value) {
        this.organizationDescriptiveMetadata = value;
    }

}
