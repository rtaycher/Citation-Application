package citation.extra;

import java.util.Enumeration;

import net.rim.device.api.util.MultiMap;
import net.rim.device.api.util.ObjectEnumerator;

public class EnumLikeUsingArray implements EnumLike {

    private MultiMap emap;
    private MultiMap humanReadableMap;
    private String easyIterate[];
    private int pointer;
    private void resetKeys(){
            pointer=0;
    }

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

    /* (non-Javadoc)
         * @see extra.enumLike#enumval(java.lang.String)
         */
    public int enumval(String key){
            return ((Integer) emap.elements(key).nextElement()).intValue();
    }

    /* (non-Javadoc)
         * @see extra.enumLike#nextTag()
         *
         */
    public String nextTag(){
            return easyIterate[pointer];
    }

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
