
package postal.objects;
import java.util.LinkedList;

import postal.classes.PostalClass;

public class MessageObject extends PostalObject
{
    String                      messagename;
    LinkedList<PostalObject>    parameters;
    MessageObject(String messagename, PostalClass c)
    {
    	super(c);
        this.messagename = messagename;
        parameters = new LinkedList<PostalObject>();
    }
    public void addParameter(PostalObject o)
    {
        parameters.add(o);
    }
	public String name() {
		return messagename;
	}
	
	public PostalObject param(int i)
	{
		return parameters.get(i);
	}
}
