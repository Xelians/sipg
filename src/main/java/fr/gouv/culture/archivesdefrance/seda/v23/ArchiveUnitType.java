//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;

/**
 * Unité de base des métadonnées de description contenant la gestion de l'arborescence.
 *
 * <p>Java class for ArchiveUnitType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="ArchiveUnitType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <choice>
 *         <element name="ArchiveUnitRefId" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}ArchiveUnitRefIdType"/>
 *         <sequence>
 *           <element name="ArchiveUnitProfile" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}IdentifierType" minOccurs="0"/>
 *           <element name="Management" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}ManagementType" minOccurs="0"/>
 *           <element name="Content" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}DescriptiveMetadataContentType"/>
 *           <choice maxOccurs="unbounded" minOccurs="0">
 *             <element name="ArchiveUnit" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}ArchiveUnitType"/>
 *             <element name="DataObjectReference" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}DataObjectRefType"/>
 *             <element name="DataObjectGroup" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}ObjectGroupRefType"/>
 *             <any processContents='lax' minOccurs="0"/>
 *           </choice>
 *         </sequence>
 *       </choice>
 *       <attribute name="id" use="required" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}ArchiveUnitIdType" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "ArchiveUnitType",
    propOrder = {
      "archiveUnitRefId",
      "archiveUnitProfile",
      "management",
      "content",
      "archiveUnitOrDataObjectReferenceOrDataObjectGroup"
    })
@XmlRootElement(name = "ArchiveUnit")
public class ArchiveUnitType {

  /** Permet de faire une référence à d'autres ArchiveUnit dans la même transaction. */
  @XmlElement(name = "ArchiveUnitRefId")
  @XmlIDREF
  @XmlSchemaType(name = "IDREF")
  protected Object archiveUnitRefId;

  /**
   * Référence à une partie d'un profil d’archivage applicable à un ArchiveUnit en particulier.
   * Permet par exemple de faire référence à une typologie documentaire dans un profil d'archivage.
   */
  @XmlElement(name = "ArchiveUnitProfile")
  protected IdentifierType archiveUnitProfile;

  /** Métadonnées de gestion applicables à l’ArchiveUnit concernée et à ses héritiers. */
  @XmlElement(name = "Management")
  protected ManagementType management;

  /** Métadonnées de description associées à un ArchiveUnit. */
  @XmlElement(name = "Content")
  protected DescriptiveMetadataContentType content;

  @XmlElementRefs({
    @XmlElementRef(
        name = "ArchiveUnit",
        namespace = "fr:gouv:culture:archivesdefrance:seda:v2.3",
        type = JAXBElement.class,
        required = false),
    @XmlElementRef(
        name = "DataObjectReference",
        namespace = "fr:gouv:culture:archivesdefrance:seda:v2.3",
        type = JAXBElement.class,
        required = false),
    @XmlElementRef(
        name = "DataObjectGroup",
        namespace = "fr:gouv:culture:archivesdefrance:seda:v2.3",
        type = JAXBElement.class,
        required = false)
  })
  @XmlAnyElement(lax = true)
  protected List<Object> archiveUnitOrDataObjectReferenceOrDataObjectGroup;

  /**
   * Identifiant de l'unité d'archives utilisé par exemple dans le cas de multiples héritages, pour
   * savoir quel noeud contient une erreur.
   */
  @XmlAttribute(name = "id", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  protected String id;

  /**
   * Permet de faire une référence à d'autres ArchiveUnit dans la même transaction.
   *
   * @return possible object is {@link Object }
   */
  public Object getArchiveUnitRefId() {
    return archiveUnitRefId;
  }

  /**
   * Sets the value of the archiveUnitRefId property.
   *
   * @param value allowed object is {@link Object }
   * @see #getArchiveUnitRefId()
   */
  public void setArchiveUnitRefId(Object value) {
    this.archiveUnitRefId = value;
  }

  /**
   * Référence à une partie d'un profil d’archivage applicable à un ArchiveUnit en particulier.
   * Permet par exemple de faire référence à une typologie documentaire dans un profil d'archivage.
   *
   * @return possible object is {@link IdentifierType }
   */
  public IdentifierType getArchiveUnitProfile() {
    return archiveUnitProfile;
  }

  /**
   * Sets the value of the archiveUnitProfile property.
   *
   * @param value allowed object is {@link IdentifierType }
   * @see #getArchiveUnitProfile()
   */
  public void setArchiveUnitProfile(IdentifierType value) {
    this.archiveUnitProfile = value;
  }

  /**
   * Métadonnées de gestion applicables à l’ArchiveUnit concernée et à ses héritiers.
   *
   * @return possible object is {@link ManagementType }
   */
  public ManagementType getManagement() {
    return management;
  }

  /**
   * Sets the value of the management property.
   *
   * @param value allowed object is {@link ManagementType }
   * @see #getManagement()
   */
  public void setManagement(ManagementType value) {
    this.management = value;
  }

  /**
   * Métadonnées de description associées à un ArchiveUnit.
   *
   * @return possible object is {@link DescriptiveMetadataContentType }
   */
  public DescriptiveMetadataContentType getContent() {
    return content;
  }

  /**
   * Sets the value of the content property.
   *
   * @param value allowed object is {@link DescriptiveMetadataContentType }
   * @see #getContent()
   */
  public void setContent(DescriptiveMetadataContentType value) {
    this.content = value;
  }

  /**
   * Gets the value of the archiveUnitOrDataObjectReferenceOrDataObjectGroup property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the
   * archiveUnitOrDataObjectReferenceOrDataObjectGroup property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   * getArchiveUnitOrDataObjectReferenceOrDataObjectGroup().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link JAXBElement }{@code <}{@link
   * ArchiveUnitType }{@code >} {@link JAXBElement }{@code <}{@link DataObjectRefType }{@code >}
   * {@link JAXBElement }{@code <}{@link ObjectGroupRefType }{@code >} {@link Object } {@link
   * Element }
   *
   * @return The value of the archiveUnitOrDataObjectReferenceOrDataObjectGroup property.
   */
  public List<Object> getArchiveUnitOrDataObjectReferenceOrDataObjectGroup() {
    if (archiveUnitOrDataObjectReferenceOrDataObjectGroup == null) {
      archiveUnitOrDataObjectReferenceOrDataObjectGroup = new ArrayList<>();
    }
    return this.archiveUnitOrDataObjectReferenceOrDataObjectGroup;
  }

  /**
   * Identifiant de l'unité d'archives utilisé par exemple dans le cas de multiples héritages, pour
   * savoir quel noeud contient une erreur.
   *
   * @return possible object is {@link String }
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the value of the id property.
   *
   * @param value allowed object is {@link String }
   * @see #getId()
   */
  public void setId(String value) {
    this.id = value;
  }
}
