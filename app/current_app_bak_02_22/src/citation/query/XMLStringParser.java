package citation.query;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;

import javax.microedition.xml.rpc.Element;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import net.rim.device.api.xml.jaxp.SAXParserImpl;
import net.rim.device.api.xml.parsers.DocumentBuilder;
import net.rim.device.api.xml.parsers.DocumentBuilderFactory;

public class XMLStringParser {

	private String xmlString;
	private Document doc = null;
	
	public XMLStringParser(String _xml)
	{
		xmlString = _xml;
		
		try {
			ByteArrayInputStream stream = new ByteArrayInputStream(xmlString.getBytes());
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        InputSource is = new InputSource();
	        is.setByteStream(stream);
	        //doc = db.parse(stream);
	        
	        doc = db.parse(is);
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	String getNamedElement(String _tagName)
	{
		String result = "";
		if (doc != null) 
		{
	        NodeList nodes = doc.getElementsByTagName(_tagName);
	        if ( nodes != null )
	        {
	        	Node line = nodes.item(0);
	        	result = line.getFirstChild().getNodeValue();
	        }
		}
        
        return result;		
	}
	
}
