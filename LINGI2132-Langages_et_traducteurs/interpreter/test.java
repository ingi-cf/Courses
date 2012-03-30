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
    	System.out.println("Test of print : Try to print 42 : ");
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
    	System.out.println("Test of variables : Try to print 84 from a variable : ");
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
    	System.out.println("Test of arithmetics : Try to print 2*84+1="+(2*84+1)+" from a variable : ");
    	
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
    	 * print 8 if 2>1 and print 9 if 2<1
    	 */
    	System.out.println("Test of if and booleans : if(2>1) prints 8 else prints 9  ");
    	
    	//build ast
    	seq = new SequenceNode(); //sequence node as root
    	
    	seq.addStatement(new AssignNode("out", new IntegerObject(5))); //out=5
    	outNode = new VariableNode("out");
    	
    	MessageObject comp = new MessageObject("gt", new IntegerObject(1)); //comparison message >1
    	
    	
    	SequenceNode truth = new SequenceNode();
    	truth.addStatement(new AssignNode("out", new IntegerObject(8))); //if true out=8
    	SequenceNode lie = new SequenceNode();
    	lie.addStatement(new AssignNode("out", new IntegerObject(9)));//else out=9
    	
    	IfNode ifnode = new IfNode(new SendNode(new IntegerObject(2), comp), truth, lie);
    	
    	seq.addStatement(ifnode); //queue the if
    	
    	message = new MessageObject("print",outNode); //build print out message
    	
    	
    	seq.addStatement(new SendNode(stdio, message));//add print message sending to sequence
    	e = new PostalEnvironment();
    	
    	//interpret ast
    	seq.execute(e);
    	
    	
    	/*
    	 * while(i<10) print(i); i++;
    	 */
    	System.out.println("Test of while : while(i<6){ print(i); i++;}  ");
    	
    	//build ast
    	seq = new SequenceNode(); //sequence node as root
    	
    	seq.addStatement(new AssignNode("i", new IntegerObject(0))); //i=0
    	VariableNode iNode = new VariableNode("i");
    	message = new MessageObject("print",iNode); //build print i message
    	
    	 comp = new MessageObject("lt", new IntegerObject(6)); //comparison message <10
    	
    	
    	SequenceNode body = new SequenceNode();
    	body.addStatement(new SendNode(stdio, message)); //print i
    	MessageObject summ = new MessageObject("sum",new IntegerObject(1));//++message
    	body.addStatement(new AssignNode("i", new SendNode(iNode, summ))); //if true out=8
    	
    	
    	WhileNode wnode = new WhileNode(new SendNode(iNode, comp), body); //while
    	
    	seq.addStatement(wnode); //queue the while
    	
    	
    	
    	
    	
    	e = new PostalEnvironment();
    	
    	//interpret ast
    	seq.execute(e);
    	/*
    	 * 
    	 */
    	
        System.out.println("end of the tests");
    }
}