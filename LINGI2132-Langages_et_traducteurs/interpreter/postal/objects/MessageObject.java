
package postal.objects;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.ListIterator;

import postal.ast.ElementNode;
import postal.ast.PostalNode;
import postal.classes.PostalClass;
import postal.environment.MessageImplementation;
import postal.environment.PostalEnvironment;

public class MessageObject extends PostalObject
{
    String                      messagename;
    LinkedList<ElementNode>    parameters;
    public MessageObject(String messagename)
    {
    	super(null);
        this.messagename = messagename;
        parameters = new LinkedList<ElementNode>();
    }

    public void addParameter(ElementNode o)
    {
        parameters.add(o);
    }
	public String getName() {
		return messagename;
	}
	
	public PostalObject param(int i)
	{
		return (PostalObject)parameters.get(i);
	}
	
	public PostalObject resolve(PostalEnvironment e)
    {
		MessageObject resolvedmsg = new MessageObject(messagename);
		ListIterator<ElementNode> itr = parameters.listIterator();
        while(itr.hasNext())
        	resolvedmsg.addParameter(itr.next().resolve(e));
        return resolvedmsg;
    }
	
	public String toString()
	{
		String ret = messagename;
		ListIterator<ElementNode> itr = parameters.listIterator();
        while(itr.hasNext())
        	ret = ret+" , "+itr.next().toString();
        return ret;
	}

	public LinkedList<ElementNode> getParameters() {
		// TODO Auto-generated method stub
		return parameters;
	}
}
