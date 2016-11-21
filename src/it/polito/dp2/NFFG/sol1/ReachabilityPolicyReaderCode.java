package it.polito.dp2.NFFG.sol1;

import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.ReachabilityPolicyReader;
import it.polito.dp2.NFFG.VerificationResultReader;
import it.polito.dp2.NFFG.sol1.jaxb.NFFGType;
import it.polito.dp2.NFFG.sol1.jaxb.NodeType;
import it.polito.dp2.NFFG.sol1.jaxb.ReachabilityPolicyType;

public class ReachabilityPolicyReaderCode implements ReachabilityPolicyReader {

	private NffgReader nffgReader;
	private VerificationResultReader verificationResultReader;
	private Boolean isPositive;
	private String reachabilityPolicyName;
	private NodeReader reachabilityDestinationNode;
	private NodeReader reachabilitySourceNode;
	
	
	public ReachabilityPolicyReaderCode(NFFGType nffg, ReachabilityPolicyType reachabilityPolicy){
		this.reachabilityPolicyName = reachabilityPolicy.getId();
		this.isPositive = reachabilityPolicy.isIsPositive();
		this.verificationResultReader = new VerificationResusltReaderCode(nffg, reachabilityPolicy);
		this.nffgReader = new NffgReaderCode(nffg);
		
		for(NodeType node: nffg.getNodes().getNode()){
			if(node.equals(reachabilityPolicy.getDestination())){
				this.reachabilityDestinationNode = new NodeReaderCode(node,nffg);
			}
		}
		
		for(NodeType node: nffg.getNodes().getNode()){
			if(node.equals(reachabilityPolicy.getDestination())){
				this.reachabilitySourceNode = new NodeReaderCode(node,nffg);
			}
		}
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
	public String getName() {
		return this.reachabilityPolicyName;
	}

	@Override
	public NodeReader getDestinationNode() {
		return this.reachabilityDestinationNode;
	}

	@Override
	public NodeReader getSourceNode() {
		return this.reachabilitySourceNode;
	}

}
