/*
 * CitationStore.java
 *
 * Copyright (c) 2009-2010 PSU Capstone Team D
 * Scott Glazer, Cong Hoang, Ba Nguyen, Marek Dolgos,
 * Steve Phelps, Mark Smith, Roman Taycher
 *
 * Citation Application is free/open source software released under
 * the unmodified MIT/X11 license. A copy can be found in the
 * LICENSE file or at:
 *
 *     http://www.opensource.org/licenses/mit-license.php
 *
 */
package citation.data;

import java.util.Vector;

import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.util.Persistable;

import java.rmi.RemoteException;
import java.util.Vector;
import net.rim.device.api.system.*;
import citation.data.*;
import citation.ui.CustomLabelField;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;


public final class CitationStore implements Persistable {
    public static Vector List;    
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
        if (idx >= 0 && idx < CitationStore.List.size())
            return (Citation)CitationStore.List.elementAt(idx);
        else
            return null;
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
    
    public static boolean Remove(int index) {
        if(CitationStore.List.isEmpty()){
             Dialog.inform("Nothing to delete.");
             return false;
        }
        if(CitationStore.List.elementAt(index).equals(null)){
             Dialog.inform("Error:  Invalid index.");
             return false;
        }
        CitationStore.List.removeElementAt(index);
        return true;
    }    
    
    public static Citation getNewCitation() {
        Citation c = new Citation();
        c.Number = 1000 + getCitationCount() + 1;
        CitationStore.List.addElement(c);
        return c;
    }
}
