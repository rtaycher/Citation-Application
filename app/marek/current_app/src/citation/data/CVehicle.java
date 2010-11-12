/*
 * CVehicle.java
 *
 * � <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package citation.data;

import java.util.Vector;
import net.rim.device.api.util.Persistable;


/**
 * 
 */
public class CVehicle implements Persistable {
        
    public static final int TYPE = 0;
    public static final int PLATE = 1;
    public static final int REG_STATE = 2;
    public static final int MAKE = 3;
    public static final int MODEL = 4;
    public static final int STYLE = 5;
    public static final int YEAR = 6;
    public static final int PRIM_COLOR = 7;
    public static final int SEC_COLOR = 8;
    public static final int VIN = 9;
    public static final int ELEMENT_COUNT = 10;
        
    private Vector _elements;
    private boolean readonly;
        
    public CVehicle() {  
    	setReadOnly(false);
        _elements = new Vector(ELEMENT_COUNT);
        for (int i = 0; i < _elements.capacity(); ++i) {
            _elements.addElement(new String(""));
        }
    }

    public String getElement(int id) {
        return (String) _elements.elementAt(id);
    }

    public void setElement(int id, String value) {
    	if (!isReadOnly()) {
    		_elements.setElementAt(value, id);
    	}
    }
    
    public void setReadOnly(boolean readonly) {
		this.readonly = readonly;
	}

	public boolean isReadOnly() {
		return readonly;
	}

	public String toPrettyString()
    {
        return "Type: "+ getElement(0) + "\n" +
        "Plate: "+ getElement(1) + "\n" +
        "Registration State: "+ getElement(2) + "\n" +
        "Make of Car: "+ getElement(3) + "\n" +
        "Model of Car: "+ getElement(4) + "\n" +
        "Style of Car: "+ getElement(5) + "\n" +
        "Year: "+ getElement(6) + "\n" +
        "Cars primary color: "+ getElement(7) + "\n" +
        "Cars secondary color: "+ getElement(8) + "\n" +
        "Car's vin: "+ getElement(9) + "\n";
        }
    
    public String toXMLString()
    {
        return
        "<CVehicle>" + "\n" +
        "<NUM_OF_PERSONS>" + getElement(0) + "</NUM_OF_PERSONS>" + "\n" + 
        "<NUM_OF_VEHICLES>" + getElement(1) + "</NUM_OF_VEHICLES>" + "\n" +
        "<ADDRESS>" + getElement(2) + "</ADDRESS>" + "\n" +
        "<CITY>" + getElement(3) + "</CITY>" + "\n" +
        "<STATE>" + getElement(4) + "</STATE>" + "\n" +
        "<ZIP>" + getElement(5) + "</ZIP>" + "\n" +
        "<HIGHWAY>" + getElement(6) + "</HIGHWAY>" + "\n" +
        "<COMMONPLACE>" + getElement(7) + "</COMMONPLACE>" + "\n" +
        "</CVehicle>" + "\n";
    }
  
}
    
 
