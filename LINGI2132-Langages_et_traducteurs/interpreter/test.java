import postal.ast.*;
import postal.classes.*;
import postal.environment.*;
import postal.objects.*;

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
    	seq.addStatement(new AssignNode("out", new IntegerObject(42, new IntegerClass())));
    	
    	VariableNode stdio = new VariableNode("stdio");
    	
    	seq.addStatement(new SendNode(stdio, );
    	
    	PostalEnvironment e = new PostalEnvironment();
    	
    	//interpret ast
    	seq.execute(e);
    	
    	/*
    	 * 
    	 */
    	
        System.out.println("Hello postal");
    }
}