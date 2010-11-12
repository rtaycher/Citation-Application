/*
 * CitationOutput.java
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
package citation.output;

import java.rmi.RemoteException;

import javax.xml.rpc.JAXRPCException;

import net.rim.device.api.system.RadioInfo;
import citation.comm.CitationCatcher_Stub;
import citation.data.Citation;
import citation.print.Printer;
import citation.print.PrinterException;


/**
 * CitationOutput class - this class provides a wrapper interface
 *                        for output methods.  Currently supports
 *                        output to a printer and for output of 
 *                        citation record to back-end server
 */
public class CitationOutput {

    public CitationOutput(){}

    
    /**
     * printCitation - print a citation to a zebra(?) printer
     * @param _record : Citation class to be printed
     */
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

    
    /**
     * submitCitation - send a citation object as an XML object
     *                  to a back end server.
     * @param _record : Citation object to transmit. 
     *                  Assumes class supports a toXMLString method
     */
    public void submitCitation(Object _record) 
    {
        int result = -1;
        
        // guard conditions - make sure _record is Citation 
        if (!(_record instanceof Citation)) {
            return;
        }
        
                // make sure that there is at least one active data service network
                if (!RadioInfo.isDataServiceOperational()) {
                        return;                 
                }

        Citation c = (Citation) _record;

        CitationCatcher_Stub serverObj = new CitationCatcher_Stub();

        try {
            // send it off to the server
            result = serverObj.postCitationAsXML(c.toXMLString());
        }
        catch (JAXRPCException e) {
                result = -1;
        }
        catch (RemoteException e) {
            result = -1;
        }

        // successfully sent - flag the citation as already submitted
        if (0 == result) {
            c.setSubmitted(true);
        }
    }

}
