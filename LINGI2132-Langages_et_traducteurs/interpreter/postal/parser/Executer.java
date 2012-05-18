package postal.parser;
import postal.ast.PostalNode;
import postal.environment.PostalEnvironment;
import postal.interpreter.CParams;
import postal.lexer.IToken;
import postal.lexer.PostalLexer;
public class Executer {
	static gtools.GTools gt = CParams.GT ;
	
	static public void execute(String s ) throws Exception
	{
		//System.out.println("Expression : " + s) ;
		PostalLexer lexer = new PostalLexer(s);
		IToken t = lexer.getNextSymbol();
		//System.out.print("Tokens : ");
		//while (t.getTerminal() != gt.numberOfTerminals())
		//{
		//	System.out.print("("+t.getTerminal()+")"+t.getSymbol()+" ");
		//	t = lexer.getNextSymbol();
		//}
		//System.out.println();
		TreeNode st = LL1Parser.parse(new PostalLexer(s), gt) ;
		//System.out.println("ST created");
		PostalNode ast = (PostalNode) ST2AST.tradProgram(st);
		//System.out.println("AST created");
		//System.out.println(ast);
		PostalEnvironment e = new PostalEnvironment();
		ast.execute(e);
		//System.out.println("Execution done");
			
	}

}