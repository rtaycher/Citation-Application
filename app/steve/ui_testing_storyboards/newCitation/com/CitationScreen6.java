/*
 * CitationScreen6.java
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
class CitationScreen6 extends MainScreen {
    ButtonField btn;
     
    CitationScreen6(Citation c1) {  

      LabelField lf1 = new LabelField("VERIFY CITATION & CLOSE INCIDENT", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH)
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
       
        int temp3 = c1.getVehicleCnt();
        int temp4 = c1.getPersonNum();
        for(int k=0; k < temp3; k++){
            add(new RichTextField("Auto License Plate #: " + c1.v[k].plate.getText()));    
            for(int m=0; m < temp4; m++){
                String assoc = "Associate with vehicle #" + k;
                if(c1.p[m].associated.getLabel().equals(assoc)){
                    add(new RichTextField("Name: " + c1.p[m].first.toString() + " " + c1.p[m].middle.toString() + " " + c1.p[m].last.toString()));
                }     
            }
        }  
        
        // ButtonField
        btn = new ButtonField("Continue", ButtonField.CONSUME_CLICK);
        btn.setChangeListener(new ButtonListener6(c1)); 
        add(btn);
    }
    
    public class ButtonListener6 implements FieldChangeListener {
        Citation c6;
                    
        public ButtonListener6(Citation n6){
            //c2 = new CitationScreen2(n2);
            c6 = n6;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn = (ButtonField) field;
            if(btn.getLabel().toString() == "Continue"){ 
                UiApplication.getUiApplication().pushScreen(new CitationScreen7(c6));
                //pushScreen(new CitationScreen3(c2));
            }
            //else if(btn4.getLabel().toString() == "Go Back"){
            //    UiApplication.getUiApplication().pushScreen(new CitationScreen1(c2));
            //     //pushScreen(new CitationScreen1());
            //}   
        }
    }     
} 
