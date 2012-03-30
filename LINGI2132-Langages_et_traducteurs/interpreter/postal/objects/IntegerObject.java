
package postal.objects;

import postal.classes.PostalClass;

public class IntegerObject extends PostalObject
{
    int value; 
    public IntegerObject(int v, PostalClass c)
    {
    	super(c);
        this.value = v;
    }
	public int value() {
		return value;
	}
    
}
