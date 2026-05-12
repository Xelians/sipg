//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:09:37 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v22;

import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Objet-données numérique.
 * 
 * Métadonnées techniques minimales : URI, Digest, Poids.
 * 
 * <p>Classe Java pour BinaryDataObjectType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="BinaryDataObjectType">
 *   <complexContent>
 *     <extension base="{fr:gouv:culture:archivesdefrance:seda:v2.2}MinimalDataObjectType">
 *       <sequence>
 *         <group ref="{fr:gouv:culture:archivesdefrance:seda:v2.2}MinimalBinaryDataObjectGroup" minOccurs="0"/>
 *         <element name="Size" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}SizeInBytesType" minOccurs="0"/>
 *         <element name="Compressed" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}CompressedType" minOccurs="0"/>
 *         <group ref="{fr:gouv:culture:archivesdefrance:seda:v2.2}BinaryTechnicalDescriptionGroup"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BinaryDataObjectType", propOrder = {
    "attachment",
    "uri",
    "messageDigest",
    "size",
    "compressed",
    "formatIdentification",
    "fileInfo",
    "metadata",
    "otherMetadata"
})
@XmlRootElement(name = "BinaryDataObject")
public class BinaryDataObjectType
    extends MinimalDataObjectType
{

    /**
     * Objet-données (contenu binaire ou fichier joint).
     * 
     */
    @XmlElement(name = "Attachment")
    protected BinaryObjectType attachment;
    /**
     * L'URI spécifie où se trouve l'objet-données numérique. Peut correspondre à un chemin relatif.
     * 
     */
    @XmlElement(name = "Uri")
    @XmlSchemaType(name = "anyURI")
    protected String uri;
    /**
     * Empreinte de l'objet-données.
     * 
     */
    @XmlElement(name = "MessageDigest")
    protected MessageDigestBinaryObjectType messageDigest;
    /**
     * Permet de spécifier la taille de l'objet-données en octet.
     * 
     */
    @XmlElement(name = "Size")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger size;
    /**
     * Indique si l’objet-données est compressé et doit être décompressé.
     * 
     */
    @XmlElement(name = "Compressed")
    protected CompressedType compressed;
    /**
     * Identification du format de l'objet-données.
     * 
     */
    @XmlElement(name = "FormatIdentification")
    protected FormatIdentificationType formatIdentification;
    /**
     * Propriétés techniques génériques du fichier (nom d’origine, logiciel de création, système d’exploitation de création).
     * 
     */
    @XmlElement(name = "FileInfo")
    protected FileInfoType fileInfo;
    /**
     * Propriétés techniques spécifiques du fichier en fonction de sa nature technique (texte, document, image, audio, vidéo, etc.).
     * 
     */
    @XmlElement(name = "Metadata")
    protected CoreMetadataType metadata;
    /**
     * Autres métadonnées techniques si celles définies précédemment ne suffisent pas.
     * 
     */
    @XmlElement(name = "OtherMetadata")
    protected DescriptiveTechnicalMetadataType otherMetadata;

    /**
     * Objet-données (contenu binaire ou fichier joint).
     * 
     * @return
     *     possible object is
     *     {@link BinaryObjectType }
     *     
     */
    public BinaryObjectType getAttachment() {
        return attachment;
    }

    /**
     * Définit la valeur de la propriété attachment.
     * 
     * @param value
     *     allowed object is
     *     {@link BinaryObjectType }
     *     
     * @see #getAttachment()
     */
    public void setAttachment(BinaryObjectType value) {
        this.attachment = value;
    }

    /**
     * L'URI spécifie où se trouve l'objet-données numérique. Peut correspondre à un chemin relatif.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUri() {
        return uri;
    }

    /**
     * Définit la valeur de la propriété uri.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getUri()
     */
    public void setUri(String value) {
        this.uri = value;
    }

    /**
     * Empreinte de l'objet-données.
     * 
     * @return
     *     possible object is
     *     {@link MessageDigestBinaryObjectType }
     *     
     */
    public MessageDigestBinaryObjectType getMessageDigest() {
        return messageDigest;
    }

    /**
     * Définit la valeur de la propriété messageDigest.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageDigestBinaryObjectType }
     *     
     * @see #getMessageDigest()
     */
    public void setMessageDigest(MessageDigestBinaryObjectType value) {
        this.messageDigest = value;
    }

    /**
     * Permet de spécifier la taille de l'objet-données en octet.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSize() {
        return size;
    }

    /**
     * Définit la valeur de la propriété size.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     * @see #getSize()
     */
    public void setSize(BigInteger value) {
        this.size = value;
    }

    /**
     * Indique si l’objet-données est compressé et doit être décompressé.
     * 
     * @return
     *     possible object is
     *     {@link CompressedType }
     *     
     */
    public CompressedType getCompressed() {
        return compressed;
    }

    /**
     * Définit la valeur de la propriété compressed.
     * 
     * @param value
     *     allowed object is
     *     {@link CompressedType }
     *     
     * @see #getCompressed()
     */
    public void setCompressed(CompressedType value) {
        this.compressed = value;
    }

    /**
     * Identification du format de l'objet-données.
     * 
     * @return
     *     possible object is
     *     {@link FormatIdentificationType }
     *     
     */
    public FormatIdentificationType getFormatIdentification() {
        return formatIdentification;
    }

    /**
     * Définit la valeur de la propriété formatIdentification.
     * 
     * @param value
     *     allowed object is
     *     {@link FormatIdentificationType }
     *     
     * @see #getFormatIdentification()
     */
    public void setFormatIdentification(FormatIdentificationType value) {
        this.formatIdentification = value;
    }

    /**
     * Propriétés techniques génériques du fichier (nom d’origine, logiciel de création, système d’exploitation de création).
     * 
     * @return
     *     possible object is
     *     {@link FileInfoType }
     *     
     */
    public FileInfoType getFileInfo() {
        return fileInfo;
    }

    /**
     * Définit la valeur de la propriété fileInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link FileInfoType }
     *     
     * @see #getFileInfo()
     */
    public void setFileInfo(FileInfoType value) {
        this.fileInfo = value;
    }

    /**
     * Propriétés techniques spécifiques du fichier en fonction de sa nature technique (texte, document, image, audio, vidéo, etc.).
     * 
     * @return
     *     possible object is
     *     {@link CoreMetadataType }
     *     
     */
    public CoreMetadataType getMetadata() {
        return metadata;
    }

    /**
     * Définit la valeur de la propriété metadata.
     * 
     * @param value
     *     allowed object is
     *     {@link CoreMetadataType }
     *     
     * @see #getMetadata()
     */
    public void setMetadata(CoreMetadataType value) {
        this.metadata = value;
    }

    /**
     * Autres métadonnées techniques si celles définies précédemment ne suffisent pas.
     * 
     * @return
     *     possible object is
     *     {@link DescriptiveTechnicalMetadataType }
     *     
     */
    public DescriptiveTechnicalMetadataType getOtherMetadata() {
        return otherMetadata;
    }

    /**
     * Définit la valeur de la propriété otherMetadata.
     * 
     * @param value
     *     allowed object is
     *     {@link DescriptiveTechnicalMetadataType }
     *     
     * @see #getOtherMetadata()
     */
    public void setOtherMetadata(DescriptiveTechnicalMetadataType value) {
        this.otherMetadata = value;
    }

}
