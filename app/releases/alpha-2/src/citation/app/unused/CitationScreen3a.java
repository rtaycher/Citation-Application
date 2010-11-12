/*
 * CitationScreen3a.java
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



class CitationScreen3a extends MainScreen {
    
    private Citation c;
    
    private CheckboxField field_accident;
    private CheckboxField field_radar;
    private CheckboxField field_jail_booking;
    private CheckboxField field_paced;
    private CheckboxField field_school_zone;
    private CheckboxField field_alcohol;
    private EditField field_vbfi;
    private ObjectChoiceField field_speed_limit;
    private EditField field_alleged_speed;
    
    CitationScreen3a(Citation _c) {  
        c = _c;
        
        CustomLabelField lf1 = new CustomLabelField("INCIDENT ATTRIBUTES", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        lf1.setBackgroundColor(Color.WHITE);
        lf1.setTextColor(Color.BLUE);

        FontFamily fontFamily[] = FontFamily.getFontFamilies(); 
        Font font1 = fontFamily[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf1.setFont(font1); 
        setTitle(lf1);
                   
        add(new SeparatorField());
        
        //--------------

        // CheckboxField
        field_accident = new CheckboxField("Accident", false);
        add(field_accident);
    
        // CheckboxField
        field_radar = new CheckboxField("Radar", false);
        add(field_radar);
    
        // CheckboxField
        field_jail_booking = new CheckboxField("Jail Booking", false);
        add(field_jail_booking);
    
        // CheckboxField
        field_paced = new CheckboxField("Paced", false);
        add(field_paced);
    
        // CheckboxField
        field_school_zone = new CheckboxField("School Zone", false);
        add(field_school_zone);
        
        // CheckboxField
        field_alcohol = new CheckboxField("Alcohol", false);
        add(field_alcohol);
        
        add(new SeparatorField());
        
        // EditField ** Not sure what this is ** (there may be a more suitable field type than EditField)
        field_vbfi = new EditField("VBFI: ", "");
        add(field_vbfi);
        
        // ObjectChoiceField
        String choicestrs1[] = {"5MPH", "10MPH", "15MPH", "20MPH", "25MPH", "30MPH", "35MPH", "40MPH", "45MPH", "50MPH", "55MPH", "60MPH", "65MPH", "70MPH", "75MPH", "80MPH"};
        field_speed_limit = new ObjectChoiceField("Speed Limit: ", choicestrs1, 0);
        add(field_speed_limit);                  
               
        // EditField
        field_alleged_speed = new EditField("Alleged Speed: ", "");
        add(field_alleged_speed);
        
        add(new SeparatorField()); 
        
        
        // ButtonField row - set all buttons at the bottom row of the form
        HorizontalFieldManager navButtonManager = new HorizontalFieldManager();

        // ButtonField
        ButtonField btn_prev;        
        btn_prev = new ButtonField("Prev", ButtonField.CONSUME_CLICK);
        btn_prev.setChangeListener(new ButtonListener3a(this, c)); 
        navButtonManager.add(btn_prev);
                
        ButtonField btn_next;
        btn_next = new ButtonField("Next", ButtonField.CONSUME_CLICK);
        btn_next.setChangeListener(new ButtonListener3a(this, c)); 
        navButtonManager.add(btn_next);

        add(navButtonManager);

        add(new SeparatorField());


        addMenuItem(saveItem3a);
        addMenuItem(getItem3a);
        
        
        //restoreUIFieldsFromCitation();
     }
     
    public static class ButtonListener3a implements FieldChangeListener {
        Citation c;
        CitationScreen3a cs2;
                    
        public ButtonListener3a(CitationScreen3a _cs2, Citation _n2){
            cs2 = _cs2;
            c = _n2;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn4 = (ButtonField) field;
            if(btn4.getLabel().toString() == "Next"){ 
                //cs2.storeUIFieldsToCitation();
                if(cs2.field_alcohol.getChecked() == true){
                  UiApplication.getUiApplication().pushScreen(new CitationScreen3b(c));
                }
                else {
                  UiApplication.getUiApplication().pushScreen(new CitationScreen3c(c));  
                }      
            }
            else if(btn4.getLabel().toString() == "Prev"){
                //cs2.storeUIFieldsToCitation();
                UiApplication.getUiApplication().popScreen(cs2);
            }   
        }
    } 
    
    
 
 
    private MenuItem saveItem3a = new MenuItem("Save", 110, 10) {
        public void run() {
                
                //storeUIFieldsToCitation();
                CitationStore.Save(); 
                
        }
    };

    private MenuItem getItem3a = new MenuItem("Get", 110, 11) {
        public void run() {
                
                CitationStore.Restore();
                //restoreUIFieldsFromCitation();
           
        }
    };     
    
    /**
     * storeUIFieldsToCitation - transfer the UI field values to the citation record
     */
    /* 
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
    */
    
    /**
     * restoreUIFieldsFromCitation - populate the UI fields from those currently
     *                               stored in the citation record
     */
    /* 
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
    */   
} 
