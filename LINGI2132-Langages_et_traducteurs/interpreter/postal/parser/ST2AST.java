package postal.parser;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.ListIterator;

import postal.ast.AssignNode;
import postal.ast.ClassDeclarationNode;
import postal.ast.ElementNode;
import postal.ast.IfNode;
import postal.ast.InstantiateClassNode;
import postal.ast.PostalNode;
import postal.ast.SendNode;
import postal.ast.SequenceNode;
import postal.ast.VariableNode;
import postal.ast.WhileNode;
import postal.environment.MessageImplementation;
import postal.objects.BooleanObject;
import postal.objects.IntegerObject;
import postal.objects.MessageObject;
import postal.objects.PostalObject;
import postal.objects.TupleObject;


//TODO Tuples WTF



public class ST2AST{
  private static String trad3(TreeNode tree){
  // Tree symbol is IDENTIFIER

      return tree.getStringValue();
  }
  private static ElementNode trad11(TreeNode tree){
  // Tree symbol is INTEGER

      return new IntegerObject(Integer.parseInt(tree.getStringValue())) ; 
  }
  private static String trad32(TreeNode tree){
  // Tree symbol is CLASSIDENTIFIER

      return tree.getStringValue() ; 
  }
  private static SequenceNode trad40(TreeNode tree){
  // tree symbol is <S>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <S> --> <block code> 
               { 
                 SequenceNode x0 = trad58(tree.getChild(0)) ;
                 return x0 ; 
               }
       default : return null ;
    }
  }
  private static PostalNode trad41(TreeNode tree){
  // tree symbol is <statement>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <statement> --> <class> 
               { 
                 ClassDeclarationNode x0 = trad62(tree.getChild(0)) ;
                 return x0 ; // a modifier
               }
       case 1 : // <statement> --> <element> ; 
               { 
                 ElementNode x0 = trad48(tree.getChild(0)) ;
                 return (PostalNode) x0 ; 
               }
       case 2 : // <statement> --> return <element> ; 
               { 
                 ElementNode x1 = trad48(tree.getChild(1)) ;
                 return (PostalNode) x1 ; 
               }
       case 3 : // <statement> --> <while statement> 
               { 
                 WhileNode x0 = trad60(tree.getChild(0)) ;
                 return x0 ; 
               }
       case 4 : // <statement> --> <if statement> 
               { 
                 IfNode x0 = trad61(tree.getChild(0)) ;
                 return x0 ; 
               }
       default : return null ;
    }
  }
  private static ElementNode trad42(TreeNode tree){
  // tree symbol is <message>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <message> --> { IDENTIFIER <comma first element list> } 
               { 
                 String x1 = trad3(tree.getChild(1)) ;
                 LinkedList<ElementNode> x2 = trad45(tree.getChild(2)) ;
                 MessageObject m = new MessageObject(x1) ;
                 m.setParameters(x2);
                 return m;
               }
       default : return null ;
    }
  }
  private static ElementSuffixe trad43(TreeNode tree){
  // tree symbol is <message sending suffixe>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <message sending suffixe> --> MSA <element> 
               { 
                 ElementNode x1 = trad48(tree.getChild(1)) ;
                 return new ElementSuffixe(ElementSuffixe.MESSAGESENDING, x1) ; 
               }
       default : return null ;
    }
  }
  private static LinkedList<ElementNode> trad44(TreeNode tree){
  // tree symbol is <element list>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <element list> --> <element> <comma first element list> 
               { 
                 ElementNode x0 = trad48(tree.getChild(0)) ;
                 LinkedList<ElementNode> x1 = trad45(tree.getChild(1)) ;
                 x1.push(x0);
                 return x1 ; // a modifier
               }
       case 1 : // <element list> --> <lambda>
               { 
                 return null ; // a modifier
               }
       default : return null ;
    }
  }
  private static LinkedList<ElementNode> trad45(TreeNode tree){
  // tree symbol is <comma first element list>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <comma first element list> --> <lambda>
               { 
                 return null ; 
               }
       case 1 : // <comma first element list> --> , <element> <comma first element list> 
               { 
                 ElementNode x1 = trad48(tree.getChild(1)) ;
                 LinkedList<ElementNode> x2 = trad45(tree.getChild(2)) ;
                 if(x2==null)
                	 x2 = new LinkedList<ElementNode>();
                 x2.push(x1);
                 return x2 ; // a modifier
               }
       default : return null ;
    }
  }
  private static ElementNode trad46(TreeNode tree){
  // tree symbol is <boolean value>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <boolean value> --> true 
               { 
                 return new BooleanObject(true) ; // a modifier
               }
       case 1 : // <boolean value> --> false 
               { 
                 return new BooleanObject(false) ; // a modifier
               }
       default : return null ;
    }
  }
  private static ElementNode trad47(TreeNode tree){
  // tree symbol is <tuple>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <tuple> --> [ <element list> ] 
               { 
            	 TupleObject to = new TupleObject();
                 LinkedList<ElementNode> x1 = trad44(tree.getChild(1)) ;
                 if (x1 != null)
                 {
                	 //TODO here there is something to do
                	 //to.setElements(x1);
                 }
                 return null ; // a modifier
               }
       default : return null ;
    }
  }
  private static ElementNode trad48(TreeNode tree){
  // tree symbol is <element>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <element> --> <element prefixe> <element suffixe> 
               { 
                 ElementPrefixe x0 = trad50(tree.getChild(0)) ;
                 LinkedList<ElementSuffixe> x1 = trad49(tree.getChild(1)) ;
                 if(x1 != null)
                 {
	                 ListIterator<ElementSuffixe> itr = x1.listIterator();
	                 while(itr.hasNext())
	                 {
	                	ElementSuffixe es = itr.next();
	                	switch(es.getAction())
	                	{
	                		case ElementSuffixe.ACCESS:
	                			x0 = new ElementPrefixe(x0.getElementObject(),es.getString());
	                			break;
	                		case ElementSuffixe.ASSIGNMENT:
	                			AssignNode an;
	                			if(x0.getElement() == null)
	                				an = new AssignNode(x0.getIdentifier(), es.getElement());
	                			else
	                				an = new AssignNode(x0.getIdentifier(),x0.getElement(), es.getElement());
	                			
	                			x0 = new ElementPrefixe(an);
	                			break;
	                		case ElementSuffixe.MESSAGESENDING:
	                			x0=new ElementPrefixe(new SendNode(x0.getElementObject(),es.getElement()));
	                			break;
	                		case ElementSuffixe.OPERATION:
	                			MessageObject m = new MessageObject(es.getString(),es.getElement());
	                			x0 = new ElementPrefixe(new SendNode(x0.getElementObject(),m));
	                			break;
	                	}
	                 }
                 }
                 return x0.getElementObject() ; // a modifier
               }
       default : return null ;
    }
  }
  private static LinkedList<ElementSuffixe> trad49(TreeNode tree){
  // tree symbol is <element suffixe>

    int r = tree.getRule() ;
  	switch (r)
    {
       case 0 : // <element suffixe> --> <element access> <element suffixe> 
               { 
                 ElementSuffixe x0 = trad52(tree.getChild(0)) ;
                 LinkedList<ElementSuffixe> x1 = trad49(tree.getChild(1)) ;
                 if (x1 == null)
                	 x1 = new LinkedList<ElementSuffixe>();
                 x1.push(x0);
                 return x1 ; // a modifier
               }
       case 1 : // <element suffixe> --> <operation suffixe> <element suffixe> 
               { 
                 ElementSuffixe x0 = trad53(tree.getChild(0)) ;
                 LinkedList<ElementSuffixe> x1 = trad49(tree.getChild(1)) ;
                 if (x1 == null)
                	 x1 = new LinkedList<ElementSuffixe>();
                 x1.push(x0);
                 return x1; // a modifier
               }
       case 2 : // <element suffixe> --> <message sending suffixe> <element suffixe> 
               { 
                 ElementSuffixe x0 = trad43(tree.getChild(0)) ;
                 LinkedList<ElementSuffixe> x1 = trad49(tree.getChild(1)) ;
                 if (x1 == null)
                	 x1 = new LinkedList<ElementSuffixe>();
                 x1.push(x0);
                 return x1;
               }
       case 3 : // <element suffixe> --> <assignment expression suffixe> <element suffixe> 
               { 
                 ElementSuffixe x0 = trad57(tree.getChild(0)) ;
                 LinkedList<ElementSuffixe> x1 = trad49(tree.getChild(1)) ;
                 if (x1 == null)
                	 x1 = new LinkedList<ElementSuffixe>();
                 x1.push(x0);
                 return x1;
               }
       case 4 : // <element suffixe> --> <lambda>
               { 
                 return null ; // a modifier
               }
       default : return null ;
    }
  }
  private static ElementPrefixe trad50(TreeNode tree){
  // tree symbol is <element prefixe>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <element prefixe> --> IDENTIFIER 
               { 
                 String x0 = trad3(tree.getChild(0)) ;
                 return new ElementPrefixe(x0);
               }
       case 1 : // <element prefixe> --> INTEGER 
               { 
                 ElementNode x0 = trad11(tree.getChild(0)) ;
                 return new ElementPrefixe(x0);
               }
       case 2 : // <element prefixe> --> # 
               { 
            	   return new ElementPrefixe(new VariableNode("#"));
               }
       case 3 : // <element prefixe> --> self 
               { 
            	   return new ElementPrefixe(new VariableNode("self"));
               }
       case 4 : // <element prefixe> --> super 
               { 
            	   return new ElementPrefixe(new VariableNode("super"));
               }
       case 5 : // <element prefixe> --> <instantiation> 
               { 
                 InstantiateClassNode x0 = trad56(tree.getChild(0)) ;
                 return new ElementPrefixe(x0); 
               }
       case 6 : // <element prefixe> --> <boolean value> 
               { 
                 ElementNode x0 = trad46(tree.getChild(0)) ;
                 return new ElementPrefixe(x0) ; // a modifier
               }
       case 7 : // <element prefixe> --> <message> 
               { 
                 ElementNode x0 = trad42(tree.getChild(0)) ;
                 return new ElementPrefixe(x0) ; // a modifier
               }
       case 8 : // <element prefixe> --> <tuple> 
               { 
                 ElementNode x0 = trad47(tree.getChild(0)) ;
                 return new ElementPrefixe(x0) ; // a modifier
               }
       case 9 : // <element prefixe> --> <unary operation> 
               { 
                 ElementNode x0 = trad51(tree.getChild(0)) ;
                 return new ElementPrefixe(x0) ; // a modifier
               }
       case 10 : // <element prefixe> --> ( <element> ) 
               { 
                 ElementNode x1 = trad48(tree.getChild(1)) ;
                 return new ElementPrefixe(x1) ; // a modifier
               }
       default : return null ;
    }
  }
  private static ElementNode trad51(TreeNode tree){
  // tree symbol is <unary operation>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <unary operation> --> <unary operator> <element> 
               { 
                 String x0 = trad55(tree.getChild(0)) ;
                 ElementNode x1 = trad48(tree.getChild(1)) ;
                 MessageObject m = new MessageObject(x0);
                 return new SendNode(x1, m) ; 
               }
       default : return null ;
    }
  }
  private static ElementSuffixe trad52(TreeNode tree){
  // tree symbol is <element access>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <element access> --> . IDENTIFIER 
               { 
                 String x1 = trad3(tree.getChild(1)) ;
                 return new ElementSuffixe(ElementSuffixe.ACCESS,x1);
               }
       default : return null ;
    }
  }
  private static ElementSuffixe trad53(TreeNode tree){
  // tree symbol is <operation suffixe>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <operation suffixe> --> <binary operator> <element> 
               { 
                 String x0 = trad54(tree.getChild(0)) ;
                 ElementNode x1 = trad48(tree.getChild(1)) ;
                 return new ElementSuffixe(ElementSuffixe.OPERATION,x0,x1) ; 
               }
       default : return null ;
    }
  }
  private static String trad54(TreeNode tree){
  // tree symbol is <binary operator>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <binary operator> --> + 
               { 
                 return "sum" ; // a modifier
               }
       case 1 : // <binary operator> --> - 
               { 
                 return "difference" ; // a modifier
               }
       case 2 : // <binary operator> --> * 
               { 
                 return "multiplication" ; // a modifier
               }
       case 3 : // <binary operator> --> / 
               { 
                 return "division" ; // a modifier
               }
       case 4 : // <binary operator> --> % 
               { 
                 return "mod" ; // a modifier
               }
       case 5 : // <binary operator> --> <= 
               { 
                 return "leq" ; // a modifier
               }
       case 6 : // <binary operator> --> => 
               { 
                 return "geq" ; // a modifier
               }
       case 7 : // <binary operator> --> < 
               { 
                 return "lt" ; // a modifier
               }
       case 8 : // <binary operator> --> > 
               { 
                 return "gt" ; // a modifier
               }
       case 9 : // <binary operator> --> == 
               { 
                 return "eq" ; // a modifier
               }
       case 10 : // <binary operator> --> != 
               { 
                 return "ne" ; // a modifier
               }
       case 11 : // <binary operator> --> and 
               { 
                 return "and" ; // a modifier
               }
       case 12 : // <binary operator> --> or 
               { 
                 return "or" ; // a modifier
               }
       default : return null ;
    }
  }
  private static String trad55(TreeNode tree){
  // tree symbol is <unary operator>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <unary operator> --> ! 
               { 
                 return "not" ; // a modifier
               }
       case 1 : // <unary operator> --> - 
               { 
                 return "minus" ; // a modifier
               }
       default : return null ;
    }
  }
  private static InstantiateClassNode trad56(TreeNode tree){
  // tree symbol is <instantiation>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <instantiation> --> CLASSIDENTIFIER MSA { new <comma first element list> } 
               { 
                 String x0 = trad32(tree.getChild(0)) ;
                 LinkedList<ElementNode> x4 = trad45(tree.getChild(4)) ;
                 MessageObject m = new MessageObject("new");
                 m.setParameters(x4);
                 return new InstantiateClassNode(x0, m) ; // a modifier
               }
       default : return null ;
    }
  }
  private static ElementSuffixe trad57(TreeNode tree){
  // tree symbol is <assignment expression suffixe>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <assignment expression suffixe> --> = <element> 
               { 
                 ElementNode x1 = trad48(tree.getChild(1)) ;
                 return new ElementSuffixe(ElementSuffixe.ASSIGNMENT,x1) ; // a modifier
               }
       default : return null ;
    }
  }
  private static SequenceNode trad58(TreeNode tree){
  // tree symbol is <block code>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <block code> --> <statement> <statements> 
               { 
                 PostalNode x0 = trad41(tree.getChild(0)) ;
                 LinkedList<PostalNode> x1 = trad59(tree.getChild(1)) ;
                 if(x1 == null)
                	 x1 = new LinkedList<PostalNode>();
                 x1.push(x0);
                 return new SequenceNode(x1) ;
               }
       default : return null ;
    }
  }
  private static LinkedList<PostalNode> trad59(TreeNode tree){
  // tree symbol is <statements>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <statements> --> <lambda>
               { 
                 return null ; // a modifier
               }
       case 1 : // <statements> --> <statement> <statements> 
               { 
                 PostalNode x0 = trad41(tree.getChild(0)) ;
                 LinkedList<PostalNode> x1 = trad59(tree.getChild(1)) ;
                 if(x1==null)
                	 x1 = new LinkedList<PostalNode>();
                 if(x0 == null)
                	 //TODO remove
                	 System.out.println("the error is here");
                 x1.push(x0);
                 return x1 ;
               }
       default : return null ;
    }
  }
  private static WhileNode trad60(TreeNode tree){
  // tree symbol is <while statement>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <while statement> --> while ( <element> ) { <block code> } 
               { 
                 ElementNode x2 = trad48(tree.getChild(2)) ;
                 SequenceNode x5 = trad58(tree.getChild(5)) ;
                 return new WhileNode(x2, x5) ; 
               }
       default : return null ;
    }
  }
  private static IfNode trad61(TreeNode tree){
  // tree symbol is <if statement>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <if statement> --> if ( <element> ) { <block code> } 
               { 
                 ElementNode x2 = trad48(tree.getChild(2)) ;
                 SequenceNode x5 = trad58(tree.getChild(5)) ;
                 return new IfNode(x2, x5);
               }
       default : return null ;
    }
  }
  private static ClassDeclarationNode trad62(TreeNode tree){
  // tree symbol is <class>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <class> --> class CLASSIDENTIFIER <extends> { <class body> } 
               { 
                 String x1 = trad32(tree.getChild(1)) ;
                 String x2 = trad63(tree.getChild(2)) ;
                 ClassDeclarationNode x4 = trad64(tree.getChild(4)) ;
                 x4.setName(x1);
                 x4.setExtends(x2);
                 return x4 ;
               }
       default : return null ;
    }
  }
  private static String trad63(TreeNode tree){
  // tree symbol is <extends>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <extends> --> <lambda>
               { 
                 return null ; // a modifier
               }
       case 1 : // <extends> --> identifier CLASSIDENTIFIER 
               { 
                 String x1 = trad32(tree.getChild(1)) ;
                 return x1 ; // a modifier
               }
       default : return null ;
    }
  }
  private static ClassDeclarationNode trad64(TreeNode tree){
  // tree symbol is <class body>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <class body> --> { <attributes declaration> } { <messages declaration> } 
               { 
                 LinkedList<String> x1 = trad65(tree.getChild(1)) ;
                 Hashtable<String,MessageImplementation> x4 = trad66(tree.getChild(4)) ;
                 return new ClassDeclarationNode(x1,x4) ; // a modifier
               }
       default : return null ;
    }
  }
  private static LinkedList<String> trad65(TreeNode tree){
  // tree symbol is <attributes declaration>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <attributes declaration> --> <lambda>
               { 
                 return null ; // a modifier
               }
       case 1 : // <attributes declaration> --> <attribute declaration> <attributes declaration> 
               { 
                 String x0 = trad68(tree.getChild(0)) ;
                 LinkedList<String> x1 = trad65(tree.getChild(1)) ;
                 if (x1==null)
                	 x1 = new LinkedList<String>();
                 x1.push(x0);
                 
                 return x1 ; 
               }
       default : return null ;
    }
  }
  private static Hashtable<String,MessageImplementation> trad66(TreeNode tree){
  // tree symbol is <messages declaration>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <messages declaration> --> <lambda>
               { 
                 return null ; // a modifier
               }
       case 1 : // <messages declaration> --> <message declaration> <messages declaration> 
               { 
                 MessageImplementation x0 = trad67(tree.getChild(0)) ;
                 Hashtable<String,MessageImplementation> x1 = trad66(tree.getChild(1)) ;
                 if(x1 == null)
                	x1 = new Hashtable<String,MessageImplementation>(); 
                 x1.put(x0.getName(), x0);
                 return x1 ; // a modifier
               }
       default : return null ;
    }
  }
  private static MessageImplementation trad67(TreeNode tree){
  // tree symbol is <message declaration>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <message declaration> --> def { IDENTIFIER <comma first element list> } { <block code> } 
               { 
                 String x2 = trad3(tree.getChild(2)) ;
                 LinkedList<ElementNode> x3 = trad45(tree.getChild(3)) ;
                 SequenceNode x6 = trad58(tree.getChild(6)) ;
                 //return new MessageImplementation(x2, x3, x6) ;
                 //TODO :'( grammaire foireuse ça marche pas avec comma first element list
               }
       default : return null ;
    }
  }
  private static String trad68(TreeNode tree){
  // tree symbol is <attribute declaration>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <attribute declaration> --> IDENTIFIER ; 
               { 
                 String x0 = trad3(tree.getChild(0)) ;
                 return x0 ;
               }
       default : return null ;
    }
  }
 public static Object tradProgram(TreeNode tree) throws Exception
 { return  trad40(tree) ; }

}
