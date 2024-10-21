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
 * Java class for LogBookType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="LogBookType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="Event" type="{fr:gouv:culture:archivesdefrance:seda:v2}EventType" maxOccurs="unbounded"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "LogBookType",
    propOrder = {"event"})
public class LogBookType {

  @XmlElement(name = "Event", required = true)
  protected List<EventType> event;

  /**
   * Gets the value of the event property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the event property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   * getEvent().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link EventType }
   *
   * @return The value of the event property.
   */
  public List<EventType> getEvent() {
    if (event == null) {
      event = new ArrayList<>();
    }
    return this.event;
  }
}
