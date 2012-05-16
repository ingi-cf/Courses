package postal.lexer;

public class PostalToken implements IToken {

	int terminal;
	String symbol;
	
	public PostalToken(int terminal, String symbol)
	{
		this.symbol = symbol;
		this.terminal = terminal;
	}
	
	public int getTerminal() {
		
		return terminal;
	}


	public String getSymbol() {
		return symbol;
	}

}
