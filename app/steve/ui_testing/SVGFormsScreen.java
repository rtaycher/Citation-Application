

package mySample7.com;

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.ui.component.*;
import javax.microedition.m2g.*;
import org.w3c.dom.svg.*;
import org.w3c.dom.*;
import java.io.*;

/**
 * A MainScreen on which to render SVG elements
 */
class SVGFormsScreen extends MainScreen
{    
    // Sample SVG forms file to be displayed
    private static final String SVG_URL = "cod://citation/svgforms.svg";
    
    // Scroll directions
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;
    
    // Panel Ids
    static final int BUTTON = 1;
  
    
    // SVGImage instance to store the parsed SVG data
    private SVGImage _image;
    
    // SVGAnimator to obtain an SVGField
    private SVGAnimator _animator;
    
    // Document to hold SVGImage contents
    private Document _document;
    
    // Panels
    private TabEventHandler _tabEventHandler;
    private ButtonPanel _buttonPanel;

    
    // Current view
    private int _currentView = BUTTON;
    
    // Indicates the current mode
    private boolean _tabMode = true;
    private boolean _controlsMode = false;
    private boolean _dialogboxMode = false;
    
    private int _direction;       
    
    // Constructor
    SVGFormsScreen() 
    {
        super(Screen.NO_VERTICAL_SCROLL);
        
        try 
        {
            // Obtains an input stream to the SVG file URL
            InputStream is = javax.microedition.io.Connector.openInputStream(SVG_URL);
            
            // Loads the SVG image using the input stream connection
            _image = (SVGImage)SVGImage.createImage(is, null);
            
            // Create an interactive SVG animator that hosts SVG field
            _animator = SVGAnimator.createAnimator(_image, "net.rim.device.api.ui.Field");
            
            // Obtain the images document
            _document = _image.getDocument();
        } 
        catch (IOException ioe)
        {
            System.out.println("Error while opening the .svg file.");
        }
        
        // Get the SVG field
        Field _svgField = (Field)_animator.getTargetComponent(); 
        
        // Manager to lay out checkboxes
        VerticalFieldManager vfm = new VerticalFieldManager();
        this.add(vfm);
        vfm.add(_svgField);
        
        // Initialize the event handlers
        _tabEventHandler = new TabEventHandler(this);

        _buttonPanel = new ButtonPanel(this);

        
        // Start the animator
        _animator.play();
    }    
    
    /**
     * Returns the animator
     * @return SVGAnimator The animator
     */
    SVGAnimator getAnimator()
    {
        return _animator;
    }    
    
    /**
     * Returns element corresponding to a given ID
     * @param elementId The ID  of the element to return
     * @return SVGElement An SVG element corresponding to the given ID
     */
    SVGElement getElementById(String elementId)
    {
        SVGElement _svgElement = (SVGElement)_document.getElementById(elementId);
        return _svgElement;
    }    
   
    /**
     * Sets focus to a specified element or null
     */
    void setFocus(SVGElement svgElement)
    {
        _image.focusOn(svgElement);
    }    
    
    /**
     * Sets the current panel view
     * @param view Integer representing button, sliders, lists, or text views
     */
    void setView(int view)
    {
        this._currentView = view;
    }    
    
    /**
     * Handles navigation movement inside the tabs 
     */
    private void tabControlsNavigation(int dx, int dy, int status, int time)
    {
        switch(_direction)
        {
            case UP:
                break;
                
            case DOWN:
            {
                setFocus(null);
                
                // Set the focus to the first element of the chosen panel when scroll ball direction is "down"     
                switch (_currentView)
                {
                    case BUTTON:
                        _image.focusOn(_buttonPanel.getFirstElement());
                        break;

                }  
                
                _tabMode=false;
                _controlsMode=true;
                break;
            }
            case LEFT:
            {
                super.navigationMovement(dx, dy, status, time);
                break;
            }
            case RIGHT:
            {
                // If the scroll direction is "right" on the last element then stay at the last tab element.
                if (_tabEventHandler.isLastTabElement() == false) 
                    super.navigationMovement(dx, dy, status, time);
                break;
            }
        }
    }    
    
