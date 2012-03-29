public class IntegerClass extends PostalClass
{
    
    public abstract messageReceived(MessageObjyect m);
    {
        if(m.name().equals("sum"))
            return new IntegerObject(m.param(0) + m.param(1));
     
    }
    
}
