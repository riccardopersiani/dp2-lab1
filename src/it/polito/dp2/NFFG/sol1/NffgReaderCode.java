package it.polito.dp2.NFFG.sol1;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

import it.polito.dp2.NFFG.LinkReader;
import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.sol1.jaxb.LinkType;
import it.polito.dp2.NFFG.sol1.jaxb.NFFGType;
import it.polito.dp2.NFFG.sol1.jaxb.NodeType;

public class NffgReaderCode implements NffgReader {

	private String nffgName;
	private XMLGregorianCalendar XMLGregorianlastUpdateTime; 
	private Calendar lastUpdateTime;
	private Set<NodeReader> nodeReaders;
	
	private Set<LinkReader> linkReaders;
	
	public NffgReaderCode(NFFGType nffg) {
		this.nffgName = nffg.getName();
		this.XMLGregorianlastUpdateTime = nffg.getLastUpdateTime();
		this.lastUpdateTime = this.XMLGregorianlastUpdateTime.toGregorianCalendar();

		nodeReaders = new HashSet<NodeReader>();
		linkReaders = new HashSet<LinkReader>();
		
		// Call NodeReader() to instantiate it
		for(NodeType node : nffg.getNodes().getNode()) {
			NodeReader nodeReader = new NodeReaderCode(node, nffg);
			System.out.println("nodeReader added: "+nodeReader.toString());
			this.nodeReaders.add(nodeReader);
		}
		// Save in a set all the link readers instantiated
		for(NodeReader nr : nodeReaders) {
			System.out.println("nr: "+nr.toString());
			for(LinkReader lr : nr.getLinks()){
				System.out.println("lr added: "+lr.toString());
				this.linkReaders.add(lr); 
			}
		}
		//Set the destination node of a link reader as a Real NodeReader
		for(LinkReader lr : linkReaders){
			for(NodeReader nr : nodeReaders) {
				if(lr.getDestinationNode().getName() == nr.getName()){
					((LinkReaderCode) lr).setDestinationNode(nr);
					System.out.println("DESTINATION UPDATE"+lr.toString());
				}
			}
		}
	}
	
	@Override
	public String getName() {
		return this.nffgName;
	}

	@Override
	public Calendar getUpdateTime() {
		return this.lastUpdateTime;
	}

	@Override
	public Set<NodeReader> getNodes() {
		return this.nodeReaders;
	}

	@Override
	public NodeReader getNode(String arg0) {
		for(NodeReader nodeReader: nodeReaders){
			if(nodeReader.equals(arg0)){
				return nodeReader;
			}
		}
		//TODO
		return null;
	}

}
