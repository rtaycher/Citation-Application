package citation.print;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.LabelField;

public class ColorfulLabelField extends LabelField {

    private int color;

    public ColorfulLabelField(final int initialColor) {
        this.color = initialColor;
    }

    public void repaint(final int color) {
        this.color = color;
        invalidate();

    }

    protected void paint(Graphics graphics) {
        graphics.setColor(this.color);
        graphics.fillRect(0, 0, 860, 100);
        graphics.drawRect(0, 0, 860, 100);
        graphics.setColor(Color.BLACK);
        super.paint(graphics);
    }
}
