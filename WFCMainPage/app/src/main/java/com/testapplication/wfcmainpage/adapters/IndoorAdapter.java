//package com.testapplication.wfcmainpage.adapters;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.util.Log;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.indooratlas.android.CalibrationState;
//import com.indooratlas.android.FloorPlan;
//import com.indooratlas.android.FutureResult;
//import com.indooratlas.android.IndoorAtlas;
//import com.indooratlas.android.IndoorAtlasException;
//import com.indooratlas.android.IndoorAtlasFactory;
//import com.indooratlas.android.IndoorAtlasListener;
//import com.indooratlas.android.ServiceState;
//
//import java.io.IOException;
//
///**
// * Created by Jozef Fraai on 2-6-2015.
// */
//public class IndoorAdapter extends ImageView implements IndoorAtlasListener {
//
//	String mApiKey = "0ffdc6fa-5af1-4b36-8898-f132b9b27ea1";
//	String mApiSecret = "8x!XaLPUCDx4DSNlKi6&flJPHpXLKmX&)7IMFn(yy2TcrQRzsuQZvVT%aJwKWop7pwqGN)Bsvlm8zyf%db(dOZZhc82lI22nxM8XVJ7eY5GFw)GZp4sEBEeyGmIyhO6R";
//	String mFloorPlanId = "f1b92acd-7a57-4bdd-8395-6f9a4cb84724";
//	String mFloorId = "ce808d5e-6410-4c1e-9796-66b115673eeb";
//	String mVenueId = "b2f56148-7faa-4ce4-b543-32c67c01b015";
//
//	final String TAG = "MijnTag";
//
//	private IndoorAtlas mIndoorAtlas;
//	private boolean mIsPositioning;
//	private FloorPlan mFloorPlan;
//
//	IndoorAtlas mFloorplan = IndoorAtlasFactory.createIndoorAtlas(getContext(), this,mApiKey,mApiSecret);
//
//	FutureResult<FloorPlan> result = mIndoorAtlas.fetchFloorPlan(mFloorPlanId);
//	result.setCallback(new com.indooratlas.android.ResultCallback<FloorPlan>()
//
//	{
//
//		@Override
//		public void onResult ( final FloorPlan result){
//		mFloorPlan = result;
//		loadFloorPlanImage(result);
//
//
//	}
//
//		@Override
//		public void onSystemError (IOException e){
//
//	}
//
//		@Override
//		public void onApplicationError (IndoorAtlasException e){
//
//	}
//	}
//
//	);
//
//	private void initIndoorAtlas() {
//
//		try {
//			// obtain instance to positioning service, note that calibrating might begin instantly
//			mIndoorAtlas = IndoorAtlasFactory.createIndoorAtlas(
//					getContext(),
//					this, // IndoorAtlasListener
//					mApiKey,
//					mApiSecret);
//			togglePositioning();
//
//		} catch (IndoorAtlasException ex) {
//
//		}
//
//	}
//
//	private void togglePositioning() {
//		if (mIsPositioning) {
//			stopPositioning();
//		} else {
//			startPositioning();
//		}
//	}
//
//	private void startPositioning() {
//		if (mIndoorAtlas != null) {
//
//			try {
//				mIndoorAtlas.startPositioning(mVenueId, mFloorId, mFloorPlanId);
//				mIsPositioning = true;
//			} catch (IndoorAtlasException e) {
//
//			}
//		} else {
//		}
//	}
//
//	private void stopPositioning() {
//		mIsPositioning = false;
//		if (mIndoorAtlas != null) {
//			mIndoorAtlas.stopPositioning();
//		}
//	}
//
//	private BitmapFactory.Options createBitmapOptions(FloorPlan floorPlan) {
//
//		BitmapFactory.Options options = new BitmapFactory.Options();
//
////		int reqWidth = 2048;
////		int reqHeight = 2048;
////		final int width = (int) floorPlan.dimensions[0];
////		final int height = (int) floorPlan.dimensions[1];
////		int inSampleSize = 1;
////
////		if (height > reqHeight || width > reqWidth) {
////			final int halfHeight = height / 2;
////			final int halfWidth = width / 2;
////			while ((halfHeight / inSampleSize) > reqHeight
////					&& (halfWidth / inSampleSize) > reqWidth) {
////				inSampleSize *= 2;
////			}
////		}
////
////
////		options.inSampleSize = inSampleSize;
//		return options;
//	}
//
//	void loadFloorPlanImage(FloorPlan floorPlan) {
//		BitmapFactory.Options options = createBitmapOptions(floorPlan);
//		FutureResult<Bitmap> result = mIndoorAtlas.fetchFloorPlanImage(floorPlan, options);
//		result.setCallback(new com.indooratlas.android.ResultCallback<Bitmap>() {
//			@Override
//			public void onResult(final Bitmap result) {
//				runOnUiThread(new Runnable() {
//					@Override
//					public void run() {
//						image.setImageBitmap(result);
//					}
//				});
//
//			}
//
//			@Override
//			public void onSystemError(IOException e) {
//
//			}
//
//			@Override
//			public void onApplicationError(IndoorAtlasException e) {
//
//			}
//			// handle error conditions too
//		});
//	}
//
//	private void tearDown() {
//		if (mIndoorAtlas != null) {
//			mIndoorAtlas.tearDown();
//		}
//	}
//
//	/* IndoorAtlasListener interface */
//
//	/**
//	 * This is where you will handle location updates.
//	 */
//	public void onServiceUpdate(ServiceState state) {
//
//
//	}
//
//	@Override
//	public void onServiceFailure(int errorCode, String reason) {
//		Log.e(TAG, "onServiceFailure: reason : " + reason);
//	}
//
//	@Override
//	public void onServiceInitializing() {
//		Log.e(TAG, "onServiceInitializing");
//	}
//
//	@Override
//	public void onServiceInitialized() {
//		Log.e(TAG, "onServiceInitialized");
//	}
//
//	@Override
//	public void onInitializationFailed(final String reason) {
//		Log.e(TAG, "onInitializationFailed: " + reason);
//	}
//
//	@Override
//	public void onServiceStopped() {
//		Log.e(TAG, "onServiceStopped");
//	}
//
//	@Override
//	public void onCalibrationStatus(CalibrationState calibrationState) {
//		Log.e(TAG, "onCalibrationStatus, percentage: " + calibrationState.getPercentage());
//	}
//
//	/**
//	 * Notification that calibration has reached level of quality that provides best possible
//	 * positioning accuracy.
//	 */
//	@Override
//	public void onCalibrationReady() {
//		Log.e(TAG, "onCalibrationReady");
//		Context context = getContext();
//		CharSequence text = "Calibration is ready";
//		int duration = Toast.LENGTH_SHORT;
//
//		Toast toast = Toast.makeText(context, text, duration);
//		toast.show();
//	}
//
//	@Override
//	public void onNetworkChangeComplete(boolean success) {
//	}
//
//	/**
//	 * @deprecated this callback is deprecated as of version 1.4
//	 */
//	@Override
//	public void onCalibrationInvalid() {
//	}
//
//	/**
//	 * @deprecated this callback is deprecated as of version 1.4
//	 */
//	@Override
//	public void onCalibrationFailed(String reason) {
//	}
//
//
//}
