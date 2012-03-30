
package postal.objects;
import java.util.LinkedList;

import postal.classes.PostalClass;

public class MessageObject extends PostalObject
{
    String                      messagename;
    LinkedList<PostalObject>    parameters;
    public MessageObject(String messagename)
    {
    	super(null);
        this.messagename = messagename;
        parameters = new LinkedList<PostalObject>();
    }
    /*
     * Simplified constructor for binary operations messages
     */
    public MessageObject(String messagename, PostalObject o)
    {
    	super(null);
        this.messagename = messagename;
        parameters = new LinkedList<PostalObject>();
        addParameter(o);
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
