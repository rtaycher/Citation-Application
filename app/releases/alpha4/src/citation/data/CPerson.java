/*
 * Person.java
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
public class CPerson implements Persistable {
	
    public static final int TYPE = 0;
    public static final int ID = 1;
    public static final int ID_STATE = 2;
    public static final int TITLE = 3;
    public static final int FIRST = 4;
    public static final int MIDDLE = 5;
    public static final int LAST = 6;
    public static final int SUFFIX = 7;
    public static final int ADDRESS = 8;
    public static final int CITY = 9;
    public static final int STATE = 10;
    public static final int ZIP = 11;
    public static final int DOB = 12;
    public static final int SEX = 13;
    public static final int RACE = 14;
    public static final int EYES = 15;
    public static final int HAIR = 16;
    public static final int ENDORSEMENTS = 17;
    public static final int RESTRICTIONS = 18;
    public static final int HEIGHT = 19;
    public static final int WEIGHT = 20;
    public static final int OBSERVATIONS = 21;
    public static final int NUM_OF_VIOLATIONS = 22;
    public static final int ELEMENT_COUNT = 23;

    private Vector _elements;
	
    public CPerson() {  
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
