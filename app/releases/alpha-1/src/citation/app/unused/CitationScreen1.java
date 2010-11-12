/*
 * CitationScreen1.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package citation.app;

import citation.data.*;
import citation.ui.CustomLabelField;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;

class CitationScreen1 extends MainScreen {

	public VerticalFieldManager listManager = null;
	private static final String NEW_CITATION = "<New Citation>";
    private Citation c = null;

    private static RadioButtonGroup rbGroup;
    private static RadioButtonField bf;
    
    
    
    public CitationScreen1(Citation _c){
        super();
        c = _c;
        // Setting page title
        CustomLabelField lf1 = new CustomLabelField("OREGON UNIFORM CITATION & COMPLAINT", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        lf1.setBackgroundColor(Color.GREEN);
        lf1.setTextColor(Color.WHITE);
        
        FontFamily fontFamily[] = FontFamily.getFontFamilies(); 
        Font font1 = fontFamily[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf1.setFont(font1); 

        setTitle(lf1);
        
        add(new SeparatorField());
        
        CustomLabelField lf = new CustomLabelField("**** SELECT A CITATION *****", Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH);
        lf.setBackgroundColor(Color.WHITE);
        lf.setTextColor(Color.BLUE);
        
        FontFamily fontFamily3[] = FontFamily.getFontFamilies(); 
        Font fnt = fontFamily3[1].getFont(FontFamily.CBTF_FONT, 12).derive(Font.BOLD | Font.ITALIC); 
        lf.setFont(fnt);
        add(lf);
        add(new SeparatorField());
        
        
        listManager = new VerticalFieldManager();
        rbGroup = new RadioButtonGroup();
        

        bf = new RadioButtonField(NEW_CITATION, rbGroup, false);
        listManager.add(bf);
        
        int citation_count = CitationStore.getCitationCount();		//----get previous citations
        for (int i = 0; i < citation_count; i++ ) {
        	Citation c = CitationStore.getCitationByIndex(i);
        	if ( c != null) {
        		listManager.add(new RadioButtonField(Integer.toString(c.Number), rbGroup, false)); 
        	}
        }
    	add(listManager);
              
         // ButtonField row - set all buttons at the bottom row of the form
        HorizontalFieldManager navButtonManager = new HorizontalFieldManager();

        ButtonField btn_next;        
        btn_next = new ButtonField("Next", ButtonField.CONSUME_CLICK);
        btn_next.setChangeListener(new ButtonListener(this, c)); 
        navButtonManager.add(btn_next);

        // add the button row to the main screen form
        add(new SeparatorField());
        add(navButtonManager);
        add(new SeparatorField());       
    }
    
    
    
    public void refresh_count(){   	
    	//listManager = new VerticalFieldManager();			
        //rbGroup = new RadioButtonGroup();
        
        int index = 0;       
        int citation_count = CitationStore.getCitationCount();		//----get previous citations
        for (int i = index; i < citation_count; i++ ) {
        	Citation c = CitationStore.getCitationByIndex(i);
        	if ( c != null) {
        		listManager.add(new RadioButtonField(Integer.toString(c.Number), rbGroup, false)); 
        	}
        }
    	//add(listManager);
    	//updateDisplay();
    }
    
    
    
    static final class ButtonListener implements FieldChangeListener {
        CitationScreen1 cs1;
        Citation c;
                    
        public ButtonListener(CitationScreen1 _cs1, Citation n) {
        	cs1 = _cs1;
            c = n;
        }
                    
        public void fieldChanged(Field field, int context) {
        	int count = cs1.listManager.getFieldCount();
        	RadioButtonField rbf = null;
        	for ( int i = 0; i < count; i++)
        	{
        		rbf = (RadioButtonField)cs1.listManager.getField(i);
        		if ( rbf.isSelected() )
        			break;
        	}

        	if (rbf != null) {
        		String selected = (String)rbf.getLabel();
        		if (selected.equals(NEW_CITATION)) {
        			Dialog.inform("Creating new citation..");
        			
        			c = CitationStore.getNewCitation();			//---------------NEW CITATION
        			cs1.refresh_count();
        		}
        		else {
        			Dialog.inform("Retrieving citation..");
        			c = CitationStore.getCitation(Integer.parseInt(selected));
        		}
        	}
        	
            UiApplication.getUiApplication().pushScreen(new CitationScreen2(c));
        }
    }

} 
