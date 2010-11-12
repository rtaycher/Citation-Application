
public class CitationCatcher {
	
	public int openStatus = -1;

	public int ping()
	{
		//System.out.println("Got a Ping");
		return 0;
	}
	
	public int postString(String s)
	{
		//System.out.println("Received: " + s);
		return 0;
	}
	
	public int postCitationAsXML(String xml)
	{
		//System.out.println("Recieved XML Citation: " + xml);
		return 0;
	}
	
	public int openCitation(int citationNumber)
	{
		openStatus = citationNumber;
		//System.out.println("Starting Citation Entry: " + Integer.toString(citationNumber));
		return 0;
	}
	
	public int postCitationEntry(int citationNumber, String key, String value)
	{
		if ( openStatus == citationNumber ) 
		{
			//System.out.println("Adding Entry Citation: " + Integer.toString(citationNumber) + ", " + key + ":" + value);
			return 0;
		}
		else 
		{
			//System.out.println("Recieved Request but Citation not opened");
			return -1;
		}
	}
	
	public void closeCitation() 
	{
		openStatus = -1;
	}
	
	public String getDMVResultAsXML(String _license)
	{
		// TODO: link to server side lookup - for now return fixed result
		StringBuffer result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		
			result.append("<DMV>\n");
			result.append("<Fname>GEORGIA</Fname>\n");
			result.append("<Mname>CAROL</Mname>\n");
			result.append("<Lname>ZAPPENHEIMER</Lname>\n");
			result.append("<Address>123 S CLEMATIS RD</Address>\n");
			result.append("<City>GLADDEN FIELD</City>\n");
			result.append("<State>OR</State>\n");
			result.append("<Zip>97068</Zip>\n");
			result.append("<DOB>12-06-1945</DOB>\n");
			result.append("<Sex>F</Sex>\n");
			result.append("<Height>5-01</Height>\n");
			result.append("<Weight>158</Weight>\n");
			result.append("<Endorsements>C</Endorsements>\n");
			result.append("<Restrictions>BD</Restrictions>\n");
			result.append("</DMV>\n");

		//System.out.println("Recieved Request for DMV record: " + _license);
		return result.toString();
	}
}
