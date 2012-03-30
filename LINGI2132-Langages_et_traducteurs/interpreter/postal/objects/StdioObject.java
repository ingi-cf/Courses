
package postal.objects;

import postal.classes.*;

public class StdioObject extends PostalObject
{

	public StdioObject() {
		super(new StdioClass());
	}

	public void postalPrint(PostalObject toPrint) {
		// TODO Auto-generated method stub
		System.out.println(toPrint.toString());
	}
	
}
