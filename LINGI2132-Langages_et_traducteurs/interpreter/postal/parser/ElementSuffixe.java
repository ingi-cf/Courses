package postal.parser;

import postal.ast.ElementNode;

public class ElementSuffixe {
	public final static int MESSAGESENDING 	= 0;
	public final static int ACCESS 			= 1;
	public final static int OPERATION		= 2;
	public final static int ASSIGNMENT		= 3;
	int action;
	ElementNode en;
	String stringVal;
	
	public ElementSuffixe(int action, ElementNode en)
	{
		this.action = action;
		this.en = en;
	}
	
	public ElementSuffixe(int action, String variableName)
	{
		this.action = action;
		this.stringVal = variableName;
	}
	
	public ElementSuffixe(int action, String x0, ElementNode x1) {
		this.action = action;
		this.stringVal = x0;
		this.en = x1;
	}

	public int getAction()
	{
		return action;
	}
	
	public ElementNode getElement()
	{
		return en;
	}

	public String getString()
	{
		return stringVal;
	}
}
