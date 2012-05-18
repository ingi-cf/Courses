package postal.environment;

import java.util.Hashtable;
import java.util.Iterator;

import postal.classes.PostalClass;
import postal.exceptions.AccessException;
import postal.objects.PostalObject;
import postal.objects.StdioObject;

/*
 * Represents the environment to execute postal nodes
 */
public class PostalEnvironment {
	
	//variables
	Hashtable<String,PostalObject> vc;
	//classes
	Hashtable<String,PostalClass> cc;
	
	/*
	 * build a new environment
	 */
	public PostalEnvironment()
	{
		vc = new Hashtable<String,PostalObject>();
		cc = new Hashtable<String,PostalClass>();
		
		//add a stdio object to the environment to send messages to print
		setVariable("stdio", new StdioObject()); 
	}

	public PostalObject getVariable(String identifier) 
	{
		if (vc.get(identifier) == null)
			throw new AccessException("trying to access " + identifier + " but not found");
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
		if (cn != null)
			return cc.get(cn);
		else
			return null;
	}
	
	public String toString()
	{
		String str= "Values : \n";
		Iterator<String> itr = vc.keySet().iterator();
		String identifier;
		while(itr.hasNext())
		{
			identifier = itr.next();
			str+= identifier + " : " + vc.get(identifier)+ "\n";
		}
			
		
		str+= "Classes : \n";
		itr = cc.keySet().iterator();
		while(itr.hasNext())
		{
			identifier = itr.next();
			str+= identifier + " : " + cc.get(identifier)+ "\n";
		}
		return str;
		
	}
}
