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
			
		for(NodeType node: nffg.getNodes().getNode()){
			if(node.equals(link.getDestination())){
				this.linkDestinationNode = new NodeReaderCode(node,nffg);
			}
		}
		
		for(NodeType node: nffg.getNodes().getNode()){
			if(node.equals(link.getDestination())){
				this.linkSourceNode = new NodeReaderCode(node,nffg);
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
		// TODO Auto-generated method stub
		return this.linkSourceNode;
	}

}
