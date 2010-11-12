
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

import citation.extra.ExtraStringUtils;


import net.rim.device.api.io.FileInputStream;
import net.rim.device.api.io.FileOutputStream;
import net.rim.device.api.util.Persistable;
/**
 * A mostly Abstract(some state to make things easier 
 * and for a primitive lock) Class for fetching a unique ticket number
 * it reads a file, takes the first number and returns that as ticket num
 * If the ticket file has less then fewTickets be it asks the database for more(not implemented yet
 * It also has a function to ask for more tickets if needed from outside of getting unique ticket count  
 * (Useful for running when your Application gets a network connection, if your phone frequently doesn't have one.)
 */
public class TicketNumberHandler{

    private String[] usableTicketNumbers;
    private int fewTickets=100;//Used to tell if you need more tickets
    private static boolean locked=false;//simple lock to prevent timing issues
/***
 * Simple Lock to make sure this and checkNumberOfTicketsandWrite
 * don't run at the same time, I think it would simply wait for the other to complete
 * @return A unique ticket number or -1 on error
 *
 */
    public int getUniqueTicketNumber(){
        try{
            while(locked)
                    this.wait(1);
        }catch(InterruptedException badlock){
                return -1;
        }
        locked=true;
        readCurrentTickets();
        checkIfRunningLowOnTickets(1);
        writeTicketsFromMemoryMinusHead();
        locked=false;
        return head();

    }
    /**
     * Again with the simple lock, this version is used
     * outside of getting tickets
     * @return true if enough tickets, or you were able to get more,false otherwise
     */
    public boolean checkNumberOfTicketsandWrite(){//version for calling from outside getUniqueTicketNumber when trying to add tickets (if needed while we have a network).
        try{
            while(locked)
                    this.wait(1);
        }catch(InterruptedException badlock){
                return false;
        }
        boolean tempBool=true;
        locked=true;
        tempBool=checkIfRunningLowOnTickets(0);
        locked=false;
        return tempBool;

    }
    /*
     * return true if good-don't need more tickets or got more tickets,false otherwise
     */
    private boolean checkIfRunningLowOnTickets(int cutoff) {
        if (usableTicketNumbers.length< fewTickets)
            return checkoutTicketBook(cutoff);
        else return true;
    }
/**
 * read tickets and store in class ticket storage array
 */
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
        }
    }
/**
 * Put back all tickets back into the file except head(used ticket)
 */
    private void writeTicketsFromMemoryMinusHead() {
        try{
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
        catch(IOException e){

        }



    }
/**
 * @return head of ticket string list as int
 */
    private int head(){
        if(usableTicketNumbers.length>0)
            return Integer.parseInt(usableTicketNumbers[0]);
        else
            return -1;//failure
    }
    /**
     * The actual hard work of reading in the tickets as bytes and making an array of strings(that hold integers in string form)
     * @param tf = fileconnection
     * @return array of strings
     * @throws IOException
     */
    private String[] readTicketsFromFileReturnStringArray(FileConnection tf) throws IOException {
            InputStream readticketFile=tf.openInputStream();

            ByteArrayOutputStream holdBytes=new ByteArrayOutputStream();
                byte arr[]= new byte[1024];
                while(readticketFile.read(arr,0,1024)>0){//need to check how this function works!!!!!
                    holdBytes.write(arr,0,1024);
                }

                String temp[]=ExtraStringUtils.split(holdBytes.toString(),' ');

                holdBytes.flush();//are both flush and close needed?
                holdBytes.close();
                readticketFile.close();
                return temp;
}
/**
 * 
 * @param strings=array to write back to file
 * @param wfile=file connection
 * @throws UnsupportedEncodingException
 * @throws IOException
 */
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


    private boolean checkoutTicketBook(int cutoff)
    {
        return addTicketsToArray(askServerForNewTicketBook(),cutoff);
    }
    /**
     * unimplemented
     * @return Array of strings (ints) or null if it didn't work
     */
    private String [] askServerForNewTicketBook()
    {
        return null;//Needs Implementing

    }
    /**
     * 
     * @param Ticketbook array to add to array held by class
     * @param cutoff, not used probably a mistake, get rid of it.
     * @return true if Ticketbook is not null
     */
    private boolean addTicketsToArray(String[] Ticketbook,int cutoff)
    {
        if(Ticketbook!=null)
            {
            String checkout[]={"Stub"};
            String temparray[]=new String[checkout.length+usableTicketNumbers.length];
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
/**
 * Make sure we are working on existing directory by making it if it doesn't exist
 * @param FolderPath
 * @throws IOException
 */
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

        FileConnection fc=null;
        fc = (FileConnection)Connector.open(FilePath);

        if (!fc.exists())
            {
            fc.close();
            return null;
            }

        return (FileConnection)Connector.open(FilePath);

    }
    /*
     * Creates file if it doesn't exist, returns null if it can't create it.
     */
    public FileConnection FileOpenForWriting(String FilePath) throws IOException
    {
    try{
        FileConnection fc=null;
        fc = (FileConnection)Connector.open(FilePath);
        if (!fc.exists())
            {
            fc.create();
            }
        return fc;
    }catch(IOException ProblemOpeningFile){
        return null;
    }

    }



}
