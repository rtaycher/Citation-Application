/*
 * XMLStringParser.java
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


/**
 * XMLStringParser class - simple xml parser class.  Allows input of XML as a string object
 *                         for later of named elements named elements
 *
 */
public class XMLStringParser {

	private String xmlString;
	private Document doc = null;
	
	
	/**
	 * XMLStringParser constructor - construct the XMLStringParser object using the 
	 *                               input string formatted in XML
	 *                               
	 *                               xml parser validation silently fails
	 *                               the XMLStringParser object is valid, but calls to
	 *                               getNamedElement return empty string
	 *                                
	 * @param _xml - string containing xml formatted elements <name>hi</name>
	 */
	public XMLStringParser(String _xml)
	{
		xmlString = _xml;
		
		try {
			ByteArrayInputStream stream = new ByteArrayInputStream(xmlString.getBytes());
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        InputSource is = new InputSource();
	        is.setByteStream(stream);
	        
	        doc = db.parse(is);
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	/**
	 * getNamedElement - return the value of the named element in the XML string
	 * 
	 * @param _tagName - name of element to get value on (example: getNamedElement("name") for <name>
	 * 
	 * @return - string of named element value
	 *           empty string if named element does not exist
	 *           empty string if xml parsing failed
	 */
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
