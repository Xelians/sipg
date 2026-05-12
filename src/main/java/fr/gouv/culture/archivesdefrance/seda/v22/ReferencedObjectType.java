//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:09:37 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v22;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Contient la référence à l'objet signé (et son empreinte jusqu'à la fin de la phase de versement dans le SAE).
 * 
 * <p>Classe Java pour ReferencedObjectType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ReferencedObjectType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="SignedObjectId" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}DataObjectRefIdType"/>
 *         <element name="SignedObjectDigest" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}MessageDigestBinaryObjectType"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferencedObjectType", propOrder = {
    "signedObjectId",
    "signedObjectDigest"
})
public class ReferencedObjectType {

    /**
     * Identifiant de l'objet-données signé.
     * 
     */
    @XmlElement(name = "SignedObjectId", required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object signedObjectId;
    /**
     * Empreinte obligatoire jusqu'au processus de versement pour assurer la portabilité de la valeur probante. Le SAE peut ne pas la conserver si l'on considère que l'identifiant de l'objet correspondant suffit. Ce procédé permet de résister au temps lorsque les informations binaires du paquet seront converties au gré des opérations de préservation de la lisibilité des formats. Au cours de ces opérations, l'identifiant ne changera pas, contrairement au format dufichier et donc à son empreinte.
     * 
     */
    @XmlElement(name = "SignedObjectDigest", required = true)
    protected MessageDigestBinaryObjectType signedObjectDigest;

    /**
     * Identifiant de l'objet-données signé.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getSignedObjectId() {
        return signedObjectId;
    }

    /**
     * Définit la valeur de la propriété signedObjectId.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     * @see #getSignedObjectId()
     */
    public void setSignedObjectId(Object value) {
        this.signedObjectId = value;
    }

    /**
     * Empreinte obligatoire jusqu'au processus de versement pour assurer la portabilité de la valeur probante. Le SAE peut ne pas la conserver si l'on considère que l'identifiant de l'objet correspondant suffit. Ce procédé permet de résister au temps lorsque les informations binaires du paquet seront converties au gré des opérations de préservation de la lisibilité des formats. Au cours de ces opérations, l'identifiant ne changera pas, contrairement au format dufichier et donc à son empreinte.
     * 
     * @return
     *     possible object is
     *     {@link MessageDigestBinaryObjectType }
     *     
     */
    public MessageDigestBinaryObjectType getSignedObjectDigest() {
        return signedObjectDigest;
    }

    /**
     * Définit la valeur de la propriété signedObjectDigest.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageDigestBinaryObjectType }
     *     
     * @see #getSignedObjectDigest()
     */
    public void setSignedObjectDigest(MessageDigestBinaryObjectType value) {
        this.signedObjectDigest = value;
    }

}
