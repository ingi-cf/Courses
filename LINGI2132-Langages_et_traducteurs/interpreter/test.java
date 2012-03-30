import postal.ast.*;
import postal.environment.PostalEnvironment;

public class test
{
    test()
    {
            
    }
    public static void main (String [] args)
    {
    	System.out.println("start some tests on postal");
    	
    	/*
    	 * simple print of 42 
    	 */
    	//build ast
    	SequenceNode seq = new SequenceNode();
    	seq.addStatement(new AssignNode("out", "ELEMENT CLASS IS EMPTY"));
    	VariableNode stdio = new VariableNode("stdio")
    	seq.addStatement(new SendNode(stdio, new MessageObject("print", "TODO", "SACRE ELEMENT")));
    	
    	PostalEnvironment e = new PostalEnvironment();
    	
    	//interpret ast
    	seq.execute(e);
    	
    	/*
    	 * 
    	 */
    	
        System.out.println("Hello postal");
    }
}