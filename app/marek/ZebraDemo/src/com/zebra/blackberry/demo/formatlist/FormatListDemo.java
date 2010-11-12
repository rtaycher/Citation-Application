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

package com.zebra.blackberry.demo.formatlist;

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
import com.zebra.blackberry.demo.CustomObjectListField;
import com.zebra.blackberry.demo.util.DemoSleeper;
import com.zebra.blackberry.printer.PrinterLanguage;
import com.zebra.blackberry.printer.ZebraIllegalArgumentException;
import com.zebra.blackberry.printer.ZebraPrinter;

public class FormatListDemo {

    private CustomObjectListField formats;
    private ConnectionScreen screen;
    private ZebraPrinter printer;

    public void displayFormatListDemo(UiApplication application) {
        screen = new ConnectionScreen();
        screen.setTitle("List Formats Demo");

        FlowFieldManager buttonManager = new FlowFieldManager();
        ButtonField connectButton = new ButtonField("Connect", ButtonField.CONSUME_CLICK);
        buttonManager.add(connectButton);

        ButtonField testListFilesButton = new ButtonField("Test List Files", ButtonField.CONSUME_CLICK);
        buttonManager.add(testListFilesButton);

        ButtonField testListFormatsButton = new ButtonField("Test List Formats", ButtonField.CONSUME_CLICK);
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

        testListFilesButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        testListFormats(screen, false);
                    }
                });
                t.start();
            }
        });

        testListFormatsButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        testListFormats(screen, true);
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

        {
            VerticalFieldManager formatListManager = new VerticalFieldManager();

            LabelField formatListLabel = new LabelField();
            formatListLabel.setText("Formats:");
            formatListManager.add(formatListLabel);

            formats = new CustomObjectListField();
            formatListManager.add(formats);
            formatListManager.add(new LabelField());

            formats.setChangeListener(new FieldChangeListener() {
                public void fieldChanged(Field field, int context) {
                    formats.invalidate(); // forces repainting
                }
            });

            screen.add(formatListManager);

        }
        application.pushScreen(screen);
    }

    private void testListFormats(ConnectionScreen screen, boolean filterFormats) {

        screen.setStatus("Clearing List", Color.BLUE);
        DemoSleeper.sleep(500);
        UiApplication.getUiApplication().invokeAndWait(new Runnable() {
            public void run() {
                while (formats.getSize() > 0) {
                    formats.delete(0);
                }
            }
        });

        try {
            final String[] files;

            if (filterFormats) {
                String[] extensions;
                PrinterLanguage printerLang = printer.getPrinterControlLanguage();
                if (printerLang == PrinterLanguage.CPCL) {
                    extensions = new String[] { "FMT",
                            "LBL" };
                } else if (printerLang == PrinterLanguage.ZPL) {
                    extensions = new String[] { "ZPL" };
                } else {
                    extensions = new String[] {};
                }
                files = printer.getFileUtil().retrieveFileNames(extensions);
                screen.setStatus("Found " + files.length
                        + " "
                        + printerLang
                        + " formats", Color.GREEN);
            } else {
                files = printer.getFileUtil().retrieveFileNames();
                screen.setStatus("Found " + files.length
                        + " files", Color.GREEN);
            }
            UiApplication.getUiApplication().invokeLater(new Runnable() {
                public void run() {
                    for (int i = 0; i < files.length; i++) {
                        formats.insert(i, files[i]);
                    }
                }
            });
        } catch (ZebraPrinterConnectionException e1) {
            screen.setStatus("Communication Error! Disconnected", Color.RED);
        } catch (ZebraIllegalArgumentException e2) {
            screen.setStatus("Error Listing Formats", Color.RED);
        }
    }
}