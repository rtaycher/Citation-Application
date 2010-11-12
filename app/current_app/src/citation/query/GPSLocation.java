/*
 * GPSLocation.java
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

import javax.microedition.location.Criteria;
import javax.microedition.location.Location;
import javax.microedition.location.LocationException;
import javax.microedition.location.LocationListener;
import javax.microedition.location.LocationProvider;
import javax.microedition.location.QualifiedCoordinates;


/**
 * GPSLocation class - provides current GPS coordinates through means of starting
 *                     a listener which updates the location at a periodic interval
 *                     (default is to update every 5 seconds).  To use, construct
 *                     class and then call startGPSListener to initiate the listener.
 *                     After the 1st update within 5 seconds, clients can call
 *                     getCoordinates to determine the current lat/lon values for the
 *                     device.
 */
public class GPSLocation {

	private Location currentLocation;
	private static int UPDATE_INTERVAL = 5;
	private static int LOCATION_TIMEOUT = 1;
	private static int MAX_AGE = 3;
	
	
	public GPSLocation(){
		
	}
	
	/**
	 * startGPSListener - start up the GPS listener.  Once started, a periodic
	 *                    callback event from the GPS service will update the 
	 *                    location coordinates.  
	 */
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

	
	/**
	 * isValid - determine if currentLocation is a valid GPS coordinate
	 * @return - true/false
	 */
	public boolean isValid()
	{
		return (currentLocation != null && currentLocation.isValid());
	}
	
	
	/**
	 * getCoordinates - provide the coordinates as a QualifiedCoordinates class
	 *                  of the current location
	 * @return QualifiedCoordinates class
	 */
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
            }

        }

		public void providerStateChanged(LocationProvider provider, int newState) {
			// TODO Auto-generated method stub		
		}  
    }
}
