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

package com.zebra.blackberry.demo.storedformat;

import java.io.UnsupportedEncodingException;

import net.rim.device.api.collection.util.SparseList;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.FlowFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.zebra.blackberry.comm.ZebraPrinterConnectionException;
import com.zebra.blackberry.demo.ConnectionScreen;
import com.zebra.blackberry.demo.CustomObjectListField;
import com.zebra.blackberry.demo.DemoMainScreen;
import com.zebra.blackberry.demo.util.DemoSleeper;
import com.zebra.blackberry.printer.FieldDescriptionData;
import com.zebra.blackberry.printer.ZebraIllegalArgumentException;
import com.zebra.blackberry.printer.ZebraPrinter;
import com.zebra.blackberry.util.CollectionProvider;
import com.zebra.blackberry.util.LongMap;

/*
 * 
 * Stored Format Demo
 * This demo shows how to do the following:
 * 1. Retrieve list of formats on the printer
 * 2. Retrieve list of variable fields in a format
 * 3. Print a format using variable data
 * 
 */

public class StoredFormatDemo {
    private ConnectionScreen screen;
    private ListFormatsScreen listFormatsScreen;
    private FormatScreen formatScreen;
    private ResultsScreen resultsScreen;
    private ZebraPrinter printer;
    private final UiApplication parentApp;

    public StoredFormatDemo(UiApplication application) {
        screen = new ConnectionScreen();
        listFormatsScreen = new ListFormatsScreen();
        formatScreen = new FormatScreen();
        resultsScreen = new ResultsScreen();
        parentApp = application;
    }

    private String[] filesOnPrinter = null;

