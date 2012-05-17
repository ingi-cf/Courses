package postal.environment;

import java.util.LinkedList;

import postal.ast.ElementNode;
import postal.ast.SequenceNode;

/*
 * used to store the implementations for user defined classes in the environment
 */
public class MessageImplementation {
	String 				messageName;
	SequenceNode		body;
	LinkedList<String>	parametersIdentifier;
	
	public MessageImplementation(String name, SequenceNode body)
	{
		this.body = body;
		this.messageName = name;
		parametersIdentifier = new LinkedList<String>();
	}
	
	public MessageImplementation(String name, LinkedList<String> parametersIdentifiers,
			SequenceNode body) {
		this.body = body;
		this.messageName = name;
		this.parametersIdentifier = parametersIdentifiers;
	}

	public void addParameter(String identifier)
	{
		parametersIdentifier.add(identifier);
	}
	
	public SequenceNode getBody()
	{
		return body;
	}

	public String getName() {
		return messageName;
	}

	public LinkedList<String> getParametersIdentifiers() {
		return parametersIdentifier;
	}
	
	
}
