package postal.environment;

import java.util.Hashtable;
import java.util.Iterator;

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
