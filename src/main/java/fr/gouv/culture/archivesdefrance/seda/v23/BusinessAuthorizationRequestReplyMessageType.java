//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Java class for BusinessAuthorizationRequestReplyMessageType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="BusinessAuthorizationRequestReplyMessageType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2.3}BusinessReplyMessageType">
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessAuthorizationRequestReplyMessageType")
@XmlSeeAlso({
  AuthorizationControlAuthorityRequestReplyType.class,
  AuthorizationOriginatingAgencyRequestReplyType.class
})
public abstract class BusinessAuthorizationRequestReplyMessageType
    extends BusinessReplyMessageType {}
