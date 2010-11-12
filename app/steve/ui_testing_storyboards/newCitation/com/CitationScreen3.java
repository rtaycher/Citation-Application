/*
 * CitationScreen3.java
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
class CitationScreen3 extends MainScreen {
    ButtonField btn;
    
    CitationScreen3(Citation c1) {  
        LabelField lf1 = new LabelField("VEHICLE INFORMATION", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH)
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
                   
        //--------------
        int temp = Integer.parseInt(c1.numOfVehicles.getText());
        c1.v = new Vehicle[temp];
        
        for(int a=0; a < temp; a++){
            c1.v[a] = new Vehicle();
        }
        
        for(int i=0; i < temp; i++){

            add(new SeparatorField());

            LabelField lbl2 = new LabelField("************* Vehicle   " + (i+1) + " of " + temp + " ************", 0, -1, Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH)
            {
                protected void paintBackground(net.rim.device.api.ui.Graphics g) 
                { 
                g.clear(); 
                g.getColor(); 
                g.setColor(Color.LIGHTGREY); 
                g.fillRect(0, 0, Display.getWidth(), Display.getHeight()); 
                g.setColor(Color.BLUE);                
                } 
            };
        
            FontFamily fontFamily2[] = FontFamily.getFontFamilies(); 
            Font fnt2 = fontFamily2[1].getFont(FontFamily.CBTF_FONT, 14).derive(Font.BOLD | Font.ITALIC); 
            lbl2.setFont(fnt2);
            add(lbl2);
        
            add(new SeparatorField());    
    
            // ObjectChoiceField
            String choicestrs0[] = {"Auto", "Boat", "Plane", "Moped"};
            c1.v[i].type = new ObjectChoiceField("Type: ", choicestrs0, 0);
            add(c1.v[i].type); 
    
            // EditField
            c1.v[i].plate = new EditField("License Plate #: ", "");
            add(c1.v[i].plate);
        
            // ObjectChoiceField
            String choicestrs1[] = {"OR", "WA", "CA"};
            c1.v[i].reg_state = new ObjectChoiceField("Registered State: ", choicestrs1, 0);
            add(c1.v[i].reg_state);
        
            add(new SeparatorField());
    
            // EditField
            c1.v[i].make = new EditField("Make: ", "");
            add(c1.v[i].make);
  
            // EditField
            c1.v[i].model = new EditField("Model: ", "");
            add(c1.v[i].model);  
  
            // ObjectChoiceField
            String choicestrs2[] = {"Coupe", "Compact", "Mid-Sized", "Full-Sized", "SUV", "Minivan", "Passenger Van"};
            c1.v[i].style = new ObjectChoiceField("Style: ", choicestrs2, 0);
            add(c1.v[i].style);  
    
            // NumericChoiceField   
            c1.v[i].year = new NumericChoiceField("Year: ", 1990, 2011, 1);
            add(c1.v[i].year);
             
            // ObjectChoiceField
            String choicestrs3[] = {"Black", "White", "Grey", "Red", "Green", "Yellow", "Purple", "Orange", "Brown"};
            c1.v[i].prim_color = new ObjectChoiceField("Primary Color: ", choicestrs3, 0);
            add(c1.v[i].prim_color);
        
            // ObjectChoiceField
            String choicestrs4[] = {"Black", "White", "Grey", "Red", "Green", "Yellow", "Purple", "Orange", "Brown"};
            c1.v[i].sec_color = new ObjectChoiceField("2ndary Color: ", choicestrs4, 0);
            add(c1.v[i].sec_color);
            
            // EditField
            c1.v[i].VIN = new EditField("Vehicle ID#: ", "");
            add(c1.v[i].VIN); 
            
            add(new SeparatorField());
             
         }
         
         add(new SeparatorField());  
                
         // ButtonField
         btn = new ButtonField("Continue", ButtonField.CONSUME_CLICK);
         btn.setChangeListener(new ButtonListener3(c1)); 
         add(btn);
         //btn3 = new ButtonField("Go Back", ButtonField.CONSUME_CLICK);
         //btn3.setChangeListener(new ButtonListener2(c)); 
         //add(btn3);
        

    }   
    
    public class ButtonListener3 implements FieldChangeListener {
        Citation c3;
                    
        public ButtonListener3(Citation n3){
            //c2 = new CitationScreen2(n2);
            c3 = n3;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn = (ButtonField) field;
            if(btn.getLabel().toString() == "Continue"){ 
                UiApplication.getUiApplication().pushScreen(new CitationScreen4(c3));
                //pushScreen(new CitationScreen3(c2));
            }
            //else if(btn4.getLabel().toString() == "Go Back"){
            //    UiApplication.getUiApplication().pushScreen(new CitationScreen1(c2));
            //     //pushScreen(new CitationScreen1());
            //}   
        }
    }                
} 
