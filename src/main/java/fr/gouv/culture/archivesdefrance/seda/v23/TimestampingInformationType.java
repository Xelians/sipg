//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Java class for TimestampingInformationType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="TimestampingInformationType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="TimeStamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="AdditionalTimestampingInformation" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "TimestampingInformationType",
    propOrder = {"timeStamp", "additionalTimestampingInformation"})
public class TimestampingInformationType {

  /** Horodatage de la signature */
  @XmlElement(name = "TimeStamp")
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar timeStamp;

  /** Informations complémentaires sur l'horodatage d'une signature */
  @XmlElement(name = "AdditionalTimestampingInformation")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String additionalTimestampingInformation;

  /**
   * Horodatage de la signature
   *
   * @return possible object is {@link XMLGregorianCalendar }
   */
  public XMLGregorianCalendar getTimeStamp() {
    return timeStamp;
  }

  /**
   * Sets the value of the timeStamp property.
   *
   * @param value allowed object is {@link XMLGregorianCalendar }
   * @see #getTimeStamp()
   */
  public void setTimeStamp(XMLGregorianCalendar value) {
    this.timeStamp = value;
  }

  /**
   * Informations complémentaires sur l'horodatage d'une signature
   *
   * @return possible object is {@link String }
   */
  public String getAdditionalTimestampingInformation() {
    return additionalTimestampingInformation;
  }

  /**
   * Sets the value of the additionalTimestampingInformation property.
   *
   * @param value allowed object is {@link String }
   * @see #getAdditionalTimestampingInformation()
   */
  public void setAdditionalTimestampingInformation(String value) {
    this.additionalTimestampingInformation = value;
  }
}
