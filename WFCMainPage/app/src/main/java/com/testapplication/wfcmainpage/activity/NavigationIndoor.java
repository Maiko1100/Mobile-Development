package com.testapplication.wfcmainpage.activity;

import android.bluetooth.BluetoothAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.testapplication.wfcmainpage.R;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.List;

public class NavigationIndoor extends ActionBarActivity {

	private TextView mTvLocation;
	private static final String ESTIMOTE_PROXIMITY_UUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
	private static final Region ALL_ESTIMOTE_BEACONS = new Region("regionId", ESTIMOTE_PROXIMITY_UUID, null, null);

	private static final String STRING_MAC_BLUE_BEACON1= "E5:96:E5:68:D9:A7";
	private static final String STRING_MAC_BLUE_BEACON2= "D0:89:FA:BD:EB:22";
	private static final String STRING_MAC_PURPLE_BEACON= "F3:BC:3A:0D:23:24";

	private BeaconManager mBeaconManager;
	private BluetoothAdapter mBluetoothAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navigation_indoor);
		mTvLocation = (TextView) findViewById(R.id.tvNavigationLocation);

		mBeaconManager = new BeaconManager(this);
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		mBeaconManager.setRangingListener(new BeaconManager.RangingListener(){

			@Override
			public void onBeaconsDiscovered(Region region, List<Beacon> beacons) {
				for (Beacon b: beacons){
					switch(b.getMacAddress()){
						case STRING_MAC_BLUE_BEACON1:
							mTvLocation.setText("You are near beacon 1 (Blue)");
							break;
						case STRING_MAC_BLUE_BEACON2:
							mTvLocation.setText("You are near beacon 2 (Blue)");
							break;
						case STRING_MAC_PURPLE_BEACON:
							mTvLocation.setText("You are near beacon 3(Purple)");
							break;
					}
				}
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_navigation_indoor, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
