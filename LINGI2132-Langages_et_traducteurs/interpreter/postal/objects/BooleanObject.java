
package postal.objects;

import postal.classes.PostalClass;

public class BooleanObject extends PostalObject
{
    boolean value;
    public BooleanObject(boolean b, PostalClass c)
    {
    	super(c);
        this.value = b;
    }

    public boolean booleanValue()
    {
        return value;
    }
}
