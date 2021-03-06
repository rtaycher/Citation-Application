package citation.data;

import java.util.Vector;

import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.util.Persistable;



public final class CitationStore implements Persistable {
    public static Vector List;    

    //private static Vector List;
    private static PersistentObject store;
    
       
    static {
        store = PersistentStore.getPersistentObject(0xdec6a67096f833cL);
    
        synchronized (store) {
            if (store.getContents() == null) {
                store.setContents(new Vector());
                store.commit();
            }
        }
        List = new Vector();
        List = (Vector) store.getContents();
    }
    
    public static int getCitationCount() {
        return CitationStore.List.size();
    }

    public static Citation getCitationByIndex(int idx) {
        return (Citation)CitationStore.List.elementAt(idx);
    }
    
    public static Citation getCitation(int citation_number){
        int count = CitationStore.List.size();
        for (int i =0; i < count; i++) {
                Citation c = (Citation) CitationStore.List.elementAt(i);
                if (citation_number == c.Number)  {
                        return c;
                }
        }
        return null;
    }
    
    public static void Save() {
        synchronized(store) {
                store.commit();
        }
    }
    
    public static void Restore() {
        synchronized(store) {
                List = (Vector) store.getContents();
        }
    }
    
    public static Citation getNewCitation() {
        Citation c = new Citation();
        c.Number = 1000 + getCitationCount() + 1;
        CitationStore.List.addElement(c);
        return c;
    }
    
    public static void removeCitationByIndex(int idx){
    	CitationStore.List.removeElementAt(idx);
    	//Citation c =(Citation)CitationStore.List.elementAt(idx);
    }
}
