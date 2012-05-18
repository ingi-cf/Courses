import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import postal.parser.Executer;


public class Postal {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String code="";
		
		if(args.length<1)
		{
			System.err.println("This program needs at least one argument containing a filename or a program to execute. ");
			System.exit(-1);
		}
		
		try 
		{
			
			BufferedReader in = new BufferedReader(new FileReader(args[0]));
			String line;
			
			while ((line = in.readLine()) != null)
			{
				code = code + line;
			}
			in.close();
		} catch (FileNotFoundException e) 
		{
			code = args[0];
		} catch (IOException e) 
		{
			System.err.println("Error reading file.");
			e.printStackTrace();
		}
		
		try 
		{
			//System.out.println(code);
			Executer.execute(code);
		} 
		catch (Exception e) 
		{
			System.err.println("Error executing code : "+ e.getMessage());
			//e.printStackTrace();
		}

	}

}
