package citation.query;

public class QueryMgr implements IServerQuery {
	
	public QueryMgr()
	{		
	}

	public Object getCurrentLocation()
	{
		GPSLocation gps = new GPSLocation();
		gps.fixCurrentLocation();
		if (gps.isValid()) {
			return gps;
		}
		return null;
	}

	public Object findDMVRecord(String _license) {
		throw new RuntimeException("findDMVRecord not implemented");
	}
}
