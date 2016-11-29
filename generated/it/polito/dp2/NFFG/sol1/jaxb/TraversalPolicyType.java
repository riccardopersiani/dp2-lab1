//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.11.28 alle 05:26:18 PM CET 
//


package it.polito.dp2.NFFG.sol1.jaxb;

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
 *         &lt;element name="Devices" type="{http://www.riccardopersiani.com/Schema}DevicesListType"/>
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
    protected DevicesListType devices;

    /**
     * Recupera il valore della proprietà devices.
     * 
     * @return
     *     possible object is
     *     {@link DevicesListType }
     *     
     */
    public DevicesListType getDevices() {
        return devices;
    }

    /**
     * Imposta il valore della proprietà devices.
     * 
     * @param value
     *     allowed object is
     *     {@link DevicesListType }
     *     
     */
    public void setDevices(DevicesListType value) {
        this.devices = value;
    }

}
