package postal.ast;

import postal.environment.PostalEnvironment;
import postal.objects.PostalObject;

public class InstantiateClassNode extends PostalNode {

	private ElementNode message;
	private String className;

	InstantiateClassNode(String className, ElementNode m)
    {
		this.className = className;
        this.message = m;
    }

    public PostalObject execute(PostalEnvironment e)
    {
		return resolve(e);
    }

	
	public PostalObject resolve(PostalEnvironment e) {
		return e.newObject(className, message.resolve(e));

	}

}
