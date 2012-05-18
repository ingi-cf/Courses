package postal.parser;
import postal.interpreter.CParams;
import postal.lexer.IToken;
import postal.lexer.PostalLexer;
import postal.lexer.PostalToken;
import slip.grammars.Grammar;
import gtools.GTools;
public class LL1Parser{
	static IToken t;

	public static TreeNode parse(PostalLexer lex, GTools gt) throws Exception
	{ 
		t = lex.getNextSymbol();
		return parse(lex, gt,gt.nonTerminal("<S>"));
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
	public static TreeNode parse(PostalLexer lex, GTools gt, int nt) throws Exception
	{ 
		
		//create a node with nt?
		int[] productionRule = gt.parseTable()[nt-gt.numberOfTerminals()][t.getTerminal()];
		
		//System.out.print("Found the production rule : " + nt + "," + t.getTerminal() + "->");
		//for(int i=0; i<productionRule.length ; i++)
		//	System.out.print(productionRule[i]+ " ");
		//System.out.println();
		int productionRuleNr = gt.ruleNumber(nt,productionRule);
		TreeNode tn = new TreeNode(nt,productionRuleNr);
			
		TreeNode[] childs = new TreeNode[productionRule.length];
		for(int i=0; i<productionRule.length ; i++)
		{
			if(productionRule[i] < gt.numberOfTerminals())
				//this is a terminal
			{
				if(productionRule[i] == t.getTerminal()){
					childs[i] = new TreeNode(t.getTerminal(),t.getSymbol());

				} else {
					throw new Exception("error : \""+t.getSymbol()+"\" not expected");
				}
				t = lex.getNextSymbol();

			} else {
				//this is not a terminal
				childs[i] = parse(lex,gt,productionRule[i]);
				
			}
			
		}
		tn.putChilds(childs);
		return tn;
	}
	
    
}