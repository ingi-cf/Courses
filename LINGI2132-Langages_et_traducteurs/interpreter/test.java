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
    	System.out.println("Try to print 42 : ");
    	//build ast 	
    	MessageObject message = new MessageObject("print");
    	message.addParameter(new IntegerObject(42));
    	
    	SendNode sen = new SendNode(new StdioObject(), message);
    	
    	PostalEnvironment e = new PostalEnvironment();
    	
    	//interpret ast
    	sen.execute(e);
    	
    	/*
    	 * print of 84 with use of a variable and the env stdio
    	 */
    	System.out.println("Try to print 84 : ");
    	//build ast
    	SequenceNode seq = new SequenceNode();
    	seq.addStatement(new AssignNode("out", new IntegerObject(84)));
    	
    	VariableNode stdio = new VariableNode("stdio");
    	
    	message = new MessageObject("print");
    	message.addParameter(new VariableNode("out"));
    	
    	seq.addStatement(new SendNode(stdio, message));
    	
    	e = new PostalEnvironment();
    	
    	//interpret ast
    	seq.execute(e);
    	
    	/*
    	 * 
    	 */
    	
        System.out.println("end of the tests");
    }
}