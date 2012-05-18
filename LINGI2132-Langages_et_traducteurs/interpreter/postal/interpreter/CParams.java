package postal.interpreter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class CParams 
{
	public final static String PATHDIR = "D:\\INFO21\\git\\courses\\LINGI2132-Langages_et_traducteurs\\interpreter\\";
	//public final static String PATHDIR = "/home/frol/Documents/Ucl/Cours/projects/LINGI2132-Langages_et_traducteurs/interpreter/postal/";
	//public final static String PATHDIR = "";
	public final static String PATH = PATHDIR + "gLL1.txt";
		
	public static gtools.GTools gt = null;
	static{ 
		try 
		{
			//if no file at PATH we extract it from the jar : 
			File f = new File(PATH); 
			if(!f.exists() || !f.isFile())
			{
				OutputStream out = new FileOutputStream(f); 
				InputStream in = CParams.class.getResourceAsStream("/gLL1.txt");
				
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0)
				{
					out.write(buf, 0, len);
				}
				in.close();
				out.close();
				
			}
			//now read grammar from the file
			gt = new gtools.GTools(PATH);
		}
	catch(Exception e){ System.err.println(e) ;}
	}
	public final static gtools.GTools GT = gt ;
}
