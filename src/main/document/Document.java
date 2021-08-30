package main.java.document;

import java.util.Map;

public class Document {
	private final Map<String, String> attributes;
	
	Document(final Map<String, String> attributes) {
		// TODO Auto-generated constructor stub
		this.attributes = attributes;
	}
	
	public String getAttribute(final String attributeName){
		return attributes.get(attributeName);
	}
}
