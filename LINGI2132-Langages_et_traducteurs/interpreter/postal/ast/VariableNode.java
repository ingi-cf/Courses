package postal.ast;

import postal.environment.PostalEnvironment;
import postal.objects.PostalObject;

public class VariableNode extends PostalNode implements ElementNode{

	private String identifier;
	VariableNode (String identifier)
	{
		//TODO verify identifier to see if well formated
		this.identifier = identifier;
	}
	public PostalObject resolve(PostalEnvironment e) {
		return e.getVariable(identifier);
	}
	
	public ElementNode execute(PostalEnvironment e) {
		return resolve(e);
	}

}
