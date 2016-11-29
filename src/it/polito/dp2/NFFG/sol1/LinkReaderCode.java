package it.polito.dp2.NFFG.sol1;

import it.polito.dp2.NFFG.LinkReader;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.sol1.jaxb.LinkType;
import it.polito.dp2.NFFG.sol1.jaxb.NFFGType;
import it.polito.dp2.NFFG.sol1.jaxb.NodeType;

public class LinkReaderCode extends NamedEntityReaderCode implements LinkReader {

	private NodeReader linkDestinationNode;
	private NodeReader linkSourceNode;

	public LinkReaderCode(NFFGType nffg, LinkType link){
		
		super(link.getId());
		
		NodeReader nodus = null;

		//Looking for the Source Node
		for(NodeType node: nffg.getNodes().getNode()){
			if(node.getId().equals(link.getSource())){
				nodus = new NodeReaderUncomplete(node.getId());
				this.linkSourceNode = nodus;			
			}
		}

		//Looking for the Destination node
		for(NodeType node: nffg.getNodes().getNode()){	
			if(node.getId().equals(link.getDestination())){
				nodus = new NodeReaderUncomplete(node.getId());
				this.linkDestinationNode = nodus;
			}
		}
	}

	@Override
	public NodeReader getDestinationNode() {
		return this.linkDestinationNode;
	}

	@Override
	public NodeReader getSourceNode() {
		return this.linkSourceNode;
	}
	
	public void setSourceNode(NodeReader nodeReader){
		this.linkSourceNode = nodeReader;
	}
	
	public void setDestinationNode(NodeReader nodeReader){
		this.linkDestinationNode = nodeReader;
	}
	
	public String toString(){
		return "LinkReader -> Name: "+this.getName()+" - Source: "+this.linkSourceNode.getName()+" - Destination: "+this.linkDestinationNode.getName();
	}
}