    /**
     * Handles navigation movement inside a panel
     * @param svgPanel The SVGPanel corresponding to the current view
     * @param dx Magnitude of navigational motion: negative for a move left and postive for a move right
     * @param dy dy - Magnitude of navigational motion: negative for an upwards move, and positive for a downwards move
     * @param status Modifier key status at time of move
     * @param time Number of milliseconds since the device was turned on
     */    
    private void panelControlsNavigation(SVGPanel svgPanel, int dx, int dy, int status, int time)
    {
        /* It is essential that the first panel element is never skipped. The following changes to the dx/dy ensures it. */
        if(dx<0) dx = -1;
        if(dx>0) dx = 1;
        if(dy<0) dy = -1;
        if(dy>0) dy = 1;
        
        switch(_direction)
        {
            case UP:
            {
                // If the scroll direction is "up" while on the first panel element, shift the focus to 
                // to Tabmode and the focus to the first tab element.
                if (svgPanel.isFirstPanelElement())
                {
                    setFocus(null);
                    _image.focusOn(_tabEventHandler.getFirstTabElement());
                    _controlsMode=false;
                    _tabMode=true;
                }
                super.navigationMovement(dx, dy, status, time);
                break;
            }
            case LEFT:
            {
                // If the scroll direction is left on the first element then do nothing
                if (svgPanel.isFirstPanelElement() == false)
                {
                    super.navigationMovement(dx, dy, status, time);
                }
                break;
            }
            case RIGHT:
            case DOWN:
            {
                super.navigationMovement(dx, dy, status, time);
                break;
            }
        }
    }    
    
  
    /**
     * Handles navigation movement inside a Combobox
     * @param dx Magnitude of navigational motion: negative for a move left and postive for a move right
     * @param dy dy - Magnitude of navigational motion: negative for an upwards move, and positive for a downwards move
     * @param status Modifier key status at time of move
     * @param time Number of milliseconds since the device was turned on
     */
    private void listboxNavigation(int dx, int dy, int status, int time)
    {
        switch(_direction)
        {
            case UP:
            {
                if(_buttonPanel.inComboboxModeNavigation())
                {
                    // If the current focus element jumps beyond the list box elements, 
                    // focus back to the top combobox element.
                    if(_buttonPanel.isComboboxElement() == false)
                    {
                        _image.focusOn(_buttonPanel.getFirstComboboxElement());
                    }
                    
                    // If scroll direction is "up" and the element is the first, then stay there
                    if(_buttonPanel.getCurrentFocusElement() != _buttonPanel.getFirstComboboxElement())
                    {
                        super.navigationMovement(dx, dy, status, time);
                    }
                }
                break;
            }
            case DOWN:
            {
                if(_buttonPanel.inComboboxModeNavigation())
                {
                    // If the current focus element jumps beyond the combobox elements, 
                    // focus back to the top bottom-most element.
                    if(_buttonPanel.isComboboxElement() == false)
                    {
                        _image.focusOn(_buttonPanel.getLastComboboxElement());
                    }
                    
                    // If scroll direction is "down" and the element is the last, then stay there.
                    if(_buttonPanel.getCurrentFocusElement() != _buttonPanel.getLastComboboxElement())
                    {
                        super.navigationMovement(dx, dy, status, time);
                    }
                }
                break;
            }
            case RIGHT:
            case LEFT:
                break;
        }        
    }      
  
   /**
     * Handles navigation movement inside a Textbox      
     */
    private void textBoxNavigation()
    {
        switch (_direction)
        {
            case LEFT:
            case RIGHT:
            case UP:
            case DOWN:
            {
                _image.focusOn(_buttonPanel.getCurrentFocusElement());
                break;
            }            
        }
    }
    
