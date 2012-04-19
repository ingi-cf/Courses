package postal.classes;

import postal.exceptions.TypeException;
import postal.objects.MessageObject;
import postal.objects.PostalObject;
import postal.objects.StdioObject;

/*
 * class with the implementation of messages for stdio
 */
public class StdioClass extends PostalClass
{
	
	public PostalObject postalNew()
	{
		return new StdioObject();
	}
	
    public PostalObject messageReceived(PostalObject o, MessageObject m)
    {
    	assert(o instanceof StdioObject);
    	StdioObject o1 = (StdioObject) o;
        if(m.getName().equals("print"))
        {
        	o1.postalPrint(m.param(0));
        	return null;        	
        }
        else
        {
            throw new TypeException("BooleanClass: Message not known.");
        }
    }
}
