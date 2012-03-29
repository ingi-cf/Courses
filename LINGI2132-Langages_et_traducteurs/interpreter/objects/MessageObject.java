public class MessageObject extends PostalObject
{
    String                      messagename;
    LinkedList<PostalObject>    parameters;
    NodeMessage(String messagename)
    {
        this.messagename = messagename;
        parameters = new LinkedList<PostalObject>();
    }
    public void addParameter(PostalObject o)
    {
        parameters.add(o);
    }
}
