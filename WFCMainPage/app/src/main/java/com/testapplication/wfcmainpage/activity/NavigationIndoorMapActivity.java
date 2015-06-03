package com.testapplication.wfcmainpage.activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.indooratlas.android.CalibrationState;
import com.indooratlas.android.FloorPlan;
import com.indooratlas.android.FutureResult;
import com.indooratlas.android.ImagePoint;
import com.indooratlas.android.IndoorAtlas;
import com.indooratlas.android.IndoorAtlasException;
import com.indooratlas.android.IndoorAtlasFactory;
import com.indooratlas.android.IndoorAtlasListener;
import com.indooratlas.android.MetricPoint;
import com.indooratlas.android.ResultCallback;
import com.indooratlas.android.ServiceState;
import com.testapplication.wfcmainpage.R;

import java.io.IOException;


public class NavigationIndoorMapActivity extends ActionBarActivity implements IndoorAtlasListener {

	String mApiKey = "0ffdc6fa-5af1-4b36-8898-f132b9b27ea1";
	String mApiSecret = "8x!XaLPUCDx4DSNlKi6&flJPHpXLKmX&)7IMFn(yy2TcrQRzsuQZvVT%aJwKWop7pwqGN)Bsvlm8zyf%db(dOZZhc82lI22nxM8XVJ7eY5GFw)GZp4sEBEeyGmIyhO6R";
	String mFloorPlanId = "f1b92acd-7a57-4bdd-8395-6f9a4cb84724";
	String mFloorId = "ce808d5e-6410-4c1e-9796-66b115673eeb";
	String mVenueId = "b2f56148-7faa-4ce4-b543-32c67c01b015";

	private IndoorAtlas mIndoorAtlas;
	private boolean mIsPositioning;
	private FloorPlan mFloorPlan;

	ImageView image, blueDot;

	private final String TAG = "MijnTag";
	double B, fpx, fpy;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navigation_indoor_map);
		setTitle(getString(R.string.indoor_maps_title_text));

		image = (ImageView) findViewById(R.id.showIndoorMap);
		blueDot = (ImageView) findViewById(R.id.showBlueDot);


		initIndoorAtlas();

		FutureResult<FloorPlan> result = mIndoorAtlas.fetchFloorPlan(mFloorPlanId);
		result.setCallback(new ResultCallback<FloorPlan>() {

			@Override
			public void onResult(final FloorPlan result) {
				mFloorPlan = result;
				B = mFloorPlan.pixelToMetricConversion;
				fpx = mFloorPlan.dimensions[0];
				fpy = mFloorPlan.dimensions[1];
				loadFloorPlanImage(result);

				Log.e(TAG, "Metric to pixel: " + B + " Normal X: " + fpx + " Scaled X: " + (fpx / B) + " Normal Y: ." + fpy + " Scaled Y: " + (fpy / B));

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
			Log.e(TAG, String.format("startPositioning, venueId: %s, floorId: %s, floorPlanId: %s",
					mVenueId,
					mFloorId,
					mFloorPlanId));
			try {
				mIndoorAtlas.startPositioning(mVenueId, mFloorId, mFloorPlanId);
				mIsPositioning = true;
			} catch (IndoorAtlasException e) {
				Log.e(TAG, "startPositioning failed: " + e);
			}
		} else {
			Log.e(TAG, "calibration not ready, cannot start positioning");
		}
	}

	private void stopPositioning() {
		mIsPositioning = false;
		if (mIndoorAtlas != null) {
			Log.e(TAG, "Stop positioning");
			mIndoorAtlas.stopPositioning();
		}
	}

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

	void loadFloorPlanImage(FloorPlan floorPlan) {
		BitmapFactory.Options options = createBitmapOptions(floorPlan);
		FutureResult<Bitmap> result = mIndoorAtlas.fetchFloorPlanImage(floorPlan, options);
		result.setCallback(new ResultCallback<Bitmap>() {
			@Override
			public void onResult(final Bitmap result) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						image.setImageBitmap(result);
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
		setImagePoint(state.getImagePoint());

	}

	private double getMetricCalculatedScale(Double metr) {
		metr = (metr * B);

//		float res = convertPixelsToDp(metr.floatValue(), this);
//
//		metr = (double) res;
		return metr;
	}


	public static float convertPixelsToDp(float px, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		return dp;
	}

	private void setImagePoint(final ImagePoint imgPt) {

		double dx = getMetricCalculatedScale((double) imgPt.getI());
		double dy = getMetricCalculatedScale((double)imgPt.getJ());
//		float x = getMetricCalculatedScale(imgPt.getI());
//		float y = getMetricCalculatedScale(imgPt.getJ());


		blueDot.setX((float)dx);
		blueDot.setY((float)dy);


		Log.e(TAG, "Print" +  " \nDouble output Y: " + dy + " Double output X: " + dx + " image X: " + image.getX());

	}


	@Override
	public void onServiceFailure(int errorCode, String reason) {
		Log.e(TAG, "onServiceFailure: reason : " + reason);
	}

	@Override
	public void onServiceInitializing() {
		Log.e(TAG, "onServiceInitializing");
	}

	@Override
	public void onServiceInitialized() {
		Log.e(TAG, "onServiceInitialized");
	}

	@Override
	public void onInitializationFailed(final String reason) {
		Log.e(TAG, "onInitializationFailed: " + reason);
	}

	@Override
	public void onServiceStopped() {
		Log.e(TAG, "onServiceStopped");
	}

	@Override
	public void onCalibrationStatus(CalibrationState calibrationState) {
		Log.e(TAG, "onCalibrationStatus, percentage: " + calibrationState.getPercentage());
	}

	/**
	 * Notification that calibration has reached level of quality that provides best possible
	 * positioning accuracy.
	 */
	@Override
	public void onCalibrationReady() {
		Log.e(TAG, "onCalibrationReady");
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