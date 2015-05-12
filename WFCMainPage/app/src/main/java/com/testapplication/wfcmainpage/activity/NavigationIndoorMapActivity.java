package com.testapplication.wfcmainpage.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.testapplication.wfcmainpage.R;

public class NavigationIndoorMapActivity extends ActionBarActivity implements View.OnClickListener {

	private final LatLngBounds mMobiBounds = new LatLngBounds(new LatLng(52.3713710997303, 4.88609969615936), new LatLng(52.371600, 4.886524));
	private final String LOCATION_MOBI_STRING = "Herengracht 266 1016 BV Amsterdam";

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

		GoogleMap mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
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