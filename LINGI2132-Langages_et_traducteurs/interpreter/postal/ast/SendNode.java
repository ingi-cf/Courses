package postal.ast;
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

    public PostalObject execute()
    {
		return src.getPostalClass().messageReceived(src,message);
    }
}
