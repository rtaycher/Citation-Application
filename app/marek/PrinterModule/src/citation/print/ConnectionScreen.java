package citation.print;

import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RadioButtonField;
import net.rim.device.api.ui.component.RadioButtonGroup;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.FlowFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.zebra.blackberry.comm.BluetoothPrinterConnection;
import com.zebra.blackberry.comm.TcpPrinterConnection;
import com.zebra.blackberry.comm.ZebraPrinterConnection;
import com.zebra.blackberry.comm.ZebraPrinterConnectionException;
import com.zebra.blackberry.printer.PrinterLanguage;
import com.zebra.blackberry.printer.ZebraPrinter;
import com.zebra.blackberry.printer.ZebraPrinterFactory;
import com.zebra.blackberry.printer.ZebraPrinterLanguageUnknownException;

/*
 * Main class which shows Connection options to the user
 * Contains:
 * Status label which users of this class can set the text and color of by calling setStatus()
 * A radio field to toggle between Bluetooth and TCP connections
 * Edit fields to enter connection information
 */
public class ConnectionScreen extends DemoMainScreen {
    private ColorfulLabelField statusField;
    private EnablableBasicEditField macAddressField;
    private EnablableBasicEditField tcpAddressField;
    private EnablableBasicEditField tcpPortNumber;
    private RadioButtonField blueToothButton;
    private ZebraPrinterConnection zebraPrinterConnection;

    private static final String bluetoothAddressKey = "ZEBRA_DEMO_BLUETOOTH_ADDRESS";
    private static final String tcpAddressKey = "ZEBRA_DEMO_TCP_ADDRESS";
    private static final String tcpPortKey = "ZEBRA_DEMO_TCP_PORT";

