 
package mySample7.com;

import org.w3c.dom.svg.*;
import org.w3c.dom.events.*; 
import java.util.*;
 
 
//import org.w3c.dom.svg.*;
//import org.w3c.dom.events.*;
import net.rim.device.api.ui.*;
import net.rim.device.api.system.*; 
 
import net.rim.device.api.ui.container.*;
import net.rim.device.api.ui.component.*;
import javax.microedition.m2g.*;
import org.w3c.dom.svg.*;
import org.w3c.dom.*;
import java.io.*; 
 
/**
 *  A panel containing various SVG buttons
 */
class ButtonPanel extends SVGPanel implements org.w3c.dom.events.EventListener
{
    // Button panel elements
    private SVGElement _checkbox1;
    private SVGElement _checkbox1_check;
    private SVGElement _checkbox1_text;
    private SVGElement _checkbox2;
    private SVGElement _checkbox2_check;
    private SVGElement _checkbox2_text;

    private SVGElement _checkbox3;
    private SVGElement _checkbox3_check;
    private SVGElement _checkbox3_text;
    private SVGElement _checkbox4;
    private SVGElement _checkbox4_check;
    private SVGElement _checkbox4_text;
    private SVGElement _checkbox5;
    private SVGElement _checkbox5_check;
    private SVGElement _checkbox5_text;
    private SVGElement _checkbox6;
    private SVGElement _checkbox6_check;
    private SVGElement _checkbox6_text;
    private SVGElement _checkbox7;
    private SVGElement _checkbox7_check;
    private SVGElement _checkbox7_text;
        
    
    
    // Textbox panel elements
    private SVGElement _textbox;
    private SVGElement _textbox_cursor;
    private SVGElement _textbox_text;
    
    // Contains the textfield helper class
    private SvgTextFieldHelper _svgTextFieldHelper;    
    
    // Contains the textbox contents
    private String _textboxText;    
    
    // Contains the textbox field cursor element 
    private SVGElement _textboxCursor;
    
    // Indicates if textbox is active or not
    private boolean _textFieldActive = false;        
    
    
    
    
    private SVGElement _combobox;
    private SVGElement _combobox_group;
    private SVGElement _combobox_downarrow;
    private SVGElement _combobox_uparrow;
    private SVGElement _combobox_currentItem;
    private SVGElement _combobox_currentItem_text;
    
    private SVGElement _combobox1;
    private SVGElement _combobox1_text;
    private SVGElement _combobox2;
    private SVGElement _combobox2_text;
    private SVGElement _combobox3;
    private SVGElement _combobox3_text;    
    
    // Combobox states
    private boolean _comboboxActivated = false;
    private boolean _comboboxModeNavigation = false;
    private SVGElement _firstComboboxItem, _lastComboboxItem;
    private SVGElement _currentComboboxItem;
    private SVGElement _currentComboboxItemText;   

    private SVGElement _button1;
    
    private SVGElement _dialogbox;
    private SVGElement _dialogboxbutton;
    private SVGElement _dialogboxbutton2;
    
    //private SVGElement _dialogbox_text1;
    //private SVGElement _dialogbox_text2;
    //private SVGElement _dialogbox_text3;
    public SVGElement _dialogbox_text1;
    public SVGElement _dialogbox_text2;
    public SVGElement _dialogbox_text3;
    public SVGElement _dialogbox_text4;
    
    // Stores the Checkbox states
    private Vector _checkBoxVector1;   
    private Vector _checkBoxVector2;
    
    /**
     * Constructs a new ButtonPanel
     * @param svgFormsScreen The applications's main screen
     */
    ButtonPanel(SVGFormsScreen svgFormsScreen)
    {
        super(svgFormsScreen);
        this.initializeButtons();
        this.activateButtons();
        this.populateVectors();
        super.setFirstElement(_checkbox1);
        super.setLastElement(_button1);
        
        
        this.setFirstComboboxElement(_combobox1);
        this.setLastComboboxElement(_combobox3);
        _currentComboboxItem = this.getFirstComboboxElement();
        //this.setTextBoxElement1(_textbox_cursor);
        
        // Textfield helper class is associated with the text panel
        _svgTextFieldHelper = new SvgTextFieldHelper(svgFormsScreen, this);        
    }
    
