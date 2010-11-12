
public class CitationCatcher {
	
	public int openStatus = -1;

	public int ping()
	{
		System.out.println("Got a Ping");
		return 0;
	}
	
	public int postString(String s)
	{
		System.out.println("Received: " + s);
		return 0;
	}
	
	public int postCitationAsXML(String xml)
	{
		System.out.println("Recieved XML Citation: " + xml);
		return 0;
	}
	
	public int openCitation(int citationNumber)
	{
		openStatus = citationNumber;
		System.out.println("Starting Citation Entry: " + Integer.toString(citationNumber));
		return 0;
	}
	
	public int postCitationEntry(int citationNumber, String key, String value)
	{
		if ( openStatus == citationNumber ) 
		{
			System.out.println("Adding Entry Citation: " + Integer.toString(citationNumber) + ", " + key + ":" + value);
			return 0;
		}
		else 
		{
			System.out.println("Recieved Request but Citation not opened");
			return -1;
		}
	}
	
	public void closeCitation() 
	{
		openStatus = -1;
	}
}
