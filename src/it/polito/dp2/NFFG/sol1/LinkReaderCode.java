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

		this.linkName = link.getId();	
		NodeReader nodus = null;

		//Looking for the Source Node
		for(NodeType node: nffg.getNodes().getNode()){
			if(node.getId().equals(link.getSource())){
				nodus = new NodeReaderUncomplete(node.getId());
				this.linkSourceNode = nodus;			
			}
		}

		//TODO Try to subsubstitute the for inside the other
		//Looking for the Destination node
		for(NodeType node: nffg.getNodes().getNode()){	
			if(node.getId().equals(link.getDestination())){
				nodus = new NodeReaderUncomplete(node.getId());
				this.linkDestinationNode = nodus;
			}
		}
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
		return "LinkReader -> Name: "+this.linkName+" - Source: "+this.linkSourceNode.getName()+" - Destination: "+this.linkDestinationNode.getName();
	}
}
