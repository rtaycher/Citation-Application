/*
 * CitationScreen5.java
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
import citation.app.CitationScreen2.DMVButtonListener2;
import citation.data.*;
import citation.ui.CustomLabelField;
import net.rim.device.api.io.http.HttpDateParser;
import citation.data.*;
import citation.ui.*;
import citation.query.*;
import net.rim.device.api.system.*;

class CitationScreen5 extends MainScreen {

    //--------------------     

    private EditField field_crt_name;
    private EditField field_crt_address;
    private EditField field_crt_city;
    private EditField field_crt_state;
    private EditField field_crt_zip;
    private EditField field_crt_phone;
    
    private DateField field_crt_dte;
    private DateField field_crt_tme;
     
    private Citation c = null;
    
    CitationScreen5(Citation _c) {  
        c = _c;
 
        String cNumberString = Integer.toString(c.Number);
 /*       
        CustomLabelField lf1 = new CustomLabelField("DEFENDENT INFRACTIONS", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        lf1.setBackgroundColor(0x00194E84);
        lf1.setTextColor(0x00ffffff);

        FontFamily fontFamily[] = FontFamily.getFontFamilies(); 
        Font font1 = fontFamily[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf1.setFont(font1); 
        setTitle(lf1);
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
/*         
        CustomLabelField lbl3 = new CustomLabelField("*********** Court Information ***********", Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH);
        lbl3.setBackgroundColor(Color.LIGHTGREY);
        lbl3.setTextColor(Color.BLUE);
    
        FontFamily fontFamily3[] = FontFamily.getFontFamilies(); 
        Font fnt3 = fontFamily3[1].getFont(FontFamily.CBTF_FONT, 14).derive(Font.BOLD | Font.ITALIC); 
        lbl3.setFont(fnt3);
        add(lbl3);
*/
      LabelField lf3 = new LabelField("*********** Court Information ***********", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH | DrawStyle.HCENTER)
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
        FontFamily fontFamily3[] = FontFamily.getFontFamilies(); 
        Font font3 = fontFamily3[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf3.setFont(font0);              
        add(lf3);     
        add(new SeparatorField());             
         
        ButtonField btn_court = new ButtonField("Get Court Date", ButtonField.CONSUME_CLICK);
        btn_court.setChangeListener((new CourtButtonListener5(this)));
        add(btn_court);
                                     
        // EditField
        field_crt_name = new EditField("Court Name: ", "");
        add(field_crt_name);     
            
         // EditField
        field_crt_address = new EditField("Address: ", "");
        add(field_crt_address);

        // EditField
        field_crt_city = new EditField("City: ", "");
        add(field_crt_city);                       
               
        // EditField
        field_crt_state = new EditField("State: ", "");
        add(field_crt_state);                     
             
        // EditField
        field_crt_zip = new EditField("Zip: ", "");
        add(field_crt_zip);      
        
        // EditField
        field_crt_phone = new EditField("Phone #: ", "");
        add(field_crt_phone); 
        
        add(new SeparatorField());

        field_crt_dte = new DateField("Date: ", Long.MIN_VALUE, DateField.DATE);
        field_crt_dte.setDate(System.currentTimeMillis());
        add(field_crt_dte);
        field_crt_tme = new DateField("Time: ", Long.MIN_VALUE, DateField.TIME);
        field_crt_tme.setDate(System.currentTimeMillis());
        add(field_crt_tme);   


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
        
 
        addMenuItem(saveItem5);
        addMenuItem(getItem5);
        
        restoreUIFieldsFromCitation();
    }   
    
    /**
     * Inner Class: ButtonListener5
     * Description: This class implements a button listener to respond to user pressed
     * buttons on the first UI screen. 
     */    
    public static class ButtonListener5 implements FieldChangeListener {
        Citation c;
        CitationScreen5 cs5;
                    
        public ButtonListener5(CitationScreen5 _cs5, Citation _c){
            cs5 = _cs5;
            c = _c;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn = (ButtonField) field;
            if(btn.getLabel().toString() == "Next"){ 
                cs5.storeUIFieldsToCitation();
                UiApplication.getUiApplication().pushScreen(new CitationScreen6(c));
            }
            else if(btn.getLabel().toString() == "Prev"){
                cs5.storeUIFieldsToCitation();
                UiApplication.getUiApplication().popScreen(cs5);
            }   
        }
    } 
    
    static final class CourtButtonListener5 implements FieldChangeListener {
        private CitationScreen5 cs5 = null;
        
        public CourtButtonListener5(CitationScreen5 _cs5){
                cs5 = _cs5;
        }
        
        public void fieldChanged(Field field, int context) {
                ButtonField btn = (ButtonField)field;
                if (btn != null) {
                                Status.show("Retrieving Court Date...");
                        QueryMgr query = ((CitationApp)UiApplication.getUiApplication()).getQueryMgr();
                        CViolation cv = (CViolation)query.getCourtInfo();
                        
                        if (cv != null) {
                        String temp_date = cv.getElement(CViolation.COURT_DATE);
                        // that's dumb - date.toString() inserts GMT which HttpDateParser can't seem to handle
                        int startGMT = temp_date.indexOf("GMT ");
                        int endGMT = startGMT + 4; 
                        if ( startGMT > 0 )
                        {
                                temp_date = temp_date.substring(0, startGMT) + temp_date.substring(endGMT);
                        }
                        long td = HttpDateParser.parse(temp_date);
                        cs5.field_crt_dte.setDate(td);
                        
                        String temp_time = cv.getElement(CViolation.COURT_TIME);
                        // that's dumb - date.toString() inserts GMT which HttpDateParser can't seem to handle
                        startGMT = temp_time.indexOf("GMT ");
                        endGMT = startGMT + 4; 
                        if ( startGMT > 0 )
                        {   // strip 'GMT ' out of the time string
                                temp_time = temp_time.substring(0, startGMT) + temp_time.substring(endGMT);
                        }
                        long tt = HttpDateParser.parse(temp_time);
                        cs5.field_crt_tme.setDate(tt);

                        cs5.field_crt_name.setText(cv.getElement(CViolation.COURT_NAME));
                        cs5.field_crt_address.setText(cv.getElement(CViolation.COURT_ADDRESS));
                        cs5.field_crt_city.setText(cv.getElement(CViolation.COURT_CITY));
                        cs5.field_crt_state.setText(cv.getElement(CViolation.COURT_STATE));
                        cs5.field_crt_zip.setText(cv.getElement(CViolation.COURT_ZIP));
                        cs5.field_crt_phone.setText(cv.getElement(CViolation.COURT_PHONE));                            }
                        else {
                            StringBuffer msg = new StringBuffer("Unable to retrieve Court Date. \n\n");
                            msg.append(query.getLastError());
                            Dialog.inform(msg.toString());
                        }
                }
        }
    }
    



    private MenuItem saveItem5 = new MenuItem("Save", 110, 10) {
        public void run() {
                
               storeUIFieldsToCitation();
            CitationStore.Save();
        }
    };

    private MenuItem getItem5 = new MenuItem("Get", 110, 11) {
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
        c.Violation.setElement(CViolation.COURT_NAME, field_crt_name.getText());
        c.Violation.setElement(CViolation.COURT_ADDRESS, field_crt_address.getText());
        c.Violation.setElement(CViolation.COURT_CITY, field_crt_city.getText());
        c.Violation.setElement(CViolation.COURT_STATE, field_crt_state.getText());
        c.Violation.setElement(CViolation.COURT_ZIP, field_crt_zip.getText());
        c.Violation.setElement(CViolation.COURT_PHONE, field_crt_phone.getText());
  
        c.Violation.setElement(CViolation.COURT_DATE, field_crt_dte.toString());
        c.Violation.setElement(CViolation.COURT_TIME, field_crt_tme.toString());
    }
    
    /**
     * restoreUIFieldsFromCitation - populate the UI fields from those currently
     *                               stored in the citation record
     */
    private void restoreUIFieldsFromCitation()
    {
 
        String temp_date = c.Violation.getElement(CViolation.DATE);
        // that's dumb - date.toString() inserts GMT which HttpDateParser can't seem to handle
        int startGMT = temp_date.indexOf("GMT ");
        int endGMT = startGMT + 4; 
        if ( startGMT > 0 )
        {
                temp_date = temp_date.substring(0, startGMT) + temp_date.substring(endGMT);
        }
        long td = HttpDateParser.parse(temp_date);
        field_crt_dte.setDate(td);
        
        String temp_time = c.Violation.getElement(CViolation.TIME);
        // that's dumb - date.toString() inserts GMT which HttpDateParser can't seem to handle
        startGMT = temp_time.indexOf("GMT ");
        endGMT = startGMT + 4; 
        if ( startGMT > 0 )
        {   // strip 'GMT ' out of the time string
                temp_time = temp_time.substring(0, startGMT) + temp_time.substring(endGMT);
        }
        long tt = HttpDateParser.parse(temp_time);
        field_crt_tme.setDate(tt);

        field_crt_name.setText(c.Violation.getElement(CViolation.COURT_NAME));
        field_crt_address.setText(c.Violation.getElement(CViolation.COURT_ADDRESS));
        field_crt_city.setText(c.Violation.getElement(CViolation.COURT_CITY));
        field_crt_state.setText(c.Violation.getElement(CViolation.COURT_STATE));
        field_crt_zip.setText(c.Violation.getElement(CViolation.COURT_ZIP));
        field_crt_phone.setText(c.Violation.getElement(CViolation.COURT_PHONE));                  
    }
    
} 

