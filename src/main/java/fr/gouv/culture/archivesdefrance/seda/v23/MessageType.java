//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:05:56 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java pour MessageType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MessageType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="Comment" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}TextType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="Date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         <element name="MessageIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType"/>
 *         <element name="Signature" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}SignatureMessageType" minOccurs="0"/>
 *       </sequence>
 *       <attribute ref="{http://www.w3.org/XML/1998/namespace}id"/>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageType", propOrder = {
    "comment",
    "date",
    "messageIdentifier",
    "signature"
})
@XmlSeeAlso({
    AcknowledgementType.class,
    BusinessMessageType.class
})
public abstract class MessageType {

    /**
     * Commentaire sur le message.
     * 
     */
    @XmlElement(name = "Comment")
    protected List<TextType> comment;
    /**
     * Date du message.
     * 
     */
    @XmlElement(name = "Date", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    /**
     * Identifiant du message.
     * 
     */
    @XmlElement(name = "MessageIdentifier", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String messageIdentifier;
    /**
     * Signature du message.
     * 
     */
    @XmlElement(name = "Signature")
    protected SignatureMessageType signature;
    /**
     * &lt;pre&gt;
     * &amp;lt;?xml version="1.0" encoding="UTF-8"?&amp;gt;&amp;lt;div xmlns="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&amp;gt;&amp;lt;h3&amp;gt;id (as an attribute name)&amp;lt;/h3&amp;gt;&amp;lt;p&amp;gt;
     *        denotes an attribute whose value
     *        should be interpreted as if declared to be of type ID.
     *        This name is reserved by virtue of its definition in the
     *        xml:id specification.&amp;lt;/p&amp;gt;&amp;lt;p&amp;gt;
     *       See &amp;lt;a href="http://www.w3.org/TR/xml-id/"&amp;gt;http://www.w3.org/TR/xml-id/&amp;lt;/a&amp;gt;
     *       for information about this attribute.
     *      &amp;lt;/p&amp;gt;&amp;lt;/div&amp;gt;
     * &lt;/pre&gt;
     * 
     */
    @XmlAttribute(name = "id", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Commentaire sur le message.
     * 
     * Gets the value of the comment property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the comment property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getComment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextType }
     * </p>
     * 
     * 
     * @return
     *     The value of the comment property.
     */
    public List<TextType> getComment() {
        if (comment == null) {
            comment = new ArrayList<>();
        }
        return this.comment;
    }

    /**
     * Date du message.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Définit la valeur de la propriété date.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     * @see #getDate()
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Identifiant du message.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageIdentifier() {
        return messageIdentifier;
    }

    /**
     * Définit la valeur de la propriété messageIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getMessageIdentifier()
     */
    public void setMessageIdentifier(String value) {
        this.messageIdentifier = value;
    }

    /**
     * Signature du message.
     * 
     * @return
     *     possible object is
     *     {@link SignatureMessageType }
     *     
     */
    public SignatureMessageType getSignature() {
        return signature;
    }

    /**
     * Définit la valeur de la propriété signature.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureMessageType }
     *     
     * @see #getSignature()
     */
    public void setSignature(SignatureMessageType value) {
        this.signature = value;
    }

    /**
     * &lt;pre&gt;
     * &amp;lt;?xml version="1.0" encoding="UTF-8"?&amp;gt;&amp;lt;div xmlns="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&amp;gt;&amp;lt;h3&amp;gt;id (as an attribute name)&amp;lt;/h3&amp;gt;&amp;lt;p&amp;gt;
     *        denotes an attribute whose value
     *        should be interpreted as if declared to be of type ID.
     *        This name is reserved by virtue of its definition in the
     *        xml:id specification.&amp;lt;/p&amp;gt;&amp;lt;p&amp;gt;
     *       See &amp;lt;a href="http://www.w3.org/TR/xml-id/"&amp;gt;http://www.w3.org/TR/xml-id/&amp;lt;/a&amp;gt;
     *       for information about this attribute.
     *      &amp;lt;/p&amp;gt;&amp;lt;/div&amp;gt;
     * &lt;/pre&gt;
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getId()
     */
    public void setId(String value) {
        this.id = value;
    }

}
