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
		System.out.println("Expression : " + s) ;
		PostalLexer lexer = new PostalLexer(s);
		IToken t = lexer.getNextSymbol();
		System.out.print("Tokens : ");
		while (t.getTerminal() != gt.numberOfTerminals())
		{
			System.out.print("("+t.getTerminal()+")"+t.getSymbol()+" ");
			t = lexer.getNextSymbol();
		}
		System.out.println();
		TreeNode st = LL1Parser.parse(new PostalLexer(s), gt) ;
		System.out.println("ST created");
		PostalNode ast = (PostalNode) ST2AST.tradProgram(st);
		System.out.println("AST created");
		System.out.println(ast);
		ast.execute(new PostalEnvironment());
		System.out.println("Execution done");
/*	try {
	// Construire l’arbre syntaxique st
	TreeNode st = LL1Parsers.parse(new CLexer(line), gt) ;
	// st.print(g); // Imprimer l’arbre syntaxique
	21
	// Construire l’arbre syntaxique abstrait expr
	Expr expr = ST2AST.tradProgram(st) ;
	// Demander les valeurs des variables
	Expr.var = makeVarTable(ST2AST.getTable()) ;
	// Calculer le résultat et l’afficher
	System.out.println("valeur == " + expr.value()) ;
	}
	catch(Exception e)
	{
	if (e instanceof LL1ParsingException)
	{
	// Erreur syntaxique
	LL1ParsingException e1 = (LL1ParsingException)e ;
	int xORX = e1.getTop() ;
	TokenWithContext tok = (TokenWithContext)e1.getTok() ;
	System.out.println(tok.getPointer() + " " + errMsg(xORX)) ;
	}else
	if (e instanceof LexicalException)
	{
	// Erreur lexicale
	LexicalException e2 = (LexicalException)e ;
	TokenWithContext tok = (TokenWithContext)e2.getToken() ;
	System.out.println(tok.getPointer() + " symbole invalide.") ;
	} else System.out.println(e) ; // Software or hardware error (waf !)
	}
	System.out.println("Expression ?") ;
	line = clavier.lireUnString() ;
	}
	*/
	}

}