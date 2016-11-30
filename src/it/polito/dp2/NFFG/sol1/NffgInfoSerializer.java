package it.polito.dp2.NFFG.sol1;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import it.polito.dp2.NFFG.*;
import it.polito.dp2.NFFG.sol1.jaxb.DevicesListType;
import it.polito.dp2.NFFG.sol1.jaxb.LinkType;
import it.polito.dp2.NFFG.sol1.jaxb.LinksType;
import it.polito.dp2.NFFG.sol1.jaxb.NFFGType;
import it.polito.dp2.NFFG.sol1.jaxb.NodeType;
import it.polito.dp2.NFFG.sol1.jaxb.NodesType;
import it.polito.dp2.NFFG.sol1.jaxb.ObjectFactory;
import it.polito.dp2.NFFG.sol1.jaxb.PoliciesType;
import it.polito.dp2.NFFG.sol1.jaxb.ReachabilityPolicyType;
import it.polito.dp2.NFFG.sol1.jaxb.RootNetworkType;
import it.polito.dp2.NFFG.sol1.jaxb.ServiceType;
import it.polito.dp2.NFFG.sol1.jaxb.TraversalPolicyType;
import it.polito.dp2.NFFG.sol1.jaxb.VerificationType;

public class NffgInfoSerializer {

	public static final String XSD_NAME = "xsd/nffgInfo.xsd";
	public static final String PACKAGE = "it.polito.dp2.NFFG.sol1.jaxb";

	private NffgVerifier monitor;

	// Root element of the XML SCHEMA
	RootNetworkType rootNetwork = new ObjectFactory().createRootNetworkType();
	// Create the root?
	JAXBElement<RootNetworkType> root = new ObjectFactory().createRootNetwork(rootNetwork);

	//private RootNetworkType root = new ObjectFactory().createRootNetworkType();

	/**
	 * Default constructor
	 * @throws NffgVerifierException 
	 */
	public NffgInfoSerializer() throws NffgVerifierException {
		it.polito.dp2.NFFG.NffgVerifierFactory factory = it.polito.dp2.NFFG.NffgVerifierFactory.newInstance();
		monitor = factory.newNffgVerifier();
	}

	public NffgInfoSerializer(NffgVerifier monitor) {
		super();
		this.monitor = monitor;
	}

