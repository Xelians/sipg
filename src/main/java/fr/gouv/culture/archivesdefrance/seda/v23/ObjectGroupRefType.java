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
 * Référence à un objet-données ou à un groupe d'objets-données existant.
 * 
 * <p>Classe Java pour ObjectGroupRefType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ObjectGroupRefType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="DataObjectReference" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}DataObjectRefType" minOccurs="0"/>
 *         <element name="DataObjectGroupExistingReferenceId" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectGroupRefType", propOrder = {
    "dataObjectReference",
    "dataObjectGroupExistingReferenceId"
})
public class ObjectGroupRefType {

    /**
     * Permet de faire référence à un objet-donnée binaire ou physique déjà existant.
     * 
     */
    @XmlElement(name = "DataObjectReference")
    protected DataObjectRefType dataObjectReference;
    /**
     * Référence à un groupe d'objets-données déjà existants.
     * 
     */
    @XmlElement(name = "DataObjectGroupExistingReferenceId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String dataObjectGroupExistingReferenceId;

    /**
     * Permet de faire référence à un objet-donnée binaire ou physique déjà existant.
     * 
     * @return
     *     possible object is
     *     {@link DataObjectRefType }
     *     
     */
    public DataObjectRefType getDataObjectReference() {
        return dataObjectReference;
    }

    /**
     * Définit la valeur de la propriété dataObjectReference.
     * 
     * @param value
     *     allowed object is
     *     {@link DataObjectRefType }
     *     
     * @see #getDataObjectReference()
     */
    public void setDataObjectReference(DataObjectRefType value) {
        this.dataObjectReference = value;
    }

    /**
     * Référence à un groupe d'objets-données déjà existants.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataObjectGroupExistingReferenceId() {
        return dataObjectGroupExistingReferenceId;
    }

    /**
     * Définit la valeur de la propriété dataObjectGroupExistingReferenceId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getDataObjectGroupExistingReferenceId()
     */
    public void setDataObjectGroupExistingReferenceId(String value) {
        this.dataObjectGroupExistingReferenceId = value;
    }

}
