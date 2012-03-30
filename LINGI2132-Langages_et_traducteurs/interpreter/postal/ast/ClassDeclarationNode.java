package postal.ast;

import java.util.Hashtable;
import java.util.LinkedList;

import postal.classes.UserDefinedClass;
import postal.environment.MessageImplementation;
import postal.environment.PostalEnvironment;

public class ClassDeclarationNode extends PostalNode {

	private String className;
	Hashtable<String,MessageImplementation> messagesImplementations;
	LinkedList<String> attributes;
	
	public ElementNode execute(PostalEnvironment e,String className) {
		this.className = className;
		return null;
	}
	public void addArgument(String argumentName)
	{
		attributes.add(argumentName);
	}
	
	public void addMessageImplementation(MessageImplementation mi)
	{
		
	}
	
	public ElementNode execute(PostalEnvironment e) {
		UserDefinedClass c = new UserDefinedClass(className);
		
		return null;
	}

}
