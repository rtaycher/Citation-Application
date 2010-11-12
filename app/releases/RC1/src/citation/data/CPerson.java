/*
 * CPerson.java
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
 * 	CPerson 
 * 
 *  This class is a data storage object that contains personal
 *  information of the citation incident.  
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
    
    public static final int PERSON = 23;    		// part of the TYPE radio button group
    public static final int PERSON_SELECT = 24;    // part of the TYPE radio button group
    public static final int BUSINESS = 25;  		// part of the TYPE radio button group
    public static final int BUSINESS_SELECT = 26;    // part of the TYPE radio button group
    
    public static final int ELEMENT_COUNT = 27;

    private Vector _elements;
    private boolean readonly;
        
    public CPerson() { 
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
	  public String toPrettyString(){
		return "Type: "+ getElement(TYPE) + "\n" +
			"ID: "+ getElement(ID) + "\n" +
			"ID state: " + getElement(ID_STATE) + "\n" +
			"Title: " + getElement(TITLE) + "\n" +
			"First: " + getElement(FIRST) + "\n" +
			"Middle: " + getElement(MIDDLE) + "\n" + 
			"Last: " + getElement(LAST) + "\n" + 6 +
			"Suffix: " + getElement(SUFFIX) + "\n" + 
			"Address: " + getElement(ADDRESS) + "\n" + 
			"City: " + getElement(CITY) + "\n" + 
			"State: " + getElement(STATE) + "\n" + 
			"Zip: " + getElement(ZIP) + "\n" + 
			"Dob: " + getElement(DOB) + "\n" + 
			"Sex: " + getElement(SEX) + "\n" + 
			"Race: " + getElement(RACE) + "\n" + 
			"Eyes: " + getElement(EYES) + "\n" + 
			"Hair: " + getElement(HAIR) + "\n" + 
			"Endorsements: " + getElement(ENDORSEMENTS) + "\n" + 
			"Restrictions: " + getElement(RESTRICTIONS) + "\n" + 
			"Height: " + getElement(HEIGHT) + "\n" + 
			"Weight: " + getElement(WEIGHT) + "\n" + 
			"Observations: " + getElement(OBSERVATIONS) + "\n" + 
			"Number of Violations: " + getElement(NUM_OF_VIOLATIONS) + "\n" ;
    }
    
	  
	/**
	 * toXMLString - format this citation to a XML string for transmission to a server
	 */
    public String toXMLString(){
	    return "<Person>" + 
		    "<TYPE>" + getElement(TYPE) + "</TYPE>"+ "\n" +
		    "<ID>"+ getElement(ID) + "</ID>"+ "\n" +
		    "<ID_STATE>" + getElement(ID_STATE) + "</ID_STATE>"+ "\n" +
		    "<TITLE>" + getElement(TITLE) + "<TITLE>" + "\n"  +
		    "<FIRST>" + getElement(FIRST) + "<FIRST>" + "\n" +
		    "<MIDDLE>" + getElement(MIDDLE) +"<MIDDLE>" +  "\n" + 
		    "<LAST>" + getElement(LAST) + "<LAST>" + "\n" +
		    "<SUFFIX>" + getElement(SUFFIX) +"<SUFFIX>" + "\n" + 
		    "<ADDRESS>" + getElement(ADDRESS) + "<ADDRESS>" + "\n" + 
		    "<CITY>" + getElement(CITY) + "<CITY>" + "\n" + 
		    "<STATE>" + getElement(STATE) +"<STATE>" +  "\n" + 
		    "<ZIP" + getElement(ZIP) + "</ZIP>" + "\n" + 
		    "<DOB>" + getElement(DOB) + "</DOB>" + "\n" + 
		    "<SEX>" + getElement(SEX) + "</SEX>" + "\n" + 
		    "<RACE>" + getElement(RACE) + "</RACE>" + "\n" + 
		    "<EYES>" + getElement(EYES) + "</EYES>" + "\n" + 
		    "<HAIR>" + getElement(HAIR) + "</HAIR>" + "\n" + 
		    "<ENDORSEMENTS>" + getElement(ENDORSEMENTS)+ "</ENDORSEMENTS>" + "\n" + 
		    "<RESTRICTIONS>" + getElement(RESTRICTIONS) + "</RESTRICTIONS>" + "\n" + 
		    "<HEIGHT>" + getElement(HEIGHT) + "</HEIGHT>" + "\n" + 
		    "<WEIGHT>" + getElement(WEIGHT) + "</WEIGHT>" + "\n" + 
		    "<OBSERVATIONS>" + getElement(OBSERVATIONS) + "</OBSERVATIONS>" + "\n" + 
		    "<NUM_OF_VIOLATIONS>" + getElement(NUM_OF_VIOLATIONS) + "</NUM_OF_VIOLATIONS>" + "\n" + 
	    "</Person>";
    }
}
