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
import jakarta.xml.bind.annotation.XmlType;


/**
 * Référence à un objet-données ou à un groupe d'objets-données existant.
 * 
 * <p>Classe Java pour ManagementHistoryDataType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ManagementHistoryDataType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="Version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="Management" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}ManagementType" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ManagementHistoryDataType", propOrder = {
    "version",
    "management"
})
public class ManagementHistoryDataType {

    /**
     * Version d'historisation
     * 
     */
    @XmlElement(name = "Version", required = true)
    protected String version;
    /**
     * Métadonnées de gestion historisées (08/2018 : seulement, ClassificationRule).
     * 
     */
    @XmlElement(name = "Management")
    protected ManagementType management;

    /**
     * Version d'historisation
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Définit la valeur de la propriété version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getVersion()
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Métadonnées de gestion historisées (08/2018 : seulement, ClassificationRule).
     * 
     * @return
     *     possible object is
     *     {@link ManagementType }
     *     
     */
    public ManagementType getManagement() {
        return management;
    }

    /**
     * Définit la valeur de la propriété management.
     * 
     * @param value
     *     allowed object is
     *     {@link ManagementType }
     *     
     * @see #getManagement()
     */
    public void setManagement(ManagementType value) {
        this.management = value;
    }

}
