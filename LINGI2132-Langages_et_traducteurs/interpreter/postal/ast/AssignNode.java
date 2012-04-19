package postal.ast;

import postal.environment.PostalEnvironment;
import postal.objects.PostalObject;

public class AssignNode extends PostalNode{
	private String identifier;
	private ElementNode element;
	private ElementNode src;
	
	AssignNode (String identifier,ElementNode src, ElementNode e)
	{
		//TODO verify identifier to see if well formated
		this.identifier = identifier;
		this.element = e;
		this. src = src;
	}
	
	AssignNode (String identifier, ElementNode e)
	{
		//TODO verify identifier to see if well formated
		this.identifier = identifier;
		this.element = e;
	}
	
	public ElementNode execute(PostalEnvironment e) {
		if(src == null)
			e.setVariable(identifier,element);
		else
			src.resolve(e).getEnvironment().setVariable(identifier,element);
		return null;
	}
	
	
}