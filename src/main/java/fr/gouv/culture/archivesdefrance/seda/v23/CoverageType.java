//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:05:56 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CoverageType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
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
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the spatial property.</p>
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
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the temporal property.</p>
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
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the juridictional property.</p>
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
