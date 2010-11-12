/*
 * SVGForms.java
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
 * ...another commment...
 */
//2nd conflict test
package com.rim.samples.device.svgformsdemo;

import net.rim.device.api.ui.*;

/**
 * SVG UI sample application. 
 */
class SVGForms extends UiApplication
{
    // Constructor
    private SVGForms() 
    {
        SVGFormsScreen _formsScreen = new SVGFormsScreen();
        this.pushScreen(_formsScreen);
    }   
    
    /**
     * Entry point for the application
     * @param args Command line arguments
     */     
    public static void main(String[] args) 
    {
        SVGForms _svgForms = new SVGForms();
        _svgForms.enterEventDispatcher();
    }
}
