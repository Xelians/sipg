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
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour EventLogBookOgType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="EventLogBookOgType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2.3}EventType">
 *       <sequence>
 *         <element name="DataObjectReferenceId" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}DataObjectRefIdType" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventLogBookOgType", propOrder = {
    "dataObjectReferenceId"
})
public class EventLogBookOgType
    extends EventType
{

    /**
     * Permet de faire référence à un objet-donnée binaire ou physique déjà présent dans les métadonnées du bordereau.
     * 
     */
    @XmlElement(name = "DataObjectReferenceId")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object dataObjectReferenceId;

    /**
     * Permet de faire référence à un objet-donnée binaire ou physique déjà présent dans les métadonnées du bordereau.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getDataObjectReferenceId() {
        return dataObjectReferenceId;
    }

    /**
     * Définit la valeur de la propriété dataObjectReferenceId.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     * @see #getDataObjectReferenceId()
     */
    public void setDataObjectReferenceId(Object value) {
        this.dataObjectReferenceId = value;
    }

}
