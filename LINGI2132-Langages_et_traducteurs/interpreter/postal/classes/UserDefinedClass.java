package postal.classes;

import java.util.Hashtable;
import java.util.Iterator;
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
		super.name = className;
		super.superClass = superClass;
		this.messagesImplementations = messagesImplementations;
		this.attributes = attributes;
	}

	public PostalObject messageReceived(PostalObject o, MessageObject m) {
		//if the message is new
		if(o == null && m.getName().equals("new"))
		{
			//return messageReceived(postalNew(), m);
			//TODO because of that cannot add a special new
			return postalNew();
		}
		System.out.println("received message " +m);
		System.out.println("environment is" + o.getEnvironment());
		
		//find the message implementation 
		MessageImplementation impl= getMessageImplementation(m.getName());
		
		
	
		if(impl == null)
		{
			
			PostalClass p = getRootClass();
			if (!(p instanceof UserDefinedClass))
			{
				return p.messageReceived(o, m);
			}
				
			if(impl == null && (superClass == null || !(superClass instanceof UserDefinedClass)))
			{
				System.out.println(messagesImplementations.keySet());
				throw new MessageDefinitionException("Cannot find message implementation : " + m.getName() + " in class " + this.name); 
			}
		} else
		{
			//create the environment
			PostalEnvironment e = new PostalEnvironment();
			
			// add self super # and params values in the environment
			e.setVariable("self", o);
			if(o.getSuperObject() != null)
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
			if(m.getName().equals("new"))
			{
				impl.getBody().execute(e);
				return o;
			} else
			{
				System.out.println("E before execution f the body of " + impl.getName() + " - " + e);
				return impl.getBody().execute(e);
			}
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
			//cannot add null in a hashtable
			e.setVariable(itr.next(), new PostalObject(null));
		System.out.println("Received new on class "+this.name);
		System.out.println(e);
		return new UserDefinedObject(this, e);
		
	}

	public String toString()
	{
		String str= "Classe : " + name + "\n";
		str+= "Attributes : ";
		ListIterator<String> itr = attributes.listIterator();
		while(itr.hasNext()) str += itr.next() + " ";
		str+="\n";
		
		str+= "MessagesImplementations("+ messagesImplementations.size()+") : ";
		Iterator<String> itrm = messagesImplementations.keySet().iterator();
		while(itrm.hasNext()) str+= itrm.next() + " ";
		return str;
	}

}
 