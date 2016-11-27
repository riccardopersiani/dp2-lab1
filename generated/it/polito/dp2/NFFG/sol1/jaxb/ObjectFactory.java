//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.11.27 alle 06:12:39 PM CET 
//


package it.polito.dp2.NFFG.sol1.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.polito.dp2.NFFG.sol1.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
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

    private final static QName _RootNetwork_QNAME = new QName("http://www.riccardopersiani.com/Schema", "RootNetwork");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.polito.dp2.NFFG.sol1.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CatalogType }
     * 
     */
    public CatalogType createCatalogType() {
        return new CatalogType();
    }

    /**
     * Create an instance of {@link RootNetworkType }
     * 
     */
    public RootNetworkType createRootNetworkType() {
        return new RootNetworkType();
    }

    /**
     * Create an instance of {@link NFFGType }
     * 
     */
    public NFFGType createNFFGType() {
        return new NFFGType();
    }

    /**
     * Create an instance of {@link VerificationType }
     * 
     */
    public VerificationType createVerificationType() {
        return new VerificationType();
    }

    /**
     * Create an instance of {@link NodesType }
     * 
     */
    public NodesType createNodesType() {
        return new NodesType();
    }

    /**
     * Create an instance of {@link TraversalPolicyType }
     * 
     */
    public TraversalPolicyType createTraversalPolicyType() {
        return new TraversalPolicyType();
    }

    /**
     * Create an instance of {@link ReachabilityPolicyType }
     * 
     */
    public ReachabilityPolicyType createReachabilityPolicyType() {
        return new ReachabilityPolicyType();
    }

    /**
     * Create an instance of {@link NodeType }
     * 
     */
    public NodeType createNodeType() {
        return new NodeType();
    }

    /**
     * Create an instance of {@link DevicesListType }
     * 
     */
    public DevicesListType createDevicesListType() {
        return new DevicesListType();
    }

    /**
     * Create an instance of {@link PoliciesType }
     * 
     */
    public PoliciesType createPoliciesType() {
        return new PoliciesType();
    }

    /**
     * Create an instance of {@link LinkType }
     * 
     */
    public LinkType createLinkType() {
        return new LinkType();
    }

    /**
     * Create an instance of {@link LinksType }
     * 
     */
    public LinksType createLinksType() {
        return new LinksType();
    }

    /**
     * Create an instance of {@link CatalogType.SingleService }
     * 
     */
    public CatalogType.SingleService createCatalogTypeSingleService() {
        return new CatalogType.SingleService();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RootNetworkType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.riccardopersiani.com/Schema", name = "RootNetwork")
    public JAXBElement<RootNetworkType> createRootNetwork(RootNetworkType value) {
        return new JAXBElement<RootNetworkType>(_RootNetwork_QNAME, RootNetworkType.class, null, value);
    }

}
