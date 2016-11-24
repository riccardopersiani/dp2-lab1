package it.polito.dp2.NFFG.sol1;

import java.util.HashSet;
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
		LinkReader linkReader = null;
		System.out.println("NodeReaderCode - nodeName: "+node.getId());
		
		nodeLinksList = new HashSet<LinkReader>();

		//Setting only linkName and destination
		for(LinkType link: nffg.getLinks().getLink()){
			//Se la destinazione del link è uguale al nodo in questione
			if(link.getDestination().equals(node.getId())){
				//Vado a creare un link con destinazione uguale al nodo in questione
				linkReader = new LinkReaderCode(nffg, link);
				//Aggiungo il link al nodo, anche se il link reader è parziale perché non ha la sorgente
				System.out.println("NodeReaderCode - Before adding linkReader1");
				System.out.println(linkReader.toString());
				nodeLinksList.add(linkReader);
			}
		}
		System.out.println("NodeReaderCode - End Costructor");
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
