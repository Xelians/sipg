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
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java pour BusinessReplyMessageType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="BusinessReplyMessageType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2.2}BusinessMessageType">
 *       <sequence>
 *         <element name="ReplyCode" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}NonEmptyTokenType" minOccurs="0"/>
 *         <element name="Operation" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}OperationType" minOccurs="0"/>
 *         <element name="MessageRequestIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}IdentifierType"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessReplyMessageType", propOrder = {
    "replyCode",
    "operation",
    "messageRequestIdentifier"
})
@XmlSeeAlso({
    ArchiveDeliveryRequestReplyType.class,
    ArchiveRestitutionRequestReplyType.class,
    ArchiveTransferReplyType.class,
    ArchiveTransferRequestReplyType.class,
    BusinessAuthorizationRequestReplyMessageType.class
})
public abstract class BusinessReplyMessageType
    extends BusinessMessageType
{

    /**
     * Code de la réponse.
     * 
     */
    @XmlElement(name = "ReplyCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String replyCode;
    /**
     * Liste des événements dans les messages de
     *                                 réponse
     * 
     */
    @XmlElement(name = "Operation")
    protected OperationType operation;
    /**
     * Identifiant de la demande.
     * 
     */
    @XmlElement(name = "MessageRequestIdentifier", required = true)
    protected IdentifierType messageRequestIdentifier;

    /**
     * Code de la réponse.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReplyCode() {
        return replyCode;
    }

    /**
     * Définit la valeur de la propriété replyCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getReplyCode()
     */
    public void setReplyCode(String value) {
        this.replyCode = value;
    }

    /**
     * Liste des événements dans les messages de
     *                                 réponse
     * 
     * @return
     *     possible object is
     *     {@link OperationType }
     *     
     */
    public OperationType getOperation() {
        return operation;
    }

    /**
     * Définit la valeur de la propriété operation.
     * 
     * @param value
     *     allowed object is
     *     {@link OperationType }
     *     
     * @see #getOperation()
     */
    public void setOperation(OperationType value) {
        this.operation = value;
    }

    /**
     * Identifiant de la demande.
     * 
     * @return
     *     possible object is
     *     {@link IdentifierType }
     *     
     */
    public IdentifierType getMessageRequestIdentifier() {
        return messageRequestIdentifier;
    }

    /**
     * Définit la valeur de la propriété messageRequestIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifierType }
     *     
     * @see #getMessageRequestIdentifier()
     */
    public void setMessageRequestIdentifier(IdentifierType value) {
        this.messageRequestIdentifier = value;
    }

}
