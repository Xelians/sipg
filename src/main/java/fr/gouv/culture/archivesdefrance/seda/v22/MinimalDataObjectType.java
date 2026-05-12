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
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java pour MinimalDataObjectType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MinimalDataObjectType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="DataObjectProfile" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}IdentifierType" minOccurs="0"/>
 *         <element name="DataObjectSystemId" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}NonEmptyTokenType" minOccurs="0"/>
 *         <element name="DataObjectGroupSystemId" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}NonEmptyTokenType" minOccurs="0"/>
 *         <element name="Relationship" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}RelationshipType" maxOccurs="unbounded" minOccurs="0"/>
 *         <group ref="{fr:gouv:culture:archivesdefrance:seda:v2.2}DataObjectVersionGroup" minOccurs="0"/>
 *         <element name="DataObjectVersion" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}VersionIdType" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="id" use="required" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}DataObjectIdType" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MinimalDataObjectType", propOrder = {
    "dataObjectProfile",
    "dataObjectSystemId",
    "dataObjectGroupSystemId",
    "relationship",
    "dataObjectGroupReferenceId",
    "dataObjectGroupId",
    "dataObjectVersion"
})
@XmlSeeAlso({
    BinaryDataObjectType.class,
    PhysicalDataObjectType.class
})
public abstract class MinimalDataObjectType {

    /**
     * Référence à une partie d'un profil d’archivage applicable à un objet technique en particulier.
     * 
     */
    @XmlElement(name = "DataObjectProfile")
    protected IdentifierType dataObjectProfile;
    /**
     * Identifiant attribué aux objets de données. Il est attribué par le SAE et correspond à un identifiant interne.
     * 
     */
    @XmlElement(name = "DataObjectSystemId")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String dataObjectSystemId;
    /**
     * Identifiant attribué aux groupes d'objets de données. Il est attribué par le SAE et correspond à un identifiant interne.
     * 
     */
    @XmlElement(name = "DataObjectGroupSystemId")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String dataObjectGroupSystemId;
    /**
     * Permet de spécifier un lien technique entre un objet-données et une signature.
     * 
     */
    @XmlElement(name = "Relationship")
    protected List<RelationshipType> relationship;
    /**
     * Référence à un Identifiant du groupe d'objets-données DataObjectVersionGroup.
     * 
     */
    @XmlElement(name = "DataObjectGroupReferenceId")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object dataObjectGroupReferenceId;
    /**
     * Identifiant du groupe d'objets-données DataObjectVersionGroup (première et unique définition).
     * 
     */
    @XmlElement(name = "DataObjectGroupId")
    protected String dataObjectGroupId;
    /**
     * Version d’un objet-données (par exemple : original papier, conservation, diffusion, vignette, txt, …).
     * 
     */
    @XmlElement(name = "DataObjectVersion")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String dataObjectVersion;
    /**
     * Identifiant de l'objet-données associé.
     * 
     */
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;

    /**
     * Référence à une partie d'un profil d’archivage applicable à un objet technique en particulier.
     * 
     * @return
     *     possible object is
     *     {@link IdentifierType }
     *     
     */
    public IdentifierType getDataObjectProfile() {
        return dataObjectProfile;
    }

    /**
     * Définit la valeur de la propriété dataObjectProfile.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifierType }
     *     
     * @see #getDataObjectProfile()
     */
    public void setDataObjectProfile(IdentifierType value) {
        this.dataObjectProfile = value;
    }

    /**
     * Identifiant attribué aux objets de données. Il est attribué par le SAE et correspond à un identifiant interne.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataObjectSystemId() {
        return dataObjectSystemId;
    }

    /**
     * Définit la valeur de la propriété dataObjectSystemId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getDataObjectSystemId()
     */
    public void setDataObjectSystemId(String value) {
        this.dataObjectSystemId = value;
    }

    /**
     * Identifiant attribué aux groupes d'objets de données. Il est attribué par le SAE et correspond à un identifiant interne.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataObjectGroupSystemId() {
        return dataObjectGroupSystemId;
    }

    /**
     * Définit la valeur de la propriété dataObjectGroupSystemId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getDataObjectGroupSystemId()
     */
    public void setDataObjectGroupSystemId(String value) {
        this.dataObjectGroupSystemId = value;
    }

    /**
     * Permet de spécifier un lien technique entre un objet-données et une signature.
     * 
     * Gets the value of the relationship property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the relationship property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getRelationship().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RelationshipType }
     * </p>
     * 
     * 
     * @return
     *     The value of the relationship property.
     */
    public List<RelationshipType> getRelationship() {
        if (relationship == null) {
            relationship = new ArrayList<>();
        }
        return this.relationship;
    }

    /**
     * Référence à un Identifiant du groupe d'objets-données DataObjectVersionGroup.
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
     * Identifiant du groupe d'objets-données DataObjectVersionGroup (première et unique définition).
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataObjectGroupId() {
        return dataObjectGroupId;
    }

    /**
     * Définit la valeur de la propriété dataObjectGroupId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getDataObjectGroupId()
     */
    public void setDataObjectGroupId(String value) {
        this.dataObjectGroupId = value;
    }

    /**
     * Version d’un objet-données (par exemple : original papier, conservation, diffusion, vignette, txt, …).
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataObjectVersion() {
        return dataObjectVersion;
    }

    /**
     * Définit la valeur de la propriété dataObjectVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getDataObjectVersion()
     */
    public void setDataObjectVersion(String value) {
        this.dataObjectVersion = value;
    }

    /**
     * Identifiant de l'objet-données associé.
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
