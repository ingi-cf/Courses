package postal.ast;

import postal.environment.PostalEnvironment;

/*
 * node used to return a element at the end of a message implementation
 */
public class ReturnNode extends PostalNode {
	ElementNode elem;
	
	/*
	 * this create a return node that will return the resolution of elem
	 */
	public ReturnNode(ElementNode elem)
	{
		this.elem = elem;
	}
	
	public ElementNode execute(PostalEnvironment e) {
		return elem.resolve(e);
	}

}
