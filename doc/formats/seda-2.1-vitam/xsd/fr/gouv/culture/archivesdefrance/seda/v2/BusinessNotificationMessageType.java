//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.01.15 at 09:28:44 PM CET 
//


package fr.gouv.culture.archivesdefrance.seda.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BusinessNotificationMessageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessNotificationMessageType">
 *   &lt;complexContent>
 *     &lt;extension base="{fr:gouv:culture:archivesdefrance:seda:v2.1}BusinessMessageType">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessNotificationMessageType")
@XmlSeeAlso({
    ArchiveDestructionNotificationType.class,
    ArchiveModificationNotificationType.class
})
public abstract class BusinessNotificationMessageType
    extends BusinessMessageType
{


}
