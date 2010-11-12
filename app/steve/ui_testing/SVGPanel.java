/*
 * SVGPanel.java
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

import javax.microedition.m2g.*;
import org.w3c.dom.svg.*;
import org.w3c.dom.events.*;

/**
 * This class represents an SVG panel
 */
class SVGPanel
{    
    // Reference to the main screen
    SVGFormsScreen _svgFormsScreen;
    
    // Reference to the animator    
    SVGAnimator _animator;
    
    SVGElement _currentFocusInElement, _currentFocusOutElement, _currentClickedElement;
    
    // The currently focused element 
    private SVGElement _currentFocusedElement;
    
    // The first and the last element of the SVG Panel
    private SVGElement _lastElement, _firstElement;
    
    // The last event that occurred in the panel
    String _currentEvent = "";    
    
    
    /**
     * Constructs a new SVGPanel
     * @param screen The applications's main screen
     */
    SVGPanel(SVGFormsScreen screen) 
    {
        _svgFormsScreen = screen;
        _animator = screen.getAnimator();
    }        
    
    /**
     * Sets the currently focused element
     * @param svgElement The SVGElement for which to set the focus
     */
    void setCurrentFocusElement(SVGElement svgElement)
    {
        this._currentFocusedElement = svgElement;
    }    
    
    /**
     * Returns the currently focused element
     * @return The currently focused element
     */
    SVGElement getCurrentFocusElement()
    {
        return this._currentFocusedElement;
    }    
    
    /**
     * Indicates whether the currently focused element is the first element in the panel
     * @return True if the currently focused element is the first element in the panel, otherwise false
     */
    boolean isFirstPanelElement()
    {
        if(this._currentFocusedElement == this._firstElement)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
        
    
    /**
     * Sets the first focusable element in the panel
     * @param svgElement The element to be the first to receive focus
     */
    void setFirstElement(SVGElement svgElement)
    {
        _firstElement = svgElement;
    } 
    
    /**
     * Returns the first focusable element of the panel
     * @return SVGElement The first focusable element of the panel
     */
    SVGElement getFirstElement()
    {
        return _firstElement;
    }    
    
    /**
     * Sets the last focusable element in the panel
     * @param svgElement The SVGElement to be the last to receive focus
     */    
    void setLastElement(SVGElement svgElement)
    {
        _lastElement = svgElement;
    }    
    
    /**
     * Returns the last focusable element of the panel
     * @return SVGElement The last focusable element of the panel
     */
    SVGElement getLastElement()
    {
        return _lastElement;
    }    
   
    /**
     * Activates the given svg element by registering an event listener for click and focus events
     * @param element The SVGElement to activate
     * @param eventListener The EventListener implementation that handles events for the given element
     */
    void activateSVGElement(SVGElement element, EventListener eventListener)
    {
        element.addEventListener("click", eventListener, false);
        element.addEventListener("DOMFocusIn", eventListener, false);
        //element.addEventListener("DOMFocusOut", eventListener, false);        
    }    

    /**
     * Deactivates the given svg element by de-registering it's EventListener
     * @param element The SVGElement to de-activate
     * @param eventListener The EventListener implementation that handles events for the given element
     */
    void deActivateSVGElement(SVGElement element, EventListener eventListener)
    {
        element.removeEventListener("click", eventListener, false);
        element.removeEventListener("DOMFocusIn", eventListener, false);
        element.removeEventListener("DOMFocusOut", eventListener, false);
    }   
}
