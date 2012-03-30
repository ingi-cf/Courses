package postal.environment;

import postal.ast.ElementNode;
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
	
	public PostalObject newObject(String className, MessageObject newMessage)
	{
		return cc.getClasse(className).messageReceived(null, newMessage);
	}

}
