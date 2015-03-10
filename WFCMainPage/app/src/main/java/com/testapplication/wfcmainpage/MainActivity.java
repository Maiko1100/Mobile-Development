package com.testapplication.wfcmainpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

	private Timer mCarrouselTimer;
	private int mCurrentImage = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle(getString(R.string.main_banner_text));

		Button infoButton = (Button) findViewById(R.id.infoButton);
		Button facilitiesButton = (Button) findViewById(R.id.facilitiesButton);
		Button navigationButton = (Button) findViewById(R.id.navigationButton);
		Button rentButton = (Button) findViewById(R.id.rentButton);
		ImageButton prevButton = (ImageButton) findViewById(R.id.prevButton);
		ImageButton nextButton = (ImageButton) findViewById(R.id.nextButton);

		infoButton.setOnClickListener(this);
		facilitiesButton.setOnClickListener(this);
		navigationButton.setOnClickListener(this);
		rentButton.setOnClickListener(this);
		prevButton.setOnClickListener(this);
		nextButton.setOnClickListener(this);

		// int[] carrouselIds = getResources().getIntArray(R.array.carrouselImages);

		mCarrouselTimer = new Timer();
		mCarrouselTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				TimerMethod();
			}
		}, 0, 7000);
	}

	private void TimerMethod() {
		runOnUiThread(Timer_Tick);

	}

	private Runnable Timer_Tick = new Runnable() {
		@Override
		public void run() {

			ImageView img = (ImageView) findViewById(R.id.imageView);

			mCurrentImage++;

			if (mCurrentImage >= 4 || mCurrentImage <= 0) {
				mCurrentImage = 1;
			}

			switch (mCurrentImage) {
				case 1:
					img.setImageResource(R.drawable.image1);
					break;
				case 2:
					img.setImageResource(R.drawable.image2);
					break;
				case 3:
					img.setImageResource(R.drawable.image3);
					mCurrentImage = 0;
					break;
				default:
					img.setImageResource(R.drawable.image1);
			}
		}
	};

	public void showInfo(View v) {
		Intent showInfo = new Intent(this, InfoActivity.class);
		startActivity(showInfo);
	}

	public void showMaps(View v) {
		Intent showMaps = new Intent(this, MapsActivity.class);
		startActivity(showMaps);
	}

	public void showFacilities(View v) {
		Intent showFacilities = new Intent(this, FacilitiesActivity.class);
		startActivity(showFacilities);
	}

	public void showRent(View v) {
		//todo code for the rent button
	}

	public void nextImage(View v) {
		ImageView img1 = (ImageView) findViewById(R.id.imageView);

		mCurrentImage++;

		if (mCurrentImage >= 4 || mCurrentImage <= 0) {
			mCurrentImage = 1;
		}

		switch (mCurrentImage) {
			case 1:
				img1.setImageResource(R.drawable.image1);
				break;
			case 2:
				img1.setImageResource(R.drawable.image2);
				break;
			case 3:
				img1.setImageResource(R.drawable.image3);
				break;
			default:
				img1.setImageResource(R.drawable.image1);
		}

		mCarrouselTimer.cancel();
		mCarrouselTimer = new Timer();
		mCarrouselTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				TimerMethod();
			}
		}, 7000, 7000);
	}

	public void prevImage(View v) {
		ImageView img = (ImageView) findViewById(R.id.imageView);

		mCurrentImage--;

		if (mCurrentImage >= 4 || mCurrentImage <= 0) {
			mCurrentImage = 3;
		}

		switch (mCurrentImage) {
			case 1:
				img.setImageResource(R.drawable.image1);
				break;
			case 2:
				img.setImageResource(R.drawable.image2);
				break;
			case 3:
				img.setImageResource(R.drawable.image3);
				break;
			default:
				System.out.println(mCurrentImage);
		}

		mCarrouselTimer.cancel();
		mCarrouselTimer = new Timer();
		mCarrouselTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				TimerMethod();
			}
		}, 7000, 7000);
	}

	@Override
	public void onClick(View v) {
		{

			int id = v.getId();
			switch (id) {
				case R.id.infoButton:
					showInfo(v);
					break;

				case R.id.navigationButton:
					showMaps(v);
					break;

				case R.id.facilitiesButton:
					showFacilities(v);
					break;

				case R.id.rentButton:
					showRent(v);
					break;

				case R.id.prevButton:
					prevImage(v);
					break;

				case R.id.nextButton:
					nextImage(v);
					break;
			}
		}
	}
}

