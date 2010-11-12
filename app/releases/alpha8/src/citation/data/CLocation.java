/*
 * CLocation.java
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
    private boolean readonly;

    public CLocation() { 
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
   
    public String toPrettyString(){
        return "Number of People Invloved: "+ getElement(0) + "\n" +
        	"Number of Vehicles Invloved: "+ getElement(1) + "\n" +
        	"Address: "+ getElement(2) + "\n" +
        	"City: "+ getElement(3) + "\n" +
        	"State: "+ getElement(4) + "\n" +
        	"Zip: "+ getElement(5) + "\n" +
        	"Highway: "+ getElement(6) + "\n" +
        	"Commonplace: "+ getElement(7) + "\n"; 
        }

    
    public String toXMLString(){
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
