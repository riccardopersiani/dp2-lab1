package it.polito.dp2.NFFG.sol1;

import java.util.HashSet;
import java.util.Set;

import it.polito.dp2.NFFG.FunctionalType;
import it.polito.dp2.NFFG.LinkReader;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.sol1.jaxb.LinkType;
import it.polito.dp2.NFFG.sol1.jaxb.NFFGType;
import it.polito.dp2.NFFG.sol1.jaxb.NodeType;

import it.polito.dp2.NFFG.sol1.util.Util;

public class NodeReaderCode extends NamedEntityReaderCode implements NodeReader{
	private FunctionalType nodeFunctionalType;
	private Set<LinkReader> nodeLinksList;
	
	public NodeReaderCode(NodeType node, NFFGType nffg){
		super(node.getId());
		
		this.nodeFunctionalType = Util.covertServiceToFunctional(node.getService());
		LinkReader linkReader = null;
		
		nodeLinksList = new HashSet<LinkReader>();

		// Setting link only if the actual node is the source
		for(LinkType link: nffg.getLinks().getLink()){
			if(link.getSource().equals(node.getId())){
				linkReader = new LinkReaderCode(nffg, link);
				((LinkReaderCode) linkReader).setSourceNode(this);
				nodeLinksList.add(linkReader);
			}
		}
		System.out.println(this.toString());
	}

	@Override
	public FunctionalType getFuncType() {
		return this.nodeFunctionalType;
	}

	@Override
	public Set<LinkReader> getLinks() {
		return nodeLinksList;
	}
	
	public String toString(){
		return "NodeReader -> Name: "+this.getName()+" - Function: "+this.nodeFunctionalType.toString();
	}
}
