package postal.objects;
import java.util.LinkedList;
import java.util.ListIterator;

import postal.ast.ElementNode;
import postal.classes.*;
public class TupleObject extends PostalObject
{
    LinkedList<PostalObject>    elements;
    
    public TupleObject()
    {
    	super(new TupleClass());
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
    
    public boolean isEmpty() {
    	return elements.isEmpty();
    }
    
    public int size() {
    	return elements.size();
    }
    
    public LinkedList<PostalObject> elements()
    {
    	return elements;
    }
    
	public String toString()
	{
		String ret = "";
		ListIterator<PostalObject> itr = elements.listIterator();
        while(itr.hasNext())
        	ret = ret+" , "+itr.next().toString();
        return ret;
	}
}