//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.5
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
//

package fr.gouv.culture.archivesdefrance.seda.v22;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

/**
 * Java class for TextType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="TextType">
 *   <simpleContent>
 *     <extension base="<http://www.w3.org/2001/XMLSchema>string">
 *       <attribute ref="{http://www.w3.org/XML/1998/namespace}lang"/>
 *     </extension>
 *   </simpleContent>
 * </complexType>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "TextType",
    propOrder = {"value"})
@XmlSeeAlso({CustodialHistoryItemType.class})
public class TextType {

  @XmlValue protected String value;

  /**
   *
   *
   * <pre>
   * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;div xmlns="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;&lt;h3&gt;lang (as an attribute name)&lt;/h3&gt;&lt;p&gt;
   *        denotes an attribute whose value
   *        is a language code for the natural language of the content of
   *        any element; its value is inherited.  This name is reserved
   *        by virtue of its definition in the XML specification.&lt;/p&gt;&lt;/div&gt;
   * </pre>
   *
   * <pre>
   * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;div xmlns="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;&lt;h4&gt;Notes&lt;/h4&gt;&lt;p&gt;
   *       Attempting to install the relevant ISO 2- and 3-letter
   *       codes as the enumerated possible values is probably never
   *       going to be a realistic possibility.
   *      &lt;/p&gt;&lt;p&gt;
   *       See BCP 47 at &lt;a href="http://www.rfc-editor.org/rfc/bcp/bcp47.txt"&gt;
   *        http://www.rfc-editor.org/rfc/bcp/bcp47.txt&lt;/a&gt;
   *       and the IANA language subtag registry at&lt;a href="http://www.iana.org/assignments/language-subtag-registry"&gt;
   *        http://www.iana.org/assignments/language-subtag-registry&lt;/a&gt;
   *       for further information.&lt;/p&gt;&lt;p&gt;
   *       The union allows for the 'un-declaration' of xml:lang with
   *       the empty string.
   *      &lt;/p&gt;&lt;/div&gt;
   * </pre>
   */
  @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
  protected String lang;

  /**
   * Gets the value of the value property.
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
   *
   *
   * <pre>
   * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;div xmlns="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;&lt;h3&gt;lang (as an attribute name)&lt;/h3&gt;&lt;p&gt;
   *        denotes an attribute whose value
   *        is a language code for the natural language of the content of
   *        any element; its value is inherited.  This name is reserved
   *        by virtue of its definition in the XML specification.&lt;/p&gt;&lt;/div&gt;
   * </pre>
   *
   * <pre>
   * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;div xmlns="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;&lt;h4&gt;Notes&lt;/h4&gt;&lt;p&gt;
   *       Attempting to install the relevant ISO 2- and 3-letter
   *       codes as the enumerated possible values is probably never
   *       going to be a realistic possibility.
   *      &lt;/p&gt;&lt;p&gt;
   *       See BCP 47 at &lt;a href="http://www.rfc-editor.org/rfc/bcp/bcp47.txt"&gt;
   *        http://www.rfc-editor.org/rfc/bcp/bcp47.txt&lt;/a&gt;
   *       and the IANA language subtag registry at&lt;a href="http://www.iana.org/assignments/language-subtag-registry"&gt;
   *        http://www.iana.org/assignments/language-subtag-registry&lt;/a&gt;
   *       for further information.&lt;/p&gt;&lt;p&gt;
   *       The union allows for the 'un-declaration' of xml:lang with
   *       the empty string.
   *      &lt;/p&gt;&lt;/div&gt;
   * </pre>
   *
   * @return possible object is {@link String }
   */
  public String getLang() {
    return lang;
  }

  /**
   * Sets the value of the lang property.
   *
   * @param value allowed object is {@link String }
   * @see #getLang()
   */
  public void setLang(String value) {
    this.lang = value;
  }
}
