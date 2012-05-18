package postal.ast;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import postal.classes.UserDefinedClass;
import postal.environment.MessageImplementation;
import postal.environment.PostalEnvironment;

/*
 * represents a class definition
 */
public class ClassDeclarationNode extends PostalNode {

	private String className;
	Hashtable<String,MessageImplementation> messagesImplementations;
	LinkedList<String> attributes;
	private String superClassName;
	
	
	/*
	 * create node with a class name and a super class name
	 */
	public ClassDeclarationNode(String className, String superClassName) {
		this.className = className;
		this.superClassName = superClassName;
		this.messagesImplementations = new Hashtable<String,MessageImplementation>();
		this.attributes = new LinkedList<String>();
	}
	public ClassDeclarationNode(LinkedList<String> attributes,
			Hashtable<String, MessageImplementation> messagesImplementations) {
		this.messagesImplementations = messagesImplementations;
		this.attributes = attributes;
	}
	/*
	 * declare a class variable
	 */	
	public void addArgument(String argumentName)
	{
		attributes.add(argumentName);
	}
	
	/*
	 * declare a class message implementation
	 */
	public void addMessageImplementation(MessageImplementation mi)
	{
		
		messagesImplementations.put(mi.getName(), mi);
	}
	
	/*
	 * Effectively adds the class to environment e
	 */
	public ElementNode execute(PostalEnvironment e) {
		UserDefinedClass c = new UserDefinedClass(className, messagesImplementations, attributes,e.getClass(superClassName));
		e.insertClass(c);
		return null;
	}
	public void setExtends(String x) {
		this.superClassName=x;
		
	}
	public void setName(String x) {
		this.className=x;
		
	}

    public String toString()
    {
    	String s="";
    	s+="[(Class Declaratoin Node) : ";
    	s+=className;
    	s+=" extends \"" + superClassName +"\" ";
    	s+="attributes : ";
    	ListIterator<String> itr = attributes.listIterator();
        while(itr.hasNext())
        	s += itr.next() + " ";
        s+="messages : ";
        Iterator<String> itr2 = messagesImplementations.keySet().iterator();
		while(itr2.hasNext())
		{
			s+=messagesImplementations.get(itr2.next()).toString();
		}

        s+="]";
    	return s;
    }
}
