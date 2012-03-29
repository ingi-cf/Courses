package postal.classes;

import postal.environment.PostalEnvironment;
import postal.exceptions.TypeException;
import postal.objects.BooleanObject;
import postal.objects.MessageObject;
import postal.objects.PostalObject;

public class BooleanClass extends PostalClass
{
    public PostalObject messageReceived(PostalObject o, MessageObject m)
    {
    	assert(o instanceof BooleanObject);
    	BooleanObject o1 = (BooleanObject) o;
        // UNARY OPERATOR
        if(m.name().equals("not"))
        {
            return new BooleanObject(!o1.booleanValue());
        }
        // BINARY OPERATOR
        else
        {
            PostalObject o2 = m.param(0);
            if(m.name().equals("and"))
            {
                if(o2 instanceof BooleanObject)
                {
                   return new BooleanObject(o1.booleanValue() && ((BooleanObject) o2).booleanValue());
                }
                else 
                {
                    throw new TypeException("Trying to do a binary operation (and) with a no boolean.");
                }
            }
            else if(m.name().equals("or"))
            {
                if(o2 instanceof BooleanObject)
                {
                   return new BooleanObject(o1.booleanValue() || ((BooleanObject) o2).booleanValue());
                }
                else 
                {
                    throw new TypeException("Trying to do a binary operation (or) with a no boolean.");
                }
            }
            else
            {
                throw new TypeException("BooleanClass: Message not known.");
            }
        }
    }
}
