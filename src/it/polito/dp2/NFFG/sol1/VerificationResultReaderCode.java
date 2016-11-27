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
		System.out.println("policy: "+policy);
		System.out.println("Id: "+policy.getId());
		System.out.println("VerificationMessage: "+policy.getVerification().getMessage());
		System.out.println("VerificationTime: "+policy.getVerification().getTime());
		System.out.println("VerificationReuslt: "+policy.getVerification().isResult());	
		this.verificationResult = policy.getVerification().isResult();
		this.verificationResultMsg = policy.getVerification().getMessage(); 
		this.XMLGregVerificationTime = policy.getVerification().getTime();
		this.verificationPolicyReader = new PolicyReaderCode(nffg, nffgReader, policy, this);
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
