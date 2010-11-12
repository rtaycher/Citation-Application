package citation.print;

import net.rim.device.api.ui.container.MainScreen;

/**
 * The Main Screen for the Zebra API demos. This will be used as the main screen for each of the demos.
 */
public class DemoMainScreen extends MainScreen {

    public DemoMainScreen() {
        super();
    }

    public DemoMainScreen(long style) {
        super(style);
    }

    /**
     * Override MainScreen.onSavePrompt to avoid the "save discard cancel" dialog when exiting the demos.
     * 
     * @see net.rim.device.api.ui.container.MainScreen#onSavePrompt()
     */
    protected boolean onSavePrompt() {

        return true;

    }

}
