
package postal.objects;

import postal.classes.*;

public class BooleanObject extends PostalObject
{
    boolean value;
    public BooleanObject(boolean b)
    {
    	
    	super(new BooleanClass());    	
        this.value = b;
    }

    public boolean booleanValue()
    {
        return value;
    }
    
	public String toString()
	{
		return ""+value;
	}
}
