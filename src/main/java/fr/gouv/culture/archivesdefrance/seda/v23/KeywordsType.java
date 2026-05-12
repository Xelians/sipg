//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:05:56 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Mots-clés.
 * 
 * <p>Classe Java pour KeywordsType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="KeywordsType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="KeywordContent" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}TextType"/>
 *         <element name="KeywordReference" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}IdentifierType" minOccurs="0"/>
 *         <element name="KeywordType" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}KeyType" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KeywordsType", propOrder = {
    "keywordContent",
    "keywordReference",
    "keywordType"
})
public class KeywordsType {

    /**
     * Valeur du mot-clé. A utiliser avec Keyword.
     * 
     */
    @XmlElement(name = "KeywordContent", required = true)
    protected TextType keywordContent;
    /**
     * Identifiant du mot clé dans un référentiel donné. Par exemple, pour un lieu, il pourrait s'agir de son code officiel géographique selon l'INSEE.
     * 
     */
    @XmlElement(name = "KeywordReference")
    protected IdentifierType keywordReference;
    /**
     * Type de mot clé.
     * 
     */
    @XmlElement(name = "KeywordType")
    protected KeyType keywordType;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Valeur du mot-clé. A utiliser avec Keyword.
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getKeywordContent() {
        return keywordContent;
    }

    /**
     * Définit la valeur de la propriété keywordContent.
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     * @see #getKeywordContent()
     */
    public void setKeywordContent(TextType value) {
        this.keywordContent = value;
    }

    /**
     * Identifiant du mot clé dans un référentiel donné. Par exemple, pour un lieu, il pourrait s'agir de son code officiel géographique selon l'INSEE.
     * 
     * @return
     *     possible object is
     *     {@link IdentifierType }
     *     
     */
    public IdentifierType getKeywordReference() {
        return keywordReference;
    }

    /**
     * Définit la valeur de la propriété keywordReference.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifierType }
     *     
     * @see #getKeywordReference()
     */
    public void setKeywordReference(IdentifierType value) {
        this.keywordReference = value;
    }

    /**
     * Type de mot clé.
     * 
     * @return
     *     possible object is
     *     {@link KeyType }
     *     
     */
    public KeyType getKeywordType() {
        return keywordType;
    }

    /**
     * Définit la valeur de la propriété keywordType.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyType }
     *     
     * @see #getKeywordType()
     */
    public void setKeywordType(KeyType value) {
        this.keywordType = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
