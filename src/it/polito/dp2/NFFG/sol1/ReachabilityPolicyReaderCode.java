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

	public ReachabilityPolicyReaderCode(NFFGType nffg,NffgReader nffgReader, ReachabilityPolicyType reachabilityPolicy){
		this.reachabilityPolicyName = reachabilityPolicy.getId();
		this.isPositive = reachabilityPolicy.isIsPositive();
		this.nffgReader = nffgReader;
		this.verificationResultReader = new VerificationResultReaderCode(nffg, nffgReader, reachabilityPolicy);	
		
		//TODO is necessary? or is necessary to put null field inside verifier?
		if(reachabilityPolicy.getVerification() == null){
			this.verificationResultReader = null;
		}

		// Set the source node of the reachability policy
		for(NodeType node: nffg.getNodes().getNode()){
			if(node.equals(reachabilityPolicy.getSource())){
				this.reachabilitySourceNode = nffgReader.getNode(node.getId());
			}
		}
		
		// Set the destination node of the reachability policy
		for(NodeType node: nffg.getNodes().getNode()){
			if(node.equals(reachabilityPolicy.getDestination())){
				this.reachabilityDestinationNode = nffgReader.getNode(node.getId());
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
