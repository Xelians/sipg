//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:05:56 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Informations décrivant un identifiant pérenne.
 * 
 * <p>Classe Java pour PersistentIdType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="PersistentIdType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="PersistentIdentifierType" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType"/>
 *         <element name="PersistentIdentifierOrigin" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType" minOccurs="0"/>
 *         <element name="PersistentIdentifierReference" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType" minOccurs="0"/>
 *         <element name="PersistentIdentifierContent" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersistentIdType", propOrder = {
    "persistentIdentifierType",
    "persistentIdentifierOrigin",
    "persistentIdentifierReference",
    "persistentIdentifierContent"
})
public class PersistentIdType {

    /**
     * Type d'identifiant pérenne.
     * 
     */
    @XmlElement(name = "PersistentIdentifierType", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String persistentIdentifierType;
    /**
     * origine d'identifiant pérenne.
     * 
     */
    @XmlElement(name = "PersistentIdentifierOrigin")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String persistentIdentifierOrigin;
    /**
     * Référence d'identifiant pérenne.
     * 
     */
    @XmlElement(name = "PersistentIdentifierReference")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String persistentIdentifierReference;
    /**
     * valeur de l'identifiant pérenne.
     * 
     */
    @XmlElement(name = "PersistentIdentifierContent", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String persistentIdentifierContent;

    /**
     * Type d'identifiant pérenne.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersistentIdentifierType() {
        return persistentIdentifierType;
    }

    /**
     * Définit la valeur de la propriété persistentIdentifierType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getPersistentIdentifierType()
     */
    public void setPersistentIdentifierType(String value) {
        this.persistentIdentifierType = value;
    }

    /**
     * origine d'identifiant pérenne.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersistentIdentifierOrigin() {
        return persistentIdentifierOrigin;
    }

    /**
     * Définit la valeur de la propriété persistentIdentifierOrigin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getPersistentIdentifierOrigin()
     */
    public void setPersistentIdentifierOrigin(String value) {
        this.persistentIdentifierOrigin = value;
    }

    /**
     * Référence d'identifiant pérenne.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersistentIdentifierReference() {
        return persistentIdentifierReference;
    }

    /**
     * Définit la valeur de la propriété persistentIdentifierReference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getPersistentIdentifierReference()
     */
    public void setPersistentIdentifierReference(String value) {
        this.persistentIdentifierReference = value;
    }

    /**
     * valeur de l'identifiant pérenne.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersistentIdentifierContent() {
        return persistentIdentifierContent;
    }

    /**
     * Définit la valeur de la propriété persistentIdentifierContent.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getPersistentIdentifierContent()
     */
    public void setPersistentIdentifierContent(String value) {
        this.persistentIdentifierContent = value;
    }

}
