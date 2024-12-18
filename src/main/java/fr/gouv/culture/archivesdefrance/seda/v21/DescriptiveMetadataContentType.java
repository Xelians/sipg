//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.10.29 at 01:17:12 AM CET
//
package fr.gouv.culture.archivesdefrance.seda.v21;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;

/**
 * Permet de définir les métadonnées de description. Peut être étendu.
 *
 * <p>Java class for DescriptiveMetadataContentType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DescriptiveMetadataContentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{fr:gouv:culture:archivesdefrance:seda:v2.1}ObjectGroup"/>
 *         &lt;group ref="{fr:gouv:culture:archivesdefrance:seda:v2.1}ManagementHistoryGroup" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "DescriptiveMetadataContentType",
    propOrder = {
      "descriptionLevel",
      "title",
      "filePlanPosition",
      "systemId",
      "originatingSystemId",
      "archivalAgencyArchiveUnitIdentifier",
      "originatingAgencyArchiveUnitIdentifier",
      "transferringAgencyArchiveUnitIdentifier",
      "description",
      "custodialHistory",
      "type",
      "documentType",
      "language",
      "descriptionLanguage",
      "status",
      "version",
      "tag",
      "keyword",
      "coverage",
      "originatingAgency",
      "submissionAgency",
      "agentAbstract",
      "authorizedAgent",
      "writer",
      "addressee",
      "recipient",
      "transmitter",
      "sender",
      "source",
      "relatedObjectReference",
      "createdDate",
      "transactedDate",
      "acquiredDate",
      "sentDate",
      "receivedDate",
      "registeredDate",
      "startDate",
      "endDate",
      "event",
      "signature",
      "gps",
      "any",
      "history"
    })
public class DescriptiveMetadataContentType {

  /** The Description level. */
  @XmlElement(name = "DescriptionLevel")
  @XmlSchemaType(name = "token")
  protected LevelType descriptionLevel;

  /** The Title. */
  @XmlElement(name = "Title")
  protected List<TextType> title;

  /** The File plan position. */
  @XmlElement(name = "FilePlanPosition")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected List<String> filePlanPosition;

  /** The System id. */
  @XmlElement(name = "SystemId")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected List<String> systemId;

  /** The Originating system id. */
  @XmlElement(name = "OriginatingSystemId")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected List<String> originatingSystemId;

  /** The Archival agency archive unit identifier. */
  @XmlElement(name = "ArchivalAgencyArchiveUnitIdentifier")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected List<String> archivalAgencyArchiveUnitIdentifier;

  /** The Originating agency archive unit identifier. */
  @XmlElement(name = "OriginatingAgencyArchiveUnitIdentifier")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected List<String> originatingAgencyArchiveUnitIdentifier;

  /** The Transferring agency archive unit identifier. */
  @XmlElement(name = "TransferringAgencyArchiveUnitIdentifier")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected List<String> transferringAgencyArchiveUnitIdentifier;

  /** The Description. */
  @XmlElement(name = "Description")
  protected List<TextType> description;

  /** The Custodial history. */
  @XmlElement(name = "CustodialHistory")
  protected CustodialHistoryType custodialHistory;

  /** The Type. */
  @XmlElement(name = "Type")
  protected TextType type;

  /** The Document type. */
  @XmlElement(name = "DocumentType")
  protected TextType documentType;

  /** The Language. */
  @XmlElement(name = "Language")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "language")
  protected List<String> language;

  /** The Description language. */
  @XmlElement(name = "DescriptionLanguage")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "language")
  protected String descriptionLanguage;

  /** The Status. */
  @XmlElement(name = "Status")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected String status;

  /** The Version. */
  @XmlElement(name = "Version")
  protected String version;

  /** The Tag. */
  @XmlElement(name = "Tag")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "token")
  protected List<String> tag;

  /** The Keyword. */
  @XmlElement(name = "Keyword")
  protected List<KeywordsType> keyword;

  /** The Coverage. */
  @XmlElement(name = "Coverage")
  protected CoverageType coverage;

  /** The Originating agency. */
  @XmlElement(name = "OriginatingAgency")
  protected OrganizationType originatingAgency;

  /** The Submission agency. */
  @XmlElement(name = "SubmissionAgency")
  protected OrganizationType submissionAgency;

  /** The Agent abstract. */
  @XmlElement(name = "AgentAbstract")
  protected List<AgentType> agentAbstract;

  /** The Authorized agent. */
  @XmlElement(name = "AuthorizedAgent")
  protected List<AgentType> authorizedAgent;

  /** The Writer. */
  @XmlElement(name = "Writer")
  protected List<AgentType> writer;

  /** The Addressee. */
  @XmlElement(name = "Addressee")
  protected List<AgentType> addressee;

  /** The Recipient. */
  @XmlElement(name = "Recipient")
  protected List<AgentType> recipient;

  /** The Transmitter. */
  @XmlElement(name = "Transmitter")
  protected List<AgentType> transmitter;

  /** The Sender. */
  @XmlElement(name = "Sender")
  protected List<AgentType> sender;

  /** The Source. */
  @XmlElement(name = "Source")
  protected String source;

  /** The Related object reference. */
  @XmlElement(name = "RelatedObjectReference")
  protected RelatedObjectReferenceType relatedObjectReference;

  /** The Created date. */
  @XmlElement(name = "CreatedDate")
  protected String createdDate;

  /** The Transacted date. */
  @XmlElement(name = "TransactedDate")
  protected String transactedDate;

  /** The Acquired date. */
  @XmlElement(name = "AcquiredDate")
  protected String acquiredDate;

  /** The Sent date. */
  @XmlElement(name = "SentDate")
  protected String sentDate;

  /** The Received date. */
  @XmlElement(name = "ReceivedDate")
  protected String receivedDate;

  /** The Registered date. */
  @XmlElement(name = "RegisteredDate")
  protected String registeredDate;

  /** The Start date. */
  @XmlElement(name = "StartDate")
  protected String startDate;

  /** The End date. */
  @XmlElement(name = "EndDate")
  protected String endDate;

  /** The Event. */
  @XmlElement(name = "Event")
  protected List<EventType> event;

  /** The Signature. */
  @XmlElement(name = "Signature")
  protected List<SignatureType> signature;

  /** The Gps. */
  @XmlElement(name = "Gps")
  protected GpsType gps;

  /** The Any. */
  @XmlAnyElement(lax = true)
  protected List<Object> any;

  /** The History. */
  @XmlElement(name = "History")
  protected List<ManagementHistoryType> history;

  /**
   * Gets the value of the descriptionLevel property.
   *
   * @return possible object is {@link LevelType }
   */
  public LevelType getDescriptionLevel() {
    return descriptionLevel;
  }

  /**
   * Sets the value of the descriptionLevel property.
   *
   * @param value allowed object is {@link LevelType }
   */
  public void setDescriptionLevel(LevelType value) {
    this.descriptionLevel = value;
  }

  /**
   * Gets the value of the title property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the title property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getTitle().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link TextType }*
   *
   * @return the title
   */
  public List<TextType> getTitle() {
    if (title == null) {
      title = new ArrayList<TextType>();
    }
    return this.title;
  }

  /**
   * Gets the value of the filePlanPosition property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the filePlanPosition property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getFilePlanPosition().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link String }
   *
   * @return the file plan position
   */
  public List<String> getFilePlanPosition() {
    if (filePlanPosition == null) {
      filePlanPosition = new ArrayList<String>();
    }
    return this.filePlanPosition;
  }

  /**
   * Gets the value of the systemId property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the systemId property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getSystemId().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link String }
   *
   * @return the system id
   */
  public List<String> getSystemId() {
    if (systemId == null) {
      systemId = new ArrayList<String>();
    }
    return this.systemId;
  }

  /**
   * Gets the value of the originatingSystemId property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the originatingSystemId property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getOriginatingSystemId().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link String }
   *
   * @return the originating system id
   */
  public List<String> getOriginatingSystemId() {
    if (originatingSystemId == null) {
      originatingSystemId = new ArrayList<String>();
    }
    return this.originatingSystemId;
  }

  /**
   * Gets the value of the archivalAgencyArchiveUnitIdentifier property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the archivalAgencyArchiveUnitIdentifier property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getArchivalAgencyArchiveUnitIdentifier().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link String }
   *
   * @return the archival agency archive unit identifier
   */
  public List<String> getArchivalAgencyArchiveUnitIdentifier() {
    if (archivalAgencyArchiveUnitIdentifier == null) {
      archivalAgencyArchiveUnitIdentifier = new ArrayList<String>();
    }
    return this.archivalAgencyArchiveUnitIdentifier;
  }

  /**
   * Gets the value of the originatingAgencyArchiveUnitIdentifier property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the originatingAgencyArchiveUnitIdentifier property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getOriginatingAgencyArchiveUnitIdentifier().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link String }
   *
   * @return the originating agency archive unit identifier
   */
  public List<String> getOriginatingAgencyArchiveUnitIdentifier() {
    if (originatingAgencyArchiveUnitIdentifier == null) {
      originatingAgencyArchiveUnitIdentifier = new ArrayList<String>();
    }
    return this.originatingAgencyArchiveUnitIdentifier;
  }

  /**
   * Gets the value of the transferringAgencyArchiveUnitIdentifier property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the transferringAgencyArchiveUnitIdentifier
   * property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getTransferringAgencyArchiveUnitIdentifier().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link String }
   *
   * @return the transferring agency archive unit identifier
   */
  public List<String> getTransferringAgencyArchiveUnitIdentifier() {
    if (transferringAgencyArchiveUnitIdentifier == null) {
      transferringAgencyArchiveUnitIdentifier = new ArrayList<String>();
    }
    return this.transferringAgencyArchiveUnitIdentifier;
  }

  /**
   * Gets the value of the description property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the description property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getDescription().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link TextType }*
   *
   * @return the description
   */
  public List<TextType> getDescription() {
    if (description == null) {
      description = new ArrayList<TextType>();
    }
    return this.description;
  }

  /**
   * Gets the value of the custodialHistory property.
   *
   * @return possible object is {@link CustodialHistoryType }
   */
  public CustodialHistoryType getCustodialHistory() {
    return custodialHistory;
  }

  /**
   * Sets the value of the custodialHistory property.
   *
   * @param value allowed object is {@link CustodialHistoryType }
   */
  public void setCustodialHistory(CustodialHistoryType value) {
    this.custodialHistory = value;
  }

  /**
   * Gets the value of the type property.
   *
   * @return possible object is {@link TextType }
   */
  public TextType getType() {
    return type;
  }

  /**
   * Sets the value of the type property.
   *
   * @param value allowed object is {@link TextType }
   */
  public void setType(TextType value) {
    this.type = value;
  }

  /**
   * Gets the value of the documentType property.
   *
   * @return possible object is {@link TextType }
   */
  public TextType getDocumentType() {
    return documentType;
  }

  /**
   * Sets the value of the documentType property.
   *
   * @param value allowed object is {@link TextType }
   */
  public void setDocumentType(TextType value) {
    this.documentType = value;
  }

  /**
   * Gets the value of the language property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the language property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getLanguage().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link String }
   *
   * @return the language
   */
  public List<String> getLanguage() {
    if (language == null) {
      language = new ArrayList<String>();
    }
    return this.language;
  }

  /**
   * Gets the value of the descriptionLanguage property.
   *
   * @return possible object is {@link String }
   */
  public String getDescriptionLanguage() {
    return descriptionLanguage;
  }

  /**
   * Sets the value of the descriptionLanguage property.
   *
   * @param value allowed object is {@link String }
   */
  public void setDescriptionLanguage(String value) {
    this.descriptionLanguage = value;
  }

  /**
   * Gets the value of the status property.
   *
   * @return possible object is {@link String }
   */
  public String getStatus() {
    return status;
  }

  /**
   * Sets the value of the status property.
   *
   * @param value allowed object is {@link String }
   */
  public void setStatus(String value) {
    this.status = value;
  }

  /**
   * Gets the value of the version property.
   *
   * @return possible object is {@link String }
   */
  public String getVersion() {
    return version;
  }

  /**
   * Sets the value of the version property.
   *
   * @param value allowed object is {@link String }
   */
  public void setVersion(String value) {
    this.version = value;
  }

  /**
   * Gets the value of the tag property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the tag property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getTag().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link String }
   *
   * @return the tag
   */
  public List<String> getTag() {
    if (tag == null) {
      tag = new ArrayList<String>();
    }
    return this.tag;
  }

  /**
   * Gets the value of the keyword property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the keyword property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getKeyword().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link KeywordsType }*
   *
   * @return the keyword
   */
  public List<KeywordsType> getKeyword() {
    if (keyword == null) {
      keyword = new ArrayList<KeywordsType>();
    }
    return this.keyword;
  }

  /**
   * Gets the value of the coverage property.
   *
   * @return possible object is {@link CoverageType }
   */
  public CoverageType getCoverage() {
    return coverage;
  }

  /**
   * Sets the value of the coverage property.
   *
   * @param value allowed object is {@link CoverageType }
   */
  public void setCoverage(CoverageType value) {
    this.coverage = value;
  }

  /**
   * Gets the value of the originatingAgency property.
   *
   * @return possible object is {@link OrganizationType }
   */
  public OrganizationType getOriginatingAgency() {
    return originatingAgency;
  }

  /**
   * Sets the value of the originatingAgency property.
   *
   * @param value allowed object is {@link OrganizationType }
   */
  public void setOriginatingAgency(OrganizationType value) {
    this.originatingAgency = value;
  }

  /**
   * Gets the value of the submissionAgency property.
   *
   * @return possible object is {@link OrganizationType }
   */
  public OrganizationType getSubmissionAgency() {
    return submissionAgency;
  }

  /**
   * Sets the value of the submissionAgency property.
   *
   * @param value allowed object is {@link OrganizationType }
   */
  public void setSubmissionAgency(OrganizationType value) {
    this.submissionAgency = value;
  }

  /**
   * Gets the value of the agentAbstract property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the agentAbstract property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getAgentAbstract().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link AgentType }*
   *
   * @return the agent abstract
   */
  public List<AgentType> getAgentAbstract() {
    if (agentAbstract == null) {
      agentAbstract = new ArrayList<AgentType>();
    }
    return this.agentAbstract;
  }

  /**
   * Gets the value of the authorizedAgent property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the authorizedAgent property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getAuthorizedAgent().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link AgentType }*
   *
   * @return the authorized agent
   */
  public List<AgentType> getAuthorizedAgent() {
    if (authorizedAgent == null) {
      authorizedAgent = new ArrayList<AgentType>();
    }
    return this.authorizedAgent;
  }

  /**
   * Gets the value of the writer property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the writer property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getWriter().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link AgentType }*
   *
   * @return the writer
   */
  public List<AgentType> getWriter() {
    if (writer == null) {
      writer = new ArrayList<AgentType>();
    }
    return this.writer;
  }

  /**
   * Gets the value of the addressee property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the addressee property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getAddressee().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link AgentType }*
   *
   * @return the addressee
   */
  public List<AgentType> getAddressee() {
    if (addressee == null) {
      addressee = new ArrayList<AgentType>();
    }
    return this.addressee;
  }

  /**
   * Gets the value of the recipient property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the recipient property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getRecipient().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link AgentType }*
   *
   * @return the recipient
   */
  public List<AgentType> getRecipient() {
    if (recipient == null) {
      recipient = new ArrayList<AgentType>();
    }
    return this.recipient;
  }

  /**
   * Gets the value of the transmitter property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the transmitter property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getTransmitter().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link AgentType }*
   *
   * @return the transmitter
   */
  public List<AgentType> getTransmitter() {
    if (transmitter == null) {
      transmitter = new ArrayList<AgentType>();
    }
    return this.transmitter;
  }

  /**
   * Gets the value of the sender property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the sender property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getSender().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link AgentType }*
   *
   * @return the sender
   */
  public List<AgentType> getSender() {
    if (sender == null) {
      sender = new ArrayList<AgentType>();
    }
    return this.sender;
  }

  /**
   * Gets the value of the source property.
   *
   * @return possible object is {@link String }
   */
  public String getSource() {
    return source;
  }

  /**
   * Sets the value of the source property.
   *
   * @param value allowed object is {@link String }
   */
  public void setSource(String value) {
    this.source = value;
  }

  /**
   * Gets the value of the relatedObjectReference property.
   *
   * @return possible object is {@link RelatedObjectReferenceType }
   */
  public RelatedObjectReferenceType getRelatedObjectReference() {
    return relatedObjectReference;
  }

  /**
   * Sets the value of the relatedObjectReference property.
   *
   * @param value allowed object is {@link RelatedObjectReferenceType }
   */
  public void setRelatedObjectReference(RelatedObjectReferenceType value) {
    this.relatedObjectReference = value;
  }

  /**
   * Gets the value of the createdDate property.
   *
   * @return possible object is {@link String }
   */
  public String getCreatedDate() {
    return createdDate;
  }

  /**
   * Sets the value of the createdDate property.
   *
   * @param value allowed object is {@link String }
   */
  public void setCreatedDate(String value) {
    this.createdDate = value;
  }

  /**
   * Gets the value of the transactedDate property.
   *
   * @return possible object is {@link String }
   */
  public String getTransactedDate() {
    return transactedDate;
  }

  /**
   * Sets the value of the transactedDate property.
   *
   * @param value allowed object is {@link String }
   */
  public void setTransactedDate(String value) {
    this.transactedDate = value;
  }

  /**
   * Gets the value of the acquiredDate property.
   *
   * @return possible object is {@link String }
   */
  public String getAcquiredDate() {
    return acquiredDate;
  }

  /**
   * Sets the value of the acquiredDate property.
   *
   * @param value allowed object is {@link String }
   */
  public void setAcquiredDate(String value) {
    this.acquiredDate = value;
  }

  /**
   * Gets the value of the sentDate property.
   *
   * @return possible object is {@link String }
   */
  public String getSentDate() {
    return sentDate;
  }

  /**
   * Sets the value of the sentDate property.
   *
   * @param value allowed object is {@link String }
   */
  public void setSentDate(String value) {
    this.sentDate = value;
  }

  /**
   * Gets the value of the receivedDate property.
   *
   * @return possible object is {@link String }
   */
  public String getReceivedDate() {
    return receivedDate;
  }

  /**
   * Sets the value of the receivedDate property.
   *
   * @param value allowed object is {@link String }
   */
  public void setReceivedDate(String value) {
    this.receivedDate = value;
  }

  /**
   * Gets the value of the registeredDate property.
   *
   * @return possible object is {@link String }
   */
  public String getRegisteredDate() {
    return registeredDate;
  }

  /**
   * Sets the value of the registeredDate property.
   *
   * @param value allowed object is {@link String }
   */
  public void setRegisteredDate(String value) {
    this.registeredDate = value;
  }

  /**
   * Gets the value of the startDate property.
   *
   * @return possible object is {@link String }
   */
  public String getStartDate() {
    return startDate;
  }

  /**
   * Sets the value of the startDate property.
   *
   * @param value allowed object is {@link String }
   */
  public void setStartDate(String value) {
    this.startDate = value;
  }

  /**
   * Gets the value of the endDate property.
   *
   * @return possible object is {@link String }
   */
  public String getEndDate() {
    return endDate;
  }

  /**
   * Sets the value of the endDate property.
   *
   * @param value allowed object is {@link String }
   */
  public void setEndDate(String value) {
    this.endDate = value;
  }

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
   *    getEvent().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link EventType }*
   *
   * @return the event
   */
  public List<EventType> getEvent() {
    if (event == null) {
      event = new ArrayList<EventType>();
    }
    return this.event;
  }

  /**
   * Gets the value of the signature property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the signature property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getSignature().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link SignatureType }*
   *
   * @return the signature
   */
  public List<SignatureType> getSignature() {
    if (signature == null) {
      signature = new ArrayList<SignatureType>();
    }
    return this.signature;
  }

  /**
   * Gets the value of the gps property.
   *
   * @return possible object is {@link GpsType }
   */
  public GpsType getGps() {
    return gps;
  }

  /**
   * Sets the value of the gps property.
   *
   * @param value allowed object is {@link GpsType }
   */
  public void setGps(GpsType value) {
    this.gps = value;
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
   *    getAny().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list null null null null {@link Element
   * } {@link Object }
   *
   * @return the any
   */
  public List<Object> getAny() {
    if (any == null) {
      any = new ArrayList<Object>();
    }
    return this.any;
  }

  /**
   * Gets the value of the history property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the history property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getHistory().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link ManagementHistoryType }*
   *
   * @return the history
   */
  public List<ManagementHistoryType> getHistory() {
    if (history == null) {
      history = new ArrayList<ManagementHistoryType>();
    }
    return this.history;
  }
}
