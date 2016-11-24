package it.polito.dp2.NFFG.sol1;

import java.util.Calendar;

import javax.xml.datatype.XMLGregorianCalendar;

import it.polito.dp2.NFFG.PolicyReader;
import it.polito.dp2.NFFG.VerificationResultReader;
import it.polito.dp2.NFFG.sol1.jaxb.NFFGType;
import it.polito.dp2.NFFG.sol1.jaxb.ReachabilityPolicyType;

public class VerificationResusltReaderCode implements VerificationResultReader {

	private PolicyReader verificationPolicyReader;
	private Boolean verificationResult;
	private String verificationResultMsg;
	private XMLGregorianCalendar XMLGregVerificationTime;
	
	public VerificationResusltReaderCode(NFFGType nffg, ReachabilityPolicyType policy){
		//this.verificationPolicyReader = new PolicyReaderCode(nffg, policy);
		this.verificationResult = policy.getVerification().isResult();
		this.verificationResultMsg = policy.getVerification().getMessage(); 
		this.XMLGregVerificationTime = policy.getVerification().getTime();
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
