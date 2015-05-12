package com.testapplication.wfcmainpage.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.VisibleRegion;
import com.testapplication.wfcmainpage.R;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class NavigationIndoorMapActivity extends ActionBarActivity implements View.OnClickListener {

	private final LatLngBounds mMobiBounds = new LatLngBounds(new LatLng(52.3713710997303, 4.88609969615936), new LatLng(52.371600, 4.886524));
	private final String LOCATION_MOBI_STRING = "Herengracht 266 1016 BV Amsterdam";
	private final int MAX_ZOOM = 18;
	private final int MIN_ZOOM = 14;
	private final GoogleMap mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	private OverscrollHandler mOverscrollHandler = new OverscrollHandler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navigation_indoor_map);
		setTitle(getString(R.string.indoor_maps_title_text));

		ImageButton buttonCar = (ImageButton) findViewById(R.id.buttonCar);
		buttonCar.getDrawable().setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP);
		ImageButton buttonWalk = (ImageButton) findViewById(R.id.buttonWalk);
		buttonWalk.getDrawable().setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP);
		ImageButton buttonBicycle = (ImageButton) findViewById(R.id.buttonBicycle);
		buttonBicycle.getDrawable().setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP);
		ImageButton buttonOV = (ImageButton) findViewById(R.id.buttonOV);
		buttonOV.getDrawable().setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP);

		buttonCar.setOnClickListener(this);
		buttonWalk.setOnClickListener(this);
		buttonBicycle.setOnClickListener(this);
		buttonOV.setOnClickListener(this);


		mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

		// Zoom on location on start
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mMobiBounds.getCenter(), 20));

		// Set custom marker text
		mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
			@Override
			public View getInfoWindow(Marker marker) {
				return null;
			}

			@Override
			public View getInfoContents(Marker pMarker) {
				// Getting view from the layout file info_window_layout
				View v = getLayoutInflater().inflate(R.layout.info_window_layout, null);

				return v;
			}
		});

		// Show custom marker
		GroundOverlayOptions mobiMap = new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.plattegrond)).positionFromBounds(mMobiBounds);
		mMap.addGroundOverlay(mobiMap);
	}

	/**
	 * Returns the correction for Lat and Lng if camera is trying to get outside of visible map
	 * @param cameraBounds Current camera bounds
	 * @return Latitude and Longitude corrections to get back into bounds.
	 */
	private LatLng getLatLngCorrection(LatLngBounds cameraBounds) {
		double latitude=0, longitude=0;
		if(cameraBounds.southwest.latitude < mMobiBounds.southwest.latitude) {
			latitude = mMobiBounds.southwest.latitude - cameraBounds.southwest.latitude;
		}
		if(cameraBounds.southwest.longitude < mMobiBounds.southwest.longitude) {
			longitude = mMobiBounds.southwest.longitude - cameraBounds.southwest.longitude;
		}
		if(cameraBounds.northeast.latitude > mMobiBounds.northeast.latitude) {
			latitude = mMobiBounds.northeast.latitude - cameraBounds.northeast.latitude;
		}
		if(cameraBounds.northeast.longitude > mMobiBounds.northeast.longitude) {
			longitude = mMobiBounds.northeast.longitude - cameraBounds.northeast.longitude;
		}
		return new LatLng(latitude, longitude);
	}

	/**
	 * Bounds the user to the overlay.
	 */
	private class OverscrollHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			CameraPosition position = mMap.getCameraPosition();
			VisibleRegion region = mMap.getProjection().getVisibleRegion();
			float zoom = 0;
			if(position.zoom < MIN_ZOOM) zoom = MIN_ZOOM;
			if(position.zoom > MAX_ZOOM) zoom = MAX_ZOOM;
			LatLng correction = getLatLngCorrection(region.latLngBounds);
			if(zoom != 0 || correction.latitude != 0 || correction.longitude != 0) {
				zoom = (zoom==0)?position.zoom:zoom;
				double lat = position.target.latitude + correction.latitude;
				double lon = position.target.longitude + correction.longitude;
				CameraPosition newPosition = new CameraPosition(new LatLng(lat,lon), zoom, position.tilt, position.bearing);
				CameraUpdate update = CameraUpdateFactory.newCameraPosition(newPosition);
				mMap.moveCamera(update);
			}
        /* Recursively call handler every 100ms */
			sendEmptyMessageDelayed(0,100);
		}

		@Override
		public void close() {

		}

		@Override
		public void flush() {

		}

		@Override
		public void publish(LogRecord record) {

		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mContext = getActivity();
		mMap = getMap();
		mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
		mMap.addTileOverlay(new TileOverlayOptions().tileProvider(new VexLocalTileProvider(getResources().getAssets())));
		CameraUpdate upd = CameraUpdateFactory.newLatLngZoom(new LatLng(41.87145, 12.52849), 14);
		mMap.moveCamera(upd);
		mOverscrollHandler.sendEmptyMessageDelayed(0,100);
	}

	//Start navigation
	public void startCar(View v) {
		//Start Google Maps Drive, Navigate immediately
		Uri gmmIntentUri = Uri.parse("google.navigation:q=" + LOCATION_MOBI_STRING + "&mode=d");
		Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
		mapIntent.setPackage("com.google.android.apps.maps");
		startActivity(mapIntent);
	}

	public void startWalk(View v) {
		//Start Google Maps Walk, Navigate immediately
		Uri gmmIntentUri = Uri.parse("google.navigation:q=" + LOCATION_MOBI_STRING + "&mode=w");
		Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
		mapIntent.setPackage("com.google.android.apps.maps");
		startActivity(mapIntent);
	}

	public void startBicycle(View v) {
		//Start Google Maps Bike, Navigate immediately
		Uri gmmIntentUri = Uri.parse("google.navigation:q=" + LOCATION_MOBI_STRING + "&mode=b");
		Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
		mapIntent.setPackage("com.google.android.apps.maps");
		startActivity(mapIntent);
	}

	public void startOV(View v) {
		//Start Google Maps Public Transport, Navigate immediately
		Uri gmmIntentUri = Uri.parse("google.navigation:q=" + LOCATION_MOBI_STRING + "&mode=r");
		Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
		mapIntent.setPackage("com.google.android.apps.maps");
		startActivity(mapIntent);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();

		switch (id) {
			case R.id.buttonCar:
				startCar(v);
				break;
			case R.id.buttonWalk:
				startWalk(v);
				break;
			case R.id.buttonBicycle:
				startBicycle(v);
				break;
			case R.id.buttonOV:
				startOV(v);
				break;
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.hold_screen, android.R.anim.slide_out_right);
	}
}