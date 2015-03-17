package com.testapplication.wfcmainpage;

		/**
		* @author Remco Hilbert & Fren de Haan
		* Main activity voor de carrousel en de Info, Rent, Navigation & Facilities knoppen
		*/
import android.content.Intent;
import android.content.res.TypedArray;
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
	private int mCurrentImage = -1;
    private ImageView mCarrouselImage;
    //final int[] imageIds1 = {R.drawable.image1, R.drawable.image2, R.drawable.image3};




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle(getString(R.string.main_banner_text));

		// knoppen declareren
		Button infoButton = (Button) findViewById(R.id.infoButton);
		Button facilitiesButton = (Button) findViewById(R.id.facilitiesButton);
		Button navigationButton = (Button) findViewById(R.id.navigationButton);
		Button rentButton = (Button) findViewById(R.id.rentButton);
		ImageButton prevButton = (ImageButton) findViewById(R.id.prevButton);
		ImageButton nextButton = (ImageButton) findViewById(R.id.nextButton);

		//onclick listener initialiseren
		infoButton.setOnClickListener(this);
		facilitiesButton.setOnClickListener(this);
		navigationButton.setOnClickListener(this);
		rentButton.setOnClickListener(this);
		prevButton.setOnClickListener(this);
		nextButton.setOnClickListener(this);


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

// method die om de 7 seconden het carrouselplaatje wisselt
	private Runnable Timer_Tick = new Runnable() {
		@Override
		public void run() {
            TypedArray carrouselImages = getResources().obtainTypedArray(R.array.carrouselImages);
            int arrayLength = carrouselImages.length();

            int[] imageId = new int[arrayLength];

            for(int i = 0; i < arrayLength; i++)
            {
                imageId[i] = carrouselImages.getResourceId(i,0);
            }

            carrouselImages.recycle();

            mCurrentImage++;

            mCarrouselImage = (ImageView) findViewById(R.id.imageView);

            if(mCurrentImage >= arrayLength)
            {
                mCurrentImage = 0;
            }
            mCarrouselImage.setImageResource(imageId[mCurrentImage]);
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

    // Method die het volgende carrouselplaatje aanroept.
	public void nextImage(View v) {
        runOnUiThread(Timer_Tick);

		//Reset de timer
		mCarrouselTimer.cancel();
		mCarrouselTimer = new Timer();
		mCarrouselTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				TimerMethod();
			}
		}, 7000, 7000);
	}
	// Method die het vorige carrouselplaatje aanroept.
	public void prevImage(View v) {
        TypedArray carrouselImages = getResources().obtainTypedArray(R.array.carrouselImages);
        int arrayLength = carrouselImages.length();

        int[] imageId = new int[arrayLength];

        for(int i = 0; i < arrayLength; i++)
        {
            imageId[i] = carrouselImages.getResourceId(i,0);
        }

        carrouselImages.recycle();

        mCarrouselImage = (ImageView) findViewById(R.id.imageView);

        mCurrentImage--;

        if(mCurrentImage <= -1)
        {
            mCurrentImage = arrayLength -1;
        }
        mCarrouselImage.setImageResource(imageId[mCurrentImage]);


		//Reset de timer
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

