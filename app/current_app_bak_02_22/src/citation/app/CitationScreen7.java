/*
 * CitationScreen7.java
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


class CitationScreen7 extends MainScreen {
    
    private Citation c;
    
    
    private EditField field_statute;
    private EditField field_fine;
    private CheckboxField field_intentional;
    private CheckboxField field_knowing;
    private CheckboxField field_reckless;
    private CheckboxField field_criminal_neg;
    private CheckboxField field_culpable;
    
    CitationScreen7(Citation _c) {  
        c = _c;
        
        
        String cNumberString = Integer.toString(c.Number);
        CustomLabelField lf = new CustomLabelField("OREGON UNIFORM CITATION: #" + cNumberString, LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        lf.setBackgroundColor(0x00194E84);
        lf.setTextColor(0x00ffffff);
        FontFamily fontFamily0[] = FontFamily.getFontFamilies(); 
        Font font = fontFamily0[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf.setFont(font); 
        
        add(lf);         
        
        add(new SeparatorField()); 
        
        
        CustomLabelField lf1 = new CustomLabelField("************ VIOLATION INFORMATION *************", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        lf1.setBackgroundColor(Color.LIGHTGREY);
        lf1.setTextColor(Color.BLUE);

        FontFamily fontFamily[] = FontFamily.getFontFamilies(); 
        Font font1 = fontFamily[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf1.setFont(font1); 
        add(lf1);
                   
        add(new SeparatorField());
        
        //--------------

        // EditField
        field_statute = new EditField("Statute: ", "");
        add(field_statute);
        
        // EditField
        field_fine = new EditField("Fine: ", "");
        add(field_fine);
        
        add(new SeparatorField());  

        CustomLabelField lf2 = new CustomLabelField("Involves:", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        lf2.setBackgroundColor(Color.LIGHTGREY);
        lf2.setTextColor(Color.BLUE);

        FontFamily fontFamily2[] = FontFamily.getFontFamilies(); 
        Font font2 = fontFamily2[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf2.setFont(font2); 
        add(lf2);
                   
        add(new SeparatorField());
        
        // CheckboxField
        field_intentional = new CheckboxField("Intentional", false);
        add(field_intentional);
    
        // CheckboxField
        field_knowing = new CheckboxField("Knowing", false);
        add(field_knowing);
    
        // CheckboxField
        field_reckless = new CheckboxField("Reckless", false);
        add(field_reckless);
    
        // CheckboxField
        field_criminal_neg = new CheckboxField("Criminal Negligence", false);
        add(field_criminal_neg);
    
        // CheckboxField
        field_culpable = new CheckboxField("No Culpable Mental State", false);
        add(field_culpable);
        
        add(new SeparatorField());  
        
        // ButtonField row - set all buttons at the bottom row of the form
        HorizontalFieldManager navButtonManager = new HorizontalFieldManager();

        // ButtonField
        ButtonField btn_prev;        
        btn_prev = new ButtonField("Prev", ButtonField.CONSUME_CLICK);
        btn_prev.setChangeListener(new ButtonListener7(this, c)); 
        navButtonManager.add(btn_prev);
        /*        
        ButtonField btn_done;        
        btn_done = new ButtonField("Done", ButtonField.CONSUME_CLICK);
        btn_done.setChangeListener(new ButtonListener7(this, c)); 
        navButtonManager.add(btn_done);       
        */        
        ButtonField btn_next;
        btn_next = new ButtonField("Next", ButtonField.CONSUME_CLICK);
        btn_next.setChangeListener(new ButtonListener7(this, c)); 
        navButtonManager.add(btn_next);

        add(navButtonManager);

        add(new SeparatorField());


        addMenuItem(saveItem7);
        addMenuItem(getItem7);
        
        
        restoreUIFieldsFromCitation();
     }
     
    public static class ButtonListener7 implements FieldChangeListener {
        Citation c;
        CitationScreen7 cs7;
                    
        public ButtonListener7(CitationScreen7 _cs7, Citation _n7){
            cs7 = _cs7;
            c = _n7;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn4 = (ButtonField) field;
            if(btn4.getLabel().toString() == "Next"){ 
                cs7.storeUIFieldsToCitation();
                UiApplication.getUiApplication().pushScreen(new CitationScreen8(c));
            }
            /*
            else if(btn4.getLabel().toString() == "Done"){
                cs7.storeUIFieldsToCitation();
                //UiApplication.getUiApplication().pushScreen(new CitationScreen7(c));
            } 
            */  
            else if(btn4.getLabel().toString() == "Prev"){
                cs7.storeUIFieldsToCitation();
                UiApplication.getUiApplication().popScreen(cs7);
            }   
        }
    } 
    

    private MenuItem saveItem7 = new MenuItem("Save", 110, 10) {
        public void run() {       
                storeUIFieldsToCitation();
                CitationStore.Save();          
        }
    };

    private MenuItem getItem7 = new MenuItem("Get", 110, 11) {
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
        c.Violation.setElement(CViolation.STATUTE, field_statute.getText());
        c.Violation.setElement(CViolation.FINE, field_fine.getText());
        
        String intentionalStatus = field_intentional.getChecked() ? "true" : "false";
        String knowingStatus = field_knowing.getChecked() ? "true" : "false";
        String recklessStatus = field_reckless.getChecked() ? "true" : "false";
        String criminal_negStatus = field_criminal_neg.getChecked() ? "true" : "false";
        String culpableStatus = field_culpable.getChecked() ? "true" : "false";
        c.Violation.setElement(CViolation.INTENTIONAL, intentionalStatus);  
        c.Violation.setElement(CViolation.KNOWING, knowingStatus);  
        c.Violation.setElement(CViolation.RECKLESS, recklessStatus);  
        c.Violation.setElement(CViolation.CRIMINAL_NEG, criminal_negStatus); 
        c.Violation.setElement(CViolation.CULPABLE, culpableStatus);        
    }
 
    
    /**
     * restoreUIFieldsFromCitation - populate the UI fields from those currently
     *                               stored in the citation record
     */
 
    private void restoreUIFieldsFromCitation()
    {
        field_statute.setText(c.Violation.getElement(CViolation.STATUTE));
        field_fine.setText(c.Violation.getElement(CViolation.FINE));
        
        boolean intentionalStatus = c.Violation.getElement(CViolation.INTENTIONAL).equals("true");
        boolean knowingStatus = c.Violation.getElement(CViolation.KNOWING).equals("true");
        boolean recklessStatus = c.Violation.getElement(CViolation.RECKLESS).equals("true");
        boolean criminal_negStatus = c.Violation.getElement(CViolation.CRIMINAL_NEG).equals("true");
        boolean culpableStatus = c.Violation.getElement(CViolation.CULPABLE).equals("true");
        field_intentional.setChecked(intentionalStatus);
        field_knowing.setChecked(knowingStatus); 
        field_reckless.setChecked(recklessStatus);
        field_criminal_neg.setChecked(criminal_negStatus); 
        field_culpable.setChecked(culpableStatus);
    } 
    
} 
