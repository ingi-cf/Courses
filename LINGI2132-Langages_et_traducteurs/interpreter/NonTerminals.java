import postal.interpreter.CParams;


public class NonTerminals {
	public static void main(String[] args)
	{
		gtools.GTools gt = CParams.GT;
		String[] nonTerminals = {"<lambda>","<S>","<statement>","<message>","<message sending suffixe>","<element list>","<comma first element list>","<boolean value>","<tuple>","<element>","<element suffixe>","<element prefixe>","<unary operation>","<element access>","<operation suffixe>","<binary operator>","<unary operator>","<instantiation>","<assignment expression suffixe>","<block code>","<statements>","<while statement>","<if statement>","<class>","<extends>","<class body>","<attributes declaration>","<messages declaration>","<message declaration>","<attribute declaration>"
};
		
		for (int i=0; i< nonTerminals.length; i++)
		{
			System.out.println(gt.nonTerminal(nonTerminals[i]) + " : " + nonTerminals[i]);
		}
		System.out.println("-------------------------------------------------");
		String[] terminals = {";","{","IDENTIFIER","}","MSA","true","false","INTEGER","#","self","super","+","-","*","/","%","<=","=>","<",">","==","!=","and","or","!","CLASSIDENTIFIER","while","if","class","def"};
		
		for (int i=0; i< terminals.length; i++)
		{
			System.out.println(gt.terminal(terminals[i]) + " : " + terminals[i]);
		}
	}
}


