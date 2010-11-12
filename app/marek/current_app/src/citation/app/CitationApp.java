/*
 * Citation.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package citation.app;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import citation.query.QueryMgr;

/**
 * 
 */
public class CitationApp extends UiApplication {

    private QueryMgr query = null;

    public static void main(String[] args) {
        CitationApp theApp = new CitationApp();
        theApp.enterEventDispatcher();
    }

    public CitationApp() {
        // create the query mgr at startup, this will invoke the gps listener
        query = new QueryMgr();
        pushScreen(new CitationScreen1(null));
    }

    public QueryMgr getQueryMgr() {
        return query;
    }

    public boolean onClose() {
        Dialog.alert("Goodbye!");
        System.exit(0);
        return true;
    }
}
