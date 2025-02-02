//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Java class for BirthOrDeathPlaceType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="BirthOrDeathPlaceType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <group ref="{fr:gouv:culture:archivesdefrance:seda:v2.3}LocationGroup"/>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "BirthOrDeathPlaceType",
    propOrder = {"geogname", "address", "postalCode", "city", "region", "country"})
public class BirthOrDeathPlaceType {

  /** Références : ead.geogname */
  @XmlElement(name = "Geogname")
  protected String geogname;

  /** Références : ead.address */
  @XmlElement(name = "Address")
  protected String address;

  /**
   * En plus des balises Tag et Keyword, il est possible d'indexer les objets avec des éléments
   * pré-définis : Code postal.
   */
  @XmlElement(name = "PostalCode")
  protected String postalCode;

  /**
   * En plus des balises Tag et Keyword, il est possible d'indexer les objets avec des éléments
   * pré-définis : Ville.
   */
  @XmlElement(name = "City")
  protected String city;

  /**
   * En plus des balises Tag et Keyword, il est possible d'indexer les objets avec des éléments
   * pré-définis : Région.
   */
  @XmlElement(name = "Region")
  protected String region;

  /**
   * En plus des balises Tag et Keyword, il est possible d'indexer les objets avec des éléments
   * pré-définis : Pays.
   */
  @XmlElement(name = "Country")
  protected String country;

  /**
   * Références : ead.geogname
   *
   * @return possible object is {@link String }
   */
  public String getGeogname() {
    return geogname;
  }

  /**
   * Sets the value of the geogname property.
   *
   * @param value allowed object is {@link String }
   * @see #getGeogname()
   */
  public void setGeogname(String value) {
    this.geogname = value;
  }

  /**
   * Références : ead.address
   *
   * @return possible object is {@link String }
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the value of the address property.
   *
   * @param value allowed object is {@link String }
   * @see #getAddress()
   */
  public void setAddress(String value) {
    this.address = value;
  }

  /**
   * En plus des balises Tag et Keyword, il est possible d'indexer les objets avec des éléments
   * pré-définis : Code postal.
   *
   * @return possible object is {@link String }
   */
  public String getPostalCode() {
    return postalCode;
  }

  /**
   * Sets the value of the postalCode property.
   *
   * @param value allowed object is {@link String }
   * @see #getPostalCode()
   */
  public void setPostalCode(String value) {
    this.postalCode = value;
  }

  /**
   * En plus des balises Tag et Keyword, il est possible d'indexer les objets avec des éléments
   * pré-définis : Ville.
   *
   * @return possible object is {@link String }
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets the value of the city property.
   *
   * @param value allowed object is {@link String }
   * @see #getCity()
   */
  public void setCity(String value) {
    this.city = value;
  }

  /**
   * En plus des balises Tag et Keyword, il est possible d'indexer les objets avec des éléments
   * pré-définis : Région.
   *
   * @return possible object is {@link String }
   */
  public String getRegion() {
    return region;
  }

  /**
   * Sets the value of the region property.
   *
   * @param value allowed object is {@link String }
   * @see #getRegion()
   */
  public void setRegion(String value) {
    this.region = value;
  }

  /**
   * En plus des balises Tag et Keyword, il est possible d'indexer les objets avec des éléments
   * pré-définis : Pays.
   *
   * @return possible object is {@link String }
   */
  public String getCountry() {
    return country;
  }

  /**
   * Sets the value of the country property.
   *
   * @param value allowed object is {@link String }
   * @see #getCountry()
   */
  public void setCountry(String value) {
    this.country = value;
  }
}
