//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.10.29 at 01:17:12 AM CET 
//
package fr.gouv.culture.archivesdefrance.seda.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Java class for CodeType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CodeType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;fr:gouv:culture:archivesdefrance:seda:v2.1>NonEmptyTokenType">
 *       &lt;attribute name="listID" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="listAgencyID" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="listAgencyName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="listName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="listVersionID" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="languageID" type="{http://www.w3.org/2001/XMLSchema}language" />
 *       &lt;attribute name="listURI" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="listSchemeURI" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CodeType", propOrder = {
    "value"
})
public class CodeType {

    /**
     * The Value.
     */
    @XmlValue
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String value;
    /**
     * The List id.
     */
    @XmlAttribute(name = "listID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String listID;
    /**
     * The List agency id.
     */
    @XmlAttribute(name = "listAgencyID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String listAgencyID;
    /**
     * The List agency name.
     */
    @XmlAttribute(name = "listAgencyName")
    protected String listAgencyName;
    /**
     * The List name.
     */
    @XmlAttribute(name = "listName")
    protected String listName;
    /**
     * The List version id.
     */
    @XmlAttribute(name = "listVersionID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String listVersionID;
    /**
     * The Name.
     */
    @XmlAttribute(name = "name")
    protected String name;
    /**
     * The Language id.
     */
    @XmlAttribute(name = "languageID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String languageID;
    /**
     * The List uri.
     */
    @XmlAttribute(name = "listURI")
    @XmlSchemaType(name = "anyURI")
    protected String listURI;
    /**
     * The List scheme uri.
     */
    @XmlAttribute(name = "listSchemeURI")
    @XmlSchemaType(name = "anyURI")
    protected String listSchemeURI;

    /**
     * Elément ne pouvant être vide.
     *
     * @return possible object is {@link String }
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value allowed object is {@link String }
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the listID property.
     *
     * @return possible object is {@link String }
     */
    public String getListID() {
        return listID;
    }

    /**
     * Sets the value of the listID property.
     *
     * @param value allowed object is {@link String }
     */
    public void setListID(String value) {
        this.listID = value;
    }

    /**
     * Gets the value of the listAgencyID property.
     *
     * @return possible object is {@link String }
     */
    public String getListAgencyID() {
        return listAgencyID;
    }

    /**
     * Sets the value of the listAgencyID property.
     *
     * @param value allowed object is {@link String }
     */
    public void setListAgencyID(String value) {
        this.listAgencyID = value;
    }

    /**
     * Gets the value of the listAgencyName property.
     *
     * @return possible object is {@link String }
     */
    public String getListAgencyName() {
        return listAgencyName;
    }

    /**
     * Sets the value of the listAgencyName property.
     *
     * @param value allowed object is {@link String }
     */
    public void setListAgencyName(String value) {
        this.listAgencyName = value;
    }

    /**
     * Gets the value of the listName property.
     *
     * @return possible object is {@link String }
     */
    public String getListName() {
        return listName;
    }

    /**
     * Sets the value of the listName property.
     *
     * @param value allowed object is {@link String }
     */
    public void setListName(String value) {
        this.listName = value;
    }

    /**
     * Gets the value of the listVersionID property.
     *
     * @return possible object is {@link String }
     */
    public String getListVersionID() {
        return listVersionID;
    }

    /**
     * Sets the value of the listVersionID property.
     *
     * @param value allowed object is {@link String }
     */
    public void setListVersionID(String value) {
        this.listVersionID = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the languageID property.
     *
     * @return possible object is {@link String }
     */
    public String getLanguageID() {
        return languageID;
    }

    /**
     * Sets the value of the languageID property.
     *
     * @param value allowed object is {@link String }
     */
    public void setLanguageID(String value) {
        this.languageID = value;
    }

    /**
     * Gets the value of the listURI property.
     *
     * @return possible object is {@link String }
     */
    public String getListURI() {
        return listURI;
    }

    /**
     * Sets the value of the listURI property.
     *
     * @param value allowed object is {@link String }
     */
    public void setListURI(String value) {
        this.listURI = value;
    }

    /**
     * Gets the value of the listSchemeURI property.
     *
     * @return possible object is {@link String }
     */
    public String getListSchemeURI() {
        return listSchemeURI;
    }

    /**
     * Sets the value of the listSchemeURI property.
     *
     * @param value allowed object is {@link String }
     */
    public void setListSchemeURI(String value) {
        this.listSchemeURI = value;
    }

}
