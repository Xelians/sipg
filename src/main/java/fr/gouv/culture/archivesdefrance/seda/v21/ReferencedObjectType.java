//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.10.29 at 01:17:12 AM CET 
//
package fr.gouv.culture.archivesdefrance.seda.v21;

import jakarta.xml.bind.annotation.*;

/**
 * Contient la référence à l'objet signé (et son empreinte jusqu'à la fin de la phase de versement dans le SAE).
 *
 * <p>
 * Java class for ReferencedObjectType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ReferencedObjectType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SignedObjectId" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}DataObjectRefIdType"/>
 *         &lt;element name="SignedObjectDigest" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}MessageDigestBinaryObjectType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferencedObjectType", propOrder = {
    "signedObjectId",
    "signedObjectDigest"
})
public class ReferencedObjectType {

    /**
     * The Signed object id.
     */
    @XmlElement(name = "SignedObjectId", required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object signedObjectId;
    /**
     * The Signed object digest.
     */
    @XmlElement(name = "SignedObjectDigest", required = true)
    protected MessageDigestBinaryObjectType signedObjectDigest;

    /**
     * Gets the value of the signedObjectId property.
     *
     * @return possible object is {@link Object }
     */
    public Object getSignedObjectId() {
        return signedObjectId;
    }

    /**
     * Sets the value of the signedObjectId property.
     *
     * @param value allowed object is {@link Object }
     */
    public void setSignedObjectId(Object value) {
        this.signedObjectId = value;
    }

    /**
     * Gets the value of the signedObjectDigest property.
     *
     * @return possible object is {@link MessageDigestBinaryObjectType }
     */
    public MessageDigestBinaryObjectType getSignedObjectDigest() {
        return signedObjectDigest;
    }

    /**
     * Sets the value of the signedObjectDigest property.
     *
     * @param value allowed object is {@link MessageDigestBinaryObjectType }
     */
    public void setSignedObjectDigest(MessageDigestBinaryObjectType value) {
        this.signedObjectDigest = value;
    }

}