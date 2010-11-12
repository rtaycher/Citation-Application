/*
 * CitationScreen1.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package newCitation.com;


import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;

class CitationScreen1 extends MainScreen {
        
        LabelField lbl;
        LabelField lbl2;
        LabelField lbl3;
        
        ButtonField btn;
        
        Citation c;
        
    public CitationScreen1(){
        super();
        c = new Citation();
     
        // Setting page title
        LabelField lf1 = new LabelField("OREGON UNIFORM CITATION & COMPLAINT", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH)
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
        Font font1 = fontFamily[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf1.setFont(font1); 

        setTitle(lf1);
        
        //LabelField lf2 = new LabelField("Use for All Violations or Crimes Where Separate Complaint Will Not Be Filled/ORS 153.045 or 133.069", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        //setTitle(lf2);

        //FontFamily fontFamily2[] = FontFamily.getFontFamilies(); 
        //Font font2 = fontFamily2[1].getFont(FontFamily.CBTF_FONT, 12); 
        //RichTextField mess1 = new RichTextField("Use for All Violations or Crimes Where Separate Complaint Will");
        //mess1.setFont(font2);
        //RichTextField mess3 = new RichTextField("Not Be Filled/ORS 153.045 or 133.069");
        //mess3.setFont(font2);
        //add(mess1);
        //add(mess3);

        add(new SeparatorField());
        
        lbl2 = new LabelField("Create Citation(s)", 0, -1, Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH)
        {
            protected void paintBackground(net.rim.device.api.ui.Graphics g) 
            { 
            g.clear(); 
            g.getColor(); 
            g.setColor(Color.GRAY); 
            g.fillRect(0, 0, Display.getWidth(), Display.getHeight()); 
            g.setColor(Color.BLUE);                
            } 
        };
        
        FontFamily fontFamily2[] = FontFamily.getFontFamilies(); 
        Font fnt2 = fontFamily2[1].getFont(FontFamily.CBTF_FONT, 12).derive(Font.BOLD | Font.ITALIC); 
        lbl2.setFont(fnt2);
        add(lbl2);
             
        add(new SeparatorField());
        
        // EditField
        c.numOfPersons = new EditField("Number of Persons Involved: ", "");
        add(c.numOfPersons);
        
         // EditField
        c.numOfVehicles = new EditField("Number of Vehicles Involved: ", "");
        add(c.numOfVehicles);       
        
        add(new RichTextField("Location of the Incident"));
        add(new SeparatorField());
        
         // EditField
        c.address = new EditField("Address: ", "");
        add(c.address);           
        
        // ObjectChoiceField
        String choicestrs0[] = {"Portland", "Milwaukie", "Clackamas", "Gresham", "Gladstone", "Canby", "Hillsboro"};
        c.city = new ObjectChoiceField("City/Other Public Body: ", choicestrs0, 0);
        add(c.city);
        
        // ObjectChoiceField
        String choicestrs1[] = {"OR", "WA", "CA"};
        c.state = new ObjectChoiceField("State: ", choicestrs1, 0);
        add(c.state);
        
        // EditField
        c.zip = new EditField("Zip: ", "");
        add(c.zip);   
        
               
        // ButtonField
        btn = new ButtonField("Submit", ButtonField.CONSUME_CLICK);
        
        btn.setChangeListener(new ButtonListener1(c)); 
        
        add(btn);
        
        add(new SeparatorField());
    }
    
    final class ButtonListener1 implements FieldChangeListener {
        Citation c1;
                    
        public ButtonListener1(Citation n){
            c1 = n;
        }
                    
        public void fieldChanged(Field field, int context) {
            UiApplication.getUiApplication().pushScreen(new CitationScreen2(c1));
        }
    }
         
} 
