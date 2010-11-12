/**********************************************
 * CONFIDENTIAL AND PROPRIETARY
 *  
 * The information contained herein is the confidential and the exclusive property of
 * ZIH Corp. This document, and the information contained herein, shall not be copied, reproduced, published,
 * displayed or distributed, in whole or in part, in any medium, by any means, for any purpose without the express
 * written consent of ZIH Corp. 
 * 
 * Copyright ZIH Corp. 2009 
 * 
 * ALL RIGHTS RESERVED
 ***********************************************/

package com.zebra.blackberry.demo;

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
