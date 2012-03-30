package postal.classes;

import postal.exceptions.TypeException;
import postal.objects.BooleanObject;
import postal.objects.IntegerObject;
import postal.objects.MessageObject;
import postal.objects.PostalObject;

public class IntegerClass extends PostalClass
{
	
	public PostalObject postalNew(int value)
	{
		return new IntegerObject(value);
	}
	
	public PostalObject postalNew()
	{
		return new IntegerObject(0);
	}
	
    public PostalObject messageReceived(PostalObject o, MessageObject m)
    {
    	assert(o instanceof IntegerObject);
    	IntegerObject o1 = (IntegerObject) o;
        // UNARY OPERATOR
    	if(m.getName().equals("minus"))
        {
            return new IntegerObject(-o1.value());
        }
        // BINARY OPERATOR
        else
        {
            PostalObject o2 = m.param(0);
            if(m.getName().equals("sum"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new IntegerObject(o1.value() + ((IntegerObject) o2).value());
                }
                else 
                {
                    throw new TypeException("Trying to add a non integer to an integer.");
                }
            }
            else if(m.getName().equals("difference"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new IntegerObject(o1.value() - ((IntegerObject) o2).value());
                }
                else 
                {
                    throw new TypeException("Trying to subtract a non integer to an integer.");
                }
            }
            else if(m.getName().equals("multiplication"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new IntegerObject(o1.value() * ((IntegerObject) o2).value());
                }
                else 
                {
                    throw new TypeException("Trying to multiply a non integer to an integer.");
                }
            }
            else if(m.getName().equals("division"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new IntegerObject(o1.value() / ((IntegerObject) o2).value());
                }
                else 
                {
                    throw new TypeException("Trying to divide a non integer to an integer.");
                }
            }
            else if(m.getName().equals("mod"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new IntegerObject(o1.value() % ((IntegerObject) o2).value());
                }
                else 
                {
                    throw new TypeException("Trying to calculate the modulo of an integer with a non integer.");
                }
            }
            else if(m.getName().equals("leq"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new BooleanObject(o1.value() <= ((IntegerObject) o2).value());
                }
                else 
                {
                    throw new TypeException("Trying to do a binary operation (less or equal) with a non integer.");
                }
            }
            else if(m.getName().equals("geq"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new BooleanObject(o1.value() >= ((IntegerObject) o2).value());
                }
                else 
                {
                    throw new TypeException("Trying to do a binary operation (greater or equal) with a non integer.");
                }
            }
            else if(m.getName().equals("eq"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new BooleanObject(o1.value() == ((IntegerObject) o2).value());
                }
                else 
                {
                    throw new TypeException("Trying to do a binary operation (equivalent) with a non integer.");
                }
            }
            else if(m.getName().equals("neq"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new BooleanObject(o1.value() != ((IntegerObject) o2).value());
                }
                else 
                {
                    throw new TypeException("Trying to do a binary operation (not equivalent) with a non integer.");
                }
            }
            else if(m.getName().equals("lt"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new BooleanObject(o1.value() < ((IntegerObject) o2).value());
                }
                else 
                {
                    throw new TypeException("Trying to do a binary operation (lesser than) with a non integer.");
                }
            }
            else if(m.getName().equals("gt"))
            {
                if(o2 instanceof IntegerObject)
                {
                	
                   return new BooleanObject(o1.value() > ((IntegerObject) o2).value());
                }
                else 
                {
                    throw new TypeException("Trying to do a binary operation (grater than) with a non integer.");
                }
            }
            else
            {
                throw new TypeException("IntegerClass: Message not known.");
            }
        }
    }
}
