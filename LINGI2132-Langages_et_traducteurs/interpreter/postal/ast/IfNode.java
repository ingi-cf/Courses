
package postal.ast;


import postal.objects.BooleanObject;
import postal.objects.PostalObject;
import postal.environment.PostalEnvironment;
import postal.exceptions.TypeException;

/*
 * node representing an if condition, the condition and the
 *  instructions from the body and the else body
 */
public class IfNode extends PostalNode
{
    ElementNode   condition;
    SequenceNode  body;
    SequenceNode  elsebody;
    
    /*
     * Create if node with no else block
     */
    public IfNode(ElementNode e, SequenceNode s)
    {   
        this.condition = e;
        this.body = s;
    }
    /*
     * Create if node with an else block
     */
    public IfNode(ElementNode e, SequenceNode s, SequenceNode selse)
    {   
        this.condition = e;
        this.body = s;
        this.elsebody = selse;
    }
    /*
     * Execute either the body block or the else block
     * depending on the evaluation of the condition in environment e
     */
    public PostalObject execute(PostalEnvironment e)
    {
        PostalObject o = condition.resolve(e);
        if(!(o instanceof BooleanObject))
        	throw new TypeException("Error, the condition is not a boolean");
        
    	if(((BooleanObject)o).booleanValue())
        	body.execute(e);
    	else if(elsebody != null)
			elsebody.execute(e);
		

        return null;
    }
}
