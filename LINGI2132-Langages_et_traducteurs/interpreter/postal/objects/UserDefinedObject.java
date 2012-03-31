package postal.objects;

import postal.classes.PostalClass;
import postal.environment.PostalEnvironment;

public class UserDefinedObject extends PostalObject {
	
	//object attributes
	PostalObject superObject;
	public UserDefinedObject(PostalClass c,PostalEnvironment a) {
		super(c);
		environment = a;
		if(c.getSuperClass() != null)
			superObject = c.getSuperClass().postalNew();
	}
	public PostalObject getSuperObject() {
		return superObject;
	}
    

}
