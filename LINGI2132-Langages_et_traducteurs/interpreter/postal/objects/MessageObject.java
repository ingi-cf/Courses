
package postal.objects;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.ListIterator;

import postal.ast.ElementNode;
import postal.ast.PostalNode;
import postal.classes.PostalClass;
import postal.environment.MessageImplementation;
import postal.environment.PostalEnvironment;

/*
 * represents a message
 */
public class MessageObject extends PostalObject
{
    String                      messagename;
    LinkedList<ElementNode>    parameters;
    
    /*
     * create a message with messagename as name and no parameter
     */
    public MessageObject(String messagename)
    {
    	super(null);
        this.messagename = messagename;
        parameters = new LinkedList<ElementNode>();
    }
    /*
     * simplified constructor for binary operations :
     * create a message with messagename as name and o as parameter
     */
    public MessageObject(String messagename, ElementNode o)
    {
    	super(null);
        this.messagename = messagename;
        parameters = new LinkedList<ElementNode>();
        addParameter(o);
    }

    /*
     * add o to the message parameters
     */
    public void addParameter(ElementNode o)
    {
        parameters.add(o);
    }
    
    /*
     * get message name
     */
	public String getName() {
		return messagename;
	}
	
	/*
	 * get ith parameter
	 */
	public PostalObject param(int i)
	{
		return (PostalObject)parameters.get(i);
	}
	
	/*
	 * resolve the parameter to objects in the environment e
	 */
	public PostalObject resolve(PostalEnvironment e)
    {
		MessageObject resolvedmsg = new MessageObject(messagename);
		ListIterator<ElementNode> itr = parameters.listIterator();
        while(itr.hasNext())
        	resolvedmsg.addParameter(itr.next().resolve(e));
        return resolvedmsg;
    }
	
	/*
	 * string representation for printing
	 */
	public String toString()
	{
		
		String s="";
    	s+="[(Message Object) : ";
    	s += messagename;
		ListIterator<ElementNode> itr = parameters.listIterator();
        while(itr.hasNext())
        	s+= ", "+itr.next().toString();
        s+="]";
    	return s;

	}

	public LinkedList<ElementNode> getParameters() {
		// TODO Auto-generated method stub
		return parameters;
	}
	public void setParameters(LinkedList<ElementNode> p) {
		parameters = p;
		
	}
}