    /**
     * Restricts the navigation mode to the dialog box area.  Also sets the
     * focus to dialogbox button when in dialog box mode and to the last
     * element when in controls mode.
     * @param svgPanel The SVGPanel corresponding to the current view
     * @param dialogboxmode Boolean indicating the current dialog box mode status
     */
    void dialogboxMode(SVGPanel svgPanel, boolean dialogboxmode)
    {
        // If dialog box pops up, restrict the navigation movement within the dialog box
        if(dialogboxmode == true)
        {
            this._controlsMode = false;
            
            
            
            //if(this.getElementById.getAttribute("dialogboxbutton").compareTo("dialogboxbutton") == 0)
            //{
                
                
              SVGElement dialogboxbutton = this.getElementById("dialogboxbutton");
              _image.focusOn(dialogboxbutton);
              
              
           // }
          //  else if(this.getElementById.getAttribute("dialogboxbutton2").compareTo("dialogboxbutton2") == 0)
           // {
              SVGElement dialogboxbutton2 = this.getElementById("dialogboxbutton2");
              _image.focusOn(dialogboxbutton2);
           // }
              
        }
        else 
        {
            this._controlsMode = true;          
            _image.focusOn(svgPanel.getLastElement());
        }
        _dialogboxMode = dialogboxmode;
    } 
    