    public ConnectionScreen() {
        super();

        FlowFieldManager statusManager = new FlowFieldManager();

        LabelField statusFieldLabel = new LabelField();
        statusFieldLabel.setText("Status:");
        statusManager.add(statusFieldLabel);
        statusField = new ColorfulLabelField(Color.RED);
        statusManager.add(statusField);
        setStatus("Not Connected", Color.RED);

        this.add(statusManager);
        this.add(new LabelField());

        VerticalFieldManager connectionFieldManager = new VerticalFieldManager();

        LabelField connectionTypeLabel = new LabelField();
        connectionTypeLabel.setText("Select Connection Type:");
        connectionFieldManager.add(connectionTypeLabel);

        RadioButtonGroup radioButtonGroup = new RadioButtonGroup();
        blueToothButton = new RadioButtonField("BT", radioButtonGroup, true);
        String bluetoothAddress = getStoredString(bluetoothAddressKey);
        macAddressField = new EnablableBasicEditField("MAC Address: ", bluetoothAddress, 12, BasicEditField.FILTER_HEXADECIMAL);

        RadioButtonField ipButton = new RadioButtonField("802.11", radioButtonGroup, false);
        String tcpAddress = getStoredString(tcpAddressKey);
        tcpAddressField = new EnablableBasicEditField("IP/DNS: ", tcpAddress, 50, BasicEditField.FILTER_DEFAULT);
        String tcpPort = getStoredString(tcpPortKey);
        tcpPortNumber = new EnablableBasicEditField("Port Number: ", tcpPort, 5, BasicEditField.FILTER_NUMERIC);

        connectionFieldManager.add(new SeparatorField());
        connectionFieldManager.add(blueToothButton);
        connectionFieldManager.add(macAddressField);
        connectionFieldManager.add(new SeparatorField());
        connectionFieldManager.add(ipButton);
        connectionFieldManager.add(tcpAddressField);
        connectionFieldManager.add(tcpPortNumber);
        connectionFieldManager.add(new SeparatorField());
        this.add(connectionFieldManager);

        setGuiState();

        blueToothButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                setGuiState();
            }
        });

        ipButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                setGuiState();
            }
        });

    }

    protected void onUndisplay() {
        disconnect();
        super.onUndisplay();
    }

    public ZebraPrinterConnection getConnection() {
        return zebraPrinterConnection;
    }

    private String getMacAddressFieldText() {
        return macAddressField.getText();
    }

    private String getTcpAddress() {
        return tcpAddressField.getText();
    }

    private String getTcpPortNumber() {
        return tcpPortNumber.getText();
    }

    private boolean isBlueToothSelected() {
        return blueToothButton.isSelected();
    }

    public void setStatus(final String statusMessage, final int color) {
        UiApplication.getUiApplication().invokeAndWait(new Runnable() {
            public void run() {
                statusField.repaint(color);
                statusField.setText(statusMessage);
                DemoSleeper.sleep(500); // Just to allow the user time to read the status
            }
        });
    }

    public ZebraPrinter connect() {
        if (isBlueToothSelected()) {
            pairBT();
        } else {
            connectToTcp();
        }

        ZebraPrinter printer = null;

        if (zebraPrinterConnection.isConnected()) {
            try {
                printer = ZebraPrinterFactory.getInstance(zebraPrinterConnection);
                setStatus("Determining Printer Language", Color.GOLD);
                PrinterLanguage pl = printer.getPrinterControlLanguage();
                setStatus("Printer Language " + pl, Color.CORNFLOWERBLUE);
            } catch (ZebraPrinterConnectionException e) {
                setStatus("Unknown Printer Language", Color.RED);
                printer = null;
                DemoSleeper.sleep(1000);
                disconnect();
            } catch (ZebraPrinterLanguageUnknownException e) {
                setStatus("Unknown Printer Language", Color.RED);
                printer = null;
                DemoSleeper.sleep(1000);
                disconnect();
            }
        }

        return printer;
    }

    public void disconnect() {
        try {
            setStatus("Disconnecting", Color.RED);
            if (zebraPrinterConnection != null) {
                zebraPrinterConnection.close();
            }
            setStatus("Not Connected", Color.RED);
        } catch (ZebraPrinterConnectionException e) {
            setStatus("COMM Error! Disconnected", Color.RED);
        }
        zebraPrinterConnection = null;
    }

    private void pairBT() {
        try {
            if (zebraPrinterConnection != null && zebraPrinterConnection.isConnected() == true) {
                setStatus("Already Connected", Color.LIME);
                return;
            }
            setStatus("Connecting... Please wait...", Color.GOLD);
            zebraPrinterConnection = new BluetoothPrinterConnection(getMacAddressFieldText());
            zebraPrinterConnection.open();
            setStatus("Connected", Color.LIME);
            setStoredString(bluetoothAddressKey, macAddressField.getText());
        } catch (ZebraPrinterConnectionException e) {
            setStatus("Comm Error! Disconnecting", Color.RED);
            DemoSleeper.sleep(1000);
            disconnect();
        }
    }

    private void connectToTcp() {
        if (zebraPrinterConnection != null && zebraPrinterConnection.isConnected() == true) {
            setStatus("Already Connected", Color.GREEN);
            return;
        }
        /*
         * NOTE: If you are running this app in the BlackBerry(R) smartphone simulator without running the MDS simulator, 
         * the simulator reports that it was able to connect via MDS, even though it was not.
         */
        tryTcpConnect(false);
        if (zebraPrinterConnection == null || zebraPrinterConnection.isConnected() == false) {
            tryTcpConnect(true);
        }
    }

    private void tryTcpConnect(boolean isDirectWifi) {
        try {
            setStatus("Connecting... Please wait...", Color.GOLD);
            zebraPrinterConnection = new TcpPrinterConnection(getTcpAddress(), Integer.parseInt(getTcpPortNumber()), isDirectWifi);
            zebraPrinterConnection.open();
            setStatus("Connected", Color.GREEN);
            setStoredString(tcpAddressKey, tcpAddressField.getText());
            setStoredString(tcpPortKey, tcpPortNumber.getText());
        } catch (ZebraPrinterConnectionException e) {
            setStatus("Comm Error! Disconnecting", Color.RED);
            DemoSleeper.sleep(1000);
            disconnect();
        }
    }

    private void setGuiState() {
        if (blueToothButton.isSelected()) {
            tcpAddressField.setEnabled(false);
            tcpPortNumber.setEnabled(false);
            macAddressField.setEnabled(true);
        } else {
            tcpAddressField.setEnabled(true);
            tcpPortNumber.setEnabled(true);
            macAddressField.setEnabled(false);
        }
    }

    private String getStoredString(String key) {
        PersistentObject object = PersistentStore.getPersistentObject(key.hashCode());
        Object contents = object.getContents();
        if (contents instanceof String) {
            return (String) contents;
        } else {
            return "";
        }
    }

    private void setStoredString(String key, String value) {
        PersistentObject object = PersistentStore.getPersistentObject(key.hashCode());
        object.setContents(value);
        object.commit();
    }
}
