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



class CitationScreen3b extends MainScreen {
    
    private Citation c;
    
    private DateField field_bac_dte;
    private DateField field_bac_tme;
    private ObjectChoiceField field_speed_limit;
    
    CitationScreen3b(Citation _c) {  
        c = _c;
        
        CustomLabelField lf1 = new CustomLabelField("BAC ATTRIBUTES", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        lf1.setBackgroundColor(Color.WHITE);
        lf1.setTextColor(Color.BLUE);

        FontFamily fontFamily[] = FontFamily.getFontFamilies(); 
        Font font1 = fontFamily[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf1.setFont(font1); 
        setTitle(lf1);
                   
        add(new SeparatorField());
        
        //--------------

        // ObjectChoiceField
        String choicestrs0[] = {"N/A", "0.01-0.029", "0.03-0.059", "0.06-0.10", "0.11-0.20", "0.21-0.29", "0.30-0.39", ">0.40"};
        field_bac = new ObjectChoiceField("Blood Alcohol Level (BAC): ", choicestrs0, 0);
        add(field_bac);
        
        add(new SeparatorField()); 
             
        field_bac_dte = new DateField("Date of BAC: ", Long.MIN_VALUE, DateField.DATE);
        add(field_bac_dte);
        field_bac_tme = new DateField("Time of BAC: ", Long.MIN_VALUE, DateField.TIME);
        add(field_bac_tme);    
        
        add(new SeparatorField());        
        
        // ButtonField row - set all buttons at the bottom row of the form
        HorizontalFieldManager navButtonManager = new HorizontalFieldManager();

        // ButtonField
        ButtonField btn_prev;        
        btn_prev = new ButtonField("Prev", ButtonField.CONSUME_CLICK);
        btn_prev.setChangeListener(new ButtonListener3b(this, c)); 
        navButtonManager.add(btn_prev);
                
        ButtonField btn_next;
        btn_next = new ButtonField("Next", ButtonField.CONSUME_CLICK);
        btn_next.setChangeListener(new ButtonListener3b(this, c)); 
        navButtonManager.add(btn_next);

        add(navButtonManager);

        add(new SeparatorField());


        addMenuItem(saveItem3b);
        addMenuItem(getItem3b);
        
        
        //restoreUIFieldsFromCitation();
     }
     
    public static class ButtonListener3b implements FieldChangeListener {
        Citation c;
        CitationScreen3b cs2;
                    
        public ButtonListener3b(CitationScreen3b _cs2, Citation _n2){
            cs2 = _cs2;
            c = _n2;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn4 = (ButtonField) field;
            if(btn4.getLabel().toString() == "Next"){ 
                //cs2.storeUIFieldsToCitation();
                UiApplication.getUiApplication().pushScreen(new CitationScreen3c(c));
            }
            else if(btn4.getLabel().toString() == "Prev"){
                //cs2.storeUIFieldsToCitation();
                UiApplication.getUiApplication().popScreen(cs2);
            }   
        }
    } 
    
    
 
 
    private MenuItem saveItem3b = new MenuItem("Save", 110, 10) {
        public void run() {
                
                //storeUIFieldsToCitation();
                CitationStore.Save(); 
                
        }
    };

    private MenuItem getItem3b = new MenuItem("Get", 110, 11) {
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
