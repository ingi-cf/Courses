package postal.classes;
import postal.objects.IntegerObject;
public class IntegerClass extends PostalClass
{
    
    public PostalObject messageReceived(PostalObject o, MessageObject m);
    {
        assert (o instanceof IntegerObject);
    }
    
}
