//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.01.15 at 09:28:44 PM CET 
//


package fr.gouv.culture.archivesdefrance.seda.v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * La liste d'identifiants de règles à appliquer et à ignorer qui doit être appliquée à partir de cet ArchiveUnit.
 * 
 * <p>Java class for AppraisalRuleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AppraisalRuleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="Rule" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}RuleIdType"/>
 *           &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;choice minOccurs="0">
 *           &lt;group ref="{fr:gouv:culture:archivesdefrance:seda:v2.1}PreventInheritanceGroup"/>
 *           &lt;element name="RefNonRuleId" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}RuleIdType" maxOccurs="unbounded"/>
 *         &lt;/choice>
 *         &lt;element name="FinalAction" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}FinalActionAppraisalCodeType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppraisalRuleType", propOrder = {
    "ruleAndStartDate",
    "preventInheritance",
    "refNonRuleId",
    "finalAction"
})
public class AppraisalRuleType {

    @XmlElements({
        @XmlElement(name = "Rule", type = RuleIdType.class),
        @XmlElement(name = "StartDate", type = XMLGregorianCalendar.class, nillable = true)
    })
    protected List<Object> ruleAndStartDate;
    @XmlElement(name = "PreventInheritance", defaultValue = "false")
    protected Boolean preventInheritance;
    @XmlElement(name = "RefNonRuleId")
    protected List<RuleIdType> refNonRuleId;
    @XmlElement(name = "FinalAction", required = true)
    @XmlSchemaType(name = "token")
    protected FinalActionAppraisalCodeType finalAction;

    /**
     * Gets the value of the ruleAndStartDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ruleAndStartDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRuleAndStartDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RuleIdType }
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<Object> getRuleAndStartDate() {
        if (ruleAndStartDate == null) {
            ruleAndStartDate = new ArrayList<Object>();
        }
        return this.ruleAndStartDate;
    }

    /**
     * Gets the value of the preventInheritance property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPreventInheritance() {
        return preventInheritance;
    }

    /**
     * Sets the value of the preventInheritance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPreventInheritance(Boolean value) {
        this.preventInheritance = value;
    }

    /**
     * Gets the value of the refNonRuleId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refNonRuleId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefNonRuleId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RuleIdType }
     * 
     * 
     */
    public List<RuleIdType> getRefNonRuleId() {
        if (refNonRuleId == null) {
            refNonRuleId = new ArrayList<RuleIdType>();
        }
        return this.refNonRuleId;
    }

    /**
     * Gets the value of the finalAction property.
     * 
     * @return
     *     possible object is
     *     {@link FinalActionAppraisalCodeType }
     *     
     */
    public FinalActionAppraisalCodeType getFinalAction() {
        return finalAction;
    }

    /**
     * Sets the value of the finalAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link FinalActionAppraisalCodeType }
     *     
     */
    public void setFinalAction(FinalActionAppraisalCodeType value) {
        this.finalAction = value;
    }

}
