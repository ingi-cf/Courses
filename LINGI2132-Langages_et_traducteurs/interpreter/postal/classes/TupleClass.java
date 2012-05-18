package postal.classes;

import postal.exceptions.TypeException;
import postal.objects.BooleanObject;
import postal.objects.IntegerObject;
import postal.objects.MessageObject;
import postal.objects.PostalObject;
import postal.objects.TupleObject;

/*
 * class with the implementation of messages for tuples
 */
public class TupleClass extends PostalClass
{
	
	public PostalObject postalNew()
	{
		return new TupleObject();
	}
	
    public PostalObject messageReceived(PostalObject o, MessageObject m)
    {
    	assert(o instanceof TupleObject);   	
    	
    	TupleObject o1 = (TupleObject) o;
        if(m.getName().equals("add"))
        {
            PostalObject o2 = m.param(0);
            o1.addElement(o2);
            return null;
        }
        else if(m.getName().equals("remove"))
        {
            int index = ((IntegerObject) m.param(0)).value();
            PostalObject temp = (PostalObject) o1.getElement(index);
        	o1.removeElement(index);
        	return temp;
        }
        else if(m.getName().equals("itemAt"))
        {
            int index = ((IntegerObject) m.param(0)).value();
        	return (PostalObject) o1.getElement(index);
        }
        else if(m.getName().equals("size"))
        {
        	return new IntegerObject(o1.size());
        }
        else if(m.getName().equals("isEmpty"))
        {
        	return new BooleanObject(o1.isEmpty());
        }
        else
        {
            throw new TypeException("TupleClass: Message not known.");
        }
    }
}
