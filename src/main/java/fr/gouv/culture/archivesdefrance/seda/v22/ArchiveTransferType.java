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
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ArchiveTransferType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ArchiveTransferType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2.2}BusinessRequestMessageType">
 *       <sequence>
 *         <element name="RelatedTransferReference" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}IdentifierType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="TransferRequestReplyIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}IdentifierType" minOccurs="0"/>
 *         <element name="ArchivalAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}OrganizationWithIdType"/>
 *         <element name="TransferringAgency" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}OrganizationWithIdType"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArchiveTransferType", propOrder = {
    "relatedTransferReference",
    "transferRequestReplyIdentifier",
    "archivalAgency",
    "transferringAgency"
})
@XmlRootElement(name = "ArchiveTransfer")
public class ArchiveTransferType
    extends BusinessRequestMessageType
{

    /**
     * Identifiant d’un transfert
     *                                 associé.
     * 
     */
    @XmlElement(name = "RelatedTransferReference")
    protected List<IdentifierType> relatedTransferReference;
    /**
     * Identifiant de la réponse à une demande de
     *                                 transfert.
     * 
     */
    @XmlElement(name = "TransferRequestReplyIdentifier")
    protected IdentifierType transferRequestReplyIdentifier;
    /**
     * Service d'archives responsable du
     *                                 transfert.
     * 
     */
    @XmlElement(name = "ArchivalAgency", required = true)
    protected OrganizationWithIdType archivalAgency;
    /**
     * Service versant chargé de réaliser le
     *                                 transport.
     * 
     */
    @XmlElement(name = "TransferringAgency", required = true)
    protected OrganizationWithIdType transferringAgency;

    /**
     * Identifiant d’un transfert
     *                                 associé.
     * 
     * Gets the value of the relatedTransferReference property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the relatedTransferReference property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getRelatedTransferReference().add(newItem);
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
     *     The value of the relatedTransferReference property.
     */
    public List<IdentifierType> getRelatedTransferReference() {
        if (relatedTransferReference == null) {
            relatedTransferReference = new ArrayList<>();
        }
        return this.relatedTransferReference;
    }

    /**
     * Identifiant de la réponse à une demande de
     *                                 transfert.
     * 
     * @return
     *     possible object is
     *     {@link IdentifierType }
     *     
     */
    public IdentifierType getTransferRequestReplyIdentifier() {
        return transferRequestReplyIdentifier;
    }

    /**
     * Définit la valeur de la propriété transferRequestReplyIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifierType }
     *     
     * @see #getTransferRequestReplyIdentifier()
     */
    public void setTransferRequestReplyIdentifier(IdentifierType value) {
        this.transferRequestReplyIdentifier = value;
    }

    /**
     * Service d'archives responsable du
     *                                 transfert.
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
     * Service versant chargé de réaliser le
     *                                 transport.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationWithIdType }
     *     
     */
    public OrganizationWithIdType getTransferringAgency() {
        return transferringAgency;
    }

    /**
     * Définit la valeur de la propriété transferringAgency.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationWithIdType }
     *     
     * @see #getTransferringAgency()
     */
    public void setTransferringAgency(OrganizationWithIdType value) {
        this.transferringAgency = value;
    }

}
