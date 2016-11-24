//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.11.24 alle 01:12:31 PM CET 
//


package it.polito.dp2.NFFG.sol1.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ServiceType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ServiceType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Firewall"/>
 *     &lt;enumeration value="DPI"/>
 *     &lt;enumeration value="NAT"/>
 *     &lt;enumeration value="Anti-spam"/>
 *     &lt;enumeration value="Web-cache"/>
 *     &lt;enumeration value="VPN gateway"/>
 *     &lt;enumeration value="Web server"/>
 *     &lt;enumeration value="Web client"/>
 *     &lt;enumeration value="Mail server"/>
 *     &lt;enumeration value="Mail client"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ServiceType")
@XmlEnum
public enum ServiceType {

    @XmlEnumValue("Firewall")
    FIREWALL("Firewall"),
    DPI("DPI"),
    NAT("NAT"),
    @XmlEnumValue("Anti-spam")
    ANTI_SPAM("Anti-spam"),
    @XmlEnumValue("Web-cache")
    WEB_CACHE("Web-cache"),
    @XmlEnumValue("VPN gateway")
    VPN_GATEWAY("VPN gateway"),
    @XmlEnumValue("Web server")
    WEB_SERVER("Web server"),
    @XmlEnumValue("Web client")
    WEB_CLIENT("Web client"),
    @XmlEnumValue("Mail server")
    MAIL_SERVER("Mail server"),
    @XmlEnumValue("Mail client")
    MAIL_CLIENT("Mail client");
    private final String value;

    ServiceType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ServiceType fromValue(String v) {
        for (ServiceType c: ServiceType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
