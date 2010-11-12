/*
 * CitationScreen2.java
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

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import citation.data.*;
import citation.query.HTTPGoogleAPI;
import citation.query.QueryMgr;
import citation.ui.CustomLabelField;
import net.rim.device.api.system.*;

/**
 * Class: CitationScreen2
 * Description: This class handles the input of defendent information from the user.
 */
public class CitationScreen2 extends MainScreen {
       
    private RadioButtonGroup rgrp0;
    private RadioButtonField rad_person;
    private RadioButtonField rad_business;
    
    private EditField field_id; 
    private ObjectChoiceField field_idstate;

    private ObjectChoiceField field_title;
    private EditField field_first;
    private EditField field_middle;
    private EditField field_last;
    private ObjectChoiceField field_suffix;
    private EditField field_address;
    private EditField field_city;
    private EditField field_state;
    private EditField field_zip;
    private EditField field_DOB;
    private ObjectChoiceField field_sex;
    private EditField field_race;
    private ObjectChoiceField field_eyes;
    private ObjectChoiceField field_hair;
    private EditField field_endorsements;
    private EditField field_restrictions;
    private EditField field_height;
    private EditField field_weight;
    private EditField field_observations;
    private ObjectChoiceField field_violations;

    private Citation c;
    
    /**
     * This is the constructor method for CitationScreen2. It handles the creation of data fields 
     * necessary for storing required defendent information for the citation. 
     * Inputs: Citation object
     * Outputs: none
     */
    public CitationScreen2(Citation _c) {  
        c = _c;
        
        String cNumberString = Integer.toString(c.Number);

        LabelField lf0 = new LabelField("OREGON UNIFORM CITATION: #" + cNumberString, LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH | DrawStyle.HCENTER)
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
        FontFamily fontFamily0[] = FontFamily.getFontFamilies(); 
        Font font0 = fontFamily0[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf0.setFont(font0);              
        setTitle(lf0);                    
        
        //--------------

        add(new SeparatorField());

        LabelField lf2 = new LabelField("************** DEFENDANT ***************", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH | DrawStyle.HCENTER)
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
        add(lf2);     
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
        
        // ObjectChoiceField
        String choicestrs0[] = {"OR", "WA", "CA"};
        field_idstate = new ObjectChoiceField("State: ", choicestrs0, 0);
        add(field_idstate);
        
        ButtonField btn_dmv = new ButtonField("DMV Lookup", ButtonField.CONSUME_CLICK);
        btn_dmv.setChangeListener((new DMVButtonListener2(this)));
        add(btn_dmv);
         
        add(new SeparatorField());   
        
        // ObjectChoiceField
        String choicestrs1[] = {"Mr.", "Mrs.", "Miss", "Ms.", "Dr."};
        field_title = new ObjectChoiceField("Title: ", choicestrs1, 0);
        add(field_title);   
        
        //add(new SeparatorField());  
        
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
        
        // EditField
        field_city = new EditField("City: ", "");
        add(field_city);                      
       
        // ObjectChoiceField
        field_state = new EditField("State: ", "");
        add(field_state); 
        
         // EditField
        field_zip = new EditField("Zip: ", "");
        add(field_zip);    
        
        add(new SeparatorField()); 
        
        // EditField
        //field_DOB = new EditField("Date of Birth\n(mm/dd/yyyy): ", "");
        field_DOB = new EditField("Date of Birth: ", "");
        add(field_DOB);  
        
         // ObjectChoiceField
        String choicestrs5[] = {"Male", "Female"};
        field_sex = new ObjectChoiceField("Sex: ", choicestrs5, 0);
        add(field_sex);                                
     
        // ObjectChoiceField
        field_race = new EditField("Race: ", "");
        add(field_race);         
     
        // ObjectChoiceField
        String choicestrs7[] = {"Amber", "Blue", "Brown", "Gray", "Green", "Hazel", "Red"};
        field_eyes = new ObjectChoiceField("Eyes: ", choicestrs7, 0);
        add(field_eyes);            
        
        // ObjectChoiceField
        String choicestrs8[] = {"Black", "Blonde", "Brown", "Auburn", "Gray/White", "Red"};
        field_hair = new ObjectChoiceField("Hair: ", choicestrs8, 0);
        add(field_hair);
        
        add(new SeparatorField()); 
        
        // EditField
        field_endorsements = new EditField("Endorsements: ", "");
        add(field_endorsements);
        
        // EditField
        field_restrictions = new EditField("Restrictions: ", "");
        add(field_restrictions);
        
        add(new SeparatorField());  

        // EditField
        field_height = new EditField("Height: ", "");
        add(field_height);
        
        // EditField
        field_weight = new EditField("Weight: ", "");
        add(field_weight);
    
        add(new SeparatorField());
    
        // EditField
        field_observations = new EditField("Other Observations: ", "");
        add(field_observations);
        
        add(new SeparatorField());  
        
        // ObjectChoiceField
        String choicestrs10[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        field_violations = new ObjectChoiceField("Number of Violations: ", choicestrs10, 0);
        add(field_violations);
        
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
     
    /**
     * Inner Class: ButtonListener2
     * Description: This class implements a button listener to respond to user pressed
     * buttons on the first UI screen. 
     */     
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
    
    /**
     * Class: DMVButtonListener2
     * Description: This class implements the DMV lookup and allows for auto-population of 
     * other necessary data fields.
     */
    static final class DMVButtonListener2 implements FieldChangeListener {
        private CitationScreen2 cs2 = null;
        
        public DMVButtonListener2(CitationScreen2 _cs2){
                cs2 = _cs2;
        }
        
        public void fieldChanged(Field field, int context) {
                ButtonField btn = (ButtonField)field;
                if (btn != null) {
                                Status.show("Retrieving DMV record...");
                        QueryMgr query = ((CitationApp)UiApplication.getUiApplication()).getQueryMgr();
                        CPerson cp = (CPerson)query.findDMVRecord(cs2.field_id.getText());
                        if (cp != null) {
                                cs2.field_last.setText(cp.getElement(CPerson.LAST));
                                cs2.field_middle.setText(cp.getElement(CPerson.MIDDLE));
                                cs2.field_first.setText(cp.getElement(CPerson.FIRST));
                                cs2.field_address.setText(cp.getElement(CPerson.ADDRESS));
                                cs2.field_city.setText(cp.getElement(CPerson.CITY));
                                cs2.field_state.setText(cp.getElement(CPerson.STATE));
                                cs2.field_zip.setText(cp.getElement(CPerson.ZIP));
                                cs2.field_DOB.setText(cp.getElement(CPerson.DOB));
                                cs2.field_endorsements.setText(cp.getElement(CPerson.ENDORSEMENTS));
                                cs2.field_height.setText(cp.getElement(CPerson.HEIGHT));
                                cs2.field_restrictions.setText(cp.getElement(CPerson.RESTRICTIONS));
                                cs2.field_sex.setSelectedIndex(cp.getElement(CPerson.SEX));
                                cs2.field_weight.setText(cp.getElement(CPerson.WEIGHT));
                        }
                        else {
                            StringBuffer msg = new StringBuffer("Unable to retrieve DMV record.\n\n");
                            msg.append(query.getLastError());
                            Dialog.inform(msg.toString());
                        }
                }
        }
    }
    
    /**
     * This declaration of variable saveItem2 creates a new menu item option for saving the current data.
     */ 
    private MenuItem saveItem2 = new MenuItem("Save", 110, 10) {
        public void run() {
                
                storeUIFieldsToCitation();
                CitationStore.Save(); 
                
        }
    };

    /**
     * This declaration of variable getItem2 creates a new menu item option for retrieving previously stored data.
     */ 
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

        String person_sel = rad_person.isSelected() ? "true" : "false";
        c.Person.setElement(CPerson.PERSON_SELECT, person_sel);
        c.Person.setElement(CPerson.PERSON, rad_person.getLabel()); 
        System.out.println("Store Function -> PERSON = " + rad_person.getLabel());
        
        String business_sel = rad_business.isSelected() ? "true" : "false";
        c.Person.setElement(CPerson.BUSINESS_SELECT, business_sel); 
        c.Person.setElement(CPerson.BUSINESS, rad_business.getLabel()); 
        System.out.println("Store Function -> BUSINESS = " + rad_business.getLabel());  
          
        c.Person.setElement(CPerson.ID, field_id.getText());
        c.Person.setElement(CPerson.ID_STATE, field_idstate.toString());
        c.Person.setElement(CPerson.TITLE, field_title.toString());  
        c.Person.setElement(CPerson.FIRST, field_first.getText());
        c.Person.setElement(CPerson.MIDDLE, field_middle.getText());
        c.Person.setElement(CPerson.LAST, field_last.getText());    
        c.Person.setElement(CPerson.SUFFIX, field_suffix.toString());    
        c.Person.setElement(CPerson.ADDRESS, field_address.getText());
        c.Person.setElement(CPerson.CITY, field_city.getText());
        c.Person.setElement(CPerson.STATE, field_state.toString());          
        c.Person.setElement(CPerson.ZIP, field_zip.getText());
        c.Person.setElement(CPerson.DOB, field_DOB.getText());
        c.Person.setElement(CPerson.SEX, field_sex.toString());
        c.Person.setElement(CPerson.RACE, field_race.getText());
        c.Person.setElement(CPerson.EYES, field_eyes.toString());
        c.Person.setElement(CPerson.HAIR, field_hair.toString());
        c.Person.setElement(CPerson.ENDORSEMENTS, field_endorsements.getText());
        c.Person.setElement(CPerson.RESTRICTIONS, field_restrictions.getText());
        c.Person.setElement(CPerson.HEIGHT, field_height.toString());
        c.Person.setElement(CPerson.WEIGHT, field_weight.getText());
        c.Person.setElement(CPerson.OBSERVATIONS, field_observations.getText());
        c.Person.setElement(CPerson.NUM_OF_VIOLATIONS, field_violations.toString());
    }
 
    /**
     * restoreUIFieldsFromCitation - populate the UI fields from those currently
     *                               stored in the citation record
     */
    private void restoreUIFieldsFromCitation()
    {
        boolean person_sel = c.Person.getElement(CPerson.PERSON_SELECT).equals("true");
        rad_person.setSelected(person_sel);
               
        boolean business_sel = c.Person.getElement(CPerson.BUSINESS_SELECT).equals("true");
        rad_business.setSelected(business_sel);
                   
        field_id.setText(c.Person.getElement(CPerson.ID));
        field_idstate.setSelectedIndex(c.Person.getElement(CPerson.ID_STATE));
        field_title.setSelectedIndex(c.Person.getElement(CPerson.TITLE));
        field_first.setText(c.Person.getElement(CPerson.FIRST));
        field_middle.setText(c.Person.getElement(CPerson.MIDDLE));
        field_last.setText(c.Person.getElement(CPerson.LAST));
        field_suffix.setSelectedIndex(c.Person.getElement(CPerson.SUFFIX));
        field_address.setText(c.Person.getElement(CPerson.ADDRESS));
        field_city.setText(c.Person.getElement(CPerson.CITY));
        field_state.setText(c.Person.getElement(CPerson.STATE));
        field_zip.setText(c.Person.getElement(CPerson.ZIP));
        field_DOB.setText(c.Person.getElement(CPerson.DOB));
        field_sex.setSelectedIndex(c.Person.getElement(CPerson.SEX));
        field_race.setText(c.Person.getElement(CPerson.RACE));
        field_eyes.setSelectedIndex(c.Person.getElement(CPerson.EYES));
        field_hair.setSelectedIndex(c.Person.getElement(CPerson.HAIR));
        field_endorsements.setText(c.Person.getElement(CPerson.ENDORSEMENTS));
        field_restrictions.setText(c.Person.getElement(CPerson.RESTRICTIONS));
        field_height.setText(c.Person.getElement(CPerson.HEIGHT));
        field_weight.setText(c.Person.getElement(CPerson.WEIGHT));
        field_observations.setText(c.Person.getElement(CPerson.OBSERVATIONS));   
        field_violations.setSelectedIndex(c.Person.getElement(CPerson.NUM_OF_VIOLATIONS));
    }    
} 
