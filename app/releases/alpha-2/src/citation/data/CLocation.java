/*
 * CLocation.java
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
public class CLocation implements Persistable {
        
    public static final int NUM_OF_PERSONS = 0;
    public static final int NUM_OF_VEHICLES = 1;
    public static final int ADDRESS = 2;
    public static final int CITY = 3;
    public static final int STATE = 4;
    public static final int ZIP = 5;
    public static final int HIGHWAY = 6;
    public static final int COMMONPLACE = 7;
    public static final int ELEMENT_COUNT = 8;

    private Vector _elements;
        
    public CLocation() {    
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
