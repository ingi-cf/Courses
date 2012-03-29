
package postal.ast;
import java.util.LinkedList;

import postal.objects.BooleanObject;
import postal.exceptions.TypeException;

public class IfNode
{
    ElementNode   condition;
    SequenceNode  body;
    IfNode(PostalElement e, SequenceNode s)
    {   
        this.e = e;
        this.s = s;
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
