//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.11.27 alle 06:12:39 PM CET 
//


package it.polito.dp2.NFFG.sol1.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per PoliciesType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="PoliciesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReachabilityPolicy" type="{http://www.riccardopersiani.com/Schema}ReachabilityPolicyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TraversalPolicy" type="{http://www.riccardopersiani.com/Schema}TraversalPolicyType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PoliciesType", propOrder = {
    "reachabilityPolicy",
    "traversalPolicy"
})
public class PoliciesType {

    @XmlElement(name = "ReachabilityPolicy")
    protected List<ReachabilityPolicyType> reachabilityPolicy;
    @XmlElement(name = "TraversalPolicy")
    protected List<TraversalPolicyType> traversalPolicy;

    /**
     * Gets the value of the reachabilityPolicy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reachabilityPolicy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReachabilityPolicy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReachabilityPolicyType }
     * 
     * 
     */
    public List<ReachabilityPolicyType> getReachabilityPolicy() {
        if (reachabilityPolicy == null) {
            reachabilityPolicy = new ArrayList<ReachabilityPolicyType>();
        }
        return this.reachabilityPolicy;
    }

    /**
     * Gets the value of the traversalPolicy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the traversalPolicy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTraversalPolicy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TraversalPolicyType }
     * 
     * 
     */
    public List<TraversalPolicyType> getTraversalPolicy() {
        if (traversalPolicy == null) {
            traversalPolicy = new ArrayList<TraversalPolicyType>();
        }
        return this.traversalPolicy;
    }

}
