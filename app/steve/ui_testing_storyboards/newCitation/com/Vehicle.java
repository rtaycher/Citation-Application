/*
 * Vehicle.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package newCitation.com;

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;

/**
 * 
 */
class Vehicle {
    ObjectChoiceField type;
    EditField plate;
    ObjectChoiceField reg_state;
    EditField make;
    EditField model;
    ObjectChoiceField style;
    NumericChoiceField year;
    ObjectChoiceField prim_color;
    ObjectChoiceField sec_color;
    EditField VIN;
    
    //------------------
    
    
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
    
    Vehicle() {  
    }
} 
