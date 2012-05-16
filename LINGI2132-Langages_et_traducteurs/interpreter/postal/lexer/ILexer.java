package postal.lexer;

public interface ILexer {
	IToken getNextSymbol() throws Exception;
}
