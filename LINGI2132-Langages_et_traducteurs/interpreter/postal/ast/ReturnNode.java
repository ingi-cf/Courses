package postal.ast;

import postal.environment.PostalEnvironment;
import postal.objects.PostalObject;

/*
 * node used to return a element at the end of a message implementation
 */
public class ReturnNode extends ElementNode {
	ElementNode elem;
	
	/*
	 * this create a return node that will return the resolution of elem
	 */
	public ReturnNode(ElementNode elem)
	{
		this.elem = elem;
	}
	
	public ElementNode execute(PostalEnvironment e) {
		ElementNode en = elem.resolve(e);
		
		return en;
	}

	public PostalObject resolve(PostalEnvironment e) {
		PostalObject po = elem.resolve(e);
		return (PostalObject) po;
	}

}
