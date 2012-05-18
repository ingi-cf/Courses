
package postal.objects;

import postal.classes.*;

/*
 * Stdio object, empty object used only to access the Stdio class for printing
 */
public class StdioObject extends PostalObject
{

	public StdioObject() {
		super(new StdioClass());
	}

	public void postalPrint(PostalObject toPrint) {
		// TODO Auto-generated method stub
		System.out.println("PostalSTDIO : " + toPrint.toString());
	}
	
}
