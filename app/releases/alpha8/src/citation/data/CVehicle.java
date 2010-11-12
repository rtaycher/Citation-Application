package citation.data;

import java.util.Vector;
import net.rim.device.api.util.Persistable;

public class CVehicle implements Persistable {
        
    public static final int TYPE = 0;
    public static final int PLATE = 1;
    public static final int REG_STATE = 2;
    public static final int MAKE = 3;
    public static final int MODEL = 4;
    public static final int STYLE = 5;
    public static final int YEAR = 6;
    public static final int PRIM_COLOR = 7;
    public static final int SEC_COLOR = 8;
    public static final int VIN = 9;
    public static final int ELEMENT_COUNT = 10;
        
    private Vector _elements;
    private boolean readonly;
        
    public CVehicle() {  
        setReadOnly(false);
        _elements = new Vector(ELEMENT_COUNT);
        for (int i = 0; i < _elements.capacity(); ++i) {
            _elements.addElement(new String(""));
        }
    }

    public String getElement(int id) {
        return (String) _elements.elementAt(id);
    }

    public void setElement(int id, String value) {
        if (!isReadOnly()) {
            _elements.setElementAt(value, id);
        }
    }
    
    public void setReadOnly(boolean readonly) {
        this.readonly = readonly;
    }

    public boolean isReadOnly() {
        return readonly;
    }

    public String toPrettyString()
    {
        return  "Type: "+ getElement(0) + "\n" +
                "Plate: "+ getElement(1) + "\n" +
                "Registration State: "+ getElement(2) + "\n" +
                "Make of Car: "+ getElement(3) + "\n" +
                "Model of Car: "+ getElement(4) + "\n" +
                "Style of Car: "+ getElement(5) + "\n" +
                "Year: "+ getElement(6) + "\n" +
                "Cars primary color: "+ getElement(7) + "\n" +
                "Cars secondary color: "+ getElement(8) + "\n" +
                "Car's vin: "+ getElement(9) + "\n";
    }
    
    public String toXMLString()
    {
        return  "<CVehicle>" + "\n" +
                "<TYPE>" + getElement(0) + "</TYPE>" + "\n" + 
                "<PLATE>" + getElement(1) + "</PLATE>" + "\n" +
                "<STATE>" + getElement(2) + "</STATE>" + "\n" +
                "<MAKE>" + getElement(3) + "</MAKE>" + "\n" +
                "<MODEL>" + getElement(4) + "</MODEL>" + "\n" +
                "<STYLE>" + getElement(5) + "</STYLE>" + "\n" +
                "<YEAR>" + getElement(6) + "</YEAR>" + "\n" +
                "<PRICOLOR>" + getElement(7) + "</PRICOLOR>" + "\n" +
                "<SECCOLOR>" + getElement(8) + "</SECCOLOR>" + "\n" +
                "<VIN>" + getElement(9) + "</VIN>" + "\n" +
                "</CVehicle>" + "\n";
    }
}
