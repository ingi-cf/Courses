package postal.objects;
import postal.ast.ElementNode;
import postal.classes.PostalClass;
public class PostalObject implements ElementNode
{
    private PostalClass postalClass;

	PostalObject()
    {
        
    }

    public PostalObject resolve()
    {
        return this;
    }

	public PostalClass getPostalClass() {
		return postalClass;
	}
    
    
}
