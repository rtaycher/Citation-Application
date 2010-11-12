/*
 * CitationScreen5.java
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
class CitationScreen5 extends MainScreen {
    ButtonField btn;
     
    CitationScreen5(Citation c1) {  
      LabelField lf1 = new LabelField("VEHICLE / PERSON ASSOCIATION", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH)
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
        
        //-------------
        
        int temp = c1.getVehicleCnt();
        for(int i=0; i < temp; i++){
            
            add(new RichTextField("Auto License Plate #: " + c1.v[i].plate.getText()));
            add(new RichTextField("State: " + c1.v[i].reg_state.toString()));
            add(new RichTextField("Make: " + c1.v[i].make.getText()));
            add(new RichTextField("Model: " + c1.v[i].model.getText()));
            add(new RichTextField("Year: " + c1.v[i].year.toString()));
            add(new RichTextField("Color: " + c1.v[i].prim_color.toString()));
            add(new RichTextField("VIN: " + c1.v[i].VIN.getText()));
                
            add(new SeparatorField());
                
            LabelField lb1 = new LabelField("************ People Associated ************", 0, -1, Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH)
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
        
            FontFamily fontFamily1[] = FontFamily.getFontFamilies(); 
            Font fnt1 = fontFamily1[1].getFont(FontFamily.CBTF_FONT, 14).derive(Font.BOLD | Font.ITALIC); 
            lb1.setFont(fnt1);
            add(lb1);
        
            add(new SeparatorField());  
            
            int temp2 = c1.getPersonNum();
             
            for(int j=0; j < temp2; j++){
                add(new SeparatorField()); 
                LabelField lb2 = new LabelField("Person #" + j, 0, -1, Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH)
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
                lb2.setFont(fnt2);
                add(lb2);            
            
                add(new RichTextField("Name: " + c1.p[j].first.getText() + " " + c1.p[j].middle.getText() + " " + c1.p[j].last.getText()));
                add(new RichTextField("ID #: " + c1.p[j].id.getText()));
                add(new RichTextField("DOB: " + c1.p[j].DOB.toString()));
                // CheckboxField
                c1.p[j].associated = new CheckboxField("Associate with vehicle #" + i, false);
                add(c1.p[j].associated);

                add(new SeparatorField()); 
             }

             add(new SeparatorField()); 
             
        }

        // ButtonField
        btn = new ButtonField("Continue", ButtonField.CONSUME_CLICK);
        btn.setChangeListener(new ButtonListener5(c1)); 
        add(btn);
       
    }
    
    public class ButtonListener5 implements FieldChangeListener {
        Citation c5;
                    
        public ButtonListener5(Citation n5){
            c5 = n5;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn = (ButtonField) field;
            if(btn.getLabel().toString() == "Continue"){ 
                UiApplication.getUiApplication().pushScreen(new CitationScreen6(c5));
                //pushScreen(new CitationScreen3(c2));
            }
 
        }
    }    
} 
