package citation.extra;

import java.util.Enumeration;

import net.rim.device.api.util.MultiMap;
import net.rim.device.api.util.ObjectEnumerator;

public class EnumLikeWOutArray implements EnumLike {

    private MultiMap emap;
    private MultiMap humanReadableMap;
        private Enumeration emapEnumarator;
    private void resetKeys(){
            emapEnumarator=(ObjectEnumerator)emap.keys();
    }

    public EnumLikeWOutArray(String [] slist){
            emap=new MultiMap();
            humanReadableMap= new MultiMap();
            int i;
            for(i=0;i<slist.length;i++)
                {
            emap.add(slist[i], new Integer(i));
                }
            resetKeys();
    }
        public EnumLikeWOutArray(String [] slist, String [] readablelist){
            emap=new MultiMap();
            humanReadableMap= new MultiMap();
            int i;
            for(i=0;i<slist.length;i++){
                emap.add(slist[i],new Integer(i));
            }
            for(i=0;i<readablelist.length;i++){
                humanReadableMap.add(readablelist[i],readablelist[i+1]);
            }
            resetKeys();
    }

    /*
         * The important function,returns the integer representation of key
         */
    public int enumval(String key){
            return ((Integer) emap.elements(key).nextElement()).intValue();
    }

    /*
         * returns next tag by iterating through enumeration;else returns null
         */
    public String nextTag(){
        if(emapEnumarator.hasMoreElements())
                return (String) emapEnumarator.nextElement();
        else
                return null;
    }

    public String nextReadable(){
        String tag;
        Enumeration temp;
        if(emapEnumarator.hasMoreElements())
                tag=(String) emapEnumarator.nextElement();
        else
                return null;
        temp=humanReadableMap.elements(tag);
                if(temp.hasMoreElements())
                        return (String)temp.nextElement();
                else
                        return tag;

    }

}

//for (fields;fields.hasMoreElements() ;)
//nextElement()