/*
 * CitationScreen1.java
 *
 * � <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package citation.app;

import java.util.Vector;
import net.rim.device.api.system.*;

import citation.data.*;
import citation.ui.CustomLabelField;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
 
class newCitationScreen1 extends MainScreen {

    private Citation c = null;   
    private ListField list;
    
    
    public newCitationScreen1(Citation _c){
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
        
        CustomLabelField lf = new CustomLabelField("************** SELECT A CITATION ***************", Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH);
        lf.setBackgroundColor(Color.WHITE);
        lf.setTextColor(Color.BLUE);
        
        FontFamily fontFamily3[] = FontFamily.getFontFamilies(); 
        Font fnt = fontFamily3[1].getFont(FontFamily.CBTF_FONT, 12).derive(Font.BOLD | Font.ITALIC); 
        lf.setFont(fnt);
        add(lf);
        add(new SeparatorField());

        // Listfield 
        list = new ListField();
        list.setEmptyString("No Stored Citations Available", DrawStyle.LEFT);
        
        int citation_count = CitationStore.getCitationCount();          //----get previous citations
        list.setSize(citation_count);
        TestListCallback newCallback = new TestListCallback();
        list.setCallback(newCallback);
        add(list);
        
        add(new SeparatorField());

        CustomLabelField lf2 = new CustomLabelField("NOTE: Use the menu to either Open a previous citation, or to create a new one.", Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH);
        lf2.setBackgroundColor(Color.WHITE);
        lf2.setTextColor(Color.GREEN);
        
        FontFamily fontFamily4[] = FontFamily.getFontFamilies(); 
        Font fnt2 = fontFamily4[1].getFont(FontFamily.CBTF_FONT, 12).derive(Font.BOLD | Font.ITALIC); 
        lf2.setFont(fnt);
        add(lf2);


        addMenuItem(createNew);
        addMenuItem(openPrev);      
    }
      
    private MenuItem createNew = new MenuItem("New", 110, 10) {
        public void run() {
             Dialog.inform("Creating new citation..");                             
             c = CitationStore.getNewCitation();                     //---------------NEW CITATION
             UiApplication.getUiApplication().pushScreen(new CitationScreen2(c));    
        }
    };

    private MenuItem openPrev = new MenuItem("Open", 110, 11) {
        public void run() {
            Dialog.inform("Retrieving citation..");
            int index = list.getSelectedIndex();
            c = CitationStore.getCitationByIndex(index);          
            UiApplication.getUiApplication().pushScreen(new CitationScreen2(c));
        }
    };  
    
    
} 

final class TestListCallbacknew implements ListFieldCallback {//TestListCallbacknew because TestListCallback is in use on the old Citation Screen 1, maybe we should get rid of the old screen
    public Citation _controller;
    
    public TestListCallbacknew (){
        _controller = new Citation();
    }
    
    public void drawListRow(ListField list, Graphics g, int index, int y, int w) {
        int count = CitationStore.getCitationCount();
        if(index >= count){
            return;
        }
  
        _controller = CitationStore.getCitationByIndex(index);   
        g.drawText("Citation # " + String.valueOf(index), 0, y, DrawStyle.LEFT, w);
    }
    
    public Object get(ListField listField, int index) {
        return (Object)CitationStore.List.elementAt(index);
    }
    public int getPreferredWidth(ListField listField) {
        return Graphics.getScreenWidth();
    }
    public int indexOfList(ListField listField, String prefix, int start) {
        return listField.indexOfList(prefix, start);
    }
}
