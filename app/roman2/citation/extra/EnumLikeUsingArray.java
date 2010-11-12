package extra;

import net.rim.device.api.util.MultiMap;
import net.rim.device.api.util.ObjectEnumerator;

public class EnumLikeUsingArray implements EnumLike {

    private MultiMap emap;
    private MultiMap humanReadableMap;
    private ObjectEnumerator temp;
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
	        emap.add(slist[i],i);
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
	        emap.add(slist[i],i);
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
	    return (int)(Integer) emap.elements(key).nextElement();
    }
    
    /* (non-Javadoc)
	 * @see extra.enumLike#nextTag()
	 */
    public String nextTag(){
	    return easyIterate[pointer];
    }
    
    
}

//for (fields;fields.hasMoreElements() ;)
//nextElement()