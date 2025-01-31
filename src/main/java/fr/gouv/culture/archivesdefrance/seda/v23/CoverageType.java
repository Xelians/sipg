//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package fr.gouv.culture.archivesdefrance.seda.v23;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CoverageType complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="CoverageType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="Spatial" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}TextType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="Temporal" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}TextType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="Juridictional" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}TextType" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoverageType", propOrder = {
    "spatial",
    "temporal",
    "juridictional"
})
public class CoverageType {

    /**
     * Références: AGKRMS.spatialCoverage
     * 
     */
    @XmlElement(name = "Spatial")
    protected List<TextType> spatial;
    /**
     * Références: AGKRMS.temporalCoverage
     * 
     */
    @XmlElement(name = "Temporal")
    protected List<TextType> temporal;
    /**
     * Références: AGKRMS.juridictionalCoverage
     * 
     */
    @XmlElement(name = "Juridictional")
    protected List<TextType> juridictional;

    /**
     * Références: AGKRMS.spatialCoverage
     * 
     * Gets the value of the spatial property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spatial property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getSpatial().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextType }
     * </p>
     * 
     * 
     * @return
     *     The value of the spatial property.
     */
    public List<TextType> getSpatial() {
        if (spatial == null) {
            spatial = new ArrayList<>();
        }
        return this.spatial;
    }

    /**
     * Références: AGKRMS.temporalCoverage
     * 
     * Gets the value of the temporal property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the temporal property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getTemporal().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextType }
     * </p>
     * 
     * 
     * @return
     *     The value of the temporal property.
     */
    public List<TextType> getTemporal() {
        if (temporal == null) {
            temporal = new ArrayList<>();
        }
        return this.temporal;
    }

    /**
     * Références: AGKRMS.juridictionalCoverage
     * 
     * Gets the value of the juridictional property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the juridictional property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getJuridictional().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextType }
     * </p>
     * 
     * 
     * @return
     *     The value of the juridictional property.
     */
    public List<TextType> getJuridictional() {
        if (juridictional == null) {
            juridictional = new ArrayList<>();
        }
        return this.juridictional;
    }

}