    /**
     * Initializes the DOM elements
     */
    private void initializeButtons()
    {
        _checkbox1 = (SVGElement)_svgFormsScreen.getElementById("checkbox1");
        _checkbox1_check = (SVGElement)_svgFormsScreen.getElementById("check1");
        _checkbox1_text = (SVGElement)_svgFormsScreen.getElementById("checkboxtext1");
        
        _checkbox2 = (SVGElement)_svgFormsScreen.getElementById("checkbox2");
        _checkbox2_check = (SVGElement)_svgFormsScreen.getElementById("check2");
        _checkbox2_text = (SVGElement)_svgFormsScreen.getElementById("checkboxtext2");

        _checkbox3 = (SVGElement)_svgFormsScreen.getElementById("checkbox3");
        _checkbox3_check = (SVGElement)_svgFormsScreen.getElementById("check3");
        _checkbox3_text = (SVGElement)_svgFormsScreen.getElementById("checkboxtext3");
        
        _checkbox4 = (SVGElement)_svgFormsScreen.getElementById("checkbox4");
        _checkbox4_check = (SVGElement)_svgFormsScreen.getElementById("check4");
        _checkbox4_text = (SVGElement)_svgFormsScreen.getElementById("checkboxtext4");
        
        _checkbox5 = (SVGElement)_svgFormsScreen.getElementById("checkbox5");
        _checkbox5_check = (SVGElement)_svgFormsScreen.getElementById("check5");
        _checkbox5_text = (SVGElement)_svgFormsScreen.getElementById("checkboxtext5");
        
        _checkbox6 = (SVGElement)_svgFormsScreen.getElementById("checkbox6");
        _checkbox6_check = (SVGElement)_svgFormsScreen.getElementById("check6");
        _checkbox6_text = (SVGElement)_svgFormsScreen.getElementById("checkboxtext6");
        
        _checkbox7 = (SVGElement)_svgFormsScreen.getElementById("checkbox7");
        _checkbox7_check = (SVGElement)_svgFormsScreen.getElementById("check7");
        _checkbox7_text = (SVGElement)_svgFormsScreen.getElementById("checkboxtext7");        
            
            
        _textbox = (SVGElement)_svgFormsScreen.getElementById("textbox");
        _textbox_cursor = (SVGElement)_svgFormsScreen.getElementById("textboxcursor");
        _textbox_text = (SVGElement)_svgFormsScreen.getElementById("textboxtext");    
            
        
        _combobox = (SVGElement)_svgFormsScreen.getElementById("combobox");
        _combobox_group = (SVGElement)_svgFormsScreen.getElementById("comboboxgroup");
        _combobox_downarrow = (SVGElement)_svgFormsScreen.getElementById("comboboxarrowdown");
        _combobox_uparrow = (SVGElement)_svgFormsScreen.getElementById("comboboxarrowup");
        _combobox_currentItem = (SVGElement)_svgFormsScreen.getElementById("currentcomboboxitem");
        _combobox_currentItem_text = (SVGElement)_svgFormsScreen.getElementById("currentcomboboxitemtext");
        
        _combobox1 = (SVGElement)_svgFormsScreen.getElementById("comboboxrect1");
        _combobox1_text = (SVGElement)_svgFormsScreen.getElementById("comboboxitemtext1");
        _combobox2 = (SVGElement)_svgFormsScreen.getElementById("comboboxrect2");
        _combobox2_text = (SVGElement)_svgFormsScreen.getElementById("comboboxitemtext2");
        _combobox3 = (SVGElement)_svgFormsScreen.getElementById("comboboxrect3");
        _combobox3_text = (SVGElement)_svgFormsScreen.getElementById("comboboxitemtext3");  
        
                        
        _button1 = (SVGElement)_svgFormsScreen.getElementById("button1");
        
        _dialogbox = (SVGElement)_svgFormsScreen.getElementById("dialogbox");
        _dialogboxbutton = (SVGElement)_svgFormsScreen.getElementById("dialogboxbutton");
        
        _dialogboxbutton2 = (SVGElement)_svgFormsScreen.getElementById("dialogboxbutton2");
        
        _dialogbox_text1 = (SVGElement)_svgFormsScreen.getElementById("dialogboxtext1");
        _dialogbox_text2 = (SVGElement)_svgFormsScreen.getElementById("dialogboxtext2");
        _dialogbox_text3 = (SVGElement)_svgFormsScreen.getElementById("dialogboxtext3");
        _dialogbox_text4 = (SVGElement)_svgFormsScreen.getElementById("dialogboxtext4");
    }
    
