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

package com.zebra.blackberry.demo.connectivity;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.container.FlowFieldManager;

import com.zebra.blackberry.comm.ZebraPrinterConnectionException;
import com.zebra.blackberry.demo.ConnectionScreen;
import com.zebra.blackberry.demo.util.DemoSleeper;
import com.zebra.blackberry.printer.PrinterLanguage;
import com.zebra.blackberry.printer.ZebraPrinter;

/*
 * 
 * Connectivity Demo
 * The purpose of this demo program is to demonstrate how to connect with
 * a Bluetooth or Wireless printer. The program will do the following:
 * 1. Depending on which radio button is selected, will establish a connection to a Zebra printer
 * 1a. If bluetooth, will connect via the MAC address specified by the user
 * 1b. If Wi-Fi, will attempt to connect via the BlackBerry(R) Mobile Data Service. If connection fails will retry with direct Wi-Fi (JDE 4.6.0 or higher)
 * 2. Will interrogate the printer for the printer control language
 * 3. Will send a test label (based on the printer control language) to the printer
 * 4. Will disconnect from the printer
 * 
 */

public class ConnectivityDemo {
    private ConnectionScreen screen;
    private ZebraPrinter printer;

    public void displayBluetoothPairingDemo(UiApplication application) {
        screen = new ConnectionScreen();
        screen.setTitle("Connectivity Demo");

        FlowFieldManager buttonManager = new FlowFieldManager(Field.FIELD_HCENTER);
        ButtonField testButton = new ButtonField("Test", ButtonField.CONSUME_CLICK);
        buttonManager.add(testButton);

        screen.add(buttonManager);

        testButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        doConnectionTest();
                    }
                });
                t.start();
            }
        });

        application.pushScreen(screen);
    }

    private void doConnectionTest() {
        printer = screen.connect();
        if (printer != null) {
            sendTestLabel();
        }
    }

    private void sendTestLabel() {
        try {
            byte[] configLabel = getConfigLabel();
            screen.getConnection().write(configLabel);
            screen.setStatus("Sending Data", Color.BLUE);
            DemoSleeper.sleep(2000);
        } catch (ZebraPrinterConnectionException e) {
            screen.setStatus(e.getMessage(), Color.RED);
        } finally {
            screen.disconnect();
        }
    }

    /*
     * Returns the command for a test label depending on the printer control language
     * The test label is a box with the word "TEST" inside of it
     * 
     * _________________________
     * |                       |
     * |                       |
     * |        TEST           |
     * |                       |
     * |                       |
     * |_______________________|
     * 
     * 
     */
    private byte[] getConfigLabel() {
        PrinterLanguage printerLanguage = printer.getPrinterControlLanguage();

        byte[] configLabel = null;
        if (printerLanguage == PrinterLanguage.ZPL) {
            configLabel = "^XA^FO17,16^GB379,371,8^FS^FT65,255^A0N,135,134^FDTEST^FS^XZ".getBytes();
        } else if (printerLanguage == PrinterLanguage.CPCL) {
            String cpclConfigLabel = "! 0 200 200 406 1\r\n" + "ON-FEED IGNORE\r\n"
                    + "BOX 20 20 380 380 8\r\n"
                    + "T 0 6 137 177 TEST\r\n"
                    + "PRINT\r\n";
            configLabel = cpclConfigLabel.getBytes();
        }
        return configLabel;
    }
}