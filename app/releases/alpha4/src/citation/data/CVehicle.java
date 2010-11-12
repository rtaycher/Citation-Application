/*
 * CVehicle.java
 *
 * © <your company here>, 2003-2008
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
	
    public CVehicle() {  
        _elements = new Vector(ELEMENT_COUNT);
        for (int i = 0; i < _elements.capacity(); ++i) {
            _elements.addElement(new String(""));
        }
    }

    public String getElement(int id) {
        return (String) _elements.elementAt(id);
    }

    public void setElement(int id, String value) {
        _elements.setElementAt(value, id);
    }
}
    
 