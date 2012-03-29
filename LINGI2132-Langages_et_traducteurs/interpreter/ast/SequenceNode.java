
package postal.ast;
import postal.objects.PostalObject;
import java.util.LinkedList;
import java.util.ListIterator;

public class SequenceNode
{
    LinkedList<PostalNode>    statements;
    SequenceNode()
    {
        statements = new LinkedList<PostalObject>();
    }
    public void addStatement(PostalNode statement)
    {
        statements.add(statement);
    }
    public ElementNode execute()
    {
        itr = statements.listIterator();
        while(itr.hasNext())
            itr.next().execute();
        return null;
    }
}
