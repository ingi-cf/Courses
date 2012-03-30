
package postal.objects;

import postal.classes.*;

public class IntegerObject extends PostalObject
{
    int value; 
    public IntegerObject(int v)
    {
    	super(new IntegerClass());
        this.value = v;
    }
	public int value() {
		return value;
	}
    
}
