package postal.objects;
import java.util.LinkedList;
import java.util.ListIterator;

import postal.ast.ElementNode;
import postal.classes.*;
import postal.environment.PostalEnvironment;
/*
 * tuple representation for collections of data
 */
public class TupleObject extends PostalObject
{
    LinkedList<ElementNode>    elements;
    
    public TupleObject()
    {
    	super(new TupleClass());
        elements = new LinkedList<ElementNode>();
    }
    
    public void addElement(ElementNode e)
    {
        elements.add(e);
    }
    
    public void removeElement(int i)
    {
    	elements.remove(i);
    }
    
    public ElementNode getElement(int i) {
    	return elements.get(i);
    }
    
    public boolean isEmpty() {
    	return elements.isEmpty();
    }
    
    public int size() {
    	return elements.size();
    }
    
    public LinkedList<ElementNode> elements()
    {
    	return elements;
    }
	/*
	 * resolve the elements to objects in the environment e
	 */
	public PostalObject resolve(PostalEnvironment e)
    {
		TupleObject resolvedtuple = new TupleObject();
		ListIterator<ElementNode> itr = elements.listIterator();
        while(itr.hasNext())
        	resolvedtuple.addElement(itr.next().resolve(e));
        return resolvedtuple;
    }
    



	public void setElements(LinkedList<ElementNode> x1) {
		elements = x1;
		
	}
	
    public String toString()
    {
    	String s="";
    	s+="[(Tuple Object) : ";
    	ListIterator<ElementNode> itr = elements.listIterator();
    	boolean first = true;
        while(itr.hasNext())
        {
        	if(!first)
        		s += " , ";
        	else 
        		first = false;
        	s +=itr.next().toString();
        }		
        s+="]";
    	return s;
    }
}