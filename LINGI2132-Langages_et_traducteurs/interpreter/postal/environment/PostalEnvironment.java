package postal.environment;

import java.util.Hashtable;

import postal.classes.PostalClass;
import postal.objects.PostalObject;

public class PostalEnvironment {
	
	Hashtable<String,PostalObject> vc;
	Hashtable<String,PostalClass> cc;

	public PostalObject getVariable(String identifier) 
	{
		return vc.get(identifier);
	}
	
	public void setVariable(String identifier, PostalObject element) 
	{
		vc.put(identifier, element);
	}
	
	public PostalObject newObject(String className, PostalObject newMessage)
	{
		return cc.get(className).messageReceived((PostalObject)null, newMessage);
	}

	public void insertClass(PostalClass c) {
		cc.put(c.getName(), c);
	}

}
