package postal.ast;
import postal.environment.PostalEnvironment;
import postal.objects.PostalObject;
public interface ElementNode
{
    public PostalObject resolve(PostalEnvironment e);
    
}
