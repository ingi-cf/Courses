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
    	SequenceNode seq = new SequenceNode();
    	seq.addStatement(new AssignNode("out", "ELEMENT CLASS IS EMPTY"));
    	seq.addStatement(new SendNode("HOW THE FUCK CAN I HAVE A POSTALOBJECT HERE", new MessageObject("print", "TODO", "SACRE ELEMENT")));
    	
    	PostalEnvironment e = new PostalEnvironment();
    	
    	seq.execute(e);
    	
    	/*
    	 * 
    	 */
    	
        System.out.println("Hello postal");
    }
}