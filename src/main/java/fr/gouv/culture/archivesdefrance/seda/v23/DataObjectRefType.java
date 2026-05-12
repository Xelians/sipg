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
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Référence à un objet-données ou à un groupe d'objets-données.
 * 
 * <p>Classe Java pour DataObjectRefType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="DataObjectRefType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <choice>
 *           <element name="DataObjectReferenceId" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}DataObjectRefIdType"/>
 *           <element name="DataObjectGroupReferenceId" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}GroupRefIdType"/>
 *         </choice>
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
@XmlType(name = "DataObjectRefType", propOrder = {
    "dataObjectReferenceId",
    "dataObjectGroupReferenceId"
})
@XmlRootElement(name = "DataObjectReference")
public class DataObjectRefType {

    /**
     * Référence à un objet-données listé dans les métadonnées de transport.
     * 
     */
    @XmlElement(name = "DataObjectReferenceId")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object dataObjectReferenceId;
    /**
     * Référence à un groupe d'objets-données listé dans les métadonnées de transport.
     * 
     */
    @XmlElement(name = "DataObjectGroupReferenceId")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object dataObjectGroupReferenceId;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Référence à un objet-données listé dans les métadonnées de transport.
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

    /**
     * Référence à un groupe d'objets-données listé dans les métadonnées de transport.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getDataObjectGroupReferenceId() {
        return dataObjectGroupReferenceId;
    }

    /**
     * Définit la valeur de la propriété dataObjectGroupReferenceId.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     * @see #getDataObjectGroupReferenceId()
     */
    public void setDataObjectGroupReferenceId(Object value) {
        this.dataObjectGroupReferenceId = value;
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
