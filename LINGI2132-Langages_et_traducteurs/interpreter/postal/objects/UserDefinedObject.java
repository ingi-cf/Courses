package postal.objects;

import postal.classes.PostalClass;
import postal.environment.PostalEnvironment;

public class UserDefinedObject extends PostalObject {
	
	//object attributes
	PostalEnvironment attributes;
	public UserDefinedObject(PostalClass c,PostalEnvironment a) {
		super(c);
		attributes = a;
	}

}
