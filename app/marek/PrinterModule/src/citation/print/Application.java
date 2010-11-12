package citation.print;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.container.FlowFieldManager;
import net.rim.device.api.ui.container.MainScreen;

public class Application extends UiApplication {

    public static void main(String[] args) {
        new Application().enterEventDispatcher();
    }

    public Application() {
        MainScreen screen = new DemoMainScreen();
        screen.setTitle("Citation Print Module");
        this.pushScreen(screen);

        FlowFieldManager buttonManager = new FlowFieldManager();
        ButtonField connectivityButton = new ButtonField("Connectivity Demo", ButtonField.CONSUME_CLICK);
        buttonManager.add(connectivityButton);

        ButtonField discoveryButton = new ButtonField("Discovery Demo", ButtonField.CONSUME_CLICK);
        buttonManager.add(discoveryButton);

        ButtonField readyToPrintButton = new ButtonField("Ready To Print Demo", ButtonField.CONSUME_CLICK);
        buttonManager.add(readyToPrintButton);

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

        readyToPrintButton.setChangeListener(new FieldChangeListener() {
            public void fieldChanged(Field field, int context) {
                new ReadyToPrintDemo().displayReadyToPrintDemo(mainApp);
            }
        });
    }

}
