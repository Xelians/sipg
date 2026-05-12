//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:05:56 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ToDeleteType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ToDeleteType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence maxOccurs="unbounded">
 *         <element name="ArchiveUnitRefId" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}ArchiveUnitRefIdType"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ToDeleteType", propOrder = {
    "archiveUnitRefId"
})
public class ToDeleteType {

    /**
     * Permet la suppression de liens entre un
     *                     ArchiveUnit père et une liste définie de ses fils.
     * 
     */
    @XmlElementRef(name = "ArchiveUnitRefId", namespace = "fr:gouv:culture:archivesdefrance:seda:v2.3", type = JAXBElement.class)
    protected List<JAXBElement<Object>> archiveUnitRefId;

    /**
     * Permet la suppression de liens entre un
     *                     ArchiveUnit père et une liste définie de ses fils.
     * 
     * Gets the value of the archiveUnitRefId property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the archiveUnitRefId property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getArchiveUnitRefId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
     * </p>
     * 
     * 
     * @return
     *     The value of the archiveUnitRefId property.
     */
    public List<JAXBElement<Object>> getArchiveUnitRefId() {
        if (archiveUnitRefId == null) {
            archiveUnitRefId = new ArrayList<>();
        }
        return this.archiveUnitRefId;
    }

}
