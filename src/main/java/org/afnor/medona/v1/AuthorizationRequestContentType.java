//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.09.15 at 07:07:47 PM CEST 
//
package org.afnor.medona.v1;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for AuthorizationRequestContentType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AuthorizationRequestContentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AuthorizationReason" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="Comment" type="{org:afnor:medona:v1.0}TextType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="RequestDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="UnitIdentifier" type="{org:afnor:medona:v1.0}IdentifierType" maxOccurs="unbounded"/>
 *         &lt;element name="Requester" type="{org:afnor:medona:v1.0}OrganizationType"/>
 *         &lt;element name="AuthorizationRequestReply" type="{org:afnor:medona:v1.0}BusinessAuthorizationRequestReplyMessageType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}id"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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
     * The Authorization reason.
     */
    @XmlElement(name = "AuthorizationReason", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String authorizationReason;
    /**
     * The Comment.
     */
    @XmlElement(name = "Comment")
    protected List<TextType> comment;
    /**
     * The Request date.
     */
    @XmlElement(name = "RequestDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar requestDate;
    /**
     * The Unit identifier.
     */
    @XmlElement(name = "UnitIdentifier", required = true)
    protected List<IdentifierType> unitIdentifier;
    /**
     * The Requester.
     */
    @XmlElement(name = "Requester", required = true)
    protected OrganizationType requester;
    /**
     * The Authorization request reply.
     */
    @XmlElement(name = "AuthorizationRequestReply")
    protected List<BusinessAuthorizationRequestReplyMessageType> authorizationRequestReply;
    /**
     * The Id.
     */
    @XmlAttribute(name = "id", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the authorizationReason property.
     *
     * @return possible object is {@link String }
     */
    public String getAuthorizationReason() {
        return authorizationReason;
    }

    /**
     * Sets the value of the authorizationReason property.
     *
     * @param value allowed object is {@link String }
     */
    public void setAuthorizationReason(String value) {
        this.authorizationReason = value;
    }

    /**
     * Gets the value of the comment property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the comment property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComment().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link TextType
     * }*
     *
     * @return the comment
     */
    public List<TextType> getComment() {
        if (comment == null) {
            comment = new ArrayList<TextType>();
        }
        return this.comment;
    }

    /**
     * Gets the value of the requestDate property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getRequestDate() {
        return requestDate;
    }

    /**
     * Sets the value of the requestDate property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     */
    public void setRequestDate(XMLGregorianCalendar value) {
        this.requestDate = value;
    }

    /**
     * Gets the value of the unitIdentifier property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the unitIdentifier property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnitIdentifier().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link IdentifierType
     * }*
     *
     * @return the unit identifier
     */
    public List<IdentifierType> getUnitIdentifier() {
        if (unitIdentifier == null) {
            unitIdentifier = new ArrayList<IdentifierType>();
        }
        return this.unitIdentifier;
    }

    /**
     * Gets the value of the requester property.
     *
     * @return possible object is {@link OrganizationType }
     */
    public OrganizationType getRequester() {
        return requester;
    }

    /**
     * Sets the value of the requester property.
     *
     * @param value allowed object is {@link OrganizationType }
     */
    public void setRequester(OrganizationType value) {
        this.requester = value;
    }

    /**
     * Gets the value of the authorizationRequestReply property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the authorizationRequestReply property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthorizationRequestReply().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link BusinessAuthorizationRequestReplyMessageType
     * }*
     *
     * @return the authorization request reply
     */
    public List<BusinessAuthorizationRequestReplyMessageType> getAuthorizationRequestReply() {
        if (authorizationRequestReply == null) {
            authorizationRequestReply = new ArrayList<BusinessAuthorizationRequestReplyMessageType>();
        }
        return this.authorizationRequestReply;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is {@link String }
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is {@link String }
     */
    public void setId(String value) {
        this.id = value;
    }

}
