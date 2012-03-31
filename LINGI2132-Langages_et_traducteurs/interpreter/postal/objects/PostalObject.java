package postal.objects;
import postal.ast.ElementNode;
import postal.classes.PostalClass;
import postal.environment.PostalEnvironment;

/*
 * represents any postal object
 */
public class PostalObject implements ElementNode
{
	//associated class
    private PostalClass postalClass;
    //environment inside the object for messages execution
    PostalEnvironment environment;
    
    /*
     * Create a postal object for postal class c
     */
	public PostalObject(PostalClass c)

	{
		this.postalClass = c;
    }

	/*
	 * an object's evaluation is itself
	 */
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

	public void print(PostalObject param) {
		// TODO Auto-generated method stub
	}

	public PostalObject getSuperObject() {
		return null;
	}
    
    
}
