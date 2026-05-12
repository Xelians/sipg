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
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java pour DataObjectPackageType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="DataObjectPackageType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <choice maxOccurs="unbounded" minOccurs="0">
 *           <element name="DataObjectGroup" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}DataObjectGroupType"/>
 *           <choice maxOccurs="unbounded" minOccurs="0">
 *             <element name="BinaryDataObject" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}BinaryDataObjectType"/>
 *             <element name="PhysicalDataObject" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}PhysicalDataObjectType"/>
 *           </choice>
 *         </choice>
 *         <element name="DescriptiveMetadata" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}DescriptiveMetadataType"/>
 *         <element name="ManagementMetadata" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}ManagementMetadataType"/>
 *       </sequence>
 *       <attribute ref="{http://www.w3.org/XML/1998/namespace}id"/>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataObjectPackageType", propOrder = {
    "dataObjectGroupOrBinaryDataObjectOrPhysicalDataObject",
    "descriptiveMetadata",
    "managementMetadata"
})
@XmlRootElement(name = "DataObjectPackage")
public class DataObjectPackageType {

    @XmlElements({
        @XmlElement(name = "DataObjectGroup", type = DataObjectGroupType.class),
        @XmlElement(name = "BinaryDataObject", type = BinaryDataObjectType.class),
        @XmlElement(name = "PhysicalDataObject", type = PhysicalDataObjectType.class)
    })
    protected List<Object> dataObjectGroupOrBinaryDataObjectOrPhysicalDataObject;
    /**
     * Bloc de métadonnées descriptives des
     *                         objets-données.
     * 
     */
    @XmlElement(name = "DescriptiveMetadata", required = true)
    protected DescriptiveMetadataType descriptiveMetadata;
    /**
     * Bloc des métadonnées de gestion par défaut des
     *                         objets-données.
     * 
     */
    @XmlElement(name = "ManagementMetadata", required = true)
    protected ManagementMetadataType managementMetadata;
    /**
     * &lt;pre&gt;
     * &amp;lt;?xml version="1.0" encoding="UTF-8"?&amp;gt;&amp;lt;div xmlns="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&amp;gt;&amp;lt;h3&amp;gt;id (as an attribute name)&amp;lt;/h3&amp;gt;&amp;lt;p&amp;gt;
     *        denotes an attribute whose value
     *        should be interpreted as if declared to be of type ID.
     *        This name is reserved by virtue of its definition in the
     *        xml:id specification.&amp;lt;/p&amp;gt;&amp;lt;p&amp;gt;
     *       See &amp;lt;a href="http://www.w3.org/TR/xml-id/"&amp;gt;http://www.w3.org/TR/xml-id/&amp;lt;/a&amp;gt;
     *       for information about this attribute.
     *      &amp;lt;/p&amp;gt;&amp;lt;/div&amp;gt;
     * &lt;/pre&gt;
     * 
     */
    @XmlAttribute(name = "id", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the dataObjectGroupOrBinaryDataObjectOrPhysicalDataObject property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the dataObjectGroupOrBinaryDataObjectOrPhysicalDataObject property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDataObjectGroupOrBinaryDataObjectOrPhysicalDataObject().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BinaryDataObjectType }
     * {@link DataObjectGroupType }
     * {@link PhysicalDataObjectType }
     * </p>
     * 
     * 
     * @return
     *     The value of the dataObjectGroupOrBinaryDataObjectOrPhysicalDataObject property.
     */
    public List<Object> getDataObjectGroupOrBinaryDataObjectOrPhysicalDataObject() {
        if (dataObjectGroupOrBinaryDataObjectOrPhysicalDataObject == null) {
            dataObjectGroupOrBinaryDataObjectOrPhysicalDataObject = new ArrayList<>();
        }
        return this.dataObjectGroupOrBinaryDataObjectOrPhysicalDataObject;
    }

    /**
     * Bloc de métadonnées descriptives des
     *                         objets-données.
     * 
     * @return
     *     possible object is
     *     {@link DescriptiveMetadataType }
     *     
     */
    public DescriptiveMetadataType getDescriptiveMetadata() {
        return descriptiveMetadata;
    }

    /**
     * Définit la valeur de la propriété descriptiveMetadata.
     * 
     * @param value
     *     allowed object is
     *     {@link DescriptiveMetadataType }
     *     
     * @see #getDescriptiveMetadata()
     */
    public void setDescriptiveMetadata(DescriptiveMetadataType value) {
        this.descriptiveMetadata = value;
    }

    /**
     * Bloc des métadonnées de gestion par défaut des
     *                         objets-données.
     * 
     * @return
     *     possible object is
     *     {@link ManagementMetadataType }
     *     
     */
    public ManagementMetadataType getManagementMetadata() {
        return managementMetadata;
    }

    /**
     * Définit la valeur de la propriété managementMetadata.
     * 
     * @param value
     *     allowed object is
     *     {@link ManagementMetadataType }
     *     
     * @see #getManagementMetadata()
     */
    public void setManagementMetadata(ManagementMetadataType value) {
        this.managementMetadata = value;
    }

    /**
     * &lt;pre&gt;
     * &amp;lt;?xml version="1.0" encoding="UTF-8"?&amp;gt;&amp;lt;div xmlns="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&amp;gt;&amp;lt;h3&amp;gt;id (as an attribute name)&amp;lt;/h3&amp;gt;&amp;lt;p&amp;gt;
     *        denotes an attribute whose value
     *        should be interpreted as if declared to be of type ID.
     *        This name is reserved by virtue of its definition in the
     *        xml:id specification.&amp;lt;/p&amp;gt;&amp;lt;p&amp;gt;
     *       See &amp;lt;a href="http://www.w3.org/TR/xml-id/"&amp;gt;http://www.w3.org/TR/xml-id/&amp;lt;/a&amp;gt;
     *       for information about this attribute.
     *      &amp;lt;/p&amp;gt;&amp;lt;/div&amp;gt;
     * &lt;/pre&gt;
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
     * @see #getId()
     */
    public void setId(String value) {
        this.id = value;
    }

}
