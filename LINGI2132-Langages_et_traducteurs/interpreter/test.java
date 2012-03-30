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
    	System.out.println("Try to print 84 from a variable : ");
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
    	 * print of 2*84+1 with use of variables and the env stdio
    	 */
    	System.out.println("Try to print 2*84+1="+(2*84+1)+" from a variable : ");
    	
    	//build ast
    	seq = new SequenceNode(); //sequence node as root
    	
    	seq.addStatement(new AssignNode("out", new IntegerObject(1))); //add assign node to sequence out=1
    	
    	VariableNode outNode = new VariableNode("out");
    	
    	MessageObject multmsg = new MessageObject("multiplication");
    	multmsg.addParameter(new IntegerObject(84));
    	
    	seq.addStatement(new AssignNode("mult", new SendNode(new IntegerObject(2), multmsg))); //mult = 2*84
    	
    	 MessageObject addmsg = new MessageObject("sum");
    	 addmsg.addParameter(new VariableNode("mult"));
    	 
    	seq.addStatement(new AssignNode("out", new SendNode(outNode, addmsg))); //out = out + mult
    	

    	message = new MessageObject("print"); //build print out message
    	message.addParameter(outNode);
    	
    	seq.addStatement(new SendNode(stdio, message));//add print message sending to sequence
    	e = new PostalEnvironment();
    	
    	//interpret ast
    	seq.execute(e);
    	
    	/*
    	 * 
    	 */
    	
        System.out.println("end of the tests");
    }
}