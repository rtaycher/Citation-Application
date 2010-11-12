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



class CitationScreen4 extends MainScreen {

    //--------------------
	LabelField field_citation_no;
	
    DateField field_dte;
    DateField field_tme;
    
    EditField field_offenses;
    CheckboxField field_accident;
    CheckboxField field_radar;
    CheckboxField field_school_zone;
    CheckboxField field_paced;
    RadioButtonGroup rgrp1;
    RadioButtonField rgrp1_yes;
    RadioButtonField rgrp1_no;

    EditField field_officer_first;
    EditField field_officer_middle;
    EditField field_officer_last;
    EditField field_officer_badge;
    EditField field_officer_id;
    
    CheckboxField field_circuit_crt;
    EditField field_other_crt;
    CheckboxField field_community_crt;
    RadioButtonGroup rgrp2;
    RadioButtonField rgrp2_oregon_city;
    RadioButtonField rgrp2_clackamas;
    CheckboxField field_municipal_crt1;
    RadioButtonGroup rgrp3;
    RadioButtonField rgrp3_damascus;
    RadioButtonField rgrp3_estacada;
    CheckboxField field_municipal_crt2;
    RadioButtonGroup rgrp4;
    RadioButtonField rgrp4_happy_valley;
    RadioButtonField rgrp4_wilsonville; 
    
    DateField field_court_dte;
    DateField field_court_tme;
    
    ObjectChoiceField field_bac;
    DateField field_bac_dte;
    DateField field_bac_tme;
    
    EditField field_violation_type;
    ObjectChoiceField field_speed_limit;
    EditField field_alleged_speed;
    EditField field_narrative;
     
    Citation c;
    
