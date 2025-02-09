//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;

/**
 * Java class for EventType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="EventType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="EventIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType" minOccurs="0"/>
 *         <element name="EventTypeCode" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType" minOccurs="0"/>
 *         <element name="EventType" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType" minOccurs="0"/>
 *         <element name="EventDateTime" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}DateType"/>
 *         <element name="EventDetail" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}TextType" minOccurs="0"/>
 *         <element name="Outcome" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType" minOccurs="0"/>
 *         <element name="OutcomeDetail" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType" minOccurs="0"/>
 *         <element name="OutcomeDetailMessage" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType" minOccurs="0"/>
 *         <element name="EventDetailData" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType" minOccurs="0"/>
 *         <element name="LinkingAgentIdentifier" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}LinkingAgentIdentifierType" maxOccurs="unbounded" minOccurs="0"/>
 *         <any processContents='lax' maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "EventType",
    propOrder = {
      "eventIdentifier",
      "eventTypeCode",
      "eventType",
      "eventDateTime",
      "eventDetail",
      "outcome",
      "outcomeDetail",
      "outcomeDetailMessage",
      "eventDetailData",
      "linkingAgentIdentifier",
      "any"
    })
@XmlSeeAlso({EventLogBookOgType.class})
public class EventType {

  /** Références : premis.eventIdentifier */
  @XmlElement(name = "EventIdentifier")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String eventIdentifier;

  /** Code du type d'événement. */
  @XmlElement(name = "EventTypeCode")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String eventTypeCode;

  /** Type d'événement. */
  @XmlElement(name = "EventType")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String eventType;

  /** Date et heure de l'événement. */
  @XmlElement(name = "EventDateTime", required = true)
  protected String eventDateTime;

  /** Détail sur l'événement. */
  @XmlElement(name = "EventDetail")
  protected TextType eventDetail;

  /** Résultat du traitement. */
  @XmlElement(name = "Outcome")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String outcome;

  /** Détail sur le résultat du traitement. */
  @XmlElement(name = "OutcomeDetail")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String outcomeDetail;

  /** Message détaillé sur le résultat du traitement. */
  @XmlElement(name = "OutcomeDetailMessage")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String outcomeDetailMessage;

  /** Message technique détaillant l'erreur. */
  @XmlElement(name = "EventDetailData")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String eventDetailData;

  /** Permet de renseigner des agents répertoriés dans des évènements. */
  @XmlElement(name = "LinkingAgentIdentifier")
  protected List<LinkingAgentIdentifierType> linkingAgentIdentifier;

  @XmlAnyElement(lax = true)
  protected List<Object> any;

  /**
   * Références : premis.eventIdentifier
   *
   * @return possible object is {@link String }
   */
  public String getEventIdentifier() {
    return eventIdentifier;
  }

  /**
   * Sets the value of the eventIdentifier property.
   *
   * @param value allowed object is {@link String }
   * @see #getEventIdentifier()
   */
  public void setEventIdentifier(String value) {
    this.eventIdentifier = value;
  }

  /**
   * Code du type d'événement.
   *
   * @return possible object is {@link String }
   */
  public String getEventTypeCode() {
    return eventTypeCode;
  }

  /**
   * Sets the value of the eventTypeCode property.
   *
   * @param value allowed object is {@link String }
   * @see #getEventTypeCode()
   */
  public void setEventTypeCode(String value) {
    this.eventTypeCode = value;
  }

  /**
   * Type d'événement.
   *
   * @return possible object is {@link String }
   */
  public String getEventType() {
    return eventType;
  }

  /**
   * Sets the value of the eventType property.
   *
   * @param value allowed object is {@link String }
   * @see #getEventType()
   */
  public void setEventType(String value) {
    this.eventType = value;
  }

  /**
   * Date et heure de l'événement.
   *
   * @return possible object is {@link String }
   */
  public String getEventDateTime() {
    return eventDateTime;
  }

  /**
   * Sets the value of the eventDateTime property.
   *
   * @param value allowed object is {@link String }
   * @see #getEventDateTime()
   */
  public void setEventDateTime(String value) {
    this.eventDateTime = value;
  }

  /**
   * Détail sur l'événement.
   *
   * @return possible object is {@link TextType }
   */
  public TextType getEventDetail() {
    return eventDetail;
  }

  /**
   * Sets the value of the eventDetail property.
   *
   * @param value allowed object is {@link TextType }
   * @see #getEventDetail()
   */
  public void setEventDetail(TextType value) {
    this.eventDetail = value;
  }

  /**
   * Résultat du traitement.
   *
   * @return possible object is {@link String }
   */
  public String getOutcome() {
    return outcome;
  }

  /**
   * Sets the value of the outcome property.
   *
   * @param value allowed object is {@link String }
   * @see #getOutcome()
   */
  public void setOutcome(String value) {
    this.outcome = value;
  }

  /**
   * Détail sur le résultat du traitement.
   *
   * @return possible object is {@link String }
   */
  public String getOutcomeDetail() {
    return outcomeDetail;
  }

  /**
   * Sets the value of the outcomeDetail property.
   *
   * @param value allowed object is {@link String }
   * @see #getOutcomeDetail()
   */
  public void setOutcomeDetail(String value) {
    this.outcomeDetail = value;
  }

  /**
   * Message détaillé sur le résultat du traitement.
   *
   * @return possible object is {@link String }
   */
  public String getOutcomeDetailMessage() {
    return outcomeDetailMessage;
  }

  /**
   * Sets the value of the outcomeDetailMessage property.
   *
   * @param value allowed object is {@link String }
   * @see #getOutcomeDetailMessage()
   */
  public void setOutcomeDetailMessage(String value) {
    this.outcomeDetailMessage = value;
  }

  /**
   * Message technique détaillant l'erreur.
   *
   * @return possible object is {@link String }
   */
  public String getEventDetailData() {
    return eventDetailData;
  }

  /**
   * Sets the value of the eventDetailData property.
   *
   * @param value allowed object is {@link String }
   * @see #getEventDetailData()
   */
  public void setEventDetailData(String value) {
    this.eventDetailData = value;
  }

  /**
   * Permet de renseigner des agents répertoriés dans des évènements.
   *
   * <p>Gets the value of the linkingAgentIdentifier property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the linkingAgentIdentifier property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   * getLinkingAgentIdentifier().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link LinkingAgentIdentifierType }
   *
   * @return The value of the linkingAgentIdentifier property.
   */
  public List<LinkingAgentIdentifierType> getLinkingAgentIdentifier() {
    if (linkingAgentIdentifier == null) {
      linkingAgentIdentifier = new ArrayList<>();
    }
    return this.linkingAgentIdentifier;
  }

  /**
   * Gets the value of the any property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the any property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   * getAny().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link Object } {@link Element }
   *
   * @return The value of the any property.
   */
  public List<Object> getAny() {
    if (any == null) {
      any = new ArrayList<>();
    }
    return this.any;
  }
}
