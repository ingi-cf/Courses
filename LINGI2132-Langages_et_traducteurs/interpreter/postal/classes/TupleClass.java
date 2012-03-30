package postal.classes;

import postal.exceptions.TypeException;
import postal.objects.BooleanObject;
import postal.objects.IntegerObject;
import postal.objects.MessageObject;
import postal.objects.PostalObject;
import postal.objects.TupleObject;

public class TupleClass extends PostalClass
{
	
	public PostalObject postalNew()
	{
		return new TupleObject(this);
	}
	
    public PostalObject messageReceived(PostalObject o, MessageObject m)
    {
    	assert(o instanceof TupleObject);
    	TupleObject o1 = (TupleObject) o;
        if(m.name().equals("add"))
        {
            PostalObject o2 = m.param(0);
            o1.addElement(o2);
            return null;
        }
        else if(m.name().equals("remove"))
        {
            int index = ((IntegerObject) m.param(0)).value();
            PostalObject temp = o1.getElement(index);
        	o1.removeElement(index);
        	return temp;
        }
        else if(m.name().equals("itemAt"))
        {
            int index = ((IntegerObject) m.param(0)).value();
        	return o1.getElement(index);
        }
        else if(m.name().equals("size"))
        {
        	return new IntegerObject(o1.size(), this);
        }
        else if(m.name().equals("isEmpty"))
        {
        	return new BooleanObject(o1.isEmpty(), this);
        }
        else
        {
            throw new TypeException("TupleClass: Message not known.");
        }
    }
}
