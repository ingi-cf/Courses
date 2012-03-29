
package postal.objects;

public class BooleanObject extends PostalObject
{
    boolean value;
    public BooleanObject(boolean b)
    {
        this.value = b;
    }

    public boolean booleanValue()
    {
        return value;
    }
}