	public void FromJavaToXml(PrintStream filename){	
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("it.polito.dp2.NFFG.sol1.jaxb");
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			Schema schema = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI).newSchema(new File(XSD_NAME));

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);   
			jaxbMarshaller.setSchema(schema);
			jaxbMarshaller.marshal(root, filename);
			jaxbMarshaller.marshal(root, System.out);

		} catch (JAXBException e) {
			System.err.println("Error creating the new instance of the JAXBContent");
			e.printStackTrace();
		} catch (SAXException e) {
			System.err.println("Error creating the XML Schema object");
			System.err.println(e.toString());
			e.printStackTrace();
		} catch(IllegalArgumentException e) {
			System.err.println("Error! No implementation of the schema language is available");
			throw e;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			NffgInfoSerializer serializer = new NffgInfoSerializer();
			serializer.createNffgsStructure();
			PrintStream fpout = new PrintStream(new File(args[0]));
			serializer.FromJavaToXml(fpout);
		} catch (NffgVerifierException e) {
			/*******TODO********/
			System.err.println("Error creating the new NFFGInfoSerializer");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.err.println("Error! The file: "+args[0]+" does not exists!");
			e.printStackTrace();
		}
	}

	private XMLGregorianCalendar calendarToXMLGregorianCalendar(Calendar calendar) {
		try {
			DatatypeFactory dtf = DatatypeFactory.newInstance();
			XMLGregorianCalendar xgc = dtf.newXMLGregorianCalendar();
			xgc.setYear(calendar.get(Calendar.YEAR));
			xgc.setMonth(calendar.get(Calendar.MONTH) + 1);
			xgc.setDay(calendar.get(Calendar.DAY_OF_MONTH));
			xgc.setHour(calendar.get(Calendar.HOUR_OF_DAY));
			xgc.setMinute(calendar.get(Calendar.MINUTE));
			xgc.setSecond(calendar.get(Calendar.SECOND));
			xgc.setMillisecond(calendar.get(Calendar.MILLISECOND));
			// Calendar ZONE_OFFSET and DST_OFFSET fields are in milliseconds.
			int offsetInMinutes = (calendar.get(Calendar.ZONE_OFFSET) + calendar.get(Calendar.DST_OFFSET)) / (60 * 1000);
			xgc.setTimezone(offsetInMinutes);
			return xgc;
		} catch (DatatypeConfigurationException e) {
			System.out.print(e.getMessage());
			return null;
		}
	}

	private void createPoliciesStructure(NFFGType NFFG) {
		// Get the list of policies of the NFFG with name nffgName
		Set<PolicyReader> Policyset = monitor.getPolicies(NFFG.getName());
		/*** Create Policies ***/
		PoliciesType Policies = new ObjectFactory().createPoliciesType();
		// Add Policies to the actual NFFG
		NFFG.setPolicies(Policies);

		// For each policy print related data
		for (PolicyReader pr: Policyset) {
			try{
				/*** Create TraversalPolicy ***/
				TraversalPolicyType TraversalPolicy = new ObjectFactory().createTraversalPolicyType();			    
				// Set a single traversal policy
				TraversalPolicyReader policy = (TraversalPolicyReader) pr;
				// Get the list of traversed devices in the single policy
				Set<FunctionalType> FunctionalSet = policy.getTraversedFuctionalTypes();
				/*** Create Devices ***/
				DevicesListType Devices = new ObjectFactory().createDevicesListType();

				// Select each device traversed by the policy 
				for(FunctionalType f: FunctionalSet) {
					ServiceType Service = covertFunctionalToService(f);
					Devices.getDevice().add(Service);
				}	

				/* Setting Traversal Policy elements */
				TraversalPolicy.setId(policy.getName());
				TraversalPolicy.setSource(policy.getSourceNode().getName());
				TraversalPolicy.setDestination(policy.getDestinationNode().getName());
				TraversalPolicy.setIsPositive(policy.isPositive());
				TraversalPolicy.getDevices().add(Devices);

				VerificationResultReader result = policy.getResult();

				if (result == null) {
				} else{
					VerificationType Verification = new ObjectFactory().createVerificationType();

					// Set <attribute name="last_update_time">
					Calendar verificationTime = result.getVerificationTime();
					XMLGregorianCalendar verificationTimeXGC;
					verificationTimeXGC = calendarToXMLGregorianCalendar(verificationTime);

					if (result.getVerificationResult()){
						Verification.setResult(true);
						Verification.setTime(verificationTimeXGC);
						Verification.setMessage(result.getVerificationResultMsg());
					} else{
						Verification.setResult(false);
						Verification.setTime(verificationTimeXGC);
						Verification.setMessage(result.getVerificationResultMsg());
					}
					// Add Verification infos to the actual Traversal Policy
					TraversalPolicy.setVerification(Verification);
				}
				//Add the actual Traversal Policy to the Policies container
				Policies.getTraversalPolicy().add(TraversalPolicy);
			} 
			catch(ClassCastException e2){
				/*** Create ReachabilityPolicy ***/
				ReachabilityPolicyType ReachabilityPolicy = new ObjectFactory().createReachabilityPolicyType();			    
				// Set a single "potential" traversal policy
				ReachabilityPolicyReader policy = (ReachabilityPolicyReader) pr;
				/* Setting Reachability Policy elements */
				ReachabilityPolicy.setId(policy.getName());
				ReachabilityPolicy.setSource(policy.getSourceNode().getName());
				ReachabilityPolicy.setDestination(policy.getDestinationNode().getName());
				ReachabilityPolicy.setIsPositive(policy.isPositive());

				VerificationType Verification = new ObjectFactory().createVerificationType();
				VerificationResultReader result = policy.getResult();
				if (result != null){
					// Set <attribute name="last_update_time">
					Calendar verificationTime = result.getVerificationTime();
					XMLGregorianCalendar verificationTimeXGC;
					verificationTimeXGC = calendarToXMLGregorianCalendar(verificationTime);

					if (result.getVerificationResult()){
						Verification.setResult(true);
						Verification.setTime(verificationTimeXGC);
						Verification.setMessage(result.getVerificationResultMsg());
					} else{
						Verification.setResult(false);
						Verification.setTime(verificationTimeXGC);
						Verification.setMessage(result.getVerificationResultMsg());
					}
					// Add Verification infos to the actual Traversal Policy
					ReachabilityPolicy.setVerification(Verification);	
				}
				//Add the actual Reachability Policy to the Policies container
				Policies.getReachabilityPolicy().add(ReachabilityPolicy);
			}
		}
	}

	private ServiceType covertFunctionalToService(FunctionalType functional){
		ServiceType service = ServiceType.WEB_CACHE;
		switch(functional){
		case CACHE: service = ServiceType.WEB_CACHE;
					break;
		case DPI: service = ServiceType.DPI;
					break;
		case FW: service = ServiceType.FIREWALL;
					break;
		case NAT: service = ServiceType.NAT;
					break;
		case SPAM: service = ServiceType.ANTI_SPAM;
					break;
		case VPN: service = ServiceType.VPN_GATEWAY;
					break;
		case WEB_CLIENT: service = ServiceType.WEB_CLIENT;
					break;
		case MAIL_CLIENT: service = ServiceType.MAIL_CLIENT;
					break;
		case MAIL_SERVER: service = ServiceType.MAIL_SERVER;
					break;
		case WEB_SERVER: service = ServiceType.WEB_SERVER;
					break;
		}
		return service;
	}

	private void createNffgsStructure() {
		// Get the list of NFFGs
		Set<NffgReader> NFFGset = monitor.getNffgs();

		// For each NFFG print related data
		for (NffgReader nffg_r: NFFGset) {
			/*** Create one NFFG ***/
			NFFGType NFFG = new ObjectFactory().createNFFGType();
			// Set <attribute name="name">
			NFFG.setName(nffg_r.getName());
			// Set <attribute name="last_update_time">
			Calendar lastUpdateTime = nffg_r.getUpdateTime();
			XMLGregorianCalendar lastUpdateTimeXGC;
			lastUpdateTimeXGC = calendarToXMLGregorianCalendar(lastUpdateTime);
			NFFG.setLastUpdateTime(lastUpdateTimeXGC);

			// Add a single NFFG to the root
			rootNetwork.getNFFG().add(NFFG);
			// Method that add polices to the actual NFFG
			createPoliciesStructure(NFFG);
			// Get the list of nodes
			Set<NodeReader> nodeSet = nffg_r.getNodes();
			/*** Create Nodes ***/
			NodesType Nodes = new ObjectFactory().createNodesType();
			/*** Create Links ***/
			LinksType Links = new ObjectFactory().createLinksType();
			// Add Nodes to the NFFG
			NFFG.setNodes(Nodes);
			// Add Links to the NFFG
			NFFG.setLinks(Links);

			for (NodeReader nr: nodeSet) {
				/*** Create a Node ***/
				NodeType Node= new ObjectFactory().createNodeType();
				// Set <attribute name="id">
				Node.setId(nr.getName());
				// Set <attribute name="Service">
				Node.setService(covertFunctionalToService(nr.getFuncType()));
				// Add a single Node to the Nodes
				Nodes.getNode().add(Node);
				// Get the list of links
				Set<LinkReader> linkSet = nr.getLinks();

				for (LinkReader lr: linkSet){
					/*** Create a Link ***/
					LinkType Link = new ObjectFactory().createLinkType();
					// Set element Source
					Link.setSource(lr.getSourceNode().getName());
					// Set element Destination
					Link.setDestination(lr.getDestinationNode().getName());
					// Set <attribute name="id">
					Link.setId(lr.getName());
					// Add a single Link to the Links
					Links.getLink().add(Link);
				}// End for(Link)

			}// End for(Node)
		}// End for (NFFG)	
	}
}
