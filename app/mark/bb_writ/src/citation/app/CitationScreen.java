/*
 * CitationScreen1.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package citation.app;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import citation.data.*;
import citation.query.GPSLocation;
import citation.query.HTTPGoogleAPI;
import citation.query.QueryMgr;
import citation.ui.MapButtonField;
import citation.ui.NavButtonField;
import citation.xml.reader.XML2UI;



class CitationScreen extends MainScreen {
        
    private XML2UI formPage;
    private CitationRecord citation;
    private QueryMgr query;
    public GPSLocation currentLocation;  
	private HorizontalFieldManager navButtonManager = new HorizontalFieldManager();

    public CitationScreen(XML2UI _formPage)
    {
        super();
          
        this.formPage = _formPage;
        citation = new CitationRecord();
        query = new QueryMgr();
        
        // TODO get the current location on a new thread by making it runnable and then invoke it
        //      could also add a listener so that the GPSLocation is constantly updated with position
        // get the current location at start-up.
        currentLocation = (GPSLocation)query.getCurrentLocation();
        if ( currentLocation.isValid() ) {
        	String location = HTTPGoogleAPI.ReverseGeoQuery(currentLocation.getCoordinates().getLatitude(), currentLocation.getCoordinates().getLongitude());
        	String address = HTTPGoogleAPI.parseMapResult(location, HTTPGoogleAPI.ParseType.ADDRESS);
        }
        
        // get the title field
        LabelField fm_title = formPage.getTitle();
        
        // Setting page title
        setTitle(fm_title);
        
        // get the first field and then iterate through all the fields for this page
        Field form_field = formPage.getNextField();
        while (form_field != null)
        {           
            // If it's a NavButtonfield, register a listener and add to the horizontal manager
            Class nb = form_field.getClass();
            if ( nb.getName().equals("citation.ui.NavButtonField"))
            {
            	navButtonManager.add(form_field);
            	form_field.setChangeListener((new NavButtonListener(this)));
            	// nav button will added to bottom of form
            } 
            else if ( nb.getName().equals("citation.ui.MapButtonField"))
            {
            	form_field.setChangeListener((new MapButtonListener(this)));
            	add(form_field);
            }
            else 
            {
                add(form_field);            	
            }
            
            form_field = formPage.getNextField();
        }
        
        // add the final nav button manager
        add(navButtonManager);
    }
    
    final class NavButtonListener implements FieldChangeListener {
        private CitationScreen currentScreen;
                    
        public NavButtonListener(CitationScreen n){
        	currentScreen = n;
        }

		public void fieldChanged(Field field, int context) {
	    
			NavButtonField btn = (NavButtonField) field;
            if( btn != null ){ 
            	String next_page = btn.getNextPage();
            	formPage.setFormPage(next_page);
            	
            	//TODO: problem - must fix - creating a new screen every time...
            	//      not saving data when navigating between screens
            	//      need to either save data and rebind later or locate screen of interest on stack
                UiApplication.getUiApplication().pushScreen(new CitationScreen(formPage));
            }
		}
    }
    
    final class MapButtonListener implements FieldChangeListener {
        private CitationScreen cs;
        
        public MapButtonListener(CitationScreen n){
        	cs = n;
        }
        
    	public void fieldChanged(Field field, int context) {
    		MapButtonField btn = (MapButtonField)field;
    		if (btn!=null) {
    	        if (cs.currentLocation != null && cs.currentLocation.isValid()) {
    	        	btn.setCoordinates(cs.currentLocation.getCoordinates());
    	        	btn.run();
    	        }
    	        else {
    	        	Dialog.inform("Unable to determine current location.");
    	        }
    		}
    	}
    }
         
} 
