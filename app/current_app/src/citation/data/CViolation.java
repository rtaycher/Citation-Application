/*
 * CViolation.java
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

import java.util.Date;
import java.util.Vector;
import net.rim.device.api.util.Persistable;


/**
 * 	CViolation 
 * 
 *  This class is a data storage object that contains the violation
 *  information of the citation incident.  
 */
public class CViolation implements Persistable {          
    public static final int CITATION_NO = 0;
    public static final int DATE = 1;
    public static final int TIME = 2;
    public static final int OFFENSES = 3;
    public static final int ACCIDENT = 4;
    public static final int RADAR = 5;
    public static final int JAIL_BOOKING = 6;
    public static final int PACED = 7;
    public static final int SCHOOL_ZONE = 8;
    public static final int ALCOHOL = 9;
    
    public static final int VBFI = 10;
    
    public static final int SPEED_LIMIT = 11;
    public static final int ALLEGED_SPEED = 12;
      
    public static final int COURT_DATE = 13;
    public static final int COURT_TIME = 14;
    
    public static final int BAC = 15;
    public static final int BAC_DATE = 16;     
    public static final int BAC_TIME = 17;  
    
    public static final int VIOLATION_TYPE = 18;

    public static final int NARRATIVE = 19;
    
    public static final int STATUTE = 20;
    public static final int FINE = 21;
    public static final int INTENTIONAL = 22;
    public static final int KNOWING = 23;
    public static final int RECKLESS = 24;
    public static final int CRIMINAL_NEG = 25;
    public static final int CULPABLE = 26;
    
    public static final int COURT_NAME = 27;
    public static final int COURT_ADDRESS = 28;
    public static final int COURT_CITY = 29;
    public static final int COURT_STATE = 30;
    public static final int COURT_ZIP = 31;
    public static final int COURT_PHONE = 32;
    
    public static final int ELEMENT_COUNT = 33;
   
    private Vector _elements;
    private boolean readonly;
        
