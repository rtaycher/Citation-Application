package citation.ui;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.RichTextField;

class ExRichTextField extends RichTextField { 
	 
    int mTextColor; 
    int mBgColor; 

    public ExRichTextField(String text, int bgColor, int textColor) { 
            super(text); 
            mTextColor = textColor; 
            mBgColor = bgColor; 
    } 

    protected void paint(Graphics graphics) { 
            graphics.clear(); 
            graphics.setColor(mBgColor); 
            graphics.fillRect(0, 0, getWidth(), getHeight()); 
            graphics.setColor(mTextColor); 
            super.paint(graphics); 
    } 
}
