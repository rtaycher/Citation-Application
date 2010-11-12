/*
 * CitationScreen8.java
 *
 * � <your company here>, 2003-2008
 * Confidential and proprietary.
 */ 

package citation.app;


import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;
import citation.comm.*;//does this exist, we do seem to need it!
import citation.data.*;
import citation.output.CitationOutput;
import citation.ui.*;

import net.rim.device.api.util.*;

import java.rmi.RemoteException;
import java.util.*;

/**
 * 
 */
class CitationScreen8 extends MainScreen {

    
    private Citation c;
     
    CitationScreen8(Citation _c) {  
        c = _c;

        
        /*
        HorizontalFieldManager hfm = new HorizontalFieldManager(Field.FIELD_HCENTER) {
           protected void paint(Graphics graphics)
           {
             graphics.setBackgroundColor(Color.LIGHTGREY);
             graphics.clear();

             super.paint(graphics);
           }
        };
        */

        String cNumberString = Integer.toString(c.Number);
        CustomLabelField lf = new CustomLabelField("OREGON UNIFORM CITATION: #" + cNumberString, LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        //lf.setBackgroundColor(Color.CYAN);
        lf.setBackgroundColor(0x00194E84);
        //lf.setTextColor(Color.BLUE);
        lf.setTextColor(0x00ffffff);
        FontFamily fontFamily0[] = FontFamily.getFontFamilies(); 
        Font font = fontFamily0[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf.setFont(font); 
        
        add(lf);         
        
        add(new SeparatorField());


      LabelField lf1 = new LabelField("********* VERIFY LOCATION INFORMATION **********", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH | LabelField.FIELD_HCENTER)
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
        FontFamily fontFamily[] = FontFamily.getFontFamilies(); 
        Font font1 = fontFamily[1].getFont(FontFamily.CBTF_FONT, 12); 
        lf1.setFont(font1); 
        add(lf1);
       
        
        add(new SeparatorField());  

      
            add(new RichTextField("Address: " + c.Loc.getElement(2)));      
            add(new RichTextField("City: " + c.Loc.getElement(3)));
            add(new RichTextField("State: " + c.Loc.getElement(4)));
            add(new RichTextField("Zip: " + c.Loc.getElement(5)));
          

             
            add(new SeparatorField()); 
            
            //String temp = c.Loc.getElement(6).equals(null) ? c.Loc.getElement(7) : c.Loc.getElement(6);
            String temp;
            if(!c.Loc.getElement(6).equals("false")){
                add(new RichTextField("--> Highway "));    
            } 
            if(!c.Loc.getElement(7).equals("false")){
                add(new RichTextField("--> Commonplace")); 
            }
            
            add(new SeparatorField()); 
            
      LabelField lf2 = new LabelField("********* VERIFY PERSON INFORMATION **********", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH | LabelField.FIELD_HCENTER)
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
        Font font2 = fontFamily2[1].getFont(FontFamily.CBTF_FONT, 12); 
        lf2.setFont(font2); 
        add(lf2);
        
        
        add(new SeparatorField()); 
         
            //int i=0;
            add(new RichTextField("Type: " + c.Person.getElement(0))); 
            add(new RichTextField("ID: " + c.Person.getElement(1))); 
            add(new RichTextField("ID State: " + c.Person.getElement(2))); 
            add(new SeparatorField());
            add(new RichTextField("Title: " + c.Person.getElement(3))); 
            add(new RichTextField("First: " + c.Person.getElement(4))); 
            add(new RichTextField("Middle: " + c.Person.getElement(5))); 
            add(new RichTextField("Last: " + c.Person.getElement(6))); 
            add(new RichTextField("Suffix: " + c.Person.getElement(7))); 
            add(new SeparatorField());
            add(new RichTextField("Address: " + c.Person.getElement(8))); 
            add(new RichTextField("City: " + c.Person.getElement(9))); 
            add(new RichTextField("State: " + c.Person.getElement(10))); 
            add(new RichTextField("Zip: " + c.Person.getElement(11))); 
            add(new SeparatorField());
            add(new RichTextField("DOB: " + c.Person.getElement(12))); 
            add(new RichTextField("Sex: " + c.Person.getElement(13))); 
            add(new RichTextField("Race: " + c.Person.getElement(14))); 
            add(new RichTextField("Eyes: " + c.Person.getElement(15))); 
            add(new RichTextField("Hair: " + c.Person.getElement(16))); 
            add(new SeparatorField());
            add(new RichTextField("Endorsements: " + c.Person.getElement(17))); 
            add(new RichTextField("Restrictions: " + c.Person.getElement(18))); 
            add(new SeparatorField());
            add(new RichTextField("Height: " + c.Person.getElement(19))); 
            add(new RichTextField("Weight: " + c.Person.getElement(20))); 
            add(new SeparatorField());
            add(new RichTextField("Observations: " + c.Person.getElement(21))); 
            add(new RichTextField("Number of Violations: " + c.Person.getElement(22))); 

         add(new SeparatorField());  
               
         
      LabelField lf3 = new LabelField("********* VERIFY VEHICLE INFORMATION **********", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH | LabelField.FIELD_HCENTER)
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
        Font font3 = fontFamily3[1].getFont(FontFamily.CBTF_FONT, 12); 
        lf3.setFont(font3); 
        add(lf3);
        
        
        add(new SeparatorField());          
         
            //i=0;
            add(new RichTextField("Type: " + c.Vehicle.getElement(0))); 
            add(new RichTextField("Plate: " + c.Vehicle.getElement(1))); 
            add(new RichTextField("Registered State: " + c.Vehicle.getElement(2))); 
            add(new SeparatorField());
            add(new RichTextField("Make: " + c.Vehicle.getElement(3))); 
            add(new RichTextField("Model: " + c.Vehicle.getElement(4))); 
            add(new RichTextField("Style: " + c.Vehicle.getElement(5))); 
            add(new RichTextField("Year: " + c.Vehicle.getElement(6))); 
            add(new RichTextField("Primary Color: " + c.Vehicle.getElement(7))); 
            add(new RichTextField("Secondary Color: " + c.Vehicle.getElement(8))); 
            add(new RichTextField("Vehicle ID: " + c.Vehicle.getElement(9))); 
            
        add(new SeparatorField());  
        
      LabelField lf4 = new LabelField("********* VERIFY VIOLATION INFORMATION **********", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH | LabelField.FIELD_HCENTER)
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
        FontFamily fontFamily4[] = FontFamily.getFontFamilies(); 
        Font font4 = fontFamily4[1].getFont(FontFamily.CBTF_FONT, 12); 
        lf4.setFont(font4); 
        add(lf4);   
        
        
        add(new SeparatorField());  
        
            //i=0;
            
            add(new RichTextField("Citation #: " + cNumberString)); 
            add(new RichTextField("Date: ")); 
            add(new RichTextField("--> " + c.Violation.getElement(1))); 
            add(new RichTextField("Time: ")); 
            add(new RichTextField("--> " + c.Violation.getElement(2))); 
            //add(new RichTextField("Offenses: " + c.Violation.getElement(3))); 
            add(new SeparatorField());
            add(new RichTextField("Statute: " + c.Violation.getElement(42))); 
            add(new RichTextField("Fine: " + c.Violation.getElement(43))); 
            
            add(new SeparatorField());
            add(new LabelField("Incident Attributes", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH | LabelField.FIELD_HCENTER));
            
            if(!c.Violation.getElement(4).equals("false")){
                add(new RichTextField("--> Accident")); 
            }
            if(!c.Violation.getElement(5).equals("false")){
                add(new RichTextField("--> Radar Used")); 
            }
            if(!c.Violation.getElement(6).equals("false")){
                add(new RichTextField("--> Jail Booking")); 
            }
            if(!c.Violation.getElement(7).equals("false")){
                add(new RichTextField("--> Paced")); 
            }   
            if(!c.Violation.getElement(8).equals("false")){
                add(new RichTextField("--> School Zone")); 
            }            
            if(!c.Violation.getElement(9).equals("false")){
                add(new RichTextField("--> Alcohol Involvement"));
                add(new SeparatorField());
                add(new LabelField("BAC Attributes", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH | LabelField.FIELD_HCENTER));
                add(new SeparatorField()); 
                add(new RichTextField("BAC: " + c.Violation.getElement(37)));
                add(new RichTextField("BAC Date: "));
                add(new RichTextField("--> " + c.Violation.getElement(38)));
                add(new RichTextField("BAC Time: "));
                add(new RichTextField("--> " + c.Violation.getElement(39))); 
            }
            add(new SeparatorField());
            add(new RichTextField("VFBI: " + c.Violation.getElement(10))); 
            add(new RichTextField("Speed Limit: " + c.Violation.getElement(11))); 
            add(new RichTextField("Alleged Speed: " + c.Violation.getElement(12)));             
              
              
                
            add(new SeparatorField());
            add(new LabelField("Involves: ", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH | LabelField.FIELD_HCENTER));
            
            if(!c.Violation.getElement(44).equals("false")){
                add(new RichTextField("--> Intentional")); 
            }
            if(!c.Violation.getElement(45).equals("false")){
                add(new RichTextField("--> Knowing")); 
            }
            if(!c.Violation.getElement(46).equals("false")){
                add(new RichTextField("--> Reckless")); 
            }
            if(!c.Violation.getElement(47).equals("false")){
                add(new RichTextField("--> Criminal Negligence")); 
            }   
            if(!c.Violation.getElement(48).equals("false")){
                add(new RichTextField("--> Culpable Mental State")); 
            }             
              

       // }
        add(new SeparatorField()); 
        add(new SeparatorField()); 


        CustomLabelField endlf = new CustomLabelField("", LabelField.USE_ALL_WIDTH);      
        endlf.setBackgroundColor(0x00194E84);
        add(endlf); 



        // ButtonField row - set all buttons at the bottom row of the form
        HorizontalFieldManager navButtonManager = new HorizontalFieldManager();

        // ButtonField
        ButtonField btn_Prev;
        btn_Prev = new ButtonField("Prev", ButtonField.CONSUME_CLICK);
        btn_Prev.setChangeListener(new ButtonListener8(this, c)); 
        navButtonManager.add(btn_Prev);

                
        // ButtonField
        ButtonField btn_print;
        btn_print = new ButtonField("Print", ButtonField.CONSUME_CLICK);
        btn_print.setChangeListener(new ButtonListener8(this, c)); 
        navButtonManager.add(btn_print);

        
        // ButtonField
        ButtonField btn_submit;
        btn_submit = new ButtonField("Submit", ButtonField.CONSUME_CLICK);
        btn_submit.setChangeListener(new ButtonListener8(this, c)); 
        navButtonManager.add(btn_submit);

        // ButtonField
        ButtonField btn_edit;
        btn_edit = new ButtonField("Edit", ButtonField.CONSUME_CLICK);
        btn_edit.setChangeListener(new ButtonListener8(this, c)); 
        navButtonManager.add(btn_edit); 
        
        add(navButtonManager);

        add(new SeparatorField());
                
        // addMenuItem(saveItem8);
        // addMenuItem(getItem8);       
    }

    public class ButtonListener8 implements FieldChangeListener {
        Citation c;
        CitationScreen8 cs8;
                    
        public ButtonListener8(CitationScreen8 _cs8, Citation _c){
                c = _c;
            cs8 = _cs8;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn = (ButtonField) field;
            if(btn.getLabel().toString() == "Prev"){ 
                UiApplication.getUiApplication().popScreen(cs8);
            }
            else if(btn.getLabel().toString() == "Print"){
                //UiApplication.getUiApplication().pushScreen(new CitationScreen1(c2));
                 //pushScreen(new CitationScreen1());
            }   
            else if(btn.getLabel().toString() == "Submit") {
            	CitationOutput c_out = new CitationOutput();
            	c_out.submitCitation(c);
            }
            else if(btn.getLabel().toString() == "Edit"){
                //UiApplication.getUiApplication().pushScreen(new CitationScreen1(c2));
                 //pushScreen(new CitationScreen1());
            }             
        }
    }  
    
    
    class ExRichTextField extends RichTextField { 
 
        int mTextColor; 
        int mBgColor; 
 
        public ExRichTextField(String text, int bgColor, int textColor) { 
                super(text); 
                mTextColor = textColor; 
                mBgColor = bgColor; 
        } 
 
        protected void paint(Graphics graphics) { 
                graphics.clear(); 
                graphics.setColor(mBgColor); 
                graphics.fillRect(0, 0, getWidth(), getHeight()); 
                graphics.setColor(mTextColor); 
                super.paint(graphics); 
        } 
} 
    
    
 /*   
 private MenuItem saveItem8 = new MenuItem("Save", 110, 10) {
        public void run() {
            
            StoreInfo1 info1 = new StoreInfo1();



            _data.addElement(info1);
        
            synchronized (store) {
                store.setContents(_data);
                store.commit();
            }
            Dialog.inform("Success!");



            
        }
    };

    private MenuItem getItem8 = new MenuItem("Get", 110, 11) {
        public void run() {
            
            synchronized (store) {
                _data = (Vector) store.getContents();
                if (!_data.isEmpty()) {
                    StoreInfo info = (StoreInfo)
                    _data.lastElement();



                }
            }
            
        }
    } 
 */   
} 
