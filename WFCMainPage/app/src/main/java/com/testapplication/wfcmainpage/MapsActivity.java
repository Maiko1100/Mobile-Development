package com.testapplication.wfcmainpage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends Activity{

	private final LatLng LOCATION_WFC = new LatLng(52.354484, 4.841304);
	GoogleMap mMap;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);


		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

		CameraUpdate start = CameraUpdateFactory.newLatLngZoom(LOCATION_WFC, 18);


		mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
			@Override
			public View getInfoWindow(Marker marker) {
				return null;
			}

			@Override
			public View getInfoContents(Marker pMarker) {
				// Getting view from the layout file info_window_layout
				View v = getLayoutInflater().inflate(R.layout.info_window_layout, null);

				// Getting the position from the marker
				LatLng latLng = pMarker.getPosition();

				// Getting reference to the TextView to set info text
				TextView infoText = (TextView) v.findViewById(R.id.tv_info_window_text);


				// Setting the info text
				//infoText.setText());


				return v;
			}
		});

		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(LOCATION_WFC);
		mMap.animateCamera(start);
		mMap.moveCamera(start);

		Marker marker = mMap.addMarker(markerOptions);

		marker.showInfoWindow();

		mMap.setMyLocationEnabled(true);
	}

}