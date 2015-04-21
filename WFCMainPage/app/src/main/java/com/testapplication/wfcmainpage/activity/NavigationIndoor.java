package com.testapplication.wfcmainpage.activity;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.testapplication.wfcmainpage.R;

import java.util.List;

public class NavigationIndoor extends ActionBarActivity {

    private static final String TAG = "mijnLog";

    private TextView mTvLocation;
    private static final String ESTIMOTE_PROXIMITY_UUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
    private static final Region ALL_ESTIMOTE_BEACONS = new Region("regionId", ESTIMOTE_PROXIMITY_UUID, null, null);

    private static final String STRING_MAC_BLUE_BEACON1 = "E5:96:E5:68:D9:A7";
    private static final String STRING_MAC_BLUE_BEACON2 = "D0:89:FA:BD:EB:22";
    private static final String STRING_MAC_PURPLE_BEACON = "F3:BC:3A:0D:23:24";

    private BeaconManager mBeaconManager;
    BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_indoor);
        mTvLocation = (TextView) findViewById(R.id.tvNavigationLocation);

        mBeaconManager = new BeaconManager(this);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        //enable bluetooth
        if (!mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.enable();
        }
        ;

        mBeaconManager.setRangingListener(new BeaconManager.RangingListener() {

            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> beacons) {
                for (Beacon b : beacons) {
                    switch (b.getMacAddress()) {
                        case STRING_MAC_BLUE_BEACON1:
	                        if (b.getRssi() > -60) {
		                        mTvLocation.setText("You are near our project table");
	                        }
                            break;
                        case STRING_MAC_BLUE_BEACON2:
	                        if (b.getRssi() > -60) {
		                        mTvLocation.setText("You are near the coffee table");
	                        }
                            break;
                        case STRING_MAC_PURPLE_BEACON:
	                        if (b.getRssi() > -60) {
		                        mTvLocation.setText("You are near the exit");
	                        }
                            break;
                        default:
	                        mTvLocation.setText(R.string.no_beacon_text);
                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        mBeaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                try {
                    mBeaconManager.startRanging(ALL_ESTIMOTE_BEACONS);
                } catch (RemoteException e) {
                    Log.e(TAG, "Cannot start ranging", e);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        try {
            mBeaconManager.stopRanging(ALL_ESTIMOTE_BEACONS);
        } catch (RemoteException e) {
            Log.e(TAG, "Cannot stop but it does not matter now", e);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mBeaconManager.disconnect();
    }
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}
}
