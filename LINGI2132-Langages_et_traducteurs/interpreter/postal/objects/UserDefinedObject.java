package postal.objects;

import postal.classes.PostalClass;
import postal.environment.PostalEnvironment;

/*
 * representation of objects defined within postal
 */
public class UserDefinedObject extends PostalObject {
	
	//object attributes
	PostalEnvironment attributes;
	PostalObject superObject;
	public UserDefinedObject(PostalClass c,PostalEnvironment a) {
		super(c);
		attributes = a;
		superObject = c.getSuperClass().postalNew();
	}
	public PostalObject getSuperObject() {
		return superObject;
	}
    

}
