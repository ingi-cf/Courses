import slip.trees.TreeNode;

public class ST2AST{
  private static String trad3(TreeNode tree){
  // Tree symbol is IDENTIFIER

      return null ; // a modifier
  }
  private static ElementNode trad11(TreeNode tree){
  // Tree symbol is INTEGER

      return null ; // a modifier
  }
  private static String trad32(TreeNode tree){
  // Tree symbol is CLASSIDENTIFIER

      return null ; // a modifier
  }
  private static Object trad40(TreeNode tree){
  // tree symbol is <S>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <S> --> <block code> 
               { 
                 SequenceNode x0 = trad58(tree.getChild(0)) ;
                 return null ; // a modifier
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
                 return null ; // a modifier
               }
       case 1 : // <statement> --> <element> ; 
               { 
                 ElementNode x0 = trad48(tree.getChild(0)) ;
                 return null ; // a modifier
               }
       case 2 : // <statement> --> return <element> ; 
               { 
                 ElementNode x1 = trad48(tree.getChild(1)) ;
                 return null ; // a modifier
               }
       case 3 : // <statement> --> <while statement> 
               { 
                 WhileNode x0 = trad60(tree.getChild(0)) ;
                 return null ; // a modifier
               }
       case 4 : // <statement> --> <if statement> 
               { 
                 IfNode x0 = trad61(tree.getChild(0)) ;
                 return null ; // a modifier
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
                 return null ; // a modifier
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
                 return null ; // a modifier
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
                 return null ; // a modifier
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
                 return null ; // a modifier
               }
       case 1 : // <comma first element list> --> , <element> <comma first element list> 
               { 
                 ElementNode x1 = trad48(tree.getChild(1)) ;
                 LinkedList<ElementNode> x2 = trad45(tree.getChild(2)) ;
                 return null ; // a modifier
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
                 return null ; // a modifier
               }
       case 1 : // <boolean value> --> false 
               { 
                 return null ; // a modifier
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
                 LinkedList<ElementNode> x1 = trad44(tree.getChild(1)) ;
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
                 return null ; // a modifier
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
                 return null ; // a modifier
               }
       case 1 : // <element suffixe> --> <operation suffixe> <element suffixe> 
               { 
                 ElementSuffixe x0 = trad53(tree.getChild(0)) ;
                 LinkedList<ElementSuffixe> x1 = trad49(tree.getChild(1)) ;
                 return null ; // a modifier
               }
       case 2 : // <element suffixe> --> <message sending suffixe> <element suffixe> 
               { 
                 ElementSuffixe x0 = trad43(tree.getChild(0)) ;
                 LinkedList<ElementSuffixe> x1 = trad49(tree.getChild(1)) ;
                 return null ; // a modifier
               }
       case 3 : // <element suffixe> --> <assignment expression suffixe> <element suffixe> 
               { 
                 ElementSuffixe x0 = trad57(tree.getChild(0)) ;
                 LinkedList<ElementSuffixe> x1 = trad49(tree.getChild(1)) ;
                 return null ; // a modifier
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
                 return null ; // a modifier
               }
       case 1 : // <element prefixe> --> INTEGER 
               { 
                 ElementNode x0 = trad11(tree.getChild(0)) ;
                 return null ; // a modifier
               }
       case 2 : // <element prefixe> --> # 
               { 
                 return null ; // a modifier
               }
       case 3 : // <element prefixe> --> self 
               { 
                 return null ; // a modifier
               }
       case 4 : // <element prefixe> --> super 
               { 
                 return null ; // a modifier
               }
       case 5 : // <element prefixe> --> <instantiation> 
               { 
                 InstantiateClassNode x0 = trad56(tree.getChild(0)) ;
                 return null ; // a modifier
               }
       case 6 : // <element prefixe> --> <boolean value> 
               { 
                 ElementNode x0 = trad46(tree.getChild(0)) ;
                 return null ; // a modifier
               }
       case 7 : // <element prefixe> --> <message> 
               { 
                 ElementNode x0 = trad42(tree.getChild(0)) ;
                 return null ; // a modifier
               }
       case 8 : // <element prefixe> --> <tuple> 
               { 
                 ElementNode x0 = trad47(tree.getChild(0)) ;
                 return null ; // a modifier
               }
       case 9 : // <element prefixe> --> <unary operation> 
               { 
                 ElementNode x0 = trad51(tree.getChild(0)) ;
                 return null ; // a modifier
               }
       case 10 : // <element prefixe> --> ( <element> ) 
               { 
                 ElementNode x1 = trad48(tree.getChild(1)) ;
                 return null ; // a modifier
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
                 return null ; // a modifier
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
                 return null ; // a modifier
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
                 return null ; // a modifier
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
                 return null ; // a modifier
               }
       case 1 : // <binary operator> --> - 
               { 
                 return null ; // a modifier
               }
       case 2 : // <binary operator> --> * 
               { 
                 return null ; // a modifier
               }
       case 3 : // <binary operator> --> / 
               { 
                 return null ; // a modifier
               }
       case 4 : // <binary operator> --> % 
               { 
                 return null ; // a modifier
               }
       case 5 : // <binary operator> --> <= 
               { 
                 return null ; // a modifier
               }
       case 6 : // <binary operator> --> => 
               { 
                 return null ; // a modifier
               }
       case 7 : // <binary operator> --> < 
               { 
                 return null ; // a modifier
               }
       case 8 : // <binary operator> --> > 
               { 
                 return null ; // a modifier
               }
       case 9 : // <binary operator> --> == 
               { 
                 return null ; // a modifier
               }
       case 10 : // <binary operator> --> != 
               { 
                 return null ; // a modifier
               }
       case 11 : // <binary operator> --> and 
               { 
                 return null ; // a modifier
               }
       case 12 : // <binary operator> --> or 
               { 
                 return null ; // a modifier
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
                 return null ; // a modifier
               }
       case 1 : // <unary operator> --> - 
               { 
                 return null ; // a modifier
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
                 return null ; // a modifier
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
                 return null ; // a modifier
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
                 PostalNode x1 = trad59(tree.getChild(1)) ;
                 return null ; // a modifier
               }
       default : return null ;
    }
  }
  private static PostalNode trad59(TreeNode tree){
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
                 PostalNode x1 = trad59(tree.getChild(1)) ;
                 return null ; // a modifier
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
                 return null ; // a modifier
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
                 return null ; // a modifier
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
                 return null ; // a modifier
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
       case 1 : // <extends> --> extends CLASSIDENTIFIER 
               { 
                 String x1 = trad32(tree.getChild(1)) ;
                 return null ; // a modifier
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
                 return null ; // a modifier
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
                 String x0 = trad69(tree.getChild(0)) ;
                 LinkedList<String> x1 = trad65(tree.getChild(1)) ;
                 return null ; // a modifier
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
                 return null ; // a modifier
               }
       default : return null ;
    }
  }
  private static MessageImplementation trad67(TreeNode tree){
  // tree symbol is <message declaration>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <message declaration> --> def { IDENTIFIER <comma first identifier list> } { <block code> } 
               { 
                 String x2 = trad3(tree.getChild(2)) ;
                 LinkedList<ElementNode> x3 = trad68(tree.getChild(3)) ;
                 SequenceNode x6 = trad58(tree.getChild(6)) ;
                 return null ; // a modifier
               }
       default : return null ;
    }
  }
  private static LinkedList<ElementNode> trad68(TreeNode tree){
  // tree symbol is <comma first identifier list>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <comma first identifier list> --> <lambda>
               { 
                 return null ; // a modifier
               }
       case 1 : // <comma first identifier list> --> , IDENTIFIER <comma first identifier list> 
               { 
                 String x1 = trad3(tree.getChild(1)) ;
                 LinkedList<ElementNode> x2 = trad68(tree.getChild(2)) ;
                 return null ; // a modifier
               }
       default : return null ;
    }
  }
  private static String trad69(TreeNode tree){
  // tree symbol is <attribute declaration>

    int r = tree.getRule() ;
    switch (r)
    {
       case 0 : // <attribute declaration> --> IDENTIFIER ; 
               { 
                 String x0 = trad3(tree.getChild(0)) ;
                 return null ; // a modifier
               }
       default : return null ;
    }
  }
 public static Object tradProgram(TreeNode tree) throws Exception
 { return  trad40(tree) ; }

}