    CitationScreen4(Citation _c) {  
        c = _c;
        
        CustomLabelField lf1 = new CustomLabelField("DEFENDENT INFRACTIONS", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        lf1.setBackgroundColor(Color.CYAN);
        lf1.setTextColor(Color.BLUE);

        FontFamily fontFamily[] = FontFamily.getFontFamilies(); 
        Font font1 = fontFamily[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf1.setFont(font1); 
        setTitle(lf1);
                   
        add(new SeparatorField());

        
        CustomLabelField lbl2 = new CustomLabelField("******* Defendent Infraction *******", Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH);
        lbl2.setBackgroundColor(Color.LIGHTGREY);
        lbl2.setTextColor(Color.BLUE);
    
        FontFamily fontFamily2[] = FontFamily.getFontFamilies(); 
        Font fnt2 = fontFamily2[1].getFont(FontFamily.CBTF_FONT, 14).derive(Font.BOLD | Font.ITALIC); 
        lbl2.setFont(fnt2);
        add(lbl2);
    
        add(new SeparatorField());    

        String name = c.Person.getElement(CPerson.FIRST) + " " + c.Person.getElement(CPerson.MIDDLE) + " " + c.Person.getElement(CPerson.LAST);
        String dob = c.Person.getElement(CPerson.DOB).toString();
        add(new RichTextField("Name: " + name));
        add(new RichTextField("Date of Birth: " + dob));
        
        add(new SeparatorField());   
        
        // EditField
        field_citation_no = new LabelField("Citation #: " + String.valueOf(c.Number));
        add(field_citation_no); 
        
        add(new SeparatorField());   
        
        field_dte = new DateField("Date: ", Long.MIN_VALUE, DateField.DATE);
        add(field_dte);
        field_tme = new DateField("Time: ", Long.MIN_VALUE, DateField.TIME);
        add(field_tme);
        
        // EditField
        field_offenses = new EditField("Offenses: ", "", 100, 0);
        add(field_offenses);   

        add(new SeparatorField());
    
        // CheckboxField
        field_accident = new CheckboxField("Accident", false);
        add(field_accident);
    
        // CheckboxField
        field_radar = new CheckboxField("Radar", false);
        add(field_radar);
    
        // CheckboxField
        field_school_zone = new CheckboxField("School Zone", false);
        add(field_school_zone);
    
        // CheckboxField
        field_paced = new CheckboxField("Paced", false);
        add(field_paced);
        
        add(new SeparatorField());
   
        add(new RichTextField("Jail Booking Involved: "));
        rgrp1 = new RadioButtonGroup();
        rgrp1_yes = new RadioButtonField("Yes", rgrp1, true);
        rgrp1_no = new RadioButtonField("No", rgrp1, false);    
        add(rgrp1_yes);
        add(rgrp1_no);
        
        add(new SeparatorField());
         
        CustomLabelField lbl3 = new CustomLabelField("*********** Officer Information ***********", Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH);
        lbl3.setBackgroundColor(Color.LIGHTGREY);
        lbl3.setTextColor(Color.BLUE);
    
        FontFamily fontFamily3[] = FontFamily.getFontFamilies(); 
        Font fnt3 = fontFamily3[1].getFont(FontFamily.CBTF_FONT, 14).derive(Font.BOLD | Font.ITALIC); 
        lbl3.setFont(fnt3);
        add(lbl3);
    
        add(new SeparatorField());             
         
        // EditField
        field_officer_first = new EditField("First: ", "");
        add(field_officer_first);  
        
        // EditField
        field_officer_middle = new EditField("Middle: ", "");
        add(field_officer_middle);                       
               
        // EditField
        field_officer_last = new EditField("Last: ", "");
        add(field_officer_last);  
        
        add(new SeparatorField());     

        // EditField
        field_officer_badge = new EditField("Badge: ", "");
        add(field_officer_badge);                       
               
        // EditField
        field_officer_id = new EditField("ID #: ", "");
        add(field_officer_id);                     
             
        add(new SeparatorField());

        CustomLabelField lbl4 = new CustomLabelField("************ Court Information ************", Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH);
        lbl4.setBackgroundColor(Color.LIGHTGREY);
        lbl4.setTextColor(Color.BLUE);
    
        FontFamily fontFamily4[] = FontFamily.getFontFamilies(); 
        Font fnt4 = fontFamily4[1].getFont(FontFamily.CBTF_FONT, 14).derive(Font.BOLD | Font.ITALIC); 
        lbl4.setFont(fnt4);
        add(lbl4);
    
        // CheckboxField
        field_circuit_crt = new CheckboxField("Circuit Court", false);
        add(field_circuit_crt);
    
        // EditField
        field_other_crt = new EditField("Other: ", "");
        add(field_other_crt);
    
        add(new SeparatorField());

        // CheckboxField
        field_community_crt = new CheckboxField("Community Court", false);
        add(field_community_crt);
    
        rgrp2 = new RadioButtonGroup();
        rgrp2_oregon_city = new RadioButtonField("Oregon City", rgrp2, false);
        rgrp2_clackamas = new RadioButtonField("Clackamas", rgrp2, false);
        add(rgrp2_oregon_city);
        add(rgrp2_clackamas);
    
        add(new SeparatorField());
    
        // CheckboxField
        field_municipal_crt1 = new CheckboxField("Municipal Courts", false);
        add(field_municipal_crt1);
    
        rgrp3 = new RadioButtonGroup();
        rgrp3_damascus = new RadioButtonField("Damascus", rgrp3, false);
        rgrp3_estacada = new RadioButtonField("Estacada", rgrp3, false);
        add(rgrp3_damascus);
        add(rgrp3_estacada);
    
        add(new SeparatorField());
    
        // CheckboxField
        field_municipal_crt2 = new CheckboxField("Municipal Courts", false);
        add(field_municipal_crt2);
    
        rgrp4 = new RadioButtonGroup();
        rgrp4_happy_valley = new RadioButtonField("Happy Valley", rgrp4, false);
        rgrp4_wilsonville = new RadioButtonField("Wilsonville", rgrp4, false);
        add(rgrp4_happy_valley);
        add(rgrp4_wilsonville);
    
        add(new SeparatorField());            
              
        field_court_dte = new DateField("Date: ", Long.MIN_VALUE, DateField.DATE);
        add(field_court_dte);
        field_court_tme = new DateField("Time: ", Long.MIN_VALUE, DateField.TIME);
        add(field_court_tme);  
         
        add(new SeparatorField()); 
        
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
        
        // EditField
        field_violation_type = new EditField("Violation Type: ", "");
        add(field_violation_type);                       
               
        // ObjectChoiceField
        String choicestrs1[] = {"5MPH", "10MPH", "15MPH", "20MPH", "25MPH", "30MPH", "35MPH", "40MPH", "45MPH", "50MPH", "55MPH", "60MPH", "65MPH", "70MPH", "75MPH", "80MPH"};
        field_speed_limit = new ObjectChoiceField("Speed Limit: ", choicestrs1, 0);
        add(field_speed_limit);                  
               
        // EditField
        field_alleged_speed= new EditField("Alleged Speed: ", "");
        add(field_alleged_speed);
        
        add(new SeparatorField()); 
        
        // EditField
        field_narrative= new EditField("Narrative: ", "", 100, 0);
        add(field_narrative);                   
 
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
            	//cs4.storeUIFieldsToCitation();
                //UiApplication.getUiApplication().pushScreen(new CitationScreen3(c));
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
        c.Violation.setElement(CViolation.DATE, field_dte.toString());
        c.Violation.setElement(CViolation.TIME, field_tme.toString());
        c.Violation.setElement(CViolation.OFFENSES, field_offenses.getText());
        c.Violation.setElement(CViolation.ACCIDENT, field_accident.getLabel());
        c.Violation.setElement(CViolation.RADAR, field_radar.getLabel());
        c.Violation.setElement(CViolation.SCHOOL_ZONE, field_school_zone.getLabel());
        c.Violation.setElement(CViolation.PACED, field_paced.getLabel());
          
        c.Violation.setElement(CViolation.RGRP1, field_paced.getLabel());
        c.Violation.setElement(CViolation.YES, rgrp1_yes.getLabel());
        c.Violation.setElement(CViolation.NO, rgrp1_no.getLabel());
          
        c.Violation.setElement(CViolation.OFFICER_FIRST, field_officer_first.getText());
        c.Violation.setElement(CViolation.OFFICER_MIDDLE, field_officer_middle.getText());
        c.Violation.setElement(CViolation.OFFICER_LAST, field_officer_last.getText());
        c.Violation.setElement(CViolation.OFFICER_BADGE, field_officer_badge.getText());
        c.Violation.setElement(CViolation.OFFICER_ID, field_officer_id.getText());
          
        c.Violation.setElement(CViolation.CIRCUIT_CRT, field_circuit_crt.getLabel());
        c.Violation.setElement(CViolation.OTHER_CRT, field_other_crt.getText());
        c.Violation.setElement(CViolation.COMMUNITY_CRT, field_community_crt.getLabel());
          
        c.Violation.setElement(CViolation.RGRP2, Integer.toString(rgrp2.getSelectedIndex()));
        c.Violation.setElement(CViolation.OREGON_CITY, rgrp2_oregon_city.getLabel());
        c.Violation.setElement(CViolation.CLACKAMAS, rgrp2_clackamas.getLabel());
          
        c.Violation.setElement(CViolation.MUNICIPAL_CRT, field_municipal_crt1.getLabel());
        
        c.Violation.setElement(CViolation.RGRP3, Integer.toString(rgrp3.getSelectedIndex()));
        c.Violation.setElement(CViolation.DAMASCUS, rgrp3_damascus.getLabel());
        c.Violation.setElement(CViolation.ESTACADA, rgrp3_estacada.getLabel());
          
        c.Violation.setElement(CViolation.MUNICIPAL_CRT2, field_municipal_crt2.getLabel());
          
        c.Violation.setElement(CViolation.RGRP4, Integer.toString(rgrp4.getSelectedIndex()));
        c.Violation.setElement(CViolation.HAPPY_VALLEY, rgrp4_happy_valley.getLabel());
        c.Violation.setElement(CViolation.WILSONVILLE, rgrp4_wilsonville.getLabel());
         
        c.Violation.setElement(CViolation.COURT_DATE, field_court_dte.toString());
        c.Violation.setElement(CViolation.COURT_TIME, field_court_tme.toString());
        c.Violation.setElement(CViolation.BAC_DATE, field_bac_dte.toString());
        c.Violation.setElement(CViolation.BAC_TIME, field_bac_tme.toString());
          
        c.Violation.setElement(CViolation.VIOLATION_TYPE, field_violation_type.getText());
        c.Violation.setElement(CViolation.SPEED_LIMIT, field_speed_limit.toString());
        c.Violation.setElement(CViolation.ALLEGED_SPEED, field_alleged_speed.getText());
        c.Violation.setElement(CViolation.NARRATIVE, field_narrative.getText());
    }
    
    /**
     * restoreUIFieldsFromCitation - populate the UI fields from those currently
     *                               stored in the citation record
     */
    private void restoreUIFieldsFromCitation()
    {
    	field_dte.setLabel(c.Violation.getElement(CViolation.DATE));
    	field_tme.setLabel(c.Violation.getElement(CViolation.TIME));
    	field_offenses.setText(c.Violation.getElement(CViolation.OFFENSES));
    	field_accident.setLabel(c.Violation.getElement(CViolation.ACCIDENT));
    	field_radar.setLabel(c.Violation.getElement(CViolation.RADAR));
    	field_school_zone.setLabel(c.Violation.getElement(CViolation.SCHOOL_ZONE));
    	field_paced.setLabel(c.Violation.getElement(CViolation.PACED));
    	//c4.p[i].rgrp1.setSelectedIndex(Integer.parseInt(info4.getElement(StoreInfo4.RGRP1_4)));
    	rgrp1_yes.setLabel(c.Violation.getElement(CViolation.YES));
    	rgrp1_no.setLabel(c.Violation.getElement(CViolation.NO));
    	field_officer_first.setText(c.Violation.getElement(CViolation.OFFICER_FIRST));
    	field_officer_middle.setText(c.Violation.getElement(CViolation.OFFICER_MIDDLE));
    	field_officer_last.setText(c.Violation.getElement(CViolation.OFFICER_LAST));
    	field_officer_badge.setText(c.Violation.getElement(CViolation.OFFICER_BADGE));
    	field_officer_id.setText(c.Violation.getElement(CViolation.OFFICER_ID));
    	field_circuit_crt.setLabel(c.Violation.getElement(CViolation.CIRCUIT_CRT));
    	field_other_crt.setText(c.Violation.getElement(CViolation.OTHER_CRT));
    	field_community_crt.setLabel(c.Violation.getElement(CViolation.COMMUNITY_CRT));
    	//c4.p[i].rrgrp2.setSelectedIndex(Integer.parseInt(info4.getElement(StoreInfo4.Rrgrp2_4)));
    	rgrp2_oregon_city.setLabel(c.Violation.getElement(CViolation.OREGON_CITY));
    	rgrp2_clackamas.setLabel(c.Violation.getElement(CViolation.CLACKAMAS));
    	field_municipal_crt1.setLabel(c.Violation.getElement(CViolation.MUNICIPAL_CRT));
      //c4.p[i].rgrp3.setSelectedIndex(Integer.parseInt(info4.getElement(StoreInfo4.RGRP3_4)));
    	rgrp3_damascus.setLabel(c.Violation.getElement(CViolation.DAMASCUS));
    	rgrp3_estacada.setLabel(c.Violation.getElement(CViolation.ESTACADA));

    	field_municipal_crt2.setLabel(c.Violation.getElement(CViolation.MUNICIPAL_CRT2));
      //c4.p[i].rgrp4.setSelectedIndex(Integer.parseInt(info4.getElement(StoreInfo4.RGRP4_4)));
    	rgrp4_happy_valley.setLabel(c.Violation.getElement(CViolation.HAPPY_VALLEY));
    	rgrp4_wilsonville.setLabel(c.Violation.getElement(CViolation.WILSONVILLE));
    	field_court_dte.setLabel(c.Violation.getElement(CViolation.COURT_DATE));
    	field_court_tme.setLabel(c.Violation.getElement(CViolation.COURT_TIME));
    	field_bac.setLabel(c.Violation.getElement(CViolation.BAC));
    	field_bac_dte.setLabel(c.Violation.getElement(CViolation.BAC_DATE));
    	field_bac_tme.setLabel(c.Violation.getElement(CViolation.BAC_TIME));
    	field_violation_type.setText(c.Violation.getElement(CViolation.VIOLATION_TYPE));
    	field_speed_limit.setLabel(c.Violation.getElement(CViolation.SPEED_LIMIT));
    	field_alleged_speed.setText(c.Violation.getElement(CViolation.ALLEGED_SPEED));
    	field_narrative.setText(c.Violation.getElement(CViolation.NARRATIVE));                   
    }
    
} 
