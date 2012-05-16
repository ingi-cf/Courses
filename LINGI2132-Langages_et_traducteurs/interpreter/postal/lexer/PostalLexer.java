package postal.lexer;

import postal.interpreter.CParams;

public class PostalLexer implements ILexer {

	String line;
	
	public PostalLexer(String line)
	{
		this.line = line;
	}
	
	
	public IToken getNextSymbol()
	{
		gtools.GTools gt = CParams.GT;
		int NT = gt.numberOfTerminals() ; //number of terminals in grammar, to be returned if no more token in line
		
		//gt.terminal("<expression>") <- get the terminal corresponding to <expression>
		
		//read line a char at a time 
		//line.
		return null;
	}

}
