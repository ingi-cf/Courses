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
		line = line.trim(); //remove spaces from start or end of line
		gtools.GTools gt = CParams.GT;
		
		String symbol = "";
		int terminal=gt.numberOfTerminals();
		
		//todo make sure that 1:this list is complete; 2:gt.terminal cannot be used
		//todo extend not in grammar as word ?? 
		String termAlpha = "true|false|super|self|def|and|or|while|if|class|extends|new";
		String termNonAlpha = ";|\\{|\\}|\\+|-|\\*|/|%|<=|=>|<|>|==|!=|!|#|=|,|\\(|\\)";

		Boolean completeToken = false;
		String candidateToken="";
		while(line.length()>0 && !completeToken)
		{
			candidateToken = candidateToken+line.substring(0,1);
			line = line.substring(1);
			
			if(candidateToken.equals("<") && line.substring(0,1).equals("-"))
			{
				line = line.substring(1);
				symbol = "<-";
				terminal = gt.terminal("MSA");
				completeToken = true;
			}
			//first if is a dirty way to check if the current token is a terminal but how else ?
			else if(candidateToken.matches(termNonAlpha) || (candidateToken.matches(termAlpha) && (line.length()==0 || !line.substring(0,1).matches("[a-zA-Z]")))) //detect if it is a terminal symbol composed of letters
			{
				symbol = candidateToken;
				terminal = gt.terminal(symbol); 
				completeToken = true;
			}
			else if(candidateToken.matches("[a-z][a-zA-Z]*") && (line.length()==0 || !line.substring(0,1).matches("[a-zA-Z]"))) //is an identifier and is finished
			{
				symbol = candidateToken;
				terminal = gt.terminal("IDENTIFIER");
				completeToken = true;
			}
			else if(candidateToken.matches("[A-Z][a-zA-Z]*") && (line.length()==0 || !line.substring(0,1).matches("[a-zA-Z]"))) //is a class identifier and is finished
			{
				symbol = candidateToken;
				terminal = gt.terminal("CLASSIDENTIFIER");
				completeToken = true;
			}
			else if(candidateToken.matches("\\d+") && (line.length()==0 || !line.substring(0,1).matches("\\d"))) //is an integer and is finished
			{
				symbol = candidateToken;
				terminal = gt.terminal("INTEGER");
				completeToken = true;
			}
			else if(line.length()==0 || line.substring(0,1).matches(" ")) //finished line or word
			{
				completeToken = true;
			}
			

		}

		
		return new PostalToken(terminal, symbol);
	}

}
