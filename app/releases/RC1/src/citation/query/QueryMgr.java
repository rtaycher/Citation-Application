/*
 * QueryMgr.java
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
package citation.query;

import java.rmi.RemoteException;

import javax.xml.rpc.JAXRPCException;

import net.rim.device.api.io.SocketConnectionEnhanced;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.CoverageInfo;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.api.ui.component.Status;

import citation.comm.CitationCatcher_Stub;
import citation.data.*;


/**
 * QueryMgr - provides a top level manager class for performing query
 *            look ups through a back-end server.  Uses auto-generated
 *            client stubs generated with JSR172 WSDL files 
 *            (citation.comm package)
 *            
 */
public class QueryMgr implements IServerQuery {
        
        private GPSLocation gps = null;
        private String lastError = "";
        
        
        /**
         * QueryMgr constructor - creates the default GPSLocation object and auto
         *                        starts the GPS update listener
         */
        public QueryMgr()
        {       
                gps = new GPSLocation();
                // initialize location listener
                gps.startGPSListener();
        }
        

        /**
         * getLastError - get the error result of the last method called
         * @return - string of the last error condition - or empty if no errors 
         *           have occurred.
         */
        public String getLastError()
        {
                return lastError;
        }
        
        
        /**
         * getGPSLocationObj - retrieve the current GPS location.  Can be used
         *                     to determine current device coordinates.  
         * @return - GPSLocation class
         */
        public Object getGPSLocationObj()
        {
                return gps;
        }

        
        /**
         * findDMVRecord - retrieves a DMV record for a person from a Web service
         * @param _license - a string of the license number to lookup
         * @return - CPerson class with relevant user information entered
         */
        public Object findDMVRecord(String _license) 
        {
            CPerson cp = null;  // this is the return object
            String xml = null;
            
            // make sure that there is at least one active data service network
            if (!RadioInfo.isDataServiceOperational()) {
                    lastError = new String("No Data Service Available");
                    return null;                    
            }
                
            // get the request from the server
            CitationCatcher_Stub serverObj = new CitationCatcher_Stub();
            try {
                    xml = serverObj.getDMVResultAsXML(_license);
            } 
            catch (JAXRPCException e) {
                    lastError = e.getMessage();
            }
            catch (RemoteException e) {
                    lastError = e.getMessage();
            }
            
            // fill out a person object from the returned DMV lookup results
            if (xml != null)
            {
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
            return cp;
        }

        
        /**
         * getCourtInfo - return a court date and address information from the Web Service
         * @param - none
         * @return - CViolation class with relevant court information entered
         */
        public Object getCourtInfo() 
        {
                CViolation cv = null;  // this is the return object that has the necessary court information embedded
                String xml = null;    // unused (non-court info) members are left emtpy
                
                // make sure that there is at least one active data service network
                if (!RadioInfo.isDataServiceOperational()) {
                        lastError = new String("No Data Service Available");
                        return null;                    
                }

                // get the request from the server
                CitationCatcher_Stub serverObj = new CitationCatcher_Stub();
                try {
                        xml = serverObj.getCourtInfoAsXML();
                }
                catch (JAXRPCException e) {
                        lastError = e.getMessage();
                }
                catch (RemoteException e) {
                        lastError = e.getMessage();
                }
        
                // fill out a violation object from the returned court lookup results
                if (xml != null)
                {
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
                return cv;
        }

        public Object getVehicleInfo(String _type, String _id, String _state){
            CVehicle cv = null;  // this is the return object
        
            // get the request from the server
            CitationCatcher_Stub serverObj = new CitationCatcher_Stub();
            //try{
                    //String xml = serverObj.getVehicleAsXML(_type, _id, _state);
                    // Dummy implementation.
                    String xml = null;
                    if (xml == null){
                        cv = new CVehicle();
                        XMLStringParser parser = new XMLStringParser(xml);
                        cv.setElement(CVehicle.MAKE, parser.getNamedElement("Make"));
                        cv.setElement(CVehicle.MODEL, parser.getNamedElement("Model"));
                        cv.setElement(CVehicle.STYLE, parser.getNamedElement("Style"));
                        cv.setElement(CVehicle.YEAR, parser.getNamedElement("Year"));
                        cv.setElement(CVehicle.PRIM_COLOR, parser.getNamedElement("Color1"));
                        cv.setElement(CVehicle.SEC_COLOR, parser.getNamedElement("Color2"));
                        cv.setElement(CVehicle.VIN, parser.getNamedElement("VIN"));
                    }
            /*}
            catch (RemoteException e) {
                    // TODO Auto-generated catch block - need to add error recovery
                    e.printStackTrace();
            }*/
            return cv;
        }
}
