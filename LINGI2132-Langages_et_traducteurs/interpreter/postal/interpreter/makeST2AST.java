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
		
		ASTname[gt.terminal("<S>")] 							="SequenceNode";
		ASTname[gt.terminal("<statement>")]	 					="PostalNode";
		ASTname[gt.terminal("<message>")] 						="";
		ASTname[gt.terminal("<message sending suffixe>")] 		="";
		ASTname[gt.terminal("<element list>")] 					="";
		ASTname[gt.terminal("<comma first element list>")] 		="";
		ASTname[gt.terminal("<boolean value>")] 				="ElementNode";
		ASTname[gt.terminal("<tuple>")] 						="ElementNode";
		ASTname[gt.terminal("<element>")] 						="ElementNode";
		ASTname[gt.terminal("<element suffixe>")] 				="";
		ASTname[gt.terminal("<element prefixe>")] 				="ElementNode";
		ASTname[gt.terminal("<unary operation>")] 				="ElementNode";
		ASTname[gt.terminal("<element access>")] 				="String";
		ASTname[gt.terminal("<operation suffixe>")] 			="ElementNode";
		ASTname[gt.terminal("<binary operator>")] 				="String";
		ASTname[gt.terminal("<unary operator>")] 				="String";
		ASTname[gt.terminal("<instantiation>")] 				="InstantiateClassNode";
		ASTname[gt.terminal("<assignment expression suffixe>")] ="";
		ASTname[gt.terminal("<block code>")] 					="SequenceNode";
		ASTname[gt.terminal("<statements>")] 					="";
		ASTname[gt.terminal("<while statement>")] 				="WhileNode";
		ASTname[gt.terminal("<if statement>")] 					="IfNode";
		ASTname[gt.terminal("<class>")] 						="ClassDeclarationNode";
		ASTname[gt.terminal("<extends>")] 						="String";
		ASTname[gt.terminal("<class body>")] 					="";
		ASTname[gt.terminal("<attributes declaration>")] 		="";
		ASTname[gt.terminal("<messages declaration>")] 			="";
		ASTname[gt.terminal("<message declaration>")] 			="";
		ASTname[gt.terminal("<attribute declaration>")] 		="";

		
		ASTname[gt.terminal("IDENTIFIER")] 						= "String" ;
		ASTname[gt.terminal("CLASSIDENTIFIER")] 				= "String" ;
		ASTname[gt.terminal("INTEGER")] 						= "String" ;
		
		genST2AST gen = new genST2AST(CParams.PATH, ASTname) ;
		gen.genTrad() ;
	}
}