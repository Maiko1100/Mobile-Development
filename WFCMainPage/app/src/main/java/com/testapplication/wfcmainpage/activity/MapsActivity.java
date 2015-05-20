package com.testapplication.wfcmainpage.activity;

import android.content.Intent;
import android.content.SyncStatusObserver;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.testapplication.wfcmainpage.R;

import org.apache.http.io.SessionOutputBuffer;


public class MapsActivity extends ActionBarActivity implements View.OnClickListener {

	private final LatLng LOCATION_WFC = new LatLng(52.354484, 4.841304);
	private final String LOCATION_WFC_STRING = "Koningin Wilhelminaplein 13 1062 HH Amsterdam";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		setTitle(getString(R.string.maps_title_text));




		ImageButton buttonCar = (ImageButton) findViewById(R.id.buttonCar);
		buttonCar.getDrawable().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
		ImageButton buttonWalk = (ImageButton) findViewById(R.id.buttonWalk);
		buttonWalk.getDrawable().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
		ImageButton buttonBicycle = (ImageButton) findViewById(R.id.buttonBicycle);
		buttonBicycle.getDrawable().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
		ImageButton buttonOV = (ImageButton) findViewById(R.id.buttonOV);
		buttonOV.getDrawable().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);

		buttonCar.setOnClickListener(this);
		buttonWalk.setOnClickListener(this);
		buttonBicycle.setOnClickListener(this);
		buttonOV.setOnClickListener(this);

		GoogleMap mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);




		// Zoom on location on start
		CameraUpdate start = CameraUpdateFactory.newLatLngZoom(LOCATION_WFC, 18);
		mMap.animateCamera(start);

		// Set custom marker text
		mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
			@Override
			public View getInfoWindow(Marker marker) {
				return null;
			}

			@Override
			public View getInfoContents(Marker pMarker) {
				// Getting view from the layout file info_window_layout
				System.out.println("dsdfdfsdf");
				View v = getLayoutInflater().inflate(R.layout.info_window_layout, null);
				v.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						System.out.println("balblabl");
						Intent intentPhone = new Intent(Intent.ACTION_DIAL);
						intentPhone.setData(Uri.parse("tel:"));
						startActivity(intentPhone);
					}
				});
				return v;
			}
		});

		// Show custom marker
		MarkerOptions mMarkerOptions = new MarkerOptions();
		mMarkerOptions.position(LOCATION_WFC);
		Marker mMarker = mMap.addMarker(mMarkerOptions);
		mMarker.showInfoWindow();
		mMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.kleinlogo));

		mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

			@Override
			public void onInfoWindowClick(Marker marker) {
				Intent intentPhone = new Intent(Intent.ACTION_DIAL);
				intentPhone.setData(Uri.parse(getString(R.string.tel_number)));
				startActivity(intentPhone);
			}
		});

	}



	//Start navigation
	public void startCar(View v) {
		//Start Google Maps Drive, Navigate immediately
		Uri gmmIntentUri = Uri.parse("google.navigation:q=" + LOCATION_WFC_STRING + "&mode=d");
		Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
		mapIntent.setPackage("com.google.android.apps.maps");
		startActivity(mapIntent);
	}

	public void startWalk(View v) {
		//Start Google Maps Walk, Navigate immediately
		Uri gmmIntentUri = Uri.parse("google.navigation:q=" + LOCATION_WFC_STRING + "&mode=w");
		Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
		mapIntent.setPackage("com.google.android.apps.maps");
		startActivity(mapIntent);
	}

	public void startBicycle(View v) {
		//Start Google Maps Bike, Navigate immediately
		Uri gmmIntentUri = Uri.parse("google.navigation:q=" + LOCATION_WFC_STRING + "&mode=b");
		Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
		mapIntent.setPackage("com.google.android.apps.maps");
		startActivity(mapIntent);
	}

	public void startOV(View v) {
		//Start Google Maps Public Transport, Navigate immediately
		Uri gmmIntentUri = Uri.parse("google.navigation:q=" + LOCATION_WFC_STRING + "&mode=r");
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
			case R.id.tv_info_window_phone:
				Intent intentPhone = new Intent(Intent.ACTION_DIAL);
				intentPhone.setData(Uri.parse("tel:"));
				startActivity(intentPhone);
				break;
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.hold_screen, android.R.anim.slide_out_right);
	}
}