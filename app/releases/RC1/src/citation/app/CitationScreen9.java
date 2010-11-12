/*
 * CitationScreen9.java
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

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.component.Status;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import citation.data.CLocation;
import citation.data.CPerson;
import citation.data.CVehicle;
import citation.data.CViolation;
import citation.data.Citation;
import citation.output.CitationOutput;
import citation.ui.CustomLabelField;

/**
 * 
 */
class CitationScreen9 extends MainScreen {
        
    private ButtonField btn_Prev;
    private HorizontalFieldManager navButtonManager;
    private Citation c;

    CitationScreen9(Citation _c) {
        c = _c;

        String cNumberString = Integer.toString(c.Number);
        
        LabelField lf0 = new LabelField("OREGON UNIFORM CITATION: #" + cNumberString, LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH | DrawStyle.HCENTER) {
            protected void paintBackground(net.rim.device.api.ui.Graphics g) {
                g.clear();
                g.getColor();
                g.setColor(Color.GREEN);
                g.fillRect(0, 0, Display.getWidth(), Display.getHeight());
                g.setColor(Color.WHITE);
            }
        };
        
        if (c.isSubmitted()) {
            ButtonField btn_submit;
            btn_submit = new ButtonField("Re-Print", ButtonField.CONSUME_CLICK);
            btn_submit.setChangeListener(new ButtonListener9(this, c));
            add(btn_submit);
        }
        
        FontFamily fontFamily0[] = FontFamily.getFontFamilies();
        Font font0 = fontFamily0[1].getFont(FontFamily.CBTF_FONT, 14);
        lf0.setFont(font0);
        setTitle(lf0);
        add(new SeparatorField());
        
        LabelField lf1 = new LabelField("******** VERIFY LOCATION INFORMATION ********", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH | DrawStyle.HCENTER) {
            protected void paintBackground(net.rim.device.api.ui.Graphics g) {
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

        add(new RichTextField("Address: " + c.Loc.getElement(CLocation.ADDRESS)));
        add(new RichTextField("City: " + c.Loc.getElement(CLocation.CITY)));
        add(new RichTextField("State: " + c.Loc.getElement(CLocation.STATE)));
        add(new RichTextField("Zip: " + c.Loc.getElement(CLocation.ZIP)));

        if (!c.Loc.getElement(CLocation.HIGHWAY).equals("false") || !c.Loc.getElement(CLocation.COMMONPLACE).equals("false"))
            add(new SeparatorField());
        if (!c.Loc.getElement(CLocation.HIGHWAY).equals("false")) {
            add(new RichTextField("--> Highway "));
        }
        if (!c.Loc.getElement(CLocation.COMMONPLACE).equals("false")) {
            add(new RichTextField("--> Commonplace"));
        }

        add(new SeparatorField());

        LabelField lf2 = new LabelField("******** VERIFY PERSON INFORMATION ********", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH
                | DrawStyle.HCENTER) {
            protected void paintBackground(net.rim.device.api.ui.Graphics g) {
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

        // int i=0;

        boolean person_sel = c.Person.getElement(CPerson.PERSON_SELECT).equals("true");
        boolean business_sel = c.Person.getElement(CPerson.BUSINESS_SELECT).equals("true");

        if (person_sel == true) {
            add(new RichTextField("Type: Person"));
        } else {
            add(new RichTextField("Type: Business"));
        }
        
        add(new RichTextField("ID: " + c.Person.getElement(CPerson.ID)));
        add(new RichTextField("ID State: " + c.Person.getElement(CPerson.ID_STATE)));
        add(new SeparatorField());
        add(new RichTextField("Title: " + c.Person.getElement(CPerson.TITLE)));
        add(new RichTextField("First: " + c.Person.getElement(CPerson.FIRST)));
        add(new RichTextField("Middle: " + c.Person.getElement(CPerson.MIDDLE)));
        add(new RichTextField("Last: " + c.Person.getElement(CPerson.LAST)));
        add(new RichTextField("Suffix: " + c.Person.getElement(CPerson.SUFFIX)));
        add(new SeparatorField());
        add(new RichTextField("Address: " + c.Person.getElement(CPerson.ADDRESS)));
        add(new RichTextField("City: " + c.Person.getElement(CPerson.CITY)));
        add(new RichTextField("State: " + c.Person.getElement(CPerson.STATE)));
        add(new RichTextField("Zip: " + c.Person.getElement(CPerson.ZIP)));
        add(new SeparatorField());
        add(new RichTextField("DOB: " + c.Person.getElement(CPerson.DOB)));
        add(new RichTextField("Sex: " + c.Person.getElement(CPerson.SEX)));
        add(new RichTextField("Race: " + c.Person.getElement(CPerson.RACE)));
        add(new RichTextField("Eyes: " + c.Person.getElement(CPerson.EYES)));
        add(new RichTextField("Hair: " + c.Person.getElement(CPerson.HAIR)));
        add(new SeparatorField());
        add(new RichTextField("Endorsements: " + c.Person.getElement(CPerson.ENDORSEMENTS)));
        add(new RichTextField("Restrictions: " + c.Person.getElement(CPerson.RESTRICTIONS)));
        add(new SeparatorField());
        add(new RichTextField("Height: " + c.Person.getElement(CPerson.HEIGHT)));
        add(new RichTextField("Weight: " + c.Person.getElement(CPerson.WEIGHT)));
        add(new SeparatorField());
        add(new RichTextField("Observations: " + c.Person.getElement(CPerson.OBSERVATIONS)));

        add(new SeparatorField());

        LabelField lf3 = new LabelField("******** VERIFY VEHICLE INFORMATION ********", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH
                | DrawStyle.HCENTER) {
            protected void paintBackground(net.rim.device.api.ui.Graphics g) {
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

        // i=0;
        add(new RichTextField("Type: " + c.Vehicle.getElement(CVehicle.TYPE)));
        add(new RichTextField("Plate: " + c.Vehicle.getElement(CVehicle.PLATE)));
        add(new RichTextField("Registered State: " + c.Vehicle.getElement(CVehicle.REG_STATE)));
        add(new SeparatorField());
        add(new RichTextField("Make: " + c.Vehicle.getElement(CVehicle.MAKE)));
        add(new RichTextField("Model: " + c.Vehicle.getElement(CVehicle.MODEL)));
        add(new RichTextField("Style: " + c.Vehicle.getElement(CVehicle.STYLE)));
        add(new RichTextField("Year: " + c.Vehicle.getElement(CVehicle.YEAR)));
        add(new RichTextField("Primary Color: " + c.Vehicle.getElement(CVehicle.PRIM_COLOR)));
        add(new RichTextField("Secondary Color: " + c.Vehicle.getElement(CVehicle.SEC_COLOR)));
        add(new RichTextField("Vehicle ID: " + c.Vehicle.getElement(CVehicle.VIN)));

        add(new SeparatorField());

        LabelField lf3a = new LabelField("******** VERIFY COURT INFORMATION ********", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH
                | DrawStyle.HCENTER) {
            protected void paintBackground(net.rim.device.api.ui.Graphics g) {
                g.clear();
                g.getColor();
                g.setColor(Color.GREEN);
                g.fillRect(0, 0, Display.getWidth(), Display.getHeight());
                g.setColor(Color.WHITE);
            }
        };
        FontFamily fontFamily3a[] = FontFamily.getFontFamilies();
        Font font3a = fontFamily3a[1].getFont(FontFamily.CBTF_FONT, 12);
        lf3a.setFont(font3a);
        add(lf3a);

        add(new SeparatorField());
        add(new RichTextField("Court Name: " + c.Violation.getElement(CViolation.COURT_PHONE)));
        add(new RichTextField("Address: " + c.Violation.getElement(CViolation.COURT_ADDRESS)));
        add(new RichTextField("City: " + c.Violation.getElement(CViolation.COURT_CITY)));
        add(new RichTextField("State: " + c.Violation.getElement(CViolation.COURT_STATE)));
        add(new RichTextField("Zip: " + c.Violation.getElement(CViolation.COURT_ZIP)));
        add(new RichTextField("Phone #: " + c.Violation.getElement(CViolation.COURT_PHONE)));
        add(new RichTextField("Date: " + c.Violation.getElement(CViolation.COURT_DATE)));
        add(new RichTextField("Time: " + c.Violation.getElement(CViolation.COURT_TIME)));

        LabelField lf4 = new LabelField("******** VERIFY VIOLATION INFORMATION ********", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH
                | DrawStyle.HCENTER) {
            protected void paintBackground(net.rim.device.api.ui.Graphics g) {
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
        // i=0;

        add(new RichTextField("Citation #: " + cNumberString));
        add(new RichTextField("Date: " + c.Violation.getElement(CViolation.DATE)));
        add(new RichTextField("Time: " + c.Violation.getElement(CViolation.TIME)));
        // add(new RichTextField("Offenses: " + c.Violation.getElement(3)));
        add(new SeparatorField());
        add(new RichTextField("Statute: " + c.Violation.getElement(CViolation.STATUTE)));
        add(new RichTextField("Fine: " + c.Violation.getElement(CViolation.FINE)));

        add(new SeparatorField());
        add(new LabelField("Incident Attributes", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH
                | DrawStyle.LEFT));

        if (!c.Violation.getElement(CViolation.ACCIDENT).equals("false")) {
            add(new RichTextField("--> Accident"));
        }
        if (!c.Violation.getElement(CViolation.RADAR).equals("false")) {
            add(new RichTextField("--> Radar Used"));
        }
        if (!c.Violation.getElement(CViolation.JAIL_BOOKING).equals("false")) {
            add(new RichTextField("--> Jail Booking"));
        }
        if (!c.Violation.getElement(CViolation.PACED).equals("false")) {
            add(new RichTextField("--> Paced"));
        }
        if (!c.Violation.getElement(CViolation.SCHOOL_ZONE).equals("false")) {
            add(new RichTextField("--> School Zone"));
        }
        if (!c.Violation.getElement(CViolation.ALCOHOL).equals("false")) {
            add(new RichTextField("--> Alcohol Involvement"));
            add(new SeparatorField());
            LabelField bacLbl = new LabelField("BAC Attributes", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH
                    | DrawStyle.LEFT) {
                protected void paintBackground(net.rim.device.api.ui.Graphics g) {
                    g.clear();
                    g.getColor();
                    g.setColor(Color.LIGHTGREY);
                    g.fillRect(0, 0, Display.getWidth(), Display.getHeight());
                    g.setColor(Color.RED);
                }
            };
            add(bacLbl);
            add(new RichTextField("--> BAC: " + c.Violation.getElement(CViolation.BAC)));
            add(new RichTextField("--> BAC Date: " + c.Violation.getElement(CViolation.BAC_DATE)));
            add(new RichTextField("--> BAC Time: " + c.Violation.getElement(CViolation.BAC_TIME)));
        }
        add(new SeparatorField());
        add(new RichTextField("VFBI: " + c.Violation.getElement(CViolation.VBFI)));
        add(new RichTextField("Speed Limit: " + c.Violation.getElement(CViolation.SPEED_LIMIT)));
        add(new RichTextField("Alleged Speed: " + c.Violation.getElement(CViolation.ALLEGED_SPEED)));

        add(new SeparatorField());
        add(new LabelField("Involves: ", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH
                | DrawStyle.LEFT));

        if (!c.Violation.getElement(CViolation.INTENTIONAL).equals("false")) {
            add(new RichTextField("--> Intentional"));
        }
        if (!c.Violation.getElement(CViolation.KNOWING).equals("false")) {
            add(new RichTextField("--> Knowing"));
        }
        if (!c.Violation.getElement(CViolation.RECKLESS).equals("false")) {
            add(new RichTextField("--> Reckless"));
        }
        if (!c.Violation.getElement(CViolation.CRIMINAL_NEG).equals("false")) {
            add(new RichTextField("--> Criminal Negligence"));
        }
        if (!c.Violation.getElement(CViolation.CULPABLE).equals("false")) {
            add(new RichTextField("--> Culpable Mental State"));
        }

        // }
        add(new SeparatorField());
        add(new SeparatorField());

        CustomLabelField endlf = new CustomLabelField("", LabelField.USE_ALL_WIDTH);
        endlf.setBackgroundColor(0x00194E84);
        add(endlf);
        
        navButtonManager = new HorizontalFieldManager();

        // ButtonField
        btn_Prev = new ButtonField("Prev", ButtonField.CONSUME_CLICK);
        btn_Prev.setChangeListener(new ButtonListener9(this, c));
        navButtonManager.add(btn_Prev);
        
        if (c.isSubmitted()) {
            ButtonField btn_submit;
            btn_submit = new ButtonField("Print", ButtonField.CONSUME_CLICK);
            btn_submit.setChangeListener(new ButtonListener9(this, c));
            navButtonManager.add(btn_submit);
        } else {
            ButtonField btn_submit;
            btn_submit = new ButtonField("Print & Submit", ButtonField.CONSUME_CLICK);
            btn_submit.setChangeListener(new ButtonListener9(this, c));
            navButtonManager.add(btn_submit);
        }

        add(navButtonManager);
        add(new SeparatorField());

    }
    
    /**
     * removePreviousBtn - called when citation is set to read only - prevents user from returning to previous
     *                     screen and creating further edits
     */
    private void removePrevBtnField() 
    {
        if (btn_Prev != null)
        {
                
                navButtonManager.delete(btn_Prev);
                btn_Prev = null;
        }
    }

    /**
     * Inner Class: ButtonListener9
     * Description: This class implements a button listener to respond to user pressed
     * buttons on the first UI screen. 
     */
    public class ButtonListener9 implements FieldChangeListener {
        Citation c;
        CitationScreen9 cs9;

        public ButtonListener9(CitationScreen9 _cs9, Citation _c) {
            c = _c;
            cs9 = _cs9;
        }

        public void fieldChanged(Field field, int context) {
            ButtonField btn = (ButtonField) field;
            if (btn.getLabel().toString() == "Prev") {
                UiApplication.getUiApplication().popScreen(cs9);
            } else if (btn.getLabel().toString() == "Print"
                    || btn.getLabel().toString() == "Re-Print"
                    || btn.getLabel().toString() == "Print & Submit") {
                // No further modifications to the citation are now allowed
                c.setReadOnly(true);
                
                CitationOutput c_out = new CitationOutput();
                if (btn.getLabel().toString() == "Print & Submit"){
                    Status.show("Sending Citation Record...");
                    c_out.submitCitation(c);
                }
                
                // Dump all screens on the stack and re-load screen 1 and this preview screen (8).
                //while (UiApplication.getUiApplication().getScreenCount() > 0){
                //    UiApplication.getUiApplication().popScreen();
                //}
                UiApplication.getUiApplication().pushScreen(new CitationScreen1(c));
                UiApplication.getUiApplication().pushScreen(new CitationScreen9(c));
            }
        }
    }
}
