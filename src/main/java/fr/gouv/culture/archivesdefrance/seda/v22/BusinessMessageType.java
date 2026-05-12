//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:09:37 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v22;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour BusinessMessageType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="BusinessMessageType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2.2}MessageType">
 *       <sequence>
 *         <element name="ArchivalAgreement" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}IdentifierType" minOccurs="0"/>
 *         <element name="CodeListVersions" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}CodeListVersionsType"/>
 *         <element name="DataObjectPackage" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}DataObjectPackageType" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessMessageType", propOrder = {
    "archivalAgreement",
    "codeListVersions",
    "dataObjectPackage"
})
@XmlSeeAlso({
    BusinessRequestMessageType.class,
    BusinessReplyMessageType.class,
    BusinessNotificationMessageType.class
})
public abstract class BusinessMessageType
    extends MessageType
{

    /**
     * Accord de service.
     * 
     */
    @XmlElement(name = "ArchivalAgreement")
    protected IdentifierType archivalAgreement;
    /**
     * Listes de codes de références utilisés dans le
     *                                 message.
     * 
     */
    @XmlElement(name = "CodeListVersions", required = true)
    protected CodeListVersionsType codeListVersions;
    /**
     * Objets-données échangés dans le
     *                                 message.
     * 
     */
    @XmlElement(name = "DataObjectPackage")
    protected DataObjectPackageType dataObjectPackage;

    /**
     * Accord de service.
     * 
     * @return
     *     possible object is
     *     {@link IdentifierType }
     *     
     */
    public IdentifierType getArchivalAgreement() {
        return archivalAgreement;
    }

    /**
     * Définit la valeur de la propriété archivalAgreement.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifierType }
     *     
     * @see #getArchivalAgreement()
     */
    public void setArchivalAgreement(IdentifierType value) {
        this.archivalAgreement = value;
    }

    /**
     * Listes de codes de références utilisés dans le
     *                                 message.
     * 
     * @return
     *     possible object is
     *     {@link CodeListVersionsType }
     *     
     */
    public CodeListVersionsType getCodeListVersions() {
        return codeListVersions;
    }

    /**
     * Définit la valeur de la propriété codeListVersions.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeListVersionsType }
     *     
     * @see #getCodeListVersions()
     */
    public void setCodeListVersions(CodeListVersionsType value) {
        this.codeListVersions = value;
    }

    /**
     * Objets-données échangés dans le
     *                                 message.
     * 
     * @return
     *     possible object is
     *     {@link DataObjectPackageType }
     *     
     */
    public DataObjectPackageType getDataObjectPackage() {
        return dataObjectPackage;
    }

    /**
     * Définit la valeur de la propriété dataObjectPackage.
     * 
     * @param value
     *     allowed object is
     *     {@link DataObjectPackageType }
     *     
     * @see #getDataObjectPackage()
     */
    public void setDataObjectPackage(DataObjectPackageType value) {
        this.dataObjectPackage = value;
    }

}
