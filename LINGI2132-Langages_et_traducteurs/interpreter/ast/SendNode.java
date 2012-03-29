package postal.ast;
import postal.objects.PostalObject;
import java.util.LinkedList;

public class MessageNode
{
    String                      messagename;
    LinkedList<PostalObject>    parameters;
    MessageNode(String messagename)
    {
        this.messagename = messagename;
        parameters = new LinkedList<PostalObject>();
    }
    public void addParameter(PostalObject o)
    {
        parameters.add(o);
    }
}
