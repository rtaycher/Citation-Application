package extra;

import java.util.Enumeration;

import net.rim.device.api.util.MultiMap;
import net.rim.device.api.util.ObjectEnumerator;

public class EnumLikeWOutArray implements EnumLike {

    private MultiMap emap;
    private MultiMap humanReadableMap;
    private ObjectEnumerator temp;
	private ObjectEnumerator emapEnumarator;
    private void resetKeys(){
	    emapEnumarator=(ObjectEnumerator)emap.keys(); 
    }
    
    public EnumLikeWOutArray(String [] slist){
	    emap=new MultiMap();
	    humanReadableMap= new MultiMap();
	    int i;
	    for(i=0;i<slist.length;i++)
	        {
            emap.add(slist[i], i);
	        }
	    resetKeys();
    }
        public EnumLikeWOutArray(String [] slist, String [] readablelist){
	    emap=new MultiMap();
	    humanReadableMap= new MultiMap();
	    int i;
	    for(i=0;i<slist.length;i++){
	        emap.add(slist[i],i);
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
	    return (int)(Integer) emap.elements(key).nextElement();
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
    
    
}

//for (fields;fields.hasMoreElements() ;)
//nextElement()