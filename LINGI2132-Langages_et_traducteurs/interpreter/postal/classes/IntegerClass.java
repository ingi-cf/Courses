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
		return new IntegerObject(value, this);
	}
	
    public PostalObject messageReceived(PostalObject o, MessageObject m)
    {
    	assert(o instanceof IntegerObject);
    	IntegerObject o1 = (IntegerObject) o;
        // UNARY OPERATOR
    	if(m.name().equals("minus"))
        {
            return new IntegerObject(-o1.value(), this);
        }
        // BINARY OPERATOR
        else
        {
            PostalObject o2 = m.param(0);
            if(m.name().equals("sum"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new IntegerObject(o1.value() + ((IntegerObject) o2).value(), this);
                }
                else 
                {
                    throw new TypeException("Trying to add a non integer to an integer.");
                }
            }
            else if(m.name().equals("difference"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new IntegerObject(o1.value() - ((IntegerObject) o2).value(), this);
                }
                else 
                {
                    throw new TypeException("Trying to subtract a non integer to an integer.");
                }
            }
            else if(m.name().equals("multiplication"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new IntegerObject(o1.value() * ((IntegerObject) o2).value(), this);
                }
                else 
                {
                    throw new TypeException("Trying to multiply a non integer to an integer.");
                }
            }
            else if(m.name().equals("division"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new IntegerObject(o1.value() / ((IntegerObject) o2).value(), this);
                }
                else 
                {
                    throw new TypeException("Trying to divide a non integer to an integer.");
                }
            }
            else if(m.name().equals("mod"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new IntegerObject(o1.value() % ((IntegerObject) o2).value(), this);
                }
                else 
                {
                    throw new TypeException("Trying to calculate the modulo of an integer with a non integer.");
                }
            }
            else if(m.name().equals("leq"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new BooleanObject(o1.value() <= ((IntegerObject) o2).value(), this);
                }
                else 
                {
                    throw new TypeException("Trying to do a binary operation (less or equal) with a non integer.");
                }
            }
            else if(m.name().equals("geq"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new BooleanObject(o1.value() >= ((IntegerObject) o2).value(), this);
                }
                else 
                {
                    throw new TypeException("Trying to do a binary operation (greater or equal) with a non integer.");
                }
            }
            else if(m.name().equals("eq"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new BooleanObject(o1.value() == ((IntegerObject) o2).value(), this);
                }
                else 
                {
                    throw new TypeException("Trying to do a binary operation (equivalent) with a non integer.");
                }
            }
            else if(m.name().equals("neq"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new BooleanObject(o1.value() != ((IntegerObject) o2).value(), this);
                }
                else 
                {
                    throw new TypeException("Trying to do a binary operation (not equivalent) with a non integer.");
                }
            }
            else if(m.name().equals("lt"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new BooleanObject(o1.value() < ((IntegerObject) o2).value(), this);
                }
                else 
                {
                    throw new TypeException("Trying to do a binary operation (lesser than) with a non integer.");
                }
            }
            else if(m.name().equals("gt"))
            {
                if(o2 instanceof IntegerObject)
                {
                   return new BooleanObject(o1.value() > ((IntegerObject) o2).value(), this);
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
