package package1;

import java.util.Hashtable;
import java.util.Vector;


import net.rim.device.api.i18n.*;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;
import net.rim.device.api.util.*;
import java.util.*;


//---------------------------------------------------------------------

public class Persister extends UiApplication implements PersisterResource{
	//---- fields for the UI ---
	private AutoTextEditField citizen;
	private AutoTextEditField car;
	private EditField driverLicense;
	
	//-- include a vector and persistent object and resource bundle
	private static Vector vector;
	private static PersistentObject PersistObj;
	private static ResourceBundle ReBundle;
	private static Hashtable hash;
	
	
	
	
	
	//=========== class for data storage using vector  ===========//
	private static final class node implements Persistable{
		//Every object that can persist between handheld resets must implement persistable. 
		private Vector array;
		public static final int citizenID = 0;		//not the actual value, but the variable ID for the value
		public static final int carID = 1;			//this id shows Vector which value needs to be changed
		public static final int driverID = 2;		//example car = 1 tells vector to look at the index 2 in the vector
		
		public node (){	//constructor
			
			array = new Vector(3);
			
			for (int i = 0 ; i < array.capacity() ; ++i){	//run thru every slot
				array.addElement(new String(""));
				
			}
		}
		
		public String getElement(int id){
			return (String)array.elementAt(id);
		}
		public void setElement(int id, String value){
			array.setElementAt(value, id);
		}
		
	}//---------------------- end node -------------*/
	
	
	
	
	//======== CONNECTING THE NODE ========
	static{	
		//aqquire the resource from the personal interface PersisterResource.java
		ReBundle = ResourceBundle.getBundle(
				PersisterResource.BUNDLE_ID, 
				PersisterResource.BUNDLE_NAME);
		
		//aqquire the persistent object
		PersistObj = PersistentStore.getPersistentObject(0x13f20d7fd5d14846L);
		
		//if no data, commit the first initialization to the flash memory
		synchronized (PersistObj){
			if (PersistObj.getContents() == null){
				PersistObj.setContents(new Vector());
				PersistObj.commit();
			}
		}
		
		//make a vector instance and set it up with a persistent object
		vector = new Vector();
		vector = (Vector)PersistObj.getContents();
	}
	
	
	
	
	
	
	
	
	
	/**************************** MENUS ***********************************/
	//main window as a constructor
	public Persister(){
		MainScreen mainscreen = new MainScreen();
		mainscreen.setTitle(new LabelField("Persistent Storage Application"));
		citizen = new AutoTextEditField("Citizen:", "");
		car = new AutoTextEditField("Car:", "");
		driverLicense = new EditField("Driver License:", "",Integer.MAX_VALUE,BasicEditField.FILTER_NUMERIC);
		
		//add these fields to the screen
		mainscreen.add(citizen);
		mainscreen.add(car);
		mainscreen.add(driverLicense);
        mainscreen.addMenuItem(saveItem);
        mainscreen.addMenuItem(getItem);		
		pushScreen(mainscreen);
		
	}
    //create a Save menu item for users to save the music store
    //information that they have entered
    private MenuItem saveItem = new MenuItem(
                    ReBundle, MENUITEM_SAVE, 110, 10) {
            public void run() {

                    //create a new StoreInfo object
                    node info = new node();

                    //set node elements by retrieving text that user entered
                    info.setElement(node.citizenID, citizen.getText());
                    info.setElement(node.carID, car.getText());
                    info.setElement(node.driverID, driverLicense.getText());



                    //add the new node object to the Vector
                    vector.addElement(info);

                    //add the Vector of node objects to the persistent store
                    synchronized(PersistObj) {
                    	PersistObj.setContents(vector);
                    	PersistObj.commit();
                    }

                    //display a dialog box that indicates that data was
                    //saved successfully
                    Dialog.inform(ReBundle.getString(APP_SUCCESS));

                    //reset the UI fields to empty
                    citizen.setText(null);
                    car.setText(null);
                    driverLicense.setText("");
            }
    };


    //create a Get menu item for users to retrieve information about the
    //last music store that they added
    private MenuItem getItem = new MenuItem(ReBundle, MENUITEM_GET, 110, 11) {
        public void run() {
            synchronized(PersistObj) {
                    vector = (Vector)PersistObj.getContents();
                    if (!vector.isEmpty()) {
                            node info = (node)vector.lastElement();
                            citizen.setText(info.getElement(node.citizenID));
                            car.setText(info.getElement(node.carID));
                            driverLicense.setText(info.getElement(node.driverID));

                    }
            }
    }
};	
/**************************** MENUS ***********************************/
	









	
	/****** VOID MAIN ******/
	public static void main(String[] args) {
		Persister app = new Persister();
		app.enterEventDispatcher();
	}
	
}
