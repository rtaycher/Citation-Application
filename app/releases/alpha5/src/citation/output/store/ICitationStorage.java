package citation.output.store;

public interface ICitationStorage {

	public void writeCitation(Object _record);
	public void readCitation(Object _record);
	
}
