import postal.parser.Executer;


public class PostalTests
{

	static int numberOfTests = 10;
	/**
	 * Used to run a battery of tests
	 */
	public static void main(String[] args)
	{
		String succ = ""; 
		String fail = "";
		for(int i=1;i<=numberOfTests;i++)
		{
			try
			{
				System.out.println("test number "+i);
				test(i);
				succ = succ+i+" ";
			}
			catch (Exception e)
			{
				fail = fail+i+" ";
			}
		}
		
		System.out.println("tests passed : "+succ.trim()+" (total : "+ (succ.length()==0 ? 0:succ.trim().split(" ").length)+") ");
		System.out.println("tests failed : "+fail.trim()+" (total : "+(fail.length()==0 ? 0:fail.trim().split(" ").length)+") ");
	}
	
	static void test(int i) throws Exception
	{
		switch(i)
		{
			case 1:
				Executer.execute("stdio<-{print,(4/2)+4};stdio<-{print,(4/2)+14};");
				break;
			case 2:
				Executer.execute("a=4;stdio<-{print,a};");
				break;
			case 3:
				Executer.execute("if(true){stdio<-{print,true};}");
				break;
			case 4:
				Executer.execute("a=10;while(a>1){stdio<-{print,a};a=a-1;}");
				break;
			case 5:
				Executer.execute("stdio<-{print,((1+1)+4)};");
				break;
			case 6:
				Executer.execute("a=5;b=6;stdio<-{print,a+b};");
				break;
			case 7:
				Executer.execute("class Point {{p;}{def{show}{stdio<-{print,self.p};}def{set}{self . p=4;}}}a = Point<-{new};a<-{set};a<-{show};");
				break;
			case 8:
				Executer.execute("a=[3,4,5];stdio<-{print,a};");
				break;
			case 9:
				Executer.execute("class Counter {{n;}{def{count, i}{stdio<-{print,i};if(i<self.n){self<-{count, i+1};}}def{set, i}{self.n=i;}}}c=Counter<-{new};c<-{set, 5};c<-{count,0};");
				break;
			case 10:
				Executer.execute("class Point {{p;}{def{get}{stdio<-{print,self . p};return self . p;}def{set,a}{self . p=a;}}}a = Point<-{new};a<-{set,9};stdio<-{print,a<-{get}};");
				break;
		}
	}

}