    /**
     * @see net.rim.device.api.ui.Manager#sublayout(int,int)      
     * @param width Width available for this screen
     * @param height Width available for this screen
     */
    protected void sublayout( int width, int height )
    {
        int tabWidth = width / 6;   
        int textboxBasePosition = width / 2; 
        int dialogBasePosition = width / 10;        
        int dialogButtonBasePosition = width / 4;
        
        float colorSliderBasePosition = (float) (width / 1.60);   
        float comboboxBasePosition = (float)(width / 1.75);  
        
        
        /***********************************************************************
        *                              Border                                  *
        ***********************************************************************/               
        
        SVGElement border = (SVGElement)getElementById("border");                
        border.setFloatTrait("width", width - 20);
        border.setFloatTrait("x", 10);        
        
        
        /***********************************************************************
        *             Tab rect (section that contains the tab name)            *
        ***********************************************************************/               
        
        // Get tab rect elements
        SVGElement buttonTabRect = (SVGElement)getElementById("buttontabrect");

            
        // Set tab rect width               
        buttonTabRect.setFloatTrait("width", tabWidth);

        
        // Set tab rect x position
        buttonTabRect.setFloatTrait("x", 20);

        
        /***********************************************************************
        *                          Tab name text                               *
        ***********************************************************************/               
        
        // Get tab name text elements
        SVGElement buttonTabNameText = (SVGElement)getElementById("buttontabnametext");

      
        //  Set tab name text x position        
        buttonTabNameText.setFloatTrait("x", 22);        
         
        
        /***********************************************************************
        *           Tab enabled (erased border underneath selected tab)        *
        ***********************************************************************/               
        
        // Get tab enabled elements
        SVGElement buttonTabEnabled = (SVGElement)getElementById("buttontabenabled");

        
        // Set tab enabled widths
        buttonTabEnabled.setFloatTrait("width", tabWidth);

        
        // Set tab enabled x positions
        buttonTabEnabled.setFloatTrait("x", 20);

        
        /***********************************************************************
        *                             Buttons tab                              *
        ***********************************************************************/               
        
        // Get button 1 elements
        SVGElement button1Rect = (SVGElement)getElementById("button1rect");
        SVGElement button1Text = (SVGElement)getElementById("button1text");
        
        // Set button 1 positions
        button1Rect.setFloatTrait("x", width / 2 - 50);   
        button1Text.setFloatTrait("x", width / 2 - 33);   
        
        
        // Get text box elements
        SVGElement textBoxLabelText = (SVGElement)getElementById("textboxlabeltext");
        SVGElement textBoxRect = (SVGElement)getElementById("textboxrect");
        SVGElement textBoxText = (SVGElement)getElementById("textboxtext");
        SVGElement textBoxCursor = (SVGElement)getElementById("textboxcursor");
        
        
        // Calculate position of cursor relative to text field
        String textBoxRectPositionString = textBoxRect.getTrait("x");
        Float textBoxRectPositionFloat = Float.valueOf(textBoxRectPositionString);
        float textBoxRectPosition = textBoxRectPositionFloat.floatValue();
        String textBoxCursorPostionStr = textBoxCursor.getTrait("x");
        Float textBoxCursorPositionFloat = Float.valueOf(textBoxCursorPostionStr);
        float textBoxCursorPosition = textBoxCursorPositionFloat.floatValue();
        float textBoxCursorRelativePosition = textBoxCursorPosition - textBoxRectPosition; 
        
        // Set text box positions
        textBoxCursor.setFloatTrait("x", textboxBasePosition - 165 + textBoxCursorRelativePosition);   
            
        textBoxLabelText.setFloatTrait("x", textboxBasePosition - 165);              
        textBoxRect.setFloatTrait("x", textboxBasePosition - 165);        
        textBoxText.setFloatTrait("x", textboxBasePosition - 160);          
        
        
// Get combo box rect and text elements
        SVGElement comboBoxRect = (SVGElement)getElementById("comboboxrect");
        SVGElement comboBoxRect1 = (SVGElement)getElementById("comboboxrect1");
        SVGElement comboBoxRect2 = (SVGElement)getElementById("comboboxrect2");
        SVGElement comboBoxRect3 = (SVGElement)getElementById("comboboxrect3");
        SVGElement comboBoxItemText1 = (SVGElement)getElementById("comboboxitemtext1");
        SVGElement comboBoxItemText2 = (SVGElement)getElementById("comboboxitemtext2");
        SVGElement comboBoxItemText3 = (SVGElement)getElementById("comboboxitemtext3");        
        SVGElement currentComboBoxRect = (SVGElement)getElementById("currentcomboboxrect");        
        SVGElement currentComboBoxText = (SVGElement)getElementById("currentcomboboxitemtext");      
        //SVGElement button2Rect = (SVGElement)getElementById("button2rect");
        //SVGElement button2Text = (SVGElement)getElementById("button2text");   
        
        // Set combo box rect and text positions
        comboBoxRect.setFloatTrait("x", comboboxBasePosition);          
        comboBoxRect1.setFloatTrait("x", comboboxBasePosition);
        comboBoxRect2.setFloatTrait("x", comboboxBasePosition);
        comboBoxRect3.setFloatTrait("x", comboboxBasePosition);        
        //comboBoxItemText1.setFloatTrait("x", comboboxBasePosition + 50);
        //comboBoxItemText2.setFloatTrait("x", comboboxBasePosition + 50);
        //comboBoxItemText3.setFloatTrait("x", comboboxBasePosition + 50);
        comboBoxItemText1.setFloatTrait("x", comboboxBasePosition);
        comboBoxItemText2.setFloatTrait("x", comboboxBasePosition);
        comboBoxItemText3.setFloatTrait("x", comboboxBasePosition);
        
        currentComboBoxRect.setFloatTrait("x", comboboxBasePosition );
        //currentComboBoxText.setFloatTrait("x", comboboxBasePosition + 50 ); 
        currentComboBoxText.setFloatTrait("x", comboboxBasePosition ); 
              
        //button2Rect.setFloatTrait("x", width / 2 - 50);   
        //button2Text.setFloatTrait("x", width / 2 - 33);   
        
        // Get combo box arrow elements
        SVGElement comboBoxArrowRect = (SVGElement)getElementById("comboboxarrowrect");
        SVGElement comboBoxArrowUp = (SVGElement)getElementById("comboboxarrowup");
        SVGElement comboBoxArrowDown = (SVGElement)getElementById("comboboxarrowdown");
        
        // Create points string for up and down arrows
        float point1x = comboboxBasePosition + 105;        
        float point2x = comboboxBasePosition + 110;       
        float point3x = comboboxBasePosition + 115;   
        String point1xString = Float.toString(point1x);        
        String point2xString = Float.toString(point2x);        
        String point3xString = Float.toString(point3x);      
        //String upArrowPointsString = point1xString + ", 82, "  + point2xString + ",77, " +  point3xString + ",82";      
        //String downArrowPointsString = point1xString + ", 77, " +  point2xString + ",82, " +  point3xString + ",77";      
        String upArrowPointsString = point1xString + ", 172, "  + point2xString + ",167, " +  point3xString + ",172";      
        String downArrowPointsString = point1xString + ", 167, " +  point2xString + ",172, " +  point3xString + ",167"; 
        
        // Set combo box arrow positions
        comboBoxArrowRect.setFloatTrait("x", comboboxBasePosition + 100);
        comboBoxArrowUp.setTrait("points", upArrowPointsString);
        comboBoxArrowDown.setTrait("points", downArrowPointsString);       




        // Get dialog box elements
        SVGElement dialogBoxRect = (SVGElement)getElementById("dialogboxrect");
        SVGElement dialogBoxText1 = (SVGElement)getElementById("dialogboxtext1");
        SVGElement dialogBoxText2 = (SVGElement)getElementById("dialogboxtext2");
        SVGElement dialogBoxText3 = (SVGElement)getElementById("dialogboxtext3");
        SVGElement dialogBoxText4 = (SVGElement)getElementById("dialogboxtext4");
        SVGElement dialogBoxButtonRect = (SVGElement)getElementById("dialogboxbuttonrect");
        SVGElement dialogBoxButtonText = (SVGElement)getElementById("dialogboxbuttontext");
        
        // Set dialog box positions
        dialogBoxRect.setFloatTrait("x", dialogBasePosition);
        dialogBoxText1.setFloatTrait("x", dialogBasePosition + 40);
        dialogBoxText2.setFloatTrait("x", dialogBasePosition + 40);
        dialogBoxText3.setFloatTrait("x", dialogBasePosition + 40);
        dialogBoxText4.setFloatTrait("x", dialogBasePosition + 40);
        dialogBoxButtonRect.setFloatTrait("x", dialogButtonBasePosition);
        dialogBoxButtonText.setFloatTrait("x", dialogButtonBasePosition + 5);

        super.sublayout(width, height);       
    }
    
