package postal.environment;

import java.util.Hashtable;

import postal.classes.PostalClass;
import postal.objects.PostalObject;
import postal.objects.StdioObject;

public class PostalEnvironment {
	
	Hashtable<String,PostalObject> vc;
	Hashtable<String,PostalClass> cc;
	
	public PostalEnvironment()
	{
		vc = new Hashtable<String,PostalObject>();
		cc = new Hashtable<String,PostalClass>();
		
		setVariable("stdio", new StdioObject()); 
	}

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
	
	public PostalClass getClass(String cn) {
		return cc.get(cn);
	}

}
