package citation.xml.reader;

import java.io.InputStream;
import java.util.Hashtable;

import net.rim.device.api.collection.util.SparseList;
import net.rim.device.api.util.StringUtilities;
import net.rim.device.api.xml.parsers.DocumentBuilder;
import net.rim.device.api.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The main xml parser for the application forms.  Displays the results of parsing the XML file.
 * 
 * This class assumes a xml format in the following form
 *      <CitationUI>  --  top level node must match the name
 *           <Page_X> --  2nd level indicates the form page number, where X is number
 *                <Title>      -- 3rd level must always start with a form Title
 *                <ui_element> -- 3rd level indicates a specific form element to render (<Edit>, <Choice>, <Radio>, <Label>)
 *                                see XML2UI.java for list of valid <ui_element> and attributes
 *      TODO: add version number attribute to <CitationUI>
 */
public class XMLDocParser {
	
	private static String FORM_NAME = "CitationUI";
	private static String PAGE_INDICATOR = "Page_";
	private static String TITLE_NAME = "Title";
	
	class XMLFormPage {
		public Node pageNode;
		public Node titleNode;
		public SparseList fieldElements; 
		
		public XMLFormPage(Node _pageNode)
		{
			this.pageNode = _pageNode;
			this.fieldElements = new SparseList();
		}
	}
	
	private Document xmlDoc = null;
	private Hashtable xmlFormPageTable;
	private String currentPageKey = new String();
	private String startPageKey = new String();
	
	/**
     * This constructor parses the XML file into a W3C DOM document and makes it's contents available
     * 
     * @param _xmlFileName - pathname and file to be parsed
     */
	public XMLDocParser(String _xmlFileName) {
        try 
        {
            // Build a document based on the XML file.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream inputStream = getClass().getResourceAsStream( _xmlFileName );
            
            xmlDoc = builder.parse( inputStream );   
            if (xmlDoc != null)
            {
            	if ( validateFormVersion() )
            	{
            		// parse the entire tree and create a node list
            		xmlFormPageTable = new Hashtable();
            		parseNodes(xmlDoc.getChildNodes().item(0), 0);
            		
            		// reset to the start page
            		currentPageKey = startPageKey;
            	}
            	else 
            	{
            		xmlDoc = null;            		
            	}
            }
        } 
        catch ( Exception e ) 
        {
            System.out.println( e.toString() );
        }
	}
	
	
	public boolean isValid() {
		return (xmlDoc != null);
	}
	
	private boolean validateFormVersion() 
	{
		Node node = xmlDoc.getChildNodes().item(0);
		return node.getNodeName().equals(XMLDocParser.FORM_NAME);
	}

	public int getFormPageCount()
	{
		// guard - ensures we've successfully parsed the xml file
		if (!isValid()) { return 0; }

		return xmlFormPageTable.size();
	}
	
	public void setFormPage(String _page)
	{
		// guard - ensures we've successfully parsed the xml file
		if (!isValid()) { return; }

		// ensure _page is a valid page tag
		if ( this.xmlFormPageTable.containsKey(_page) )
		{
			this.currentPageKey = _page;
		}
	}
	
	public String getFormPage()
	{
		// guard - ensures we've successfully parsed the xml file
		if (!isValid()) { return null; }

		return this.currentPageKey;
	}
	
	public String getTitle() 
	{	
		// guard - ensures we've successfully parsed the xml file
		if (!isValid()) { return null; }
		
		// the text value is stored in the child node of the element
		Node titleNode = ((XMLFormPage)xmlFormPageTable.get(currentPageKey)).titleNode;
		NodeList childNodes = titleNode.getChildNodes();
		Node firstChild = childNodes.item(0);
		return firstChild.getNodeValue();		
	}

	public int getFieldCount()
	{
		return ((XMLFormPage)xmlFormPageTable.get(currentPageKey)).fieldElements.size();
	}
	
	public String getFieldType(int idx)
	{
		// guard - ensures we've successfully parsed the xml file
		if (!isValid()) { return null; }

		// make sure request is in range
		if (idx >= getFieldCount())
		{
			return null;
		}
		
		// the text value is stored in the child node of the element
		SparseList fieldList = ((XMLFormPage)xmlFormPageTable.get(currentPageKey)).fieldElements;
		Node fieldNode = (Node)(fieldList.get(idx));
		return fieldNode.getNodeName();				
	}
	
	public String getFieldTextValue(int idx)
	{
		// guard - ensures we've successfully parsed the xml file
		if (!isValid()) { return null; }

		// make sure request is in range
		if (idx >= getFieldCount())
		{
			return null;
		}
		
		// the text value is stored in the child node of the element
		SparseList fieldList = ((XMLFormPage)xmlFormPageTable.get(currentPageKey)).fieldElements;
		Node fieldNode = (Node)(fieldList.get(idx));
		NodeList childNodes = fieldNode.getChildNodes();
		Node firstChild = childNodes.item(0);
		return firstChild.getNodeValue();				
	}
	
