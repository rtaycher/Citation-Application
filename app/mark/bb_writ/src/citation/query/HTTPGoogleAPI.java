package citation.query;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.ContentConnection;
import javax.microedition.io.HttpConnection;
import javax.microedition.io.StreamConnection;
import javax.microedition.location.AddressInfo;


public class HTTPGoogleAPI {
	
	public class ParseType {
		public static final int ADDRESS = 1;
		public static final int CITY = 2;
		public static final int STATE = 3;
		public static final int ZIP = 4;
	}
	
	public static String ReverseGeoQuery(double lat, double lon) {

		String response = null;
		
		StringBuffer url = new StringBuffer();
		url.append("http://maps.google.com/maps/geo?");
		url.append("q=" + Double.toString(lat) + "," + Double.toString(lon));
		url.append("&output=json&sensor=true_or_false");
		
		try {
			
			//String result = post(url.toString());
			response = getPage(url.toString());
		}

		// TODO clean up empty catch
		catch (Exception e) {
			System.out.println("HTTPGoogleAPI.getPage Exception: " + e.toString());
		}
		
		return response;
	}

	/*
	 * getPage - sample code from web for getting simple page results
	 * TODO clean up code for error conditions and remove the 255 response limit
	 */
	private static String getPage(String url) {
		
		String response = null;

		try {
			StreamConnection s = (StreamConnection)Connector.open(url);

			InputStream input = s.openInputStream();

			byte[] data = new byte[256];
			int len = 0;
			StringBuffer raw = new StringBuffer();

			while( -1 != (len = input.read(data))) {
				raw.append(new String(data, 0, len));
			}

			response = raw.toString();

			input.close();
			s.close();
		} 
		// TODO clean up empty catch
		catch(Exception e) { 
			System.out.println("StreamConnection Exception: " + e.toString());			
		}

		return response;
	}
	

	/*
	 * Sample code from JAVA SDK
	 */
	private static String post(String url) throws Exception {

		try {

			InputStream is;

			HttpConnection http = (HttpConnection) Connector.open(url, Connector.READ_WRITE, true);

			http.setRequestMethod(HttpConnection.POST);

			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			byte[] data = new byte[256];
			http.setRequestProperty("Content-length", "" + data.length);

			OutputStream out = http.openOutputStream();

			out.write(data);

			out.flush();

			String response = null;

			byte[] buff;

			int rc = http.getResponseCode();

			if (rc == HttpConnection.HTTP_OK) {

				int len = (int) http.getLength();

				is = http.openInputStream();

				if (len != -1) {

					buff = new byte[len];

					is.read(buff);

				}

				else {

					ByteArrayOutputStream baos = new ByteArrayOutputStream();

					int ch;

					while ((ch = is.read()) != -1) {

						baos.write(ch);

					}

					buff = baos.toByteArray();

					baos.close();

				}

				response = new String(buff);

			}

			else {

				throw new IOException("HTTP response code: " + rc);

			}

			http.close();

			return response;

		}

		catch (Exception e) {

			throw new Exception("Can't send http post request" );

		}

	}
	
	/*
	 * TODO add exception / error handling for bad string results
	 *      (index out of range, etc)
	 */
	public static String parseMapResult(String _str, int addrType )
	{
		String response = null;
		String startParseKey = "address";
		String endParseKey = ",\n";
		
		int beginIndex = _str.indexOf(startParseKey) + startParseKey.length() + 3;
		int endIndex = _str.indexOf(endParseKey, beginIndex);
		
		String fullAddress = _str.substring(beginIndex, endIndex);
		
		String address;
		String city;
		String state;
		String zip;
		
		beginIndex = 0;
		endIndex = fullAddress.indexOf(",", beginIndex);
		address = fullAddress.substring(beginIndex, endIndex);
		
		beginIndex = endIndex + 2;
		endIndex = fullAddress.indexOf(",", beginIndex);
		city = fullAddress.substring(beginIndex, endIndex);
		
		beginIndex = endIndex + 2;
		endIndex = fullAddress.indexOf(" ", beginIndex);
		state = fullAddress.substring(beginIndex, endIndex);
		
		beginIndex = endIndex + 1;
		endIndex = fullAddress.indexOf(",", beginIndex);
		zip = fullAddress.substring(beginIndex, endIndex);
		
		
		if ((int)addrType == ParseType.ADDRESS)
		{
			response = address;
		}
		else if (addrType == ParseType.CITY)
		{
			response = city;
		}
		else if (addrType == ParseType.STATE)
		{
			response = state;
		}
		else if (addrType == ParseType.ZIP)
		{
			response = zip;			
		}
		else 
		{
			response = fullAddress;
		}
		return response;
	}
}
