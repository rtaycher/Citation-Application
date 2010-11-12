/*
 * CitationScreen1.java
 *
 * Copyright (c) 2009-2010 PSU Capstone Team D
 * Scott Glazer, Cong Hoang, Ba Nguyen, Marek Dolgos,
 * Steve Phelps, Mark Smith, Roman Taycher
 *
 * Citation Application is free/open source software released under
 * the unmodified MIT/X11 license. A copy can be found in the
 * LICENSE file or at:
 *
 *     http://www.opensource.org/licenses/mit-license.php
 *
 */

package citation.app;

import java.rmi.RemoteException;
import java.util.Vector;
import net.rim.device.api.system.*;
import citation.data.*;
import citation.ui.CustomLabelField;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;

/**
 * Class: CitationScreen1
 * Description: This class handles the initial screen of the application which 
 * displays previously stored citations and gives the user the ability to open
 * an old citation or create a new one.
 */
class CitationScreen1 extends MainScreen {

    private Citation c = null;   
    private ListField list;

    private static CitationScreen1 cs1 = null;
         
     /**
      * This is the constructor method for CitationScreen1. It handles loading the background graphic
      * image and displays labelfield headings.
      * @param _c - This is the citation object that is passed between each UI screen.
      */       
    public CitationScreen1(Citation _c){
        super(NO_VERTICAL_SCROLL);
        
        cs1 = this;
        
        // String bgImage = "img/badge.png";    
        // final Bitmap _backgroundBitmap = Bitmap.getBitmapResource(bgImage);  
        
        VerticalFieldManager verticalManager = null;

        //rather than  adding component in the mainScreen add components
        //in this verticalManager and then add this manager to mainScreen  
        verticalManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);

        this.add(verticalManager);        
              
        c = _c;
        
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
        
        LabelField lf3 = new LabelField("NOTE: Click the ball to open the main menu.", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH | DrawStyle.BOTTOM)
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
        
        ButtonField btn;        
        btn = new ButtonField("Create New", ButtonField.CONSUME_CLICK | DrawStyle.BOTTOM);
        btn.setChangeListener(new ButtonListener(this, c)); 
        
        verticalManager.add(btn);

        addMenuItem(createNew);
        addMenuItem(openPrev); 
        addMenuItem(delPrev);           
    }

    /**
     * Inner Class: ButtonListener
     * Description: This class implements a button listener to respond to user pressed
     * buttons on the first UI screen. 
     */
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
                c = CitationStore.getNewCitation();
                UiApplication.getUiApplication().popScreen(UiApplication.getUiApplication().getActiveScreen());
                UiApplication.getUiApplication().pushScreen(new CitationScreen1(c));
                UiApplication.getUiApplication().pushScreen(new CitationScreen2(c));
            }
        }
    }      
     
    /**
     * This declaration of variable createNew creates a new menu item option for creating new citations.
     */
    private MenuItem createNew = new MenuItem("New", 110, 10) {
        public void run()
        {                         
            c = CitationStore.getNewCitation();
            UiApplication.getUiApplication().popScreen(UiApplication.getUiApplication().getActiveScreen());
            UiApplication.getUiApplication().pushScreen(new CitationScreen1(c));
            UiApplication.getUiApplication().pushScreen(new CitationScreen2(c));
        }
    };

    /**
     * This declaration of variable openPrev creates a new menu item option for opening previous citations.
     */ 
    private MenuItem openPrev = new MenuItem("Open", 110, 11) {
        public void run() {
            int index = list.getSelectedIndex();
            c = CitationStore.getCitationByIndex(index);    
            if (c == null){
                Dialog.inform("Failed to open.");
                return;
            }
            if (c.isReadOnly()) {
                // skip to preview screen if citation has already been printed or submitted
                UiApplication.getUiApplication().pushScreen(new CitationScreen9(c));
            }
            else {
                UiApplication.getUiApplication().pushScreen(new CitationScreen2(c));                    
            }
        }
    };
    
    /**
     * This declaration of variable delPrev creates a new menu item option for deleting old citations.
     */
    private MenuItem delPrev = new MenuItem("Delete", 110, 11) {
        public void run() {
            //Dialog.inform("Removing citation...");
            int index = list.getSelectedIndex();
            CitationStore.Remove(index);
            UiApplication.getUiApplication().popScreen(UiApplication.getUiApplication().getActiveScreen());
            UiApplication.getUiApplication().pushScreen(new CitationScreen1(c));
        }
    };

} 

/**
 * Class: TestListCallback
 * Description: This class handles the lookup and retrieval of the stored citations
 * and allows for displaying them to the user in the form of a list.
 */
final class TestListCallback implements ListFieldCallback {
    public Citation _controller;
    
    /**
     * This is the constructor method for the TestListCallback class.
     * Inputs: none
     * Outputs: none
     */
    public TestListCallback (){
        _controller = new Citation();
    }
    
    /**
     * This is the drawListRow method which handles the actual drawing of the citation
     * listing images on the UI screen.
     * Inputs: ListField object, Graphics object, int, int, int
     * Outputs: none
     */
    public void drawListRow(ListField list, Graphics g, int index, int y, int w) {
        int count = CitationStore.getCitationCount();
        if(index >= count){
            return;
        }
  
        _controller = CitationStore.getCitationByIndex(index);   
        g.drawText("Citation # " + String.valueOf(index), 0, y, DrawStyle.LEFT, w);
    }
    
    /**
     * This is the get method which handles the obtaining the citation object stored
     * at a given index.
     * Inputs: ListField object, int
     * Outputs: generic Object
     */
    public Object get(ListField listField, int index) {
        return (Object)CitationStore.List.elementAt(index);
    }
    
    /**
     * This is the method that handles obtaining the width of the current UI screen for 
     * generating proper layout of UI images.
     * Inputs: ListField object
     * Outputs: int
     */
    public int getPreferredWidth(ListField listField) {
        return Graphics.getScreenWidth();
    }
    
    /**
     * This method return the index of a list of citations given three inputs.
     * Inputs: ListField object, String, int
     * Outputs: int
     */
    public int indexOfList(ListField listField, String prefix, int start) {
        return listField.indexOfList(prefix, start);
    }
}
