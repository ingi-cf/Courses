package postal.classes;
import postal.objects.MessageObject;
import postal.objects.PostalObject;
public abstract class PostalClass
{       
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
    
    public abstract PostalObject messageReceived(PostalObject o, MessageObject m);
}
