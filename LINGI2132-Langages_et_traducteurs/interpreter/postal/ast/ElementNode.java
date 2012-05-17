package postal.ast;
import postal.environment.PostalEnvironment;
import postal.objects.PostalObject;

/*
 * Nodes representing elements from the syntax
 */
public abstract class ElementNode extends PostalNode
{
	/*
	 * returns the PostalObject obtained after evaluation of the element 
	 * with the environment e
	 */
    public abstract PostalObject resolve(PostalEnvironment e);
    
}