    public void displayStoredFormatDemo() {
        screen.setTitle("Stored Format Demo - Connect");

        FlowFieldManager buttonManager = new FlowFieldManager(Field.FIELD_HCENTER);
        screen.add(buttonManager);

        ButtonField listFormatsButton = new ButtonField("List Formats", ButtonField.CONSUME_CLICK);
        listFormatsButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        filesOnPrinter = retrieveFilesFromPrinter();
                        UiApplication.getUiApplication().invokeAndWait(new Runnable() {
                            public void run() {
                                if (filesOnPrinter != null) {
                                    listFormatsScreen.populateFilesFromPrinter();
                                    parentApp.pushScreen(listFormatsScreen);
                                }
                            }
                        });
                    }
                });
                t.start();
            }
        });
        buttonManager.add(listFormatsButton);

        parentApp.pushScreen(screen);
    }

    private String[] retrieveFilesFromPrinter() {
        printer = screen.connect();
        if (screen.getConnection().isConnected()) {
            return getListOfFilesOnPrinter();
        }
        return null;
    }

    private void displayResultsScreen() {
        parentApp.pushScreen(resultsScreen);
    }

    private void printFormat(String formatName, LongMap vars, int printQuantity) {
        try {
            for (int i = 0; i < printQuantity; i++) {
                printer.getFormatUtil().printStoredFormat(formatName, vars, "utf8");
            }
            DemoSleeper.sleep(1000);
        } catch (ZebraPrinterConnectionException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private FieldDescriptionData[] getFormatFieldNames(String formatName) {
        FieldDescriptionData[] fieldNames = new FieldDescriptionData[] {};
        try {
            final byte[] formatData = printer.getFormatUtil().retrieveFormatFromPrinter(formatName);
            String formatString = new String(formatData);

            fieldNames = printer.getFormatUtil().getVariableFields(formatString);
        } catch (ZebraPrinterConnectionException e1) {
            e1.printStackTrace();
        }
        return fieldNames;
    }

    private String[] getListOfFilesOnPrinter() {
        String[] formatFiles = new String[] {};
        try {
            formatFiles = printer.getFileUtil().retrieveFileNames();
        } catch (ZebraPrinterConnectionException e1) {
            e1.printStackTrace();
        } catch (ZebraIllegalArgumentException e2) {
            e2.printStackTrace();
        }
        return formatFiles;
    }

    class ListFormatsScreen extends DemoMainScreen {
        private VerticalFieldManager formatListManager;
        private LabelField formatListLabel;
        private CustomObjectListField formatList;
        private FlowFieldManager buttonManager;
        private ButtonField refreshButton;
        private String formatName = null;
        private FieldDescriptionData[] varNames = null;

        public ListFormatsScreen() {
            super();

            setTitle("Stored Format Demo - List Formats");

            formatListManager = new VerticalFieldManager();
            add(formatListManager);

            formatListLabel = new LabelField();
            formatListLabel.setText("Select a Format:");
            formatListManager.add(formatListLabel);

            formatList = new CustomObjectListField() {
                protected boolean navigationClick(int status, int time) {
                    Thread t = new Thread(new Runnable() {
                        public void run() {
                            UiApplication.getUiApplication().invokeAndWait(new Runnable() {
                                public void run() {
                                    formatName = getSelectedFormatName();
                                }
                            });
                            if (formatName != null) {
                                varNames = getFormatFieldNames(formatName);
                            }
                            UiApplication.getUiApplication().invokeAndWait(new Runnable() {
                                public void run() {
                                    formatScreen.populate(formatName, varNames);
                                    parentApp.pushScreen(formatScreen);
                                }
                            });

                        }
                    });
                    t.start();

                    return true;
                }
            };
            formatList.setChangeListener(new FieldChangeListener() {
                public void fieldChanged(Field field, int context) {
                    formatList.invalidate(); // forces repainting
                }
            });
            formatListManager.add(formatList);

            buttonManager = new FlowFieldManager(Field.FIELD_HCENTER);
            add(buttonManager);

            refreshButton = new ButtonField("Refresh List", ButtonField.CONSUME_CLICK);
            refreshButton.setChangeListener(new FieldChangeListener() {
                public void fieldChanged(Field field, int context) {
                    Thread t = new Thread(new Runnable() {
                        public void run() {
                            filesOnPrinter = retrieveFilesFromPrinter();
                            UiApplication.getUiApplication().invokeAndWait(new Runnable() {
                                public void run() {
                                    if (filesOnPrinter != null) {
                                        populateFilesFromPrinter();
                                    }
                                }
                            });
                        }
                    });
                    t.start();
                }
            });
            buttonManager.add(refreshButton);
        }

        public void populateFilesFromPrinter() {
            while (formatList.getSize() > 0) {
                formatList.delete(0);
            }

            for (int i = 0; i < filesOnPrinter.length; i++) {
                formatList.insert(i, filesOnPrinter[i]);
            }
        }

        private String getSelectedFormatName() {
            if (formatList.getSelectedIndex() >= 0) {
                return formatList.get(formatList, formatList.getSelectedIndex()).toString();
            } else {
                return "";
            }
        }
    }

    class FormatScreen extends DemoMainScreen {
        private VerticalFieldManager rowManager;
        private String formatName;
        FieldDescriptionData[] fieldNames = null;
        private SparseList fieldsForVariables;
        private BasicEditField pqField;

        public FormatScreen() {
            super();

            setTitle("Stored Format Demo - Enter Data");

            rowManager = new VerticalFieldManager();
            add(rowManager);

        }

        public void populate(String theFormatName, FieldDescriptionData[] variableNames) {
            formatName = theFormatName;
            rowManager.deleteAll();

            LabelField formatLabel = new LabelField();
            formatLabel.setText("Format " + formatName
                    + ":");
            rowManager.add(formatLabel);

            LabelField variablesLabel = new LabelField("Please fill in the variables:");
            rowManager.add(variablesLabel);

            fieldNames = variableNames;

            fieldsForVariables = new SparseList();

            for (int ix = 0; ix < fieldNames.length; ix++) {
                String labelText = fieldNames[ix].fieldName == null ? "" + fieldNames[ix].fieldNumber : fieldNames[ix].fieldName;
                BasicEditField field = new BasicEditField(labelText + " : ", "", 50, BasicEditField.FILTER_DEFAULT);
                fieldsForVariables.add(field);
                rowManager.add(field);
            }

            rowManager.add(new LabelField()); // spacer
            pqField = new BasicEditField("Quantity : ", "1", 4, BasicEditField.FILTER_NUMERIC);
            rowManager.add(pqField);

            ButtonField printButton = new ButtonField("Print", ButtonField.CONSUME_CLICK);
            printButton.setChangeListener(new FieldChangeListener() {
                public void fieldChanged(Field field, int context) {
                    Thread t = new Thread(new Runnable() {
                        public void run() {
                            print();
                            UiApplication.getUiApplication().invokeAndWait(new Runnable() {
                                public void run() {
                                    displayResultsScreen();
                                }
                            });
                        }
                    });
                    t.start();
                }
            });
            rowManager.add(printButton);
        }

        public void print() {
            printFormat(getFormatName(), getFormatFieldsMap(), getPrintQuantity());
        }

        private int getPrintQuantity() {
            return Integer.parseInt(pqField.getText());
        }

        private String getFormatName() {
            return formatName;
        }

        private LongMap getFormatFieldsMap() {
            LongMap formatFieldsMap = CollectionProvider.getLongMap();
            for (int i = 0; i < fieldNames.length; i++) {
                formatFieldsMap.put(fieldNames[i].fieldNumber, ((BasicEditField) fieldsForVariables.get(i)).getText());
            }
            return formatFieldsMap;
        }
    }

    class ResultsScreen extends DemoMainScreen {

        public ResultsScreen() {
            super();

            setTitle("Stored Format Demo - Results");

            VerticalFieldManager buttonManager = new VerticalFieldManager(Field.FIELD_HCENTER);
            add(buttonManager);

            LabelField resultsLabel = new LabelField("Print Job Sent", Field.FIELD_HCENTER);
            buttonManager.add(resultsLabel);

            ButtonField reprintButton = new ButtonField("Reprint", ButtonField.CONSUME_CLICK | Field.FIELD_HCENTER);
            reprintButton.setChangeListener(new FieldChangeListener() {
                public void fieldChanged(Field field, int context) {
                    Thread t = new Thread(new Runnable() {
                        public void run() {
                            formatScreen.print();
                        }
                    });
                    t.start();
                }
            });
            buttonManager.add(reprintButton);

            ButtonField backToFormatButton = new ButtonField("Re-Enter Data", ButtonField.CONSUME_CLICK | Field.FIELD_HCENTER);
            backToFormatButton.setChangeListener(new FieldChangeListener() {
                public void fieldChanged(Field field, int context) {
                    parentApp.popScreen(resultsScreen);
                }
            });
            buttonManager.add(backToFormatButton);

            ButtonField backToListFormatsButton = new ButtonField("Back to List Formats", ButtonField.CONSUME_CLICK | Field.FIELD_HCENTER);
            backToListFormatsButton.setChangeListener(new FieldChangeListener() {
                public void fieldChanged(Field field, int context) {
                    parentApp.popScreen(resultsScreen);
                    parentApp.popScreen(formatScreen);
                }
            });
            buttonManager.add(backToListFormatsButton);

            ButtonField exitButton = new ButtonField("Exit", ButtonField.CONSUME_CLICK | Field.FIELD_HCENTER);
            exitButton.setChangeListener(new FieldChangeListener() {
                public void fieldChanged(Field field, int context) {
                    Thread t = new Thread(new Runnable() {
                        public void run() {
                            screen.disconnect();
                        }
                    });
                    t.start();
                    parentApp.popScreen(resultsScreen);
                    parentApp.popScreen(formatScreen);
                    parentApp.popScreen(listFormatsScreen);
                    parentApp.popScreen(screen);
                }
            });
            buttonManager.add(exitButton);
        }
    }
}
