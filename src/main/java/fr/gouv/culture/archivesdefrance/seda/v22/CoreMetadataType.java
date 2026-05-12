//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.8 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2026.05.12 à 12:09:37 PM CEST
//

package fr.gouv.culture.archivesdefrance.seda.v22;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * Métadonnées de base par type d'objet-données.
 * 
 * <p>Classe Java pour CoreMetadataType complex type.</p>
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.</p>
 * 
 * <pre>{@code
 * <complexType name="CoreMetadataType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <choice>
 *         <element name="Text" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}TextTechnicalMetadataType"/>
 *         <element name="Document" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}DocumentTechnicalMetadataType"/>
 *         <element name="Image" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}ImageTechnicalMetadataType"/>
 *         <element name="Audio" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}AudioTechnicalMetadataType"/>
 *         <element name="Video" type="{fr:gouv:culture:archivesdefrance:seda:v2.2}VideoTechnicalMetadataType"/>
 *         <any processContents='lax' minOccurs="0"/>
 *       </choice>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoreMetadataType", propOrder = {
    "text",
    "document",
    "image",
    "audio",
    "video",
    "any"
})
public class CoreMetadataType {

    /**
     * Métadonnées pour un objet-données de type textuel.
     * 
     */
    @XmlElement(name = "Text")
    protected TextTechnicalMetadataType text;
    /**
     * Métadonnées pour un objet-données de type document.
     * 
     */
    @XmlElement(name = "Document")
    protected DocumentTechnicalMetadataType document;
    /**
     * Métadonnées pour un objet-données de type image.
     * 
     */
    @XmlElement(name = "Image")
    protected ImageTechnicalMetadataType image;
    /**
     * Métadonnées pour un objet-données de type audio.
     * 
     */
    @XmlElement(name = "Audio")
    protected AudioTechnicalMetadataType audio;
    /**
     * Métadonnées pour un objet-données de type vidéo.
     * 
     */
    @XmlElement(name = "Video")
    protected VideoTechnicalMetadataType video;
    @XmlAnyElement(lax = true)
    protected Object any;

    /**
     * Métadonnées pour un objet-données de type textuel.
     * 
     * @return
     *     possible object is
     *     {@link TextTechnicalMetadataType }
     *     
     */
    public TextTechnicalMetadataType getText() {
        return text;
    }

    /**
     * Définit la valeur de la propriété text.
     * 
     * @param value
     *     allowed object is
     *     {@link TextTechnicalMetadataType }
     *     
     * @see #getText()
     */
    public void setText(TextTechnicalMetadataType value) {
        this.text = value;
    }

    /**
     * Métadonnées pour un objet-données de type document.
     * 
     * @return
     *     possible object is
     *     {@link DocumentTechnicalMetadataType }
     *     
     */
    public DocumentTechnicalMetadataType getDocument() {
        return document;
    }

    /**
     * Définit la valeur de la propriété document.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentTechnicalMetadataType }
     *     
     * @see #getDocument()
     */
    public void setDocument(DocumentTechnicalMetadataType value) {
        this.document = value;
    }

    /**
     * Métadonnées pour un objet-données de type image.
     * 
     * @return
     *     possible object is
     *     {@link ImageTechnicalMetadataType }
     *     
     */
    public ImageTechnicalMetadataType getImage() {
        return image;
    }

    /**
     * Définit la valeur de la propriété image.
     * 
     * @param value
     *     allowed object is
     *     {@link ImageTechnicalMetadataType }
     *     
     * @see #getImage()
     */
    public void setImage(ImageTechnicalMetadataType value) {
        this.image = value;
    }

    /**
     * Métadonnées pour un objet-données de type audio.
     * 
     * @return
     *     possible object is
     *     {@link AudioTechnicalMetadataType }
     *     
     */
    public AudioTechnicalMetadataType getAudio() {
        return audio;
    }

    /**
     * Définit la valeur de la propriété audio.
     * 
     * @param value
     *     allowed object is
     *     {@link AudioTechnicalMetadataType }
     *     
     * @see #getAudio()
     */
    public void setAudio(AudioTechnicalMetadataType value) {
        this.audio = value;
    }

    /**
     * Métadonnées pour un objet-données de type vidéo.
     * 
     * @return
     *     possible object is
     *     {@link VideoTechnicalMetadataType }
     *     
     */
    public VideoTechnicalMetadataType getVideo() {
        return video;
    }

    /**
     * Définit la valeur de la propriété video.
     * 
     * @param value
     *     allowed object is
     *     {@link VideoTechnicalMetadataType }
     *     
     * @see #getVideo()
     */
    public void setVideo(VideoTechnicalMetadataType value) {
        this.video = value;
    }

    /**
     * Obtient la valeur de la propriété any.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     {@link Element }
     *     
     */
    public Object getAny() {
        return any;
    }

    /**
     * Définit la valeur de la propriété any.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     {@link Element }
     *     
     */
    public void setAny(Object value) {
        this.any = value;
    }

}
