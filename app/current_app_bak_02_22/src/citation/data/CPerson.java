/*
 * Person.java
 *
 * � <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package citation.data;

import java.util.Vector;
import net.rim.device.api.util.Persistable;


/**
 * 
 */
public class CPerson implements Persistable {
        
    public static final int TYPE = 0;
    
    public static final int ID = 1;
    public static final int ID_STATE = 2;
    public static final int TITLE = 3;
    public static final int FIRST = 4;
    public static final int MIDDLE = 5;
    public static final int LAST = 6;
    public static final int SUFFIX = 7;
    public static final int ADDRESS = 8;
    public static final int CITY = 9;
    public static final int STATE = 10;
    public static final int ZIP = 11;
    public static final int DOB = 12;
    public static final int SEX = 13;
    public static final int RACE = 14;
    public static final int EYES = 15;
    public static final int HAIR = 16;
    public static final int ENDORSEMENTS = 17;
    public static final int RESTRICTIONS = 18;
    public static final int HEIGHT = 19;
    public static final int WEIGHT = 20;
    public static final int OBSERVATIONS = 21;
    public static final int NUM_OF_VIOLATIONS = 22;
    
    public static final int PERSON = 23;    // part of the TYPE radio button group
    public static final int PERSON_SELECT = 24;    // part of the TYPE radio button group
    public static final int BUSINESS = 25;  // part of the TYPE radio button group
    public static final int BUSINESS_SELECT = 26;    // part of the TYPE radio button group
    
    public static final int ELEMENT_COUNT = 27;

    private Vector _elements;
        
    public CPerson() {  
        _elements = new Vector(ELEMENT_COUNT);
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
    

    public String toPrettyString(){
    return "Type: "+ getElement(0) + "\n" +
    "ID: "+ getElement(1) + "\n" +
    "ID state: " + getElement(2) + "\n" +
    "Title: " + getElement(3) + "\n" +
    "First: " + getElement(4) + "\n" +
    "Middle: " + getElement(5) + "\n" + 
    "Last: " + getElement(6) + "\n" + 6 +
    "Suffix: " + getElement(7) + "\n" + 
    "Address: " + getElement(8) + "\n" + 
    "City: " + getElement(9) + "\n" + 
    "State: " + getElement(10) + "\n" + 
    "Zip: " + getElement(11) + "\n" + 
    "Dob: " + getElement(12) + "\n" + 
    "Sex: " + getElement(13) + "\n" + 
    "Race: " + getElement(14) + "\n" + 
    "Eyes: " + getElement(15) + "\n" + 
    "Hair: " + getElement(16) + "\n" + 
    "Endorsements: " + getElement(17) + "\n" + 
    "Restrictions: " + getElement(18) + "\n" + 
    "Height: " + getElement(19) + "\n" + 
    "Weight: " + getElement(20) + "\n" + 
    "Observations: " + getElement(21) + "\n" + 
    "Number of Violations: " + getElement(22) + "\n" ;
    }
    
    public String toXMLString(){
    return "<CPerson>" + 
    "<TYPE>" + getElement(0) + "</TYPE>"+ "\n" +
    "<ID>"+ getElement(1) + "</ID>"+ "\n" +
    "<ID_STATE>" + getElement(2) + "</ID_STATE>"+ "\n" +
    "<TITLE>" + getElement(3) + "<TITLE>" + "\n"  +
    "<FIRST>" + getElement(4) + "<FIRST>" + "\n" +
    "<MIDDLE>" + getElement(5) +"<MIDDLE>" +  "\n" + 
    "<LAST>" + getElement(6) + "<LAST>" + "\n" +
    "<SUFFIX>" + getElement(7) +"<SUFFIX>" + "\n" + 
    "<ADDRESS>" + getElement(8) + "<ADDRESS>" + "\n" + 
    "<CITY>" + getElement(9) + "<CITY>" + "\n" + 
    "<STATE>" + getElement(10) +"<STATE>" +  "\n" + 
    "<ZIP" + getElement(11) + "</ZIP>" + "\n" + 
    "<DOB>" + getElement(12) + "</DOB>" + "\n" + 
    "<SEX>" + getElement(13) + "</SEX>" + "\n" + 
    "<RACE>" + getElement(14) + "</RACE>" + "\n" + 
    "<EYES>" + getElement(15) + "</EYES>" + "\n" + 
    "<HAIR>" + getElement(16) + "</HAIR>" + "\n" + 
    "<ENDORSEMENTS>" + getElement(17)+ "</ENDORSEMENTS>" + "\n" + 
    "<RESTRICTIONS>" + getElement(18) + "</RESTRICTIONS>" + "\n" + 
    "<HEIGHT>" + getElement(19) + "</HEIGHT>" + "\n" + 
    "<WEIGHT>" + getElement(20) + "</WEIGHT>" + "\n" + 
    "<OBSERVATIONS>" + getElement(21) + "</OBSERVATIONS>" + "\n" + 
    "<NUM_OF_VIOLATIONS>" + getElement(21) + "</NUM_OF_VIOLATIONS>" + "\n" + 
    "<NUMBER OF VIOLATIONS>" + getElement(22) + "</NUMBER OF VIOLET " +"\n" +
    "</CPerson>";
    }


    
}
