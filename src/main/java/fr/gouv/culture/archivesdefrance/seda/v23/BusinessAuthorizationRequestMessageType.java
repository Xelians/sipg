//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:05:56 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour BusinessAuthorizationRequestMessageType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="BusinessAuthorizationRequestMessageType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2.3}BusinessRequestMessageType">
 *       <sequence>
 *         <element name="AuthorizationRequestContent" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}AuthorizationRequestContentType"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessAuthorizationRequestMessageType", propOrder = {
    "authorizationRequestContent"
})
@XmlSeeAlso({
    AuthorizationControlAuthorityRequestType.class,
    AuthorizationOriginatingAgencyRequestType.class
})
public abstract class BusinessAuthorizationRequestMessageType
    extends BusinessRequestMessageType
{

    /**
     * Demande d’autorisation.
     * 
     */
    @XmlElement(name = "AuthorizationRequestContent", required = true)
    protected AuthorizationRequestContentType authorizationRequestContent;

    /**
     * Demande d’autorisation.
     * 
     * @return
     *     possible object is
     *     {@link AuthorizationRequestContentType }
     *     
     */
    public AuthorizationRequestContentType getAuthorizationRequestContent() {
        return authorizationRequestContent;
    }

    /**
     * Définit la valeur de la propriété authorizationRequestContent.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthorizationRequestContentType }
     *     
     * @see #getAuthorizationRequestContent()
     */
    public void setAuthorizationRequestContent(AuthorizationRequestContentType value) {
        this.authorizationRequestContent = value;
    }

}
