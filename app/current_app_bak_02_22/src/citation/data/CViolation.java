
package citation.data;

import java.util.Date;
import java.util.Vector;

import net.rim.device.api.i18n.DateFormat;
import net.rim.device.api.i18n.SimpleDateFormat;
import net.rim.device.api.util.Persistable;



public class CViolation implements Persistable {
           
    public static final int CITATION_NO = 0;
    public static final int DATE = 1;
    public static final int TIME = 2;
    public static final int OFFENSES = 3;
    public static final int ACCIDENT = 4;
    public static final int RADAR = 5;
    public static final int JAIL_BOOKING = 6;
    public static final int PACED = 7;
    public static final int SCHOOL_ZONE = 8;
    public static final int ALCOHOL = 9;
    
    public static final int VBFI = 10;
    
    public static final int SPEED_LIMIT = 11;
    public static final int ALLEGED_SPEED = 12;
    

   
    public static final int COURT_DATE = 35;
    public static final int COURT_TIME = 36;
    
    public static final int BAC = 37;
    public static final int BAC_DATE = 38;     
    public static final int BAC_TIME = 39;  
    
    public static final int VIOLATION_TYPE = 40;

    public static final int NARRATIVE = 41;
    
    public static final int STATUTE = 42;
    public static final int FINE = 43;
    public static final int INTENTIONAL = 44;
    public static final int KNOWING = 45;
    public static final int RECKLESS = 46;
    public static final int CRIMINAL_NEG = 47;
    public static final int CULPABLE = 48;
    
    public static final int COURT_NAME = 49;
    public static final int COURT_ADDRESS = 50;
    public static final int COURT_CITY = 51;
    public static final int COURT_STATE = 52;
    public static final int COURT_ZIP = 53;
    public static final int COURT_PHONE = 54;
    
    public static final int ELEMENT_COUNT = 55;
    

    private Vector _elements;
        
    public CViolation() {  
        _elements = new Vector(ELEMENT_COUNT);
        for (int i = 0; i < _elements.capacity(); ++i) 
        {
                // initialize with current date-time values
                if (i == DATE || i == TIME || i == BAC_DATE || i == BAC_TIME) 
                {
                        Date date = new Date(System.currentTimeMillis());
                        _elements.addElement(date.toString());
                }
                else 
                {
                        _elements.addElement(new String(""));
                }
        }
    }

    public String getElement(int id) {
        return (String) _elements.elementAt(id);
    }

    public void setElement(int id, String value) {
        _elements.setElementAt(value, id);
    }
    
    public String toPrettyString(){
        return  "Citation Number: " + getElement(0) + "\n" +
        "Date: " + getElement(1) + "\n" +
        "Time: " + getElement(2) + "\n" +
        "Offenses: " + getElement(3) + "\n" +
        "Type of Accident: " + getElement(4) + "\n" +
        "Radar speed: " + getElement(5) + "\n" +
        "Are they being jailed: " + getElement(6) + "\n" +
        "Were they Paced" + getElement(7) + "\n" +
        "Was it in a School Zone: " + getElement(8) + "\n" +
        "Did they use Alchohol: " + getElement(9) + "\n" +
        "VBFI: " + getElement(10) + "\n" +
        "Speed Limit: " + getElement(11) + "\n" +
        "Alleged Speed: " + getElement(12) + "\n" +
        "RGRP1: " + getElement(14) + "\n" +
        "YES: " + getElement(15) + "\n" +
        "Officers First Name: " + getElement(16) + "\n" +
        "Officers Middle Name: " + getElement(17) + "\n" +
        "Officers Last Name: " + getElement(18) + "\n" +
        "Officers Badge Number: " + getElement(19) + "\n" +
        "Officers ID Number: " + getElement(20) + "\n" +
        "Circuit Court?: " + getElement(21) + "\n" +
        "Other Court?: " + getElement(22) + "\n" +
        "Community Court?: " + getElement(23) + "\n" +
        "RGRP2: " + getElement(24) + "\n" +
        "In Oregon City?: " + getElement(25) + "\n" +
        "In Clackamas: " + getElement(26) + "\n" +
        "Municpial Court?: " + getElement(27) + "\n" +
        "RGRP3: " + getElement(28) + "\n" +
        "Damascus?: " + getElement(29) + "\n" +
        "Estacada?: " + getElement(30) + "\n" +
        "Municpial Court?2???: " + getElement(31) + "\n" +
        "RGRP4: " + getElement(32) + "\n" +
        "Happy Valley?: " + getElement(33) + "\n" +
        "Wilsonvilee?: " + getElement(34) + "\n" +
        "Date of Court Hearing: " + getElement(35) + "\n" +
        "Time of Court Hearing: " + getElement(36) + "\n" +
        "Blood Alchol Content: " + getElement(37) + "\n" +
        "Date of Blood Alchol Content test: " + getElement(38) + "\n" +
        "Rime of Blood Alchol Content test: " + getElement(39) + "\n" +
        "Type of Violation: " + getElement(40) + "\n" +
        "Story/Narrative of what happened: " + getElement(41) + "\n" +
        "Relavant Statue(s): " + getElement(42) + "\n" +
        "Fine: " + getElement(43) + "\n" +
        "Was it intentional?: " + getElement(44) + "\n" +
        "Was it done knowingly?: " + getElement(45) + "\n" +
        "Were they Reckless: " + getElement(46) + "\n" +
        "Was it done with Criminal Negligence: " + getElement(47) + "\n" +
        "Are they Culpable: " + getElement(48) + "\n" ;
        }
    
