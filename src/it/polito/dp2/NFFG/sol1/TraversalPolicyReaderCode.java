package it.polito.dp2.NFFG.sol1;

import java.util.HashSet;
import java.util.Set;

import it.polito.dp2.NFFG.FunctionalType;
import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.TraversalPolicyReader;
import it.polito.dp2.NFFG.VerificationResultReader;
import it.polito.dp2.NFFG.sol1.jaxb.NFFGType;
import it.polito.dp2.NFFG.sol1.jaxb.NodeType;
import it.polito.dp2.NFFG.sol1.jaxb.ServiceType;
import it.polito.dp2.NFFG.sol1.jaxb.TraversalPolicyType;

public class TraversalPolicyReaderCode extends NamedEntityReaderCode implements TraversalPolicyReader{

	private NffgReader nffgReader;
	private VerificationResultReader verificationResultReader;
	private Boolean isPositive;
	private NodeReader traversalDestinationNode;
	private NodeReader traversalSourceNode;
	private Set<FunctionalType> traversalDevices;
	
	public TraversalPolicyReaderCode(NFFGType nffg, NffgReader nffgReader, TraversalPolicyType traversalPolicy){
		
		super(traversalPolicy.getId());
		
		this.isPositive = traversalPolicy.isIsPositive();
		this.nffgReader = nffgReader;
		this.verificationResultReader = new VerificationResultReaderCode(nffg, nffgReader, traversalPolicy);
		
		if(traversalPolicy.getVerification() == null){
			this.verificationResultReader = null;
		}
			
		this.traversalDevices = new HashSet<FunctionalType>();
				
		for(NodeType node: nffg.getNodes().getNode()){
			if(node.equals(traversalPolicy.getDestination())){
				this.traversalDestinationNode = nffgReader.getNode(node.getId());
			}
		}
		
		for(NodeType node: nffg.getNodes().getNode()){
			if(node.equals(traversalPolicy.getDestination())){
				this.traversalSourceNode = nffgReader.getNode(node.getId());
			}
		}
		
		for(ServiceType service: traversalPolicy.getDevices().getDevice()){
			traversalDevices.add(covertServiceToFunctional(service));
		}
	}
	
	@Override
	public NodeReader getDestinationNode() {
		return this.traversalDestinationNode;
	}

	@Override
	public NodeReader getSourceNode() {
		return this.traversalSourceNode;
	}

	@Override
	public NffgReader getNffg() {
		return this.nffgReader;
	}

	@Override
	public VerificationResultReader getResult() {
		return this.verificationResultReader;
	}

	@Override
	public Boolean isPositive() {
		return this.isPositive;
	}

	@Override
	public Set<FunctionalType> getTraversedFuctionalTypes() {
		return this.traversalDevices;
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
