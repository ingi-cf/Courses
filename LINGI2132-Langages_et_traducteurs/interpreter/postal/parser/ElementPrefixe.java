package postal.parser;

import postal.ast.ElementNode;
import postal.ast.VariableNode;

public class ElementPrefixe {
	ElementNode en;
	String identifier;
	public ElementPrefixe(ElementNode en)
	{
		this.en = en;
	}
	public ElementPrefixe(String identifier)
	{
		this.identifier = identifier;
	}
	
	public ElementPrefixe(ElementNode en,String identifier)
	{
		this.en = en;
		this.identifier = identifier;
	}
	
	/**
	 * return the element stored
	 * @return
	 */
	public ElementNode getElement()
	{
		return en;
	}
	
	public String getIdentifier()
	{
		return identifier;
	}
	
	/**
	 * return the element object so if there is and identifier return a VariableNode
	 * 
	 */
	public ElementNode getElementObject()
	{
		if(identifier != null)
		{
			if(en!= null)
				return new VariableNode(en,identifier);
			else
				return new VariableNode(identifier);
			
		}else
			return en;
	}
}
