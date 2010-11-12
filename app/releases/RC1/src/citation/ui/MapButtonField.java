package citation.ui;

import javax.microedition.location.QualifiedCoordinates;

import net.rim.blackberry.api.invoke.Invoke;
import net.rim.blackberry.api.invoke.MapsArguments;
import net.rim.blackberry.api.maps.MapView;
import net.rim.device.api.ui.component.ButtonField;

public class MapButtonField extends ButtonField {

	private int lat = 4542349;
	private int lon = -7569792;
	private int zoomRatio = 2;
	
    public MapButtonField(String fieldValue, long consumeClick) {
		super(fieldValue, consumeClick);
	}

	public void run() 
    {
        MapView mapview = new MapView();
        mapview.setLatitude(lat);
        mapview.setLongitude(lon);
        mapview.setZoom(this.zoomRatio);
        
        // Invoke maps application with specified MapView.
        Invoke.invokeApplication(Invoke.APP_TYPE_MAPS, new MapsArguments(mapview));
    }

	public void setCoordinates(QualifiedCoordinates _where) {
		if (_where != null)
		{
			lat = (int)(_where.getLatitude() * 100000);
			lon = (int)(_where.getLongitude() * 100000);
		}
	}

	public void setZoom(int zoom) {
		this.zoomRatio = zoom;
	}

}
