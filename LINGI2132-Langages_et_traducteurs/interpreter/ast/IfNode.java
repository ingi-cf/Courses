
package postal.ast;
import java.util.LinkedList;

import postal.objects.BooleanObject;
import postal.exceptions.TypeException;

public class IfNode extends PostalNode
{
    ElementNode   condition;
    SequenceNode  body;
    IfNode(PostalElement e, SequenceNode s)
    {   
        this.condition = e;
        this.body = s;
    }
    public PostalObject execute()
    {
        PostalObject o = condition.resolve();

        if(o instanceof BooleanObject)
        {
            body.execute();
        } else throw new TypeException("Error, the condition is not a boolean");
    }
}
