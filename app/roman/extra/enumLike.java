package extra;

import net.rim.device.api.util.MultiMap;
import net.rim.device.api.util.ObjectEnumerator;

public class enumLike {

    private MultiMap emap;
    private MultiMap humanReadableMap;
    private ObjectEnumerator temp;
    private String easyIterate[];
    private int pointer;
    private boolean resetKeys(){
	pointer=0;
	temp=(ObjectEnumerator)emap.keys();
    }
    
    public enumLike(String [] slist)
    {
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
    public enumLike(String [] slist, String [] readablelist)
    {
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
    public int enumval(String key){
	return (int)(Integer) emap.elements(key).nextElement();
    }
    public STuple nextTag()
    {
	return (new STuple(startTag(easyIterate[pointer]),endTag(easyIterate[pointer])));
    }
    
    public String Capitalize(String str)
    {
	return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public String uCapitalize(String str)
    {
	return str.charAt(0) + str.substring(1).toLowerCase();
    }

    public String startTag(String str)
    {
    return "<" + str + ">" ;  
    }
    
    public String endTag(String str)
    {
    return "</" + str + ">" ;      
    }
    
    
    public String asXMLElement(String ,String )
    {
        return startTag() + +endTag();
        
    }
    
}

//for (fields;fields.hasMoreElements() ;)
//nextElement()