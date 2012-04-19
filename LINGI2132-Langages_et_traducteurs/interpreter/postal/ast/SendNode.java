package postal.ast;
import postal.ast.ElementNode;
import postal.environment.PostalEnvironment;
import postal.objects.MessageObject;
import postal.objects.PostalObject;

/*
 * Node representing a message sending
 */
public class SendNode extends PostalNode implements ElementNode
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
		return resolvedDst.getPostalClass().messageReceived(resolvedDst,message.resolve(e));
	}
}
