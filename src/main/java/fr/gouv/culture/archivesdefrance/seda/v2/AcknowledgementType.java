//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package fr.gouv.culture.archivesdefrance.seda.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AcknowledgementType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="AcknowledgementType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2}MessageType">
 *       <sequence>
 *         <element name="MessageReceivedIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2}IdentifierType"/>
 *         <element name="Sender" type="{fr:gouv:culture:archivesdefrance:seda:v2}OrganizationWithIdType"/>
 *         <element name="Receiver" type="{fr:gouv:culture:archivesdefrance:seda:v2}OrganizationWithIdType"/>
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

    @XmlElement(name = "MessageReceivedIdentifier", required = true)
    protected IdentifierType messageReceivedIdentifier;
    @XmlElement(name = "Sender", required = true)
    protected OrganizationWithIdType sender;
    @XmlElement(name = "Receiver", required = true)
    protected OrganizationWithIdType receiver;

    /**
     * Gets the value of the messageReceivedIdentifier property.
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
     * Sets the value of the messageReceivedIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifierType }
     *     
     */
    public void setMessageReceivedIdentifier(IdentifierType value) {
        this.messageReceivedIdentifier = value;
    }

    /**
     * Gets the value of the sender property.
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
     * Sets the value of the sender property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationWithIdType }
     *     
     */
    public void setSender(OrganizationWithIdType value) {
        this.sender = value;
    }

    /**
     * Gets the value of the receiver property.
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
     * Sets the value of the receiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationWithIdType }
     *     
     */
    public void setReceiver(OrganizationWithIdType value) {
        this.receiver = value;
    }

}
