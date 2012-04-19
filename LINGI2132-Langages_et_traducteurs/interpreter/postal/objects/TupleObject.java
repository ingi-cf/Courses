package postal.objects;
import java.util.LinkedList;

import postal.classes.PostalClass;
public class TupleObject extends PostalObject
{
    LinkedList<PostalObject>    elements;
    
    TupleObject(PostalClass c)
    {
    	super(c);
        elements = new LinkedList<PostalObject>();
    }
    
    public void addElement(PostalObject e)
    {
        elements.add(e);
    }
    
    public void removeElement(int i)
    {
    	elements.remove(i);
    }
    
    public PostalObject getElement(int i) {
    	return elements.get(i);
    }
    
    public BooleanObject isEmpty() {
    	return new BooleanObject(elements.isEmpty());
    }
    
    public LinkedList<PostalObject> elements()
    {
    	return elements;
    }
}