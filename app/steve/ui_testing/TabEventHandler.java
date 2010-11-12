/*
 * TabEventHandler.java
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
 * Class to handle SVG elements representing tabs.
 */
class TabEventHandler implements org.w3c.dom.events.EventListener 
{
    // Reference to the main screen
    private SVGFormsScreen _svgFormsScreen;
    
    // Reference to the animator
    private SVGAnimator _animator;
    
    // Reference to the recently focused and clicked elements
    private SVGElement _currentFocusInElement, _currentClickedElement, _lastClickedTabElement;
    
    // The first and the last tab element of the SVG Panel
    private SVGElement _lastElement, _firstElement;
    
    // Tab elements
    private SVGElement _buttonTab;
    private SVGElement _buttonTab_enable;
 
    
    // Reference to various panels.
    private SVGElement _buttonview;
 
    
    /**
     * Constructs a new TabEventHandler
     * @param svgFormsScreen The application's main screen
     */
    TabEventHandler(SVGFormsScreen svgFormsScreen)
    {
        this._svgFormsScreen = svgFormsScreen;
        _animator = _svgFormsScreen.getAnimator();
        this.initializeTabs();
        this.activateTabs();
        _firstElement = _buttonTab;
        _lastElement = _buttonTab;
        _lastClickedTabElement = _buttonTab;
    }    
    
    /**
     * Initializes the tabs
     */     
     private void initializeTabs()
     {
        _buttonTab = (SVGElement)_svgFormsScreen.getElementById("buttontab");
        _buttonTab_enable = ( SVGElement)_svgFormsScreen.getElementById("buttontabenabled");
        _buttonview = _svgFormsScreen.getElementById("buttonview");

     }
     
    /**
     * Activates the given svg element by registering an event listener for click and focus events
     * @param element The SVGElement to activate
     * @param eventListener The EventListener implementation that handles events for the given element
     */
    private void activateSVGElement(SVGElement element, EventListener eventListener)
    {
        element.addEventListener("click", eventListener, false);
        element.addEventListener("DOMFocusIn", eventListener, false);
    }
     
     /**
     * Activates the tab elements
     */
    private void activateTabs()
    {
        activateSVGElement(_buttonTab, this);

    }    
    
    /**
     * Check if the currently focused tab element is last
     * @return True if the currently focused tab element is last, otherwise false
     */
    boolean isLastTabElement()
    {
        if(_currentFocusInElement == _lastElement)
        {
            return true;
        }
        else
        { 
            return false;
        }
    }    
    
    /**
     * Gets the first tab element
     * @return The first tab element
     */
    SVGElement getFirstTabElement()
    {
        return _firstElement;
    }
    
    
    /**
     * Updates the tab view
     * @param lastElement The most recently clicked tab element
     */
    private void updateTabView(SVGElement lastElement)
    {
        if(lastElement == _buttonTab)
        {
            _buttonTab_enable.setTrait("visibility", "hidden");
            _buttonview.setTrait("display", "none");
        }
        
    }
    
    /**
    * Handles the DOM event. 
    * @param evt The event to be handled
    */
    public void handleEvent(Event evt)
    {        
        if(evt.getType().equals("DOMFocusIn"))
        {
            _currentFocusInElement = (SVGElement)evt.getCurrentTarget();
        }
        if(evt.getType().equals("click"))
        {
            _currentClickedElement = (SVGElement)evt.getCurrentTarget();
            if(_currentClickedElement == _lastClickedTabElement)
            {
                return;
            }
                
            _animator.invokeLater(new Runnable()
            {
                public void run()
                {
                    if(_currentClickedElement == _buttonTab)
                    {   
                        _svgFormsScreen.setView(SVGFormsScreen.BUTTON);
                        _buttonview.setTrait("display", "inline");
                        updateTabView(_lastClickedTabElement);
                        _buttonTab_enable.setTrait("visibility", "visible");
                    }

                    _lastClickedTabElement = _currentClickedElement;
                }
            });
        }
    }
}
