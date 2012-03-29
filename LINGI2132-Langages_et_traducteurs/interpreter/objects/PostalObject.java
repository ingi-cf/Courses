package postal.objects;
import postal.node.ElementNode;
public class PostalObject implements ElementNode
{
    PostalObject()
    {
        
    }

    PostalObject resolve()
    {
        return this;
    }
    
    public abstract PostalObject resolve();
}
