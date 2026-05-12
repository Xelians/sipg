//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:09:37 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v22;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * <p>Classe Java pour ManagementType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ManagementType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <group ref="{fr:gouv:culture:archivesdefrance:seda:v2.2}ManagementGroup"/>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ManagementType", propOrder = {
    "storageRule",
    "appraisalRule",
    "accessRule",
    "disseminationRule",
    "reuseRule",
    "classificationRule",
    "logBook",
    "needAuthorization",
    "holdRule",
    "updateOperation",
    "any"
})
public class ManagementType {

    /**
     * Gestion de la durée d’utilité courante.
     * 
     */
    @XmlElement(name = "StorageRule")
    protected StorageRuleType storageRule;
    /**
     * Gestion de la durée d’utilité administrative.
     * 
     */
    @XmlElement(name = "AppraisalRule")
    protected AppraisalRuleType appraisalRule;
    /**
     * Gestion de la communicabilité.
     * 
     */
    @XmlElement(name = "AccessRule")
    protected AccessRuleType accessRule;
    /**
     * Gestion de la diffusion.
     * 
     */
    @XmlElement(name = "DisseminationRule")
    protected DisseminationRuleType disseminationRule;
    /**
     * Gestion de la réutilisation.
     * 
     */
    @XmlElement(name = "ReuseRule")
    protected ReuseRuleType reuseRule;
    /**
     * Gestion de la classification.
     * 
     */
    @XmlElement(name = "ClassificationRule")
    protected ClassificationRuleType classificationRule;
    /**
     * Gestion des traces.
     * 
     */
    @XmlElement(name = "LogBook")
    protected LogBookType logBook;
    /**
     * Indique si une autorisation humaine est nécessaire pour vérifier ou valider les opérations de gestion des ArchiveUnit.
     * 
     */
    @XmlElement(name = "NeedAuthorization")
    protected Boolean needAuthorization;
    /**
     * Gestion de la durée de gel des ArchiveUnits.
     * 
     */
    @XmlElement(name = "HoldRule")
    protected HoldRuleType holdRule;
    /**
     * Gestion des opérations sur un ArchiveUnit.
     * 
     */
    @XmlElement(name = "UpdateOperation")
    protected UpdateOperationType updateOperation;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gestion de la durée d’utilité courante.
     * 
     * @return
     *     possible object is
     *     {@link StorageRuleType }
     *     
     */
    public StorageRuleType getStorageRule() {
        return storageRule;
    }

    /**
     * Définit la valeur de la propriété storageRule.
     * 
     * @param value
     *     allowed object is
     *     {@link StorageRuleType }
     *     
     * @see #getStorageRule()
     */
    public void setStorageRule(StorageRuleType value) {
        this.storageRule = value;
    }

    /**
     * Gestion de la durée d’utilité administrative.
     * 
     * @return
     *     possible object is
     *     {@link AppraisalRuleType }
     *     
     */
    public AppraisalRuleType getAppraisalRule() {
        return appraisalRule;
    }

    /**
     * Définit la valeur de la propriété appraisalRule.
     * 
     * @param value
     *     allowed object is
     *     {@link AppraisalRuleType }
     *     
     * @see #getAppraisalRule()
     */
    public void setAppraisalRule(AppraisalRuleType value) {
        this.appraisalRule = value;
    }

    /**
     * Gestion de la communicabilité.
     * 
     * @return
     *     possible object is
     *     {@link AccessRuleType }
     *     
     */
    public AccessRuleType getAccessRule() {
        return accessRule;
    }

    /**
     * Définit la valeur de la propriété accessRule.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessRuleType }
     *     
     * @see #getAccessRule()
     */
    public void setAccessRule(AccessRuleType value) {
        this.accessRule = value;
    }

    /**
     * Gestion de la diffusion.
     * 
     * @return
     *     possible object is
     *     {@link DisseminationRuleType }
     *     
     */
    public DisseminationRuleType getDisseminationRule() {
        return disseminationRule;
    }

    /**
     * Définit la valeur de la propriété disseminationRule.
     * 
     * @param value
     *     allowed object is
     *     {@link DisseminationRuleType }
     *     
     * @see #getDisseminationRule()
     */
    public void setDisseminationRule(DisseminationRuleType value) {
        this.disseminationRule = value;
    }

    /**
     * Gestion de la réutilisation.
     * 
     * @return
     *     possible object is
     *     {@link ReuseRuleType }
     *     
     */
    public ReuseRuleType getReuseRule() {
        return reuseRule;
    }

    /**
     * Définit la valeur de la propriété reuseRule.
     * 
     * @param value
     *     allowed object is
     *     {@link ReuseRuleType }
     *     
     * @see #getReuseRule()
     */
    public void setReuseRule(ReuseRuleType value) {
        this.reuseRule = value;
    }

    /**
     * Gestion de la classification.
     * 
     * @return
     *     possible object is
     *     {@link ClassificationRuleType }
     *     
     */
    public ClassificationRuleType getClassificationRule() {
        return classificationRule;
    }

    /**
     * Définit la valeur de la propriété classificationRule.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassificationRuleType }
     *     
     * @see #getClassificationRule()
     */
    public void setClassificationRule(ClassificationRuleType value) {
        this.classificationRule = value;
    }

    /**
     * Gestion des traces.
     * 
     * @return
     *     possible object is
     *     {@link LogBookType }
     *     
     */
    public LogBookType getLogBook() {
        return logBook;
    }

    /**
     * Définit la valeur de la propriété logBook.
     * 
     * @param value
     *     allowed object is
     *     {@link LogBookType }
     *     
     * @see #getLogBook()
     */
    public void setLogBook(LogBookType value) {
        this.logBook = value;
    }

    /**
     * Indique si une autorisation humaine est nécessaire pour vérifier ou valider les opérations de gestion des ArchiveUnit.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNeedAuthorization() {
        return needAuthorization;
    }

    /**
     * Définit la valeur de la propriété needAuthorization.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     * @see #isNeedAuthorization()
     */
    public void setNeedAuthorization(Boolean value) {
        this.needAuthorization = value;
    }

    /**
     * Gestion de la durée de gel des ArchiveUnits.
     * 
     * @return
     *     possible object is
     *     {@link HoldRuleType }
     *     
     */
    public HoldRuleType getHoldRule() {
        return holdRule;
    }

    /**
     * Définit la valeur de la propriété holdRule.
     * 
     * @param value
     *     allowed object is
     *     {@link HoldRuleType }
     *     
     * @see #getHoldRule()
     */
    public void setHoldRule(HoldRuleType value) {
        this.holdRule = value;
    }

    /**
     * Gestion des opérations sur un ArchiveUnit.
     * 
     * @return
     *     possible object is
     *     {@link UpdateOperationType }
     *     
     */
    public UpdateOperationType getUpdateOperation() {
        return updateOperation;
    }

    /**
     * Définit la valeur de la propriété updateOperation.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateOperationType }
     *     
     * @see #getUpdateOperation()
     */
    public void setUpdateOperation(UpdateOperationType value) {
        this.updateOperation = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the any property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * {@link Element }
     * </p>
     * 
     * 
     * @return
     *     The value of the any property.
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<>();
        }
        return this.any;
    }

}
