package citation.print;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RadioButtonField;
import net.rim.device.api.ui.component.RadioButtonGroup;
import net.rim.device.api.ui.container.FlowFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.zebra.blackberry.comm.ZebraPrinterConnectionException;
import com.zebra.blackberry.discovery.BluetoothDiscoverer;
import com.zebra.blackberry.discovery.DiscoveredPrinter;
import com.zebra.blackberry.discovery.NetworkDiscoverer;

/*
 * This App will show how to do Bluetooth and TCP discovery from a BlackBerry(R) smartphone
 * 
 */
public class DiscoveryDemo {

    private ColorfulLabelField statusField;
    private CustomObjectListField discoveredPrinters;

    public void displayDiscoveryDemo(UiApplication application) {
        MainScreen screen = new DemoMainScreen();
        screen.setTitle("Discovery Demo");

        VerticalFieldManager choiceFieldManager = new VerticalFieldManager(Field.FIELD_LEFT);
        final BasicEditField tcpAddressField = new BasicEditField("IP (xxx.xxx.xxx.*): ", "", 50, BasicEditField.FILTER_DEFAULT);
        choiceFieldManager.add(tcpAddressField);

        RadioButtonGroup radioButtonGroup = new RadioButtonGroup();
        final RadioButtonField wifiButton = new RadioButtonField("Direct Wifi", radioButtonGroup, true);
        RadioButtonField mdsButton = new RadioButtonField("MDS", radioButtonGroup, false);

        choiceFieldManager.add(wifiButton);
        choiceFieldManager.add(mdsButton);

        screen.add(choiceFieldManager);

        FlowFieldManager buttonManager = new FlowFieldManager(Field.FIELD_HCENTER);
        ButtonField testButton = new ButtonField("Discovery via Network", ButtonField.CONSUME_CLICK);
        buttonManager.add(testButton);

        ButtonField bluetoothDiscoveryButton = new ButtonField("Discovery via Bluetooth", ButtonField.CONSUME_CLICK);
        buttonManager.add(bluetoothDiscoveryButton);

        screen.add(buttonManager);

        FlowFieldManager statusManager = new FlowFieldManager();

        LabelField statusFieldLabel = new LabelField();
        statusFieldLabel.setText("Status:");
        statusManager.add(statusFieldLabel);
        statusField = new ColorfulLabelField(Color.RED);
        statusManager.add(statusField);
        setStatus("Hit 'Discover' to find printers", Color.RED);
        screen.add(statusManager);

        VerticalFieldManager discoveryManager = new VerticalFieldManager();

        LabelField discoveredPrintersLabel = new LabelField();
        discoveredPrintersLabel.setText("Discovered Printers:");
        discoveryManager.add(discoveredPrintersLabel);

        discoveredPrinters = new CustomObjectListField();
        discoveryManager.add(discoveredPrinters);
        discoveryManager.add(new LabelField());

        discoveredPrinters.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                discoveredPrinters.invalidate(); // forces repainting
            }
        });

        screen.add(discoveryManager);

        testButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        try {
                            doDiscovery(tcpAddressField.getText(), wifiButton.isSelected());
                        } catch (IllegalArgumentException e) {
                            setStatus(e.getMessage(), Color.RED);
                        }
                    }
                });
                t.start();
            }
        });

        bluetoothDiscoveryButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        doBluetoothDiscovery();
                    }
                });
                t.start();
            }
        });

        application.pushScreen(screen);
    }

    private void doDiscovery(final String discoveryString, final boolean isDirectWifi) throws IllegalArgumentException {

        setStatus("Clearing List", Color.BLUE);
        DemoSleeper.sleep(500);
        UiApplication.getUiApplication().invokeAndWait(new Runnable() {
            public void run() {
                while (discoveredPrinters.getSize() > 0) {
                    discoveredPrinters.delete(0);
                }
            }
        });

        setStatus("Searching for printers", Color.GOLD);
        final DiscoveredPrinter[] printers = NetworkDiscoverer.findPrinters(discoveryString, isDirectWifi);
        setStatus("Found " + printers.length
                + " printers!", Color.LIME);

        UiApplication.getUiApplication().invokeLater(new Runnable() {
            public void run() {
                for (int i = 0; i < printers.length; i++) {
                    discoveredPrinters.insert(i, printers[i]);
                }
            }
        });
    }

    private void doBluetoothDiscovery() {
        setStatus("Clearing List", Color.BLUE);
        DemoSleeper.sleep(500);
        UiApplication.getUiApplication().invokeAndWait(new Runnable() {
            public void run() {
                while (discoveredPrinters.getSize() > 0) {
                    discoveredPrinters.delete(0);
                }
            }
        });

        setStatus("Searching for printers", Color.GOLD);
        DemoSleeper.sleep(500);

        DiscoveredPrinter[] printers;
        try {
            printers = BluetoothDiscoverer.findPrinters();
        } catch (ZebraPrinterConnectionException e) {
            printers = new DiscoveredPrinter[0];
        }

        final DiscoveredPrinter[] printersForGui = new DiscoveredPrinter[printers.length];
        for (int printerIx = 0; printerIx < printers.length; printerIx++) {
            printersForGui[printerIx] = printers[printerIx];
        }

        setStatus("Found " + printers.length
                + " printers!", Color.LIME);

        UiApplication.getUiApplication().invokeLater(new Runnable() {
            public void run() {
                for (int i = 0; i < printersForGui.length; i++) {
                    discoveredPrinters.insert(i, printersForGui[i]);
                }
            }
        });
    }

    private void setStatus(final String statusMessage, final int color) {
        UiApplication.getUiApplication().invokeLater(new Runnable() {
            public void run() {
                statusField.repaint(color);
                statusField.setText(statusMessage);
            }
        });
    }
}
