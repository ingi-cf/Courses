package postal.classes;
import postal.objects.PostalObject;
public class PostalClass
{       
    PostalClass()
    {
        
    }

    public abstract PostalObject messageReceived(PostalObject o, MessageObject m);
}
