package postal.ast;
import postal.objects.PostalObject;
import java.util.LinkedList;

public class SendNode extends PostalNode
{
    String                      messagename;
    LinkedList<PostalObject>    parameters;
    SendNode(String messagename)
    {
        this.messagename = messagename;
        parameters = new LinkedList<PostalObject>();
    }
    public void addParameter(PostalObject o)
    {
        parameters.add(o);
    }
    public PostalObject execute()
    {
        return null;
    }
}
