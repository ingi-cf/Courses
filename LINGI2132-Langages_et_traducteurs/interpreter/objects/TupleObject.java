public class TupleObject extends PostalObject
{
    LinkedList<PostalObject>    elements;
    Elements()
    {
        elements = new LinkedList<PostalObject>();
    }
    public void addElement(PostalObject e)
    {
        elements.add(e);
    }

}
