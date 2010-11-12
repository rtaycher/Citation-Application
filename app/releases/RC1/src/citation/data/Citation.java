/*
 * Citation.java
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

import net.rim.device.api.util.Persistable;


/**
 *  Citation class.  
 *  Each Citation object contains the individual components of a violation
 *  including the person, location, vehicle and violation.
 *  
 *  The storage object saves off all citation and it's member classes automatically on Save()
 */
public class Citation implements Persistable {
        
    public int Number;
    public CLocation Loc;
    public CPerson Person;
    public CVehicle Vehicle;
    public CViolation Violation;
    // status flags
    private boolean readonly;
    private boolean submitted;
    
    public Citation () {
        Person = new CPerson();
        Vehicle = new CVehicle();
        Loc = new CLocation();
        Violation = new CViolation();
        
        setReadOnly(false);
        setSubmitted(false);
    }
 
    /**
     * setReadOnly - set this citation and all sub-classes to a read only state
     *               attempt to modify a read-only citation is treated as no-op
     *               Also, once set to read-only, no further edits are allowed in UI
     *               
     * @param readonly - true/false
     */
	public void setReadOnly(boolean readonly) {
		this.readonly = readonly;
		
		Loc.setReadOnly(readonly);
		Person.setReadOnly(readonly);
		Vehicle.setReadOnly(readonly);
		Violation.setReadOnly(readonly);
	}

	public boolean isReadOnly() {
		return readonly;
	}

	/**
	 * setSubmitted - set the submit state for this citation.  Once marked
	 *                as submitted, the user will not be given the option
	 *                to submit to the server again
	 *                
	 * @param submitted - true/false
	 */
	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}

	public boolean isSubmitted() {
		return submitted;
	}

	
	/**
	 * toPrettyString - format this citation to a user readable string format
	 */
	public String toPrettyString()
    {
    	return "Citation: \n" + "Personal Information: \n"+ Person.toPrettyString() + "\n"+
    		"Vehicle Information: \n" + Vehicle.toPrettyString() +  "\n"+
    		"Location of Incedent: \n" + Loc.toPrettyString() +  "\n"+
    		"Violation Information: \n" + Violation.toPrettyString() + "\n";
    }
	
	/**
	 * toXMLString - format this citation to a XML string for transmission to a server
	 */
    public String toXMLString()
    {
        String header= "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "\n";
        
        String citeXML= 
        	"<Citation>" + 
        		Person.toXMLString()  + Vehicle.toXMLString() +Loc.toXMLString() + Violation.toXMLString() +
        	"</Citation>";
        
        return header + citeXML ;
    }

    
}
 
