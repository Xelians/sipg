//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Java class for RelatedObjectReferenceType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="RelatedObjectReferenceType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="IsVersionOf" type="{fr:gouv:culture:archivesdefrance:seda:v2}DataObjectOrArchiveUnitReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="Replaces" type="{fr:gouv:culture:archivesdefrance:seda:v2}DataObjectOrArchiveUnitReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="Requires" type="{fr:gouv:culture:archivesdefrance:seda:v2}DataObjectOrArchiveUnitReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="IsPartOf" type="{fr:gouv:culture:archivesdefrance:seda:v2}DataObjectOrArchiveUnitReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="References" type="{fr:gouv:culture:archivesdefrance:seda:v2}DataObjectOrArchiveUnitReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "RelatedObjectReferenceType",
    propOrder = {"isVersionOf", "replaces", "requires", "isPartOf", "references"})
public class RelatedObjectReferenceType {

  /** Références : DC.Relation.isVersionOf */
  @XmlElement(name = "IsVersionOf")
  protected List<DataObjectOrArchiveUnitReferenceType> isVersionOf;

  /** DC.Relation.replaces */
  @XmlElement(name = "Replaces")
  protected List<DataObjectOrArchiveUnitReferenceType> replaces;

  /** Références : DC.Relation.requires */
  @XmlElement(name = "Requires")
  protected List<DataObjectOrArchiveUnitReferenceType> requires;

  /** Références : DC.Relation.isPartOf */
  @XmlElement(name = "IsPartOf")
  protected List<DataObjectOrArchiveUnitReferenceType> isPartOf;

  /** DC.Relation.references */
  @XmlElement(name = "References")
  protected List<DataObjectOrArchiveUnitReferenceType> references;

  /**
   * Références : DC.Relation.isVersionOf
   *
   * <p>Gets the value of the isVersionOf property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the isVersionOf property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   * getIsVersionOf().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link
   * DataObjectOrArchiveUnitReferenceType }
   *
   * @return The value of the isVersionOf property.
   */
  public List<DataObjectOrArchiveUnitReferenceType> getIsVersionOf() {
    if (isVersionOf == null) {
      isVersionOf = new ArrayList<>();
    }
    return this.isVersionOf;
  }

  /**
   * DC.Relation.replaces
   *
   * <p>Gets the value of the replaces property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the replaces property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   * getReplaces().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link
   * DataObjectOrArchiveUnitReferenceType }
   *
   * @return The value of the replaces property.
   */
  public List<DataObjectOrArchiveUnitReferenceType> getReplaces() {
    if (replaces == null) {
      replaces = new ArrayList<>();
    }
    return this.replaces;
  }

  /**
   * Références : DC.Relation.requires
   *
   * <p>Gets the value of the requires property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the requires property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   * getRequires().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link
   * DataObjectOrArchiveUnitReferenceType }
   *
   * @return The value of the requires property.
   */
  public List<DataObjectOrArchiveUnitReferenceType> getRequires() {
    if (requires == null) {
      requires = new ArrayList<>();
    }
    return this.requires;
  }

  /**
   * Références : DC.Relation.isPartOf
   *
   * <p>Gets the value of the isPartOf property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the isPartOf property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   * getIsPartOf().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link
   * DataObjectOrArchiveUnitReferenceType }
   *
   * @return The value of the isPartOf property.
   */
  public List<DataObjectOrArchiveUnitReferenceType> getIsPartOf() {
    if (isPartOf == null) {
      isPartOf = new ArrayList<>();
    }
    return this.isPartOf;
  }

  /**
   * DC.Relation.references
   *
   * <p>Gets the value of the references property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the references property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   * getReferences().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link
   * DataObjectOrArchiveUnitReferenceType }
   *
   * @return The value of the references property.
   */
  public List<DataObjectOrArchiveUnitReferenceType> getReferences() {
    if (references == null) {
      references = new ArrayList<>();
    }
    return this.references;
  }
}
