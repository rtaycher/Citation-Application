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
import net.rim.device.api.ui.component.BasicEditField;

public class EnablableBasicEditField extends BasicEditField {

    private boolean enabled = true;

    public EnablableBasicEditField(String string, String string2, int i, long filterDefault) {
        super(string, string2, i, filterDefault);
    }

    protected void paint(Graphics graphics) {
        graphics.clear();
        if (enabled) {
            graphics.setColor(Color.BLACK);
        } else {
            graphics.setColor(Color.GRAY);
        }
        super.paint(graphics);
    }

    public void repaint() {
        invalidate();
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        setEditable(this.enabled);
        repaint();
    }
}
