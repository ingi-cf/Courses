package postal.ast;
import postal.environment.PostalEnvironment;
import postal.objects.MessageObject;
import postal.objects.PostalObject;
import java.util.LinkedList;

public class SendNode extends PostalNode
{
    
    private MessageObject message;
	private PostalObject src;
	SendNode(PostalObject src, MessageObject m)
    {
		this.src = src;
        this.message = m;
    }

    public PostalObject execute(PostalEnvironment e)
    {
		return src.getPostalClass().messageReceived(src,message);
    }
}
