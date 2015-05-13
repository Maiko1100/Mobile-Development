package com.testapplication.wfcmainpage.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.indooratlas.android.CalibrationState;
import com.indooratlas.android.FloorPlan;
import com.indooratlas.android.FutureResult;
import com.indooratlas.android.IndoorAtlas;
import com.indooratlas.android.IndoorAtlasException;
import com.indooratlas.android.IndoorAtlasFactory;
import com.indooratlas.android.IndoorAtlasListener;
import com.indooratlas.android.PositionerParameters;
import com.indooratlas.android.ServiceState;
import com.testapplication.wfcmainpage.R;

import java.util.ArrayList;

/**
 * Created by Jozef Fraai on 12-5-2015.
 */
public class CustomIndoorAdapter implements IndoorAtlasListener {

	private static final String TAG = "MijnTag";

	String mApiKey = "0ffdc6fa-5af1-4b36-8898-f132b9b27ea1";
	String mApiSecret = "8x!XaLPUCDx4DSNlKi6&flJPHpXLKmX&)7IMFn(yy2TcrQRzsuQZvVT%aJwKWop7pwqGN)Bsvlm8zyf%db(dOZZhc82lI22nxM8XVJ7eY5GFw)GZp4sEBEeyGmIyhO6R";

	private IndoorAtlas mIndoorAtlas;
	private boolean mIsPositioning;

	String mVenueId = "b2f56148-7faa-4ce4-b543-32c67c01b015";
	String mFloorId = "ce808d5e-6410-4c1e-9796-66b115673eeb";
	String mFloorPlanId = "dc895a7c-fbb7-43d4-b6e2-7552ad6abab0";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		initIndoorAtlas();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		tearDown();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_clear_log:
				mLogAdapter.clear();
				return true;
			case R.id.action_toggle_positioning:
				togglePositioning();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void tearDown() {
		if (mIndoorAtlas != null) {
			mIndoorAtlas.tearDown();
		}
	}

	@Override
	public boolean isCalibrationReady() {
		return false;
	}

	@Override
	public float getCommunicationLatency() {
		return 0;
	}

	@Override
	public int getBufferedQueueLength() {
		return 0;
	}

	@Override
	public void submitExplicitGeoPosition(double v, double v1, float v2) {

	}

	@Override
	public void submitExplicitPixelPosition(int i, int i1, float v) {

	}

	@Override
	public void submitExplicitMetricPosition(int i, int i1, float v) {

	}

	@Override
	public FutureResult<FloorPlan> fetchFloorPlan(String s) {
		return null;
	}

	@Override
	public FutureResult<Bitmap> fetchFloorPlanImage(FloorPlan floorPlan, BitmapFactory.Options options) {
		return null;
	}


	@Override
	public void startPositioning(String s, String s1, String s2) throws IndoorAtlasException {

	}

	@Override
	public void startPositioning(String s, String s1, String s2, PositionerParameters positionerParameters) throws IndoorAtlasException {

	}

	private void stopPositioning() {
		mIsPositioning = false;
		if (mIndoorAtlas != null) {
			log("Stop positioning");
			mIndoorAtlas.stopPositioning();
		}
	}

	@Override
	public void setPreferMobileConnection(boolean b) {

	}

	private void startPositioning() {
		if (mIndoorAtlas != null) {
			log(String.format("startPositioning, venueId: %s, floorId: %s, floorPlanId: %s",
					mVenueId,
					mFloorId,
					mFloorPlanId));
			try {
				mIndoorAtlas.startPositioning(mVenueId, mFloorId, mFloorPlanId);
				mIsPositioning = true;
			} catch (IndoorAtlasException e) {
				log("startPositioning failed: " + e);
			}
		} else {
			log("calibration not ready, cannot start positioning");
		}
	}

	private void togglePositioning() {
		if (mIsPositioning) {
			stopPositioning();
		} else {
			startPositioning();
		}
	}

	private void initIndoorAtlas() {

		try {

			log("Connecting with IndoorAtlas, apiKey: " + mApiKey);

			// obtain instance to positioning service, note that calibrating might begin instantly
			mIndoorAtlas = IndoorAtlasFactory.createIndoorAtlas(
					getApplicationContext(),
					this, // IndoorAtlasListener
					mApiKey,
					mApiSecret);

			log("IndoorAtlas instance created");
			togglePositioning();

		} catch (IndoorAtlasException ex) {
			Log.e("IndoorAtlas", "init failed", ex);
			log("init IndoorAtlas failed, " + ex.toString());
		}

	}


	private void log(final String msg) {
		Log.d(TAG, msg);
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mLogAdapter.add(msg);
				mLogAdapter.notifyDataSetChanged();
			}
		});
	}

    /* IndoorAtlasListener interface */

	/**
	 * This is where you will handle location updates.
	 */
	public void onServiceUpdate(ServiceState state) {

		mSharedBuilder.setLength(0);
		mSharedBuilder.append("Location: ")
				.append("\n\troundtrip : ").append(state.getRoundtrip()).append("ms")
				.append("\n\tlat : ").append(state.getGeoPoint().getLatitude())
				.append("\n\tlong : ").append(state.getGeoPoint().getLongitude())
				.append("\n\tX [meter] : ").append(state.getMetricPoint().getX())
				.append("\n\tY [meter] : ").append(state.getMetricPoint().getY())
				.append("\n\tI [pixel] : ").append(state.getImagePoint().getI())
				.append("\n\tJ [pixel] : ").append(state.getImagePoint().getJ())
				.append("\n\theading : ").append(state.getHeadingDegrees())
				.append("\n\tuncertainty: ").append(state.getUncertainty());

		log(mSharedBuilder.toString());
	}


	@Override
	public void onServiceFailure(int errorCode, String reason) {
		log("onServiceFailure: reason : " + reason);
	}

	@Override
	public void onServiceInitializing() {
		log("onServiceInitializing");
	}

	@Override
	public void onServiceInitialized() {
		log("onServiceInitialized");
	}

	@Override
	public void onInitializationFailed(final String reason) {
		log("onInitializationFailed: " + reason);
	}

	@Override
	public void onServiceStopped() {
		log("onServiceStopped");
	}

	@Override
	public void onCalibrationStatus(CalibrationState calibrationState) {
		log("onCalibrationStatus, percentage: " + calibrationState.getPercentage());
	}

	/**
	 * Notification that calibration has reached level of quality that provides best possible
	 * positioning accuracy.
	 */
	@Override
	public void onCalibrationReady() {
		log("onCalibrationReady");
	}

	@Override
	public void onNetworkChangeComplete(boolean success) {
	}

	/**
	 * @deprecated this callback is deprecated as of version 1.4
	 */
	@Override
	public void onCalibrationInvalid() {
	}

	/**
	 * @deprecated this callback is deprecated as of version 1.4
	 */
	@Override
	public void onCalibrationFailed(String reason) {
	}

	static class LogAdapter extends BaseAdapter {

		private ArrayList<String> mLines = new ArrayList<>();
		private LayoutInflater mInflater;

		public LogAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return mLines.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView text = (TextView) convertView;
			if (convertView == null) {
				text = (TextView) mInflater.inflate(android.R.layout.simple_list_item_1, parent,
						false);
			}
			text.setText(mLines.get(position));
			return text;
		}

		public void add(String line) {
			mLines.add(0, line);
		}

		public void clear() {
			mLines.clear();
			notifyDataSetChanged();
		}
	}

}
