//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package fr.gouv.culture.archivesdefrance.seda.v23;

import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Informations décrivant une personne physique ou morale.
 * 
 * <p>Java class for AgentType complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="AgentType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <group ref="{fr:gouv:culture:archivesdefrance:seda:v2.3}PersonOrEntityGroup"/>
 *         <group ref="{fr:gouv:culture:archivesdefrance:seda:v2.3}BusinessGroup"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AgentType", propOrder = {
    "firstName",
    "birthName",
    "fullName",
    "givenName",
    "gender",
    "birthDate",
    "birthPlace",
    "deathDate",
    "deathPlace",
    "nationality",
    "corpname",
    "identifier",
    "function",
    "activity",
    "position",
    "role",
    "mandate"
})
public class AgentType {

    /**
     * Prénom d'une personne.
     * 
     */
    @XmlElement(name = "FirstName")
    protected String firstName;
    /**
     * Nom de naissance d'une personne.
     * 
     */
    @XmlElement(name = "BirthName")
    protected String birthName;
    /**
     * Nom complet d'une personne.
     * 
     */
    @XmlElement(name = "FullName")
    protected String fullName;
    /**
     * Nom d'usage d'une personne.
     * 
     */
    @XmlElement(name = "GivenName")
    protected String givenName;
    /**
     * Sexe de la personne.
     * 
     */
    @XmlElement(name = "Gender")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String gender;
    /**
     * Date de naissance de la personne.
     * 
     */
    @XmlElement(name = "BirthDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar birthDate;
    /**
     * Lieu de naissance de la personne.
     * 
     */
    @XmlElement(name = "BirthPlace")
    protected BirthOrDeathPlaceType birthPlace;
    /**
     * Date de décès d'une personne.
     * 
     */
    @XmlElement(name = "DeathDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar deathDate;
    /**
     * Lieu de décès d'une personne.
     * 
     */
    @XmlElement(name = "DeathPlace")
    protected BirthOrDeathPlaceType deathPlace;
    /**
     * Nationalité d'une personne.
     * 
     */
    @XmlElement(name = "Nationality")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected List<String> nationality;
    /**
     * Nom d'une entité.
     * 
     */
    @XmlElement(name = "Corpname")
    protected String corpname;
    /**
     * Identifiant de la personne (par exemple, le numéro matricule) ou de l'entité.
     * 
     */
    @XmlElement(name = "Identifier")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected List<String> identifier;
    /**
     * En plus des balises Tag et Keyword, il est possible d'indexer les objets avec des éléments pré-définis : Fonction.
     * 
     */
    @XmlElement(name = "Function")
    protected List<TextType> function;
    /**
     * En plus des balises Tag et Keyword, il est possible d'indexer les objets avec des éléments pré-définis : Activité.
     * 
     */
    @XmlElement(name = "Activity")
    protected List<TextType> activity;
    /**
     * Intitulé du poste de travail occupé par la personne.
     * 
     */
    @XmlElement(name = "Position")
    protected List<TextType> position;
    /**
     * Références : moreq.role
     * 
     */
    @XmlElement(name = "Role")
    protected List<TextType> role;
    /**
     * Définit la propriété intellectuelle et artistique.
     * 
     */
    @XmlElement(name = "Mandate")
    protected List<TextType> mandate;

    /**
     * Prénom d'une personne.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getFirstName()
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Nom de naissance d'une personne.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthName() {
        return birthName;
    }

    /**
     * Sets the value of the birthName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getBirthName()
     */
    public void setBirthName(String value) {
        this.birthName = value;
    }

    /**
     * Nom complet d'une personne.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the value of the fullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getFullName()
     */
    public void setFullName(String value) {
        this.fullName = value;
    }

    /**
     * Nom d'usage d'une personne.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Sets the value of the givenName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getGivenName()
     */
    public void setGivenName(String value) {
        this.givenName = value;
    }

    /**
     * Sexe de la personne.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getGender()
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * Date de naissance de la personne.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the value of the birthDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     * @see #getBirthDate()
     */
    public void setBirthDate(XMLGregorianCalendar value) {
        this.birthDate = value;
    }

    /**
     * Lieu de naissance de la personne.
     * 
     * @return
     *     possible object is
     *     {@link BirthOrDeathPlaceType }
     *     
     */
    public BirthOrDeathPlaceType getBirthPlace() {
        return birthPlace;
    }

    /**
     * Sets the value of the birthPlace property.
     * 
     * @param value
     *     allowed object is
     *     {@link BirthOrDeathPlaceType }
     *     
     * @see #getBirthPlace()
     */
    public void setBirthPlace(BirthOrDeathPlaceType value) {
        this.birthPlace = value;
    }

    /**
     * Date de décès d'une personne.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeathDate() {
        return deathDate;
    }

    /**
     * Sets the value of the deathDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     * @see #getDeathDate()
     */
    public void setDeathDate(XMLGregorianCalendar value) {
        this.deathDate = value;
    }

    /**
     * Lieu de décès d'une personne.
     * 
     * @return
     *     possible object is
     *     {@link BirthOrDeathPlaceType }
     *     
     */
    public BirthOrDeathPlaceType getDeathPlace() {
        return deathPlace;
    }

    /**
     * Sets the value of the deathPlace property.
     * 
     * @param value
     *     allowed object is
     *     {@link BirthOrDeathPlaceType }
     *     
     * @see #getDeathPlace()
     */
    public void setDeathPlace(BirthOrDeathPlaceType value) {
        this.deathPlace = value;
    }

    /**
     * Nationalité d'une personne.
     * 
     * Gets the value of the nationality property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nationality property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getNationality().add(newItem);
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
     *     The value of the nationality property.
     */
    public List<String> getNationality() {
        if (nationality == null) {
            nationality = new ArrayList<>();
        }
        return this.nationality;
    }

    /**
     * Nom d'une entité.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorpname() {
        return corpname;
    }

    /**
     * Sets the value of the corpname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getCorpname()
     */
    public void setCorpname(String value) {
        this.corpname = value;
    }

    /**
     * Identifiant de la personne (par exemple, le numéro matricule) ou de l'entité.
     * 
     * Gets the value of the identifier property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identifier property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getIdentifier().add(newItem);
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
     *     The value of the identifier property.
     */
    public List<String> getIdentifier() {
        if (identifier == null) {
            identifier = new ArrayList<>();
        }
        return this.identifier;
    }

    /**
     * En plus des balises Tag et Keyword, il est possible d'indexer les objets avec des éléments pré-définis : Fonction.
     * 
     * Gets the value of the function property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the function property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getFunction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextType }
     * </p>
     * 
     * 
     * @return
     *     The value of the function property.
     */
    public List<TextType> getFunction() {
        if (function == null) {
            function = new ArrayList<>();
        }
        return this.function;
    }

    /**
     * En plus des balises Tag et Keyword, il est possible d'indexer les objets avec des éléments pré-définis : Activité.
     * 
     * Gets the value of the activity property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the activity property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getActivity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextType }
     * </p>
     * 
     * 
     * @return
     *     The value of the activity property.
     */
    public List<TextType> getActivity() {
        if (activity == null) {
            activity = new ArrayList<>();
        }
        return this.activity;
    }

    /**
     * Intitulé du poste de travail occupé par la personne.
     * 
     * Gets the value of the position property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the position property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getPosition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextType }
     * </p>
     * 
     * 
     * @return
     *     The value of the position property.
     */
    public List<TextType> getPosition() {
        if (position == null) {
            position = new ArrayList<>();
        }
        return this.position;
    }

    /**
     * Références : moreq.role
     * 
     * Gets the value of the role property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the role property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextType }
     * </p>
     * 
     * 
     * @return
     *     The value of the role property.
     */
    public List<TextType> getRole() {
        if (role == null) {
            role = new ArrayList<>();
        }
        return this.role;
    }

    /**
     * Définit la propriété intellectuelle et artistique.
     * 
     * Gets the value of the mandate property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mandate property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getMandate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextType }
     * </p>
     * 
     * 
     * @return
     *     The value of the mandate property.
     */
    public List<TextType> getMandate() {
        if (mandate == null) {
            mandate = new ArrayList<>();
        }
        return this.mandate;
    }

}
