package it.polito.dp2.NFFG.sol1;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

import java.io.File;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifier;
import it.polito.dp2.NFFG.PolicyReader;
import it.polito.dp2.NFFG.ReachabilityPolicyReader;
import it.polito.dp2.NFFG.TraversalPolicyReader;
import it.polito.dp2.NFFG.sol1.jaxb.NFFGType;
import it.polito.dp2.NFFG.sol1.jaxb.ReachabilityPolicyType;
import it.polito.dp2.NFFG.sol1.jaxb.RootNetworkType;
import it.polito.dp2.NFFG.sol1.jaxb.TraversalPolicyType;

public class NffgVerifierCode implements NffgVerifier {

	private Set<NffgReader> nffgReaders;
	private Set<PolicyReader> policyReaders;

	public static final String XSD_NAME = "xsd/NffgInfo.xsd";
	public static final String PACKAGE = "it.polito.dp2.NFFG.sol1.jaxb";

	public NffgVerifierCode() throws SAXException, JAXBException{	
		nffgReaders = new HashSet<NffgReader>();
		policyReaders = new HashSet<PolicyReader>();

		String fileName = System.getProperty("it.polito.dp2.NFFG.sol1.NffgInfo.file");
		System.out.println(fileName+"**************************************");
		RootNetworkType root = null;
		// Get The RootElement
		root = doUnmarshall(new File(fileName));

		// Browse the list of all the Nffgs 		
		for(NFFGType nffg : root.getNFFG()) {
			// Covertion from NffgType to NffgReader
			NffgReader nffgReader = new NffgReaderCode(nffg);
			// Add the actual NffgReader to the set, otherwise it will be lost
			nffgReaders.add(nffgReader);
			for(ReachabilityPolicyType reachabilityPolicy: nffg.getPolicies().getReachabilityPolicy()){
				ReachabilityPolicyReader reachabilityReader = new ReachabilityPolicyReaderCode(nffg, reachabilityPolicy);
				policyReaders.add(reachabilityReader);
			}
			for(TraversalPolicyType traversalPolicy: nffg.getPolicies().getTraversalPolicy()){
				TraversalPolicyReader traversalReader = new TraversalPolicyReaderCode(nffg, traversalPolicy);
				policyReaders.add(traversalReader);
			}
		}	
	}

	@Override
	public Set<NffgReader> getNffgs() {
		return nffgReaders;
	}

	@Override
	public NffgReader getNffg(String arg0) {
		for(NffgReader nffgReader: nffgReaders){
			if(nffgReader.equals(arg0)){
				return nffgReader;
			}
		}
		//TODO
		return null;
	}

	@Override
	public Set<PolicyReader> getPolicies() {
		// TODO Auto-generated method stub
		return policyReaders;
	}

	@Override
	public Set<PolicyReader> getPolicies(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PolicyReader> getPolicies(Calendar arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private RootNetworkType doUnmarshall(File inputFile) throws JAXBException, SAXException, IllegalArgumentException {
		JAXBContext jc = JAXBContext.newInstance(PACKAGE);
		Schema schema = null;
		// Create the package where the class used to read XML elements and create objects like NodeType

		try {
			// Instantiate the schema from the file .xsd
			schema = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI).newSchema(new File(XSD_NAME));

		}catch(IllegalArgumentException e) {
			System.err.println("Error! No implementation of the schema language is available");
			throw e;
		}
		catch(NullPointerException e) {
			System.err.println("Error! The instance of the schema or the file of the schema is not well created!\n");
			throw new SAXException("The schema file is null!");
		}
		// Create the Unmarshaller to extract the schema data
		Unmarshaller jaxbUnmarshaller = jc.createUnmarshaller();
		// Set the schema 
		jaxbUnmarshaller.setSchema(schema);
		// Set the input file to be unmarshalled 
		return (RootNetworkType)jaxbUnmarshaller.unmarshal(inputFile);		
	}

}
