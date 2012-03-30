
package postal.ast;


import postal.objects.BooleanObject;
import postal.objects.PostalObject;
import postal.environment.PostalEnvironment;
import postal.exceptions.TypeException;

public class IfNode extends PostalNode
{
    ElementNode   condition;
    SequenceNode  body;
    SequenceNode  elsebody;
    public IfNode(ElementNode e, SequenceNode s)
    {   
        this.condition = e;
        this.body = s;
    }
    public IfNode(ElementNode e, SequenceNode s, SequenceNode selse)
    {   
        this.condition = e;
        this.body = s;
        this.elsebody = selse;
    }
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
