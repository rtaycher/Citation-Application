/*
 * CVehicle.java
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
 * 	CVehicle 
 * 
 *  This class is a data storage object that contains the vehicular
 *  information of the citation incident.  
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

    	if (!isReadOnly()) {
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
	public String toPrettyString()
    {
        return "Type: "+ getElement(TYPE) + "\n" +
	        "Plate: "+ getElement(PLATE) + "\n" +
	        "Registration State: "+ getElement(REG_STATE) + "\n" +
	        "Make of Car: "+ getElement(MAKE) + "\n" +
	        "Model of Car: "+ getElement(MODEL) + "\n" +
	        "Style of Car: "+ getElement(STYLE) + "\n" +
	        "Year: "+ getElement(YEAR) + "\n" +
	        "Cars primary color: "+ getElement(PRIM_COLOR) + "\n" +
	        "Cars secondary color: "+ getElement(SEC_COLOR) + "\n" +
	        "Car's vin: "+ getElement(VIN) + "\n";
        }
    
	/**
	 * toXMLString - format this citation to a XML string for transmission to a server
	 */
    public String toXMLString()
    {
        return
        "<Vehicle>" + "\n" +
	        "<TYPE>" + getElement(TYPE) + "</TYPE>" + "\n" + 
	        "<PLATE>" + getElement(PLATE) + "</PLATE>" + "\n" +
	        "<REG_STATE>" + getElement(REG_STATE) + "</REG_STATE>" + "\n" +
	        "<MAKE>" + getElement(MAKE) + "</MAKE>" + "\n" +
	        "<MODEL>" + getElement(MODEL) + "</MODEL>" + "\n" +
	        "<STYLE>" + getElement(STYLE) + "</STYLE>" + "\n" +
	        "<YEAR>" + getElement(YEAR) + "</YEAR>" + "\n" +
	        "<PRIM_COLOR>" + getElement(PRIM_COLOR) + "</PRIM_COLOR>" + "\n" +
	        "<SEC_COLOR>" + getElement(SEC_COLOR) + "</SEC_COLOR>" + "\n" +
	        "<VIN>" + getElement(VIN) + "</VIN>" + "\n" +
        "</Vehicle>" + "\n";
    }
  
}
    
 
