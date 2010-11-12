/*
 * Citation.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package citation.com;

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;


/**
 * 
 */
public class CitationApp extends UiApplication {
    public static void main(String[] args) {
        CitationApp theApp = new CitationApp();
        theApp.enterEventDispatcher();
    }
    public CitationApp () {
        pushScreen(new CitationScreen1());
    }
       
    public boolean onClose() {
          Dialog.alert("Goodbye!");
          System.exit(0);
          return true;
    }        
}
