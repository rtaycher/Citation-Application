package citation.query;

public interface IServerQuery {

	Object findDMVRecord(String _license);
	Object getCourtInfo();
	Object getGPSLocationObj();
	
}
