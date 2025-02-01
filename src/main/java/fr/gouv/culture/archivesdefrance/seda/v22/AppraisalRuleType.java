//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v22;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * La liste d'identifiants de règles à appliquer et à ignorer qui doit être appliquée à partir de
 * cet ArchiveUnit.
 *
 * <p>Java class for AppraisalRuleType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="AppraisalRuleType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <sequence maxOccurs="unbounded" minOccurs="0">
 *           <element name="Rule" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}RuleIdType"/>
 *           <element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         </sequence>
 *         <choice minOccurs="0">
 *           <group ref="{fr:gouv:culture:archivesdefrance:seda:v2.2}PreventInheritanceGroup"/>
 *           <element name="RefNonRuleId" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}RuleIdType" maxOccurs="unbounded"/>
 *         </choice>
 *         <element name="FinalAction" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}FinalActionAppraisalCodeType"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "AppraisalRuleType",
    propOrder = {"ruleAndStartDate", "preventInheritance", "refNonRuleId", "finalAction"})
public class AppraisalRuleType {

  @XmlElements({
    @XmlElement(name = "Rule", type = RuleIdType.class),
    @XmlElement(name = "StartDate", type = XMLGregorianCalendar.class, nillable = true)
  })
  protected List<Object> ruleAndStartDate;

  /**
   * Indique si les règles de gestion héritées des ArchiveUnit parents doivent être ignorées pour
   * l’ArchiveUnit concerné.
   */
  @XmlElement(name = "PreventInheritance", defaultValue = "false")
  protected Boolean preventInheritance;

  /** L'identifiant de la règle spécifiée pourra être retirée de l'héritage dans ce noeud. */
  @XmlElement(name = "RefNonRuleId")
  protected List<RuleIdType> refNonRuleId;

  /** Action à mettre en œuvre au terme de la durée de gestion. */
  @XmlElement(name = "FinalAction", required = true)
  @XmlSchemaType(name = "token")
  protected FinalActionAppraisalCodeType finalAction;

  /**
   * Gets the value of the ruleAndStartDate property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the ruleAndStartDate property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   * getRuleAndStartDate().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link RuleIdType } {@link
   * XMLGregorianCalendar }
   *
   * @return The value of the ruleAndStartDate property.
   */
  public List<Object> getRuleAndStartDate() {
    if (ruleAndStartDate == null) {
      ruleAndStartDate = new ArrayList<>();
    }
    return this.ruleAndStartDate;
  }

  /**
   * Indique si les règles de gestion héritées des ArchiveUnit parents doivent être ignorées pour
   * l’ArchiveUnit concerné.
   *
   * @return possible object is {@link Boolean }
   */
  public Boolean isPreventInheritance() {
    return preventInheritance;
  }

  /**
   * Sets the value of the preventInheritance property.
   *
   * @param value allowed object is {@link Boolean }
   * @see #isPreventInheritance()
   */
  public void setPreventInheritance(Boolean value) {
    this.preventInheritance = value;
  }

  /**
   * L'identifiant de la règle spécifiée pourra être retirée de l'héritage dans ce noeud.
   *
   * <p>Gets the value of the refNonRuleId property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the refNonRuleId property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   * getRefNonRuleId().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link RuleIdType }
   *
   * @return The value of the refNonRuleId property.
   */
  public List<RuleIdType> getRefNonRuleId() {
    if (refNonRuleId == null) {
      refNonRuleId = new ArrayList<>();
    }
    return this.refNonRuleId;
  }

  /**
   * Action à mettre en œuvre au terme de la durée de gestion.
   *
   * @return possible object is {@link FinalActionAppraisalCodeType }
   */
  public FinalActionAppraisalCodeType getFinalAction() {
    return finalAction;
  }

  /**
   * Sets the value of the finalAction property.
   *
   * @param value allowed object is {@link FinalActionAppraisalCodeType }
   * @see #getFinalAction()
   */
  public void setFinalAction(FinalActionAppraisalCodeType value) {
    this.finalAction = value;
  }
}
