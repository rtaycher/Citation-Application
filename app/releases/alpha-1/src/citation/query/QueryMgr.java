package citation.query;

public class QueryMgr implements IServerQuery {
	
	private GPSLocation gps = null;
	
	public QueryMgr()
	{	
		gps = new GPSLocation();
		// initialize location listener
		gps.startGPSListener();
	}

	public Object getGPSLocationObj()
	{
		return gps;
	}

	public Object findDMVRecord(String _license) {
		throw new RuntimeException("findDMVRecord not implemented");
	}
}
