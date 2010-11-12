/*
 * CitationScreen2.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package citation.app;

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import citation.data.*;
import citation.ui.CustomLabelField;


public class CitationScreen2 extends MainScreen {
       
    RadioButtonGroup rgrp0;
    RadioButtonField rad_person;
    RadioButtonField rad_business;
    
    EditField field_id; 
    ObjectChoiceField field_idstate;

    ObjectChoiceField field_title;
    EditField field_first;
    EditField field_middle;
    EditField field_last;
    ObjectChoiceField field_suffix;
    EditField field_address;
    ObjectChoiceField field_city;
    ObjectChoiceField field_state;
    EditField field_zip;
    EditField field_DOB;
    ObjectChoiceField field_sex;
    ObjectChoiceField field_race;
    ObjectChoiceField field_eyes;
    ObjectChoiceField field_hair;
    EditField field_endorsements;
    EditField field_restrictions;
    ObjectChoiceField field_height;
    EditField field_weight;
    EditField field_observations;
    ObjectChoiceField field_numOfViolations;
    

    private Citation c;
    
    public CitationScreen2(Citation _c) {  
        c = _c;
        
        String cNumberString = Integer.toString(c.Number);
        CustomLabelField lf1 = new CustomLabelField("OREGON UNIFORM CITATION: #" + cNumberString, LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        lf1.setBackgroundColor(Color.CYAN);
        lf1.setTextColor(Color.BLUE);
        FontFamily fontFamily[] = FontFamily.getFontFamilies(); 
        Font font1 = fontFamily[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf1.setFont(font1); 
        
        add(lf1);
                   
        
        //--------------


        add(new SeparatorField());

        CustomLabelField lbl = new CustomLabelField("****************** DEFENDENT *******************", Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH);
        lbl.setBackgroundColor(Color.LIGHTGREY);
        lbl.setTextColor(Color.BLUE);
    
        FontFamily fontFamily2[] = FontFamily.getFontFamilies(); 
        Font fnt2 = fontFamily2[1].getFont(FontFamily.CBTF_FONT, 14); 
        lbl.setFont(fnt2);
        add(lbl);
    
        add(new SeparatorField());

        rgrp0 = new RadioButtonGroup();
        rad_person = new RadioButtonField("Person", rgrp0, true);
        rad_business = new RadioButtonField("Business", rgrp0, false);
        add(rad_person);
        add(rad_business);
    
        add(new SeparatorField());   
        
        // EditField
        field_id = new EditField("ID #: ", "");
        add(field_id);
        
        add(new SeparatorField());  
        
        // ObjectChoiceField
        String choicestrs0[] = {"OR", "WA", "CA"};
        field_idstate = new ObjectChoiceField("State: ", choicestrs0, 0);
        add(field_idstate);
         
        add(new SeparatorField());   
        
        // ObjectChoiceField
        String choicestrs1[] = {"Mr.", "Mrs.", "Miss", "Ms.", "Dr."};
        field_title = new ObjectChoiceField("Title: ", choicestrs1, 0);
        add(field_title);   
        
        add(new SeparatorField());  
        
        // EditField
        field_first = new EditField("First: ", "");
        add(field_first);  
        
        // EditField
        field_middle = new EditField("Middle: ", "");
        add(field_middle);                       
               
        // EditField
        field_last = new EditField("Last: ", "");
        add(field_last); 
        
        // ObjectChoiceField
        String choicestrs2[] = {"N/A", "Sr.", "Jr.", "I", "II", "III", "IV"};
        field_suffix = new ObjectChoiceField("Suffix: ", choicestrs2, 0);
        add(field_suffix);  
        
        add(new SeparatorField());  
        
        // EditField
        field_address = new EditField("Address: ", "");
        add(field_address);   
        
        // ObjectChoiceField
        String choicestrs3[] = {"Portland", "Milwaukie", "Clackamas", "Gresham", "Gladstone", "Canby", "Hillsboro"};
        field_city = new ObjectChoiceField("City: ", choicestrs3, 0);
        add(field_city);                      
       
        // ObjectChoiceField
        String choicestrs4[] = {"OR", "WA", "CA"};
        field_state = new ObjectChoiceField("State: ", choicestrs4, 0);
        add(field_state); 
        
         // EditField
        field_zip = new EditField("Zip: ", "");
        add(field_zip);    
        
        add(new SeparatorField()); 
        
        // EditField
        field_DOB = new EditField("Date of Birth: ", "");
        add(field_DOB);  
        
         // ObjectChoiceField
        String choicestrs5[] = {"M", "F"};
        field_sex = new ObjectChoiceField("Sex: ", choicestrs5, 0);
        add(field_sex);                                
     
        // ObjectChoiceField
        String choicestrs6[] = {"White", "Black", "Chinese", "Asian", "Arab", "Sudanese", "French", "Japanese", "Punjabis"};
        field_race = new ObjectChoiceField("Race: ", choicestrs6, 0);
        add(field_race);         
     
        // ObjectChoiceField
        String choicestrs7[] = {"Green", "Blue", "Black", "Brown"};
        field_eyes = new ObjectChoiceField("Eyes: ", choicestrs7, 0);
        add(field_eyes);            
        
        // ObjectChoiceField
        String choicestrs8[] = {"Blonde", "Black", "Grey", "Red", "Brown"};
        field_hair = new ObjectChoiceField("Hair: ", choicestrs8, 0);
        add(field_hair);            
        
        add(new SeparatorField()); 
        
        // EditField
        field_endorsements = new EditField("Endorsements: ", "");
        add(field_endorsements);  
        
        add(new SeparatorField()); 
        
        // EditField
        field_restrictions = new EditField("Restrictions: ", "");
        add(field_restrictions);  

        // ObjectChoiceField
        String choicestrs9[] = {"5' 0''", "5' 1''", "5' 2''", "5' 3''", "5' 4''", "5' 5''", "5' 6''", "5' 7''", "5' 8''", "5' 9''", "5' 10''", "5' 11''", "6' 0''", "6' 1''", "6' 2''", "6' 3''", "6' 4''", "6' 5''", "6' 6''"};
        field_height = new ObjectChoiceField("Height: ", choicestrs9, 0);
        add(field_height);  
        
        // EditField
        field_weight = new EditField("Weight: ", "");
        add(field_weight);
    
        add(new SeparatorField()); 
    
        // EditField
        field_observations = new EditField("Other Observations: ", "", 200, 0);
        add(field_observations);          
        
        add(new SeparatorField());  
        
        // ObjectChoiceField
        String choicestrs10[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        field_numOfViolations = new ObjectChoiceField("Number of Violations: ", choicestrs10, 0);
        add(field_numOfViolations);  
        
        add(new SeparatorField());  
        
          
        // ButtonField row - set all buttons at the bottom row of the form
        HorizontalFieldManager navButtonManager = new HorizontalFieldManager();

        // ButtonField
        ButtonField btn_prev;        
        btn_prev = new ButtonField("Prev", ButtonField.CONSUME_CLICK);
        btn_prev.setChangeListener(new ButtonListener2(this, c)); 
        navButtonManager.add(btn_prev);
                
        ButtonField btn_next;
        btn_next = new ButtonField("Next", ButtonField.CONSUME_CLICK);
        btn_next.setChangeListener(new ButtonListener2(this, c)); 
        navButtonManager.add(btn_next);

        add(navButtonManager);

        add(new SeparatorField());


        addMenuItem(saveItem2);
        addMenuItem(getItem2);
        
        
        restoreUIFieldsFromCitation();
     }
     
    public static class ButtonListener2 implements FieldChangeListener {
        Citation c;
        CitationScreen2 cs2;
                    
        public ButtonListener2(CitationScreen2 _cs2, Citation _n2){
            cs2 = _cs2;
            c = _n2;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn4 = (ButtonField) field;
            if(btn4.getLabel().toString() == "Next"){ 
                cs2.storeUIFieldsToCitation();
                UiApplication.getUiApplication().pushScreen(new CitationScreen3(c));
            }
            else if(btn4.getLabel().toString() == "Prev"){
                cs2.storeUIFieldsToCitation();
                UiApplication.getUiApplication().popScreen(cs2);
            }   
        }
    } 
    
    
 
 
    private MenuItem saveItem2 = new MenuItem("Save", 110, 10) {
        public void run() {
                
                storeUIFieldsToCitation();
                CitationStore.Save(); 
                
        }
    };

    private MenuItem getItem2 = new MenuItem("Get", 110, 11) {
        public void run() {
                
                CitationStore.Restore();
                restoreUIFieldsFromCitation();
           
        }
    };     
    
    /**
     * storeUIFieldsToCitation - transfer the UI field values to the citation record
     */
    private void storeUIFieldsToCitation()
    {
        if(rad_person.isSelected() == true){
                c.Person.setElement(CPerson.TYPE, rad_person.getLabel());      
        }
        else if(rad_business.isSelected() == true){
                c.Person.setElement(CPerson.TYPE, rad_business.getLabel());    
        }            
        c.Person.setElement(CPerson.ID, field_id.getText());
        c.Person.setElement(CPerson.ID_STATE, field_idstate.toString());
        c.Person.setElement(CPerson.TITLE, field_title.toString());
        c.Person.setElement(CPerson.FIRST, field_first.getText());
        c.Person.setElement(CPerson.MIDDLE, field_middle.getText());
        c.Person.setElement(CPerson.LAST, field_last.getText());    
        c.Person.setElement(CPerson.SUFFIX, field_suffix.toString());    
        c.Person.setElement(CPerson.ADDRESS, field_address.getText());
        c.Person.setElement(CPerson.CITY, field_city.toString());
        c.Person.setElement(CPerson.STATE, field_state.toString());          
        c.Person.setElement(CPerson.ZIP, field_zip.getText());
        c.Person.setElement(CPerson.DOB, field_DOB.getText());
        c.Person.setElement(CPerson.SEX, field_sex.toString());
        c.Person.setElement(CPerson.RACE, field_race.toString());
        c.Person.setElement(CPerson.EYES, field_eyes.toString());
        c.Person.setElement(CPerson.HAIR, field_hair.toString());
        c.Person.setElement(CPerson.ENDORSEMENTS, field_endorsements.getText());
        c.Person.setElement(CPerson.RESTRICTIONS, field_restrictions.getText());
        c.Person.setElement(CPerson.HEIGHT, field_height.toString());
        c.Person.setElement(CPerson.WEIGHT, field_weight.getText());
        c.Person.setElement(CPerson.OBSERVATIONS, field_observations.getText());
        c.Person.setElement(CPerson.NUM_OF_VIOLATIONS, field_numOfViolations.toString());
    }
    
    /**
     * restoreUIFieldsFromCitation - populate the UI fields from those currently
     *                               stored in the citation record
     */
    private void restoreUIFieldsFromCitation()
    {
                field_id.setText(c.Person.getElement(CPerson.ID));
                field_idstate.setSelectedIndex(c.Person.getElement(CPerson.ID_STATE));
                field_title.setSelectedIndex(c.Person.getElement(CPerson.TITLE));
                field_first.setText(c.Person.getElement(CPerson.FIRST));
                field_middle.setText(c.Person.getElement(CPerson.MIDDLE));
                field_last.setText(c.Person.getElement(CPerson.LAST));
                field_suffix.setSelectedIndex(c.Person.getElement(CPerson.SUFFIX));
                field_address.setText(c.Person.getElement(CPerson.ADDRESS));
                field_city.setSelectedIndex(c.Person.getElement(CPerson.CITY));
                field_state.setSelectedIndex(c.Person.getElement(CPerson.STATE));
                field_zip.setText(c.Person.getElement(CPerson.ZIP));
                field_DOB.setText(c.Person.getElement(CPerson.DOB));
                field_sex.setSelectedIndex(c.Person.getElement(CPerson.SEX));
                field_race.setSelectedIndex(c.Person.getElement(CPerson.RACE));
                field_eyes.setSelectedIndex(c.Person.getElement(CPerson.EYES));
                field_hair.setSelectedIndex(c.Person.getElement(CPerson.HAIR));
                field_endorsements.setText(c.Person.getElement(CPerson.ENDORSEMENTS));
                field_restrictions.setText(c.Person.getElement(CPerson.RESTRICTIONS));
                field_height.setSelectedIndex(c.Person.getElement(CPerson.HEIGHT));
                field_weight.setText(c.Person.getElement(CPerson.WEIGHT));
                field_observations.setText(c.Person.getElement(CPerson.OBSERVATIONS));    
                field_numOfViolations.setLabel(c.Person.getElement(CPerson.NUM_OF_VIOLATIONS));
    }    
} 
