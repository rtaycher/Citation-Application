package citation.xml.reader;

import java.util.Hashtable;

import citation.ui.*;

import net.rim.device.api.i18n.DateFormat;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.RadioButtonField;
import net.rim.device.api.ui.component.RadioButtonGroup;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.SeparatorField;

/**
 * The class to convert xml to gui elements for the application forms.  
 * 
 * UI Definition File Format
 *      <CitationUI>  --  top level node must match the name
 *           <Page_X> --  2nd level indicates the form page number, where X is number
 *                <Title>      -- 3rd level must always start with a form Title
 *                <ui_element> -- where ui_element equals one of the following element names
 *                
 *                <EditField>text</EditField> 	 
 *                <LabelField>text</LabelField>
 *                <CitationLabelField>text</CitationLabelField>
 *                <Separator>_</Separator> 		
 *                <CheckboxField>text</CheckboxField> 
 *                <ButtonField>text</ButtonField>   
 *                <RichTextField>text</RichTextField>
 *                <DateField>text</DateField>
 *                <ObjectChoiceField choices='choice1 choice2 choice3'>text</ObjectChoiceField>
 *                <RadioButtonField group='buttonGroup1'>text</RadioButtonField>
 *                
 *      TODO: add version number attribute to <CitationUI>
 */
public class XML2UI implements IXMLUIElements{

	// field names
	private static String EDIT_FIELD = "EditField";
	private static String LABEL_FIELD = "LabelField";
	private static String CUSTOMLABEL_FIELD = "CustomLabelField";
	private static String SEPARATOR_FIELD = "Separator";
	private static String CHECKBOX_FIELD = "CheckboxField";
	private static String BUTTON_FIELD = "ButtonField";
	private static String RICHTEXT_FIELD = "RichTextField";
	private static String DATE_FIELD = "DateField";
	private static String OBJECTCHOICE_FIELD = "ObjectChoiceField";
	private static String RADIOBUTTON_FIELD = "RadioButtonField";
	
	/* custom ui field options */
	private static String CUSTOMEDIT_FIELD = "CustomEditField";
	private static String NAVBUTTON_FIELD = "NavButtonField";
	private static String MAPBUTTON_FIELD = "MapButtonField";
	
	
	private XMLDocParser docParser = null;
	private int current_field = 0;
	private Hashtable radioGroupTable = null;
	
	
	/**
	 * XML2UI Constructor
	 * @param _xmlFilePath - indicates file name and path of UI form definition
	 */
	public XML2UI(String _xmlFilePath)
	{	
		docParser = new XMLDocParser(_xmlFilePath);
		radioGroupTable = new Hashtable();
	}
	
	public boolean isValid() 
	{
		return (docParser != null && docParser.isValid());
	}

	/**
	 * Get the form title based on XML definition.  
	 * @return - LabelField ui object.  Add to main screen
	 *           null on error
	 */
	public LabelField getTitle() 
	{
		// guard
		if ( !isValid() ) return null;
		
		// declare return value
		LabelField title_field = null;
		
		String title = docParser.getTitle();
		if ( title != null ) {
			title_field = new LabelField(title, Field.USE_ALL_WIDTH | Field.USE_ALL_HEIGHT | Field.FIELD_HCENTER )
			{
	            protected void paintBackground(net.rim.device.api.ui.Graphics g) 
	            { 
		            g.clear(); 
		            g.getColor(); 
		            g.setColor(Color.GREEN); 
		            g.fillRect(0, 0, Display.getWidth(), Display.getHeight()); 
		            g.setColor(Color.WHITE);                
	            } 
			};
			
			FontFamily fontFamily[] = FontFamily.getFontFamilies(); 
			Font font1 = fontFamily[1].getFont(FontFamily.CBTF_FONT, 14); 
			title_field.setFont(font1); 
		}
		
	    return title_field;
	}
	