    /**
     * Populates checkbox and radiobutton states in the corresponding vectors
     */
     private void populateVectors()
     {
        _checkBoxVector1 = new Vector();
        _checkBoxVector1.addElement(_checkbox1_check);
        _checkBoxVector1.addElement(_checkbox2_check);
        
        _checkBoxVector2 = new Vector();
        _checkBoxVector2.addElement(_checkbox3_check);
        _checkBoxVector2.addElement(_checkbox4_check);
        _checkBoxVector2.addElement(_checkbox5_check);
        _checkBoxVector2.addElement(_checkbox6_check);
        _checkBoxVector2.addElement(_checkbox7_check);        
     }    
    
   /**
    * Activates buttons and check boxes
    */
    private void activateButtons()
    {
        // Activate the buttons and add the check elements in the corresponding vectors(if applicable)
        activateSVGElement(_checkbox1, this);
        activateSVGElement(_checkbox2, this);
        activateSVGElement(_checkbox3, this);
        activateSVGElement(_checkbox4, this);
        activateSVGElement(_checkbox5, this);
        activateSVGElement(_checkbox6, this);
        activateSVGElement(_checkbox7, this);
        
        
        activateSVGElement(_textbox, this);
        
        activateSVGElement(_combobox, this);
        
        
        activateSVGElement(_combobox1, this);
        activateSVGElement(_combobox2, this);
        activateSVGElement(_combobox3, this);
        
        
        activateSVGElement(_button1, this);
    }    
    
    
     /**
     * Registers the events associated with the combobox elements
     */
    private void activateComboboxBox()
    {
        _comboboxActivated = true;
        activateSVGElement(_combobox1, this);
        activateSVGElement(_combobox2, this);
        activateSVGElement(_combobox3, this);
    }    
   
    /**
     * Un-Registers the events associated with the combobox elements
     */
    private void deActivateComboboxBox()
    {
        _comboboxActivated = false;
        deActivateSVGElement(_combobox1, this);
        deActivateSVGElement(_combobox2, this);
        deActivateSVGElement(_combobox3, this);
    }    
    
    
    /**
     * Returns the status of the combobox
     * @return True if the combo box is activated, otherwise false
     */
    boolean isComboboxActivated()
    {
        return this._comboboxActivated;
    } 
    
    /**
     * Sets the first focusable combobox item
     * @param svgElement The combobox item to be set
     */
    private void setFirstComboboxElement(SVGElement svgElement)
    {
        _firstComboboxItem = svgElement;
    }    
    
    /**
     * Sets the last focusable combobox item
     * @param svgElement The combobox item to be set
     */
    private void setLastComboboxElement(SVGElement svgElement)
    {
        _lastComboboxItem = svgElement;
    }
    
    
    /**
     * Returns the first combobox element
     * @return The first combobox element
     */
    SVGElement getFirstComboboxElement()
    {
        return _firstComboboxItem;
    }    
    
    /**
     * Returns the last combobox element
     * @return The last combobox element
     */
    SVGElement getLastComboboxElement()
    {
        return _lastComboboxItem;
    }    
    
    
    /**
     * Turns combobox mode navigation on
     */
    void comboboxModeNavigationOn()
    {
        _comboboxModeNavigation = true;
    }    
    
    /**
     * Turn combobox mode navigation off
     */
    void comboboxModeNavigationOff()
    {
        _comboboxModeNavigation = false;
    }    
    
    /**
     * Checks if combobox navigation is on.
     * @return True is combobox navigation is turned on, otherwise false
     */
    boolean inComboboxModeNavigation()
    {
        return _comboboxModeNavigation;
    }    
    
     /**
     * Checks if the current element is a combobox element
     * @return True if current element is a combobox element, otherwise false
     */
    boolean isComboboxElement()
    {
        Node currentNode = _currentFocusInElement.getParentNode();
        Node comboboxsubgroupNode = currentNode.getParentNode();
        if ( comboboxsubgroupNode.getParentNode() == (Node)_combobox)
        {
            return true;
        }
        else
        { 
            return false;
        }
    }
    
   /* 
    private void setTextBoxElement(SVGElement svgElement)
    {
        _firstTextBoxText = svgElement;
    }
   */
        
    /**
     * Checks if the text box is active
     * @return True if the text box is active, otherwise false
     */
    boolean isTextBoxActive()
    {
        return _textFieldActive;
    }      
    
    
    /**
     * Adds the svgTextField that remains in the background
     */
    void addTextField()
    {
        _svgTextFieldHelper.addTextField();
        _textFieldActive = true;
    }    
    
