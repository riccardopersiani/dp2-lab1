package it.polito.dp2.NFFG.sol1;

import it.polito.dp2.NFFG.NamedEntityReader;

public class NamedEntityReaderCode implements NamedEntityReader {
	
	private String entityName;
	
	//TODO
	
	public NamedEntityReaderCode(String name) {
		this.entityName = name;
	}
	
	@Override
	public String getName() {
		return entityName;
	}


}
