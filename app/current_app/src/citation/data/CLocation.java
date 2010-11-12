/*
 * CLocation.java
 *
 * Copyright (c) 2009-2010 PSU Capstone Team D
 * Scott Glazer, Cong Hoang, Ba Nguyen, Marek Dolgos,
 * Steve Phelps, Mark Smith, Roman Taycher
 *
 * Citation Application is free/open source software released under
 * the unmodified MIT/X11 license. A copy can be found in the
 * LICENSE file or at:
 *
 *     http://www.opensource.org/licenses/mit-license.php
 *
 */
package citation.data;

import java.util.Vector;
import net.rim.device.api.util.Persistable;


/**
 * 	CLocation 
 * 
 *  This class is a data storage object that contains location
 *  information of the citation incident.  
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
    
    /**
     * getElement - return the string value of the element at the enumerated location
     * @param id - enumerated element to get string value of.
     * @return - String value of enumerated element or empty string if not previously set
     */
    public String getElement(int id) {
    	
    	// provide parameter guard check
    	if (id < 0 || id >= ELEMENT_COUNT ) return null;
    	
        return (String) _elements.elementAt(id);
    }

    /**
     * setElement - set the enumerated element at id to the string value
     * 
     * @param id - enumerated element id to set
     * @param value - string value to store for this enumerated element
     */
    public void setElement(int id, String value) {
    	
    	// provide parameter guard check
    	if (id < 0 || id >= ELEMENT_COUNT ) return;

    	if (!isReadOnly() ) {
    		_elements.setElementAt(value, id);
    	}
    }
    
    /**
     * setReadOnly - set to a read only state
     *               
     * @param readonly - true/false
     */
	 public void setReadOnly(boolean readonly) {
		this.readonly = readonly;
	 }

	 public boolean isReadOnly() {
		return readonly;
	 }
   
	
	/**
	 * toPrettyString - format this citation to a user readable string format
	 */
    public String toPrettyString(){
        return "Number of People Invloved: "+ getElement(NUM_OF_PERSONS) + "\n" +
        	"Number of Vehicles Invloved: "+ getElement(NUM_OF_VEHICLES) + "\n" +
        	"Address: "+ getElement(ADDRESS) + "\n" +
        	"City: "+ getElement(CITY) + "\n" +
        	"State: "+ getElement(STATE) + "\n" +
        	"Zip: "+ getElement(ZIP) + "\n" +
        	"Highway: "+ getElement(HIGHWAY) + "\n" +
        	"Commonplace: "+ getElement(COMMONPLACE) + "\n"; 
    }

    
	/**
	 * toXMLString - format this citation to a XML string for transmission to a server
	 */
    public String toXMLString(){
        return
        	"<Location>" + "\n" +
        		"<NUM_OF_PERSONS>" + getElement(NUM_OF_PERSONS) + "</NUM_OF_PERSONS>" + "\n" + 
        		"<NUM_OF_VEHICLES>" + getElement(NUM_OF_VEHICLES) + "</NUM_OF_VEHICLES>" + "\n" +
        		"<ADDRESS>" + getElement(ADDRESS) + "</ADDRESS>" + "\n" +
        		"<CITY>" + getElement(CITY) + "</CITY>" + "\n" +
        		"<STATE>" + getElement(STATE) + "</STATE>" + "\n" +
        		"<ZIP>" + getElement(ZIP) + "</ZIP>" + "\n" +
        		"<HIGHWAY>" + getElement(HIGHWAY) + "</HIGHWAY>" + "\n" +
        		"<COMMONPLACE>" + getElement(COMMONPLACE) + "</COMMONPLACE>" + "\n" +
        	"</Location>" + "\n";
        }

} 
