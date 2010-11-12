package citation.query;

import javax.microedition.location.AddressInfo;
import javax.microedition.location.Criteria;
import javax.microedition.location.Location;
import javax.microedition.location.LocationException;
import javax.microedition.location.LocationProvider;
import javax.microedition.location.QualifiedCoordinates;

public class GPSLocation {

	private Location currentLocation;
	private static int LOCATION_TIMEOUT = 60;
	
	
	public GPSLocation(){
		
	}
	
	public void fixCurrentLocation()
	{
		Criteria cr = new Criteria();
		cr.setAddressInfoRequired(false);
		cr.setAltitudeRequired(false);
		cr.setPreferredResponseTime(Criteria.NO_REQUIREMENT);
		cr.setSpeedAndCourseRequired(false);
		cr.setCostAllowed(true);
		cr.setHorizontalAccuracy(Criteria.NO_REQUIREMENT);
		cr.setPreferredPowerConsumption(Criteria.NO_REQUIREMENT);
		cr.setVerticalAccuracy(Criteria.NO_REQUIREMENT);
		
		// get provider
		LocationProvider provider = null;
		try {
			provider = LocationProvider.getInstance(cr);
		} catch (LocationException e) {
			// TODO re throw exception
			e.printStackTrace();
			return;
		}		
		
		if (provider != null)
		{
			// get and store Location
			try {
				currentLocation = provider.getLocation(LOCATION_TIMEOUT);
			} catch (LocationException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isValid()
	{
		return (currentLocation != null && currentLocation.isValid());
	}
	
	public QualifiedCoordinates getCoordinates() {
		if (isValid()) {
			return currentLocation.getQualifiedCoordinates();
		}
		return null;
	}

	/**
	 * Get a text based address.  
	 * TODO RIM documentation states that textual address info is not yet implemented
	 *      Need to backfill with a external lookup (aka google maps api)
	 *      
	 * @return null
	 */
	public AddressInfo getAddress() {
		if (isValid()) {
			return currentLocation.getAddressInfo();
		}
		return null;
	}

}
