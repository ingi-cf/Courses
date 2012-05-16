package postal.interpreter;

import slip.trees.genST2AST;

class makeST2AST{
	static public void main(String[] arg) throws Exception
	{
		gtools.GTools gt = CParams.GT ;
		int NT = gt.numberOfTerminals() ;
		int NNT = gt.numberOfNonTerminals() ;
		String[] ASTname = new String[NT + NNT];
		{	int i = 0 ;
			while (i != ASTname.length)
			{ASTname[i ++] = "" ; }	
		}
		
		ASTname[gt.nonTerminal("<expression>")] = "Expr" ;
	
		genST2AST gen = new genST2AST(CParams.PATH, ASTname) ;
		gen.genTrad() ;
	}
}