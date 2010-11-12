/*
 * CitationScreen1.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package citation.app;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.NumericChoiceField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.RadioButtonField;
import net.rim.device.api.ui.component.RadioButtonGroup;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import citation.data.*;
import citation.ui.NavButtonField;
import citation.xml.reader.XML2UI;



class CitationScreen extends MainScreen {
        
    private XML2UI formPage;
    private CitationRecord citation;
	private HorizontalFieldManager navButtonManager = new HorizontalFieldManager();

    public CitationScreen(XML2UI _formPage)
    {
        super();
          
        this.formPage = _formPage;
        citation = new CitationRecord();
        
          
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
         
} 