	public Field getNextField()
	{
		// guard
		if ( !isValid() ) return null;

		//TODO need to iterate until end of file, or valid field
		//      current design will stop processing if encountering an unknown field
		String fieldType = docParser.getFieldType(current_field); 
		String fieldValue = docParser.getFieldTextValue(current_field);
		
		Field nextField = null;
		if ( fieldType != null && fieldValue != null )
		{
			if (fieldType.equals(LABEL_FIELD))
			{
				nextField = new LabelField(fieldValue, Field.FIELD_HCENTER);
			}
			else if (fieldType.equals(CUSTOMLABEL_FIELD))
			{
				nextField = createCustomLabelField(fieldValue, Field.USE_ALL_WIDTH);
			}
			else if (fieldType.equals(EDIT_FIELD))
			{
				nextField =  new EditField(fieldValue, "", 255, Field.HIGHLIGHT_FOCUS);
			}
			else if (fieldType.equals(CUSTOMEDIT_FIELD))
			{
				int height = docParser.getFieldAttrAsNumeric(current_field, "height");
				if (height == Integer.MIN_VALUE) height = 12;
				
				nextField =  new CustomEditField(fieldValue, "", 255, height, Field.HIGHLIGHT_FOCUS);
			}
			else if (fieldType.equals(RICHTEXT_FIELD))
			{
				nextField =  new RichTextField(fieldValue);
			}
			else if (fieldType.equals(CHECKBOX_FIELD))
			{
				nextField =  new CheckboxField(fieldValue, false);
			}
			else if (fieldType.equals(BUTTON_FIELD))
			{
				// TODO: Need to include attributes to register handlers
				nextField =  new ButtonField(fieldValue, ButtonField.CONSUME_CLICK);
			}
			else if (fieldType.equals(NAVBUTTON_FIELD))
			{
				String next_page = docParser.getFieldAttrAsString(current_field, "next");
				nextField =  new NavButtonField(fieldValue, ButtonField.CONSUME_CLICK, next_page);
			}
			else if (fieldType.equals(MAPBUTTON_FIELD))
			{
				nextField =  new MapButtonField(fieldValue, ButtonField.CONSUME_CLICK);
			}
			else if (fieldType.equals(DATE_FIELD))
			{
				nextField =  new DateField(fieldValue, System.currentTimeMillis(), DateFormat.DATE_SHORT);
			}
			else if (fieldType.equals(OBJECTCHOICE_FIELD))
			{
				String choiceArray[] = docParser.getFieldAttrAsStringArray(current_field, "choices");
				nextField =  new ObjectChoiceField(fieldValue, choiceArray, 0);
			}
			else if (fieldType.equals(RADIOBUTTON_FIELD))
			{
				// maintain a map of the button groups and map this field to it's parent group
				RadioButtonGroup buttonGroup = null;
				String buttonGroupName = docParser.getFieldAttrAsString(current_field, "group");
				if (radioGroupTable.size() > 0 && radioGroupTable.containsKey(buttonGroupName))
				{
					buttonGroup = (RadioButtonGroup)radioGroupTable.get(buttonGroupName);
					nextField = new RadioButtonField(fieldValue, buttonGroup, false);
				}
				else 
				{   // create a new group and set the first button to checked
					buttonGroup = new RadioButtonGroup();
					radioGroupTable.put(buttonGroupName, buttonGroup);
					nextField = new RadioButtonField(fieldValue, buttonGroup, true);
				}
			}
			else if (fieldType.equals(SEPARATOR_FIELD))
			{
				nextField =  new SeparatorField();
			}
		}
		
		current_field++;
		return nextField;
	}

	public int getPageCount()
	{
		// guard
		if ( !isValid() ) return 0;
		
		return docParser.getFormPageCount();
	}
	
	public String getFormPage() 
	{
		// guard
		if ( !isValid() ) return null;

		return docParser.getFormPage();
	}

	public void setFormPage(String _page) 
	{
		// guard
		if ( !isValid() || _page == null ) return;
		
		// reset the field index
		current_field = 0;
		
		// XMLDocParser checks validity of page
		docParser.setFormPage(_page);
	}
	
	
	private Field createCustomLabelField(String _fieldValue, long _defaultStyle)
	{
		// determine style and create label field
		long style = _defaultStyle;
		String styleStr = docParser.getFieldAttrAsString(current_field, "fieldStyle");
		if (styleStr != null)
		{
			style = UIHelper.stringListToStyle(styleStr);
		}
		Field labelField = new CustomLabelField(_fieldValue, style);
		
		// determine background and font color
		String colorStr = docParser.getFieldAttrAsString(current_field, "fieldColor");
		if (colorStr != null)
		{
			((CustomLabelField)labelField).setBackgroundColor(colorStr);
		}
		colorStr = docParser.getFieldAttrAsString(current_field, "fontColor");
		if (colorStr != null)
		{
			((CustomLabelField)labelField).setTextColor(colorStr);
		}
		
		// set font size and style for this field
		int fontSize;
		int fontStyle = Font.PLAIN;
		styleStr = docParser.getFieldAttrAsString(current_field, "fontStyle");
		if (styleStr != null)
		{
			fontStyle = UIHelper.stringListToFontStyle(styleStr);
		}
		
		fontSize = docParser.getFieldAttrAsNumeric(current_field, "fontSize");
		if (fontSize == Integer.MIN_VALUE) fontSize = 12;
		
		Font fieldFont = UIHelper.getFont(fontSize, fontStyle);
		labelField.setFont(fieldFont);
		
		return labelField;
	}
}
