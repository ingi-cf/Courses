
package postal.objects;
import java.util.LinkedList;
import java.util.ListIterator;

import postal.ast.ElementNode;
import postal.ast.PostalNode;
import postal.classes.PostalClass;
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
    /*
     * siplified constructor for binary operations
     */
    public MessageObject(String messagename, ElementNode o)
    {
    	super(null);
        this.messagename = messagename;
        parameters = new LinkedList<ElementNode>();
        addParameter(o);
    }

    public void addParameter(ElementNode o)
    {
        parameters.add(o);
    }
	public String name() {
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
}
