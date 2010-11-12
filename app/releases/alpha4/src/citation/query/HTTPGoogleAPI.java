package citation.query;

import java.io.InputStream;
import java.util.Vector;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

public class HTTPGoogleAPI {

	public class ParseType {
		public static final int ADDRESS = 1;
		public static final int CITY = 2;
		public static final int STATE = 3;
		public static final int ZIP = 4;
	}

	public static final int MIN_ACCURACY_LEVEL = 6;
	public static final int MAX_ADDRESS_RESULTS = 8;
	
	/*
	 * GEOCODE ACCURACY LEVELS 0 Unknown accuracy. 1 Country level accuracy. 2
	 * Region (state, province, prefecture, etc.) level accuracy. 3 Sub-region
	 * (county, municipality, etc.) level accuracy. 4 Town (city, village) level
	 * accuracy. 5 Post code (zip code) level accuracy. 6 Street level accuracy.
	 * 7 Intersection level accuracy. 8 Address level accuracy. 9 Premise
	 * (building name, property name, shopping center, etc.) level accuracy.
	 */

	/**
	 * ReverseGeoQuery - runs the basic geo query to Google geo query service
	 * 
	 */
	public static String ReverseGeoQuery(double lat, double lon) {

		String response = null;

		StringBuffer url = new StringBuffer();
		url.append("http://maps.google.com/maps/geo?");
		url.append("q=" + Double.toString(lat) + "," + Double.toString(lon));
		url.append("&output=json&sensor=true_or_false");

		try {

			// String result = post(url.toString());
			response = getPage(url.toString());
		}

		// TODO clean up empty catch
		catch (Exception e) {
			System.out.println("HTTPGoogleAPI.getPage Exception: "
					+ e.toString());
		}

		return response;
	}

	/*
	 * getPage - sample code from web for getting simple page results TODO clean
	 * up code for error conditions and remove the 255 response limit
	 */
	private static String getPage(String url) {

		String response = null;

		try {
			StreamConnection s = (StreamConnection) Connector.open(url);

			InputStream input = s.openInputStream();

			byte[] data = new byte[256];
			int len = 0;
			StringBuffer raw = new StringBuffer();

			while (-1 != (len = input.read(data))) {
				raw.append(new String(data, 0, len));
			}

			response = raw.toString();

			input.close();
			s.close();
		}
		// TODO clean up empty catch
		catch (Exception e) {
			System.out.println("StreamConnection Exception: " + e.toString());
		}

		return response;
	}

	/**
	 * getAddressList - return all geocode addresses with accuracy 6 or better
	 * 
	 * @param _str
	 *            - full response string to the geocode request
	 * @return vector of strings containing results
	 */
	public static String[] getAddressList(String _str)
	{
		String[] response;
		Vector addrList = new Vector();
		
		String startAddressParseKey = "\"address\": ";
		String endParseKey = ",\n";
		String accuracyParseKey = "\"Accuracy\" :";
		
		int accuracyLevel = 0;
		int beginIndex = 0;
		int endIndex = 0;
		for (int i = 0; i < MAX_ADDRESS_RESULTS; i++)
		{
			beginIndex = _str.indexOf(startAddressParseKey, beginIndex) + startAddressParseKey.length() + 1;
			endIndex = _str.indexOf(endParseKey, beginIndex) - 1;
			String fullAddress = _str.substring(beginIndex, endIndex);
			
			beginIndex = endIndex + 1;
			beginIndex = _str.indexOf(accuracyParseKey, beginIndex) + accuracyParseKey.length() + 1;
			endIndex = _str.indexOf(endParseKey, beginIndex);
			String accStr = _str.substring(beginIndex, endIndex);
			accuracyLevel = Integer.parseInt(accStr.trim());
			
			// only add items that meet minimum accuracy levels
			if ( accuracyLevel >= MIN_ACCURACY_LEVEL ) {
				addrList.addElement(fullAddress);
			}
			else {
				// remaining results will be at or below this accuracy, so we can break and return
				break;
			}
		}
		
		// transfer the vector into an appropriately sized array
		response = new String[addrList.size()];
		for ( int j = 0; j < addrList.size(); j++)
		{
			response[j] = (String)addrList.elementAt(j);
		}
		
		return response;
	}

	/**
	 * parseFullMapResult - take a geocode response, and parse out the first
	 * address into the requested parts
	 * 
	 * @param _str
	 * @param addrType
	 * @return
	 */
	public static String parseFullMapResult(String _str, int _addrType) 
	{
		String fullAddress = null;
		String[] addrList = getAddressList(_str);
		if (addrList.length <= 0)
		{
			return null;
		}	
		
		fullAddress = addrList[0];
		return parseAddressResult(fullAddress, _addrType);
	}

	/**
	 * parseAddressResult - parse a full address into it's constituent parts
	 * 
	 * @param _str
	 *            - assumes input string is a full address as street, city,
	 *            state, zip
	 * 
	 * @param addrType
	 *            - defines which portion of string to return
	 * @return
	 */
	public static String parseAddressResult(String _str, int _addrType) {
		String response = null;
		String address;
		String city;
		String state;
		String zip;

		int beginIndex = 0;
		int endIndex = 0;

		endIndex = _str.indexOf(",", beginIndex);
		address = _str.substring(beginIndex, endIndex);

		beginIndex = endIndex + 2;
		endIndex = _str.indexOf(",", beginIndex);
		city = _str.substring(beginIndex, endIndex);

		beginIndex = endIndex + 2;
		endIndex = _str.indexOf(" ", beginIndex);
		state = _str.substring(beginIndex, endIndex);

		beginIndex = endIndex + 1;
		endIndex = _str.indexOf(",", beginIndex);
		zip = _str.substring(beginIndex, endIndex);

		if ((int) _addrType == ParseType.ADDRESS) {
			response = address;
		} else if (_addrType == ParseType.CITY) {
			response = city;
		} else if (_addrType == ParseType.STATE) {
			response = state;
		} else if (_addrType == ParseType.ZIP) {
			response = zip;
		} else {
			response = _str;
		}
		return response;
	}

}
