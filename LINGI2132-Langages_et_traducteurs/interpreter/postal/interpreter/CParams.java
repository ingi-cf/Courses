package postal.interpreter;

public class CParams {
	public final static String PATHDIR = "D:\\INFO21\\git\\courses\\LINGI2132-Langages_et_traducteurs\\interpreter\\";
	public final static String PATH = PATHDIR + "gLL1.txt";
	public static gtools.GTools gt = null;
	static{ try {gt = new gtools.GTools(PATH) ;}
	catch(Exception e){ System.err.println(e) ;}
	}
	public final static gtools.GTools GT = gt ;
}
