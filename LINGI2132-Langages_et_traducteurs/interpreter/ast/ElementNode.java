package postal.ast;
import postal.objects.PostalObject;
public interface ElementNode extends PostalNode
{
    public PostalObject resolve()
    {
        return null;
    }
}