    /**
     * Removes the text field
     */
    void removeTextField()
    {
         _svgTextFieldHelper.removeTextField();
        _svgFormsScreen.setFocus(this._currentClickedElement);
        _textFieldActive = false;
    }     
    
    
   /**
     * Sets the text of the textbox element
     * @param text The text to display in the textbox
     */
    void setText(String text)
    {
        _textboxText = text;
        _animator.invokeLater(new Runnable()
        {
            public void run()
            {
                if(_textbox_cursor.getTrait("display").equals("none"))
                {
                    _textbox_cursor.setTrait("display", "inline");
                }
                _textbox_text.setTrait("#text", _textboxText);
                
                try
                {
                    // Obtain the font object corresponding to the text element
                    FontFamily family = FontFamily.forName("BBMillbank");
                    Font font = family.getFont(Font.PLAIN, 15, Ui.UNITS_px);
                    
                    // Set the X and Y co-ordinates of the cursor rect
                    //_textbox_cursor.setFloatTrait("x", (Display.getWidth() / 2 - 83 + font.getAdvance(_textboxText)));
                    _textbox_cursor.setFloatTrait("x", (Display.getWidth() / 2 - 123 + font.getAdvance(_textboxText)));
                    //_textbox_cursor.setFloatTrait("y", 144.0f);
                    _textbox_cursor.setFloatTrait("y", 164.0f);
                }
                catch(ClassNotFoundException cnfe)
                {
                    System.out.println(cnfe.toString());
                }
            }
        });
    }
        
