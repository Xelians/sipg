//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v22;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Java class for CustodialHistoryType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="CustodialHistoryType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="CustodialHistoryItem" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}CustodialHistoryItemType" maxOccurs="unbounded"/>
 *         <element name="CustodialHistoryFile" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}DataObjectRefType" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "CustodialHistoryType",
    propOrder = {"custodialHistoryItem", "custodialHistoryFile"})
public class CustodialHistoryType {

  /** Références : seda.CustodialHistoryItem */
  @XmlElement(name = "CustodialHistoryItem", required = true)
  protected List<CustodialHistoryItemType> custodialHistoryItem;

  /** Référence à un fichier de journalisation externe. */
  @XmlElement(name = "CustodialHistoryFile")
  protected DataObjectRefType custodialHistoryFile;

  /**
   * Références : seda.CustodialHistoryItem
   *
   * <p>Gets the value of the custodialHistoryItem property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the custodialHistoryItem property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   * getCustodialHistoryItem().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link CustodialHistoryItemType }
   *
   * @return The value of the custodialHistoryItem property.
   */
  public List<CustodialHistoryItemType> getCustodialHistoryItem() {
    if (custodialHistoryItem == null) {
      custodialHistoryItem = new ArrayList<>();
    }
    return this.custodialHistoryItem;
  }

  /**
   * Référence à un fichier de journalisation externe.
   *
   * @return possible object is {@link DataObjectRefType }
   */
  public DataObjectRefType getCustodialHistoryFile() {
    return custodialHistoryFile;
  }

  /**
   * Sets the value of the custodialHistoryFile property.
   *
   * @param value allowed object is {@link DataObjectRefType }
   * @see #getCustodialHistoryFile()
   */
  public void setCustodialHistoryFile(DataObjectRefType value) {
    this.custodialHistoryFile = value;
  }
}
