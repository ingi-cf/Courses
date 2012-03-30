package postal.ast;

import postal.environment.PostalEnvironment;
import postal.objects.PostalObject;

public class VariableNode extends PostalNode implements ElementNode{

	private String identifier;
	private ElementNode element;
	/**
	 * Accessing a variable from the local environment
	 * @param identifier
	 */
	VariableNode (String identifier)
	{
		//TODO verify identifier to see if well formated
		this.identifier = identifier;
	}
	
	/**
	 * Accessing a variable from the environment of an object
	 * @param identifier
	 */
	VariableNode (ElementNode e, String identifier)
	{
		//TODO verify identifier to see if well formated
		this.identifier = identifier;
		this.element = e;
	}
	public PostalObject resolve(PostalEnvironment e) {
		//TODO add error if the variable doesn't exists (here or in getEnvironment
		if (element == null)
			return e.getVariable(identifier);
		else 
			return element.resolve(e).getEnvironment().getVariable(identifier);
	}
	
	public ElementNode execute(PostalEnvironment e) {
		return resolve(e);
	}

}
