package com.testapplication.wfcmainpage;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends Activity{

	private final LatLng LOCATION_WFC = new LatLng(52.354484, 4.841304);
	private GoogleMap map;
	private static final String TAG = "MijnLog";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		Log.i(TAG, "Fragment");
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		CameraUpdate start = CameraUpdateFactory.newLatLngZoom(LOCATION_WFC, 18);
		Log.i(TAG, "CameraUpdate");
		map.moveCamera(start);
		map.animateCamera(start);
		Log.i(TAG, "Move and animate camera");
		map.addMarker(new MarkerOptions().position(LOCATION_WFC).title("World Fashion Center"));
	}
}