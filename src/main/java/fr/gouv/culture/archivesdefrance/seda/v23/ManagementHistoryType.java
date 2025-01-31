//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
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
 * <p>Java class for ManagementHistoryType complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
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
     * Sets the value of the updateDate property.
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
     * Sets the value of the data property.
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
