package it.polito.dp2.NFFG.sol1;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifier;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.PolicyReader;
import it.polito.dp2.NFFG.ReachabilityPolicyReader;
import it.polito.dp2.NFFG.TraversalPolicyReader;
import it.polito.dp2.NFFG.sol1.jaxb.NFFGType;
import it.polito.dp2.NFFG.sol1.jaxb.ReachabilityPolicyType;
import it.polito.dp2.NFFG.sol1.jaxb.RootNetworkType;
import it.polito.dp2.NFFG.sol1.jaxb.TraversalPolicyType;

public class NffgVerifierCode implements NffgVerifier {

	public static final String XSD_NAME = "xsd/nffgInfo.xsd";
	public static final String PACKAGE = "it.polito.dp2.NFFG.sol1.jaxb";
	
	private Set<NffgReader> nffgReaders; //Contains all the Nffgs
	private Set<PolicyReader> policyReaders;	// Contains all the Policies
	
	private Set<PolicyReader> oneNffgPolicies; //Contains the policy for one nffg
	private Map<NffgReader,Set<PolicyReader>> nffgPoliciesMap; //Contains a map of all policies for every nffg

	public NffgVerifierCode() throws SAXException, JAXBException, IllegalArgumentException, NffgVerifierException{	

		nffgReaders = new HashSet<NffgReader>();
		policyReaders = new HashSet<PolicyReader>();
		nffgPoliciesMap = new HashMap<NffgReader,Set<PolicyReader>>();

		String fileName = System.getProperty("it.polito.dp2.NFFG.sol1.NffgInfo.file");
		RootNetworkType root = doUnmarshall(new File(fileName));

		for(NFFGType nffg : root.getNFFG()) {
			oneNffgPolicies = new HashSet<PolicyReader>();
			NffgReader nffgReader = new NffgReaderCode(nffg);
			nffgReaders.add(nffgReader);
			
			for(ReachabilityPolicyType reachabilityPolicy: nffg.getPolicies().getReachabilityPolicy()){
				ReachabilityPolicyReader reachabilityReader = new ReachabilityPolicyReaderCode(nffg, nffgReader, reachabilityPolicy);
				policyReaders.add(reachabilityReader);
				oneNffgPolicies.add(reachabilityReader);
			}
			
			for(TraversalPolicyType traversalPolicy: nffg.getPolicies().getTraversalPolicy()){
				TraversalPolicyReader traversalReader = new TraversalPolicyReaderCode(nffg, nffgReader, traversalPolicy);
				policyReaders.add(traversalReader);
				oneNffgPolicies.add(traversalReader);

			}
			nffgPoliciesMap.put(nffgReader, oneNffgPolicies);
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
		return null;
	}

	@Override
	public Set<PolicyReader> getPolicies() {
		return policyReaders;
	}

	@Override
	//Filtering policies by nffg name
	public Set<PolicyReader> getPolicies(String arg0) {
		for(NffgReader nffgReader: nffgReaders){
			if(nffgReader.equals(arg0)){
				return nffgPoliciesMap.get(nffgReader);
			}
		}
		return null;
	}

	@Override
	public Set<PolicyReader> getPolicies(Calendar arg0) {
		Set<PolicyReader> policyAfter = new HashSet<PolicyReader>();
		for(PolicyReader policyReader: policyReaders){
			if(policyReader.getResult().getVerificationTime().after(arg0)){
				policyAfter.add(policyReader);
			}
		}
		return policyAfter;
	}
	
	private RootNetworkType doUnmarshall(File inputFile) throws JAXBException, SAXException, IllegalArgumentException, NffgVerifierException {
		System.out.println("inputFile: "+inputFile);
		JAXBContext jc = JAXBContext.newInstance(PACKAGE);
		Schema schema = null;
		// Create the package where the class used to read XML elements and create objects (like NodeType).
		try {
			schema = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI).newSchema(new File(XSD_NAME));

		}catch(IllegalArgumentException e) {
			System.err.println("Error! No implementation of the schema language is available");
			throw new NffgVerifierException();
		}
		catch(NullPointerException e) {
			System.err.println("Error! The instance of the schema or the file of the schema is not well created!\n");
			throw new NffgVerifierException();
		}
		// Create the Unmarshaller to extract the schema data
		Unmarshaller jaxbUnmarshaller = jc.createUnmarshaller();
		// Set the schema 
		jaxbUnmarshaller.setSchema(schema);
		// Set the input file to be unmarshalled 
		@SuppressWarnings("unchecked")
		JAXBElement<RootNetworkType> root = (JAXBElement<RootNetworkType>) jaxbUnmarshaller.unmarshal(inputFile);
		RootNetworkType r = root.getValue();
		return r;
	}

}
