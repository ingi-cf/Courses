
package postal.ast;

import postal.environment.PostalEnvironment;

public abstract class PostalNode
{

    public abstract ElementNode execute(PostalEnvironment e);
}
