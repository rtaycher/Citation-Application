package citation.output;

import java.rmi.RemoteException;
import java.util.Vector;

import citation.comm.CitationCatcher_Stub;
import citation.data.Citation;

public class CitationOutput implements ICitationOutput {

	public CitationOutput()
	{
	}

	public void printCitation(Object _record) {
		// TODO Auto-generated method stub
		
	}

	public void submitCitation(Object _record) {
		if (_record.getClass() != Citation.class )
		{
			return;
		}
		
		Citation c = (Citation)_record;
		
        // send it off to the server
        CitationCatcher_Stub serverObj = new CitationCatcher_Stub();
        try {
        	serverObj.postCitationAsXML(c.toXMLString());
        } 
        catch (RemoteException e) {
        	// TODO Auto-generated catch block - need to add error recovery
        	e.printStackTrace();
        }
	}
	
}
