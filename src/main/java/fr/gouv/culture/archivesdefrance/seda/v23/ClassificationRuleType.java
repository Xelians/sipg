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
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * La liste d'identifiants de règles à appliquer et à ignorer qui doit être appliquée à partir de cet ArchiveUnit.
 * 
 * <p>Classe Java pour ClassificationRuleType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ClassificationRuleType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <sequence maxOccurs="unbounded" minOccurs="0">
 *           <element name="Rule" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}RuleIdType"/>
 *           <element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         </sequence>
 *         <element name="ClassificationAudience" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType" minOccurs="0"/>
 *         <choice minOccurs="0">
 *           <group ref="{fr:gouv:culture:archivesdefrance:seda:v2.3}PreventInheritanceGroup"/>
 *           <element name="RefNonRuleId" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}RuleIdType" maxOccurs="unbounded"/>
 *         </choice>
 *         <element name="ClassificationLevel" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType"/>
 *         <element name="ClassificationOwner" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType"/>
 *         <element name="ClassificationReassessingDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         <element name="NeedReassessingAuthorization" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClassificationRuleType", propOrder = {
    "ruleAndStartDate",
    "classificationAudience",
    "preventInheritance",
    "refNonRuleId",
    "classificationLevel",
    "classificationOwner",
    "classificationReassessingDate",
    "needReassessingAuthorization"
})
public class ClassificationRuleType {

    @XmlElements({
        @XmlElement(name = "Rule", type = RuleIdType.class),
        @XmlElement(name = "StartDate", type = XMLGregorianCalendar.class, nillable = true)
    })
    protected List<Object> ruleAndStartDate;
    /**
     * Permet de gérer les questions de "diffusion restreinte", de "spécial France" et de "Confidentiel Industrie".
     * 
     */
    @XmlElement(name = "ClassificationAudience")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String classificationAudience;
    /**
     * Indique si les règles de gestion héritées des ArchiveUnit parents doivent être ignorées pour l’ArchiveUnit concerné.
     * 
     */
    @XmlElement(name = "PreventInheritance", defaultValue = "false")
    protected Boolean preventInheritance;
    /**
     * L'identifiant de la règle spécifiée pourra être retirée de l'héritage dans ce noeud.
     * 
     */
    @XmlElement(name = "RefNonRuleId")
    protected List<RuleIdType> refNonRuleId;
    /**
     * Référence au niveau de classification.
     * 
     */
    @XmlElement(name = "ClassificationLevel", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String classificationLevel;
    /**
     * Propriétaire de la classification. Service émetteur au sens de l’IGI 1300.
     * 
     */
    @XmlElement(name = "ClassificationOwner", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String classificationOwner;
    /**
     * Date de réévaluation de la classification.
     * 
     */
    @XmlElement(name = "ClassificationReassessingDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar classificationReassessingDate;
    /**
     * Indique si une autorisation humaine est nécessaire pour réévaluer la classification.
     * 
     */
    @XmlElement(name = "NeedReassessingAuthorization")
    protected Boolean needReassessingAuthorization;

    /**
     * Gets the value of the ruleAndStartDate property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the ruleAndStartDate property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getRuleAndStartDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RuleIdType }
     * {@link XMLGregorianCalendar }
     * </p>
     * 
     * 
     * @return
     *     The value of the ruleAndStartDate property.
     */
    public List<Object> getRuleAndStartDate() {
        if (ruleAndStartDate == null) {
            ruleAndStartDate = new ArrayList<>();
        }
        return this.ruleAndStartDate;
    }

    /**
     * Permet de gérer les questions de "diffusion restreinte", de "spécial France" et de "Confidentiel Industrie".
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassificationAudience() {
        return classificationAudience;
    }

    /**
     * Définit la valeur de la propriété classificationAudience.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getClassificationAudience()
     */
    public void setClassificationAudience(String value) {
        this.classificationAudience = value;
    }

    /**
     * Indique si les règles de gestion héritées des ArchiveUnit parents doivent être ignorées pour l’ArchiveUnit concerné.
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
     * Définit la valeur de la propriété preventInheritance.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     * @see #isPreventInheritance()
     */
    public void setPreventInheritance(Boolean value) {
        this.preventInheritance = value;
    }

    /**
     * L'identifiant de la règle spécifiée pourra être retirée de l'héritage dans ce noeud.
     * 
     * Gets the value of the refNonRuleId property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the refNonRuleId property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getRefNonRuleId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RuleIdType }
     * </p>
     * 
     * 
     * @return
     *     The value of the refNonRuleId property.
     */
    public List<RuleIdType> getRefNonRuleId() {
        if (refNonRuleId == null) {
            refNonRuleId = new ArrayList<>();
        }
        return this.refNonRuleId;
    }

    /**
     * Référence au niveau de classification.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassificationLevel() {
        return classificationLevel;
    }

    /**
     * Définit la valeur de la propriété classificationLevel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getClassificationLevel()
     */
    public void setClassificationLevel(String value) {
        this.classificationLevel = value;
    }

    /**
     * Propriétaire de la classification. Service émetteur au sens de l’IGI 1300.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassificationOwner() {
        return classificationOwner;
    }

    /**
     * Définit la valeur de la propriété classificationOwner.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getClassificationOwner()
     */
    public void setClassificationOwner(String value) {
        this.classificationOwner = value;
    }

    /**
     * Date de réévaluation de la classification.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getClassificationReassessingDate() {
        return classificationReassessingDate;
    }

    /**
     * Définit la valeur de la propriété classificationReassessingDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     * @see #getClassificationReassessingDate()
     */
    public void setClassificationReassessingDate(XMLGregorianCalendar value) {
        this.classificationReassessingDate = value;
    }

    /**
     * Indique si une autorisation humaine est nécessaire pour réévaluer la classification.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNeedReassessingAuthorization() {
        return needReassessingAuthorization;
    }

    /**
     * Définit la valeur de la propriété needReassessingAuthorization.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     * @see #isNeedReassessingAuthorization()
     */
    public void setNeedReassessingAuthorization(Boolean value) {
        this.needReassessingAuthorization = value;
    }

}
