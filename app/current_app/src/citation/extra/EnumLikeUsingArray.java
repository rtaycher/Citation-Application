package citation.extra;

import java.util.Enumeration;

import net.rim.device.api.util.MultiMap;
import net.rim.device.api.util.ObjectEnumerator;
/**
 * version of enum using array
 */
public class EnumLikeUsingArray implements EnumLike {

    private MultiMap emap;
    private MultiMap humanReadableMap;
    private String easyIterate[];
    private int pointer;
    private void resetKeys(){
            pointer=0;
    }
    /**
     * @param slist=list of enums
     */
    public EnumLikeUsingArray(String [] slist){
            emap=new MultiMap();
            humanReadableMap= new MultiMap();
            int i;
            easyIterate=new String[slist.length];
            for(i=0;i<slist.length;i++)
                {
                emap.add(slist[i],new Integer(i));
                easyIterate[i]=slist[i];
                }
            resetKeys();
    }
    /**
     * @param slist=list of enums
     * @param readablelist=human readable substitues for enum tags
     */
        public EnumLikeUsingArray(String [] slist, String [] readablelist){
            emap=new MultiMap();
            humanReadableMap= new MultiMap();
            int i;
            easyIterate=new String[slist.length];
            for(i=0;i<slist.length;i++)
            {
                emap.add(slist[i],new Integer(i));
                easyIterate[i]=slist[i];
            }
            for(i=0;i<readablelist.length;i++)
            {
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
     * returns next tag by iterating through the array, updating the offset int
     * @ return the next tag, on error returns null
     */
    public String nextTag(){
            return easyIterate[pointer];
    }
    /**
     *@return either the readable tag substitute or if it doesn't exist a somewhat readable version of the tag "TAG"->"Tag: "
     */
    public String nextReadable(){
        String tag;
        Enumeration temp;
        if(pointer>easyIterate.length)
                tag=easyIterate[pointer];
        else
                return null;
        temp=humanReadableMap.elements(tag);
                if(temp.hasMoreElements())
                        return (String)temp.nextElement();
                else
                        return tag;

    }

}
