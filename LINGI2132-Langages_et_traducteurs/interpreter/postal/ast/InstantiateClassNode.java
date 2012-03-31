package postal.ast;

import postal.environment.PostalEnvironment;
import postal.objects.PostalObject;


/*
 * Node representing the instanciation of a class 
 */
public class InstantiateClassNode extends PostalNode implements ElementNode {

	private ElementNode message;
	private String className;

	/*
	 * Node for instanciating class classname with constructor message m
	 */
	public InstantiateClassNode(String className, ElementNode m)
    {
		this.className = className;
        this.message = m;
    }

	/*
	 * Executes the instanciation and returns the obtained instance
	 */
    public PostalObject execute(PostalEnvironment e)
    {
		return resolve(e);
    }


	public PostalObject resolve(PostalEnvironment e) {
		return e.newObject(className, message.resolve(e));

	}

}
