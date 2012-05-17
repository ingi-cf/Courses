package postal.ast;

import postal.environment.PostalEnvironment;
import postal.objects.PostalObject;

/*
 *  Node representing assignment identifier = element
 */
public class AssignNode extends ElementNode{
	private String identifier;
	private ElementNode element;
	private ElementNode src;
	

	public AssignNode (String identifier,ElementNode src, ElementNode e)
	{
		//TODO verify identifier to see if well formated
		this.identifier = identifier;
		this.element = e;
		this. src = src;
	}
	
	public AssignNode (ElementNode src, ElementNode e)
	{
		this.element = e;
		this. src = src;
	}
	
	/*
	 * build node representing i = e 
	 */
	public AssignNode (String identifier, ElementNode e)
	{
		//TODO verify identifier to see if well formated
		this.identifier = identifier;
		this.element = e;
	}
	
	/*
	 * Executes assignment on the environment e, changing the value of identifier or creating it ine
	 */
	public ElementNode execute(PostalEnvironment e) {
		
		if(src == null)
			e.setVariable(identifier,element.resolve(e));
		else
		{
			System.out.println(identifier + " - " + element);
			src.resolve(e).getEnvironment().setVariable(identifier,element.resolve(e));
		}
		return null;
	}


	public PostalObject resolve(PostalEnvironment e) {
		// TODO Auto-generated method stub
		return (PostalObject) execute(e);
	}
	
	
}
