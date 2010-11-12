/*
 * CitationScreen8.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */ 

package citation.app;


import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;
import citation.data.*;
import citation.ui.CustomLabelField;

import net.rim.device.api.util.*;
import java.util.*;

/**
 * 
 */
class CitationScreen8 extends MainScreen {
    ButtonField btn;
    ButtonField btn2;
    ButtonField btn3;
    
    private Citation c;
     
    CitationScreen8(Citation _c) {  
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


      LabelField lf1 = new LabelField("********* VERIFY LOCATION INFORMATION **********", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH)
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
            if(!c.Loc.getElement(6).equals(null)){
                add(new RichTextField("Highway/Commonplace: Highway"));
            } else if(!c.Loc.getElement(7).equals(null)){
                add(new RichTextField("Highway/Commonplace: Commonplace")); 
            }
            
            add(new SeparatorField()); 
            
      LabelField lf2 = new LabelField("********* VERIFY PERSON INFORMATION **********", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH)
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
         
            int i=0;
            add(new RichTextField("Type: " + c.Person.getElement(i++))); 
            add(new RichTextField("ID: " + c.Person.getElement(i++))); 
            add(new RichTextField("ID State: " + c.Person.getElement(i++))); 
            add(new RichTextField("Title: " + c.Person.getElement(i++))); 
            add(new RichTextField("First: " + c.Person.getElement(i++))); 
            add(new RichTextField("Middle: " + c.Person.getElement(i++))); 
            add(new RichTextField("Last: " + c.Person.getElement(i++))); 
            add(new RichTextField("Suffix: " + c.Person.getElement(i++))); 
            add(new RichTextField("Address: " + c.Person.getElement(i++))); 
            add(new RichTextField("City: " + c.Person.getElement(i++))); 
            add(new RichTextField("State: " + c.Person.getElement(i++))); 
            add(new RichTextField("Zip: " + c.Person.getElement(i++))); 
            add(new RichTextField("DOB: " + c.Person.getElement(i++))); 
            add(new RichTextField("Sex: " + c.Person.getElement(i++))); 
            add(new RichTextField("Race: " + c.Person.getElement(i++))); 
            add(new RichTextField("Eyes: " + c.Person.getElement(i++))); 
            add(new RichTextField("Hair: " + c.Person.getElement(i++))); 
            add(new RichTextField("Endorsements: " + c.Person.getElement(i++))); 
            add(new RichTextField("Restrictions: " + c.Person.getElement(i++))); 
            add(new RichTextField("Height: " + c.Person.getElement(i++))); 
            add(new RichTextField("Weight: " + c.Person.getElement(i++))); 
            add(new RichTextField("Observations: " + c.Person.getElement(i++))); 
            add(new RichTextField("Number of Violations: " + c.Person.getElement(i))); 

         add(new SeparatorField());  
         
      LabelField lf3 = new LabelField("********* VERIFY VEHICLE INFORMATION **********", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH)
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
         
            i=0;
            add(new RichTextField("Type: " + c.Vehicle.getElement(i++))); 
            add(new RichTextField("Plate: " + c.Vehicle.getElement(i++))); 
            add(new RichTextField("Registered State: " + c.Vehicle.getElement(i++))); 
            add(new RichTextField("Make: " + c.Vehicle.getElement(i++))); 
            add(new RichTextField("Model: " + c.Vehicle.getElement(i++))); 
            add(new RichTextField("Style: " + c.Vehicle.getElement(i++))); 
            add(new RichTextField("Year: " + c.Vehicle.getElement(i++))); 
            add(new RichTextField("Primary Color: " + c.Vehicle.getElement(i++))); 
            add(new RichTextField("Secondary Color: " + c.Vehicle.getElement(i++))); 
            add(new RichTextField("Vehicle ID: " + c.Vehicle.getElement(i))); 
            
        add(new SeparatorField());  
        
      LabelField lf4 = new LabelField("********* VERIFY VIOLATION INFORMATION **********", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH)
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
        
            i=0;
            add(new RichTextField("Citation #: " + c.Violation.getElement(i++))); 
            add(new RichTextField("Date: " + c.Violation.getElement(i++))); 
            add(new RichTextField("Time: " + c.Violation.getElement(i++))); 
            add(new RichTextField("Offenses: " + c.Violation.getElement(i++))); 
            if(!c.Violation.getElement(i++).equals(null)){
                add(new RichTextField("Type: Accident")); 
            }
            if(!c.Violation.getElement(i++).equals(null)){
                add(new RichTextField("Type: Radar")); 
            }
            if(!c.Violation.getElement(i++).equals(null)){
                add(new RichTextField("Type: School Zone")); 
            }
            if(!c.Violation.getElement(i++).equals(null)){
                add(new RichTextField("Type: Paced")); 
            }   
            
            // The next enumeration from the CViolation class is a RadioButtonGroup so skip this...
            i++;
            
            // Check if the Yes or No option is selected 
            /*
            if(!c.Violation.getElement(i).equals(null)){
                add(new RichTextField("Type: Paced"));
            add(new RichTextField("Radar: " + c.Violation.getElement(i++))); 
            add(new RichTextField(": " + c.Violation.getElement(i++))); 
            add(new RichTextField("Primary Color: " + c.Violation.getElement(i++))); 
            add(new RichTextField("Secondary Color: " + c.Violation.getElement(i++))); 
            add(new RichTextField("Vehicle ID: " + c.Violation.getElement(i)));           
            */               

       // }
       
        add(new SeparatorField()); 
        add(new SeparatorField()); 
/*
        // ButtonField
        btn = new ButtonField("Go Back", ButtonField.CONSUME_CLICK);
        btn.setChangeListener(new ButtonListener8(c)); 
        add(btn);
                
        // ButtonField
        btn2 = new ButtonField("Close", ButtonField.CONSUME_CLICK);
        btn2.setChangeListener(new ButtonListener8(c)); 
        add(btn2);
        
        // ButtonField
        btn3 = new ButtonField("Edit", ButtonField.CONSUME_CLICK);
        btn3.setChangeListener(new ButtonListener8(c1)); 
        add(btn3); 
        
        
        addMenuItem(saveItem8);
        addMenuItem(getItem8);       
*/
    }
/*    
    public class ButtonListener8 implements FieldChangeListener {
        Citation c8;
                    
        public ButtonListener8(Citation n8){
            //c2 = new CitationScreen2(n2);
            c8 = n8;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn = (ButtonField) field;
            if(btn.getLabel().toString() == "Go Back"){ 
                //UiApplication.getUiApplication().pushScreen(new CitationScreen9(c8));
               
            }
            else if(btn.getLabel().toString() == "Close"){
                //UiApplication.getUiApplication().pushScreen(new CitationScreen1(c2));
                 //pushScreen(new CitationScreen1());
            }   
            else if(btn.getLabel().toString() == "Edit"){
                //UiApplication.getUiApplication().pushScreen(new CitationScreen1(c2));
                 //pushScreen(new CitationScreen1());
            }             
        }
    }  
    
    
    
    
    
    
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
    };


    static {
        store = PersistentStore.getPersistentObject(0xdec6a67096f833cL);
    
        synchronized (store) {
            if (store.getContents() == null) {
                store.setContents(new Vector());
                store.commit();
            }
        }
        _data = new Vector();
        _data = (Vector) store.getContents();
    }

    private static final class StoreInfo implements Persistable {
        private Vector _elements;




        public StoreInfo() {
            _elements = new Vector(4);
            for (int i = 0; i < _elements.capacity(); ++i) {
                _elements.addElement(new String(""));
            }
        }
        public String getElement(int id) {
            return (String) _elements.elementAt(id);
        }

        public void setElement(int id, String value) {
            _elements.setElementAt(value, id);
        }
    }
    
 */   
} 

