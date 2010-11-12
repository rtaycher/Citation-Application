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
