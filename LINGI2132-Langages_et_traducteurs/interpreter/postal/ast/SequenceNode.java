
package postal.ast;
import postal.environment.PostalEnvironment;
import postal.objects.PostalObject;
import java.util.LinkedList;
import java.util.ListIterator;

public class SequenceNode extends PostalNode
{
    LinkedList<PostalNode>    statements;
    public SequenceNode()
    {
        statements = new LinkedList<PostalNode>();
    }
    public void addStatement(PostalNode statement)
    {
        statements.add(statement);
    }
    public PostalObject execute(PostalEnvironment e)
    {
        ListIterator<PostalNode> itr = statements.listIterator();
        while(itr.hasNext())
            itr.next().execute(e);
        return null;
    }
}
