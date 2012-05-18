package postal.environment;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

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
		if (parametersIdentifiers != null)
			this.parametersIdentifier = parametersIdentifiers;
		else
			this.parametersIdentifier = new LinkedList<String>();
			
			
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
	
	public String toString()
    {
    	String s="";
    	s+="[(Message Implementation) : ";
    	s+="{"+messageName ;
		ListIterator<String> itr = parametersIdentifier.listIterator();
        while(itr.hasNext())
        	s += "," + itr.next();
        s+="}";
        s+= body.toString();
        s+="]";
    	return s;
    }
	
}
