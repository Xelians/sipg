//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:05:56 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Référence à un objet-données ou à un groupe d'objets-données existant.
 * 
 * <p>Classe Java pour ManagementHistoryType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ManagementHistoryType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="UpdateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         <element name="Data" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}ManagementHistoryDataType"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ManagementHistoryType", propOrder = {
    "updateDate",
    "data"
})
public class ManagementHistoryType {

    /**
     * Date d'historisation.
     * 
     */
    @XmlElement(name = "UpdateDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateDate;
    /**
     * Data
     * 
     */
    @XmlElement(name = "Data", required = true)
    protected ManagementHistoryDataType data;

    /**
     * Date d'historisation.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateDate() {
        return updateDate;
    }

    /**
     * Définit la valeur de la propriété updateDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     * @see #getUpdateDate()
     */
    public void setUpdateDate(XMLGregorianCalendar value) {
        this.updateDate = value;
    }

    /**
     * Data
     * 
     * @return
     *     possible object is
     *     {@link ManagementHistoryDataType }
     *     
     */
    public ManagementHistoryDataType getData() {
        return data;
    }

    /**
     * Définit la valeur de la propriété data.
     * 
     * @param value
     *     allowed object is
     *     {@link ManagementHistoryDataType }
     *     
     * @see #getData()
     */
    public void setData(ManagementHistoryDataType value) {
        this.data = value;
    }

}
