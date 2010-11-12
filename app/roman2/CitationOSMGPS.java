package citation;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;
import javax.microedition.io.*;
import java.io.*;

public class CitationOSMGPS{
	public String reverse_lookup_OSM_GPS(int latitude, int longitude, int detail)
    {
        String Query= "" + latitude + "" + longitude + "" + detail+ "";
        String QueryAddress="http://nominatim.openstreetmap.org/reverse?" + Query  ;
        QueryAddress.toLowerCase();
        fetch
        
        return ;
          
    }
	public String getFullAdress(int latitude, int longitude, int detail)
	{
		String xmldInfo=reverse_lookup_OSM_GPS(latitude,longitude,detail);
		Document reverseGPSXMLResult=(xmldInfo);
		return reverseGPSXMLResult.getElementsByTagName("result").item(0).getTextContent();	
	}
	public String getSreetAdress(int latitude, int longitude, int detail)
	{
		String xmldInfo=reverse_lookup_OSM_GPS(latitude,longitude,detail);
		Document reverseGPSXMLResult=(xmldInfo);
		return reverseGPSXMLResult.getElementsByTagName("street").item(0).getTextContent();	
	}
	public String getStateAdress(int latitude, int longitude, int detail)
	{
		String xmldInfo=reverse_lookup_OSM_GPS(latitude,longitude,detail);
		Document reverseGPSXMLResult=(xmldInfo);
		return reverseGPSXMLResult.getElementsByTagName("state").item(0).getTextContent();	
	}
}
 