package postal.ast;
import postal.environment.PostalEnvironment;
import postal.objects.MessageObject;
import postal.objects.PostalObject;
import java.util.LinkedList;

public class SendNode extends PostalNode implements ElementNode
{
    
    private MessageObject message;
	private ElementNode src;

	SendNode(ElementNode src, MessageObject m)
    {
		this.src = src;
        this.message = m;
    }

    public PostalObject execute(PostalEnvironment e)
    {
		return resolve(e);
    }

	
	public PostalObject resolve(PostalEnvironment e) {
		return src.resolve(e).getPostalClass().messageReceived(src.resolve(e),message);
	}
}
