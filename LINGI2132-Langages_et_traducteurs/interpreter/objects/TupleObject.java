package postal.objects;
import java.util.LinkedList;
public class TupleObject extends PostalObject
{
    LinkedList<PostalObject>    elements;
    TupleObject()
    {
        elements = new LinkedList<PostalObject>();
    }
    public void addElement(PostalObject e)
    {
        elements.add(e);
    }

}
