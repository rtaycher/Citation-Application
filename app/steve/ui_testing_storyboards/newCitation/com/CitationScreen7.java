/*
 * CitationScreen7.java
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
class CitationScreen7 extends MainScreen {
    ButtonField btn;
     
    CitationScreen7(Citation c1) {  

      LabelField lf1 = new LabelField("LOCATION INFORMATION", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH)
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
        
         // EditField
        c1.address2 = new EditField("Address: ", "");
        add(c1.address2);           
        
        // ObjectChoiceField
        String choicestrs0[] = {"Portland", "Milwaukie", "Clackamas", "Gresham", "Gladstone", "Canby", "Hillsboro"};
        c1.city2 = new ObjectChoiceField("City/Other Public Body: ", choicestrs0, 0);
        add(c1.city2);
        
        // ObjectChoiceField
        String choicestrs1[] = {"OR", "WA", "CA"};
        c1.state2 = new ObjectChoiceField("State: ", choicestrs1, 0);
        add(c1.state2);
        
        // EditField
        c1.zip2 = new EditField("Zip: ", "");
        add(c1.zip2);   
        
        add(new SeparatorField()); 
        add(new SeparatorField()); 
        
        // ButtonField
        btn = new ButtonField("Continue", ButtonField.CONSUME_CLICK);
        btn.setChangeListener(new ButtonListener7(c1)); 
        add(btn);
    }
    
    public class ButtonListener7 implements FieldChangeListener {
        Citation c7;
                    
        public ButtonListener7(Citation n7){
            //c2 = new CitationScreen2(n2);
            c7 = n7;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn = (ButtonField) field;
            if(btn.getLabel().toString() == "Continue"){ 
                UiApplication.getUiApplication().pushScreen(new CitationScreen8(c7));
               
            }
            //else if(btn4.getLabel().toString() == "Go Back"){
            //    UiApplication.getUiApplication().pushScreen(new CitationScreen1(c2));
            //     //pushScreen(new CitationScreen1());
            //}   
        }
    }     
} 
