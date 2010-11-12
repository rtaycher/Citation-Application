/*
 * CitationScreen4.java
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
class CitationScreen4 extends MainScreen {
    ButtonField btn;
    
    CitationScreen4(Citation c1) {  
        LabelField lf1 = new LabelField("DEFENDENT INFRACTIONS", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH)
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
        //int temp = Integer.parseInt(c1.numOfVehicles.getText());
        //c1.v = new Vehicle[temp];
        
        //for(int a=0; a < temp; a++){
        //    c1.v[a] = new Vehicle();
        //}
        int temp = c1.getPersonNum();
        
        for(int i=0; i < temp; i++){

            add(new SeparatorField());

            LabelField lbl2 = new LabelField("******* Defendent " + (i+1) + " of " + temp + " Infraction *******", 0, -1, Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH)
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
    
            add(new RichTextField("Name: " + c1.p[i].first.getText() + " " + c1.p[i].middle.getText() + " " + c1.p[i].last.getText()));
            add(new RichTextField("Date of Birth: " + c1.p[i].DOB.toString()));
            
            add(new SeparatorField());   
            
            // EditField
            c1.citation_no = new EditField("Citation #: ", "");
            add(c1.citation_no); 
            
            add(new SeparatorField());   
            
            c1.p[i].dte = new DateField("Date: ", Long.MIN_VALUE, DateField.DATE);
            add(c1.p[i].dte);
            c1.p[i].tme = new DateField("Time: ", Long.MIN_VALUE, DateField.TIME);
            add(c1.p[i].tme);
            
            // EditField
            c1.p[i].offenses = new EditField("Offenses: ", "", 100, 0);
            add(c1.p[i].offenses);   
    
            add(new SeparatorField());
        
            // CheckboxField
            c1.p[i].accident = new CheckboxField("Accident", false);
            add(c1.p[i].accident);
        
            // CheckboxField
            c1.p[i].radar = new CheckboxField("Radar", false);
            add(c1.p[i].radar);
        
            // CheckboxField
            c1.p[i].school_zone = new CheckboxField("School Zone", false);
            add(c1.p[i].school_zone);
        
            // CheckboxField
            c1.p[i].paced = new CheckboxField("Paced", false);
            add(c1.p[i].paced);
        
            add(new SeparatorField());
   
            add(new RichTextField("Jail Booking Involved: "));
            c1.p[i].rgrp1 = new RadioButtonGroup();
            c1.p[i].yes = new RadioButtonField("Yes", c1.p[i].rgrp1, true);
            c1.p[i].no = new RadioButtonField("No", c1.p[i].rgrp1, false);    
            add(c1.p[i].yes);
            add(c1.p[i].no);
            
            add(new SeparatorField());
             
            LabelField lbl3 = new LabelField("*********** Officer Information ***********", 0, -1, Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH)
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
        
            FontFamily fontFamily3[] = FontFamily.getFontFamilies(); 
            Font fnt3 = fontFamily3[1].getFont(FontFamily.CBTF_FONT, 14).derive(Font.BOLD | Font.ITALIC); 
            lbl3.setFont(fnt3);
            add(lbl3);
        
            add(new SeparatorField());             
             
            // EditField
            c1.p[i].officer_first = new EditField("First: ", "");
            add(c1.p[i].officer_first);  
            
            // EditField
            c1.p[i].officer_middle = new EditField("Middle: ", "");
            add(c1.p[i].officer_middle);                       
                   
            // EditField
            c1.p[i].officer_last = new EditField("Last: ", "");
            add(c1.p[i].officer_last);  
            
            add(new SeparatorField());     

            // EditField
            c1.p[i].officer_badge = new EditField("Badge: ", "");
            add(c1.p[i].officer_badge);                       
                   
            // EditField
            c1.p[i].officer_id = new EditField("ID #: ", "");
            add(c1.p[i].officer_id);                     
                 
            add(new SeparatorField());

            LabelField lbl4 = new LabelField("************ Court Information ************", 0, -1, Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH)
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
        
            FontFamily fontFamily4[] = FontFamily.getFontFamilies(); 
            Font fnt4 = fontFamily4[1].getFont(FontFamily.CBTF_FONT, 14).derive(Font.BOLD | Font.ITALIC); 
            lbl4.setFont(fnt4);
            add(lbl4);
        
            // CheckboxField
            c1.p[i].circuit_crt = new CheckboxField("Circuit Court", false);
            add(c1.p[i].circuit_crt);
        
            // EditField
            c1.p[i].other_crt = new EditField("Other: ", "");
            add(c1.p[i].other_crt);
        
            add(new SeparatorField());

            // CheckboxField
            c1.p[i].community_crt = new CheckboxField("Community Court", false);
            add(c1.p[i].community_crt);
        
            c1.p[i].rgrp2 = new RadioButtonGroup();
            c1.p[i].oregon_city = new RadioButtonField("Oregon City", c1.p[i].rgrp2, false);
            c1.p[i].clackamas = new RadioButtonField("Clackamas", c1.p[i].rgrp2, false);
            add(c1.p[i].oregon_city);
            add(c1.p[i].clackamas);
        
            add(new SeparatorField());
        
            // CheckboxField
            c1.p[i].municipal_crt1 = new CheckboxField("Municipal Courts", false);
            add(c1.p[i].municipal_crt1);
        
            c1.p[i].rgrp3 = new RadioButtonGroup();
            c1.p[i].damascus = new RadioButtonField("Damascus", c1.p[i].rgrp3, false);
            c1.p[i].estacada = new RadioButtonField("Estacada", c1.p[i].rgrp3, false);
            add(c1.p[i].damascus);
            add(c1.p[i].estacada);
        
            add(new SeparatorField());
        
            // CheckboxField
            c1.p[i].municipal_crt2 = new CheckboxField("Municipal Courts", false);
            add(c1.p[i].municipal_crt2);
        
            c1.p[i].rgrp4 = new RadioButtonGroup();
            c1.p[i].happy_valley = new RadioButtonField("Happy Valley", c1.p[i].rgrp4, false);
            c1.p[i].wilsonville = new RadioButtonField("Wilsonville", c1.p[i].rgrp4, false);
            add(c1.p[i].happy_valley);
            add(c1.p[i].wilsonville);
        
            add(new SeparatorField());            
                  
            c1.p[i].court_dte = new DateField("Date: ", Long.MIN_VALUE, DateField.DATE);
            add(c1.p[i].court_dte);
            c1.p[i].court_tme = new DateField("Time: ", Long.MIN_VALUE, DateField.TIME);
            add(c1.p[i].court_tme);  
             
            add(new SeparatorField()); 
            
            // ObjectChoiceField
            String choicestrs0[] = {"N/A", "0.01-0.029", "0.03-0.059", "0.06-0.10", "0.11-0.20", "0.21-0.29", "0.30-0.39", ">0.40"};
            c1.p[i].bac = new ObjectChoiceField("Blood Alcohol Level (BAC): ", choicestrs0, 0);
            add(c1.p[i].bac);
            
            add(new SeparatorField()); 
                 
            c1.p[i].court_dte = new DateField("Date of BAC: ", Long.MIN_VALUE, DateField.DATE);
            add(c1.p[i].court_dte);
            c1.p[i].court_tme = new DateField("Time of BAC: ", Long.MIN_VALUE, DateField.TIME);
            add(c1.p[i].court_tme);    
            
            add(new SeparatorField());  
            
            // EditField
            c1.p[i].violation_type = new EditField("Violation Type: ", "");
            add(c1.p[i].violation_type);                       
                   
            // ObjectChoiceField
            String choicestrs1[] = {"5MPH", "10MPH", "15MPH", "20MPH", "25MPH", "30MPH", "35MPH", "40MPH", "45MPH", "50MPH", "55MPH", "60MPH", "65MPH", "70MPH", "75MPH", "80MPH"};
            c1.p[i].speed_limit = new ObjectChoiceField("Speed Limit: ", choicestrs1, 0);
            add(c1.p[i].speed_limit);                  
                   
            // EditField
            c1.p[i].alleged_speed= new EditField("Alleged Speed: ", "");
            add(c1.p[i].alleged_speed);
            
            add(new SeparatorField()); 
            
            // EditField
            c1.p[i].narrative= new EditField("Narrative: ", "", 100, 0);
            add(c1.p[i].narrative);                   
         }
         
         add(new SeparatorField());  
                
         // ButtonField
         btn = new ButtonField("Continue", ButtonField.CONSUME_CLICK);
         btn.setChangeListener(new ButtonListener4(c1)); 
         add(btn);
         //btn3 = new ButtonField("Go Back", ButtonField.CONSUME_CLICK);
         //btn3.setChangeListener(new ButtonListener2(c)); 
         //add(btn3);
        

    }   
    
    public class ButtonListener4 implements FieldChangeListener {
        Citation c4;
                    
        public ButtonListener4(Citation n4){
            //c2 = new CitationScreen2(n2);
            c4 = n4;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn = (ButtonField) field;
            if(btn.getLabel().toString() == "Continue"){ 
                UiApplication.getUiApplication().pushScreen(new CitationScreen5(c4));
                //pushScreen(new CitationScreen3(c2));
            }
            //else if(btn4.getLabel().toString() == "Go Back"){
            //    UiApplication.getUiApplication().pushScreen(new CitationScreen1(c2));
            //     //pushScreen(new CitationScreen1());
            //}   
        }
    }                
} 
