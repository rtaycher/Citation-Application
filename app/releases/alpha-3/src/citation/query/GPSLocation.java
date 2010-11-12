package citation.query;

import javax.microedition.location.Criteria;
import javax.microedition.location.Location;
import javax.microedition.location.LocationException;
import javax.microedition.location.LocationListener;
import javax.microedition.location.LocationProvider;
import javax.microedition.location.QualifiedCoordinates;

public class GPSLocation {

	private Location currentLocation;
	private static int UPDATE_INTERVAL = 10;
	private static int LOCATION_TIMEOUT = 5;
	private static int MAX_AGE = 5;
	
	
	public GPSLocation(){
		
	}
	
	public void startGPSListener()
	{
		Criteria cr = new Criteria();
		cr.setAddressInfoRequired(false);
		cr.setAltitudeRequired(false);
		cr.setPreferredResponseTime(Criteria.NO_REQUIREMENT);
		cr.setSpeedAndCourseRequired(false);
		cr.setCostAllowed(false);
		cr.setHorizontalAccuracy(Criteria.NO_REQUIREMENT);
		cr.setPreferredPowerConsumption(Criteria.NO_REQUIREMENT);
		cr.setVerticalAccuracy(Criteria.NO_REQUIREMENT);
		
		// get provider
		LocationProvider provider = null;
		try {
			provider = LocationProvider.getInstance(cr);
		} catch (LocationException e) {
			System.out.println("GPSLocation.getLocation Exception: " + e.toString());				
			return;
		}		
		
		if (provider != null)
		{
			provider.setLocationListener(new LocationListenerImpl(), UPDATE_INTERVAL, LOCATION_TIMEOUT, MAX_AGE);
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
     * Implementation of the LocationListener interface.
     */
    private class LocationListenerImpl implements LocationListener
    {
        
        public void locationUpdated(LocationProvider _provider, Location _location)
        {
            if( _location.isValid() )
            {
            	// update our location position
            	currentLocation = _location;
            	// debug output
            	// System.out.println("GPSLocation: Updating location to " + _location.toString());
            }

        }

		public void providerStateChanged(LocationProvider provider, int newState) {
			// TODO Auto-generated method stub		
		}  
    }
}
