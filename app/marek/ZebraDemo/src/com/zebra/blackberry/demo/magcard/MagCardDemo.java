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

package com.zebra.blackberry.demo.magcard;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.FlowFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.zebra.blackberry.comm.ZebraPrinterConnectionException;
import com.zebra.blackberry.demo.ConnectionScreen;
import com.zebra.blackberry.printer.MagCardReader;
import com.zebra.blackberry.printer.ZebraPrinter;

/*
 * Magnetic Card Reader Demo
 * This demo shows how to read magnetic card data from a printer's magnetic card reader.
 * 
 */
public class MagCardDemo {

    private ConnectionScreen screen;
    private ZebraPrinter printer;
    private LabelField trackField1;
    private LabelField trackField2;
    private LabelField trackField3;

    public void displayMagCardDemo() {
        screen = new ConnectionScreen();
        screen.setTitle("MagCard Demo");

        FlowFieldManager buttonManager = new FlowFieldManager();
        ButtonField connectButton = new ButtonField("Connect", ButtonField.CONSUME_CLICK);
        buttonManager.add(connectButton);

        ButtonField testMagCardButton = new ButtonField("Read MagCard", ButtonField.CONSUME_CLICK);
        buttonManager.add(testMagCardButton);

        ButtonField disconnectButton = new ButtonField("Disconnect", ButtonField.CONSUME_CLICK);
        buttonManager.add(disconnectButton);

        screen.add(buttonManager);

        connectButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        printer = screen.connect();
                    }
                });
                t.start();
            }
        });

        testMagCardButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        doReadMagCard();
                    }
                });
                t.start();
            }
        });

        disconnectButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        screen.disconnect();
                    }
                });
                t.start();
            }
        });

        VerticalFieldManager dataManager = new VerticalFieldManager();

        trackField1 = new LabelField("T1: ");
        trackField2 = new LabelField("T2: ");
        trackField3 = new LabelField("T3: ");
        dataManager.add(trackField1);
        dataManager.add(trackField2);
        dataManager.add(trackField3);

        screen.add(dataManager);

        UiApplication.getUiApplication().pushScreen(screen);
    }

    private void doReadMagCard() {

        UiApplication.getUiApplication().invokeAndWait(new Runnable() {
            public void run() {
                trackField1.setText("T1: ");
                trackField2.setText("T2: ");
                trackField3.setText("T3: ");
            }
        });

        try {
            MagCardReader mcr = printer.getMagCardReader();
            if (mcr != null) {
                screen.setStatus("Waiting for swipe...", Color.GOLD);
                final String[] trackData = mcr.read(10 * 1000);
                UiApplication.getUiApplication().invokeAndWait(new Runnable() {
                    public void run() {
                        trackField1.setText("T1: " + trackData[0]);
                        trackField2.setText("T2: " + trackData[1]);
                        trackField3.setText("T3: " + trackData[2]);
                    }
                });
                screen.setStatus("Done", Color.BLUE);
            } else {
                screen.setStatus("Error", Color.RED);
            }
        } catch (ZebraPrinterConnectionException e1) {
            screen.setStatus("Communication Error! Disconnected", Color.RED);
        }

    }

}
