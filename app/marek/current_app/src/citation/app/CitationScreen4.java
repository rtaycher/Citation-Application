/*
 * CitationScreen4.java
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
import net.rim.device.api.system.*;


class CitationScreen4 extends MainScreen {
    
        private ObjectChoiceField field_type;
        private EditField field_plate;
        private ObjectChoiceField field_reg_state;
        private EditField field_make;
        private EditField field_model;
        private ObjectChoiceField field_style;
        private EditField field_year;
        private ObjectChoiceField field_prim_color;
        private ObjectChoiceField field_sec_color;
        private EditField field_VIN;
    
    private Citation c;
    
    CitationScreen4(Citation _c) {  
        c = _c;
        
        
        String cNumberString = Integer.toString(c.Number);
/*
        CustomLabelField lf = new CustomLabelField("OREGON UNIFORM CITATION: #" + cNumberString, LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        lf.setBackgroundColor(0x00194E84);
        lf.setTextColor(0x00ffffff);
        FontFamily fontFamily0[] = FontFamily.getFontFamilies(); 
        Font font = fontFamily0[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf.setFont(font); 
        
        add(lf);         
*/
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
        add(new SeparatorField());        
        
        
        CustomLabelField lf1 = new CustomLabelField("********** VEHICLE INFORMATION **********", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        lf1.setBackgroundColor(Color.LIGHTGREY);
        lf1.setTextColor(Color.BLUE);

        FontFamily fontFamily[] = FontFamily.getFontFamilies(); 
        Font font1 = fontFamily[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf1.setFont(font1); 
        add(lf1);
                   
        add(new SeparatorField());
        
        //--------------   

        // ObjectChoiceField
        String choicestrs0[] = {"Auto", "Boat", "Plane", "Moped"};
        field_type = new ObjectChoiceField("Type: ", choicestrs0, 0);
        add(field_type); 

        // EditField
        field_plate = new EditField("License Plate #: ", "");
        add(field_plate);
    
        // ObjectChoiceField
        String choicestrs1[] = {"OR", "WA", "CA"};
        field_reg_state = new ObjectChoiceField("Registered State: ", choicestrs1, 0);
        add(field_reg_state);
    
        add(new SeparatorField());

        // EditField
        field_make = new EditField("Make: ", "");
        add(field_make);
  
            // EditField
        field_model = new EditField("Model: ", "");
        add(field_model);  
  
            // ObjectChoiceField
        String choicestrs2[] = {"Coupe", "Compact", "Mid-Sized", "Full-Sized", "SUV", "Minivan", "Passenger Van"};
        field_style = new ObjectChoiceField("Style: ", choicestrs2, 0);
        add(field_style);  

        // NumericChoiceField   
        field_year = new EditField("Year: ", "");
        add(field_year);
         
        // ObjectChoiceField
        String choicestrs3[] = {"Black", "White", "Grey", "Red", "Green", "Yellow", "Purple", "Orange", "Brown"};
        field_prim_color = new ObjectChoiceField("Primary Color: ", choicestrs3, 0);
        add(field_prim_color);
    
        // ObjectChoiceField
        String choicestrs4[] = {"Black", "White", "Grey", "Red", "Green", "Yellow", "Purple", "Orange", "Brown"};
        field_sec_color = new ObjectChoiceField("2ndary Color: ", choicestrs4, 0);
        add(field_sec_color);
        
        // EditField
        field_VIN = new EditField("Vehicle ID#: ", "");
        add(field_VIN); 
        
        add(new SeparatorField());
               
        // ButtonField row - set all buttons at the bottom row of the form
        HorizontalFieldManager navButtonManager = new HorizontalFieldManager();

        // ButtonField
        ButtonField btn_prev;        
        btn_prev = new ButtonField("Prev", ButtonField.CONSUME_CLICK);
        btn_prev.setChangeListener(new ButtonListener4(this, c)); 
        navButtonManager.add(btn_prev);
                
        ButtonField btn_next;
        btn_next = new ButtonField("Next", ButtonField.CONSUME_CLICK);
        btn_next.setChangeListener(new ButtonListener4(this, c)); 
        navButtonManager.add(btn_next);

        add(navButtonManager);
        
        addMenuItem(saveItem4);
        addMenuItem(getItem4);
        
        restoreUIFieldsFromCitation();
    }   
    
    public static class ButtonListener4 implements FieldChangeListener {
        Citation c;
        CitationScreen4 cs4;
                    
        public ButtonListener4(CitationScreen4 _cs4, Citation _c){
            cs4 = _cs4;
            c = _c;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn = (ButtonField) field;
            if(btn.getLabel().toString() == "Next"){ 
                cs4.storeUIFieldsToCitation();
                UiApplication.getUiApplication().pushScreen(new CitationScreen4a(c));
            }
            else if(btn.getLabel().toString() == "Prev"){
                cs4.storeUIFieldsToCitation();
                UiApplication.getUiApplication().popScreen(cs4);
            }   
        }
    } 
    

 
 
    private MenuItem saveItem4 = new MenuItem("Save", 110, 10) {
        public void run() {
            
                storeUIFieldsToCitation();
                CitationStore.Save();
        }
    };

    private MenuItem getItem4 = new MenuItem("Get", 110, 11) {
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
        c.Vehicle.setElement(CVehicle.TYPE, field_type.toString());
        c.Vehicle.setElement(CVehicle.PLATE, field_plate.toString());
        c.Vehicle.setElement(CVehicle.REG_STATE, field_reg_state.toString());
        c.Vehicle.setElement(CVehicle.MAKE, field_make.getText());
        c.Vehicle.setElement(CVehicle.MODEL, field_model.getText());
        c.Vehicle.setElement(CVehicle.STYLE, field_style.toString());    
        c.Vehicle.setElement(CVehicle.YEAR, field_year.toString());    
        c.Vehicle.setElement(CVehicle.PRIM_COLOR, field_prim_color.toString());
        c.Vehicle.setElement(CVehicle.SEC_COLOR, field_sec_color.toString());
        c.Vehicle.setElement(CVehicle.VIN, field_VIN.getText());       
    }
    
    /**
     * restoreUIFieldsFromCitation - populate the UI fields from those currently
     *                               stored in the citation record
     */
    private void restoreUIFieldsFromCitation()
    {
        field_type.setSelectedIndex(c.Vehicle.getElement(CVehicle.TYPE));
        field_plate.setText(c.Vehicle.getElement(CVehicle.PLATE));
        field_reg_state.setSelectedIndex(c.Vehicle.getElement(CVehicle.REG_STATE));
        field_make.setText(c.Vehicle.getElement(CVehicle.MAKE));
        field_model.setText(c.Vehicle.getElement(CVehicle.MODEL));
        field_style.setSelectedIndex(c.Vehicle.getElement(CVehicle.STYLE));
        field_year.setText(c.Vehicle.getElement(CVehicle.YEAR));
        field_prim_color.setSelectedIndex(c.Vehicle.getElement(CVehicle.PRIM_COLOR));
        field_sec_color.setSelectedIndex(c.Vehicle.getElement(CVehicle.SEC_COLOR));
        field_VIN.setText(c.Vehicle.getElement(CVehicle.VIN));
    }
} 
