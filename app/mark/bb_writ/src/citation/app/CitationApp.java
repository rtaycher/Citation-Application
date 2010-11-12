/*
 * Citation.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package citation.app;

import citation.xml.reader.XML2UI;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;


/**
 * 
 */
public class CitationApp extends UiApplication {
    // Statics -------------------------------------------------------------------------------------
    private static String _xmlFileName = "../UIDef.xml";

   public static void main(String[] args) {
        CitationApp theApp = new CitationApp();
        theApp.enterEventDispatcher();
    }
   
    public CitationApp () {
        XML2UI form_page = new XML2UI(_xmlFileName);
        if (!form_page.isValid()) System.exit(-33);
        
        pushScreen(new CitationScreen(form_page));
    }
       
    public boolean onClose() {
          Dialog.alert("Goodbye!");
          System.exit(0);
          return true;
    }        
}
