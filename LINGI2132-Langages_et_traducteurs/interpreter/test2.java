import postal.parser.Executer;


public class test2 {
	public static void main(String[] args) throws Exception
	{
		t5();
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
}
