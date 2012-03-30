package postal.ast;
import postal.ast.ElementNode;
import postal.environment.PostalEnvironment;
import postal.objects.MessageObject;
import postal.objects.PostalObject;

public class SendNode extends PostalNode implements ElementNode
{
    
    private ElementNode message;
	private ElementNode dst;

	public SendNode(ElementNode dst, ElementNode m)
    {
		this.dst = dst;
        this.message = m;
    }

    public PostalObject execute(PostalEnvironment e)
    {
		return resolve(e);
    }

	
	public PostalObject resolve(PostalEnvironment e) {
		PostalObject resolvedDst = dst.resolve(e);
		return resolvedDst.getPostalClass().messageReceived(resolvedDst,message.resolve(e));
	}
}
