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

/*
 * class with the implementation of messages for user defined classes
 */
public class UserDefinedClass extends PostalClass {

	PostalEnvironment e; //the environment in wich the class is defined
	Hashtable<String,MessageImplementation> messagesImplementations;
	LinkedList<String> attributes;
	
	
	public UserDefinedClass(String className,
			Hashtable<String, MessageImplementation> messagesImplementations,
			LinkedList<String> attributes,
			PostalClass superClass) {
		super.name = name;
		super.superClass = superClass;
		this.messagesImplementations = messagesImplementations;
		this.attributes = attributes;
	}

	public PostalObject messageReceived(PostalObject o, MessageObject m) {
		//if the message is new
		if(o == null && m.getName().equals("new"))
		{
			return messageReceived(postalNew(), m);
		}
		
		//find the message implementation 
		MessageImplementation impl= getMessageImplementation(m.getName());
		
		
	
		if(impl == null)
		{
			
			PostalClass p = getRootClass();
			if (!(p instanceof UserDefinedClass))
				return p.messageReceived(o, m);
				
				
			if(impl == null && (superClass == null || !(superClass instanceof UserDefinedClass)))
				throw new MessageDefinitionException("Cannot find message implementation : " + m.getName() + " in class " + this.name); 

		} else
		{
			//create the environment
			PostalEnvironment e = new PostalEnvironment();
			
			// add self super # and params values in the environment
			e.setVariable("self", o);
			e.setVariable("super", o.getSuperObject());
			e.setVariable("#",m);
			//add params
			if (impl.getParametersIdentifiers().size() != m.getParameters().size())
				
				throw new MessageDefinitionException("The message " +m.getName() + " take " +
						impl.getParametersIdentifiers().size() + "parameters but "			
						+  m.getParameters().size() + "given");
			else
			{
				for(int i =0; i<m.getParameters().size() ;i++)
					e.setVariable(impl.getParametersIdentifiers().get(i)
							,m.param(i));
			}
			
			return impl.getBody().execute(e);
		}	
		
		
		return null;
		
	}
	

	private MessageImplementation getMessageImplementation(String messageName) {
		MessageImplementation impl = messagesImplementations.get(messageName);
		if(impl == null && superClass != null && superClass instanceof UserDefinedClass)
			impl = ((UserDefinedClass) superClass).getMessageImplementation(messageName);
		
		return impl;
		
	}
	
	public PostalClass getSuperClass() {
		return superClass;
	}

	public PostalObject postalNew() {
		//create the environment with the attributes
		PostalEnvironment e = new PostalEnvironment();
		//fill the environment with defined attributes
		ListIterator<String> itr = attributes.listIterator();
		while(itr.hasNext())
			e.setVariable(itr.next(), null);
		return new UserDefinedObject(this, e);
		
	}


}
