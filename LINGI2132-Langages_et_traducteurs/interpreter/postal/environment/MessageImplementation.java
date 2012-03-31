package postal.environment;

import java.util.LinkedList;

import postal.ast.SequenceNode;

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
