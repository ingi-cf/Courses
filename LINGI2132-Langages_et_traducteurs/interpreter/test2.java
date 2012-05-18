import postal.parser.Executer;

public class test2 {
	public static void main(String[] args) throws Exception
	{
		t9();
	}
	static void t0() throws Exception//OK
	{
		Executer.execute("a=4");
	}
	static void t1() throws Exception//OK
	{
		Executer.execute("stdio<-{print,(4/2)+4};stdio<-{print,(4/2)+14};");
	}
	static void t2() throws Exception//OK
	{
		Executer.execute("a=4;stdio<-{print,a};");
	}
	static void t3() throws Exception //OK
	{
		Executer.execute("if(true){stdio<-{print,true};}");
	}
	static void t4() throws Exception //OK
	{
		Executer.execute("a=10;while(a>1){stdio<-{print,a};a=a-1;}");
	}
	static void t5() throws Exception //OK
	{
		Executer.execute("stdio<-{print,((1+1)+4)};");
	}
	static void t6() throws Exception //OK
	{
		Executer.execute("a=5;b=6;stdio<-{print,a+b};");
	}
	static void t7() throws Exception //OK
	{
		Executer.execute("class Point {{p;}{def{show}{stdio<-{print,self . p};}def{set,a}{self . p=a;}}}a = Point<-{new};a<-{set,8};a<-{show};");
		//
	}

	static void t8() throws Exception //OK
	{
		Executer.execute("a=[3,4,5];stdio<-{print,a};");
		
	}	
	static void t9() throws Exception //NOT ok return is failing
	{
		Executer.execute("class Point {{p;}{def{get}{stdio<-{print,self . p};return self . p;}def{set,a}{self . p=a;}}}a = Point<-{new};a<-{set,9};stdio<-{print,a<-{get}};");
		//
		
	}	
	static void t10() throws Exception //not OK
	{
		Executer.execute("class Point {{p;}{def{show}{stdio<-{print,self . p};}def{set}{self . p=a;}}}class Pointb {{d;}{def{show}{stdio<-{print,super . p};}}}a = Pointb<-{new};a<-{set 8};a<-{show};");
		//
	}
}
