package postal.ast;

import postal.classes.UserDefinedClass;
import postal.environment.MessageImplementation;
import postal.environment.PostalEnvironment;

public class ClassDeclarationNode extends PostalNode {

	private String className;

	public ElementNode execute(PostalEnvironment e,String className) {
		this.className = className;
		return null;
	}
	public void addArgument(String argumentName)
	{
		
	}
	
	public void addMessageImplementation(MessageImplementation mi)
	{
		
	}
	
	public ElementNode execute(PostalEnvironment e) {
		UserDefinedClass c = new UserDefinedClass(className);
		
		return null;
	}

}
