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
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * <p>Classe Java pour PhysicalDataObjectType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="PhysicalDataObjectType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2.2}MinimalDataObjectType">
 *       <sequence>
 *         <element name="PhysicalId" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}IdentifierType" minOccurs="0"/>
 *         <group ref="{fr:gouv:culture:archivesdefrance:seda:v2.2}PhysicalTechnicalDescriptionGroup"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PhysicalDataObjectType", propOrder = {
    "physicalId",
    "physicalDimensions",
    "any"
})
@XmlRootElement(name = "PhysicalDataObject")
public class PhysicalDataObjectType
    extends MinimalDataObjectType
{

    /**
     * Identifiant physique d’un objet-données physique, externe à celui-ci (ex. code-barres).
     * 
     */
    @XmlElement(name = "PhysicalId")
    protected IdentifierType physicalId;
    /**
     * Dimensions d'un objet-données physique.
     * 
     */
    @XmlElement(name = "PhysicalDimensions")
    protected DimensionsType physicalDimensions;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Identifiant physique d’un objet-données physique, externe à celui-ci (ex. code-barres).
     * 
     * @return
     *     possible object is
     *     {@link IdentifierType }
     *     
     */
    public IdentifierType getPhysicalId() {
        return physicalId;
    }

    /**
     * Définit la valeur de la propriété physicalId.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifierType }
     *     
     * @see #getPhysicalId()
     */
    public void setPhysicalId(IdentifierType value) {
        this.physicalId = value;
    }

    /**
     * Dimensions d'un objet-données physique.
     * 
     * @return
     *     possible object is
     *     {@link DimensionsType }
     *     
     */
    public DimensionsType getPhysicalDimensions() {
        return physicalDimensions;
    }

    /**
     * Définit la valeur de la propriété physicalDimensions.
     * 
     * @param value
     *     allowed object is
     *     {@link DimensionsType }
     *     
     * @see #getPhysicalDimensions()
     */
    public void setPhysicalDimensions(DimensionsType value) {
        this.physicalDimensions = value;
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
