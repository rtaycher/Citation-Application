/*
 * Citation.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package citation.app;

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;

/**
 * 
 */
public class CitationApp extends UiApplication {
    
    
    public static void main(String[] args) {
        CitationApp theApp = new CitationApp();
        theApp.enterEventDispatcher();
    }
    
    public CitationApp () {
        pushScreen(new CitationScreen1(null));
    }
       
    public boolean onClose() {
          Dialog.alert("Goodbye!");
          System.exit(0);
          return true;
    }       
        
}
