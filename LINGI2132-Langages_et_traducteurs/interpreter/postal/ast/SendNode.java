package postal.ast;
import postal.environment.PostalEnvironment;
import postal.objects.MessageObject;
import postal.objects.PostalObject;

public class SendNode extends PostalNode implements ElementNode
{
    
    private ElementNode message;
	private ElementNode dst;

	SendNode(ElementNode dst, ElementNode m)
    {
		this.dst = dst;
        this.message = m;
    }

    public PostalObject execute(PostalEnvironment e)
    {
		return resolve(e);
    }

	
	public PostalObject resolve(PostalEnvironment e) {
		return dst.resolve(e).getPostalClass().messageReceived(dst.resolve(e),message.resolve(e));
	}
}
