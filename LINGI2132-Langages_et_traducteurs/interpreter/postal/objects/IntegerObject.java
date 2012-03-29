
package postal.objects;

public class IntegerObject extends PostalObject
{
    int value; 
    public IntegerObject(int v)
    {
        this.value = v;
    }
	public int value() {
		return value;
	}
    
}
