package it.polito.dp2.NFFG.sol1;

import java.util.Set;

import it.polito.dp2.NFFG.FunctionalType;
import it.polito.dp2.NFFG.LinkReader;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.sol1.jaxb.LinkType;
import it.polito.dp2.NFFG.sol1.jaxb.NFFGType;
import it.polito.dp2.NFFG.sol1.jaxb.NodeType;
import it.polito.dp2.NFFG.sol1.jaxb.ServiceType;

public class NodeReaderCode implements NodeReader{
	private String nodeName;
	private FunctionalType nodeFunctionalType;
	private Set<LinkReader> nodeLinksList;
	
	public NodeReaderCode(NodeType node, NFFGType nffg){
		this.nodeName = node.getId();
		this.nodeFunctionalType = covertServiceToFunctional(node.getService());
		
		for(LinkType link: nffg.getLinks().getLink()){
			LinkReader linkReader = new LinkReaderCode(nffg,link);
			nodeLinksList.add(linkReader);
		}
	}
	
	@Override
	public String getName() {
		return this.nodeName;
	}

	@Override
	public FunctionalType getFuncType() {
		return this.nodeFunctionalType;
	}

	@Override
	public Set<LinkReader> getLinks() {
		return nodeLinksList;
	}
	
	private FunctionalType covertServiceToFunctional(ServiceType service){
		FunctionalType functional = FunctionalType.CACHE;
		switch(service){
		case WEB_CACHE: functional = FunctionalType.CACHE;
				    break;
		case DPI: functional = FunctionalType.DPI;
					break;
		case FIREWALL: functional = FunctionalType.FW;
					break;
		case NAT: functional = FunctionalType.NAT;
					break;
		case ANTI_SPAM: functional = FunctionalType.SPAM;
					break;
		case VPN_GATEWAY: functional = FunctionalType.VPN;
					break;
		case WEB_CLIENT: functional = FunctionalType.WEB_CLIENT;
					break;
		case MAIL_CLIENT: functional = FunctionalType.MAIL_CLIENT;
					break;
		case MAIL_SERVER: functional = FunctionalType.MAIL_SERVER;
					break;
		case WEB_SERVER: functional = FunctionalType.WEB_SERVER;
					break;
		}
		return functional;
	}
}