    public CViolation() {  
    	setReadOnly(false);
        _elements = new Vector(ELEMENT_COUNT);
        for (int i = 0; i < _elements.capacity(); ++i) 
        {
                // initialize with current date-time values
                if (i == DATE || i == TIME || i == BAC_DATE || i == BAC_TIME) 
                {
                        Date date = new Date(System.currentTimeMillis());
                        _elements.addElement(date.toString());
                }
                else 
                {
                        _elements.addElement(new String(""));
                }
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
        return  "Citation Number: " + getElement(CITATION_NO) + "\n" +
	        "Date: " + getElement(DATE) + "\n" +
	        "Time: " + getElement(TIME) + "\n" +
	        "Offenses: " + getElement(OFFENSES) + "\n" +
	        "Type of Accident: " + getElement(ACCIDENT) + "\n" +
	        "Radar speed: " + getElement(RADAR) + "\n" +
	        "Are they being jailed: " + getElement(JAIL_BOOKING) + "\n" +
	        "Were they Paced" + getElement(PACED) + "\n" +
	        "Was it in a School Zone: " + getElement(SCHOOL_ZONE) + "\n" +
	        "Did they use Alchohol: " + getElement(ALCOHOL) + "\n" +
	        "VBFI: " + getElement(VBFI) + "\n" +
	        "Speed Limit: " + getElement(SPEED_LIMIT) + "\n" +
	        "Alleged Speed: " + getElement(ALLEGED_SPEED) + "\n" +
	        "Date of Court Hearing: " + getElement(COURT_DATE) + "\n" +
	        "Time of Court Hearing: " + getElement(COURT_TIME) + "\n" +
	        "Blood Alchol Content: " + getElement(BAC) + "\n" +
	        "Date of Blood Alchol Content test: " + getElement(BAC_DATE) + "\n" +
	        "Rime of Blood Alchol Content test: " + getElement(BAC_TIME) + "\n" +
	        "Type of Violation: " + getElement(VIOLATION_TYPE) + "\n" +
	        "Story/Narrative of what happened: " + getElement(NARRATIVE) + "\n" +
	        "Relavant Statue(s): " + getElement(STATUTE) + "\n" +
	        "Fine: " + getElement(FINE) + "\n" +
	        "Was it intentional?: " + getElement(INTENTIONAL) + "\n" +
	        "Was it done knowingly?: " + getElement(KNOWING) + "\n" +
	        "Were they Reckless: " + getElement(RECKLESS) + "\n" +
	        "Was it done with Criminal Negligence: " + getElement(CRIMINAL_NEG) + "\n" +
	        "Are they Culpable: " + getElement(CULPABLE) + "\n" +
	        "Court Name: " + getElement(COURT_NAME) + "\n" +
	        "Court Address: " + getElement(COURT_ADDRESS) + "\n" +
	        "Court City: " + getElement(COURT_CITY) + "\n" +
	        "Court State: " + getElement(COURT_STATE) + "\n" +
	        "Court Zip: " + getElement(COURT_ZIP) + "\n" +
	        "Court Phone: " + getElement(COURT_PHONE) + "\n";
        }
    
	/**
	 * toXMLString - format this citation to a XML string for transmission to a server
	 */
	public String toXMLString(){                                                                                                                                                                                         
        return                                                                                                           
        "<Violation>" + "\n" +                                                                        
	        "<CITATION_NO>" + getElement(CITATION_NO) + "</CITATION_NO>" + "\n" +                                                      
	        "<DATE>" + getElement(DATE) + "</DATE>" + "\n" +                                                                    
	        "<TIME>" + getElement(TIME) + "</TIME>" + "\n" +                                                                    
	        "<OFFENSES>" + getElement(OFFENSES) + "</OFFENSES>" + "\n" +                                                            
	        "<ACCIDENT>" + getElement(ACCIDENT) + "</ACCIDENT>" + "\n" +                                                            
	        "<RADAR>" + getElement(RADAR) + "</RADAR>" + "\n" +                                                                  
	        "<JAIL_BOOKING>" + getElement(JAIL_BOOKING) +"</JAIL_BOOKING>" + "\n" +                                                     
	        "<PACED>" + getElement(PACED) + "</PACED>" + "\n" +                                                                  
	        "<SCHOOL_ZONE>" + getElement(SCHOOL_ZONE) + "</SCHOOL_ZONE>" + "\n" +                                                      
	        "<ALCOHOL>" + getElement(ALCOHOL) + "</ALCOHOL>" + "\n" +                                                             
	        "<VBFI>" + getElement(VBFI) + "</VBFI>" + "\n" +                                                                   
	        "<SPEED_LIMIT>" + getElement(SPEED_LIMIT) + "</SPEED_LIMIT>" + "\n" +                                                     
	        "<ALLEGED_SPEED>" + getElement(ALLEGED_SPEED) + "</ALLEGED_SPEED>" + "\n" +                                                 
	        "<COURT_DATE>" + getElement(COURT_DATE) + "</COURT_DATE>" + "\n" +                                                       
	        "<COURT_TIME>" + getElement(COURT_TIME) + "</COURT_TIME>" + "\n" +                                                       
	        "<BAC>" + getElement(BAC) + "</BAC>" + "\n" +                                                                     
	        "<BAC_DATE>" + getElement(BAC_DATE) + "</BAC_DATE>" + "\n" +                                                           
	        "<BAC_TIME>" + getElement(BAC_TIME) + "</BAC_TIME>" + "\n" +                                                           
	        "<VIOLATION_TYPE>" + getElement(VIOLATION_TYPE) + "</VIOLATION_TYPE>" + "\n" +                                               
	        "<NARRATIVE>" + getElement(NARRATIVE) + "</NARRATIVE>" + "\n" +                                                         
	        "<STATUTE>" + getElement(STATUTE) + "</STATUTE>" + "\n" +                                                             
	        "<FINE>" + getElement(FINE) + "</FINE>" + "\n" +                                                                   
	        "<INTENTIONAL>" + getElement(INTENTIONAL) + "</INTENTIONAL>" + "\n" +                                                     
	        "<KNOWING>" + getElement(KNOWING) + "</KNOWING>" + "\n" +                                                             
	        "<RECKLESS>" + getElement(RECKLESS) + "</RECKLESS>" + "\n" +                                                           
	        "<CRIMINAL_NEG>" + getElement(CRIMINAL_NEG) +"</CRIMINAL_NEG>" + "\n" +                                                    
	        "<CULPABLE>" + getElement(CULPABLE) + "</CULPABLE>" + "\n" +   
	        "<COURT_NAME>" + getElement(COURT_NAME) + "</COURT_NAME>" + "\n" +
	        "<COURT_ADDRESS>" + getElement(COURT_ADDRESS) + "</COURT_ADDRESS>" + "\n" +
	        "<COURT_CITY>" + getElement(COURT_CITY) + "</COURT_CITY>" + "\n" +
	        "<COURT_STATE>" + getElement(COURT_STATE) + "</COURT_STATE>" + "\n" +
	        "<COURT_ZIP>" + getElement(COURT_ZIP) + "</COURT_ZIP>" + "\n" +
	        "<COURT_PHONE>" + getElement(COURT_PHONE) + "</COURT_PHONE>" + "\n" +
        "</Violation>" + "\n";                                                       
        }
}
