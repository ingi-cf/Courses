package postal.classes;

import java.util.Hashtable;

import postal.ast.SequenceNode;
import postal.environment.PostalEnvironment;
import postal.exceptions.MessageDefinitionException;
import postal.objects.MessageObject;
import postal.objects.PostalObject;

public class UserDefinedClass extends PostalClass {

	PostalEnvironment e; //the environment in wich the class is defined
	Hashtable<String,SequenceNode> messagesImplementations;
	
	private String name;
	
	UserDefinedClass(String name)
	{
		this.name = name;
	}
	
	public PostalObject messageReceived(PostalObject o, MessageObject m) {
		//create the environment
		PostalEnvironment e = new PostalEnvironment();
		SequenceNode impl = getMessageImplementation(m.name());
		
		//TODO add self super # and params values in the environment
		
		
		//try prim class
		if(impl == null)
		{
			PostalClass p = getRootClass();
			if (!(p instanceof UserDefinedClass))
				return p.messageReceived(o, m);
				
				
			if(impl == null && (postalSuper == null || !(postalSuper instanceof UserDefinedClass)))
				throw new MessageDefinitionException("Cannot find message implementation : " + m.name() + " in class " + this.name); 

		} else
			return impl.execute(e);
			
		
		
		return null;
		
	}
	

	private SequenceNode getMessageImplementation(String messageName) {
		SequenceNode impl = messagesImplementations.get(messageName);
		if(impl == null && postalSuper != null && postalSuper instanceof UserDefinedClass)
			impl = ((UserDefinedClass) postalSuper).getMessageImplementation(messageName);
		
		return impl;
		
	}

}
