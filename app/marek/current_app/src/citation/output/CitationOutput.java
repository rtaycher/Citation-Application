package citation.output;

import java.rmi.RemoteException;

import citation.comm.CitationCatcher_Stub;
import citation.data.Citation;
import citation.print.Printer;
import citation.print.PrinterException;

public class CitationOutput {

    public CitationOutput() {
    }

    public void printCitation(Object _record) {
        if (!(_record instanceof Citation))
            return;

        try {
            Printer printer = new Printer();
            printer.print((Citation) _record);
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

    public void submitCitation(Object _record) {
        if (_record.getClass() != Citation.class) {
            return;
        }

        Citation c = (Citation) _record;

        // send it off to the server
        CitationCatcher_Stub serverObj = new CitationCatcher_Stub();

        try {
            int result = serverObj.postCitationAsXML(c.toXMLString());

            // successfully sent - flag the citation as already submitted
            if (0 == result) {
                c.setSubmitted(true);
            }
        }

        catch (RemoteException e) {
            // TODO Auto-generated catch block - need to add error recovery
            e.printStackTrace();
        }
    }

}
