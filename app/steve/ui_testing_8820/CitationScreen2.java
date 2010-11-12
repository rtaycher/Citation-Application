/*
 * TestScreen2.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package citation.com;

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;

public class CitationScreen2 extends MainScreen {
    ButtonField btn2;
    ButtonField btn3;
    
    LabelField lbl;
    LabelField lbl2;
    LabelField lbl3;
    
    public CitationScreen2(CitationScreen1 s1) {  
        LabelField lf2 = new LabelField("SELECTED ENTRYS OF CITATION FORM", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH)
        {
            protected void paintBackground(net.rim.device.api.ui.Graphics g) 
            { 
                g.clear(); 
                g.getColor(); 
                g.setColor(Color.CYAN); 
                g.fillRect(0, 0, Display.getWidth(), Display.getHeight()); 
                g.setColor(Color.BLUE);                
            } 
        };
        FontFamily fontFamily[] = FontFamily.getFontFamilies(); 
        Font font = fontFamily[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf2.setFont(font); 
        setTitle(lf2);
                   
        if(s1.c.radio0.isSelected() == true){
            add(new RichTextField("Crime or Violation: " + s1.c.radio0.getLabel()));   
        }
        else if(s1.c.radio1.isSelected() == true){
            add(new RichTextField("Crime or Violation: " + s1.c.radio1.getLabel()));    
        }
                   
        add(new SeparatorField());
                  
        if(s1.c.chk0.getChecked() == true){  
            add(new RichTextField("Type: " + s1.c.chk0.getLabel()));  
         }
         else if(s1.c.chk1.getChecked() == true){
                      add(new RichTextField("Type: " +s1.c.chk1.getLabel()));   
         } 
         else if(s1.c.chk2.getChecked() == true){
                      add(new RichTextField("Type: " +s1.c.chk2.getLabel()));   
         } 
         else if(s1.c.chk3.getChecked() == true){
                      add(new RichTextField("Type: " +s1.c.chk3.getLabel()));   
         } 
         else if(s1.c.chk4.getChecked() == true){
                      add(new RichTextField("Type: " +s1.c.chk4.getLabel()));   
         }      
                   
         add(new SeparatorField());
                   
         add(new RichTextField("City/Other Public Body: " +s1.c.choice0.toString()));  
                   
         add(new SeparatorField());
                   
         add(new RichTextField("County of: " +s1.c.choice1.toString()));  
                   
         add(new SeparatorField());
                   
         add(new RichTextField("Docket No.: " +s1.c.edit.getText()));  
                   
         add(new SeparatorField());
                   
         if(s1.c.chk5.getChecked() == true){  
                     add(new RichTextField("Court: " + s1.c.chk5.getLabel()));  
         }
         else if(s1.c.chk6.getChecked() == true){
                      add(new RichTextField("Court: " +s1.c.chk6.getLabel()));   
         } 
         else if(s1.c.chk7.getChecked() == true){
                      add(new RichTextField("Court: " +s1.c.chk7.getLabel()));   
         } 
         else if(s1.c.chk8.getChecked() == true){
                      add(new RichTextField("Court: " +s1.c.chk8.getLabel()));   
         } 
         else if(s1.c.chk9.getChecked() == true){
                      add(new RichTextField("Court: " +s1.c.chk9.getLabel()));   
         }
         else if(s1.c.chk10.getChecked() == true){
                      add(new RichTextField("Court: " +s1.c.chk10.getLabel()));   
         }
         
         add(new SeparatorField());
         
         lbl2 = new LabelField("THE FOLLOWING PERSON INFO HAS BEEN ENTERED:", 0, -1, Field.FIELD_HCENTER);
        
         FontFamily fontFamily2[] = FontFamily.getFontFamilies(); 
         Font fnt2 = fontFamily2[1].getFont(FontFamily.CBTF_FONT, 14).derive(Font.BOLD | Font.ITALIC); 
         //FontFamily fontFamily3[1] = FontFamily.getFontFamilies(); 
         //Font fnt3 = this.getFont().derive(Font.BOLD | Font.ITALIC);
         lbl2.setFont(fnt2);
         add(lbl2);
         
         add(new SeparatorField());
         
         if(s1.p.chk0.getChecked() == true){  
                     add(new RichTextField("Non-CDL/CDL: " + s1.p.chk0.getLabel()));  
         }
         else if(s1.p.chk1.getChecked() == true){
                      add(new RichTextField("Non-CDL/CDL: " + s1.p.chk1.getLabel()));   
         } 
         
         add(new RichTextField("ID Type: " + s1.p.edit0.getText()));  
         
         add(new SeparatorField());
          
         add(new RichTextField("ID No: " + s1.p.edit1.getText()));  
         
         add(new SeparatorField()); 
          
         add(new RichTextField("State: " +s1.p.choice0.toString()));   
          
         add(new SeparatorField());  
          
         add(new RichTextField("Tel No: " +s1.p.edit2.getText()));  
          
         add(new SeparatorField());   
          
         add(new RichTextField("Last Name: " +s1.p.edit3.getText()));  
         
         add(new RichTextField("First Name: " +s1.p.edit4.getText()));  

         add(new RichTextField("MI: " +s1.p.edit5.getText()));  
          
         add(new SeparatorField()); 
         
         add(new RichTextField("Address: " +s1.p.edit6.getText()));  
         
         add(new SeparatorField()); 
         
         if(s1.p.chk2.getChecked() == true){  
                     add(new RichTextField("Person is Employed to Drive"));  
         }
         else if(s1.p.chk2.getChecked() == false){
                      add(new RichTextField("Person is Not Employed to Drive"));   
         }
          
         add(new SeparatorField()); 
         
         lbl3 = new LabelField("AT THE FOLLOWING TIME AND PLACE:", 0, -1, Field.FIELD_HCENTER);
        
         FontFamily fontFamily5[] = FontFamily.getFontFamilies(); 
         Font fnt3 = fontFamily5[1].getFont(FontFamily.CBTF_FONT, 14).derive(Font.BOLD | Font.ITALIC); 
         lbl3.setFont(fnt3);
         add(lbl3);
         
         add(new SeparatorField()); 
         
         add(new RichTextField("Month: " +s1.loc.choice0.toString()));

         add(new RichTextField("Day: " +s1.loc.choice1.toString()));
         
         add(new RichTextField("Year: " +s1.loc.num0.toString()));

         add(new RichTextField("Time: " +s1.p.edit0.getText()));  

         if(s1.loc.radio0.isSelected() == true){
            add(new RichTextField("AM/PM: " + s1.loc.radio0.getLabel()));   
         }
         else if(s1.loc.radio1.isSelected() == true){
            add(new RichTextField("AM/PM: " + s1.loc.radio1.getLabel()));    
         } 
          
         
         if(s1.loc.chk0.getChecked() == true){  
                     add(new RichTextField("Occured On: " + s1.loc.chk0.getLabel()));  
         }
         else if(s1.loc.chk1.getChecked() == true){
                      add(new RichTextField("Occured On: " +s1.loc.chk1.getLabel()));   
         } 
         else if(s1.loc.chk2.getChecked() == true){
                      add(new RichTextField("Occured On: " +s1.loc.chk2.getLabel()));  
                      add(new RichTextField("------>: " +s1.loc.edit1.getText()));   
         } 
         
         add(new SeparatorField()); 
         
         add(new RichTextField("At or Near Location: " +s1.loc.edit2.getText()));  
         
         add(new SeparatorField());  
         
         
         
         
        // ButtonField
        btn2 = new ButtonField("Continue", ButtonField.CONSUME_CLICK);
        btn2.setChangeListener(new ButtonListener2(this)); 
        add(btn2);
        btn3 = new ButtonField("Go Back", ButtonField.CONSUME_CLICK);
        btn3.setChangeListener(new ButtonListener2(this)); 
        add(btn3);
        
        add(new SeparatorField());

     }
     
    public class ButtonListener2 implements FieldChangeListener {
        CitationScreen2 c2;
                    
        public ButtonListener2(CitationScreen2 n2){
            //c2 = new CitationScreen2(n2);
            c2 = n2;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn4 = (ButtonField) field;
            if(btn4.getLabel().toString() == "Continue"){ 
                UiApplication.getUiApplication().pushScreen(new CitationScreen3(c2));
                //pushScreen(new CitationScreen3(c2));
            }
            else if(btn4.getLabel().toString() == "Go Back"){
                UiApplication.getUiApplication().pushScreen(new CitationScreen1());
                 //pushScreen(new CitationScreen1());
            }   
        }
    }              

}
