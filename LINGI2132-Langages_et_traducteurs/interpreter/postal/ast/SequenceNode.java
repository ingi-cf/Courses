
package postal.ast;
import postal.environment.PostalEnvironment;
import postal.objects.PostalObject;
import java.util.LinkedList;
import java.util.ListIterator;
/*
 * Node representing a sequence of nodes
 */
public class SequenceNode extends ElementNode
{
    LinkedList<PostalNode>    statements;
    public SequenceNode()
    {
        statements = new LinkedList<PostalNode>();
    }
    public SequenceNode(LinkedList<PostalNode> s)
    {
        statements = s;
    }
    public void addStatement(PostalNode statement)
    {
        statements.add(statement);
    }
    /*
     * Execute every node in the sequence sequentialy
     */
    public PostalObject execute(PostalEnvironment e)
    {
        ListIterator<PostalNode> itr = statements.listIterator();
        while(itr.hasNext())
        {
        	itr.next().execute(e);
        }
        return null;
    }
    
    public String toString()
    {
    	String s="";
    	s+="[(Sequence Node) : ";
    	ListIterator<PostalNode> itr = statements.listIterator();
        while(itr.hasNext())
        {
        	s+=itr.next().toString();
        }
        s+="]";
    	return s;
    }
	public PostalObject resolve(PostalEnvironment e) {
        ListIterator<PostalNode> itr = statements.listIterator();
        PostalNode ret = null;
        while(itr.hasNext())
        {
        	ret = itr.next().execute(e);
        }
        if(ret != null )
        	return ((PostalObject) ret).resolve(e);
        else
        	return null;
	}
}
