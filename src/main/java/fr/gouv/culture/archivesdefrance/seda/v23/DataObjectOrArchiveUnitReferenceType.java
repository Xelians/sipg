//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:05:56 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java pour DataObjectOrArchiveUnitReferenceType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="DataObjectOrArchiveUnitReferenceType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <choice>
 *         <element name="ArchiveUnitRefId" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}ArchiveUnitRefIdType"/>
 *         <element name="DataObjectReference" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}DataObjectRefType"/>
 *         <element name="RepositoryArchiveUnitPID" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType"/>
 *         <element name="RepositoryObjectPID" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType"/>
 *         <element name="ExternalReference" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType"/>
 *       </choice>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataObjectOrArchiveUnitReferenceType", propOrder = {
    "archiveUnitRefId",
    "dataObjectReference",
    "repositoryArchiveUnitPID",
    "repositoryObjectPID",
    "externalReference"
})
public class DataObjectOrArchiveUnitReferenceType {

    /**
     * Référence à un ArchiveUnit interne.
     * 
     */
    @XmlElement(name = "ArchiveUnitRefId")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object archiveUnitRefId;
    /**
     * Référence à un objet-données ou à un groupe d'objets-données interne(s).
     * 
     */
    @XmlElement(name = "DataObjectReference")
    protected DataObjectRefType dataObjectReference;
    /**
     * Référence à un ArchiveUnit déjà conservé dans un système d'archivage.
     * 
     */
    @XmlElement(name = "RepositoryArchiveUnitPID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String repositoryArchiveUnitPID;
    /**
     * Référence à un un objet-données ou à un groupe d'objets-données déjà conservé(s) dans un système d'archivage.
     * 
     */
    @XmlElement(name = "RepositoryObjectPID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String repositoryObjectPID;
    /**
     * Référence externe.
     * 
     */
    @XmlElement(name = "ExternalReference")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String externalReference;

    /**
     * Référence à un ArchiveUnit interne.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getArchiveUnitRefId() {
        return archiveUnitRefId;
    }

    /**
     * Définit la valeur de la propriété archiveUnitRefId.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     * @see #getArchiveUnitRefId()
     */
    public void setArchiveUnitRefId(Object value) {
        this.archiveUnitRefId = value;
    }

    /**
     * Référence à un objet-données ou à un groupe d'objets-données interne(s).
     * 
     * @return
     *     possible object is
     *     {@link DataObjectRefType }
     *     
     */
    public DataObjectRefType getDataObjectReference() {
        return dataObjectReference;
    }

    /**
     * Définit la valeur de la propriété dataObjectReference.
     * 
     * @param value
     *     allowed object is
     *     {@link DataObjectRefType }
     *     
     * @see #getDataObjectReference()
     */
    public void setDataObjectReference(DataObjectRefType value) {
        this.dataObjectReference = value;
    }

    /**
     * Référence à un ArchiveUnit déjà conservé dans un système d'archivage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepositoryArchiveUnitPID() {
        return repositoryArchiveUnitPID;
    }

    /**
     * Définit la valeur de la propriété repositoryArchiveUnitPID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getRepositoryArchiveUnitPID()
     */
    public void setRepositoryArchiveUnitPID(String value) {
        this.repositoryArchiveUnitPID = value;
    }

    /**
     * Référence à un un objet-données ou à un groupe d'objets-données déjà conservé(s) dans un système d'archivage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepositoryObjectPID() {
        return repositoryObjectPID;
    }

    /**
     * Définit la valeur de la propriété repositoryObjectPID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getRepositoryObjectPID()
     */
    public void setRepositoryObjectPID(String value) {
        this.repositoryObjectPID = value;
    }

    /**
     * Référence externe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalReference() {
        return externalReference;
    }

    /**
     * Définit la valeur de la propriété externalReference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getExternalReference()
     */
    public void setExternalReference(String value) {
        this.externalReference = value;
    }

}
