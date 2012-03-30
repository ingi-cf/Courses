package postal.classes;
import postal.ast.ElementNode;
import postal.environment.PostalEnvironment;
import postal.exceptions.TypeException;
import postal.objects.MessageObject;
import postal.objects.PostalObject;
public abstract class PostalClass
{       
	String name;
	protected PostalClass postalSuper;
    PostalClass()
    {
        
    }

	protected PostalClass getRootClass() {
		if(postalSuper == null)
			return this;
		else
			return postalSuper.getRootClass();
	}
    
    public abstract PostalObject messageReceived(PostalObject o, MessageObject mess);
    
    public PostalObject messageReceived(PostalObject o, PostalObject mess)
    {
    	if(mess instanceof MessageObject)
    		return messageReceived(o, (MessageObject) mess);
    	else
    		throw new TypeException("Trying to send an object that is not a message");
    		
    }

	public String getName() {
		return name;
	}

}
