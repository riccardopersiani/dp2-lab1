package it.polito.dp2.NFFG.sol1;

import java.util.Set;

import it.polito.dp2.NFFG.FunctionalType;
import it.polito.dp2.NFFG.LinkReader;
import it.polito.dp2.NFFG.NodeReader;

public class NodeReaderUncomplete extends NamedEntityReaderCode implements NodeReader{
	private String nodeName;
	
	public NodeReaderUncomplete(String nameNode){
		super(nameNode);
		this.nodeName = nameNode;
	}
	
	public String getName() {
		return this.nodeName;
	}

	public FunctionalType getFuncType() {
		return null;
	}

	@Override
	public Set<LinkReader> getLinks() {
		return null;
	}	
}
