package citation.print;

import net.rim.device.api.ui.component.Dialog;
import citation.data.Citation;

import com.zebra.blackberry.comm.BluetoothPrinterConnection;
import com.zebra.blackberry.comm.ZebraPrinterConnection;
import com.zebra.blackberry.comm.ZebraPrinterConnectionException;

/**
 * Printer assumes that connections are made via BlueBooth and that connected printer is one of Zebra CPCL printer
 * models.
 */
public class Printer {
    private String macAddress = "00037A25D18D";
    private ZebraPrinterConnection zebraPrinterConnection;

    private byte[] stringToCPCL(String aString) {
        String cpclControlSequence = 
        		"! 0 200 200 800 1\r\n" 
        		+ "ON-FEED IGNORE\r\n"
                + "T 0 6 137 177 TEST1\r\n"
                + "T 0 6 137 277 TEST2\r\n"
                + "PRINT\r\n";
        return cpclControlSequence.getBytes();
    }

    public void connect() throws PrinterException {
        try {
            if (zebraPrinterConnection != null && zebraPrinterConnection.isConnected() == true)
                return;

            System.out.println("Creating new BluetoothPrinterConnection...");
            zebraPrinterConnection = new BluetoothPrinterConnection(macAddress);
            Dialog.inform("Calling ZebraPrinterConnection.open()...");
            zebraPrinterConnection.open();
        } catch (ZebraPrinterConnectionException ex) {
            disconnect();
            zebraPrinterConnection = null;
            throw new PrinterException(ex.getMessage());
        }
    }

    public void disconnect() throws PrinterException {
        try {
            if (zebraPrinterConnection != null)
                zebraPrinterConnection.close();
        } catch (ZebraPrinterConnectionException ex) {
            throw new PrinterException(ex.getMessage());
        } finally {
            zebraPrinterConnection = null;
        }
    }

    public void print(Citation aCitation) throws PrinterException {
        try {
        	System.out.println("Trying to connect via BT...");
            connect();
            byte[] cpclControlSequence = stringToCPCL("");
            System.out.println("Writing byte sequence to printer...");
            zebraPrinterConnection.write(cpclControlSequence);
            Dialog.inform("Wrote byte sequence to printer...");
            Thread.sleep(2000);
        } catch (ZebraPrinterConnectionException ex) {
            throw new PrinterException(ex.getMessage());
        } catch (InterruptedException ex) {
            throw new PrinterException(ex.getMessage());
        } finally {
            disconnect();
            System.out.println("Trying to disconnect from BT...");
        }
    }
}
