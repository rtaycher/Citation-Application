package citation.print;

import citation.data.Citation;

import com.zebra.blackberry.comm.BluetoothPrinterConnection;
import com.zebra.blackberry.comm.ZebraPrinterConnection;
import com.zebra.blackberry.comm.ZebraPrinterConnectionException;

/**
 * Printer assumes that connections are made via BlueBooth and that connected printer is one of Zebra CPCL printer
 * models.
 */
public class Printer {
    private String macAddress;
    private ZebraPrinterConnection zebraPrinterConnection;

    private byte[] stringToCPCL(String aString) {
        String cpclControlSequence = "! 0 200 200 406 1\r\n" + "ON-FEED IGNORE\r\n"
                + "BOX 20 20 380 380 8\r\n"
                + "T 0 6 137 177 TEST\r\n"
                + "PRINT\r\n";
        return cpclControlSequence.getBytes();
    }

    public void connect() throws PrinterException {
        try {
            if (zebraPrinterConnection != null && zebraPrinterConnection.isConnected() == true)
                return;

            zebraPrinterConnection = new BluetoothPrinterConnection(macAddress);
            zebraPrinterConnection.open();
        } catch (ZebraPrinterConnectionException ex) {
            disconnect();
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
            connect();
            byte[] cpclControlSequence = stringToCPCL("");
            zebraPrinterConnection.write(cpclControlSequence);
            Thread.sleep(2000);
        } catch (ZebraPrinterConnectionException ex) {
            throw new PrinterException(ex.getMessage());
        } catch (InterruptedException ex) {
            throw new PrinterException(ex.getMessage());
        } finally {
            disconnect();
        }
    }
}
