package citation.print;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ObjectListField;

public class CustomObjectListField extends ObjectListField {

    /* 
     * overwriting paint as a convenience method to
     * alternate the color of each row
     */
    protected void paint(Graphics graphics) {
        graphics.clear();
        graphics.setColor(Color.BLACK);
        graphics.drawRoundRect(0, 0, (this.getWidth()), (this.getHeight()), 10, 10);

        for (int i = 0; i < getSize(); i++) {
            if (i % 2 == 0) {
                graphics.setColor(Color.WHITE);
            } else {
                graphics.setColor(Color.LIGHTGREY);
            }
            graphics.setGlobalAlpha(100);
            graphics.fillRect(0, (getRowHeight() * i), this.getWidth() - 2, getRowHeight());
            graphics.drawRect(0, (getRowHeight() * i), this.getWidth() - 2, getRowHeight());
        }

        graphics.setGlobalAlpha(255);
        graphics.setColor(Color.BLACK);

        super.paint(graphics);
    }
}
