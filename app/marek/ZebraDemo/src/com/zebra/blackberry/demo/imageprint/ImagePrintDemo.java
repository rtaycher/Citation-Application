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

package com.zebra.blackberry.demo.imageprint;

import net.rim.blackberry.api.invoke.CameraArguments;
import net.rim.blackberry.api.invoke.Invoke;
import net.rim.device.api.io.file.FileSystemJournal;
import net.rim.device.api.io.file.FileSystemJournalEntry;
import net.rim.device.api.io.file.FileSystemJournalListener;
import net.rim.device.api.system.Application;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.container.FlowFieldManager;

import com.zebra.blackberry.comm.ZebraPrinterConnectionException;
import com.zebra.blackberry.demo.ConnectionScreen;
import com.zebra.blackberry.printer.ZebraPrinter;

/*
 * 
 */

public class ImagePrintDemo implements FileSystemJournalListener {
    private ZebraPrinter printer;
    private ConnectionScreen screen;

    public void displayImagePrintDemo(UiApplication application) {
        Application.getApplication().addFileSystemJournalListener(this);
        screen = new ConnectionScreen() {
            protected void onUndisplay() {
                Application.getApplication().removeFileSystemJournalListener(ImagePrintDemo.this);
                super.onUndisplay();
            }
        };
        screen.setTitle("Image Print Demo");

        FlowFieldManager buttonManager = new FlowFieldManager(Field.FIELD_HCENTER);
        ButtonField testButton = new ButtonField("Capture Image", ButtonField.CONSUME_CLICK);
        buttonManager.add(testButton);

        ButtonField disconnectButton = new ButtonField("Disconnect", ButtonField.CONSUME_CLICK);
        buttonManager.add(disconnectButton);

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

    private void doConnectionTest() {
        printer = screen.connect();
        if (printer != null) {
            launchCamera();
        }
    }

    private void launchCamera() {
        Invoke.invokeApplication(Invoke.APP_TYPE_CAMERA, new CameraArguments());
    }

    private synchronized void print(String filePath) {
        try {
            printer.getGraphicsUtil().printImage("file://" + filePath, 0, 0, 550, 412, false);
            screen.setStatus("Sent graphic", Color.GOLD);
        } catch (ZebraPrinterConnectionException e) {
            if (e.getMessage().startsWith("Unknown Printer Language")) {
                screen.setStatus(e.getMessage(), Color.RED);
            }
        }
    }

    private long _myStoredUSN = FileSystemJournal.getNextUSN() - 1;

    public void fileJournalChanged() {
        long nextUSN = FileSystemJournal.getNextUSN();
        for (long lookUSN = nextUSN - 1; lookUSN >= _myStoredUSN; lookUSN--) {
            FileSystemJournalEntry entry = FileSystemJournal.getEntry(lookUSN);
            if (entry == null) {
                // we didn't find an entry.
                break;
            }
            if (entry.getEvent() == FileSystemJournalEntry.FILE_ADDED) {
                final String path = entry.getPath();
                if (path.toUpperCase().endsWith(".JPG")) {
                    Thread t = new Thread(new Runnable() {
                        public void run() {
                            print(path);
                        }
                    });
                    t.start();
                }
            }
        }
        _myStoredUSN = nextUSN;
    }
}