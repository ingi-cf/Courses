package postal.objects;
import postal.ast.ElementNode;
import postal.classes.PostalClass;
import postal.environment.PostalEnvironment;
public class PostalObject implements ElementNode
{
    private PostalClass postalClass;
    PostalEnvironment environment;
	PostalObject()
    {
        
    }

    public PostalObject resolve(PostalEnvironment e)
    {
        return this;
    }

	public PostalClass getPostalClass() {
		return postalClass;
	}

	public PostalEnvironment getEnvironment() {
		return environment;
	}
    
    
}
