package postal.ast;
import java.util.ListIterator;

import postal.ast.ElementNode;
import postal.environment.PostalEnvironment;
import postal.objects.MessageObject;
import postal.objects.PostalObject;

/*
 * Node representing a message sending
 */
public class SendNode extends ElementNode
{
    
    private ElementNode message;
	private ElementNode dst;

	/*
	 * build node tosend the message evaluated from m to the evaluation of dst
	 */
	public SendNode(ElementNode dst, ElementNode m)
    {
		this.dst = dst;
        this.message = m;
    }

	/*
	 * Execute the message sending
	 */
    public PostalObject execute(PostalEnvironment e)
    {
		return resolve(e);
    }

	/*
	 * Return the results from the message
	 */
	public PostalObject resolve(PostalEnvironment e) {
		PostalObject resolvedDst = dst.resolve(e);
		PostalObject ret = resolvedDst.getPostalClass().messageReceived(resolvedDst,message.resolve(e));
		//System.out.println("Send message " + message.toString() + " received " + ret);
		return ret;
	}
	
    public String toString()
    {
    	String s="";
    	s+="[(Send Node) : ";
    	s+= dst.toString();
    	s+= " ";
    	s+= message.toString();
        s+="]";
    	return s;
    }
}
