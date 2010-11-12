package citation.extra;

import java.util.Enumeration;

import net.rim.device.api.util.MultiMap;
import net.rim.device.api.util.ObjectEnumerator;
/**
 * Version of Enum using Enumeration iteration.
 */
public class EnumLikeWOutArray implements EnumLike {

    private MultiMap emap;
    private MultiMap humanReadableMap;
    private Enumeration emapEnumarator;
/**
 * Used to rest iteration
 */
    private void resetKeys(){
            emapEnumarator=(ObjectEnumerator)emap.keys();
    }
/**
 * @param slist=list of enums
 */
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
    /**
     * @param slist=list of enums
     * @param readablelist=human readable substitues for enum tags
     */
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

    /**
     * The important function,
     * @return the integer representation of a key
     */
    public int enumval(String key){
            return ((Integer) emap.elements(key).nextElement()).intValue();
    }

    /**
     * returns next tag by iterating through enumeration
     * @ return the next tag, on error returns null
     */
    public String nextTag(){
        if(emapEnumarator.hasMoreElements())
                return (String) emapEnumarator.nextElement();
        else
                return null;
    }
    /**
     *@return either the readable tag substitute or if it doesn't exist a somewhat readable version of the tag "TAG"->"Tag: "
     */
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