   /**
    * Handles the DOMActivate event 
    * @param evt The event to be handled
    */
    public void handleEvent(Event evt)
    {        
        // Help avoid bubbled event handling
        if (_currentEvent.equals(evt.getType())) 
        {
            return;
        }
        else 
        {
            _currentEvent = evt.getType();
        }
    

        // Handle the DOMFocusIn event.
        if(evt.getType().equals("DOMFocusIn"))
        {
            // Store the currently focused element
            _currentFocusInElement = (SVGElement)evt.getCurrentTarget();
            setCurrentFocusElement(_currentFocusInElement);
             if(isComboboxActivated())
             {
                        _currentFocusInElement.setTrait("fill", "red");
             }
             else 
             {
                        _currentFocusInElement.setFloatTrait("stroke-opacity", 1.0f);
             }
            
        }
        else if(evt.getType().equals("DOMFocusOut"))
        {
            // Store the currently focused element
            _currentFocusInElement = (SVGElement)evt.getCurrentTarget();
            
             if(isComboboxActivated())
             {
                        _currentFocusOutElement.setTrait("fill", "#FF6600");
             }
             else 
             {
                        _currentFocusOutElement.setFloatTrait("stroke-opacity", 0.25f);
             }
            
        }
        // Handle the click event
        else if(evt.getType().equals("click"))
        {
            _currentClickedElement = (SVGElement)evt.getCurrentTarget();
            
           if(_currentClickedElement == _combobox)
            {
                _combobox_downarrow.setTrait("display", "none");
                _combobox_uparrow.setTrait("display", "inline");
                _combobox_currentItem.setTrait("display", "none");
                _combobox_group.setTrait("display", "inline");
                _animator.invokeLater(new Runnable()
                {
                    public void run()
                    {
                        _currentComboboxItem.setFloatTrait("fill-opacity", 0.5f);
                        activateComboboxBox();
                    }
                });
            }            
            else if(_currentClickedElement == _combobox1 || _currentClickedElement == _combobox2 || 
                        _currentClickedElement == _combobox3)
            {
                _animator.invokeLater(new Runnable()
                {
                    public void run()
                    {
                        if(_currentComboboxItem != _currentClickedElement)
                        {
                            _currentFocusInElement.setTrait("fill", "#FF6600");
                            _currentComboboxItem.setFloatTrait("stroke-opacity", 0.1f);
                            _currentComboboxItem.setFloatTrait("fill-opacity", 0.25f);
                            _currentComboboxItem = _currentClickedElement;
                            _currentComboboxItem.setFloatTrait("fill-opacity", 0.5f);
                        }
                        _combobox_downarrow.setTrait("display", "inline");
                        _combobox_uparrow.setTrait("display", "none");
                        _combobox_currentItem.setTrait("display", "inline");
                        _combobox_group.setTrait("display", "none");
                        
                        if(_currentClickedElement == _combobox1)
                        {
                            _currentComboboxItemText = _combobox1_text;
                        }
                        else if(_currentClickedElement == _combobox2)
                        {
                            _currentComboboxItemText = _combobox2_text;
                        }
                        else if(_currentClickedElement == _combobox3)
                        {
                            _currentComboboxItemText = _combobox3_text;
                        } 
                        
                        //(SVGElement)getElementById("currentcomboboxitemtext"); 
                        
                        _combobox_currentItem_text.setTrait("#text", _currentComboboxItemText.getTrait("#text"));
                        deActivateComboboxBox();     
                    }
                });
            }
            
            
            // Handle the "Submit" button click
            else if(_currentClickedElement == _button1)
            {
                activateSVGElement(_dialogboxbutton, this); // Ativate dialog button
                _svgFormsScreen.dialogboxMode(this, true); // Switch to dialog box mode
                
                _animator.invokeLater(new Runnable()
                {
                    public void run()
                    {
                        _svgFormsScreen.setFocus(null);
                        String items1 = "Crime(s) OR Violation(s): ";
                        String items2 = "Type: ";
                        for(int i = 0;i < _checkBoxVector1.size();i++)
                        {
                            SVGElement checkBox1 =(SVGElement)_checkBoxVector1.elementAt(i);
                            String checkTrait1 = checkBox1.getTrait("display");
                            if(checkTrait1.equals("inline"))
                            {
                                String itemName1 = "";
                                if(checkBox1 == _checkbox1_check)
                                {
                                    itemName1 = _checkbox1_text.getTrait("#text");
                                }
                                else if(checkBox1 == _checkbox2_check)
                                {
                                    itemName1 = _checkbox2_text.getTrait("#text");
                                }
                                items1 = items1 + itemName1 + " ";
                            }
                        }
                        for(int i = 0;i < _checkBoxVector2.size();i++)
                        {
                            SVGElement checkBox2 =(SVGElement)_checkBoxVector2.elementAt(i);
                            String checkTrait2 = checkBox2.getTrait("display");
                            if(checkTrait2.equals("inline"))
                            {
                                String itemName2 = "";
                                if(checkBox2 == _checkbox3_check)
                                {
                                    itemName2 = _checkbox3_text.getTrait("#text");
                                }
                                else if(checkBox2 == _checkbox4_check)
                                {
                                    itemName2 = _checkbox4_text.getTrait("#text");
                                }
                                else if(checkBox2 == _checkbox5_check)
                                {
                                    itemName2 = _checkbox5_text.getTrait("#text");
                                }
                                else if(checkBox2 == _checkbox6_check)
                                {
                                    itemName2 = _checkbox6_text.getTrait("#text");
                                }
                                else if(checkBox2 == _checkbox7_check)
                                {
                                    itemName2 = _checkbox7_text.getTrait("#text");
                                }
                                items2 = items2 + itemName2 + " ";
                            }
                        }                       
                        
                        _dialogbox.setTrait("display", "inline");
                        _dialogbox_text1.setTrait("#text", items1);
                        _dialogbox_text2.setTrait("#text", items2);
                        

                        String items3 = "City/Other Public Body: = ";
                       // _dialogbox.setTrait("display", "inline");
                        String itemName3 = "";   
                        itemName3 = _textbox_text.getTrait("#text");                     
                        items3 = items3 + itemName3;               
                        _dialogbox_text3.setTrait("#text", items3);
                        
                        
                        String items4 = "County: = ";
                        String itemName4 = "";
                        itemName4 = _combobox_currentItem_text.getTrait("#text") + "  ";
                        items4 = items4 + itemName4;
                        _dialogbox_text4.setTrait("#text", items4); 
                        
                    }
                });
            }
        /*
            else if ((_currentClickedElement.getId()).equals("textbox"))
            {
                if (_textFieldActive == false)
                {
                    addTextField();
                }
            }
        */    
            // Handle the dialog box button
            else if(_currentClickedElement == _dialogboxbutton)
            {
                deActivateSVGElement(_dialogboxbutton, this);     
                //deActivateSVGElement(_dialogboxbutton2, this);
                _svgFormsScreen.dialogboxMode(this, false);
                _animator.invokeLater(new Runnable()
                {
                    public void run()
                    {
                        _dialogbox.setTrait("display", "none");
                    }
                });
                  
                // show the 2nd screen... run later in EDT...
                 //UiApplication.getApplication().invokeLater(new Runnable(){
                  //public void run(){
                  //SVGFormsScreen2 screen2 = new SVGFormsScreen2(_dialogbox_text1, _dialogbox_text2, _dialogbox_text3, _dialogbox_text4);
                  //add(new RichTextField("About to show screen2 " ,Field.NON_FOCUSABLE)); 
                 
                  //UiApplication.getUiApplication().pushScreen(screen2);
                  //add(new RichTextField("Have already shown screen2 " ,Field.NON_FOCUSABLE)); 
                 
                 // }
                 //});
            }
            else if(_currentClickedElement == _dialogboxbutton2)
            {
                deActivateSVGElement(_dialogboxbutton2, this);     
                //deActivateSVGElement(_dialogboxbutton2, this);
                _svgFormsScreen.dialogboxMode(this, false);
                _animator.invokeLater(new Runnable()
                {
                    public void run()
                    {
                        _dialogbox.setTrait("display", "none");
                    }
                });
                  
                 // Display a farewell message before closing application.
                 Dialog.alert("Goodbye!");
                 System.exit(0);
        
                 //super.close();
            }
            
            // Handle the checkboxes and radiobutton events
            else
            {
                _animator.invokeLater(new Runnable()
                {
                    public void run()
                    {
                        if(_currentClickedElement == _checkbox1)
                        {
                            String checkBoxState = _checkbox1_check.getTrait("display");
                            if(checkBoxState.equals("none"))
                            {
                                _checkbox1_check.setTrait("display","inline");
                            }
                            else if(checkBoxState.equals("inline"))
                            {
                                _checkbox1_check.setTrait("display","none");
                            }                                        
                        }
                        else if(_currentClickedElement == _checkbox2)
                        {
                            String checkBoxState = _checkbox2_check.getTrait("display");
                            if(checkBoxState.equals("none"))
                            {
                                _checkbox2_check.setTrait("display","inline");
                            }
                            else if(checkBoxState.equals("inline"))
                            {
                                _checkbox2_check.setTrait("display","none");
                            }
                        }
                        else if(_currentClickedElement == _checkbox3)
                        {
                            String checkBoxState = _checkbox3_check.getTrait("display");
                            if(checkBoxState.equals("none"))
                            {
                                _checkbox3_check.setTrait("display","inline");
                            }
                            else if(checkBoxState.equals("inline"))
                            {
                                _checkbox3_check.setTrait("display","none");
                            }
                        }
                          else if(_currentClickedElement == _checkbox4)
                        {
                            String checkBoxState = _checkbox4_check.getTrait("display");
                            if(checkBoxState.equals("none"))
                            {
                                _checkbox4_check.setTrait("display","inline");
                            }
                            else if(checkBoxState.equals("inline"))
                            {
                                _checkbox4_check.setTrait("display","none");
                            }
                        }
                         else if(_currentClickedElement == _checkbox5)
                        {
                            String checkBoxState = _checkbox5_check.getTrait("display");
                            if(checkBoxState.equals("none"))
                            {
                                _checkbox5_check.setTrait("display","inline");
                            }
                            else if(checkBoxState.equals("inline"))
                            {
                                _checkbox5_check.setTrait("display","none");
                            }
                        } 
                        else if(_currentClickedElement == _checkbox6)
                        {
                            String checkBoxState = _checkbox6_check.getTrait("display");
                            if(checkBoxState.equals("none"))
                            {
                                _checkbox6_check.setTrait("display","inline");
                            }
                            else if(checkBoxState.equals("inline"))
                            {
                                _checkbox6_check.setTrait("display","none");
                            }
                        }  
                        else if(_currentClickedElement == _checkbox7)
                        {
                            String checkBoxState = _checkbox7_check.getTrait("display");
                            if(checkBoxState.equals("none"))
                            {
                                _checkbox7_check.setTrait("display","inline");
                            }
                            else if(checkBoxState.equals("inline"))
                            {
                                _checkbox7_check.setTrait("display","none");
                            }
                        }
                        

                         
                                                                  
                        else if ((_currentClickedElement.getId()).equals("textbox"))
                        {
                          if (_textFieldActive == false)
                          {
                            addTextField();
                          }
                        }
                    }
                });
            }
        }
    }
}

