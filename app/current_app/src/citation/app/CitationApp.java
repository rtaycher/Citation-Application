/*
 * CitationApp.java
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

package citation.app;

import citation.query.QueryMgr;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;

/**
 * class CitationApp: This class launches the first screen of the UI and passes the first
 * citation parameter with a null value. 
 */
public class CitationApp extends UiApplication {
    
    private QueryMgr query = null;
    
     /**
      * This is the main method for the CitationApp class. It handles the enterEventDispatcher call which
      * launches the application.
      * @param args - default String arguments passed to main method
      */
    public static void main(String[] args) {
        CitationApp theApp = new CitationApp();
        theApp.enterEventDispatcher();
    }
    
     /**
      * This is the constructor method for the CiationApp class which handles the declaration of a query 
      * object to be used in the citation UI screens.
      */
    public CitationApp () {
        // create the query mgr at startup, this will invoke the gps listener 
        query = new QueryMgr();
        pushScreen(new CitationScreen1(null));
    }
    
     /**
      * This method returns a pointer to the query object.
      * @return pointer to a QueryMgr object
      */
    public QueryMgr getQueryMgr()
    {
        return query;
    }
    
     /**
      * onClose: This method is called upon exit from the citation application.
      * @return boolean value
      */
    public boolean onClose() {
          Dialog.alert("Goodbye!");
          System.exit(0);
          return true;
    }       
        
}
