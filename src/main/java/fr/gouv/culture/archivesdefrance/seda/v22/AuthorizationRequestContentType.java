//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
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
 * <p>Java class for AuthorizationRequestContentType complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
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
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;div xmlns="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;&lt;h3&gt;id (as an attribute name)&lt;/h3&gt;&lt;p&gt;
     *        denotes an attribute whose value
     *        should be interpreted as if declared to be of type ID.
     *        This name is reserved by virtue of its definition in the
     *        xml:id specification.&lt;/p&gt;&lt;p&gt;
     *       See &lt;a href="http://www.w3.org/TR/xml-id/"&gt;http://www.w3.org/TR/xml-id/&lt;/a&gt;
     *       for information about this attribute.&lt;/p&gt;&lt;/div&gt;
     * </pre>
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
     * Sets the value of the authorizationReason property.
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
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comment property.</p>
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
     * Sets the value of the requestDate property.
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
     * Sets the value of the requester property.
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
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the authorizationRequestReply property.</p>
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
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;div xmlns="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;&lt;h3&gt;id (as an attribute name)&lt;/h3&gt;&lt;p&gt;
     *        denotes an attribute whose value
     *        should be interpreted as if declared to be of type ID.
     *        This name is reserved by virtue of its definition in the
     *        xml:id specification.&lt;/p&gt;&lt;p&gt;
     *       See &lt;a href="http://www.w3.org/TR/xml-id/"&gt;http://www.w3.org/TR/xml-id/&lt;/a&gt;
     *       for information about this attribute.&lt;/p&gt;&lt;/div&gt;
     * </pre>
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
     * Sets the value of the id property.
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
