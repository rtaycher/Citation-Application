
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
    
    public static final int RGRP1 = 13;
    public static final int YES = 14;
    public static final int NO = 15;
    
    public static final int OFFICER_FIRST = 16;
    public static final int OFFICER_MIDDLE = 17;
    public static final int OFFICER_LAST = 18;
    public static final int OFFICER_BADGE = 19;
    public static final int OFFICER_ID = 20;
    public static final int CIRCUIT_CRT = 21;
    public static final int OTHER_CRT = 22;
    public static final int COMMUNITY_CRT = 23;
    
    public static final int RGRP2 = 24;
    public static final int OREGON_CITY = 25;
    public static final int CLACKAMAS = 26;
    
    public static final int MUNICIPAL_CRT = 27;
    
    public static final int RGRP3 = 28;
    public static final int DAMASCUS = 29;
    public static final int ESTACADA = 30;
    
    public static final int MUNICIPAL_CRT2 = 31;
    
    public static final int RGRP4 = 32;
    public static final int HAPPY_VALLEY = 33;
    public static final int WILSONVILLE = 34;
   
    public static final int COURT_DATE = 35;
    public static final int COURT_TIME = 36;
    
    public static final int BAC = 37;
    public static final int BAC_DATE = 38;     
    public static final int BAC_TIME = 39;  
    
    public static final int VIOLATION_TYPE = 40;

    public static final int NARRATIVE = 41;
    
    public static final int ELEMENT_COUNT = 42;
    
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
}
