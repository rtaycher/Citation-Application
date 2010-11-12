/*
 * Vehicle.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package citation.data;

import net.rim.device.api.ui.component.*;

/**
 * 
 */
public class Vehicle {
    CheckboxField chk0;
    CheckboxField chk1;
    CheckboxField chk2;
    CheckboxField chk3;
    
    CheckboxField chk4;
    CheckboxField chk5;
    CheckboxField chk6;
    CheckboxField chk7;
    
    ObjectChoiceField choice0;  // State
    ObjectChoiceField choice1;  // Style
    ObjectChoiceField choice2;  // Color
    ObjectChoiceField choice3;  // Secondary Color
    ObjectChoiceField choice4;  //
    ObjectChoiceField choice5;  //
    
    NumericChoiceField num0;  // Year
    NumericChoiceField num1;  // Plate Exp. Year
    
    
    EditField edit0;  // Regis/VIN/ID No.
    EditField edit1;  // Make
    EditField edit2;  // Model
    EditField edit3;  // License Plate No.
    EditField edit4;  // VIN
    
    public Vehicle() {    }
} 
