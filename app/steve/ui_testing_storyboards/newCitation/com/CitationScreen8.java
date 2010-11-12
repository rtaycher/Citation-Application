/*
 * CitationScreen8.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package newCitation.com;


import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;

/**
 * 
 */
class CitationScreen8 extends MainScreen {
    ButtonField btn;
    ButtonField btn2;
    ButtonField btn3;
     
    CitationScreen8(Citation c1) {  

      LabelField lf1 = new LabelField("VERIFY CITATION INFORMATION", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH)
        {
            protected void paintBackground(net.rim.device.api.ui.Graphics g) 
            { 
                g.clear(); 
                g.getColor(); 
                g.setColor(Color.CYAN); 
                g.fillRect(0, 0, Display.getWidth(), Display.getHeight()); 
                g.setColor(Color.BLUE);                
            } 
        };
        FontFamily fontFamily[] = FontFamily.getFontFamilies(); 
        Font font1 = fontFamily[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf1.setFont(font1); 
        setTitle(lf1);
        
        add(new SeparatorField());    
        
 
        
        add(new SeparatorField()); 
        add(new SeparatorField()); 

        // ButtonField
        btn = new ButtonField("Go Back", ButtonField.CONSUME_CLICK);
        btn.setChangeListener(new ButtonListener8(c1)); 
        add(btn);
                
        // ButtonField
        btn2 = new ButtonField("Close", ButtonField.CONSUME_CLICK);
        btn2.setChangeListener(new ButtonListener8(c1)); 
        add(btn2);
        
        // ButtonField
        btn3 = new ButtonField("Edit", ButtonField.CONSUME_CLICK);
        btn3.setChangeListener(new ButtonListener8(c1)); 
        add(btn3);        
    }
    
    public class ButtonListener8 implements FieldChangeListener {
        Citation c8;
                    
        public ButtonListener8(Citation n8){
            //c2 = new CitationScreen2(n2);
            c8 = n8;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn = (ButtonField) field;
            if(btn.getLabel().toString() == "Go Back"){ 
                //UiApplication.getUiApplication().pushScreen(new CitationScreen9(c8));
               
            }
            else if(btn.getLabel().toString() == "Close"){
                //UiApplication.getUiApplication().pushScreen(new CitationScreen1(c2));
                 //pushScreen(new CitationScreen1());
            }   
            else if(btn.getLabel().toString() == "Edit"){
                //UiApplication.getUiApplication().pushScreen(new CitationScreen1(c2));
                 //pushScreen(new CitationScreen1());
            }             
        }
    }     
} 
