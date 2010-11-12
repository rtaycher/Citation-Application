package citation.output.send;

import citation.data.Citation;
import citation.data.TicketBook;

public class send {
	/**
	 * 
	 * @param currentCitation
	 * Tries to submit Citation,returns false if it fails
	 */
	public boolean submitCitation(Citation currentCitation)
	{
		return false;
		
	}
	/**
	 * returns the range of new tickets or null if it fails
	 * @return
	 */
	public TicketBook getBook()
	{
		return new TicketBook(0,0);
		
	}
	
	
}