	public String[] getFieldAttrAsStringArray(int idx, String attrText)
	{
		// guard - ensures we've successfully parsed the xml file
		if (!isValid()) { return null; }

		// make sure request is in range
		if (idx >= getFieldCount())
		{
			return null;
		}
		
		// the text value is stored in the child node of the element
		SparseList fieldList = ((XMLFormPage)xmlFormPageTable.get(currentPageKey)).fieldElements;
		Node fieldNode = (Node)(fieldList.get(idx));
		NamedNodeMap attrNamedNode = fieldNode.getAttributes();
		Node listNode = attrNamedNode.getNamedItem(attrText);
		if (listNode == null) return null;
		
		String listText = listNode.getNodeValue();
		
		return StringUtilities.stringToKeywords(listText);						
	}
	
	public String getFieldAttrAsString(int idx, String attrText)
	{
		// guard - ensures we've successfully parsed the xml file
		if (!isValid()) { return null; }

		// make sure request is in range
		if (idx >= getFieldCount())
		{
			return null;
		}
		
		// the text value is stored in the child node of the element
		SparseList fieldList = ((XMLFormPage)xmlFormPageTable.get(currentPageKey)).fieldElements;
		Node fieldNode = (Node)(fieldList.get(idx));
		NamedNodeMap attrNamedNode = fieldNode.getAttributes();
		Node attrNode = attrNamedNode.getNamedItem(attrText);
		if (attrNode == null) return null;
			
		return attrNode.getNodeValue();		
	}
	
	public int getFieldAttrAsNumeric(int idx, String attrText)
	{
		// guard - ensures we've successfully parsed the xml file
		if (!isValid()) { return Integer.MIN_VALUE; }

		// make sure request is in range
		if (idx >= getFieldCount())
		{
			return Integer.MIN_VALUE;
		}
		
		// the text value is stored in the child node of the element
		SparseList fieldList = ((XMLFormPage)xmlFormPageTable.get(currentPageKey)).fieldElements;
		Node fieldNode = (Node)(fieldList.get(idx));
		NamedNodeMap attrNamedNode = fieldNode.getAttributes();
		Node attrNode = attrNamedNode.getNamedItem(attrText);
		if (attrNode == null) return Integer.MIN_VALUE;
		
		String nodeValue = attrNode.getNodeValue();

		return Integer.parseInt(nodeValue);					
	}
	
	/**
	 * parseNodes - parsing the complete XML file and it's descendants.  adds elements to UI array
	 * 
     * @param node The node to display.
     * @param depth The depth of this node in the document tree.
	 */
    private void parseNodes( Node node, int depth ) 
    {        
        // Because we can inspect the XML file, we know that it contains only XML elements
        // and text, so this algorithm is written specifically to handle these cases.  
        // A real-world application will be more robust, and will handle all node types. 
        // See the entire list in org.w3c.dom.Node.      
        // The XML file is laid out such that each Element node will either have one Text 
        // node child (e.g. <Element>Text</Element>), or >= 1 children consisting of at 
        // least one Element node, and possibly some Text nodes.  Start by figuring out
        // what kind of node we're dealing with.
        if ( node.getNodeType() == Node.ELEMENT_NODE ) 
        {
            StringBuffer buffer = new StringBuffer();
            NodeList childNodes = node.getChildNodes();
            int numChildren = childNodes.getLength();
            Node firstChild = childNodes.item( 0 );
            
            // If the node has only one child and that child is a Text node, then it's of 
            // the form  <Element>Text</Element>, so print 'Element = "Text"'.
            if ( numChildren == 1 && firstChild.getNodeType() == Node.TEXT_NODE ) 
            {
            	// debug fun
                buffer.append( node.getNodeName() ).append( " = \"" ).append( firstChild.getNodeValue() ).append( '"' );
            	System.out.println(buffer.toString());
            	
            	// the real work - if it's a title node, add it - otherwise add to the field element list
            	if (node.getNodeName().equals(TITLE_NAME))
            	{
            		((XMLFormPage)(xmlFormPageTable.get(currentPageKey))).titleNode = node;
            	}
            	else
            	{
            		((XMLFormPage)(xmlFormPageTable.get(currentPageKey))).fieldElements.add(node);
            	}
            } 
            else 
            {
                // The node either has > 1 children, or it has at least one Element node child. 
                // Either way, its children have to be visited.  Print the name of the element
                // and recurse.
            	
            	// debug fun
                buffer.append( node.getNodeName() );
            	System.out.println(buffer.toString());
                
            	// the real work
            	if ( node.getNodeName().indexOf(PAGE_INDICATOR) == 0)
            	{
            		// set the start page as the first page definition encountered
            		if (startPageKey.length() == 0)
            		{
            			startPageKey = node.getNodeName();
            		}
            		// new page marker - create a new form page
            		currentPageKey = node.getNodeName();
            		xmlFormPageTable.put(currentPageKey, new XMLFormPage(node));
            	}
            	
                // Recursively visit all this node's children.
                for ( int i = 0; i < numChildren; ++i ) 
                {
                    parseNodes( childNodes.item( i ), depth + 1 );
                }
            }
        } 
    }
}
