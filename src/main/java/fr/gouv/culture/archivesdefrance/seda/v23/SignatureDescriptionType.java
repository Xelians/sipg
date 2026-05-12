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
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java pour SignatureDescriptionType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="SignatureDescriptionType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="Signer" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}SignerType" minOccurs="0"/>
 *         <element name="Validator" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}ValidatorType" minOccurs="0"/>
 *         <element name="SigningType" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}NonEmptyTokenType" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SignatureDescriptionType", propOrder = {
    "signer",
    "validator",
    "signingType"
})
public class SignatureDescriptionType {

    /**
     * Références : premis.signer
     * 
     */
    @XmlElement(name = "Signer")
    protected SignerType signer;
    /**
     * Validateur de la signature.
     * 
     */
    @XmlElement(name = "Validator")
    protected ValidatorType validator;
    /**
     * Type de signature, au sens juridique du terme. Par exemple, simple, avancée, qualifiée.
     * 
     */
    @XmlElement(name = "SigningType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String signingType;

    /**
     * Références : premis.signer
     * 
     * @return
     *     possible object is
     *     {@link SignerType }
     *     
     */
    public SignerType getSigner() {
        return signer;
    }

    /**
     * Définit la valeur de la propriété signer.
     * 
     * @param value
     *     allowed object is
     *     {@link SignerType }
     *     
     * @see #getSigner()
     */
    public void setSigner(SignerType value) {
        this.signer = value;
    }

    /**
     * Validateur de la signature.
     * 
     * @return
     *     possible object is
     *     {@link ValidatorType }
     *     
     */
    public ValidatorType getValidator() {
        return validator;
    }

    /**
     * Définit la valeur de la propriété validator.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidatorType }
     *     
     * @see #getValidator()
     */
    public void setValidator(ValidatorType value) {
        this.validator = value;
    }

    /**
     * Type de signature, au sens juridique du terme. Par exemple, simple, avancée, qualifiée.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSigningType() {
        return signingType;
    }

    /**
     * Définit la valeur de la propriété signingType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getSigningType()
     */
    public void setSigningType(String value) {
        this.signingType = value;
    }

}
