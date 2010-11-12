/*
 * CitationScreen1.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package citation.com;

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;

class CitationScreen1 extends MainScreen {
        
        LabelField lbl;
        LabelField lbl2;
        LabelField lbl3;
        LabelField lbl3a;
        LabelField lbl3b;
        LabelField lbl3c;
        LabelField lbl4;
        LabelField lbl5;
        LabelField lbl6;
        LabelField lbl7;
        LabelField lbl8;
        LabelField lbl9;
        
        ButtonField btn;
        
        Citation c;
        Person p;
        Location loc;
        Vehicle v;
        
    public CitationScreen1(){
          super();
          c = new Citation();
          p = new Person();
          loc = new Location();
          v = new Vehicle();
          
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
        Font font = fontFamily[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf1.setFont(font); 

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
        
        lbl3 = new LabelField("Use for All Violations or Crimes Where Separate Complaint Will Not Be Filled/ORS 153.045 or 133.069", 0, -1, Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH)
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
        
        FontFamily fontFamily3[] = FontFamily.getFontFamilies(); 
        Font fnt3 = fontFamily3[1].getFont(FontFamily.CBTF_FONT, 12).derive(Font.BOLD | Font.ITALIC); 
        lbl3.setFont(fnt3);
        add(lbl3);
        
  
        
        
        add(new SeparatorField());
        
        c.rgrp = new RadioButtonGroup();
        c.radio0 = new RadioButtonField("Crime(s)", c.rgrp, true);
        c.radio1 = new RadioButtonField("Violation(s)", c.rgrp, false);
        
        add(c.radio0);
        add(c.radio1);
        
        add(new SeparatorField());
        
        // CheckboxField
        c.chk0 = new CheckboxField("Traffic", true);
        c.chk1 = new CheckboxField("Other", false);
        c.chk2 = new CheckboxField("Wildlife", false);
        c.chk3 = new CheckboxField("Boating", false);
        c.chk4 = new CheckboxField("Commercial Fishing", false);
        add(c.chk0);
        add(c.chk1);
        add(c.chk2);
        add(c.chk3);
        add(c.chk4);
       
        add(new SeparatorField());

        lbl = new LabelField("STATE OF OREGON", 0, -1, Field.FIELD_HCENTER);
        Font fnt = this.getFont().derive(Font.BOLD | Font.ITALIC);
        lbl.setFont(fnt);
        add(lbl);
        
        add(new SeparatorField());
        
        // ObjectChoiceField
        String choicestrs0[] = {"Portland", "Milwaukie", "Clackamas", "Gresham", "Gladstone", "Canby", "Hillsboro"};
        c.choice0 = new ObjectChoiceField("City/Other Public Body: ", choicestrs0, 0);
        add(c.choice0);
        
        add(new SeparatorField());
        
        // ObjectChoiceField
        String choicestrs1[] = {"Clackamas", "Washington", "Multnomah"};
        c.choice1 = new ObjectChoiceField("County of: ", choicestrs1, 0);
        
        add(c.choice1);
       
        add(new SeparatorField());
        
        // EditField
        c.edit = new EditField("Docket Number: ", "");
        add(c.edit);
        
        add(new SeparatorField());
        
        lbl2 = new LabelField("COURT:", 0, -1, Field.FIELD_HCENTER);
        Font fnt2 = this.getFont().derive(Font.BOLD | Font.ITALIC);
        lbl2.setFont(fnt2);
        add(lbl2);
        
        // CheckboxField
        c.chk5 = new CheckboxField("Municipal", true);
        c.chk6 = new CheckboxField("Juvenile", false);
        c.chk7 = new CheckboxField("Community", false);
        c.chk8 = new CheckboxField("Justice", false);
        c.chk9 = new CheckboxField("Circuit", false);
        c.chk10 = new CheckboxField("Tribal", false);
        add(c.chk5);
        add(c.chk6);
        add(c.chk7);
        add(c.chk8);
        add(c.chk9);
        add(c.chk10);
        
        add(new SeparatorField());
        
        lbl3a = new LabelField("THE UNDERSIGNED CERTIFIES & SAYS THAT THE FOLLOWING PERSON:", 0, -1, Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH)
        {
            protected void paintBackground(net.rim.device.api.ui.Graphics g) 
            { 
            g.clear(); 
            g.getColor(); 
            g.setColor(Color.LIGHTGREY); 
            g.fillRect(0, 0, Display.getWidth(), Display.getHeight()); 
            g.setColor(Color.BLUE);                
            } 
        };
        
        FontFamily fontFamily3a[] = FontFamily.getFontFamilies(); 
        Font fnt3a = fontFamily3a[1].getFont(FontFamily.CBTF_FONT, 14).derive(Font.BOLD | Font.ITALIC); 
        //FontFamily fontFamily3[1] = FontFamily.getFontFamilies(); 
        //Font fnt3 = this.getFont().derive(Font.BOLD | Font.ITALIC);
        lbl3a.setFont(fnt3a);
        add(lbl3a);
        
        add(new SeparatorField());
        
        
        // CheckboxField
        p.chk0 = new CheckboxField("Non-CDL", false);
        p.chk1 = new CheckboxField("CDL", false);
        add(p.chk0);
        add(p.chk1);
        
         // EditField
        p.edit0 = new EditField("ID Type: ", "");
        add(p.edit0);
        
        add(new SeparatorField());
        
         // EditField
        p.edit1 = new EditField("ID No: ", "");
        add(p.edit1);
        
        add(new SeparatorField());
        
        // ObjectChoiceField
        String choicestrs3[] = {"OR", "WA", "CA"};
        p.choice0 = new ObjectChoiceField("State: ", choicestrs3, 0);
        add(p.choice0);
        
        add(new SeparatorField());
        
         // EditField
        p.edit2 = new EditField("Tel No: ", "");
        add(p.edit2);
        
        add(new SeparatorField());
        
         // EditField
        p.edit3 = new EditField("Last Name: ", "");
        add(p.edit3);
        
        // EditField
        p.edit4 = new EditField("First Name: ", "");
        add(p.edit4);
        
        // EditField
        p.edit5 = new EditField("MI: ", "");
        add(p.edit5);
        
        add(new SeparatorField());
        
        // EditField
        p.edit6 = new EditField("Address: ", "");
        add(p.edit6);
        
        add(new SeparatorField());
        
        // ObjectChoiceField
        String choicestrs4[] = {"A", "B", "C", "D", "M"};
        p.choice1 = new ObjectChoiceField("License Class: ", choicestrs4, 0);
        add(p.choice1);
        
        add(new SeparatorField());
        
        // CheckboxField
        p.chk2 = new CheckboxField("Employed to Drive", false);
        add(p.chk2);
        
        add(new SeparatorField());
        
        // ObjectChoiceField
        String choicestrs5[] = {"Portland", "Milwaukie", "Clackamas", "Gresham", "Gladstone", "Canby", "Hillsboro"};
        p.choice2 = new ObjectChoiceField("City: ", choicestrs5, 0);
        add(p.choice2);
        
        // ObjectChoiceField
        String choicestrs6[] = {"OR", "WA", "CA"};
        p.choice3 = new ObjectChoiceField("State: ", choicestrs6, 0);
        add(p.choice3);
        
        // EditField
        p.edit7 = new EditField("Zip Code: ", "");
        add(p.edit7);
        
        add(new SeparatorField());
        
        //FontFamily fontFamily4[] = FontFamily.getFontFamilies(); 
        //Font fnt4 = fontFamily4[1].getFont(FontFamily.CBTF_FONT, 14); 
       // RichTextField mess2 = new RichTextField("Def is: ");
        //mess2.setFont(fnt4);
        //add(mess2);
       
        lbl3b = new LabelField("Def is:", 0, -1, Field.FIELD_LEFT | LabelField.USE_ALL_WIDTH)
        {
            protected void paintBackground(net.rim.device.api.ui.Graphics g) 
            { 
            g.clear(); 
            g.getColor(); 
            g.setColor(Color.LIGHTGREY); 
            g.fillRect(0, 0, Display.getWidth(), Display.getHeight()); 
            g.setColor(Color.BLUE);                
            } 
        };
        
        FontFamily fontFamily3b[] = FontFamily.getFontFamilies(); 
        Font fnt3b = fontFamily3b[1].getFont(FontFamily.CBTF_FONT, 12).derive(Font.BOLD | Font.ITALIC); 
        lbl3b.setFont(fnt3b);
        add(lbl3b);
       
       
       
       
       
         // CheckboxField
        p.chk3 = new CheckboxField("Passenger", false);
        add(p.chk3);
        
        // CheckboxField
        p.chk4 = new CheckboxField("Other", false);
        add(p.chk4);
        
        // EditField
        p.edit8 = new EditField("(Enter Other): ", "");
        add(p.edit8);
        
        add(new SeparatorField());
        
        // ObjectChoiceField
        String choicestrs7[] = {"M", "F"};
        p.choice4 = new ObjectChoiceField("Sex: ", choicestrs7, 0);
        add(p.choice4);
        
        // ObjectChoiceField
        String choicestrs8[] = {"White", "Black", "Chinese", "Asian", "Arab", "Sudanese", "French", "Japanese", "Punjabis"};
        p.choice5 = new ObjectChoiceField("Race: ", choicestrs8, 0);
        add(p.choice5);
        
        // EditField
        p.edit9 = new EditField("DOB: ", "");
        add(p.edit9);
        
        // EditField
        p.edit10 = new EditField("Weight: ", "");
        add(p.edit10);
        
        // ObjectChoiceField
        String choicestrs9[] = {"5' 0''", "5' 1''", "5' 2''", "5' 3''", "5' 4''", "5' 5''", "5' 6''", "5' 7''", "5' 8''", "5' 9''", "5' 10''", "5' 11''", "6' 0''", "6' 1''", "6' 2''", "6' 3''", "6' 4''", "6' 5''", "6' 6''"};
        p.choice6 = new ObjectChoiceField("Height: ", choicestrs9, 0);
        add(p.choice6);
        
        // ObjectChoiceField
        String choicestrs10[] = {"Blonde", "Black", "Grey", "Red", "Brown"};
        p.choice7 = new ObjectChoiceField("Hair: ", choicestrs10, 0);
        add(p.choice7);
        
        // ObjectChoiceField
        String choicestrs11[] = {"Green", "Blue", "Black", "Brown"};
        p.choice8 = new ObjectChoiceField("Eyes: ", choicestrs11, 0);
        add(p.choice8);
        
        add(new SeparatorField());
        
        lbl4 = new LabelField("AT THE FOLLOWING TIME AND PLACE IN THE ABOVE-MENTIONED STATE AND COUNTY:", 0, -1, Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH)
        {
            protected void paintBackground(net.rim.device.api.ui.Graphics g) 
            { 
            g.clear(); 
            g.getColor(); 
            g.setColor(Color.LIGHTGREY); 
            g.fillRect(0, 0, Display.getWidth(), Display.getHeight()); 
            g.setColor(Color.BLUE);                
            } 
        };
        
        FontFamily fontFamily5[] = FontFamily.getFontFamilies(); 
        Font fnt5 = fontFamily5[1].getFont(FontFamily.CBTF_FONT, 14).derive(Font.BOLD | Font.ITALIC); 
        lbl4.setFont(fnt5);
        add(lbl4);
        
        add(new SeparatorField());
        
        // ObjectChoiceField
        String choicestrs12[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        loc.choice0 = new ObjectChoiceField("Month: ", choicestrs12, 0);
        add(loc.choice0);
        
        // ObjectChoiceField
        String choicestrs13[] = {"Mon", "Tues", "Wed", "Thur", "Fri", "Sat", "Sun"};
        loc.choice1 = new ObjectChoiceField("Day: ", choicestrs13, 0);
        add(loc.choice1);
        
        // NumericChoiceField   
        loc.num0 = new NumericChoiceField("Year: ", 2009, 2020, 1);
        add(loc.num0);
        
        // EditField
        loc.edit0 = new EditField("Time: ", "");
        add(loc.edit0);
        
        
        loc.rgrp = new RadioButtonGroup();
        loc.radio0 = new RadioButtonField("AM", loc.rgrp, true);
        loc.radio1 = new RadioButtonField("PM", loc.rgrp, false);
        add(loc.radio0);
        add(loc.radio1);
        
        add(new SeparatorField());
        
         // CheckboxField
        loc.chk0 = new CheckboxField("Highway", false);
        add(loc.chk0);
        
        // CheckboxField
        loc.chk1 = new CheckboxField("Premises Open to Public", false);
        add(loc.chk1);
        
        // CheckboxField
        loc.chk2 = new CheckboxField("Other", false);
        add(loc.chk2);
        
        // EditField
        loc.edit1 = new EditField("(Enter Other Here): ", "");
        add(loc.edit1);
        
        add(new SeparatorField());
        
        // EditField
        loc.edit2 = new EditField("At or Near Location: ", "");
        add(loc.edit2);
        
        add(new SeparatorField());
        
        lbl6 = new LabelField("INVOLVING THE FOLLOWING:", 0, -1, Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH)
        {
            protected void paintBackground(net.rim.device.api.ui.Graphics g) 
            { 
            g.clear(); 
            g.getColor(); 
            g.setColor(Color.LIGHTGREY); 
            g.fillRect(0, 0, Display.getWidth(), Display.getHeight()); 
            g.setColor(Color.BLUE);                
            } 
        }; 
        FontFamily fontFamily6[] = FontFamily.getFontFamilies(); 
        Font fnt6 = fontFamily6[1].getFont(FontFamily.CBTF_FONT, 14).derive(Font.BOLD | Font.ITALIC); 
        lbl6.setFont(fnt6);
        add(lbl6);        
        
        add(new SeparatorField());
        
        // EditField
        c.edit1 = new EditField("Type: ", "");
        add(c.edit1);
        
        // EditField
        v.edit0 = new EditField("Regis/VIN/ID No: ", "");
        add(v.edit0);
        
        // ObjectChoiceField
        String choicestrs14[] = {"OR", "WA", "CA"};
        v.choice0 = new ObjectChoiceField("State: ", choicestrs14, 0);
        add(v.choice0);
        
        add(new SeparatorField());
        
        // CheckboxField
        v.chk0 = new CheckboxField("Accident", false);
        add(v.chk0);
        
        // CheckboxField
        v.chk1 = new CheckboxField("Injury", false);
        add(v.chk1);
        
        // CheckboxField
        v.chk2 = new CheckboxField("Property Damage", false);
        add(v.chk2);
        
        // CheckboxField
        v.chk3 = new CheckboxField("Endanger Others", false);
        add(v.chk3);
        
        add(new SeparatorField());
        
        lbl7 = new LabelField("VEHICLE INFORMATION:", 0, -1, Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH)
        {
            protected void paintBackground(net.rim.device.api.ui.Graphics g) 
            { 
            g.clear(); 
            g.getColor(); 
            g.setColor(Color.LIGHTGREY); 
            g.fillRect(0, 0, Display.getWidth(), Display.getHeight()); 
            g.setColor(Color.BLUE);                
            } 
        }; 
        FontFamily fontFamily7[] = FontFamily.getFontFamilies(); 
        Font fnt7 = fontFamily7[1].getFont(FontFamily.CBTF_FONT, 14).derive(Font.BOLD | Font.ITALIC); 
        lbl7.setFont(fnt7);
        add(lbl7);  
        
        add(new SeparatorField());
        
        // NumericChoiceField   
        v.num0 = new NumericChoiceField("Year: ", 1990, 2011, 1);
        add(v.num0);
        
        // EditField
        v.edit1 = new EditField("Make: ", "");
        add(v.edit1);
        
        // EditField
        v.edit2 = new EditField("Model: ", "");
        add(v.edit2);
        
        // ObjectChoiceField
        String choicestrs15[] = {"Coupe", "Compact", "Mid-Sized", "Full-Sized", "SUV", "Minivan", "Passenger Van"};
        v.choice1 = new ObjectChoiceField("Style: ", choicestrs15, 0);
        add(v.choice1);
        
        // ObjectChoiceField
        String choicestrs16[] = {"Black", "White", "Grey", "Red", "Green", "Yellow", "Purple", "Orange", "Brown"};
        v.choice2 = new ObjectChoiceField("Primary Color: ", choicestrs16, 0);
        add(v.choice2);
        
        // ObjectChoiceField
        String choicestrs17[] = {"Black", "White", "Grey", "Red", "Green", "Yellow", "Purple", "Orange", "Brown"};
        v.choice3 = new ObjectChoiceField("2ndary Color: ", choicestrs17, 0);
        add(v.choice3);
        
        // EditField
        v.edit3 = new EditField("Other, Describe: ", "");
        add(v.edit3);
        
        add(new SeparatorField());
        
        // CheckboxField
        v.chk4 = new CheckboxField("Driver Not Reg. Owner", false);
        add(v.chk4);
        
        // CheckboxField
        v.chk5 = new CheckboxField("Haz. Material", false);
        add(v.chk5);
        
        // CheckboxField
        v.chk6 = new CheckboxField("Com. Vehicle", false);
        add(v.chk6);
        
        // CheckboxField
        v.chk7 = new CheckboxField("Com. Passenger", false);
        add(v.chk7);
        
        add(new SeparatorField());
        
        lbl8 = new LabelField("DID THEN AND THERE COMMIT THE FOLLOWING OFFENSE(S):", 0, -1, Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH)
        {
            protected void paintBackground(net.rim.device.api.ui.Graphics g) 
            { 
            g.clear(); 
            g.getColor(); 
            g.setColor(Color.LIGHTGREY); 
            g.fillRect(0, 0, Display.getWidth(), Display.getHeight()); 
            g.setColor(Color.BLUE);                
            } 
        }; 
        FontFamily fontFamily8[] = FontFamily.getFontFamilies(); 
        Font fnt8 = fontFamily8[1].getFont(FontFamily.CBTF_FONT, 14).derive(Font.BOLD | Font.ITALIC); 
        lbl8.setFont(fnt8);
        add(lbl8);  
        
        add(new SeparatorField());
        
        FontFamily fontFamily9[] = FontFamily.getFontFamilies(); 
        Font fnt9 = fontFamily9[1].getFont(FontFamily.CBTF_FONT, 14); 
        RichTextField mess4 = new RichTextField("1. Violated (cite ORS/ORD/rule) ");
        mess4.setFont(fnt9);
        add(mess4);
        
        // EditField
        c.edit2 = new EditField(": ", "");
        add(c.edit2);
        
        add(new SeparatorField());
        
        FontFamily fontFamily10[] = FontFamily.getFontFamilies(); 
        Font fnt10 = fontFamily10[1].getFont(FontFamily.CBTF_FONT, 14); 
        RichTextField mess5 = new RichTextField("Describe: ");
        mess5.setFont(fnt10);
        add(mess5);
        
         // EditField
        c.edit3 = new EditField("Alleged Speed: ", "");
        add(c.edit3);
        
        // EditField
        c.edit4 = new EditField("Designated Speed: ", "");
        add(c.edit4);
        
        // CheckboxField
        c.chk11 = new CheckboxField("Posted Limit", false);
        add(c.chk11);
        
        // CheckboxField
        c.chk12 = new CheckboxField("VBR", false);
        add(c.chk12);
        
        add(new SeparatorField());
       
        // CheckboxField
        c.chk13 = new CheckboxField("Radar", false);
        add(c.chk13);
        
        // CheckboxField
        c.chk14 = new CheckboxField("Pace", false);
        add(c.chk14);
        
        // CheckboxField
        c.chk15 = new CheckboxField("Laser", false);
        add(c.chk15);
        
        // CheckboxField
        c.chk16 = new CheckboxField("Other", false);
        add(c.chk16);
        
        // EditField
        c.edit5 = new EditField(": ", "");
        add(c.edit5);
        
        add(new SeparatorField());
        
        // CheckboxField
        c.chk17 = new CheckboxField("Pstd Sch Zn", false);
        add(c.chk17);
        
        // CheckboxField
        c.chk18 = new CheckboxField("Hwy Wk Zn", false);
        add(c.chk18);
        
        add(new SeparatorField());
        
        // CheckboxField
        c.chk19 = new CheckboxField("Intentional", false);
        add(c.chk19);
        
        // CheckboxField
        c.chk20 = new CheckboxField("Knowing", false);
        add(c.chk20);
        
        // CheckboxField
        c.chk21 = new CheckboxField("Reckless", false);
        add(c.chk21);
        
        // CheckboxField
        c.chk22 = new CheckboxField("Criminal Negligence", false);
        add(c.chk22);
        
        // CheckboxField
        c.chk23 = new CheckboxField("No Culpable Mental State", false);
        add(c.chk23);
        
        // EditField
        c.edit6 = new EditField("1. Base Fine: ", "");
        add(c.edit6);
        
        add(new SeparatorField());
        
        add(new SeparatorField());
        
        FontFamily fontFamily11[] = FontFamily.getFontFamilies(); 
        Font fnt11 = fontFamily11[1].getFont(FontFamily.CBTF_FONT, 14); 
        RichTextField mess6 = new RichTextField("1. Violated (cite ORS/ORD/rule) ");
        mess6.setFont(fnt11);
        add(mess6);
        
        // EditField
        c.edit7 = new EditField(": ", "");
        add(c.edit7);
        
        add(new SeparatorField());
        
        FontFamily fontFamily12[] = FontFamily.getFontFamilies(); 
        Font fnt12 = fontFamily12[1].getFont(FontFamily.CBTF_FONT, 14); 
        RichTextField mess7 = new RichTextField("Describe ");
        mess7.setFont(fnt12);
        add(mess7);
        
         // EditField
        c.edit8 = new EditField("->: ", "");
        add(c.edit8);
        
        add(new SeparatorField());
        
         // CheckboxField
        c.chk20 = new CheckboxField("Intentional", false);
        add(c.chk20);
        
        // CheckboxField
        c.chk21 = new CheckboxField("Knowing", false);
        add(c.chk21);
        
        // CheckboxField
        c.chk22 = new CheckboxField("Reckless", false);
        add(c.chk22);
        
        // CheckboxField
        c.chk23 = new CheckboxField("Criminal Negligence", false);
        add(c.chk23);
        
        // CheckboxField
        c.chk24 = new CheckboxField("No Culpable Mental State", false);
        add(c.chk24);
        
        // EditField
        c.edit7 = new EditField("1. Base Fine: ", "");
        add(c.edit7);
        
        add(new SeparatorField());
        
        add(new SeparatorField());
        
        FontFamily fontFamily13[] = FontFamily.getFontFamilies(); 
        Font fnt13 = fontFamily13[1].getFont(FontFamily.CBTF_FONT, 14); 
        RichTextField mess8 = new RichTextField("1. Violated (cite ORS/ORD/rule) ");
        mess8.setFont(fnt13);
        add(mess8);
        
        // EditField
        c.edit8 = new EditField(": ", "");
        add(c.edit8);
        
        add(new SeparatorField());
        
        FontFamily fontFamily14[] = FontFamily.getFontFamilies(); 
        Font fnt14 = fontFamily14[1].getFont(FontFamily.CBTF_FONT, 14); 
        RichTextField mess9 = new RichTextField("Describe ");
        mess9.setFont(fnt14);
        add(mess9);
        
         // EditField
        c.edit9 = new EditField("->: ", "");
        add(c.edit9);
        
        add(new SeparatorField());
        
         // CheckboxField
        c.chk25 = new CheckboxField("Intentional", false);
        add(c.chk25);
        
        // CheckboxField
        c.chk26 = new CheckboxField("Knowing", false);
        add(c.chk26);
        
        // CheckboxField
        c.chk27 = new CheckboxField("Reckless", false);
        add(c.chk27);
        
        // CheckboxField
        c.chk28 = new CheckboxField("Criminal Negligence", false);
        add(c.chk28);
        
        // CheckboxField
        c.chk29 = new CheckboxField("No Culpable Mental State", false);
        add(c.chk29);
        
        // EditField
        c.edit10 = new EditField("1. Base Fine: ", "");
        add(c.edit10);
        
        add(new SeparatorField());
        
        // EditField
        c.edit10 = new EditField("Other: ", "");
        add(c.edit10);
        
        // EditField
        c.edit11 = new EditField("Explanation: ", "");
        add(c.edit11);
        
        add(new SeparatorField());
        add(new SeparatorField());
        
        //FontFamily fontFamily15[] = FontFamily.getFontFamilies(); 
        //Font fnt15 = fontFamily15[1].getFont(FontFamily.CBTF_FONT, 14); 
        //RichTextField mess10 = new RichTextField("I certify under ORS 153.045 and 153.990 and under other applicable law and under penalties for false swearing, do swear/affirm that I have sufficient grounds to and do believe that the above-mentioned defendant/person committed the above offense(s) and I have served the defendant/person with this complaint. ");
        //mess10.setFont(fnt15);
        //add(mess10);
        
        lbl3c = new LabelField("I certify under ORS 153.045 and 153.990 and under other applicable law and under penalties for false swearing, do swear/affirm that I have sufficient grounds to and do believe that the above-mentioned defendant/person committed the above offense(s) and I have served the defendant/person with this complaint.", 0, -1, Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH)
        {
            protected void paintBackground(net.rim.device.api.ui.Graphics g) 
            { 
            g.clear(); 
            g.getColor(); 
            g.setColor(Color.LIGHTGREY); 
            g.fillRect(0, 0, Display.getWidth(), Display.getHeight()); 
            g.setColor(Color.GREEN);                
            } 
        };
        
        FontFamily fontFamily3c[] = FontFamily.getFontFamilies(); 
        Font fnt3c = fontFamily3c[1].getFont(FontFamily.CBTF_FONT, 12).derive(Font.BOLD | Font.ITALIC); 
        lbl3c.setFont(fnt3c);
        add(lbl3c);        
        
        
        
        
        // Date Issued:
        // ObjectChoiceField
        String choicestrs18[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        c.choice2 = new ObjectChoiceField("Date Issued Month: ", choicestrs18, 0);
        add(c.choice2);
        
        String choicestrs19[] = {"Mon", "Tues", "Wed", "Thur", "Fri", "Sat", "Sun"};
        c.choice3 = new ObjectChoiceField("Date Issued Day: ", choicestrs19, 0);
        add(c.choice3);
        
        // NumericChoiceField   
        c.num0 = new NumericChoiceField("Date Issued Year: ", 2009, 2020, 1);
        add(c.num0);
        
        FontFamily fontFamily16[] = FontFamily.getFontFamilies(); 
        Font fnt16 = fontFamily16[1].getFont(FontFamily.CBTF_FONT, 14); 
        RichTextField mess11 = new RichTextField("1st Officer Signature ");
        mess11.setFont(fnt16);
        add(mess11);
        RichTextField mess12 = new RichTextField(" ");
        add(mess12);
        RichTextField mess13 = new RichTextField("__________________________________ ");
        add(mess13);
        
        add(new SeparatorField());
        
        // EditField
        c.edit12 = new EditField("Officer(s) Agency ID: ", "");
        add(c.edit12);
        
        // EditField
        c.edit13 = new EditField("1st Officer ID No: ", "");
        add(c.edit13);
        
        // EditField
        c.edit14 = new EditField("2nd Officer ID No: ", "");
        add(c.edit14);
        
        add(new SeparatorField());
        
        RichTextField mess14 = new RichTextField("1st Officer of Arresting Person: ");
        add(mess14);
        
        // EditField
        c.edit15 = new EditField("First Name: ", "");
        add(c.edit15);
        
        // EditField
        c.edit16 = new EditField("Last Name: ", "");
        add(c.edit16);
        
        add(new SeparatorField());
        
        RichTextField mess15 = new RichTextField("2nd Officer of Arresting Person: ");
        add(mess15);
        
        // EditField
        c.edit17 = new EditField("First Name: ", "");
        add(c.edit17);
        
        // EditField
        c.edit18 = new EditField("Last Name: ", "");
        add(c.edit18);
        
        FontFamily fontFamily17[] = FontFamily.getFontFamilies(); 
        Font fnt17 = fontFamily17[1].getFont(FontFamily.CBTF_FONT, 14); 
        RichTextField mess16 = new RichTextField("2nd Officer Signature ");
        mess16.setFont(fnt17);
        add(mess16);
        RichTextField mess17 = new RichTextField(" ");
        add(mess17);
        RichTextField mess18 = new RichTextField("__________________________________ ");
        add(mess18);
        RichTextField mess19 = new RichTextField(" ");
        add(mess19);
        
        add(new SeparatorField());
        
        
        lbl9 = new LabelField("YOUR COURT APPEARANCE DATE, TIME AND LOCATION ARE:", 0, -1, Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH)
        {
            protected void paintBackground(net.rim.device.api.ui.Graphics g) 
            { 
            g.clear(); 
            g.getColor(); 
            g.setColor(Color.LIGHTGREY); 
            g.fillRect(0, 0, Display.getWidth(), Display.getHeight()); 
            g.setColor(Color.GREEN);                
            } 
        }; 
        FontFamily fontFamily18[] = FontFamily.getFontFamilies(); 
        Font fnt18 = fontFamily18[1].getFont(FontFamily.CBTF_FONT, 14).derive(Font.BOLD | Font.ITALIC); 
        lbl9.setFont(fnt18);
        add(lbl9);  
        
        add(new SeparatorField());
        
        // Court Appearance Date:
        // ObjectChoiceField
        String choicestrs20[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        c.choice4 = new ObjectChoiceField("Court Appearance Month: ", choicestrs20, 0);
        add(c.choice4);
        
        String choicestrs21[] = {"Mon", "Tues", "Wed", "Thur", "Fri", "Sat", "Sun"};
        c.choice5 = new ObjectChoiceField("Court Appearance Day: ", choicestrs21, 0);
        add(c.choice5);
        
        // NumericChoiceField   
        c.num1 = new NumericChoiceField("Court Appearance Year: ", 2009, 2020, 1);
        add(c.num1);
        
        
        // CheckboxField
        c.chk34 = new CheckboxField("AM", false);
        add(c.chk34);
        
        // CheckboxField
        c.chk35 = new CheckboxField("PM", false);
        add(c.chk35);
        
        add(new SeparatorField());
        
        // CheckboxField
        c.chk36 = new CheckboxField("Circuit Court", false);
        add(c.chk36);
        
        // EditField
        c.edit19 = new EditField("Other: ", "");
        add(c.edit19);
        
        add(new SeparatorField());
        
        // CheckboxField
        c.chk37 = new CheckboxField("Community Court", false);
        add(c.chk37);
        
        c.rgrp2 = new RadioButtonGroup();
        c.radio2 = new RadioButtonField("Oregon City", c.rgrp2, false);
        c.radio3 = new RadioButtonField("Clackamas", c.rgrp2, false);
        add(c.radio2);
        add(c.radio3);
        
        add(new SeparatorField());
        
        // CheckboxField
        c.chk38 = new CheckboxField("Municipal Courts", false);
        add(c.chk38);
        
        c.rgrp3 = new RadioButtonGroup();
        c.radio4 = new RadioButtonField("Damascus", c.rgrp3, false);
        c.radio5 = new RadioButtonField("Estacada", c.rgrp3, false);
        add(c.radio4);
        add(c.radio5);
        
        add(new SeparatorField());
        
        // CheckboxField
        c.chk39 = new CheckboxField("Municipal Courts", false);
        add(c.chk39);
        
        c.rgrp4 = new RadioButtonGroup();
        c.radio6 = new RadioButtonField("Happy Valley", c.rgrp4, false);
        c.radio7 = new RadioButtonField("Wilsonville", c.rgrp4, false);
        add(c.radio6);
        add(c.radio7);
        
        add(new SeparatorField());
        
          
        // CheckboxField
        c.chk40 = new CheckboxField("See Reverse", false);
        add(c.chk40);
        
        
        
        
        // ButtonField
        btn = new ButtonField("Submit", ButtonField.CONSUME_CLICK);
        
        btn.setChangeListener(new ButtonListener1(this)); 
        
        add(btn);
        
        add(new SeparatorField());
    }
    
    final class ButtonListener1 implements FieldChangeListener {
        CitationScreen1 c1;
                    
        public ButtonListener1(CitationScreen1 n){
            c1 = n;
        }
                    
        public void fieldChanged(Field field, int context) {
            UiApplication.getUiApplication().pushScreen(new CitationScreen2(c1));
        }
    }
         
} 
