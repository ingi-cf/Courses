package postal.parser;

import postal.ast.ElementNode;

public class ElementSuffixe {
	public final static int MESSAGESENDING 	= 0;
	public final static int ACCESS 			= 1;
	public final static int OPERATION		= 2;
	public final static int ASSIGNMENT		= 3;
	int action;
	ElementNode en;
	
	public ElementSuffixe(int action, ElementNode en)
	{
		this.action = action;
		this.en = en;
	}
}
