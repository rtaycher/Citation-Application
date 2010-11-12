package citation.ui;

import net.rim.device.api.ui.component.ButtonField;


public class NavButtonField extends ButtonField {
	
	private String next_page;
	
	public NavButtonField(String value, long style, String _next)
	{
		super(value, style);
		next_page = new String(_next);
	}
	
	public String getNextPage()
	{
		return next_page;
	}
}
