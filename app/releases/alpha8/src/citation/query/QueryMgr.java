package citation.query;

import java.rmi.RemoteException;

import citation.comm.CitationCatcher_Stub;
import citation.data.*;

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

    public Object findDMVRecord(String _license){
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

    public Object getVehicleInfo(String _type, String _id, String _state){
        CPerson cp = null;  // this is the return object
        
        // get the request from the server
        CitationCatcher_Stub serverObj = new CitationCatcher_Stub();
        try {
            String xml = serverObj.getVehicleAsXML(_type, _id, _state);
            // fill out a person object
            cp = new CPerson();
            XMLStringParser parser = new XMLStringParser(xml);
            cp.setElement(CVehicle.MAKE, parser.getNamedElement("Make"));
            cp.setElement(CVehicle.MODEL, parser.getNamedElement("Model"));
            cp.setElement(CVehicle.STYLE, parser.getNamedElement("Style"));
            cp.setElement(CVehicle.YEAR, parser.getNamedElement("Year"));
            cp.setElement(CVehicle.PRIM_COLOR, parser.getNamedElement("Color1"));
            cp.setElement(CVehicle.SEC_COLOR, parser.getNamedElement("Color2"));
            cp.setElement(CVehicle.VIN, parser.getNamedElement("VIN"));
        } 
        catch (RemoteException e) {
            // TODO Auto-generated catch block - need to add error recovery
            e.printStackTrace();
        }
        return cp;
    }

    public Object getCourtInfo() {
        CViolation cv = null;   // this is the return object that has the necessary court information embedded
                                // unused (non-court info) members are left emtpy
                                // get the request from the server
        CitationCatcher_Stub serverObj = new CitationCatcher_Stub();
        try {
            String xml = serverObj.getCourtInfoAsXML();
        
            // fill out a person object
            cv = new CViolation();
            XMLStringParser parser = new XMLStringParser(xml);
                
            cv.setElement(CViolation.COURT_DATE, parser.getNamedElement("COURT_DATE"));
            cv.setElement(CViolation.COURT_NAME, parser.getNamedElement("COURT_NAME"));
            cv.setElement(CViolation.COURT_ADDRESS, parser.getNamedElement("COURT_ADDRESS"));
            cv.setElement(CViolation.COURT_CITY, parser.getNamedElement("COURT_CITY"));
            cv.setElement(CViolation.COURT_STATE, parser.getNamedElement("COURT_STATE"));
            cv.setElement(CViolation.COURT_ZIP, parser.getNamedElement("COURT_ZIP"));
            cv.setElement(CViolation.COURT_PHONE, parser.getNamedElement("COURT_PHONE"));
        }
        catch (RemoteException e) {
            // TODO Auto-generated catch block - need to add error recovery
            e.printStackTrace();
        }
        return cv;
    }
}
