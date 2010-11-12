package citation.ui;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.component.LabelField;

/**
 * The CitationLabelField extends a standard LabelField 
 * in order to provide custom graphic options such as
 * setting text color and background color
 * 
 * @author msmith
 */
public class CustomLabelField extends LabelField {

	private int backgroundColor = Color.WHITE;
	private int textColor = Color.BLACK;
	
	public CustomLabelField(Object _text, long _style)
	{
		super(_text, _style);
	}
	
	/**
	 * Custom paintBackground that sets the background color and font
	 */
    protected void paintBackground(net.rim.device.api.ui.Graphics g) 
    { 
    	g.clear(); 
    	g.getColor(); 
    	g.setColor(backgroundColor); 
    	g.fillRect(0, 0, Display.getWidth(), Display.getHeight()); 
    	g.setColor(textColor);                
    } 

    public void setBackgroundColor(int _color)
    {
    	backgroundColor = _color;
    }
    
    public void setBackgroundColor(String _color)
    {
    	backgroundColor = UIHelper.stringToColor(_color);
    }
    
    public void setTextColor(int _color)
    {
    	textColor = _color;
    }
    
    public void setTextColor(String _color)
    {
    	textColor = UIHelper.stringToColor(_color);
    }
}
