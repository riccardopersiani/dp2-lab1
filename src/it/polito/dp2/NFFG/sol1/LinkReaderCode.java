package it.polito.dp2.NFFG.sol1;

import it.polito.dp2.NFFG.LinkReader;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.sol1.jaxb.LinkType;
import it.polito.dp2.NFFG.sol1.jaxb.NFFGType;
import it.polito.dp2.NFFG.sol1.jaxb.NodeType;

public class LinkReaderCode implements LinkReader{

	private String linkName;
	private NodeReader linkDestinationNode;
	private NodeReader linkSourceNode;


	public LinkReaderCode(NFFGType nffg, LinkType link){
		System.out.println("LinkReaderCode - Inside Costructor");

		this.linkName = link.getId();	
		NodeReader nodus = null;

		//Aggiunge al link solo il nodo destinazione
		for(NodeType node: nffg.getNodes().getNode()){
			//String nodeName = node.getId();
			if(node.getId().equals(link.getDestination())){
				nodus = new NodeReaderUncomplete(node.getId());
				/*System.out.println("*** LINK-READER DESTINATION ***");
				System.out.println("NodeTypeID: "+node.getId());
				System.out.println("LinkDST: "+link.getDestination().toString());
				System.out.println("EQUALS");
				System.out.println("NodeReaderUncompl: "+nodus.getName());*/
				this.linkDestinationNode = nodus;
				System.out.println("Name: "+this.linkName);
				System.out.println("linkDestinationNode: "+this.linkDestinationNode.getName());				
			}
		}

		for(NodeType node: nffg.getNodes().getNode()){	
			String nodeName = node.getId();
			if(nodeName.equals(link.getSource())){
				nodus = new NodeReaderUncomplete(nodeName);
				this.linkSourceNode = nodus;
			}
		}
		//Bisogna aggiungere la sorgente ma puo darsi che il nodo sorgente non sia ancora stato istanziato
		System.out.println("LinkReaderCode - End Costructor");
	}

	@Override
	public String getName() {
		return this.linkName;
	}

	@Override
	public NodeReader getDestinationNode() {
		return this.linkDestinationNode;
	}

	@Override
	public NodeReader getSourceNode() {
		return this.linkSourceNode;
	}

	public String toString(){
		return "LINKREADER-> Name: "+this.linkName+" - Destination: "+this.linkDestinationNode.getName()+" - Source: "+this.linkSourceNode.getName();
	}

}
