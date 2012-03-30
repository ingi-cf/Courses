
package postal.ast;


import postal.objects.BooleanObject;
import postal.objects.PostalObject;
import postal.environment.PostalEnvironment;
import postal.exceptions.TypeException;

public class WhileNode extends PostalNode
{
    ElementNode   condition;
    SequenceNode  body;
    WhileNode(ElementNode e, SequenceNode s)
    {   
        this.condition = e;
        this.body = s;
    }
    public PostalObject execute(PostalEnvironment e)
    {
        PostalObject o = condition.resolve(e);
        if(!(o instanceof BooleanObject))
        	throw new TypeException("Error, the condition is not a boolean");

        
    	while(((BooleanObject)o).booleanValue())
    	{
        	body.execute(e);
        	o = condition.resolve(e);
            if(!(o instanceof BooleanObject))
            	throw new TypeException("Error, the condition is not a boolean");
    	}
        	

        return null;
    }
}
