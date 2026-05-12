//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:09:37 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v22;

import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java pour AuthorizationRequestContentType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AuthorizationRequestContentType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="AuthorizationReason" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         <element name="Comment" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}TextType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="RequestDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         <element name="UnitIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}IdentifierType" maxOccurs="unbounded"/>
 *         <element name="Requester" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}OrganizationType"/>
 *         <element name="AuthorizationRequestReply" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}BusinessAuthorizationRequestReplyMessageType" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "AuthorizationRequestContentType", propOrder = {
    "authorizationReason",
    "comment",
    "requestDate",
    "unitIdentifier",
    "requester",
    "authorizationRequestReply"
})
public class AuthorizationRequestContentType {

    /**
     * Motif de l'autorisation.
     * 
     */
    @XmlElement(name = "AuthorizationReason", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String authorizationReason;
    /**
     * Commentaire sur la transaction.
     * 
     */
    @XmlElement(name = "Comment")
    protected List<TextType> comment;
    /**
     * Date de la demande d'autorisation.
     * 
     */
    @XmlElement(name = "RequestDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar requestDate;
    /**
     * Identifiant de l'unité documentaire.
     * 
     */
    @XmlElement(name = "UnitIdentifier", required = true)
    protected List<IdentifierType> unitIdentifier;
    /**
     * Demandeur de l'autorisation.
     * 
     */
    @XmlElement(name = "Requester", required = true)
    protected OrganizationType requester;
    /**
     * Réponse à la demande d’autorisation.
     * 
     */
    @XmlElement(name = "AuthorizationRequestReply")
    protected List<BusinessAuthorizationRequestReplyMessageType> authorizationRequestReply;
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
     * Motif de l'autorisation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorizationReason() {
        return authorizationReason;
    }

    /**
     * Définit la valeur de la propriété authorizationReason.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getAuthorizationReason()
     */
    public void setAuthorizationReason(String value) {
        this.authorizationReason = value;
    }

    /**
     * Commentaire sur la transaction.
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
     * Date de la demande d'autorisation.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRequestDate() {
        return requestDate;
    }

    /**
     * Définit la valeur de la propriété requestDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     * @see #getRequestDate()
     */
    public void setRequestDate(XMLGregorianCalendar value) {
        this.requestDate = value;
    }

    /**
     * Identifiant de l'unité documentaire.
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
     * Demandeur de l'autorisation.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationType }
     *     
     */
    public OrganizationType getRequester() {
        return requester;
    }

    /**
     * Définit la valeur de la propriété requester.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationType }
     *     
     * @see #getRequester()
     */
    public void setRequester(OrganizationType value) {
        this.requester = value;
    }

    /**
     * Réponse à la demande d’autorisation.
     * 
     * Gets the value of the authorizationRequestReply property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the authorizationRequestReply property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAuthorizationRequestReply().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BusinessAuthorizationRequestReplyMessageType }
     * </p>
     * 
     * 
     * @return
     *     The value of the authorizationRequestReply property.
     */
    public List<BusinessAuthorizationRequestReplyMessageType> getAuthorizationRequestReply() {
        if (authorizationRequestReply == null) {
            authorizationRequestReply = new ArrayList<>();
        }
        return this.authorizationRequestReply;
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