  public String toXMLString(){                                                                                                                                                                                         
        return                                                                                                           
        "<CViolation>" + "\n" +                                                                        
        "<CITATION_NO>" + getElement(0) + "</CITATION_NO>" + "\n" +                                                      
        "<DATE>" + getElement(1) + "</DATE>" + "\n" +                                                                    
        "<TIME>" + getElement(2) + "</TIME>" + "\n" +                                                                    
        "<OFFENSES>" + getElement(3) + "</OFFENSES>" + "\n" +                                                            
        "<ACCIDENT>" + getElement(4) + "</ACCIDENT>" + "\n" +                                                            
        "<RADAR>" + getElement(5) + "</RADAR>" + "\n" +                                                                  
        "<JAIL_BOOKING>" + getElement(6) +"</JAIL_BOOKING>" + "\n" +                                                     
        "<PACED>" + getElement(7) + "</PACED>" + "\n" +                                                                  
        "<SCHOOL_ZONE>" + getElement(8) + "</SCHOOL_ZONE>" + "\n" +                                                      
        "<ALCOHOL>" + getElement(9) + "</ALCOHOL>" + "\n" +                                                             
        "<VBFI>" + getElement(10) + "</VBFI>" + "\n" +                                                                   
        "<SPEED_LIMIT>" + getElement(11) + "</SPEED_LIMIT>" + "\n" +                                                     
        "<ALLEGED_SPEED>" + getElement(12) + "</ALLEGED_SPEED>" + "\n" +                                                 
        "<RGRP1>" + getElement(13) + "</RGRP1>" + "\n" +                                                                 
        "<YES>" + getElement(14) + "</YES>" + "\n" +                                                                     
        "<NO>" + getElement(15) + "</NO>" + "\n" +                                                                       
        "<OFFICER_FIRST>" + getElement(16) + "</OFFICER_FIRST>" + "\n" +                                                 
        "<OFFICER_MIDDLE>" + getElement(17) + "</OFFICER_MIDDLE>" + "\n" +                                               
        "<OFFICER_LAST>" + getElement(28) +"</OFFICER_LAST>" + "\n" +                                                    
        "<OFFICER_BADGE>" + getElement(19) + "</OFFICER_BADGE>" + "\n" +                                                 
        "<OFFICER_ID>" + getElement(20) + "</OFFICER_ID>" + "\n" +                                                       
        "<CIRCUIT_CRT>" + getElement(21) + "</CIRCUIT_CRT>" + "\n" +                                                     
        "<OTHER_CRT>" + getElement(23) + "</OTHER_CRT>" + "\n" +                                                         
        "<COMMUNITY_CRT>" + getElement(23) + "</COMMUNITY_CRT>" + "\n" +                                                 
        "<RGRP2>" + getElement(24) + "</RGRP2>" + "\n" +                                                                 
        "<OREGON_CITY>" + getElement(25) + "</OREGON_CITY>" + "\n" +                                                     
        "<CLACKAMAS>" + getElement(26) + "</CLACKAMAS>" + "\n" +                                                         
        "<MUNICIPAL_CRT>" + getElement(27) + "</MUNICIPAL_CRT>" + "\n" +                                                 
        "<RGRP3>" + getElement(28) + "</RGRP3>" + "\n" +                                                                 
        "<DAMASCUS>" + getElement(29) + "</DAMASCUS>" + "\n" +                                                           
        "<ESTACADA>" + getElement(30) + "</ESTACADA>" + "\n" +                                                           
        "<MUNICIPAL_CRT2>" + getElement(31) + "</MUNICIPAL_CRT2>" + "\n" +                                               
        "<RGRP4>" + getElement(32) + "</RGRP4>" + "\n" +                                                                 
        "<HAPPY_VALLEY>" + getElement(33) +"</HAPPY_VALLEY>" + "\n" +                                                    
        "<WILSONVILLE>" + getElement(34) + "</WILSONVILLE>" + "\n" +                                                     
        "<COURT_DATE>" + getElement(35) + "</COURT_DATE>" + "\n" +                                                       
        "<COURT_TIME>" + getElement(36) + "</COURT_TIME>" + "\n" +                                                       
        "<BAC>" + getElement(37) + "</BAC>" + "\n" +                                                                     
        "<BAC_DATE>" + getElement(38) + "</BAC_DATE>" + "\n" +                                                           
        "<BAC_TIME>" + getElement(39) + "</BAC_TIME>" + "\n" +                                                           
        "<VIOLATION_TYPE>" + getElement(40) + "</VIOLATION_TYPE>" + "\n" +                                               
        "<NARRATIVE>" + getElement(41) + "</NARRATIVE>" + "\n" +                                                         
        "<STATUTE>" + getElement(42) + "</STATUTE>" + "\n" +                                                             
        "<FINE>" + getElement(43) + "</FINE>" + "\n" +                                                                   
        "<INTENTIONAL>" + getElement(44) + "</INTENTIONAL>" + "\n" +                                                     
        "<KNOWING>" + getElement(45) + "</KNOWING>" + "\n" +                                                             
        "<RECKLESS>" + getElement(46) + "</RECKLESS>" + "\n" +                                                           
        "<CRIMINAL_NEG>" + getElement(47) +"</CRIMINAL_NEG>" + "\n" +                                                    
        "<CULPABLE>" + getElement(48) + "</CULPABLE>" + "\n" +                                                           
        "</CViolation>" + "\n";                                                       
        }

}