    /**
    * Prevent the save dialog from being displayed
    * @see MainScreen#onSavePrompt()
    */
    public boolean onSavePrompt()
    {
        return true;
    }    
    
    /**
     * @see Screen#onMenu(int)
     */
    public boolean onMenu(int instance) 
    {
        if (instance == Menu.INSTANCE_CONTEXT) 
        {
            return true;            
        }
        else 
        {
            return super.onMenu(instance);
        }
    }    
    
    /**
     * @see Manager#navigationMovement(int, int, int, int)
     */
    protected boolean navigationMovement(int dx, int dy, int status, int time) 
    {
        if(dx < 0)
        {
            _direction = LEFT;
        }
        if(dy < 0)
        {
            _direction = UP;
        }
        if(dx > 0)
        {
            _direction = RIGHT;
        }
        if(dy > 0)
        {
            _direction = DOWN;
        }
        
        // Control movement inside the tabs
        if(_tabMode)
        {
            tabControlsNavigation(dx, dy, status,time);
        }
        else if (_controlsMode)
        {
            switch(_currentView)
            {
                // Control movement inside the button panel
                case BUTTON:
                {
                    //panelControlsNavigation(_buttonPanel, dx, dy, status, time);
                   
                    if(_buttonPanel.isTextBoxActive())
                    {
                        this.textBoxNavigation();
                    }
                    else if(_buttonPanel.isComboboxActivated())
                    {
                        if(_buttonPanel.inComboboxModeNavigation() == false)
                        {
                            _buttonPanel.comboboxModeNavigationOn();
                            _image.focusOn(_buttonPanel.getFirstComboboxElement());
                        }
                        this.listboxNavigation(dx, dy, status, time);
                    }
                    else
                    { 
                        if(_buttonPanel.inComboboxModeNavigation() == true)
                        {
                            _buttonPanel.comboboxModeNavigationOff();
                            _image.focusOn(_buttonPanel.getFirstElement());
                        }    
                        panelControlsNavigation(_buttonPanel, dx, dy, status, time);
                    }
                   
                    break;
                }
              
            }
        } 
        else if(_dialogboxMode == true)
        {
            // Keep the focus on to the dialog box button
            //SVGElement dialogboxbutton = this.getElementById("dialogboxbutton");
           // _image.focusOn(dialogboxbutton);
            
            
            
            
         //   if(this.getElementById.getAttribute("dialogboxbutton").compareTo("dialogboxbutton") == 0)
         //   {    
              SVGElement dialogboxbutton = this.getElementById("dialogboxbutton");
              _image.focusOn(dialogboxbutton);    
         //   }
         //   else if(this.getElementById.getAttribute("dialogboxbutton2").compareTo("dialogboxbutton2") == 0)
         //   {
              SVGElement dialogboxbutton2 = this.getElementById("dialogboxbutton2");
              _image.focusOn(dialogboxbutton2);
         //   }
            
            
        }
        return true;
    }    
    

}
