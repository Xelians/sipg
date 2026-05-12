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
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CustodialHistoryType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="CustodialHistoryType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="CustodialHistoryItem" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}CustodialHistoryItemType" maxOccurs="unbounded"/>
 *         <element name="CustodialHistoryFile" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}DataObjectRefType" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustodialHistoryType", propOrder = {
    "custodialHistoryItem",
    "custodialHistoryFile"
})
public class CustodialHistoryType {

    /**
     * Références : seda.CustodialHistoryItem
     * 
     */
    @XmlElement(name = "CustodialHistoryItem", required = true)
    protected List<CustodialHistoryItemType> custodialHistoryItem;
    /**
     * Référence à un fichier de journalisation externe.
     * 
     */
    @XmlElement(name = "CustodialHistoryFile")
    protected DataObjectRefType custodialHistoryFile;

    /**
     * Références : seda.CustodialHistoryItem
     * 
     * Gets the value of the custodialHistoryItem property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the custodialHistoryItem property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getCustodialHistoryItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustodialHistoryItemType }
     * </p>
     * 
     * 
     * @return
     *     The value of the custodialHistoryItem property.
     */
    public List<CustodialHistoryItemType> getCustodialHistoryItem() {
        if (custodialHistoryItem == null) {
            custodialHistoryItem = new ArrayList<>();
        }
        return this.custodialHistoryItem;
    }

    /**
     * Référence à un fichier de journalisation externe.
     * 
     * @return
     *     possible object is
     *     {@link DataObjectRefType }
     *     
     */
    public DataObjectRefType getCustodialHistoryFile() {
        return custodialHistoryFile;
    }

    /**
     * Définit la valeur de la propriété custodialHistoryFile.
     * 
     * @param value
     *     allowed object is
     *     {@link DataObjectRefType }
     *     
     * @see #getCustodialHistoryFile()
     */
    public void setCustodialHistoryFile(DataObjectRefType value) {
        this.custodialHistoryFile = value;
    }

}
