package postal.ast;

import postal.environment.PostalEnvironment;
import postal.objects.PostalObject;

public class AssignNode extends PostalNode{
	private String identifier;
	private ElementNode element;
	AssignNode (String identifier, ElementNode e)
	{
		//TODO verify identifier to see if well formated
		this.identifier = identifier;
		this.element = e;
	}
	
	public ElementNode execute(PostalEnvironment e) {
		e.setVariable(identifier,element);
		return null;
	}
	
	
}
