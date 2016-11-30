//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.11.30 alle 03:24:17 PM CET 
//


package it.polito.dp2.NFFG.sol1.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per TraversalPolicyType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TraversalPolicyType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.riccardopersiani.com/Schema}ReachabilityPolicyType">
 *       &lt;sequence>
 *         &lt;element name="Devices" type="{http://www.riccardopersiani.com/Schema}DevicesListType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TraversalPolicyType", propOrder = {
    "devices"
})
public class TraversalPolicyType
    extends ReachabilityPolicyType
{

    @XmlElement(name = "Devices", required = true)
    protected List<DevicesListType> devices;

    /**
     * Gets the value of the devices property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the devices property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDevices().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DevicesListType }
     * 
     * 
     */
    public List<DevicesListType> getDevices() {
        if (devices == null) {
            devices = new ArrayList<DevicesListType>();
        }
        return this.devices;
    }

}
