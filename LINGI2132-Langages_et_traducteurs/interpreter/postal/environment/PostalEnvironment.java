package postal.environment;

import postal.ast.ElementNode;
import postal.classes.UserDefinedClass;
import postal.objects.MessageObject;
import postal.objects.PostalObject;

public class PostalEnvironment {
	
	VariablesContainer vc;
	ClassesContainer cc;

	public PostalObject getVariable(String identifier) {
		return null;
	}
	
	public void setVariable(String identifier, ElementNode element) {
		// TODO Auto-generated method stub
		
	}
	
	public PostalObject newObject(String className, PostalObject newMessage)
	{
		return cc.getClasse(className).messageReceived((PostalObject)null, newMessage);
	}

	public void insertClass(UserDefinedClass c) {
		// TODO Auto-generated method stub
		
	}

}
