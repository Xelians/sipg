//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:05:56 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour AcknowledgementType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AcknowledgementType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2.3}MessageType">
 *       <sequence>
 *         <element name="MessageReceivedIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}IdentifierType"/>
 *         <element name="Sender" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}OrganizationWithIdType"/>
 *         <element name="Receiver" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}OrganizationWithIdType"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AcknowledgementType", propOrder = {
    "messageReceivedIdentifier",
    "sender",
    "receiver"
})
public class AcknowledgementType
    extends MessageType
{

    /**
     * Identifiant du message reçu.
     * 
     */
    @XmlElement(name = "MessageReceivedIdentifier", required = true)
    protected IdentifierType messageReceivedIdentifier;
    /**
     * Expéditeur du message.
     * 
     */
    @XmlElement(name = "Sender", required = true)
    protected OrganizationWithIdType sender;
    /**
     * Destinataire du message.
     * 
     */
    @XmlElement(name = "Receiver", required = true)
    protected OrganizationWithIdType receiver;

    /**
     * Identifiant du message reçu.
     * 
     * @return
     *     possible object is
     *     {@link IdentifierType }
     *     
     */
    public IdentifierType getMessageReceivedIdentifier() {
        return messageReceivedIdentifier;
    }

    /**
     * Définit la valeur de la propriété messageReceivedIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifierType }
     *     
     * @see #getMessageReceivedIdentifier()
     */
    public void setMessageReceivedIdentifier(IdentifierType value) {
        this.messageReceivedIdentifier = value;
    }

    /**
     * Expéditeur du message.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationWithIdType }
     *     
     */
    public OrganizationWithIdType getSender() {
        return sender;
    }

    /**
     * Définit la valeur de la propriété sender.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationWithIdType }
     *     
     * @see #getSender()
     */
    public void setSender(OrganizationWithIdType value) {
        this.sender = value;
    }

    /**
     * Destinataire du message.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationWithIdType }
     *     
     */
    public OrganizationWithIdType getReceiver() {
        return receiver;
    }

    /**
     * Définit la valeur de la propriété receiver.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationWithIdType }
     *     
     * @see #getReceiver()
     */
    public void setReceiver(OrganizationWithIdType value) {
        this.receiver = value;
    }

}
