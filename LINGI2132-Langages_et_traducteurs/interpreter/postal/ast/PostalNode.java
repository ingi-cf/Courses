
package postal.ast;

import postal.environment.PostalEnvironment;

/*
 * Represents any node used in our ast representation
 */
public abstract class PostalNode
{

    public abstract ElementNode execute(PostalEnvironment e);
}
