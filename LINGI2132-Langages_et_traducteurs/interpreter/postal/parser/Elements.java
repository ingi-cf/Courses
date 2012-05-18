package postal.parser;

import gtools.GTools;
import postal.interpreter.CParams;

public class Elements {
	static String[] elements;
	static {
		
		GTools gt = CParams.GT;
		elements = new String[gt.numberOfTerminals()+gt.numberOfNonTerminals()];
		String[] unorderedTerminals = {"IDENTIFIER","CLASSIDENTIFIER","MSA","INTEGER","return","true","false","super","self","def","and","or","while","if","class","extends","new",";","{","}","+","-","*","/","%","<=","=>","<",">","==","!=","!","#","=",",","(",")","[","]","."};
		String[] unorderedNonTerminals = {"<lambda>","<S>","<statement>","<message>","<message sending suffixe>","<element list>","<comma first element list>","<boolean value>","<tuple>","<element>","<element suffixe>","<element prefixe>","<unary operation>","<element access>","<operation suffixe>","<binary operator>","<unary operator>","<instantiation>","<assignment expression suffixe>","<block code>","<statements>","<while statement>","<if statement>","<class>","<extends>","<class body>","<attributes declaration>","<messages declaration>","<message declaration>","<attribute declaration>"
				,"<comma first identifier list>"};
		for(int i = 0; i< unorderedTerminals.length ; i++)
		{
			elements[gt.terminal(unorderedTerminals[i])] = unorderedTerminals[i];
		}
		for(int i = 0; i< unorderedNonTerminals.length ; i++)
		{
			elements[gt.nonTerminal(unorderedNonTerminals[i])] = unorderedNonTerminals[i];
		}
		for(int i = 0 ; i< elements.length; i++)
			System.out.println(i + " - " + elements[i]);
	}
	
	public static String terminal(int i)
	{
		return elements[i];
	}
	public static String nonTerminal(int i)
	{
		return elements[i];
	}
	public static String element(int i)
	{
		return elements[i];
	}
}
