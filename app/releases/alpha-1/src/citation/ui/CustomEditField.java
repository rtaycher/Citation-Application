package citation.ui;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.EditField;

public class CustomEditField extends EditField {
	
    private int iRectX = getFont().getAdvance(getLabel());
    private int iRectWidth = Display.getWidth() - iRectX - 4;
    private int iRectHeight = 20;
    
    public CustomEditField(String _label, String _initialValue)
    {
    	super(_label, _initialValue);
    }

    public CustomEditField(String _label, String _initialValue, int _max_char, int _height, long _style)
    {
    	super(_label, _initialValue, _max_char, _style);
    	iRectHeight = _height;
    }
    
    public int getPreferredWidth() {
        return Display.getWidth();
   }

    public int getPreferredHeight() {
        return iRectHeight;
    }

    public void layout(int width, int height) {
        super.layout(width, getPreferredHeight());
        setExtent(width, getPreferredHeight());
    }
            
    public void paint(Graphics g) {
        g.setColor(0x000000);
        g.drawRect(iRectX, 0, iRectWidth, iRectHeight);
        super.paint(g);
    }
}
