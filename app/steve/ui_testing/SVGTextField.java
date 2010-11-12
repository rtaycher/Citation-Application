/*
 * SVGTextField.java
 *
 * Copyright © 1998-2008 Research In Motion Ltd.
 * 
 * Note: For the sake of simplicity, this sample application may not leverage
 * resource bundles and resource strings.  However, it is STRONGLY recommended
 * that application developers make use of the localization features available
 * within the BlackBerry development platform to ensure a seamless application
 * experience across a variety of languages and geographies.  For more information
 * on localizing your application, please refer to the BlackBerry Java Development
 * Environment Development Guide associated with this release.
 */

package com.rim.samples.device.svgformsdemo;

import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.*;


/**
 * A Custom text field that is the base for the SVG - Textbox
 */
class SVGTextField extends TextField
{    
    // Reference to the current text panel
    private ButtonPanel _myTextPanel;    
        
    /**
     * Constructs a new SVGTextField
     * @param textPanel The TextPanel that holds the SVGTextField 
     */
    SVGTextField(ButtonPanel buttonPanel) 
    {
        _myTextPanel = buttonPanel;
    }    
    
    /**
    * @see Field#keyChar(char, int, int)
    */
    public  boolean keyChar(char key, int status, int time) 
    {
        super.keyChar(key, status, time);
        _myTextPanel.setText(this.getText());
        return true;
    }  
    
    /**
     * @see TextField#navigationClick(int, int)
     */
    protected boolean navigationClick(int status, int time) 
    {
        if( Touchscreen.isSupported() ) {
            return false; // Ignore click events - centralize touch event handling in touchEvent() method
        }
        
        if (_myTextPanel.isTextBoxActive())
        {
            _myTextPanel.removeTextField();
        }
        return true;
    }    
    
    /**
     * @see TextField#touchEvent(TouchEvent)
     */
    protected boolean touchEvent(TouchEvent message)
    {        
       if (message.getEvent() == TouchEvent.CLICK)
       {            
            if (_myTextPanel.isTextBoxActive())
            {
                _myTextPanel.removeTextField();
            }
        }        
        return super.touchEvent(message);
    }
}
