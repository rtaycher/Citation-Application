/*
 * SvgTextFieldHelper.java
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

/**
 * Helper class for SVG text fields
 */
class SvgTextFieldHelper
{    
    // The main screen
    private SVGFormsScreen _svgFormsScreen;
    
    // The text panel
    private ButtonPanel _buttonPanel;
    
    // Text field that functions for the SVG text field
    private SVGTextField _svgTextfield;
    
    // Manager that hides the SVGTextField
    private TextBoxManager _textBoxManager;    
    
    /**
     * Constructs a new SvgTextFieldHelper
     * @param svgFormsScreen The applications's main screen
     * @param textPanel The panel that contains the text field
     */
    SvgTextFieldHelper(SVGFormsScreen svgFormsScreen, ButtonPanel buttonPanel)
    {
        _svgFormsScreen = svgFormsScreen;
        _buttonPanel = buttonPanel;
        _textBoxManager = new TextBoxManager();        
        _svgTextfield = new SVGTextField(_buttonPanel);        
    }

    /**
     * Adds a text field to the screen and initializes it
     */
    void addTextField()
    {   
        // Add the text field to the TextBoxManager
        _textBoxManager.add(_svgTextfield);
        
        // Add the manager to the screen
        _svgFormsScreen.add(_textBoxManager);
        _textBoxManager.setFocus();
        
        // Set the maximum length of the TextField
        _svgTextfield.setMaxSize(20);
        
        // Set the cursor position at the end of the text
        String text = _svgTextfield.getText();
        int size = text.length();
        _svgTextfield.setCursorPosition(size);
    }    
    
   /**
    * Removes the text field from the screen and sets the focus back to the panel
    */
    void removeTextField()
    {
        _textBoxManager.delete(_svgTextfield);
        _svgFormsScreen.delete(_textBoxManager);
        _svgFormsScreen.setFocus(_buttonPanel.getFirstElement());
    }
}
