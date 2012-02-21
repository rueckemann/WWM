package de.workshop.fff;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class GoogleMapActivity extends MapActivity {
	
	private MapView mapView;
	private MapController mapController;
	private MyLocationOverlay myLocationOverlay;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.map);
		
		mapView = (MapView) findViewById(R.id.map);
		mapView.setBuiltInZoomControls(true);
		mapController = mapView.getController();		
		
		// set the initial center of the map to Provinzialplatz
		mapController.setCenter(getGeoPoint(51.196172, 6.811779));
		mapController.setZoom(15);
		
		// define an overlay marking the current position
		myLocationOverlay = new MyLocationOverlay(this, mapView);
        mapView.getOverlays().add(myLocationOverlay);
        myLocationOverlay.enableMyLocation();
        myLocationOverlay.runOnFirstFix(new Runnable() {
            public void run() {
            	GeoPoint myLocation = myLocationOverlay.getMyLocation();
                mapController.animateTo(myLocation);					
            }
        });

        
		// register for location changes and display the current location
		final LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		final LocationListener lol = new LocationListener() {
			Location previous = null;
			public void onLocationChanged(Location loc) {
				if(loc != null && !loc.equals(previous)) {
				    final String locationString = "Your new location is: [" + loc.getLongitude() + ", " + loc.getLatitude() + "]";
					Toast.makeText(getBaseContext(), locationString, Toast.LENGTH_SHORT).show();
					previous = loc;
				}				
			}
			public void onProviderDisabled(String provider) {
			}
			public void onProviderEnabled(String provider) {
			}
			public void onStatusChanged(String provider, int status, Bundle extras) {
				
			}};		
		
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, lol);		
	}

	
	@Override
	protected void onResume() {
		super.onResume();
		myLocationOverlay.enableMyLocation();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		myLocationOverlay.disableMyLocation();
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
			
	private GeoPoint getGeoPoint(double latitude, double longitude) {
		return new GeoPoint((int)(latitude * 1e6), (int)(longitude * 1e6));
	}
	
}		
	
