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
 * <p>Classe Java pour DescriptiveMetadataType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="DescriptiveMetadataType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="ArchiveUnit" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}ArchiveUnitType" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DescriptiveMetadataType", propOrder = {
    "archiveUnit"
})
public class DescriptiveMetadataType {

    /**
     * Correspond à la notion de composant en ISAD(G). ArchiveUnit
     *                         permet à la fois de gérer la hiérarchie intellectuelle, tout en contenant
     *                         les métadonnées de description et de gestion propres à chaque niveau de
     *                         description archivistique.
     * 
     */
    @XmlElement(name = "ArchiveUnit")
    protected List<ArchiveUnitType> archiveUnit;

    /**
     * Correspond à la notion de composant en ISAD(G). ArchiveUnit
     *                         permet à la fois de gérer la hiérarchie intellectuelle, tout en contenant
     *                         les métadonnées de description et de gestion propres à chaque niveau de
     *                         description archivistique.
     * 
     * Gets the value of the archiveUnit property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the archiveUnit property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getArchiveUnit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArchiveUnitType }
     * </p>
     * 
     * 
     * @return
     *     The value of the archiveUnit property.
     */
    public List<ArchiveUnitType> getArchiveUnit() {
        if (archiveUnit == null) {
            archiveUnit = new ArrayList<>();
        }
        return this.archiveUnit;
    }

}
