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

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.container.FlowFieldManager;
import net.rim.device.api.ui.container.MainScreen;

import com.zebra.blackberry.demo.connectivity.ConnectivityDemo;
import com.zebra.blackberry.demo.discovery.DiscoveryDemo;
import com.zebra.blackberry.demo.formatlist.FormatListDemo;
import com.zebra.blackberry.demo.imageprint.ImagePrintDemo;
import com.zebra.blackberry.demo.magcard.MagCardDemo;
import com.zebra.blackberry.demo.readytoprint.ReadyToPrintDemo;
import com.zebra.blackberry.demo.sendfile.SendFileDemo;
import com.zebra.blackberry.demo.storedformat.StoredFormatDemo;

public class Application extends UiApplication {

    public static void main(String[] args) {
        new Application().enterEventDispatcher();
    }

    public Application() {
        MainScreen screen = new DemoMainScreen();
        screen.setTitle("ZebraLink SDK Demos");
        this.pushScreen(screen);

        FlowFieldManager buttonManager = new FlowFieldManager();
        ButtonField connectivityButton = new ButtonField("Connectivity Demo", ButtonField.CONSUME_CLICK);
        buttonManager.add(connectivityButton);

        ButtonField discoveryButton = new ButtonField("Discovery Demo", ButtonField.CONSUME_CLICK);
        buttonManager.add(discoveryButton);

        ButtonField listFormatsButton = new ButtonField("List Formats Demo", ButtonField.CONSUME_CLICK);
        buttonManager.add(listFormatsButton);

        ButtonField sendFileButton = new ButtonField("Send File Demo", ButtonField.CONSUME_CLICK);
        buttonManager.add(sendFileButton);

        ButtonField storedFormatButton = new ButtonField("Stored Format Demo", ButtonField.CONSUME_CLICK);
        buttonManager.add(storedFormatButton);

        ButtonField imagePrintButton = new ButtonField("Image Print Demo", ButtonField.CONSUME_CLICK);
        buttonManager.add(imagePrintButton);

        ButtonField readyToPrintButton = new ButtonField("Ready To Print Demo", ButtonField.CONSUME_CLICK);
        buttonManager.add(readyToPrintButton);

        ButtonField magCardButton = new ButtonField("MagCard Demo", ButtonField.CONSUME_CLICK);
        buttonManager.add(magCardButton);

        screen.add(buttonManager);

        final Application mainApp = this;
        connectivityButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                new ConnectivityDemo().displayBluetoothPairingDemo(mainApp);
            }
        });
        discoveryButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                new DiscoveryDemo().displayDiscoveryDemo(mainApp);
            }
        });

        listFormatsButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                new FormatListDemo().displayFormatListDemo(mainApp);
            }
        });

        sendFileButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                new SendFileDemo().displaySendFileDemo(mainApp);
            }
        });

        storedFormatButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                new StoredFormatDemo(mainApp).displayStoredFormatDemo();
            }
        });

        imagePrintButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                new ImagePrintDemo().displayImagePrintDemo(mainApp);
            }
        });

        readyToPrintButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                new ReadyToPrintDemo().displayReadyToPrintDemo(mainApp);
            }
        });

        magCardButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                new MagCardDemo().displayMagCardDemo();
            }
        });
    }

}
