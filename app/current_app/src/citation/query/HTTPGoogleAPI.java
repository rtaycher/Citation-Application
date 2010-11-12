/*
 * HTTPGoogleAPI.java
 *
 * Copyright (c) 2009-2010 PSU Capstone Team D
 * Scott Glazer, Cong Hoang, Ba Nguyen, Marek Dolgos,
 * Steve Phelps, Mark Smith, Roman Taycher
 *
 * Citation Application is free/open source software released under
 * the unmodified MIT/X11 license. A copy can be found in the
 * LICENSE file or at:
 *
 *     http://www.opensource.org/licenses/mit-license.php
 *
 */
package citation.query;

import java.io.InputStream;
import java.util.Vector;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;


/**
 * HTTPGoogleAPI class - provides reverse geocode lookup through Google api
 *                       All methods are static and provide helper functions for address
 *                       lookup.
 *                       
 *                       Perform operation in the following sequence:
 *                       ---------------------------------------------
 *                       1. ReverseGeoQuery - get address list from a lat/lon 
 *                       2. getAddressList - parse results from #1, and return an array of addresses

 */
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
	 * @param lat - latitude of lookup location
	 * @param lon - longitute of lookup location
	 * 
	 * @return - String containing the raw Google lookup results
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
		StreamConnection s = null;
		
		try {
			// try wifi network connection first
			s = (StreamConnection) Connector.open(url + ";deviceside=true;interface=wifi");			
		}
		catch (Exception e) {
			// no-op; failure to open a wi-fi connection is quite likely
		}
		
		try {
			// wi-fi failed so try normal network connection
			if (s == null) {
				s = (StreamConnection) Connector.open(url);
			}
			
			// still no good - time to bail
			if (s == null) return response;
			
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
	 * @param _str - full response string to the geocode request
	 * @return - array of strings containing address parse results from previous Google query
	 */
	public static String[] getAddressList(String _str)
	{
		String[] response = null;
		Vector addrList = new Vector();
		
		String startAddressParseKey = "\"address\": ";
		String endParseKey = ",\n";
		String accuracyParseKey = "\"Accuracy\" :";
		
		int accuracyLevel = 0;
		int beginIndex = 0;
		int endIndex = 0;
		for (int i = 0; i < MAX_ADDRESS_RESULTS; i++)
		{
			try {
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
			
			// one of the parse functions failed, so bail as the results are unknown and there's no
			// simple way to recover and pick up where we left off.  
			catch(IndexOutOfBoundsException ex) 
			{
				break;
			}
			catch (NumberFormatException ex)
			{
				break;
			}
		}
		
		// transfer the vector into an appropriately sized array
		if (addrList.size() > 0) 
		{
			response = new String[addrList.size()];
			for ( int j = 0; j < addrList.size(); j++)
			{
				response[j] = (String)addrList.elementAt(j);
			}
		}
		
		return response;
	}

	
	/**
	 * parseFullMapResult - take a geocode response, and parse out an
	 *                      address into the requested parts
	 * 
	 * @param _str - full address string from a Google query
	 * @param addrType - enum from HTTPGoogleAPI indicating which part of an address to get
	 * 
	 * @return - string of the address part requested
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
	 * @param _str - assumes input string is a full address as street, city,
	 *               state, zip
	 * 
	 * @param addrType - defines which portion of string to return
	 * 
	 * @return - string of portion of address requested
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
