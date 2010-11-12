/*
 * Citation.java
 *
 * � <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package citation.data;

import net.rim.device.api.util.Persistable;

/**
 *  Main Citation class.  
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

	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}

	public boolean isSubmitted() {
		return submitted;
	}

	
	public String toPrettyString()
    {
    	return "Citation: \n" + "Personal Information: \n"+ Person.toPrettyString() + "\n"+
    		"Vehicle Information: \n" + Vehicle.toPrettyString() +  "\n"+
    		"Location of Incedent: \n" + Loc.toPrettyString() +  "\n"+
    		"Violation Information: \n" + Violation.toPrettyString() + "\n";
    }
    public String toXMLString()
    {
        String header= "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "\n";
        String citeXML= "<Citation>" + 
        Person.toXMLString()  + Vehicle.toXMLString() +Loc.toXMLString() + Violation.toXMLString() +
        "</Citation>";
        return header + citeXML ;
    }

    
}
 
