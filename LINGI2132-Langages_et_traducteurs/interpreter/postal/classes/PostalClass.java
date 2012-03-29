package postal.classes;
import postal.objects.MessageObject;
import postal.objects.PostalObject;
public abstract class PostalClass
{       
    PostalClass()
    {
        
    }

    public abstract PostalObject messageReceived(PostalObject o, MessageObject m);
}
