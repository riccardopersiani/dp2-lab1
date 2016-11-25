package it.polito.dp2.NFFG.sol1;

import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.PolicyReader;
import it.polito.dp2.NFFG.VerificationResultReader;
import it.polito.dp2.NFFG.sol1.jaxb.NFFGType;
import it.polito.dp2.NFFG.sol1.jaxb.ReachabilityPolicyType;

public class PolicyReaderCode implements PolicyReader{

	private String policyName;
	private Boolean policyIsPositive;
	private NffgReader policyNffgReader;
	private VerificationResultReader policyVerificationResultReader;
	
	public PolicyReaderCode(NFFGType nffg, NffgReader nffgReader, ReachabilityPolicyType policy, VerificationResultReader verificationResultReader){
		this.policyName = policy.getId();
		this.policyIsPositive = policy.isIsPositive();
		this.policyNffgReader = nffgReader;
		this.policyVerificationResultReader = verificationResultReader;	
	}	

	@Override
	public String getName() {
		return this.policyName;
	}

	@Override
	public NffgReader getNffg() {
		return this.policyNffgReader;
	}

	@Override
	public VerificationResultReader getResult() {
		return this.policyVerificationResultReader;
	}

	@Override
	public Boolean isPositive() {
		return this.policyIsPositive;
	}
	
	public String toString(){ //TODO
		return "PolicyReader -> Name: "+this.policyName+", NffgReader: "+this.policyNffgReader.toString()+", ";
	}

}
