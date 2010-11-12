
package citation.data;

/**
 * This is a class to retrieve real ticket numbers 
 * and ask for more if you need more
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;


import net.rim.device.api.io.FileInputStream;
import net.rim.device.api.io.FileOutputStream;
import net.rim.device.api.util.Persistable;

public class TicketHandler{

    private String[] usableTicketNumbers;
    private int fewTickets=100;
/***
 * 
 * @return ticketnumber or -1 on error
 * @throws IOException
 */
    public int getUniqueTicketNumber() throws IOException{
        readCurrentTickets();
        writeTicketsFromMemoryMinusHead();
        checkNumberOfTickets();
        return head();

    }
    private void checkNumberOfTickets() {
        if (usableTicketNumbers.length< fewTickets)
            checkoutTicketBook();                                                                                                                                                                                                                
                                                                                                                                                                                                                                                 
}                                                                                                                                                                                                                                                
    private void readCurrentTickets() {                                                                                                                                                                                                          
        try {                                                                                                                                                                                                                                    
        FolderCreateIfItDoesntExist("file:///store/home/user/Cite");                                                                                                                                                                             
        FileConnection ticketFile = FileOpenForReading("file:///store/home/user/Cite/ticketFile");                                                                                                                                               
        if(ticketFile!=null)                                                                                                                                                                                                                     
            {                                                                                                                                                                                                                                    
            usableTicketNumbers=readTicketsFromFileReturnStringArray(ticketFile);                                                                                                                                                                
            ticketFile.delete();
            }

    } catch (IOException e) {
    e.printStackTrace();
    
    }  
    
    private void FileCreateIfItDoesntExistAndOpen(String string) {
        // TODO Auto-generated method stub

    }
    private void writeTicketsFromMemoryMinusHead() throws UnsupportedEncodingException, IOException {
        FileConnection ticketTempFile = FileOpenForWriting("file:///store/home/user/Cite/ticketTempFile");
        WriteStringArrayToFile(usableTicketNumbers,ticketTempFile);
        FileConnection ticketfile = (FileConnection)Connector.open("file:///store/home/user/Cite/ticketFile");
        if(ticketfile.exists())
            {
            ticketfile.delete();
            ticketfile.close();
            }
        ticketTempFile.rename("file:///store/home/user/Cite/ticketFile");
        ticketTempFile.close();
            


    
    }

    private int head(){
        if(usableTicketNumbers.length>0)
            return Integer.parseInt(usableTicketNumbers[0]); 
        else
            return -1;//failure
    }
    private String[] readTicketsFromFileReturnStringArray(FileConnection tf) throws IOException {
            InputStream readticketFile=tf.openInputStream();
            
            ByteArrayOutputStream holdBytes=new ByteArrayOutputStream();
                byte arr[]= new byte[1024];
                while(readticketFile.read(arr,0,1024)>0)//need to check how this function works!!!!!
                holdBytes.write(arr,0,1024);//
                
                String temp[]=holdBytes.toString("UTF-8").split(" ",1000);
            
                holdBytes.flush();//are both flush and close needed?
                holdBytes.close();
                readticketFile.close();
                return temp;
}

    private void WriteStringArrayToFile(String[] strings,FileConnection wfile) throws UnsupportedEncodingException, IOException {
        if(wfile!=null)
            {
            OutputStream writeTemp=wfile.openOutputStream();

            for(int i=1;i<usableTicketNumbers.length;i++)
                writeTemp.write(usableTicketNumbers[i].getBytes("UTF-8"));
                writeTemp.flush();
                writeTemp.close();
            }

    
}


    private boolean checkoutTicketBook()
    {
        return addTicketsToArray(askServerForNewTicketBook())
\    }
    private String [] askServerForNewTicketBook()
    {
        return null;//Needs Implementing

    }
    private boolean addTicketsToArray(String[] Ticketbook)
    {
        if(Ticketbook!=null)
            {
            String checkout[]={"Stub"};
            String temparray[]=new String[checkout.length+usableTicketNumbers.length]
            int i,j=0;
            for(i=0;i<usableTicketNumbers.length;i++)
                temparray[i]=usableTicketNumbers[i];
            for(j=0;j<checkout.length;j++)
                temparray[i+j]=checkout[j];
            usableTicketNumbers=temparray;
            return true;
            }
        else
            return false;

    }
    
    public void FolderCreateIfItDoesntExist(String FolderPath) throws IOException
    {
        FileConnection ticketFolder = (FileConnection)Connector.open("FolderPath");
        if (!ticketFolder.exists())
            ticketFolder.mkdir();
        ticketFolder.close();
    }
    /*
     * Returns null if the file doesn't exist,since you don't need to read an empty file
     */
    public FileConnection FileOpenForReading(String FilePath) throws IOException 
    {
    
