//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.10.29 at 01:17:12 AM CET 
//
package fr.gouv.culture.archivesdefrance.seda.v2;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for SignatureType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SignatureType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Signer" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}SignerType" maxOccurs="unbounded"/>
 *         &lt;element name="Validator" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}ValidatorType"/>
 *         &lt;element name="Masterdata" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}CodeType" minOccurs="0"/>
 *         &lt;element name="ReferencedObject" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}ReferencedObjectType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SignatureType", propOrder = {
    "signer",
    "validator",
    "masterdata",
    "referencedObject"
})
public class SignatureType {

    /**
     * The Signer.
     */
    @XmlElement(name = "Signer", required = true)
    protected List<SignerType> signer;
    /**
     * The Validator.
     */
    @XmlElement(name = "Validator", required = true)
    protected ValidatorType validator;
    /**
     * The Masterdata.
     */
    @XmlElement(name = "Masterdata")
    protected CodeType masterdata;
    /**
     * The Referenced object.
     */
    @XmlElement(name = "ReferencedObject", required = true)
    protected ReferencedObjectType referencedObject;

    /**
     * Gets the value of the signer property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the signer property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSigner().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link SignerType
     * }*
     *
     * @return the signer
     */
    public List<SignerType> getSigner() {
        if (signer == null) {
            signer = new ArrayList<SignerType>();
        }
        return this.signer;
    }

    /**
     * Gets the value of the validator property.
     *
     * @return possible object is {@link ValidatorType }
     */
    public ValidatorType getValidator() {
        return validator;
    }

    /**
     * Sets the value of the validator property.
     *
     * @param value allowed object is {@link ValidatorType }
     */
    public void setValidator(ValidatorType value) {
        this.validator = value;
    }

    /**
     * Gets the value of the masterdata property.
     *
     * @return possible object is {@link CodeType }
     */
    public CodeType getMasterdata() {
        return masterdata;
    }

    /**
     * Sets the value of the masterdata property.
     *
     * @param value allowed object is {@link CodeType }
     */
    public void setMasterdata(CodeType value) {
        this.masterdata = value;
    }

    /**
     * Gets the value of the referencedObject property.
     *
     * @return possible object is {@link ReferencedObjectType }
     */
    public ReferencedObjectType getReferencedObject() {
        return referencedObject;
    }

    /**
     * Sets the value of the referencedObject property.
     *
     * @param value allowed object is {@link ReferencedObjectType }
     */
    public void setReferencedObject(ReferencedObjectType value) {
        this.referencedObject = value;
    }

}
