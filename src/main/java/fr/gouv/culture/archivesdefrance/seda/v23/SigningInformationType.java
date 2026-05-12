//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:05:56 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v23;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java pour SigningInformationType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="SigningInformationType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="SigningRole" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}SigningRoleType" maxOccurs="unbounded"/>
 *         <element name="DetachedSigningRole" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}DetachedSigningRoleType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="SignedDocumentReferenceId" type="{http://www.w3.org/2001/XMLSchema}IDREF" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="SignatureDescription" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}SignatureDescriptionType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="TimestampingInformation" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}TimestampingInformationType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="AdditionalProof" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}AdditionalProofType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="Extended" type="{fr:gouv:culture:archivesdefrance:seda:v2.3}ExtendedType" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SigningInformationType", propOrder = {
    "signingRole",
    "detachedSigningRole",
    "signedDocumentReferenceId",
    "signatureDescription",
    "timestampingInformation",
    "additionalProof",
    "extended"
})
public class SigningInformationType {

    /**
     * Rôle de l'unité d'archives dans un contexte de signature. Quatre rôles (étiquettes) ont été identifiés : document signé, signature, horodatage et preuves complémentaires.
     * 
     */
    @XmlElement(name = "SigningRole", required = true)
    @XmlSchemaType(name = "token")
    protected List<SigningRoleType> signingRole;
    /**
     * Référence aux rôles des unités d'archives encapsulées sous la racine contenant le document signé.
     * 
     */
    @XmlElement(name = "DetachedSigningRole")
    @XmlSchemaType(name = "token")
    protected List<DetachedSigningRoleType> detachedSigningRole;
    /**
     * Relation technique à l'unité d'archives racine décrivant le document signé à partir des unités d'archives encapsulées contenant les autres rôles d'un contexte de signature.
     * 
     */
    @XmlElement(name = "SignedDocumentReferenceId")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "IDREF")
    protected List<String> signedDocumentReferenceId;
    /**
     * &lt;pre&gt;
     * &amp;lt;?xml version="1.0" encoding="UTF-8"?&amp;gt;&amp;lt;xsd:documentation xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="fr:gouv:culture:archivesdefrance:seda:v2.3" xmlns:pr="info:lc/xmlns/premis-v2"&amp;gt;Bloc permettant de décrire la signature dans un contexte de signature.&amp;lt;/xsd:documentation&amp;gt;
     * &lt;/pre&gt;
     * 
     */
    @XmlElement(name = "SignatureDescription")
    protected List<SignatureDescriptionType> signatureDescription;
    /**
     * Bloc permettant de conserver les informations d'horodatage dans un contexte de signature.
     * 
     */
    @XmlElement(name = "TimestampingInformation")
    protected List<TimestampingInformationType> timestampingInformation;
    /**
     * Bloc permettant de conserver les preuves complémentaires dans un contexte de signature.
     * 
     */
    @XmlElement(name = "AdditionalProof")
    protected List<AdditionalProofType> additionalProof;
    @XmlElement(name = "Extended")
    protected ExtendedType extended;

    /**
     * Rôle de l'unité d'archives dans un contexte de signature. Quatre rôles (étiquettes) ont été identifiés : document signé, signature, horodatage et preuves complémentaires.
     * 
     * Gets the value of the signingRole property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the signingRole property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getSigningRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SigningRoleType }
     * </p>
     * 
     * 
     * @return
     *     The value of the signingRole property.
     */
    public List<SigningRoleType> getSigningRole() {
        if (signingRole == null) {
            signingRole = new ArrayList<>();
        }
        return this.signingRole;
    }

    /**
     * Référence aux rôles des unités d'archives encapsulées sous la racine contenant le document signé.
     * 
     * Gets the value of the detachedSigningRole property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the detachedSigningRole property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDetachedSigningRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetachedSigningRoleType }
     * </p>
     * 
     * 
     * @return
     *     The value of the detachedSigningRole property.
     */
    public List<DetachedSigningRoleType> getDetachedSigningRole() {
        if (detachedSigningRole == null) {
            detachedSigningRole = new ArrayList<>();
        }
        return this.detachedSigningRole;
    }

    /**
     * Relation technique à l'unité d'archives racine décrivant le document signé à partir des unités d'archives encapsulées contenant les autres rôles d'un contexte de signature.
     * 
     * Gets the value of the signedDocumentReferenceId property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the signedDocumentReferenceId property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getSignedDocumentReferenceId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * </p>
     * 
     * 
     * @return
     *     The value of the signedDocumentReferenceId property.
     */
    public List<String> getSignedDocumentReferenceId() {
        if (signedDocumentReferenceId == null) {
            signedDocumentReferenceId = new ArrayList<>();
        }
        return this.signedDocumentReferenceId;
    }

    /**
     * &lt;pre&gt;
     * &amp;lt;?xml version="1.0" encoding="UTF-8"?&amp;gt;&amp;lt;xsd:documentation xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="fr:gouv:culture:archivesdefrance:seda:v2.3" xmlns:pr="info:lc/xmlns/premis-v2"&amp;gt;Bloc permettant de décrire la signature dans un contexte de signature.&amp;lt;/xsd:documentation&amp;gt;
     * &lt;/pre&gt;
     * 
     * Gets the value of the signatureDescription property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the signatureDescription property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getSignatureDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SignatureDescriptionType }
     * </p>
     * 
     * 
     * @return
     *     The value of the signatureDescription property.
     */
    public List<SignatureDescriptionType> getSignatureDescription() {
        if (signatureDescription == null) {
            signatureDescription = new ArrayList<>();
        }
        return this.signatureDescription;
    }

    /**
     * Bloc permettant de conserver les informations d'horodatage dans un contexte de signature.
     * 
     * Gets the value of the timestampingInformation property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the timestampingInformation property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getTimestampingInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimestampingInformationType }
     * </p>
     * 
     * 
     * @return
     *     The value of the timestampingInformation property.
     */
    public List<TimestampingInformationType> getTimestampingInformation() {
        if (timestampingInformation == null) {
            timestampingInformation = new ArrayList<>();
        }
        return this.timestampingInformation;
    }

    /**
     * Bloc permettant de conserver les preuves complémentaires dans un contexte de signature.
     * 
     * Gets the value of the additionalProof property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the additionalProof property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAdditionalProof().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdditionalProofType }
     * </p>
     * 
     * 
     * @return
     *     The value of the additionalProof property.
     */
    public List<AdditionalProofType> getAdditionalProof() {
        if (additionalProof == null) {
            additionalProof = new ArrayList<>();
        }
        return this.additionalProof;
    }

    /**
     * Obtient la valeur de la propriété extended.
     * 
     * @return
     *     possible object is
     *     {@link ExtendedType }
     *     
     */
    public ExtendedType getExtended() {
        return extended;
    }

    /**
     * Définit la valeur de la propriété extended.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtendedType }
     *     
     */
    public void setExtended(ExtendedType value) {
        this.extended = value;
    }

}
