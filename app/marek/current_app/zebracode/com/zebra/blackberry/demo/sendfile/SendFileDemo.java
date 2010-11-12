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

package com.zebra.blackberry.demo.sendfile;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.container.FlowFieldManager;

import com.zebra.blackberry.comm.ZebraPrinterConnectionException;
import com.zebra.blackberry.demo.ConnectionScreen;
import com.zebra.blackberry.printer.PrinterLanguage;
import com.zebra.blackberry.printer.ZebraPrinter;

public class SendFileDemo {

    private ConnectionScreen screen;
    private ZebraPrinter printer;

    public void displaySendFileDemo(UiApplication application) {
        screen = new ConnectionScreen();
        screen.setTitle("Send File Demo");

        FlowFieldManager buttonManager = new FlowFieldManager();
        ButtonField connectButton = new ButtonField("Connect", ButtonField.CONSUME_CLICK);
        buttonManager.add(connectButton);

        ButtonField testListFormatsButton = new ButtonField("Test Send File", ButtonField.CONSUME_CLICK);
        buttonManager.add(testListFormatsButton);

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

        testListFormatsButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        testSendFile();
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

        application.pushScreen(screen);
    }

    private void testSendFile() {

        screen.setStatus("Sending File", Color.BLUE);
        try {
            String testFile = "file:///store/home/user/documents/TEST.LBL";
            createDemoFile(testFile);
            printer.getFileUtil().sendFileContents(testFile);
            screen.setStatus("Sent File", Color.BLUE);
        } catch (ZebraPrinterConnectionException e1) {
            screen.setStatus("Communication Error! Disconnected", Color.RED);
        } catch (IOException e) {
            screen.setStatus("File IO Error: " + e.getMessage(), Color.RED);
        }
    }

    private void createDemoFile(String fileName) throws IOException, ZebraPrinterConnectionException {
        FileConnection fileConnector = (FileConnection) Connector.open(fileName);

        if (fileConnector.exists() == false) {
            fileConnector.create();
        } else {
            fileConnector.truncate(0);
        }

        DataOutputStream os = fileConnector.openDataOutputStream();

        byte[] configLabel = null;

        PrinterLanguage pl = printer.getPrinterControlLanguage();
        if (pl == PrinterLanguage.ZPL) {
            configLabel = "^XA^FO17,16^GB379,371,8^FS^FT65,255^A0N,135,134^FDTEST^FS^XZ".getBytes();
        } else if (pl == PrinterLanguage.CPCL) {
            String cpclConfigLabel = "! 0 200 200 406 1\r\n" + "ON-FEED IGNORE\r\n"
                    + "BOX 20 20 380 380 8\r\n"
                    + "T 0 6 137 177 TEST\r\n"
                    + "PRINT\r\n";
            configLabel = cpclConfigLabel.getBytes();
        }
        os.write(configLabel);
        os.flush();
        os.close();
    }
}