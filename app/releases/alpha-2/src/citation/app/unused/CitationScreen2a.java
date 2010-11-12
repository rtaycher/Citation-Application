/*
 * CitationScreen2a.java
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
import citation.ui.*;
import citation.query.*;

import net.rim.device.api.i18n.SimpleDateFormat;
import net.rim.device.api.i18n.DateFormat;
import java.util.Date;

import java.util.Calendar;

/**
 * 
 */
class CitationScreen2a extends MainScreen {
    public GPSLocation currentLocation = null;  
    private QueryMgr query = null;
    private Citation c = null;
 
 
    private EditField field_gps;
    private EditField field_gps_address;
    private EditField field_gps_intersection;
    private EditField field_address;
    private EditField field_city;
    private EditField field_state;
    private EditField field_zip;
    
    //private String field_date;
    //private String field_time;
    private DateField field_date;
    private DateField field_time;
    
    private CheckboxField field_highway;
    private CheckboxField field_commonplace;
    
    CitationScreen2a(Citation _c) {    
        super();
        c = _c;
        query = new QueryMgr();
        
        CustomLabelField lf1 = new CustomLabelField("TIME & PLACE INFORMATION", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        lf1.setBackgroundColor(Color.CYAN);
        lf1.setTextColor(Color.BLUE);
        FontFamily fontFamily[] = FontFamily.getFontFamilies(); 
        Font font1 = fontFamily[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf1.setFont(font1); 
        
        setTitle(lf1);

        //--------------
        add(new SeparatorField());
        
        // EditField
        field_gps = new EditField("GPS: ", "");
        add(field_gps);
        
        field_gps_address = new EditField("GPS Address: ", "");
        add(field_gps_address);
        
        field_gps_intersection = new EditField("GPS Intersection: ", "");
        add(field_gps_intersection);
        
        add(new SeparatorField());

         // EditField
        field_address = new EditField("Address (approx): ", "");
        add(field_address);   
        
        // ObjectChoiceField
        field_city = new EditField("City: ", "");
        add(field_city);                      
       
        // ObjectChoiceField
        field_state = new EditField("State: ", "");
        add(field_state); 
        
         // EditField
        field_zip = new EditField("Zip: ", "");
        add(field_zip);    
        
        add(new SeparatorField()); 
    
        /*
        field_date = new SimpleDateFormat("yyyy/MM/dd").format(new Date(System.currentTimeMillis()));  
        add(new RichTextField("Date: " + field_date));
    
        field_time = new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));
        add(new RichTextField("Time: " + field_time));
        */
        field_date = new DateField("Date: ", Long.MIN_VALUE, DateField.DATE);
        add(field_date);
        field_time = new DateField("Time: ", Long.MIN_VALUE, DateField.TIME);
        add(field_time);  
        
        add(new SeparatorField()); 
        
         // CheckboxField
        field_highway = new CheckboxField("Highway", false);
        add(field_highway);
    
        // CheckboxField
        field_commonplace = new CheckboxField("Commonplace", false);
        add(field_commonplace);
        
        add(new SeparatorField());
        
        // ButtonField row - set all buttons at the bottom row of the form
        HorizontalFieldManager navButtonManager = new HorizontalFieldManager();

        // ButtonField
        ButtonField btn_prev;        
        btn_prev = new ButtonField("Prev", ButtonField.CONSUME_CLICK);
        btn_prev.setChangeListener(new ButtonListener2a(this, c)); 
        navButtonManager.add(btn_prev);
                
        ButtonField btn_next;
        btn_next = new ButtonField("Next", ButtonField.CONSUME_CLICK);
        btn_next.setChangeListener(new ButtonListener2a(this, c)); 
        navButtonManager.add(btn_next);

        add(navButtonManager);

        add(new SeparatorField());


        addMenuItem(saveItem2a);
        addMenuItem(getItem2a);
        
        
        //restoreUIFieldsFromCitation();
     }
     
    public static class ButtonListener2a implements FieldChangeListener {
        Citation c;
        CitationScreen2a cs2;
                    
        public ButtonListener2a(CitationScreen2a _cs2, Citation _n2){
            cs2 = _cs2;
            c = _n2;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn4 = (ButtonField) field;
            if(btn4.getLabel().toString() == "Next"){ 
                //cs2.storeUIFieldsToCitation();
                UiApplication.getUiApplication().pushScreen(new CitationScreen3(c));
            }
            else if(btn4.getLabel().toString() == "Prev"){
                //cs2.storeUIFieldsToCitation();
                UiApplication.getUiApplication().popScreen(cs2);
            }   
        }
    } 
    
    
 
 
    private MenuItem saveItem2a = new MenuItem("Save", 110, 10) {
        public void run() {
                
                //storeUIFieldsToCitation();
                CitationStore.Save(); 
                
        }
    };

    private MenuItem getItem2a = new MenuItem("Get", 110, 11) {
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
