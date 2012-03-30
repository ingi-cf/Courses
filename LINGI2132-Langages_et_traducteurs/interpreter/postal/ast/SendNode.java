package postal.ast;
import postal.environment.PostalEnvironment;
import postal.objects.MessageObject;
import postal.objects.PostalObject;
import java.util.LinkedList;

public class SendNode extends PostalNode
{
    
    private MessageObject message;
	private PostalObject dst;
	SendNode(PostalObject dst, MessageObject m)
    {
		this.dst = dst;
        this.message = m;
    }

    public PostalObject execute(PostalEnvironment e)
    {
		return dst.getPostalClass().messageReceived(dst,message);
    }
}
