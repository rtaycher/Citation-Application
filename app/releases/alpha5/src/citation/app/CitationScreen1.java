/*
 * CitationScreen1.java
 *
 * � <your company here>, 2003-2008
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


//import net.rim.device.api.ui.decor.BackgroundFactory;




class CitationScreen1 extends MainScreen {

    private Citation c = null;   
    private ListField list;
       
    public CitationScreen1(Citation _c){
        //super();
        
        
        
        
        super(NO_VERTICAL_SCROLL);
        String bgImage = "img/badge.png";    
        final Bitmap _backgroundBitmap = Bitmap.getBitmapResource(bgImage);  
        
        VerticalFieldManager verticalManager = null;

        //rather than  adding component 
        //in the mainScreen add components
        //in this verticalManager and then
        //add this manager to mainScreen  
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
               graphics.clear();
               graphics.drawBitmap( (this.getWidth() / 2) - (_backgroundBitmap .getWidth() /2),
                           (this.getHeight() / 2) - (_backgroundBitmap .getHeight() /2),
                           _backgroundBitmap .getWidth(),
                           _backgroundBitmap .getHeight(),
                           _backgroundBitmap ,0,0);
               subpaint(graphics);
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
  

  
  
  
  
        // Setting page title
        CustomLabelField lf1 = new CustomLabelField("OREGON UNIFORM CITATION & COMPLAINT", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        lf1.setBackgroundColor(Color.GREEN);
        lf1.setTextColor(Color.WHITE);
        
        FontFamily fontFamily[] = FontFamily.getFontFamilies(); 
        Font font1 = fontFamily[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf1.setFont(font1); 

        setTitle(lf1);
   
        verticalManager.add(new SeparatorField());
        verticalManager.add(new SeparatorField());
        verticalManager.add(new SeparatorField());
        verticalManager.add(new SeparatorField());
        
        CustomLabelField lf = new CustomLabelField("************** SELECT A CITATION ***************", Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH);
        lf.setBackgroundColor(Color.WHITE);  
        lf.setTextColor(Color.BLUE);
        
        FontFamily fontFamily3[] = FontFamily.getFontFamilies(); 
        //Font fnt = fontFamily3[1].getFont(FontFamily.CBTF_FONT, 12).derive(Font.BOLD | Font.ITALIC); 
        Font fnt = fontFamily3[1].getFont(FontFamily.CBTF_FONT, 14);
        lf.setFont(fnt);
 
       
        verticalManager.add(lf);
        verticalManager.add(new SeparatorField());



        // Listfield 
        list = new ListField();
        list.setEmptyString("No Stored Citations Available", DrawStyle.LEFT);
        
        int citation_count = CitationStore.getCitationCount();          //----get previous citations
        list.setSize(citation_count);
        TestListCallback newCallback = new TestListCallback();
        list.setCallback(newCallback);
        verticalManager.add(list);
        
        
        
        
        
        /*       
        HorizontalFieldManager hfm = null;

        try {
           String bgImage = "img/badge.png";    
           final Bitmap backgroundBitmap = Bitmap.getBitmapResource(bgImage);
           hfm = new HorizontalFieldManager(HorizontalFieldManager.USE_ALL_WIDTH | HorizontalFieldManager.USE_ALL_HEIGHT){
             public void paint(Graphics graphics){
               graphics.drawBitmap(0, 0, 320, 240, backgroundBitmap, 0, 0);
               super.paint(graphics);
             }
           };
           add(hfm);
        }
        catch (Exception e){
           System.out.println("Exception caught at creating background image.");
        } 
        */
        
        
        
        verticalManager.add(new SeparatorField());

        
        CustomLabelField lf2 = new CustomLabelField("NOTE: Use the menu to either open a previous citation, or to create a new one.", Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH);
        //lf2.setBackgroundColor(Color.WHITE);
        lf2.setTextColor(Color.GREEN);
        
        FontFamily fontFamily4[] = FontFamily.getFontFamilies(); 
        Font fnt2 = fontFamily4[1].getFont(FontFamily.CBTF_FONT, 12).derive(Font.BOLD | Font.ITALIC); 
        lf2.setFont(fnt);
        verticalManager.add(lf2);


        addMenuItem(createNew);
        addMenuItem(openPrev); 
        addMenuItem(delPrev);     
        
    }
      
    private MenuItem createNew = new MenuItem("New", 110, 10) {
        public void run() {
             Dialog.inform("Creating new citation...");                             
             c = CitationStore.getNewCitation();                     //---------------NEW CITATION
             UiApplication.getUiApplication().pushScreen(new CitationScreen2(c));    
        }
    };

    private MenuItem openPrev = new MenuItem("Open", 110, 11) {
        public void run() {
            Dialog.inform("Retrieving citation...");
            int index = list.getSelectedIndex();
            c = CitationStore.getCitationByIndex(index);          
            UiApplication.getUiApplication().pushScreen(new CitationScreen2(c));
        }
    };  
    
    private MenuItem delPrev = new MenuItem("Delete", 110, 11) {
        public void run() {
            Dialog.inform("Removing citation...");
            int index = list.getSelectedIndex();
            // to be finished...
            
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
