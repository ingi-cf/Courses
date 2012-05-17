package postal.parser;
import postal.interpreter.CParams;
import postal.lexer.IToken;
import postal.lexer.PostalLexer;
import postal.lexer.PostalToken;
import slip.grammars.Grammar;
import gtools.GTools;
public class LL1Parser{
	public static boolean isOk(PostalLexer lex, GTools gt) throws Exception
	{
		return true;
	}
	public static TreeNode parse(PostalLexer lex, GTools gt) throws Exception
	{ 
		
		return null;
	}
	/**
	 * parse knowing that the current non terminal is nt and the first token is t
	 * @param lex
	 * @param gt
	 * @param t
	 * @param nt
	 * @return
	 * @throws Exception
	 */
	public static TreeNode parse(PostalLexer lex, GTools gt,IToken t, int nt) throws Exception
	{ 
		//create a node with nt?
		TreeNode tn = new TreeNode();
		int[] productionRule = gt.parseTable()[nt-gt.numberOfTerminals()][t.getTerminal()];
		for(int i=0; i<productionRule.length ; i++)
		{
			if(productionRule[i] < gt.numberOfTerminals())
				//this is a terminal
			{
				
				t = lex.getNextSymbol();
			} else {
				parse(lex,gt,t,productionRule[i]);
			}
		}
		return null;
	}
	public static void main (String [] args) throws Exception
    {
		parse(new PostalLexer("ihhi"), CParams.GT);
		GTools gt = CParams.GT;
		int a = gt.nonTerminal("<message>")-gt.numberOfTerminals();
		int b = gt.terminal("{");
		System.out.println(a);
		System.out.println(b);
		
		System.out.println("{ : " + gt.terminal("{"));
		System.out.println("IDENTIFIER : " + gt.terminal("IDENTIFIER"));
		System.out.println("<coma f> : " + gt.nonTerminal("<comma first element list>"));
		System.out.println("} : " + gt.terminal("}"));
		System.out.println("Rule number : "+gt.ruleNumber(a+gt.numberOfTerminals(), gt.parseTable()[a][b]));
		for(int i=0; i<gt.parseTable()[a][b].length ; i++)
			System.out.println(gt.parseTable()[a][b][i]);
		
    }
    
}