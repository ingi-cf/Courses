
package postal.objects;

import java.util.ListIterator;

import postal.ast.ElementNode;
import postal.classes.*;

/*
 * represents a boolean
 */
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
		String s="";
    	s+="[(Boolean Object) : ";
    	s+= value;
    	s+="]";
    	return s;
	}
}
