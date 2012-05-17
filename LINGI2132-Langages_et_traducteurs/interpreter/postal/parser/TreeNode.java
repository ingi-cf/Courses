package postal.parser;
import slip.grammars.Grammar ;
/* Cette classe (TreeNode) sert � construire l'arbre syntaxique d'un programme.

  Chaque instance de cette classe repr�sente un noeud de l'arbre syntaxique.
  Alternativement, chaque instance peut �tre vue comme la racine d'un arbre
  syntaxique ou d'un sous-arbre syntaxique.
  
  Un noeud peut �tre terminal ou non terminal.
  
  S'il est terminal, il correspond � un terminal de la grammaire du langage analys�.
  Dans ce cas, 
     - le champ X est l'entier repr�sentant le terminal de la grammaire,
     - le champ r est ind�fini,
     - le champ child est �gal � null,
     - le champ v est une valeur enti�re si le terminal X correspond aux constantes enti�res,
     - le champ s est un string si le terminal X correspond aux identificateurs ou � d'autres
       sortes de terminaux repr�sentant des classes de symboles de bases (sauf les entiers)
  Si le terminal correspond � un mot-cl�, un s�parateur, etc., c'est-�-dire s'il n'y a qu'un
  symbole possible correspondant � ce terminal, seuls les champs X et child sont significatifs
  (child == null si et seulement si X est terminal)
      
  Si le noeud est non terminal, il correspond � une r�gle de la grammaire.
  Dans ce cas,
     - le champ X est l'entier correspondant � la partie gauche de la r�gle,
     - le champ r est l'entier donnant le num�ro de la r�gle dans le tableau pRonde
       de la grammaire (donc, pRonde[X - NT][r] est la partie droite de la r�gle),
     - le champ child est un tableau de noeuds qui sont les fils de ce noeud
       et qui correspondent aux symboles de la partie droite de la r�gle
       (donc child.length == pRonde[X - NT][r].length),
     - le champ v est ind�fini (inutilis�),
     - le champ s est ind�fini (inutilis�).
     

*/
public class TreeNode{

	private int X ; // Symbol of the node
	private int r ; // rule of the node (number of the rule in pRonde[X])
	private TreeNode[] child; 
	private int v ; // integer value of the node (if any)
	private String s ; // string value of the note (if any)
	
	public TreeNode(int X)
	{ this.X = X ; }
	
			
	public TreeNode(int X, int r)
	{ this.X = X ; this.r = r ;	}
		
	public TreeNode(int X, int r, int v)
	// Terminal node with an int value
	// r is meaningless
	{ this.X = X ; this.r = r ;	this.v = v ;}
	
	public TreeNode(int X, String s)
	// Terminal node with a String value
	{ this.X = X ; this.s = s ;}
		
 	public TreeNode(int X, int r, TreeNode[] child)
		// For bottom-up parsers
		{ this(X, r) ;	putChilds(child) ;}
 
		public void putChilds(TreeNode[] child)
		// For top-down parsers
		{ this.child = child ; }
	
		public void putChilds(int r, TreeNode[] child)
		// For top-down parsers
		{ this.r = r ; this.child = child ; }
			
		public TreeNode getChild(int i){ return child[i] ; }
		public int getSymbol(){ return X ; }
		public int getRule(){ return r ; }
		
		public int getIntValue(){ return v ; }
		public String getStringValue(){ return s ; }
		public void putStringValue(String s){ this.s = s ; }
		
		public void print(Grammar g){ print("", g) ; }
		
		public void print(String s, Grammar g)
		{
			 String symbol = this.s ;
			 if (symbol == null) symbol = "" ;
			 	 else symbol = " [" + symbol + "]" ;
			 
		   System.out.println(s + g.symbol(X) + symbol) ;
		   if (child != null)
		   	 { s += "||" ;
		   	   int i = 0 ;
		   	   while (i != child.length) child[i ++].print(s, g) ;
		   	 }
		}
}
