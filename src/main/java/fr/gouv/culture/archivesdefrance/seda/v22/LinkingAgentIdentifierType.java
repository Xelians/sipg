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
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java pour LinkingAgentIdentifierType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="LinkingAgentIdentifierType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="LinkingAgentIdentifierType" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}NonEmptyTokenType" minOccurs="0"/>
 *         <element name="LinkingAgentIdentifierValue" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}NonEmptyTokenType" minOccurs="0"/>
 *         <element name="LinkingAgentRole" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}NonEmptyTokenType" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LinkingAgentIdentifierType", propOrder = {
    "linkingAgentIdentifierType",
    "linkingAgentIdentifierValue",
    "linkingAgentRole"
})
public class LinkingAgentIdentifierType {

    /**
     * Identifiant d'un agent répertorié dans des évènements.
     * 
     */
    @XmlElement(name = "LinkingAgentIdentifierType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String linkingAgentIdentifierType;
    /**
     * Mention d'un agent répertorié dans des évènements.
     * 
     */
    @XmlElement(name = "LinkingAgentIdentifierValue")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String linkingAgentIdentifierValue;
    /**
     * Fonction d'un agent répertorié dans des évènements.
     * 
     */
    @XmlElement(name = "LinkingAgentRole")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String linkingAgentRole;

    /**
     * Identifiant d'un agent répertorié dans des évènements.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkingAgentIdentifierType() {
        return linkingAgentIdentifierType;
    }

    /**
     * Définit la valeur de la propriété linkingAgentIdentifierType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getLinkingAgentIdentifierType()
     */
    public void setLinkingAgentIdentifierType(String value) {
        this.linkingAgentIdentifierType = value;
    }

    /**
     * Mention d'un agent répertorié dans des évènements.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkingAgentIdentifierValue() {
        return linkingAgentIdentifierValue;
    }

    /**
     * Définit la valeur de la propriété linkingAgentIdentifierValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getLinkingAgentIdentifierValue()
     */
    public void setLinkingAgentIdentifierValue(String value) {
        this.linkingAgentIdentifierValue = value;
    }

    /**
     * Fonction d'un agent répertorié dans des évènements.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkingAgentRole() {
        return linkingAgentRole;
    }

    /**
     * Définit la valeur de la propriété linkingAgentRole.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getLinkingAgentRole()
     */
    public void setLinkingAgentRole(String value) {
        this.linkingAgentRole = value;
    }

}
