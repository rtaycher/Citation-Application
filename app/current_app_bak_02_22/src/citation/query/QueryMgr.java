package citation.query;

import java.rmi.RemoteException;

import citation.comm.CitationCatcher_Stub;
import citation.data.CPerson;

public class QueryMgr implements IServerQuery {
	
	private GPSLocation gps = null;
	
	public QueryMgr()
	{	
		gps = new GPSLocation();
		// initialize location listener
		gps.startGPSListener();
	}

	public Object getGPSLocationObj()
	{
		return gps;
	}

	public Object findDMVRecord(String _license) 
	{
		CPerson cp = null;  // this is the return object
		
        // get the request from the server
        CitationCatcher_Stub serverObj = new CitationCatcher_Stub();
        try {
        	String xml = serverObj.getDMVResultAsXML(_license);
        	// fill out a person object
        	cp = new CPerson();
        	XMLStringParser parser = new XMLStringParser(xml);
        	cp.setElement(CPerson.LAST, parser.getNamedElement("Lname"));
        	cp.setElement(CPerson.MIDDLE, parser.getNamedElement("Mname"));
        	cp.setElement(CPerson.FIRST, parser.getNamedElement("Fname"));
        	cp.setElement(CPerson.ADDRESS, parser.getNamedElement("Address"));
        	cp.setElement(CPerson.CITY, parser.getNamedElement("City"));
        	cp.setElement(CPerson.ZIP, parser.getNamedElement("Zip"));
        	cp.setElement(CPerson.DOB, parser.getNamedElement("DOB"));
        	cp.setElement(CPerson.SEX, parser.getNamedElement("Sex"));
        	cp.setElement(CPerson.HEIGHT, parser.getNamedElement("Height"));
        	cp.setElement(CPerson.WEIGHT, parser.getNamedElement("Weight"));
        	cp.setElement(CPerson.ENDORSEMENTS, parser.getNamedElement("Endorsements"));
        	cp.setElement(CPerson.RESTRICTIONS, parser.getNamedElement("Restrictions"));
       } 
        
        catch (RemoteException e) {
        	// TODO Auto-generated catch block - need to add error recovery
        	e.printStackTrace();
        }
        
		return cp;
	}
}
