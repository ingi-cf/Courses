package postal.interpreter;

import java.util.Hashtable;

import postal.environment.MessageImplementation;
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
		
		ASTname[gt.nonTerminal("<statement>")]	 					="PostalNode";
		ASTname[gt.nonTerminal("<message>")] 						="ElementNode";
		ASTname[gt.nonTerminal("<message sending suffixe>")] 		="ElementSuffixe";
		ASTname[gt.nonTerminal("<element list>")] 					="LinkedList<ElementNode>";
		ASTname[gt.nonTerminal("<comma first element list>")] 		="LinkedList<ElementNode>";
		ASTname[gt.nonTerminal("<boolean value>")] 				    ="ElementNode";
		ASTname[gt.nonTerminal("<tuple>")] 						    ="ElementNode";
		ASTname[gt.nonTerminal("<element>")] 						="ElementNode";
		ASTname[gt.nonTerminal("<element suffixe>")] 				="LinkedList<ElementSuffixe>"; //linked list of what exaclty? a couple <action, element>  element can be a string? or a variable node? 
		ASTname[gt.nonTerminal("<element prefixe>")] 				="ElementPrefixe";
		ASTname[gt.nonTerminal("<unary operation>")] 				="ElementNode";
		ASTname[gt.nonTerminal("<element access>")] 				="ElementSuffixe";
		ASTname[gt.nonTerminal("<operation suffixe>")] 			    ="ElementSuffixe";
		ASTname[gt.nonTerminal("<binary operator>")] 				="String";
		ASTname[gt.nonTerminal("<unary operator>")] 				="String";
		ASTname[gt.nonTerminal("<instantiation>")] 				    ="InstantiateClassNode";
		ASTname[gt.nonTerminal("<assignment expression suffixe>")]  ="ElementSuffixe";
		ASTname[gt.nonTerminal("<block code>")] 					="SequenceNode";
		ASTname[gt.nonTerminal("<statements>")] 					="PostalNode";
		ASTname[gt.nonTerminal("<while statement>")] 				="WhileNode";
		ASTname[gt.nonTerminal("<if statement>")] 					="IfNode";
		ASTname[gt.nonTerminal("<class>")] 						    ="ClassDeclarationNode";
		ASTname[gt.nonTerminal("<extends>")] 						="String";
		ASTname[gt.nonTerminal("<class body>")] 					="ClassDeclarationNode";
		ASTname[gt.nonTerminal("<attributes declaration>")] 		="LinkedList<String>";
		ASTname[gt.nonTerminal("<messages declaration>")] 			="Hashtable<String,MessageImplementation>";
		ASTname[gt.nonTerminal("<message declaration>")] 			="MessageImplementation";
		ASTname[gt.nonTerminal("<attribute declaration>")] 		    ="String";

		
		ASTname[gt.terminal("IDENTIFIER")] 						= "String" ;
		ASTname[gt.terminal("CLASSIDENTIFIER")] 				= "String" ;
		ASTname[gt.terminal("INTEGER")] 						= "ElementNode" ;
		
		genST2AST gen = new genST2AST(CParams.PATH, ASTname) ;
		gen.genTrad() ;
	}
}