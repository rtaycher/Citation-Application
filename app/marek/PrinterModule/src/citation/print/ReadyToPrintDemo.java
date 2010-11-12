package citation.print;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.FlowFieldManager;

import com.zebra.blackberry.comm.ZebraPrinterConnection;
import com.zebra.blackberry.comm.ZebraPrinterConnectionException;
import com.zebra.blackberry.printer.PrinterStatus;
import com.zebra.blackberry.printer.PrinterStatusMessages;
import com.zebra.blackberry.printer.ZebraPrinter;

/*
 * While the printer is connected, poll the printer status every 3 seconds and display the results.
 */

public class ReadyToPrintDemo {
    private ZebraPrinter printer;
    private ConnectionScreen screen;
    private ColorfulLabelField printerStatusField;
    private LabelField labelsInBatch;
    private LabelField labelsInRecvBuffer;

    public void displayReadyToPrintDemo(UiApplication application) {
        screen = new ConnectionScreen();
        screen.setTitle("Ready To Print Demo");

        FlowFieldManager statusManager = new FlowFieldManager();

        LabelField statusFieldLabel = new LabelField();
        statusFieldLabel.setText("Printer Status:");
        statusManager.add(statusFieldLabel);
        printerStatusField = new ColorfulLabelField(Color.RED);
        statusManager.add(printerStatusField);
        setPrinterStatus("Unknown", Color.GOLD);
        screen.insert(statusManager, 1); // At the top of the screen, just under the connection status

        FlowFieldManager labelsInBatchMgr = new FlowFieldManager();
        LabelField labelsInBatchTitle = new LabelField();
        labelsInBatchTitle.setText("Labels in Batch:");
        labelsInBatchMgr.add(labelsInBatchTitle);
        labelsInBatch = new LabelField();
        labelsInBatchMgr.add(labelsInBatch);
        labelsInBatch.setText("0");
        screen.insert(labelsInBatchMgr, 2);

        FlowFieldManager labelsInRecvBufferMgr = new FlowFieldManager();
        LabelField labelsInRecvBufferTitle = new LabelField();
        labelsInRecvBufferTitle.setText("Labels in Receive Buffer:");
        labelsInRecvBufferMgr.add(labelsInRecvBufferTitle);
        labelsInRecvBuffer = new LabelField();
        labelsInRecvBufferMgr.add(labelsInRecvBuffer);
        labelsInRecvBuffer.setText("0");
        screen.insert(labelsInRecvBufferMgr, 3);

        FlowFieldManager buttonManager = new FlowFieldManager(Field.FIELD_HCENTER);
        ButtonField connectButton = new ButtonField("Connect", ButtonField.CONSUME_CLICK);
        buttonManager.add(connectButton);

        ButtonField disconnectButton = new ButtonField("Disconnect", ButtonField.CONSUME_CLICK);
        buttonManager.add(disconnectButton);

        screen.add(buttonManager);

        connectButton.setChangeListener(new FieldChangeListener() {
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
            pollForStatus();
        }
    }

    /**
     * Loop forever, polling the printer status every 3 seconds. This method should execute in a thread other than the
     * GUI thread, since it never returns.
     */
    private void pollForStatus() {
        while (screen.getConnection().isConnected()) {
            try {
                updatePrinterStatus();
            } catch (ZebraPrinterConnectionException e) {
                e.printStackTrace();
            }
            DemoSleeper.sleep(3000);
        }
    }

    /**
     * Get the printer status, and display it.
     * 
     * @throws ZebraPrinterConnectionException
     */
    private void updatePrinterStatus() throws ZebraPrinterConnectionException {
        ZebraPrinterConnection connection = screen.getConnection();
        final PrinterStatus printerStatus;
        if (connection != null && connection.isConnected()) {
            printerStatus = printer.getCurrentStatus();

            UiApplication.getUiApplication().invokeAndWait(new Runnable() {
                public void run() {
                    updatePrinterStatusUI(printerStatus);
                    DemoSleeper.sleep(500); // Just to allow the user time to read the status
                }
            });
        } else {
            screen.setStatus("Not ready", Color.RED);
            setPrinterStatus("Not Connected", Color.RED);
        }

    }

    private void updatePrinterStatusUI(PrinterStatus printerStatus) {
        boolean ready = false;

        String printerStatusStringForDisplay = "";
        if (printerStatus != null) {
            ready = printerStatus.isReadyToPrint;

            String[] printerStatusString = new PrinterStatusMessages(printerStatus).getStatusMessage();

            printerStatusStringForDisplay = concatStringsForDisplay(printerStatusString);
        } else {
            ready = false;
            printerStatusStringForDisplay = "Unable to determine status";
        }

        String readyString = ready ? "Ready" : "Not ready";
        int readyColor = ready ? Color.GREEN : Color.RED;
        screen.setStatus(readyString, readyColor);
        setPrinterStatus(printerStatusStringForDisplay, readyColor);
        labelsInBatch.setText(String.valueOf(printerStatus.labelsRemainingInBatch));
        labelsInRecvBuffer.setText(String.valueOf(printerStatus.numberOfFormatsInReceiveBuffer));
    }

    private String concatStringsForDisplay(String[] printerStatusString) {
        StringBuffer retVal = new StringBuffer();
        for (int i = 0; i < printerStatusString.length; i++) {
            retVal.append(printerStatusString[i]);
            retVal.append(":");
        }
        String statusString = retVal.toString();
        if (statusString.equals("")) {
            statusString = "No Error";
        }
        return statusString;
    }

    public void setPrinterStatus(final String statusMessage, final int color) {
        UiApplication.getUiApplication().invokeAndWait(new Runnable() {
            public void run() {
                printerStatusField.repaint(color);
                printerStatusField.setText(statusMessage);
                DemoSleeper.sleep(500); // Just to allow the user time to read the status
            }
        });
    }

}