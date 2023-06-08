//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.10.29 at 01:17:12 AM CET 
//
package fr.gouv.culture.archivesdefrance.seda.v2;

import jakarta.xml.bind.annotation.*;
import org.w3c.dom.Element;

/**
 * Métadonnées de base par type d'objet-données.
 *
 * <p>
 * Java class for CoreMetadataType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CoreMetadataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="Text" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}TextTechnicalMetadataType"/>
 *         &lt;element name="Document" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}DocumentTechnicalMetadataType"/>
 *         &lt;element name="Image" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}ImageTechnicalMetadataType"/>
 *         &lt;element name="Audio" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}AudioTechnicalMetadataType"/>
 *         &lt;element name="Video" type="{fr:gouv:culture:archivesdefrance:seda:v2.1}VideoTechnicalMetadataType"/>
 *         &lt;any processContents='lax' minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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
     * The Text.
     */
    @XmlElement(name = "Text")
    protected TextTechnicalMetadataType text;
    /**
     * The Document.
     */
    @XmlElement(name = "Document")
    protected DocumentTechnicalMetadataType document;
    /**
     * The Image.
     */
    @XmlElement(name = "Image")
    protected ImageTechnicalMetadataType image;
    /**
     * The Audio.
     */
    @XmlElement(name = "Audio")
    protected AudioTechnicalMetadataType audio;
    /**
     * The Video.
     */
    @XmlElement(name = "Video")
    protected VideoTechnicalMetadataType video;
    /**
     * The Any.
     */
    @XmlAnyElement(lax = true)
    protected Object any;

    /**
     * Gets the value of the text property.
     *
     * @return possible object is {@link TextTechnicalMetadataType }
     */
    public TextTechnicalMetadataType getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     *
     * @param value allowed object is {@link TextTechnicalMetadataType }
     */
    public void setText(TextTechnicalMetadataType value) {
        this.text = value;
    }

    /**
     * Gets the value of the document property.
     *
     * @return possible object is {@link DocumentTechnicalMetadataType }
     */
    public DocumentTechnicalMetadataType getDocument() {
        return document;
    }

    /**
     * Sets the value of the document property.
     *
     * @param value allowed object is {@link DocumentTechnicalMetadataType }
     */
    public void setDocument(DocumentTechnicalMetadataType value) {
        this.document = value;
    }

    /**
     * Gets the value of the image property.
     *
     * @return possible object is {@link ImageTechnicalMetadataType }
     */
    public ImageTechnicalMetadataType getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     *
     * @param value allowed object is {@link ImageTechnicalMetadataType }
     */
    public void setImage(ImageTechnicalMetadataType value) {
        this.image = value;
    }

    /**
     * Gets the value of the audio property.
     *
     * @return possible object is {@link AudioTechnicalMetadataType }
     */
    public AudioTechnicalMetadataType getAudio() {
        return audio;
    }

    /**
     * Sets the value of the audio property.
     *
     * @param value allowed object is {@link AudioTechnicalMetadataType }
     */
    public void setAudio(AudioTechnicalMetadataType value) {
        this.audio = value;
    }

    /**
     * Gets the value of the video property.
     *
     * @return possible object is {@link VideoTechnicalMetadataType }
     */
    public VideoTechnicalMetadataType getVideo() {
        return video;
    }

    /**
     * Sets the value of the video property.
     *
     * @param value allowed object is {@link VideoTechnicalMetadataType }
     */
    public void setVideo(VideoTechnicalMetadataType value) {
        this.video = value;
    }

    /**
     * Gets the value of the any property.
     *
     * @return possible object is null null null null     {@link Element }     {@link Object }
     */
    public Object getAny() {
        return any;
    }

    /**
     * Sets the value of the any property.
     *
     * @param value allowed object is null null null null     {@link Element }     {@link Object }
     */
    public void setAny(Object value) {
        this.any = value;
    }

}
