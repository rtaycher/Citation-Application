/**
 * 
 */
package citation.xml.reader;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.LabelField;


/**
 * XMLUIElements provides the interface definitions for the GUI and XML parser
 * 
 * @author Mark Smith
 */
public interface IXMLUIElements {

	/* provides check that requested XML file definition was valid (parsed without error) */
	public boolean isValid();
	
	/* set or get the current form page number for the parsed file */
	public int getPageCount();
	public String getFormPage();
	public void setFormPage(String _page);

	/* get title and iterate through each of the fields in the form */
	public LabelField getTitle();	
	public Field getNextField();
	
	/* TODO: do we need an iterator accessor to retrieve a specific field at runtime(?) */
	
}
