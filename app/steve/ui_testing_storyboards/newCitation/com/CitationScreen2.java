/*
 * TestScreen2.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package newCitation.com;

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;

public class CitationScreen2 extends MainScreen {
    ButtonField btn2;
    ButtonField btn3;
    
    LabelField lbl;
    LabelField lbl2;
    LabelField lbl3;
   
    
    public CitationScreen2(Citation c) {  
        LabelField lf1 = new LabelField("DEFENDENT INFORMATION", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH)
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
        int temp = Integer.parseInt(c.numOfPersons.getText());
        c.p = new Person[temp];
        
        for(int a=0; a < temp; a++){
            c.p[a] = new Person();
        }
        
        for(int i=0; i < temp; i++){

            add(new SeparatorField());

            lbl2 = new LabelField("*********** DEFENDENT   " + (i+1) + " of " + temp + " ************", 0, -1, Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH)
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

            c.p[i].rgrp0 = new RadioButtonGroup();
            c.p[i].rad_person = new RadioButtonField("Person", c.p[i].rgrp0, true);
            c.p[i].rad_business = new RadioButtonField("Business", c.p[i].rgrp0, false);
            add(c.p[i].rad_person);
            add(c.p[i].rad_business);
        
            add(new SeparatorField());   
            
            // EditField
            c.p[i].id = new EditField("ID #: ", "");
            add(c.p[i].id);  
            
            add(new SeparatorField());  
            
            // ObjectChoiceField
            String choicestrs0[] = {"OR", "WA", "CA"};
            c.p[i].state0 = new ObjectChoiceField("State: ", choicestrs0, 0);
            add(c.p[i].state0);
             
            add(new SeparatorField());   
            
            // ObjectChoiceField
            String choicestrs1[] = {"Mr.", "Mrs.", "Miss", "Ms.", "Dr."};
            c.p[i].title = new ObjectChoiceField("Title: ", choicestrs1, 0);
            add(c.p[i].title);   
            
            add(new SeparatorField());  
            
            // EditField
            c.p[i].first = new EditField("First: ", "");
            add(c.p[i].first);  
            
            // EditField
            c.p[i].middle = new EditField("Middle: ", "");
            add(c.p[i].middle);                       
                   
            // EditField
            c.p[i].last = new EditField("Last: ", "");
            add(c.p[i].last); 
            
            // ObjectChoiceField
            String choicestrs2[] = {"N/A", "Sr.", "Jr.", "I", "II", "III", "IV"};
            c.p[i].suffix = new ObjectChoiceField("Suffix: ", choicestrs2, 0);
            add(c.p[i].suffix);  
            
            add(new SeparatorField());  
            
            // EditField
            c.p[i].address = new EditField("Address: ", "");
            add(c.p[i].address);   
            
            // ObjectChoiceField
            String choicestrs3[] = {"Portland", "Milwaukie", "Clackamas", "Gresham", "Gladstone", "Canby", "Hillsboro"};
            c.p[i].city = new ObjectChoiceField("City: ", choicestrs3, 0);
            add(c.p[i].city);                      
           
            // ObjectChoiceField
            String choicestrs4[] = {"OR", "WA", "CA"};
            c.p[i].state1 = new ObjectChoiceField("State: ", choicestrs4, 0);
            add(c.p[i].state1); 
            
             // EditField
            c.p[i].zip = new EditField("Zip: ", "");
            add(c.p[i].zip);    
            
            add(new SeparatorField()); 
            
            // EditField
            c.p[i].DOB = new EditField("Date of Birth: ", "");
            add(c.p[i].DOB);  
            
             // ObjectChoiceField
            String choicestrs5[] = {"M", "F"};
            c.p[i].sex = new ObjectChoiceField("Sex: ", choicestrs5, 0);
            add(c.p[i].sex);                                
         
            // ObjectChoiceField
            String choicestrs6[] = {"White", "Black", "Chinese", "Asian", "Arab", "Sudanese", "French", "Japanese", "Punjabis"};
            c.p[i].race = new ObjectChoiceField("Race: ", choicestrs6, 0);
            add(c.p[i].race);         
         
            // ObjectChoiceField
            String choicestrs7[] = {"Green", "Blue", "Black", "Brown"};
            c.p[i].eyes = new ObjectChoiceField("Eyes: ", choicestrs7, 0);
            add(c.p[i].eyes);            
            
            // ObjectChoiceField
            String choicestrs8[] = {"Blonde", "Black", "Grey", "Red", "Brown"};
            c.p[i].hair = new ObjectChoiceField("Hair: ", choicestrs8, 0);
            add(c.p[i].hair);            
            
            add(new SeparatorField()); 
            
            // EditField
            c.p[i].endorsements = new EditField("Endorsements: ", "");
            add(c.p[i].endorsements);  
            
            add(new SeparatorField()); 
            
            // EditField
            c.p[i].restrictions = new EditField("Restrictions: ", "");
            add(c.p[i].restrictions);  

            // ObjectChoiceField
            String choicestrs9[] = {"5' 0''", "5' 1''", "5' 2''", "5' 3''", "5' 4''", "5' 5''", "5' 6''", "5' 7''", "5' 8''", "5' 9''", "5' 10''", "5' 11''", "6' 0''", "6' 1''", "6' 2''", "6' 3''", "6' 4''", "6' 5''", "6' 6''"};
            c.p[i].height = new ObjectChoiceField("Height: ", choicestrs9, 0);
            add(c.p[i].height);  
            
            // EditField
            c.p[i].weight = new EditField("Weight: ", "");
            add(c.p[i].weight);
        
            add(new SeparatorField()); 
        
            // EditField
            c.p[i].observations = new EditField("Other Observations: ", "", 200, 0);
            add(c.p[i].observations);          
            
            add(new SeparatorField());  
            
            // ObjectChoiceField
            String choicestrs10[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
            c.p[i].numOfViolations = new ObjectChoiceField("Number of Violations: ", choicestrs10, 0);
            add(c.p[i].numOfViolations);  
            
            add(new SeparatorField());  
              
        }
        
        //int temp2 = Integer.parseInt(s1.c.numOfVehicles.getText());
        //for(int j=0; j < temp2; j++){
        //    j[j] = new Vehicle();
        //} 
        //----------------
        
         
        add(new SeparatorField());   
         
        // ButtonField
        btn2 = new ButtonField("Continue", ButtonField.CONSUME_CLICK);
        btn2.setChangeListener(new ButtonListener2(c)); 
        add(btn2);
        //btn3 = new ButtonField("Go Back", ButtonField.CONSUME_CLICK);
        //btn3.setChangeListener(new ButtonListener2(c)); 
        //add(btn3);
        
        add(new SeparatorField());

     }
     
    public class ButtonListener2 implements FieldChangeListener {
        Citation c2;
                    
        public ButtonListener2(Citation n2){
            //c2 = new CitationScreen2(n2);
            c2 = n2;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn4 = (ButtonField) field;
            if(btn4.getLabel().toString() == "Continue"){ 
                UiApplication.getUiApplication().pushScreen(new CitationScreen3(c2));
                //pushScreen(new CitationScreen3(c2));
            }
            //else if(btn4.getLabel().toString() == "Go Back"){
            //    UiApplication.getUiApplication().pushScreen(new CitationScreen1(c2));
            //     //pushScreen(new CitationScreen1());
            //}   
        }
    }              

}
