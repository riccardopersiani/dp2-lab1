package it.polito.dp2.NFFG.sol1;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import it.polito.dp2.NFFG.NffgVerifier;
import it.polito.dp2.NFFG.NffgVerifierException;

public class NffgVerifierFactory extends it.polito.dp2.NFFG.NffgVerifierFactory {

	@Override
	public NffgVerifier newNffgVerifier() throws NffgVerifierException {
		NffgVerifier myNffgVerifier = null;
		try {
			myNffgVerifier = new NffgVerifierCode();
			System.out.println("new NffgVerifierCode() successfully called.");
		} catch (SAXException | JAXBException e) {
			e.printStackTrace();
			//System.out.println("INSIDE CATCH!!");
		}
		return myNffgVerifier;
	}

}
