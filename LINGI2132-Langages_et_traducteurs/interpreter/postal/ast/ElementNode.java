package postal.ast;
import postal.environment.PostalEnvironment;
import postal.objects.PostalObject;

/*
 * Nodes representing elements from the syntax
 */
public interface ElementNode
{
	/*
	 * returns the PostalObject obtained after evaluation of the element 
	 * with the environment e
	 */
    public PostalObject resolve(PostalEnvironment e);
    
}
