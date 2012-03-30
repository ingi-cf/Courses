package postal.classes;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.ListIterator;

import postal.ast.SequenceNode;
import postal.environment.MessageImplementation;
import postal.environment.PostalEnvironment;
import postal.exceptions.MessageDefinitionException;
import postal.objects.MessageObject;
import postal.objects.PostalObject;
import postal.objects.UserDefinedObject;

public class UserDefinedClass extends PostalClass {

	PostalEnvironment e; //the environment in wich the class is defined
	Hashtable<String,MessageImplementation> messagesImplementations;
	LinkedList<String> attributes;
	
	
	public UserDefinedClass(String className,
			Hashtable<String, MessageImplementation> messagesImplementations,
			LinkedList<String> attributes) {
		super.name = name;
		this.messagesImplementations = messagesImplementations;
		this.attributes = attributes;
	}

	public PostalObject messageReceived(PostalObject o, MessageObject m) {
		//if the message is new
		if(o == null && m.name().equals("new"))
		{
			//create the environment with the attributes
			PostalEnvironment e = new PostalEnvironment();
			//fill the environment with defined attributes
			ListIterator<String> itr = attributes.listIterator();
			while(itr.hasNext())
				e.setVariable(itr.next(), null);
			return messageReceived(new UserDefinedObject(this, e), m);
		}
		//create the environment
		PostalEnvironment e = new PostalEnvironment();
		MessageImplementation impl = getMessageImplementation(m.name());
		
		//TODO add self super # and params values in the environment
		
	
		if(impl == null)
		{
			PostalClass p = getRootClass();
			if (!(p instanceof UserDefinedClass))
				return p.messageReceived(o, m);
				
				
			if(impl == null && (postalSuper == null || !(postalSuper instanceof UserDefinedClass)))
				throw new MessageDefinitionException("Cannot find message implementation : " + m.name() + " in class " + this.name); 

		} else
			return impl.getBody().execute(e);
			
		
		
		return null;
		
	}
	

	private MessageImplementation getMessageImplementation(String messageName) {
		MessageImplementation impl = messagesImplementations.get(messageName);
		if(impl == null && postalSuper != null && postalSuper instanceof UserDefinedClass)
			impl = ((UserDefinedClass) postalSuper).getMessageImplementation(messageName);
		
		return impl;
		
	}

}
