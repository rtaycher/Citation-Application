/*
 * CitationScreen5.java
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



class CitationScreen5 extends MainScreen {
    
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
    
    CitationScreen5(Citation _c) {  
        c = _c;
        
        String cNumberString = Integer.toString(c.Number);
        CustomLabelField lf = new CustomLabelField("OREGON UNIFORM CITATION: #" + cNumberString, LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        lf.setBackgroundColor(Color.CYAN);
        lf.setTextColor(Color.BLUE);
        FontFamily fontFamily0[] = FontFamily.getFontFamilies(); 
        Font font = fontFamily0[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf.setFont(font); 
        
        add(lf);         
        
        add(new SeparatorField());
        
        CustomLabelField lf1 = new CustomLabelField("************* INCIDENT ATTRIBUTES **************", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        lf1.setBackgroundColor(Color.LIGHTGREY);
        lf1.setTextColor(Color.BLUE);

        FontFamily fontFamily[] = FontFamily.getFontFamilies(); 
        Font font1 = fontFamily[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf1.setFont(font1); 
        add(lf1);
                   
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
        btn_prev.setChangeListener(new ButtonListener5(this, c)); 
        navButtonManager.add(btn_prev);
                
        ButtonField btn_next;
        btn_next = new ButtonField("Next", ButtonField.CONSUME_CLICK);
        btn_next.setChangeListener(new ButtonListener5(this, c)); 
        navButtonManager.add(btn_next);

        add(navButtonManager);

        add(new SeparatorField());


        addMenuItem(saveItem5);
        addMenuItem(getItem5);
        
        
        //restoreUIFieldsFromCitation();
     }
     
    public static class ButtonListener5 implements FieldChangeListener {
        Citation c;
        CitationScreen5 cs5;
                    
        public ButtonListener5(CitationScreen5 _cs5, Citation _n5){
            cs5 = _cs5;
            c = _n5;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn4 = (ButtonField) field;
            if(btn4.getLabel().toString() == "Next"){ 
                //cs2.storeUIFieldsToCitation();
                if(cs5.field_alcohol.getChecked() == true){
                  UiApplication.getUiApplication().pushScreen(new CitationScreen6(c));
                }
                else {
                  UiApplication.getUiApplication().pushScreen(new CitationScreen7(c));  
                }      
            }
            else if(btn4.getLabel().toString() == "Prev"){
                //cs2.storeUIFieldsToCitation();
                UiApplication.getUiApplication().popScreen(cs5);
            }   
        }
    } 
    
    
 
 
    private MenuItem saveItem5 = new MenuItem("Save", 110, 10) {
        public void run() {
                
                //storeUIFieldsToCitation();
                CitationStore.Save(); 
                
        }
    };

    private MenuItem getItem5 = new MenuItem("Get", 110, 11) {
        public void run() {
                
                CitationStore.Restore();
                //restoreUIFieldsFromCitation();
           
        }
    };     
    
    /**
     * storeUIFieldsToCitation - transfer the UI field values to the citation record
     */
    private void storeUIFieldsToCitation()
    {           
        c.Violation.setElement(CViolation.ACCIDENT, field_accident.toString());
        c.Violation.setElement(CViolation.RADAR, field_radar.toString());
        c.Violation.setElement(CViolation.JAIL_BOOKING, field_jail_booking.toString());
        c.Violation.setElement(CViolation.PACED, field_paced.toString());
        c.Violation.setElement(CViolation.SCHOOL_ZONE, field_school_zone.toString());
        c.Violation.setElement(CViolation.ALCOHOL, field_alcohol.toString());
        
        c.Violation.setElement(CViolation.VBFI, field_vbfi.getText());
        c.Violation.setElement(CViolation.SPEED_LIMIT, field_speed_limit.toString());
        c.Violation.setElement(CViolation.ALLEGED_SPEED, field_alleged_speed.getText());
    }
    
    /**
     * restoreUIFieldsFromCitation - populate the UI fields from those currently
     *                               stored in the citation record
     */
    private void restoreUIFieldsFromCitation()
    {
        field_accident.setLabel(c.Violation.getElement(CViolation.ACCIDENT));
        field_radar.setLabel(c.Violation.getElement(CViolation.RADAR));
        field_jail_booking.setLabel(c.Violation.getElement(CViolation.JAIL_BOOKING));
        field_paced.setLabel(c.Violation.getElement(CViolation.PACED));
        field_school_zone.setLabel(c.Violation.getElement(CViolation.SCHOOL_ZONE));
        field_alcohol.setLabel(c.Violation.getElement(CViolation.ALCOHOL));
        
        field_vbfi.setLabel(c.Violation.getElement(CViolation.VBFI));
        field_speed_limit.setLabel(c.Violation.getElement(CViolation.SPEED_LIMIT));
        field_alleged_speed.setLabel(c.Violation.getElement(CViolation.ALLEGED_SPEED));
    }    
} 
