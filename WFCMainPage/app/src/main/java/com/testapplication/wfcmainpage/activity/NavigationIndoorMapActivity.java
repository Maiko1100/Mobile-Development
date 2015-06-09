package com.testapplication.wfcmainpage.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.indooratlas.android.CalibrationState;
import com.indooratlas.android.FloorPlan;
import com.indooratlas.android.FutureResult;
import com.indooratlas.android.IndoorAtlas;
import com.indooratlas.android.IndoorAtlasException;
import com.indooratlas.android.IndoorAtlasFactory;
import com.indooratlas.android.IndoorAtlasListener;
import com.indooratlas.android.MetricPoint;
import com.indooratlas.android.ResultCallback;
import com.indooratlas.android.ServiceState;
import com.testapplication.wfcmainpage.R;

import java.io.IOException;

/**
 * Activity that handles indoor navigation
 *
 * @author Team 12
 */
public class NavigationIndoorMapActivity extends ActionBarActivity implements IndoorAtlasListener {

	/* Keys necessary to load Floorplan and indoor positioning details */
	String mApiKey = "0ffdc6fa-5af1-4b36-8898-f132b9b27ea1";
	String mApiSecret = "8x!XaLPUCDx4DSNlKi6&flJPHpXLKmX&)7IMFn(yy2TcrQRzsuQZvVT%aJwKWop7pwqGN)Bsvlm8zyf%db(dOZZhc82lI22nxM8XVJ7eY5GFw)GZp4sEBEeyGmIyhO6R";
	String mFloorPlanId = "f1b92acd-7a57-4bdd-8395-6f9a4cb84724";
	String mFloorId = "ce808d5e-6410-4c1e-9796-66b115673eeb";
	String mVenueId = "b2f56148-7faa-4ce4-b543-32c67c01b015";

	private IndoorAtlas mIndoorAtlas;
	private boolean mIsPositioning;
	private FloorPlan mFloorPlan;

	ImageView ivFloorPLan, blueDot;

	private final String LOG_TAG = "MIJN_TAG";
	double mConversion;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navigation_indoor_map);
		setTitle(getString(R.string.indoor_maps_title_text));

		ivFloorPLan = (ImageView) findViewById(R.id.showIndoorMap);
		blueDot = (ImageView) findViewById(R.id.showBlueDot);


		initIndoorAtlas();

		FutureResult<FloorPlan> result = mIndoorAtlas.fetchFloorPlan(mFloorPlanId);
		result.setCallback(new ResultCallback<FloorPlan>() {

			@Override
			public void onResult(final FloorPlan result) {
				mFloorPlan = result;
				mConversion = mFloorPlan.metricToPixelConversion;
				loadFloorPlanImage(result);
			}

			@Override
			public void onSystemError(IOException e) {

			}

			@Override
			public void onApplicationError(IndoorAtlasException e) {

			}
		});
		startPositioning();


	}


	private void initIndoorAtlas() {

		try {
			// obtain instance to positioning service, note that calibrating might begin instantly
			mIndoorAtlas = IndoorAtlasFactory.createIndoorAtlas(
					getApplicationContext(),
					this, // IndoorAtlasListener
					mApiKey,
					mApiSecret);
			togglePositioning();

		} catch (IndoorAtlasException ex) {
			Log.e("IndoorAtlas", "init failed", ex);
			Log.e("IndoorAtlas", "init IndoorAtlas failed, " + ex.toString());
		}

	}

	private void togglePositioning() {
		if (mIsPositioning) {
			stopPositioning();
		} else {
			startPositioning();
		}
	}

	private void startPositioning() {
		if (mIndoorAtlas != null) {
			Log.e(LOG_TAG, String.format("startPositioning, venueId: %s, floorId: %s, floorPlanId: %s",
					mVenueId,
					mFloorId,
					mFloorPlanId));
			try {
				mIndoorAtlas.startPositioning(mVenueId, mFloorId, mFloorPlanId);
				mIsPositioning = true;
			} catch (IndoorAtlasException e) {
				Log.e(LOG_TAG, "startPositioning failed: " + e);
			}
		} else {
			Log.e(LOG_TAG, "calibration not ready, cannot start positioning");
		}
	}

	private void stopPositioning() {
		mIsPositioning = false;
		if (mIndoorAtlas != null) {
			Log.e(LOG_TAG, "Stop positioning");
			mIndoorAtlas.stopPositioning();
		}
	}

	void loadFloorPlanImage(FloorPlan floorPlan) {
		BitmapFactory.Options options = createBitmapOptions(floorPlan);
		FutureResult<Bitmap> result = mIndoorAtlas.fetchFloorPlanImage(floorPlan, options);
		result.setCallback(new ResultCallback<Bitmap>() {
			@Override
			public void onResult(final Bitmap result) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						ivFloorPLan.setImageBitmap(result);
					}
				});

			}

			@Override
			public void onSystemError(IOException e) {

			}

			@Override
			public void onApplicationError(IndoorAtlasException e) {

			}
			// handle error conditions too
		});
	}

	/**
	 * Scale down the floorplan image if the size is too big
	 * @param floorPlan
	 * @return
	 */

	private BitmapFactory.Options createBitmapOptions(FloorPlan floorPlan) {

		BitmapFactory.Options options = new BitmapFactory.Options();

		int reqWidth = 2048;
		int reqHeight = 2048;
		final int width = (int) floorPlan.dimensions[0];
		final int height = (int) floorPlan.dimensions[1];
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			final int halfHeight = height / 2;
			final int halfWidth = width / 2;
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}


		options.inSampleSize = inSampleSize;
		return options;
	}

	private void tearDown() {
		if (mIndoorAtlas != null) {
			mIndoorAtlas.tearDown();
		}
	}

	/* IndoorAtlasListener interface */

	/**
	 * This is where you will handle location updates.
	 */
	public void onServiceUpdate(ServiceState state) {
		setMetricPoint(state.getMetricPoint());
	}

	/**
	 * Method that scales metric points to floorplan image size and displays the blue dot
	 * @param mMetricPoint
	 */
	private void setMetricPoint(final MetricPoint mMetricPoint) {

		float x = (float) (mMetricPoint.getX() * mConversion);
		float y = (float) (mMetricPoint.getY() * mConversion);

		blueDot.setX(x);
		blueDot.setY(y);
	}


	@Override
	public void onServiceFailure(int errorCode, String reason) {
		Log.e(LOG_TAG, "onServiceFailure: reason : " + reason);
	}

	@Override
	public void onServiceInitializing() {
		Log.e(LOG_TAG, "onServiceInitializing");
	}

	@Override
	public void onServiceInitialized() {
		Log.e(LOG_TAG, "onServiceInitialized");
	}

	@Override
	public void onInitializationFailed(final String reason) {
		Log.e(LOG_TAG, "onInitializationFailed: " + reason);
	}

	@Override
	public void onServiceStopped() {
		Log.e(LOG_TAG, "onServiceStopped");
	}

	@Override
	public void onCalibrationStatus(CalibrationState calibrationState) {
		Log.e(LOG_TAG, "onCalibrationStatus, percentage: " + calibrationState.getPercentage());
	}

	/**
	 * Notification that calibration has reached level of quality that provides best possible
	 * positioning accuracy.
	 */
	@Override
	public void onCalibrationReady() {
		Log.e(LOG_TAG, "onCalibrationReady");
		Context context = getApplicationContext();
		CharSequence text = "Calibration is ready";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
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

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		tearDown();
		overridePendingTransition(R.anim.hold_screen, android.R.anim.slide_out_right);
	}
}