
package postal.objects;

public class BooleanObject extends PostalObject
{
    boolean value;
    BooleanObject(boolean b)
    {
        this.value = b;
    }

    boolean booleanValue()
    {
        return value;
    }
}
