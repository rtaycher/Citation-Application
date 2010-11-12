/*
 * CitationScreen6.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package citation.app;

import java.util.Date;
import java.util.Vector;

import net.rim.device.api.io.http.HttpDateParser;

import citation.ui.*;
import citation.query.*;

import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import citation.data.*;
import citation.ui.CustomLabelField;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import citation.data.*;
import net.rim.device.api.system.*;

class CitationScreen6 extends MainScreen {
    
    private Citation c;
    
    private ObjectChoiceField field_bac;
    private DateField field_bac_dte;
    private DateField field_bac_tme;
    
    CitationScreen6(Citation _c) {  
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
        
 /*       
        CustomLabelField lf1 = new CustomLabelField("*************** BAC ATTRIBUTES *****************", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        lf1.setBackgroundColor(Color.LIGHTGREY);
        lf1.setTextColor(Color.BLUE);

        FontFamily fontFamily[] = FontFamily.getFontFamilies(); 
        Font font1 = fontFamily[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf1.setFont(font1); 
        add(lf1);
 */
        LabelField lf3 = new LabelField("********** BAC ATTRIBUTES *********", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH | DrawStyle.HCENTER)
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
        btn_prev.setChangeListener(new ButtonListener6(this, c)); 
        navButtonManager.add(btn_prev);
                
        ButtonField btn_next;
        btn_next = new ButtonField("Next", ButtonField.CONSUME_CLICK);
        btn_next.setChangeListener(new ButtonListener6(this, c)); 
        navButtonManager.add(btn_next);

        add(navButtonManager);

        add(new SeparatorField());


        addMenuItem(saveItem6);
        addMenuItem(getItem6);
        
        
        restoreUIFieldsFromCitation();
     }
     
    public static class ButtonListener6 implements FieldChangeListener {
        Citation c;
        CitationScreen6 cs6;
                    
        public ButtonListener6(CitationScreen6 _cs6, Citation _n6){
            cs6 = _cs6;
            c = _n6;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn4 = (ButtonField) field;
            if(btn4.getLabel().toString() == "Next"){ 
                cs6.storeUIFieldsToCitation();
                UiApplication.getUiApplication().pushScreen(new CitationScreen7(c));
            }
            else if(btn4.getLabel().toString() == "Prev"){
                cs6.storeUIFieldsToCitation();
                UiApplication.getUiApplication().popScreen(cs6);
            }   
        }
    } 
    
    
 
 
    private MenuItem saveItem6 = new MenuItem("Save", 110, 10) {
        public void run() {        
                storeUIFieldsToCitation();
                CitationStore.Save();           
        }
    };

    private MenuItem getItem6 = new MenuItem("Get", 110, 11) {
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
        c.Violation.setElement(CViolation.BAC, field_bac.toString());
        // get the time value and re-convert back to a full date string
        //c.Violation.setElement(CViolation.BAC_DATE, (new Date(field_bac_dte.getDate()).toString()));
        //c.Violation.setElement(CViolation.BAC_TIME, (new Date(field_bac_tme.getDate()).toString()));
        c.Violation.setElement(CViolation.BAC_DATE, field_bac_dte.toString());
        c.Violation.setElement(CViolation.BAC_TIME, field_bac_tme.toString());
    }

    
    /**
     * restoreUIFieldsFromCitation - populate the UI fields from those currently
     *                               stored in the citation record
     */
    
    private void restoreUIFieldsFromCitation()
    {
        field_bac.setSelectedIndex(c.Violation.getElement(CViolation.BAC));
         
        String temp_date = c.Violation.getElement(CViolation.BAC_DATE);
        // that's dumb - date.toString() inserts GMT which HttpDateParser can't seem to handle
        int startGMT = temp_date.indexOf("GMT ");
        int endGMT = startGMT + 4; 
        if ( startGMT > 0 )
        {
                temp_date = temp_date.substring(0, startGMT) + temp_date.substring(endGMT);
        }
        long td = HttpDateParser.parse(temp_date);
        field_bac_dte.setDate(td);
        
        String temp_time = c.Violation.getElement(CViolation.BAC_TIME);
        // that's dumb - date.toString() inserts GMT which HttpDateParser can't seem to handle
        startGMT = temp_time.indexOf("GMT ");
        endGMT = startGMT + 4; 
        if ( startGMT > 0 )
        {   // strip 'GMT ' out of the time string
                temp_time = temp_time.substring(0, startGMT) + temp_time.substring(endGMT);
        }
        long tt = HttpDateParser.parse(temp_time);
        field_bac_tme.setDate(tt);
    } 
      
} 
