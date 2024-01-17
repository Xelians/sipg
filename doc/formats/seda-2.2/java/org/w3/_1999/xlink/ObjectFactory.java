//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v4.0.4 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
//


package org.w3._1999.xlink;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.w3._1999.xlink package. 
 * <p>An ObjectFactory allows you to programmatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private static final QName _Title_QNAME = new QName("http://www.w3.org/1999/xlink", "title");
    private static final QName _Resource_QNAME = new QName("http://www.w3.org/1999/xlink", "resource");
    private static final QName _Locator_QNAME = new QName("http://www.w3.org/1999/xlink", "locator");
    private static final QName _Arc_QNAME = new QName("http://www.w3.org/1999/xlink", "arc");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.w3._1999.xlink
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TitleEltType }
     * 
     * @return
     *     the new instance of {@link TitleEltType }
     */
    public TitleEltType createTitleEltType() {
        return new TitleEltType();
    }

    /**
     * Create an instance of {@link ResourceType }
     * 
     * @return
     *     the new instance of {@link ResourceType }
     */
    public ResourceType createResourceType() {
        return new ResourceType();
    }

    /**
     * Create an instance of {@link LocatorType }
     * 
     * @return
     *     the new instance of {@link LocatorType }
     */
    public LocatorType createLocatorType() {
        return new LocatorType();
    }

    /**
     * Create an instance of {@link ArcType }
     * 
     * @return
     *     the new instance of {@link ArcType }
     */
    public ArcType createArcType() {
        return new ArcType();
    }

    /**
     * Create an instance of {@link Simple }
     * 
     * @return
     *     the new instance of {@link Simple }
     */
    public Simple createSimple() {
        return new Simple();
    }

    /**
     * Create an instance of {@link Extended }
     * 
     * @return
     *     the new instance of {@link Extended }
     */
    public Extended createExtended() {
        return new Extended();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TitleEltType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TitleEltType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.w3.org/1999/xlink", name = "title")
    public JAXBElement<TitleEltType> createTitle(TitleEltType value) {
        return new JAXBElement<>(_Title_QNAME, TitleEltType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResourceType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ResourceType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.w3.org/1999/xlink", name = "resource")
    public JAXBElement<ResourceType> createResource(ResourceType value) {
        return new JAXBElement<>(_Resource_QNAME, ResourceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocatorType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LocatorType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.w3.org/1999/xlink", name = "locator")
    public JAXBElement<LocatorType> createLocator(LocatorType value) {
        return new JAXBElement<>(_Locator_QNAME, LocatorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArcType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArcType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.w3.org/1999/xlink", name = "arc")
    public JAXBElement<ArcType> createArc(ArcType value) {
        return new JAXBElement<>(_Arc_QNAME, ArcType.class, null, value);
    }

}
