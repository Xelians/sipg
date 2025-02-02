//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * La liste d'identifiants de règles à appliquer et à ignorer qui doit être appliquée à partir de
 * cet ArchiveUnit.
 *
 * <p>Java class for HoldRuleType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="HoldRuleType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <sequence maxOccurs="unbounded" minOccurs="0">
 *           <element name="Rule" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}RuleIdType"/>
 *           <element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *           <element name="HoldEndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *           <element name="HoldOwner" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType" minOccurs="0"/>
 *           <element name="HoldReassessingDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *           <element name="HoldReason" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType" minOccurs="0"/>
 *           <element name="PreventRearrangement" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         </sequence>
 *         <choice minOccurs="0">
 *           <group ref="{fr:gouv:culture:archivesdefrance:seda:v2.3}PreventInheritanceGroup"/>
 *           <element name="RefNonRuleId" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}RuleIdType" maxOccurs="unbounded"/>
 *         </choice>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "HoldRuleType",
    propOrder = {"ruleAndStartDateAndHoldEndDate", "preventInheritance", "refNonRuleId"})
public class HoldRuleType {

  @XmlElementRefs({
    @XmlElementRef(
        name = "Rule",
        namespace = "fr:gouv:culture:archivesdefrance:seda:v2.3",
        type = JAXBElement.class,
        required = false),
    @XmlElementRef(
        name = "StartDate",
        namespace = "fr:gouv:culture:archivesdefrance:seda:v2.3",
        type = JAXBElement.class,
        required = false),
    @XmlElementRef(
        name = "HoldEndDate",
        namespace = "fr:gouv:culture:archivesdefrance:seda:v2.3",
        type = JAXBElement.class,
        required = false),
    @XmlElementRef(
        name = "HoldOwner",
        namespace = "fr:gouv:culture:archivesdefrance:seda:v2.3",
        type = JAXBElement.class,
        required = false),
    @XmlElementRef(
        name = "HoldReassessingDate",
        namespace = "fr:gouv:culture:archivesdefrance:seda:v2.3",
        type = JAXBElement.class,
        required = false),
    @XmlElementRef(
        name = "HoldReason",
        namespace = "fr:gouv:culture:archivesdefrance:seda:v2.3",
        type = JAXBElement.class,
        required = false),
    @XmlElementRef(
        name = "PreventRearrangement",
        namespace = "fr:gouv:culture:archivesdefrance:seda:v2.3",
        type = JAXBElement.class,
        required = false)
  })
  protected List<JAXBElement<?>> ruleAndStartDateAndHoldEndDate;

  /**
   * Indique si les règles de gestion héritées des ArchiveUnit parents doivent être ignorées pour
   * l’ArchiveUnit concerné.
   */
  @XmlElement(name = "PreventInheritance", defaultValue = "false")
  protected Boolean preventInheritance;

  /** L'identifiant de la règle spécifiée pourra être retirée de l'héritage dans ce noeud. */
  @XmlElement(name = "RefNonRuleId")
  protected List<RuleIdType> refNonRuleId;

  /**
   * Gets the value of the ruleAndStartDateAndHoldEndDate property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the ruleAndStartDateAndHoldEndDate property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   * getRuleAndStartDateAndHoldEndDate().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link JAXBElement }{@code <}{@link
   * RuleIdType }{@code >} {@link JAXBElement }{@code <}{@link Boolean }{@code >} {@link JAXBElement
   * }{@code <}{@link String }{@code >} {@link JAXBElement }{@code <}{@link String }{@code >} {@link
   * JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >} {@link JAXBElement }{@code
   * <}{@link XMLGregorianCalendar }{@code >} {@link JAXBElement }{@code <}{@link
   * XMLGregorianCalendar }{@code >}
   *
   * @return The value of the ruleAndStartDateAndHoldEndDate property.
   */
  public List<JAXBElement<?>> getRuleAndStartDateAndHoldEndDate() {
    if (ruleAndStartDateAndHoldEndDate == null) {
      ruleAndStartDateAndHoldEndDate = new ArrayList<>();
    }
    return this.ruleAndStartDateAndHoldEndDate;
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
}
