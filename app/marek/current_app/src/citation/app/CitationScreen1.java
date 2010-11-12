/*
 * CitationScreen1.java
 *
 * <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package citation.app;

import java.rmi.RemoteException;
import java.util.Vector;
import net.rim.device.api.system.*;
import citation.data.*;
//import citation.comm.CitationCatcher_Stub; does this exist?
import citation.ui.CustomLabelField;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
//import net.rim.device.api.ui.decor.BackgroundFactory;    <- not available until Blackberry JDE 4.6.0

class CitationScreen1 extends MainScreen {

    private Citation c = null;   
    private ListField list;
       
    public CitationScreen1(Citation _c){
        //super();
        super(NO_VERTICAL_SCROLL);
        String bgImage = "img/badge.png";    
        final Bitmap _backgroundBitmap = Bitmap.getBitmapResource(bgImage);  
        
        VerticalFieldManager verticalManager = null;

        //rather than  adding component in the mainScreen add components
        //in this verticalManager and then add this manager to mainScreen  
        verticalManager = new VerticalFieldManager(
                          Manager.VERTICAL_SCROLL | 
                          Manager.VERTICAL_SCROLLBAR)
        {
            public void paint(Graphics graphics)
            {
               //blue 
               //graphics.setBackgroundColor(0x000000ff);
               //graphics.clear();
               //super.paint(graphics);
                if ( _backgroundBitmap != null )
                {
                       graphics.clear();
                       graphics.drawBitmap( (this.getWidth() / 2) - (_backgroundBitmap .getWidth() /2),
                                   (this.getHeight() / 2) - (_backgroundBitmap .getHeight() /2),
                                   _backgroundBitmap .getWidth(),
                                   _backgroundBitmap .getHeight(),
                                   _backgroundBitmap ,0,0);
                       subpaint(graphics);
                }
            }            
            protected void sublayout( int maxWidth, int maxHeight )
            {
               int width = Display.getWidth();
               int height = Display.getHeight();
               super.sublayout( width, height);
               setExtent( width, height);
            }
        };
        this.add(verticalManager);        
              
        c = _c;
/*
        CustomLabelField lf1 = new CustomLabelField("OREGON UNIFORM CITATION & COMPLAINT", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH | DrawStyle.HCENTER);
        lf1.setBackgroundColor(Color.GREEN);
        lf1.setTextColor(Color.WHITE);
        
        FontFamily fontFamily[] = FontFamily.getFontFamilies(); 
        Font font1 = fontFamily[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf1.setFont(font1); 

        setTitle(lf1);
*/        
        
      LabelField lf1 = new LabelField("OREGON UNIFORM CITATION & COMPLAINT", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH | DrawStyle.HCENTER)
        {
            protected void paintBackground(net.rim.device.api.ui.Graphics g) 
            { 
                g.clear(); 
                g.getColor(); 
                g.setColor(Color.GREEN); 
                g.fillRect(0, 0, Display.getWidth(), Display.getHeight()); 
                g.setColor(Color.WHITE);                
            } 
        };
        FontFamily fontFamily[] = FontFamily.getFontFamilies(); 
        Font font1 = fontFamily[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf1.setFont(font1);              
        setTitle(lf1);
        
        
/*        
        CustomLabelField lf = new CustomLabelField("************** SELECT A CITATION ***************", Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH | DrawStyle.HCENTER);
        lf.setBackgroundColor(Color.WHITE);  
        lf.setTextColor(Color.BLUE);
        
        FontFamily fontFamily3[] = FontFamily.getFontFamilies(); 
        //Font fnt = fontFamily3[1].getFont(FontFamily.CBTF_FONT, 12).derive(Font.BOLD | Font.ITALIC); 
        Font fnt = fontFamily3[1].getFont(FontFamily.CBTF_FONT, 14);
        lf.setFont(fnt);
*/
      LabelField lf2 = new LabelField("******** SELECT A CITATION ********", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH | DrawStyle.HCENTER)
        {
            protected void paintBackground(net.rim.device.api.ui.Graphics g) 
            { 
                g.clear(); 
                g.getColor(); 
                g.setColor(Color.GREEN); 
                g.fillRect(0, 0, Display.getWidth(), Display.getHeight()); 
                g.setColor(Color.WHITE);                
            } 
        };
        FontFamily fontFamily2[] = FontFamily.getFontFamilies(); 
        Font font2 = fontFamily2[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf2.setFont(font2); 
        //add(lf2);         
        
        
        verticalManager.add(lf2);
        verticalManager.add(new SeparatorField());

        // Listfield 
        list = new ListField();
        list.setEmptyString("No Stored Citations Available", DrawStyle.LEFT);
        
        int citation_count = CitationStore.getCitationCount();          //----get previous citations
        list.setSize(citation_count);
        TestListCallback newCallback = new TestListCallback();
        list.setCallback(newCallback);
        verticalManager.add(list);
           
        verticalManager.add(new SeparatorField());
   /*
        CustomLabelField lf3 = new CustomLabelField("NOTE: Use the menu to either open a previous citation, or to create a new one.", Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH);
        //lf2.setBackgroundColor(Color.WHITE);
        lf3.setTextColor(Color.GREEN);
        
        FontFamily fontFamily4[] = FontFamily.getFontFamilies(); 
        Font fnt2 = fontFamily4[1].getFont(FontFamily.CBTF_FONT, 12).derive(Font.BOLD | Font.ITALIC); 
        lf3.setFont(fnt2);
   */
        LabelField lf3 = new LabelField("NOTE: Use the menu to open/delete a previous citation", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH | DrawStyle.BOTTOM)
        {
            protected void paintBackground(net.rim.device.api.ui.Graphics g) 
            { 
                g.clear(); 
                g.getColor(); 
                g.setColor(Color.WHITE); 
                g.fillRect(0, 0, Display.getWidth(), Display.getHeight()); 
                g.setColor(Color.RED);                
            } 
        };
        FontFamily fontFamily3[] = FontFamily.getFontFamilies(); 
        Font font3 = fontFamily3[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf3.setFont(font3);       
        verticalManager.add(lf3);
        
        // ButtonField
        ButtonField btn;        
        btn = new ButtonField("Create New", ButtonField.CONSUME_CLICK | DrawStyle.BOTTOM);
        btn.setChangeListener(new ButtonListener(this, c)); 
        //navButtonManager.add(btn);

        //add(navButtonManager);
        verticalManager.add(btn);

        addMenuItem(createNew);
        addMenuItem(openPrev); 
        addMenuItem(delPrev);           
    }
     
     
    public static class ButtonListener implements FieldChangeListener {
        Citation c;
        CitationScreen1 cs1;
                    
        public ButtonListener(CitationScreen1 _cs1, Citation _n1){
            cs1 = _cs1;
            c = _n1;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn4 = (ButtonField) field;
            if(btn4.getLabel().toString() == "Create New"){ 
             c = CitationStore.getNewCitation();                     //---------------NEW CITATION
             UiApplication.getUiApplication().pushScreen(new CitationScreen2(c)); 
            }
        }
    }      
          
    private MenuItem createNew = new MenuItem("New", 110, 10) {
        public void run() {
             //Dialog.inform("Creating new citation...");                             
             c = CitationStore.getNewCitation();                     //---------------NEW CITATION
             UiApplication.getUiApplication().pushScreen(new CitationScreen2(c));    
        }
    };

    private MenuItem openPrev = new MenuItem("Open", 110, 11) {
        public void run() {
            //Dialog.inform("Retrieving citation...");
            int index = list.getSelectedIndex();
            c = CitationStore.getCitationByIndex(index);    
            if ( c.isReadOnly() ) {
            	// skip to preview screen if citation has already been printed or submitted
            	UiApplication.getUiApplication().pushScreen(new CitationScreen8(c));
            }
            else {
            	UiApplication.getUiApplication().pushScreen(new CitationScreen2(c));            	
            }
        }
    };  
    
    private MenuItem delPrev = new MenuItem("Delete", 110, 11) {
        public void run() {
            //Dialog.inform("Removing citation...");
            int index = list.getSelectedIndex();
            CitationStore.Remove(index);  
        }
    };           
} 

final class TestListCallback implements ListFieldCallback {
    public Citation _controller;
    
    public TestListCallback (){
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
