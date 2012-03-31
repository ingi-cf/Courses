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
    	 *  stdio<-{print, 42}
    	 */
    	System.out.println("Test of print : stdio<-{print, 42} : ");
    	//build ast 	
    	MessageObject message = new MessageObject("print");
    	message.addParameter(new IntegerObject(42));
    	
    	SendNode sen = new SendNode(new StdioObject(), message);
    	
    	PostalEnvironment e = new PostalEnvironment();
    	
    	//interpret ast
    	sen.execute(e);
    	
    	/*
    	 * out = 84; stdio<-{print, out}
    	 */
    	System.out.println("Test of variables : out = 84; stdio<-{print, out} : ");
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
    	 * stdio<-{print, 1<-{sum, 2<-{multiplication, 84}}}
    	 */
    	System.out.println("Test of arithmetics : stdio<-{print, 1<-{sum, 2<-{multiplication, 84}}} : ");
    	
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
    	 * if(2<-{gt, 1}){ stdio<-{print, 8};} else{ stdio<-{print, 9};}  
    	 */
    	System.out.println("Test of if and booleans : if(2<-{gt, 1}){ stdio<-{print, 8};} else{ stdio<-{print, 9};}  ");
    	
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
    	 * while(i<-{lt, 10}){ stdio<-{print, i}; i = i<-{sum,1};}
    	 */
    	System.out.println("Test of while : while(i<-{lt, 10}){ stdio<-{print, i}; i = i<-{sum,1};}  ");
    	
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
        
        testRapport();
    }
    
    public static void testRapport()
    {
    	System.out.println("testRapport");
    	SequenceNode rootS = new SequenceNode();
    	
    	ClassDeclarationNode c = new ClassDeclarationNode("Point", null);
    	c.addArgument("x");
    	c.addArgument("y");
    	
    	SequenceNode bodynew = new SequenceNode();
    	bodynew.addStatement(new AssignNode("x", new VariableNode("self"), new VariableNode("px")));
    	bodynew.addStatement(new AssignNode("y", new VariableNode("self"), new VariableNode("py")));
    	
    	//Define the new message
    	MessageImplementation minew = new MessageImplementation("new",bodynew);
    	minew.addParameter("px");
    	minew.addParameter("py");
    	
    	//add the message inplementation to the class declaration
    	c.addMessageImplementation(minew);
    	
    	
    	//Add message implementation to add a point to another
    	
    	SequenceNode bodyadd = new SequenceNode();
    	MessageObject sum1 = new MessageObject("sum");
    	sum1.addParameter(new VariableNode("px"));
    	MessageObject sum2 = new MessageObject("sum");
    	sum2.addParameter(new VariableNode("py"));
    	SendNode snsm1 = new SendNode(new VariableNode(new VariableNode("self"),"x"), sum1);
    	SendNode snsm2 = new SendNode(new VariableNode(new VariableNode("self"),"y"), sum2);
    	bodyadd.addStatement(new AssignNode("x", new VariableNode("self"), snsm1));
    	bodyadd.addStatement(new AssignNode("y", new VariableNode("self"), snsm2));
    	
    	//Define the new message
    	MessageImplementation miadd = new MessageImplementation("add",bodyadd);
    	miadd.addParameter("px");
    	miadd.addParameter("py");
    	
    	//add the message inplementation to the class declaration
    	c.addMessageImplementation(miadd);
    	
    	
    	rootS.addStatement(c);
    	
    	//add a message to instantiate Point
    	//create the message
    	MessageObject snm1 = new MessageObject("new");
    	snm1.addParameter(new IntegerObject(1));
    	snm1.addParameter(new IntegerObject(4));
    	InstantiateClassNode sn1 = new InstantiateClassNode("Point", snm1);
    	rootS.addStatement(new AssignNode("p1",sn1));
    	
    	//add (42,69) to the Point
    	MessageObject snm2 = new MessageObject("add");
    	snm2.addParameter(new IntegerObject(42));
    	snm2.addParameter(new IntegerObject(69));
    	SendNode sn2 = new SendNode(new VariableNode("p1"), snm2);
    	rootS.addStatement(sn2);
    	
    	//message to print p1.x
    	MessageObject snmp1 = new MessageObject("print");
    	snmp1.addParameter(	new VariableNode(new VariableNode("p1"),"x"));
    	SendNode snp1 = new SendNode(new VariableNode("stdio"), snmp1);
    	rootS.addStatement(snp1);
    	
    	//message to print p1.x
    	MessageObject snmp2 = new MessageObject("print");
    	snmp2.addParameter(	new VariableNode(new VariableNode("p1"),"y"));
    	SendNode snp2 = new SendNode(new VariableNode("stdio"), snmp2);
    	rootS.addStatement(snp2);
    	
    	PostalEnvironment pe = new PostalEnvironment();
    	rootS.execute(pe);
    	
    	System.out.println("pe state:");
    	System.out.println(pe);
    	
    	
    	
    }
}