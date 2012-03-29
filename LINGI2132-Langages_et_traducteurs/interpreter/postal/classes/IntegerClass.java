package postal.classes;
import postal.objects.IntegerObject;
import postal.objects.MessageObject;
import postal.objects.PostalObject;
public class IntegerClass extends PostalClass
{
    
    public PostalObject messageReceived(PostalObject o, MessageObject m)
    {
        assert (o instanceof IntegerObject);
		return null;
    }
    
}
