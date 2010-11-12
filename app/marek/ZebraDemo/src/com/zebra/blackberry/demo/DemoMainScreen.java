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

import net.rim.device.api.ui.container.MainScreen;

/**
 * The Main Screen for the Zebra API demos. This will be used as the main screen for each of the demos.
 */
public class DemoMainScreen extends MainScreen {

    public DemoMainScreen() {
        super();
    }

    public DemoMainScreen(long style) {
        super(style);
    }

    /**
     * Override MainScreen.onSavePrompt to avoid the "save discard cancel" dialog when exiting the demos.
     * 
     * @see net.rim.device.api.ui.container.MainScreen#onSavePrompt()
     */
    protected boolean onSavePrompt() {

        return true;

    }

}
