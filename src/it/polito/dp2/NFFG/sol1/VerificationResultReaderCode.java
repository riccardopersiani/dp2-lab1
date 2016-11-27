package it.polito.dp2.NFFG.sol1;

import java.util.Calendar;

import javax.xml.datatype.XMLGregorianCalendar;

import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.PolicyReader;
import it.polito.dp2.NFFG.VerificationResultReader;
import it.polito.dp2.NFFG.sol1.jaxb.NFFGType;
import it.polito.dp2.NFFG.sol1.jaxb.ReachabilityPolicyType;

public class VerificationResultReaderCode implements VerificationResultReader {

	private PolicyReader verificationPolicyReader;
	private Boolean verificationResult;
	private String verificationResultMsg;
	private XMLGregorianCalendar XMLGregVerificationTime;

	public VerificationResultReaderCode(NFFGType nffg, NffgReader nffgReader, ReachabilityPolicyType policy){
		
		if(policy.getVerification() == null){
			System.out.println("NO VERIFICATION");
			this.verificationResult = null;
			this.verificationResultMsg = null; 
			this.XMLGregVerificationTime = null;
			this.verificationPolicyReader = new PolicyReaderCode(nffg, nffgReader, policy, this);
		} else {
			System.out.println("VERIFICATION");
			this.verificationResult = policy.getVerification().isResult();
			this.verificationResultMsg = policy.getVerification().getMessage(); 
			this.XMLGregVerificationTime = policy.getVerification().getTime();
			this.verificationPolicyReader = new PolicyReaderCode(nffg, nffgReader, policy, this);
		}
	}

	@Override
	public PolicyReader getPolicy() {
		return this.verificationPolicyReader;
	}

	@Override
	public Boolean getVerificationResult() {
		return this.verificationResult;
	}

	@Override
	public String getVerificationResultMsg() {
		return this.verificationResultMsg;
	}

	@Override
	public Calendar getVerificationTime() {
		Calendar verificationTime;
		verificationTime = XMLGregVerificationTime.toGregorianCalendar();
		return verificationTime;

	}
}
